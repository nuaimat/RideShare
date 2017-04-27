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
    getWeather(lat, long);
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
                    .addClass("handler-registered")
                    .parents(".panel-footer").find(".like_post").click(likeEventHandler);
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

    var likeEventHandler = function (evt) {
        evt.stopPropagation();
        evt.preventDefault();
        $parentForm = $(this).parents(".panel-footer").find(".comment_form");

        $.ajax({
            url: "/likes", //this is the submit URL
            type: "POST", //or POST
            data: {postid: $parentForm.find("input[name='postid']").val(), action: "like" },
            likeLink: $(this),
            success: function (data) {
                console.log(data);
                var like = data;
                if(data == null || data.result <= 0){
                    return;
                }
                console.log(this.likeLink);
                $likeLink = this.likeLink;
                var oldText = $likeLink.parents(".ride-likes").find("span.text-primary").text();
                $likeLink.parents(".ride-likes").find("span.text-primary").text(parseInt(oldText) + 1 + " Users");
                $likeLink.html("Unlike <span class=\"glyphicon glyphicon-thumbs-down\"></span>");
                $likeLink.unbind( "click" );
                $likeLink.click(dislikeEventHandler);


            },
            error: function (err) {
                console.log("Error during ajax " + err);
            }
        });
    };

    var dislikeEventHandler = function (evt) {
        evt.stopPropagation();
        evt.preventDefault();
        $parentForm = $(this).parents(".panel-footer").find(".comment_form");

        $.ajax({
            url: "/likes", //this is the submit URL
            type: "POST", //or POST
            data: {postid: $parentForm.find("input[name='postid']").val(), action: "dislike" },
            likeLink: $(this),
            success: function (data) {
                console.log(data);
                var like = data;
                if(data == null || data.result <= 0){
                    return;
                }
                console.log(this.likeLink);
                $likeLink = this.likeLink;
                var oldText = $likeLink.parents(".ride-likes").find("span.text-primary").text();
                $likeLink.parents(".ride-likes").find("span.text-primary").text(parseInt(oldText) - 1 + " Users");
                $likeLink.html("Like <span class=\"glyphicon glyphicon-thumbs-up\"></span>");
                $likeLink.unbind( "click" );
                $likeLink.click(likeEventHandler);


            },
            error: function (err) {
                console.log("Error during ajax " + err);
            }
        });
    };

    $(".like_post").click(likeEventHandler);
    $(".unlike_post").click(dislikeEventHandler);

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
                        .keyup(keyupCommentHandler)
                        .parents(".panel-footer")
                        .find(".like_post")
                        .click(likeEventHandler)
                        .parents(".panel-footer")
                        .find(".unlike_post")
                        .click(dislikeEventHandler);

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


    $("#fetch_new_rides").click(function (evt) {
        console.log("newRidesIdsQueue: " + newRidesIdsQueue.join(", "));
        evt.preventDefault();
        evt.stopPropagation();

        $("#inner-message").hide();

        $.ajax({
            url: $('#new-ride-form').attr("action"), //this is the submit URL
            type: "GET", //or POST
            data: {"format": "ajax_specific_ids", "ids": newRidesIdsQueue.reverse().join(",")},
            success: function (data) {
                $("html, body").animate({ scrollTop: 0 }, "fast");

                $rowParent = $($(".ridetitle").get(0)).closest(".row");
                $($(data)).insertBefore($rowParent);
                // re-attach handlers to new ajax content
                $(".add-comment-button")
                    .not(".handler-registered")
                    .click(addCommentButtonHandler)
                    .addClass("handler-registered")
                    .parents(".panel-footer").find(".like_post").click(likeEventHandler);

                newRidesIdsQueue.length = 0; // remove all pending ids
            },
            error: function (err) {
                console.log("Error during ajax " + err);
            }
        });

    })

    $("#inner-message").hide();
    $("[data-hide]").on("click", function(){
        $(this).closest("." + $(this).attr("data-hide")).hide();
    });

});

var rightoolboxtop = 0;
var APPID = "160f29f3d6c3a43f8fe9c3a2145786ec"; // openweathermap

// function to get weather for an address
function getWeather(latitude,longitude) {
    if(latitude != '' && longitude != '') {
        $("#weather").val("Retrieving weather...");										// write temporary response while we get the weather
        $.getJSON( "http://api.openweathermap.org/data/2.5/weather?&APPID="+APPID+"&lat="+latitude+"&lon="+longitude+"&units=imperial", function(data) {	// add '&units=imperial' to get U.S. measurements
            var currWeather					= new Array();								// create array to hold our weather response data
            currWeather['currTemp']			= Math.round(data.main.temp);				// current temperature
            currWeather['highTemp']			= Math.round(data.main.temp_max);			// today's high temp
            currWeather['lowTemp']			= Math.round(data.main.temp_min);			// today's low temp
            currWeather['humidity']			= Math.round(data.main.humidity);			// humidity (in percent)
            currWeather['pressure']			= data.main.pressure * 0.02961339710085;	// barometric pressure (converting hPa to inches)
            currWeather['pressure']			= currWeather['pressure'].toFixed(2);		// barometric pressure (rounded to 2 decimals)

            currWeather['description']		= data.weather[0].description;				// short text description (ie. rain, sunny, etc.)
            currWeather['icon']				= "http://openweathermap.org/img/w/"+data.weather[0].icon+".png";	// 50x50 pixel png icon
            currWeather['cloudiness']		= data.clouds.all;							// cloud cover (in percent)
            currWeather['windSpeed']		= Math.round(data.wind.speed);				// wind speed

            currWeather['windDegree']		= data.wind.deg;							// wind direction (in degrees)
            currWeather['windCompass']		= Math.round((currWeather['windDegree'] -11.25) / 22.5);	// wind direction (compass value)

            // array of direction (compass) names
            var windNames					= new Array("North","North Northeast","Northeast","East Northeast","East","East Southeast", "Southeast", "South Southeast","South","South Southwest","Southwest","West Southwest","West","West Northwest","Northwest","North Northwest");
            // array of abbreviated (compass) names
            var windShortNames				= new Array("N","NNE","NE","ENE","E","ESE", "SE", "SSE","S","SSW","SW","WSW","W","WNW","NW","NNW");
            currWeather['windDirection']	= windNames[currWeather['windCompass']];	// convert degrees and find wind direction name


            var response 		= "<img src='"+currWeather['icon']+"' ><br />Current Weather: "+currWeather['currTemp']+"\xB0 and "+currWeather['description'];
            var spokenResponse	= "It is currently "+currWeather['currTemp']+" degrees and "+currWeather['description'];

            if(currWeather['windSpeed']>0) {											// if there's wind, add a wind description to the response
                response		= response+" with winds out of the "+windNames[currWeather['windCompass']]+" at "+currWeather['windSpeed'];
                spokenResponse	= spokenResponse+" with winds out of the "+windNames[currWeather['windCompass']]+" at "+currWeather['windSpeed'];
                if(currWeather['windSpeed']==1) {
                    response		+= " mile per hour";
                    spokenResponse	+= " mile per hour";
                } else {
                    response		+= " miles per hour";
                    spokenResponse	+= " miles per hour";
                }
            }

            console.log(data);												// log weather data for reference (json format)
            $("#weather").html(response);									// write current weather to textarea
            //speakText(spokenResponse);
        });
    } else {
        return false;														// respond w/error if no address entered
    }
}