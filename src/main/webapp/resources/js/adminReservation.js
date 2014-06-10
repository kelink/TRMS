window.onload = function() {

	// ////////////////////////////////////////////
	$("#backBtn").click(function() {
		window.top.location.reload();
	});
	$("#logoutBtn").mouseover(function() {
		$(this).attr("class", "btnLogout btnHover");

	});
	$("#logoutBtn").mouseout(function() {
		$(this).attr("class", "btnLogout");
	});
	$("#logoutBtn").mousedown(function() {
		$(this).attr("class", "btnLogout btnLogoutSelected");
	});

	$(".close").mouseover(function() {
		$(".closeIcon").attr("src", "/trms/resources/images/closeDeep.png");
	});
	$(".close").mouseout(function() {
		$(".closeIcon").attr("src", "/trms/resources/images/closeShallow.png");
	});
	$("#closeShow").click(function() {
		$("#showPanel").css("display", "none");
	});
	$("#closeSort").click(function() {
		$("#sortPanel").css("display", "none");
	});

	$("#sortBtn").click(function() {
		if ($("#sortPanel").css("display") == "none")
			$("#sortPanel").css("display", "block");
		else
			$("#sortPanel").css("display", "none");

		$("#showPanel").css("display", "none");
	});
	$(".sortPanelItem").mouseover(function() {
		$(this).css("background", "#4183c4");
		$(this).css("color", "white");
	});
	$(".sortPanelItem").mouseout(function() {
		$(this).css("background", "white");
		$(this).css("color", "rgb(105, 105, 105)");
	});

	$("#showBtn").click(function() {
		if ($("#showPanel").css("display") == "none")
			$("#showPanel").css("display", "block");
		else
			$("#showPanel").css("display", "none");
		$("#sortPanel").css("display", "none");
	});
	$(".sortPanelItem").mouseover(function() {
		$(this).css("background", "#4183c4");
		$(this).css("color", "white");
	});
	$(".sortPanelItem").mouseout(function() {
		$(this).css("background", "white");
		$(this).css("color", "rgb(105, 105, 105)");
	});

	// /////////////////////////////////////////////
	$("#search1").bind("mouseover", function() {
		$(this).css("background-color", "rgb(238, 238, 238)");
	});
	$("#search1").bind("mouseout", function() {
		$(this).css("background-color", "");
	});
	$("#search2").bind("mouseover", function() {
		$(this).css("background-color", "rgb(238, 238, 238)");
	});
	$("#search2").bind("mouseout", function() {
		$(this).css("background-color", "");
	});
	$("#search3").bind("mouseover", function() {
		$(this).css("background-color", "rgb(238, 238, 238)");
	});
	$("#search3").bind("mouseout", function() {
		$(this).css("background-color", "");
	});
	$("#search4").bind("mouseover", function() {
		$(this).css("background-color", "rgb(238, 238, 238)");
	});
	$("#search4").bind("mouseout", function() {
		$(this).css("background-color", "");
	});

	$("#search0").click(function() {
		$("#search0").unbind("mouseover mouseout");
		$("#search0").css("background-color", "");
		$("#search1").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search1").bind("mouseout", function() {
			$(this).css("background-color", "");
		});
		$("#search2").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search2").bind("mouseout", function() {
			$(this).css("background-color", "");
		});
		$("#search3").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search3").bind("mouseout", function() {
			$(this).css("background-color", "");
		});
		$("#search4").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search4").bind("mouseout", function() {
			$(this).css("background-color", "");
		});

		$(this).attr("class", "checkMethod selected");
		$("#search1").attr("class", "checkMethod");
		$("#search2").attr("class", "checkMethod");
		$("#search3").attr("class", "checkMethod");
		$("#search4").attr("class", "checkMethod");
		$("#searchDivWrapper").css("display", "none");
		$(".btnSearch").css("display", "none");

		$("#checkRightInner").attr("src", "/trms/reservation/list?status=-1");

	});

	$("#search1").click(function() {
		$("#search1").unbind("mouseover mouseout");
		$("#search1").css("background-color", "");
		$("#search0").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search0").bind("mouseout", function() {
			$(this).css("background-color", "");
		});
		$("#search3").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search3").bind("mouseout", function() {
			$(this).css("background-color", "");
		});
		$("#search2").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search2").bind("mouseout", function() {
			$(this).css("background-color", "");
		});
		$("#search4").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search4").bind("mouseout", function() {
			$(this).css("background-color", "");
		});
		$(this).attr("class", "checkMethod selected1");
		$("#search0").attr("class", "checkMethod");
		$("#search3").attr("class", "checkMethod");
		$("#search2").attr("class", "checkMethod");
		$("#search4").attr("class", "checkMethod");

		$("#searchDivWrapper").css("display", "none");
		$(".btnSearch").css("display", "none");
		$("#checkRightInner").attr("src", "/trms/reservation/list");
		// ////////////////////////////////////////////////////////////这里修改为show
		// all reservation的Url//////////////////////

	});

	$("#search2").click(function() {
		$("#search2").unbind("mouseover mouseout");
		$("#search2").css("background-color", "");
		$("#search0").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search0").bind("mouseout", function() {
			$(this).css("background-color", "");
		});
		$("#search1").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search1").bind("mouseout", function() {
			$(this).css("background-color", "");
		});
		$("#search3").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search3").bind("mouseout", function() {
			$(this).css("background-color", "");
		});
		$("#search4").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search4").bind("mouseout", function() {
			$(this).css("background-color", "");
		});

		$(this).attr("class", "checkMethod selected1");
		$("#search0").attr("class", "checkMethod");
		$("#search1").attr("class", "checkMethod");
		$("#search3").attr("class", "checkMethod");
		$("#search4").attr("class", "checkMethod");
		$("#searchDivWrapper").css("display", "block");
		$(".btnSearch").css("display", "block");
		$("#searchDivWrapper").animate({
			height : "47px"
		}, {
			duration : 400,
			complete : function() {
				$(".vague").css("display", "none");
				$(".preciseSearch").css("display", "none");
			}
		});

	});

	$("#search3").click(function() {
		$("#search3").unbind("mouseover mouseout");
		$("#search3").css("background-color", "");
		$("#search0").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search0").bind("mouseout", function() {
			$(this).css("background-color", "");
		});
		$("#search1").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search1").bind("mouseout", function() {
			$(this).css("background-color", "");
		});
		$("#search2").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search2").bind("mouseout", function() {
			$(this).css("background-color", "");
		});
		$("#search4").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search4").bind("mouseout", function() {
			$(this).css("background-color", "");
		});
		$(this).attr("class", "checkMethod selected1");
		$("#search0").attr("class", "checkMethod");
		$("#search1").attr("class", "checkMethod");
		$("#search2").attr("class", "checkMethod");
		$("#search4").attr("class", "checkMethod");

		$(".btnSearch").css("display", "block");
		$("#searchDivWrapper").animate({
			height : "188px"
		}, {
			duration : 400,
			complete : function() {
				$(".vague").css("display", "block");
				$(".preciseSearch").css("display", "none");
			}
		});

	});

	$("#search4").click(function() {
		$("#search4").unbind("mouseover mouseout");
		$("#search4").css("background-color", "");
		$("#search0").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search0").bind("mouseout", function() {
			$(this).css("background-color", "");
		});
		$("#search1").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search1").bind("mouseout", function() {
			$(this).css("background-color", "");
		});
		$("#search2").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search2").bind("mouseout", function() {
			$(this).css("background-color", "");
		});
		$("#search3").bind("mouseover", function() {
			$(this).css("background-color", "rgb(238, 238, 238)");
		});
		$("#search3").bind("mouseout", function() {
			$(this).css("background-color", "");
		});
		$(this).attr("class", "checkMethod selected1");
		$("#search0").attr("class", "checkMethod");
		$("#search1").attr("class", "checkMethod");
		$("#search2").attr("class", "checkMethod");
		$("#search3").attr("class", "checkMethod");
		$(".btnSearch").css("display", "block");
		$("#searchDivWrapper").animate({
			height : "390px"
		}, {
			duration : 400,
			complete : function() {
				$(".preciseSearch").css("display", "block");
				$(".vague").css("display", "none");
			}
		});

	});

	for (var i = 1; i <= 4; i++) {
		$("#searchInput" + i).bind("input propertychange", function() {
			if ($(this).val() == "") {
				$(this).css("background", "transparent");
			} else
				$(this).css("background", "white");

		});

	}

};
function closeSort() {
	$("#sortPanel").css("display", "none");
}
function closeShow() {
	$("#showPanel").css("display", "none");
}
function logout(url) {
	$("#logoutBtn").attr("class", "btnLogout btnSelected");
	window.location.href = url;
}
function checkMyReservation(user_ID) {
	$("#checkRightInner").attr("src",
			"/trms/reservation/list?user_ID=" + user_ID);
}
