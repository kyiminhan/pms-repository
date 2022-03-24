package com.kyiminhan.mm.pms.spring.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kyiminhan.mm.pms.common.utils.AccountUtils;

@Aspect
@Component
public class LoggingAop {

	private final String END = "End";
	private final String START = "Start";

	@Before("execution(* com.kyiminhan.mm.pms.spring.controller.*.*.*(..))")
	public void controllerBefore(final JoinPoint joinPoint) {
		this.methodLogForController(joinPoint, this.START);
	}

	@After("execution(* com.kyiminhan.mm.pms.spring.controller.*.*.*(..))")
	public void controllerAfter(final JoinPoint joinPoint) {
		this.methodLogForController(joinPoint, this.END);
	}

	@Around("execution(* com.kyiminhan.mm.pms.spring.controller.*.*.*(..))")
	public Object controllerAround(final ProceedingJoinPoint joinPoint) throws Throwable {
		return this.methodLog(joinPoint);
	}

	@Before("execution(* com.kyiminhan.mm.pms.spring.service.*.*(..))")
	public void serviceBefore(final JoinPoint joinPoint) {
		this.methodLog(joinPoint, this.START);
	}

	@After("execution(* com.kyiminhan.mm.pms.spring.service.*.*(..))")
	public void serviceAfter(final JoinPoint joinPoint) {
		this.methodLog(joinPoint, this.END);
	}

	@Around("execution(* com.kyiminhan.mm.pms.spring.service.*.*(..))")
	public Object serviceAround(final ProceedingJoinPoint joinPoint) throws Throwable {
		return this.methodLog(joinPoint);
	}

	@Before("execution(* com.kyiminhan.mm.pms.spring.dao.*.*(..))")
	public void daoBefore(final JoinPoint joinPoint) {
		this.methodLog(joinPoint, this.START);
	}

	@After("execution(* com.kyiminhan.mm.pms.spring.dao.*.*(..))")
	public void daoAfter(final JoinPoint joinPoint) {
		this.methodLog(joinPoint, this.END);
	}

	@Around("execution(* com.kyiminhan.mm.pms.spring.dao.*.*(..))")
	public Object daoAround(final ProceedingJoinPoint joinPoint) throws Throwable {
		return this.methodLog(joinPoint);
	}

	@Before("execution(* com.kyiminhan.mm.pms.spring.validator.*.*(..))")
	public void validatorBefore(final JoinPoint joinPoint) {
		this.methodLog(joinPoint, this.START);
	}

	@After("execution(* com.kyiminhan.mm.pms.spring.validator.*.*(..))")
	public void validatorAfter(final JoinPoint joinPoint) {
		this.methodLog(joinPoint, this.END);
	}

	@Around("execution(* com.kyiminhan.mm.pms.spring.validator.*.*(..))")
	public Object validatorAround(final ProceedingJoinPoint joinPoint) throws Throwable {
		return this.methodLog(joinPoint);
	}

	private void methodLog(final JoinPoint joinPoint, final String message) {
		final Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		final StringBuilder logMessage = new StringBuilder();
		logMessage.append(joinPoint.getSignature().getName());
		logMessage.append("()");
		logMessage.append("\t");
		logMessage.append(message);
		logMessage.append(".");
		logger.info(logMessage.toString());
	}

	private void methodLogForController(final JoinPoint joinPoint, final String message) {
		final Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		final StringBuilder logMessage = new StringBuilder();
		logMessage.append(joinPoint.getSignature().getName());
		logMessage.append("()");
		logMessage.append("\t");
		logMessage.append(message);
		logMessage.append(".");
		logMessage.append("\t");
		logMessage.append(AccountUtils.getInstance().getLoginUserIdLogMsg());
		logMessage.append("\t");
		logMessage.append(this.getRequestUrlMessage());
		logger.info(logMessage.toString());
	}

	private Object methodLog(final ProceedingJoinPoint joinPoint) throws Throwable {
		final StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		final Object retVal = joinPoint.proceed();
		stopWatch.stop();
		final Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		final StringBuilder logParameters = new StringBuilder();
		logParameters.append(joinPoint.getSignature().getName());
		logParameters.append("() ");
		logParameters.append("Method Parameter : ");
		final Object[] args = joinPoint.getArgs();
		for (final Object arg : args) {
			logParameters.append(arg).append(",");
		}
		if (args.length > 0) {
			logParameters.deleteCharAt(logParameters.length() - 1);
		}
		final StringBuilder logMessage = new StringBuilder();
		logMessage.append(joinPoint.getSignature().getName());
		logMessage.append("() execution time: ");
		logMessage.append(stopWatch.getTotalTimeMillis());
		logMessage.append(" ms.");
		logMessage.append("\t");
		logMessage.append(AccountUtils.getInstance().getLoginUserIdLogMsg());
		logger.info(logParameters.toString());
		logger.info(logMessage.toString());
		return retVal;
	}

	private String getRequestUrlMessage() {
		final String REQUEST_URL = "Rquest URL : ";
		final RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		if (RequestContextHolder.getRequestAttributes() != null) {
			final HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();
			final String requestURL = new StringBuilder("[").append(REQUEST_URL)
					.append(request.getRequestURL().toString()).append("]").toString();
			return new StringBuilder(requestURL).toString();
		}
		return new String();
	}
}