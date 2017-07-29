package com.ssm.maven.core.service;

import java.util.List;
import java.util.Map;

import com.ssm.maven.core.entity.DeleteDateBean;
import com.ssm.maven.core.entity.UserColComments;
import com.ssm.maven.core.entity.UserTabComments;


public interface SqlService {

	//数据库查询表
	/*public List<String> queryDataBases(Map<String,Object> map) throws Exception ;*/
	//数据库查询表
	public List<UserTabComments> queryTableName(String dbName ) throws Exception ;
	//数据库查询表字段和内容
	public  List<UserColComments> queryTableField(Map<String,Object> map) throws Exception ;
	//数据库查询表数据
	public  List<Map<String,Object>> queryTableInfo(Map<String,Object> map) throws Exception ;
	//数据库查询表数量
	public  Long getTotal(String tableName) throws Exception ;
	//数据库批量删除数据
	public  Long delData(List<DeleteDateBean> lists) throws Exception;
	//数据库批量修改数据
	public  Long updateData(String infos,String tbName) throws Exception;
}
