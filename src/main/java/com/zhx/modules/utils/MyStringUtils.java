package com.zhx.modules.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class MyStringUtils {

	/**
	 * 将字符串首字母变为大写
	 * @param string
	 * @return
	 */
	public static String toUpperFristChar(String string) {  
	    char[] charArray = string.toCharArray();  
	    char firstChar = charArray[0];
	    if(Character.isLowerCase(firstChar)){
	    	charArray[0] -= 32;  
	    }
	    return String.valueOf(charArray);  
	}
	
	/**
	 * 将字符串首字母变为小写
	 * @param string
	 * @return
	 */
	public static String toLowerFirstChar(String string){
		char[] charArray = string.toCharArray();  
	    char firstChar = charArray[0];
	    if(Character.isUpperCase(firstChar)){
	    	charArray[0] += 32;  
	    }
	    return String.valueOf(charArray);  
	}
	
	/**
	 * 判断字符是否是大写
	 * @param ch
	 * @return
	 */
	public static boolean isUpperChar(char ch){
		return Character.isUpperCase(ch);
	}
	
	/**
	 * 判断字符是否是小写
	 * @param ch
	 * @return
	 */
	public static boolean isLowerChar(char ch){
		return Character.isLowerCase(ch);
	}
	
	/**
	 * 获取字符串中大写字母的索引值
	 * @param string
	 * @return
	 */
	public static List<Integer> getUpperCharIndex(String string){
		List<Integer> indexs = new ArrayList<Integer>();
		char[] charArray = string.toCharArray();  
		for(int i=0;i<charArray.length;i++){
			if(isUpperChar(charArray[i])){
				indexs.add(i);
			}
		}
		return indexs;
	}
	
	/**
	 * 驼峰式的字符串转为以非首位的大写字母为分割点转为_小写的形式
	 * 例如：SysUserRole(sysUserRole) --> sys_user_role
	 * @param str
	 * @return
	 */
	public static String humpStrTo_Str(String str){
		if(StringUtils.isEmpty(str)) return "";
		//获取字符串中大写字母的索引
		List<Integer> indexs = MyStringUtils.getUpperCharIndex(str);
		if(indexs.get(0)!=0){
			indexs.add(0, 0);
		}
		String[] chips = new String[indexs.size()];//将类名以大写字母为分割点切片，不包含首字母大写
		indexs.add(str.length());//考虑最后一次出现的大写字母的切割
		//开始切割
		if(null!=indexs&&indexs.size()>0){
			for(int i=0;i<indexs.size();i++){
				if(indexs.get(i)!=0){//第一个索引不为0时才切割
					String chip = str.substring(indexs.get(i-1),indexs.get(i));
					chips[i-1] = MyStringUtils.toLowerFirstChar(chip);//保存切片
				}
			}
		}
		return StringUtils.join(chips,"_");//将所有切片以"_"为分隔返回
	}
	
	/**
	 * 与humpStrTo_Str方法相反
	 * 例如 sys_user_role --> sysUserRole
	 * @return
	 */
	public static String _StrToHumpStr(String str){
		if(StringUtils.isEmpty(str)) return "";
		StringBuilder sb = new StringBuilder();
		String[] chips = str.toLowerCase().split("_");//按"_"劈开
		for(int i=0;i<chips.length;i++){
			if(i==0){
				sb.append(chips[i]);continue;
			}
			sb.append(toUpperFristChar(chips[i]));
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(_StrToHumpStr("sys_user_role"));
	}
}
