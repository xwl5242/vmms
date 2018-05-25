package com.zhx.modules.sys.role.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhx.modules.common.DBException;
import com.zhx.modules.sys.role.bean.SysRole;
import com.zhx.modules.sys.role.dao.SysRoleDao;
import com.zhx.modules.sys.role.service.SysRoleService;
import com.zhx.modules.utils.DateUtils;
import com.zhx.modules.utils.UUIDGenerator;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao roleDao;
	
	/**
	 * 新建角色
	 */
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@Override
	public int saveRole(SysRole role) throws Exception{
		int insertRet = 0;
		try {
			role.setId(UUIDGenerator.getUUID());
			role.setIsDel("0");
			role.setCreateTime(DateUtils.date2yyyyMMddHHmmssStr(null));
			role.setUpdateTime(DateUtils.date2yyyyMMddHHmmssStr(null));
			insertRet = roleDao.insertRole(role);
			if(insertRet==0){
				throw new DBException();
			}
		} catch (Exception e) {
			throw e;
		}
		return insertRet;
	}
	
	/**
	 * 修改角色
	 */
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@Override
	public int editRole(SysRole role) throws Exception{
		int insertRet = 0;
		try {
			role.setUpdateTime(DateUtils.date2yyyyMMddHHmmssStr(null));
			insertRet = roleDao.updateRole(role);
			if(insertRet==0){
				throw new DBException();
			}
		} catch (Exception e) {
			throw e;
		}
		return insertRet;
	}

	/**
	 * 查询角色分页信息
	 */
	@Transactional(readOnly=true)
	@Override
	public Map<String, Object> queryRoleList(Map<String, String> params) {
		return roleDao.selectRoleList(params);
	}

	/**
	 * 删除角色
	 */
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@Override
	public boolean removeRoles(Map<String, String> params) throws Exception{
		boolean result = false;
		try {
			String ids = params.get("ids");
			int ur = roleDao.selectUserRoleCountByRoleId(ids);
			int rr = roleDao.selectRoleRightCountByRoleId(ids);
			int dr = roleDao.deleteRoles(ids);
			result = (dr==ids.split(",").length+ur+rr);
			if(!result) throw new DBException();
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * 导出角色
	 */
	@Transactional(readOnly=true)
	@Override
	public List<Map<String, Object>> exportRole(Map<String, String> params) {
		return roleDao.selectRoles4Export(params);
	}

	@Transactional(readOnly=true)
	@Override
	public SysRole queryById(String id) {
		return roleDao.selectById(id);
	}

	@Transactional(readOnly=true)
	@Override
	public SysRole queryByRoleName(String roleName) {
		return roleDao.selectByRoleName(roleName);
	}

	/**
	 * 根据角色id查询，该角色下的所有权限
	 */
	@Transactional(readOnly=true)
	@Override
	public List<Map<String, Object>> queryRoleRightByRoleId(String id) {
		return roleDao.selectRoleRightByRoleId(id);
	}

	/**
	 * 角色授权权限
	 */
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@Override
	public boolean grantRoleRight(String roleId, String[] rightIds) throws Exception{
		boolean result = false;
		try {
			int c = roleDao.selectRoleRightCountByRoleId(roleId);
			int u = roleDao.updateRoleRight(roleId,rightIds);
			result = (u==c);
			if(!result) throw new DBException();
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

}
