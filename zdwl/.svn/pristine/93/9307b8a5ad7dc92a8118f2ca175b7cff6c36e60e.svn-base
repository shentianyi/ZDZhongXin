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
		if($("[name=payment\\.isArrears]:checked").val()==2){
			$("#qianfei").hide();
		}
	});
	
	//执行表单重置操作
	function doReset() {
		document.forms[0].reset();
	}
</script>
</head>
<body onLoad="doLoad()">

	<div class="title">监管费缴费记录单</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/market/payment.do" styleId="paymentForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="update" />
				<html:hidden property="payment.id"/>
				<html:hidden property="payment.dealerId" styleId="dealerId"/>
				

				<table class="formTable">
					<tr>
						<td class="nameCol">经销店名称：</td>
						<td class="codeCol"><input type="text" id="dealerName" readonly="readonly"/></td>
						<td class="nameCol">金融机构：</td>
						<td class="codeCol">
							<input type="text"id="bankName" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">品牌：</td>
						<td class="codeCol"><input type="text" id="brand" readonly="readonly"/></td>
						<td class="nameCol">进店时间：</td>
						<td class="codeCol"><input type="text" id="inStoreDate" readonly="readonly"/></td>
					</tr>
					<tr>
						<td class="nameCol">是否绑定：</td>
						<td class="codeCol">
							<input type="text"id="ddorbd" readonly="readonly"/>
						</td>
						<td class="nameCol">监管费标准（元/年）：</td>
						<td class="codeCol">
							<input type="text"id="superviseMoney" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">付费方式：</td>
						<td class="codeCol">
							<input type="text"id="payMode" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">应缴费时间：</td>
						<td class="codeCol">
							<form:calendar
								property="payment.paymentDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								disabled="true"/>
						</td>
						<td class="nameCol">应缴费金额（元）：</td>
						<td class="codeCol">
							<html:text property="payment.paymentMoney" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td class="nameCol">实际缴费时间：</td>
						<td class="codeCol">
							<form:calendar
								property="payment.actualPaymentDate" styleId="payment.actualPaymentDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								disabled="true" />
						</td>
						<td class="nameCol">实际缴费金额（元）：</td>
						<td class="codeCol">
							<html:text property="payment.actualPaymentMoney" readonly="true"></html:text>
						</td>
					</tr>
					
					<tr>
						<td class="nameCol">汇款账号：</td>
						<td class="codeCol">
							<html:text property="payment.remittanceAccount" readonly="true"></html:text>
						</td>
						<td class="nameCol">收款账号：</td>
						<td class="codeCol">
							<html:text property="payment.collectionAccount" readonly="true"></html:text>
						</td>
					</tr>
					
					<tr>
						<td class="nameCol">收款开户行：</td>
						<td class="codeCol">
							<html:text property="payment.accountBank" readonly="true"></html:text>
						</td>
						<td class="nameCol">收款单位：</td>
						<td class="codeCol">
							<html:text property="payment.collectionUnit" readonly="true"></html:text>
						</td>
					</tr>
					
					<tr >
						<td class="nameCol">是否欠费：</td>
						<td class="codeCol">
							<form:radios
								property="payment.isArrears" collection="yesorno" disabled="true"></form:radios>
						</td>
					</tr>
					
					<tr id="qianfei">
						<td class="nameCol">欠费时间：</td>
						<td class="codeCol">
							<form:calendar
								property="payment.arrearsDate" styleId="payment.arrearsDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" disabled="true"/>
						</td>
						<td class="nameCol">欠费金额：</td>
						<td class="codeCol">
							<html:text property="payment.arrearsMoney" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td class="nameCol">备注：</td>
						<td class="codeCol">
							<html:text property="payment.remark" readonly="true"></html:text>
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
