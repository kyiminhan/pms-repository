package com.kyiminhan.mm.pms.spring.entity;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;

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
public class Task extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsNull.class })
	@NotEmpty(message = JSRMsg.REQUIRED, groups = { IsEmpty.class })
	@NotBlank(message = JSRMsg.REQUIRED, groups = { IsBlank.class })
	@Size(max = 50, message = JSRMsg.MAX, groups = { MaxLength.class })
	@Column
	private String taskName;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsNull.class })
	@NotEmpty(message = JSRMsg.REQUIRED, groups = { IsEmpty.class })
	@NotBlank(message = JSRMsg.REQUIRED, groups = { IsBlank.class })
	@Size(max = 50, message = JSRMsg.MAX, groups = { MaxLength.class })
	@Lob
	@Column
	private String taskDescription;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsNull.class })
	@NotEmpty(message = JSRMsg.REQUIRED, groups = { IsEmpty.class })
	@NotBlank(message = JSRMsg.REQUIRED, groups = { IsBlank.class })
	@Size(max = 50, message = JSRMsg.MAX, groups = { MaxLength.class })
	@Lob
	@Column
	private String taskRemark;
	@NotNull(message = JSRMsg.REQUIRED, groups = { IsNull.class })
	@Enumerated(EnumType.STRING)
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
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Project.class, optional = true)
	@JoinColumn(nullable = false)
	private Project project;
	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = SubTask.class)
	@Where(clause = "deleteFlag = 'ACTIVE'")
	private Collection<SubTask> subTasks;

	@Builder
	public Task(final Long id,
			@NotNull(message = "required", groups = IsNull.class) @NotEmpty(message = "required", groups = IsEmpty.class) @NotBlank(message = "required", groups = IsBlank.class) @Size(max = 50, message = "{max}", groups = MaxLength.class) final String taskName,
			@NotNull(message = "required", groups = IsNull.class) @NotEmpty(message = "required", groups = IsEmpty.class) @NotBlank(message = "required", groups = IsBlank.class) @Size(max = 50, message = "{max}", groups = MaxLength.class) final String taskDescription,
			@NotNull(message = "required", groups = IsNull.class) @NotEmpty(message = "required", groups = IsEmpty.class) @NotBlank(message = "required", groups = IsBlank.class) @Size(max = 50, message = "{max}", groups = MaxLength.class) final String taskRemark,
			@NotNull(message = "required", groups = IsNull.class) final Progress progress,
			@NotNull(message = "required", groups = IsNull.class) final LocalDate planStartDate,
			@NotNull(message = "required", groups = IsNull.class) final LocalDate planEndDate,
			@NotNull(message = "required", groups = IsNull.class) final LocalDate actualStartDate,
			@NotNull(message = "required", groups = IsNull.class) final LocalDate actualEndDate,
			@NotNull(message = "required", groups = IsConstraint.class) final Project project,
			final Collection<SubTask> subTasks) {
		super(id);
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.taskRemark = taskRemark;
		this.progress = progress;
		this.planStartDate = planStartDate;
		this.planEndDate = planEndDate;
		this.actualStartDate = actualStartDate;
		this.actualEndDate = actualEndDate;
		this.project = project;
		this.subTasks = subTasks;
	}
}