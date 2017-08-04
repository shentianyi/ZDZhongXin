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
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<!-- <link rel="stylesheet" href="/css/combo.select.css" type="text/css" >
<script src="/js/jquery.min.js"></script>
<script src="/js/jquery.combo.select.js"></script> -->
<!-- easyui -->
<link href="/easyui/themes/material/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>

<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	var status=document.getElementsByName("repository.status").value;
	setReason(status);
	var role=<%=request.getAttribute("role")%>;
	if(role!=4){
		document.getElementById("supervisorId").disabled=true;
		document.getElementById("repository.interviewee").disabled=true;
		document.getElementById("repository.interviewScore").disabled=true;
		document.getElementById("repository.interviewComment").disabled=true;
		var arr=document.getElementsByName("repository.status");
		for(var i=0;i<arr.length;i++){
			arr[i].disabled=true;
		}
		var array=document.getElementsByName("repository.reason");
		for(var i=0;i<array.lengh;i++){
			array[i].disabled=true;
		}
		document.getElementById("repository.attribute").disabled=true;
	}
	var supervisorId=document.getElementById("supervisorId").value;
	changeSupervisor(supervisorId);
	
}

//执行保存操作
function doSave(){
		if (!$("#supervisorId").combobox("getValue")) {
			alert("请选择监管员");
			return false;
		}
		var value = document.getElementById("repository.interviewee").value;
		if(value == ""){
			alert("请填写面试人");
			return false;
		}
		var value = document.getElementById("repository.interviewScore").value;
		if(value == "0.0"){
			alert("请填写面试评分");
			return false;
		}
		var value=""; 
		var arr = document.getElementsByName("repository.status");
		for(var i=0;i<arr.length;i++){
		        if(arr[i].checked){    
		          value=arr[i].value;
		        }
		    }
		if(value == ""){
			alert("请选择状态");
			return false;
		}else if(value==3){
			setReason(value);
			var value=""; 
			var arr = document.getElementsByName("repository.reason");
			for(var i=0;i<arr.length;i++){
			        if(arr[i].checked){    
			          value=arr[i].value;
			        }
			    }
			if(value == ""){
				alert("请选择失效原因");
				return false;
			}
		}
		var value = document.getElementById("repository.attribute").value;
		if(value == ""){
			alert("请填写属性");
			return false;
		}
	
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("repositoryForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/repository.do?method=repositoryPageList";
}

function setReason(status) {
	var value=""; 
	var arr = document.getElementsByName("repository.reason");
	if(status == 3){
		for(var i=0;i<arr.length;i++){
		       arr[i].removeAttribute("disabled");
		}
	}else{
		for(var i=0;i<arr.length;i++){
			arr[i].checked=false;
			arr[i].disabled=true;
		}
	}
}
//执行表单重置操作
function doReset(){
	document.forms[0].reset();
	var supervisorId=document.getElementById("supervisorId").value;
	changeSupervisor(supervisorId);
}
function changeDealer(id) {
	if(id==-1){
		document.forms[0].reset();
		return;
	}
	
	var url = "../json/getDealerByIdJsonAction.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setDealer(data[0]);
	});
}

