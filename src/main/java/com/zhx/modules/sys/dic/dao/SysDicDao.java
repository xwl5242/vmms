package com.zhx.modules.sys.dic.dao;

import java.util.Map;

import com.zhx.modules.frames.CrudDao;
import com.zhx.modules.sys.dic.bean.SysDic;

public interface SysDicDao extends CrudDao<SysDic> {

	SysDic selectByNameAndK(String name, String key);

	Map<String, Object> selectDicList(Map<String, String> params);

	int insertDic(SysDic dic);

	SysDic selectById(String id);

	int updateDic(SysDic dic);

	SysDic selectByDicName(String dicName);

	int deleteDics(String ids);

}
