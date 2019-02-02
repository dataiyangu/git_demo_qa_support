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
<!--   <script>
    var CWRUMLICENCE = 'J45Engw88NchTUhqO1yVcnYn5/nOhfqv';
</script>
<script src="http://portal-qa.toushibao.com/rum/EndUserAgentPreload.js"></script>
-->
<script> 

    window['adrum-start-time']= new Date().getTime();  

    //'<app-key-for-this-app>'; 
//       window['adrum-app-key'] = 'MyApp';

</script> 

 <script src="<%=request.getContextPath() %>/resource/js/adrum.js"></script>  

<script type="text/javascript">
jQuery("document").ready(function(){
	
	
	jQuery("#procedure").click(function(){
		//alert("123");
		jQuery("#student").attr("action","<%=request.getContextPath() %>/student/updateAndSelect.action");
		jQuery("#student").submit();
	});
	jQuery("#add").click(function(){
		//alert("123");
		jQuery("#student").attr("action","<%=request.getContextPath() %>/student/addStudent.action");
		jQuery("#student").submit();
	});
	jQuery("#sid").change(function(){
		var sid = jQuery(this).val();
		jQuery("#sname").val("stu"+sid);
		jQuery("#major").val("cc");
		jQuery("#score").val("99.9");
		jQuery("#cid").val("1");
	});
	jQuery("#redisTest").click(function(){
		//alert("123");
		jQuery("#student").attr("action","<%=request.getContextPath() %>/emp/redis.action");
		jQuery("#student").submit();
	});
	
	jQuery("#resturl").click(function(){
		//alert("123");
		jQuery("#student").attr("action","<%=request.getContextPath() %>/student/resturl.action");
		jQuery("#student").submit();
	});
	
})
function delstu(sid){
<%-- 	location.href = "<%=request.getContextPath() %>/student/delStudentById.action?student.sid="+sid; --%>
	$.ajax({  
	    url: "<%=request.getContextPath() %>/student/delStudentById.action?", //调用的action  student.sid="+sid
	    data: { 'student.sid':sid }, //请求的参数  
	    type:'post',
	    datatype:'json',
	    success:function(data){  
	    	//alert("msg"+data);
	    	$("#"+sid).remove();
	        $("#dbxl").html(data);
	    }  
	});

	<%-- var newWin=window.open("<%=request.getContextPath() %>/header.jsp", "_blank" , "height=300,width=500,scrollbars=no,location=no");
 --%>
 }

</script>
	
  </head>
  
  <body>
  	 <form id="student" action="${pageContext.request.contextPath}/student/allStudent.action" method="post" >
     	<table cellpadding="0" cellspacing="0" bgcolor="#F4F4F4">
     		<tr>
     			<td>学生id</td>
     			<td><input type="text" id="sid" name="student.sid"/></td>
     			<td>学生姓名</td>
     			<td><input type="text" id="sname" name="student.sname"/></td>
     		</tr>
     		<tr>
     			<td>专业</td>
     			<td><input type="text" id="major" name="student.major"/></td>
     			<td>得分</td>
     			<td><input type="text" id="score" name="student.score"/></td>
     		</tr>
     		<tr>
     			<td>班级id</td>
     			<td><input type="text" id="cid" name="student.cid"/></td>
     			<td>生日</td>
     			<td><input type="date" id="birth" name="student.birth"/></td>
     		</tr>
     	</table>
     	<input type="submit" value="全部"/>
     	<!-- <input type="button" id="procedure" value="修改姓名后测试oracle_procedure"/> -->
     	<input type="button" id="redisTest" value="跳转" />
     	<input type="button" id="resturl" value="RestURL" />
     	<input type="button" id="add" value="新增"/>
     	<span id="dbxl" ></span>
     </form>
     <input type="button" onclick="window.location.href='${pageContext.request.contextPath}/student/allStudent.action'" value="获取所有学生"/><br/>
     <c:if test="${fn:length(stuList) > 0}">
     <table style="border: 1px solid blue;" border="1px" cellspacing="0" bgcolor="#F4F4F4">
     	<tr align="center">
     		<td width="100px">sid</td>
     		<td width="100px">sname</td>
     		<td width="100px">major</td>
     		<td width="150px">birth</td>
     		<td width="100px">score</td>
     		<td width="100px">操作</td>
     	</tr>
     	<c:forEach items="${stuList}" var="student">
     	<tr id="${student.sid}">
     		<td width="100px">${student.sid}</td>
     		<td width="100px">${student.sname}</td>
     		<td width="100px">${student.major}</td>
     		<td width="180px"><fmt:formatDate value="${student.birth}" pattern="yyyy-MM-dd HH:mm:ss" /><br></td>
     		<td width="100px">${student.score}</td>
     		<td><input type="button" onclick="delstu('${student.sid}')" value="删除"/> </td>
     	</tr>
     </c:forEach>
     </table>
     </c:if>
     ${msg}
  </body>
</html>
