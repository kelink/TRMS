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
	$("#closeShow2").click(function(){
		$("#showPanel2").css("display","none");
	});
	$("#closeShow6").click(function(){
		$("#showPanel6").css("display","none");
	});
	
	

	$("#addTeamBtn").click(function(){
		if($("#showPanel2").css("display")=="none")
		$("#showPanel2").css("display","block");
		else
			$("#showPanel2").css("display","none");
		$("#sortPanel2").css("display","none");
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
	

		  $("#departments").change(function(){
			 
			var department_ID=$("#departments").val();

		    getDepartmentAllUser(department_ID);
		    getDepartmentAllteam(department_ID);

		
		    
		  });
	
	
	
	
	
};

//
// function edit(account,access,gender,tele,password,dept_ID,user_ID)
// {
// for(var i=1;i<=6;i++)
// {
// if(i==1){document.getElementById("1").value=account;}
// else if(i==2){
// var a=document.getElementById("2");
// for(k=0;k<a.options.length;k++)
// {
// if(a.options[k].value==access)
// {
// a.options[k].selected=true;
// }
// }
// }
// else if(i==3){
// var a=document.getElementById("3");
// for(k=0;k<a.options.length;k++)
// {
// if(a.options[k].value==gender)
// {
// a.options[k].selected=true;
// }
// }
// }
// else if(i==4){document.getElementById("4").value=tele;}
// else if(i==5){document.getElementById("5").value=password;}
// else if(i==6){
// var a=document.getElementById("6");
// for(k=0;k<a.options.length;k++)
// {
// if(a.options[k].value==dept_ID)
// {
// a.options[k].selected=true;
// }
// }
// }
//		
// }
//	
//	
// if($("#showPanel6").css("display")=="none")
// $("#showPanel6").css("display","block");
// else
// $("#showPanel6").css("display","none");
// $("#sortPanel6").css("display","none");
// }


function checkSubmit(team_ID){
	// 判断当前team 是否存在
	var team_ID=$("#teams").val();
	$.ajax({
		url : "/trms/team/isTeamManaged",
		type : "Get",
		data : "team_ID=" + team_ID,
		dataType : "html",
		success : function(json) {
			if(json!=""){
				if(confirm(json))
				{
				   return true;	
			    }else{
				  return false;
			  	}
			}else{
				return 
			}
			
       },
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
			return false;
		}
	});	
}
function getDepartmentAllUser(department_ID){
	$.ajax({
			url : "/trms/team/getDepartmentAllCommontUser",
			type : "Get",
			data : "department_ID=" + department_ID,
			dataType : "json",
			success : function(json) {
				if(json=="")
					$("#users").empty();
				html="<option value=''></option>";
				
				for (position in json) {				
						var user_ID=json[position].user_ID;
						var department_ID=json[position].department_ID;
						var account=json[position].account;
						// 添加元素，使得option可以选择
						html+="<option value=\""+user_ID+"\">"+account+"</option>"
				}		
				$("#users").html(html);
           },
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				
			}
		});
}
function getDepartmentAllteam(department_ID){
	$.ajax({
		url : "/trms/team/getDepartmentAllteam",
		type : "Get",
		data : "department_ID=" + department_ID,
		dataType : "json",
		success : function(json) {
			html="<option value=''></option>";
			for (position in json) {				
					var team_ID=json[position].team_ID;
					var department_ID=json[position].department_ID;
					var teamName=json[position].teamName;
					// 添加元素，使得option可以选择
					html+="<option value=\""+team_ID+"\">"+teamName+"</option>";
			}			
			$("#teams").html(html);
       },
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
		}
	});
}