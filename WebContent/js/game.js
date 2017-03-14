/**
 * 
 */
var x1,y1,x2,y2;
var step=0;
var myStep=0;

$(document).ready(function(){
	
	userid = $("#userid").text();
	$("#gameboard").append('<table id="gametable"></table>');
	var table = document.getElementById("gametable");
	var tr=new Array()
	for(var i=0;i<8;i++) 
		tr[i] = table.insertRow(i);

	for(var i=0;i<8;i++) {
		tr[0].insertCell(i).innerHTML = trans_int_char(i);
	}
	
	
	for(var i=1;i<8;i++) {
		for(var j=0;j<8;j++) {
			if(j==0)
				tr[i].insertCell(j).innerHTML = i;
			else
				tr[i].insertCell(j);
		}		
	}
	
	$("#gametable td").click(function(){
		
		if(myStep==1 && checkValid(this.parentElement.rowIndex,this.cellIndex)) {

			if(step==0){
				y1 = this.cellIndex;
				x1 = this.parentElement.rowIndex;
				if(x1>0&&y1>0){
					setColor(x1,y1,"DeepSkyBlue");
					step = 1;
				}
			}
			else {
				var col = this.cellIndex;
				var row = this.parentElement.rowIndex;
				if( (Math.abs(y1-col)==1 && x1==row) || (Math.abs(x1-row) && y1==col)) {
					if(col>0&&row>0){ 
						y2 = col;
						x2 = row;
						step = 0;
						myStep=0;
						setColor(x2,y2,"DeepSkyBlue");
						sendStep(x1,y1,x2,y2);
					 }
				}
				else{
					clearColor(x1,y1);
					y1 = this.cellIndex;
					x1 = this.parentElement.rowIndex;
					setColor(x1,y1,"DeepSkyBlue");
				}
			}
		}
		
	});
	
	$("#gametable td").dblclick(function(){
		if(myStep==1 && step==1){
			clearColor(x1,y1);
			step=0;
		}
	});
	
	$("#startbtn").click(function(){
		
		var msg = {}; 
		msg["action"] = "start";
		msg["playerid"] = userid;

		sendMessage(JSON.stringify(msg));
	});
	
	
	
	
});


function trans_int_char(i) {
	var char;
	switch(i) {
		case 1: char = "A";
			break;
		case 2: char = "B";
			break;
		case 3: char = "C";
			break;
		case 4: char = "D";
			break;
		case 5: char = "E";
			break;
		case 6: char = "F";
			break;
		case 7: char = "G";
			break;
		default: 
			char = "";
			break;
	}
	return char;
}

function setColor(x,y,color) {
	var cell = $("#gametable");	
	var td = cell[0].children[0].children[x].children[y];
	td.style.backgroundColor = color;
}

function clearColor(x,y) {
	var cell = $("#gametable");	
	var td = cell[0].children[0].children[x].children[y];
	td.style.backgroundColor = "";
}

function checkValid(x,y) {
	var cell = $("#gametable");	
	var td = cell[0].children[0].children[x].children[y];
	if(td.style.backgroundColor == "")
		return 1;
	else
		return 0;
}


function sendStep(a1,b1,a2,b2) {

	var msg = {}; 
	msg["action"] = "step";
	msg["playerid"] = userid;
	msg["step"] =a1+""+b1+""+a2+""+b2;
	
	sendMessage(JSON.stringify(msg));
}





