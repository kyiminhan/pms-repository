package com.kyiminhan.mm.pms.spring.service.impl;

import org.springframework.stereotype.Service;

import com.kyiminhan.mm.pms.spring.dao.BaseDao;
import com.kyiminhan.mm.pms.spring.entity.Issue;
import com.kyiminhan.mm.pms.spring.service.IssueService;

import lombok.NonNull;

@Service
public class IssueServiceImpl extends BaseServiceImpl<Issue> implements IssueService {

	private static final long serialVersionUID = 1L;

	public IssueServiceImpl(@NonNull final BaseDao<Issue> baseDao) {
		super(baseDao);
	}
}