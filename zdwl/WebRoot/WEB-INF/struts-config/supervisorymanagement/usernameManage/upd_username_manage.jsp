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
<script type="text/javascript">
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//执行保存操作
function doSave(){
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("usernameManageForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/usernameManage.do?method=usernameManageList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">修改系统用户名</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/usernameManage" styleId="usernameManageForm" method="post" onsubmit="return false">
<html:hidden property="usernameManage.id" styleId="usernameManage.id"/>
<input type="hidden" name="method" id="method" value="updUsernameManage">

<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>经销商：</td>
		<td class="codeCol">
			<html:text property="usernameManage.distribid" styleId="usernameManage.distribid"></html:text>
		</td>
		<td class="nameCol">合作机构</td>
		<td class="codeCol">
			<html:text property="usernameManage.bankname" styleId="usernameManage.bankname"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">监管员姓名：</td>
		<td class="codeCol">
			<html:textarea property="usernameManage.supervisory_name" styleId="usernameManage.supervisory_name"></html:textarea>
		</td>
		<td class="nameCol">账号：</td>
		<td class="codeCol">
			<html:textarea property="usernameManage.loginid" styleId="usernameManage.loginid"></html:textarea>
		</td>
	</tr>
	<tr>
		<td class="nameCol">密码：</td>
		<td class="codeCol">
			<html:textarea property="usernameManage.password" styleId="usernameManage.password"></html:textarea>
		</td>
		<td class="nameCol">备注：</td>
		<td class="codeCol">
			<html:textarea property="usernameManage.des" styleId="usernameManage.des"></html:textarea>
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
