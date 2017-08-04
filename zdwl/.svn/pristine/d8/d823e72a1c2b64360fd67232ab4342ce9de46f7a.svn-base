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
	var value = document.getElementById("education.educationStartTime").value;
	if(value == ""){
		alert("请填写教育开始时间");
		return false;
	}
	var value = document.getElementById("education.educationEndTime").value;
	if(value == ""){
		alert("请填写教育结束时间");
		return false;
	}
	var value = document.getElementById("education.schoolName").value;
	if(value == ""){
		alert("请填写学校名称");
		return false;
	}
	var value = document.getElementById("education.major").value;
	if(value == ""){
		alert("请填写主修专业");
		return false;
	}
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("supervisoryForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	var id=document.getElementsByName("baseInfo.id")[0].value;
	location = "<url:context/>/supervisory.do?method=getEducationListBySupervisoryId&baseInfo.id="+id;
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
	
<html:form action="/supervisory" styleId="supervisoryForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addSupervisoryEducation"/>
<html:hidden property="baseInfo.id" styleId="baseInfo.id" />
<table class="formTable">
	<tr class="formTitle">
		<td >新增监管员教育状况信息</td>
	</tr>
	<tr>
		<td >
			<table >
				<tr>
					<td class="nameCol" colspan="6" style="text-align: left;">二、教育状况</td>
				</tr>
				<tr>
					<td class="nameCol" style="text-align: center;"><font color="#FF0000">*</font>起</td>
					<td class="nameCol"  style="text-align: center;"><font color="#FF0000">*</font>止</td>
					<td class="nameCol"  style="text-align: center;"><font color="#FF0000">*</font>学校名称</td>
					<td class="nameCol"  style="text-align: center;"><font color="#FF0000">*</font>主修专业</td>
					<td class="nameCol"  style="text-align: center;">获得证书</td>
					<td class="nameCol"  style="text-align: center;">获得学位</td>
				</tr>
				 <tr>
					<td class="codeCol"style="text-align: center;">
						<form:calendar property="education.educationStartTime" styleId="education.educationStartTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
					</td>
					<td class="codeCol"style="text-align: center;">
						<form:calendar property="education.educationEndTime" styleId="education.educationEndTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
					</td>
					<td class="codeCol"style="text-align: center;"><html:text property="education.schoolName" styleId="education.schoolName"/></td>
					<td class="codeCol"style="text-align: center;"><html:text property="education.major" styleId="education.major"/></td>
					<td class="codeCol"style="text-align: center;"><html:text property="education.certificate" styleId="education.certificate"/></td>
					<td class="codeCol"style="text-align: center;"><html:text property="education.degree" styleId="education.degree"/></td>
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
