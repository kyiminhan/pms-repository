package com.kyiminhan.mm.pms.spring.validator;

import java.util.Collection;

import org.springframework.validation.Errors;

import lombok.NonNull;

/**
 * The Class AbstractBaseValidator.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since Mar 9, 2019 </BR>
 *        project-mgt-sys system </BR>
 *        com.kyiminhan.mm.pms.spring.validator </BR>
 *        AbstractBaseValidator.java </BR>
 */
public abstract class AbstractBaseValidator implements BaseValidator {

	/**
	 * Sets the validate group.
	 *
	 * @return Collection
	 */
	protected abstract Collection<Class<?>> setValidateGroup();

	/*
	 * (non-Javadoc)
	 *
	 * @see com.kyiminhan.mm.mgt.spring.validator.BaseValidator#validate(java.lang.
	 * Object)
	 */
	@Override
	public void validate(@NonNull final Object obj, @NonNull final Errors errors) {
	}
}