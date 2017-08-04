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
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>

//执行保存操作
function doSave(){
	
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("supervisorForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>supervisory.do?method=supervisoryPageList";
}

</script>
</head>
  
<body>
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">监管员信息管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">监管员招聘管理</a>
             &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">导入监管员基本信息</a>
         </span>
</div>
<div class="title">导入监管员基本信息</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/supervisory" styleId="supervisorForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="importExcel">
<table class="formTable">
	<tr>
		<td colspan="4" style="text-align: center;">
			导入模板：<a style="color: blue;" href="/system/importTemplate/xinxidaoru.xls">下载</a>
		</td>
	</tr>
	<tr>
		<td class="nameCol">上传：</td>
		<td class="codeCol" colspan="3"><html:file property="excelfile"></html:file></td>
	</tr>
	<tr class="formTableFoot">
		<td colspan="4" align="center">
			<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
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
