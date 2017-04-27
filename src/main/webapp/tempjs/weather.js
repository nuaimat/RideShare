/**
 * Created by zaid on 4/26/2017.
 */

$(document).ready((function (){
    var city = $('#weathercity').val();

    var appid = "b1b15e88fa797225412429c1c50c122a1";
    if ((city != null)) {
        $.ajax({

            url: "http://openweathermap.org/data/2.5/weather?q=" + city + "&units=metric" + "&appid=" + appid + "",
            type: "GET",
            dataType: "jsonp",
            success: function (data) {
                //TO DO replace the retrieved data
                $("#currentweather").innerHTML = "display text on success"

                console.log("returned current map of user's city")
                console.log(data)
            }


        });

    }
    else {
        console.log("error on weather map load main.js")
    }

}));



//TO DO integrate with google map merge to main scripts
