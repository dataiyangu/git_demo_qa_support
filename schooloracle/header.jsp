<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%><!--use Enumeration, import package -->
<html>
<head>
<title>TEST_headers</title>
</head>
<body>
<%
    Enumeration enu=request.getHeaderNames();//取得全部的头信息
    while(enu.hasMoreElements()){    //依次取出头信息
        String headerName=(String)enu.nextElement();
        String headerValue=request.getHeader(headerName);//取出头信息内容
%>
    <h5><%=headerName%> -- <%=headerValue%></h5>
<%
    }
%>
<h5>----RESPONSE----</h5>
<p></p>
<p></p>
<%
 String ct = response.getHeader("CLOUDWISE_TRACE");
%>
<h5>CLOUDWISE_REQUEST_ID -- <%=response.getHeader("CLOUDWISE_REQUEST_ID")%></h5>
<h5>CLOUDWISEREQUESTID -- <%=response.getHeader("CLOUDWISEREQUESTID")%></h5>
<p></p>
<p></p>
<h5>CLOUDWISE_TRACE -- <%=ct%></h5>
<h5>CLOUDWISETRACE -- <%=response.getHeader("CLOUDWISETRACE")%></h5>
<h5>HeaderNames -- <%=response.getHeaderNames()%></h5>
<h5>Headers -- <%=response.getHeaders("CLOUDWISE_TRACE")%> 
<%=response.getHeaders("CLOUDWISETRACE")%></h5>
</body>
</html>