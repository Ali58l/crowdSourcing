<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" > 
             <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script> 
             <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" ></script> 
             <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<title>Login</title>
</head>
<body>  
<div class="container-fluid"> 
   
<div class="form-signin">
<div class="alert alert-success" role="alert">
  <h2 class="form-signin-heading">Please sign in</h2>
</div>

<form:form method="post" action="/login" commandName="loginForm" class="navbar-form navbar-left">
<div class="form-group">
   <table>
    <tr>
        <td><form:label path="username"><h4>Username </h4></form:label></td>
        <td><form:input path="username" class="form-control"/></td>
    </tr>
    <tr>
        <td><form:label path="password" ><h4>Password </h4></form:label></td>
        <td><form:input path="password" type="password" class="form-control"/></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="Submit" value="Login" class="btn btn-lg btn-primary btn-block"/>
        </td>
    </tr>
</table>  
</div>
  </form:form>
  </div>
</div>
<div>
   <a href="/register" class="navbar-brand"><h4>Register</h4></a></br></br>
  </div>
</body>
</html>
