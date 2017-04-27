<%--
  Created by IntelliJ IDEA.
  User: nuaimat
  Date: 4/26/17
  Time: 11:03 PM
  To change this template use File | Settings | File Templates.
--%>
<script type="text/javascript">
    var newRidesIdsQueue = [];
    startWebSocketConnection("//${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/websocketendpoint");

    function startWebSocketConnection(websocketServerLocation){
        var webSocket = new WebSocket(websocketServerLocation);
        webSocket.onopen = function(message){ wsOpen(message);};
        webSocket.onmessage = function(message){ wsGetMessage(message);};
        webSocket.onclose = function(message){ wsClose(message);};
        webSocket.onerror = function(message){ wsError(message);};
        function wsOpen(message){
            console.log("wsserver >> Connected ...");
        }

        function wsCloseConnection(){
            webSocket.close();
        }
        function wsGetMessage(message){
            console.log("wsserver >> " + message.data);
            var msg = JSON.parse(message.data);
            var newRideId = msg.ride;

            if($("#btn_" + newRideId).length == 0 && ${sessionScope.user.userid} != msg.userid){ // we don't have this ride div before
                newRidesIdsQueue.push(newRideId);
                $("#inner-message").fadeIn( "fast" );
            }
        }
        function wsClose(message){
            console.log("wsserver >> closed");
            setTimeout(function(){startWebSocketConnection(websocketServerLocation)}, 5000);
        }

        function wserror(message){
            console.log("wsserver >> Error ... " + message);
        }
    }

</script>