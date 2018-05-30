package com.zhx.modules.sys.user.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhx.log.OpLog;
import com.zhx.modules.constants.Const;
import com.zhx.modules.frames.BaseController;
import com.zhx.modules.sys.user.bean.SysUser;
import com.zhx.modules.sys.user.service.SysUserService;
import com.zhx.modules.utils.CommonExcelExport;
import com.zhx.modules.utils.DESUtils;

@Controller
@RequestMapping(value={"/user","/pjax/user"})
public class SysUserController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(SysUserController.class);
	
	@Autowired
	private SysUserService userService;
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public void get(){
		userService.get();
	}

	/**
	 * 跳转到用户列表页面
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String userList(){
		return "web/user/list";
	}
	
	/**
	 * 跳转到用户列表页面
	 * @return
	 */
	@RequestMapping(value="/lista",method=RequestMethod.GET)
	public String userLista(){
		return "web/user/list";
	}
	
	
	/**
	 * 获取用户列表
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@OpLog(optName="查询用户列表",optKey="用户管理",optType=Const.OPLOG_TYPE_SELECT)
	@RequestMapping(value="/list",method=RequestMethod.PATCH)
	@ResponseBody
	public String userList(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String,String> params){
		logger.info("获取用户列表：params="+params);
		params.putAll(paramsStr2Map(params.get("params")));
		Map<String,Object> result = userService.queryUserList(params);
		return returnJson4Custom(result);
	}
	
	/**
	 * 跳转到添加用户页面
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addUser(){
		return "web/user/add";
	}
	
	/**
	 * 添加用户
	 * @param user
	 */
	@OpLog(optName="新增用户信息",optKey="用户管理",optType=Const.OPLOG_TYPE_INSERT)
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public String addUser(HttpServletRequest request,SysUser user) throws Exception{
		logger.info("添加用户:user="+user);
		//获取当前登录用户的ID
//		user.setCreator(getUserId(request));
		user.setId("2");
		user.setUserCode("2");
		user.setUserName("2");
		user.setUpdateTime("2018-05-25 16:25:55");
		int addRet = userService.saveUser(user);
		return addRet==1?returnJson4Success():returnJson4Fail();
	}
	
	/**
	 * 跳转到添加修改页面
	 */
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public ModelAndView editUser(@PathVariable String id) throws Exception{
		logger.info("跳转到用户修改页面：userId="+id);
		ModelAndView mv = new ModelAndView();
		SysUser user = userService.queryById(id);
		user.setPassword(DESUtils.decrypt(user.getPassword()));
		mv.setViewName("web/user/edit");
		mv.addObject("user", user);
		return mv;
	}
	
	/**
	 * 跳转到用户授权角色页面
	 */
	@RequestMapping(value="/grant/{id}",method=RequestMethod.GET)
	public ModelAndView grantUser(@PathVariable String id) throws Exception{
		logger.info("跳转到用户授权角色页面:userId="+id);
		ModelAndView mv = new ModelAndView();
		List<Map<String,Object>> roleList = userService.queryUserRoleByUserId(id);
		mv.setViewName("web/user/grant");
		mv.addObject("userId", id);
		mv.addObject("roleList", new ObjectMapper().writeValueAsString(roleList));
		return mv;
	}
	
	/**
	 * 用户角色授权
	 * @param request
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	@OpLog(optName="用户角色授权",optKey="用户管理",optType=Const.OPLOG_TYPE_UPDATE)
	@RequestMapping(value="/grant",method=RequestMethod.POST)
	@ResponseBody
	public String grantUser(HttpServletRequest request,String userId,String[] roleIds){
		logger.info("用户授权角色：userId="+userId+",roleIds="+roleIds);
		boolean ret = false;
		try {
			ret = userService.grantUser(userId,roleIds);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return returnJson4Exception(e);
		}
		return ret?returnJson4Success("用户授权角色成功！"):returnJson4Fail("用户授权角色失败！");
	}
	
	/**
	 * 修改用户
	 * @param user
	 */
	@OpLog(optName="修改用户信息",optKey="用户管理",optType=Const.OPLOG_TYPE_UPDATE)
	@RequestMapping(value="/edit",method=RequestMethod.PUT)
	@ResponseBody
	public String editUser(HttpServletRequest request,SysUser user)  throws Exception{
		logger.info("修改用户信息:user="+user);
		//获取当前登录用户的ID
//		user.setUpdator(getUserId(request));
		user.setUpdator("2222");
		user.setId("2");
		int addRet = userService.editUser(user);
		return addRet==1?returnJson4Success("修改用户成功！"):returnJson4Fail("修改用户失败！");
	}
	
	/**
	 * 登录名称是否唯一
	 * @param request
	 * @param response
	 * @param userCode
	 * @return
	 */
	@OpLog(optName="查询用户登录名称是否唯一",optKey="用户管理",optType=Const.OPLOG_TYPE_UPDATE)
	@RequestMapping(value="/loginUnique",method=RequestMethod.GET)
	@ResponseBody
	public String loginUnique(HttpServletRequest request,HttpServletResponse response,String userCode){
		logger.info("检测用户登录名称是否唯一：userCode="+userCode);
		SysUser user = userService.queryByUserCode(userCode);
		if(null==user){
			return returnJson4Success();
		}else {
			return returnJson4Fail();
		}
	}
	
	/**
	 * 启用或禁用用户
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@OpLog(optName="用户禁用或启用",optKey="用户管理",optType=Const.OPLOG_TYPE_UPDATE)
	@RequestMapping(value="/useStatus",method=RequestMethod.PUT)
	@ResponseBody
	public String updateUseStatus(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String,String> params){
		logger.info("启用或禁用用户:params="+params);
		boolean result = false;
		try {
			result = userService.updateUseStatus(params);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return returnJson4Exception(e);
		}
		return result?returnJson4Success():returnJson4Fail();
	}
	
	/**
	 * 重置密码
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@OpLog(optName="用户重置密码",optKey="用户管理",optType=Const.OPLOG_TYPE_UPDATE)
	@RequestMapping(value="/resetPwd",method=RequestMethod.PUT)
	@ResponseBody
	public String resetPwd(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String,String> params){
		logger.info("重置密码:params="+params);
		boolean result = false;
		try {
			result = userService.resetPwd(params);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return returnJson4Exception(e);
		}
		return result?returnJson4Success("重置密码成功！"):returnJson4Fail("重置密码失败！");
	}
	
	/**
	 * 删除用户
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@OpLog(optName="删除用户信息",optKey="用户管理",optType=Const.OPLOG_TYPE_DELETE)
	@RequestMapping(value="delete",method=RequestMethod.DELETE)
	@ResponseBody
	public String removeUsers(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String,String> params){
		logger.info("删除用户:params="+params);
		boolean result = false;
		try {
			result = userService.removeUsers(params);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return returnJson4Exception(e);
		}
		return result?returnJson4Success("删除用户成功！"):returnJson4Fail("删除用户失败！");
	}
	
	/**
	 * 导出用户
	 * @param params
	 * @param response
	 */
	@OpLog(optName="导出用户记录",optKey="用户管理",optType=Const.OPLOG_TYPE_SELECT)
	@RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, String> params, HttpServletResponse response) {
        logger.info("导出用户列表EXCEL");
        String workBookName = "用户列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
        cellName.put("id", "用户主键");
        cellName.put("user_code", "登录名称");
        cellName.put("user_name", "用户名称");
        cellName.put("nick_name", "用户昵称");
        cellName.put("password", "密码");
        cellName.put("phone", "手机");
        cellName.put("mail", "邮箱");
        cellName.put("use_status", "使用状态");
        //列值
        List<Map<String, Object>> cellValues = null;
        try {
        	params = paramsStr2Map(params.get("pramas"));
            cellValues = userService.exportUser(params);
            CommonExcelExport.excelExport(response,cellName,cellValues,workBookName);
        } catch (Exception e) {
            logger.error("导出用户列表EXCEL异常" + e.getMessage());
        }
    }
}
