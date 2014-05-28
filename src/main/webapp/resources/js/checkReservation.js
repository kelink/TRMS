window.onload=function(){
	$("#search2").bind("mouseover",function(){
		$(this).css("background-color","rgb(238, 238, 238)");
	});
	$("#search2").bind("mouseout",function(){
		$(this).css("background-color","");
	});
	$("#search3").bind("mouseover",function(){
		$(this).css("background-color","rgb(238, 238, 238)");
	});
	$("#search3").bind("mouseout",function(){
		$(this).css("background-color","");
	});
	
	
	$("#search1").click(function(){
		$("#search1").unbind("mouseover mouseout");
		$("#search1").css("background-color","");
		$("#search2").bind("mouseover",function(){
			$(this).css("background-color","rgb(238, 238, 238)");
		});
		$("#search2").bind("mouseout",function(){
			$(this).css("background-color","");
		});
		$("#search3").bind("mouseover",function(){
			$(this).css("background-color","rgb(238, 238, 238)");
		});
		$("#search3").bind("mouseout",function(){
			$(this).css("background-color","");
		});
		
		$(this).attr("class","checkMethod selected");
		$("#search2").attr("class","checkMethod");
		$("#search3").attr("class","checkMethod");
		$("#searchDivWrapper").animate({height:"47px"},{duration:400,complete:function(){
			$(".vague").css("display","none");
			$(".preciseSearch").css("display","none");
		}});
		
		
		
		
		
	});
	$("#search2").click(function(){
		$("#search2").unbind("mouseover mouseout");
		$("#search2").css("background-color","");
		$("#search1").bind("mouseover",function(){
			$(this).css("background-color","rgb(238, 238, 238)");
		});
		$("#search1").bind("mouseout",function(){
			$(this).css("background-color","");
		});
		$("#search3").bind("mouseover",function(){
			$(this).css("background-color","rgb(238, 238, 238)");
		});
		$("#search3").bind("mouseout",function(){
			$(this).css("background-color","");
		});
		$(this).attr("class","checkMethod selected1");
		$("#search1").attr("class","checkMethod");
		$("#search3").attr("class","checkMethod");
		
		
		
		
		$("#searchDivWrapper").animate({height:"188px"},{duration:400,complete:function(){
			$(".vague").css("display","block");
			$(".preciseSearch").css("display","none");
		}});
	   
	});
	
	$("#search3").click(function(){
		$("#search3").unbind("mouseover mouseout");
		$("#search3").css("background-color","");
		$("#search1").bind("mouseover",function(){
			$(this).css("background-color","rgb(238, 238, 238)");
		});
		$("#search1").bind("mouseout",function(){
			$(this).css("background-color","");
		});
		$("#search2").bind("mouseover",function(){
			$(this).css("background-color","rgb(238, 238, 238)");
		});
		$("#search2").bind("mouseout",function(){
			$(this).css("background-color","");
		});
		$(this).attr("class","checkMethod selected1");
		$("#search1").attr("class","checkMethod");
		$("#search2").attr("class","checkMethod");
		
		
		
		
		$("#searchDivWrapper").animate({height:"390px"},{duration:400,complete:function(){
			$(".preciseSearch").css("display","block");
			$(".vague").css("display","none");
		}});
	   
	});
	

	for(var i=1;i<=4;i++)
	{
		$("#searchInput"+i).bind("input propertychange",function(){
			if($(this).val()=="")
				{
				    $(this).css("background","transparent");
				}
			else
				$(this).css("background","white");
				
		});
		
	}
	
	
	
	
};