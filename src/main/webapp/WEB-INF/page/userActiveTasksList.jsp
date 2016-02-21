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
<title>Allocated Users</title>
</head>
<body>   
   
<div class="alert alert-success" role="alert">
  <h2 class="form-signin-heading">Allocated Users</h2>
  <a href="/logout" ><p align="right">logout</p></a>
   <a href="/options" class="navbar-brand"><h4>Options</h4></a>
</div>

<table class="table">
   <thead> 
<tr> 
	<th>Payment</th>
	<th>Assign Credibility</th>
    <th>Username</th> 
    <th>Skill</th>     
</tr> 
</thead> 
<tbody> 
    <c:forEach items="${taskworker}" var="tw"> 
       <tr> 
    	   <td><a href="/task/pay/${tw.id}">Payment</a></td>
    	   <td><a href="/task/assignWorkerCredibility/${tw.id}">Assign Credibility</a></td>
           <td>${tw.person.username}</td> 
           <td>${tw.task.skill}</td> 
       </tr> 
   </c:forEach> 
</tbody> 
   
</table>
</body>
</html>
