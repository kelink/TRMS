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
	$("#logoutBtn").attr("class","btn btnSelected");
	window.location.href=url;
}
function turnback()
{
	var a=window.top.document.getElementById("calArea8");
	var b=window.top.document.getElementById("cover");
	window.top.document.getElementById("iframe8").setAttribute("src","/trms/room/calendar?room_ID=8");
    a.setAttribute("class","calArea");
    b.setAttribute("class","");
    
}
window.onload=function(){

	$("#logoutBtn").mouseover(function(){
		$(this).attr("class","btn btnHover");
		
	});
	$("#logoutBtn").mouseout(function(){
		$(this).attr("class","btn");
	});
	


	
};