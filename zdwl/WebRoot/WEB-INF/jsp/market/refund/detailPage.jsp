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
	//执行保存操作
	function doSave() {
			document.forms[0].submit();
	}


	//执行表单重置操作
	function doReset() {
		document.forms[0].reset();
	}
	
	$(function(){
		var kaipiao = $("[name=refund\\.isInvoice]:checked").val();
		if(kaipiao==1){
			$(".kaipiao").show();
		}else{
			$(".kaipiao").hide();
		}
	});
</script>
</head>
<body onLoad="doLoad()">

	<div class="title">经销商退费流转单</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/market/refund.do" styleId="refundForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="update" />
				<html:hidden property="refund.id"/>
				<html:hidden property="refund.dealerId" styleId="dealerId"/>
				

				<table class="formTable">
					<tr>
						<td class="nameCol">经销店：</td>
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
						<td class="nameCol">实收监管费（元/年）：</td>
						<td class="codeCol">
							<html:text property="refund.actualPayment" readonly="true"></html:text>
						</td>
						<td class="nameCol">缴费时间：</td>
						<td class="codeCol">
							<form:calendar disabled="true"
								property="refund.actualPaymentDate" styleId="refund.actualPaymentDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">退还监管费（元/年）：</td>
						<td class="codeCol">
							<html:text property="refund.refundMoney" readonly="true"></html:text>
						</td>
						<td class="nameCol">退费时间：</td>
						<td class="codeCol">
							<form:calendar disabled="true"
								property="refund.refundDate" styleId="refund.refundDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">是否开发票：</td>
						<td class="codeCol">
							<form:radios disabled="true" property="refund.isInvoice" collection="yesorno"></form:radios>
						</td>
						<td class="nameCol kaipiao">开票时间：</td>
						<td class="codeCol kaipiao">
							<form:calendar disabled="true"
								property="refund.invoiceDate" styleId="refund.invoiceDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"/>
						</td>
					</tr>
					<tr class="kaipiao">
						<td class="nameCol">发票类型：</td>
						<td class="codeCol">
							<html:text property="refund.invoiceType" readonly="true"></html:text>
						</td>
						<td class="nameCol">开票单位：</td>
						<td class="codeCol">
							<html:text readonly="true" property="refund.invoiceCompany"></html:text>
						</td>
					</tr>
					<tr>
						<td class="nameCol">退费原因：</td>
						<td class="codeCol">
							<html:text readonly="true" property="refund.refundResource"></html:text>
						</td>
						<td class="nameCol">申请时间：</td>
						<td class="codeCol">
							<form:calendar disabled="true"
								property="refund.applyDate" styleId="refund.applyDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"/>
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
