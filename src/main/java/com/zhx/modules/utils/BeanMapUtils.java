package com.zhx.modules.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BeanMapUtils {
	
	public static ObjectMapper om = new ObjectMapper();

	/**
	 * map 转为 bean
	 * @param map
	 * @param clazz
	 * @return
	 */
	public static <T> T mapTrans4Bean(Map<String,Object> map,Class<?> clazz){
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
	
	/**
	 * map转为javaBean
	 * @param source 带转换的map
	 * @param clazz javaBean 的class
	 * @return
	 */
	public static <T> T beanTrans4Map(T bean){
		T result = null;
		try{
			result = (T) om.readValue(om.writeValueAsBytes(bean), Map.class);
		}catch(Exception e){
		}
		return result;
	}
	
	/**
	 * list<JavaBean> 转换为 list<map<K,V>>
	 * @param list 带转换的list
	 * @return 转换结果
	 */
	public static List<Map<String,Object>> beanListTrans4MapList(List<?> list){
		List<Map<String,Object>> rlist = new ArrayList<Map<String,Object>>();
		byte[] bytes;
		try {
			bytes = om.writeValueAsBytes(list);
			rlist = (List<Map<String,Object>>)om.readValue(bytes, List.class);
		} catch (Exception e) {
		}
		return rlist;
	}
}
