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
	function doReturn(){
		history.go(-1);
	}
	
</script>
</head>
<body >

	<div class="title">经销商详情</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">


				<table class="formTable">
					<tr>
						<td class="nameCol">经销店名称：</td>
						<td class="codeCol">
							<c:out value="${dealer.dealerName }"></c:out>
						</td>
						<td class="nameCol">店方联系人：</td>
						<td class="codeCol">
							<c:out value="${dealer. contact}"></c:out>
						</td>
					</tr>
					<tr>
						<td class="nameCol">联系人电话：</td>
						<td class="codeCol"><c:out value="${dealer.contactPhone }"></c:out></td>
						<td class="nameCol">金融机构：</td>
						<td class="codeCol" >
							<c:out value="${dealer.bankName }"></c:out>
						</td>
					</tr>
					<tr>
						<td class="nameCol">银行客户经理：</td>
						<td class="codeCol"><c:out value="${dealer.bankContact}"></c:out></td>
						<td class="nameCol">联系电话：</td>
						<td class="codeCol"><c:out value="${dealer.bankPhone }"></c:out></td>
					</tr>
					<tr>
						<td class="nameCol">品牌：</td>
						<td class="codeCol"><c:out value='${dealer.brand }'/></td>
						<td class="nameCol">协议监管模式：</td>
						<td class="codeCol">
							<c:out value="${dealer.supervisionMode }"></c:out>
						</td>
					</tr>
					<tr>
						<td class="nameCol">合作模式：</td>
						<td class="codeCol"><c:out value="${dealer.cooperationModel }"></c:out></td>
						<td class="nameCol">经销商性质：</td>
						<td class="codeCol"><c:out value="${dealer.dealerNature }"></c:out></td>
					</tr>
					<tr>
						<td class="nameCol">省：</td>
						<td class="codeCol"><c:out value="${dealer. province}"></c:out></td>
						<td class="nameCol">市：</td>
						<td class="codeCol"><c:out value="${dealer.city}"></c:out></td>
					</tr>
					<tr>
						<td class="nameCol">监管详细地址：</td>
						<td class="codeCol">
							<c:out value="${dealer.address }"></c:out>
						</td>
						<td class="nameCol">是否提供午餐：</td>
						<td class="codeCol">
							<c:if test="${dealer.provideLunch==1 }">是</c:if>
							<c:if test="${dealer.provideLunch==2 }">否</c:if>
						</td>
					</tr>
					<tr>
						
						<td class="nameCol">是否绑定店：</td>
						<td class="codeCol">
							<c:if test="${dealer.ddorbd==2 }">单店</c:if>
							<c:if test="${dealer.ddorbd==1 }">绑定</c:if>
						</td>
						<c:if test="${dealer.ddorbd==1 }">
							<td class="nameCol">绑定信息：</td>
							<td class="codeCol">
								<c:out value="${dealer.bindInfo }"></c:out>
							</td>
					</c:if>
					</tr>
					<tr>
						<td class="nameCol">付款方式：</td>
						<td class="codeCol">
							<select:payMode state="${dealer.payMode }"></select:payMode>
						</td>
						<td class="nameCol">监管费标准/年：</td>
						<td class="codeCol">
							<c:out value="${dealer.superviseMoney }"></c:out>
							
						</td>
					</tr>
					
					<tr>
						<td class="nameCol">交接时间：</td>
						<td class="codeCol"><select:timestamp timestamp="${dealer.handoverDate}" idtype="date"/></td>
						<td class="nameCol">预进驻时间：</td>
						<td class="codeCol"><select:timestamp timestamp="${dealer.inDate}" idtype="date"/></td>
					</tr>
					<tr>
						<td class="nameCol">合作状态：</td>
						<td class="codeCol">
							<c:if test="${dealer.cooperationState==1 }">
								<c:out value="合作中"/>
							</c:if>
							<c:if test="${dealer.cooperationState==2 }">
								<c:out value="未进店"/>
							</c:if>
							<c:if test="${dealer.cooperationState==3 }">
								<c:out value="撤店"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td class="nameCol">备注：</td>
						<%-- <td class="codeCol" colspan="3"><html:textarea readonly="true"
								property="mpc.inStoreRemark" styleId="mpc.inStoreRemark" style="width:60%;"></html:textarea>
						</td> --%>
					</tr>
					

					<tr class="formTableFoot">
						<td colspan="4" align="center">
							<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
						</td>
					</tr>
				</table>
				<br />
				<div class="message" id="message" style="display: none"></div>

		</div>
	</div>
</body>
</html>
