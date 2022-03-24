package com.kyiminhan.mm.pms.spring.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyiminhan.mm.pms.common.constant.MessageConstant;
import com.kyiminhan.mm.pms.spring.dao.BaseDao;
import com.kyiminhan.mm.pms.spring.dao.SearchMode;
import com.kyiminhan.mm.pms.spring.service.BaseService;

import lombok.NonNull;

@Service
public abstract class BaseServiceImpl<T extends Serializable> extends BaseServiceConfig<T> implements BaseService<T> {

	private static final long serialVersionUID = 1L;

	public BaseServiceImpl(@NonNull final BaseDao<T> baseDao) {
		super(baseDao);
	}

	@Transactional(readOnly = true)
	@Override
	public List<T> findAll() {
		return this.baseDao.findAll();
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String save(@NonNull final T t) {
		this.baseDao.save(t);
		return MessageConstant.SUCCESSFULLY_SAVE;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String update(@NonNull final T t) {
		if (this.validate(t)) {
			this.baseDao.update(t);
			return MessageConstant.SUCCESSFULLY_UPDATE;
		}
		return MessageConstant.UNSUCCESSFULLY_UPDATE;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String remove(@NonNull final String uuid) {
		if (this.validate(uuid)) {
			this.baseDao.remove(this.baseDao.findByUUID(uuid));
			return MessageConstant.SUCCESSFULLY_DELETE;
		}
		return MessageConstant.UNSUCCESSFULLY_DELETE;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String delete(@NonNull final String uuid) {
		if (this.validate(uuid)) {
			this.baseDao.delete(this.baseDao.findByUUID(uuid));
			return MessageConstant.SUCCESSFULLY_DELETE;
		}
		return MessageConstant.UNSUCCESSFULLY_DELETE;
	}

	@Transactional(readOnly = true)
	@Override
	public T findByProperties(@NonNull final Map<String, Object> map) {
		return this.baseDao.findByProperties(map);
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<T> findAllByEqualMap(@NonNull final Map<String, Object> map) {
		return this.baseDao.findAllByEqualMap(map);
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<T> findAllByNotEqualMap(@NonNull final Map<String, Object> map) {
		return this.baseDao.findAllByNotEqualMap(map);
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<T> findAllBySameKey(@NonNull final Set<Object> set, @NonNull final Object key,
			@NonNull final SearchMode searchMode) {
		return this.baseDao.findAllBySameKey(set, key, searchMode);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateProperties(@NonNull final Map<String, Object> dataMap,
			@NonNull final Map<String, Object> conditionMap) {
		this.baseDao.updateProperties(dataMap, conditionMap);
	}
}