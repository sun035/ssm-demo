package com.ssm.maven.core.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ssm.maven.core.basic.ResultWarp;
import com.ssm.maven.core.entity.DeleteDateBean;
import com.ssm.maven.core.entity.UserColComments;
import com.ssm.maven.core.entity.UserTabComments;
import com.ssm.maven.core.service.SqlService;


@Controller
@RequestMapping("/sql")
public class SqlController {

    @Autowired
    private SqlService sqlService;
    private static final Logger log = Logger.getLogger(SqlController.class);// 日志文件
    ResultWarp rw=null;
  
   /* @RequestMapping(value="/showdatabases",method={RequestMethod.GET})
    @ResponseBody
    public String showdatabases(HttpServletRequest request) {
    	try {
    		log.debug("begin query queryDatabases    ");
    		Map<String,Object>map=new HashMap<String,Object>();
    		map.put("schema_name","SCHEMA_NAME");
    		List<String> queryDatabases = sqlService.queryDataBases(map);
    		if(queryDatabases!=null&&!queryDatabases.isEmpty()){
    			rw=new ResultWarp(ResultWarp.SUCCESS,"查询数据库成功");
    			rw.addData("queryDatabases", queryDatabases);
    			log.debug("success query showdatabases    result:"+JSON.toJSONString(queryDatabases));
    		}else{
    			rw=new ResultWarp(ResultWarp.FALED,"查询表名失败");
    			log.debug("false query queryDatabases    result:"+JSON.toJSONString(queryDatabases));
    		}
			String jsonString = JSON.toJSONString(rw,SerializerFeature.WriteMapNullValue);
			return jsonString;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error query queryDatabases ",e);
		}
		return null;
    }*/
    
    @RequestMapping(value="/getsqltb",method={RequestMethod.GET})
    @ResponseBody
    public String getSqlTb(HttpServletRequest request) {
    	try {
    		String data = request.getParameter("data");
    		JSONObject parseObject = JSON.parseObject(data);
    		String dbName = parseObject.getString("dbName");
    		log.debug("begin query tableNames    dbName:"+dbName);
    		List<UserTabComments> queryTest = sqlService.queryTableName(dbName);
    		if(queryTest!=null&&!queryTest.isEmpty()){
    			rw=new ResultWarp(ResultWarp.SUCCESS,"查询表名成功");
    			rw.addData("getTables", queryTest);
    			log.debug("success query tableNames    result:"+JSON.toJSONString(queryTest));
    		}else{
    			rw=new ResultWarp(ResultWarp.FALED,"查询表名失败");
    			log.debug("false query tableNames    result:"+JSON.toJSONString(queryTest));
    		}
			String jsonString = JSON.toJSONString(rw,SerializerFeature.WriteMapNullValue);
			return jsonString;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error query tableNames ",e);
		}
		return null;
        
    }
    
