package com.kyiminhan.mm.pms.spring.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.validation.Errors;

public interface BaseValidator {

	void validate(Object obj, Errors errors);

	/**
	 * Gets the voilations.
	 *
	 * @param obj the obj
	 * @return the voilations
	 */
	default Set<ConstraintViolation<Object>> getVoilations(final Object obj) {
		final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		final Validator validator = factory.getValidator();
		final Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);
		return constraintViolations;
	}

	/**
	 * Gets the voilations.
	 *
	 * @param obj  the obj
	 * @param type the type
	 * @return the voilations
	 */
	default Set<ConstraintViolation<Object>> getVoilations(final Object obj, final Class<?> type) {
		final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		final Validator validator = factory.getValidator();
		final Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj, type);
		return constraintViolations;
	}
}