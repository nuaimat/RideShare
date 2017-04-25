<%--
  Created by IntelliJ IDEA.
  User: nuaimat
  Date: 4/25/17
  Time: 11:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" style="margin-right: 2em">
    <div class="panel panel-primary">
        <div class="panel-heading ridetitle"><span class="text-primary">${ride_obj.userid}</span><span class="title-text">${ride_obj.post}</span></div>
        <div class="panel-body">
            <div class="row">
                <div class="ridedest">
                    <img src="https://maps.googleapis.com/maps/api/staticmap?maptype=roadmap&center=${ride_obj.src.googleFormat}&zoom=11&size=160x100&key=${gmap_api_key}" />
                    <span class="bld">From: </span>${ride_obj.srcHumanReadable}
                </div>
                <div class="ridedest">
                    <img src="https://maps.googleapis.com/maps/api/staticmap?maptype=roadmap&center=${ride_obj.dest.googleFormat}&zoom=11&size=160x100&key=${gmap_api_key}" />
                    <span class="bld">To: </span>${ride_obj.destHumanReadable}
                </div>

            </div>
        </div>
        <div class="panel-footer">
            <div class="row ride-likes">
                <jsp:include page="/rides/ride_likes.jsp" />
            </div>
            <div class="row ride-comments">
                <jsp:include page="/rides/ride_comments.jsp" />
            </div>
        </div>
    </div>

</div>
