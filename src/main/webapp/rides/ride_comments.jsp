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
        <dt>${comment.user.fullName}: </dt>
        <dd>${comment.comment}</dd>
    </c:forEach>
</dl>
<form method="post" action="/comments" class="comment_form">
    <div class="row">
        <div class="col-sm-10"><input type="text" class="form-control" name="comment">
            <input type="hidden" name="postid" value="${ride_obj.postid}"></div>
        <div class="col-sm-2">
            <button type="button" class="btn btn-default add-comment-button" id="btn_${ride_obj.postid}">
                Submit
            </button>
        </div>
    </div>


</form>