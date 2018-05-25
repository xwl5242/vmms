package com.zhx.modules.sys.log.dao.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.zhx.modules.frames.BaseJdbcTemplate;
import com.zhx.modules.sys.log.bean.SysLog;
import com.zhx.modules.sys.log.dao.SysLogDao;

@Repository
public class SysLogDaoImpl extends BaseJdbcTemplate<SysLog> implements SysLogDao{

	/**
	 * 写入操作日志
	 */
	@Override
	public void insertOperateLog(SysLog log) {
//		String insertSql = CreateSqlTools.getCreateSql(log, SysLog.class, "sys_log");
//		update(insertSql);
	}

	/**
	 * 查询操作日志列表
	 */
	@Override
	public Map<String, Object> selectLogList(Map<String, String> params) {
		String sql="SELECT l.id,l.opt_name,l.opt_key,l.opt_type,l.class_name,l.method_name,"
				+ "l.remote_ip,l.remote_name,l.remote_port,l.req_uri,l.req_url,(u.user_name) creator,"
				+ "l.create_time from sys_log l LEFT JOIN sys_user u on l.creator=u.id  where 1=1 ";
		if(!StringUtils.isEmpty(params.get("optKey"))){
			sql += " and opt_key like '%"+params.get("optKey")+"%'";
		}
		if(!StringUtils.isEmpty(params.get("optType"))){
			sql += " and opt_type = '"+params.get("optType")+"'";
		}
		sql+=" order by create_time desc";
		return queryTableList(sql,Integer.valueOf(params.get("pageNumber")), Integer.valueOf(params.get("pageSize")));
	}

}
