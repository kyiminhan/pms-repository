package com.kyiminhan.mm.pms.spring.config.sec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.stereotype.Component;

import com.kyiminhan.mm.pms.common.constant.URLConstant;

@Component
public class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl {

	private final String LOGIN_TOKEN_ERROR = URLConstant.LOGIN + "?tokenError";

	@Override
	public void handle(final HttpServletRequest request, final HttpServletResponse response,
			final AccessDeniedException accessDeniedException) throws IOException, ServletException {

		if (response.isCommitted()) {
			return;
		}
		if ((accessDeniedException instanceof MissingCsrfTokenException)
				|| (accessDeniedException instanceof InvalidCsrfTokenException)) {
			new DefaultRedirectStrategy().sendRedirect(request, response,
					(StringUtils.equals(URLConstant.LOGIN, request.getServletPath()) ? this.LOGIN_TOKEN_ERROR
							: URLConstant.TOKEN_ERROR));
		} else {
			new DefaultRedirectStrategy().sendRedirect(request, response,
					(StringUtils.equals(URLConstant.LOGIN, request.getServletPath()) ? this.LOGIN_TOKEN_ERROR
							: URLConstant.ACCESS_DENIED));
		}
	}
}