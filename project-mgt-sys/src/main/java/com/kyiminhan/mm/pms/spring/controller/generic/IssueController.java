package com.kyiminhan.mm.pms.spring.controller.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyiminhan.mm.pms.spring.entity.Issue;
import com.kyiminhan.mm.pms.spring.service.BaseService;
import com.kyiminhan.mm.pms.spring.service.IssueService;

@Controller
@RequestMapping(path = "/issue")
public class IssueController extends GenericBaseController<Issue> {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private final IssueService issueService;

	@Autowired
	public IssueController(@Qualifier("issueServiceImpl") final BaseService<Issue> baseService) {
		super(baseService);
		this.issueService = (IssueService) baseService;

	}
}