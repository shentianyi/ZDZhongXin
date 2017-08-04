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
		if($("[name=mpc\\.isSignAnAgreement]:checked").val()==2){
			$(".agreementIsRecovery").hide();
		}
	});
	
</script>
</head>
<body onLoad="doLoad()">

	<div class="title">项目进驻流转单详情</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/market/projectCirculationForm" styleId="mpcForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="approval" />
				<input type="hidden" name="query.id" value="<c:out value='${mpcForm.mpc.id }'/>"/>
				<input type="hidden" name="query.approvalState" id="approvalState" />
				

				<table class="formTable">

					<tr class="formTitle">
						<td colspan="4">一、新进店信息</td>
					</tr>
					<tr>
						<td class="nameCol">经销店名称：</td>
						<td class="codeCol">
							<html:text readonly="true" style="width:50%;" property="mpc.dealerName"></html:text>
						</td>
						<td class="nameCol">店方联系人：</td>
						<td class="codeCol">
							<html:text readonly="true" property="mpc.dealerPerson" styleId="mpc.dealerPerson"></html:text>
						</td>
					</tr>
					<tr>
						<td class="nameCol">联系人电话：</td>
						<td class="codeCol"><html:text readonly="true" property="mpc.dealerPhone"></html:text></td>
						<td class="nameCol">金融机构：</td>
						<td class="codeCol" colspan="3">
							<html:text readonly="true" property="bank.bankFullName"></html:text>
						</td>
					</tr>
					<tr>
						<td class="nameCol">银行客户经理：</td>
						<td class="codeCol"><html:text readonly="true" property="bm.manager"></html:text></td>
						<td class="nameCol">联系电话：</td>
						<td class="codeCol"><html:text readonly="true" property="bm.managerPhone"></html:text></td>
					</tr>
					<tr>
						<td class="nameCol">品牌：</td>
						<td class="codeCol"><c:out value='${brandName }'/></td>
						<td class="nameCol">协议监管模式：</td>
						<td class="codeCol"><form:radios styleId="supervisionMode" disabled="disabled"
								property="mpc.supervisionMode" collection="supervisionModes"></form:radios>
						</td>
					</tr>
					<tr>
						<td class="nameCol">合作模式：</td>
						<td class="codeCol"><form:radios disabled="true"
								property="mpc.cooperationModel" styleId="mpc.cooperationModel"
								collection="cooperationModels" /></td>
						<td class="nameCol">经销商性质：</td>
						<td class="codeCol"><html:text readonly="true" property="mpc.dealerNature"
								styleId="mpc.dealerNature"></html:text></td>
					</tr>
					<tr>
						<td class="nameCol">监管详细地址：</td>
						<td class="codeCol" colspan="3"><html:text readonly="true"  style="width:80%;"
								property="mpc.superviseAddress" styleId="mpc.superviseAddress"></html:text>
						</td>
						
					</tr>
					<tr>
						<td class="nameCol">是否绑定店：</td>
						<td class="codeCol"><form:radios property="mpc.isBindShop" disabled="true"
								collection="yesorno"></form:radios></td>
						<td class="nameCol">是否提供午餐：</td>
						<td class="codeCol"><form:radios property="mpc.provideLunch" disabled="true"
								collection="yesorno"></form:radios></td>
					</tr>
					<c:if test="${mpcForm.mpc.isBindShop==1 }">
						<tr>
							<td class="nameCol">绑定店名称：</td>
							<td class="codeCol"><input type="text" readonly="readonly" value='<c:out value="${bindShopName }"></c:out>'></td>
							<td class="nameCol">距离(公里)：</td>
							<td class="codeCol"><input type="text" readonly="readonly" value='<c:out value="${juli }"></c:out>'>KM</td>
						</tr>
					</c:if>
					
					<tr>
						<td class="nameCol">是否需要交接：</td>
						<td class="codeCol"><form:radios disabled="true"
								property="mpc.isNeedHandover" collection="yesorno"></form:radios>
						</td>
						<td class="nameCol">预进驻时间：</td>
						<td class="codeCol"><form:calendar property="mpc.inStoreDate" disabled="true"
								styleId="mpc.inStoreDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								readonly="true" /></td>
					</tr>
					<c:if test="${mpcForm.mpc.isNeedHandover==1 }">
					<tr>
						<td class="nameCol">交接时间：</td>
						<td class="codeCol"><form:calendar disabled="true"
								property="mpc.handoverDate" styleId="mpc.handoverDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								readonly="true" /></td>
						<td class="nameCol">交接公司：</td>
						<td class="codeCol"><html:text readonly="true" property="mpc.handoverCompany"
								styleId="mpc.handoverCompany"></html:text></td>
					</tr>
					</c:if>
					<tr>
						<td class="nameCol">备注：</td>
						<td class="codeCol" colspan="3"><html:textarea readonly="true"
								property="mpc.inStoreRemark" styleId="mpc.inStoreRemark" style="width:60%;"></html:textarea>
						</td>
					</tr>
											<!-- 市场部信息 -->
				<c:if test="${currClientType==5||currClientType==10||currClientType==12||currClientType==30 }">
					<tr class="formTitle">
						<td colspan="4">二、市场部信息</td>
					</tr>
					<tr>
						<td class="nameCol">监管费责任人：</td>
						<td class="codeCol"><html:text readonly="true"
								property="mpc.superviseMoneyPerson" styleId="mpc.superviseMoneyPerson"></html:text>
						</td>
						<td class="nameCol">付费方式：</td>
						<td class="codeCol">
							<form:select property="mpc.payMode" styleId="mpc.payMode" disabled="true" >
								<html:optionsCollection name="payModes" label="label" value="value"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="nameCol">监管费（元/年）：</td>
						<td class="codeCol"><html:text readonly="true"
								property="mpc.superviseMoney" styleId="mpc.superviseMoney"></html:text>
						</td>
						<td class="nameCol">发票开具方式：</td>
						<td class="codeCol">
							<html:text readonly="true"
								property="mpc.invoiceMode" styleId="mpc.invoiceMode"></html:text>
						</td>
					</tr>
					<tr>
						<td class="nameCol">协议是否签署：</td>
						<td class="codeCol">
							<form:radios disabled="true" collection="yesorno" property="mpc.isSignAnAgreement"></form:radios>
						</td>
						<td class="nameCol agreementIsRecovery">协议是否回收：</td>
						<td class="codeCol agreementIsRecovery">
							<form:radios disabled="true" collection="yesorno" property="mpc.agreementIsRecovery"></form:radios>
						</td>
					</tr>
					<tr>
						<td class="nameCol">付款对象：</td>
						<td class="codeCol">
							<html:text readonly="true"
								property="mpc.paymentObject" styleId="mpc.paymentObject"></html:text>
						</td>
						<td class="nameCol">授信额度：</td>
						<td class="codeCol">
							<html:text readonly="true"
								property="mpc.credit" styleId="mpc.superviseMoney"></html:text>
						</td>
					</tr>
					<tr>
						<td class="nameCol" >备注：</td>
						<td class="codeCol" colspan="3">
							<html:textarea readonly="true" property="mpc.marketRemark" ></html:textarea>
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
