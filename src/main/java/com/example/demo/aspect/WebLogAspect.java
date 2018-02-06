package com.example.demo.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Result;
import com.example.demo.common.ExceptionHandle;

@Aspect
@Component
@EnableAspectJAutoProxy
public class WebLogAspect {
	private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

	@Autowired
    private ExceptionHandle exceptionHandle;
	
	@Pointcut("execution(public * com.example.demo.controller.*.*.*(..))")//要处理的方法，包名+类名+方法名
	public void log() {}
	
	
	@Before("log()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        // 记录下请求内容
        logger.info("URL : {}" , request.getRequestURL().toString());
        logger.info("HTTP_METHOD : {}" , request.getMethod());
        logger.info("IP : {}",request.getRemoteAddr());
        logger.info("CLASS_METHOD : {}" , joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : {}" , Arrays.toString(joinPoint.getArgs()));

    }

	
	@SuppressWarnings("unused")
	@Around("log()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Result result = null;
        try {
        } catch (Exception e) {
        	logger.info("CLASS_METHOD : {}" , proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." + proceedingJoinPoint.getSignature().getName());
            logger.info("ARGS : {}" , Arrays.toString(proceedingJoinPoint.getArgs()));
            return exceptionHandle.catchFromControler(e);
        }
        if(result == null){
            return proceedingJoinPoint.proceed();
        }else {
            return result;
        }
    }
	
	
	@After("log()")//无论Controller中调用方法以何种方式结束，都会执行
    public void doAfter(){
        logger.info("----doAfter-----------");
    }
	
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) throws Throwable {
        // 处理完请求，返回内容
        logger.info("response={}",JSONObject.toJSON(object));
    }
	
}
