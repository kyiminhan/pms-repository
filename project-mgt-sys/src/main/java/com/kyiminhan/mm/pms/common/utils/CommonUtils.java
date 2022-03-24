package com.kyiminhan.mm.pms.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.uuid.Generators;
import com.kyiminhan.mm.pms.common.constant.Constant;
import com.kyiminhan.mm.pms.common.type.Gender;

import lombok.NonNull;

/**
 * The Class CommonUtils.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since Mar 8, 2019 </BR>
 *        project-mgt-sys system </BR>
 *        com.kyiminhan.mm.pms.common.utils </BR>
 *        CommonUtils.java </BR>
 */
@Component
public class CommonUtils {

	/** The common utils. */
	private volatile static CommonUtils commonUtils;

	/**
	 * Gets the single instance of CommonUtils.
	 *
	 * @return single instance of CommonUtils
	 */
	public static CommonUtils getInstance() {
		if (null == CommonUtils.commonUtils) {
			synchronized (CommonUtils.class) {
				CommonUtils.commonUtils = new CommonUtils();
			}
		}
		return CommonUtils.commonUtils;
	}

	/**
	 * Instantiates a new common utils.
	 */
	private CommonUtils() {
		super();
	}

	/**
	 * Gets the genders.
	 *
	 * @return the genders
	 */
	public List<Gender> getGenders() {
		final List<Gender> gender = new ArrayList<>();
		Arrays.asList(Gender.values()).forEach(value -> gender.add(value));
		return gender;
	}

	/**
	 * Crate map.
	 *
	 * @param data the data
	 * @return Map
	 */
	public Map<String, Object> crateMap(@NonNull final String... data) {
		final Map<String, Object> map = new HashMap<>();
		final List<String> list = Arrays.asList(data);
		if (0 == (list.size() % 2)) {
			for (int i = 0; i < list.size(); i += 2) {
				map.put(list.get(i), list.get(i + 1));
			}
		}
		return map;
	}

	/**
	 * Crate set.
	 *
	 * @param data the data
	 * @return Set
	 */
	public Set<Object> crateSet(@NonNull final Object... data) {
		final Set<Object> set = new HashSet<>();
		Arrays.asList(data).forEach(d -> set.add(d));
		return set;
	}

	/**
	 * Generate UUID.
	 *
	 * @return String
	 */
	public String generateUUID() {
		return Generators.timeBasedGenerator().generate().toString().replace(Constant.HYPHEN, Constant.EMPTY_STRING);
	}
}