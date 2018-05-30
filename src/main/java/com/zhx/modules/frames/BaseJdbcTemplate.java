package com.zhx.modules.frames;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.zhx.modules.utils.BeanMapUtils;
import com.zhx.modules.utils.IdsUtil;
import com.zhx.modules.utils.MyStringUtils;

/**
 * dao层的基础类，默认实现了CrudDao中的方法，dao的实现继承此类即可
 * 此类中的方法只适合查询单个实体，关于复杂的业务逻辑查询请自行写sql调用JdbcTemplate中的方法
 * @author xwl
 *
 * @param <T>
 */
@SuppressWarnings({"unchecked","rawtypes","hiding"})
public class BaseJdbcTemplate<T> extends JdbcTemplate {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 获取T的class
	 * @return
	 */
	public Class<?> getClazz(){
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();//获取当前new对象的泛型的父类类型  
	    return (Class<T>) parameterizedType.getActualTypeArguments()[0];  
	}
	
	/**
	 * 获取T的class的名称不带路径
	 * @return
	 */
	public String getType(){
		String classname = getClazz().getName();
		return classname.substring(classname.lastIndexOf(".")+1);
	}
	
	/**
	 * 获取业务表名称
	 * 例如 sysUserService调用 返回 sys_user
	 *    sysRoleService调用 返回 sys_role 
	 * @return
	 */
	public String getTableName(){
		return MyStringUtils.humpStrTo_Str(getType());
	}
	
	/**
	 * 根据主键获取对象信息
	 * @return
	 */
	public T get(String id){
		return getByWhere("id='"+id+"'");
	}
	
	/**
	 * 根据where条件获取唯一的对象信息
	 * @param where 字符串类型的，不能带?要传递实际值，例如： and user_code='1',最前面可带可不带and
	 * @return
	 */
	public T getByWhere(String where){
		return getByWhere(where, null);
	}
	
	/**
	 * 根据where条件获取唯一的对象信息
	 * @param where 字符串类型的，带?。例如: and user_code=? and user_name=?,最前面可带可不带and
	 * @param args 相对应?的实际值
	 * @return
	 */
	public T getByWhere(String where,Object[] args){
		log.info("根据主键获取对象信息：where="+where);
		String w4 = where.substring(0,4).toLowerCase();
		String sql = GlobalCache.selectAllStrMap.get(getTableName())+(w4.startsWith("and ")?where:"and "+where);
		log.info("根据主键获取对象信息,select sql:"+sql);
		return queryForBean(sql,args);
	}
	
	/**
	 * 查询list
	 * @param where 查询语句不带?要传递实际值,最前面可带可不带and
	 * @return
	 */
	public List<T> findAllList(String where,String sort) {
		return findAllList(where,null,sort);
	}
	
	/**
	 * 查询list
	 * @param params 查询参数map
	 * @return
	 */
	public List<T> findAllList(Map<String,Object> params,String sort){
		List<T> list = null;
		Set<String> keys = params.keySet();
		if(null!=keys&&keys.size()>0){
			boolean pageFlag = false;
			StringBuilder where = new StringBuilder();//存储拼接后的sql
			Object[] objects = new Object[keys.size()];//存储查询条件相对应的值
			if(keys.contains("start")&&null!=params.get("length")){//需要分页
				objects = new Object[keys.size()-2];
				pageFlag=true;//分页操作
			}
			int i=0;
			for(String key:keys){
				if(!"start".equals(key)&&!"length".equals(key)){//页码和每页条数不需要作为where条件
					objects[i]=params.get(key);
					//分析params中的key，转换为数据库表中列名称的格式
					key=!key.contains("_")?MyStringUtils.humpStrTo_Str(key):key.toLowerCase();
					where.append(" and ").append(key).append("=?");
					i++;
				}
			}
			if(StringUtils.isNotEmpty(sort.trim())){//sort排序条件去除首尾空格，并且部位空或null
				sort = sort.toLowerCase().replace("order by", "");
				where.append(" order by ").append(sort);//拼接sort排序
			}
			if(pageFlag){
				int pageNo = Integer.parseInt(params.get("start").toString());
				int pageSize = Integer.parseInt(params.get("length").toString());
				int low = (pageNo-1)*pageSize;
				int up = pageNo*pageSize;
				//mysql分页sql用limit
				where.append(" LIMIT ").append(low).append(",").append(up);
			}
			list = findAllList(where.toString(),objects,"");
		}else{
			list = findAllList("",null,sort);
		}
		return list;
	}
	
