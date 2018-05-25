package com.zhx.modules.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

public class PropertiesLoader {
	
	public final Properties properties;
	
	public PropertiesLoader(String location){
		properties = loadProperties(location);
		loadMoreProperties();
	}

	public Properties getProperties(){
		return properties;
	}
	
	private Properties loadProperties(String location) {
		Properties pros = new Properties();
		try {
			InputStream inStream = new ClassPathResource(location)
					.getInputStream();
			pros.load(new BufferedReader(new InputStreamReader(inStream)));
		} catch (Exception e) {
		}
		return pros;
	}
	
	private void loadMoreProperties(){
		String[] locations = getProperty("configDirs").split(",");
		for(String location:locations){
			if(StringUtils.isNotBlank(location)){
				try {
					InputStream inStream = new ClassPathResource(location+".properties").getInputStream();
					properties.load(new BufferedReader(new InputStreamReader(inStream)));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String getProperty(String key){
		String value = null;
		if(StringUtils.isNotEmpty(key)){
			value = properties.getProperty(key);
		}
		return value;
	}
}
