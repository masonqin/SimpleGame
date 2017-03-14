
function ajaxAsyncRequest(reqURL)
{
	//Creating a new XMLHttpRequest object
    var xmlhttp;
    if (window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
    }
    //Create a asynchronous GET request
    xmlhttp.open("GET", reqURL, true);  
    //When readyState is 4 then get the server output
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4) { 
            if (xmlhttp.status == 200) 
            {
                alert(xmlhttp.responseText);
            } 
            else
            {
                alert('Something is wrong !!');
            }
        }
    };   
    xmlhttp.send(null);
}

$(document).ready(function(){
	
	$("#ajax_get").click(function(){
	  $.get("GetUsers",function(ret){
		  $("#usertable").empty(); 
		  $("#usertable").append(
	    			'<tr>'
	    			+'<th>First Name</th>'
	    			+'<th>Last Name</th>'
	    			+'<th>Email</th>'
	    			+'<th>Date</th>');
	      $.each(ret,function(index,user){
	    	
	    	  $("#usertable").append('<tr><td>' 
	      							+user.firstName
	      							+ '</td>' + '<td>' 
	      							+user.lastName
	      							+ '</td>' + '<td>'
	      							+user.email
	      							+ '</td>' + '<td>' 
	      							+user.dob 
	      							+ '</td></tr>' + '<br>');
	     });
	   
	  })
	$("#usertable").attr("frame","border");
	$("#usertable").attr("border","1");
	})

});



