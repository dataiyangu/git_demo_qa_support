package com.ilucky.restlet.resource;

import org.restlet.data.Reference;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class HelloWorldResource extends ServerResource{
	
	@Get
	public String helloWorld(){
		/*int i = 0;
		int j =1 ;
		System.out.println(j/i);*/
	/*	Reference r = getRequest().getHostRef();
		System.out.println(r.getHostDomain());
		System.out.println(r.getHostPort());
		System.out.println(r.getQuery());
		System.out.println(r.getPath());
		System.out.println(r.getUserInfo());
		System.out.println("---------------------");*/
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(getRequest().toString());
		return "<h1>Hello World</h1>";
	}
}