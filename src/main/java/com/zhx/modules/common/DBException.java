package com.zhx.modules.common;

public class DBException extends Exception {

	private static final long serialVersionUID = 1L;

	public DBException(){
		super("数据库异常");
	}
}
