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
	var value = document.getElementById("refundInvoice.distribid").value;
	if(value == "-1"){
		alert("请选择经销商");
		return false;
	}
	var value = document.getElementById("refundInvoice.financial_institution").value;
	if(value == ""){
		alert("请填写金融机构");
		return false;
	}
	var value = document.getElementById("refundInvoice.bank").value;
	if(value == ""){
		alert("请填写合作银行");
		return false;
	}
	var value = document.getElementById("refundInvoice.brand").value;
	if(value == ""){
		alert("请填写品牌");
		return false;
	}
	var value = document.getElementById("refundInvoice.intime").value;
	if(value == ""){
		alert("请选择进店时间");
		return false;
	}
	var value = document.getElementById("refundInvoice.supervisionfee_standard").value;
	if(value == ""){
		alert("请填写监管费标准");
		return false;
	}
	var value = document.getElementById("refundInvoice.payment").value;
	if(value == ""){
		alert("请填写付费方式");
		return false;
	}
	var value = document.getElementById("refundInvoice.pay_standard").value;
	if(value == ""){
		alert("请填写缴费标准");
		return false;
	}
	var value = document.getElementById("refundInvoice.pay_time").value;
	if(value == ""){
		alert("请填写缴费时间");
		return false;
	}
	var value = document.getElementById("refundInvoice.pay_money").value;
	if(value == ""){
		alert("请填写缴费金额");
		return false;
	}
	var value = document.getElementById("refundInvoice.refund_time").value;
	if(value == ""){
		alert("请填写退费时间");
		return false;
	}
	var value = document.getElementById("refundInvoice.refund_money").value;
	if(value == ""){
		alert("请填写退费金额");
		return false;
	}
	var value = document.getElementById("refundInvoice.refund_des").value;
	if(value == ""){
		alert("请填写退费原因");
		return false;
	}
	var value = document.getElementById("refundInvoice.isinvoice").value;
	if(value == "-1"){
		alert("请填写是否收到退款发票");
		return false;
	}
	var value = document.getElementById("refundInvoice.invoice_time").value;
	if(value == ""){
		alert("请填写收票时间");
		return false;
	}
	var value = document.getElementById("refundInvoice.invoice_company").value;
	if(value == ""){
		alert("请填写开票单位");
		return false;
	}
	var value = document.getElementById("refundInvoice.invoice_type").value;
	if(value == ""){
		alert("请填写发票类型");
		return false;
	}
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("refundInvoiceForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/refundInvoice.do?method=refundInvoiceList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">修改退款发票</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/refundInvoice" styleId="refundInvoiceForm" method="post" onsubmit="return false">
<html:hidden property="refundInvoice.id" styleId="refundInvoice.id"/>
<input type="hidden" name="method" id="method" value="updRefundInvoice">

<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>经销商：</td>
		<td class="codeCol">
			<form:select property="refundInvoice.distribid" styleId="refundInvoice.distribid">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="dealersOptions" label="label" value="value" />
			</form:select>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>金融机构：</td>
		<td class="codeCol">
			<html:text property="refundInvoice.financial_institution" styleId="refundInvoice.financial_institution"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>合作银行：</td>
		<td class="codeCol">
			<html:text property="refundInvoice.bank" styleId="refundInvoice.bank"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>品牌：</td>
		<td class="codeCol">
			<html:text property="refundInvoice.brand" styleId="refundInvoice.brand"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>进店时间：</td>
		<td class="codeCol">
			<form:calendar property="refundInvoice.intime" styleId="refundInvoice.intime" pattern="<%=PatternConstants.TIMESTAMP_SS.getCode()%>" readonly="true" />
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>监管费标准：</td>
		<td class="codeCol">
			<html:text property="refundInvoice.supervisionfee_standard" styleId="refundInvoice.supervisionfee_standard"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>付费方式：</td>
		<td class="codeCol">
			<html:text property="refundInvoice.payment" styleId="refundInvoice.payment"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>缴费标准：</td>
		<td class="codeCol">
			<html:text property="refundInvoice.pay_standard" styleId="refundInvoice.pay_standard"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>缴费时间：</td>
		<td class="codeCol">
			<form:calendar property="refundInvoice.pay_time" styleId="refundInvoice.pay_time" pattern="<%=PatternConstants.TIMESTAMP_SS.getCode()%>" readonly="true" />
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>缴费金额：</td>
		<td class="codeCol">
			<html:text property="refundInvoice.pay_money" styleId="refundInvoice.pay_money"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>退费时间：</td>
		<td class="codeCol">
			<form:calendar property="refundInvoice.refund_time" styleId="refundInvoice.refund_time" pattern="<%=PatternConstants.TIMESTAMP_SS.getCode()%>" readonly="true" />
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>退费金额：</td>
		<td class="codeCol">
			<html:text property="refundInvoice.refund_money" styleId="refundInvoice.refund_money"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>退费原因：</td>
		<td class="codeCol">
			<html:text property="refundInvoice.refund_des" styleId="refundInvoice.refund_des"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>是否收到退款发票：</td>
		<td class="codeCol">
			<form:select property="refundInvoice.isinvoice" styleId="refundInvoice.isinvoice">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="yesornoOptions" label="label" value="value" />
			</form:select>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>收票时间：</td>
		<td class="codeCol">
			<form:calendar property="refundInvoice.invoice_time" styleId="refundInvoice.invoice_time" pattern="<%=PatternConstants.TIMESTAMP_SS.getCode()%>" readonly="true" />
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>开票单位：</td>
		<td class="codeCol">
			<html:text property="refundInvoice.invoice_company" styleId="refundInvoice.invoice_company"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>发票类型：</td>
		<td class="codeCol">
			<html:text property="refundInvoice.invoice_type" styleId="refundInvoice.invoice_type"></html:text>
		</td>
		<td class="nameCol">备注：</td>
		<td class="codeCol">
			<html:text property="refundInvoice.des" styleId="refundInvoice.des"></html:text>
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
