## 如何启动本项目
> 用ide打开本项目，本项目的后端是agiletest，用springboot构建，选择springboot项目打开即可
> 本项目采用的是mysql数据库 使用前先导入ticket.sql文件注意创建ticket这个数据库才能导入文件！！！
> 如果部署这个项目的呢，假设现在在aliletest中，需要修改的配置有
在 agiletest\src\main\resources\application.yml文件中修改mysql的配置

## 运行项目前配置项目


```yaml
yml
    spring:
     session:
       store-type: none
     datasource:
        name: mysql_test
        type: com.alibaba.druid.pool.DruidDataSource
        #druid相关配置
        druid:
          #监控统计拦截的filters
          filters: stat
          driver-class-name: com.mysql.jdbc.Driver
          #基本属性
			url: jdbc:mysql://localhost:3306/ticket?							 zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=America/New_York

      username: xxxx  # 这里修改成自己的用户名
      password: xxxx # 这里修改成自己的密码
```
```javascript
//如果要部署本项目，在部署前请修改agiletest\src\main\resources\static\static\adminmanage\js\requesturl.js文件中的url地址
var requestUrl = "http://localhost:8080/manage";

//在agiletest\src\main\resources\static\static\js\custom.js中的
var base_url = "http://47.102.113.125:8089";

//在agiletest\src\main\resources\static\static\js\loginJS.js中的
var base_url = "http://47.102.113.125:8089";
```

