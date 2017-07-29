# ssm-demo
一个简单的数据库查询练手项目....    

第一步：通过配置js文件中main.js 
    1. configIp:"http://localhost:8080"  //后台端口主地址
		2. databases:"wblog"//数据库的名称
    
第二步。。没了 直接访问  http://localhost:8080/ssm-maven/views/sjkListMenu.html

前端使用了bootstrap样式 分页插件
后端spring mybatis mysql  


1.增加简单的表注释，表字段注释
2.添加批量删除
3.添加批量修改







问题：后端通过json格式化数据会将MySQL datetime字段序列化成（1234567891234）格式，前端展示没有做处理
