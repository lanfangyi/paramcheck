package com.lanfangyi.service.paramcheck.aop;

import com.alibaba.fastjson.JSON;
import com.lanfangyi.service.paramcheck.annotation.Check;
import com.lanfangyi.service.paramcheck.annotation.Valid;
import com.lanfangyi.service.paramcheck.annotation.ValidateBy;
import com.lanfangyi.service.paramcheck.aop.validate.ErrorLevelEnum;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.aop.validate.Validateable;
import com.lanfangyi.service.paramcheck.exception.TypeMismatchException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.lanfangyi.service.paramcheck.aop.validate.ErrorLevelEnum.ERROR;

/**
 * 校验执行器
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class ParamCheckAop {

    private static final String RETURN_TYPE_VOID = "void";

    /**
     * 切面函数，只有加了@Valid注解的接口才会开启注解校验
     *
     * @param joinPoint 切点
     * @return Object 接口的返回值
     */
    @Around("@annotation(com.lanfangyi.service.paramcheck.annotation.Valid)")
    public Object valid(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取方法的入参
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //校验
        ValidateResult check = getValidateResult(args, method.getParameterAnnotations(), signature.getParameterNames());
        Valid valid = AnnotationUtils.findAnnotation(method, Valid.class);
        assert valid != null;

        //参数不符合要求
        if (check != null) {
            //获取方法头上Valid注解的信息
            boolean addErrLog = valid.addErrLog();

            //记录校验错误日志
            if (addErrLog) {
                addErrLog(valid.logMsg(), check.getValidMsg(), valid.errLogLevel());
            }

            Class clazz = valid.msgClass();
            if (!Valid.class.equals(clazz) && !StringUtils.isEmpty(valid.msgClassStaticField())) {
                Field declaredField = clazz.getDeclaredField(valid.msgClassStaticField().trim());
                boolean b = checkReturnType(method, declaredField.get(clazz));
                if (!b) {
                    //抛出类型不匹配异常
                    throw new TypeMismatchException();
                }
                //记录方法日志
                addMethodLog(args, declaredField.get(clazz), valid.methodLogLevel(), method.getName(), valid.addMethodLog());

                return declaredField.get(clazz);
            } else {
                //判断返回值类型是否是BaseResponse或其子类
                Class<?> returnType = method.getReturnType();
                if (valid.setCodeAndMsg() && !RETURN_TYPE_VOID.equals(returnType.getName())) {
                    Object returnObj = returnType.newInstance();
                    if (null != returnObj) {
                        try {
                            // TODO: 2019/10/22 字段名做成可配置的，别的公司的框架不一定用msg这个名字
                            Field message = ReflectionUtils.findField(returnType, "message");
                            Field code = ReflectionUtils.findField(returnType, "code");
                            if (message == null) {
                                log.error("can not find message field");
                            } else {
                                message.setAccessible(true);
                                message.set(returnObj, check.getValidMsg());
                            }
                            if (code == null) {
                                log.error("can not find code field");
                            } else {
                                code.setAccessible(true);
                                code.set(returnObj, check.getCode());
                            }
                        } catch (Throwable throwable) {
                            log.error(throwable.getMessage());
                        }
                    }

                    //记录方法日志
                    addMethodLog(args, returnObj, valid.methodLogLevel(), method.getName(), valid.addMethodLog());

                    return returnObj;
                } else {
                    if (!RETURN_TYPE_VOID.equals(returnType.getName())) {

                        Object returnObj = returnType.newInstance();

                        //记录方法日志
                        addMethodLog(args, returnObj, valid.methodLogLevel(), method.getName(), valid.addMethodLog());

                        return returnObj;
                    }
                    return null;
                }
            }
        }
        //方法放行
        Object proceedReturnObj = joinPoint.proceed();

        //记录方法日志
        addMethodLog(args, proceedReturnObj, valid.methodLogLevel(), method.getName(), valid.addMethodLog());

        return proceedReturnObj;
    }

    /**
     * 校验参数
     *
     * @param args                 方法的所有入参
     * @param parameterAnnotations 方法的所有注解
     * @param parameterNames       方法的所有参数的名字
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private ValidateResult getValidateResult(Object[] args, Annotation[][] parameterAnnotations, String[] parameterNames)
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ValidateResult check = null;
        //遍历取出方法的注解
        int index = 0;
        checkFor:
        for (Annotation[] annotations : parameterAnnotations) {
            index++;
            if (annotations.length == 0) {
                continue;
            }
            for (Annotation annotation : annotations) {
                check = check(annotation, args[index - 1], parameterNames[index - 1]);
                if (check != null) {
                    break checkFor;
                }
            }
        }
        return check;
    }

    /**
     * 根据注解校验入参
     *
     * @param annotation 校验的注解
     * @param param      参数
     * @param paramName  参数名
     * @return ValidateResult
     */
    private ValidateResult check(Annotation annotation, Object param, String paramName) throws InvocationTargetException,
        NoSuchMethodException, InstantiationException, IllegalAccessException {
        ValidateResult validateResult;
        if (annotation == null) {
            return null;
        }
        //获取annotation的校验类
        ValidateBy validateBy = annotation.annotationType().getDeclaredAnnotation(ValidateBy.class);

        //如果不存在ValidateBy, 则不是我们校验体系的注解。
        if (validateBy == null) {
            return null;
        }
        //如果是@Check注解，则校验的是实体内部的属性
        if (annotation instanceof Check) {
            return checkEntity(param);
        }
        try {
            Class validatedClass = validateBy.validatedClass();
            Method valid = ReflectionUtils.findMethod(validatedClass, "valid", Annotation.class, Object.class, String.class);
            if (valid == null) {
                return new ValidateResult();
            }
            //获得校验器的实例子对象
            Validateable validateable = (Validateable) validatedClass.newInstance();
            validateResult = (ValidateResult) ReflectionUtils.invokeMethod(valid, validateable, annotation, param, paramName);
        } catch (Exception e) {
            //记一个日志
            addErrLog(null, e.toString(), ERROR);
            throw e;
        }
        return validateResult;
    }

    /**
     * 递归校验实体属性
     *
     * @param param 实体实例对象
     * @return ValidateResult
     */
    private ValidateResult checkEntity(Object param) throws IllegalAccessException, NoSuchMethodException,
        InstantiationException, InvocationTargetException {
        ValidateResult validateResult = null;
        Class<?> clazz = param.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        checkFor:
        for (Field declaredField : declaredFields) {
            Annotation[] declaredAnnotations = declaredField.getDeclaredAnnotations();
            if (declaredAnnotations != null && declaredAnnotations.length > 0) {
                for (Annotation declaredAnnotation : declaredAnnotations) {
                    //递归校验，实体嵌套实体
                    declaredField.setAccessible(true);
                    ValidateResult check = check(declaredAnnotation, declaredField.get(param), declaredField.getName());
                    if (check != null) {
                        validateResult = check;
                        break checkFor;
                    }
                }
            }
        }
        return validateResult;
    }

    /**
     * 校验返回类型是否正确
     *
     * @param method     方法对象
     * @param returnType 返回类型
     * @return boolean
     */
    private boolean checkReturnType(Method method, Object returnType) {
        return method.getReturnType().equals(returnType.getClass());
    }

    /**
     * 参数校验记日志
     *
     * @param userLogMsg  用户配置的日志信息
     * @param checkLogMsg 系统默认的日志信息
     * @param logLevel    日志级别
     */
    private void addErrLog(String userLogMsg, String checkLogMsg, ErrorLevelEnum logLevel) {
        String logMsg;
        if (!StringUtils.isEmpty(userLogMsg)) {
            logMsg = userLogMsg;
        } else {
            logMsg = checkLogMsg;
        }
        addLog(logLevel, logMsg);
    }

    /**
     * 方法日志
     *
     * @param args         方法的入参
     * @param returnObj    方法的返回值
     * @param logLevel     日志级别
     * @param methodName   方法名
     * @param addMethodLog 是否添加日志
     */
    private void addMethodLog(Object[] args, Object returnObj, ErrorLevelEnum logLevel, String methodName, boolean addMethodLog) {
        if (addMethodLog) {
            addLog(logLevel, "method name :" + methodName + ". params:" + JSON.toJSONString(args) + ". method return value :" + JSON.toJSONString(returnObj));
        }
    }

    private void addLog(ErrorLevelEnum logLevel, String logMsg) {
        if (!StringUtils.isEmpty(logMsg)) {
            switch (logLevel) {
                case INFO:
                    log.info(logMsg);
                    break;
                case WARN:
                    log.warn(logMsg);
                    break;
                case DEBUG:
                    log.debug(logMsg);
                    break;
                case ERROR:
                case FATAL:
                    log.error(logMsg);
                    break;
                default:
                    log.info(logMsg);
                    break;
            }
        }
    }

}
