package com.kyiminhan.mm.pms.spring.service;

import com.kyiminhan.mm.pms.spring.entity.Account;

import lombok.NonNull;

public interface AccountService extends BaseService<Account> {

	Account findByEmail(@NonNull String email);

}