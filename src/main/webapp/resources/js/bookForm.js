window.onload=function(){
	
	$("#close").mouseover(function(){
		$("#closeIcon").attr("src","/trms/resources/images/closeDeep.png");
	});
	$("#close").mouseout(function(){
		$("#closeIcon").attr("src","/trms/resources/images/closeShallow.png");
	});

};

function back(id)
{
	var a=window.top.document.getElementById("calArea"+id);
	var b=window.top.document.getElementById("cover");
	window.top.document.getElementById("iframe"+id).setAttribute("src","/trms/room/calendar?room_ID="+id);
    a.setAttribute("class","calArea");
    b.setAttribute("class","");
    
}