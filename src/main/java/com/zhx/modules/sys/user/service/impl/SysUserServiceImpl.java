package com.zhx.modules.sys.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhx.modules.sys.user.bean.SysUser;
import com.zhx.modules.sys.user.dao.SysUserDao;
import com.zhx.modules.sys.user.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao userDao;

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
	@Override
	public List<SysUser> queryList(Map<String, Object> params) {
		return userDao.findAllList(params, "");
	}
	
}
