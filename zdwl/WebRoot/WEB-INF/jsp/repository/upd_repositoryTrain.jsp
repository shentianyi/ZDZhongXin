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
<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>

<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/public.css" />
<script src="/js/thumbpage/thumbpage.js"></script>
<script type="text/javascript" src="/js/jquery.divscroll.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	var role=<%=request.getAttribute("role")%>;
	if(role!=7){
		document.getElementById("repositoryTrain.startTime").disabled=true;
		document.getElementById("repositoryTrain.endTime").disabled=true;
		document.getElementById("repositoryTrain.trainSpecialist").disabled=true;
		document.getElementById("repositoryTraindealer").disabled=true;
		document.getElementById("repositoryTrain.trainer").disabled=true;
		document.getElementById("repositoryTrain.contactNumber").disabled=true;
		document.getElementById("repositoryTrain.staffNo").disabled=true;
		document.getElementById("repositoryTrain.trainingContent").disabled=true;
		document.getElementById("repositoryTrain.trainingContentBasic").disabled=true;
		document.getElementById("repositoryTrain.assessmentComputerScore").disabled=true;
		document.getElementById("repositoryTrain.assessmentTheoryScore").disabled=true;
		document.getElementById("repositoryTrain.assessmentCommunicateScore").disabled=true;
		document.getElementById("repositoryTrain.assessmentScore").disabled=true;
		document.getElementById("repositoryTrain.remark").disabled=true;
	}
	var dealerId=document.getElementById("repositoryTraindealer").value;
	changeDealer(dealerId);
}

//执行保存操作
function doSave(){
	var role=<%=request.getAttribute("role")%>;
	if(role==7){
		var value = document.getElementById("repositoryTrain.startTime").value;
		if(value == ""){
			alert("请选择培训开始时间");
			return false;
		}
		var value = document.getElementById("repositoryTrain.endTime").value;
		if(value == ""){
			alert("请选择培训结束时间");
			return false;
		}
		var value = document.getElementById("repositoryTrain.trainSpecialist").value;
		if(value == ""){
			alert("请填写培训专员");
			return false;
		}
		var value = document.getElementById("repositoryTrain.trainer").value;
		if(value == ""){
			alert("请填写培训人");
			return false;
		}
		var value = document.getElementById("repositoryTrain.contactNumber").value;
		if(value == ""){
			alert("请填写培训人联系电话");
			return false;
		}
		var value = document.getElementById("repositoryTrain.trainingContent").value;
		if(value == ""){
			alert("请填写培训内容（业务）");
			return false;
		}
		var value = document.getElementById("repositoryTrain.trainingContentBasic").value;
		if(value == ""){
			alert("请填写培训内容（入职基础知识）");
			return false;
		}
		var value = document.getElementById("repositoryTrain.assessmentComputerScore").value;
		if(value == ""){
			alert("请填写考核总评（电脑水平）");
			return false;
		}
		var value = document.getElementById("repositoryTrain.assessmentTheoryScore").value;
		if(value == ""){
			alert("请填写考核总评（理论知识）");
			return false;
		}
		var value = document.getElementById("repositoryTrain.assessmentCommunicateScore").value;
		if(value == ""){
			alert("考核总评（沟通及其它）");
			return false;
		}
		var value = document.getElementById("repositoryTrain.assessmentScore").value;
		if(value == "0.0"){
			alert("请填写考核得分");
			return false;
		}
	}
	
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("repositoryForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/repository.do?method=repositoryPageList";
}
//执行表单重置操作
function doReset(){
	document.forms[0].reset();
	var dealerId=document.getElementById("repositoryTraindealer").value;
	changeDealer(dealerId);
}
function changeDealer(id) {
	if(id==-1){
		document.forms[0].reset();
		return;
	}
	var url = "../json/getDealerByIdJsonAction.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setDealer(data[0]);
	});
}

function setDealer(dealer){
	$("#brand").val(dealer.brand);
	$("#address").val(dealer.address);
	var brand=dealer.bankName;
	var brands=brand.split("/");
	$("#headBank").val(brands[0]);
	$("#branch").val(brands[1]);
	$("#subbranch").val(brands[2]);
}
function changeSupervisor(id) {
	if(id==-1){
		document.forms[0].reset();
		return;
	}
	var url = "../json/getSupervisorById.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setSupervisor(data[0]);
	});
}
function setSupervisor(supervisor){
	$("#idNumber").val(supervisor.idNumber);
	$("#gender").val(supervisor.gender);
	$("#birthday").val(supervisor.birthdayStr);
	$("#fertilityState").val(supervisor.fertilityState);
	$("#nation").val(supervisor.nation);
	$("#educationBackground").val(supervisor.educationBackground);
	
}

