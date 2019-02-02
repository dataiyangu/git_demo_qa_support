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
    <title>Redis_emp</title>
<script type="text/javascript">
jQuery("document").ready(function(){
	
	
	jQuery("#save").click(function(){
		//alert("123");
		var name = jQuery("#sname").val();
		var value = jQuery("#svalue").val();
		jQuery("#emp").attr("action","<%=request.getContextPath() %>/emp/save.action?name="+name+"&value="+value);
		jQuery("#emp").submit();
	});
	
	jQuery("#saveM").click(function(){
		//alert("123");
		var name = jQuery("#sname").val();
		var value = jQuery("#svalue").val();
		jQuery("#emp").attr("action","<%=request.getContextPath() %>/emp/saveMongo.action?name="+name+"&value="+value);
		jQuery("#emp").submit();
	});
// 	jQuery("#get").change(function(){
// 		var name = jQuery("#sname").val();
// 		get(name);
<%--		jQuery("#emp").attr("action","<%=request.getContextPath() %>/emp/search.action?name="+name); --%>
// // 		jQuery("#emp").submit();
// 	});
// 	jQuery("#del").click(function(){
// 		//alert("123");
// 		var name = jQuery("#sname").val();
// 		del(name);
<%-- 	jQuery("#emp").attr("action","<%=request.getContextPath() %>/emp/del.action?name="+name); --%>
// // 		jQuery("#emp").submit();
// 	});
})
function getRedis(){
<%-- 	location.href = "<%=request.getContextPath() %>/student/delStudentById.action?student.sid="+sid; --%>
	var name = jQuery("#sname").val();
//	alert("get->"+name);
	$.ajax({
	    type:'post',
		url: "<%=request.getContextPath() %>/emp/search.action?name="+name, //调用的action  
	    data: {}, //请求的参数  
	    dataType:'json',
	    success:function(data){  
//	    	alert("msg"+data);
	    	$("#sname").val(name);
	    	$("#svalue").val(data.svalue);
	    }  
	});
}

function hmgetRedis(){
	<%-- 	location.href = "<%=request.getContextPath() %>/student/delStudentById.action?student.sid="+sid; --%>
		var name = jQuery("#sname").val();
		var value = jQuery("#svalue").val();
//		alert("get->"+name);
		$.ajax({
		    type:'post',
			url: "<%=request.getContextPath() %>/emp/hmget.action?name="+name+"&value="+value, //调用的action  
		    data: {}, //请求的参数  
		    dataType:'json',
		    success:function(data){  
//		    	alert("msg"+data);
		    	$("#sname").val(name);
		    	$("#svalue").val(data.svalue);
		    }  
		});
	}
	
function hmsetRedis(){
	<%-- 	location.href = "<%=request.getContextPath() %>/student/delStudentById.action?student.sid="+sid; --%>
		var name = jQuery("#sname").val();
		var value = jQuery("#svalue").val();
//		alert("get->"+name +"-val->" + value);
		$.ajax({
		    type:'post',
			url: "<%=request.getContextPath() %>/emp/hmset.action?name="+name+"&value="+value, //调用的action  
		    data: {}, //请求的参数  
		    dataType:'json',
		    success:function(data){  
//		    	alert("msg"+data);
		    	$("#sname").val(name);
		    	$("#svalue").val(data.svalue);
		    }  
		});
	}
	
function pingRedis(){
	<%-- 	location.href = "<%=request.getContextPath() %>/student/delStudentById.action?student.sid="+sid; --%>
		var name = jQuery("#sname").val();
//		alert("get->"+name);
		$.ajax({
		    type:'post',
			url: "<%=request.getContextPath() %>/emp/ping.action?name="+name, //调用的action  
		    data: {}, //请求的参数  
		    dataType:'json',
		    success:function(data){  
//		    	alert("msg"+data);
		    	$("#sname").val(name);
		    	$("#svalue").val(data.svalue);
		    }  
		});
	}

function delRedis(){
	<%-- 	location.href = "<%=request.getContextPath() %>/student/delStudentById.action?student.sid="+sid; --%>
	var name = jQuery("#sname").val();
//	alert("del->"+name);	
	$.ajax({
			url: "<%=request.getContextPath() %>/emp/del.action?name="+name, //调用的action  
		    data: {  }, //请求的参数  
		    type:'post',
		    dataType:'json',
		    success:function(data){  
//		    	alert("msg"+data);
		    	$("#dbxl").html(data);
		    }  
		});
	}
	
function saveCommRedis(){
	<%-- 	location.href = "<%=request.getContextPath() %>/student/delStudentById.action?student.sid="+sid; --%>
	var name = jQuery("#sname").val();
//	alert("del->"+name);	
	$.ajax({
			url: "<%=request.getContextPath() %>/emp/saveComm.action?name="+name, //调用的action  
		    data: {  }, //请求的参数  
		    type:'post',
		    dataType:'json',
		    success:function(data){  
//		    	alert("msg"+data);
		    	$("#dbxl").html(data);
		    }  
		});
	}
	
