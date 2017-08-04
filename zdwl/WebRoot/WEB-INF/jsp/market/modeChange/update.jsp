﻿<%@page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ taglib uri="select.tld" prefix="select"%>


<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />

<!-- easyui -->
<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>

<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script src="/pagejs/modeChange.js"></script>
<script>
$(function(){
	init();
});
</script>
</head>
<body>

	<div class="title">操作模式变更流转单</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/market/modeChange.do" styleId="mcForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="update" />
				<html:hidden property="mc.id"/>
				

				<table class="formTable">
					<tr class="formTitle">
						<td colspan="4">市   场   部</td>
					</tr>
					<tr>
						<td class="nameCol">经销商：</td>
						<td class="codeCol" colspan="3">
							<select style="width:70%" id="jxs" name="mc.dealerId" >
								<c:forEach items="${dealers }" var="row">
									<option<c:if test="${mcForm.mc.dealerId==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="nameCol">店方联系人：</td>
						<td class="codeCol"><input type="text" id="contact" readonly="readonly"/></td>
						<td class="nameCol">联系人电话：</td>
						<td class="codeCol"><input type="text" id="contactPhone" readonly="readonly"/></td>
					</tr>
					<tr>
						<td class="nameCol">金融机构：</td>
						<td class="codeCol">
							<input type="text"id="bankName" readonly="readonly"/>
						</td>
						<td class="nameCol">银行客户经理：</td>
						<td class="codeCol">
							<input type="text"id="bankContact" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">联系电话：</td>
						<td class="codeCol">
							<input type="text"id="bankPhone" readonly="readonly"/>
						</td>
						<td class="nameCol">品牌：</td>
						<td class="codeCol"><input type="text" id="brand" readonly="readonly"/></td>
					</tr>
					<tr>
						<td class="nameCol">协议监管模式：</td>
						<td class="codeCol">
							<input type="radio" value="车证" id="supervisionMode_1" disabled="disabled"/>车证
							<input type="radio" value="合格证" id="supervisionMode_2" disabled="disabled"/>合格证
							<input type="radio" value="合格证" id="supervisionMode_3" disabled="disabled"/>贷后巡库
							<input type="radio" value="合格证" id="supervisionMode_4" disabled="disabled"/>金融监管库
						</td>
						<td class="nameCol">合作模式：</td>
						<td class="codeCol">
							两方：<input type="radio" id="cooperationModel_1" disabled="disabled"/>
							三方：<input type="radio" id="cooperationModel_2" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>变更监管操作模式：</td>
						<td class="codeCol">
							<form:radios styleId="changeOperationMode"
								property="mc.changeOperationMode" collection="supervisionModes"></form:radios>
						</td>
						<td class="nameCol"><font color="#FF0000">*</font>变更时间：</td>
						<td class="codeCol">
							<form:calendar
								property="mc.changeDate" styleId="mc.changeDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								readonly="true" />
						</td>
					</tr>
					<tr>
					    <td class="nameCol">原监管费用(元/年)：</td>
						<td class="codeCol"><input type="text" id="superviseMoney"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>费用是否变更：</td>
						<td class="codeCol">
							<form:radios
								property="mc.moneyIsChange" collection="yesorno">
								</form:radios>
						</td>
						<td class="nameCol"><font color="#FF0000">*</font>制单时间：</td>
						<td class="codeCol">
							<form:calendar
								property="mc.createDate" styleId="mc.createDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<td class="nameCol">备注：</td>
						<td class="codeCol" colspan="3"><html:textarea property="mc.remark"/></td>
					</tr>

					<tr class="formTableFoot">
						<td colspan="4" align="center">
							<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
							<button class="formButton" onClick="doReset()">重&nbsp;置</button>&nbsp;
							<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
						</td>
					</tr>
				</table>
				<br />
				<div class="message" id="message" style="display: none"></div>
			</html:form>

		</div>
	</div>
</body>
</html>
