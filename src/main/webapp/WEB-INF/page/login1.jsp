<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
<title>Login</title>
</head>
<body>  
  
<div class="container">

  <form class="form-signin" method="post" action="/auctionbase1/login">
    <h2 class="form-signin-heading">Please sign in</h2>
    <label for="username" class="sr-only">Username</label>
    <input type="text" id="username" class="form-control" placeholder="username" required autofocus>
    <label for="username" class="sr-only">Password</label>
    <input type="password" id="password" class="form-control" placeholder="password" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  </form>

</div> <!-- /container --> 

 <a href="/auctionbase1/register">Register</a></br></br>
</body>
</html>
