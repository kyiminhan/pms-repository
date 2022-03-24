package com.kyiminhan.mm.pms.common.type;

import java.util.function.Predicate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Enum Role.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/06 </BR>
 *        project-mgt-sys system </BR>
 *        com.kyiminhan.mm.pms.common.type </BR>
 *        Role.java </BR>
 */
@NoArgsConstructor
@AllArgsConstructor
public enum Role {

	/** The member. */
	MEMBER(0, "Member", "ROLE_MEMBER"),

	/** The leader. */
	LEADER(1, "Leader", "ROLE_LEADER"),

	/** The manager. */
	MANAGER(2, "Manager", "ROLE_MANAGER"),

	/** The admin. */
	ADMIN(3, "Admin", "ROLE_ADMIN");

	@Getter
	@Setter
	private int id;
	@Getter
	@Setter
	private String name_en;
	@Getter
	@Setter
	private String accRole;

	public static Predicate<String> hasAnyRole() {
		final Predicate<String> p1 = s -> s.equals(MEMBER.getAccRole()) || s.equals(LEADER.getAccRole())
				|| s.equals(MANAGER.getAccRole()) || s.equals(ADMIN.getAccRole());
		return p1;
	}
}