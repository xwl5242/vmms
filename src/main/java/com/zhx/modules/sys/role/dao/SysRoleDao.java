package com.zhx.modules.sys.role.dao;

import java.util.List;
import java.util.Map;

import com.zhx.modules.frames.CrudDao;
import com.zhx.modules.sys.role.bean.SysRole;

public interface SysRoleDao extends CrudDao<SysRole> {

	/**
	 * 新建角色
	 * @param role
	 * @return
	 */
	int insertRole(SysRole role);

	/**
	 * 分页查询角色列表
	 * @param params
	 * @return
	 */
	Map<String, Object> selectRoleList(Map<String, String> params);

	/**
	 * 删除角色
	 * @param ids
	 * @return
	 */
	int deleteRoles(String ids);

	/**
	 * 导出角色
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> selectRoles4Export(Map<String, String> params);

	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	int updateRole(SysRole role);

	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	SysRole selectById(String id);

	/**
	 * 根据角色名称查询角色信息
	 * @param roleName
	 * @return
	 */
	SysRole selectByRoleName(String roleName);

	/**
	 * 根据角色id查询角色权限关联
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> selectRoleRightByRoleId(String id);

	/**
	 * 角色授权权限
	 * @param roleId
	 * @param rightIds
	 * @return
	 */
	int updateRoleRight(String roleId, String[] rightIds);

	/**
	 * 根据角色id查询角色和用户关联个数
	 * @param ids
	 * @return
	 */
	int selectUserRoleCountByRoleId(String ids);

	/**
	 * 根据角色id查询角色和权限关联个数
	 * @param ids
	 * @return
	 */
	int selectRoleRightCountByRoleId(String ids);

}
