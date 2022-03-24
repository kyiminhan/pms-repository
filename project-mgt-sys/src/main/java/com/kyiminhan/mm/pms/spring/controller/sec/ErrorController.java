package com.kyiminhan.mm.pms.spring.controller.sec;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kyiminhan.mm.pms.common.constant.URLConstant;
import com.kyiminhan.mm.pms.common.constant.ViewConstant;
import com.kyiminhan.mm.pms.common.utils.AccountUtils;

@Controller
public class ErrorController {

	@GetMapping(value = { URLConstant.UNKNOWN_URL })
	public String unknownURL(final HttpServletRequest request, final HttpServletResponse response) {

		AccountUtils.getInstance().doLogout(request, response);
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return ViewConstant.ERROR;
	}

	@GetMapping(value = { URLConstant.SYSTEM_ERROR })
	public String occurSystemException(final HttpServletRequest request, final HttpServletResponse response) {

		AccountUtils.getInstance().doLogout(request, response);
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return ViewConstant.ERROR;
	}
}