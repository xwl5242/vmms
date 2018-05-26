package com.zhx.modules.sys.dic.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhx.modules.common.DBException;
import com.zhx.modules.sys.dic.bean.SysDic;
import com.zhx.modules.sys.dic.dao.SysDicDao;
import com.zhx.modules.sys.dic.service.SysDicService;
import com.zhx.modules.utils.IdsUtil;
import com.zhx.modules.utils.UUIDGenerator;

@Service
public class SysDicServiceImpl implements SysDicService {

	@Autowired
	private SysDicDao dicDao;

	@Transactional(readOnly=true)
	@Override
	public SysDic queryByNameAndK(String name, String key) {
		return dicDao.getByWhere("dc_name='"+name+"' and dc_k='"+key+"'");
	}

	@Transactional(readOnly=true)
	@Override
	public Map<String, Object> queryDicList(Map<String, String> params) {
		return null;
//		return dicDao.selectDicList(params);
	}

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@Override
	public int saveDic(SysDic dic) throws Exception{
		int insertRet = 0;
		try {
			dic.setId(UUIDGenerator.getUUID());
			dic.setDcSeq("0");
			insertRet = dicDao.insert(dic);
			if(insertRet==0){
				throw new DBException();
			}
		} catch (Exception e) {
			throw e;
		}
		return insertRet;
	}

	@Transactional(readOnly=true)
	@Override
	public SysDic queryById(String id) {
		return dicDao.get(id);
	}

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@Override
	public int editDic(SysDic dic) throws Exception{
		int insertRet = 0;
		try {
			insertRet = dicDao.update(dic);
			if(insertRet==0){
				throw new DBException();
			}
		} catch (Exception e) {
			throw e;
		}
		return insertRet;
	}

	@Transactional(readOnly=true)
	@Override
	public SysDic queryByDicName(String dicName) {
		return dicDao.getByWhere("dc_name like '%"+dicName+"%'");
	}

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@Override
	public boolean removeDics(Map<String, String> params) throws Exception{
		boolean result = false;
		try{
			String ids = params.get("ids");
			int des = dicDao.batchDelete(ids);
			result = (des==IdsUtil.idsStrTrans4Array(ids).length);
			if(!result) throw new DBException();
		}catch(Exception e){
			throw e;
		}
		return result;
	}	
	
}
