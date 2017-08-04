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
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>中都汽车金融监管系统</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//执行保存操作
function doSave(){
	var ben = document.getElementById("two").value;
	if(ben == null || ben == ""){
		alert("请上传二网日查库图片");
		return false;
	}

	document.forms[0].submit();
	
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/twodate.do?method=findList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">新增二网日盘点库</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/twodate" styleId="duedateForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="addTwodate">
<input type="hidden" name="duedate.type" id="duedate.type" value="2">
<table class="formTable">
	<tr>
		<td class="nameCol">二网日查库：</td>
		<td class="codeCol">
			上传：<input type="file" name="two" id="two"/>
		</td>
		<td class="nameCol">上传2</td>
		<td class="codeCol">上传2：<input type="file" name="pic2" id="pic2"/></td>
	</tr>
	<tr>
		<td class="nameCol">上传3</td>
		<td class="codeCol">上传3：<input type="file" name="pic3" id="pic3"/></td>
		<td class="nameCol">上传4</td>
		<td class="codeCol">上传4：<input type="file" name="pic4" id="pic4"/></td>
	</tr>
	<tr>
		<td class="nameCol">上传5</td>
		<td class="codeCol">上传5：<input type="file" name="pic5" id="pic5"/></td>
		<td class="nameCol"></td>
		<td class="codeCol"></td>
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
