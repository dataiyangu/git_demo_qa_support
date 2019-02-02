package com.ilucky.restlet;

import org.restlet.Server;
import org.restlet.data.Protocol;

import com.ilucky.restlet.resource.MovieResource;

public class RestletServer2 {  
      
	public static void main( String[] args ) {
		try {
			Server server = new Server(Protocol.HTTP , 8888, MovieResource.class) ;
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
}