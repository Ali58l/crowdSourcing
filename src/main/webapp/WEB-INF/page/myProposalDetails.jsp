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
<title>My Proposal Details</title>
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
    <th>Is Active</th>
    <th>Username</th>
    <th>Max Price</th>
</tr> 
</thead> 
 <c:forEach items="${auctions}" var="auction"> 
       <tr> 
   	       <td><a href="/auctionbase1/auction/myProposalDetails/${prop.id}">${prop.id}</a></td> 
           <td>${auction.proposals.proposalName}</td> 
           <td>${auction.proposals.creationDate}</td> 
           <td>${auction.proposals.updateDate}</td>
           <td>${auction.proposals.category}</td>
           <td>${auction.proposals.isActive}</td>
           <td>${auction.personPriceProposed.username}</td>
           <td>${auction.proposedPrice}</td>
       </tr> 
   </c:forEach> 
</tbody>
</table>
</div>
</html>
