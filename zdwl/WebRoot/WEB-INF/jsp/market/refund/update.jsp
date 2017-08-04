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
<script src="/pagejs/refund.js"></script>
<script>
	$(function() {
		init();
		var kaipiao = $("[name=refund\\.isInvoice]:checked").val();
		if(kaipiao==1){
			$(".kaipiao").show();
		}else{
			$(".kaipiao").hide();
		}
		
		$("[name=refund\\.isInvoice]").click(function(){
			if(this.value==1){
				$(".kaipiao").show();
			}else{
				$(".kaipiao").hide();
			}
		});
	});
</script>
<style type="text/css">
.nameCol2{
width:25%;
border-bottom:1px solid #eee;padding-top:13px;padding-bottom:13px;
border-right:1px solid #eee; text-align: right;
}
.codeCol2{
width:25%;
border-bottom:1px solid #eee;padding-top:13px;padding-bottom:13px;
border-right:1px solid #eee; 
} 
</style>
</head>
<body>

	<div class="title">经销商退费流转单</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/market/refund.do" styleId="refundForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="update" />
				<html:hidden property="refund.id" />


				<table class="formTable">
					<tr>
						<td class="nameCol2">经销商：</td>
						<td class="codeCol2" colspan="3">
							<select style="width:50%" id="jxs" name="refund.dealerId" >
								<c:forEach items="${dealers }" var="row">
									<option <c:if test="${refundForm.refund.dealerId==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="nameCol2">金融机构：</td>
						<td class="codeCol2"><input type="text" id="bankName"
							readonly="readonly" /></td>
						<td class="nameCol2">品牌：</td>
						<td class="codeCol2"><input type="text" id="brand"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="nameCol2">进店时间：</td>
						<td class="codeCol2"><input type="text" id="inStoreDate"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>缴费时间：</td>
						<td class="codeCol2"><form:calendar
								property="refund.actualPaymentDate"
								styleId="refund.actualPaymentDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" /></td>
						<td class="nameCol2">实收监管费（元/年）：</td>
						<td class="codeCol2">
							<input type="text" name="refund.actualPayment" id="actualPayment" 
							class="easyui-numberbox" value="<c:out value='${refundForm.refund.actualPayment }'/>"
							data-options="min:0,precision:2"></input>
						</td>
					</tr>
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>退还监管费（元/年）：</td>
						<td class="codeCol2">
							<input type="text" name="refund.refundMoney" id="refundMoney" 
							class="easyui-numberbox" value="<c:out value='${refundForm.refund.refundMoney }'/>"
							data-options="min:0,precision:2"></input>
						</td>
						<td class="nameCol2"><font color="#FF0000">*</font>退费时间：</td>
						<td class="codeCol2"><form:calendar
								property="refund.refundDate" styleId="refund.refundDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" /></td>
					</tr>
					<tr>
						<td class="nameCol2">是否开发票：</td>
						<td class="codeCol2"><form:radios property="refund.isInvoice"
								collection="yesorno"></form:radios></td>
						<td class="nameCol2 kaipiao">开票时间：</td>
						<td class="codeCol2 kaipiao"><form:calendar
								property="refund.invoiceDate" styleId="refund.invoiceDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" /></td>
					</tr>
					<tr class="kaipiao">
						<td class="nameCol2">发票类型：</td>
						<td class="codeCol2"><html:text property="refund.invoiceType"></html:text>
						</td>
						<td class="nameCol2">开票单位：</td>
						<td class="codeCol2"><html:text
								property="refund.invoiceCompany"></html:text></td>
					</tr>
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>退费原因：</td>
						<td class="codeCol2"><html:text
								property="refund.refundResource" styleId="refundResource"></html:text>
						</td>
						<td class="nameCol2"><font color="#FF0000">*</font>申请时间：</td>
						<td class="codeCol2"><form:calendar
								property="refund.applyDate" styleId="refund.applyDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" /></td>
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
