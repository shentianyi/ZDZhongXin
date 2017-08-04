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
						<td class="nameCol">金融机构：</td>
                        <td class="codeCol"><c:out value="${dealer.bankName }"></c:out></td>
					</tr>
					
					<tr>
                        <td class="nameCol">省：</td>
                        <td class="codeCol"><c:out value="${dealer. province}"></c:out></td>
                        <td class="nameCol">市：</td>
                        <td class="codeCol"><c:out value="${dealer.city}"></c:out></td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">预进驻时间：</td>
                        <td class="codeCol"><select:timestamp timestamp="${dealer.inDate}" idtype="date"/></td>
                        <td class="nameCol">品牌：</td>
                        <td class="codeCol"><c:out value='${dealer.brand }'/></td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">经销商性质：</td>
                        <td class="codeCol"><c:out value="${dealer.dealerNature }"></c:out></td>
                        <td class="nameCol">监管详细地址：</td>
                        <td class="codeCol">
                            <c:out value="${dealer.address }"></c:out>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol"><span class="red">*</span>联系人：</td>
                        <td class="codeCol">
                            <c:out value="${dealer.contact }"></c:out>
                        </td>
                        <td class="nameCol">联系人电话：</td>
                        <td class="codeCol"><c:out value="${dealer.contactPhone }"></c:out></td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">监管员姓名：</td>
                        <td class="codeCol"><c:out value="${dealer.superviseName }"></c:out></td>
                        <td class="nameCol">监管员电话：</td>
                        <td class="codeCol"><c:out value="${dealer.supervisePhone }"></c:out></td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol"><span class="red">*</span>金融机构联系人：</td>
                        <td class="codeCol"><c:out value="${dealer.bankContact }"></c:out></td>
                        <td class="nameCol"><span class="red">*</span>金融机构电话：</td>
                        <td class="codeCol"><c:out value="${dealer.bankPhone }"></c:out></td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol"><span class="red">*</span>设备提供方：</td>
                        <td class="codeCol"><c:out value="${dealer.equipmentProvide }"></c:out></td>
                        <td class="nameCol">协议监管模式：</td>
                        <td class="codeCol">
                            <c:out value="${dealer.supervisionMode }"></c:out>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">合作模式：</td>
                        <td class="codeCol">
                            <c:if test="${dealer.cooperationModel == '1'}">
                                <c:out value="两方"/>
                            </c:if>
                            <c:if test="${dealer.cooperationModel == '2'}">
                                <c:out value="三方"/>
                            </c:if>
                        </td>
                        <td class="nameCol">单店/绑定：</td>
                        <td class="codeCol">
                            <c:if test="${dealer.ddorbd==2 }">
                                <c:out value="单店"/>
                            </c:if>
                            <c:if test="${dealer.ddorbd==1 }">
                                <c:out value="绑定"/>
                            </c:if>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">绑定信息：</td>
                        <td class="codeCol"><c:out value="${dealer.bindInfo }"></c:out></td>
                        <td class="nameCol">监管费标准/年：</td>
                        <td class="codeCol"><c:out value="${dealer.superviseMoney }"></c:out></td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">付费方式：</td>
                        <td class="codeCol"><select:payMode state="${dealer.payMode }"></select:payMode></td>
                        <td class="nameCol">是否变更监管费：</td>
                        <td class="codeCol">
                            <c:if test="${dealer.isChangeSuperviseMoney==1 }">
                               		 是
                            </c:if>
                            <c:if test="${dealer.isChangeSuperviseMoney==0 }">
                                	 否
                            </c:if>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">变更监管费时间：</td>
                        <td class="codeCol"><c:out value="${dealer.changeSuperviseMoneyDate }"></c:out></td>
                        <td class="nameCol">变更前监管费标准：</td>
                        <td class="codeCol"><c:out value="${dealer.changeBeforeInfo }"></c:out></td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">交接公司：</td>
                        <td class="codeCol"><c:out value="${dealer.handoverCompany }"></c:out></td>
                        <td class="nameCol">交接时间：</td>
                        <td class="codeCol"><select:timestamp timestamp="${dealer.handoverDate}" idtype="date"/></td>
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
                        <td class="nameCol">撤店时间：</td>
                        <td class="codeCol"><c:out value="${dealer.exitDate }"></c:out></td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol"><span class="red">*</span>授信额度：</td>
                        <td class="codeCol"><c:out value="${dealer.credit }"></c:out></td>
                        <td class="nameCol">汇票下发方式：</td>
                        <td class="codeCol"><c:out value="${dealer.draft_way }"></c:out></td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">押车方式：</td>
                        <td class="codeCol"><c:out value="${dealer.guard_way }"></c:out></td>
                        <td class="nameCol">合格证送达方式：</td>
                        <td class="codeCol"><c:out value="${dealer.certificate_delivery }"></c:out></td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">实际监管模式：</td>
                        <td class="codeCol"><c:out value="${dealer.actual_supervision }"></c:out></td>
                        <td class="nameCol">钥匙监管：</td>
                        <td class="codeCol"><c:out value="${dealer.key_supervise }"></c:out></td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">二网：</td>
                        <td class="codeCol"><c:out value="${dealer.website }"></c:out></td>
                        <td class="nameCol">二库：</td>
                        <td class="codeCol"><c:out value="${dealer.old_car }"></c:out></td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">监管物移动百分比：</td>
                        <td class="codeCol"><c:out value="${dealer.ydbl }"></c:out></td>
                        <td class="nameCol">特殊操作：</td>
                        <td class="codeCol"><c:out value="${dealer.special_oper }"></c:out></td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">是否对接：</td>
                        <td class="codeCol">
                            <c:if test="${dealer.isNeedHandover==1 }">
                                <c:out value="是"/>
                            </c:if>
                            <c:if test="${dealer.isNeedHandover==2 }">
                                <c:out value="否"/>
                            </c:if>
                        </td>
                        <td class="nameCol"></td>
                        <td class="codeCol"></td>
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
