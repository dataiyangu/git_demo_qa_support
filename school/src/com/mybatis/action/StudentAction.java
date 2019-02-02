package com.mybatis.action;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.log4j.MDC;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import sun.net.www.protocol.http.HttpURLConnection;

import com.mongo.demo.MongoDBDao;
import com.mongo.demo.MongoDBDaoImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mybatis.common.BaseAction;
import com.mybatis.student.IStudentService;
import com.mybatis.student.Student;
import com.utils.ConnectUtil;
import com.zenith.shuttle.session.Session;

public class StudentAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7332994485275938178L;
	private Student student;
	private List<Student> stuList;
	
	public static Long locknum = 1L;

	private static final Log log = LogFactory.getLog(StudentAction.class);
	
	private RestTemplate rest;

	public String getAllStudent() {
		String aa = "aa";
		try {
			IStudentService stuService = (IStudentService) this
					.getServiceBean("studentService");
			stuList = stuService.queryAllStudent();
			
//			TestFanXing<Object> dd = new TestFanXing<Object>();
//			dd.info(null, null);
			MDC.put("id", "1");
			System.out.println(MDC.get("id"));
//			new Exception("--test exception !@#$%^&*()\"+_? --");
//			Classes cs = new Classes();
//			cs.setCid(1);
//			cs.setCname("123");
//			System.out.println("--app classes->"+cs.service());
//			testsql();
			/**
			 * 模拟访问    redis cluster 使用    JedisCluster
			 */
//			JedisUtils.set("123", "9090");
//			System.out.println(JedisUtils.get("123").toString());
//			Thread.sleep(3000);
			/**
			 * 模拟异步线程连接数据库
			 */
//			{
//				CommonThread thread = CommonThread.newInstanceThread("asyncThread", "Line", "1", "asyncThread", "1", "1", 10L);
//				log.info("-asyncThread hashcode->" + thread.hashCode());
//				thread.start();
//			}
//			testsql();
			/**
			 * for customV
			 */
//	    	Session se = new Session();
//	    	se.setOperation("duomi_daisy");
//	    	se.setId("daisy00100000");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Runtime run = Runtime.getRuntime();
		// System.out.println("time: " + (new Date()));
		// long endMem = run.totalMemory()-run.freeMemory();
		// System.out.println("memory--> total:" + run.totalMemory() + " free:"
		// + run.freeMemory() + " used:" + endMem
		// + " max: " + run.maxMemory() );
		// System.out.println("exec path:"+System.getProperty("java.ext.dirs"));
		// System.out.println("当前程序所在目录：" + System.getProperty("user.dir"));
		// System.out.println("当前系统时间：" + System.currentTimeMillis());
		// String userDir = System.getProperty("user.dir");
		// try {
		// System.out.println("当前程序所在目录：" + URLEncoder.encode(userDir,
		// "UTF-8"));
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		return SUCCESS;
	}
	
	public String testURLConnection(){
		try{
			IStudentService stuService = (IStudentService) this
					.getServiceBean("studentService");
			stuService.testStackD();
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String testStackA(){
		try{
			IStudentService stuService = (IStudentService) this
					.getServiceBean("studentService");
			stuService.testStackB();
			stuService.testStackC();
			stuService.testStackG();
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	
		
		
		return SUCCESS;
	}
	
	public String testPG(){
		try{
			IStudentService stuService = (IStudentService) this
					.getServiceBean("studentService");
			stuService.testPGExec();
			stuService.testPGExecQuery();
			stuService.testPGBatch();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String testGetConnection(){
		try{
			ConnectUtil.getConnect();
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String testInsert(){
		try{
			ConnectUtil.testInsert();
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String delStudentById() throws Exception {
		HttpServletRequest request = this.getRequest();
		HttpServletResponse resp = this.getResponse();
		try {
			IStudentService stuService = (IStudentService) this
					.getServiceBean("studentService");
			System.out.println(student.toString());
			System.out.println("ladd:"+request.getLocalAddr() +"lport:"+ request.getLocalPort());
			int rows = stuService.delStudentById(student.getSid());
			System.out.println("---delete return-->" + rows );
			
			{
//			CommonThread thread = CommonThread.newInstanceThread("asyncThread", "Line", "1", "asyncThread", "1", "1", 10L);
//			log.info("-asyncThread hashcode->" + thread.hashCode());
//			thread.start();
		}
//			int[] dds = {};
//			int fn = dds[3];
			/*
			 * if(rows>0){ request.setAttribute("msg",
			 * "<script type='text/javascript'>alert('删除成功!');</script>");
			 * }else{ request.setAttribute("msg",
			 * "<script type='text/javascript'>alert('删除失败!');</script>"); }
			 */
//			 testBLocked();
//			 testDeadLocked();

			// Long i = 0L;
			// while (true) {
			// i++;
			// System.out.println("run:"+i);
			//
			// }
			// String validateURL =
			// "http://10.0.5.145:7001/school_j6/student/allStudent.action";
			// HttpURLConnection conn = null;
			// URL url = new URL(validateURL); //创建URL对象
			// //返回一个URLConnection对象，它表示到URL所引用的远程对象的连接
			// conn = (HttpURLConnection) url.openConnection();
			// conn.setConnectTimeout(5000); //设置连接超时为5秒
			// conn.setRequestMethod("POST"); //设定请求方式
			// conn.connect(); //建立到远程对象的实际连接
			// System.out.println("--newwork code-->" + conn.getResponseCode());
			// //判断是否正常响应数据
			// if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			// System.out.println("newwork error exception!!!");
			// // return false;
			// }
		} catch (Exception e) {
			resp.getWriter().write("delete fail");
			e.printStackTrace();
//			throw new Exception("--test exception !@#$%^&*()\"+_? --");
//			int dd = 1/0;
			
		}
		
		resp.getWriter().write("delete success");
		return null;

	}

	public String queryStudentById() {
		try {
			IStudentService stuService = (IStudentService) this
					.getServiceBean("studentService");
			student = stuService.queryStudentById(student.getSid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getAllStudentAfterupdate() {
		try {
			Map map = new HashMap();
			map.put("sid", student.getSid());
			map.put("sname", student.getSname());
			IStudentService stuService = (IStudentService) this
					.getServiceBean("studentService");
			map = stuService.getAllStudentAfterupdate(map);
			stuList = (List<Student>) map.get("studentList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String addStudent() {
		try {
			IStudentService stuService = (IStudentService) this
					.getServiceBean("studentService");
			if (student.getBirth() == null) {
				student.setBirth(new Date(System.currentTimeMillis()));
			}
			stuService.addStudent(student);
			
			boolean result = false;
	    	ArrayList<DBObject> reslist = null;
	    	MongoDBDao mdao = new MongoDBDaoImpl();
	    	String[] keys = {"1","2"};
	    	String[] values = {"dana","xiaoyu"};
	    	
			String[] keys1 = { "1" };
			String[] values1 = { "ilucky" };
			// result = mdao.inSert("leodb", "leocoll", keys, values);
			// System.out.println("--test insert over-->"+result);

			BasicDBObject oldobj = new BasicDBObject();
			for (int i = 0; i < keys.length; i++) { // 填充查询条件
				oldobj.put(keys[i], values[i]);
			}
			BasicDBObject newobj = new BasicDBObject();
			for (int i = 0; i < keys1.length; i++) { // 填充查询条件
				newobj.put(keys1[i], values1[i]);
			}
//			result = mdao.update("leodb", "leocoll", oldobj, newobj);
//			System.out.println("--test update over-->" + result);
			
//			result = mdao.delete("leodb", "leocoll", keys, values);
//	        System.out.println("--test delete -->"+result);
			
//			mdao.insertbulkordereddocs("leodb", "leocoll", oldobj, newobj);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("-- addStudent exception-->", e);
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String restApi() {
		if (student == null) {
			student = new Student();
		}
		String validateURL = student.getSname();
		String respCode = null;
		Long excTime = System.currentTimeMillis();
		try {
			if (validateURL == null || "".equals(validateURL)) {
				validateURL = "http://tomcat.apache.org/";
				validateURL = "http://10.0.1.44/forum.php";
//				validateURL = "http://127.0.0.1:8777/school/";
				// validateURL = "http://www.tingyun.com/";
			}
			student.setSname(validateURL);
			validateURL = validateURL ;//+ "?" + Math.random();
//			HttpURLConnection conn = null;
//			URL url = new URL(validateURL); // 创建URL对象
//			// 返回一个URLConnection对象，它表示到URL所引用的远程对象的连接
//			conn = (HttpURLConnection) url.openConnection();
//			conn.setConnectTimeout(5000); // 设置连接超时为5秒
//			conn.setRequestMethod("GET"); // 设定请求方式
//			conn.connect(); // 建立到远程对象的实际连接
//			conn.getInputStream();
			
//			HttpPostClient hpc = new HttpPostClient();
//			respCode = hpc.execute(validateURL, "leo" ,"");
			
			// Spring restTemplate start
			rest = getRestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
					null, headers);
			ResponseEntity<String> response = rest.exchange(validateURL,
					HttpMethod.GET, requestEntity, String.class);
			respCode = response.getBody();
			respCode = response.getStatusCode() + "";
			
			// Spring restTemplate end
			
			// Spring RedirectView start
			
			
			
			// Spring RedirectView end
			
			
			excTime = System.currentTimeMillis() - excTime;
//			respCode = conn.getResponseCode() + "";
			student.setMajor("netcode->" + respCode + " -time->" + excTime);
			log.info("--URL-->" + validateURL + "--network code-->" + respCode + "-time->" + excTime);
			System.out.println("--network code-->" + respCode + "-time->" + excTime);
			// 判断是否正常响应数据
//			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
//				System.out.println("network error exception!!!");
//				log.info("network error exception!!!");
//				// return false;
//			}
//			Thread.sleep(3000);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("-- test restApi exception-->", e);
			student.setMajor(e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public String resthttpconnection() {
		if (student == null) {
			student = new Student();
		}
		String validateURL = student.getSname();
		Integer times = student.getSid();
		Integer round = student.getCid();
		String respCode = null;
		Long excTime = System.currentTimeMillis();
		try {
			if (validateURL == null || "".equals(validateURL)) {
				validateURL = "http://tomcat.apache.org/";
				validateURL = "http://10.0.1.44/forum.php";
//				validateURL = "http://127.0.0.1:8777/school/";
				// validateURL = "http://www.tingyun.com/";
			}
			student.setSname(validateURL);
//			validateURL = validateURL ;//+ "?" + Math.random();
			HttpURLConnection conn = null;
			URL url = new URL(validateURL.trim()); // 创建URL对象
			// 返回一个URLConnection对象，它表示到URL所引用的远程对象的连接
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000); // 设置连接超时为5秒
			conn.setRequestMethod("GET"); // 设定请求方式
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("User-Agent", "directclient"); 
			conn.setRequestProperty("contentType", "utf-8");
			conn.setRequestProperty("Content-type", "text/html");
			conn.addRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
			
//			conn.setRequestProperty("Connection","close");
			conn.setDoInput(true);  
            conn.setDoOutput(true); 
//			conn.getOutputStream().write(0);
			conn.connect(); // 建立到远程对象的实际连接
			
			try {
				conn.getInputStream();
				
				respCode = conn.getResponseCode() + "";
				
			} catch (SocketException e) {
//				e.printStackTrace();
				log.error("--SocketException-->" + e.getMessage());
				respCode = "200";
			}finally{
				
				excTime = System.currentTimeMillis() - excTime;
			}
			/**
			 * unexpected end of file from server
			 * 这个问题是java受到了一格\0的流结束符号，但跟前四个字节声明的长度不一致，
			 * 所以认为这个结束符号不是正常的（java 的socket是调用c的格式），所以报这个错。
			 */
			student.setMajor("netcode->" + respCode + " -time->" + excTime);
			log.info("--URL-->" + validateURL + "--network code-->" + respCode + "-time->" + excTime);
			System.out.println("--network code-->" + respCode + "-time->" + excTime);
			// 判断是否正常响应数据
//			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
//				System.out.println("network error exception!!!");
//				log.info("network error exception!!!");
//				// return false;
//			}
//			Thread.sleep(3000);
			String nurl = "";
			for (int i = 1; i < times; i++) {
				if (round == null || round == 0) {
					nurl = validateURL ;
				}else {
					nurl = validateURL + "?" + Math.random();
				}
				url = new URL(nurl);
				conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(5000); // 设置连接超时为5秒
				conn.setRequestMethod("GET"); // 设定请求方式
				conn.connect(); // 建立到远程对象的实际连接
				conn.getInputStream();
			}
			testsql();
		} catch (Exception e) {
			// TODO: handle exception
			log.error("-- test resthttpconnection exception-->", e);
			student.setMajor(e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * HttpClient 4.4.1
	 * HttpGet
	 * @return
	 */
	public String restclient_1() {
		if (student == null) {
			student = new Student();
		}
		String validateURL = student.getSname();
		String respCode = null;
		Long excTime = System.currentTimeMillis();
//		CloseableHttpClient httpclient = HttpClients.createDefault();  
		HttpClient client = new DefaultHttpClient(); 
		
		try {
			if (validateURL == null || "".equals(validateURL)) {
				validateURL = "http://tomcat.apache.org/";
				validateURL = "http://10.0.1.44/forum.php";
//				validateURL = "http://127.0.0.1:8777/school/";
			}
			student.setSname(validateURL);
//			validateURL = validateURL ;//+ "?" + Math.random();
			
			HttpGet httpget = new HttpGet(validateURL);
//			// 执行get请求.    
//            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpResponse response = client.execute(httpget);
            
			
			excTime = System.currentTimeMillis() - excTime;
			respCode = response.getStatusLine().getStatusCode() + "";
			student.setMajor("netcode->" + respCode + " -time->" + excTime);
			log.info("--URL-->" + validateURL + "--network code-->" + respCode + "-time->" + excTime);
			System.out.println("--network code-->" + respCode + "-time->" + excTime);
			// 判断是否正常响应数据
//			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
//				System.out.println("network error exception!!!");
//				log.info("network error exception!!!");
//				// return false;
//			}
//			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("-- test restclient exception-->", e);
			student.setMajor(e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * HttpClient 4.4.1
	 * HttpPost
	 * @return
	 */
	public String restclient() {
		if (student == null) {
			student = new Student();
		}
		String validateURL = student.getSname();
		String respCode = null;
		Long excTime = System.currentTimeMillis();
		// 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(validateURL);
        org.apache.http.HttpEntity httpEntity = null ;
        
		
		try {
			if (validateURL == null || "".equals(validateURL)) {
				validateURL = "http://tomcat.apache.org/";
//				validateURL = "http://10.0.1.44/forum.php";
//				validateURL = "http://127.0.0.1:8777/school/";
			}
			student.setSname(validateURL);
//			validateURL = validateURL ;//+ "?" + Math.random();
			
			HttpGet httpget = new HttpGet(validateURL);
//			// 执行get请求.    
//            CloseableHttpResponse response = httpclient.execute(httpget);
            
            
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000)
                    .setConnectTimeout(1000).build();
            httppost.setConfig(requestConfig);
            httppost.setEntity(httpEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
			
			excTime = System.currentTimeMillis() - excTime;
			respCode = response.getStatusLine().getStatusCode() + "";
			student.setMajor("netcode->" + respCode + " -time->" + excTime);
			log.info("--URL-->" + validateURL + "--network code-->" + respCode + "-time->" + excTime);
			System.out.println("--network code-->" + respCode + "-time->" + excTime);
			// 判断是否正常响应数据
//			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
//				System.out.println("network error exception!!!");
//				log.info("network error exception!!!");
//				// return false;
//			}
//			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("-- test restclient exception-->", e);
			student.setMajor(e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * HttpClient 3.1
	 * @return
	 */
	public String restclient31() {
		String urlRequest = student.getSname();
//		urlRequest = "http://127.0.0.1:8777/school/student/allStudent.action";
		Map<String, String> params = new HashMap<String, String>();
		String oaid = "oaid";
		String startDate = "startDate";
		String endDate = "endDate";

		params.put("oaid", oaid);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		
		Map<String, String> urlQuery = null;
		String charset = "utf-8";
		String streamString = "leo";
		Integer timeOut = 4000;
		Integer soTimeOut = 4500;
		long startTime = System.currentTimeMillis();
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("通过HttpClientUtil发去Post请求，请求地址[").append(urlRequest)
				.append("]");
		strBuffer.append(",请求参数中Key[").append(urlQuery).append("]");
		if (StringUtils.isNotBlank(streamString)) {
			strBuffer.append(",Post数据流参数[").append(streamString).append("];");
		}
		PostMethod method = null;
		try {
			HttpClientParams httpClientParams = new HttpClientParams();
			httpClientParams.setVersion(HttpVersion.HTTP_1_1);
			httpClientParams.makeLenient();
			httpClientParams.setAuthenticationPreemptive(false);

			org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
			if (soTimeOut == null) {
				httpClientParams.setSoTimeout(1500);
			} else {
				httpClientParams.setSoTimeout(soTimeOut);
			}
			client.setParams(httpClientParams);
			client.getHttpConnectionManager().getParams()
					.setConnectionTimeout(timeOut);

			method = new PostMethod(urlRequest);

			method.setRequestBody(makeNameValuePair(urlQuery));

			method.setFollowRedirects(false);
			method.setDoAuthentication(false);
			method.getProxyAuthState().setAuthAttempted(false);
			method.getProxyAuthState().setAuthRequested(false);
			method.addRequestHeader("Connection", "Keep-Alive");
			if (StringUtils.isNotBlank(streamString)) {
				method.setRequestEntity(new StringRequestEntity(streamString,
						"text/plain", charset));
				method.addRequestHeader("Content-type", "text/xml;");
			}
			method.addRequestHeader("Content-Type",
					"application/x-www-form-urlencoded;charset=" + charset);

			int statusCode = client.executeMethod(method);

			if (statusCode < HttpStatus.SC_OK || statusCode >= 300) {
				log.error("Method Failed : " + method.getStatusLine());
				// throw new NetworkExecption("状态码是： " + statusCode);
//				throw new Exception("状态码是： " + statusCode);
			}
			// return IOUtils.toByteArray(method.getResponseBodyAsStream());
			student.setMajor("netcode->" + statusCode + " -time->" + (System.currentTimeMillis() - startTime));
			return SUCCESS;
		} catch(org.apache.commons.httpclient.NoHttpResponseException nhre) {
//			log.error("--NoHttpResponseException--->" + strBuffer.toString(), nhre);
			log.error("--NoHttpResponseException--->" + strBuffer.toString() + "\n--message-->" + nhre.getMessage());
		}
		catch (SocketTimeoutException e) {
			log.error("接口调用超时:" + strBuffer.toString(), e);
			e.printStackTrace();
			try {
				throw new Exception("接口调用超时");
			} catch (Exception networkExecption) {
				networkExecption.printStackTrace();
			}
		} catch (Exception e) {
			log.error("Http接口调用异常1:" + strBuffer.toString(), e);
			e.printStackTrace();
			// throw new Exception("Http接口调用异常");
		} catch (Error e) {
			log.error("Http接口调用异常2:" + strBuffer.toString(), e);
//			e.printStackTrace();
			// throw new Exception("Http接口调用异常");
		} finally {
			if (method != null) {
				method.releaseConnection();
			}
			strBuffer.append(" 总计运行时间[")
					.append(System.currentTimeMillis() - startTime)
					.append("]毫秒; ");
			log.info(strBuffer.toString());
		}

		return SUCCESS;
	}

	private static NameValuePair[] makeNameValuePair(
			Map<String, String> urlQuery) {
		if (urlQuery == null || urlQuery.isEmpty()) {
			return new NameValuePair[0];
		}
		NameValuePair[] nameValues = new NameValuePair[urlQuery.size()];
		int i = 0;
		for (Entry<String, String> entry : urlQuery.entrySet()) {
			nameValues[i] = new NameValuePair(entry.getKey(), entry.getValue());
			log.debug("HTTP接口调用参数：" + nameValues[i]);
			i++;
		}
		return nameValues;
	}
	

	public List<Student> getStuList() {
		return stuList;
	}

	public void setStuList(List<Student> stuList) {
		this.stuList = stuList;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	private void testDeadLocked() {

		System.out.println("StarE=" + System.currentTimeMillis());

		final Object lock1 = new Object();
		final Object lock2 = new Object();

		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock1) {
					System.out.println("Thread1 acquired lock1");
					try {
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (InterruptedException ignore) {
					}
					synchronized (lock2) {
						System.out.println("Thread1 acquired lock2");
					}
				}
			}

		});
		thread1.setName("lock0001");
		thread1.start();

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock2) {
					System.out.println("Thread2 acquired lock2-"+locknum);
					synchronized (lock1) {
						System.out.println("Thread2 acquired lock1-"+locknum);
					}
				}
			}
		});
		thread2.setName("lock0002");
		thread2.start();

	}

	private void testBLocked() {

		System.out.println("StarE=" + System.currentTimeMillis());

		final Object lock1 = new Object();
		final Object lock2 = new Object();

		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock1) {
					System.out.println("Thread1 acquired lock1");
					try {
						TimeUnit.MILLISECONDS.sleep(1000 * 1200);
					} catch (InterruptedException ignore) {
					}
					// synchronized (lock2) {
					// System.out.println("Thread1 acquired lock2");
					// }
				}
			}

		});
		thread1.setName("block-001");
		thread1.start();

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock2) {
					System.out.println("Thread2 acquired lock2");
					synchronized (lock1) {
						System.out.println("Thread2 acquired lock1");
					}
				}
			}
		});
		thread2.setName("block-002");
		thread2.start();
		locknum ++;
	}

	public static void main(String[] args) {

	}
	
	private void testsql(){
		Connection conn = null;
		String sql = "";
		ResultSet rs = null;
		String name = "leo";
		String id = "1";
		if (id == null || "".equals(id)) {
			id = "1";
		}
		PreparedStatement stmt = null;
//		Statement stmt = null;
		try {
			Thread.currentThread();
			Thread.sleep(5000);
//			conn = DBUtils.getConnection();
			conn = ConnectUtil.getConnect();
//			stmt = conn.createStatement();
			
			sql = " SELECT tt.cname FROM classes tt  WHERE tt.CID=? and ?=? and ?=?";
			sql = " select * from student ";
//			sql = "select st.sname from t_student st where st.sid ="+id;
			stmt = conn.prepareStatement(sql);
//			stmt.setString(1, id);
//			stmt.setLong(2, 2);
//			stmt.setLong(3, 2);
//			stmt.setLong(4, 3);
//			stmt.setLong(5, 3);
			rs = stmt.executeQuery();
//			rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				name = rs.getString(1);
			}
			System.out.println(name);
			rs.close();
			stmt.close();
            conn.close();
            
//            sqlend = 1L/0L;
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public RestTemplate getRestTemplate(){
		int poolSize = 4;
		PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager();
		connMgr.setMaxTotal(poolSize + 1);
		connMgr.setDefaultMaxPerRoute(poolSize);
		// RequestConfig defaultRequestConfig =
		// RequestConfig.custom().setSocketTimeout(1000).setConnectTimeout(1000).setConnectionRequestTimeout(1000).setStaleConnectionCheckEnabled(true).build();
		CloseableHttpClient httpc = HttpClients
				.custom()
				.setConnectionManager(connMgr)
				.setKeepAliveStrategy(
						new DefaultConnectionKeepAliveStrategy())
				.setMaxConnTotal(poolSize + 10)
				.setMaxConnPerRoute(poolSize + 10).build();
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				httpc);
//		clientHttpRequestFactory.setConnectTimeout(1000);
//		clientHttpRequestFactory.setReadTimeout(1000);

		RestTemplate rest = new RestTemplate();
		rest.setRequestFactory(clientHttpRequestFactory);
		
		// headers.set("Cookie",
		// sessionId);//JSESSIONID=0C5BF68C4C817D7D68BE840BEA436377
		
		return rest;
	}

}
