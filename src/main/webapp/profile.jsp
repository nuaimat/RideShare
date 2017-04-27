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

	<!-- <div class="descr">your personal profile contains all personal address and what we think we know about you</div> -->
	<%-- <div class=address>User Name: ${sessionScope.user.fullName}   Email: ${sessionScope.user.email}</div> --%>
	<div class=procontainer>
		<h2>My Profile</h2>
		<div>
			<img src="images/user.png" alt="Profile picture"">
		</div>
		<div class=address1>
			<div>User Name:${sessionScope.user.fullName}</div>
			<div>Email: ${sessionScope.user.email}</div>
			<div>State: ${sessionScope.user.state}</div>
			<div>City: ${sessionScope.user.city}</div>
			<div>Street: ${sessionScope.user.street}</div>
			<div>Zip code: ${sessionScope.user.zipCode}</div>
			<div>Bithday: ${sessionScope.user.birthYear}</div>
		</div>
		
		a href="<c:url value="/profile?edit=1" />"> Edit profile </a>
</body>
</html>