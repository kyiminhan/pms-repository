package com.kyiminhan.mm.pms.spring.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.kyiminhan.mm.pms.common.constant.EntityConstant;
import com.kyiminhan.mm.pms.common.constant.PagingConstant;
import com.kyiminhan.mm.pms.common.type.DeleteFlag;
import com.kyiminhan.mm.pms.spring.dao.QuestionDao;
import com.kyiminhan.mm.pms.spring.entity.Question;

import lombok.NonNull;

@Repository
public class QuestionDaoImpl extends BaseDaoImpl<Question> implements QuestionDao, PagingConstant {

	private static final long serialVersionUID = 1L;

	@Override
	protected Question getDelObj(@NonNull final Question t) {
		return (Question) t.delete();
	}

	@Override
	protected String getUUID(final Question t) {
		return t.getUuid();
	}

	@Override
	public Map<String, Object> findAllWithPaging(final int currentPage) {

		final int pageItemSize = PagingConstant.NUMBER_OF_ITEMS_PER_PAGE;
		final int firstResult = (currentPage > 0) ? (currentPage - 1) : 0;

		final CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
		final CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(this.clazz)));
		final int countResults = this.getSession().createQuery(countQuery).getSingleResult().intValue();
		final int totalPagesCount = (int) (Math.ceil(countResults / Double.valueOf(pageItemSize)));

		final Map<String, Object> map = new HashMap<>();
		Collection<Question> list = new ArrayList<>();
		if ((0 < currentPage) && (currentPage <= totalPagesCount)) {
			final Collection<Integer> totalPages = this.getPageCount(totalPagesCount, currentPage);

			map.put(PagingConstant.TOTAL_PAGES, totalPages);
			map.put(PagingConstant.PREVIOUS_PAGE, (currentPage > 0) ? currentPage - 1 : 0);
			map.put(PagingConstant.CURRENT_PAGE, currentPage);
			map.put(PagingConstant.NEXT_PAGE, (currentPage < totalPagesCount) ? currentPage + 1 : 0);

			final CriteriaQuery<Question> criteriaQuery = criteriaBuilder.createQuery(this.clazz);
			final Root<Question> root = criteriaQuery.from(this.clazz);
			criteriaQuery.select(root);
			final List<Predicate> predicates = new ArrayList<>();
			predicates.add(criteriaBuilder.equal(root.get(EntityConstant.DELETE_FLAG), DeleteFlag.ACTIVE));
			criteriaQuery.where(predicates.toArray(new Predicate[0])).distinct(true);
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get(EntityConstant.UPDATE_DATE)));
			list = this.getSession().createQuery(criteriaQuery).setFirstResult(pageItemSize * firstResult)
					.setMaxResults(pageItemSize).getResultList();

		}
		map.put(PagingConstant.PAGING_LIST, list);

		return map;
	}
}