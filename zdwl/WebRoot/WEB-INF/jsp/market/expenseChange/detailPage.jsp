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
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
	//页面初始化函数
	function doLoad(){
		//显示提示信息
		showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	}
	$(function(){
		changeDealer($('#dealerId').val());
	});
	//执行保存操作
	function doSave() {
		document.forms[0].submit();
	}

	//执行表单重置操作
	function doReset() {
		document.forms[0].reset();
	}
 
</script>
</head>
<body onLoad="doLoad()">

	<div class="title">监管费用变更流转单</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/market/expenseChange.do" styleId="ecForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="update" />
				<html:hidden property="ec.id"/>
				<html:hidden property="ec.dealerId" styleId="dealerId"/>

				<table class="formTable">
					<tr class="formTitle">
						<td colspan="4">市   场   部</td>
					</tr>
					<tr>
						<td class="nameCol">经销店：</td>
						<td class="codeCol"><input type="text" id="dealerName" readonly="readonly"/></td>
						<td class="nameCol">品牌：</td>
						<td class="codeCol"><input type="text" id="brand" readonly="readonly"/></td>
					</tr>
	
					<tr>
						<td class="nameCol">金融机构：</td>
						<td class="codeCol">
							<input type="text"id="bankName" readonly="readonly"/>
						</td>
						<td class="nameCol">进店时间：</td>
						<td class="codeCol"><input type="text" id="inStoreDate" readonly="readonly"/></td>
					</tr>
					<tr>
						<td class="nameCol">原监管费（元/年）：</td>
						<td class="codeCol">
							<input type="text"id="superviseMoney" readonly="readonly"/>
						</td>
						<td class="nameCol">变更后监管费（元/年）：</td>
						<td class="codeCol">
							<html:text property="ec.changemoney" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td class="nameCol">费用变更日期：</td>
						<td class="codeCol">
							<form:calendar
								property="ec.changeDate" styleId="ec.changeDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								disabled="true" />
						</td>
						<td class="nameCol">制单时间：</td>
						<td class="codeCol">
							<form:calendar
								property="ec.createDate" styleId="ec.createDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								disabled="true"/>
						</td>
					</tr>

					<tr>
						<td class="nameCol">备注：</td>
						<td class="codeCol" colspan="3"><html:textarea property="ec.remark" readonly="true"></html:textarea></td>
					</tr>
					
					<tr class="formTitle">
						<td colspan="4">审批意见</td>
					</tr>
					<logic:iterate name="approvals" id="row" indexId="index">
						<tr>
							<td class="nameCol">第<c:out value='${index+1 }'/>步</td>
							<td class="nameCol" style="text-align: left;">
								<c:out value="${row.roleName }"/>：
								<c:out value="${row.userName }"/>于<fmt:formatDate value="${row.remarkDate }" pattern="yyyy-MM-dd"/>
							</td>
							<td class="codeCol" colspan="2">
								<c:if test="${row.approvalResult==1}">同意&nbsp;</c:if>
								<c:if test="${row.approvalResult==2}">不同意&nbsp;</c:if>
								<c:out value="${row.remark }"/>
							</td>
						</tr>
					</logic:iterate>

					<tr class="formTableFoot">
						<td colspan="4" align="center">
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
