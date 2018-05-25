package com.zhx.modules.constants;

/**
 * 系统相关常量
 * @author xwl
 *
 */
public class Const {
	
	/**
	 * session中后台登录参数相关
	 */
	public static final String SESSION_USER = "user";//session中当前登录用户信息
	public static final String SESSION_USER_ID = "userId";//session中当前登录用户的id
	public static final String SESSION_USER_NAME = "userName";//session中当前登录用户的名称
	public static final String SESSION_RIGHT = "right";//session中当前登录用户的菜单权限
	public static final String SESSION_RIGHT_CHANGED = "rightChanged";//session中当前用户是否权限有变化
	public static final String SESSION_RIGHT_CHANGED_MENU = "rightChangedMenu";//session中当前登录用户的操作哪个菜单导致权限的变化
	
	/**
	 * session中web用户参数相关
	 */
	public static final String SESSION_MEMBER = "member";
	public static final String SESSION_MEMBER_ID = "memberId";
	public static final String SESSION_MEMBER_NAME = "memberName";
	public static final String SESSION_WX_MEMBER = "wxMember";
	
	/**
	 * jdbc配置文件相关
	 */
	public static final String APPLICATION_PROPERTIES_NAME = "application.properties";//应用配置文件名称
	public static final String APPLICATION_IGNORABLE_CONFIG = "ignorableConfig";//十分重要的字符串，参与密码加密
	public static final String JDBC_DATABASE_NAME = "jdbc.databaseName";//数据库名称，很重要，小心修改
	
	/**
	 * 日期时间格式相关
	 */
	public static final String DATE_YYYYMMDDHHMMSS_STR = "yyyy-MM-dd HH:mm:ss";//日期时间格式
	public static final String DATE_YYYYMMDD_STR = "yyyy-MM-dd";//日期格式
	public static final String DATE_HHMMSS_STR = "HH:mm:ss";//时间格式
	
	/**
	 * 返回响应体相关
	 */
	public static final String RESPONSE_CODE="code";//response code
	public static final String RESPONSE_SUCCESS="success";//响应成功
	public static final String RESPONSE_FAIL="fail";//响应失败
	public static final String RESPONSE_EXCEPTION="exception";//响应异常
	public static final String RESPONSE_MSG="msg";//response msg

	/**
	 * 权限查询相关
	 */
	public static final String RIGHT_ROOT_PID="-1";//权限根节点父id
	public static final String RIGHT_ROOT="0";//权限根节点id
	public static final String RIGHT_CION_DEFAULT="fa fa-folder";//组织机构树父目录格式
	public static final String RIGHT_CION_HTML="fa fa-file-code-o";//组织机构末端叶子节点格式
	
	/**
	 * 操作日志相关
	 */
	public static final String IS_OPT_LOG="isOptLog";//是否开启日志标志
	public static final String OPLOG_TYPE_INSERT="0";//新增类型
	public static final String OPLOG_TYPE_DELETE="1";//删除类型
	public static final String OPLOG_TYPE_UPDATE="2";//修改类型
	public static final String OPLOG_TYPE_SELECT="3";//查询类型
	
	/**
	 * 问题类型相关
	 */
	public static final String Q_TYPE_SINGLE = "0";//问题类型：单选
	public static final String Q_TYPE_MUTIL = "1";//问题类型：多选
	public static final String Q_TYPE_FILL_BLANK = "2";//问题类型：填空
	public static final String Q_TYPE_QW = "3";//问题类型：问答
	
	
	/**
	 * 反射方法类型相关
	 */
	public static final String REFLECT_METHODTYPE_GET="get";
	public static final String REFLECT_METHODTYPE_SET="set";
	
	/**
	 * 微信操作类型
	 */
	public static final String WX_OPT_DEFAULT="wx_default";
	public static final String WX_OPT_REGISTER="wx_register";
	public static final String WX_OPT_UPDATE="wx_update";
	public static final String WX_OPT_QUERY_EMPTY="wx_query_empty";
	public static final String WX_OPT_OPEN_WXBRO="wx_open_wxbro";
	
	public static final String WX_RET_SUCCESS="success";
	public static final String WX_RET_FAIL="fail";
	public static final String WX_RET_PROMPT="prompt";
}
