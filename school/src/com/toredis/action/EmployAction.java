package com.toredis.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.json.annotations.JSON;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Connection;

import com.mongo.demo.MongoDBDao;
import com.mongo.demo.MongoDBDaoImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryOperators;
import com.toredis.util.RedisUtil;


public class EmployAction extends EmployBaseAction{

	
	private static final Log log = LogFactory.getLog(EmployAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public String getAllEmployee(){
		
		return SUCCESS;
	}
	
	public String delEmployee(){
		
		return SUCCESS;
	}
	
	public String saveEmployee(){
		
		return SUCCESS;
	}
	
	public String delEmployees(){
		
		return SUCCESS;
	}
	
	
	public String redis(){
		HttpServletRequest request=this.getRequest();
		HttpServletResponse resp = this.getResponse();
		return SUCCESS;
	}
	
	private Map<String,Object> dataMap;
	
	public Map<String, Object> getDataMap() {  
        return dataMap;  
    }
	
	public String get() throws Exception{
		HttpServletRequest request=this.getRequest();
		HttpServletResponse resp = this.getResponse();
		String name = request.getParameter("name");
		String value = "leo";
		// dataMap中的数据将会被Struts2转换成JSON字符串，所以这里要先清空其中的数据  
        dataMap = new HashMap<String, Object>();
		try {
//			EmployService es = (EmployService)this.getServiceBean("employService");
//			value = es.getValue(name);
			value = getValue(name);
//			HelloDatabaseWorld hdw = new HelloDatabaseWorld();
//			hdw.doTest();
		} catch (Exception e) {
			// TODO: handle exception
			this.getRequest().setAttribute("flag", "false");
			log.error("--get error-->", e);
		}
		log.info("--get val-->"+value+"-- name-->"+name);
		dataMap.put("svalue", value);
		dataMap.put("flag", "success");
		// 放入一个是否操作成功的标识  
        dataMap.put("success", true);
//		JSONArray ja = new JSONArray();
//		ja = JSONArray.fromObject(dataMap);
//		request.setAttribute("svalue", value);
//		resp.getWriter().write(ja.toString());
		
		return SUCCESS;
	}
	
	public String save() throws Exception{
		HttpServletRequest request=this.getRequest();
		HttpServletResponse resp = this.getResponse();
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		try {
//			EmployService es = (EmployService)this.getServiceBean("employService");
//			es.saveValue(name, value);
			saveValue(name, value);
		} catch (Exception e) {
			// TODO: handle exception
			this.getRequest().setAttribute("flag", "false");
			log.error("--save error-->", e);
		}
		log.info("--save name-->"+name+"--val-->"+value);
		this.getRequest().setAttribute("flag", "success");
		return SUCCESS;
	}
	
	public String del() throws Exception{
		HttpServletRequest request=this.getRequest();
		HttpServletResponse resp = this.getResponse();
		String name = request.getParameter("name");
		try {
//			EmployService es = (EmployService)this.getServiceBean("employService");
//			es.delValue(name);
			delValue(name);
		} catch (Exception e) {
			// TODO: handle exception
			this.getRequest().setAttribute("flag", "false");
			log.error("--del error-->", e);
		}
		log.info("--del name-->"+name);
		this.getRequest().setAttribute("flag", "success");
		resp.getWriter().write("delete success");
		return SUCCESS;
	}
	
	public String quit() throws Exception{
		HttpServletRequest request=this.getRequest();
		HttpServletResponse resp = this.getResponse();
		String name = request.getParameter("name");
		try {
//			EmployService es = (EmployService)this.getServiceBean("employService");
//			es.delValue(name);
			quit();
			connA();
		} catch (Exception e) {
			// TODO: handle exception
			this.getRequest().setAttribute("flag", "false");
			log.error("--quit error-->", e);
		}
		log.info("--quit name-->"+name);
		this.getRequest().setAttribute("flag", "success");
		resp.getWriter().write("delete success");
		return SUCCESS;
	}
	
	public String saveComm() throws Exception{
		HttpServletRequest request=this.getRequest();
		HttpServletResponse resp = this.getResponse();
		String name = request.getParameter("name");
		String value = "";
		try {
//			EmployService es = (EmployService)this.getServiceBean("employService");
//			es.delValue(name);
			value = saveCommA();
		} catch (Exception e) {
			// TODO: handle exception
			this.getRequest().setAttribute("flag", "false");
			log.error("--saveComm error-->", e);
		}
		log.info("--saveComm value-->"+value);
		this.getRequest().setAttribute("flag", "success");
		resp.getWriter().write("delete success");
		return SUCCESS;
	}
	
	public String scan() throws Exception{
		HttpServletRequest request=this.getRequest();
		HttpServletResponse resp = this.getResponse();
		String name = request.getParameter("name");
		try {
//			EmployService es = (EmployService)this.getServiceBean("employService");
//			es.delValue(name);
			scanA(name);
		} catch (Exception e) {
			// TODO: handle exception
			this.getRequest().setAttribute("flag", "false");
			log.error("--scan error-->", e);
		}
		log.info("--scan name-->"+name);
		this.getRequest().setAttribute("flag", "success");
		resp.getWriter().write("delete success");
		return SUCCESS;
	}
	
	public String testConn() throws Exception{
		HttpServletRequest request=this.getRequest();
		HttpServletResponse resp = this.getResponse();
		try {
//			EmployService es = (EmployService)this.getServiceBean("employService");
//			es.delValue(name);
			@SuppressWarnings("resource")
			BinaryClient clientConn = new BinaryClient("10.0.5.83", 6379);
			clientConn.ping();
			log.info("run connection.ping to sendCommnad！！！");
		} catch (Exception e) {
			// TODO: handle exception
			this.getRequest().setAttribute("flag", "false");
			log.error("--scan error-->", e);
		}
		this.getRequest().setAttribute("flag", "success");
		resp.getWriter().write("delete success");
		return SUCCESS;
	}
	
	public String hmset() throws Exception{
		HttpServletRequest request=this.getRequest();
		HttpServletResponse resp = this.getResponse();
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		String result = "";
		try {
//			EmployService es = (EmployService)this.getServiceBean("employService");
//			es.saveValue(name, value);
			result = hmsetA(name, value);
		} catch (Exception e) {
			// TODO: handle exception
			this.getRequest().setAttribute("flag", "false");
			log.error("--hmset error-->", e);
		}
		log.info("--hmset name-->"+name+"--val-->"+value + "--re-->" + result);
		this.getRequest().setAttribute("flag", "success");
		return SUCCESS;
	}
	
	public String hmget() throws Exception{
		HttpServletRequest request=this.getRequest();
		HttpServletResponse resp = this.getResponse();
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		String result = "";
		List<String> vl = null;
		// dataMap中的数据将会被Struts2转换成JSON字符串，所以这里要先清空其中的数据  
        dataMap = new HashMap<String, Object>();
		try {
//			EmployService es = (EmployService)this.getServiceBean("employService");
//			value = es.getValue(name);
			vl = hmgetA(name,value);
			if (vl.size()>1) {
				result = vl.get(0);
			}else {
				result = vl.toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
			this.getRequest().setAttribute("flag", "false");
			log.error("--hmget error-->", e);
		}
		log.info("--hmget val-->"+value+"-- name-->"+name + "-result->"+ result);
		
		dataMap.put("svalue", value);
		dataMap.put("flag", "success");
		// 放入一个是否操作成功的标识  
        dataMap.put("success", true);
//		JSONArray ja = new JSONArray();
//		ja = JSONArray.fromObject(dataMap);
//		request.setAttribute("svalue", value);
//		resp.getWriter().write(ja.toString());
		
		return SUCCESS;
	}
	
	public String ping() throws Exception{
		HttpServletRequest request=this.getRequest();
		HttpServletResponse resp = this.getResponse();
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		// dataMap中的数据将会被Struts2转换成JSON字符串，所以这里要先清空其中的数据  
        dataMap = new HashMap<String, Object>();
		try {
//			EmployService es = (EmployService)this.getServiceBean("employService");
//			value = es.getValue(name);
			value = pingA();
//			HelloDatabaseWorld hdw = new HelloDatabaseWorld();
//			hdw.doTest();
		} catch (Exception e) {
			// TODO: handle exception
			this.getRequest().setAttribute("flag", "false");
			log.error("--ping error-->", e);
		}
		log.info("--ping val-->"+value+"-- name-->"+name);
		dataMap.put("svalue", value);
		dataMap.put("flag", "success");
		// 放入一个是否操作成功的标识  
        dataMap.put("success", true);
//		JSONArray ja = new JSONArray();
//		ja = JSONArray.fromObject(dataMap);
//		request.setAttribute("svalue", value);
//		resp.getWriter().write(ja.toString());
		
		return SUCCESS;
	}
	
	
	private Jedis jedis = RedisUtil.getJedis();
	
	
	private String getValue(String name) throws Exception {
		// TODO Auto-generated method stub
		
//		jedis.set("name", name);
		String tmp = jedis.get(name);
//		tmp += jedis.ping();
		
		tmp += jedis.clientSetname("123");
//		tmp += jedis.clientGetname();
		
//		jedis.clientList();
		
		return tmp;
	}

	private void saveValue(String name, String value) throws Exception {
		jedis.set(name, value);
		
	}

	private void delValue(String name) throws Exception {
		jedis.del(name);
	
	}
	
		
	private void connA() throws Exception {
		jedis =  RedisUtil.getJedis();
		
	}
	
	
	private String scanA(String name) throws Exception {
		
		
		return jedis.scan(name).toString();
		
	}
	
	private String saveCommA() throws Exception {
		
		return jedis.save();
		
	}
	
	private String quitA() throws Exception {
		
		return jedis.quit();
	}
	
	private String pingA() throws Exception {
		
		return jedis.ping();
	}
	
	private String hmsetA(String name, String value) throws Exception {
		Map<String,String> p = new HashMap<String,String>();
		p.put(name, value);
		return jedis.hmset(name, p);
	}
	
	private List<String> hmgetA(String name, String value) throws Exception {
		
		return jedis.hmget(name,value);
	}
	
	public String saveMongo() throws Exception{
		HttpServletRequest request=this.getRequest();
		HttpServletResponse resp = this.getResponse();
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		boolean result = false;
        ArrayList<DBObject> reslist = null;
		try {
			MongoDBDao mdao = MongoDBDaoImpl.getMongoDBDaoImplInstance();
	    	String[] keys = {name};
	    	String[] values = {value};
	    	
	    	String[] keys1 = {"1"};
	    	String[] values1 = {"dana"};
	    	result = mdao.inSert("leodb", "leocoll", keys, values);
	        System.out.println("-mongo insert over-->"+result);
		} catch (Exception e) {
			// TODO: handle exception
//			this.getRequest().setAttribute("flag", "false");
			log.error("mongo get error-->", e);
		}
		log.info("mongo save name-->"+name+"--val-->"+value);
//		this.getRequest().setAttribute("flag", result+"");
		resp.getWriter().write("save "+result);
		return SUCCESS;
	}
	
	
	public String delMongo() throws Exception{
		HttpServletRequest request=this.getRequest();
		HttpServletResponse resp = this.getResponse();
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		boolean result = false;
        ArrayList<DBObject> reslist = null;
		try {
			MongoDBDao mdao = MongoDBDaoImpl.getMongoDBDaoImplInstance();
	    	String[] keys = {name};
	    	String[] values = {value};
	    	
	    	String[] keys1 = {"1"};
	    	String[] values1 = {"dana"};
			result = mdao.delete("leodb", "leocoll", keys, values);
		} catch (Exception e) {
			// TODO: handle exception
//			this.getRequest().setAttribute("flag", "false");
			log.error("mongo del error-->", e);
		}
		log.info("mongo del name-->"+name);
//		this.getRequest().setAttribute("flag", result+"");
		resp.getWriter().write("delete "+result);
		return SUCCESS;
	}
	
	public String getMongo() throws Exception{
		HttpServletRequest request=this.getRequest();
		HttpServletResponse resp = this.getResponse();
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		// dataMap中的数据将会被Struts2转换成JSON字符串，所以这里要先清空其中的数据  
        dataMap = new HashMap<String, Object>();
        boolean result = false;
        ArrayList<DBObject> reslist = null;
        StringBuilder resultstr = new StringBuilder();
        resultstr.append("");
		try {
			MongoDBDao mdao = MongoDBDaoImpl.getMongoDBDaoImplInstance();
	    	String[] keys = {name};
	    	String[] values = {value};
	    	
	    	String[] keys1 = {"1"};
	    	String[] values1 = {"dana"};
//	    	result = mdao.inSert("leodb", "leocoll", keys, values);
//	        System.out.println("--test insert over-->"+result);
	        reslist = mdao.find("leodb", "leocoll", keys, values, -1);
	    	if (reslist != null) {
	    		for (int i = 0; i < reslist.size(); i++) {
	    			resultstr.append(reslist.get(i).toString());
//	    			System.out.println("mongo find over "+i+" -->" + reslist.get(i).toString());
	    		}
			}
	    	if (name==null || "".equals(name)) {
				resultstr.append(mdao.findAll("leodb", "leocoll"));
			}
	    	
		} catch (Exception e) {
			// TODO: handle exception
			this.getRequest().setAttribute("flag", "false");
			log.error("mongo get error-->", e);
		}
		log.info("mongo get val-->"+value+"-- name-->"+name);
//		findExists();
		dataMap.put("svalue", resultstr.toString());
		dataMap.put("flag", "success");
		// 放入一个是否操作成功的标识  
        dataMap.put("success", result);
		
		return SUCCESS;
	}
	
	public String updateMongo() throws Exception{
		HttpServletRequest request=this.getRequest();
		HttpServletResponse resp = this.getResponse();
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		// dataMap中的数据将会被Struts2转换成JSON字符串，所以这里要先清空其中的数据  
        dataMap = new HashMap<String, Object>();
        boolean result = false;
		try {
			MongoDBDao mdao = MongoDBDaoImpl.getMongoDBDaoImplInstance();
	    	String[] keys = {name};
	    	String[] values = {value};
	    	
	    	String[] keys1 = {"1","2"};
	    	String[] values1 = {"xiao","yu"};
	    	BasicDBObject oldobj = new BasicDBObject();
			for (int i = 0; i < keys.length; i++) { // 填充查询条件
				oldobj.put(name, value);
			}
			BasicDBObject newobj = new BasicDBObject();
			for (int i = 0; i < keys1.length; i++) { // 填充查询条件
				newobj.put(keys1[i], values1[i]);
			}
	    	result = mdao.update("leodb", "leocoll", oldobj, newobj);
			System.out.println("mongo update over-->" + result);
		} catch (Exception e) {
			// TODO: handle exception
//			this.getRequest().setAttribute("flag", "false");
			log.error("mongo get error-->", e);
		}
		log.info("mongo get val-->"+value+"-- name-->"+name);
//		dataMap.put("svalue", result);
//		dataMap.put("flag", "success");
		// 放入一个是否操作成功的标识  
        dataMap.put("success", result);
//		JSONArray ja = new JSONArray();
//		ja = JSONArray.fromObject(dataMap);
//		request.setAttribute("svalue", value);
		resp.getWriter().write("update "+result);
		
		return SUCCESS;
	}
	
	
//	public String findExists() throws Exception{
//		
//		BasicDBObject queryObject = new BasicDBObject().append("1", 
//    			"dana");
//    	queryObject = new BasicDBObject().append("2", 
//    			new BasicDBObject().append(QueryOperators.EXISTS, "1"));
//    	MongoDBDao mdao = MongoDBDaoImpl.getMongoDBDaoImplInstance();
//    	System.out.println(mdao.find("leodb", "leocoll", queryObject, "all"));
//		
//    	return SUCCESS;
//	}
	
}
