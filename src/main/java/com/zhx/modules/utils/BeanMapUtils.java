package com.zhx.modules.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

public class BeanMapUtils {

	public static <T> T mapToBean(Map<String,Object> map,Class<?> clazz){
		T bean = null;
		try{
			if(map!=null){
				bean = (T) clazz.newInstance();
				BeanUtils.populate(bean, map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return bean;
	}
	
	public static <T> Map<?, ?> beanToMap(T obj) {    
        if(obj == null) return null;     
        return new BeanMap(obj);    
    }    
	
	public static <T> List<Map<?,?>> beanListToMapList(List<T> list){
		List<Map<?,?>> result = new ArrayList<Map<?,?>>();
		if(null!=list&&list.size()>0){
			for(T t:list){
				Map<?,?> map = beanToMap(t);
				result.add(map);
			}
		}
		return result;
	}
}
