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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${not empty approvals}">
	<div class="bm">审批意见</div>	
	<div style="text-align:center">
		<logic:iterate name="approvals" id="row" indexId="index">
			<ul style="margin: 12px 0 14px 0;"><%
				%><li class="uli" style="width: 6%;">第<c:out value='${index+1 }'/>步</li><%
				%><li class="uli" style="width: 30%;"><%
						%><c:out value="${row.roleName }"/>：&nbsp; <%
						%><c:out value="${row.userName }"/>&nbsp;&nbsp;于&nbsp;&nbsp;<fmt:formatDate value="${row.remarkDate }" pattern="yyyy-MM-dd"/><%
				%></li><%
				%><li class="uli" style="width: 12%;"><%
					%><c:if test="${row.approvalResult==1}">同意&nbsp;</c:if><%
					%><c:if test="${row.approvalResult==2}">不同意&nbsp;</c:if><%
				%></li><%
				%><li class="uli" style="width: 30%;"><%
					%><c:out value="${row.remark }"/><%
				%></li><%
			%></ul>
		</logic:iterate>
	</div>
</c:if>