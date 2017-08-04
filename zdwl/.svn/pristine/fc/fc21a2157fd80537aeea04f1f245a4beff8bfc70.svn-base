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
function doLoad(){
	changeYj();
}
//执行返回列表操作
function doReturn(){
	location = "<url:context/>/ledger/repaircost.do?method=repairCostLedger";
}
function changeYj() {
	var repositoryId = document.getElementById("repositoryId").value;
	var url = "../json/getMailCost.do?callback=?&id="+repositoryId;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setYj(data[0]);
	});
}
	
function setYj(yj){
	$("#yjgh").val(yj.gh);
	$("#yjjxs").val(yj.jxs);
	$("#yjjrjg").val(yj.jrjg);
}
</script>
</head>
<body onLoad="doLoad()">

	<div class="title">设备维修申请单</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/ledger/repaircost" styleId="repairCostForm" method="post" onsubmit="return false">
				<html:hidden property="repaircost.id" styleId="repaircost.id"/>
				<html:hidden property="repaircost.promoter" styleId="repaircost.promoter"/>
				<input type="hidden" name="method" id="method" value="repairCostDetail">
				<input type="hidden" name="repositoryId" id="repositoryId" value="<c:out value='${repositoryId}'/>" />
				
				<table class="formTable">
					<tr>
						<td class="nameCol">姓名：</td>
						<td class="codeCol">
							<c:out value="${username}"/>
						</td>
						<td class="nameCol">经销商：</td>
						<td class="codeCol">
							<input type="text" id="yjjxs" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">工号：</td>
						<td class="codeCol">
							<input type="text" id="yjgh" readonly="readonly"/>
						</td>
						<td class="nameCol">金融机构：</td>
						<td class="codeCol">
							<input type="text" id="yjjrjg" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">维修项目：</td>
						<td class="codeCol">
							<html:text property="repaircost.repair_project" styleId="repaircost.repair_project"></html:text>
						</td>
						<td class="nameCol"></td>
						<td class="codeCol"></td>
					</tr>
					<tr>
						<td class="nameCol">具体问题：</td>
						<td class="codeCol">
							<html:text property="repaircost.problem" styleId="repaircost.problem"></html:text>
						</td>
						<td class="nameCol">维修费用(元)：</td>
						<td class="codeCol">
							<html:text property="repaircost.money" styleId="repaircost.money"></html:text>
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
								<c:if test="${row.approvalResult==1}">同意&nbsp;</c:if>
								<c:if test="${row.approvalResult==2}">不同意&nbsp;</c:if>
								<c:out value="${row.remark }"/>
							</td>
						</tr>
						</logic:iterate>
					</c:if>
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
