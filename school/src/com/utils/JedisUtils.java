package com.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;


//import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import redis.clients.util.SafeEncoder;

/**
 * @ClassName: JedisUtil
 * @Description: TODO Jedis 工具类
 * @author Chen Xinjie chenxinjie@bosideng.com
 * @date 2016年3月15日 下午1:18:55
 * 
 */
//@Slf4j
public final class JedisUtils {
  
  // 是否是集群
  private static Boolean isCluster = Boolean.FALSE;

  private static Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
  private static Properties systemProperties = null;
  private static JedisCluster jedis;
  
  static String prefix = "sms:code";
  static String KEY_SPLIT = ":"; //用于隔开缓存前缀与缓存键值

  
  static {
    /**
     * 加载环境变量 设置 Jedis 集群节点
     */
    try {
      systemProperties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/redis.properties"));
      
      if (StringUtils.isNotBlank(systemProperties.getProperty("redis.iscluster"))) {
        if ("1".equals(systemProperties.getProperty("redis.iscluster"))) {
          isCluster = Boolean.TRUE;
        }
      }
      JedisPoolConfig config = new JedisPoolConfig();  
      config.setMaxTotal(50);
      config.setMaxIdle(20);
      config.setMinIdle(10);
      config.setMaxWaitMillis(60000);
      
      config.setTestWhileIdle(true);
      config.setNumTestsPerEvictionRun(1);
      config.setMinEvictableIdleTimeMillis(300000);
      config.setTimeBetweenEvictionRunsMillis(60000);
      
      config.setTestOnBorrow(false);
      config.setTestOnReturn(false);
      
      if (isCluster) {
        // TODO 生产环境 集群
        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel1.host")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel1.port")))
          jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel1.host"), Integer.valueOf(systemProperties.get("redis.sentinel1.port").toString())));
        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel2.host")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel2.port")))
          jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel2.host"), Integer.valueOf(systemProperties.get("redis.sentinel2.port").toString())));
        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel3.host")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel3.port"))) 
          jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel3.host"), Integer.valueOf(systemProperties.get("redis.sentinel3.port").toString())));
        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel4.host")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel4.port")))
          jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel4.host"), Integer.valueOf(systemProperties.get("redis.sentinel4.port").toString())));
        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel5.host")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel5.port")))
          jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel5.host"), Integer.valueOf(systemProperties.get("redis.sentinel5.port").toString())));
        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel6.host")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel6.port")))
          jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel6.host"), Integer.valueOf(systemProperties.get("redis.sentinel6.port").toString())));
        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel7.host")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel7.port")))
          jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel7.host"), Integer.valueOf(systemProperties.get("redis.sentinel7.port").toString())));
        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel8.host")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel8.port")))
          jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel8.host"), Integer.valueOf(systemProperties.get("redis.sentinel8.port").toString())));
        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel9.host")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel9.port")))
          jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel9.host"), Integer.valueOf(systemProperties.get("redis.sentinel9.port").toString())));
        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel10.host")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel10.port")))
          jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel10.host"), Integer.valueOf(systemProperties.get("redis.sentinel10.port").toString())));
        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel11.host")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel11.port")))
          jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel11.host"), Integer.valueOf(systemProperties.get("redis.sentinel11.port").toString())));
        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel12.host")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel12.port")))
          jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel12.host"), Integer.valueOf(systemProperties.get("redis.sentinel12.port").toString())));
//        jedis = new JedisCluster(jedisClusterNodes, Protocol.DEFAULT_TIMEOUT, Protocol.DEFAULT_TIMEOUT, Integer.valueOf(systemProperties.getProperty("redis.sentinel.maxattempts")), systemProperties.getProperty("redis.sentinel.password"), new JedisPoolConfig());
      } else {
        // TODO 测试环境 集群
        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel1.test.host")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel1.test.port")))
          jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel1.test.host"), Integer.valueOf(systemProperties.get("redis.sentinel1.test.port").toString())));
        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel2.test.host")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel2.test.port")))
          jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel2.test.host"), Integer.valueOf(systemProperties.get("redis.sentinel2.test.port").toString())));
        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel3.test.host")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel3.test.port"))) 
          jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel3.test.host"), Integer.valueOf(systemProperties.get("redis.sentinel3.test.port").toString())));
        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel4.test.host")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel4.test.port")))
          jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel4.test.host"), Integer.valueOf(systemProperties.get("redis.sentinel4.test.port").toString())));
        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel5.test.host")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel5.test.port")))
          jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel5.test.host"), Integer.valueOf(systemProperties.get("redis.sentinel5.test.port").toString())));
        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel6.test.host")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel6.test.port")))
          jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel6.test.host"), Integer.valueOf(systemProperties.get("redis.sentinel6.test.port").toString())));
