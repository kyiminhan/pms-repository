package com.kyiminhan.mm.pms.spring.validator.generic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.kyiminhan.mm.pms.spring.entity.Question;

import lombok.NonNull;

@Component
@Qualifier("questionValidator")
public class QuestionValidator extends AbstractGenericValidator<Question> {

	@Override
	protected Collection<Class<?>> setValidateGroup() {
		return null;
	}

	@Override
	protected Errors customValidate(@NonNull final Question obj, @NonNull final Errors errors) {
		return null;
	}
}