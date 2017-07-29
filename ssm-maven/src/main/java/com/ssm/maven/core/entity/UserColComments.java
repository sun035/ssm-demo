package com.ssm.maven.core.entity;

public class UserColComments {
        private String columnName;// 表字段
        private String dataType   ;// 字段类型
        private String columnComment;//字段注释
		public String getColumnName() {
			return columnName;
		}
		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}
		public String getDataType() {
			return dataType;
		}
		public void setDataType(String dataType) {
			this.dataType = dataType;
		}
		public String getColumnComment() {
			return columnComment;
		}
		public void setColumnComment(String columnComment) {
			this.columnComment = columnComment;
		}
		
        
}