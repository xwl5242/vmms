package com.zhx.modules.sys.role.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.zhx.modules.frames.BaseJdbcTemplate;
import com.zhx.modules.sys.role.bean.SysRole;
import com.zhx.modules.sys.role.dao.SysRoleDao;
import com.zhx.modules.utils.IdsUtil;
import com.zhx.modules.utils.UUIDGenerator;

@Repository
public class SysRoleDaoImpl extends BaseJdbcTemplate<SysRole> implements SysRoleDao {
	
	/**
	 * 分页查询
	 */
	@Override
	public Map<String, Object> selectRoleList(Map<String, String> params) {
//		String sql="select "+getSelectAllSql("sys_role")+" from sys_role where 1=1";
//		if(!StringUtils.isEmpty(params.get("roleDesc"))){
//			sql += " and role_desc like '%"+params.get("roleDesc")+"%'";
//		}
//		if(!StringUtils.isEmpty(params.get("roleName"))){
//			sql += " and role_name like '%"+params.get("roleName")+"%'";
//		}
//		sql += " order by create_time desc";
//		return queryTableList(sql, Integer.valueOf(params.get("pageNumber")), Integer.valueOf(params.get("pageSize")));
		return null;
	}

	/**
	 * 导出角色
	 */
	@Override
	public List<Map<String, Object>> selectRoles4Export(
			Map<String, String> params) {
		String sql="select id,role_desc,role_name from sys_role where 1=1";
		if(!StringUtils.isEmpty(params.get("roleDesc"))){
			sql += " and role_desc like '%"+params.get("roleDesc")+"%'";
		}
		if(!StringUtils.isEmpty(params.get("roleName"))){
			sql += " and role_name like '%"+params.get("roleName")+"%'";
		}
		return queryForList(sql);
	}


	/**
	 * 根据角色id查询角色权限关联
	 */
	@Override
	public List<Map<String, Object>> selectRoleRightByRoleId(String id) {
		String sql = "select rr.right_id,r.right_name from sys_role_right rr LEFT JOIN sys_right r on rr.right_id=r.id "
				+ "where rr.role_id = '"+id+"' and r.is_leaf='1' and r.right_url!='#'";
		return queryForList(sql);
	}

	/**
	 * 角色授权权限
	 */
	@Override
	public int updateRoleRight(String roleId, String[] rightIds) {
		String dSql = "delete from sys_role_right where role_id = '"+roleId+"'";
		int dret = update(dSql);
		int uret = 0,success = 0;
		List<String> rIdList = new ArrayList<String>();
		rIdList.addAll(Arrays.asList(rightIds));
		for(String rightId:rightIds){
			String sql = "select pid from sys_right where id='"+rightId+"'";
			String pid = queryForObject(sql, String.class);
			if(!rIdList.contains(pid)&&!"-1".equals(pid)){
				rIdList.add(pid);
			}
		}
		for(String rightId:rIdList){
			if(!StringUtils.isBlank(rightId)&&!"0".equals(rightId)){
				uret++;
				String id = UUIDGenerator.getUUID();
				String uSql = "insert into sys_role_right(id,role_id,right_id) values('"+id+"','"+roleId+"','"+rightId+"')";
				success += update(uSql);
			}
		}
		if(uret!=success){//for循环体内又失败的事物，service层要回滚，此时置dret为-2，外层去校验
			dret = -2;
		}
		return dret;
	}

	/**
	 * 根据角色id查询角色和用户关联个数
	 */
	@Override
	public int selectUserRoleCountByRoleId(String ids) {
		ids = IdsUtil.idsAddSingleQuotes(ids);
		String sql = "select count(1) from sys_user_role where role_id in("+ids+")";
		return queryForObject(sql, Integer.class);
	}

	/**
	 * 根据角色id查询角色和权限关联个数
	 */
	@Override
	public int selectRoleRightCountByRoleId(String ids) {
		ids = IdsUtil.idsAddSingleQuotes(ids);
		String sql = "select count(1) from sys_role_right where role_id in("+ids+")";
		return queryForObject(sql, Integer.class);
	}

}
