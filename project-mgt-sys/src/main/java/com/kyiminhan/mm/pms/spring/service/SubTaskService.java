package com.kyiminhan.mm.pms.spring.service;

import java.util.Collection;

import com.kyiminhan.mm.pms.spring.entity.SubTask;
import com.kyiminhan.mm.pms.spring.entity.Task;

public interface SubTaskService extends BaseService<SubTask> {

	Collection<Task> findAllTask();

}