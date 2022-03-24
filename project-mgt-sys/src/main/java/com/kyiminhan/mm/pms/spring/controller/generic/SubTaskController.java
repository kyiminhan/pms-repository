package com.kyiminhan.mm.pms.spring.controller.generic;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyiminhan.mm.pms.spring.entity.SubTask;
import com.kyiminhan.mm.pms.spring.entity.Task;
import com.kyiminhan.mm.pms.spring.service.BaseService;
import com.kyiminhan.mm.pms.spring.service.SubTaskService;

@Controller
@RequestMapping(path = "/subtask")
public class SubTaskController extends GenericBaseController<SubTask> {

	private static final long serialVersionUID = 1L;
	private final SubTaskService subTaskService;

	@Autowired
	public SubTaskController(@Qualifier("subTaskServiceImpl") final BaseService<SubTask> baseService) {
		super(baseService);
		this.subTaskService = (SubTaskService) baseService;

	}

	@ModelAttribute(value = "tasks")
	public Collection<Task> getProjects() {
		Collection<Task> tasks = new HashSet<>();
		tasks = this.subTaskService.findAllTask();
		return tasks;
	}
}