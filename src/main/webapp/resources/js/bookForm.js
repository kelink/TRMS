

function back(id)
{
	var a=window.top.document.getElementById("floatTicket");
	var b=window.top.document.getElementById("cover");
	window.top.document.getElementById("iframe"+id).setAttribute("src","/trms/room/calendar?room_ID="+id);
    a.setAttribute("class","");
    b.setAttribute("class","");
    
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
	
}