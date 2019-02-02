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
    <title>Redis_amout</title>
<script type="text/javascript">
jQuery("document").ready(function(){
	
	
	jQuery("#save").click(function(){
		//alert("123");
		var name = jQuery("#name").val();
		var amount = jQuery("#amount").val();
		var caid = jQuery("#id").val();
		jQuery("#amount").attr("action","<%=request.getContextPath() %>/updateAmountById.action?name="+name+"&amount="+amount+"&caid="+caid);
		jQuery("#amount").submit();
	});
	jQuery("#search").click(function(){
		//alert("123");
		var caid = jQuery("#id").val();
		jQuery("#amount").attr("action","<%=request.getContextPath() %>/queryAmountById.action");
		jQuery("#amount").submit();
	});
	jQuery("#add").click(function(){
		//alert("123");
		jQuery("#amount").attr("action","<%=request.getContextPath() %>/addAmount.action");
		jQuery("#amount").submit();
	});
	jQuery("#id").change(function(){
		var caid = jQuery(this).val();
		jQuery("#name").val("stu"+caid);
		jQuery("#amount").val("99.9");
	});
})
function getRedis(){
<%-- 	location.href = "<%=request.getContextPath() %>/student/delStudentById.action?student.sid="+sid; --%>
	var caid = jQuery("#id").val();
//	alert("get->"+name);
	$.ajax({
	    type:'post',
		url: "<%=request.getContextPath() %>/queryAmountById.action?caid="+caid, //调用的action  
	    data: {}, //请求的参数  
	    dataType:'json',
	    success:function(data){  
//	    	alert("msg"+data);
	    	$("#name").val(data.name);
	    	$("#amount").val(data.svalue);
	    }  
	});
}

function delRedis(){
	<%-- 	location.href = "<%=request.getContextPath() %>/student/delStudentById.action?student.sid="+sid; --%>
	var caid = jQuery("#id").val();
//	alert("del->"+name);	
	$.ajax({
			url: "<%=request.getContextPath() %>/amount/delAmountById.action?caid="+caid, //调用的action  
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

</script>
	
  </head>
  
  <body>
  	 <form id="amount" action="${pageContext.request.contextPath}/amount/amount.action" method="post" >
     	<table cellpadding="0" cellspacing="0" bgcolor="#F4F4F4">
     		<tr>
     			<td>id</td>
     			<td><input type="text" id="id" name="id" value="${id}"/></td>
     			<td>name</td>
     			<td><input type="text" id="name" name="name" value="${name}"/></td>
     			<td>amount</td>
     			<td><input type="text" id="amount" name="amount" value="${amount}"/></td>
     		</tr>
     	</table>
<!--     	<input type="submit" value="全部"/>  -->
     	<input type="button" id="search" value="查看"  />
     	<input type="button" id="del" value="删除"  onclick="delRedis()"/>
     	<input type="button" id="save" value="保存" />
     	<input type="button" id="add" value="新增" />
     	<span id="dbxl" ></span>
     </form>
     <input type="button" onclick="window.location.href='${pageContext.request.contextPath}/amount/amount.action'" value="刷新"/><br/>
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
