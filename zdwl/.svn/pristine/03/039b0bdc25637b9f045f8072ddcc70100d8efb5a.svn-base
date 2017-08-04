<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/common.js"></script>
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
	var value = document.getElementById("family.relationship").value;
	if(value == ""){
		alert("请填写与该家庭成员关系");
		return false;
	}
	var value = document.getElementById("family.name").value;
	if(value == ""){
		alert("请填写姓名");
		return false;
	}
	var value = document.getElementById("family.profession").value;
	if(value == ""){
		alert("请填写职业");
		return false;
	}
	var value = document.getElementById("family.organization").value;
	if(value == ""){
		alert("请填写单位");
		return false;
	}
	var value = document.getElementById("family.telephone").value;
	if(value == ""){
		alert("请填写联系电话");
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
	var id=document.getElementsByName("family.supervisorId")[0].value;
	location = "<url:context/>/supervisory.do?method=getFamilyListBySupervisoryId&baseInfo.id="+id;
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}

</script>
</head>
<body onLoad="doLoad()">
<div class="title">修改地区</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/supervisory" styleId="supervisoryForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="updSupervisoryFamily"/>
<html:hidden property="family.id" styleId="family.id" />
<html:hidden property="family.supervisorId" styleId="family.supervisorId" />
<table class="formTable">
	<tr class="formTitle">
		<td colspan="4">修改监管员家庭状况</td>
	</tr>
	<tr>
		<td colspan="4">
			<table >
				<tr>
					<td class="nameCol" colspan="5" style="text-align: left;">四、家庭状况</td>
				</tr>
				<tr>
					<td class="nameCol"  style="text-align: center;"><font color="#FF0000">*</font>与该家庭成员关系</td>
					<td class="nameCol" style="text-align: center;"><font color="#FF0000">*</font>姓名</td>
					<td class="nameCol"  style="text-align: center;"><font color="#FF0000">*</font>职业</td>
					<td class="nameCol"  style="text-align: center;"><font color="#FF0000">*</font>单位</td>
					<td class="nameCol"  style="text-align: center;"><font color="#FF0000">*</font>联系电话</td>
				</tr>
				 <tr>
					<td class="codeCol"style="text-align: center;"><html:text property="family.relationship" styleId="family.relationship"/></td>
					<td class="codeCol"style="text-align: center;"><html:text property="family.name" styleId="family.name"/></td>
					<td class="codeCol"style="text-align: center;"><html:text property="family.profession" styleId="family.profession"/></td>
					<td class="codeCol"style="text-align: center;"><html:text property="family.organization" styleId="family.organization"/></td>
					<td class="codeCol"style="text-align: center;"><html:text property="family.telephone" styleId="family.telephone"/></td>
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