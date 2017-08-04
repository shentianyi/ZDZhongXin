<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.csms.constants.Constants"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<%@ page import="com.zd.tools.taglib.form.FormTagConstants"%>
<%@ page import="com.zd.csms.supervisorymanagement.model.LeaveReplaceDynamicVO" %>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>中都汽车金融监管系统</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<!-- easyui -->
<!-- <link href="/easyui/themes/material/easyui.css" rel="stylesheet"
	type="text/css" /> -->
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<c:set var="CalendarScript" value="true"/>
<script>

$(function(){
});

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/extraworkApply.do?method=findPageList";
}

function agree(){
	$("#approvalState").val(1);
	document.forms[0].submit();
}

function disAgree(){
	$("#approvalState").val(2);
	document.forms[0].submit();
}

</script>
</head>
  
<body>

<div class="title">加班申请详情</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/extraworkApply" styleId="extraworkApplyForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="approval"/>
<html:hidden property="extraworkApply.repositoryId" styleId="repositoryId"/>
<html:hidden property="extraworkApply.id" styleId="id"/>
<input type="hidden" name="query.id" value="<c:out value='${extraworkApplyForm.extraworkApply.id }'/>"/>
<html:hidden property="query.approvalState" styleId="approvalState" value="<c:out value='${extraworkApplyForm.extraworkApply.approvalState }'/>" />
<html:hidden property="extraworkApply.status" styleId="status"/>
<table class="formTable">
	<tr>
		<td colspan="4"> 
			<table style="width: 100%">
				<tr>
					<td class="nameCol">申请人工号：</td>
					<td class="codeCol" >
						<html:text  property="extraworkApply.staffNo" styleId="extraworkApply.staffNo" readonly="true"/>
					</td>
					<td class="nameCol">申请人：</td>
					<td class="codeCol" >
						<html:text  property="extraworkApply.name" styleId="extraworkApply.name"  readonly="true"/>
					</td>
					<td class="nameCol" >申请时间：</td>
					<td class="codeCol" >
						<c:out value="${applyTime }" />
					</td>
				</tr>
				<tr>
					<td class="nameCol">省：</td>
					<td class="codeCol" >
						<html:text  property="extraworkApply.province" styleId="extraworkApply.province" readonly="true"/>
					</td>
					<td class="nameCol">市：</td>
					<td class="codeCol" >
						<html:text  property="extraworkApply.city" styleId="extraworkApply.city" readonly="true"/>
					</td>
				</tr>
				<c:if test="${currentDealerList!=null}">
					<logic:iterate name="currentDealerList" id="row" indexId="index" >
						<tr>
							<td class="nameCol" style="width:15%">经销商<c:out value="${index+1}" />：</td>
							<td class="codeCol" style="width:15%">
								<c:out value='${row.dealerName}' />
							</td>
							<td class="nameCol" style="width:15%">金融机构<c:out value="${index+1}" />：</td>
							<td class="codeCol" style="width:25%">
								<c:out value='${row.bankName}' />
							</td>
							<td class="nameCol" style="width:15%">品牌<c:out value="${index+1}" />：</td>
							<td class="codeCol" style="width:15%">
								<c:out value='${row.brandName}' />
							</td>
						</tr>
					</logic:iterate>
				</c:if>
				<tr>
					<td class="nameCol">开始时间：</td>
					<td class="codeCol" >
						<form:calendar style="width:80%" property="extraworkApply.startTime" styleId="extraworkApply.startTime" pattern="<%=PatternConstants.TIMESTAMP_MM.getCode()%>" readonly="true" disabled="true" />
					</td>
					<td class="nameCol">结束时间：</td>
					<td class="codeCol" >
						<form:calendar style="width:80%" property="extraworkApply.endTime" styleId="extraworkApply.endTime" pattern="<%=PatternConstants.TIMESTAMP_MM.getCode()%>" readonly="true" disabled="true"/>
					</td>
					<td class="nameCol" >加班天数：</td>
					<td class="codeCol" >
						<input type="text" id="extraWorkDays" name="extraworkApply.extraWorkDays" class="easyui-numberbox" value="<c:out value='${extraworkApplyForm.extraworkApply.extraWorkDays}'/>" readonly="true"
								data-options="min:0,precision:0"/>
					</td>
				</tr>
				<tr>
					<%-- <td class="nameCol"><font color="#FF0000">*</font>审批部门：</td>
					<td class="codeCol">
						<form:select property="extraworkApply.approvalDepartment" disabled="true"
							styleId="approvalDepartment" >
							<html:optionsCollection name="departments" label="label" value="value" />
						</form:select>
					</td> --%>
					<td class="nameCol">加班事由：</td>
					<td class="codeCol" colspan="3">
						<html:textarea style="width:80%" property="extraworkApply.reason" styleId="extraworkApply.reason" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="6"class="nameCol"></td>
				</tr>
			</table>
		</td>	
	</tr>
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
					<c:out value="${row.remark }"/>
				</td>
			</tr>
		</logic:iterate>
	</c:if>	
	<tr>
		<td class="nameCol">审批意见：</td>
		<td class="codeCol" colspan="3"><html:text style="width:700px" property="query.remark"></html:text></td>
	</tr>
	
	<tr class="formTableFoot">
		<td colspan="4" align="center">
			<button class="formButton" onClick="agree()">同&nbsp;意</button>&nbsp;
			<button class="formButton" onClick="disAgree()">不&nbsp;同&nbsp;意</button>&nbsp;
			<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
		</td>
	</tr>	
</table>

</html:form>
</div>
</div>
</body>
</html>
