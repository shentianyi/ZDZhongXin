<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>中都汽车金融监管系统</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script src="/ue/ueditor.parse.js"></script>
<script type="text/javascript">

$(function(){
	uParse("#ueContent",{
	    rootPath: '/ue/'
	});
});

function agree(){
	$("#approvalState").val(1);
	doSave();
}

function disAgree(){
	$("#approvalState").val(2);
	doSave();
}

//执行返回列表操作
function doReturn(){
	location="<url:context/>/notice/noticeContent.do?method=noticeContentList&query.pageType=1&query.contentType="+$("#contentType").val();
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}

function doSave(){
	document.forms[0].submit();
}
</script>
</head>
  
<body>
<div class="title">
	<c:if test="${noticeContentForm.query.contentType==1}">公告</c:if>
	<c:if test="${noticeContentForm.query.contentType==2}">制度</c:if>
</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/notice/noticeContent" styleId="noticeContentForm" method="post" onsubmit="return false">
<input type="hidden" name="method" value="approval">
<html:hidden property="query.approvalState" styleId="approvalState"/>
<html:hidden property="noticeContent.id"/>
<table class="formTable">
	<tr>
		<h4><c:out value="${noticeContentForm.noticeContent.title }"/></h4>
	</tr>
	<tr>
		<td class="codeCol" colspan="4" style="line-height:25px">
		<div style="width:100%;height:auto;">
			<div id="ueContent" style="margin:auto;width:70%;">
				<%=request.getAttribute("content")%>
			</div>
		</div>
			
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
					<tr>
						<td class="nameCol">审批意见：</td>
						<td class="codeCol" colspan="3"><html:textarea property="query.remark"></html:textarea></td>
					</tr>
	<tr class="formTableFoot">
		<td colspan="4" align="center">
			<button class="formButton" onClick="agree()">同&nbsp;意</button>&nbsp;
			<button class="formButton" onClick="disAgree()">不&nbsp;同&nbsp;意</button>&nbsp;
			<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
		</td>
	</tr>
</table>
</html:form>
<br/>
<div id="message" class="message" style="display:none"></div>

</div>
</div>
</body>
</html>
