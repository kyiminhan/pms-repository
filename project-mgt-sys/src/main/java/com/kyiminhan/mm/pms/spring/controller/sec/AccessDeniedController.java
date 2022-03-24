package com.kyiminhan.mm.pms.spring.controller.sec;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kyiminhan.mm.pms.common.constant.URLConstant;
import com.kyiminhan.mm.pms.common.constant.ViewConstant;
import com.kyiminhan.mm.pms.common.utils.AccountUtils;

@Controller
public class AccessDeniedController {

	@GetMapping(value = URLConstant.TOKEN_ERROR)
	public String tokenError(final Model model, final Principal user, final HttpServletRequest request,
			final HttpServletResponse response) {

		AccountUtils.getInstance().doLogout(request, response);
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		return ViewConstant.ACCESS_DENIED;
	}

	@GetMapping(value = URLConstant.ACCESS_DENIED)
	public String accessDenied(final Model model, final Principal user, final HttpServletRequest request,
			final HttpServletResponse response) {

		AccountUtils.getInstance().doLogout(request, response);
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		return ViewConstant.ACCESS_DENIED;
	}
}