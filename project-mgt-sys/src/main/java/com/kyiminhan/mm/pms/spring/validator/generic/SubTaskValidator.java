package com.kyiminhan.mm.pms.spring.validator.generic;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.kyiminhan.mm.pms.common.constant.EntityConstant;
import com.kyiminhan.mm.pms.common.constant.JSRMsg;
import com.kyiminhan.mm.pms.spring.entity.SubTask;
import com.kyiminhan.mm.pms.spring.entity.Task;
import com.kyiminhan.mm.pms.spring.validator.group.IsConstraint;

import lombok.NonNull;

@Component
@Qualifier("subTaskValidator")
public class SubTaskValidator extends AbstractGenericValidator<SubTask> {

	@Override
	protected Collection<Class<?>> setValidateGroup() {
		return Arrays.asList(new Class<?>[] { IsConstraint.class });
	}

	@Override
	protected Errors customValidate(@NonNull final SubTask obj, @NonNull final Errors errors) {
		return null;
	}

	@Override
	protected void validateConstraintObj(@NonNull final SubTask obj, @NonNull final Errors errors) {
		final Task task = obj.getTask();
		if ((null == task.getId()) || (task.getId() <= 0)) {
			final String msg = this.messageUtils.getMessage(JSRMsg.REQUIRED, EntityConstant.TASK);
			errors.rejectValue(EntityConstant.TASK, msg, msg);
		}
	}
}