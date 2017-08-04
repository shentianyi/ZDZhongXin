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

	function agree() {
		$("#approvalState").val(1);
		doSave();
	}

	function disAgree() {
		$("#approvalState").val(2);
		doSave();
	}
	//执行保存操作
	function doSave() {

		//对表单内容进行验证，包括对输入类型等限制方式
		if (validateMain("refundForm")) {
			//为时间类型输入项补齐时间戳
			setTimeSuffix();
			//提交表单
			document.forms[0].submit();
		}
	}

	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/supervisor/checkStock.do?method=findList";
	}

	//执行表单重置操作
	function doReset() {
		document.forms[0].reset();
	}

	function changeDealer(id) {
		if (id == -1) {
			document.forms[0].reset();
			return;
		}
		var url = "../json/getDealerByIdJsonAction.do?callback=?&id=" + id;
		$.getJSON(url, function(result) {
			var data = result.data;
			setDealer(data[0]);
		});
	}

	function setDealer(dealer) {
		var tDate;
		$("#dealerName").val(dealer.dealerName);
		$("#brand").val(dealer.brand);
		$("#bankName").val(dealer.bankName);
		//进店时间
		tDate = new Date(dealer.inDate.time);
		$("#inDate").val(
				tDate.getFullYear() + "-" + (tDate.getMonth() + 1) + "-"
						+ tDate.getDate());
	}
</script>
</head>
<body onLoad="doLoad()">

	<div class="title">查库频次申请</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/market/refund.do" styleId="refundForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="approval" />
				<html:hidden property="query.approvalState" styleId="approvalState" />
				<html:hidden property="query.id" />

				<table class="formTable">
					<logic:iterate name="dealerList" id="row" indexId="index">
						<tr>
							<td class="nameCol">经销商：</td>
							<td class="codeCol"><c:out value="${row.dealerName }"></c:out>
							<td class="nameCol">金融机构：</td>
							<td class="codeCol"><c:out value="${row.bankName }"></c:out>
							
						</tr>
						<tr>
							<td class="nameCol">品牌：</td>
							<td class="codeCol"><c:out value="${row.brand }"></c:out>
						</tr>
					</logic:iterate>

					<tr>
						<td class="nameCol">申请人：</td>
						<td class="codeCol"><c:out value="${createUser.userName }"></c:out>
						</td>
						<td class="nameCol">申请时间：</td>
						<td class="codeCol"><select:timestamp
								timestamp="${checkStockForm.checkStock.createDate}"
								idtype="date" /></td>
					</tr>
					<tr>
						<td class="nameCol">申请原因：</td>
						<td class="codeCol"><c:out value="${checkStockForm.checkStock.reason }"></c:out></td>
						<td class="nameCol">查库频次：</td>
						<td class="codeCol"><c:out value="${checkStockForm.checkStock.count }"></c:out>
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
