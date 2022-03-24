package com.kyiminhan.mm.pms.spring.validator.generic;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.kyiminhan.mm.pms.spring.entity.Answer;
import com.kyiminhan.mm.pms.spring.validator.group.IsConstraint;

import lombok.NonNull;

@Component
@Qualifier("answerValidator")
public class AnswerValidator extends AbstractGenericValidator<Answer> {

	@Override
	protected Collection<Class<?>> setValidateGroup() {
		return Arrays.asList(new Class<?>[] { IsConstraint.class });
	}

	@Override
	protected Errors customValidate(@NonNull final Answer obj, @NonNull final Errors errors) {
		return null;
	}
}