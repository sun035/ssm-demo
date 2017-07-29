package com.ssm.maven.core.util;

public class TypeUtil {
	
	
	/**
	 * {"parent":"24|varchar","rcount":"237|int","createTime":"1396625003000|datetime"}
	 * 处理类别来拼接字段
	 * @param obj
	 * @return
	 */
	public static String typeAnalysis(String key,Object values){
		if(values instanceof String){
			String value=(String) values;
			String[] split = value.split("&");
			return splitType(split[0],split[1]);
		}
		return null;
	}
	private static String splitType(String str1, String str2) {
		if("int".equals(str2)||"double".equals(str2)){
			return str1;
		}else{
			return "'"+str1+"'";
		}
	}


	

}
