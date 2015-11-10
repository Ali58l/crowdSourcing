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
<title>Proposal Details</title>
</head>
<body>   
   
<div class="alert alert-success" role="alert">
  <h2 class="form-signin-heading">Proposal Details</h2>
  <a href="/logout" ><p align="right">logout</p></a>
   <a href="/options" class="navbar-brand"><h4>Options</h4></a>
</div>
<div>
<table class="table">
   <thead> 
<tr> 
	<th>ID</th>
    <th>Item</th> 
    <th>Creation Date</th>
    <th>Update Date</th>  
    <th>Category</th>
    <th>Max Price</th>
    <th>Winner</th>
</tr> 
</thead> 
<tbody>  
       <tr> 
           <td>${maxPrice.proposals.id}</a></td> 
           <td>${maxPrice.proposals.proposalName}</td> 
           <td>${maxPrice.proposals.creationDate}</td> 
           <td>${maxPrice.proposals.updateDate}</td>
           <td>${maxPrice.proposals.category}</td>
           <td>${maxPrice.proposedPrice}</td> 
           <td>${maxPrice.personPriceProposed.name}</td>
           
       </tr> 
</tbody> 
</table>
</div>
</div>
</body>
</html>
