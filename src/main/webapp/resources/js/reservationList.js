
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
}