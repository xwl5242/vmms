package com.zhx.modules.sys.dic.bean;

public class SysDic {

	private String id;
	private String dcName;
	private String dcK;
	private String dcV;
	private String dcSeq;
	private String dcDesc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDcName() {
		return dcName;
	}
	public void setDcName(String dcName) {
		this.dcName = dcName;
	}
	public String getDcK() {
		return dcK;
	}
	public void setDcK(String dcK) {
		this.dcK = dcK;
	}
	public String getDcV() {
		return dcV;
	}
	public void setDcV(String dcV) {
		this.dcV = dcV;
	}
	public String getDcSeq() {
		return dcSeq;
	}
	public void setDcSeq(String dcSeq) {
		this.dcSeq = dcSeq;
	}
	public String getDcDesc() {
		return dcDesc;
	}
	public void setDcDesc(String dcDesc) {
		this.dcDesc = dcDesc;
	}
	@Override
	public String toString() {
		return "Dic=[id:"+this.id+",dcName:"+this.dcName+",dcK:"+this.dcK+",dcV:"+this.dcV+",dcDesc:"+this.dcDesc+",dcSeq:"+dcSeq;
	}
	
}
