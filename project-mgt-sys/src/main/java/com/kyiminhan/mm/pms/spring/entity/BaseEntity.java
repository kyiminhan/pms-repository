package com.kyiminhan.mm.pms.spring.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.springframework.stereotype.Component;

import com.kyiminhan.mm.pms.common.type.DeleteFlag;
import com.kyiminhan.mm.pms.common.utils.CommonUtils;
import com.kyiminhan.mm.pms.common.utils.AccountUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * The Class BaseEntity.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/23 </BR>
 * project-mgt-sys system </BR>
 * com.kyiminhan.mm.mgt.spring.entity </BR>
 * BaseEntity.java </BR>
 */

/**
 * Gets the delete flag.
 *
 * @return the delete flag
 */
@Getter

/**
 * Sets the delete flag.
 *
 * @param deleteFlag the new delete flag
 */
@Setter

/*
 * (non-Javadoc)
 *
 * @see java.lang.Object#toString()
 */
@SuperBuilder
@Component
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The uuid. */
	@Column(insertable = true, nullable = false, updatable = true)
	private String uuid;

	/** The create user. */
	@Column(insertable = true, nullable = false, updatable = false)
	private String createUser;

	/** The create date. */
	@Column(insertable = true, nullable = false, updatable = false)
	private LocalDateTime createDate;

	/** The update user. */
	@Column(insertable = true, nullable = false, updatable = true)
	private String updateUser;

	/** The update date. */
	@Column(insertable = true, nullable = false, updatable = true)
	private LocalDateTime updateDate;

	/** The delete flag. */
	@Enumerated(EnumType.STRING)
	@Column
	private DeleteFlag deleteFlag;

	@Version
	@Column(nullable = false)
	private Long version;

	/**
	 * Instantiates a new base entity.
	 */
	public BaseEntity() {
		super();
		this.init();
	}

	/**
	 * Instantiates a new base entity.
	 *
	 * @param id the id
	 */
	public BaseEntity(final Long id) {
		super();
		if ((null != id) && (0 < id.longValue())) {
			this.id = id;
		}
		this.init();
	}

	/**
	 * Inits the.
	 */
	public void init() {
		this.deleteFlag = DeleteFlag.ACTIVE;
		this.createDate = LocalDateTime.now();
		this.updateDate = LocalDateTime.now();
		this.createUser = AccountUtils.getInstance().getLoginUserName();
		this.updateUser = AccountUtils.getInstance().getLoginUserName();
		this.uuid = CommonUtils.getInstance().generateUUID();
	}

	/**
	 * Removes the.
	 */
	public BaseEntity delete() {
		this.init();
		this.deleteFlag = DeleteFlag.DELETED;
		return this;
	}

	@PreUpdate
	public void preUpdate() {
		this.uuid = CommonUtils.getInstance().generateUUID();
	}
}