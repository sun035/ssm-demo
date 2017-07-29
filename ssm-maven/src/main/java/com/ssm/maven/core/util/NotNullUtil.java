package com.ssm.maven.core.util;

public class NotNullUtil {
	
	public static void notNoll(Object obj){
		if(obj==null){
			throw new RuntimeException("object data is null");
		}
		
	};

}
