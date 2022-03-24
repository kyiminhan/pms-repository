package com.kyiminhan.mm.pms.spring.controller.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyiminhan.mm.pms.spring.entity.Assign;
import com.kyiminhan.mm.pms.spring.service.AssignService;
import com.kyiminhan.mm.pms.spring.service.BaseService;

@Controller
@RequestMapping(path = "/assign")
public class AssignController extends GenericBaseController<Assign> {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private final AssignService assignService;

	@Autowired
	public AssignController(@Qualifier("assignServiceImpl") final BaseService<Assign> baseService) {
		super(baseService);
		this.assignService = (AssignService) baseService;

	}
}