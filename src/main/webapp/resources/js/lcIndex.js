var expand1 = false;
function displayCal(id)
{
    list="#list"+id
    calArea="#calArea"+id
    ifr="#iframe"+id
    
	
	
		if (expand1 == false) {
			$(list).animate({
				height : "500"
			}, 400);
			$(calArea).css("display", "block");
			$(ifr).attr("src", "/trms/user/calendar?room_ID="+"room");
			expand1 = true;
		} else if (expand1 == true) {
			$(list).animate({
				height : "84"
			}, 400);
			$(calArea).css("display", "none");
			expand1 = false;
		}
	
}

