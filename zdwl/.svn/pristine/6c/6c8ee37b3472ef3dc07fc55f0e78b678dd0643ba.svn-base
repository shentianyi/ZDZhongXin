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
		changeRoster($("#rosterId").val());
	});
	
	//执行保存操作
	function doSave() {

		//对表单内容进行验证，包括对输入类型等限制方式
		if (validateMain("complaintForm")) {
			//为时间类型输入项补齐时间戳
			setTimeSuffix();
			//提交表单
			document.forms[0].submit();
		}
	}

	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/complaint.do?method=findList";
	}

	//执行表单重置操作
	function doReset() {
		document.forms[0].reset();
	}

	function changeDealer(id) {
		if (id == -1) {
			setDealer(new Object());
			return;
		}
		var url = "../json/getDealerByIdJsonAction.do?callback=?&id=" + id;
		$.getJSON(url, function(result) {
			var data = result.data;
			console.info(data);
			setDealer(data[0]);
		});
	}
	
	function changeRoster(id) {
		if (id == -1) {
			setRoster(new Object());
			return;
		}
		var url = "../json/getRosterById.do?callback=?&id=" + id;
		$.getJSON(url, function(result) {
			var data = result.data;
			console.info(data);
			setRoster(data);
		});
	}

	function setDealer(dealer) {
		$("#dealerName").val(dealer.dealerName);
		$("#brand").val(dealer.brand);
		$("#bankName").val(dealer.bankName);
	}
	
	function setRoster(roster){
		$("#rosterName").val(roster.name);
		$("#staffNo").val(roster.staffNo);
		$("#rosterPhone").val(roster.phone);
	}
</script>
</head>
<body onLoad="doLoad()">

	<div class="title">来电投诉记录表</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/complaint.do" styleId="complaintForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="approval" />
				<html:hidden property="complaint.id"/>
				<html:hidden property="complaint.dealerId" styleId="dealerId"/>
				<html:hidden property="complaint.rosterId" styleId="rosterId"/>

				<table class="formTable">
					<tr>
						<td class="nameCol">经销店：</td>
						<td class="codeCol"><input type="text" id="dealerName"
							readonly="readonly" /></td>
						<td class="nameCol">金融机构：</td>
						<td class="codeCol"><input type="text" id="bankName"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="nameCol">品牌：</td>
						<td class="codeCol"><input type="text" id="brand"
							readonly="readonly" /></td>
						<td class="nameCol">投诉人职位：</td>
						<td class="codeCol"><html:text property="complaint.complainantPosition"></html:text></td>
					</tr>
					<tr>
						<td class="nameCol">投诉人姓名：</td>
						<td class="codeCol"><html:text property="complaint.complainantName"></html:text></td>
						<td class="nameCol">投诉人电话：</td>
						<td class="codeCol"><html:text property="complaint.complainantPhone"></html:text></td>
					</tr>
					<tr>
						<td class="nameCol">监管员工号：</td>
						<td class="codeCol"><input type="text" id="staffNo" readonly="readonly"></td>
						<td class="nameCol">监管员姓名：</td>
						<td class="codeCol"><input type="text" id="rosterName" readonly="readonly"></td>
					</tr>
					<tr>
						<td class="nameCol">监管员电话：</td>
						<td class="codeCol"><input type="text" id="rosterPhone" readonly="readonly"></td>
						
					</tr>
					<tr>
						<td class="nameCol">投诉问题分类：</td>
						<td class="codeCol" colspan="3">
							<form:radios collection="complaintSorts" property="complaint.complaintSorts"></form:radios>
						</td>
					</tr>
					<tr>
						<td class="nameCol">投诉内容：</td>
						<td class="codeCol" colspan="3"><html:text property="complaint.complaintContent"></html:text></td>
					</tr>
					<tr>
						<td class="nameCol">处理部门：</td>
						<td class="codeCol" colspan="3">
							<form:radios collection="processingDepartment" property="complaint.processingDepartment"></form:radios>
						</td>
					</tr>
					<c:if test="${approvals!=null }">
						<tr class="formTitle">
							<td colspan="4">意见</td>
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
					<tr class="formTableFoot">
						<td colspan="4" align="center">
							<button class="formButton" onClick="doReturn()">返&nbsp;回</button>&nbsp;
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
