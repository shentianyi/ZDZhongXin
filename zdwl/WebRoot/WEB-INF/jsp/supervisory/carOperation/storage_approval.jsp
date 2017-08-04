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
function agree(){
	$("#approvalState").val(1);
	doSave();
}

function disAgree(){
	$("#approvalState").val(2);
	doSave();
}
//执行保存操作
function doSave() {

	//对表单内容进行验证，包括对输入类型等限制方式
	if (validateMain("carOperationForm")) {
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}
//执行返回列表操作
function doReturn() {
	location = "<url:context/>/carOperation.do?method=storagefindList";
}
</script>
</head>
<body>

	<div class="title">监管物入库申请</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/carOperation.do" styleId="carOperationForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="storageApproval" />
				<input type="hidden" name="carOperationquery.id" value="<c:out value='${carOperationForm.carOperation.id }'/>"/>
				<html:hidden property="carOperationquery.approvalState" styleId="approvalState"/>

				<table class="formTable">
					<logic:iterate name="list" id="row" indexId="index">
						<tr>
							<td class="nameCol">票号：</td>
							<td class="codeCol"><c:out value="${row.draft_num}" /></td>
							<td class="nameCol">车辆型号：</td>
							<td class="codeCol"><c:out value="${row.car_model}" /></td>
						</tr>
						<tr>
							<td class="nameCol">车架号：</td>
							<td class="codeCol"><c:out value="${row.vin}" /></td>
							<td class="nameCol">合格证号：</td>
							<td class="codeCol"><c:out value="${row.certificate_num}" /></td>
						</tr>
						<tr>
							<td class="nameCol">发动机号：</td>
							<td class="codeCol"><c:out value="${row.engine_num}" /></td>
							<td class="nameCol">单价(元)：</td>
							<td class="codeCol"><c:out value="${row.money}" /></td>
						</tr>
					</logic:iterate>
					<c:if test="${approvals!=null }">
						<tr class="formTitle">
							<td colspan="4">部门意见</td>
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
					</c:if>
					
					<tr>
						<td class="nameCol">审批意见：</td>
						<td class="codeCol" colspan="3"><html:text property="carOperationquery.remark"></html:text></td>
					</tr>
					
					<tr class="formTableFoot">
						<td colspan="4" align="center">
							<button class="formButton" onClick="agree()">同&nbsp;意</button>&nbsp;
							<button class="formButton" onClick="disAgree()">不&nbsp;同&nbsp;意</button>&nbsp;
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
