package com.kyiminhan.mm.pms.common.utils;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import lombok.NonNull;
import lombok.Setter;

/**
 * The Class MessageUtils.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since Mar 9, 2019 </BR>
 *        project-mgt-sys system </BR>
*        com.kyiminhan.mm.pms.common.utils </BR>
 *        MessageUtils.java </BR>
 */
@Component
@Setter(onMethod = @__(@Autowired))
public class MessageUtils {

	/** The message source. */
	private MessageSource messageSource;

	/** The locale resolver. */
	private LocaleResolver localeResolver;

	/**
	 * Gets the locale.
	 *
	 * @return the locale
	 */
	private Locale getLocale() {
		final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (null != requestAttributes) {
			final HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
			return this.localeResolver.resolveLocale(httpServletRequest);
		}
		return Locale.US;
	}

	/**
	 * Gets the message.
	 *
	 * @param messageCode the message code
	 * @return the message
	 */
	public String getMessage(@NonNull final String messageCode) {
		return this.messageSource.getMessage(messageCode, null, this.getLocale());
	}

	/**
	 * Gets the message.
	 *
	 * @param messageCode      the message code
	 * @param messageParameter the message parameter
	 * @return the message
	 */
	public String getMessage(@NonNull final String messageCode, @NonNull final String messageParameter) {
		return this.messageSource.getMessage(messageCode, new String[] { this.getMessage(messageParameter) },
				this.getLocale());
	}

	/**
	 * Gets the message with arg.
	 *
	 * @param messageCode  the message code
	 * @param messagesArgs the messages args
	 * @return the message with arg
	 */
	public String getMessageWithArg(@NonNull final String messageCode, @NonNull final String[] messagesArgs) {
		return this.messageSource.getMessage(messageCode, messagesArgs, this.getLocale());
	}
}