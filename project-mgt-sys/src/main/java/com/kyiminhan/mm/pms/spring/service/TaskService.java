package com.kyiminhan.mm.pms.spring.service;

import java.util.Collection;

import com.kyiminhan.mm.pms.spring.entity.Project;
import com.kyiminhan.mm.pms.spring.entity.Task;

public interface TaskService extends BaseService<Task> {

	Collection<Project> findAllProjects();

}