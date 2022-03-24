package com.kyiminhan.mm.pms.spring.controller.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kyiminhan.mm.pms.common.constant.URLConstant;
import com.kyiminhan.mm.pms.common.constant.ViewConstant;
import com.kyiminhan.mm.pms.common.utils.AccountUtils;
import com.kyiminhan.mm.pms.spring.service.AccountService;

import lombok.Setter;

@Controller
@Setter(onMethod = @__(@Autowired))
public class InitPwdChangeController {

	@SuppressWarnings("unused")
	private AccountService accountService;

	@GetMapping(value = URLConstant.INIT_PWD_CHANGE)
	public String changeInitPassword(final Model model)
			throws org.springframework.security.access.AccessDeniedException {

		if (AccountUtils.getInstance().isInitPwdState()) {
			return ViewConstant.INIT_PWD_CHANGE;
		}
		throw new org.springframework.security.access.AccessDeniedException(URLConstant.INIT_PWD_CHANGE);
	}

	@PostMapping(value = URLConstant.INIT_PWD_CHANGE)
	public String changeInitPassword(@ModelAttribute("user") final User user, final BindingResult bindingResult,
			final Model model) {

		return ViewConstant.INIT_PWD_CHANGE;
	}
}