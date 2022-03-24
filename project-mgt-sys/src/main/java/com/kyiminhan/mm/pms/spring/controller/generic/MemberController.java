package com.kyiminhan.mm.pms.spring.controller.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyiminhan.mm.pms.spring.entity.Member;
import com.kyiminhan.mm.pms.spring.service.BaseService;
import com.kyiminhan.mm.pms.spring.service.MemberService;

@Controller
@RequestMapping(path = "/member")
public class MemberController extends GenericBaseController<Member> {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private final MemberService memberService;

	@Autowired
	public MemberController(@Qualifier("memberServiceImpl") final BaseService<Member> baseService) {
		super(baseService);
		this.memberService = (MemberService) baseService;

	}
}