    /**
     * 查询表字段及信息
     * @param request
     * @return
     */
    @RequestMapping(value="/getsqltbfiled",method={RequestMethod.GET})
    @ResponseBody
    public String getsqltbfiled(HttpServletRequest request) {
    	try {
    		String data = request.getParameter("data");
    		JSONObject parseObject = JSON.parseObject(data);
    		String dbName = parseObject.getString("dbName");
    		String tbName = parseObject.getString("tbName");
    		int page = parseObject.getIntValue("page");
    		int size = parseObject.getIntValue("size");
    		Map<String,Object>map=new HashMap<String,Object>();
    		map.put("dbName",dbName);
    		map.put("tbName",tbName);
    		map.put("start",(page-1)*size);
    		map.put("end", page*size);
    		
    		
    		log.debug("begin getsqltbfiled   map:"+JSON.toJSONString(map));
    		List<UserColComments> queryTableField = sqlService.queryTableField(map);//表字段信息
    		List<Map<String,Object>> queryTableInfo = sqlService.queryTableInfo(map);//表数据
    		
			if(queryTableField!=null&&!queryTableField.isEmpty()){
				
				rw=new ResultWarp(ResultWarp.SUCCESS,"查询表字段成功");
    			rw.addData("queryTableField",queryTableField);
    			rw.addData("queryTableInfo",queryTableInfo);
    			Long total = sqlService.getTotal(tbName);
    			rw.addData("total", total);
    			log.debug("success getsqltbfiled   result:"+JSON.toJSONString(queryTableField));
    		}else{
    			rw=new ResultWarp(ResultWarp.FALED,"查询表字段失败");
    			log.debug("false getsqltbfiled   result:"+JSON.toJSONString(queryTableField));
    		}
			String jsonString = JSON.toJSONString(rw,SerializerFeature.WriteMapNullValue);
			System.out.println(jsonString);
			return jsonString;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error query tableNames ",e);
		}
		return null;
        
    }
    /**
     * 删除数据
     * @param request
     * @return
     */
    @RequestMapping(value="/delData",method={RequestMethod.POST})
    @ResponseBody
    public String delData(HttpServletRequest request) {
    	try {
    		String data = request.getParameter("data").replaceAll("\"","");
    		System.out.println(data);
    		JSONArray array = JSON.parseArray(data);
    		List<DeleteDateBean>lists=new ArrayList<DeleteDateBean>();
    		for(int i=0;i<array.size();i++){
    			DeleteDateBean delDatas=array.getObject(i,DeleteDateBean.class);
    			delDatas.setId("'"+delDatas.getId()+"'");
    			lists.add(delDatas);
    		}
    		if(lists.isEmpty()){
    			rw=new ResultWarp(ResultWarp.FALED,"删除表数据为空");
    			return JSON.toJSONString(rw,SerializerFeature.WriteMapNullValue);
    		}
    		log.debug("begin delData   lists:"+JSON.toJSONString(lists));
    		Long num = sqlService.delData(lists);//
    		
			if(num!=0L){
				rw=new ResultWarp(ResultWarp.SUCCESS,"删除表数据成功");
    			rw.addData("num", num);
    			log.debug("success delData   result:"+JSON.toJSONString(num));
    		}else{
    			rw=new ResultWarp(ResultWarp.FALED,"删除表数据为空");
    			log.debug("false delData   result:"+JSON.toJSONString(num));
    		}
			String jsonString = JSON.toJSONString(rw,SerializerFeature.WriteMapNullValue);
			System.out.println(jsonString);
			return jsonString;
		} catch (Exception e) {
			log.error("error  delData ",e);
			e.printStackTrace();
			rw=new ResultWarp(ResultWarp.FALED,""+e);
			return JSON.toJSONString(rw,SerializerFeature.WriteMapNullValue);
			
		}
        
    }
    
    /**
     * 修改数据
     * @param request
     * @return
     */
    @RequestMapping(value="/updateData",method={RequestMethod.POST})
    @ResponseBody
    public String updateData(HttpServletRequest request) {
    	try {
    		String tbName = request.getParameter("tbName");
    		String data = request.getParameter("data").replaceAll("\"","");
    		System.out.println(data);
    		JSONArray array = JSON.parseArray(data);
    		Long d=0L;
    		for(int i=0;i<array.size();i++){
    			Long num = sqlService.updateData(array.get(i).toString(),tbName);
    			d+=num;
    		}
    		if(d==0){
    			rw=new ResultWarp(ResultWarp.FALED,"修改表数据失败");
    			return JSON.toJSONString(rw,SerializerFeature.WriteMapNullValue);
    		}else{
    			rw=new ResultWarp(ResultWarp.SUCCESS,"修改表数据成功");
    			log.debug("false delData   result:"+JSON.toJSONString(d));
    		}
			String jsonString = JSON.toJSONString(rw,SerializerFeature.WriteMapNullValue);
			System.out.println(jsonString);
			return jsonString;
		} catch (Exception e) {
			log.error("error  delData ",e);
			e.printStackTrace();
			rw=new ResultWarp(ResultWarp.FALED,""+e);
			return JSON.toJSONString(rw,SerializerFeature.WriteMapNullValue);
			
		}
        
    }
    
    
}
