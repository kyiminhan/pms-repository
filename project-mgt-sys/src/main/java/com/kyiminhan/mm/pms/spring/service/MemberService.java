package com.kyiminhan.mm.pms.spring.service;

import com.kyiminhan.mm.pms.spring.entity.Member;

public interface MemberService extends BaseService<Member> {

	boolean hasMember();

	void createInitMember();

}