	/**
	 * 查询list
	 * @param where 查询语句带?,最前面可带可不带and
	 * @param args 相对应?的实际值
	 * @return
	 */
	public List<T> findAllList(String where,Object[] args,String sort){
		log.info("根据主键获取对象信息,where条件:"+where);
		//获取业务表的所有列的select语句，select c1,c2,c3... from tablename where 1=1
		String sql = GlobalCache.selectAllStrMap.get(getTableName());
		StringBuilder sqlStr = new StringBuilder(sql);//存储sql
		if(StringUtils.isNotEmpty(where.trim())){//where查询条件去除首尾空格，并且部位空或null
			String w4 = where.substring(0,4).toLowerCase();
			sqlStr.append(w4.startsWith("and ")?where:"and "+where);//拼接where条件
		}
		if(StringUtils.isNotEmpty(sort.trim())){//sort排序条件去除首尾空格，并且部位空或null
			sort = sort.toLowerCase().replace("order by", "");
			sqlStr.append(" order by ").append(sort);//拼接sort排序
		}
		log.info("根据主键获取对象信息,select sql:"+sqlStr.toString());
		return queryForBeanList(sqlStr.toString(),args);
	}
	
	/**
	 * 新增
	 * @param t
	 * @return
	 */
	public int insert(T t){
		log.info("新增操作:"+t);
		int insertRet = 0;
		try {
			//获取表中列的字符串 c1,c2,c3...
			String columnStr = GlobalCache.columnsStrMap.get(getTableName());
			//以逗号分割，遍历列名称，拼接insert语句和object[]信息
			String[] columns = columnStr.split(",");
			StringBuilder sql = new StringBuilder();//拼接sql
			sql.append("insert into ").append(getTableName())
			  .append(" (").append(columnStr).append(") values (");
			Object[] args = new Object[columns.length];//存储insert语句中所有的?的具体内容
			for(int i=0;i<columns.length;i++){
				sql.append("?,");//拼接?
				//通过反射获取要新增对象的各个属性值
				String column = MyStringUtils._StrToHumpStr(columns[i]);
				column = MyStringUtils.toUpperFristChar(column);
				Method method = getClazz().getDeclaredMethod("get"+column, null);
				args[i] = method.invoke(t, null);
			}
			sql.delete(sql.length()-1, sql.length()).append(")");//sql拼接完成
			log.info("新增sql:"+sql.toString());
			insertRet = update(sql.toString(), args);//执行新增操作
		}catch (Exception e) {
			log.error(e.getMessage());
		}
		return insertRet;
	}
	
