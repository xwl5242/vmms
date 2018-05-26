package com.zhx.modules.sys.right.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhx.modules.frames.BaseJdbcTemplate;
import com.zhx.modules.sys.right.bean.SysRight;
import com.zhx.modules.sys.right.dao.SysRightDao;
import com.zhx.modules.utils.GlobalCacheUtils;

@Repository
public class SysRightDaoImpl extends BaseJdbcTemplate<SysRight> implements SysRightDao {
	
	/**
	 * 分页查询
	 */
	@Override
	public Map<String, Object> selectRightList(Map<String, String> params) {
//		String id = params.get("params");
//		String sql="select "+getSelectAllSql("sys_right")+" from sys_right where id='"+id+"' or pid='"+id+"' ORDER BY is_leaf,seq";
//		return queryTableList(sql, Integer.valueOf(params.get("pageNumber")), Integer.valueOf(params.get("pageSize")));
		return null;
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
	 * 查询权限和角色关联个数
	 */
	@Override
	public int selectRoleRightCountByRightId(String ids) {
		String sql = "select count(1) from sys_role_right where right_id = '"+ids+"'";
		return queryForObject(sql, Integer.class);
	}

}
