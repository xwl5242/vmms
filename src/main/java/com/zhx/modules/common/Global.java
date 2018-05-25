package com.zhx.modules.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zhx.modules.constants.Const;

public class Global {

	private static Global global;
	
	private static Map<String,String> map = new HashMap<String,String>();
	
	private static PropertiesLoader propsLoader = new PropertiesLoader(Const.APPLICATION_PROPERTIES_NAME);
	
	private Global(){}
	
	public static Global getInstance(){
		if(global==null){
			synchronized (Global.class) {
				global = new Global();
			}
		}
		return global;
	}
	
	public static String getConfig(String key){
		String value = map.get(key);
		if(null==value){
			value = propsLoader.getProperty(key);
			map.put(key, value!=null?value:"");
		}
		return value;
	}
	
	public static String getProperty(String key){
		return getConfig(key);
	}
	
	public static String getIgnorableConfig(){
		return getProperty(Const.APPLICATION_IGNORABLE_CONFIG);
	}
	
	public static String getJdbc2DatabaseName(){
		return getProperty(Const.JDBC_DATABASE_NAME);
	}
	
	public static boolean isOpLog(){
		String s =  getProperty(Const.IS_OPT_LOG);
		return StringUtils.isBlank(s)?false:Boolean.valueOf(s);
	}
}
