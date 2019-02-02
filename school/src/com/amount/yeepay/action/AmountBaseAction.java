package com.amount.yeepay.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AmountBaseAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Object getServiceBean(String beanId){
		ServletContext sc=ServletActionContext.getServletContext();
		WebApplicationContext ctx=WebApplicationContextUtils.getWebApplicationContext(sc);
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
