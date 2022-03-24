package com.kyiminhan.mm.pms.spring.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.kyiminhan.mm.pms.spring.dao.MemberDao;
import com.kyiminhan.mm.pms.spring.entity.Member;

import lombok.NonNull;

@Repository
public class MemberDaoImpl extends BaseDaoImpl<Member> implements MemberDao {

	private static final long serialVersionUID = 1L;

	@Override
	protected Member getDelObj(@NonNull final Member t) {
		return (Member) t.delete();
	}

	@Override
	protected String getUUID(final Member t) {
		return t.getUuid();
	}

	@Override
	public int count() {
		final CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
		final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		final Root<Member> root = criteriaQuery.from(this.clazz);
		criteriaQuery.select(criteriaBuilder.count(root));
		final int count = this.getSession().createQuery(criteriaQuery).getSingleResult().intValue();
		return count;
	}
}