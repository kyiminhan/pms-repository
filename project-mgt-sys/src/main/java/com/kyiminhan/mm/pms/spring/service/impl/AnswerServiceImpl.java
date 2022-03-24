package com.kyiminhan.mm.pms.spring.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyiminhan.mm.pms.common.constant.EntityConstant;
import com.kyiminhan.mm.pms.spring.dao.BaseDao;
import com.kyiminhan.mm.pms.spring.dao.QuestionDao;
import com.kyiminhan.mm.pms.spring.entity.Answer;
import com.kyiminhan.mm.pms.spring.entity.Question;
import com.kyiminhan.mm.pms.spring.service.AnswerService;

import lombok.NonNull;
import lombok.Setter;

@Service
@Setter(onMethod = @__(@Autowired))
public class AnswerServiceImpl extends BaseServiceImpl<Answer> implements AnswerService {

	private static final long serialVersionUID = 1L;

	private QuestionDao questionDao;

	public AnswerServiceImpl(@NonNull final BaseDao<Answer> baseDao) {
		super(baseDao);
	}

	@Override
	public String save(@NonNull final Answer t) {
		final Question question = this.questionDao
				.findByProperties(this.commonUtils.crateMap(EntityConstant.ID, t.getQuestion().getId().toString()));
		t.setQuestion(question);
		return super.save(t);
	}

	@Override
	public String update(@NonNull final Answer t) {
		final Question question = this.questionDao
				.findByProperties(this.commonUtils.crateMap(EntityConstant.ID, t.getQuestion().getId().toString()));
		t.setQuestion(question);
		return super.update(t);
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<Question> findAllQuestions() {
		final Collection<Question> collections = this.questionDao.findAll();
		return collections;
	}
}