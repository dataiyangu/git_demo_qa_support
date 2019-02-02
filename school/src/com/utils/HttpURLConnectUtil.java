package com.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectUtil {
	private static  HttpURLConnection con;
		
	public  static HttpURLConnection getConnection(URL dataUrl){
		try {
			if(con == null){
				con = (HttpURLConnection) dataUrl
						.openConnection();
				con.setRequestMethod("POST");
				con.setUseCaches(false);  //不允许缓存
				con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				con.setRequestProperty("Proxy-Connection", "Keep-Alive");
				con.setDoOutput(true);
				con.setDoInput(true);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
}
