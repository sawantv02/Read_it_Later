<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
<script src="resources/signup.js"></script>
<link rel="stylesheet" type="text/css" href="signup.css"/>

</head>
<body>
<div class="container">

<div class="row" style="margin-top:20px">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
		<form:form role="form" action="sendmessage.htm" commandName="message" method="post">
			<fieldset>
				<h2>Send Message</h2>
				<hr class="colorgraph">
				<c:set var="check" value="${requestScope.sendtoall}"/>
				<c:choose>
					<c:when test="${check=='true'}">
					<div class="form-group">
					<input type="text" value="Share to All" class="form-control input-lg" disabled="disabled"/>
                   
					</div>
					</c:when>
					<c:when test="${check=='false'}">
					<div class="form-group">
                    <form:select path="receiver" type="text" name="receiver" class="form-control input-lg" placeholder="Send to"
                   items="${list}" itemValue="personID" itemLabel="emailId"/>
                   
					</div>
					</c:when>
				</c:choose>
				<div class="form-group">
                    <form:input path="messageBody" disabled="disabled" type="text" value="${description}" name="messageBody" id="password" class="form-control input-lg"/>
				</div>
				<div class="form-group">
                    <form:input path="messageDate" type="hidden" name="messageDate" value="<%= new java.util.Date() %>" id="messageDate"/>
				</div>
				<div class="form-group">
                    <form:input path="sender" type="hidden" name="sender" value="${user.emailId}" id="sender"/>
				</div>
				<hr class="colorgraph">
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6">
                        <input type="submit" class="btn btn-lg btn-success btn-block" value="Send">
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>

</div>

</body>
</html>