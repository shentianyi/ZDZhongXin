function changeDealer(id) {
	if (id == -1) {
		return;
	}
	var url = "../json/getDealerByIdJsonAction.do?callback=?&id=" + id;
	$.getJSON(url, function(result) {
		var data = result.data;
		setDealer(data[0]);
	});
}

function changeRoster(id) {
	$("[name='complaint.bankName']").val("");
	if(document.getElementById("complaint.rosterId")){
		document.getElementById("complaint.rosterId").value = id;
	}
	$("#storeId").html("<option value=\"-1\">请选择...</option>");
	if (id == -1) {
		return;
	}
	var url = "../json/getRepositoryBySupervisorId.do?callback=?&id=" + id;
	$.getJSON(url, function(result) {
		var data = result.data[0];
		console.info(data);
		setRoster(data);
	});
	url = "/json/getDealerBankInfoByRepId.do?callback=?&id=" + id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(result);
		$.each(data, function(i, item) {
			var option = "<option value="+item.dealerId+">" + item.dealerName
			+ "</option>";
			$("#storeId").append(option);
		});
	});
}

function changeStore(dealerId){
	if(dealerId == -1){
		$("[name='complaint.bankName']").val("");
		return;
	}
	var id = -1;
	if(document.getElementById("complaint.rosterId")){
		id = document.getElementById("complaint.rosterId").value;
	}
	document.getElementById("complaint.storeId").value = dealerId;
	if(id > 0 && dealerId){
		url = "/json/getDealerBankInfoByRepId.do?callback=?&id=" + id;
		$.getJSON(url, function(result) {
			var data = result.data;
			$.each(data, function(i, item) {
				if(dealerId == item.dealerId){
					$("[name='complaint.bankName']").val(item.bankName);
					$("[name='complaint.bankId']").val(item.bankId);
				}
			});
		});
	}
}

function setDealer(dealer) {
	$("#brand").val(dealer.brand);
	$("#brandId").val(dealer.brandId);
}

function setRoster(roster){
	$("[name='complaint.jobNum']").val(roster.staffNo);
}
function initBank() {
	loadSelect(-1, $("#one"));

	$("#one").change(function() {
		$("#two option:gt(0)").remove();
		$("#three option:gt(0)").remove();
		var id = this.value;
		if (id>0) {
			loadSelect(id, $("#two"));
			setBank(id);
		}else{
			setBank("");
			
			//清空下级银行内容
			$("#two").val(-1);
			$("#three").val(-1);
		}
	});
	$("#two").change(function() {
		$("#three option:gt(0)").remove();
		var id = this.value;
		if (id>0) {
			loadSelect(id, $("#three"));
			setBank(id);
		}else{
			setBank($("#one").val());
			$("#three").val(-1);
		}
	});
	$("#three").change(function(){
		var id = this.value;
		if (id>0) {
			setBank(id);
		}else{
			setBank($("#two").val());
		}
	});
	
	$("#managerId").change(function(){
		$("#managerPhone").val("");
		var id = this.value;
		if(id>0){
			for(var i = 0;i<managers.length;i++){
				if(id == managers[i].id){
					$("#managerPhone").val(managers[i].managerPhone);
					break;
				}
			}
		}
	});
}
function setBank(id,name){
	if(id!=-1){
		$("#financeId").val(id);
	}else{
		$("#financeId").val("");
	}
}

function loadSelect(id, nextSelect) {
	var url = "../json/getBankChildById.do?method=findChildListById&callback=?&bankQuery.id="
			+ id;
	$.ajax({
		url:url,
		async:false,
		dataType:"jsonp",
		success:function(result){
			var data = result.data;
			$.each(data, function(i, item) {
				var option = "<option value="+item.id+">" + item.bankName
						+ "</option>";
				nextSelect.append(option);
			});
		}
	});
}

$(function(){
	loadProcessingName($("[name=complaint\\.processingDepartment]:checked").val());//加载部门人员，默认是第一个
	
	$("#complaintObjInfoOne").hide();$("#complaintObjInfoTwo").hide();
	$("#complaintDealer").hide();$("#complaintFinance").hide();
	$("#rosterInfoOne").hide();$("#rosterInfoTwo").hide();
	//动态显示处理人员名称
	$("[name='complaint.processingDepartment']").change(function(){
		$("#processingId").html("<option value=\"-1\">请选择...</option>");
		var processingId = $('#processingDepartment input[name="complaint.processingDepartment"]:checked').val();
		loadProcessingName(processingId);
	});
	
	$("[name='complaint.complaintObjId']").change(function(){
		$("#complaintObjInfoOne").hide();$("#complaintObjInfoTwo").hide();
		$("#complaintDealer").hide();$("#complaintFinance").hide();
		$("#rosterInfoOne").hide();$("#rosterInfoTwo").hide();
		
		$("#one option:first").prop("selected", "selected");
		$("#two option:first").prop("selected", "selected");
		$("#three option:first").prop("selected", "selected");
		$("[name='complaint.dealerId'] option:first").prop("selected", "selected");
		$("[name='complaint.rosterId'] option:first").prop("selected", "selected");
		
		
		$("#financeId").val(-1);
		$("[name='complaint.rosterId']").val(-1);
		$("[name='complaint.dealerId']").val(-1);
		
		$("[name='complaint.brandId']").val("-1");//品牌Id
		$("[name='complaint.brandName']").val("");//品牌
		$("[name='complaint.complainantName']").val("");//来电人
		$("[name='complaint.complainantPosition']").val("");//职务
		$("[name='complaint.jobNum']").val("");//工号
		$("#storeId").val(-1);//所在店
		$("[name='complaint.bankName']").val("");//所在行
		
		var complaintObjId = $('#complaintObjId input[name="complaint.complaintObjId"]:checked').val();
		if(complaintObjId && complaintObjId == 1){
			$("#complaintDealer").show();$("#complaintObjInfoOne").show();
			$("#complaintObjInfoTwo").show();
		}
		if(complaintObjId && complaintObjId == 2){
			$("#complaintFinance").show();$("#complaintObjInfoTwo").show();
		}
		if(complaintObjId && complaintObjId == 3){
			$("#rosterInfoOne").show();$("#rosterInfoTwo").show();
		}
	});
	initTools();
	initBank();
});

function initTools(){
	$('#createDate').datebox({    
		editable:false   
	});   
	$('#treatmentProcessTime').datebox({    
		editable:false   
	});   
	$('#solutionProcessTime').datebox({    
		editable:false   
	});   
	$('#trackProcessTime').datebox({    
		editable:false   
	});
	$('#createTime').timespinner({     
	    showSeconds: false
	});
	var d = new Date();
	$('#createTime').timespinner('setValue',d.getHours()+":"+d.getMinutes());


}
function loadProcessingName(id){
	var url = "../json/getProcessingById.do?callback=?&id=" + id;
	$.ajax({
		url:url,
		async:false,
		dataType:"jsonp",
		success:function(result){
			var data = result.data;
			$.each(data, function(i, item) {
				var option = "<option value="+item.id+">" + item.userName + "</option>";
				$("#processingId").append(option);
			});
		}
	});
}