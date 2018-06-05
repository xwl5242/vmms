package com.zhx.modules.sys;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhx.modules.frames.BaseController;
import com.zhx.modules.utils.UserUtils;

/**
 * 系统设置,没有具体的业务数据对应,更多的是做跳转页面
 * @author xwl
 *
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {

	/**
	 * 跳转到系统维护页面
	 * @return
	 */
	@RequestMapping("/maintenance")
	public String maintenance(){
		return "web/system/maintenance";
	}
	
	@RequestMapping("/settings/display")
	public String display(){
		return "web/system/settings/display";
	}
	
	@RequestMapping("/locked")
	public String locked(HttpServletRequest request,Model model){
		model.addAttribute("userCode", UserUtils.getCurUser().getUserCode());
		removeAllSession(request);
		return "web/system/locked";
	}
	
}
