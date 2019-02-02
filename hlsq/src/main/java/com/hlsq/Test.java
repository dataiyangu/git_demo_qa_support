package com.hlsq;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Test {
	public static void main(String[] args) {
		
		
		//IP地址从properties中获取
		Properties p = new Properties();
		System.out.println("p获取"+p);
		InputStream r = Test.class.getClassLoader().getResourceAsStream("config.txt");
		//获取当前系统的桌面路径
//		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
//		String desktopPath = desktopDir.getAbsolutePath();
//		System.out.println(desktopPath);
//		BufferedReader r = new BufferedReader(new FileReader(""+desktopPath+"/Desktop/cxf-2.4.1/config.txt"));
		try {
			p.load(r);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ip = p.getProperty("ip");
		System.out.println(ip);
//
////		 String str="wei232123jin234";
////		   String ragex = "\\d{4,}";
////		   String newstr = "*";
////		   String s =str.replaceAll(ragex, newstr);
////		   System.out.println(s);
//
//		String msg = "org.hsqldb.jdbc.JDBCPreparedStatement@3e3047e6[sql=[select * from TEST]]";
//
////		String msg = "PerformanceManager[第1个中括号]Product[第2个中括号[中括号中包含中括号]]<[第3个中括号]79~";
//		Object list = extractMessage(msg);
//		String a =String.valueOf(list);
////		System.out.println(a);
//		String c[] =a.split("=");
//		String d =c[1];
//		System.out.println("d="+"-->"+d);
////		String e =d.replaceAll("[]+","");
////		List<String> list = extractMessage(msg);
////		for (int i = 0; i < list.size(); i++) {
////			System.out.println(i+"-->"+list.get(0));
////		
////		}
////		Object list = extractMessage(msg);
//		System.out.println("e"+d);

	}
	public static List<String> extractMessage(String msg) {
		 
		List<String> list = new ArrayList<String>();
		int start = 0;
		int startFlag = 0;
		int endFlag = 0;
		for (int i = 0; i < msg.length(); i++) {
			if (msg.charAt(i) == '[') {
				startFlag++;
				if (startFlag == endFlag + 1) {
					start = i;
				}
			} else if (msg.charAt(i) == ']') {
				endFlag++;
				if (endFlag == startFlag) {
					list.add(msg.substring(start + 1, i));
				}
			}
		}
		return list;

}
	}
