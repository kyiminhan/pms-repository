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
public class SubTask implements Serializable {

	private static final long serialVersionUID = 1L;
	private String subTaskName;
	private LocalDate startDate;
	private LocalDate endDate;
	private Collection<Plan> subTaskPlans;
}