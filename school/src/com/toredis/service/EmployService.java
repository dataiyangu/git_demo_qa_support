package com.toredis.service;

import com.toredis.model.Employee;

public interface EmployService {
	
	public abstract Employee get(String uid)throws Exception;
	
	public abstract void save(Employee em)throws Exception;
	
	public abstract void delete(String uid)throws Exception;
	
	public abstract String getValue(String name)throws Exception;
	
	public abstract void saveValue(String name, String value)throws Exception;
	
	public abstract void delValue(String name)throws Exception;
}
