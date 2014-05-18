<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags'%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>万年历</title>
<link href="/trms/resources/css/calendar.css" rel="stylesheet" type="text/css">

</head>

<body class="calBody">
<div class="day">
    <div class="DaySelect">
		<i class="lr" onclick="Month('l')" title="上一月"><</i>
        <div class="select">
            <div class="stop" id="cy">2013</div>
            <div class="sbox">
                <ul id="YearAll">
                    <li>2013</li>
                </ul>
            </div>
        </div>
        <div class="select" id="sm">
            <div class="stop" id="cm">06</div>
            <div class="sbox" id="mm">
                <ul id="DateAll">
                    <li>01</li>
                </ul>
            </div>
        </div>
        <i class="lr" onclick="Month('r')" title="下一月">></i>
        <i onclick="now()">今天</i>
    </div>
    <div id="DayAll"></div>
</div>
</body>
<script>
var bookedDate=[{year:2014,month:1,day:20,department:"USER_EXPERIENCE",lc:"xx",usage:"xx",usertele:"xx"},{year:2014,month:1,day:23,department:"xx",lc:"xx",usage:"xx",usertele:"xx"}];//这里是传入一个存放键值对的对象的数组，说明哪些天被订了。Key有year,month,day，department,lc,usage,usertele。格式参照上面例子。
var book=false;

var SY,SM,SD,cy,cm
SY = new Date().getFullYear();
SM = new Date().getMonth()+1;
SD = new Date().getDate();
cy = document.getElementById("cy")
cm = document.getElementById("cm")
window.onload = function(){
	getDynamicTable(SY,SM)
	document.getElementById("YearAll").innerHTML = YearAll(SY)
	document.getElementById("DateAll").innerHTML = DateAll(SY,SM)
}
function YearAll(Y){
	var Ystr = ""
	for (var y = Y - 10; y <= Y + 10; y++) {
		Ystr += "<li onclick='getym(this,\"cy\")'>"+ y +"</li>"
	}
	return Ystr
}
function DateAll(Y,M){
	var Mstr = "",Mnum = GetDaysInMonth(Y,M)
	for (var m = 1; m <= 12; m++) {
		Mstr += "<li onclick='getym(this,\"cm\")'>"+ (m < 10 ? "0" + m : m) +"</li>"
	}
	return Mstr
}
function getym(o,s){
	document.getElementById(s).innerHTML = parseInt(o.innerHTML)
	getDynamicTable(parseInt(cy.innerHTML),parseInt(cm.innerHTML))
}
function Month(s){
	var y = parseInt(cy.innerHTML),m = parseInt(cm.innerHTML)
	if (s == "l") {
		if (m <= 1) {
			m = 12
			y --
		} else {
			m --
		}
	} else {
		if (m >= 12) {
			m = 1
			y ++
		} else {
			m ++
		}
	}
	cy.innerHTML = y
	cm.innerHTML = m
	getDynamicTable(y,m)
}
function now(){
	getDynamicTable(SY,SM)
	cy.innerHTML = SY
	cm.innerHTML = SM
}
function getDynamicTable(Y,M){
	var Temp,i,j,k,l
	
	var FirstDate,MonthDate,CirNum,ErtNum // '当月第一天为星期几,当月的总天数,表格的单元格数及循环数,表格第一排空格数与当月天数之和
	FirstDate = GetWeekdayMonthStartsOn(Y,M)// '得到该月的第一天是星期几  0-6
	MonthDate = GetDaysInMonth(Y,M)// '得到该月的总天数 30
	ErtNum = FirstDate + MonthDate// -1 
	
	Temp = ""
	TDstyle = " "   
	if (ErtNum > 35){
   		CirNum = 42
	}else if (ErtNum == 28){
   		CirNum = 28 
	}else{
   		CirNum = 35
	}
	j=1
	//var k=0
	//alert(bookedDate[k].year);
	//alert(Y+","+M)
	//alert("第一天:"+FirstDate+"; 总天数:"+MonthDate+"; ErtNum:"+ErtNum+"; 表格行数:"+(CirNum/7))
	for (i = 1; i <= CirNum; i++){
		if (i == 1){
			Temp += "<table><tr><th>日</th><th>一</th><th>二</th><th>三</th><th>四</th><th>五</th><th>六</th></tr><tr>"
		}
    	if (i < FirstDate + 1 || i > ErtNum){
       		Temp += "<td></td>"
		}else{
		  	
			for(k=0;k<bookedDate.length;k++)
			{
			    if(bookedDate[k].year==Y&&bookedDate[k].month==M&&bookedDate[k].day==j)
				{book=true;l=k;}
			}
			if(book==true)
			{
				Temp +=  "<td class='booked'onclick='bookInfo("+l+")'>" + j +"</td>"
				book=false;
			}
			else
			{
			    Temp += (SY == Y && SM == M && SD == j ? "<td class='now'onclick='setDate("+Y+","+M+","+j+")'>" : "<td class='idle'onclick='setDate("+Y+","+M+","+j+")'>") + j +"</td>"
				
				
			}
	      		  
	   		j = j + 1
		}
		if (i % 7 == 0 && i < ErtNum){
			Temp += "</tr><tr>"
		}
		if (i == CirNum){
			Temp += "</tr></table>"
		}
	}
	document.getElementById("DayAll").innerHTML = Temp
}
function GetDaysInMonth(Y,M){				//'得到该月的总天数
	if (M==1||M==3||M==5||M==7||M==8||M==10||M==12)
		return 31;
	else if (M==4||M==6||M==9||M==11)
		return 30;
	else if (M==2)
		if((Y%4==0 && Y%100!=0)||(Y%100==0 && Y%400==0))
			return 29;
		else
			return 28;
	else
		return 28;
}
function GetWeekdayMonthStartsOn(Y,M){		//'得到该月的第一天是星期几
	var date = new Date(Y,M-1,1)
	return date.getDay()
}

function bookInfo(a)
{
    alert("dept:"+bookedDate[a].department+"lc:"+bookedDate[a].lc+"tele:"+bookedDate[a].usertele);
}
function setDate(year,month,day)
{
	 window.location.href("lcBook.html");
}
</script>
</html>
