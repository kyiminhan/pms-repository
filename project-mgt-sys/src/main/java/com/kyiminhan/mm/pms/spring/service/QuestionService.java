package com.kyiminhan.mm.pms.spring.service;

import java.util.Map;

import com.kyiminhan.mm.pms.spring.entity.Question;

public interface QuestionService extends BaseService<Question> {

	Map<String, Object> findAllWithPaging(int pageNumber);
}