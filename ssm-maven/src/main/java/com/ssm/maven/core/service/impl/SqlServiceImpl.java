package com.ssm.maven.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssm.maven.core.dao.SqlDao;
import com.ssm.maven.core.entity.DeleteDateBean;
import com.ssm.maven.core.entity.UserColComments;
import com.ssm.maven.core.entity.UserTabComments;
import com.ssm.maven.core.service.SqlService;
import com.ssm.maven.core.util.NotNullUtil;
import com.ssm.maven.core.util.TypeUtil;


@Service("sqlService")
public class SqlServiceImpl implements SqlService {

	@Autowired
    private SqlDao sqlDao;

	@Override
	public List<UserTabComments> queryTableName(String dbName) throws Exception {
		List<UserTabComments> queryTest = sqlDao.queryTableName(dbName);
		for(UserTabComments usertab:queryTest){
			usertab.setTableName(usertab.getTableName().toUpperCase());
		}
		return queryTest;
	}
	@Override
	public List<UserColComments> queryTableField(Map<String,Object> map) throws Exception {
		 //1.查询所有表字段
		return  sqlDao.queryTableField(map);
	}
	@Override
	public List<Map<String,Object>> queryTableInfo(Map<String, Object> map) throws Exception {
		return sqlDao.queryTableInfo(map);
	}
	@Override
	public Long getTotal(String tableName) throws Exception {
		return sqlDao.getTotal(tableName);
	}
	
	@Override
	public Long delData(List<DeleteDateBean> lists) throws Exception {
		NotNullUtil.notNoll(lists);
		int i=0;
		for(DeleteDateBean delBean:lists){
			sqlDao.delData(delBean);
			i++;
		}
		return (long) i;
	}
	
	@Override
	public Long updateData(String infos,String tbName) throws Exception {
		
		JSONObject parseObject = JSON.parseObject(infos);
		StringBuffer str=new StringBuffer();
		String id="";
		for(Entry<String, Object> set:parseObject.entrySet()){
			String typeAnalysis = TypeUtil.typeAnalysis(set.getKey(),set.getValue());
			if("id".equals(set.getKey())){ 
				id=typeAnalysis;
			}else{
				str.append(set.getKey()+"=");
				str.append(typeAnalysis+",");
			}
			
		}
		String updateInfo=str.substring(0,str.length()-1);//修改拼接数据
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("tbName",tbName);
		map.put("updateInfo",updateInfo);
		map.put("id",id);
		Long updateData = sqlDao.updateData(map);
		return updateData;
	}
	/*@Override
	public List<String> queryDataBases(Map<String,Object> map) throws Exception {
		return sqlDao.queryDataBases(map);
	}*/

   


}
