package com.zhx.modules.sys.user.dao;

import java.util.List;
import java.util.Map;

import com.zhx.modules.frames.CrudDao;
import com.zhx.modules.sys.user.bean.SysUser;

public interface SysUserDao extends CrudDao<SysUser>  {

	/**
	 * 根据用户登录名查询用户信息
	 * @param userCode
	 * @return
	 */
	SysUser selectByUserCode(String userCode);

	/**
	 * 分页查询用户列表
	 * @param params
	 * @return
	 */
	Map<String, Object> selectUserList(Map<String, String> params);

	/**
	 * 禁用或启用用户
	 * @param ids
	 * @param status
	 * @return
	 */
	int updateUseStatusByIds(String ids, String status);

	/**
	 * 重置密码
	 * @param ids
	 * @return
	 */
	int update4ResetPwd(String ids) throws Exception;

	/**
	 * 导出用户
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> selectUsers4Export(Map<String, String> params);

	/**
	 * 根据主键获取用户信息
	 * @param id
	 * @return
	 */
	SysUser selectById(String id);

	/**
	 * 用户的角色授权
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	int updateUserRole(String userId, String[] roleIds);

	/**
	 * 根据userId查询用户角色关联
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> selectUserRoleByUserId(String id);

	/**
	 * 根据用户id集合，查询这些用户的用户和角色关联个数
	 * @param ids
	 * @return
	 */
	int selectUserRoleCount(String ids);

}
