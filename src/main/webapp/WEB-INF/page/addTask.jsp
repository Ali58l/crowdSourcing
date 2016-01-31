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
<title>Add Task</title>
</head>
<body>
<div class="container-fluid"> 
   <a href="/logout" ><p align="right">logout</p></a></br></br>
<div class="form-signin">
<div class="alert alert-success" role="alert">
  <h2 class="form-signin-heading">Add Task</h2>
</div>

<form:form method="POST" action="/task/registerTask" commandName="addTask" class="navbar-form navbar-left">
   <table>
    <tr>
        <td><form:label path=""><h4>skill</h4></form:label></td>
        <td><form:input path="skill" class="form-control" /></td>
    </tr>
    <tr>
        <td><form:label path="duration"><h4>Duration(human/hr)</h4></form:label></td>
        <td><form:input path="duration" class="form-control"/></td>
    </tr>
    <tr>
        <td><form:label path="levelSkill"><h4>Skill Level (year)</h4></form:label></td>
        <td><form:input path="levelSkill" class="form-control" /></td>
    </tr>
     <tr>
        <td><form:label path=credibility">credibility</h4></form:label></td>
        <td><form:input path="credibility" class="form-control" /></td>
    </tr>
    <tr>
        <td><form:label path="limitBudgetPerUser"><h4>Limit Budget Per Worker(hr/$)</h4></form:label></td>
        <td><form:input path="limitBudgetPerUser" class="form-control" /></td>
    </tr>
    <tr>
        <td><form:label path="maxWorker"><h4>Maximum Worker</h4></form:label></td>
        <td><form:input path="maxWorker" class="form-control" /></td>
    </tr>
    <tr>
        <td><form:label path="location"><h4>Location</h4></form:label></td>
        <td><form:input path="location" class="form-control" /></td>
    </tr>
    <tr>
        <td><form:label path="description"><h4>Description</h4></form:label></td>
        <td><form:input path="description" class="form-control" /></td>
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