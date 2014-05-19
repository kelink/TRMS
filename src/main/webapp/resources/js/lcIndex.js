
function displayCal(id)
{
    list="#list"+id
    calArea="#calArea"+id
    ifr="#iframe"+id
    
       eval("expand" +id+"="+"false");
	
	
		if (eval("expand" +id) == false) {
			$(list).animate({
				height : "500"
			}, 400);
			$(calArea).css("display", "block");
			$(ifr).attr("src", "/trms/user/calendar?room_ID="+"room");
			expand1 = true;
		} else if (eval("expand" +id) == true) {
			$(list).animate({
				height : "84"
			}, 400);
			$(calArea).css("display", "none");
			expand1 = false;
		}
	
}

