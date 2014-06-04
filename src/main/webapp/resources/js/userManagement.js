

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


	$("#userSearchInput").bind("input propertychange",function(){
		if($(this).val()=="")
			{
			    $(this).css("background","transparent");
			}
		else
			$(this).css("background","white");
		
		
		var searchWord=$("#userSearchInput").val();
		$.ajax({
			url : "/trms/team/getDetail",
			type : "Get",
			data : {searchWord:searchWord },
			dataType : "json",
			success : function(json) {
				var html="";
				      for(var i=0;i<json.result.length;i++)
				    	  {
				    	      var departmentName=json.result[i].departmentName;
					          var lcAccount=json.result[i].lcAccount;
					          var tele=json.result[i].tele;
					          var team1=json.result[i].teams[0];
					          var team2=json.result[i].teams[1];
					          html+=departmentName+lcAccount+tele+team1+team2+"/";
				    	  }
				      
		              $("#userDisplay").html(html);
		              



	         },
			error : function(XMLHttpRequest, textStatus, errorThrown) {
	               alert('failed');
			}
		});
				
	});
		
	

	
};