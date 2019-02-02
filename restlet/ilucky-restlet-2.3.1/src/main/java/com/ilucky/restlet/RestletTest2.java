package com.ilucky.restlet;

import java.io.IOException;

import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

public class RestletTest2 {

	public static void main(String[] args) {
		try {
			/*test01();
			test02();
			test03();
			test04();*/
			test05();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void test01() throws IOException{  
        ClientResource client = new ClientResource("http://localhost:8888/");  
        Representation result =  client.get() ;     //调用get方法  
        System.out.println(result.getText());    
    }  
      
    public static void test02() throws IOException{  
        ClientResource client = new ClientResource("http://localhost:8888/");    
        Representation result =  client.post(null) ;        //调用post方法  
        System.out.println(result.getText());    
    }  
      
    public static void test03() throws IOException{  
        ClientResource client = new ClientResource("http://localhost:8888/");    
        Representation result =  client.put(null) ;     //调用put方法  
        System.out.println(result.getText());    
    }  
      
    public static void test04() throws IOException{  
        ClientResource client = new ClientResource("http://localhost:8888/");    
        Representation result =  client.delete() ;      //调用delete方法  
        System.out.println(result.getText());    
    }  
    
    public static void test05() throws IOException{  
        ClientResource client = new ClientResource("http://localhost:8888/haha");    
        Representation result =  client.post("MOVIE") ;        //调用post方法  
        System.out.println(result.getText());    
    }  
}
