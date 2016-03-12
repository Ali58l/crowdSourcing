<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <!DOCTYPE html> 
<html>

<head>
<title>Pay through PayPal: http://www.TestAccount.com</title>
</head>
<body onload="document.forms['paypalForm'].submit();">
<form name="paypalForm" action="https://www.paypal.com/cgi-bin/webscr" commandName="taskworker" method="post">
 <input type="hidden" name="cmd" value="_xclick" />
 <!-- <input type="hidden" name="business" value="API username" /> -->
 <!-- Identify your business so that you can collect the payments. -->
 <input type="hidden" name="business"  value="hojabr.sattari@gmail.com">
 
 <input type="hidden" name="password" value="API password" />
 <input type="hidden" name="custom" value="1123" />
 <input type="hidden" name="item_name" value="${taskworker.task.description}" />
 <input type="hidden" name="amount" value="${taskworker.task.limitBudgetPerUser}"/>
 <input type="hidden" name="rm" value="1" />
 <input type="hidden" name="return" value="http://localhost:8080/PaypalGS/paypalResponse.jsp" />
 <input type="hidden" name="cancel_return" value="http://localhost:8080/PaypalGS/paypalResponseCancel.jsp" />
 <input type="hidden" name="cert_id" value="API Singature" />
  <!-- Display the payment button. -->

    <input type="image" name="submit"

    src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif"

    alt="PayPal - The safer, easier way to pay online">
 
</form>
</body>
</html>
</html>
