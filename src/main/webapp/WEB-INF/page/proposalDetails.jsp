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
  <a href="/auctionbase1/logout" ><p align="right">logout</p></a>
   <a href="/auctionbase1/options" class="navbar-brand"><h4>Options</h4></a>
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
       </tr> 
</tbody> 
</table>
</div>
<div class="form-signin">
<form:form method="post" action="/auctionbase1/auction/submitNewAuction" commandName="newAuction" class="navbar-form navbar-left">
<div class="form-group">
   <table>
    <tr>
        <td><form:label path="proposedPrice"><h4>Price($) </h4></form:label></td>
        <td><form:input path="proposedPrice" class="form-control"/></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="Submit" value="Submit Your price" class="btn btn-lg btn-primary btn-block"/>
        </td>
    </tr>
</table>  
</div>
  </form:form>
  </div>
</div>
</body>
</html>
