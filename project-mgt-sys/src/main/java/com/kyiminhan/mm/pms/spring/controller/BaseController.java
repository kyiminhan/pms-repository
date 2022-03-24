package com.kyiminhan.mm.pms.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kyiminhan.mm.pms.common.constant.Constant;
import com.kyiminhan.mm.pms.common.constant.URLConstant;
import com.kyiminhan.mm.pms.common.utils.CommonUtils;
import com.kyiminhan.mm.pms.common.utils.MessageUtils;

import lombok.NonNull;
import lombok.Setter;

/**
 * The Class BaseController.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since Mar 9, 2019 </BR>
 *        project-mgt-sys system </BR>
 *        com.kyiminhan.mm.pms.spring.controller </BR>
 *        BaseController.java </BR>
 */
@Controller
@Setter(onMethod = @__(@Autowired))
public abstract class BaseController {

	/** The message utils. */
	protected MessageUtils messageUtils;

	/** The common utils. */
	protected CommonUtils commonUtils;

	/**
	 * Gets the message.
	 *
	 * @param messageCode the message code
	 * @return the message
	 */
	protected final String getMessage(@NonNull final String messageCode) {
		return this.messageUtils.getMessage(messageCode);
	}

	/**
	 * Redirect URL.
	 *
	 * @param url the url
	 * @return String
	 */
	protected final String redirectURL(@NonNull final String url) {
		return new StringBuilder(URLConstant.REDIRECT).append(url).toString();
	}

	/**
	 * Redirect URL.
	 *
	 * @param param the param
	 * @param url   the url
	 * @return String
	 */
	protected final String redirectURL(@NonNull final String param, @NonNull final String url) {
		return new StringBuilder(URLConstant.REDIRECT).append(param).append(Constant.SLASH).append(url).toString();
	}
}