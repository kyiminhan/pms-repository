package com.kyiminhan.mm.pms.spring.config.sec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.kyiminhan.mm.pms.common.constant.URLConstant;
import com.kyiminhan.mm.pms.common.type.InitPwdFlag;
import com.kyiminhan.mm.pms.common.type.Role;
import com.kyiminhan.mm.pms.spring.entity.Account;

@Component
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	protected void handle(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException, ServletException {

		final String targetUrl = this.determineTargetUrl(authentication);
		if (response.isCommitted()) {
			return;
		}
		this.getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(final Authentication authentication) {

		final Collection<String> roles = new ArrayList<>();
		authentication.getAuthorities().stream().forEach(auth -> roles.add(auth.getAuthority()));
		final Account user = (Account) authentication.getPrincipal();
		if (roles.stream().anyMatch(Role.hasAnyRole())) {
			return (!InitPwdFlag.INIT.equals(user.getInitPwdFlag())) ? URLConstant.HOME : URLConstant.INIT_PWD_CHANGE;
		} else {
			throw new IllegalStateException();
		}
	}
}