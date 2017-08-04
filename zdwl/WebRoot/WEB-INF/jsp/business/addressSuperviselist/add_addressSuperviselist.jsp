﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	
	var department = document.getElementById("addresslist.department").value;
	if(department == ""){
		alert("请填写部门");
		return false;
	}
	var name = document.getElementById("addresslist.name").value;
	if(name == ""){
		alert("请填写姓名");
		return false;
	}
	var quarters = document.getElementById("addresslist.quarters").value;
	if(quarters == ""){
		alert("请填写岗位");
		return false;
	}
	var landline = document.getElementById("addresslist.landline").value;
	if(landline == ""){
		alert("请填写座机");
		return false;
	}
	var fax = document.getElementById("addresslist.fax").value;
	if(fax == ""){
		alert("请填写传真");
		return false;
	}
	var qq = document.getElementById("addresslist.qq").value;
	if(qq == ""){
		alert("请填写QQ号码");
		return false;
	}
	var duty = document.getElementById("addresslist.duty").value;
	if(duty == ""){
		alert("请填写职责");
		return false;
	}

	document.forms[0].submit();
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/addressSuperviselist.do?method=addressSuperviseList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">新增通讯录</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/addressSuperviselist" styleId="addresslistForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addAddressSuperviselist">
<input type="hidden" id="addresslist.genre" name="addresslist.genre" value="2" />
<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>部门：</td>
		<td class="codeCol">
			<html:text property="addresslist.department" styleId="addresslist.department"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>姓名：</td>
		<td class="codeCol">
			<html:text property="addresslist.name" styleId="addresslist.name"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>岗位：</td>
		<td class="codeCol">
			<html:text property="addresslist.quarters" styleId="addresslist.quarters"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>座机：</td>
		<td class="codeCol">
			<html:text property="addresslist.landline" styleId="addresslist.landline"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>传真：</td>
		<td class="codeCol">
			<html:text property="addresslist.fax" styleId="addresslist.fax"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>QQ号码：</td>
		<td class="codeCol">
			<html:text property="addresslist.qq" styleId="addresslist.qq"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>职责：</td>
		<td class="codeCol">
			<html:text property="addresslist.duty" styleId="addresslist.duty"></html:text>
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
