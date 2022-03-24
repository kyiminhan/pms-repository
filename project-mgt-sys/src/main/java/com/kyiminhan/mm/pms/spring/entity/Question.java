package com.kyiminhan.mm.pms.spring.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;

import com.kyiminhan.mm.pms.common.constant.JSRMsg;
import com.kyiminhan.mm.pms.spring.validator.group.IsBlank;
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
public class Question extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsNull.class })
	@NotEmpty(message = JSRMsg.REQUIRED, groups = { IsEmpty.class })
	@NotBlank(message = JSRMsg.REQUIRED, groups = { IsBlank.class })
	@Size(max = 50, message = JSRMsg.MAX, groups = { MaxLength.class })
	@Column
	private String title;
	@Lob
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsNull.class })
	@NotEmpty(message = JSRMsg.REQUIRED, groups = { IsEmpty.class })
	@NotBlank(message = JSRMsg.REQUIRED, groups = { IsBlank.class })
	@Size(max = 2000, message = JSRMsg.MAX, groups = { MaxLength.class })
	@Column
	private String description;
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Answer.class)
	@Where(clause = "deleteFlag = 'ACTIVE'")
	private Collection<Answer> answers;

	@Builder
	public Question(final Long id,
			@NotNull(message = "required", groups = IsNull.class) @NotEmpty(message = "required", groups = IsEmpty.class) @NotBlank(message = "required", groups = IsBlank.class) @Size(max = 50, message = "{max}", groups = MaxLength.class) final String title,
			@NotNull(message = "required", groups = IsNull.class) @NotEmpty(message = "required", groups = IsEmpty.class) @NotBlank(message = "required", groups = IsBlank.class) @Size(max = 50, message = "{max}", groups = MaxLength.class) final String description,
			final Collection<Answer> answers) {
		super(id);
		this.title = title;
		this.description = description;
		this.answers = answers;
	}
}