<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class= procontainer>
<h2>My Profile</h2>
<div><img src="images/user.png" alt="Profile picture" "></div>
<div class=address1>
<form method="post" action= "editprofile">
 <div>User Name:${sessionScope.user.fullName}  </div><div>
 Email: ${sessionScope.user.email}<input type= "text" name="email" value="${sessionScope.user.email}"/></div><div>
 State: ${sessionScope.user.state}<input type= "text" name="state"value="${sessionScope.user.state}"/></div><div>
 City: ${sessionScope.user.city}<input type= "text" name="city" value="${sessionScope.user.city}"/></div><div>
 Street: ${sessionScope.user.street}<input type= "text" name="street" value="${sessionScope.user.street}"/></div><div>
 Zip code: ${sessionScope.user.zipCode}<input type= "text" name="zip" value="${sessionScope.user.zipCode}"/></div><div>
 Bithday: ${sessionScope.user.birthYear}<input type= "text" name="year" value="${sessionScope.user.birthYear}"/></div>
 <input type="submit"  value="save"/>
 </form>
</div>
</body>
</html>