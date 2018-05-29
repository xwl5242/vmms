package com.zhx.modules.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统设置,没有具体的业务数据对应,更多的是做跳转页面
 * @author xwl
 *
 */
@Controller
@RequestMapping("/system")
public class SystemController {

	/**
	 * 跳转到系统维护页面
	 * @return
	 */
	@RequestMapping("/maintenance")
	public String maintenance(){
		return "web/system/maintenance";
	}
	
}
