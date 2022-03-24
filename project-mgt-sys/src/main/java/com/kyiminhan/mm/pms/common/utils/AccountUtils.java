package com.kyiminhan.mm.pms.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.kyiminhan.mm.pms.common.constant.Constant;
import com.kyiminhan.mm.pms.common.type.InitPwdFlag;
import com.kyiminhan.mm.pms.spring.entity.Account;

public class AccountUtils {

	private volatile static AccountUtils accountUtils;

	public static AccountUtils getInstance() {
		if (null == AccountUtils.accountUtils) {
			synchronized (AccountUtils.class) {
				AccountUtils.accountUtils = new AccountUtils();
			}
		}
		return AccountUtils.accountUtils;
	}

	private AccountUtils() {
		super();
	}

	public String getLoginUserIdLogMsg() {
		final String LOGIN_USER_LABEL = "Login User ID : ";
		final String userId = this.getLoginUserName();
		final String userIdMessage = new StringBuilder("[").append(LOGIN_USER_LABEL)
				.append((!Constant.SYSTEM.equals(userId)) ? userId : "Anonymous User : before login or after logout")
				.append("]").toString();
		return userIdMessage;
	}

	public Account getLoginAccount() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account userDetail = null;
		if (null != auth) {
			final Object principal = auth.getPrincipal();
			if (principal instanceof Account) {
				userDetail = (Account) principal;
			}
		}
		return userDetail;
	}

	public String getLoginUserName() {
		return (null != this.getLoginAccount()) ? this.getLoginAccount().getUsername() : Constant.SYSTEM;
	}

	public String getLoginUseRole() {
		return null;
	}

	public boolean isInitPwdState() {
		final InitPwdFlag flag = (null != this.getLoginAccount()) ? this.getLoginAccount().getInitPwdFlag() : null;
		return InitPwdFlag.INIT.equals(flag) ? true : false;
	}

	public boolean isAccountLogged() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return ((null != authentication) && !("anonymousUser").equals(authentication.getName())) ? true : false;
	}

	public void doLogout(final HttpServletRequest request, final HttpServletResponse response) {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if ((null != authentication) && !("anonymousUser").equals(authentication.getName())) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
	}
}