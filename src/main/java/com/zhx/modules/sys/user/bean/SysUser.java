package com.zhx.modules.sys.user.bean;

import java.io.Serializable;

public class SysUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String userCode;
	private String userName;
	private String password;
	private String nickName;
	private String phone;
	private String mail;
	private String sex;
	private String age;
	private String type;
	private	String useStatus;
	private String lastLoginTime;
	private String loginTotal;
	private String isDel;
	private String creator;
	private String createTime;
	private String updator;
	private String updateTime;
	private String themeId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLoginTotal() {
		return loginTotal;
	}
	public void setLoginTotal(String loginTotal) {
		this.loginTotal = loginTotal;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getThemeId() {
		return themeId;
	}
	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}
	@Override
	public String toString() {
		return "SysUser [id=" + id + ", userCode=" + userCode + ", userName="
				+ userName + ", password=" + password + ", nickName="
				+ nickName + ", phone=" + phone + ", mail=" + mail + ", sex="
				+ sex + ", age=" + age + ", type=" + type + ", useStatus="
				+ useStatus + ", lastLoginTime=" + lastLoginTime
				+ ", loginTotal=" + loginTotal + ", isDel=" + isDel
				+ ", creator=" + creator + ", createTime=" + createTime
				+ ", updator=" + updator + ", updateTime=" + updateTime
				+ ", themeId=" + themeId + "]";
	}
	
}
