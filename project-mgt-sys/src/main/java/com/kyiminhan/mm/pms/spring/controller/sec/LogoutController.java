package com.kyiminhan.mm.pms.spring.controller.sec;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kyiminhan.mm.pms.common.constant.URLConstant;
import com.kyiminhan.mm.pms.common.utils.AccountUtils;

@Controller
public class LogoutController {

	@GetMapping(value = URLConstant.LOGOUT)
	public String logoutPage(final HttpServletRequest request, final HttpServletResponse response) {

		AccountUtils.getInstance().doLogout(request, response);
		return URLConstant.REDIRECT + URLConstant.LOGIN;
	}
}