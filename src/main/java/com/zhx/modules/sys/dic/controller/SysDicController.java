package com.zhx.modules.sys.dic.controller;

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
import com.zhx.modules.sys.dic.bean.SysDic;
import com.zhx.modules.sys.dic.service.SysDicService;

@Controller
@RequestMapping("/dic")
public class SysDicController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(SysDicController.class);
	
	@Autowired
	private SysDicService dicService;

	/**
	 * 跳转到字典列表页面
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String dicList(){
		return "web/dic/list";
	}
	
	/**
	 * 获取字典列表
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@OpLog(optName="查询字典列表",optKey="字典管理",optType=Const.OPLOG_TYPE_SELECT)
	@RequestMapping(value="/list",method=RequestMethod.PATCH)
	@ResponseBody
	public String dicList(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String,String> params){
		logger.info("获取字典列表：params="+params);
		params.putAll(paramsStr2Map(params.get("params")));
		Map<String,Object> result = dicService.queryDicList(params);
		return returnJson4Custom(result);
	}
	
	/**
	 * 跳转到添加字典页面
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addDic(){
		return "web/dic/add";
	}
	
	/**
	 * 添加字典
	 * @param dic
	 */
	@OpLog(optName="新增字典信息",optKey="字典管理",optType=Const.OPLOG_TYPE_INSERT)
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public String addDic(HttpServletRequest request,SysDic dic) throws Exception{
		logger.info("添加字典:dic="+dic);
		//获取当前登录字典的ID
		int addRet = dicService.saveDic(dic);
		return addRet==1?returnJson4Success():returnJson4Fail();
	}
	
	/**
	 * 跳转到添加修改页面
	 */
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public ModelAndView editDic(@PathVariable String id) throws Exception{
		logger.info("跳转到字典修改页面：dicId="+id);
		ModelAndView mv = new ModelAndView();
		SysDic dic = dicService.queryById(id);
		mv.setViewName("web/dic/edit");
		mv.addObject("dic", dic);
		return mv;
	}
	
	/**
	 * 修改字典
	 * @param dic
	 */
	@OpLog(optName="修改字典信息",optKey="字典管理",optType=Const.OPLOG_TYPE_UPDATE)
	@RequestMapping(value="/edit",method=RequestMethod.PUT)
	@ResponseBody
	public String editDic(HttpServletRequest request,SysDic dic)  throws Exception{
		logger.info("修改字典信息:dic="+dic);
		//获取当前登录字典的ID
		int addRet = dicService.editDic(dic);
		return addRet==1?returnJson4Success("修改字典成功！"):returnJson4Fail("修改字典失败！");
	}
	
	/**
	 * 字典名称是否唯一
	 * @param request
	 * @param response
	 * @param dicCode
	 * @return
	 */
	@OpLog(optName="查询字典名称是否唯一",optKey="字典管理",optType=Const.OPLOG_TYPE_UPDATE)
	@RequestMapping(value="/dicNameUnique",method=RequestMethod.GET)
	@ResponseBody
	public String dicNameUnique(HttpServletRequest request,HttpServletResponse response,String dicName){
		logger.info("检测字典名称是否唯一：dicName="+dicName);
		SysDic dic = dicService.queryByDicName(dicName);
		if(null==dic){
			return returnJson4Success();
		}else {
			return returnJson4Fail();
		}
	}
	
	/**
	 * 删除字典
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@OpLog(optName="删除字典信息",optKey="字典管理",optType=Const.OPLOG_TYPE_DELETE)
	@RequestMapping(value="delete",method=RequestMethod.DELETE)
	@ResponseBody
	public String removedics(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String,String> params){
		logger.info("删除字典:params="+params);
		boolean result = false;
		try {
			result = dicService.removeDics(params);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return returnJson4Exception(e);
		}
		return result?returnJson4Success("删除字典成功！"):returnJson4Fail("删除字典失败！");
	}
	
}
