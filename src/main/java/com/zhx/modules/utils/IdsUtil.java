package com.zhx.modules.utils;


public class IdsUtil {

	/**
	 * ids添加单引号
	 * @return
	 */
	public static String idsAddSingleQuotes(String ids){
		if(!ids.contains("'")){
			ids = ids.replaceAll(",", "','");
			ids = "'"+ids+"'";
		}
		return ids;
	}
	
	/**
	 * 将ids字符串转为数组，'id1','id2','id3' ==> [id1,id2,id3]
	 * @param ids
	 * @return
	 */
	public static String[] idsStrTrans4Array(String ids){
		ids = idsAddSingleQuotes(ids);
		ids = ids.replaceAll("'", "");
		return ids.split(",");
	}
	
	/**
	 * 将ids字符串转为数组， [id1,id2,id3] ==>'id1','id2','id3'
	 * @param ids
	 * @return
	 */
	public static String idsArrayTrans4Str(String[] ids){
		StringBuilder sb = new StringBuilder();
		for(String id:ids){
			sb.append(id).append(",");
		}
		return idsAddSingleQuotes(sb.substring(0,sb.length()-1));
	}
}
