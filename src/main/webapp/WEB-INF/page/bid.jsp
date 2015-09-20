<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Proposal Form</title>
</head>
<body>

<h2>Proposal Information</h2>
<form:form method="POST" action="/auctionbase1/bid/add" commandName="bid">
   <table>
    <tr>
        <td><form:label path="proposalName">Item</form:label></td>
        <td><form:input path="proposalName" /></td>
    </tr>
    <tr>
        <td><form:label path="basedProposedPrice">Base Price($)</form:label></td>
        <td><form:input path="basedProposedPrice" /></td>
    </tr>
     <tr>
        <td><form:label path="category">Category</form:label></td>
        <td><form:input path="category"/></td>
    </tr>
     <tr>
        <td><form:label path="description">Description</form:label></td>
        <td><form:input path="description" /></td>
    </tr>
    
    <tr>
        <td colspan="2">
            <input type="Submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>