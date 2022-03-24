package com.kyiminhan.mm.pms.spring.validator.generic;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.kyiminhan.mm.pms.common.constant.EntityConstant;
import com.kyiminhan.mm.pms.common.constant.JSRMsg;
import com.kyiminhan.mm.pms.spring.entity.Project;
import com.kyiminhan.mm.pms.spring.entity.Task;
import com.kyiminhan.mm.pms.spring.validator.group.IsConstraint;

import lombok.NonNull;

@Component
@Qualifier("taskValidator")
public class TaskValidator extends AbstractGenericValidator<Task> {

	@Override
	protected Collection<Class<?>> setValidateGroup() {
		return Arrays.asList(new Class<?>[] { IsConstraint.class });
	}

	@Override
	protected void validateConstraintObj(@NonNull final Task obj, @NonNull final Errors errors) {
		final Project project = obj.getProject();
		if ((null == project.getId()) || (project.getId() <= 0)) {
			final String msg = this.messageUtils.getMessage(JSRMsg.REQUIRED, EntityConstant.PROJECT);
			errors.rejectValue("project", msg, msg);
		}
	}

	@Override
	protected Errors customValidate(@NonNull final Task obj, @NonNull final Errors errors) {
		return null;
	}
}