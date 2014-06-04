//var bookedDate=[{year:2014,month:1,day:20,department:"USER_EXPERIENCE",lc:"xx",usage:"xx",usertele:"xx"},{year:2014,month:1,day:23,department:"xx",lc:"xx",usage:"xx",usertele:"xx"}];//�����Ǵ���һ����ż�ֵ�ԵĶ�������飬˵����Щ�챻���ˡ�Key��year,month,day��department,lc,usage,usertele����ʽ�����������ӡ�

var SY, SM, SD, cy, cm
SY = new Date().getFullYear();
SM = new Date().getMonth() + 1;
SD = new Date().getDate();
cy = document.getElementById("cy");
cm = document.getElementById("cm");
window.onload = function() {
	getDynamicTable(SY, SM)
	document.getElementById("cy").innerHTML = parseInt(SY)
	document.getElementById("cm").innerHTML = parseInt(SM)
	// document.getElementById("YearAll").innerHTML = YearAll(SY)
	// document.getElementById("DateAll").innerHTML = DateAll(SY,SM)
};
// function YearAll(Y){
// var Ystr = ""
//	
// Ystr += "<li onclick='getym(this,\"cy\")'>"+ Y +"</li>"
//	
// return Ystr
// }
// function DateAll(Y,M){
// var Mstr = "",Mnum = GetDaysInMonth(Y,M)
// for (var m = M; m <= M+1; m++) {
// Mstr += "<li onclick='getym(this,\"cm\")'>"+ (m < 10 ? "0" + m : m) +"</li>"
// }
// return Mstr
// }
// function getym(o,s){
// document.getElementById(s).innerHTML = parseInt(o.innerHTML)
// getDynamicTable(parseInt(cy.innerHTML),parseInt(cm.innerHTML))
// }
function Month(s) {

	var m = parseInt($("#cm").html())
	var sm1 = parseInt(SM)

	if (s == "l") {
		if (m == sm1) {

		} else {
			m--;
		}
	} else {
		if (m == (sm1 + 1)) {

		} else {
			m++;
		}
	}

	$("#cm").html(m)
	getDynamicTable(SY, m)
}
function now() {// ///////////////////////////////////////////////////////////
	getDynamicTable(SY, SM)
	cy.innerHTML = SY
	cm.innerHTML = SM
}
function getDynamicTable(Y, M) {
	var book = false;
	var future = false;
	var buffer = false;
	var bufferNum = 2;

	var Temp, i, j, k, l

	var FirstDate, MonthDate, CirNum, ErtNum
	FirstDate = GetWeekdayMonthStartsOn(Y, M)
	MonthDate = GetDaysInMonth(Y, M)
	ErtNum = FirstDate + MonthDate

	Temp = ""
	TDstyle = " "
	if (ErtNum > 35) {
		CirNum = 42
	} else if (ErtNum == 28) {
		CirNum = 28
	} else {
		CirNum = 35
	}
	j = 1
	// var k=0
	// alert(bookedDate[k].year);
	// alert(Y+","+M)
	// alert("��һ��:"+FirstDate+"; ������:"+MonthDate+"; ErtNum:"+ErtNum+";
	// �������:"+(CirNum/7))
	for (i = 1; i <= CirNum; i++) {
		if (i == 1) {
			Temp += "<table><tr><th>Sun</th><th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th><th>Sat</th></tr><tr>"
		}
		if (i < FirstDate + 1 || i > ErtNum) {
			Temp += "<td></td>"
		} else {
			if (bookedDate != null) {
				for (k = 0; k < bookedDate.length; k++) {
					if (bookedDate[k].year == Y && bookedDate[k].month == M
							&& bookedDate[k].day == j) {
						book = true;
						l = k;
					}
				}
			}
			if (SY <= Y && SM <= M && SD <= j || SY <= Y && SM < M) {
				future = true;
			}
			if (SY == Y && SM == M && SD == j) {

				buffer = true;

			}
			if (buffer == true) {

				if (book == true) {
					Temp += (SY == Y && SM == M && SD == j ? "<td class='now idle'onclick=\"alert('The date is unavailable to book')\"><div class=\"bookedDiv\"></div>"
							: "<td class='idle'onclick=\"alert('The date is unavailable to book')\"><div class=\"bookedDiv\"></div>")
							+ j + "</td>"
				} else {
					Temp += (SY == Y && SM == M && SD == j ? "<td class='now idle'onclick=\"alert('The date is unavailable to book')\">"
							: "<td class='idle'onclick=\"alert('The date is unavailable to book')\">")
							+ j + "</td>"
				}

				if (bufferNum == 1) {
					buffer = false;

				}
				bufferNum--;
			} else {

				if (book == true) {
					if (future == true) {
						Temp += "<td onclick='bookInfo(" + l
								+ ")'><div class=\"bookedDiv\"></div>" + j
								+ "</td>";
						book = false;
					} else {
						Temp += "<td class='pass idle'onclick='bookInfo(" + l
								+ ")'><div class=\"bookedDiv\"></div>" + j
								+ "</td>";
						book = false;
					}

				} else {

					if (future == true) {
						Temp += (SY == Y && SM == M && SD == j ? "<td class='now'onclick='setDate("
								+ Y + "," + M + "," + j + ")'>"
								: "<td class='idle'onclick='setDate(" + Y + ","
										+ M + "," + j + ")'>")
								+ j + "</td>"
					} else if (future == false) {
						Temp += "<td class='idle pass' onclick='alert(\"The date has passed\")'>"
								+ j + "</td>"
					}
				}
			}

			j = j + 1
		}
		if (i % 7 == 0 && i < ErtNum) {
			Temp += "</tr><tr>"
		}
		if (i == CirNum) {
			Temp += "</tr></table>"
		}
	}
	document.getElementById("DayAll").innerHTML = Temp
}
function GetDaysInMonth(Y, M) { // '�õ����µ�������
	if (M == 1 || M == 3 || M == 5 || M == 7 || M == 8 || M == 10 || M == 12)
		return 31;
	else if (M == 4 || M == 6 || M == 9 || M == 11)
		return 30;
	else if (M == 2)
		if ((Y % 4 == 0 && Y % 100 != 0) || (Y % 100 == 0 && Y % 400 == 0))
			return 29;
		else
			return 28;
	else
		return 28;
}
function GetWeekdayMonthStartsOn(Y, M) { // '�õ����µĵ�һ�������ڼ�
	var date = new Date(Y, M - 1, 1)
	return date.getDay()
}

