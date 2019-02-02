package com.bfb.cheetah.common.transport.http;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

//import com.bfb.cheetah.common.config.Config;
//import com.bfb.cheetah.common.trace.Trace;
//import com.bfb.cheetah.common.trace.TraceConvertor;
//import com.bfb.cheetah.common.utils.CheckUtil;
//import com.bfb.cheetah.exception.CheetahException;

/**
 * Created by AB044973 on 2014/12/15.
 */
public class HttpPostClient extends AbstractHttpClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpPostClient.class);

	public HttpPostClient(String url) {
		super(url);
	}

	public HttpPostClient() {

	}

	@Override
	public String execute(String url) throws Exception {
		return execute(url, null);
	}

	@Override
	public String execute(List<Map.Entry<String, String>> content) throws Exception {
		return execute(this.url, content);
	}

	@Override
	public String execute(String url, String content, String formatType) throws Exception {
		return execute(url, content, formatType, 10000);
	}

	@Override
	public String execute(String url, String content, String formatType, int timeOut) throws Exception {
		String responseStr = null;
		HttpURLConnection conn = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {
			conn = getPostConn(url);
			// disable keep alive
			conn.setRequestProperty("Connection", "close");
			conn.setConnectTimeout(timeOut);
			conn.setReadTimeout(timeOut);

			String contentType = "text/xml;charset=utf-8";
			if ("json".equals(formatType)) {
				contentType = "appliaction/json;charset=utf-8";
			}

			conn.setRequestProperty("Thread-name", Thread.currentThread().getName());
			conn.setRequestProperty("Content-type", contentType);
			conn.setRequestProperty("Content-Length", String.valueOf(content.getBytes("UTF-8").length));
//			Trace trace = geTrace();
//			if (trace != null) {
//				TraceConvertor.nextSpan(trace);
//				updateTrace(trace);
//				String json = TraceConvertor.toJson(trace);
//				conn.setRequestProperty(TraceConvertor.TRACE_KEY, json);
//				LOGGER.info("add trace in http header: " + json);
//			}
			LOGGER.info("will send http post for url:".concat(url).concat(",content:".concat(content)));

			outputStream = conn.getOutputStream();
			outputStream.write(content.getBytes("UTF-8"));
			outputStream.flush();
			outputStream.close();

			int statusCode = conn.getResponseCode();

			if (statusCode != 200) {
//				throw CheckUtil.errorInfo("http post failed:" + conn.getResponseMessage() + ", statusCode: "
//						+ statusCode);
			}

			inputStream = conn.getInputStream();
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			byte[] data = new byte[1024];
			int readsize = -1;
			while ((readsize = inputStream.read(data, 0, 1024)) > -1) {
				bout.write(data, 0, readsize);
			}

			responseStr = new String(bout.toByteArray(), "UTF-8");
			return responseStr;
		} catch (Exception e) {
			// 发生网络异常
			if (e instanceof SocketException) {
				throw e;
			}

//			if (e instanceof CheetahException) {
//				throw e;
//			}
//			throw CheckUtil.errorInfo("http post request error !\n", e);
			e.printStackTrace();

		} finally {

			if (null != inputStream) {
				try {
					inputStream.close();
					inputStream = null;
				} catch (Exception e1) {
//					CheckUtil.errorInfo(" error when close inputstream  for httpURLConnection get method ,url:" + url);
				  e1.printStackTrace();
				}
			}

			if (null != conn) {
				try {
					conn.disconnect();
				} catch (Exception e1) {
//					CheckUtil.errorInfo(" error when disconnect for httpURLConnection get method ,url:" + url);
					e1.printStackTrace();
				}

			}
		}
		return responseStr;
	}

	@Override
	public String execute(String url, List<Map.Entry<String, String>> content) throws Exception {
		String responseJsonStr = null;
		HttpURLConnection conn = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {

			conn = getPostConn(url);
//			conn = forPostConn(url);
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(2000);

			String contentStr = "";
			if (content != null) {
				for (Map.Entry<String, String> entry : content) {
					contentStr = contentStr.concat(URLEncoder.encode(entry.getKey(), "UTF-8")).concat("=")
							.concat(URLEncoder.encode(entry.getValue(), "UTF-8")).concat("&");
				}
//				contentStr = contentStr.substring(0, contentStr.length() - 2);
			}
			contentStr = "leo";
			conn.setRequestProperty("Content-Length", String.valueOf(contentStr.getBytes("UTF-8").length));

			LOGGER.info("will send http post for url:".concat(url).concat(",content:".concat(contentStr)));

			outputStream = conn.getOutputStream();
			outputStream.write(contentStr.getBytes("UTF-8"));
			outputStream.flush();
			outputStream.close();

			int statusCode = conn.getResponseCode();

			if (statusCode != 200) {
//				throw CheckUtil.errorInfo("http post   failed:" + conn.getResponseMessage());
				
			}

			inputStream = conn.getInputStream();
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			byte[] data = new byte[1024];
			int readsize = -1;
			while ((readsize = inputStream.read(data, 0, 1024)) > -1) {
				bout.write(data, 0, readsize);
			}

			responseJsonStr = new String(bout.toByteArray(), "UTF-8");
			return responseJsonStr;
		} catch (Exception e) {
			// 发生网络异常
			if (e instanceof SocketException) {
				throw e;
			}

//			if (e instanceof CheetahException) {
//				throw e;
//			}
//			throw CheckUtil.errorInfo("http post request error !\n", e);
			e.printStackTrace();
		} finally {
			if (null != inputStream) {
				try {
					inputStream.close();
					inputStream = null;
				} catch (Exception e1) {
//					CheckUtil.errorInfo(" error when close inputstream  for httpURLConnection get method ,url:" + url);
				  e1.printStackTrace();
				}
			}

			if (null != conn) {
				try {
					conn.disconnect();
				} catch (Exception e1) {
//					CheckUtil.errorInfo(" error when disconnect for httpURLConnection get method ,url:" + url);
				   e1.printStackTrace();
				}

			}

		}
		return responseJsonStr;

	}

//	private Trace geTrace() {
//		Trace trace = null;
//		String json = MDC.get(TraceConvertor.TRACE_KEY);
//		if (json != null) {
//			trace = TraceConvertor.fromJson(json);
//		}
//		return trace;
//	}
//
//	private void updateTrace(Trace trace) {
//		MDC.put(TraceConvertor.TRACE_KEY, TraceConvertor.toJson(trace));
//	}
	public static void main(String[] args) {
		
		
		HttpPostClient hpc = new HttpPostClient();
		List<Map.Entry<String, String>> content = null;
		String contentStr = "";
//		for (Map.Entry<String, String> entry : content) {
//			try {
//				contentStr = contentStr.concat(URLEncoder.encode(entry.getKey(), "UTF-8")).concat("=")
//						.concat(URLEncoder.encode(entry.getValue(), "UTF-8")).concat("&");
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		List<Entry<String, String>> lis = new ArrayList<Entry<String, String>>();
		content = lis;
		try {
//			hpc.execute("http://www.toushibao.com", "leo" ,"");
			hpc.execute("http://www.toushibao.com", lis);
//			System.out.println(System.nanoTime());
//			Thread.sleep(1000);
//			System.out.println(System.nanoTime());
//			System.out.println(103072/3600);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
