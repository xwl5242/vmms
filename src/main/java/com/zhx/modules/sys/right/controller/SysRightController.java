package com.zhx.modules.sys.right.controller;

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

import com.zhx.log.OpLog;
import com.zhx.modules.constants.Const;
import com.zhx.modules.frames.BaseController;
import com.zhx.modules.frames.GlobalCache;
import com.zhx.modules.sys.right.bean.SysRight;
import com.zhx.modules.sys.right.service.SysRightService;
import com.zhx.modules.utils.BeanMapUtils;
import com.zhx.modules.utils.GlobalCacheUtils;

@Controller
@RequestMapping("/right")
public class SysRightController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(SysRightController.class);
	
	@Autowired
	private SysRightService rightService;

	/**
	 * 跳转到权限列表页面
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String rightList(){
		return "web/right/list";
	}
	
	/**
	 * 获取权限列表
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@OpLog(optName="查询权限列表",optKey="权限管理",optType=Const.OPLOG_TYPE_SELECT)
	@RequestMapping(value="/list",method=RequestMethod.PATCH)
	@ResponseBody
	public String rightList(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String,String> params){
		logger.info("获取权限列表:params="+params);
		params.putAll(paramsStr2Map(params.get("params")));
		Map<String,Object> result = rightService.queryRightList(params);
		return returnJson4Custom(result);
	}
	
	/**
	 * 组织机构树
	 * @return
	 */
	@RequestMapping(value="/orgTree",method=RequestMethod.GET)
	@ResponseBody
	public String orgTree() throws Exception{
		logger.info("获取组织机构树");
		List<Map<String, Object>> rlist = BeanMapUtils.beanListTrans4MapList(GlobalCache.rightsList);
		return returnJson4Custom(GlobalCacheUtils.orgTreeList(rlist));
	}
	
	/**
	 * 跳转到添加权限页面
	 */
	@RequestMapping(value="/add/{id}",method=RequestMethod.GET)
	public ModelAndView addRight(@PathVariable String id){
		logger.info("跳转到添加权限页面：rightId="+id);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("web/right/add");
		mv.addObject("pid", id);
		return mv;
	}
	
	/**
	 * 添加权限
	 * @param right
	 */
	@OpLog(optName="新增权限信息",optKey="权限管理",optType=Const.OPLOG_TYPE_INSERT)
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public String addRight(HttpServletRequest request,SysRight right) {
		logger.info("添加权限:right="+right);
		int addRet = 0;
		try {
			//获取当前登录权限的ID
			right.setUpdator(getUserId(request));
			addRet = rightService.saveRight(right);
			refreshSession4Right(request, "003");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return returnJson4Exception(e);
		}
		return addRet==1?returnJson4Success("添加权限成功！"):returnJson4Fail("添加权限失败！");
	}
	
	/**
	 * 跳转到添加修改页面
	 */
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public ModelAndView editRight(@PathVariable String id) throws Exception{
		logger.info("跳转到修改权限页面：rightId="+id);
		ModelAndView mv = new ModelAndView();
		SysRight right = rightService.queryById(id);
		mv.setViewName("web/right/edit");
		mv.addObject("right", right);
		return mv;
	}
	
	/**
	 * 修改权限
	 * @param right
	 */
	@OpLog(optName="修改权限信息",optKey="权限管理",optType=Const.OPLOG_TYPE_UPDATE)
	@RequestMapping(value="/edit",method=RequestMethod.PUT)
	@ResponseBody
	public String editRight(HttpServletRequest request,SysRight right) {
		logger.info("修改权限:right="+right);
		int addRet = 0;
		try {
			//获取当前登录权限的ID
			right.setUpdator(getUserId(request));
			addRet = rightService.editRight(right);
			refreshSession4Right(request, "003");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return returnJson4Exception(e);
		}
		return addRet==1?returnJson4Success("修改权限成功！"):returnJson4Fail("修改权限失败！");
	}
	
	/**
	 * 权限名称是否唯一
	 * @param request
	 * @param response
	 * @param rightCode
	 * @return
	 */
	@OpLog(optName="查询权限名称是否唯一",optKey="权限管理",optType=Const.OPLOG_TYPE_SELECT)
	@RequestMapping(value="/rightUnique",method=RequestMethod.POST)
	@ResponseBody
	public String loginUnique(HttpServletRequest request,HttpServletResponse response,String rightName){
		logger.info("权限名称是否唯一：rightName="+rightName);
		SysRight right = rightService.queryByRightName(rightName);
		if(null==right){
			return returnJson4Success();
		}else {
			return returnJson4Fail();
		}
	}
	
	/**
	 * 删除权限
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@OpLog(optName="删除权限信息",optKey="权限管理",optType=Const.OPLOG_TYPE_DELETE)
	@RequestMapping(value="delete",method=RequestMethod.DELETE)
	@ResponseBody
	public String removeRights(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String,String> params){
		logger.info("删除权限：params="+params);
		boolean result = false;
		try{
			result = rightService.removeRights(params);
			refreshSession4Right(request, "003");
		}catch(Exception e){
			logger.error(e.getMessage());
			return returnJson4Exception(e);
		}
		return result?returnJson4Success("删除权限成功！"):returnJson4Fail("删除权限失败！");
	}
	
}
