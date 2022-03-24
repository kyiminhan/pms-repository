package com.kyiminhan.mm.pms.spring.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyiminhan.mm.pms.common.utils.CommonUtils;
import com.kyiminhan.mm.pms.spring.dao.BaseDao;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public abstract class BaseServiceConfig<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	@NonNull
	protected final BaseDao<T> baseDao;

	@Setter(onMethod = @__(@Autowired))
	protected CommonUtils commonUtils;

	protected Boolean validate(@NonNull final String uuid) {
		return (null != this.baseDao.findByUUID(uuid)) ? true : false;
	}

	protected Boolean validate(@NonNull final T t) {
		return (null != this.baseDao.findByUUID(t)) ? true : false;

	}

}