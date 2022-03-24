package com.kyiminhan.mm.pms.spring.validator.generic;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.kyiminhan.mm.pms.common.constant.JSRMsg;
import com.kyiminhan.mm.pms.common.utils.MessageUtils;
import com.kyiminhan.mm.pms.spring.validator.group.IsBlank;
import com.kyiminhan.mm.pms.spring.validator.group.IsConstraint;
import com.kyiminhan.mm.pms.spring.validator.group.IsEmpty;
import com.kyiminhan.mm.pms.spring.validator.group.IsNull;
import com.kyiminhan.mm.pms.spring.validator.group.MaxLength;
import com.kyiminhan.mm.pms.spring.validator.group.MinLength;
import com.kyiminhan.mm.pms.spring.validator.group.MinMaxLength;

import lombok.NonNull;
import lombok.Setter;

/**
 * The Class AbstractGenericValidator.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @param <T> the generic type
 * @since Mar 9, 2019 </BR>
 *        project-mgt-sys system </BR>
 *        com.kyiminhan.mm.pms.spring.validator </BR>
 *        AbstractGenericValidator.java </BR>
 */
@Component
@Setter(onMethod = @__(@Autowired))
public abstract class AbstractGenericValidator<T extends Serializable> implements GenericValidator<T> {

	/** The message utils. */
	protected MessageUtils messageUtils;

	/**
	 * Sets the validate group.
	 *
	 * @return Collection
	 */
	protected abstract Collection<Class<?>> setValidateGroup();

	/**
	 * Custom validate.
	 *
	 * @param obj the obj
	 * @return Errors
	 */
	protected abstract Errors customValidate(T obj, Errors errors);

	private Set<Class<?>> defaultValidateGroup() {
		final Set<Class<?>> set = new LinkedHashSet<>();
		set.addAll(Arrays.asList(IsNull.class, IsEmpty.class, IsBlank.class, MinLength.class, MaxLength.class,
				MinMaxLength.class));
		final Collection<Class<?>> collection = this.setValidateGroup();
		if ((null != collection) && !collection.isEmpty()) {
			collection.forEach(clazz -> set.add(clazz));
		}
		return set;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.kyiminhan.mm.mgt.spring.validator.BaseValidator#validate(java.lang.
	 * Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void validate(@NonNull final Object obj, @NonNull final Errors errors) {
		final Set<String> fieldSet = this.getObjectFields((T) obj);
		final Set<Class<?>> clazzez = this.defaultValidateGroup();
		for (final Class<?> clazz : clazzez) {
			this.getVoilations(obj, clazz).forEach(cv -> {
				fieldSet.forEach(f -> {
					if (f.equals(cv.getPropertyPath().toString())) {
						final String msg = this.loadMessage(f, cv.getMessage(), clazz);
						errors.rejectValue(f, msg, msg);
					}
				});
			});
			if (errors.hasErrors() && (clazz != IsNull.class)) {
				return;
			}
		}
		if (!errors.hasErrors() && clazzez.contains(IsConstraint.class)) {
			this.validateConstraintObj((T) obj, errors);
		}
		if (!errors.hasErrors()) {
			this.customValidate((T) obj, errors);
		}
	}

	/**
	 * Validate constraint obj.
	 *
	 * @param obj    the obj
	 * @param errors the errors
	 */
	protected void validateConstraintObj(@NonNull final T obj, @NonNull final Errors errors) {
		return;
	}

	private Set<String> getObjectFields(@NonNull final T t) {

		final Field[] fields = t.getClass().getDeclaredFields();
		final Set<String> list = new HashSet<>();
		Arrays.asList(fields).forEach(field -> {
			field.setAccessible(true);
			list.add(field.getName());
		});
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String loadMessage(@NonNull final String arg, @NonNull final String message, @NonNull final Class clazz) {
		String defaultMessage = null;
		if (clazz.isAssignableFrom(IsNull.class)) {
			defaultMessage = this.messageUtils.getMessage(JSRMsg.REQUIRED, arg);
		} else if (clazz.isAssignableFrom(IsEmpty.class)) {
			defaultMessage = this.messageUtils.getMessage(JSRMsg.REQUIRED, arg);
		} else if (clazz.isAssignableFrom(IsBlank.class)) {
			defaultMessage = this.messageUtils.getMessage(JSRMsg.REQUIRED, arg);
		} else if (clazz.isAssignableFrom(MinLength.class)) {
			defaultMessage = this.messageUtils.getMessageWithArg(JSRMsg.MIN_LENGTH, this.tokenMsg(message));
		} else if (clazz.isAssignableFrom(MaxLength.class)) {
			defaultMessage = this.messageUtils.getMessageWithArg(JSRMsg.MAX_LENGTH, this.tokenMsg(message));
		} else if (clazz.isAssignableFrom(MinMaxLength.class)) {
			defaultMessage = this.messageUtils.getMessageWithArg(JSRMsg.MIN_MAX_LENGTH, this.tokenMsg(message));
		}
		return defaultMessage;
	}

	private String[] tokenMsg(@NonNull final String str) {
		final String[] messagesArgs = StringUtils.split(str, ",");
		return messagesArgs;
	}
}