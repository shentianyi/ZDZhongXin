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
	
	var trade_name = document.getElementById("systemSupervise.trade_name").value;
	if(trade_name == ""){
		alert("请填写店名");
		return false;
	}
	var bankid = document.getElementById("systemSupervise.bankid").value;
	if(bankid == ""){
		alert("请填写金融机构");
		return false;
	}
	var bank_fen = document.getElementById("systemSupervise.bank_fen").value;
	if(bank_fen == ""){
		alert("请填写金融机构分行");
		return false;
	}
	var bank_zhi = document.getElementById("systemSupervise.bank_zhi").value;
	if(bank_zhi == ""){
		alert("请填写金融机构支行");
		return false;
	}
	var province = document.getElementById("systemSupervise.province").value;
	if(province == ""){
		alert("请填写省");
		return false;
	}
	var city = document.getElementById("systemSupervise.city").value;
	if(city == ""){
		alert("请填写市");
		return false;
	}
	var supervise_name = document.getElementById("systemSupervise.supervise_name").value;
	if(supervise_name == ""){
		alert("请填写监管员姓名");
		return false;
	}
	var job_num = document.getElementById("systemSupervise.job_num").value;
	if(job_num == ""){
		alert("请填写员工工号");
		return false;
	}
	var loginid = document.getElementById("systemSupervise.loginid").value;
	if(loginid == ""){
		alert("请填写系统账号名称");
		return false;
	}
	var password = document.getElementById("systemSupervise.password").value;
	if(password == ""){
		alert("请填写密码");
		return false;
	}
	
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("systemSuperviseForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/systemSupervise.do?method=systemSuperviseList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">新增系统账号明细</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/systemSupervise" styleId="systemSuperviseForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addSystemSupervise">

<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>店名：</td>
		<td class="codeCol">
			<html:text property="systemSupervise.trade_name" styleId="systemSupervise.trade_name"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>金融机构：</td>
		<td class="codeCol">
			<html:text property="systemSupervise.bankid" styleId="systemSupervise.bankid"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>金融机构分行：</td>
		<td class="codeCol">
			<html:text property="systemSupervise.bank_fen" styleId="systemSupervise.bank_fen"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>金融机构支行：</td>
		<td class="codeCol">
			<html:text property="systemSupervise.bank_zhi" styleId="systemSupervise.bank_zhi"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>省：</td>
		<td class="codeCol">
			<html:text property="systemSupervise.province" styleId="systemSupervise.province"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>市：</td>
		<td class="codeCol">
			<html:text property="systemSupervise.city" styleId="systemSupervise.city"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>监管员姓名：</td>
		<td class="codeCol">
			<html:text property="systemSupervise.supervise_name" styleId="systemSupervise.supervise_name"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>员工工号：</td>
		<td class="codeCol">
			<html:text property="systemSupervise.job_num" styleId="systemSupervise.job_num"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>系统账号名称：</td>
		<td class="codeCol">
			<html:text property="systemSupervise.loginid" styleId="systemSupervise.loginid"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>密码：</td>
		<td class="codeCol">
			<html:text property="systemSupervise.password" styleId="systemSupervise.password"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">备注：</td>
		<td class="codeCol">
			<html:text property="systemSupervise.des" styleId="systemSupervise.des"></html:text>
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

</html:form>
<br/>
<div id="message" class="message" style="display:none"></div>

	</div>
</div>
</body>
</html>
