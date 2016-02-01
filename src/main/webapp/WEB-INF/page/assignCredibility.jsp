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
<div class="container-fluid"> 
   <a href="/logout" ><p align="right">logout</p></a></br></br>
<div class="form-signin">
<div class="alert alert-success" role="alert">
  <h2 class="form-signin-heading">Assign Credibility</h2>
</div>

<form:form method="POST" action="/task/assignCredibility" commandName="taskworker" class="navbar-form navbar-left">
   <table>
   <tr>
        <td><form:label path="appreciatin">Credibility [0-1]</h4></form:label></td>
        <td><form:input path="appreciatin" class="form-control" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="Submit" value="Submit" class="btn btn-lg btn-primary btn-block"/>
        </td>
    </tr>
</table>  
</form:form>
 <div>
  <a href="/options" class="alert-link"><h3>Go options page!</h3></a></br></br>
</div>

</div>
</div>
</body>
</html>