package com.ssm.maven.core.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssm.maven.core.entity.DeleteDateBean;
import com.ssm.maven.core.entity.UserColComments;
import com.ssm.maven.core.entity.UserTabComments;


@Repository
public interface SqlDao {
		//数据库查询表
		/*public List<String> queryDataBases(Map<String,Object> map) throws Exception ;*/
		//数据库查询表
		public List<UserTabComments> queryTableName(String dbName) throws Exception ;
		//数据库查询表字段
		public  List<UserColComments> queryTableField(Map<String,Object> map) throws Exception ;
		//数据库查询表数据
		public  List<Map<String,Object>> queryTableInfo(Map<String,Object> map) throws Exception ;
		//数据库查询表数量
		public  Long getTotal(String tableName) throws Exception;
		//数据库批量删除数据
		public  Long delData(DeleteDateBean delBean) throws Exception;
		//数据库批量修改数据
		public  Long updateData(Map map) throws Exception;
    
}
