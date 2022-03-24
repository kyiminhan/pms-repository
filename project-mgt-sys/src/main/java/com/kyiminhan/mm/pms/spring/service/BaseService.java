package com.kyiminhan.mm.pms.spring.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kyiminhan.mm.pms.spring.dao.SearchMode;

/**
 * The Interface BaseService.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @param <T> the generic type
 * @since 2019/03/27 </BR>
 *        project-mgt-sys system </BR>
 *        com.kyiminhan.mm.pms.spring.service </BR>
 *        BaseService.java </BR>
 */
public interface BaseService<T extends Serializable> extends Serializable {

	List<T> findAll();

	String save(T t);

	String update(T t);

	String remove(String uuid);

	String delete(String uuid);

	T findByProperties(Map<String, Object> map);

	Collection<T> findAllByEqualMap(Map<String, Object> map);

	Collection<T> findAllByNotEqualMap(Map<String, Object> map);

	Collection<T> findAllBySameKey(Set<Object> set, Object key, SearchMode searchMode);

	void updateProperties(Map<String, Object> dataMap, Map<String, Object> conditionMap);
}