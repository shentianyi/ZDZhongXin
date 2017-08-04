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
	var distribid = document.getElementById("actualAddress.distribid").value;
	if(distribid == ""){
		alert("请填写经销商");
		return false;
	}
	var agreement_address = document.getElementById("actualAddress.agreement_address").value;
	if(agreement_address == ""){
		alert("请填写协议地址");
		return false;
	}
	var actual_address = document.getElementById("actualAddress.actual_address").value;
	if(actual_address == ""){
		alert("请填写实际监管地址");
		return false;
	}
	var relationship = document.getElementById("actualAddress.relationship").value;
	if(relationship == ""){
		alert("请填写关系");
		return false;
	}
	var isreport = document.getElementById("actualAddress.isreport").value;
	if(isreport == ""){
		alert("请填写是否上报");
		return false;
	}
	var distance = document.getElementById("actualAddress.distance").value;
	if(distance == ""){
		alert("请填写距离");
		return false;
	}
	
	
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("actualAddressForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/actualAddress.do?method=actualAddressList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">新增实际监管地址</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/actualAddress" styleId="actualAddressForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addActualAddress">
<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>经销商：</td>
		<td class="codeCol">
			<form:select property="actualAddress.distribid" styleId="actualAddress.distribid">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="dealersOptions" label="label" value="value" />
			</form:select>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>协议地址：</td>
		<td class="codeCol">
			<html:text property="actualAddress.agreement_address" styleId="actualAddress.agreement_address"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>实际监管地址：</td>
		<td class="codeCol">
			<html:text property="actualAddress.actual_address" styleId="actualAddress.actual_address"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>关系：</td>
		<td class="codeCol">
			<html:text property="actualAddress.relationship" styleId="actualAddress.relationship"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>是否上报：</td>
		<td class="codeCol">
			<form:select property="actualAddress.isreport" styleId="actualAddress.isreport">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="yesornoOptions" label="label" value="value" />
			</form:select>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>距离(公里)：</td>
		<td class="codeCol">
			<html:text property="actualAddress.distance" styleId="actualAddress.distance"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">备注：</td>
		<td class="codeCol">
			<html:text property="actualAddress.des" styleId="actualAddress.des"></html:text>
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
