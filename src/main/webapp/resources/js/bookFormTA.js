

function back(id)
{
	
	var a=document.getElementById("floatTicket");
	var b=document.getElementById("cover");
	document.getElementById("iframe"+id).setAttribute("src","/trms/room/calendarTA?room_ID="+id);
    a.setAttribute("class","");
    b.setAttribute("class","");
    $("#email,#tele,#purpose").val("");
    
}
//判断日期超过本月31日，保证start day 会小于end day ，同时不跨月
function isEmpty(){
	
	var department_ID=$("#departments").val();
	var team_ID=$("#teams").val();
	var begin_time=$("#begin_time").val();
	var end_time=$("#end_time").val();
	var email=$("#email").val();
	
	if(department_ID==""||team_ID==""||begin_time==""||end_time==""||email==""){
		alert(" Empty item ,please fill it");	
		return false;
		
	}
	else{
		var a=document.getElementById("floatTicket");
		var b=document.getElementById("cover");
		//document.getElementById("iframe"+id).setAttribute("src","/trms/room/calendarTA?room_ID="+id);
	    a.setAttribute("class","");
	    b.setAttribute("class","");

	    scrollTo(0,0);
		
		
	}
	
}

var expand = [];
function displayCal(id) {
	list = "#list" + id
	calArea = "#calArea" + id
	ifr = "#iframe" + id
    cal="#cal"+id
	if (typeof (expand[id]) == "undefined")
		expand[id] = false;

	if (expand[id] == false) {
		$(list).animate({
			height : "500"
		}, 400);
		$(calArea).css("display", "block");
		$(ifr).attr("src", "/trms/room/calendarTA?room_ID=" + id);
		$(cal).attr("class","btn btnCalendar btnSelected");
		expand[id] = true;
	} else if (expand[id] == true) {
		$(list).animate({
			height : "84"
		}, 400);
		$(calArea).css("display", "none");
		$(cal).attr("class","btn btnCalendar");
		expand[id] = false;
	}

}

function logout(url){
	$("#logoutBtn").attr("class","btnLogout btnSelected");
	window.location.href=url;
}

window.onload=function(){

	$("#logoutBtn").mouseover(function(){
		$(this).attr("class","btnLogout btnHover");
		
	});
	$("#logoutBtn").mouseout(function(){
		$(this).attr("class","btnLogout");
	});
	$("#logoutBtn").mousedown(function(){
		$(this).attr("class","btnLogout btnLogoutSelected");
	});


	$("#close").mouseover(function(){
		$("#closeIcon").attr("src","/trms/resources/images/closeDeep.png");
	});
	$("#close").mouseout(function(){
		$("#closeIcon").attr("src","/trms/resources/images/closeShallow.png");
	});
	
	
	
};