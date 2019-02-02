package com.bfb.cheetah.common.transport.http;

//import com.bfb.cheetah.common.config.Config;
//import com.bfb.cheetah.common.utils.CheckUtil;
//import com.bfb.cheetah.exception.CheetahException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by AB044973 on 2014/12/15.
 */
public class HttpGetClient extends AbstractHttpClient {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(HttpGetClient.class);

	public HttpGetClient(String url) {
		super(url);
	}

	@Override
	public String execute(List<Map.Entry<String, String>> content)
			throws Exception {
		return execute(this.url, content);
	}

	@Override
	public String execute(String url, List<Map.Entry<String, String>> content)
			throws Exception {
		String responseJsonStr = null;
		try {

			url = url.concat("?");

			for (Map.Entry<String, String> entry : content) {
				url = url.concat(URLEncoder.encode(entry.getKey(), "UTF-8"))
						.concat("=")
						.concat(URLEncoder.encode(entry.getValue(), "UTF-8"))
						.concat("&");
			}

			url = url.substring(0, url.length() - 2);
			responseJsonStr = execute(url);
		} catch (Exception e) {
			// 发生网络异常
//			if (e instanceof CheetahException) {
//				throw e;
//			}
//			throw CheckUtil.errorInfo("http get request error !\n", e);
			e.printStackTrace();
		}
		return responseJsonStr;
	}

	@Override
	public String execute(String url, String content, String formatType)
			throws Exception {
//		return execute(url, content, formatType, Config.getNetTimeOut());
		return execute(url, content, formatType, 1000);
	}

	@Override
	public String execute(String url, String content, String formatType,
			int timeOut) throws Exception {
		String responseStr = null;
		HttpURLConnection conn = null;
		InputStream inputStream = null;

		try {

			// httpget.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
			// ;
			//
			// if ("json".equals(formatType)) {
			// httpget.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
			// ;
			// }

			url = url + "?" + content;

			conn = getGetConn(url);
			conn.setConnectTimeout(2000);
			conn.setReadTimeout(2000);

			int statusCode = conn.getResponseCode();

			if (statusCode != 200) {
				LOGGER.error("http get  failed:" + conn.getResponseMessage());
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

//			if (e instanceof CheetahException) {
//				throw e;
//			}
//			throw CheckUtil.errorInfo("http get request error !\n", e);
			e.printStackTrace();

		} finally {
			if (null != inputStream) {
				try {
					inputStream.close();
					inputStream = null;
				} catch (Exception e1) {
//					CheckUtil
//							.errorInfo(" error when close inputstream  for httpURLConnection get method ,url:"
//									+ url);
					e1.printStackTrace();
				}
			}

			if (null != conn) {
				try {
					conn.disconnect();
				} catch (Exception e1) {
//					CheckUtil
//							.errorInfo(" error when disconnect for httpURLConnection get method ,url:"
//									+ url);
					e1.printStackTrace();
				}

			}
		}
		return responseStr;

	}

	@Override
	public String execute(String url) throws Exception {
		String responseStr = null;
		HttpURLConnection conn = null;
		InputStream inputStream = null;

		try {
			conn = getGetConn(url);
			conn.setConnectTimeout(2000);
			conn.setReadTimeout(2000);

			int statusCode = conn.getResponseCode();

			if (statusCode != 200) {
				LOGGER.error("http get  failed:" + responseStr);
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
//			if (e instanceof CheetahException) {
//				throw e;
//			}
//			throw CheckUtil.errorInfo("http get request error !\n", e);
			e.printStackTrace();

		} finally {

			if (null != inputStream) {
				try {
					inputStream.close();
					inputStream = null;
				} catch (Exception e1) {
//					CheckUtil
//							.errorInfo(" error when close inputstream  for httpURLConnection get method ,url:"
//									+ url);
					e1.printStackTrace();
				}
			}

			if (null != conn) {
				try {
					conn.disconnect();
				} catch (Exception e1) {
//					CheckUtil
//							.errorInfo(" error when disconnect for httpURLConnection get method ,url:"
//									+ url);
					e1.printStackTrace();
				}

			}
		}
		return responseStr;
	}
	
	
public static void main(String[] args) {
		
		String url = "http://www.toushibao.com/?";
		HttpGetClient hpc = new HttpGetClient(url);
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
			contentStr = hpc.execute(url, lis);
			System.out.println(contentStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}
