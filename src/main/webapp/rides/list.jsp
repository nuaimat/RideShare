<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../templates/header_template.jsp" />
<h2>Hello World!</h2>
<div class="row">
    <div class="col-sm-4">
        <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#newRideModal">Offer/Request Ride</button>



        <!-- Modal -->
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Modal Header</h4>
                    </div>
                    <div class="modal-body">
                        <p>Some text in the modal.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="col-sm-4">
        <c:forEach items="${rides}" var="ride">
            <div>${ride.dateCreated}</div>
        </c:forEach>
    </div>
</div>
<jsp:include page="../templates/footer_template.jsp" />