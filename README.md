## 使用jetty框架构造的Restful服务器


## Overview

本项目为我本科毕业设计所做的服务器部分，使用Java语言编写，框架使用了Spring，Mybatis，Jetty构建成Restful形式的服务器



## 项目目录结构

    \src						    //源代码目录
	\mms.action						//资源节点类包
	\mms.bean 						// POJO
	\mms.dao						//数据访问层
	\mms.dto.req						//客户端请求Json对象
	\mms.dto.rsp						//服务器响应Json对象
	\mms.executor					//定时任务，线程等
	\mms.filter						//过滤器
	\mms.service						//业务逻辑层
	\mms.util							//通用工具类
	\mms.util.mail						//邮件操作工具类
	\conf								//配置文件
	\mapper							//Mybatis 配置文件
	Context-db-main.xml				//Spring的数据库配置
	Context-executor.xml				//Spring的线程配置
	Context.xml						//bean配置
	Log4j.properties					//log4j配置文件
	\test									//测试类目录


## 联系我

更多信息，请联系 wangjialong093004@126.com