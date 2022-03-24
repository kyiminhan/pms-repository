package com.kyiminhan.mm.pms.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Issue extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@Column
	private String issueName;
	@Lob
	@Column
	private String description;
	@Lob
	@Column
	private String remark;

	@Builder
	public Issue(final Long id, final String issueName, final String description, final String remark) {
		super(id);
		this.issueName = issueName;
		this.description = description;
		this.remark = remark;
	}
}