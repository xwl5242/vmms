package com.zhx.modules.sys.log.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhx.modules.sys.log.bean.SysLog;
import com.zhx.modules.sys.log.dao.SysLogDao;
import com.zhx.modules.sys.log.service.SysLogService;

@Service
public class SysLogServiceImpl implements SysLogService {
	
	@Autowired
	private SysLogDao logDao;

	/**
	 * 写入操作日志
	 */
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@Override
	public void saveOperateLog(SysLog log) throws Exception{
		try{
			logDao.insertOperateLog(log);
		}catch(Exception e){
			throw e;
		}
	}

	/**
	 * 查询操作日志列表
	 */
	@Transactional(readOnly=true)
	@Override
	public Map<String, Object> queryLogList(Map<String, String> params) {
		return logDao.selectLogList(params);
	}

}
