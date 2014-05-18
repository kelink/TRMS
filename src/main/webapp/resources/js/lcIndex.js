window.onload = function() {
	var expand1 = false;
	$("#cal1").click(function() {
		if (expand1 == false) {
			$("#list1").animate({
				height : "500"
			}, 400);
			$("#calArea1").css("display", "block");
			$("#iframe1").attr("src", "calendar.jsp?room=1");
			expand1 = true;
		} else if (expand1 == true) {
			$("#list1").animate({
				height : "84"
			}, 400);
			$("#calArea1").css("display", "none");
			expand1 = false;
		}
	});
	var expand2 = false;
	$("#cal2").click(function() {
		if (expand2 == false) {
			$("#list2").animate({
				height : "500"
			}, 400);
			$("#calArea2").css("display", "block");
			$("#iframe2").attr("src", "calendar.html?room=2");
			expand2 = true;
		} else if (expand2 == true) {
			$("#list2").animate({
				height : "84"
			}, 400);
			$("#calArea2").css("display", "none");
			expand2 = false;
		}
	});
	var expand3 = false;
	$("#cal3").click(function() {
		if (expand3 == false) {
			$("#list3").animate({
				height : "500"
			}, 400);
			$("#calArea3").css("display", "block");
			$("#iframe3").attr("src", "calendar.html?room=3");
			expand3 = true;
		} else if (expand3 == true) {
			$("#list3").animate({
				height : "84"
			}, 400);
			$("#calArea3").css("display", "none");
			expand3 = false;
		}
	});
	var expand4 = false;
	$("#cal4").click(function() {
		if (expand4 == false) {
			$("#list4").animate({
				height : "500"
			}, 400);
			$("#calArea4").css("display", "block");
			$("#iframe4").attr("src", "calendar.html?room=4");
			expand4 = true;
		} else if (expand4 == true) {
			$("#list4").animate({
				height : "84"
			}, 400);
			$("#calArea4").css("display", "none");
			expand4 = false;
		}
	});
	var expand5 = false;
	$("#cal5").click(function() {
		if (expand5 == false) {
			$("#list5").animate({
				height : "500"
			}, 400);
			$("#calArea5").css("display", "block");
			$("#iframe5").attr("src", "calendar.html?room=5");
			expand5 = true;
		} else if (expand5 == true) {
			$("#list5").animate({
				height : "84"
			}, 400);
			$("#calArea5").css("display", "none");
			expand5 = false;
		}
	});

}
