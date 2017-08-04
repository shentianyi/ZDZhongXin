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
<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	var repositoryId=$("#repositoryId").val();
	var supervisorId=document.getElementById("roster.supervisorId").value;
	changeRepository(repositoryId);
	changeSupervisor(supervisorId);
	var dispatchCity=document.getElementsByName("roster.dispatchCity")[0].value;
	initDispatchCity(dispatchCity,$("#dispatchCity1"))
}

//执行保存操作
function doSave(){
	var value = document.getElementById("roster.paycardNo").value;
	if(value == ""){
		alert("请填写工资卡号");
		return false;
	}
	var value = document.getElementById("roster.openBank").value;
	if(value == ""){
		alert("请填写开户行");
		return false;
	}
	var value = document.getElementById("roster.organizeType").value;
	if(value == ""){
		alert("请填写编制类型");
		return false;
	}
	var value = document.getElementById("roster.driveYear").value;
	if(value == ""){
		alert("请填写司龄（年）");
		return false;
	}
	var value = document.getElementById("roster.driveMonth").value;
	if(value == ""){
		alert("请填写司龄（月）");
		return false;
	}
	var value = document.getElementById("roster.dispatchAttribute").value;
	if(value == ""){
		alert("请填写驻派属性");
		return false;
	}
	var value = document.getElementById("dispatchCity1").value;
	if(value == "-1"){
		alert("请选择驻派地区");
		return false;
	}
	var value = document.getElementById("roster.enlistStatus").value;
	if(value == ""){
		alert("请填写服役状态");
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
	location = "<url:context/>/roster.do?method=rosterPageList";
}

//执行表单重置操作
function doReset(){
	var repositoryId=$("#repositoryId").val();
	var supervisorId=document.getElementById("roster.supervisorId").value;
	changeRepository(repositoryId);
	changeSupervisor(supervisorId);
	document.forms[0].reset();
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
	$("#supervisorName").val(supervisor.name);
	$("#gender").val(supervisor.gender);
	$("#birthday").val(supervisor.birthdayStr);
	$("#fertilityState").val(supervisor.fertilityState);
	$("#nation").val(supervisor.nation);
	$("#educationBackground").val(supervisor.educationBackground);
	$("#entryDate").val(supervisor.entryTimeStr);
	$("#contactNumber").val(supervisor.selfTelephone);
	$("#schoolName").val(supervisor.graduateSchool);
	$("#major").val(supervisor.major);
	$("#recommendChannel").val(supervisor.recommendChannel);
	$("#recommenderName").val(supervisor.recommenderName);
	$("#recommenderPosition").val(supervisor.recommenderPosition);
	$("#nativePlace").val(supervisor.registeredAddress);
	$("#liveAddress").val(supervisor.liveAddress);
}
function changeRepository(id) {
	if(id==-1){
		document.forms[0].reset();
		return;
	}
	var url = "../json/getRepositoryById.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setRepository(data[0]);
	});
}
function setRepository(repository){
	$("#interviewee").val(repository.interviewee);
}
function initDispatchCity(currentId,nextSelect){
	var repositoryId=$("#repositoryId").val();
	var url = "../json/getRepositoryDispatchCityByRepositoryId.do?callback=?&repositoryId="+repositoryId;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setDispatchCity(data,currentId,nextSelect);
	});
}
function setDispatchCity(DispatchCity,currentId,nextSelect){
	for(var i=0;i<DispatchCity.length;i++){
		var option ;
		if(DispatchCity[i].id == currentId){
			option = "<option selected='selected' value="+DispatchCity[i].id+">" + DispatchCity[i].province+"  "
			+ DispatchCity[i].city+"  "+ DispatchCity[i].county+ "</option>";
		}else{ 
			option = "<option value="+DispatchCity[i].id+">" + DispatchCity[i].province+"  "
			+ DispatchCity[i].city+"  "+ DispatchCity[i].county+ "</option>";
		 } 
		nextSelect.append(option);
	}
}
function changeCity(id,nextSelect){
	$(nextSelect).val(id);
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
	
<html:form action="/roster" styleId="rosterForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="getRosterDetail"/>
<html:hidden property="roster.id" styleId="roster.id" />
<html:hidden property="roster.repositoryId" styleId="repositoryId" />
<html:hidden property="roster.supervisorId" styleId="roster.supervisorId" />
<html:hidden property="roster.dispatchCity" styleId="dispatchCity" />

<table class="formTable">
	<tr class="formTitle">
		<td colspan="4">花名册详情</td>
	</tr>
	<tr>
		<td colspan="4"> 
			<table style="width:100%">
				<tr>
					<td class="nameCol">监管员姓名：</td>
					<td class="codeCol">
						<input type="text"  id="supervisorName" disabled="disabled"/>
					</td>
					<td class="nameCol">员工工号：</td>
					<td class="codeCol">
						<html:text property="roster.staffNo" styleId="roster.staffNo" disabled="true" />
					</td>
					<td class="nameCol">入职日期：</td>
					<td class="codeCol">
						<input type="text"  id="entryDate" disabled="disabled"/>
					</td> 
				</tr>
				 <tr>
					<td class="nameCol">身份证号：</td>
					<td class="codeCol"><input type="text"  id="idNumber" disabled="disabled"/></td>
					<td class="nameCol">性别：</td>
					<td class="codeCol"><input type="text"  id="gender" disabled="disabled" /></td>
					<td class="nameCol">出生日期：</td>
					<td class="codeCol">
						<input type="text"  id="birthday" disabled="disabled"/>
					</td> 
				</tr>
				<tr>
					<td class="nameCol">婚姻状况：</td>
					<td class="codeCol"><input type="text"  id="fertilityState" disabled="disabled" /></td>
					<td class="nameCol">民族：</td>
					<td class="codeCol"><input type="text"  id="nation" disabled="disabled" /></td>
					<td class="nameCol">联系电话：</td>
					<td class="codeCol"><input type="text"  id="contactNumber" disabled="disabled" /></td>
				</tr>
				<tr>
					<td class="nameCol">学历：</td>
					<td class="codeCol"><input type="text"  id="educationBackground" disabled="disabled"/></td>
					<td class="nameCol">毕业院校：</td>
					<td class="codeCol"><input type="text"  id="schoolName" disabled="disabled"/></td>
					<td class="nameCol">专业：</td>
					<td class="codeCol"><input type="text"  id="major" disabled="disabled" /></td>
				</tr> 
				<tr>
					<td class="nameCol">招聘渠道：</td>
					<td class="codeCol"><input type="text" id="recommendChannel"disabled="disabled" /></td>
					<td class="nameCol">推荐人姓名</td>
					<td class="codeCol"><input type="text" id="recommenderName" disabled="disabled"/></td>
					<td class="nameCol">推荐人背景：</td>
					<td class="codeCol"><input type="text" id="recommenderPosition" disabled="disabled" /></td>
				</tr>
				<tr>
					<td class="nameCol">户口所在地：</td>
					<td class="codeCol" colspan="5"><input style="width: 700px" type="text" id="nativePlace" disabled="disabled" /></td>
				</tr>
				<tr>
					<td class="nameCol">现住址：</td>
					<td class="codeCol" colspan="5"><input style="width: 700px" type="text" id="liveAddress"disabled="disabled" /></td>
				</tr>
				<tr>
					<td class="nameCol">面试人：</td>
					<td class="codeCol"><input type="text" id="interviewee" disabled="disabled" /></td>
					<td class="nameCol">系统账号：</td>
					<td class="codeCol"><html:text property="roster.systemAccount" styleId="roster.systemAccount" disabled="true"/></td>
					<td class="nameCol">系统密码：</td>
					<td class="codeCol"><html:text property="roster.systemPassword" styleId="roster.systemPassword" disabled="true" /></td>
				</tr>
				<tr>
					<td class="nameCol"><font color="#FF0000">*</font>工资卡号：</td>
					<td class="codeCol"><html:text property="roster.paycardNo" styleId="roster.paycardNo" disabled="true"/></td>
					<td class="nameCol"><font color="#FF0000">*</font>开户行：</td>
					<td class="codeCol"><html:text property="roster.openBank" styleId="roster.openBank" disabled="true"/></td>
					<td class="nameCol"><font color="#FF0000">*</font>编制类型：</td>
					<td class="codeCol"><html:text property="roster.organizeType" styleId="roster.organizeType" disabled="true"/></td>
				</tr>
				<tr>
					<td class="nameCol"><font color="#FF0000">*</font>司龄（年）：</td>
					<td class="codeCol"><html:text property="roster.driveYear" styleId="roster.driveYear" disabled="true"/></td>
					<td class="nameCol"><font color="#FF0000">*</font>司龄（月）：</td>
					<td class="codeCol"><html:text property="roster.driveMonth" styleId="roster.driveMonth" disabled="true"/></td>
					<td class="nameCol"><font color="#FF0000">*</font>服役状态：</td>
					<td class="codeCol"><html:text property="roster.enlistStatus" styleId="roster.enlistStatus" disabled="true"/></td>
				</tr>
				<tr>
					<td class="nameCol"><font color="#FF0000">*</font>驻派属性：</td>
					<td class="codeCol"><html:text property="roster.dispatchAttribute" styleId="roster.dispatchAttribute" disabled="true"/></td>
					<td class="nameCol"><font color="#FF0000">*</font>驻派地区：</td>
					<td class="codeCol"><html:text property="roster.dispatchCity" styleId="roster.dispatchCity" disabled="true"/></td>
					<!-- td class="codeCol" colspan="2" >
						<select id="dispatchCity1" onchange="changeCity(this.value,$('#dispatchCity'))" 
								name="dispatchCity1">
							<option value="-1">请选择</option>		
						</select>
					</td -->
				</tr>
				<tr>
					<td colspan="6">
						<div class="dvScroll">
							<table  class="listTalbe" cellpadding="0" cellspacing="0">
								<thead>
									<tr class="title">
								      <td>序号</td>
								      <td><thumbpage:order cname="上岗日期" filedName="entryTime"/></td>
                                      <td><thumbpage:order cname="上岗性质" filedName="entryNature"/></td>
                                      <td><thumbpage:order cname="离岗日期" filedName="leaveTime"/></td>
                                      <td><thumbpage:order cname="离岗性质" filedName="leaveNature"/></td>
								      
								    </tr>
								</thead>
								<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
									<logic:iterate name="list" id="row" indexId="index">
										<tr class="listTr_a">
											<td align="center">&nbsp;<c:out value="${index+1}"/></td>
											<td>&nbsp;<select:timestamp timestamp="${row.entryTime}" idtype="date"/></td>
											<td>&nbsp;<c:out value="${row.entryNature}" /></td>
											<td>&nbsp;<select:timestamp timestamp="${row.leaveTime}" idtype="date"/></td>
											<td>&nbsp;<c:out value="${row.leaveNature}" /></td>
											
										</tr>
									</logic:iterate>
								</tbody>  
							</table>
							</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<table>
				
			</table>
		</td>
	</tr>
	<tr class="formTableFoot">
		<td colspan="4" align="center">
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
