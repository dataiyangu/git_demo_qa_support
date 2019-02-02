将两个war包分别放在tomcat下， 可以放在一个tomcat下，也可以分开放。

1.jersey_tomcat  是发送数据端放在tomcat下，启动tomcat即可，不需要访问

2.jersetDemo   是去主动请求jersey_tomcat，放在tomcat下，通过浏览器去请求jersetDemo，在jerseydemo中，
进入webapps/jerseyDemo-1.0-SNAPSHOT/WEB-INF/classes
里面有  a.properties，进入配置 jersey_tomcat的路径      



3.在浏览器去访问jerseyDemo