//        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.ip")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.port")))
//            jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.ip"), Integer.valueOf(systemProperties.get("redis.port").toString())));
        
        //2.9  version
        //jedis =new JedisCluster(jedisClusterNodes, Protocol.DEFAULT_TIMEOUT, Protocol.DEFAULT_TIMEOUT, Integer.valueOf(systemProperties.getProperty("redis.sentinel.test.maxattempts")), systemProperties.getProperty("redis.sentinel.test.password"), new JedisPoolConfig());
        
        if (StringUtils.isNotBlank(systemProperties.getProperty("redis.ip")) && StringUtils.isNotBlank(systemProperties.getProperty("redis.port")))
        {   
//        	jedisClusterNodes.add(new HostAndPort("192.168.164.128", 7000));
//	        jedisClusterNodes.add(new HostAndPort("192.168.164.128", 7001));
//	        jedisClusterNodes.add(new HostAndPort("192.168.164.128", 7002));
//        	jedisClusterNodes.add(new HostAndPort("10.0.5.83", 7000));
//	        jedisClusterNodes.add(new HostAndPort("10.0.5.83", 7001));
//	        jedisClusterNodes.add(new HostAndPort("10.0.5.83", 7002));
	        jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.ip"), 7000));
	        jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.ip"), 7001));
	        jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.ip"), 7002));
        }
        
        System.out.println(jedisClusterNodes.toString());
//        jedis = new JedisCluster(jedisClusterNodes, Protocol.DEFAULT_TIMEOUT, Protocol.DEFAULT_TIMEOUT, 10, null, new JedisPoolConfig());
//        jedis = new JedisCluster(jedisClusterNodes, Protocol.DEFAULT_TIMEOUT, Protocol.DEFAULT_TIMEOUT, 10, new JedisPoolConfig());
        
        jedis = new JedisCluster(jedisClusterNodes, 5000, 5000, 1, config);
        // TODO test
        
        System.out.println(jedis + " -- " );
      }
    } catch (IOException e) {
      String fullStackTrace = ExceptionUtils.getStackTrace(e);
//      log.error(fullStackTrace);
    }
  }
  

  /**
   * @param key
   * @param value
   */
  public static void set(String key, Object value) {
    try {
      jedis.set(key.getBytes(), ObjectUtil.toByteArray(value));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * @param key
   * @param seconds
   *          有效时间（秒）
   * @param value
   */
  public static void setex(String key, int seconds, Object value) {
    try {
      jedis.setex(key.getBytes(), seconds, ObjectUtil.toByteArray(value));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 获取
   * 
   * @param key
   * @return
   */
  public static Object get(String key) {
    try {
      byte[] ret = jedis.get(key.getBytes());
      if (ret != null && ret.length != 0) {
        return ObjectUtil.toObject(ret);
      }
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 判断是否存在
   * 
   * @param key
   * @return
   */
  public static Boolean exist(String key) {
    try {
      return jedis.exists(key.getBytes());
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 删除
   * 
   * @param key
   */
  public static void del(String key) {
    try {
      jedis.del(key.getBytes());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 获取自增列
   * 
   * @param key
   * @return
   */
  public static Long incr(String key) {
    try {
      return jedis.incr(key.getBytes());
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Long incrBy(String key, Long value) {
    try {
      return jedis.incrBy(key.getBytes(), value);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Object hget(String key, String field) {
    try {
      return new String(jedis.hget(key.getBytes(), field.getBytes()));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Long hset(String key, String field, String value) {
    try {
      return jedis.hset(key.getBytes(), field.getBytes(), value.getBytes());
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Set<String> hkeys(String key) {
    try {
      Set<String> result = new HashSet<String>();
      Set<byte[]> s = jedis.hkeys(key.getBytes());
      if (s != null && !s.isEmpty()) {
        for (byte[] b : s) {
          result.add(new String(b));
        }
      }
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  public static Map<String, String> hgetAll(String key) {
    try {
      Map<String, String> result = new HashMap<String, String>();
      Map<byte[], byte[]> s = jedis.hgetAll(key.getBytes());
      if (s != null && !s.isEmpty()) {
        for (Map.Entry<byte[], byte[]> entry : s.entrySet()) {
          result.put(new String(entry.getKey()), new String(entry.getValue()));
        }
      }
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Long sadd(String key, String... members) {
    try {
      return jedis.sadd(key.getBytes(), SafeEncoder.encodeMany(members));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
  public static String ping(){
	  try {
		  return jedis.ping();
	} catch (Exception e) {
		e.printStackTrace();
	}
	  return "false";
  }

  public static Set<String> smembers(String key) {
    try {
      Set<String> result = new HashSet<String>();
      Set<byte[]> s = jedis.smembers(key.getBytes());
      if (s != null && !s.isEmpty()) {
        for (byte[] b : s) {
          result.add(new String(b));
        }
      }
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Boolean sismember(String key, String member) {
    try {
      return jedis.sismember(key.getBytes(), member.getBytes());
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
  public static void main(String[] args) {
//	  System.out.println("-- test cluster --" + jedis.toString());
	  System.out.println("-- test cluster --" + JedisUtils.jedis.toString());
	  try {
		  JedisUtils.set(prefix + KEY_SPLIT + "123",  "456");
//		  System.out.println(jedis.set(prefix + KEY_SPLIT + "123",  "456") + jedis.exists(prefix + KEY_SPLIT + "123"));
//		  jedis.get(prefix + KEY_SPLIT + "123").toString();
		  System.out.println(JedisUtils.get(prefix + KEY_SPLIT + "123").toString());
		  
		} catch (Exception e) {
			
			e.printStackTrace();
		}
//	  JedisUtils.set("1", "leo1");
//	  System.out.println(JedisUtils.get("1"));
  }
  
  
  public void test(){
	  try {
		  jedis.set(prefix + KEY_SPLIT + "123",  "456");
	  } catch (Exception e) {
			e.printStackTrace();
	  }
  }
  
}
