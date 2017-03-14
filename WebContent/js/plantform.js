/**
 * 
 */

window.onload = getIn;


$(document).ready(function(){
	

	$("#addroom").click(function(){
		var msg = {
			action:"create"	,
			playerid:userid,
		};
		sendMessage(JSON.stringify(msg));	
	});
	
	$("#getplantbtn").click(function(){
		
		var msg = {
			action : "getplant",
			playerid : userid,
		}; 

		sendMessage(JSON.stringify(msg));
		
	});
	
	$("#getroombtn").click(function(){
		
			var msg = {
				action : "loadroom",
				playerid : userid,
			}; 
			sendMessage(JSON.stringify(msg));
		});
});

function getIn()
{
	userid = $("#userid").text();
	var msg = {
			action : "getplant",
			playerid : userid,
		}; 
	sendMessage(JSON.stringify(msg));
	msg = {
			action : "loadroom",
			playerid : userid,
		}; 
	sendMessage(JSON.stringify(msg));
}


function addRoomCallBack(roomid){
	
	var roomBoard = document.getElementById("roomboard");
	var roomDiv = document.createElement("div");
	roomDiv.setAttribute("id",roomid);
	roomDiv.setAttribute("class", "room");
	roomBoard.appendChild(roomDiv);
	
	var playerA = document.createElement("span");
	playerA.innerHTML = "Player A: " + '<a id="player">null</a> <br>' ;
	roomDiv.appendChild(playerA);
	
	var playerB = document.createElement("span");
	playerB.innerHTML = "Player B: " + '<a id="player">null</a> <br>';
	roomDiv.appendChild(playerB);
	
	var removeRoom = document.createElement("span");
    removeRoom.setAttribute("class", "removeRoom");
    removeRoom.innerHTML = "<a href=\"#\" OnClick=removeRoom(event," +roomid+")>Remove Room</a><br>";
    roomDiv.appendChild(removeRoom);
    
    var roomidcon = document.createElement("span");
    roomidcon.innerHTML = "Room ID: " + roomid + "<br>";
    roomidcon.setAttribute("class","roomid");
    //roomidcon.style.visibility='hidden';
    roomDiv.appendChild(roomidcon);
    
    var quitRoom = document.createElement("span");
    quitRoom.innerHTML = "<a href=\"#\" OnClick=quitRoom(event,"+roomid+","+userid+")>Quit</a><br>";
    roomDiv.appendChild(quitRoom);
    
    $("#"+roomid).click(function(){
    	addPlayer(this.id,userid);
	});
}

function addPlayer(roomid,playerid){
	var msg = {
			action : "getroom",
			roomid : roomid,
			playerid : playerid,
	};
	sendMessage(JSON.stringify(msg));
}

function addPlayerCallBack(roomid,playerid,index){
	
	if(index <0 ){
		if(index == -1){
			alert("room full");
			return;
		}
		if(index == -2){
			alert("You allready in room now");
			return;
		}
		
	} 
	else {

		child = $("#"+roomid)[0].children;
		for(var i=0;i<child.length;i++){
			if(child[i].firstElementChild.id=="player"){
				if(child[i].firstElementChild.innerHTML=="null" && playerid!="null"){
					child[i].firstElementChild.innerHTML = "ID: "+playerid;
					return;
				}
			}
		}
	}		
}

function removeRoom(event,roomid){
	
	event.stopPropagation();
	var msg = {
			action : "removeroom",
			roomid : roomid,
	}; 
	sendMessage(JSON.stringify(msg));
}

function removeRoomCallBack(roomid){
	document.getElementById(roomid).remove();
}

function quitRoom(event,roomid,playerid){
	
	event.stopPropagation();
	var msg = {
			action : "quitroom",
			roomid : roomid,
			playerid : playerid,
	}; 
	sendMessage(JSON.stringify(msg));
}

function quitRoomCallBack(roomid,playerid){
	
	child = $("#"+roomid)[0].children;
	for(var i=0;i<child.length;i++){
		if(child[i].firstElementChild.id=="player"){
			if(child[i].firstElementChild.innerHTML== ("ID: "+playerid)){
				child[i].firstElementChild.innerHTML = "null";
				return;
			}
		}
	}
}



function loadRoomCallBack(json){
	var length = json.length;
	var roomids = new Array();
	var players = new Array();
	var player = new Array();
	roomids = json.rooms.split(";");
	players = json.players.split(";");
	
	for(var i=0;i<length;i++){
		addRoomCallBack(roomids[i]);
		player = players[i].split(" ");
		if(player[1]!=null){
			addPlayerCallBack(player[0],player[1],1);
			if(player[2]!=null){
				addPlayerCallBack(player[0],player[2],2);
			}
		}
		
		
	}
}





