package com.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class HttpUtil
{
  private static final Logger log = Logger.getLogger(HttpUtil.class);

  public static Map<String, String> parseRequest(HttpServletRequest request)
  {
    Map fieldMap = new HashMap();
    Enumeration names = request.getParameterNames();
    while (names.hasMoreElements()) {
      String name = (String)names.nextElement();
      String values = request.getParameter(name);
      if (null != values) values = values.trim();
      fieldMap.put(name, values);
    }

    return fieldMap;
  }

  public static Map<String, Object> parseListRequest(HttpServletRequest request)
  {
    Map map = new HashMap();
    try {
      InputStream in = request.getInputStream();
      byte[] buff = new byte[in.available()];
      int n = in.read(buff);
      String responseXml = new String(buff, 0, n, "GBK");
//      map = XmlUtil.decodeBySax(buff);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }

  public static String mapToRequestParameter(Map map, String enc)
  {
    StringBuffer sb = new StringBuffer();
    Set set = map.keySet();
    for (Iterator iter = set.iterator(); iter.hasNext(); ) {
      Object name = iter.next();
      if (name != null) {
        String key = (String)name;
        if (key.trim() != "") {
          Object value = map.get(key);
          if (value == null) {
            value = "";
          }
          sb.append(key);
          sb.append("=");
          try {
            if ((null == enc) || ("null".equals(enc)))
              sb.append(URLEncoder.encode(value.toString()));
            else
              sb.append(URLEncoder.encode(value.toString(), enc));
          }
          catch (UnsupportedEncodingException e)
          {
            RuntimeException exc = new RuntimeException(e.getMessage());
            exc.setStackTrace(e.getStackTrace());
            throw exc;
          }
          sb.append("&");
        }
      }
    }
    return sb.toString();
  }

  public static String getParameterFromQuerystring(String queryString, String parameter) {
    if ((null == queryString) || (null == parameter)) return null;
    int i = queryString.indexOf(parameter);
    if (i == -1) return null;
    queryString = queryString.substring(i);
    i = queryString.indexOf("&");
    if (-1 == i) {
      return queryString.replaceFirst(new StringBuilder().append(parameter).append("=").toString(), "");
    }
    queryString = queryString.substring(0, i);
    return queryString.replaceFirst(new StringBuilder().append(parameter).append("=").toString(), "");
  }

  public static String getCookieValue(HttpServletRequest request, String name)
  {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (int i = 0; i < cookies.length; i++) {
        if (name.equals(cookies[i].getName())) {
          return cookies[i].getValue();
        }
      }

    }

    return "";
  }

  public static String getCustIp(HttpServletRequest request)
  {
    String ipAddress = null;
    ipAddress = request.getHeader("x-forwarded-for");
    if ((ipAddress == null) || (ipAddress.length() == 0) || ("unknown".equalsIgnoreCase(ipAddress))) {
      ipAddress = request.getHeader("Proxy-Client-IP");
    }
    if ((ipAddress == null) || (ipAddress.length() == 0) || ("unknown".equalsIgnoreCase(ipAddress))) {
      ipAddress = request.getHeader("WL-Proxy-Client-IP");
    }
    if ((ipAddress == null) || (ipAddress.length() == 0) || ("unknown".equalsIgnoreCase(ipAddress))) {
      ipAddress = request.getHeader("X-Real-IP");
      if ((null == ipAddress) || ("".equals(ipAddress.trim())))
        ipAddress = request.getRemoteAddr();
      if (ipAddress.equals("127.0.0.1"))
      {
        InetAddress inet = null;
        try {
          inet = InetAddress.getLocalHost();
        } catch (Exception e) {
          e.printStackTrace();
        }
        ipAddress = inet.getHostAddress();
      }
    }

    String ips = ipAddress;

    if ((ipAddress != null) && (ipAddress.length() > 15) && 
      (ipAddress.indexOf(",") > 0)) {
      ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
    }

    if ("unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = "";
    }
    log.info(String.format("Proxy-Client-IP:[%s],WL-Proxy-Client-IP:[%s],X-Real-IP:[%s],remote:[%s]", new Object[] { request.getHeader("Proxy-Client-IP"), request.getHeader("WL-Proxy-Client-IP"), request.getHeader("X-Real-IP"), request.getRemoteAddr() }));
    log.info(String.format("ips:[%s]获取用户请求IP成功:%s", new Object[] { ips, ipAddress }));
    String ua = StringUtils.trim(request.getHeader("User-Agent"));
    log.info(new StringBuilder().append("【UA信息】:").append(ua).toString());
    return ipAddress;
  }

  public static String urlSchemeReplace(HttpServletRequest request, String url) {
    String Scheme = request.getHeader("X-SCHEME");
    log.info(String.format("url：%s", new Object[] { url }));
    if ("HTTPS".equals(Scheme)) {
      if (url.startsWith("http://")) url = url.replaceFirst("http", "https");
    }
    else if (url.startsWith("https://")) url = url.replaceFirst("https", "http");

    log.info(String.format("处理后：%s", new Object[] { url }));
    return url;
  }

  public static void addCheckCookie(HttpServletResponse response, String key)
  {
    Cookie cookie = new Cookie(encodeMD5(key), UUID.randomUUID().toString().replaceAll("-", ""));
    cookie.setPath("/");
    response.addCookie(cookie);
  }

  public static boolean verifyCheckCookie(HttpServletRequest request, String key)
  {
    String cookie = getCookieValue(request, encodeMD5(key));
    return (!"".equals(cookie)) && (null != cookie);
  }

  public static String encodeMD5(String str)
  {
    return encode(str, "MD5");
  }

  public static String encodeSHA(String str)
  {
    return encode(str, "SHA");
  }

  private static String encode(String password, String algorithm)
  {
    byte[] unencodedPassword = password.getBytes();
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance(algorithm);
    }
    catch (Exception e) {
      return password;
    }
    md.reset();
    md.update(unencodedPassword);
    byte[] encodedPassword = md.digest();
    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < encodedPassword.length; i++) {
      if ((encodedPassword[i] & 0xFF) < 16) {
        buf.append("0");
      }
      buf.append(Long.toString(encodedPassword[i] & 0xFF, 16));
    }
    return buf.toString();
  }

  public static String toQueryString(Map<String, String> data, String encode)
  {
    StringBuilder sb = new StringBuilder(128);
    Iterator it = data.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry entry = (Map.Entry)it.next();
      sb.append((String)entry.getKey()).append("=");
      try {
        sb.append(URLEncoder.encode((String)entry.getValue(), encode));
      } catch (UnsupportedEncodingException ex) {
        log.error(new StringBuilder().append("进行URL编码时发生错误,数据对象=").append(data).toString());
        log.error("进行URL编码时发生错误", ex);
        sb.append((String)entry.getValue());
      }

      sb.append("&");
    }
    return sb.toString();
  }
  public static String httpGet(String reqUrl) throws Exception {
    HttpURLConnection conn = null;
    log.info(new StringBuilder().append("开始访问").append(reqUrl).toString());
    try {
      URL url = new URL(reqUrl);
      conn = (HttpURLConnection)url.openConnection();
      conn.setRequestMethod("GET");
      conn.setDoInput(true);
      conn.setDoOutput(false);
      conn.setConnectTimeout(50000);
      conn.setReadTimeout(50000);
      conn.connect();
      int code = conn.getResponseCode();
      if (200 != code) {
        throw new Exception(new StringBuilder().append("http访问错误,返回码：").append(code).toString());
      }
      BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

      StringBuilder sb = new StringBuilder();
      String lines;
      while ((lines = reader.readLine()) != null) {
        sb.append(lines);
      }
      log.info(new StringBuilder().append("response=").append(sb.toString()).toString());
      return sb.toString();
    } catch (Exception ex) {
      log.error("访问出错", ex);
      throw ex;
    } finally {
      if (null != conn) try { conn.disconnect(); }
        catch (Exception ex)
        {
        }
    }
  }
  
  
  public static String getHostNameForLiunx() {  
      try {  
          return (InetAddress.getLocalHost()).getHostName();  
      } catch (UnknownHostException uhe) {  
          String host = uhe.getMessage(); // host = "hostname: hostname"  
          if (host != null) {  
              int colon = host.indexOf(':');  
              if (colon > 0) {  
                  return host.substring(0, colon);  
              }  
          }  
          return "UnknownHost";  
      }  
  }
  
  public static String getHostNameForLiunx(String ip) {  
      try {  
    	  byte[] address=toIpByte(ip);
    	  InetAddress addr = InetAddress.getByAddress(address);
    	  System.out.println("--addr-->"+addr);
          return addr.getHostName();  
      } catch (UnknownHostException uhe) {  
          String host = uhe.getMessage(); // host = "hostname: hostname"  
          if (host != null) {  
              int colon = host.indexOf(':');  
              if (colon > 0) {  
                  return host.substring(0, colon);  
              }  
          }  
          return "UnknownHost";  
      }  
  }
  
  private static byte[] toIpByte(String ip) {
      String[] ips=ip.split("\\.");
      byte[] address=new byte[ips.length];
      for (int i=0;i<ips.length;i++)
      {
          address[i]=(byte) Integer.parseInt(ips[i]);
      }
      return address;
  }

  public static String getHostName() {  
      if (System.getenv("COMPUTERNAME") != null) {  
          return System.getenv("COMPUTERNAME");  
      } else {  
          return getHostNameForLiunx();  
      }  
  }  
  
  public static void main(String[] args) {
	
//	System.out.println(getHostName());  
	
	String ip = "10.0.1.131";
	System.out.println(getHostNameForLiunx(ip));
	
	Integer.parseInt(ip);
	
  }
  
}