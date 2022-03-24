package com.kyiminhan.mm.pms.spring.controller.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyiminhan.mm.pms.spring.entity.Project;
import com.kyiminhan.mm.pms.spring.service.BaseService;
import com.kyiminhan.mm.pms.spring.service.ProjectService;

@Controller
@RequestMapping(path = "/project")
public class ProjectController extends GenericBaseController<Project> {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private final ProjectService projectService;

	@Autowired
	public ProjectController(@Qualifier("projectServiceImpl") final BaseService<Project> baseService) {
		super(baseService);
		this.projectService = (ProjectService) baseService;

	}
}