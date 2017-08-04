function addTr(id) {
	var name1 = '';
	var name2 = '';
	if (id == "problem2") {
		name1 = "problemContent";
		name2 = "problemResult";
	} else {
		name1 = "supervisorContent";
		name2 = "supervisorResult";
	}
	var num = $("#" + id + " tbody").length;
	var htmlStr = "<tbody>" + "<tr><td class='nameCol' style='width: 1%;'>"
			+ num + ":</td><td class='codeCol' colspan='5'>";
	htmlStr += "<input type='text' name='" + name1 + "'style='width: 90%;'/>"
			+ "</td></tr><tr><td class='nameCol' style='width: 1%;'>处理结果：</td>";
	htmlStr += "<td class='codeCol' colspan='5'>" + "<input type='text' name='"
			+ name2 + "'  style='width: 90%;'/>";

	htmlStr += "&nbsp;&nbsp;&nbsp;<a class='link' href='javascript:void(0);' onClick='deleteTbody(this)'>删除</a></td></tr></tbody>";
	$("#" + id).append(htmlStr);

}

function deleteTbody(obj) {
	var table = $(obj).closest('table');
	$(obj).closest('tbody').remove();
	var tbodys = table.find("tbody:gt(0)");
	if (tbodys.length > 0) {
		tbodys.each(function(i) {
			$(this).find("tr:first td:first").text((i + 1) + ':');
		});
	}
}

function saveInfo(id, index) {
	var url = $("#" + id).attr("action");
	var formdata = $("#" + id).serializeArray();
	var json = "{";
	if (formdata != null && formdata.length > 0) {
		for (var i = 0; i < formdata.length; i++) {
			json += "\'" + formdata[i].name + "\':" + "\'" + formdata[i].value
					+ "\',";
		}

		json = json.substr(0, json.length - 1);
		json += "}";
		$.ajax({
			type : "POST",
			url : url,
			data : {
				"jsonData" : json
			},
			dataType : "json",
			success : function(result) {
				if (result.success) {
					$('#button'+index).show();
					$('#tt').tabs('enableTab', index);
					$('#tt').tabs('select', index);
                } else {
					$.messager.alert('提示', result.message);

				}
			}
		});
	}

}

function saveRecord(id, index) {
	var url = $("#" + id).attr("action");
	var formdata = $("#" + id).serializeArray();
	var problem = "[";
	var supervisor = "[";
	var inspectionId = "";
	if (formdata != null && formdata.length > 0) {
		for (var i = 0; i < formdata.length; i++) {
			if (formdata[i].name == "problemContent" && formdata[i].value != "") {
				problem += "{";
				problem += "\'" + formdata[i].name + "\':" + "\'"
						+ formdata[i].value + "\',";
				i++;
				problem += "\'" + formdata[i].name + "\':" + "\'"
						+ formdata[i].value + "\'";

				problem += "},";
			} else if (formdata[i].name == "supervisorContent"
					&& formdata[i].value != "") {
				supervisor += "{";
				supervisor += "\'" + formdata[i].name + "\':" + "\'"
						+ formdata[i].value + "\',";
				i++;
				supervisor += "\'" + formdata[i].name + "\':" + "\'"
						+ formdata[i].value + "\'";

				supervisor += "},";
			} else if (formdata[i].name == "inspectionId") {
				inspectionId = formdata[i].value;
			}
		}
		if (problem.length > 1) {
			problem = problem.substr(0, problem.length - 1);
			
		}
		problem += "]";
		if (supervisor.length > 1) {
			supervisor = supervisor.substr(0, supervisor.length - 1);
			
		}
		supervisor += "]";

		$.ajax({
			type : "POST",
			url : url,
			dataType : "json",
			data : {
				"problems" : problem,
				"supervisors" : supervisor,
				"inspectionId" : inspectionId
			},
			success : function(result) {
				if (result.success) {
					$('#button'+index).show();
					$('#tt').tabs('enableTab', index);
					$('#tt').tabs('select', index);
				} else {
					$.messager.alert('提示', result.message);

				}
			}
		});

	}
	

}

function next(index){
	$('#tt').tabs('select', index);
}

//提交
function goSubmit(id,name) {
	if(window.confirm('你确定要提交吗？提交后就不能修改了')){
		location = "/windcontrol/inspection.do?method=submitReport&id="+id+"&inspectionName="+name;
     }else{
        return false;
    }
	
}

//进入列表页面
function goList() {
	location.href = "/windcontrol/inspection.do?method=findProgressList";
}

