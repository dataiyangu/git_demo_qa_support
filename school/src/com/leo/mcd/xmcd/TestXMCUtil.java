package com.leo.mcd.xmcd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leo.mcd.xmcd.bean.Book;
import com.leo.mcd.xmcd.bean.User;

public class TestXMCUtil {
	
	private static String addr = "10.0.5.83:11211";
	
	public static void main(String[] args) {  
      testCRUD();  
//        testSerObject();  
    }  
  
    public static void testCRUD() {  
        String address = "xx01:11211 xx02:11211 xx03:11211";  
        address = addr;
        XMCUtil XMCUtil = new XMCUtil(address, new int[] { 1, 1, 1 });  
        XMCUtil.set("hello", 10, "test");  
        System.out.println(XMCUtil.get("hello"));  
        XMCUtil.update("hello", "ssss");  
        System.out.println(XMCUtil.get("hello"));  
        XMCUtil.delete("hello");  
        System.out.println(XMCUtil.get("hello"));  
    }  
      
    public static void testSerObject() {  
        String address = "arreat00:11211 arreat01:11211 arreat02:11211";
        address = addr;
        XMCUtil XMCUtil = new XMCUtil(address, new int[] { 1, 1, 1 });  
        User user = new User();  
        user.setId(1);  
        user.setMobile("11111111111");  
        user.setEmail("qasdfgt@qq.com");  
        user.setPasswd("yingyingying");  
        user.setUserName("qianjc");  
        List<String> asseat = new ArrayList<String>();  
        asseat.add("asseat0");  
        asseat.add("asseat1");  
          
        Map<String, String> car = new HashMap<String, String>();   
        car.put("car1", "val1");  
        car.put("car2", "val2");  
          
        user.setAsseat(asseat);  
        user.setCar(car);  
          
        user.setBook(buildBook(1, "memXMCUtild进阶", "smart出版"));  
        user.setBooks(buildBooks());  
  
//      XMCUtil.set("user1", 0, user);  
        System.out.println(XMCUtil.get("user1"));  
    }  
      
      
    public static Book buildBook(int id, String name, String pulish) {  
        Book book = new Book();  
        book.setId(id);  
        book.setName(name);  
        book.setPulish(pulish);  
        return book;  
    }  
      
    public static List<Book> buildBooks() {  
        List<Book> books = new ArrayList<Book>();  
          
        for (int i = 0; i < 2; ++i) {  
            books.add(buildBook(i, "memXMCUtild进阶" + i, "smart出版" + i));  
        }  
        return books;  
    }  
 

}
