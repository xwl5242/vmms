package com.zhx.modules.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhx.modules.constants.Const;
import com.zhx.modules.sys.user.bean.SysUser;

public class UserUtils {

	public static SysUser getCurUser(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return (SysUser)request.getSession().getAttribute(Const.SESSION_USER);
	}
	
	public static String getCurUserId(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession().getAttribute(Const.SESSION_USER_ID).toString();
	}
}
