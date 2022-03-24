package com.kyiminhan.mm.pms.spring.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyiminhan.mm.pms.spring.dao.BaseDao;
import com.kyiminhan.mm.pms.spring.dao.QuestionDao;
import com.kyiminhan.mm.pms.spring.entity.Question;
import com.kyiminhan.mm.pms.spring.service.QuestionService;

import lombok.NonNull;

@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements QuestionService {

	private static final long serialVersionUID = 1L;

	private final QuestionDao questionDao;

	public QuestionServiceImpl(@NonNull final @Qualifier(value = "questionDaoImpl") BaseDao<Question> baseDao) {
		super(baseDao);
		this.questionDao = (QuestionDao) baseDao;
	}

	@Transactional(readOnly = true)
	@Override
	public Map<String, Object> findAllWithPaging(final int currentPage) {
		return this.questionDao.findAllWithPaging(currentPage);
	}
}