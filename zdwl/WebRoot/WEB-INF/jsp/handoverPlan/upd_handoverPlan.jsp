<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<!-- easyui -->
<link href="/easyui/themes/material/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	/* var deliverer=document.getElementById("deliverer").value;
	changeDeliverer(deliverer);
	var receiver=document.getElementById("receiver").value;
	changeReceiver(receiver); */
}

//执行保存操作
function doSave(){
	/* var value = document.getElementById("handoverPlan.dealerId").value;
	if(value == -1){
		alert("请选择经销商");
		return false;
	}
	var value = document.getElementById("handoverPlan.bindMerchant").value;
	if(value == ""){
		alert("请填写经销商绑定店");
		return false;
	}
	var value = document.getElementById("handoverPlan.bindBank").value;
	if(value == ""){
		alert("请填写经销商绑定行");
		return false;
	} */
	var deliverer=$("#deliverer").combobox("getValue");
 	if (!deliverer) {
		alert("请选择交付人");
		return false;
	}
	var value=""; 
	var arr = document.getElementsByName("handoverPlan.handoverNature");
	for(var i=0;i<arr.length;i++){
	        if(arr[i].checked){    
	          value=arr[i].value;
	        }
	    }
	if(value == ""){
		alert("请选择交付人交接性质");
		return false;
	}
	
	var receiver=$("#receiver").combobox("getValue");
 	if (!receiver) {
		alert("请选择接收人");
		return false;
	}else if(receiver==deliverer){
		alert("接收人不能与交付人一致，请重新选择");
		return false;
	}
	
	var value=""; 
	var arr = document.getElementsByName("handoverPlan.receiveNature");
	for(var i=0;i<arr.length;i++){
	        if(arr[i].checked){    
	          value=arr[i].value;
	        }
	    }
	if(value == ""){
		alert("请选择接收人接收性质");
		return false;
	}
	var value = document.getElementById("handoverPlan.missionDate").value;
	if(value == ""){
		alert("请选择接收人调入时间");
		return false;
	}
	setDealerID();
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("handoverPlanForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}
function setDealerID(){
	if(delivererDealerID.length>0){
		$("#delivererDealerID").val(delivererDealerID.substring(0,delivererDealerID.length-1));
	}
	if(receiverDealerID.length>0){
		$("#receiverDealerID").val(receiverDealerID.substring(0,receiverDealerID.length-1));
	}
}
//执行返回列表操作
function doReturn(){
	location = "<url:context/>/handoverPlan.do?method=handoverPlanPageList";
}

//执行表单重置操作
function doReset(){
	/* var dealerId=document.getElementById("handoverPlan.dealerId").value;
	changeDealer(dealerId); */
	var deliverer=document.getElementById("deliverer").value;
	changeDeliverer(deliverer);
	var receiver=document.getElementById("receiver").value;
	changeReceiver(receiver);
	document.forms[0].reset();
}

var delivererDealerID="";
var receiverDealerID="";
function changeDealer(dealers,type) {
	if(dealers==null){
		return;
	}
	if(type==1){
		delivererDealerID="";
	}else if(type==2){
		receiverDealerID="";
	}else{
		delivererDealerID="";
		receiverDealerID="";
	}
	for(var i=0;i<dealers.length;i++){
		var url = "../json/getDealerByIdJsonAction.do?callback=?&id="+dealers[i].dealerId;
		$.getJSON(url, function(result) {
			var data = result.data;
			console.info(data);
			setDealer(data[0],type);
		});
	}
}
function setDealer(dealer,type){
	if(type==1){
		delivererDealerID=delivererDealerID+dealer.id+",";
		$("#ddealer").val($("#ddealer").val()+"  "+dealer.dealerName);
		$("#dbrand").val($("#dbrand").val()+"  "+dealer.brand);
		$("#daddress").val($("#daddress").val()+"  "+dealer.address);
		var brand=dealer.bankName;
		var brands=brand.split("/");
		if(brands!=null){
			if(brands.length==1){
				$("#dheadBank").val($("#dheadBank").val()+"  "+brands[0]);
			}else if(brands.length==2){
				$("#dheadBank").val($("#dheadBank").val()+"  "+brands[0]);
				$("#dbranch").val( $("#dbranch").val()+"  "+brands[1]);
			}else if(brands.length==3){
				$("#dheadBank").val($("#dheadBank").val()+"  "+brands[0]);
				$("#dbranch").val( $("#dbranch").val()+"  "+brands[1]);
				$("#dsubbranch").val($("#dsubbranch").val()+"  "+brands[2]);
			}
		}
		$("#dprovince").val($("#dprovince").val()+"  "+dealer.province);
		$("#dcity").val($("#dcity").val()+"  "+dealer.city);
		$('#dequipmentProvide').val($('#dequipmentProvide').val()+"  "+dealer.equipmentProvide);
		
	}else if(type==2){
		receiverDealerID=receiverDealerID+dealer.id+",";
		$("#cdealer").val($("#cdealer").val()+"  "+dealer.dealerName);
		$("#cbrand").val($("#cbrand").val()+"  "+dealer.brand);
		$("#caddress").val($("#caddress").val()+"  "+dealer.address);
		var brand=dealer.bankName;
		var brands=brand.split("/");
		if(brands!=null){
			if(brands.length==1){
				$("#cheadBank").val($("#cheadBank").val()+"  "+brands[0]);
			}else if(brands.length==2){
				$("#cheadBank").val($("#cheadBank").val()+"  "+brands[0]);
				$("#cbranch").val( $("#cbranch").val()+"  "+brands[1]);
			}else if(brands.length==3){
				$("#cheadBank").val($("#cheadBank").val()+"  "+brands[0]);
				$("#cbranch").val( $("#cbranch").val()+"  "+brands[1]);
				$("#csubbranch").val($("#csubbranch").val()+"  "+brands[2]);
			}
		}
		$("#cprovince").val($("#cprovince").val()+"  "+dealer.province);
		$("#ccity").val($("#ccity").val()+"  "+dealer.city);
		$('#cequipmentProvide').val($('#cequipmentProvide').val()+"  "+dealer.equipmentProvide);
		
	}
}
function changeDeliverer(id) {
	cleanDeliver();
	if(id==-1){
		return;
	}
	var url = "../json/getSupervisorByRepositoryId.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setDeliverer(data[0]);
	});
	getDealerBySupervisor(id,1);
}
function setDeliverer(supervisor){
	$("#idNumber").val(supervisor.idNumber);
	$("#gender").val(supervisor.gender);
	$("#staffNo").val(supervisor.staffNo);
	$("#entryDate").val(supervisor.entryTimeStr);
	$("#contactNumber").val(supervisor.selfTelephone);
	
}

