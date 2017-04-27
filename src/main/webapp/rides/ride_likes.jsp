<%--
  Created by IntelliJ IDEA.
  User: nuaimat
  Date: 4/25/17
  Time: 4:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${ride_obj.likedByCurrentUser}"><a href="#" class="unlike_post">Dislike <span class="glyphicon glyphicon-thumbs-down"></span></a></c:when>
    <c:otherwise>
        <a href="#" class="like_post">Like <span class="glyphicon glyphicon-thumbs-up"></span></a>
    </c:otherwise>
</c:choose>
Liked by <span class="text-primary">${ride_obj.likesCount} Users</span>
