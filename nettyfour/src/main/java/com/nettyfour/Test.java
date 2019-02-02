package com.nettyfour;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class Test {

	/**
	 * @author wiksys
	 * @param args
	 */
	public static void main(String[] args) {
		read("http://localhost:9090/hello-mule?method=sayHello");
	}
	public static void read(String url){
		int firSplit=url.indexOf("//");
		String proto=url.substring(0, firSplit+2);
		int webSplit=url.indexOf("/", firSplit+2);
		int portIndex=url.indexOf(":",firSplit);
		String webUrl=url.substring(firSplit+2, webSplit);
		String port= "";
		if(portIndex >= 0){
			webUrl=webUrl.substring(0, webUrl.indexOf(":"));
			port=url.substring(portIndex+1, webSplit);
			System.out.println("端口："+port);
		}
		String context=url.substring(webSplit);
		System.out.println("协议:"+proto);
		System.out.println("网址："+webUrl);
		System.out.println("内容："+context);
	}
}