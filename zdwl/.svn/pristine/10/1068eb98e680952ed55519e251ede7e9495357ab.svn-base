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
	var value = document.getElementById("openInvoiceList.pay_time").value;
	if(value == ""){
		alert("请选择缴费时间");
		return false;
	}
	var value = document.getElementById("openInvoiceList.pay_money").value;
	if(value == ""){
		alert("请填写缴费金额");
		return false;
	}
	var value = document.getElementById("openInvoiceList.isinvoice").value;
	if(value == "-1"){
		alert("请选择是否开票");
		return false;
	}
	var value = document.getElementById("openInvoiceList.invoice_type").value;
	if(value == ""){
		alert("请填写开票方式");
		return false;
	}
	var value = document.getElementById("openInvoiceList.isTransfer").value;
	if(value == "-1"){
		alert("请选择是否交接");
		return false;
	}
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("openInvoiceListForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	var openid = document.getElementById("openid").value;
	location = "<url:context/>/openInvoiceList.do?method=openInvoiceLists&openid="+openid;
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">修改开票记录</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/openInvoiceList" styleId="openInvoiceListForm" method="post" onsubmit="return false">
<html:hidden property="openInvoiceList.id" styleId="openInvoiceList.id"/>
<html:hidden property="openInvoiceList.open_invoice_id" styleId="openInvoiceList.open_invoice_id"/>
<input name="openid" id="openid" type="hidden" value="<c:out value='${openid}'/>"/>

<input type="hidden" name="method" id="method" value="updOpenInvoiceList">

<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>缴费时间：</td>
		<td class="codeCol">
			<form:calendar property="openInvoiceList.pay_time" styleId="openInvoiceList.pay_time" pattern="<%=PatternConstants.TIMESTAMP_SS.getCode()%>" readonly="true" />
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>缴费金额：</td>
		<td class="codeCol">
			<html:text property="openInvoiceList.pay_money" styleId="openInvoiceList.pay_money"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>是否开票：</td>
		<td class="codeCol">
			<form:select property="openInvoiceList.isinvoice" styleId="openInvoiceList.isinvoice">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="yesornoOptions" label="label" value="value" />
			</form:select>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>开票方式：</td>
		<td class="codeCol">
			<html:text property="openInvoiceList.invoice_type" styleId="openInvoiceList.invoice_type"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>是否交接：</td>
		<td class="codeCol">
			<form:select property="openInvoiceList.isTransfer" styleId="openInvoiceList.isTransfer">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="yesornoOptions" label="label" value="value" />
			</form:select>
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
