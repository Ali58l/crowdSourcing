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
<title>Add skill</title>
</head>
<body>
<div class="alert alert-success" role="alert">
<h2 class="form-signin-heading">Please Add your Skill:</h2>
  <a href="/logout" ><p align="right">logout</p></a>
   <a href="/options" class="navbar-brand"><h4>Options</h4></a>
</div>  
<div class="container-fluid"> 
   
<div class="form-signin">
<form:form method="post" action="/addSkill" commandName="skillForm" class="navbar-form navbar-left">
<div class="form-group">

   <table>
    <tr>
        <td><form:label path="skill"><h4> Skill </h4></form:label></td>
        <td><form:input path="skill" class="form-control"/></td>
    </tr>
    <tr>
        <td><form:label path="experience"><h4> Experience(year) </h4></form:label></td>
        <td><form:input path="experience" class="form-control"/></td>
    </tr>
     <tr>
        <td><form:label path="availabilityTime"><h4> availabilityTime(hr) </h4></form:label></td>
        <td><form:input path="availabilityTime" class="form-control"/></td>
    </tr>
    <tr>
        <td><form:label path="charge"><h4> Charge(hr/$) </h4></form:label></td>
        <td><form:input path="charge" class="form-control"/></td>
    </tr>
   
    <tr>
        <td colspan="2">
            <input type="Submit" value="submit" class="btn btn-lg btn-primary btn-block"/>
        </td>
    </tr>
</table>  
</div>
  </form:form>
  </div>
</div>
</body>
</html>
