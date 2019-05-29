package com.leesin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/app")
class Hello {

	@RequestMapping("/test")
	@ResponseBody
	public String testDemo() {

		System.out.println("开始连接redis\r\n======================================");
		TestRedis t1 = new TestRedis();
		t1.test1();
		System.out.println("开始连接mysql\r\n======================================");

		try {
			Mysql.main(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("开始连接httpclient\r\n======================================");


		Httpclient httpclient = new Httpclient();
		httpclient.main(null);
		System.out.println("开始连接httpclient_端到端\r\n======================================");

		Httpclient_duandaoduan hd = new Httpclient_duandaoduan();
		hd.main(null);

		return "Hello World!";
	}
}