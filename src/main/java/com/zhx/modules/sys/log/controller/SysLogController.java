package com.zhx.modules.sys.log.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhx.log.OpLog;
import com.zhx.modules.constants.Const;
import com.zhx.modules.frames.BaseController;
import com.zhx.modules.sys.log.service.SysLogService;

@Controller
@RequestMapping("/log")
public class SysLogController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(Logger.class);
	
	@Autowired
	private SysLogService logService;

	/**
	 * 跳转到日志列表页面
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String userList(){
		return "web/log/list";
	}
	
	/**
	 * 获取日志列表
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@OpLog(optName="查询日志列表",optKey="日志管理",optType=Const.OPLOG_TYPE_SELECT)
	@RequestMapping(value="/list",method=RequestMethod.PATCH)
	@ResponseBody
	public String userList(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String,String> params){
		logger.info("获取日志列表：params="+params);
		params.putAll(paramsStr2Map(params.get("params")));
		Map<String,Object> result = logService.queryLogList(params);
		return returnJson4Custom(result);
	}
}
