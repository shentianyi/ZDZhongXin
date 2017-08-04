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
<script src="/pagejs/draft.js"></script>
<script type="text/javascript">

$(function(){
	var msg ="<c:out value='${message}'/>";
	if(msg!=null&&msg!=""&&msg!="null"){
		alert(msg);
	}
	init();
});

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/draft.do?method=draftViewList";
}

</script>
</head>
  
<body >
<div class="title">汇票详情</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/draft" styleId="draftForm" method="post" onsubmit="return false">
<html:hidden property="draft.id" styleId="draft.id"/>
<input type="hidden" name="method" id="method" value="draftDetail">

<table class="formTable">
	<tr>
		<td class="nameCol">质押协议号：</td>
		<td class="codeCol">
			<html:text property="draft.agreement" styleId="draft.agreement"></html:text>
		</td>
		<td class="nameCol">保证金账号：</td>
		<td class="codeCol">
			<html:text property="draft.bail_num" styleId="draft.bail_num"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>经销商：</td>
		<td class="codeCol">
			<select disabled="disabled" style="width:100%" id="jxs" name="draft.distribid">
				<c:forEach items="${dealersOptions }" var="row">
					<option <c:if test="${jj==row.value}">selected</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
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
		<td class="codeCol">
			<input type="text" id="brand" readonly="readonly"/>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>汇票号码：</td>
		<td class="codeCol">
			<html:text property="draft.draft_num" styleId="draft.draft_num" readonly="true"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>开票日：</td>
		<td class="codeCol">
			<form:calendar property="draft.billing_date" styleId="draft.billing_date" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>到期日：</td>
		<td class="codeCol">
			<form:calendar property="draft.due_date" styleId="draft.due_date" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>开票金额：</td>
		<td class="codeCol">
			<input type="text" name="draft.billing_money" id="billing_money" 
								class="easyui-numberbox" value="<c:out value='${draftForm.draft.billing_money }'/>"
								data-options="min:0,precision:2"></input>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>状态：</td>
		<td class="codeCol">
			<form:select property="draft.state" styleId="draft.state">
				<html:optionsCollection name="isClearTicket" label="label" value="value" />
			</form:select>
		</td>
	</tr>
	<tr class="formTableFoot">
		<td colspan="4" align="center">
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