function bookInfo(a) {
	alert("Sorry, This Room has been booked by" + bookedDate[a].applicant_Team
			+ "More Information Please Contact��" + bookedDate[a].tele
			+ "or emails to this Address:" + bookedDate[a].email
			+ "or contact LC:" + bookedDate[a].approveBy);
	// alert("dept:"+bookedDate[a].department+"lc:"+bookedDate[a].lc+"tele:"+bookedDate[a].usertele);
}
function setDate(year, month, day) {

	// var
	// url=path+'/room/getForm?room_ID='+roomId+'&year='+year+'&month='+month+'&day='+day;
	// window.location.href=url;//禁用这个url，直接复制给floatTicketDiv浮出来
	// var a=window.top.document.getElementById("calArea"+roomId);
	// var b=window.top.document.getElementById("cover");
	//	
	// a.setAttribute("class","calArea floatTicket");
	// b.setAttribute("class","cover");
	// eval(
	// "b.setAttribute(\"onclick\",\"turnback("+roomId+");\");"
	// )

	$.ajax({
		url : path + "/room/getForm",
		type : "Get",
		data : {
			room_ID : roomId,
			year : year,
			month : month,
			day : day
		},
		dataType : "json",
		success : function(json) {
			// {"roomList":[{"room_ID":"8","room_Status":"0","last_Used_Date":"2014-05-15","item":"TKH
			// OT2 27.5","department_ID":"4"}],
			// "select_date":[{"select_date":"2014-006-7"}],
			// "teamList":[{"teamName":"Training
			// Team1","user_ID":"1","team_ID":"1","department_ID":"1"},{"teamName":"Training
			// Team2","user_ID":"1","team_ID":"2","department_ID":"1"},{"teamName":"Financial
			// Team1","user_ID":"1","team_ID":"6","department_ID":"2"},{"teamName":"Financial
			// Team2","user_ID":"1","team_ID":"8","department_ID":"2"},{"teamName":"GLTC
			// Team1","user_ID":"1","team_ID":"9","department_ID":"4"}]}

			var roomOption = window.top.document.getElementById("roomOption");
			var time1 = window.top.document.getElementById("begin_time");
			var time2 = window.top.document.getElementById("end_time");
			var close = window.top.document.getElementById("close");

			roomOption.setAttribute("value", json.roomList[0].room_ID);
			roomOption.innerHTML = json.roomList[0].item;
			time1.setAttribute("value", json.select_date[0].select_date);
			time2.setAttribute("value", json.select_date[0].select_date);

			close.setAttribute("onclick", "back(" + json.roomList[0].room_ID
					+ ")");

			var a = window.top.document.getElementById("floatTicket");
			var b = window.top.document.getElementById("cover");
			a.setAttribute("class", "floatTicket");
			b.setAttribute("class", "cover");
			var teams = "<option id='empty'></option>";
			for (var k = 0; k < json.teamList.length; k++) {
				teams += "<option ";
				teams += "value=\"" + json.teamList[k].team_ID + "\"";
				teams += ">" + json.teamList[k].teamName;
				teams += "</option>";
			}
			var teamOption = window.top.document.getElementById("teams");
			teamOption.innerHTML = teams;
			eval("b.setAttribute(\"onclick\",\"turnback(" + roomId + ");\");")

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('failed');
		}
	});
}
