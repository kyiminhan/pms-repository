package com.kyiminhan.mm.pms.spring.dao.impl;

import org.springframework.stereotype.Repository;

import com.kyiminhan.mm.pms.spring.dao.SubTaskDao;
import com.kyiminhan.mm.pms.spring.entity.SubTask;

import lombok.NonNull;

@Repository
public class SubTaskDaoImpl extends BaseDaoImpl<SubTask> implements SubTaskDao {

	private static final long serialVersionUID = 1L;

	@Override
	protected SubTask getDelObj(@NonNull SubTask t) {
		return (SubTask) t.delete();
	}

	@Override
	protected String getUUID(SubTask t) {
		return t.getUuid();
	}
}