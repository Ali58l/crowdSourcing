<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" > 
             <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script> 
             <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" ></script> 
             <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<title>Assign Credibility</title>
</head>
<body>
<div class="alert alert-success" role="alert">
  <h2 class="form-signin-heading">Payment</h2>
  <a href="/logout" ><p align="right">logout</p></a>
   <a href="/options" class="navbar-brand"><h4>Options</h4></a>
</div>


<table class="table">
   <thead> 
<tr>
	<th>Username</th>
	<th>Account Number</th>
	<th>Description</th>
    <th>Skill</th> 
    <th>Location</th>
    <th>Payment($)</th>
    <th>Pay By Paypal</th>
</tr> 
</thead> 
<tbody> 
       <tr>  
       	   <td>${taskworker.person.username}</td>  	
       	   <td>${taskworker.person.accountNumber}</td>
           <td>${taskworker.task.description}</td> 
           <td>${taskworker.task.skill}</td> 	
           <td>${taskworker.task.location}</td>  
           <td>${taskworker.task.limitBudgetPerUser}</td>
           <td><a href="/task/payPaypal/${taskworker.id}">Pay!</a></td>
           
       </tr> 
</tbody> 
   
</table>
</div>
</div>
</body>
</html>