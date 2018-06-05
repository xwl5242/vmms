package com.zhx.modules.sys.theme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhx.modules.frames.BaseController;
import com.zhx.modules.sys.theme.service.SysThemeService;

@Controller
@RequestMapping("sysTheme")
public class SysThemeController extends BaseController {

	@Autowired
	private SysThemeService sysThemeService;
	
}
