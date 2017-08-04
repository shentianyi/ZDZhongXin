﻿<%@page import="com.zd.csms.rbac.constants.RoleConstants"%>
<%@page import="com.zd.csms.rbac.util.UserSession"%>
<%@page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>
<%@page import="com.zd.csms.bank.contants.BankContants"%>
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

<%@ page import="com.zd.csms.constants.Constants"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/common.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
function detail(id){
	location.href="<url:context/>/twodate.do?method=twodetail&duedate.id="+id;
}
</script>
</head>
<body>
	<div class="title">二网日查库台账</div>
	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<div class="tagbarBody">
				<html:form action="/twodate.do" styleId="duedateForm" method="post"
					onsubmit="return false">
					<input name="method" id="method" type="hidden" value="twoLedger" />

					<div class="dvScroll">
						<table class="listTalbe" cellpadding="0" cellspacing="0">
							<thead>
								<tr class="title">
									<td>序号</td>
									<td><thumbpage:order cname="经销商名称" filedName="dealerName" /></td>
									<td><thumbpage:order cname="检查时间" filedName="agreement_address"/></td>
									<td><thumbpage:order cname="审批状态" filedName="approvalState" /></td>
									<td align="center">操作</td>
								</tr>
							</thead>
							<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()"
								onClick="clickRow()">
								<logic:iterate name="list" id="row" indexId="index">
									<tr class="listTr_a">
										<td align="center">&nbsp;<c:out value="${index+1}" /></td>
										<td><select:dealerName dealerid="${row.superviseid}" idtype="supervisorId"/></td>
										<td align="center"><select:timestamp timestamp="${row.createtime}" idtype="date"/></td>
										<td><select:approvalState type="${row.approvalState }"></select:approvalState></td>
										<td align="center" nowrap="true" class="right-border-none">
											<a href="javascript:detail('<c:out value="${row.id }"/>')">详情</a>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="twoLedger" action="/twodate.do?method=twoLedger" />
					<table class="bottomTable">
					</table>
					<div id="message" class="message" style="display: none"></div>
				</html:form>
			</div>
		</div>
	</div>

</body>
</html>
