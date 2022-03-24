package com.kyiminhan.mm.pms.spring.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kyiminhan.mm.pms.common.constant.EntityConstant;
import com.kyiminhan.mm.pms.common.type.DeleteFlag;
import com.kyiminhan.mm.pms.spring.dao.BaseDao;
import com.kyiminhan.mm.pms.spring.dao.SearchMode;

import lombok.NonNull;
import lombok.Setter;

@Repository
public abstract class BaseDaoImpl<T extends Serializable> implements BaseDao<T> {

	private static final long serialVersionUID = 1L;

	/** The clazz. */
	protected Class<T> clazz;

	/** The session factory. */
	@Setter(onMethod = @__(@Autowired))
	private SessionFactory sessionFactory;

	protected abstract T getDelObj(@NonNull T t);

	protected abstract String getUUID(T t);

	/**
	 * Gets the session.
	 *
	 * @return the session
	 */
	protected Session getSession() {
		final Session session = this.sessionFactory.getCurrentSession();
		return (null != session) ? session : this.sessionFactory.openSession();
	}

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Override
	public void save(@NonNull final T t) {
		this.getSession().persist(t);
	}

	@Override
	public void update(@NonNull final T t) {
		this.getSession().merge(t);
	}

	@Override
	public void remove(@NonNull final T t) {
		this.getSession().remove(t);
	}

	@Override
	public void delete(@NonNull final T t) {
		this.getSession().merge(this.getDelObj(t));
	}

	@Override
	public T findByProperties(@NonNull final Map<String, Object> map) {
		try {
			final CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
			final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.clazz);
			final Root<T> root = criteriaQuery.from(this.clazz);
			criteriaQuery.select(root);
			final List<Predicate> predicates = new ArrayList<>();
			map.forEach((key, value) -> predicates.add(criteriaBuilder.equal(root.get(key), value)));
			predicates.add(criteriaBuilder.equal(root.get(EntityConstant.DELETE_FLAG), DeleteFlag.ACTIVE));
			criteriaQuery.where(predicates.toArray(new Predicate[0])).distinct(true);
			return this.getSession().createQuery(criteriaQuery).getSingleResult();
		} catch (final NoResultException e) {
			return null;
		}
	}

	@Override
	public List<T> findAll() {
		final CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
		final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.clazz);
		final Root<T> root = criteriaQuery.from(this.clazz);
		criteriaQuery.select(root);
		final List<Predicate> predicates = new ArrayList<>();
		predicates.add(criteriaBuilder.equal(root.get(EntityConstant.DELETE_FLAG), DeleteFlag.ACTIVE));
		criteriaQuery.where(predicates.toArray(new Predicate[0])).distinct(true);
		return this.getSession().createQuery(criteriaQuery).setHint(QueryHints.HINT_FETCH_SIZE, 50).getResultList();
	}

	@Override
	public Collection<T> findAllByEqualMap(@NonNull final Map<String, Object> map) {
		final CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
		final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.clazz);
		final Root<T> root = criteriaQuery.from(this.clazz);
		criteriaQuery.select(root);
		final List<Predicate> predicates = new ArrayList<>();
		predicates.add(criteriaBuilder.equal(root.get(EntityConstant.DELETE_FLAG), DeleteFlag.ACTIVE));
		map.forEach((key, value) -> predicates.add(criteriaBuilder.equal(root.get(key), value)));
		criteriaQuery.where(predicates.toArray(new Predicate[0])).distinct(true);
		return this.getSession().createQuery(criteriaQuery).getResultList();
	}

	@Override
	public Collection<T> findAllByNotEqualMap(@NonNull final Map<String, Object> map) {
		final CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
		final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.clazz);
		final Root<T> root = criteriaQuery.from(this.clazz);
		criteriaQuery.select(root);
		final List<Predicate> predicates = new ArrayList<>();
		predicates.add(criteriaBuilder.equal(root.get(EntityConstant.DELETE_FLAG), DeleteFlag.ACTIVE));
		map.forEach((key, value) -> predicates.add(criteriaBuilder.equal(root.get(key), value)));
		criteriaQuery.where(predicates.toArray(new Predicate[0])).distinct(true);
		return this.getSession().createQuery(criteriaQuery).getResultList();
	}

	@Override
	public Collection<T> findAllBySameKey(@NonNull final Set<Object> set, @NonNull Object key, SearchMode searchMode) {
		final CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
		final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.clazz);
		final Root<T> root = criteriaQuery.from(this.clazz);
		criteriaQuery.select(root);
		final List<Predicate> predicates = new ArrayList<>();
		predicates.add(criteriaBuilder.equal(root.get(EntityConstant.DELETE_FLAG), DeleteFlag.ACTIVE));
		switch (searchMode) {
		case EQUAL:
			set.forEach(value -> predicates.add(criteriaBuilder.equal(root.get(key.toString()), value)));
			break;
		case NOT_EQUAL:
			set.forEach(value -> predicates.add(criteriaBuilder.notEqual(root.get(key.toString()), value)));
			break;
		case LIKE:
			set.forEach(value -> predicates.add(criteriaBuilder.like(root.get(key.toString()), value.toString())));
			break;
		case NOT_LIKE:
			set.forEach(value -> predicates.add(criteriaBuilder.notLike(root.get(key.toString()), value.toString())));
			break;
		default:
			break;
		}
		criteriaQuery.where(predicates.toArray(new Predicate[0])).distinct(true);
		return this.getSession().createQuery(criteriaQuery).getResultList();
	}

	@Override
	public void updateProperties(@NonNull final Map<String, Object> dataMap,
			@NonNull final Map<String, Object> conditionMap) {
		final CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
		final CriteriaUpdate<T> update = criteriaBuilder.createCriteriaUpdate(this.clazz);
		final Root<T> root = update.from(this.clazz);
		dataMap.forEach((key, value) -> update.set(key, value));
		final List<Predicate> predicates = new ArrayList<>();
		conditionMap.forEach((key, value) -> predicates.add(criteriaBuilder.equal(root.get(key), value)));
		update.where(predicates.toArray(new Predicate[0]));
		this.getSession().createQuery(update).executeUpdate();
	}

	@Override
	public T findByUUID(String uuid) {
		try {
			final CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
			final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.clazz);
			final Root<T> root = criteriaQuery.from(this.clazz);
			criteriaQuery.select(root);
			final List<Predicate> predicates = new ArrayList<>();
			predicates.add(criteriaBuilder.equal(root.get(EntityConstant.UUID), uuid));
			predicates.add(criteriaBuilder.equal(root.get(EntityConstant.DELETE_FLAG), DeleteFlag.ACTIVE));
			criteriaQuery.where(predicates.toArray(new Predicate[0])).distinct(true);
			return this.getSession().createQuery(criteriaQuery).getSingleResult();
		} catch (final NoResultException e) {
			return null;
		}
	}

	@Override
	public T findByUUID(T t) {
		return this.findByUUID(this.getUUID(t));
	}

}