package com.mybatis.http;

import java.net.URL;
import java.util.HashMap;

public class HttpClient {
	public static void main(String[] args) throws Exception {
//		JSONObject json = new JSONObject();
//		json.put("action", "test");
//		URL url = new URL("http://localhost:8080/TPSServer/door.do");
		String url = "http://10.0.3.52:8888/school/student/allStudent.action";
		String resp = HttpUtil3.doPost(url, null, "UTF-8", true); 
//		String resp = HttpUtil4.doPost(url, new HashMap());
		System.out.println(resp);
	}
}
