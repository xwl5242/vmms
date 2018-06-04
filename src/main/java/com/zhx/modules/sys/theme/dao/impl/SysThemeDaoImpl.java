package com.zhx.modules.sys.theme.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhx.modules.frames.BaseJdbcTemplate;
import com.zhx.modules.sys.theme.bean.SysTheme;
import com.zhx.modules.sys.theme.dao.SysThemeDao;

@Repository
public class SysThemeDaoImpl extends BaseJdbcTemplate<SysTheme> implements SysThemeDao {

}
