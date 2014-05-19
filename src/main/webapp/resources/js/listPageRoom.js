var pageIndex = 0;
var pageSize = 5;
$(function() {
	pageIndex = 1;
	AjaxGetData(pageIndex, pageSize);
});

function AjaxGetData(index, size) {
	$
			.ajax({
				url : "${pageContext.request.contextPath}/book/listPageRoom",
				type : "Get",
				data : "pageNum=" + index + "&pageSize=" + size,
				dataType : "json",
				success : function(json) {
					var html = "";
					// 获取构造输出查询内容
					for (position in json) {
						html += "<tr>";
						html += "<td>" + json[position].room_ID + "</td>";
						html += "<td>" + json[position].item + "</td>";
						html += "<td>" + json[position].room_Status + "</td>";
						html += "<td>" + json[position].last_Used_Date
								+ "</td>";
						html += "<td><a href='editposition?id="
								+ json[position].room_ID
								+ "'>Edit&nbsp;<a href='position?id="
								+ json[position].room_ID + "'>View</td>";
						html += "</tr>";

					}
					// 构造输出上下页和首页尾页的标签
					html += "</tbody>";

					html += "<tfoot>";
					html += "<tr>";
					html += "<td colspan='7'>";
					html += "<span>Total Records:" + ${recordCount}+"; Total Page:<span id='count'>" + ${pageCount}
					+"" + "<br/>";
					html += "<a href='javascript:void' onclick='GoToFirstPage()' id='FirstPage' >First&nbsp;&nbsp; ";
					html += "<a href='javascript:void' onclick='GoToPrePage()' id='PrePage' >Pre&nbsp;&nbsp; ";
					html += "<a href='javascript:void' onclick='GoToNextPage()' id='NextPage'>Next&nbsp;&nbsp; ";
					html += "<a href='javascript:void' onclick='GoToEndPage()' id='EndPage' >Last&nbsp;&nbsp; ";
					html += "<input type='text' size='4' name='page' /><input type='button' value='Jump' onclick='GoToAppointPage(this)' /> ";
					html += "</td>";
					html += "</tr>";
					html += "</tfoot>";
					html += "</table>";
					// 写入文件
					$('#divResult').html("");
					$('#divResult').html(html);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(XMLHttpRequest);
					alert(textStatus);
					alert(errorThrown);
				}
			});
}

function GoToFirstPage() {
	pageIndex = 1;
	AjaxGetData(pageIndex, pageSize);
}

function GoToPrePage() {
	pageIndex -= 1;
	pageIndex = pageIndex >= 1 ? pageIndex : 1;
	AjaxGetData(pageIndex, pageSize);
}

function GoToNextPage() {
	if (pageIndex < parseInt($("#count").text())) {
		pageIndex += 1;
	}
	AjaxGetData(pageIndex, pageSize);
}

function GoToEndPage() {
	pageIndex = parseInt($("#count").text());
	AjaxGetData(pageIndex, pageSize);
}

function GoToAppointPage(e) {
	var page = $(e).prev().val();
	if (isNaN(page)) {
		alert("Page should be a valid number");
	} else {
		var tempPageIndex = pageIndex;
		pageIndex = parseInt($(e).prev().val());
		if (pageIndex <= 0 || pageIndex > parseInt($("#count").text())) {
			pageIndex = tempPageIndex;
			alert("Please input valid page scope!");
		} else {
			AjaxGetData(pageIndex, pageSize);
		}
	}
}
