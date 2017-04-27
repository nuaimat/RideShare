<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<c:url value="/style/style.css" />?${rand}">
<script src="<c:url value="/js/validation.js" />?${rand}" ></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <div><h2>Sign Up</h2>					
    <hr>
    </div>
	<div class="container register">
		<Form  method="post" action="register" id="myForm">
			Full Name:<input type="Text" name="name" placeholder="Your first name"  id="input1"/><br> PassWord:<input
				type="password" name="password" placeholder="password" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9!@#$%^&*]{6,16}$" title="At least 8 character,1 capital letter , and 1 small letter"
				 id="input2"/> Email:<input
				type="Text" name="email" placeholder="Your Email" id="input5"/><br> City:<input
				type="text" name='city' placeholder="Your city" id="input3"/> Street:<input
				type="text" name='street' placeholder="Your Street" id="input4"/> Year of
			Birth:<input type="text" name='year' placeholder="YYYY" pattern="^\d{4}$" title="Four Digit Number" />Zip: <input
				type="text" name='zip' placeholder="ZIP-XXXXX" pattern="^\d{5}$" title="Five Digit Number"/> State:<select
				class="state" name="state" id="input7">
				<option>Iowa</option>
				<option>Colorado</option>
				<option>washington</option>
				<option>Alaska</option>
				<option>California</option>
				<option>Florida</option>
			</select>
			
			<fieldset id="input8">
				<legend>Sex</legend>
				Male <input type="radio" name="sex" id="sex" /> Female <input
					type="radio" id="sex" name="sex" />
			</fieldset>
			<input type='submit' Value="Register"  id="regbtn"/>
			</Form>
	</div>
	<jsp:include page="templates/footer_template.jsp" />