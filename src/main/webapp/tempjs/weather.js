$(function (){
    var city= "fairfield"
    var key ="b1b15e88fa797225412429c1c50c122a1";
    $.ajax({
            url:"http://openweathermap.org/data/2.5/weather?q=fairfield&appid=b1b15e88fa797225412429c1c50c122a1" , //this is the submit URL
            type:  'jsonp' , //or POST
            success: function (data) {
                console.log(data)
            },
            error: function (err) {
                console.log("Error during ajax " + err);
            }
        });


});