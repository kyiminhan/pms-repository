package com.kyiminhan.mm.pms.spring.dao.impl;

import org.springframework.stereotype.Repository;

import com.kyiminhan.mm.pms.spring.dao.IssueDao;
import com.kyiminhan.mm.pms.spring.entity.Issue;

import lombok.NonNull;

@Repository
public class IssueDaoImpl extends BaseDaoImpl<Issue> implements IssueDao {

	private static final long serialVersionUID = 1L;

	@Override
	protected Issue getDelObj(@NonNull Issue t) {
		return (Issue) t.delete();
	}

	@Override
	protected String getUUID(Issue t) {
		return t.getUuid();
	}
}