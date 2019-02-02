package com.toredis.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import redis.clients.jedis.Jedis;

public class RedisUtil {
	
	public static Jedis jedis;
	
	public static ServletContext sc;
	
	private static final Log log = LogFactory.getLog(RedisUtil.class);
	
	private static String ip = "127.0.0.1";
	private static String port = "6379";
	
	public static Jedis getJedis(){
		
		String path = "/WEB-INF/classes/redis.properties"; //读取WEB-INF中的配置文件
//		sc = ServletActionContext.getServletContext();
//	    String realPath = sc.getContextPath() + path;
	    
	    Properties props = new Properties();
	    try {
//			props.load(new InputStreamReader(new FileInputStream(realPath),"utf-8"));
	    	props=PropertiesLoaderUtils.loadAllProperties("redis.properties");
		} catch (UnsupportedEncodingException e) {
			log.error("--UnsupportedEncod-->", e);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			log.error("--FileNotFound-->", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("--IO-->", e);
			e.printStackTrace();
		}
	    ip = props.getProperty("redis.ip");
	    port = props.getProperty("redis.port");
	    if (port == null || "".equals(port)) {
			port = "6379";
		}
//	    ip = "10.0.5.83"; port = "7000";
		if (ip != null && !"".equals(ip)) {
			jedis = new Jedis(ip, Integer.valueOf(port));
		}else{
			jedis = new Jedis("127.0.0.1", 6379);
		}
		log.info("--redis_ip-->"+ip+"--port-->"+port);
		
		return jedis;
	}
	
	
	
	
}
