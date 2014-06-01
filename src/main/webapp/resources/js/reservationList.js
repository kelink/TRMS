
//
//function mouseon(e)
//{
//	$(e).css("background","rgb(240, 238, 238)");
//}
//function mouseout(e)
//{
//	$(e).css("background","white");
//}
//window.onload=function(){
//	$(".listItem").mouseover(function(){
//		$(this).css("background","rgb(240, 238, 238)");
//	});
//	
//}

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
	$("#closeSort").click(function(){
		$("#sortPanel").css("display","none");
	});
	
		$("#sortBtn").click(function(){
			if($("#sortPanel").css("display")=="none")
			$("#sortPanel").css("display","block");
			else
				$("#sortPanel").css("display","none");
			
			$("#showPanel").css("display","none");
		});
		$(".sortPanelItem").mouseover(function(){
			$(this).css("background","#4183c4");
			$(this).css("color","white");
		});
		$(".sortPanelItem").mouseout(function(){
			$(this).css("background","white");
			$(this).css("color","rgb(105, 105, 105)");
		});
		
		
		$("#showBtn").click(function(){
			if($("#showPanel").css("display")=="none")
			$("#showPanel").css("display","block");
			else
				$("#showPanel").css("display","none");
			$("#sortPanel").css("display","none");
		});
		$(".sortPanelItem").mouseover(function(){
			$(this).css("background","#4183c4");
			$(this).css("color","white");
		});
		$(".sortPanelItem").mouseout(function(){
			$(this).css("background","white");
			$(this).css("color","rgb(105, 105, 105)");
		});
	

};
function check_all(obj,cName)
{
	var checkboxs = document.getElementsByName(cName); 
    for(var i=0;i<checkboxs.length;i++){checkboxs[i].checked = obj.checked;}
}
function expand(id){
	
	var listItem="#listItem"+id;
	var listFoldBtn="#listFoldBtn"+id;
	var reservationInfo="#reservationInfo"+id;
	var listContentClass=".listContentClass"+id;
		$(listItem).animate({height:"400px"},{duration:400,complete:function(){
			$(listFoldBtn).css("display","block");
			$(reservationInfo).css("display","block");
			$(listContentClass).css("display","none");
            window.top.scrollTo(0,250);
            
		}});
   $(listItem).css("cursor","auto");
   $(listItem).removeAttr("onclick");
   $(listItem).unbind();//标签上的onclick事件只能通过jquery移除，不能通过jquery添加，jq的click事件跟标签的onclick不互通，jq重复设置click会叠加上去而不是取代。
    
}
function chose(event)
{
	if(event.stopPropagation) event.stopPropagation();
	else event.cancelBubble=true;
}
function more(event){
	$("#moreInfo").css("display","block");
	if(event.stopPropagation) event.stopPropagation();
	else event.cancelBubble=true;
}
    
function abc()
{
	alert("fdas");
}


function fold (id,event)
{
	var listItem="#listItem"+id;
	var listFoldBtn="#listFoldBtn"+id;
	var reservationInfo="#reservationInfo"+id;
	var listContentClass=".listContentClass"+id;
    $(listItem).animate({height:"33px"},{duration:400,complete:function(){
	    $(listFoldBtn).css("display","none");
	    $(reservationInfo).css("display","none");
	    $(listContentClass).css("display","block");
    }});
    
    if(event.stopPropagation) event.stopPropagation();
	else event.cancelBubble=true;
    $(listItem).css("cursor","pointer");
    $(listItem).click(function(){expand(id)});
}