

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
				
	});
		
	

	
};