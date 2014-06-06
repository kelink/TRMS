window.onload = function() {
	count();

}
var second = 5;
var timer;

function count() {

	timer = setTimeout('change()', 1000);
}

function change() {
	second--;
	if (second > -1) {
		document.getElementById("btnReturn").innerHTML = "Return " + second;
		timer = setTimeout('change()', 1000);
	} else {
		clearTimeout(timer);
		window.location.href = "/trms/room/headInfo";
	}
}
