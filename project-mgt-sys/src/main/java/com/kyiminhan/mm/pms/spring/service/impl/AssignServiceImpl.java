package com.kyiminhan.mm.pms.spring.service.impl;

import org.springframework.stereotype.Service;

import com.kyiminhan.mm.pms.spring.dao.BaseDao;
import com.kyiminhan.mm.pms.spring.entity.Assign;
import com.kyiminhan.mm.pms.spring.service.AssignService;

import lombok.NonNull;

@Service
public class AssignServiceImpl extends BaseServiceImpl<Assign> implements AssignService {

	private static final long serialVersionUID = 1L;

	public AssignServiceImpl(@NonNull final BaseDao<Assign> baseDao) {
		super(baseDao);
	}
}