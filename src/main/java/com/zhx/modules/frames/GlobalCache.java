package com.zhx.modules.frames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zhx.modules.common.Global;
import com.zhx.modules.constants.Const;
import com.zhx.modules.sys.right.bean.SysRight;
import com.zhx.modules.utils.BeanMapUtils;
import com.zhx.modules.utils.MyStringUtils;

public class GlobalCache {
	
	private Logger logger = LoggerFactory.getLogger(GlobalCache.class); 
	
	/**
	 * 数据库表相关
	 */
	public static List<String> tablesList = new ArrayList<String>();//所有表名称集合
	public static Map<String,List<String>> columnsMap = new HashMap<String,List<String>>();//表，表字段map
	public static Map<String,String> columnsStrMap = new HashMap<String,String>();//表，表字段字符串（column1,column2,column3...）
	public static Map<String,String> selectAllStrMap = new HashMap<String,String>();
	
	/**
	 * 组织机构权限相关
	 */
	public static Map<String,SysRight> rightsMap = new HashMap<String,SysRight>();
	public static List<SysRight> rightsList = new ArrayList<SysRight>();
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void init(){
		initDBTables();
		initRights();
	}

	/**
	 * 初始化组织机构权限相关信息
	 */
	public void initRights(){
		logger.info("*******************初始化权限信息开始*******************");
		try {
			//系统菜单总集sql
			String rightSql = "select id,pid,right_name rightName,right_desc rightDesc,right_url rightUrl,is_leaf isLeaf,icon,seq "
					+ "from sys_right where FIND_IN_SET(id,getChildList('"+Const.RIGHT_ROOT+"')) "
					+ "and pid!= '"+Const.RIGHT_ROOT_PID+"' order by pid,seq";
			//所有'系统菜单'sql
			String orgRightSql = "select id,pid,right_name rightName,right_desc rightDesc,right_url rightUrl,is_leaf isLeaf,icon,seq "
					+ "from sys_right where pid='"+Const.RIGHT_ROOT_PID+"'";
			
			/**
			 * 所有权限,将list遍历转换为Map格式
			 * 作用：用户登录，根据用户角色获取该角色下的权限，权限从这个内存中获取
			 * 注意：权限的增删改需要更新此内存数据
			 */
			List<Map<String,Object>> rights = jdbcTemplate.queryForList(rightSql);
			if(null!=rights&&rights.size()>0){
				for(Map<String,Object> rightMap:rights){
					String rightId = rightMap.get("id").toString();
					SysRight right = BeanMapUtils.mapTrans4Bean(rightMap, SysRight.class);
					rightsMap.put(rightId, right);
				}
			}
			/**
			 * 组织机构树list
			 * 作用：页面中所有有组织机构树的地方，都从这块内存中获取
			 * 注意：权限的增删改需要更新此内存数据
			 */
			List<Map<String,Object>> rightList = new ArrayList<Map<String,Object>>();
			rightList.add(jdbcTemplate.queryForList(orgRightSql).get(0));
			rightList.addAll(rights);
			if(null!=rightList&&rightList.size()>0){
				for(Map<String,Object> rightMap:rightList){
					SysRight right = BeanMapUtils.mapTrans4Bean(rightMap, SysRight.class);
					rightsList.add(right);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("所有权限信息："+rightsMap);
		logger.info("组织机构树信息："+rightsList);
		logger.info("*******************初始化权限信息结束*******************");
	}
	
	/**
	 * 初始化数据库表的所有信息
	 */
	public void initDBTables(){
		logger.info("*******************初始化数据库表开始*******************");
		
		//查询出所有表名称(获取数据库中所有表名称sql)
		String tablesSql = "select table_name from information_schema.TABLES "
				+ "where table_schema = '"+Global.getJdbc2DatabaseName()+"'";
		List<Map<String,Object>> list = jdbcTemplate.queryForList(tablesSql);
		
		//保存表名称，和各个表中列的信息
		if(null!=list&&list.size()>0){
			for(Map<String,Object> map:list){
				//存储所有表名称
				String tablename = map.get("table_name").toString();
				if(tablesList.contains(tablename)){
					tablesList.remove(tablename);
				}
				tablesList.add(tablename);
				//查询各个表的列信息(根据表名称，查询表所含列的sql)
				String columnsSql = "select column_name,data_type from information_schema.COLUMNS "
						+ "where table_schema = '"+Global.getJdbc2DatabaseName()+"' and table_name='"+tablename+"'";
				List<Map<String,Object>> clist = jdbcTemplate.queryForList(columnsSql);
				
				List<String> columnsList = new ArrayList<String>();//存储列名称和列类型，名称和类型之间用@分隔
				StringBuilder columnsStr = new StringBuilder();
				StringBuilder selectAllStr = new StringBuilder();
				if(null!=clist&&clist.size()>0){
					for(Map<String,Object> cmap:clist){
						String cn = cmap.get("column_name").toString();//列名称
						String ct = cmap.get("data_type").toString();//列类型
						columnsList.add(cn+"@"+ct);
						columnsStr.append(",").append(cn);//所有列以","分割的字符串
						selectAllStr.append(",").append(cn.toLowerCase()).append(" ").append(MyStringUtils._StrToHumpStr(cn));
					}
					String selectsql = "select "+selectAllStr.substring(1)+" from "+tablename+" where 1=1 ";
					columnsMap.put(tablename, columnsList);//存储
					columnsStrMap.put(tablename, columnsStr.substring(1));//存储
					selectAllStrMap.put(tablename, selectsql);
				}
			}
		}
		logger.info("所有表名称集合:"+tablesList);
		logger.info("所有表表字段集合:"+columnsMap);
		logger.info("所有表表字段字符串:"+columnsStrMap);
		logger.info("所有表表select all 语句字符串:"+selectAllStrMap);
		logger.info("*******************初始化数据库表结束*******************");
	}
	
}
