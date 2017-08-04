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
	
	var username = document.getElementById("systemManage.username").value;
	if(username == ""){
		alert("请填写姓名");
		return false;
	}
	var station = document.getElementById("systemManage.station").value;
	if(station == ""){
		alert("请填写岗位");
		return false;
	}
	var loginid = document.getElementById("systemManage.loginid").value;
	if(loginid == ""){
		alert("请填写系统账号名称");
		return false;
	}
	var password = document.getElementById("systemManage.password").value;
	if(password == ""){
		alert("请填写密码");
		return false;
	}
	
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("systemManageForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/systemManage.do?method=systemManageList";
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
	
<html:form action="/systemManage" styleId="systemManageForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addSystemManage">

<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>姓名：</td>
		<td class="codeCol">
			<html:text property="systemManage.username" styleId="systemManage.username"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>岗位：</td>
		<td class="codeCol">
			<html:text property="systemManage.station" styleId="systemManage.station"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>系统账号名称：</td>
		<td class="codeCol">
			<html:text property="systemManage.loginid" styleId="systemManage.loginid"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>密码：</td>
		<td class="codeCol">
			<html:text property="systemManage.password" styleId="systemManage.password"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">备注：</td>
		<td class="codeCol">
			<html:text property="systemManage.des" styleId="systemManage.des"></html:text>
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
