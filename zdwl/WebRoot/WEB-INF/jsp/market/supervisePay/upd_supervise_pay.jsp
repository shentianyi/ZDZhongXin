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
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script type="text/javascript">
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//执行保存操作
function doSave(){
	var value = document.getElementById("supervisePay.distribid").value;
	if(value == "-1"){
		alert("请选择经销商");
		return false;
	}
	var value = document.getElementById("supervisePay.financial_institution").value;
	if(value == ""){
		alert("请填写金融机构");
		return false;
	}
	var value = document.getElementById("supervisePay.payment_period").value;
	if(value == ""){
		alert("请填写缴费账期");
		return false;
	}
	var value = document.getElementById("supervisePay.pay_money").value;
	if(value == ""){
		alert("请填写缴费金额");
		return false;
	}
	var value = document.getElementById("supervisePay.already_pay").value;
	if(value == ""){
		alert("请填写已缴费金额");
		return false;
	}
	var value = document.getElementById("supervisePay.not_pay").value;
	if(value == ""){
		alert("请填写未缴费金额");
		return false;
	}
	var value = document.getElementById("supervisePay.change_payment").value;
	if(value == ""){
		alert("请填写变费补差金额");
		return false;
	}
	var value = document.getElementById("supervisePay.balance_payment").value;
	if(value == ""){
		alert("请填写变费余额");
		return false;
	}
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("supervisePayForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/supervisePay.do?method=supervisePayList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">修改缴费台账</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/supervisePay" styleId="supervisePayForm" method="post" onsubmit="return false">
<html:hidden property="supervisePay.id" styleId="supervisePay.id"/>
<input type="hidden" name="method" id="method" value="updSupervisePay">

<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>经销商：</td>
		<td class="codeCol">
			<form:select property="supervisePay.distribid" styleId="supervisePay.distribid">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="dealersOptions" label="label" value="value" />
			</form:select>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>金融机构</td>
		<td class="codeCol">
			<html:text property="supervisePay.financial_institution" styleId="supervisePay.financial_institution"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>监管费到期日：</td>
		<td class="codeCol">
			<form:calendar property="supervisePay.supervise_expire" styleId="supervisePay.supervise_expire" pattern="<%=PatternConstants.TIMESTAMP_SS.getCode()%>" readonly="true" />
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>缴费账期：</td>
		<td class="codeCol">
			<html:text property="supervisePay.payment_period" styleId="supervisePay.payment_period"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>缴费金额：</td>
		<td class="codeCol">
			<html:text property="supervisePay.pay_money" styleId="supervisePay.pay_money"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>已缴：</td>
		<td class="codeCol">
			<html:text property="supervisePay.already_pay" styleId="supervisePay.already_pay"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>未缴：</td>
		<td class="codeCol">
			<html:text property="supervisePay.not_pay" styleId="supervisePay.not_pay"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>变费补差：</td>
		<td class="codeCol">
			<html:text property="supervisePay.change_payment" styleId="supervisePay.change_payment"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>变费余额：</td>
		<td class="codeCol">
			<html:text property="supervisePay.balance_payment" styleId="supervisePay.balance_payment"></html:text>
		</td>
		<td class="nameCol"></td>
		<td class="codeCol"></td>
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
