package com.zhx.modules.utils;

import java.util.Map;
import java.util.TreeMap;

public class JDBCTypesUtils {

	private static Map<String, Class<?>> jdbcJavaTypes;
	
	static{
		jdbcJavaTypes = new TreeMap<String, Class<?>>();
	}
}
