package com.zhx.modules.sys.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhx.modules.frames.BaseJdbcTemplate;
import com.zhx.modules.sys.user.bean.SysUser;
import com.zhx.modules.sys.user.dao.SysUserDao;

@Repository
public class SysUserDaoImpl extends BaseJdbcTemplate<SysUser> implements SysUserDao {
	
}
