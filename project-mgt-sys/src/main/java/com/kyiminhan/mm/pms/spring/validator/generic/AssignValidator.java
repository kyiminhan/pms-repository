package com.kyiminhan.mm.pms.spring.validator.generic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.kyiminhan.mm.pms.spring.entity.Assign;

import lombok.NonNull;

@Component
@Qualifier("assignValidator")
public class AssignValidator extends AbstractGenericValidator<Assign> {

	@Override
	protected Collection<Class<?>> setValidateGroup() {
		return null;
	}

	@Override
	protected Errors customValidate(@NonNull final Assign obj, @NonNull final Errors errors) {
		return null;
	}
}