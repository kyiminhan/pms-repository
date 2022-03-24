package com.kyiminhan.mm.pms.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.kyiminhan.mm.pms.common.constant.JSRMsg;
import com.kyiminhan.mm.pms.spring.validator.group.IsBlank;
import com.kyiminhan.mm.pms.spring.validator.group.IsConstraint;
import com.kyiminhan.mm.pms.spring.validator.group.IsEmpty;
import com.kyiminhan.mm.pms.spring.validator.group.IsNull;
import com.kyiminhan.mm.pms.spring.validator.group.MaxLength;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Answer extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsNull.class })
	@NotEmpty(message = JSRMsg.REQUIRED, groups = { IsEmpty.class })
	@NotBlank(message = JSRMsg.REQUIRED, groups = { IsBlank.class })
	@Size(max = 50, message = JSRMsg.MAX, groups = { MaxLength.class })
	@Column
	private String answer;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsConstraint.class })
	@ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = Question.class)
	@JoinColumn(nullable = false)
	private Question question;

	@Builder
	public Answer(final Long id,
			@NotNull(message = "required", groups = IsNull.class) @NotEmpty(message = "required", groups = IsEmpty.class) @NotBlank(message = "required", groups = IsBlank.class) @Size(max = 50, message = "{max}", groups = MaxLength.class) final String answer,
			@NotNull(message = "required", groups = IsConstraint.class) final Question question) {
		super(id);
		this.answer = answer;
		this.question = question;
	}
}