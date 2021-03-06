package com.zhx.modules.sys.user.service;

import java.util.List;
import java.util.Map;

import com.zhx.modules.sys.theme.bean.SysTheme;
import com.zhx.modules.sys.user.bean.SysUser;


public interface SysUserService {

	/**
	 * 根据用户登录名称（用户编码）查询用户信息
	 * @param userCode
	 * @return
	 */
	SysUser queryByUserCode(String userCode);

	/**
	 * 根据查询参数（params）查询用户列表信息
	 * @param params
	 * @return
	 */
	List<SysUser> queryList(Map<String,String> params);

	/**
	 * 更新用户信息
	 * @param loginUser
	 */
	int editUser(SysUser user);

	/**
	 * 获取当前登录用户的主题信息
	 * @param themeId
	 * @return
	 */
	SysTheme getCurUserTheme(String themeId);

	/**
	 * 用户更新系统设置中的主题信息
	 * @param theme
	 * @return
	 */
	int updateTheme(SysTheme theme);
	
}
