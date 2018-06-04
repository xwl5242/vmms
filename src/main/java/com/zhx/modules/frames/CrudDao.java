package com.zhx.modules.frames;

import java.util.List;
import java.util.Map;

public interface CrudDao<T> {

	/**
	 * 根据主键获取对象信息
	 * @param id 主键
	 * @return
	 */
	T get(String id);
	
	/**
	 * 根据where条件查询
	 * @param where
	 * @return
	 */
	T getByWhere(String where);
	
	/**
	 * 根据where条件查询
	 * @param where
	 * @param args
	 * @return
	 */
	T getByWhere(String where,Object[] args);
	
	/**
	 * 获取列表
	 * @param where where条件语句 
	 * @return
	 */
	List<T> findAllList(String where,String sort);
	
	/**
	 * 获取列表
	 * @param where
	 * @param args
	 * @return
	 */
	List<T> findAllList(String where,Object[] args,String sort);
	
	/**
	 * 新增
	 * @param t
	 * @return
	 */
	int insert(T t);
	
	/**
	 * 修改
	 * @param t
	 * @return
	 */
	int update(T t);
	
	/**
	 * 删除
	 * @param t
	 * @return
	 */
	int delete(String id);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int batchDelete(String[] ids);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int batchDelete(String ids);
}
