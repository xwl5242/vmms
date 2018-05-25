package com.zhx.modules.sys.dic.dao.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.zhx.modules.frames.BaseJdbcTemplate;
import com.zhx.modules.sys.dic.bean.SysDic;
import com.zhx.modules.sys.dic.dao.SysDicDao;
import com.zhx.modules.utils.IdsUtil;

@Repository
public class SysDicDaoImpl extends BaseJdbcTemplate<SysDic> implements SysDicDao {

	@Override
	public SysDic selectByNameAndK(String name, String key) {
		String sql = "select "+getSelectAllSql("sys_dic")+" from sys_dic where dc_name='"+name+"' and dc_k='"+key+"'";
		return super.queryForBean(sql);
	}

	@Override
	public Map<String, Object> selectDicList(Map<String, String> params) {
		String sql = "select "+getSelectAllSql("sys_dic")+" from sys_dic where 1=1 ";
		if(!StringUtils.isEmpty(params.get("dcName"))){
			sql += " and dc_name like '%"+params.get("dcName")+"%'";
		}
		if(!StringUtils.isEmpty(params.get("dcDesc"))){
			sql += " and dc_desc like '%"+params.get("dcDesc")+"%'";
		}
		return queryTableList(sql,Integer.valueOf(params.get("pageNumber")), Integer.valueOf(params.get("pageSize")));
	}

	@Override
	public int insertDic(SysDic dic) {
//		String insertSql = CreateSqlTools.getCreateSql(dic,SysDic.class,"sys_dic");
//		return update(insertSql);
		return 0;
	}

	@Override
	public SysDic selectById(String id) {
		String sql = "select "+getSelectAllSql("sys_dic")+" from sys_dic where id='"+id+"'";
		return super.queryForBean(sql);
	}

	@Override
	public int updateDic(SysDic dic) {
//		String insertSql = CreateSqlTools.getUpdateSql(dic,SysDic.class,"sys_dic");
//		return update(insertSql);
		return 0;
	}

	@Override
	public SysDic selectByDicName(String dicName) {
		String sql = "select "+getSelectAllSql("sys_dic")+" from sys_dic where dc_name='"+dicName+"'";
		return super.queryForBean(sql);
	}

	@Override
	public int deleteDics(String ids) {
		ids = IdsUtil.idsAddSingleQuotes(ids);
		String sql = "delete from sys_dic where id in ("+ids+")";
		return update(sql);
	}

}
