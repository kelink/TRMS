

function choose(){
	var departments=document.getElementById("departments");
	var name=window.parent.name;
	
	
		for(var i=0;i<departments.options.length;i++)
		{
		    if(departments.options[i].value==name)
		    {
		    	departments.options[i].selected=true;
		    }
		}

		 window.parent.name="";
	
	    $("#departments").trigger("change");
	
	
	
	
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
	$("#addTeamBtn").click(function(){
		if($("#showPanel2").css("display")=="none")
		$("#showPanel2").css("display","block");
		else
			$("#showPanel2").css("display","none");
		$("#sortPanel2").css("display","none");
	});
}