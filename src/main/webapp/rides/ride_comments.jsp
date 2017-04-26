<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nuaimat
  Date: 4/25/17
  Time: 4:35 PM
  To change this template use File | Settings | File Templates.
--%>
<dl class="comment-row">
<c:forEach items="${ride_obj.commentList}" var="comment">
     <dt>${comment.userid}</dt>
     <dd>${comment.comment}</dd>
</c:forEach>
</dl>
<form method="post" action="/comments" class="comment-form">
     <input type="text" class="form-control" name="comment">
     <input type="hidden" name="postid" value="${ride_obj.postid}">
     <button type="button" class="btn btn-default add-comment-button">
          Submit
     </button>
</form>