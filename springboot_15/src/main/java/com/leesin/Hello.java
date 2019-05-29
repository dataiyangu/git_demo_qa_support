package com.leesin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class Hello {
//
//	@RequestMapping("/hello")
//	public String hello() throws InterruptedException {
//		Thread.sleep(2000);
//		return "Hello SpringBoo";
//	}
//}


@SpringBootApplication
@RestController
public class Hello
{
	@RequestMapping({"/hello"})
	public String greeting()
	{
		return "Hello World!";
	}

	public static void main(String[] args)
	{
		SpringApplication.run(Hello.class, args);
	}
}
