window.onload=function(){
	
	$("#close").mouseover(function(){
		$("#closeIcon").attr("src","/trms/resources/images/closeDeep.png");
	});
	$("#close").mouseout(function(){
		$("#closeIcon").attr("src","/trms/resources/images/closeShallow.png");
	});

};
function back()
{
	
	$(window.parent.document).find("#moreInfo").css("display","none");

	$(window.parent.document).find("#moreInfo").attr("src","#");
}
