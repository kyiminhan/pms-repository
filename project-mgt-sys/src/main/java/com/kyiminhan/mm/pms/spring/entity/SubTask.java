package com.kyiminhan.mm.pms.spring.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.kyiminhan.mm.pms.common.constant.JSRMsg;
import com.kyiminhan.mm.pms.common.type.Progress;
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
public class SubTask extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsNull.class })
	@NotEmpty(message = JSRMsg.REQUIRED, groups = { IsEmpty.class })
	@NotBlank(message = JSRMsg.REQUIRED, groups = { IsBlank.class })
	@Size(max = 50, message = JSRMsg.MAX, groups = { MaxLength.class })
	@Column
	private String subTaskName;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsNull.class })
	@NotEmpty(message = JSRMsg.REQUIRED, groups = { IsEmpty.class })
	@NotBlank(message = JSRMsg.REQUIRED, groups = { IsBlank.class })
	@Size(max = 50, message = JSRMsg.MAX, groups = { MaxLength.class })
	@Lob
	@Column
	private String subTaskDescription;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsNull.class })
	@NotEmpty(message = JSRMsg.REQUIRED, groups = { IsEmpty.class })
	@NotBlank(message = JSRMsg.REQUIRED, groups = { IsBlank.class })
	@Size(max = 50, message = JSRMsg.MAX, groups = { MaxLength.class })
	@Lob
	@Column
	private String subTaskRemark;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsNull.class })
	@Enumerated
	@Column
	private Progress progress;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsNull.class })
	@Column
	private LocalDate planStartDate;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsNull.class })
	@Column
	private LocalDate planEndDate;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsNull.class })
	@Column
	private LocalDate actualStartDate;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsNull.class })
	@Column
	private LocalDate actualEndDate;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsConstraint.class })
	@ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = Task.class)
	@JoinColumn(nullable = false)
	private Task task;

	@Builder
	public SubTask(final Long id,
			@NotNull(message = "required", groups = IsNull.class) @NotEmpty(message = "required", groups = IsEmpty.class) @NotBlank(message = "required", groups = IsBlank.class) @Size(max = 50, message = "{max}", groups = MaxLength.class) final String subTaskName,
			@NotNull(message = "required", groups = IsNull.class) @NotEmpty(message = "required", groups = IsEmpty.class) @NotBlank(message = "required", groups = IsBlank.class) @Size(max = 50, message = "{max}", groups = MaxLength.class) final String subTaskDescription,
			@NotNull(message = "required", groups = IsNull.class) @NotEmpty(message = "required", groups = IsEmpty.class) @NotBlank(message = "required", groups = IsBlank.class) @Size(max = 50, message = "{max}", groups = MaxLength.class) final String subTaskRemark,
			@NotNull(message = "required", groups = IsNull.class) final Progress progress,
			@NotNull(message = "required", groups = IsNull.class) final LocalDate planStartDate,
			@NotNull(message = "required", groups = IsNull.class) final LocalDate planEndDate,
			@NotNull(message = "required", groups = IsNull.class) final LocalDate actualStartDate,
			@NotNull(message = "required", groups = IsNull.class) final LocalDate actualEndDate,
			@NotNull(message = "required", groups = IsConstraint.class) final Task task) {
		super(id);
		this.subTaskName = subTaskName;
		this.subTaskDescription = subTaskDescription;
		this.subTaskRemark = subTaskRemark;
		this.progress = progress;
		this.planStartDate = planStartDate;
		this.planEndDate = planEndDate;
		this.actualStartDate = actualStartDate;
		this.actualEndDate = actualEndDate;
		this.task = task;
	}

}