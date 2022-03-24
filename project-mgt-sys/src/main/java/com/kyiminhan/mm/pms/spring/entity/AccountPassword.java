package com.kyiminhan.mm.pms.spring.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AccountPassword extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	@NotBlank
	@Column
	private String password;
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Account.class)
	@JoinColumn
	private Account account;

	@Builder
	public AccountPassword(final Long id, @NotNull @NotEmpty @NotBlank final String password,
			@NotNull final Account account) {
		super(id);
		this.password = password;
		this.account = account;
	}

}