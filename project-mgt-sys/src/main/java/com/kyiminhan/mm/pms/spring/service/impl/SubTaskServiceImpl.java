package com.kyiminhan.mm.pms.spring.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyiminhan.mm.pms.common.constant.EntityConstant;
import com.kyiminhan.mm.pms.common.type.Progress;
import com.kyiminhan.mm.pms.spring.dao.BaseDao;
import com.kyiminhan.mm.pms.spring.dao.SearchMode;
import com.kyiminhan.mm.pms.spring.dao.TaskDao;
import com.kyiminhan.mm.pms.spring.entity.SubTask;
import com.kyiminhan.mm.pms.spring.entity.Task;
import com.kyiminhan.mm.pms.spring.service.SubTaskService;

import lombok.NonNull;
import lombok.Setter;

@Service
@Setter(onMethod = @__(@Autowired))
public class SubTaskServiceImpl extends BaseServiceImpl<SubTask> implements SubTaskService {

	private static final long serialVersionUID = 1L;

	private TaskDao taskDao;

	public SubTaskServiceImpl(@NonNull final BaseDao<SubTask> baseDao) {
		super(baseDao);
	}

	@Override
	public String save(final SubTask t) {
		final Task task = this.taskDao
				.findByProperties(this.commonUtils.crateMap(EntityConstant.ID, t.getTask().getId().toString()));
		t.setTask(task);
		return super.save(t);
	}

	@Override
	public String update(final SubTask t) {
		final Task task = this.taskDao
				.findByProperties(this.commonUtils.crateMap(EntityConstant.ID, t.getTask().getId().toString()));
		t.setTask(task);
		return super.update(t);
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<Task> findAllTask() {
		final Collection<Task> collections = this.taskDao.findAllBySameKey(
				this.commonUtils.crateSet(Progress.FINISHED, Progress.COMPLETE), EntityConstant.PROGRESS,
				SearchMode.NOT_EQUAL);
		return collections;
	}
}