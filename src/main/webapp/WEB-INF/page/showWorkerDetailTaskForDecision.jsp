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
<title>Task Detail</title>
</head>
<body>   
   
<div class="alert alert-success" role="alert">
  <h2 class="form-signin-heading">Task Detail</h2>
  <a href="/logout" ><p align="right">logout</p></a>
   <a href="/options" class="navbar-brand"><h4>Options</h4></a>
</div>

<table class="table">
   <thead> 
<tr>
	<th>Accept</th> 
	<th>Reject</th>
	<th>Description</th>
    <th>Skill</th> 
    <th>Experience(Years)</th>
    <th>Location</th>
    <th>LimitBudgetPerUser($)</th>
</tr> 
</thead> 
<tbody> 
       <tr> 
    	   <td><a href="/task/acceptTask/${taskworker.id}">Accept</a></td>
    	   <td><a href="/task/rejectTask/${taskworker.id}">Reject</a></td>
           <td>${taskworker.task.description}</td> 
           <td>${taskworker.task.skill}</td> 
		   <td>${taskworker.task.levelSkill}</td>	
           <td>${taskworker.task.location}</td>  
           <td>${taskworker.task.limitBudgetPerUser}</td>
       </tr> 
</tbody> 
   
</table>
</body>
</html>
