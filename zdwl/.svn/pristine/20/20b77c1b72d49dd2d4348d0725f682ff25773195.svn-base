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
function doSave(){
	var name = document.getElementById("brand.name").value;
	if(name == ""){
		alert("请填写品牌");
		return false;
	}
	document.forms[0].submit();
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/brand.do?method=brandList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
</script>
</head>
  
<body>
<div class="title">修改品牌</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/brand" styleId="brandForm" method="post" onsubmit="return false">
<html:hidden property="brand.id" styleId="brand.id"/>
<input type="hidden" name="method" id="method" value="updBrand">

<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>品牌</td>
		<td class="codeCol">
			<html:text property="brand.name" styleId="brand.name"></html:text>
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
