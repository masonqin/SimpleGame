/**
 * 
 */


var websocket = null;
var userid;


	
if('WebSocket' in window){
	websocket = new WebSocket("ws://localhost:8080/SimpleGame/actions");
} else {
	alert("This browser do not support websocket");
}

websocket.onerror = function(){

};

websocket.onoppen = function(){

};

websocket.onmessage = function(event){
	
	var json = JSON.parse(event.data);
	
	switch(json.action){
	
	case "start":
		myStep=1;
		alert("you first");
		break;
	case "step":
		var str = new Array();
		var step = json.step;
		str = step.split("");
		setColor(str[0],str[1],"red");
		setColor(str[2],str[3],"red");
		myStep = 1;
		break;
	case "over":
		alert("You lost");
		break;
	case "win":
		alert("You win");
		break;
	case "create":
		addRoomCallBack(json.roomid);
		break;
	case "removeroom":
		removeRoomCallBack(json.roomid);
		break;
	case "setroom":
		addPlayerCallBack(json.roomid,json.playerid,json.index);
		break;
	case "quitroom":
		quitRoomCallBack(json.roomid,json.playerid);
	case "loadroom":
		loadRoomCallBack(json);
		break;
		
	default:
		break;
	
	}
};

websocket.onclose = function(){
	
};

window.onbeforeunload = function(){
	closews();
}

function closews(){
	websocket.close();
}

function sendMessage(msg){
	
	waitForConnection(function(){
		websocket.send(msg);
        if (typeof callback !== 'undefined') {
          callback();
        }
    }, 1000);
}

waitForConnection = function (callback, interval) {
    if (websocket.readyState === 1) {
        callback();
    } else {
    	var that = this;
        // optional: implement backoff for interval here
        setTimeout(function () {
            that.waitForConnection(callback, interval);
        }, interval);
    }
};