function getDealerBySupervisor(id,type) {
	if(id==-1){
		return;
	}
	var url = "../json/getDealerByRepositoryId.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		changeDealer(data,type);
	});
}

function changeReceiver(id) {
	cleanReceiver();
	if(id==-1){
		return;
	}
	var url = "../json/getSupervisorByRepositoryId.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setReceiver(data[0]);
	});
	getDealerBySupervisor(id,2);
}
function setReceiver(supervisor){
	$("#ridNumber").val(supervisor.idNumber);
	$("#rgender").val(supervisor.gender);
	$("#rstaffNo").val(supervisor.staffNo);
	$("#rentryDate").val(supervisor.entryTimeStr);
	$("#rcontactNumber").val(supervisor.selfTelephone);
}
function cleanDeliver(){
	$("#idNumber").val("");
	$("#gender").val("");
	$("#staffNo").val("");
	$("#entryDate").val("");
	$("#contactNumber").val("");
	$("#ddealer").val("");
	$("#dbrand").val("");
	$("#daddress").val("");
	$("#dheadBank").val("");
	$("#dbranch").val("");
	$("#dsubbranch").val("");
	$("#dprovince").val("");
	$("#dcity").val("");
	$('#dequipmentProvide').val("");
}
function cleanReceiver(){
	$("#ridNumber").val("");
	$("#rgender").val("");
	$("#rstaffNo").val("");
	$("#rentryDate").val("");
	$("#rcontactNumber").val("");
	$("#cdealer").val("");
	$("#cbrand").val("");
	$("#caddress").val("");
	$("#cheadBank").val("");
	$("#cbranch").val("");
	$("#csubbranch").val("");
	$("#cprovince").val("");
	$("#ccity").val("");
	$('#cequipmentProvide').val("");
}
function init() {
	$("#deliverer").combobox({
		onHidePanel : function() {// 验证输入的值，如果不存在下拉列表 则置空
			var newValue = $(this).combobox('getValue');
			var data = $(this).combobox('getData');
			var flag = false;
			if (data != null && data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					if (newValue == data[i].value) {
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				$(this).combobox('clear');
			} else {
				changeDeliverer(newValue);
			}

		}
	});
	var draftValue = $("#deliverer").combobox('getValue');
	if (draftValue) {
		changeDeliverer(draftValue);
	}
	$("#receiver").combobox({
		onHidePanel : function() {// 验证输入的值，如果不存在下拉列表 则置空
			var newValue = $(this).combobox('getValue');
			var data = $(this).combobox('getData');
			var flag = false;
			if (data != null && data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					if (newValue == data[i].value) {
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				$(this).combobox('clear');
			} else {
				changeReceiver(newValue);
			}

		}
	});
	var draftValue = $("#receiver").combobox('getValue');
	if (draftValue) {
		changeReceiver(draftValue);
	}
}

$(function(){
	init();
});
</script>
<style type="text/css">
#f_file{
 	left: 0;
    opacity: 0;
    position: absolute;
    top: -100;
    z-index: 2;
    width: 100%;
    height: 100%;
}
</style>
</head>
<body  onLoad="doLoad()">

<br/>

<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/handoverPlan" styleId="handoverPlanForm" method="post" onsubmit="return false" >
<input type="hidden" name="method" id="method" value="updHandoverPlan"/>
<html:hidden property="handoverPlan.id" styleId="handoverPlan.id" />
<html:hidden property="handoverPlan.isEdit" styleId="handoverPlan.isEdit" />
<html:hidden property="handoverPlan.nextApproval" styleId="handoverPlan.nextApproval" />
<html:hidden property="handoverPlan.approveStatus" styleId="handoverPlan.approveStatus" />
<html:hidden property="handoverPlan.createUser" styleId="handoverPlan.createUser" />
<html:hidden property="handoverPlan.createTime" styleId="handoverPlan.createTime" />
<html:hidden property="handoverPlan.lastModifyUser" styleId="handoverPlan.lastModifyUser" />
<html:hidden property="handoverPlan.lastModifyTime" styleId="handoverPlan.lastModifyTime" />
<%-- <html:hidden  property="handoverPlan.delivererDealer" styleId="ddealer"/>
<html:hidden  property="handoverPlan.receiverDealer" styleId="cdealer"/> --%>
<html:hidden property="handoverPlan.delivererDealerID" styleId="delivererDealerID" />
<html:hidden property="handoverPlan.receiverDealerID" styleId="receiverDealerID" />
<table class="formTable">
	<tr class="formTitle">
		<td colspan="4">修改轮岗计划</td>
	</tr>
	<tr>
		<td colspan="4"> 
			<table >
				<tr>
					<td class="nameCol"><font color="#FF0000">*</font>交付人：</td>
					<td class="codeCol">
						 <%-- <form:select property="handoverPlan.deliverer"
								styleId="handoverPlan.deliverer" onchange="changeDeliverer(this.value)">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="supervisors" label="label"
									value="value" />
						</form:select> --%>
						<select style="width:99%" id="deliverer" name="handoverPlan.deliverer" onchange="changeDeliverer(this.value)" >
							<c:forEach items="${dealerSupervisors }" var="row">
								<option <c:if test="${handoverPlanForm.handoverPlan.deliverer==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
								<%-- <option value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> --%>
							</c:forEach>
						</select>
					</td> 
					<td class="nameCol">性别：</td>
					<td class="codeCol"><input type="text"  id="gender" disabled="disabled"/></td>
					<td class="nameCol">身份证号：</td>
					<td class="codeCol"><input type="text"  id="idNumber" disabled="disabled"/></td>
				</tr>
				 <tr>
				 	<td class="nameCol">员工工号：</td>
					<td class="codeCol"><input type="text"  id="staffNo" disabled="disabled"/></td>
					<td class="nameCol">调入时间：</td>
					<td class="codeCol">
						<input type="text"  id="entryDate" disabled="disabled"/>
					</td> 
					<td class="nameCol">联系方式：</td>
					<td class="codeCol"><input type="text"  id="contactNumber" disabled="disabled"/></td>
				</tr>
				<tr>
					<td class="nameCol">经销商：</td>
					<td class="codeCol">
						<html:text  property="handoverPlan.delivererDealer" styleId="ddealer" readonly="true"/>
					</td>
					<td class="nameCol">经销商品牌：</td>
					<td class="codeCol"><input type="text"  id="dbrand" disabled="true" /></td>
					<td class="nameCol">经销商地址：</td>
					<td class="codeCol"><input type="text"  id="daddress" disabled="true" /></td>
				</tr>
				<tr>
					<td class="nameCol">经销商所在省：</td>
					<td class="codeCol"><input type="text"  id="dprovince"disabled="true" /></td>
					<td class="nameCol">经销商所在市：</td>
					<td class="codeCol"><input type="text" id="dcity"disabled="true" /></td>
					<td class="nameCol">设备提供方：</td>
					<td class="codeCol"><input type="text" id="dequipmentProvide"disabled="true" /></td>
				</tr>
				<tr>
					<td class="nameCol">合作银行（总）：</td>
					<td class="codeCol"><input type="text"  id="dheadBank"disabled="true" /></td>
					<td class="nameCol">合作银行（分）：</td>
					<td class="codeCol"><input type="text" id="dbranch"disabled="true" /></td>
					<td class="nameCol">合作银行（支）：</td>
					<td class="codeCol"><input type="text" id="dsubbranch"disabled="true" /></td>
				</tr>
				<%-- <tr>
					<td class="nameCol">绑定店：</td>
					<td class="codeCol"><html:text property="handoverPlan.bindMerchant" styleId="handoverPlan.bindMerchant"/></td>
					<td class="nameCol">绑定行：</td>
					<td class="codeCol"><html:text property="handoverPlan.bindBank" styleId="handoverPlan.bindBank"/></td>
				</tr>
				<tr>
					<td class="nameCol">店方要求：</td>
					<td class="codeCol" colspan="5"><html:text style="width:700px" property="handoverPlan.merchantDemand" styleId="handoverPlan.merchantDemand"/></td>
				</tr> --%>
				<tr>
					<td class="nameCol"><font color="#FF0000">*</font>交接性质：</td>
					<td class="nameCol"style="text-align: left;" colspan="3" >
						<form:radios  property="handoverPlan.handoverNature" collection="handoverNatures" styleId="handoverPlan.handoverNature" />
					</td>
				</tr>
				
				<tr>
					<td class="nameCol"><font color="#FF0000">*</font>接收人：</td>
					<td class="codeCol">
						 <%-- <form:select property="handoverPlan.receiver"
							styleId="handoverPlan.receiver" onchange="changeReceiver(this.value)">
							<html:option value="-1">请选择</html:option>
							<html:optionsCollection name="supervisors" label="label"
								value="value" />
						</form:select> --%>
						<select style="width:99%" id="receiver" name="handoverPlan.receiver" onchange="changeReceiver(this.value)" >
						<c:forEach items="${supervisors }" var="row">
							<option <c:if test="${handoverPlanForm.handoverPlan.receiver==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
							<%-- <option value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> --%>
						</c:forEach>
						</select>
					</td>
					<td class="nameCol">性别：</td>
					<td class="codeCol"><input type="text"  id="rgender" disabled="disabled"/></td>
					<td class="nameCol">身份证号：</td>
					<td class="codeCol"><input type="text"  id="ridNumber" disabled="disabled"/></td>
				</tr>
				 <tr>
				 	<td class="nameCol">员工工号：</td>
					<td class="codeCol"><input type="text"  id="rstaffNo" disabled="disabled"/></td>
					<td class="nameCol">调入时间：</td>
					<td class="codeCol">
						<input type="text"  id="rentryDate" disabled="disabled"/>
					</td> 
					<td class="nameCol">联系方式：</td>
					<td class="codeCol"><input type="text"  id="rcontactNumber" disabled="disabled"/></td>
				</tr>
				<tr>
					<td class="nameCol">经销商：</td>
					<td class="codeCol">
						<html:text  property="handoverPlan.receiverDealer" styleId="cdealer" readonly="true"/>
					</td>
					<td class="nameCol">经销商品牌：</td>
					<td class="codeCol"><input type="text"  id="cbrand" disabled="true" /></td>
					<td class="nameCol">经销商地址：</td>
					<td class="codeCol"><input type="text"  id="caddress" disabled="true" /></td>
				</tr>
				<tr>
					<td class="nameCol">经销商所在省：</td>
					<td class="codeCol"><input type="text"  id="cprovince"disabled="true" /></td>
					<td class="nameCol">经销商所在市：</td>
					<td class="codeCol"><input type="text" id="ccity"disabled="true" /></td>
					<td class="nameCol">设备提供方：</td>
					<td class="codeCol"><input type="text" id="cequipmentProvide"disabled="true" /></td>
				</tr>
				<tr>
					<td class="nameCol">合作银行（总）：</td>
					<td class="codeCol"><input type="text"  id="cheadBank"disabled="true" /></td>
					<td class="nameCol">合作银行（分）：</td>
					<td class="codeCol"><input type="text" id="cbranch"disabled="true" /></td>
					<td class="nameCol">合作银行（支）：</td>
					<td class="codeCol"><input type="text" id="csubbranch"disabled="true" /></td>
				</tr>
				<%-- <tr>
					<td class="nameCol">绑定店：</td>
					<td class="codeCol"><html:text property="handoverPlan.bindMerchant" styleId="handoverPlan.bindMerchant"/></td>
					<td class="nameCol">绑定行：</td>
					<td class="codeCol"><html:text property="handoverPlan.bindBank" styleId="handoverPlan.bindBank"/></td>
				</tr>
				<tr>
					<td class="nameCol">店方要求：</td>
					<td class="codeCol" colspan="5"><html:text style="width:700px" property="handoverPlan.merchantDemand" styleId="handoverPlan.merchantDemand"/></td>
				</tr> --%>
				<tr>
					<td class="nameCol"><font color="#FF0000">*</font>接收性质：</td>
					<td class="nameCol"style="text-align: left;" colspan="3">
						<form:radios  property="handoverPlan.receiveNature" collection="receiveNatures" styleId="handoverPlan.receiveNature"/>
					</td>
					<td class="nameCol"><font color="#FF0000">*</font>调入时间：</td>
					<td class="codeCol">
						<form:calendar property="handoverPlan.missionDate" styleId="handoverPlan.missionDate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
					</td>
				</tr>
				<tr>
					<td class="nameCol">备注：</td>
					<td class="codeCol"colspan="5" style="text-align: left;">
						<html:text style="width:700px" property="handoverPlan.remark" styleId="handoverPlan.remark"/>
					</td>
				</tr>
				<tr></tr>
			</table>
		</td>
	</tr>
	<tr class="formTableFoot">
		<td colspan="4" align="center">
			<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
			<button class="formButton" onClick="doReset()">重&nbsp;置</button>&nbsp;
			<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
		</td>
	</tr>
</table>
<br/>
<div class="message" id="message" style="display:none"></div>
</html:form>
	
	</div>
</div>
</body>
</html>
