package com.kyiminhan.mm.pms.spring.controller.sec;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kyiminhan.mm.pms.common.constant.URLConstant;
import com.kyiminhan.mm.pms.common.constant.ViewConstant;
import com.kyiminhan.mm.pms.common.utils.AccountUtils;
import com.kyiminhan.mm.pms.spring.service.MemberService;

import lombok.Setter;

@Controller
@Setter(onMethod = @__(@Autowired))
public class LoginController {

	private MemberService memberService;

	@GetMapping(value = URLConstant.LOGIN)
	public String login(final HttpServletRequest request, final HttpServletResponse response) {

		if (!this.memberService.hasMember()) {
			this.memberService.createInitMember();
		}

		if (AccountUtils.getInstance().isAccountLogged()) {
			AccountUtils.getInstance().doLogout(request, response);
		}
		return ViewConstant.lOGIN;
	}
}