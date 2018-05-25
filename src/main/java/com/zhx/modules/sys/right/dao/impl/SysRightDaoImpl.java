package com.zhx.modules.sys.right.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhx.modules.frames.BaseJdbcTemplate;
import com.zhx.modules.sys.right.bean.SysRight;
import com.zhx.modules.sys.right.dao.SysRightDao;
import com.zhx.modules.utils.GlobalCacheUtils;

@Repository
public class SysRightDaoImpl extends BaseJdbcTemplate<SysRight> implements SysRightDao {
	
	/**
	 * 新建权限
	 */
	@Override
	public int insertRight(SysRight right) {
//		String insertSql = CreateSqlTools.getCreateSql(right,SysRight.class,"sys_right");
//		return update(insertSql);
		return 0;
	}

	/**
	 * 修改权限
	 */
	@Override
	public int updateRight(SysRight right) {
//		String updateSql = CreateSqlTools.getUpdateSql(right, SysRight.class, "sys_right");
//		return update(updateSql);
		return 0;
	}
	
	/**
	 * 根据权限登录名称查询权限信息
	 */
	@Override
	public SysRight selectByRightName(String rightName) {
		String sql = "select "+getSelectAllSql("sys_right")+" from sys_right where right_name='"+rightName+"'";
		return super.queryForBean(sql);
	}

	/**
	 * 分页查询
	 */
	@Override
	public Map<String, Object> selectRightList(Map<String, String> params) {
		String id = params.get("params");
		String sql="select "+getSelectAllSql("sys_right")+" from sys_right where id='"+id+"' or pid='"+id+"' ORDER BY is_leaf,seq";
		return queryTableList(sql, Integer.valueOf(params.get("pageNumber")), Integer.valueOf(params.get("pageSize")));
	}

	/**
	 * 删除权限
	 */
	@Override
	public int deleteRights(String ids) {
		String sql = "delete from sys_right where id ='"+ids+"'";
		String rrsql = "delete from sys_role_right where right_id='"+ids+"'";
		return update(sql)+update(rrsql);
	}

	/**
	 * 主键查询
	 */
	@Override
	public SysRight selectById(String id) {
		String sql = "select "+getSelectAllSql("sys_right")+" from sys_right where id='"+id+"'";
		return queryForBean(sql);
	}

	/**
	 * 更新isLeaf和Icon信息
	 */
	@Override
	public int updateLeafAndIcon(String id, String isLeaf,
			String icon) {
		String updateSql = "update sys_right set is_leaf='"+isLeaf+"',icon='"+icon+"' where id='"+id+"'";
		String sql = "select * from sys_right where id='"+id+"'";
		int result = update(updateSql);
		SysRight right = queryForBean(sql);
		GlobalCacheUtils.updateGlobalCache(right, 1);
		return result;
	}

	/**
	 * 根据父id查询
	 */
	@Override
	public List<?> selectByPid(String pid) {
		String sql = "select "+getSelectAllSql("sys_right")+" from sys_right where pid='"+pid+"'";
		return queryForList(sql);
	}

	/**
	 * 查询权限和角色关联个数
	 */
	@Override
	public int selectRoleRightCountByRightId(String ids) {
		String sql = "select count(1) from sys_role_right where right_id = '"+ids+"'";
		return queryForObject(sql, Integer.class);
	}

}
