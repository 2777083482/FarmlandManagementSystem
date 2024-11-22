package com.fms.aspect;

import com.fms.exception.AutoFillException;
import com.fms.annotation.AutoFill;
import com.fms.constant.AutoFillConstant;
import com.fms.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    @Pointcut("execution(* com.fms.mapper.*.*(..)) && @annotation(com.fms.annotation.AutoFill)")
    public void autoFillPointCut() {
    }

    ;

    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("开始自动装配字段");
        MethodSignature signature = ((MethodSignature) joinPoint.getSignature());
        Method method = signature.getMethod();
        AutoFill annotation = method.getAnnotation(AutoFill.class);
        //判断是更新还是新建操作
        OperationType type = checkAddOrUpdate(annotation);
        //执行新建操作的字段填充（需要填充新建时间和更新时间）
        if (type.equals(OperationType.INSERT)) {
            insertFiledAutoFill(joinPoint);
        } else if (type.equals(OperationType.UPDATE)) {
            updateFiledAutoFill(joinPoint);
        }
    }

    private void updateFiledAutoFill(JoinPoint joinPoint) {
        //我们这里约定新建和更新操作只传入那个我们需要的对象
        Object[] args = joinPoint.getArgs();
        //拿到我们所要增强的对象
        Object entity = args[0];
        //获取当前的时间
        LocalDateTime now = LocalDateTime.now();
        try {
            //获取所需要执行的set方法
            Method setUpdatteTimeMethod = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
            //执行自动装配
            setUpdatteTimeMethod.invoke(entity, now);
        } catch (Exception e) {
            throw new AutoFillException(e.getMessage());
        }
    }

    private void insertFiledAutoFill(JoinPoint joinPoint) {
        //我们这里约定新建和更新操作只传入那个我们需要的对象
        Object[] args = joinPoint.getArgs();
        //拿到我们所要增强的对象
        Object entity = args[0];
        //获取当前的使劲啊
        LocalDateTime now = LocalDateTime.now();
        try {
            //获取所需要执行的set方法
            Method setCreateTimeMethod = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
            Method setUpdatteTimeMethod = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
            //执行自动装配
            setUpdatteTimeMethod.invoke(entity, now);
            setCreateTimeMethod.invoke(entity, now);
        } catch (Exception e) {
            throw new AutoFillException(e.getMessage());
        }
    }

    private OperationType checkAddOrUpdate(AutoFill annotation) {
        OperationType type = annotation.value();
        return type;
    }


}
