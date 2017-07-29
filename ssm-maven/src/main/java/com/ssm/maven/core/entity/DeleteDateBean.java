package com.ssm.maven.core.entity;

/**
 * @author thinkpad
 * 删除数据所封装实体类
 */
public class DeleteDateBean {
	String dbName;
	String tbName;
	String id;
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getTbName() {
		return tbName;
	}
	public void setTbName(String tbName) {
		this.tbName = tbName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	

}
