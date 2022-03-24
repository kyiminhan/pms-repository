package com.kyiminhan.mm.pms.spring.config.sec;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kyiminhan.mm.pms.common.constant.EntityConstant;
import com.kyiminhan.mm.pms.common.constant.JSRMsg;
import com.kyiminhan.mm.pms.common.constant.MessageConstant;
import com.kyiminhan.mm.pms.common.constant.URLConstant;
import com.kyiminhan.mm.pms.common.utils.MessageUtils;
import com.kyiminhan.mm.pms.spring.config.ConfigConstant;
import com.kyiminhan.mm.pms.spring.entity.Account;
import com.kyiminhan.mm.pms.spring.service.AccountService;

import lombok.NonNull;
import lombok.Setter;

@Configuration
@EnableWebSecurity
@Setter(onMethod = @__({ @Autowired }))
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@ComponentScan(basePackages = { ConfigConstant.SCAN_SPRING_PKG, ConfigConstant.SCAN_COMMON_COMPONENT })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private AccountService accountService;

	private MessageUtils messageUtils;

	private SuccessHandler successHandler;

	private CustomAccessDeniedHandler accessDeniedHandler;

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private static final String[] IGNORE_MATCHERS = { URLConstant.LOGIN };
	// private static final String[] AUTH_MATCHERS = { URLConstant.DEFAULT_URL,
	// URLConstant.DEFAULT_MULTI_URL_1, };

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers(SecurityConfig.IGNORE_MATCHERS).permitAll().anyRequest().authenticated();
		http.formLogin().loginPage(URLConstant.LOGIN).loginProcessingUrl(URLConstant.LOGIN)
				.successHandler(this.successHandler).usernameParameter(EntityConstant.EMAIL)
				.passwordParameter(EntityConstant.PASSWORD).permitAll();
		http.logout().logoutUrl(URLConstant.LOGOUT).invalidateHttpSession(true).deleteCookies("JSESSIONID");
		http.exceptionHandling().accessDeniedHandler(this.accessDeniedHandler);
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		final DaoAuthenticationProvider provider = new DaoAuthenticationProvider() {
			@Override
			public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
				if (StringUtils.isBlank(authentication.getName())) {
					final String requiredMessage = SecurityConfig.this.messageUtils.getMessage(JSRMsg.REQUIRED,
							EntityConstant.EMAIL);
					throw new UsernameNotFoundException(requiredMessage);
				} else if (!ObjectUtils.anyNotNull(authentication.getCredentials())
						| StringUtils.isBlank(authentication.getCredentials().toString())) {
					final String requiredMessage = SecurityConfig.this.messageUtils.getMessage(JSRMsg.REQUIRED,
							EntityConstant.PASSWORD);
					throw new BadCredentialsException(requiredMessage);
				} else {
					try {
						return super.authenticate(authentication);
					} catch (final BadCredentialsException e) {
						throw new BadCredentialsException(
								SecurityConfig.this.messageUtils.getMessage(MessageConstant.APP_BADCREDENTIAL));
					}
				}
			}

			@Override
			public void setPasswordEncoder(final PasswordEncoder passwordEncoder) {
				super.setPasswordEncoder(SecurityConfig.this.getPasswordEncoder());
			}
		};
		provider.setUserDetailsService((@NonNull final String email) -> {
			final Account account = SecurityConfig.this.accountService.findByEmail(email);
			if (ObjectUtils.anyNotNull(account)) {
				return account;
			}
			throw new UsernameNotFoundException(MessageConstant.USER_NOT_FOUND);
		});
		auth.authenticationProvider(provider);
	}

	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring().antMatchers(URLConstant.STATIC_PATHS);
	}
}