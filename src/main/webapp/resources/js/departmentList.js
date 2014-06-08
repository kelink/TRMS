

function check_all(obj,cName)
{
	var checkboxs = document.getElementsByName(cName); 
    for(var i=0;i<checkboxs.length;i++){checkboxs[i].checked = obj.checked;}
}
window.onload=function(){
	
	$(".close").mouseover(function(){
		$(".closeIcon").attr("src","/trms/resources/images/closeDeep.png");
	});
	$(".close").mouseout(function(){
		$(".closeIcon").attr("src","/trms/resources/images/closeShallow.png");
	});
	$("#closeShow2").click(function(){
		$("#showPanel2").css("display","none");
	});
	$("#addBtn").click(function(){
		if($("#showPanel2").css("display")=="none")
		$("#showPanel2").css("display","block");
		else
			$("#showPanel2").css("display","none");
		$("#sortPanel2").css("display","none");
	});
	
	
	
	
	$(".checkTeam").click(function(){
		
		$("#rightNav2",parent.document).css("border-right","4px solid rgb(255, 92, 0)");
		$("#rightNav4",parent.document).css("border-right","none");
	    window.parent.name=$(this).attr("id");
	});
    $(".checkRoom").click(function(){
		
		$("#rightNav3",parent.document).css("border-right","4px solid rgb(255, 92, 0)");
		$("#rightNav4",parent.document).css("border-right","none");
		window.parent.name=$(this).attr("id");
	});
};