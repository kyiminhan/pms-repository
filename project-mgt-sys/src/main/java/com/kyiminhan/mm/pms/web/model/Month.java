package com.kyiminhan.mm.pms.web.model;

import java.io.Serializable;
import java.util.Collection;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Month implements Serializable {

	private static final long serialVersionUID = 1L;

	private String monthName;
	private Collection<Day> days;
}