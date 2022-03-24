package com.kyiminhan.mm.pms.web.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Plan implements Serializable {

	private static final long serialVersionUID = 1L;
	private LocalDate date;
	private boolean planEnable;
}