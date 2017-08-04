﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>

<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script src="/ue/ueditor.parse.js"></script>
<script type="text/javascript">
//
$(function(){
	uParse("#ueContent",{
	    rootPath: '/ue/'
	});
});


//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}

function doReturn(){
	//location.href="<url:context/>/message.do?method=noticeList&fromMenuTree=true&resourceId=145";
	location="<url:context/>/notice/noticeContent.do?method=noticeContentList&query.pageType=1&query.contentType=1";
}

</script>
</head>
  
<body>
<div class="title">
	<c:if test="${noticeContentForm.noticeContent.contentType==1}">公告</c:if>
	<c:if test="${noticeContentForm.noticeContent.contentType==2}">制度</c:if>
</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
<table class="formTable">
	<tr>
		<h1><c:out value="${noticeContentForm.noticeContent.title }"/></h1>
	</tr>
	<tr>
		<td class="codeCol" colspan="4"style="line-height:25px;">
		<div style="width:100%;height:auto;">
			<div id="ueContent" style="margin:auto;width:70%;">
				<%=request.getAttribute("content")%>
			</div>
		</div>
			
		</td>
	</tr>
	<tr class="formTableFoot">
		<td colspan="4" align="center">
			<button class="formButton" onclick="doReturn()">返&nbsp;回</button>
		</td>
	</tr>
</table>
<br/>
<div id="message" class="message" style="display:none"></div>

</div>
</div>
</body>
</html>
