package com.jndiweblogic;


	
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * <p>ClassName: WebLogicJndiUtil<p>
 * <p>Description: 获取WebLogic服务器中的JNDI数据源工具类<p>
 * @author 
 * @version 1.0 V
 * @createTime 2015-1-4 下午03:54:39
 */
public class WeblogicJndiUtil {

	    //初始化上下文需要用到的工厂类
	    private final static String INITIAL_CONTEXT_FACTORY="weblogic.jndi.WLInitialContextFactory";
	    //WebLogic服务器的访问地址
	    private final static String PROVIDER_URL="t3://127.0.0.1:7001";
	    //WebLogic服务器中的JNDI数据源名称
	    private final static String ORACLE_JNDI_NAME="JNDI/OracleDataSource";
	    private final static String MYSQL_JNDI_NAME="JNDI/MysqlDataSource";
	    
	    //存储从JNDI容器中取出来的数据源
	    private static DataSource dsOracle = null;
	    private static DataSource dsMySQL = null;
	    
	    static {
	        try {
	            //初始化WebLogic Server的JNDI上下文信息
	            Context context = getInitialContext();
	            //获取数据源对象
	            dsOracle = (DataSource) context.lookup(ORACLE_JNDI_NAME);
	            dsMySQL = (DataSource) context.lookup(MYSQL_JNDI_NAME);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    /**
	     * MethodName: getInitialContext
	     * Description: 获得WebLogic ServerJNDI初始上下文信息
	     * @author xudp        
	     * @return
	     * @throws Exception
	     */
	    private static Context getInitialContext() throws Exception {
	        Properties properties = new Properties();
	        properties.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
	        properties.put(Context.PROVIDER_URL, PROVIDER_URL);
	        return new InitialContext(properties);
	    }

	    /**
	     * MethodName: getOracleConnection
	     * Description: 获取Oracle数据库连接
	     * @author xudp        
	     * @return
	     * @throws SQLException
	     */
	    public static Connection getOracleConnection() throws SQLException {
	        return dsOracle.getConnection();
	    }

	    /**
	     * MethodName: getMySQLConnection
	     * Description: 获取MySQL数据库连接
	     * @author xudp        
	     * @return
	     * @throws SQLException
	     */
	    public static Connection getMySQLConnection() throws SQLException {
	        return dsMySQL.getConnection();
	    }
}
	

