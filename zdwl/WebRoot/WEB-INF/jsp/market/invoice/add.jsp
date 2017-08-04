<%@page import="com.zd.tools.validate.constants.PatternConstants"%>
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
<link href="/easyui/themes/material/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>

<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script src="/pagejs/invoice.js"></script>
<script>
	$(function(){
		init();
	});
</script>
</head>
<body onLoad="doLoad()">

	<div class="title">开票申请流转单</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/market/invoice.do" styleId="invoiceForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="add" />

				<table class="formTable">
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>经销商：</td>
						<td class="codeCol">
							<select style="width:100%" id="jxs" name="invoice.dealerId" >
								<c:forEach items="${dealers }" var="row">
									<option value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
								</c:forEach>
							</select>
						</td>
						<td class="nameCol">金融机构：</td>
						<td class="codeCol">
							<input type="text"id="bankName" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">品牌：</td>
						<td class="codeCol"><input type="text" id="brand" readonly="readonly"/></td>
						<td class="nameCol">监管费（元/年）：</td>
						<td class="codeCol">
							<input type="text"id="superviseMoney" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>缴费金额：</td>
						<td class="codeCol">
							<html:text property="invoice.paymentAmount" styleId="invoice.paymentAmount"></html:text>
						</td>
						<td class="nameCol">付费方式：</td>
						<td class="codeCol">
							<input type="text"id="payMode" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>监管费缴费时间：</td>
						<td class="codeCol">
							<form:calendar
								property="invoice.paymentDate" styleId="invoice.paymentDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								readonly="true" />
						</td>
					</tr>
					
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>开票单位：</td>
						<td class="codeCol">
							<html:text property="invoice.office" styleId="invoice.office" ></html:text>
						</td>
						<td class="nameCol">备注：</td>
						<td class="codeCol">
							<html:text property="invoice.remark"styleId="invoice.remark"></html:text>
						</td>
					</tr>
					
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>申请人：</td>
						<td class="codeCol">
							<html:text property="invoice.applicant" styleId="invoice.applicant"></html:text>
						</td>
						<td class="nameCol"><font color="#FF0000">*</font>申请时间：</td>
						<td class="codeCol">
							<form:calendar
								property="invoice.applicantDate" styleId="invoice.applicantDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>公司名称：</td>
						<td class="codeCol">
							<html:text property="invoice.companyname" styleId="invoice.companyname"></html:text>
						</td>
						<td class="nameCol"><font color="#FF0000">*</font>开户行：</td>
						<td class="codeCol">
							<html:text property="invoice.bankaccount" styleId="invoice.bankaccount"></html:text>
						</td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>账号：</td>
						<td class="codeCol">
							<html:text property="invoice.accountnum" styleId="invoice.accountnum"></html:text>
						</td>
						<td class="nameCol">行号：</td>
						<td class="codeCol">
							<html:text property="invoice.banknum" styleId="invoice.banknum"></html:text>
						</td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>纳税人识别号：</td>
						<td class="codeCol">
							<html:text property="invoice.taxpayernum" styleId="invoice.taxpayernum"></html:text>
						</td>
						<td class="nameCol"><font color="#FF0000">*</font>注册地址：</td>
						<td class="codeCol">
							<html:text property="invoice.registeraddress" styleId="invoice.registeraddress"></html:text>
						</td>
					</tr>
					<tr>
						<td class="nameCol">电话：</td>
						<td class="codeCol">
							<html:text property="invoice.telephone" styleId="invoice.telephone"></html:text>
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
				<br />
				<div class="message" id="message" style="display: none"></div>
			</html:form>

		</div>
	</div>
</body>
</html>
