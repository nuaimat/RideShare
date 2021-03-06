<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../templates/header_template.jsp">
    <jsp:param name="title" value="Ride Share"/>
</jsp:include>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3">
            <div class="righttoolbox">
                <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#newRideModal">
                Offer/Request Ride
                </button>
            </div>



            <!-- Modal -->
            <div id="newRideModal" class="modal fade" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Request or Offer a Ride</h4>
                        </div>
                        <div class="modal-body">

                            <form method="post" action="<c:url value="/trips" />" id="new-ride-form">
                                <div class="form-group">
                                    <label for="ridetype">Are you?</label>
                                    <select class="form-control" id="ridetype" name="ridetype">
                                        <option value="0">Offering a ride - I am a driver</option>
                                        <option value="1">Asking for a ride - I am a passenger</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="ridesrc">Source:</label>
                                    <input type="text" class="form-control" id="ridesrc" name="ridesrc">
                                    <input type="hidden" name="ridesrc_coords" id="ridesrc_coords">
                                    <div id="src_map_canvas" style="width: 100%; height:100px"></div>
                                </div>
                                <div class="form-group">
                                    <label for="ridedest">Destination:</label>
                                    <input type="text" class="form-control" id="ridedest" name="ridedest">
                                    <input type="hidden" name="ridedest_coords" id="ridedest_coords">
                                    <div id="dest_map_canvas" style="width: 100%; height:100px"></div>
                                </div>
                                <div class="form-group">
                                    <label for="ridedesc">Description:</label>
                                    <textarea class="form-control" id="ridedesc" name="ridedesc" rows="3"></textarea>
                                </div>
                            </form>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal" id="submit_new_ride">
                                Submit
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="col-sm-6" id="rides_list_col">
            <c:forEach items="${rides}" var="ride">
                <c:set var="ride_obj" value="${ride}" scope="request" />
                <jsp:include page="/rides/ride_panel.jsp" />
            </c:forEach>
        </div>
        <div class="col-sm-3">
            <div class="righttoolbox">
                <div id="weather" class="well" style="text-align: center;">Current Weather Loading ...</div>
            </div>

        </div>
    </div>

</div>

<div id="new-content-message">
    <div style="padding: 5px;">
        <div id="inner-message" class="alert alert-info fade in" style="display: none;">
            <button type="button" class="close" data-hide="alert">&times;</button>
            New ride posts, click <a href="#" id="fetch_new_rides">here</a> to fetch them.
            <span style="margin-right: 5px;">&nbsp;</span>
        </div>
    </div>
</div>

<script type="text/javascript"
        src="https://maps.googleapis.com/maps/api/js?key=${gmap_api_key}&libraries=places" async
        defer></script>
<jsp:include page="/rides/ws_code.jsp" />
<jsp:include page="../templates/footer_template.jsp"/>