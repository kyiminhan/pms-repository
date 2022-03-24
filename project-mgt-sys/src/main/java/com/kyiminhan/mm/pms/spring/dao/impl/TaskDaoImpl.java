package com.kyiminhan.mm.pms.spring.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.kyiminhan.mm.pms.spring.dao.TaskDao;
import com.kyiminhan.mm.pms.spring.entity.Task;

import lombok.NonNull;

@Repository
@Qualifier(value = "taskDaoImpl")
public class TaskDaoImpl extends BaseDaoImpl<Task> implements TaskDao {

	private static final long serialVersionUID = 1L;

	@Override
	protected Task getDelObj(@NonNull Task t) {
		return (Task) t.delete();
	}

	@Override
	protected String getUUID(Task t) {
		return t.getUuid();
	}
}