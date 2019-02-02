<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增/修改图书</title>
</head>
<body>
    <h3>新增/修改图书</h3>
    <form:form action="saveOrUpdateBook" method="post" modelAttribute="book">
        <table>
            <form:hidden path="id" />
            <tr>
                <td>书名：</td>
                <td><form:input path="bookName" /></td>
            </tr>
            <tr>
                <td>作者：</td>
                <td><form:input path="bookAuthor" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="保存" /></td>
            </tr>
        </table>
    </form:form>
</body>
</html>