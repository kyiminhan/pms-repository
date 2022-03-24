package com.kyiminhan.mm.pms.spring.dao.impl;

import org.springframework.stereotype.Repository;

import com.kyiminhan.mm.pms.spring.dao.AnswerDao;
import com.kyiminhan.mm.pms.spring.entity.Answer;

import lombok.NonNull;

@Repository
public class AnswerDaoImpl extends BaseDaoImpl<Answer> implements AnswerDao {

	private static final long serialVersionUID = 1L;

	@Override
	protected Answer getDelObj(@NonNull Answer t) {
		return (Answer) t.delete();
	}

	@Override
	protected String getUUID(Answer t) {
		return t.getUuid();
	}
}