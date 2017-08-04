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
	var value = document.getElementById("workExperience.workStartTime").value;
	if(value == ""){
		alert("请填写工作经历开始时间");
		return false;
	}
	var value = document.getElementById("workExperience.workEndTime").value;
	if(value == ""){
		alert("请填写工作经历结束时间");
		return false;
	}
	var value = document.getElementById("workExperience.serviceOrganization").value;
	if(value == ""){
		alert("请填写服务机构");
		return false;
	}
	var value = document.getElementById("workExperience.position").value;
	if(value == ""){
		alert("请填写职位");
		return false;
	}
	var value = document.getElementById("workExperience.compensation").value;
	var reg = new RegExp("^([1-9][0-9]*)+(.[0-9]{1,2})?$");  
	if(value == ""){
		alert("请填写薪资");
		return false;
	}else if(!reg.test(value)){
	    alert("请检查薪资格式");
	    return false;
	}
	var value = document.getElementById("workExperience.leaveReason").value;
	if(value == ""){
		alert("请填写离职原因");
		return false;
	}
	var value = document.getElementById("workExperience.certifier").value;
	if(value == ""){
		alert("请填写证明人");
		return false;
	}
	var value = document.getElementById("workExperience.contactNumber").value;
	if(value == ""){
		alert("请填写证明人联系方式");
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
	location = "<url:context/>/supervisory.do?method=getWorkExperienceListBySupervisoryId&baseInfo.id="+id;
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
<input type="hidden" name="method" id="method" value="addSupervisoryWorkExperience"/>
<html:hidden property="baseInfo.id" styleId="baseInfo.id" />
<table class="formTable">
	<tr class="formTitle">
		<td colspan="4">新增监管员工作经历</td>
	</tr>
	<tr>
		<td colspan="4">
			<table >
				<tr>
					<td class="nameCol" colspan="8" style="text-align: left;">三、工作经历</td>
				</tr>
				<tr>
					<td class="nameCol" style="text-align: center;"><font color="#FF0000">*</font>起</td>
					<td class="codeCol" >
						<form:calendar property="workExperience.workStartTime" styleId="workExperience.workStartTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
					</td>
					<td class="nameCol"  style="text-align: center;"><font color="#FF0000">*</font>止</td>
					<td class="codeCol" >
						<form:calendar property="workExperience.workEndTime" styleId="workExperience.workEndTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
					</td>
					<td class="nameCol"  style="text-align: center;"><font color="#FF0000">*</font>服务机构</td>
					<td class="codeCol" ><html:text style="width: 150px;" property="workExperience.serviceOrganization" styleId="workExperience.serviceOrganization"/></td>
					<td class="nameCol"  style="text-align: center;"><font color="#FF0000">*</font>职位</td>
					<td class="codeCol"><html:text style="width: 150px;" property="workExperience.position" styleId="workExperience.position"/></td>
				</tr>
				<tr>
					<td class="nameCol"  style="text-align: center;"><font color="#FF0000">*</font>薪资</td>
					<td class="codeCol"><html:text style="width: 150px;"  property="workExperience.compensation" styleId="workExperience.compensation"/></td>
					<td class="nameCol"  style="text-align: center;"><font color="#FF0000">*</font>离职原因</td>
					<td class="codeCol"><html:text style="width: 150px;"  property="workExperience.leaveReason" styleId="workExperience.leaveReason"/></td>
					<td class="nameCol"  style="text-align: center;"><font color="#FF0000">*</font>证明人</td>
					<td class="codeCol"><html:text style="width: 150px;"  property="workExperience.certifier" styleId="workExperience.certifier"/></td>
					<td class="nameCol"  style="text-align: center;width: 300px;"><font color="#FF0000">*</font>证明人联系方式</td>
					<td class="codeCol"><html:text style="width: 150px;"  property="workExperience.contactNumber" styleId="workExperience.contactNumber"/></td>
				</tr>
			</table>
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
<div class="message" id="message" style="display:none"></div>
</html:form>
	
	</div>
</div>
</body>
</html>
