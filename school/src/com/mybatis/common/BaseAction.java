package com.mybatis.common;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{

	public Object getServiceBean(String beanId) throws Exception{
		ServletContext sc=ServletActionContext.getServletContext();
		WebApplicationContext ctx=WebApplicationContextUtils.getWebApplicationContext(sc);
//		try {
//			int i = 1/0;
//		} catch (Exception e) {
////			e.printStackTrace();
//			throw new Exception("--test exception !@#$%^&*()\"+_? --");
//			
////			int[] ddd = {};
////			int dda = ddd[3];
//		}
		return ctx.getBean(beanId);
	}
	
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	public Map<String, Object> getSession() {
		ActionContext act=ActionContext.getContext();
		return act.getSession();
	}
}
