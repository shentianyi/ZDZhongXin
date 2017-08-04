<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>中都汽车金融监管系统</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/common.js"></script>
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script type="text/javascript">
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	
	var province=document.getElementsByName("agreementSend.province")[0].value;
	initProvince(province,$("#province"));
	
	var city=document.getElementsByName("agreementSend.city")[0].value;
	changeProvince(province,city,$('#city'),$("#province"));
	
	var county=document.getElementsByName("agreementSend.county")[0].value;
	changeProvince(city,county,$('#county'),$("#city"));
	changedealer();
}

//执行保存操作
function doSave(){
	var distribid = document.getElementById("agreementSend.distribid").value;
	if(distribid == ""){
		alert("请填写经销商");
		return false;
	}
	var agreement_num = document.getElementById("agreementSend.agreement_num").value;
	if(agreement_num == ""){
		alert("请填写协议编号");
		return false;
	}
	var agreement_date = document.getElementById("agreementSend.agreement_date").value;
	if(agreement_date == ""){
		alert("请填写协议邮寄时间");
		return false;
	}
	var financial_user = document.getElementById("agreementSend.financial_user").value;
	if(financial_user == ""){
		alert("请填写金融机构联系人");
		return false;
	}
	var financial_phone = document.getElementById("agreementSend.financial_phone").value;
	if(financial_phone == ""){
		alert("请填写联系方式");
		return false;
	}
	var send_address = document.getElementById("agreementSend.send_address").value;
	if(send_address == ""){
		alert("请填写邮寄地址");
		return false;
	}
	var back_date = document.getElementById("agreementSend.back_date").value;
	if(back_date == ""){
		alert("请填写预计回收时间");
		return false;
	}
	var agreementsigntime = document.getElementById("agreementSend.agreementsigntime").value;
	if(agreementsigntime == ""){
		alert("请填写协议签署日期");
		return false;
	}
	var agreementexpiretime = document.getElementById("agreementSend.agreementexpiretime").value;
	if(agreementexpiretime == ""){
		alert("请填写协议到期日");
		return false;
	}
	
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("agreementSendForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/agreementSend.do?method=agreementSendList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}

function initProvince(currentId,nextSelect){
	var url = "../json/findRegionByParentId.do?callback=?&parentId=0";
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setProvince(data,currentId,nextSelect);
	});
}
function setProvince(province,currentId,nextSelect){
	for(var i=0;i<province.length;i++){
		var option ;
		if(province[i].id == currentId){
			option = "<option selected='selected' value="+province[i].id+">" + province[i].name
			+ "</option>";
		}else{ 
			option = "<option value="+province[i].id+">" + province[i].name
			+ "</option>";
		 } 
		nextSelect.append(option);
	}
}
function changeProvince(id,currentId,nextSelect,currSelect){
	if(id==-1){
		document.forms[0].reset();
		return;
	}
	$(currSelect).val(id);
	var url = "../json/findRegionByParentId.do?callback=?&parentId="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setCity(data,currentId,nextSelect);
	});
}

function setCity(city,currentId,nextSelect){
	nextSelect.html("");
	var option = "<option value='-1' >请选择</option>";
	nextSelect.append(option);
	for(var i=0;i<city.length;i++){
		var option ;
		if(currentId==city[i].id){
			option = "<option selected='selected' value="+city[i].id+">" + city[i].name
			+ "</option>";
		}else{
			option = "<option value="+city[i].id+">" + city[i].name
			+ "</option>";
		}
		nextSelect.append(option);
	}
} 

function changeCity(id,nextSelect){
	$(nextSelect).val(id);
}
function changedealer(){
	var distribid = document.getElementById("agreementSend.distribid").value;
	var url = "../json/BankNameByDealerId.do?callback=?&dealerid="+distribid;
	$.getJSON(url, function(result) {
		var data = result.data;
		document.getElementById("bankName").value=data;
	});
}
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">修改协议发送</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/agreementSend" styleId="agreementSendForm" method="post" onsubmit="return false">
<html:hidden property="agreementSend.id" styleId="agreementSend.id"/>
<input type="hidden" name="method" id="method" value="updAgreementSend">
<html:hidden property="agreementSend.province" styleId="province1" />
<html:hidden property="agreementSend.city" styleId="city1" />
<html:hidden property="agreementSend.county" styleId="county1" />

<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>经销商：</td>
		<td class="codeCol">
			<form:select property="agreementSend.distribid" styleId="agreementSend.distribid" onchange="changedealer()">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="dealersOptions" label="label" value="value" />
			</form:select>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>金融机构</td>
		<td class="codeCol"><input type="text"id="bankName"readonly="readonly" /></td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>品牌：</td>
		<td class="codeCol">
			<form:select property="agreementSend.brandid" styleId="agreementSend.brandid">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="brandOptions" label="label" value="value" />
			</form:select>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>省：</td>
		<td class="codeCol">
			<form:select property="agreementSend.province" onchange="changeProvince(this.value,-1,$('#city'),$('#province1'))" styleId="province" >
				<html:option value="-1">请选择</html:option>
			</form:select>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>市：</td>
		<td class="codeCol">
			<form:select property="agreementSend.city" onchange="changeProvince(this.value,-1,$('#county'),$('#city1'))"
				styleId="city" >
				<html:option value="-1">请选择</html:option>
			</form:select>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>区：</td>
		<td class="codeCol">
			<form:select property="agreementSend.county" onchange="changeCity(this.value,$('#county1'))" styleId="county" >
				<html:option value="-1">请选择</html:option>
			</form:select>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>协议编号：</td>
		<td class="codeCol">
			<html:text property="agreementSend.agreement_num" styleId="agreementSend.agreement_num"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>协议邮寄时间：</td>
		<td class="codeCol">
			<form:calendar property="agreementSend.agreement_date" styleId="agreementSend.agreement_date" pattern="<%=PatternConstants.TIMESTAMP_SS.getCode()%>" readonly="true" />
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>金融机构联系人：</td>
		<td class="codeCol">
			<html:text property="agreementSend.financial_user" styleId="agreementSend.financial_user"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>联系方式：</td>
		<td class="codeCol">
			<html:text property="agreementSend.financial_phone" styleId="agreementSend.financial_phone"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>邮寄地址：</td>
		<td class="codeCol">
			<html:text property="agreementSend.send_address" styleId="agreementSend.send_address"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>预计回收时间：</td>
		<td class="codeCol">
			<form:calendar property="agreementSend.back_date" styleId="agreementSend.back_date" pattern="<%=PatternConstants.TIMESTAMP_SS.getCode()%>" readonly="true" />
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>协议签署日期：</td>
		<td class="codeCol">
			<form:calendar property="agreementSend.agreementsigntime" styleId="agreementSend.agreementsigntime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>协议到期日：</td>
		<td class="codeCol">
			<form:calendar property="agreementSend.agreementexpiretime" styleId="agreementSend.agreementexpiretime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
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
<div id="message" class="message" style="display:none"></div>
</html:form>

	</div>
</div>
</body>
</html>
