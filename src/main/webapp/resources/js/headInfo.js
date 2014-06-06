window.onload=function(){
	SD = new Date().getDate();
	
	if(SD>=20)
	{
		$("#nextMonthOpen").html("available to book rooms of next month");
	}
	else{
		$("#nextMonthOpen").html("you can only book room of this month");
	};
}