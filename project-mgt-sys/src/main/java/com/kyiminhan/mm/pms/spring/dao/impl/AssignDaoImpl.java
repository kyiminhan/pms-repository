package com.kyiminhan.mm.pms.spring.dao.impl;

import org.springframework.stereotype.Repository;

import com.kyiminhan.mm.pms.spring.dao.AssignDao;
import com.kyiminhan.mm.pms.spring.entity.Assign;

import lombok.NonNull;

@Repository
public class AssignDaoImpl extends BaseDaoImpl<Assign> implements AssignDao {

	private static final long serialVersionUID = 1L;

	@Override
	protected Assign getDelObj(@NonNull Assign t) {
		return (Assign) t.delete();
	}

	@Override
	protected String getUUID(Assign t) {
		return t.getUuid();
	}
}