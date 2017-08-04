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
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	var dealerId=document.getElementById("transfer.dealerId").value;
	changeDealer(dealerId);
	getHandoverLogByDealer(dealerId);
}

//执行保存操作
function doSave(){
	var value = document.getElementById("transfer.dealerId").value;
	if(value == -1){
		alert("请选择经销商");
		return false;
	}
	var value = document.getElementById("transfer.workCardNumber").value;
	if(value == ""){
		alert("请填写工牌个数");
		return false;
	}
	var value = document.getElementById("transfer.identifyNumber").value;
	if(value == ""){
		alert("请填写标识个数");
		return false;
	}
	var value = document.getElementById("transfer.grantDate").value;
	if(value == ""){
		alert("请选择发放日期");
		return false;
	}
	var value = document.getElementById("transfer.qqNumber").value;
	if(value == ""){
		alert("请填写QQ号码");
		return false;
	}
	var value = document.getElementById("transfer.qqPassword").value;
	if(value == ""){
		alert("请填写QQ密码");
		return false;
	}
	var value = document.getElementById("transfer.qqPasswordProtect").value;
	if(value == ""){
		alert("请填写QQ密保");
		return false;
	}


	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("transferForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/transfer.do?method=transferPageList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
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
	getHandoverLogByDealer(id);
}
function setDealer(dealer){
	$("#brand").val(dealer.brand);
	$("#address").val(dealer.address);
	var brand=dealer.bankName;
	var brands=brand.split("/");
	$("#headBank").val(brands[0]);
	$("#branch").val(brands[1]);
	$("#subbranch").val(brands[2]);
	$("#province").val(dealer.province);
	$("#city").val(dealer.city);
	$('#equipmentProvide').val(dealer.equipmentProvide);
}
function getHandoverLogByDealer(id) {
	if(id==-1){
		document.forms[0].reset();
		return;
	}
	var url = "../json/getHandoverLogByDealerId.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setHandoverLog(data[0]);
	});
}
function setHandoverLog(handoverLog){
	$("#bindMerchant").val(handoverLog.bindMerchant);
	$("#bindBank").val(handoverLog.bindBank);
	$("#merchantDemand").val(handoverLog.merchantDemand);
}
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
	
<html:form action="/transfer" styleId="transferForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="addTransfer"/>

<table class="formTable">
	<tr class="formTitle">
		<td colspan="4">新增调动表记录</td>
	</tr>
	<tr>
		<td colspan="4"> 
			<table >
				<tr>
					<td class="nameCol">经销商：</td>
					<td class="codeCol">
						<form:select property="transfer.dealerId"
							styleId="transfer.dealerId" onchange="changeDealer(this.value)">
							<html:option value="-1">请选择</html:option>
							<html:optionsCollection name="dealers" label="label" value="value" />
						</form:select></td>
					</td>
					<td class="nameCol">经销商品牌：</td>
					<td class="codeCol"><input type="text"  id="brand" disabled="true" /></td>
					<td class="nameCol">经销商地址：</td>
					<td class="codeCol"><input type="text"  id="address" disabled="true" /></td>
				</tr>
				<tr>
					<td class="nameCol">合作银行（总）：</td>
					<td class="codeCol"><input type="text"  id="headBank"disabled="true" /></td>
					<td class="nameCol">合作银行（分）：</td>
					<td class="codeCol"><input type="text" id="branch"disabled="true" /></td>
					<td class="nameCol">合作银行（支）：</td>
					<td class="codeCol"><input type="text" id="subbranch"disabled="true" /></td>
				</tr>
				<tr>
					<td class="nameCol">经销商所在省：</td>
					<td class="codeCol"><input type="text"  id="province"disabled="true" /></td>
					<td class="nameCol">经销商所在市：</td>
					<td class="codeCol"><input type="text" id="city"disabled="true" /></td>
					<td class="nameCol">绑定店：</td>
					<td class="codeCol"><input type="text" id="bindMerchant" disabled="true" /></td>
				</tr>
				<tr>
					<td class="nameCol">绑定行：</td>
					<td class="codeCol"><input type="text" id="bindBank" disabled="true" /></td>
					<td class="nameCol">店方要求：</td>
					<td class="codeCol" colspan="3"><input type="text" id="merchantDemand" disabled="true" style="width:400px"  /></td>
				</tr>
				<tr>
					<td class="nameCol">工牌个数：</td>
					<td class="codeCol">
						<html:text  property="transfer.workCardNumber" styleId="transfer.workCardNumber"/>
					</td>
					<td class="nameCol">标识个数：</td>
					<td class="codeCol">
						<html:text  property="transfer.identifyNumber" styleId="transfer.identifyNumber"/>
					</td>
					<td class="nameCol">发放日期：</td>
					<td class="codeCol">
						<form:calendar property="transfer.grantDate" styleId="transfer.grantDate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />
					</td>
				</tr>
				<tr>
					<td class="nameCol">QQ号码：</td>
					<td class="codeCol">
						<html:text property="transfer.qqNumber" styleId="transfer.qqNumber"/>
					</td>
					<td class="nameCol">密码：</td>
					<td class="codeCol">
						<html:text property="transfer.qqPassword" styleId="transfer.qqPassword"/>
					</td>
					<td class="nameCol">密保：</td>
					<td class="codeCol">
						<html:text property="transfer.qqPasswordProtect" styleId="transfer.qqPasswordProtect"/>
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
