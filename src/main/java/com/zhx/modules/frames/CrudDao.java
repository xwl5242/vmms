package com.zhx.modules.frames;

import java.util.List;

public interface CrudDao<T> {

	/**
	 * 根据主键获取对象信息
	 * @param id 主键
	 * @return
	 */
	T get(String id);
	
	/**
	 * 获取列表
	 * @param where where条件语句 
	 * @return
	 */
	List<T> findAllList(String where);
	
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
}
