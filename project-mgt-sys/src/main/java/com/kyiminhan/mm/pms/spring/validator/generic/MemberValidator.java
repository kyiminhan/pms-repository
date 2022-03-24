package com.kyiminhan.mm.pms.spring.validator.generic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.kyiminhan.mm.pms.spring.entity.Member;

import lombok.NonNull;

@Component
@Qualifier("memberValidator")
public class MemberValidator extends AbstractGenericValidator<Member> {

	@Override
	protected Collection<Class<?>> setValidateGroup() {
		return null;
	}

	@Override
	protected Errors customValidate(@NonNull final Member obj, @NonNull final Errors errors) {
		return null;
	}
}