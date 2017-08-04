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
<script>
	//页面初始化函数
	function doLoad(){
		//显示提示信息
		showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	}
	$(function(){
		changeDealer($('#dealerId').val());
	});
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
		document.forms[0].submit();
	}

	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/market/modeChange.do?method=findList";
	}

	//执行表单重置操作
	function doReset() {
		document.forms[0].reset();
	}

</script>
</head>
<body onLoad="doLoad()">

	<div class="title">操作模式变更流转单</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/market/modeChange.do" styleId="mcForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="approval" />
				<input type="hidden" name="query.id" value="<c:out value='${mcForm.mc.id }'/>"/>
				<html:hidden property="query.approvalState" styleId="approvalState"/>
				<input type="hidden"id="dealerId" value="<c:out value='${mcForm.mc.dealerId }'/>"/>

				<table class="formTable">
					<tr class="formTitle">
						<td colspan="4">市   场   部</td>
					</tr>
					<tr>
						<td class="nameCol">经销店名称：</td>
						<td class="codeCol"><input style="width:100%;" type="text" id="dealerName" readonly="readonly"/></td>
						<td class="nameCol">店方联系人：</td>
						<td class="codeCol"><input type="text" id="contact" readonly="readonly"/></td>
					</tr>
					<tr>
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
							<input type="radio" value="贷货巡库" id="supervisionMode_3" disabled="disabled"/>贷后巡库
							<input type="radio" value="金融监管库" id="supervisionMode_4" disabled="disabled"/>金融监管库
						</td>
						<td class="nameCol">合作模式：</td>
						<td class="codeCol">
							两方：<input type="radio" id="cooperationModel_1" disabled="disabled"/>
							三方：<input type="radio" id="cooperationModel_2" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">变更监管操作模式：</td>
						<td class="codeCol">
							<form:radios styleId="changeOperationMode" disabled="disabled"
								property="mc.changeOperationMode" collection="supervisionModes"></form:radios>
						</td>
						<td class="nameCol">变更时间：</td>
						<td class="codeCol">
							<form:calendar
								property="mc.changeDate" styleId="mc.changeDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								disabled="true"/>
						</td>
					</tr>
					<tr>
					    <td class="nameCol">原监管费用(元/年)：</td>
						<td class="codeCol"><input type="text" id="superviseMoney"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="nameCol">费用是否变更：</td>
						<td class="codeCol">
							<form:radios disabled="true"
								property="mc.moneyIsChange" collection="yesorno">
								</form:radios>
						</td>
						<td class="nameCol">制单时间：</td>
						<td class="codeCol">
							<form:calendar
								property="mc.createDate" styleId="mc.createDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								disabled="true" />
						</td>
					</tr>
					<tr>
						<td class="nameCol">备注：</td>
						<td class="codeCol" colspan="3">
							<html:textarea property="mc.remark" readonly="true"/>
						</td>
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
					<tr>
						<td class="nameCol">审批意见：</td>
						<td class="codeCol" colspan="3"><html:textarea property="query.remark"></html:textarea></td>
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
