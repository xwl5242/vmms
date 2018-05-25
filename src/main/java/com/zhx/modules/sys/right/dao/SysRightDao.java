package com.zhx.modules.sys.right.dao;

import java.util.List;
import java.util.Map;

import com.zhx.modules.frames.CrudDao;
import com.zhx.modules.sys.right.bean.SysRight;

public interface SysRightDao extends CrudDao<SysRight>{

	/**
	 * 新建权限
	 * @param right
	 * @return
	 */
	int insertRight(SysRight right);

	/**
	 * 根据权限登录名查询权限信息
	 * @param rightName
	 * @return
	 */
	SysRight selectByRightName(String rightName);

	/**
	 * 分页查询权限列表
	 * @param params
	 * @return
	 */
	Map<String, Object> selectRightList(Map<String, String> params);

	/**
	 * 删除权限
	 * @param ids
	 * @return
	 */
	int deleteRights(String ids);

	/**
	 * 修改权限
	 * @param right
	 * @return
	 */
	int updateRight(SysRight right);

	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	SysRight selectById(String id);

	/**
	 * 修改是否末端和icon
	 * @param id
	 * @param isLeaf
	 * @param rightCionDefault
	 * @return
	 */
	int updateLeafAndIcon(String id, String isLeaf, String icon);

	/**
	 * 根据父id查询
	 * @param pid
	 * @return
	 */
	List<?> selectByPid(String pid);

	/**
	 * 查询权限和角色关联个数
	 * @param ids
	 * @return
	 */
	int selectRoleRightCountByRightId(String ids);

}
