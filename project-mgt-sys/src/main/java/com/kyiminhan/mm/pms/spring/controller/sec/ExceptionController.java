package com.kyiminhan.mm.pms.spring.controller.sec;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kyiminhan.mm.pms.common.constant.MessageConstant;
import com.kyiminhan.mm.pms.common.constant.URLConstant;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionController {

	@ExceptionHandler(value = { org.springframework.security.access.AccessDeniedException.class })
	public String occurAccessDeniedException(final HttpServletRequest request, final HttpServletResponse response,
			final Exception e, final RedirectAttributes redirectAttributes) {

		ExceptionController.log.error("org.springframework.security.access.AccessDeniedException: ", e);
		redirectAttributes.getFlashAttributes().clear();
		redirectAttributes.addFlashAttribute(MessageConstant.ERRORS, MessageConstant.ACCESS_DENIED_ERROR);
		return URLConstant.REDIRECT + URLConstant.ACCESS_DENIED;
	}

	@ExceptionHandler(value = { org.springframework.web.HttpRequestMethodNotSupportedException.class })
	public String occurHttpRequestMethodNotSupportedException(final HttpServletRequest request,
			final HttpServletResponse response, final Exception e, final RedirectAttributes redirectAttributes) {

		ExceptionController.log.error("org.springframework.web.HttpRequestMethodNotSupportedException: ", e);
		redirectAttributes.getFlashAttributes().clear();
		redirectAttributes.addFlashAttribute(MessageConstant.ERRORS, MessageConstant.ACCESS_DENIED_ERROR);
		return URLConstant.REDIRECT + URLConstant.SYSTEM_ERROR;
	}

	@ExceptionHandler(value = { RuntimeException.class, Exception.class, DataAccessException.class })
	public String occurException(final HttpServletRequest request, final HttpServletResponse response,
			final Exception e, final RedirectAttributes redirectAttributes) {

		ExceptionController.log.error("System Exception occurs : ", e);
		redirectAttributes.getFlashAttributes().clear();
		redirectAttributes.addFlashAttribute(MessageConstant.ERRORS, MessageConstant.SYSTEM_SERROR);
		return URLConstant.REDIRECT + URLConstant.SYSTEM_ERROR;
	}
}