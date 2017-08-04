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
<link href="/css/base.css" rel="stylesheet" type="text/css" />
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>

$(function(){
	var msg = "<%=request.getAttribute("message")%>";
	if(msg!=null&&msg!=""&&msg!="null"){
		alert(msg);
	}
});
//执行保存操作
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
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">系统管理与配置</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">品牌管理</a>
             &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">新增品牌</a>
         </span>
</div>
<div class="title">新增品牌</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/brand" styleId="brandForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addBrand">
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

</html:form>
<br/>
<div id="message" class="message" style="display:none"></div>

	</div>
</div>
</body>
</html>
