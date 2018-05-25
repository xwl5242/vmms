package com.zhx.modules.sys.user.service;

import java.util.List;
import java.util.Map;

import com.zhx.modules.sys.user.bean.SysUser;

public interface SysUserService {
	
	void get();

	/**
	 * 新建用户
	 * @param user
	 * @return
	 */
	int saveUser(SysUser user)  throws Exception;

	/**
	 * 根据用户登录名查询用户信息
	 * @param userCode
	 * @return
	 */
	SysUser queryByUserCode(String userCode);

	/**
	 * 查询列表
	 * @param params
	 * @return
	 */
	Map<String, Object> queryUserList(Map<String, String> params);

	/**
	 * 启用或禁用用户
	 * @param params
	 * @return
	 */
	boolean updateUseStatus(Map<String, String> params) throws Exception;

	/**
	 * 重置密码
	 * @param params
	 * @return
	 */
	boolean resetPwd(Map<String, String> params) throws Exception;

	/**
	 * 删除用户
	 * @param params
	 * @return
	 */
	boolean removeUsers(Map<String, String> params) throws Exception;

	/**
	 * 导出用户
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> exportUser(Map<String, String> params);

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	int editUser(SysUser user)  throws Exception;

	/**
	 * 根据主键查询用户
	 * @param id
	 * @return
	 */
	SysUser queryById(String id);

	/**
	 * 用户授权角色
	 * @param userId
	 * @param roleIds
	 * @return
	 * @throws Exception
	 */
	boolean grantUser(String userId, String[] roleIds) throws Exception;

	/**
	 * 根据userId查询用户角色关联
	 * @param id
	 * @return
	 */
	List<Map<String,Object>> queryUserRoleByUserId(String id);

}
