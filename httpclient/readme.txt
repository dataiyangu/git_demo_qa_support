两个版本的包在相应的文件夹下面：


进入相应的文件夹将war包放在tomcat中，

其中同样有a.properties

操作类似于 之前的jersry

先运行一次tomcat，将war解压，之后停了tomcat，进入WEB-INF目录/classes/a.properties
修改为 相应的地址和端口（还用之前的jersey_tomcat这个demo的地址和端口即可）

再次启动tomcat

执行这个war包即可