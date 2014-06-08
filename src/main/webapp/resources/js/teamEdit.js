

function choose(){
	var departments=document.getElementById("departments");
	var name=window.parent.name;
	
	
		for(var i=0;i<departments.options.length;i++)
		{
		    if(departments.options[i].value==name)
		    {
		    	departments.options[i].selected=true;
		    }
		}

		 window.parent.name="";
	
	    $("#departments").trigger("change");
	
	
	
	
}