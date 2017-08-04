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
//执行保存操作
function doSave(){
	var ueContent = ue.getContent();

	$("#noteContent").val(ueContent);
	if(!$("#title").val()){
		alert("标题不能为空");
		return false;
	}
	if(!$("input[name='noticeContent\\.type']:checked").val()){
		alert("请选择通知类型");
		return false;
	}else{
		if($("input[name='noticeContent\\.type']:checked").val()==2){
			if($("input[name='department']:checked").length<=0){
				alert("请选择要通知的部门");
				return false;
			}
		}
	}
	document.forms[0].submit();

}
$(function(){
	uParse("#ueContent",{
	    rootPath: '/ue/'
	});
	$("input[name='noticeContent\\.type']").click(function(){
		if(this.value==1){
			$("input[name='department']").prop("disabled",true);	
		}else{
			$("input[name='department']").prop("disabled",false);
		}
	});
});

//执行返回列表操作
function doReturn(){
	location="<url:context/>/notice/noticeContent.do?method=noticeContentList&query.pageType=1&query.contentType="+$("#contentType").val();
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
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
<input type="hidden" name="method" id="method" value="updNoticeContent">
<input type="hidden" name="noticeContent.content" id="noteContent"/> 
<html:hidden property="noticeContent.id"/>
<html:hidden property="query.contentType" styleId="contentType"/>
<table class="formTable">
	<tr>
		<td class="nameCol">标题：</td>
		<td class="codeCol" colspan="3">
			<html:text property="noticeContent.title" styleId="title"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">
			通知类型：
			<form:radios collection="types" property="noticeContent.type"></form:radios>
		</td>
		<td class="codeCol" colspan="3">
			<form:checkboxs styleId="department" collection="depts" property="department"></form:checkboxs>
		</td>
	</tr>
	<tr>
		<td class="codeCol" colspan="4">

			<div style="padding-left:20%;">
				<!-- 加载编辑器的容器 -->
			    <script id="container" type="text/plain" style="width:90%;height:auto;"><%=request.getAttribute("content")%></script>
			    <!-- 配置文件 -->
			    <script type="text/javascript" src="/ue/ueditor.config.js"></script>
			    <!-- 编辑器源码文件 -->
			    <script type="text/javascript" src="/ue/ueditor.all.js"></script>
			    <!-- 实例化编辑器 -->
			    <script type="text/javascript">
			        var ue = UE.getEditor('container');
			    </script>
			</div>
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
</html:form>

	</div>
</div>
</body>
</html>
