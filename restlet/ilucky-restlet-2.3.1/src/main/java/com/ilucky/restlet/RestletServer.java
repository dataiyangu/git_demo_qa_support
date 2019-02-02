package com.ilucky.restlet;

import org.restlet.Component;
import org.restlet.data.Protocol;

import com.ilucky.restlet.resource.DbResource;
import com.ilucky.restlet.resource.ExceptionResource;
import com.ilucky.restlet.resource.HelloWorldResource;

public class RestletServer  {
    
	public static void main( String[] args ) {
		try {
			//Server server = new Server(Protocol.HTTP , 9991, HelloWorldResource.class) ;
			//Server server = new  Server(null, Protocol.HTTP , "1.2.3.4", 9991, null);
			//server.start();
			
			Component comp = new Component() ;  
	        comp.getServers().add(Protocol.HTTP,  9991) ;  
	        comp.getDefaultHost().attach("/hello", HelloWorldResource.class) ;  
	        comp.getDefaultHost().attach("/exception", ExceptionResource.class) ;  
	        comp.getDefaultHost().attach("/db", DbResource.class) ;  
		     comp.start();   
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
}
