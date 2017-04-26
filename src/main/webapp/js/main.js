/**
 * Created by nuaimat on 4/24/17.
 */

if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(geoLocSuccessFunction, geoLocErrorFunction);
}

var currentLocation = {
    lat: 41.006022900000005,
    lon: -91.9787329
};
function geoLocSuccessFunction(position) {
    var lat = position.coords.latitude;
    var long = position.coords.longitude;

    currentLocation.lat = lat;
    currentLocation.lon = long;
    console.log('Your latitude is :' + lat + ' and longitude is ' + long);
}

function geoLocErrorFunction(err) {
    /*currentLocation.lat = 41.006022900000005;
     currentLocation.lon = -91.9787329;*/
}


$(function () {
    $("#submit_new_ride").click(function (evt) {
        $.ajax({
            url: $('#new-ride-form').attr("action"), //this is the submit URL
            type: $('#new-ride-form').attr("method"), //or POST
            data: $('#new-ride-form').serialize(),
            success: function (data) {
                $rowParent = $($(".ridetitle").get(0)).closest(".row");
                $($(data)).insertBefore($rowParent);
                // re-attach handlers to new ajax content
                $(".add-comment-button", $($(".ridetitle").get(0)).closest(".row"))
                    .click(addCommentButtonHandler)
                    .addClass("handler-registered");
            },
            error: function (err) {
                console.log("Error during ajax " + err);
            }
        });
    });

    var addCommentButtonHandler = function (evt) {
        $parentForm = $(this).parents(".comment_form");
        $.ajax({
            url: $parentForm.attr("action"), //this is the submit URL
            type: $parentForm.attr("method"), //or POST
            data: $parentForm.serialize(),
            parentForm: $parentForm,
            success: function (data) {
                var comment = JSON.parse(data);

                $commentsParent = this.parentForm.parents(".ride-comments");
                $commentsParent.find(".comment-row").append($("<dt>").text(comment.user.fullName));
                $commentsParent.find(".comment-row").append($("<dd>").text(comment.comment));
                $commentsParent.find("input[name='comment']").val("");
            },
            error: function (err) {
                console.log("Error during ajax " + err);
            }
        });

        evt.preventDefault();
        evt.stopPropagation();
    };

    var keyupCommentHandler = function(event){
        if(event.keyCode == 13){
            $(this).parents(".ride-comments").find(".add-comment-button").click();
        }
    };

    $(".add-comment-button").click(addCommentButtonHandler).addClass("handler-registered");
    $(".ride-comments input[type='text']").keyup(keyupCommentHandler);

    $("#newRideModal").modal({
        show: false
    }).on("shown.bs.modal", function () {
        var map_options = {
            center: new google.maps.LatLng(currentLocation.lat, currentLocation.lon),
            zoom: 11,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            disableDoubleClickZoom: true,
            streetViewControl: false,
        };

        var src_map = new google.maps.Map(document.getElementById("src_map_canvas"), map_options);
        var dest_map = new google.maps.Map(document.getElementById("dest_map_canvas"), map_options);

        var defaultBounds = new google.maps.LatLngBounds(
            new google.maps.LatLng(Math.floor(currentLocation.lat), Math.floor(currentLocation.lon)),
            new google.maps.LatLng(Math.ceil(currentLocation.lat), Math.ceil(currentLocation.lon))
        );

        var src_input = document.getElementById("ridesrc");
        var src_autocomplete = new google.maps.places.Autocomplete(src_input);
        src_autocomplete.bindTo("bounds", src_map);

        var src_marker = new google.maps.Marker({map: src_map});

        google.maps.event.addListener(src_autocomplete, "place_changed", function () {
            var place = src_autocomplete.getPlace();
            console.log(place);
            if (place.geometry.viewport) {
                src_map.fitBounds(place.geometry.viewport);
            } else {
                src_map.setCenter(place.geometry.location);
                src_map.setZoom(15);
            }

            src_marker.setPosition(place.geometry.location);
            console.log(place.geometry.location);
            $("#ridesrc_coords").val(place.geometry.location.lat() + "," + place.geometry.location.lng());
        });

        google.maps.event.addListener(src_map, "click", function (event) {
            src_marker.setPosition(event.latLng);
            src_map.panTo(event.latLng)
            $("#ridesrc_coords").val(event.latLng.lat() + "," + event.latLng.lng());
        });


        var dest_input = document.getElementById("ridedest");
        var dest_autocomplete = new google.maps.places.Autocomplete(dest_input);
        dest_autocomplete.bindTo("bounds", dest_map);

        var dest_marker = new google.maps.Marker({map: dest_map});

        google.maps.event.addListener(dest_autocomplete, "place_changed", function () {
            var place = dest_autocomplete.getPlace();

            if (place.geometry.viewport) {
                dest_map.fitBounds(place.geometry.viewport);
            } else {
                dest_map.setCenter(place.geometry.location);
                dest_map.setZoom(15);
            }

            dest_marker.setPosition(place.geometry.location);
            console.log(place.geometry.location);
            $("#ridedest_coords").val(place.geometry.location.lat() + "," + place.geometry.location.lng());
        });
        //center_changed
        google.maps.event.addListener(dest_map, "click", function (event) {
            dest_marker.setPosition(event.latLng);
            dest_map.panTo(event.latLng);
            $("#ridedest_coords").val(event.latLng.lat() + "," + event.latLng.lng());
        });
    });

    rightoolboxtop = $('.righttoolbox').offset().top;
    $(document).scroll(function(){
        $('.righttoolbox').css('position','');
        rightoolboxtop = $('.righttoolbox').offset().top;
        $('.righttoolbox').css('position','absolute');
        setTimeout(function () {
            if($(document).scrollTop() < 5){
                rightoolboxtop = 0;
            }
            $('.righttoolbox').css('top',Math.max(rightoolboxtop,$(document).scrollTop()));
        }, 100);

        displayMoreRides();
    });

    var busyLoadingRides = false;
    var currentRidesPage = 1;
    function displayMoreRides(){
        if(busyLoadingRides)
            return;

        if($(window).scrollTop() + $(window).height() >= $(document).height()){
            busyLoadingRides = true;
            currentRidesPage = currentRidesPage + 1;
            console.log("request ajax page " + currentRidesPage);
            $.ajax({
                url: "trips", //this is the submit URL
                type: "GET",
                data: {page: currentRidesPage, format: "ajax"},
                success: function (data) {
                    data = $.trim(data);
                    if(data.length < 1){ // empty response
                        currentRidesPage -= 1;
                        busyLoadingRides = false;
                        return;
                    }

                    $("#rides_list_col").append($(data));
                    $(".add-comment-button").slice(-5)
                        .not(".handler-registered")
                        .click(addCommentButtonHandler)
                        .addClass("handler-registered")
                        .parents(".ride-comments")
                        .find("input[type='text']")
                        .keyup(keyupCommentHandler);

                    busyLoadingRides = false;
                },
                error: function (err) {
                    console.log("Error during ajax " + err);
                    currentRidesPage -= 1; // to retry again
                    busyLoadingRides = false;
                }
            });
        }

    }
});

var rightoolboxtop = 0;