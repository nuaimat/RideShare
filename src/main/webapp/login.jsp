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
	<div class="input_container input">
		
		<Form method="post" action="login" class="login">
		    <p>${param.msg}</p>
			Email:<input type="Text" name="email" placeholder="email" /><br>
			PassWord:<input type="password" name="password"
				placeholder="password" />
			<input type="submit" value="LogIn" class="logbtn">
			<a href="<c:url value="/register" />"> SignUp </a>
		</Form>
		
	</div>
</body>
</html>