function scanRedis(){
	<%-- 	location.href = "<%=request.getContextPath() %>/student/delStudentById.action?student.sid="+sid; --%>
	var name = jQuery("#sname").val();
//	alert("del->"+name);	
	$.ajax({
			url: "<%=request.getContextPath() %>/emp/scan.action?name="+name, //调用的action  
		    data: {  }, //请求的参数  
		    type:'post',
		    dataType:'json',
		    success:function(data){  
//		    	alert("msg"+data);
		    	$("#dbxl").html(data);
		    }  
		});
	}
	
function quitRedis(){
	<%-- 	location.href = "<%=request.getContextPath() %>/student/delStudentById.action?student.sid="+sid; --%>
	var name = jQuery("#sname").val();
//	alert("del->"+name);	
	$.ajax({
			url: "<%=request.getContextPath() %>/emp/quit.action?name="+name, //调用的action  
		    data: {  }, //请求的参数  
		    type:'post',
		    dataType:'json',
		    success:function(data){  
//		    	alert("msg"+data);
		    	$("#dbxl").html(data);
		    }  
		});
	}


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
function getMongo(){
	<%-- 	location.href = "<%=request.getContextPath() %>/student/delStudentById.action?student.sid="+sid; --%>
		var name = jQuery("#sname").val();
		var value = jQuery("#svalue").val();
//		alert("get->"+name);
		$.ajax({
		    type:'post',
			url: "<%=request.getContextPath() %>/emp/getMongo.action?name="+name+"&value="+value, //调用的action  
		    data: {}, //请求的参数  
		    dataType:'json',
		    success:function(data){  
//		    	alert("msg"+data);
		    	$("#mname").val(name);
		    	$("#mvalue").val(data.svalue);
		    }  
		});
	}

	function delMongo(){
		<%-- 	location.href = "<%=request.getContextPath() %>/student/delStudentById.action?student.sid="+sid; --%>
		var name = jQuery("#sname").val();
		var value = jQuery("#svalue").val();
//		alert("del->"+name);	
		$.ajax({
				url: "<%=request.getContextPath() %>/emp/delMongo.action?name="+name+"&value="+value, //调用的action  
			    data: {  }, //请求的参数  
			    type:'post',
			    dataType:'json',
			    success:function(data){  
//			    	alert("msg"+data);
			    	$("#mresult").html(data);
			    }  
			});
		}
	function updateMongo(){
		<%-- 	location.href = "<%=request.getContextPath() %>/student/delStudentById.action?student.sid="+sid; --%>
		var name = jQuery("#sname").val();
		var value = jQuery("#svalue").val();
//		alert("del->"+name);	
		$.ajax({
				url: "<%=request.getContextPath() %>/emp/updateMongo.action?name="+name+"&value="+value, //调用的action  
			    data: {  }, //请求的参数  
			    type:'post',
			    dataType:'json',
			    success:function(data){  
//			    	alert("msg"+data);
			    	$("#mresult").html(data);
			    }  
			});
		}
</script>
	
  </head>
  
  <body>
  	 <form id="emp" action="${pageContext.request.contextPath}/emp/redis.action" method="post" >
     	<table cellpadding="0" cellspacing="0" bgcolor="#F4F4F4">
     		<tr>
     			<td>key</td>
     			<td><input type="text" id="sname" name="name" value="${name}"/></td>
     			<td>value</td>
     			<td><input type="text" id="svalue" name="value" value="${value}"/></td>
     		</tr>
     	</table>
<!--     	<input type="submit" value="全部"/>  -->
		<p>-- Redis operation --</p>
     	<input type="button" id="search" value="查看" onclick="getRedis()"/>
     	<input type="button" id="del" value="删除" onclick="delRedis()"/>
     	<input type="button" id="save" value="保存" />
     	<p></p>
     	<input type="button" id="saveComm" value="JedisSave" onclick="saveCommRedis()"/>
     	<input type="button" id="scan" value="Scan" onclick="scanRedis()"/>
     	<input type="button" id="quit" value="Quit" onclick="quitRedis()"/>
     	<p></p>
     	<input type="button" id="saveComm" value="HmSet" onclick="hmsetRedis()"/>
     	<input type="button" id="scan" value="HmGet" onclick="hmgetRedis()"/>
     	<input type="button" id="quit" value="Ping" onclick="pingRedis()"/>
     	
     	<span id="dbxl" ></span>
     	<p>-- Mongo operation --</p>
     	<input type="button" id="searchM" value="查看" onclick="getMongo()"/>
     	<input type="button" id="delM" value="删除" onclick="delMongo()"/>
     	<input type="button" id="updateM" value="更新" onclick="updateMongo()"/>
     	<input type="button" id="saveM" value="保存" />
     	<span id="mresult" ></span>
     	<p></p>		
     	<table cellpadding="0" cellspacing="0" bgcolor="#F4F4F4">
     		<tr>
     			<td>result:key</td>
     			<td><input type="text" id="mname" name="mname" value="${name}"/></td>
     		</tr>
     		<tr>
     			<td>result:value</td>
     			<td><input type="text" style="width:600px;height:100px;" id="mvalue" name="mvalue" value="${value}"/></td>
     		</tr>
     	</table>
     </form>
     <input type="button" onclick="window.location.href='${pageContext.request.contextPath}/emp/redis.action'" value="刷新"/><br/>
   <%--   <c:if test="${fn:length(stuList) > 0}">
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
     ${msg} --%>
  </body>
</html>
