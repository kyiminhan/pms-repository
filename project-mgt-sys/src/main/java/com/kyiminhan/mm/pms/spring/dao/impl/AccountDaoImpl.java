package com.kyiminhan.mm.pms.spring.dao.impl;

import org.springframework.stereotype.Repository;

import com.kyiminhan.mm.pms.spring.dao.AccountDao;
import com.kyiminhan.mm.pms.spring.entity.Account;

import lombok.NonNull;

@Repository
public class AccountDaoImpl extends BaseDaoImpl<Account> implements AccountDao {

	private static final long serialVersionUID = 1L;

	@Override
	protected Account getDelObj(@NonNull Account t) {
		return (Account) t.delete();
	}

	@Override
	protected String getUUID(Account t) {
		return t.getUuid();
	}
}