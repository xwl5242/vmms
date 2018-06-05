package com.zhx.modules.sys.user.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhx.modules.sys.theme.bean.SysTheme;
import com.zhx.modules.sys.theme.service.SysThemeService;
import com.zhx.modules.sys.user.bean.SysUser;
import com.zhx.modules.sys.user.dao.SysUserDao;
import com.zhx.modules.sys.user.service.SysUserService;
import com.zhx.modules.utils.UUIDGenerator;
import com.zhx.modules.utils.UserUtils;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao userDao;
	
	@Autowired
	private SysThemeService sysThemeService;

	/**
	 * 根据用户code（登录名称）查询用户信息
	 */
	@Transactional(readOnly=true)
	@Override
	public SysUser queryByUserCode(String userCode) {
		return userDao.getByWhere("user_code=?", new Object[]{userCode});
	}

	/**
	 * 根据查询参数（params）查询用户列表信息
	 */
	@Transactional(readOnly=true)
	@Override
	public List<SysUser> queryList(Map<String,String> params) {
		StringBuilder sql = new StringBuilder();
		if(StringUtils.isNotBlank(params.get("userName"))){
			sql.append(" and user_name like '%").append(params.get("userName")).append("%' ");
		}
		if(StringUtils.isNotBlank(params.get("userCode"))){
			sql.append(" and user_code like '%").append(params.get("userCode")).append("%' ");
		}
		if(StringUtils.isNotBlank(params.get("phone"))){
			sql.append(" and phone like '%").append(params.get("phone")).append("%' ");
		}
		if(StringUtils.isNotBlank(params.get("mail"))){
			sql.append(" and mail like '%").append(params.get("mail")).append("%' ");
		}
		if(StringUtils.isNotBlank(params.get("useStatus"))){
			sql.append(" and use_status ='").append(params.get("useStatus")).append("' ");
		}
		if(StringUtils.isNotBlank(params.get("isDel"))){
			sql.append(" and is_del = '").append(params.get("isDel")).append("' ");
		}
		if(StringUtils.isNotBlank(params.get("start"))){
			sql.append(" and last_login_time >= '").append(params.get("start")).append("' ");
		}
		if(StringUtils.isNotBlank(params.get("end"))){
			sql.append(" and last_login_time <= '").append(params.get("end")).append("' ");
		}
		return userDao.findAllList(sql.toString()," order by create_time desc");
	}

	/**
	 * 更新用户信息 
	 */
	@Transactional(readOnly=false)
	@Override
	public int editUser(SysUser user) {
		return userDao.update(user);
	}

	/**
	 * 获取当前登录用户的主题信息
	 */
	@Transactional(readOnly=true)
	@Override
	public SysTheme getCurUserTheme(String themeId) {
		return sysThemeService.get(themeId);
	}

	/**
	 * 更新当前用户的主题信息
	 */
	@Transactional(readOnly=false)
	@Override
	public int updateTheme(SysTheme theme) {
		int result = 0;
		String themeId = theme.getId();
		if(StringUtils.isBlank(themeId)){
			theme.setId(UUIDGenerator.getUUID());
			result += sysThemeService.save(theme);
		}else{
			result += sysThemeService.edit(theme);
		}
		SysUser user = UserUtils.getCurUser();
		user.setThemeId(theme.getId());
		result += userDao.update(user);
		return result;
	}
	
}