function setDealer(dealer){
	$("#brand").val(dealer.brand);
	$("#address").val(dealer.address);
	var brand=dealer.bankName;
	var brands=brand.split("/");
	$("#headBank").val(brands[0]);
	$("#branch").val(brands[1]);
	$("#subbranch").val(brands[2]);
}
function changeSupervisor(id) {
	if(id==-1){
		document.forms[0].reset();
		return;
	}
	var url = "../json/getSupervisorById.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setSupervisor(data[0]);
	});
}
function setSupervisor(supervisor){
	$("#idNumber").val(supervisor.idNumber);
	$("#gender").val(supervisor.gender);
	$("#birthday").val(supervisor.birthdayStr);
	$("#fertilityState").val(supervisor.fertilityState);
	$("#nation").val(supervisor.nation);
	$("#educationBackground").val(supervisor.educationBackground);
	$("#nativePlace").val(supervisor.registeredAddress);
	$("#liveAddress").val(supervisor.liveAddress);
}
function init() {
	$("#supervisorId").combobox({
		onHidePanel : function() {// 验证输入的值，如果不存在下拉列表 则置空
			var newValue = $(this).combobox('getValue');
			var data = $(this).combobox('getData');
			var flag = false;
			if (data != null && data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					if (newValue == data[i].value) {
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				$(this).combobox('clear');
			} else {
				changeSupervisor(newValue);
			}

		}
	});
	var draftValue = $("#supervisorId").combobox('getValue');
	if (draftValue) {
		changeSupervisor(draftValue);
	}
}

$(function(){
	init();
});
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
	
<html:form action="/repository" styleId="repositoryForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="updRepository"/>
<html:hidden property="repository.id" styleId="repository.id" />
<html:hidden property="repository.createUser" styleId="repository.createUser" />
<html:hidden property="repository.createTime" styleId="repository.createTime" />
<html:hidden property="repository.lastModifyUser" styleId="repository.lastModifyUser" />
<html:hidden property="repository.lastModifyTime" styleId="repository.lastModifyTime" />
<table class="formTable">
<div class="dowebok">
	<tr class="formTitle">
		<td colspan="4">修改储备库信息</td>
	</tr>
	<tr>
		<td colspan="4"> 
			<table>
				<tr>
					<td class="nameCol" colspan="6" style="text-align: left;">一、招聘信息(监管员管理部招聘专员填写)</td>
				</tr>
				<tr>
					<td class="nameCol"><font color="#FF0000">*</font>监管员：</td>
					<td colspan="5">
						<select style="width:20%" id="supervisorId" name="repository.supervisorId" onchange="changeSupervisor(this.value)" >
							<c:forEach items="${supervisors }" var="row">
								<option <c:if test="${repositoryForm.repository.supervisorId==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
							</c:forEach>
						</select>
					</td> 
					</td>
				</tr>
				 <tr>
					<td class="nameCol">身份证号：</td>
					<td class="codeCol"><input type="text"  id="idNumber" disabled="disabled"/></td>
					<td class="nameCol">性别：</td>
					<td class="codeCol"><input type="text"  id="gender" disabled="disabled"/></td>
					<td class="nameCol">出生日期：</td>
					<td class="codeCol">
						<input type="text"  id="birthday" disabled="disabled"/>
					</td> 
				</tr>
				<tr>
					<td class="nameCol">婚姻状况：</td>
					<td class="codeCol"><input type="text"  id="fertilityState" disabled="disabled"/></td>
					<td class="nameCol">民族：</td>
					<td class="codeCol"><input type="text"  id="nation" disabled="disabled"/></td>
					<td class="nameCol">学历：</td>
					<td class="codeCol"><input type="text"  id="educationBackground" disabled="disabled"/></td>
				</tr>
				<!-- <tr>
					<td class="nameCol">毕业院校：</td>
					<td class="codeCol"><input type="text"  id="schoolName" disabled="disabled"/></td>
					<td class="nameCol">专业：</td>
					<td class="codeCol"><input type="text"  id="major" disabled="disabled"/></td>
				</tr>  -->
				<tr>
					<td class="nameCol" style="width: 250px">户口所在地：</td>
					<td class="codeCol" colspan="5"  ><input type="text" style="width: 700px"  id="nativePlace" disabled="disabled"/></td>
				</tr>
				<tr>
					<td class="nameCol">现住址：</td>
					<td class="codeCol" colspan="5" ><input type="text" style="width: 700px" id="liveAddress" disabled="disabled"/></td>
				</tr>
				<tr>
					<td class="nameCol"><font color="#FF0000">*</font>面试人：</td>
					<td class="codeCol"><html:text property="repository.interviewee" styleId="repository.interviewee"/></td>
					<td class="nameCol"><font color="#FF0000">*</font>面试评分：</td>
					<td class="codeCol"><html:text property="repository.interviewScore" styleId="repository.interviewScore"/></td>
					<td class="nameCol"><font color="#FF0000">*</font>面试点评：</td>
					<td class="codeCol"><html:text property="repository.interviewComment" styleId="repository.interviewComment"/></td>
				</tr>
				<tr>
					<td class="nameCol"><font color="#FF0000">*</font>状态：</td>
					<td class="nameCol"style="text-align: left;" >
						<form:radios property="repository.status" collection="statusOptions" styleId="repository.status" onchange="setReason(this.value)"/>
					</td>
					<td class="nameCol"><font color="#FF0000">*</font>无效原因：</td>
					<td class="nameCol"style="text-align: left;" colspan="3" >
						<form:radios property="repository.reason" collection="reasonOptions" styleId="repository.reason" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol"><font color="#FF0000">*</font>属性：</td>
					<td class="codeCol" colspan="5"><html:text style="width:700px;" property="repository.attribute" styleId="repository.attribute"/></td>
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
</div>
</table>
<br/>
<div class="message" id="message" style="display:none"></div>
</html:form>
	
	</div>
</div>
</body>
</html>
