<%@ page import="edu.mum.wap42016.group1.project.model.Ride" %>
<%--
  Created by IntelliJ IDEA.
  User: nuaimat
  Date: 4/25/17
  Time: 11:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ridetype_ask">
    <%=Ride.RideType.ASKED%>
</c:set>
<div class="row">
    <div class="panel panel-primary">
        <div class="panel-heading ridetitle"><span class="text-primary">${ride_obj.user.fullName}</span>
            <span class="title-text"><c:out value="${ride_obj.post}" /></span>
            <div style="float: right;">
            <c:choose>
                <c:when test="${ride_obj.posttype == ridetype_ask}">
                    <span class="glyphicon glyphicon-plane"></span> Ask
                </c:when>
                <c:otherwise>
                    <span class="glyphicon glyphicon-bullhorn"></span> Offer
                </c:otherwise>
            </c:choose>
            </div>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="ridedest">
                    <div>
                        <span class="badge">From</span><br/>
                        <img src="https://maps.googleapis.com/maps/api/staticmap?maptype=roadmap&center=${ride_obj.src.googleFormat}&zoom=11&size=160x100&key=${gmap_api_key}"/>
                    </div>
                    <div class="address">${ride_obj.srcHumanReadable}</div>
                </div>
                <div class="ridedest">
                    <div>
                        <span class="badge">To</span><br/>
                        <img src="https://maps.googleapis.com/maps/api/staticmap?maptype=roadmap&center=${ride_obj.dest.googleFormat}&zoom=11&size=160x100&key=${gmap_api_key}"/>
                    </div>
                    <div class="address">${ride_obj.destHumanReadable}</div>
                </div>

            </div>
        </div>
        <div class="panel-footer">
            <div class="row ride-likes">
                <jsp:include page="/rides/ride_likes.jsp"/>
            </div>
            <div class="row ride-comments">
                <jsp:include page="/rides/ride_comments.jsp"/>
            </div>
        </div>
    </div>

</div>
