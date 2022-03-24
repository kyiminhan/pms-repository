package com.kyiminhan.mm.pms.spring.config;

import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * The Class AppInitializer.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/25 </BR>
 *        project-mgt-sys system </BR>
 *        com.kyiminhan.mm.pms.spring.config </BR>
 *        AppInitializer.java </BR>
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.web.servlet.support.
	 * AbstractAnnotationConfigDispatcherServletInitializer#getRootConfigClasses()
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { HibernateConfig.class };
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.web.servlet.support.
	 * AbstractAnnotationConfigDispatcherServletInitializer#getServletConfigClasses(
	 * )
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#
	 * getServletMappings()
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#
	 * getServletFilters()
	 */
	@Override
	protected Filter[] getServletFilters() {
		final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding(StandardCharsets.UTF_8.name());
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] { characterEncodingFilter };
	}
}