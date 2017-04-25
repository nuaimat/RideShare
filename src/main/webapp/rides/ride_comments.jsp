<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nuaimat
  Date: 4/25/17
  Time: 4:35 PM
  To change this template use File | Settings | File Templates.
--%>
<ul>
<c:forEach items="${ride_obj.commentList}" var="comment">
     <li>${comment.comment}</li>
</c:forEach>
</ul>