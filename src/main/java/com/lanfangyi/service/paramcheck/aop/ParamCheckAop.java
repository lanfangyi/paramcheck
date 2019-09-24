package com.lanfangyi.service.paramcheck.aop;

import com.lanfangyi.service.paramcheck.annotation.Check;
import com.lanfangyi.service.paramcheck.annotation.Valid;
import com.lanfangyi.service.paramcheck.annotation.ValidateBy;
import com.lanfangyi.service.paramcheck.aop.validate.ErrorLevelEnum;
import com.lanfangyi.service.paramcheck.aop.validate.ValidateResult;
import com.lanfangyi.service.paramcheck.exception.TypeMismatchException;
import com.lanfangyi.service.paramcheck.resp.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
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
public class ParamCheckAop {

    /**
     * 切面函数，只有加了@Valid注解的接口才会开启注解校验
     *
     * @param joinPoint 切点
     * @return Object 接口的返回值
     * @throws Throwable
     */
    @Around("@annotation(com.lanfangyi.service.paramcheck.annotation.Valid)")
    public Object valid(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取方法的入参
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        String[] parameterNames = signature.getParameterNames();
        //遍历取出方法的注解
        int index = 0;
        ValidateResult check = null;
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
        //判断是否参数不符合要求
        if (check != null) {
            //获取方法头上Valid注解的信息
            Valid valid = AnnotationUtils.findAnnotation(method, Valid.class);
            assert valid != null;
            boolean addErrLog = valid.addErrLog();
            Class clazz = valid.msgClass();
            if (!Valid.class.equals(clazz) && !StringUtils.isEmpty(valid.msgClassStaticField())) {
                Field declaredField = clazz.getDeclaredField(valid.msgClassStaticField().trim());
                boolean b = checkReturnType(method, declaredField.get(clazz));
                if (!b) {
                    //抛出类型不匹配异常
                    throw new TypeMismatchException();
                }
                if (addErrLog) {
                    addErrLog(valid.logMsg(), check.getValidMsg(), valid.logLevel());
                }
                return declaredField.get(clazz);
            } else {
                if (addErrLog) {
                    addErrLog(valid.logMsg(), check.getValidMsg(), valid.logLevel());
                }
                boolean setCodeAndMsg = valid.setCodeAndMsg();
                //判断返回值类型是否是BaseResponse或其子类
                if (setCodeAndMsg && BaseResponse.class.isAssignableFrom(method.getReturnType())) {
                    Object returnObj = method.getReturnType().newInstance();
                    if (null != returnObj) {
                        try {
                            Field message;
                            Field code;
                            if (method.getReturnType().equals(BaseResponse.class)) {
                                message = method.getReturnType().getDeclaredField("message");
                                code = method.getReturnType().getDeclaredField("code");
                            } else {
                                message = method.getReturnType().getSuperclass().getDeclaredField("message");
                                code = method.getReturnType().getSuperclass().getDeclaredField("code");
                            }
                            if (message == null) {
                                throw new RuntimeException("can not find message field");
                            }
                            if (code == null) {
                                throw new RuntimeException("can not find code field");
                            }
                            message.setAccessible(true);
                            message.set(returnObj, check.getValidMsg());

                            code.setAccessible(true);
                            code.set(returnObj, check.getCode());
                        } catch (Throwable throwable) {
                            log.error(throwable.getMessage());
                        }
                    }
                    return returnObj;
                } else {
                    return method.getReturnType().newInstance();
                }
            }
        }
        //方法放行
        return joinPoint.proceed();
    }

    /**
     * 根据注解校验入参
     *
     * @param annotation 校验的注解
     * @param param      参数
     * @param paramName  参数名
     * @return ValidateResult
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
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
            Object o = validatedClass.newInstance();
            Method valid = validatedClass.getDeclaredMethod("valid", Annotation.class, Object.class, String.class);
            if (valid == null) {
                return new ValidateResult();
            }
            validateResult = (ValidateResult) valid.invoke(o, annotation, param, paramName);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
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
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws InvocationTargetException
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
     * 记日志
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
                    log.error(logMsg);
                    break;
                default:
                    log.info(logMsg);
                    break;
            }
        }
    }

}
