package com.kyiminhan.mm.pms.spring.entity;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;

import com.kyiminhan.mm.pms.common.constant.JSRMsg;
import com.kyiminhan.mm.pms.common.type.Progress;
import com.kyiminhan.mm.pms.common.type.ProjectState;
import com.kyiminhan.mm.pms.spring.validator.group.IsBlank;
import com.kyiminhan.mm.pms.spring.validator.group.IsEmpty;
import com.kyiminhan.mm.pms.spring.validator.group.IsNull;
import com.kyiminhan.mm.pms.spring.validator.group.MaxLength;
import com.kyiminhan.mm.pms.spring.validator.group.MinMaxLength;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Project extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsNull.class })
	@NotEmpty(message = JSRMsg.REQUIRED, groups = { IsEmpty.class })
	@NotBlank(message = JSRMsg.REQUIRED, groups = { IsBlank.class })
	@Size(min = 5, max = 50, message = JSRMsg.MIN_MAX, groups = { MinMaxLength.class })
	@Column
	private String projectName;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsNull.class })
	@NotEmpty(message = JSRMsg.REQUIRED, groups = { IsEmpty.class })
	@NotBlank(message = JSRMsg.REQUIRED, groups = { IsBlank.class })
	@Size(max = 500, message = JSRMsg.MAX, groups = { MaxLength.class })
	@Lob
	@Column
	private String description;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsNull.class })
	@NotEmpty(message = JSRMsg.REQUIRED, groups = { IsEmpty.class })
	@NotBlank(message = JSRMsg.REQUIRED, groups = { IsBlank.class })
	@Size(max = 500, message = JSRMsg.MAX, groups = { MaxLength.class })
	@Lob
	@Column
	private String remark;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsNull.class })
	@Enumerated
	@Column
	private ProjectState projectState;
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
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Task.class)
	@Where(clause = "deleteFlag = 'ACTIVE'")
	private Collection<Task> tasks;

	@Builder
	public Project(final Long id,
			@NotNull(message = "required", groups = IsNull.class) @NotEmpty(message = "required", groups = IsEmpty.class) @NotBlank(message = "required", groups = IsBlank.class) @Size(max = 50, message = "{max}", groups = MaxLength.class) final String projectName,
			@NotNull(message = "required", groups = IsNull.class) @NotEmpty(message = "required", groups = IsEmpty.class) @NotBlank(message = "required", groups = IsBlank.class) @Size(max = 500, message = "{max}", groups = MaxLength.class) final String description,
			@NotNull(message = "required", groups = IsNull.class) @NotEmpty(message = "required", groups = IsEmpty.class) @NotBlank(message = "required", groups = IsBlank.class) @Size(max = 500, message = "{max}", groups = MaxLength.class) final String remark,
			@NotNull(message = "required", groups = IsNull.class) final ProjectState projectState,
			@NotNull(message = "required", groups = IsNull.class) final Progress progress,
			@NotNull(message = "required", groups = IsNull.class) final LocalDate planStartDate,
			@NotNull(message = "required", groups = IsNull.class) final LocalDate planEndDate,
			@NotNull(message = "required", groups = IsNull.class) final LocalDate actualStartDate,
			@NotNull(message = "required", groups = IsNull.class) final LocalDate actualEndDate,
			final Collection<Task> tasks) {
		super(id);
		this.projectName = projectName;
		this.description = description;
		this.remark = remark;
		this.projectState = projectState;
		this.progress = progress;
		this.planStartDate = planStartDate;
		this.planEndDate = planEndDate;
		this.actualStartDate = actualStartDate;
		this.actualEndDate = actualEndDate;
		this.tasks = tasks;
	}
}