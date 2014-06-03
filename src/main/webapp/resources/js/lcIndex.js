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
		$(ifr).attr("src", "/trms/room/calendar?room_ID=" + id);
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
function turnback(id)
{

		var a=window.top.document.getElementById("floatTicket");
		var b=window.top.document.getElementById("cover");
		window.top.document.getElementById("iframe"+id).setAttribute("src","/trms/room/calendar?room_ID="+id);
	    a.setAttribute("class","");
	    b.setAttribute("class","");
	    
	
    
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