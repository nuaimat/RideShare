<%--
  Created by IntelliJ IDEA.
  User: nuaimat
  Date: 4/26/17
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${rides}" var="ride">
    <c:set var="ride_obj" value="${ride}" scope="request" />
    <jsp:include page="/rides/ride_panel.jsp" />
</c:forEach>
