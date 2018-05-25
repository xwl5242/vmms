package com.zhx.modules.sys.dic.service;

import java.util.Map;

import com.zhx.modules.sys.dic.bean.SysDic;

public interface SysDicService {

	SysDic queryByNameAndK(String name, String key);

	Map<String, Object> queryDicList(Map<String, String> params);

	int saveDic(SysDic dic) throws Exception;

	SysDic queryById(String id);

	int editDic(SysDic dic) throws Exception;

	SysDic queryByDicName(String dicName);

	boolean removeDics(Map<String, String> params) throws Exception;

}
