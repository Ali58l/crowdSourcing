<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register Form</title>
</head>
<body>

<h2>User Information</h2>
<form:form method="POST" action="/register/add" commandName="person">
   <table>
    <tr>
        <td><form:label path="name">Name</form:label></td>
        <td><form:input path="name" /></td>
    </tr>
    <tr>
        <td><form:label path="username">Username</form:label></td>
        <td><form:input path="username" /></td>
    </tr>
     <tr>
        <td><form:label path="password">Password</form:label></td>
        <td><form:input path="password" type="password" /></td>
    </tr>
     <tr>
        <td><form:label path="email">Email</form:label></td>
        <td><form:input path="email" /></td>
    </tr>
    
    <tr>
        <td colspan="2">
            <input type="Submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>