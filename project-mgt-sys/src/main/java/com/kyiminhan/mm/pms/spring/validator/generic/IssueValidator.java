package com.kyiminhan.mm.pms.spring.validator.generic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.kyiminhan.mm.pms.spring.entity.Issue;

import lombok.NonNull;

@Component
@Qualifier("issueValidator")
public class IssueValidator extends AbstractGenericValidator<Issue> {

	@Override
	protected Collection<Class<?>> setValidateGroup() {
		return null;
	}

	@Override
	protected Errors customValidate(@NonNull final Issue obj, @NonNull final Errors errors) {
		return null;
	}
}