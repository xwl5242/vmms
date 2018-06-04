package com.zhx.modules.sys.theme.service;

import com.zhx.modules.sys.theme.bean.SysTheme;

public interface SysThemeService {

	SysTheme get(String id);

	int save(SysTheme theme);
	
	int edit(SysTheme theme);
}
