package com.kyiminhan.mm.pms.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.kyiminhan.mm.pms.common.type.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AccountRole extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Account.class)
	@JoinColumn
	private Account account;

	@Builder
	public AccountRole(final Long id, @NotNull final Role role, @NotNull final Account account) {
		super(id);
		this.role = role;
		this.account = account;
	}
}
