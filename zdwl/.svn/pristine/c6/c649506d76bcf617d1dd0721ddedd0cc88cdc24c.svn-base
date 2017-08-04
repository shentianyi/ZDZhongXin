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
<script src="/pagejs/payment.js"></script>

<script>
	$(function(){
		init();
		if($("[name=payment\\.isArrears]:checked").val()==2){
			$("#qianfei").hide();
		}
		$("[name=payment\\.isArrears]").click(function(){
			if(this.value==1){
				$("#qianfei").show();
			}else{
				$("#qianfei").hide();
			}
		});
	});
</script>
<style type="text/css">
.nameCol2{
width:25%;
border-bottom:1px solid #eee;padding-top:13px;padding-bottom:13px;
border-right:1px solid #eee; 
text-align: right;
}
.codeCol2{
width:25%;
border-bottom:1px solid #eee;padding-top:13px;padding-bottom:13px;
border-right:1px solid #eee; 
} 
</style>
</head>
<body>

	<div class="title">监管费缴费记录单</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/market/payment.do" styleId="paymentForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="update" />
				<html:hidden property="payment.id"/>
				

				<table class="formTable">
					<tr>
						<td class="nameCol2">经销商：</td>
						<td class="codeCol2" colspan="3">
							<select style="width:50%" id="jxs" name="payment.dealerId" >
								<c:forEach items="${dealers }" var="row">
									<option <c:if test="${paymentForm.payment.dealerId==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="nameCol2">经销店名称：</td>
						<td class="codeCol2"><input type="text" id="dealerName" readonly="readonly"/></td>
					</tr>
					<tr>
						<td class="nameCol2">金融机构：</td>
						<td class="codeCol2">
							<input type="text"id="bankName" readonly="readonly"/>
						</td>
						<td class="nameCol2">品牌：</td>
						<td class="codeCol2"><input type="text" id="brand" readonly="readonly"/></td>
					</tr>
					<tr>
						<td class="nameCol2">进店时间：</td>
						<td class="codeCol2"><input type="text" id="inStoreDate" readonly="readonly"/></td>
						<td class="nameCol2">是否绑定：</td>
						<td class="codeCol2">
							<input type="text"id="ddorbd" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol2">监管费标准（元/年）：</td>
						<td class="codeCol2">
							<input type="text"id="superviseMoney" readonly="readonly"/>
						</td>
						<td class="nameCol2">付费方式：</td>
						<td class="codeCol2">
							<input type="text"id="payMode" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol2">应缴费时间：</td>
						<td class="codeCol2">
							<form:calendar
								property="payment.paymentDate" 
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								 />
						</td>
						<td class="nameCol2">应缴费金额（元）：</td>
						<td class="codeCol2">
							<html:text property="payment.paymentMoney" styleId="paymentMoney" ></html:text>
						</td>
					</tr>
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>实际缴费时间：</td>
						<td class="codeCol2">
							<form:calendar
								property="payment.actualPaymentDate" styleId="payment.actualPaymentDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								readonly="true" />
						</td>
						<td class="nameCol2"><font color="#FF0000">*</font>实际缴费金额（元）：</td>
						<td class="codeCol2">
							<%-- <html:text property="payment.actualPaymentMoney" styleId="actualPaymentMoney"></html:text> --%>
							<input type="text" name="payment.actualPaymentMoney" id="actualPaymentMoney" 
								class="easyui-numberbox" 
								data-options="min:0,precision:2,value:<c:out value='${paymentForm.payment.actualPaymentMoney }'/>"></input>
						</td>
					</tr>
					
					<tr>
						<td class="nameCol2">汇款账号：</td>
						<td class="codeCol2">
							<html:text property="payment.remittanceAccount"></html:text>
						</td>
						<td class="nameCol2">收款账号：</td>
						<td class="codeCol2">
							<html:text property="payment.collectionAccount"></html:text>
						</td>
					</tr>
					
					<tr>
						<td class="nameCol2">收款开户行：</td>
						<td class="codeCol2">
							<html:text property="payment.accountBank"></html:text>
						</td>
						<td class="nameCol2">收款单位：</td>
						<td class="codeCol2">
							<html:text property="payment.collectionUnit"></html:text>
						</td>
					</tr>
					
					<tr>
						<td class="nameCol2">是否欠费：</td>
						<td class="codeCol2">
							<form:radios
								property="payment.isArrears" collection="yesorno"></form:radios>
						</td>
						
					</tr>
					
					<tr id="qianfei" >
						<td class="nameCol2">欠费时间：</td>
						<td class="codeCol2">
							<form:calendar
								property="payment.arrearsDate" styleId="arrearsDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"/>
						</td>
						<td class="nameCol2">欠费金额：</td>
						<td class="codeCol2">
							<html:text property="payment.arrearsMoney" styleId="arrearsMoney"></html:text>
						</td>
					</tr>
					<tr>
						<td class="nameCol2">备注：</td>
						<td class="codeCol2">
							<html:text property="payment.remark"></html:text>
						</td>
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
