package com.kyiminhan.mm.pms.spring.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyiminhan.mm.pms.common.type.InitPwdFlag;
import com.kyiminhan.mm.pms.common.type.Role;
import com.kyiminhan.mm.pms.spring.dao.BaseDao;
import com.kyiminhan.mm.pms.spring.dao.MemberDao;
import com.kyiminhan.mm.pms.spring.entity.Account;
import com.kyiminhan.mm.pms.spring.entity.AccountPassword;
import com.kyiminhan.mm.pms.spring.entity.AccountRole;
import com.kyiminhan.mm.pms.spring.entity.Member;
import com.kyiminhan.mm.pms.spring.service.MemberService;

import lombok.NonNull;
import lombok.Setter;

@Service
public class MemberServiceImpl extends BaseServiceImpl<Member> implements MemberService {

	private static final long serialVersionUID = 1L;

	private final MemberDao memberDao;

	@Setter(onMethod = @__(@Autowired))
	private PasswordEncoder encoder;

	public MemberServiceImpl(@NonNull final BaseDao<Member> baseDao) {
		super(baseDao);
		this.memberDao = (MemberDao) baseDao;
	}

	@Override
	public String save(@NonNull final Member t) {
		final Account account = Account.builder().member(t).email(t.getEmail()).password("temppwd").accountRoles(null)
				.build();
		final Set<AccountRole> accountRoles = new HashSet<>();
		accountRoles.add(AccountRole.builder().account(account).role(Role.MEMBER).build());
		account.setAccountRoles(accountRoles);
		t.setAccount(account);
		return super.save(t);
	}

	@Transactional(readOnly = true)
	@Override
	public boolean hasMember() {
		return (this.memberDao.count() > 0) ? true : false;

	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void createInitMember() {
		final Member t = Member.builder().firstName("Kyi Min").lastName("Han").address("Tokyo, Japan")
				.email("user@gmail.com").build();
		final Account account = Account.builder().member(t).email(t.getEmail()).password(this.encoder.encode("user"))
				.initPwdFlag(InitPwdFlag.INIT).build();
		final Set<AccountRole> accountRoles = new HashSet<>();
		accountRoles.add(AccountRole.builder().account(account).role(Role.MEMBER).build());
		account.setAccountRoles(accountRoles);
		final Set<AccountPassword> accountPasswords = new HashSet<>();
		accountPasswords.add(AccountPassword.builder().account(account).password(account.getPassword()).build());
		account.setAccountPasswords(accountPasswords);
		t.setAccount(account);
		super.save(t);
	}

}