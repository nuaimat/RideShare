<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<c:url value="/style/style.css" />?${rand}">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container register">
		<Form  method="post" action="registerController">
			Full Name:<input type="Text" name="name" placeholder="Your first name" /><br> PassWord:<input
				type="password" name="pass" placeholder="password" 
				 /> Email:<input
				type="Text" name="email" placeholder="Your Email" /><br> City:<input
				type="text" name='city' placeholder="Your city" /> Street:<input
				type="text" name='street' placeholder="Your Street" /> Year of
			Birth:<input type="text" name='year' placeholder="YYYY" pattern="^\d{4}$" />Zip: <input
				type="text" name='zip' placeholder="ZIP-XXXXX" pattern="^\d{5}$"/> State:<select
				class="state" name="state">
				<option>Iowa</option>
				<option>Colorado</option>
				<option>washington</option>
				<option>Alaska</option>
				<option>California</option>
				<option>Florida</option>
			</select>
			<fieldset>
				<legend>Sex</legend>
				Male <input type="radio" name="sex" id="sex" /> Female <input
					type="radio" id="sex" name="sex" />
			</fieldset>
			<input type='submit' Value="Register" />
			</Form>
	</div>
	<jsp:include page="templates/footer_template.jsp" />