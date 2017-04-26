<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Profile</h2>
<div><img src="user.jpg" alt="Profile picture" "></div>
<div>User Name: ${sessionScope.user.fullName}</div>
<div>Email: ${sessionScope.user.email}</div>
<div>State: ${sessionScope.user.state}</div>
<div>City: ${sessionScope.user.city}</div>
<div>Street: ${sessionScope.user.street}</div>
<div>Zip code: ${sessionScope.user.zipCode}</div>
<div>Bithday: ${sessionScope.user.birthYear}</div>


   
</body>
</html>