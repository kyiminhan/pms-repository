package com.kyiminhan.mm.pms.spring.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kyiminhan.mm.pms.common.type.InitPwdFlag;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Account extends BaseEntity implements UserDetails {

	private static final long serialVersionUID = 1L;
	@OneToOne(fetch = FetchType.EAGER, targetEntity = Member.class, optional = true)
	@MapsId
	private Member member;
	@NotNull
	@Column
	private String email;
	@Column
	private String loginId;
	@NotNull
	@Column
	private String password;
	@NotNull
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = AccountRole.class)
	private Set<AccountRole> accountRoles;
	@Enumerated(EnumType.STRING)
	@Column
	private InitPwdFlag initPwdFlag;
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = AccountPassword.class)
	private Set<AccountPassword> accountPasswords;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final Collection<GrantedAuthority> authorities = new HashSet<>();
		this.accountRoles.forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getRole().getAccRole())));
		return authorities;
	}

	public String getLoginUserName() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Builder
	public Account(final Long id, final Member member, @NotNull final String email, final String loginId,
			@NotNull final String password, @NotNull final Set<AccountRole> accountRoles,
			final InitPwdFlag initPwdFlag) {
		super(id);
		this.member = member;
		this.email = email;
		this.loginId = loginId;
		this.password = password;
		this.accountRoles = accountRoles;
		this.initPwdFlag = initPwdFlag;
	}
}