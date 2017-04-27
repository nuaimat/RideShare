<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="/tempjs/weather.js" type="text/javascript"></script>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAWPR8ffZ_ojLIHXfLm96J6Se_wS1WkHDs"></script>

</head>
<body>
<h2>Hello Weather!</h2>
<input type="hidden" id="weathercity" name="weathercity" value="${user.city}"></input>
<div id="currentweather" style="height: 50%; width:50%; ">

</div>
<div id="forcast">
    place holder temp
</div>


</body>
</html>
