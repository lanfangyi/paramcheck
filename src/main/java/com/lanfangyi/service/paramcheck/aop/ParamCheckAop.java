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

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.lanfangyi.service.paramcheck.aop.validate.ErrorLevelEnum.ERROR;

/**
 * 校验执行器
 *
 * @author haodf@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class ParamCheckAop {

    private static final String RETURN_TYPE_RPC_RESPONSE = "RpcResponse";

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
        String[] parameterNames = signature.getParameterNames();

        //校验
        ValidateResult check = getValidateResult(args, method.getParameterAnnotations(), parameterNames);
        Valid valid = AnnotationUtils.findAnnotation(method, Valid.class);
        assert valid != null;

        //参数不符合要求
        if (check != null) {
            //判断接口返回值类型是否为RpcResponse
            Class<?> returnType = method.getReturnType();
            if (!RETURN_TYPE_RPC_RESPONSE.equals(returnType.getSimpleName())) {
                throw new TypeMismatchException("接口返回值必须是RpcResponse类型!!!!");
            }

            //获取方法头上Valid注解的信息
            boolean addErrLog = valid.addErrLog();

            //记录校验错误日志
            if (addErrLog) {
                addErrLog(valid.logMsg(), check.getValidMsg(), valid.errLogLevel(),
                    method.getDeclaringClass().getSimpleName(), method.getName(), parameterNames, args);
            }

            return getReturnObject(args, method, check, valid, returnType);
        }
        //方法放行
        Object proceedReturnObj = joinPoint.proceed();

        //记录方法日志
        addMethodLog(args, proceedReturnObj, valid.methodLogLevel(), method.getName(), valid.addMethodLog());

        return proceedReturnObj;
    }

    private Object getReturnObject(Object[] args, Method method, ValidateResult check, Valid valid, Class<?> returnType)
        throws InstantiationException, IllegalAccessException {
        Object returnObj = returnType.newInstance();
        if (valid.setCodeAndMsg()) {
            if (null != returnObj) {
                try {
                    Field message = ReflectionUtils.findField(returnType, "msg");
                    Field code = ReflectionUtils.findField(returnType, "code");
                    if (message == null) {
                        log.error("can not find msg field");
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
                } catch (Exception e) {
                    log.error(e.toString());
                }
            }

            //记录方法日志
            addMethodLog(args, returnObj, valid.methodLogLevel(), method.getName(), valid.addMethodLog());

            return returnObj;
        } else {
            //记录方法日志
            addMethodLog(args, returnObj, valid.methodLogLevel(), method.getName(), valid.addMethodLog());

            return returnObj;
        }
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
        ValidateResult check;
        //遍历取出方法的注解
        int index = 0;
        for (Annotation[] annotations : parameterAnnotations) {
            index++;
            if (annotations.length > 0) {
                check = checkAnnotations(annotations, args[index - 1], parameterNames[index - 1]);
                if (null != check) {
                    return check;
                }
            }
        }
        return null;
    }

    private ValidateResult checkAnnotations(Annotation[] annotations, Object arg, String parameterName)
        throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ValidateResult check = null;
        for (Annotation annotation : annotations) {
            check = check(annotation, arg, parameterName);
            if (check != null) {
                break;
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
            //获得校验器
            Class validatedClass = validateBy.validatedClass();
            //拿到校验方法
            Method valid = ReflectionUtils.findMethod(validatedClass, "valid", Annotation.class, Object.class, String.class);
            if (valid == null) {
                log.error("annotation :{}, validator:{} , does not has valid method , please check it!!!",
                    annotation.annotationType().getName(), validatedClass.getName());
                return new ValidateResult();
            }
            //获得校验器的实例子对象
            Validateable validateable = (Validateable) validatedClass.newInstance();
            validateResult = (ValidateResult) ReflectionUtils.invokeMethod(valid, validateable, annotation, param, paramName);
        } catch (Exception e) {
            //记一个日志
            this.addLog(ERROR, e.toString());
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

        //获得参数的class
        Class<?> clazz = param.getClass();

        //反射拿到所有属性，不包括继承而来的属性
        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            //获得属性前面的注解
            Annotation[] declaredAnnotations = declaredField.getDeclaredAnnotations();
            if (declaredAnnotations != null && declaredAnnotations.length > 0) {
                declaredField.setAccessible(true);
                validateResult = this.checkAnnotations(declaredAnnotations, declaredField.get(param), declaredField.getName());
                if (null != validateResult) {
                    break;
                }
            }
        }
        return validateResult;
    }

    /**
     * 参数校验记日志
     *
     * @param userLogMsg  用户配置的日志信息
     * @param checkLogMsg 系统默认的日志信息
     * @param logLevel    日志级别
     */
    private void addErrLog(String userLogMsg, String checkLogMsg, ErrorLevelEnum logLevel, String methodClassName,
                           String methodName, String[] paramNames, Object[] args) {

        //拼接方法参数字符串
        List<String> paramList = new ArrayList<>();
        for (int i = 0; i < paramNames.length; i++) {
            Object arg = args[i];
            String paramName = paramNames[i];
            if (arg != null) {
                paramList.add(String.format("%s: %s", paramName, arg instanceof Serializable ? JSON.toJSONString(arg) : arg));
            } else {
                paramList.add(String.format("%s: null", paramName));
            }
        }

        String logMsg = "[Class: " + methodClassName + " method: " + methodName + "][params:" + paramList + "][checkLogMsg:" + checkLogMsg + "]";

        if (!StringUtils.isEmpty(userLogMsg)) {
            logMsg += "[userLogMsg:" + userLogMsg + "]";
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
            addLog(logLevel, "method name :" + methodName + ". params:" +
                JSON.toJSONString(args) + ". method return value :" + JSON.toJSONString(returnObj));
        }
    }

    /**
     * 记录日志
     *
     * @param logLevel 日志级别
     * @param logMsg   日志内容
     */
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
