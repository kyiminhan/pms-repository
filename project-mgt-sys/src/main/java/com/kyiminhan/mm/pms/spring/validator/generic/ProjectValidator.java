package com.kyiminhan.mm.pms.spring.validator.generic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.kyiminhan.mm.pms.spring.entity.Project;

import lombok.NonNull;

@Component
@Qualifier("projectValidator")
public class ProjectValidator extends AbstractGenericValidator<Project> {

	@Override
	protected Collection<Class<?>> setValidateGroup() {
		return null;
	}

	@Override
	protected Errors customValidate(@NonNull final Project obj, @NonNull final Errors errors) {
		return null;
	}
}