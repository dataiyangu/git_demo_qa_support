package com.ilucky.ejb.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.UUID;

import com.alibaba.druid.pool.DruidDataSource;

public class MainTest {

	private static DruidDataSource dataSource = null;
	
	public static void main(String[] args) {
		test();
	}
	
	public static DruidDataSource getDS() {
		if(dataSource != null) {
			return dataSource;
		}
		try {
			dataSource = new DruidDataSource();
			dataSource.setDriverClassName("com.mysql.jdbc.Driver"); 
			dataSource.setUsername("root");
			dataSource.setPassword("root");
			dataSource.setUrl("jdbc:mysql:replication://10.0.3.47:3306,10.0.3.49:3306/ms?useUnicode=true&characterEncoding=utf8"); 
			dataSource.setInitialSize(5);
			dataSource.setMinIdle(1);
			dataSource.setMaxActive(10); // 启用监控统计功能
			dataSource.setFilters("stat");// for mysql  dataSource.setPoolPreparedStatements(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSource;
	}
	
	/**
	 * 获取oracle datasource
	 * @return
	 */
	public static DruidDataSource getOrclDS() {
		if(dataSource != null) {
			return dataSource;
		}
		try {
			dataSource = new DruidDataSource();
			dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver"); 
			//dataSource.setUsername("nancy001");
			//dataSource.setPassword("test_$#");
			dataSource.setUsername("app");
			dataSource.setPassword("app");
			String url = "jdbc:oracle:thin:@10.0.1.44:1521:ORCL";
			dataSource.setUrl(url); 
			dataSource.setInitialSize(5);
			dataSource.setMinIdle(1);
			dataSource.setMaxActive(10); // 启用监控统计功能
			dataSource.setFilters("stat"); // for mysql  dataSource.setPoolPreparedStatements(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSource;
	}
	
	public static int test() {
		//Connection connection = dataSource.getConnection();
		//return 0;
		 User user = new User();
		 user.setId(UUID.randomUUID().toString());
		 user.setName( "SDX"+user.getId());
		 user.setPassword("PWD"+user.getId());
		return DbUtil.insert(user);
		//return DbUtil.insertOrcl(user);
	}
}

/**
 * 将密码改为错的报如下异常:
log4j:WARN No appenders could be found for logger (com.alibaba.druid.pool.DruidDataSource).
log4j:WARN Please initialize the log4j system properly.
java.sql.SQLException: Access denied for user 'root'@'10.0.5.140' (using password: YES)
	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:1073)
	at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3609)
	at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3541)
	at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:943)
	at com.mysql.jdbc.MysqlIO.secureAuth411(MysqlIO.java:4113)
	at com.mysql.jdbc.MysqlIO.doHandshake(MysqlIO.java:1308)
	at com.mysql.jdbc.ConnectionImpl.coreConnect(ConnectionImpl.java:2336)
	at com.mysql.jdbc.ConnectionImpl.connectOneTryOnly(ConnectionImpl.java:2369)
	at com.mysql.jdbc.ConnectionImpl.createNewIO(ConnectionImpl.java:2153)
	at com.mysql.jdbc.ConnectionImpl.<init>(ConnectionImpl.java:792)
	at com.mysql.jdbc.JDBC4Connection.<init>(JDBC4Connection.java:47)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:57)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:526)
	at com.mysql.jdbc.Util.handleNewInstance(Util.java:411)
	at com.mysql.jdbc.ConnectionImpl.getInstance(ConnectionImpl.java:381)
	at com.mysql.jdbc.NonRegisteringDriver.connect(NonRegisteringDriver.java:305)
	at com.mysql.jdbc.ReplicationConnection.<init>(ReplicationConnection.java:103)
	at com.mysql.jdbc.NonRegisteringDriver.connectReplicationConnection(NonRegisteringDriver.java:432)
	at com.mysql.jdbc.NonRegisteringDriver.connect(NonRegisteringDriver.java:290)
	at com.alibaba.druid.filter.FilterChainImpl.connection_connect(FilterChainImpl.java:148)
	at com.alibaba.druid.filter.stat.StatFilter.connection_connect(StatFilter.java:211)
	at com.alibaba.druid.filter.FilterChainImpl.connection_connect(FilterChainImpl.java:142)
	at com.alibaba.druid.pool.DruidAbstractDataSource.createPhysicalConnection(DruidAbstractDataSource.java:1377)
	at com.alibaba.druid.pool.DruidAbstractDataSource.createPhysicalConnection(DruidAbstractDataSource.java:1431)
	at com.alibaba.druid.pool.DruidDataSource.init(DruidDataSource.java:632)
	at com.alibaba.druid.pool.DruidDataSource.getConnection(DruidDataSource.java:934)
	at com.alibaba.druid.pool.DruidDataSource.getConnection(DruidDataSource.java:930)
	at com.ilucky.druid.util.DbUtil.getConn(DbUtil.java:47)
	at com.ilucky.druid.util.DbUtil.insert(DbUtil.java:25)
	at com.ilucky.druid.MainTest.test(MainTest.java:41)
	at com.ilucky.druid.MainTest.main(MainTest.java:13)
Access denied for user 'root'@'10.0.5.140' (using password: YES)
Exception in thread "main" java.lang.NullPointerException
	at com.ilucky.druid.util.DbUtil.insert(DbUtil.java:31)
	at com.ilucky.druid.MainTest.test(MainTest.java:41)
	at com.ilucky.druid.MainTest.main(MainTest.java:13)
*/
