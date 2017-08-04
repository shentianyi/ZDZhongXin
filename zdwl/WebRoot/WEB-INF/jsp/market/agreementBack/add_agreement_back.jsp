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
<!-- easyui -->
<link href="/easyui/themes/material/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>

<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script src="/pagejs/agreementBack.js"></script>
<script>
$(function(){
	init();
});
</script>
</head>
<body>
<div class="title">新增协议邮寄</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/agreementBack" styleId="agreementBackForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addAgreementBack">
<table class="formTable">
	<tr>
		<td class="nameCol">经销商：</td>
		<td class="codeCol">
			<select style="width:100%" id="jxs" name="agreementBack.distribid" >
				<c:forEach items="${dealersOptions }" var="row">
					<option value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
				</c:forEach>
			</select>
		</td>
		<td class="nameCol">金融机构：</td>
		<td class="codeCol">
			<input type="text"id="bankName"readonly="readonly" />
		</td>
	</tr>
	<tr>
		<td class="nameCol">品牌：</td>
		<td class="codeCol"><input type="text" id="brand" readonly="readonly"/></td>
		<td class="nameCol"></td>
		<td class="codeCol"></td>
	</tr>
	<tr>
		<td class="nameCol">省：</td>
		<td class="codeCol"><input type="text" id="province" readonly="readonly"/></td>
		<td class="nameCol">市：</td>
		<td class="codeCol"><input type="text" id="city" readonly="readonly"/></td>
	</tr>
	<tr>
		<td class="nameCol">金融机构联系人：</td>
		<td class="codeCol" >
			<input type="text"id="bankContact"readonly="readonly" />
		</td>
		<td class="nameCol">联系方式：</td>
		<td class="codeCol" >
			<input type="text"id="bankPhone"readonly="readonly" />
		</td>
	</tr>
	<tr>
		<td class="nameCol">协议编号：</td>
		<td class="codeCol">
			<html:text property="agreementBack.agreement_num" styleId="agreementBack.agreement_num"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>协议邮寄时间：</td>
		<td class="codeCol">
			<form:calendar property="agreementBack.agreement_date" styleId="agreementBack.agreement_date" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>邮寄地址：</td>
		<td class="codeCol">
			<html:text property="agreementBack.send_address" styleId="agreementBack.send_address"></html:text>
		</td>
		<td class="nameCol">存放地址：</td>
		<td class="codeCol">
			<html:text property="agreementBack.deposit_address" styleId="agreementBack.deposit_address"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">备注：</td>
		<td class="codeCol">
			<html:text property="agreementBack.des" styleId="agreementBack.des"></html:text>
		</td>
		<td class="nameCol"></td>
		<td class="codeCol"></td>
	</tr>
	<tr>
		<td class="nameCol">协议签署日期：</td>
		<td class="codeCol">
			<form:calendar property="agreementBack.agreementsigntime" styleId="agreementBack.agreementsigntime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
		<td class="nameCol">协议到期日：</td>
		<td class="codeCol">
			<form:calendar property="agreementBack.agreementexpiretime" styleId="agreementBack.agreementexpiretime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
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
