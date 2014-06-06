window.onload=function(){
	SD =new Date().getDate();
	
	if(SD>=15&&SD<20)
	{
		$("#nextMonthWord").html("You must assign the rooms for courses in next month before 20th.");
		$("#nextMonthOpenIcon").attr("src","/trms/resources/images/unhandle.png");
		
	}
	else{
		$("#nextMonthWord").html("You can book room for this month and next month");
		$("#nextMonthOpenIcon").attr("src","/trms/resources/images/nextMonthOpen.png");
	};
}