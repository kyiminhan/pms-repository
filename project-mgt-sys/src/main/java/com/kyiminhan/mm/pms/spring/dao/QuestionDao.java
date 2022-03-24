package com.kyiminhan.mm.pms.spring.dao;

import java.util.Map;

import com.kyiminhan.mm.pms.spring.entity.Question;

public interface QuestionDao extends BaseDao<Question> {

	Map<String, Object> findAllWithPaging(int currentPage);

}