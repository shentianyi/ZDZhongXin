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
	if(!$("#title").val()){
		alert("标题不能为空");
		return false;
	}
	var ueContent = ue.getContent();
	$("#noteContent").val(ueContent);
	document.forms[0].submit();

}
$(function(){
	uParse("#ueContent",{
	    rootPath: '/ue/'
	});
});
//执行返回列表操作
function doReturn(){
	location = "<url:context/>/note.do?method=noteList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
</script>
</head>
  
<body>
<div class="title">修改便签</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/note" styleId="noteForm" method="post" onsubmit="return false">
<html:hidden property="note.id" styleId="note.id"/>
<input type="hidden" name="method" id="method" value="updNote">

<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>标题：</td>
		<td class="codeCol" colspan="3">
			<html:text property="note.title" styleId="note.title"></html:text>
		</td>
	</tr>
	<tr>
		<td class="codeCol" colspan="4">
			<div style="padding-left:20%;">
				<!-- 加载编辑器的容器 -->
			    <script id="container" name="note.content" type="text/plain" style="width:90%;height: auto;"><%=request.getAttribute("content")%></script>
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
<br/>
<div id="message" class="message" style="display:none"></div>
</html:form>

	</div>
</div>
</body>
</html>
