package com.kyiminhan.mm.pms.spring.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import com.kyiminhan.mm.pms.common.type.Progress;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Assign extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@Column
	private Member member;
	@Column
	private Task task;
	@Column
	private SubTask subTask;
	@Column
	private Project project;
	@Lob
	@Column
	private String description;
	@Enumerated
	@Column
	private Progress progress;
	@Column
	private LocalDate planStartDate;
	@Column
	private LocalDate planEndDate;
	@Column
	private LocalDate actualStartDate;
	@Column
	private LocalDate actualEndDate;

	@Builder
	public Assign(final Long id, final Member member, final Task task, final SubTask subTask, final Project project,
			final String description, final Progress progress, final LocalDate planStartDate,
			final LocalDate planEndDate, final LocalDate actualStartDate, final LocalDate actualEndDate) {
		super(id);
		this.member = member;
		this.task = task;
		this.subTask = subTask;
		this.project = project;
		this.description = description;
		this.progress = progress;
		this.planStartDate = planStartDate;
		this.planEndDate = planEndDate;
		this.actualStartDate = actualStartDate;
		this.actualEndDate = actualEndDate;
	}
}