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
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//执行保存操作
function doSave(){
	var value = document.getElementById("superviseArrears.distribid").value;
	if(value == "-1"){
		alert("请选择经销商");
		return false;
	}
	var value = document.getElementById("superviseArrears.financial_institution").value;
	if(value == ""){
		alert("请填写金融机构");
		return false;
	}
	var value = document.getElementById("superviseArrears.arrears_begin").value;
	if(value == ""){
		alert("请填写欠费起始时间");
		return false;
	}
	var value = document.getElementById("superviseArrears.arrears_end").value;
	if(value == ""){
		alert("请填写欠费结束时间");
		return false;
	}
	var value = document.getElementById("superviseArrears.arrears_money").value;
	if(value == ""){
		alert("请填写欠费金额");
		return false;
	}
	var value = document.getElementById("superviseArrears.arrears_user").value;
	if(value == ""){
		alert("请填写代付款联系人");
		return false;
	}
	var value = document.getElementById("superviseArrears.arrears_phone").value;
	if(value == ""){
		alert("请填写联系方式");
		return false;
	}
	var value = document.getElementById("superviseArrears.follow_date").value;
	if(value == ""){
		alert("请填写跟进时间");
		return false;
	}
	var value = document.getElementById("superviseArrears.follow_user").value;
	if(value == ""){
		alert("请填写跟进人员");
		return false;
	}
	var value = document.getElementById("superviseArrears.follow_result").value;
	if(value == ""){
		alert("请填写跟进结果");
		return false;
	}
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("superviseArrearsForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/superviseArrears.do?method=superviseArrearsList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">新增欠费台账</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/superviseArrears" styleId="superviseArrearsForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addAgreementBack">
<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>经销商：</td>
		<td class="codeCol">
			<form:select property="superviseArrears.distribid" styleId="superviseArrears.distribid">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="dealersOptions" label="label" value="value" />
			</form:select>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>金融机构</td>
		<td class="codeCol">
			<html:text property="superviseArrears.financial_institution" styleId="superviseArrears.financial_institution"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>欠费起始时间：</td>
		<td class="codeCol">
			<form:calendar property="superviseArrears.arrears_begin" styleId="superviseArrears.arrears_begin" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>欠费结束时间：</td>
		<td class="codeCol">
			<form:calendar property="superviseArrears.arrears_end" styleId="superviseArrears.arrears_end" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>欠费金额：</td>
		<td class="codeCol">
			<html:text property="superviseArrears.arrears_money" styleId="superviseArrears.arrears_money"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>待付款联系人：</td>
		<td class="codeCol">
			<html:text property="superviseArrears.arrears_user" styleId="superviseArrears.arrears_user"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>联系方式：</td>
		<td class="codeCol">
			<html:text property="superviseArrears.arrears_phone" styleId="superviseArrears.arrears_phone"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>跟进时间：</td>
		<td class="codeCol">
			<form:calendar property="superviseArrears.follow_date" styleId="superviseArrears.follow_date" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>跟进人员：</td>
		<td class="codeCol">
			<html:text property="superviseArrears.follow_user" styleId="superviseArrears.follow_user"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>跟进结果：</td>
		<td class="codeCol">
			<html:text property="superviseArrears.follow_result" styleId="superviseArrears.follow_result"></html:text>
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

</html:form>
<br/>
<div id="message" class="message" style="display:none"></div>

	</div>
</div>
</body>
</html>
