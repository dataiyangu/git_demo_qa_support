package com.leesin;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;


class Httpclient_duandaoduan {


	public static void main(String[] args) {
		//	这里开始是httpclient
		String url = "http://10.0.1.224:8080/a/1";
//		String url = "http://www.baidu.com";

		Map<String, Object> parames = new HashMap();
		Gson gson = new Gson();
		String json = null;

		parames.put("AppKey","132152");
		parames.put("Ticket","57077c8537cd855621401216a");

		json =gson.toJson(parames);

		System.out.println("parames:"+json);
		String receive = doPost(url, json);
		System.out.println("receive:"+receive);

	}

	public static String doPost(String url, String postData) {
		String result = null;
		HttpPost post = null;
		try {
			HttpClient client = new DefaultHttpClient();

			post = new HttpPost(url);

			post.setHeader("Content-Type", "application/json; charset=UTF-8");
			post.setHeader("Accept", "application/json; charset=UTF-8");

			StringEntity entity = new StringEntity(postData, "UTF-8");
			post.setEntity(entity);

			HttpResponse response = client.execute(post);

			int rspCode = response.getStatusLine().getStatusCode();
			System.out.println("rspCode:" + rspCode);
			result = EntityUtils.toString(response.getEntity(), "UTF-8");

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (post != null) {
				post.releaseConnection();
			}
		}
		return null;
	}
}