package com.kyiminhan.mm.pms.common.constant;

public interface URLConstant {

	String DEFAULT_URL = "/";
	String DEFAULT_MULTI_URL_1 = "/*";
	String DEFAULT_MULTI_URL_2 = "/**";
	String STATIC_PATHS = "/static/**";

	String REDIRECT = "redirect:";

	String LOGIN = "/login";
	String LOGOUT = "/logout";
	String HOME = "/home";

	String LIST = "/list";
	String EDIT = "/edit";
	String CREATE = "/create";
	String DETAIL = "/detail";
	String DELETE = "/delete";

	String EDIT_PARAM = "/{uuid}/edit";
	String DETAIL_PARAM = "/{uuid}/detail";
	String DELETE_PARAM = "/{uuid}/delete";
	String LIST_PAGEING = "/list/{page}";
	String DELETE_PARAM_PAGE_NO = "/{uuid}/delete/{page}";

	String UNKNOWN_URL = "/unknown-url";
	String ACCESS_DENIED = "/access-denied";
	String SYSTEM_ERROR = "/system-error";
	String INIT_PWD_CHANGE = "/init-pwd-change";
	String TOKEN_ERROR = "/token-error";

}