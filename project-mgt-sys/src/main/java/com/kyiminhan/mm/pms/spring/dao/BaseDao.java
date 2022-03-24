package com.kyiminhan.mm.pms.spring.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The Interface BaseDao.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @param <T> the generic type
 * @since 2019/03/27 </BR>
 *        project-mgt-sys system </BR>
 *        com.kyiminhan.mm.pms.spring.dao </BR>
 *        BaseDao.java </BR>
 */
public interface BaseDao<T extends Serializable> extends Serializable {

	/**
	 * Find by UUID.
	 *
	 * @param uuid the uuid
	 * @return T
	 */
	T findByUUID(String uuid);

	/**
	 * Find by UUID.
	 *
	 * @param t the t
	 * @return T
	 */
	T findByUUID(T t);

	/**
	 * Find all.
	 *
	 * @return List
	 */
	List<T> findAll();

	/**
	 * Save.
	 *
	 * @param t the t
	 */
	void save(T t);

	/**
	 * Update.
	 *
	 * @param t the t
	 */
	void update(T t);

	/**
	 * Delete.
	 *
	 * @param t the t
	 */
	void remove(T t);

	/**
	 * Delete.
	 *
	 * @param t the t
	 */
	void delete(T t);

	/**
	 * Find by properties.
	 *
	 * @param map the map
	 * @return T
	 */
	T findByProperties(Map<String, Object> map);

	/**
	 * Find all by equal map.
	 *
	 * @param map the map
	 * @return Collection
	 */
	Collection<T> findAllByEqualMap(Map<String, Object> map);

	/**
	 * Find all by not equal map.
	 *
	 * @param map the map
	 * @return Collection
	 */
	Collection<T> findAllByNotEqualMap(Map<String, Object> map);

	/**
	 * Find all by not equal same key.
	 *
	 * @param set the set
	 * @param key the key
	 * @return Collection
	 */
	Collection<T> findAllBySameKey(final Set<Object> set, Object key, SearchMode searchMode);

	/**
	 * Update properties.
	 *
	 * @param dataMap      the data map
	 * @param conditionMap the condition map
	 */
	void updateProperties(Map<String, Object> dataMap, Map<String, Object> conditionMap);

}