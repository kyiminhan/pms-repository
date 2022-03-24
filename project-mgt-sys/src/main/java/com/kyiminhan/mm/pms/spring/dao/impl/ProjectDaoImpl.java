package com.kyiminhan.mm.pms.spring.dao.impl;

import org.springframework.stereotype.Repository;

import com.kyiminhan.mm.pms.spring.dao.ProjectDao;
import com.kyiminhan.mm.pms.spring.entity.Project;

import lombok.NonNull;

@Repository
public class ProjectDaoImpl extends BaseDaoImpl<Project> implements ProjectDao {

	private static final long serialVersionUID = 1L;

	@Override
	protected Project getDelObj(@NonNull Project t) {
		return (Project) t.delete();
	}

	@Override
	protected String getUUID(Project t) {
		return t.getUuid();
	}
}