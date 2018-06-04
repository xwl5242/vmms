package com.zhx.modules.sys.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zhx.log.OpLog;
import com.zhx.modules.constants.Const;
import com.zhx.modules.frames.BaseController;
import com.zhx.modules.sys.user.bean.SysUser;
import com.zhx.modules.sys.user.service.SysUserService;

@Controller
@RequestMapping("/user")
public class SysUserController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(SysUserController.class);
	
	@Autowired
	private SysUserService userService;
	
	/**
	 * 跳转到用户列表页面
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String userList(){
		return "web/user/list";
	}
	
	/**
	 * 获取用户列表
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 * @throws JsonProcessingException 
	 */
	@OpLog(optName="查询用户列表",optKey="用户管理",optType=Const.OPLOG_TYPE_SELECT)
	@RequestMapping(value="/pagelist",method=RequestMethod.GET)
	@ResponseBody
	public String userList(HttpServletRequest request,HttpServletResponse response,String params) throws JsonProcessingException{
		logger.info("获取用户列表");
		Map<String,Object> map = new HashMap<String,Object>();
		List<SysUser> list = userService.queryList(paramsStr2Map(params));
		map.put("data", list);
		return objectMapper.writeValueAsString(map);
	}
	
}
