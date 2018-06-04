package com.zhx.modules.sys.theme.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhx.modules.frames.BaseController;
import com.zhx.modules.sys.theme.bean.SysTheme;
import com.zhx.modules.sys.theme.service.SysThemeService;

@Controller
@RequestMapping("sysTheme")
public class SysThemeController extends BaseController {

	@Autowired
	private SysThemeService sysThemeService;
	
	public String save(HttpServletRequest req,HttpServletResponse resp,SysTheme theme){
		return sysThemeService.save(theme)+"";
	}
}
