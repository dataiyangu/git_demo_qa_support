package com.toredis.service;

import redis.clients.jedis.Jedis;

import com.toredis.model.Employee;
import com.toredis.util.RedisUtil;

public class EmployServiceImpl implements EmployService {
	
	private Jedis jedis = RedisUtil.getJedis();
	
	@Override
	public Employee get(String uid) throws Exception{

//		Employee em = jedis.get
		jedis.set("uid", uid);
		String tmp = jedis.get("uid");
		
		return null;
	}

	@Override
	public void save(Employee em) throws Exception{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String uid) throws Exception{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getValue(String name) throws Exception {
		// TODO Auto-generated method stub
		
//		jedis.set("name", name);
		String tmp = jedis.get(name);
		
		return tmp;
	}

	@Override
	public void saveValue(String name, String value) throws Exception {
		// TODO Auto-generated method stub
		jedis.set(name, value);
	}

	@Override
	public void delValue(String name) throws Exception {
		// TODO Auto-generated method stub
		jedis.del(name);
	}
	
	
}