	/**
	 * 修改
	 * @param t
	 * @return
	 */
	public int update(T t){
		log.info("修改操作:"+t);
		int updateRet = 0;
		try {
			//获取表中列的字符串 c1,c2,c3...
			String columnStr = GlobalCache.columnsStrMap.get(getTableName());
			//以逗号分割，遍历列名称，拼接insert语句和object[]信息
			String[] columns = columnStr.split(",");
			StringBuilder sql = new StringBuilder();//拼接sql
			sql.append("update ").append(getTableName()).append(" set ");
			List<Object> olist = new ArrayList<Object>();//存储insert语句中所有的?的具体内容
			Object id = null;
			for(int i=0;i<columns.length;i++){
				//通过反射获取要新增对象的各个属性值
				String column = MyStringUtils._StrToHumpStr(columns[i]);
				column = MyStringUtils.toUpperFristChar(column);
				Method method = getClazz().getDeclaredMethod("get"+column, null);
				Object value = method.invoke(t, null);
				if(null==value) continue;
				sql.append(columns[i]).append("=").append("?,");//拼接?
				if("ID".equals(columns[i])) id = value;
				olist.add(value);
			} 
			olist.add(id);
			sql.delete(sql.length()-1, sql.length()).append(" where id").append("=?");//sql拼接完成
			log.info("修改sql:"+sql.toString());
			updateRet = update(sql.toString(), olist.toArray());//执行新增操作
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return updateRet;
	}
	
	/**
	 * 删除
	 * @param t
	 * @return
	 */
	public int delete(String id){
		log.info("删除：id="+id);
		String sql = "update "+getTableName()+" set IS_DEL='1' where id='"+id+"'";
		log.info("删除sql:"+sql);
		return update(sql);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public int batchDelete(String[] ids){
		log.info("批量删除：ids="+StringUtils.join(ids));
		String sql = "update "+getTableName()+" set IS_DEL='1' where id in ("+IdsUtil.idsArrayTrans4Str(ids)+")";
		log.info("批量删除sql:"+sql);
		return update(sql);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public int batchDelete(String ids){
		return batchDelete(ids.split(","));
	}
	
	/**
	 * ==================分割线，下面是复写的内容，不要随意修改=======================
	 */
	
	/**
	 * 自定义查询对象方法
	 * @param sql 查询语句
	 * @return
	 */
	public T queryForBean(String sql) {
        return queryForBean(sql,null);
	}
	
	/**
	 * 自定义查询对象方法
	 * @param sql 查询语句
	 * @param arg 相应的?
	 * @return
	 */
	public T queryForBean(String sql,Object[] args){
		return (T) queryForObject(sql,args,new RowMapper<T>(){
			@Override
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				//查询结果集转为list<map>
				List<Map<String,Object>> listmap = new ArrayList<Map<String,Object>>();
		        ResultSetMetaData md = rs.getMetaData();
		        int columnCount = md.getColumnCount();
				Map rowData = new HashMap();
	            for (int i = 1; i <= columnCount; i++) {
	                rowData.put(MyStringUtils._StrToHumpStr(md.getColumnName(i)), rs.getObject(i));
	            }
	            listmap.add(rowData);
				Map<String,Object> map = listmap.get(0);//只取唯一的结果
				return (T) BeanMapUtils.mapTrans4Bean(map, getClazz());
			}
        	
        });
	}
	
	/**
     * 查询列表，返回对应的类型
     * @param sql
     * @return
     */
    public List<T> queryForBeanList(String sql){
    	return queryForBeanList(sql, null);
    }
    
    /**
     * 查询列表，返回对应的类型
     * @param sql
     * @return
     */
    public List<T> queryForBeanList(String sql,Object[] args){
    	List<T> result = new ArrayList<T>();
    	List<Map<String, Object>> list = queryForList(sql,args);
    	if(null!=list&&list.size()>0){
    		for(Map<String,Object> map:list){
    			T t = BeanMapUtils.mapTrans4Bean(map, getClazz());
    			result.add(t);
    		}
    	}
    	return result;
    }
	
	/**
     * 重写JdbcTemplate里面的queryForObject方法源码调用的requiredSingleResult，当查询到的结果为空时返回null(原来是抛出异常)
     */
    @Override
    public <T> T queryForObject(String sql, Class<T> requiredType) throws DataAccessException {
        return queryForObject(sql, getSingleColumnRowMapper(requiredType));
    }

	public <T> T queryForObject(String sql, RowMapper<T> rowMapper) throws DataAccessException {
        List<T> results = query(sql, rowMapper);
        return requiredSingleResult(results);//防止异常
    }
	
	/**
     * 重写JdbcTemplate里面的queryForObject方法源码调用的requiredSingleResult，当查询到的结果为空时返回null(原来是抛出异常)
     */
	@Override
	public <T> T queryForObject(String sql, Object[] args, Class<T> requiredType)
			throws DataAccessException {
		return queryForObject(sql, args, getSingleColumnRowMapper(requiredType));
	}
	
	public <T> T queryForObject(String sql, Object[] args, RowMapper<T> rowMapper) throws DataAccessException {
		List<T> results = query(sql,args, rowMapper);
        return requiredSingleResult(results);//防止异常
	}

    /**
     * 需要返回单个结果，防止异常
     * @param results
     * @return
     * @throws IncorrectResultSizeDataAccessException
     */
    protected <T> T requiredSingleResult(Collection<T> results) throws IncorrectResultSizeDataAccessException {
        int size = (results != null ? results.size() : 0); 
        if (size == 0) {
            return null; 
        } 
        if (results.size() > 1) {
            throw new IncorrectResultSizeDataAccessException(1, size); 
        } 
        return results.iterator().next(); 
    }
	
}
