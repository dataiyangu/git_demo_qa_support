<%@page import="org.apache.naming.java.javaURLContextFactory"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <base href="<%=basePath%>">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>测试页面</title>
    </head>
    <body>
        <h2>Hello World!</h2>
        <form action="User_login.action" method="post">
            用户名：<input type="text" name="userName" value="admin" /><br>
            密    码：<input type="password" name="password" value="123456"><br>
            <input type="submit" value="登录">    
            <input type="reset" value="重置" />
        </form>
    </body>
</html>