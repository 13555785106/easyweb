# EasyWeb Framework简介  
在使用JSP/Servlet编写代码的过程中，表单数据到Bean的转换并验证是个繁琐无聊的过程。  
使用EasyWeb框架，可以轻松地将表单数据转换成Bean，并完成数据的校验。

**运行环境**  
在jdk1.8、Tomcat 7.0上测试通过。

由于Hibernate Validator 6使用了el3.0，而Tomcat7使用的是el2.2。
所以需要使用javax.el-3.0.1-b09.jar替换Tomcat\lib下的el包，即拷贝javax.el-3.0.1-b09.jar到Tomcat7.0\lib下，
并删除其下的el-api.jar与jasper-el.jar。

Tomcat8.0以上版本不需要此操作。

![image](demo01.png)