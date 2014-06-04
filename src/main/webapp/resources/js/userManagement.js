

function logout(url){
	$("#logoutBtn").attr("class","btnLogout btnSelected");
	window.location.href=url;
}

function boot()
{
	$.ajax({
	url : "/trms/team/getBoot",
	type : "Get",
	dataType : "json",
	success : function(json) {
//		var html="";
//		      for(var i=0;i<json.result.length;i++)
//		    	  {
//		    	      var departmentName=json.result[i].departmentName;
//			          var lcAccount=json.result[i].lcAccount;
//			          var tele=json.result[i].tele;
//			          var team1=json.result[i].teams[0];
//			          var team2=json.result[i].teams[1];
//			          html+=departmentName+lcAccount+tele+team1+team2+"/";
//		    	  }
//		      
//              $("#userDisplay").html(html);
		
		      
		      //输出department导航需要的变量
              var department="";
              var repeat="";
              var temp="";
              count=0;
              //输出department导航栏
              for(var i=0;i<json.result.length;i++)
              {
            	  temp=json.result[i].departmentName;
            	  if(repeat==temp)
            	  {
            		  continue;
            	  }
            	   department+="<div class=\"checkMethod\" id=\""+count+"\">";
            	   department+=temp;
            	   repeat=temp;
            	   department+="</div>";
            	   count++;
            	   
              }
              
              //输出department对应user需要的变量
		      var userInfo="";
		      var repeat1="";
              var temp1="";
              var count1=0;
              var turn=false;
              var end=true;
              //输出department对应的user
              for(var i=0;i<json.result.length;i++)
              {
            	  temp1=json.result[i].departmentName;
            	  if(repeat1==temp1)
            		  {
            		      userInfo+="<div>"+json.result[i].departmentName+json.result[i].lcAccount+json.result[i].tele+json.result[i].teams[0]+json.result[i].teams[1]+"</div>";
            		      turn=true;
            		      continue;
            		  }
            	  if(turn==true)
            		  {
            		  userInfo+="</div>";
            		  turn=false;
            		  end=false;
            		  }
            	  if(count1>0&&end==true)
            		  {
            		  userInfo+="</div>";
            		  }
            	  end=true;
            	  userInfo+="<div id=\"displayArea"+count1+"\">";
            	  userInfo+="<div>"+json.result[i].departmentName+json.result[i].lcAccount+json.result[i].tele+json.result[i].teams[0]+json.result[i].teams[1]+"</div>";
            	  repeat1=temp1;
            	  count1++;
              }
            	  

              $("#departmentWrapper").html(department);
              $("#displayArea").html(userInfo);
              //为左侧department导航注册事件
              
              for(var i=0;i<count;i++)
              {
              	$("#"+i).bind("mouseover",function(){
              		$(this).css("background","rgb(238, 238, 238)");
              	});
              	$("#"+i).bind("mouseout",function(){
              		$(this).css("background","white");
              	});
              	
            
              	$("#"+i).bind("click",function(){
              	    $("#displayArea"+$(this).attr("id")).css("display","block");
              		$(this).unbind("mouseover mouseout");             	
              		$(this).attr("class","checkMethod selected");
              		$("#allDepartment").attr("class","checkMethod");
              		$("#allDepartment").css("background","white");
              		$("#allDepartment").bind("mouseover",function(){
                  		$(this).css("background-color","rgb(238, 238, 238)");
                  	});
              		$("#allDepartment").bind("mouseout",function(){
                  		$(this).css("background-color","white");
                    });
              		for(var k=0;k<count;k++)
              		{
              			
              			if(k!=$(this).attr("id"))
              			{
              				$("#displayArea"+k).css("display","none");
              				$("#"+k).bind("mouseover",function(){
                          		$(this).css("background-color","rgb(238, 238, 238)");
                          	});
              				$("#"+k).bind("mouseout",function(){
                          		$(this).css("background-color","white");
                          	});
              				$("#"+k).attr("class","checkMethod");
              				$("#"+k).css("background","white");

              			}

              		}
              	});
      
              
              }
              $("#allDepartment").bind("click",function(){
            	  for(var i=0;i<count;i++)
                  {
            		$("#displayArea"+i).css("display","block");
                  	$("#"+i).bind("mouseover",function(){
                  		$(this).css("background-color","rgb(238, 238, 238)");
                  	});
                  	$("#"+i).bind("mouseout",function(){
                  		$(this).css("background-color","white");
                  	});
                  	$("#"+i).attr("class","checkMethod");
                  	$("#"+i).css("background","white");
                  	
                  }
            	 
            	  $("#allDepartment").unbind("mouseover mouseout");
            	  $("#allDepartment").attr("class","checkMethod selected");
            	  
              });
              


     },
	error : function(XMLHttpRequest, textStatus, errorThrown) {
           alert('failed');
	}
});
}
var count=0;
window.onload=function(){
    boot();
    



    
	$("#logoutBtn").mouseover(function(){
		$(this).attr("class","btnLogout btnHover");
		
	});
	$("#logoutBtn").mouseout(function(){
		$(this).attr("class","btnLogout");
	});
	$("#logoutBtn").mousedown(function(){
		$(this).attr("class","btnLogout btnLogoutSelected");
	});


//	$("#userSearchInput").bind("input propertychange",function(){
//		if($(this).val()=="")
//			{
//			    $(this).css("background","transparent");
//			}
//		else
//			$(this).css("background","white");
//		
//		
//		var searchWord=$("#userSearchInput").val();
//		$.ajax({
//			url : "/trms/team/getDetail",
//			type : "Get",
//			data : {searchWord:searchWord },
//			dataType : "json",
//			success : function(json) {
//				var html="";
//				      for(var i=0;i<json.result.length;i++)
//				    	  {
//				    	      var departmentName=json.result[i].departmentName;
//					          var lcAccount=json.result[i].lcAccount;
//					          var tele=json.result[i].tele;
//					          var team1=json.result[i].teams[0];
//					          var team2=json.result[i].teams[1];
//					          html+=departmentName+lcAccount+tele+team1+team2+"/";
//				    	  }
//				      
//		              $("#userDisplay").html(html);
//		              
//
//
//
//	         },
//			error : function(XMLHttpRequest, textStatus, errorThrown) {
//	               alert('failed');
//			}
//		});
//				
//	});

		
};