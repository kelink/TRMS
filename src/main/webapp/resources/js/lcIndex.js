var expand = [];

function displayCal(id) {
	list = "#list" + id
	calArea = "#calArea" + id
	ifr = "#iframe" + id

	if (typeof (expand[id]) == "undefined")
		expand[id] = false;

	if (expand[id] == false) {
		$(list).animate({
			height : "500"
		}, 400);
		$(calArea).css("display", "block");
		$(ifr).attr("src", "/trms/room/calendar?room_ID=" + id);
		expand[id] = true;
	} else if (expand[id] == true) {
		$(list).animate({
			height : "84"
		}, 400);
		$(calArea).css("display", "none");
		expand[id] = false;
	}

}