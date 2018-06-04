package com.zhx.modules.sys.theme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhx.modules.sys.theme.bean.SysTheme;
import com.zhx.modules.sys.theme.dao.SysThemeDao;
import com.zhx.modules.sys.theme.service.SysThemeService;

@Service
public class SysThemeServiceImpl implements SysThemeService {
	
	@Autowired
	private SysThemeDao systheDao;

	@Override
	public SysTheme get(String id) {
		return systheDao.get(id);
	}

	@Override
	public int save(SysTheme theme) {
		return systheDao.insert(theme);
	}

	@Override
	public int edit(SysTheme theme) {
		return systheDao.update(theme);
	}

}
