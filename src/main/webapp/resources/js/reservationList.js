
//
//function mouseon(e)
//{
//	$(e).css("background","rgb(240, 238, 238)");
//}
//function mouseout(e)
//{
//	$(e).css("background","white");
//}
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