package com.kyiminhan.mm.pms.spring.controller.generic;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyiminhan.mm.pms.spring.entity.Project;
import com.kyiminhan.mm.pms.spring.entity.Task;
import com.kyiminhan.mm.pms.spring.service.BaseService;
import com.kyiminhan.mm.pms.spring.service.TaskService;

@Controller
@RequestMapping(path = "/task")
public class TaskController extends GenericBaseController<Task> {

	private static final long serialVersionUID = 1L;
	private final TaskService taskService;

	@Autowired
	public TaskController(@Qualifier("taskServiceImpl") final BaseService<Task> baseService) {
		super(baseService);
		this.taskService = (TaskService) baseService;

	}

	@ModelAttribute(value = "projects")
	public Collection<Project> getProjects() {
		Collection<Project> projects = new HashSet<>();
		projects = this.taskService.findAllProjects();
		return projects;
	}
}