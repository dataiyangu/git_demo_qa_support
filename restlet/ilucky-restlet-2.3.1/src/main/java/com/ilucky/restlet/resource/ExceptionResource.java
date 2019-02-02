package com.ilucky.restlet.resource;

import org.restlet.data.Reference;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class ExceptionResource extends ServerResource{
	
	@Get
	public String helloWorld(){
		int i = 0;
		int j =1 ;
		System.out.println(j/i);
		System.out.println(getRequest().toString());
		return "<h1>Hello World</h1>";
	}
}