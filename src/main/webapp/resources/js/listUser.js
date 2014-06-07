window.onload=function(){
	$(".close").mouseover(function(){
		$(".closeIcon").attr("src","/trms/resources/images/closeDeep.png");
	});
	$(".close").mouseout(function(){
		$(".closeIcon").attr("src","/trms/resources/images/closeShallow.png");
	});
	$("#closeShow").click(function(){
		$("#showPanel").css("display","none");
	});
	$("#closeShow1").click(function(){
		$("#showPanel1").css("display","none");
	});
	
	$("#sortByDepartmentBtn").click(function(){
		if($("#showPanel").css("display")=="none")
		$("#showPanel").css("display","block");
		else
			$("#showPanel").css("display","none");
		$("#sortPanel").css("display","none");
	});
	$("#addUserBtn").click(function(){
		if($("#showPanel1").css("display")=="none")
		$("#showPanel1").css("display","block");
		else
			$("#showPanel1").css("display","none");
		$("#sortPanel1").css("display","none");
	});
	
	$(".sortPanelItem").mouseover(function(){
		$(this).css("background","#4183c4");
		$(this).css("color","white");
	});
	$(".sortPanelItem").mouseout(function(){
		$(this).css("background","white");
		$(this).css("color","rgb(105, 105, 105)");
	});
	$(".userListItem").mouseover(function(){
		$(this).css("background","#f5f9fc");
		
	});
	$(".userListItem").mouseout(function(){
		$(this).css("background","white");
		
	});
};