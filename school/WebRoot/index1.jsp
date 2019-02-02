<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="<%=request.getContextPath() %>/resource/js/jquery-1.11.3.js"></script>
    <title>MyBatis3.11 + Spring3.1.2 + Struts2</title>
  </head>
  
  <body>
  	 <form action="${pageContext.request.contextPath}/classes/queryClassesById.action" method="post">
     	<table cellpadding="0" cellspacing="0" bgcolor="#F4F4F4">
     		<tr>
     			<td>班级id</td>
     			<td><input type="text" name="classes.cid"/></td>
     		</tr>
     	</table>
     	<input type="submit" value="查询"/>
     </form>
     <input type="button" onclick="window.location.href='${pageContext.request.contextPath}/classes/allStudent.action'" value="获取所有学生"/><br/>
     <table style="border: 1px solid blue;" border="1px" cellspacing="0" bgcolor="#F4F4F4">
     	<tr>
     		<td width="100px">cid</td>
     		<td width="100px">cname</td>
     		<td width="100px">teacher</td>
     		<td width="100px">crateDate</td>
     		<td width="100px">操作</td>
     	</tr>
     	<tr>
     		<td>${classes.cid}</td>
     		<td>${classes.cname}</td>
     		<td>${classes.teacher}</td>
     		<td>${classes.createDate}</td>
     		<td width="100px"><input type="button" onclick="window.location.href='${pageContext.request.contextPath}/classes/delClassesById.action?classes.cid=12'" value="删除"/> </td></td>
     	</tr>
     <c:if test="${fn:length(classes.students)>0}">
     	<tr align="center">
     		<td width="100px">sid</td>
     		<td width="100px">sname</td>
     		<td width="150px">birth</td>
     		<td width="100px">score</td>
     		<td width="100px"></td>
     	</tr>
     	<c:forEach items="${classes.students}" var="student">
     	<tr>
     		<td width="100px">${student.sid}</td>
     		<td width="100px">${student.sname}</td>
     		<td width="180px"><fmt:formatDate value="${student.birth}" pattern="yyyy-MM-dd HH:mm:ss"/><br></td>
     		<td width="100px">${student.score}</td>
     		<td><input type="button" onclick="window.location.href='${pageContext.request.contextPath}/student/delStudentById.action?student.sid=12'" value="删除"/> </td>
     	</tr>
     </c:forEach>
     </c:if>
      </table>
     ${msg}
  </body>
</html>
