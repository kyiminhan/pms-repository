package com.kyiminhan.mm.pms.spring.config;

public interface ConfigConstant {

	String PROPERTIES_PATH_DB = "classpath:db.properties";

	String PROPERTIES_PATH_APPLICATION = "classpath:application.properties";

	String SCAN_SPRING_PKG = "com.kyiminhan.mm.pms.spring";

	String SCAN_COMMON_COMPONENT = "com.kyiminhan.mm.pms.common";

	String SCAN_REPOSITORY = "com.kyiminhan.mm.pms.spring.dao";

	String SCAN_ENTITY = "com.kyiminhan.mm.pms.spring.entity";

	String RESOLVER_PREFIX = "/WEB-INF/views/";

	String RESOLVER_SUFFIX = ".html";

	String CONTENT_TYPE_TEXT_HTML_UTF8 = "text/html; charset=UTF-8";

	String PROPERTIES_PATH_i18n_MESSAGES = "i18n/messages";

	String LOCALE_PARAM = "lang";

	String LOCALE_COOKIE_NAME = "locale";

}