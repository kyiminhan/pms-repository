package com.kyiminhan.mm.pms.spring.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyiminhan.mm.pms.common.constant.EntityConstant;
import com.kyiminhan.mm.pms.common.type.Progress;
import com.kyiminhan.mm.pms.spring.dao.BaseDao;
import com.kyiminhan.mm.pms.spring.dao.ProjectDao;
import com.kyiminhan.mm.pms.spring.dao.SearchMode;
import com.kyiminhan.mm.pms.spring.entity.Project;
import com.kyiminhan.mm.pms.spring.entity.Task;
import com.kyiminhan.mm.pms.spring.service.TaskService;

import lombok.NonNull;
import lombok.Setter;

@Service
@Setter(onMethod = @__(@Autowired))
public class TaskServiceImpl extends BaseServiceImpl<Task> implements TaskService {

	private static final long serialVersionUID = 1L;

	private ProjectDao projectDao;

	public TaskServiceImpl(@NonNull final BaseDao<Task> baseDao) {
		super(baseDao);
	}

	@Override
	public String save(@NonNull final Task t) {
		final Project project = this.projectDao
				.findByProperties(this.commonUtils.crateMap(EntityConstant.ID, t.getProject().getId().toString()));
		t.setProject(project);
		return super.save(t);
	}

	@Override
	public String update(@NonNull final Task t) {
		final Project project = this.projectDao
				.findByProperties(this.commonUtils.crateMap(EntityConstant.ID, t.getProject().getId().toString()));
		t.setProject(project);
		return super.update(t);
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<Project> findAllProjects() {
		final Collection<Project> collections = this.projectDao.findAllBySameKey(
				this.commonUtils.crateSet(Progress.FINISHED, Progress.COMPLETE), EntityConstant.PROGRESS,
				SearchMode.NOT_EQUAL);
		return collections;
	}
}