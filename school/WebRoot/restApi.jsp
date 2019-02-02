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
    <title>RestTemplate or BFB</title>
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
  <H1>RestTemplate or BFB</H1>
  	 <form id="student" action="${pageContext.request.contextPath}/student/restapi.action" method="post" >
     	<table cellpadding="0" cellspacing="0" bgcolor="#F4F4F4" >
     		<tr>
     			<td>restURL</td>
     			<td ><input type="text" id="sname" name="student.sname" style="width:420px" value="${student.sname}"/></td>
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
     	<input type="submit" value="testRestAPI"/>
     	<span id="dbxl" ></span>
     </form>
     ${msg}
  </body>
</html>
