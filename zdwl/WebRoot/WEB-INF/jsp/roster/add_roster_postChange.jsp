<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	var value = document.getElementById("postChange.dimissionDate").value;
	if(value == ""){
		alert("请选择上岗日期");
		return false;
	}
	var value = document.getElementById("postChange.dimissionNature").value;
	if(value == ""){
		alert("请填写上岗性质");
		return false;
	}
	var value = document.getElementById("postChange.missionDate").value;
	if(value == ""){
		alert("请选择离岗日期");
		return false;
	}
	var value = document.getElementById("postChange.missionNature").value;
	if(value == ""){
		alert("请填写离岗性质");
		return false;
	}
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("rosterForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	var id=document.getElementsByName("roster.id")[0].value;
	location = "<url:context/>/roster.do?method=postChangeList&roster.id="+id;
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}

</script>
<style type="text/css">
#f_file{
 	left: 0;
    opacity: 0;
    position: absolute;
    top: -100;
    z-index: 2;
    width: 100%;
    height: 100%;
}
</style>
</head>
<body  onLoad="doLoad()">

<br/>

<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/roster" styleId="rosterForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addPostChange"/>
<html:hidden property="roster.id" styleId="roster.id" />
<table class="formTable">
	<tr class="formTitle">
		<td >新增花名册岗位轮换信息</td>
	</tr>
	<tr>
		<td >
			<table >
				<tr>
					<td class="nameCol" colspan="6" style="text-align: left;">岗位轮换信息</td>
				</tr>
				<tr>
					<td class="nameCol" style="text-align: center;"><font color="#FF0000">*</font>离岗日期</td>
					<td class="nameCol"  style="text-align: center;"><font color="#FF0000">*</font>离岗性质</td>
					<td class="nameCol"  style="text-align: center;"><font color="#FF0000">*</font>上岗日期</td>
					<td class="nameCol"  style="text-align: center;"><font color="#FF0000">*</font>上岗性质</td>
				</tr>
				 <tr>
					<td class="codeCol"style="text-align: center;">
						<form:calendar property="postChange.dimissionDate" styleId="postChange.dimissionDate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"/>
					</td>
					<td class="codeCol"style="text-align: center;"><html:text property="postChange.dimissionNature" styleId="postChange.dimissionNature"/></td>
					<td class="codeCol"style="text-align: center;">
						<form:calendar property="postChange.missionDate" styleId="postChange.missionDate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />
					</td>
					<td class="codeCol"style="text-align: center;"><html:text property="postChange.missionNature" styleId="postChange.missionNature"/></td>
				</tr>
			</table>
		</td>
	</tr> 
	<tr class="formTableFoot">
		<td align="center">
			<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
			<button class="formButton" onClick="doReset()">重&nbsp;置</button>&nbsp;
			<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
		</td>
	</tr>
</table>
<br/>
<div class="message" id="message" style="display:none"></div>
</html:form>
	
	</div>
</div>
</body>
</html>
