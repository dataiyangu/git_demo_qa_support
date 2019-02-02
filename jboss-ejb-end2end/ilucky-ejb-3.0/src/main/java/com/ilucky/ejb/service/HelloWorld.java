package com.ilucky.ejb.service;

import javax.ejb.Remote;

/**
 * 标注为远程接口
 */
@Remote
public interface HelloWorld {
	public String sayHello();
}