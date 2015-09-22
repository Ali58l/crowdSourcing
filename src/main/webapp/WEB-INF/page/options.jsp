<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" > 
             <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script> 
             <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" ></script> 
             <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<title>Welcome</title>
</head> 
<body>
<div class="container-fluid">
<span class="label label-success">Welcome<c:out value="${person.name}"/></span>

<a href="/auctionbase1/logout" ><p align="right">logout</p></a></br></br>
 </br>
 </br>
 <div>
  <a href="/auctionbase1/bid" class="alert-link"><h3>Add Proposal</h3></a></br>
  <a href="/auctionbase1/auction/myProposal" class="alert-link"><h3>My Proposals Status</h3></a></br>
  <a href="/auctionbase1/auction/openProposal" class="alert-link"><h3>Open Proposals</h3></a></br>
  <a href="/auctionbase1/auction/finalProposal" class="alert-link"><h3>Final Proposals</h3></a></br>
  <a href="/auctionbase1/auction/myProposalInAuction" class="alert-link"><h3>Final Proposals</h3></a></br>
  
</div>
<div>
  
</div>
  </div>
</body>
</html>
