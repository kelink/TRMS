window.onload=function(){
	SD = new Date().getDate();
	
	if(SD>=20)
	{
		$("#nextMonthWord").html("It's available to book rooms for next month");
		$("#nextMonthOpenIcon").attr("src","/trms/resources/images/nextMonthOpen.png");
		
	}
	else{
		$("#nextMonthWord").html("You can only book room for this month");
		$("#nextMonthOpenIcon").attr("src","/trms/resources/images/nextMonthClose1.png");
	};
}