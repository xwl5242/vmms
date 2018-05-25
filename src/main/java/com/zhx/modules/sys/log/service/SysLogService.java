package com.zhx.modules.sys.log.service;

import java.util.Map;

import com.zhx.modules.sys.log.bean.SysLog;

public interface SysLogService {

	/**
	 * 写入操作日志
	 * @param log
	 * @throws Exception
	 */
	void saveOperateLog(SysLog log) throws Exception;

	/**
	 * 获取操作日志列表
	 * @param params
	 * @return
	 */
	Map<String, Object> queryLogList(Map<String, String> params);

}
