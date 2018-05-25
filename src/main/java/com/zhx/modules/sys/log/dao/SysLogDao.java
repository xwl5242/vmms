package com.zhx.modules.sys.log.dao;

import java.util.Map;

import com.zhx.modules.frames.CrudDao;
import com.zhx.modules.sys.log.bean.SysLog;

public interface SysLogDao extends CrudDao<SysLog>{

	/**
	 * 写入操作日志
	 * @param log
	 */
	void insertOperateLog(SysLog log);

	/**
	 * 查询操作日志列表
	 * @param params
	 * @return
	 */
	Map<String, Object> selectLogList(Map<String, String> params);

}
