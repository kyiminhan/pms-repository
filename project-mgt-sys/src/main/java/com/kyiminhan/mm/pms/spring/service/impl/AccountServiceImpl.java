package com.kyiminhan.mm.pms.spring.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyiminhan.mm.pms.common.constant.EntityConstant;
import com.kyiminhan.mm.pms.spring.dao.BaseDao;
import com.kyiminhan.mm.pms.spring.entity.Account;
import com.kyiminhan.mm.pms.spring.service.AccountService;

import lombok.NonNull;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {

	private static final long serialVersionUID = 1L;

	public AccountServiceImpl(@NonNull final BaseDao<Account> baseDao) {
		super(baseDao);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Account findByEmail(@NonNull final String email) {
		final Map<String, Object> map = new HashMap<>();
		map.put(EntityConstant.EMAIL, email);
		return super.findByProperties(map);
	}
}