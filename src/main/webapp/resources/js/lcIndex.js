window.onload = function() {
	var expand1 = false;
	$("#cal1").click(function() {
		if (expand1 == false) {
			$("#list1").animate({
				height : "500"
			}, 400);
			$("#calArea1").css("display", "block");
			$("#iframe1").attr("src", "/trms/user/calendar?room_ID=1");
			expand1 = true;
		} else if (expand1 == true) {
			$("#list1").animate({
				height : "84"
			}, 400);
			$("#calArea1").css("display", "none");
			expand1 = false;
		}
	});
}
