package com.kyiminhan.mm.pms.spring.service;

import java.util.Collection;

import com.kyiminhan.mm.pms.spring.entity.Answer;
import com.kyiminhan.mm.pms.spring.entity.Question;

public interface AnswerService extends BaseService<Answer> {

	Collection<Question> findAllQuestions();

}
