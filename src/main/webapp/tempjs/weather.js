/**
 * Created by zaid on 4/26/2017.
 */

$(function (){
    var city = $('#weathercity').val();
    var map;
    var latd;
    var long;
    var appid = "92a0262e4445c5e338a249da33eb0d11";
    if ((city != null)) {
        $.ajax({

            url: "http://openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + appid +"" ,
            type: "GET",
            dataType: "jsonp",
            success: function (data) {

                latd=data.coord.latitude;
                long=data.coord.longitude;
                console.log(data)

            }


        });

    }
    else {
        console.log("error on weather map load main.js")
    }

    function initMap() {
        map = new google.maps.Map(document.getElementById('currentweather'), {
            center: {lat: latd, lng: long},
            zoom: 8

        });
    }

});







//TO DO integrate with google map merge to main scripts
