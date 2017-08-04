function complaintValidate(){
	var j = 0;
	$.each($("input[type='checkbox']"), function(i, item) {
		if(item.checked){
			j++;
		}
	});
	if(j < 1){
		alert("请选择来电类型");
		return false;
	};
	if(!$('#complaintObjId input[name="complaint.complaintObjId"]:checked').val()){
		alert("请选择投诉对象");
		return false;
	}
	
	var complaintObjId = $('#complaintObjId input[name="complaint.complaintObjId"]:checked').val();
	if(complaintObjId && complaintObjId == 1){
		var dealerId = document.getElementById("dealerId").value;
		if(dealerId == "-1"){
			alert("请选择经销商");
			return false;
		}
		if($("[name='complaint.complainantName']").val() == ""){
			alert("请填写来电人");
			return false;
		}
		if($("[name='complaint.complainantPosition']").val() == ""){
			alert("请填写职务");
			return false;
		}
	}
	
	if(complaintObjId && complaintObjId == 2){
		if($("#two").val() == -1){
			alert("请选择金融机构");
			return false;
		}
		if($("[name='complaint.complainantName']").val() == ""){
			alert("请填写来电人");
			return false;
		}
		if($("[name='complaint.complainantPosition']").val() == ""){
			alert("请填写职务");
			return false;
		}
	}
	if(complaintObjId && complaintObjId == 3){
		if($("[name='complaint.rosterId']").val() == -1){
			alert("请选择监管员");
			return false;
		}
		if($("[name='complaint.storeId']").val() == "" || $("[name='complaint.storeId']").val() == 0){
			alert("请选择所在店");
			return false;
		}
	}
	if($("#processingId").val() == -1){
		alert("请选择处理人员");
		return false;
	}
	
	var value = document.getElementById("complaint.contentText").value;
	if(value == ""){
		alert("请填写投诉内容");
		return false;
	}
	var value = document.getElementById("complaint.treatmentOpinion").value;
	if(value == ""){
		alert("请填写处理意见");
		return false;
	}
	return true;
}
		