function init() {
	$("#repositoryTraindealer").combobox({
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
				changeDealer(newValue);
			}

		}
	});
	var draftValue = $("#repositoryTraindealer").combobox('getValue');
	if (draftValue) {
		changeDealer(draftValue);
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
	
<html:form action="/repository" styleId="repositoryForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="updRepositoryTrain"/>
<html:hidden property="repository.id" styleId="repository.id" />
<html:hidden property="repositoryTrain.id" styleId="repositoryTrain.id" />
<html:hidden property="repositoryTrain.repositoryId" styleId="repositoryTrain.repositoryId" />
<table class="formTable">
	<tr class="formTitle">
		<td colspan="4">修改储备库信息</td>
	</tr>
	<tr>
		<td colspan="4"> 
			<table>
				<tr>
					<td class="nameCol" colspan="6" style="text-align: left;">二、培训信息(监管员管理部培训专员填写)</td>
				</tr>
				<tr>
					<td class="nameCol" style="width: 16%;"><font color="#FF0000">*</font>培训开始时间：</td>
					<td class="codeCol" style="width: 16%;">
						<form:calendar style="width: 90%;" property="repositoryTrain.startTime" styleId="repositoryTrain.startTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
					</td>
					<td class="nameCol"style="width: 16%;"><font color="#FF0000">*</font>培训结束时间：</td>
					<td class="codeCol"style="width: 16%;">
						<form:calendar style="width: 90%;" property="repositoryTrain.endTime" styleId="repositoryTrain.endTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
					</td>
					<td class="nameCol" style="width: 16%;"><font color="#FF0000">*</font>培训专员：</td>
					<td class="codeCol"style="width: 17%;"><html:text style="width: 200px;" property="repositoryTrain.trainSpecialist" styleId="repositoryTrain.trainSpecialist"/></td>
				</tr>
				<tr>
					<td class="nameCol" style="width: 16%;"><font color="#FF0000">*</font>经销商：</td>
					<td class="codeCol" style="width: 16%;">
						<select id="repositoryTraindealer"
							name="repositoryTrain.dealer" onchange="changeDealer(this.value)">
							<option value="-1">请选择</option>
							<c:forEach items="${dealers}" var="row">
										<option <c:if test="${repositoryForm.repositoryTrain.dealer==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
									</c:forEach>
						<select></td>
	
					</td>
					<td class="nameCol" style="width:16%;">经销商品牌：</td>
					<td class="codeCol"style="width: 16%;"><input type="text" style="width: 200px;"  id="brand" disabled="true" /></td>
					<td class="nameCol" style="width: 16%;">经销商地址：</td>
					<td class="codeCol"style="width: 17%;"><input type="text" style="width: 200px;"  id="address" disabled="true" /></td>
				</tr>
				<tr>
					<td class="nameCol" style="width:16%;">合作银行（总）：</td>
					<td class="codeCol"style="width: 16%;"><input type="text"  style="width: 200px;" id="headBank"disabled="true" /></td>
					<td class="nameCol" style="width: 16%;">合作银行（分）：</td>
					<td class="codeCol"style="width:16%;"><input type="text" style="width: 200px;" id="branch"disabled="true" /></td>
					<td class="nameCol" style="width: 16%;">合作银行（支）：</td>
					<td class="codeCol"style="width: 17%;"><input type="text" style="width: 200px;" id="subbranch"disabled="true" /></td>
				</tr>
				<tr>
					<td class="nameCol" style="width: 16%;"><font color="#FF0000">*</font>培训人：</td>
					<td class="codeCol"style="width:16%;"><html:text style="width: 200px;" property="repositoryTrain.trainer" styleId="repositoryTrain.trainer"/></td>
					<td class="nameCol" style="width: 16%;"><font color="#FF0000">*</font>联系方式：</td>
					<td class="codeCol"style="width: 16%;"><html:text style="width: 200px;" property="repositoryTrain.contactNumber" styleId="repositoryTrain.contactNumber"/></td>
					<td class="nameCol" style="width: 16%;"><font color="#FF0000">*</font>员工工号：</td>
					<td class="codeCol"style="width: 17%;"><html:text style="width: 200px;" property="repositoryTrain.staffNo" styleId="repositoryTrain.staffNo"/></td>
				</tr>
				<tr>
					<td class="nameCol" style="width:16%;"><font color="#FF0000">*</font>培训内容（业务）：</td>
					<td class="codeCol" colspan="6" style="text-align: left;">
						<html:text style="width:700px" property="repositoryTrain.trainingContent" styleId="repositoryTrain.trainingContent"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol" style="width: 20%;"><font color="#FF0000">*</font>培训内容(入职基础知识):</td>
					<td class="codeCol"  colspan="6" style="text-align: left;">
						<html:text style="width:700px" property="repositoryTrain.trainingContentBasic" styleId="repositoryTrain.trainingContentBasic"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol"style="width: 16%;"><font color="#FF0000">*</font>考核总评(电脑水平):</td>
					<td class="codeCol"style="width: 16%;"><html:text style="width: 200px;" property="repositoryTrain.assessmentComputerScore" styleId="repositoryTrain.assessmentComputerScore"/></td>
					<td class="nameCol"style="width: 17%;"><font color="#FF0000">*</font>考核总评(理论知识):</td>
					<td class="codeCol"style="width: 15%;"><html:text style="width: 200px;" property="repositoryTrain.assessmentTheoryScore" styleId="repositoryTrain.assessmentTheoryScore"/></td>
					<td class="nameCol"style="width: 18%;"><font color="#FF0000">*</font>考核总评(沟通及其它):</td>
					<td class="codeCol"style="width: 15%;"><html:text style="width: 200px;" property="repositoryTrain.assessmentCommunicateScore" styleId="repositoryTrain.assessmentCommunicateScore"/></td>
				</tr>
				<tr>
					<td class="nameCol"style="width: 16%;"><font color="#FF0000">*</font>考核得分：</td>
					<td class="codeCol"  style="width: 220px;"><html:text style="width: 200px;" property="repositoryTrain.assessmentScore" styleId="repositoryTrain.assessmentScore"/></td>
					<td class="nameCol">备注：</td>
					<td class="codeCol"colspan="3" ><html:text style="width:400px" property="repositoryTrain.remark" styleId="repositoryTrain.remark"/></td>
				</tr>
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
