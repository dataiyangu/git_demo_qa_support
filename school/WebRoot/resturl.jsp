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
    <title>HttpUrlConnection</title>
<script> 

    // window['adrum-start-time']= new Date().getTime();  */

    //'<app-key-for-this-app>'; 
    //    window['adrum-app-key'] = 'MyApp';

</script> 

<%-- <script src="<%=request.getContextPath() %>/resource/js/adrum.js"></script> --%>

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
	jQuery("#redisTest").click(function(){
		//alert("123");
		jQuery("#student").attr("action","<%=request.getContextPath() %>/emp/redis.action");
		jQuery("#student").submit();
	});
	
	jQuery("#restclient").click(function(){
		//alert("123");
		jQuery("#student").attr("action","<%=request.getContextPath() %>/student/restclient.action");
		jQuery("#student").submit();
	});
	
	jQuery("#restclient31").click(function(){
		//alert("123");
		jQuery("#student").attr("action","<%=request.getContextPath() %>/student/restclient31.action");
		jQuery("#student").submit();
	});
	
	jQuery("#restapi").click(function(){
		//alert("123");
		jQuery("#student").attr("action","<%=request.getContextPath() %>/student/restapi.action");
		jQuery("#student").submit();
	});
})
function delstu(sid){
<%-- 	location.href = "<%=request.getContextPath() %>/student/delStudentById.action?student.sid="+sid; --%>
	$.ajax({  
	    url: "<%=request.getContextPath() %>/student/delStudentById.action?student.sid="+sid, //调用的action  
	    data: {  }, //请求的参数  
	    type:'post',
	    datatype:'json',
	    success:function(data){  
	    	//alert("msg"+data);
	    	$("#"+sid).remove();
	        $("#dbxl").html(data);
	    }  
	});

}

</script>
	
  </head>
  
  <body>
    <H1>HttpUrlConnection</H1>
  	 <form id="student" action="${pageContext.request.contextPath}/student/resturl.action" method="post" >
     	<table cellpadding="0" cellspacing="0" bgcolor="#F4F4F4" >
     		<tr>
     			<td>restURL</td>
     			<td ><input type="text" id="sname" name="student.sname" style="width:420px" value="${student.sname}"/></td>
     		</tr>
     		<tr>
     			<td>times(int)</td>
     			<td ><input type="text" id="sid" name="student.sid" style="width:420px" value="${student.sid}"/></td>
     		</tr>
     		<tr>
     			<td>round(int)</td>
     			<td ><input type="text" id="cid" name="student.cid" style="width:420px" value="${student.cid}"/></td>
     		</tr>
     		<tr>
     			<td>result</td>
     			<td ><input type="text" id="major" name="student.major" style="width:420px" value="${student.major}"/>
     			<!-- <input type="hidden" id="score" name="student.score"/>
     			<input type="hidden" id="sid" name="student.sid"/>
     			<input type="hidden" id="cid" name="student.cid"/>
     			<input type="hidden" id="birth" name="student.birth" /> -->
     			</td>
     		</tr>
     	</table>
     	<input type="button" id="restclient" value="HttpClient" />
     	<input type="button" id="restclient31" value="HttpClient-3.1" />
     	<input type="button" id="restapi" value="RestApi" />

     	<input type="submit" value="current HttpUrlConnection"/>
     	<span id="dbxl" ></span>
     </form>
     ${msg}
  </body>
</html>
