package com.zhx.modules.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhx.modules.constants.Const;
import com.zhx.modules.frames.GlobalCache;
import com.zhx.modules.sys.right.bean.SysRight;

public class GlobalCacheUtils {
	
	/**
	 * 生成父子结构的菜单树
	 * @param menuList 数据库查询结果
	 * @param parentId 父ID
	 * @return
	 */
	public static List<Map<String,Object>> menuTreeList(List<Map<String,Object>> menuList, String parentId){
		List<Map<String,Object>> childMenu = new ArrayList<Map<String,Object>>();  
		if(null!=menuList&&menuList.size()>0){
			for (Map<String,Object> jsonMenu : menuList) {  
				String menuId = jsonMenu.get("id").toString();  
				String pid = jsonMenu.get("pid").toString();  
				if (parentId.equals(pid)) {  
					List<Map<String,Object>> c_node = menuTreeList(menuList, menuId);  
					jsonMenu.put("childNode", c_node);  
					childMenu.add(jsonMenu);  
				}  
			}  
		}
        return childMenu;  
	}
	
	public static List<Map<String,Object>> orgTreeList(List<Map<?, ?>> rlist) {
		//state里面的内容,默认树的节点都是打开的 
		Map<String,Object> state = new HashMap<String,Object>();
		state.put("opened", true);
		if(null!=rlist&&rlist.size()>0){
			//只保留有用的的部分
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			for (Map<?, ?> org : rlist) {  
				Map<String,Object> treeMap = new HashMap<String, Object>();
				treeMap.put("id", org.get("id"));
				treeMap.put("pid", org.get("pid"));
				treeMap.put("text", org.get("rightName"));
				treeMap.put("icon", org.get("icon"));
				treeMap.put("state", state);
				list.add(treeMap);
			}
			return orgTreeList(list,Const.RIGHT_ROOT_PID);
		}
		return null;
	}
	
	/**
	 * 组织机构树，jsTree
	 * @param menuList 数据库查询结果
	 * @param parentId 父ID
	 * @return
	 */
	public static List<Map<String,Object>> orgTreeList(List<Map<String,Object>> orgList, String parentId) {
		//最终结果
		List<Map<String,Object>> orgTreeList = new ArrayList<Map<String,Object>>();
		for (Map<String,Object> org : orgList) {  
			String orgId = org.get("id").toString();  
			String pid = org.get("pid").toString();
			//递归获得children
			if (parentId.equals(pid)) {  
				List<Map<String,Object>> c_node = orgTreeList(orgList, orgId);  
				org.put("children", c_node); 
				orgTreeList.add(org);  
			}  
		}  
        return orgTreeList;  
	}
	
	/**
	 * 更新内存中数据，opt是操作类型，0：insert，1：update，2：delete
	 * @param right
	 * @param opt
	 */
	public static void updateGlobalCache(SysRight right,int opt){
		if(opt==0){
			GlobalCache.rightsList.add(right);
			GlobalCache.rightsMap.put(right.getId(), right);
		}else if(opt==1){
			for(SysRight r:GlobalCache.rightsList){
				if(r.getId().equals(right.getId())){
					GlobalCache.rightsList.remove(r);
					GlobalCache.rightsList.add(right);
					break;
				}
			}
			GlobalCache.rightsMap.remove(right.getId());
			GlobalCache.rightsMap.put(right.getId(),right);
		}else{
			for(SysRight r:GlobalCache.rightsList){
				if(r.getId().equals(right.getId())){
					GlobalCache.rightsList.remove(r);
					break;
				}
			}
			GlobalCache.rightsMap.remove(right.getId());
		}
	}
}
