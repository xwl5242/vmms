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
	
	public String getTableName(){
		return MyStringUtils.humpStrTo_Str(getType());
	}
	
	/**
	 * 根据主键获取对象信息
	 * @return
	 */
	public T get(String id){
		log.info("根据主键获取对象信息：id="+id);
		String sql = GlobalCache.selectAllStrMap.get(getTableName())+"and id='"+id+"'";
		log.info("根据主键获取对象信息,select sql:"+sql);
		return queryForBean(sql);
	}
	
	/**
	 * 查询list
	 * @param sql
	 * @return
	 */
	public List<T> findAllList(String where) {
		log.info("根据主键获取对象信息,where条件:"+where);
		String tn = MyStringUtils.humpStrTo_Str(getType());
		String sql = GlobalCache.selectAllStrMap.get(tn)+where;
		log.info("根据主键获取对象信息,select sql:"+sql);
		return queryForBeanList(sql);
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
	 * 自定义查询对象方法
	 * @param sql 查询语句
	 * @param requiredType 要返回的类型
	 * @return
	 * @throws DataAccessException
	 */
	public T queryForBean(String sql) {
        return (T) queryForObject(sql, new RowMapper<T>(){
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
				return (T) BeanMapUtils.mapToBean(map, getClazz());
			}
        	
        });
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
	
    /**
     * 查询列表，返回对应的类型
     * @param sql
     * @param clazz
     * @return
     */
    public List<T> queryForBeanList(String sql){
    	List<T> result = new ArrayList<T>();
    	List<Map<String, Object>> list = queryForList(sql);
    	if(null!=list&&list.size()>0){
    		for(Map<String,Object> map:list){
    			T t = BeanMapUtils.mapToBean(map, getClazz());
    			result.add(t);
    		}
    	}
    	return result;
    }
    
    /**
     * 分页查询table数据
     * @param sql 分页查询语句
     * @param pageNo 页码
     * @param pageSize 页行数
     * @return
     */
	public Map queryTableList(String sql,int pageNo,int pageSize){
		String rowsSql = pageSql4mysql(sql,pageNo,pageSize);
		String totalSql = "select count(1) from ("+sql+") total";
		Map result = new HashMap();
		result.put("total", queryForObject(totalSql, int.class));
		result.put("rows", queryForList(rowsSql));
		return result;
	}
	
	/**
	 * 获取mysql分页sql
	 * @param sql 源sql
	 * @param pageNo 页码
	 * @param pageSize 页行数
	 * @return
	 */
	private String pageSql4mysql(String sql, int pageNo, int pageSize) {
		int low = (pageNo-1)*pageSize;
		int up = pageNo*pageSize;
		StringBuffer sb = new StringBuffer();
		//mysql分页sql用limit
		sb.append(sql).append(" LIMIT ").append(low).append(",").append(up);
		return sb.toString();
	}
	
	public String getSelectAllSql(String s){
		return "";
	}
}
