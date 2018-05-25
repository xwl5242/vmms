package com.zhx.modules.sys.user.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.zhx.modules.frames.BaseJdbcTemplate;
import com.zhx.modules.sys.user.bean.SysUser;
import com.zhx.modules.sys.user.dao.SysUserDao;
import com.zhx.modules.utils.DESUtils;
import com.zhx.modules.utils.IdsUtil;
import com.zhx.modules.utils.UUIDGenerator;

@Repository
public class SysUserDaoImpl extends BaseJdbcTemplate<SysUser> implements SysUserDao {
	
	/**
	 * 根据用户登录名称查询用户信息
	 */
	@Override
	public SysUser selectByUserCode(String userCode) {
//		String sql = "select "+getSelectAllSql("sys_user")+" from sys_user where user_code='"+userCode+"'";
//		return super.queryForBean(sql);
		return null;
	}

	/**
	 * 查询用户分页信息
	 */
	@Override
	public Map<String, Object> selectUserList(Map<String, String> params) {
//		String sql="select "+getSelectAllSql("sys_user")+" from sys_user where 1=1";
//		if(!StringUtils.isEmpty(params.get("userCode"))){
//			sql += " and user_code like '%"+params.get("userCode")+"%'";
//		}
//		if(!StringUtils.isEmpty(params.get("userName"))){
//			sql += " and user_name like '%"+params.get("userName")+"%'";
//		}
//		if(!StringUtils.isEmpty(params.get("nickName"))){
//			sql += " and nick_name like '%"+params.get("nickName")+"%'";
//		}
//		if(!StringUtils.isEmpty(params.get("phone"))){
//			sql += " and phone = '"+params.get("phone")+"'";
//		}
//		if(!StringUtils.isEmpty(params.get("mail"))){
//			sql += " and mail = '"+params.get("mail")+"'";
//		}
//		if(!StringUtils.isEmpty(params.get("userStatus"))){
//			sql += " and use_status = '"+params.get("userStatus")+"'";
//		}
//		sql+=" order by create_time desc";
//		return queryTableList(sql, Integer.valueOf(params.get("pageNumber")), Integer.valueOf(params.get("pageSize")));
		return null;
	}

	/**
	 * 禁用或启用用户
	 */
	@Override
	public int updateUseStatusByIds(String ids, String status) {
		ids = IdsUtil.idsAddSingleQuotes(ids);
		String sql = "update sys_user set use_status='"+status+"' where id in ("+ids+")";
		return update(sql);
	}

	/**
	 * 重置密码
	 */
	@Override
	public int update4ResetPwd(String ids) throws Exception{
		ids = IdsUtil.idsAddSingleQuotes(ids);
		String newPwd = DESUtils.encrypt("Ssxxh123456");
		String sql = "update sys_user set password='"+newPwd+"' where id in ("+ids+")";
		return update(sql);
	}

	/**
	 * 查询导出用户数据
	 */
	@Override
	public List<Map<String, Object>> selectUsers4Export(
			Map<String, String> params) {
		String sql="select id,user_code,user_name,nick_name,password,phone,mail,"
				+ "(case use_status when '0' then '禁用' when '1' then '启用' end) use_status from sys_user where 1=1";
		if(!StringUtils.isEmpty(params.get("userCode"))){
			sql += " and user_code like '%"+params.get("userCode")+"%'";
		}
		if(!StringUtils.isEmpty(params.get("userName"))){
			sql += " and user_name like '%"+params.get("userName")+"%'";
		}
		if(!StringUtils.isEmpty(params.get("nickName"))){
			sql += " and nick_name like '%"+params.get("nickName")+"%'";
		}
		if(!StringUtils.isEmpty(params.get("phone"))){
			sql += " and phone = '"+params.get("phone")+"'";
		}
		if(!StringUtils.isEmpty(params.get("mail"))){
			sql += " and mail = '"+params.get("mail")+"'";
		}
		if(!StringUtils.isEmpty(params.get("userStatus"))){
			sql += " and use_status = '"+params.get("userStatus")+"'";
		}
		
		return queryForList(sql);
	}

	/**
	 * 根据主键查询用户信息
	 */
	@Override
	public SysUser selectById(String id) {
//		String sql = "select "+getSelectAllSql("sys_user")+" from sys_user where id='"+id+"'";
//		return queryForBean(sql);
		return null;
	}

	/**
	 * 用户授权角色
	 */
	@Override
	public int updateUserRole(String userId, String[] roleIds) {
		String dSql = "delete from sys_user_role where user_id = '"+userId+"'";
		int dret = update(dSql);
		int uret = 0;
		for(String roleId:roleIds){
			if(!StringUtils.isBlank(roleId)){
				String id = UUIDGenerator.getUUID();
				String uSql = "insert into sys_user_role(id,user_id,role_id) values('"+id+"','"+userId+"','"+roleId+"')";
				uret += update(uSql);
			}
		}
		return dret+uret;
	}

	/**
	 * 根据userId查询用户角色关联
	 */
	@Override
	public List<Map<String, Object>> selectUserRoleByUserId(String id) {
		String sql = "select ur.role_id id,r.role_name role_name from sys_user_role ur "
				+ "LEFT JOIN sys_role r on ur.role_id=r.id where user_id = '"+id+"'";
		return queryForList(sql);
	}

	/**
	 * 根据用户id集合，查询这些用户的用户和角色关联个数
	 */
	@Override
	public int selectUserRoleCount(String ids) {
		ids = IdsUtil.idsAddSingleQuotes(ids);//根据ids智能添加''
		String sql = "select count(1) from sys_user_role where user_id in("+ids+")";
		return queryForObject(sql, Integer.class);
	}

}
