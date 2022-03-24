package com.kyiminhan.mm.pms.spring.service.impl;

import org.springframework.stereotype.Service;

import com.kyiminhan.mm.pms.spring.dao.BaseDao;
import com.kyiminhan.mm.pms.spring.entity.Project;
import com.kyiminhan.mm.pms.spring.service.ProjectService;

import lombok.NonNull;

@Service
public class ProjectServiceImpl extends BaseServiceImpl<Project> implements ProjectService {

	private static final long serialVersionUID = 1L;

	public ProjectServiceImpl(@NonNull final BaseDao<Project> baseDao) {
		super(baseDao);
	}
}