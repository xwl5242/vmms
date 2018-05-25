package com.zhx.modules.sys.right.bean;

import java.io.Serializable;

public class SysRight implements Serializable{

	private String id;
	private String pid;
	private String rightName;
	private String rightUrl;
	private String rightDesc;
	private String isDel;
	private String icon;
	private String isLeaf;
	private String seq;
	private String creator;
	private String createTime;
	private String updator;
	private String updateTime;
	
	public SysRight(){
	}
	
	public SysRight(String id,String pid,String rightName,String rightUrl,String rightDesc){
		this.id = id;
		this.pid = pid;
		this.rightName = rightName;
		this.rightUrl = rightUrl;
		this.rightDesc = rightDesc;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getRightName() {
		return rightName;
	}
	public void setRightName(String rightName) {
		this.rightName = rightName;
	}
	public String getRightUrl() {
		return rightUrl;
	}
	public void setRightUrl(String rightUrl) {
		this.rightUrl = rightUrl;
	}
	public String getRightDesc() {
		return rightDesc;
	}
	public void setRightDesc(String rightDesc) {
		this.rightDesc = rightDesc;
	}
	
	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "Right=[id:"+this.id+",rightName:"+this.rightName+",rightUrl:"+this.rightUrl
				+",rightDesc:"+this.rightDesc+",pid:"+this.pid+",isDel:"+this.isDel+",isLeaf:"
				+this.isLeaf+",seq:"+this.seq+",creator:"+this.creator+",createTime:"+this.createTime
				+",updator:"+this.updator+",updateTime:"+this.updateTime+"]";
	}
	
}
