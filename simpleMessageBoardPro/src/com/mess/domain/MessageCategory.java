package com.mess.domain;

public class MessageCategory {
	
	private int cid;
	private String name;
	private String desc;
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "MessageCategory [cid=" + cid + ", name=" + name + ", desc=" + desc + "]";
	}

}
