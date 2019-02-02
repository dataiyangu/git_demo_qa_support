package com.zenith.shuttle.session;

import java.util.UUID;

public class Session {
	
	
	private String id = UUID.randomUUID().toString();
	private String service;
	private String operation;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	
}
