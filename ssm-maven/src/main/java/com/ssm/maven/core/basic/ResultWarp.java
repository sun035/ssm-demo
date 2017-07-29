package com.ssm.maven.core.basic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResultWarp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String msg;
	private Map<String,Object> data=new HashMap<String,Object>();
	
	
	public final static String SUCCESS="0000";
	public final static String FALED="1000";
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public void addData(String key,Object val) {
		this.data.put(key,val);
	}
	public ResultWarp(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	
	

}
