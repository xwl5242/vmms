package com.zhx.modules.sys.right.dao;

import java.util.Map;

import com.zhx.modules.frames.CrudDao;
import com.zhx.modules.sys.right.bean.SysRight;

public interface SysRightDao extends CrudDao<SysRight>{

	/**
	 * 分页查询权限列表
	 * @param params
	 * @return
	 */
	Map<String, Object> selectRightList(Map<String, String> params);

	/**
	 * 修改是否末端和icon
	 * @param id
	 * @param isLeaf
	 * @param rightCionDefault
	 * @return
	 */
	int updateLeafAndIcon(String id, String isLeaf, String icon);

	/**
	 * 查询权限和角色关联个数
	 * @param ids
	 * @return
	 */
	int selectRoleRightCountByRightId(String ids);

}
