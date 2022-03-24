package com.kyiminhan.mm.pms.web.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;
	private String taskName;
	private LocalDate startDate;
	private LocalDate endDate;
	private Collection<SubTask> subTasks;
	private Collection<Plan> taskPlans;
}