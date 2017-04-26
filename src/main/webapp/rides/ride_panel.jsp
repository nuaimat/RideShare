<%--
  Created by IntelliJ IDEA.
  User: nuaimat
  Date: 4/25/17
  Time: 11:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="panel panel-primary">
        <div class="panel-heading ridetitle"><span class="text-primary">${ride_obj.userid}</span><span
                class="title-text"><c:out value="${ride_obj.post}" /></span></div>
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
