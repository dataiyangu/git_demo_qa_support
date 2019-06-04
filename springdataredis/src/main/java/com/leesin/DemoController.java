package com.leesin;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

@SuppressWarnings("unchecked")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext-redis.xml")
@Controller
@RequestMapping("/")
public class DemoController {
	@SuppressWarnings("rawtypes")
	@Autowired
	public RedisTemplate redisTemplate;
	@RequestMapping("")
	public String index(){
		TestValue t = new TestValue();

		redisTemplate.boundValueOps("name").set("xlecho");
		System.out.println("set success");


		String name1 = (String) redisTemplate.boundValueOps("name").get();
		System.out.println("name:= " + name1);


		redisTemplate.delete("name");
		System.out.println("delete success");

		return "demo";
	}
}