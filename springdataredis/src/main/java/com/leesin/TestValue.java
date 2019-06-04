package com.leesin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SuppressWarnings("unchecked")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext-redis.xml")
public class TestValue {

	@SuppressWarnings("rawtypes")
	@Autowired
	public RedisTemplate redisTemplate;

	/**
	 * 往Redis中存值
	 */
	@Test
	public void testSetValue(){
		redisTemplate.boundValueOps("name").set("xlecho");
		System.out.println("set success");
	}

	/**
	 * 从Redis中取出key为name的值
	 */
//	@Test
	public void getValue(){
		String name = (String) redisTemplate.boundValueOps("name").get();
		System.out.println("name:= " + name);
	}

	/**
	 * 从Redis中删除一个key为name的值
	 */
//	@Test
	public void deleteValue(){
		redisTemplate.delete("name");
		System.out.println("delete success");

	}
}