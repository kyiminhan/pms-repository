package com.kyiminhan.mm.pms.spring.controller.sec;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kyiminhan.mm.pms.common.constant.MessageConstant;
import com.kyiminhan.mm.pms.common.constant.URLConstant;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UnknowURLController {

	@GetMapping(value = { "/*", "/*/*", "/*/*/*/*", "/*/*/*/*/*", "/*/*/*/*/*/*", "/*/*/*/*/*/**" })
	public String unknowUrlErrorPage(final HttpServletRequest request, final HttpServletResponse response,
			final RedirectAttributes redirectAttributes) {

		UnknowURLController.log.error("********************UNKNOWN URL********************");
		final String REQUEST_URL = "Rquest URL : ";
		final String unknowRequestMessage = new StringBuilder().append("\t").append("[").append(REQUEST_URL)
				.append(request.getRequestURL()).append("]").toString();
		UnknowURLController.log.error(unknowRequestMessage);
		UnknowURLController.log.error("********************UNKNOWN URL********************");
		redirectAttributes.getFlashAttributes().clear();
		redirectAttributes.addFlashAttribute(MessageConstant.ERRORS, MessageConstant.UNKNOWN_URL);
		return URLConstant.REDIRECT + URLConstant.UNKNOWN_URL;
	}
}