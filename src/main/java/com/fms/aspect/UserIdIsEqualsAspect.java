package com.fms.aspect;

import com.fms.Exception.UserIdCheckException;
import com.fms.constant.UserIdCheckConstant;
import com.fms.util.ThreadUserIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Aspect
@Component
@Slf4j
public class UserIdIsEqualsAspect {
    @Pointcut("execution(* com.fms.controller.*.*(..)) && @annotation(com.fms.annotation.UserIdCheck)")
    public void userIdIsEqualsPointCut(){};

    @Before("userIdIsEqualsPointCut()")
    public void userIdIsEqualsThreadUserId(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object arg = args[0];
        if (arg instanceof Integer) {
            //说明当前的参数就是id
            //直接检测
            Integer userId = (Integer) arg;
            check(userId);
            return;
        }
        //不然就说明userid存在于pojo类中
        Method method = null;
        try {
            method = arg.getClass().getDeclaredMethod(UserIdCheckConstant.GET_USER_ID);
            Integer userId = ((Integer) method.invoke(arg));
            check(userId);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private void check(Integer userId) {
        ThreadUserIdUtil.ThreadUserIdIsEqualsInputId(userId);
    }
}
