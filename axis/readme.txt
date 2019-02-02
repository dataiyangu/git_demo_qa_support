1将axis.zip文件放在tomcat1中的webapps目录下，并解压，运行tomcat1，并记录下tomcat1所在机器的ip地址以及tomcat的端口号

2将axis_demo-1.0-SNAPSHOT.war文件放在tomcat2中的webapps目录下，首次运行，先不访问，在webapps下生成的war的解压版中，进入目录webapps/axis_demo-1.0-SNAPSHOT/WEB-INF/classes   修改a.properties文件将相应的ip地址和端口号改为上面 的第一步中的即可。 