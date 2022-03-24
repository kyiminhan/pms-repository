package com.kyiminhan.mm.pms.spring.dao;

import com.kyiminhan.mm.pms.spring.entity.Member;

public interface MemberDao extends BaseDao<Member> {

	int count();

}