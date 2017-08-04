<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.csms.bank.contants.BankContants"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>
<%@ page import="com.zd.csms.constants.Constants"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<html class="h-100">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>中都汽车金融监管系统</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/public.css" />
<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/jquery.divscroll.js" type="text/javascript"></script>
<script type="text/javascript">
function doReturn(){
    window.location="<url:context/>/ledger/dealer.do?method=findList";
}
</script>
</head>
<body>
	
<div class="title">经销商详情</div>
	<%-- 
	<table class="formTable">
					<tr>
						<td class="nameCol">经销店名称：</td>
						<td class="codeCol">
							<c:out value="${dealer.dealerName }"></c:out>
						</td>
						     <td class="nameCol">协议监管模式：</td>
                        <td class="codeCol">
                            <c:out value="${dealer.supervisionMode }"></c:out>
                        </td>
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
	</table> --%>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/ledger/dealer" styleId="dealerForm" method="post">
		<input type="hidden" name="method" id="method" value="findList"/>
		<div class="public-main-table hidden abs" style="top:20px;">
			<div class="ly-cont">
				<table class="t-table" border="0" cellspacing="0" cellpadding="0">
					<thead class="t-thead">
						<tr class="t-tr">
							<th class="t-th">序号</th>
							<th class="t-th">变更前操作模式</th>
							<th class="t-th">变更后操作模式</th>
							<th class="t-th">操作模式变更时间</th>
							<th class="t-th">变更前监管费标准/年</th>
							<th class="t-th">变更后监管费标准/年</th>
							<th class="t-th">监管费变更时间</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td"><c:out value="${index+1}"/></td>
								<td class="t-td"><c:out value="${row.beforeOperationMode }"/></td>
								<td class="t-td"><c:out value="${row.lastOperationMode }"/></td>
								<td class="t-td">
									<select:timestamp timestamp="${row.modifyTime}" idtype="date"/>
								</td>
								<td class="t-td"><c:out value="${row.beforeSuperviseMoneyDate }"/></td>
								<td class="t-td"><c:out value="${row.lastSuperviseMoneyDate }"/></td>
								<td class="t-td">
									<select:timestamp timestamp="${row.changeSuperviseMoneyDate}" idtype="date"/>
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		</div>
		<button class="formButton" onClick="doReturn()" style="position:absolute;bottom:0px;">&nbsp;返&nbsp;回&nbsp;</button>
		</html:form>
	</div>
</div>

</body>
</html>