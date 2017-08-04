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
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	document.getElementById("repositoryEntity.repository.supervisorId").disabled=true;
	document.getElementById("repositoryEntity.repository.interviewee").disabled=true;
	document.getElementById("repositoryEntity.repository.interviewScore").disabled=true;
	document.getElementById("repositoryEntity.repository.interviewComment").disabled=true;
	document.getElementById("repositoryEntity.repository.attribute").disabled=true;
	document.getElementById("repositoryEntity.repositoryTrain.startTime").disabled=true;
	document.getElementById("repositoryEntity.repositoryTrain.endTime").disabled=true;
	document.getElementById("repositoryEntity.repositoryTrain.trainSpecialist").disabled=true;
	document.getElementById("repositoryEntity.repositoryTrain.dealer").disabled=true;
	document.getElementById("repositoryEntity.repositoryTrain.trainer").disabled=true;
	document.getElementById("repositoryEntity.repositoryTrain.contactNumber").disabled=true;
	document.getElementById("repositoryEntity.repositoryTrain.staffNo").disabled=true;
	document.getElementById("repositoryEntity.repositoryTrain.trainingContent").disabled=true;
	document.getElementById("repositoryEntity.repositoryTrain.trainingContentBasic").disabled=true;
	document.getElementById("repositoryEntity.repositoryTrain.assessmentComputerScore").disabled=true;
	document.getElementById("repositoryEntity.repositoryTrain.assessmentTheoryScore").disabled=true;
	document.getElementById("repositoryEntity.repositoryTrain.assessmentCommunicateScore").disabled=true;
	document.getElementById("repositoryEntity.repositoryTrain.assessmentScore").disabled=true;
	document.getElementById("repositoryEntity.repositoryTrain.remark").disabled=true;
	var supervisorId=document.getElementById("repositoryEntity.repository.supervisorId").value;
	changeSupervisor(supervisorId);
	var dealerId=document.getElementById("repositoryEntity.repositoryTrain.dealer").value;
	changeDealer(dealerId);
}

//执行保存操作
function doSave(){
	document.forms[0].submit();
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/ledger/repository.do?method=repositoryLedger";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
	var supervisorId=document.getElementById("repositoryEntity.repository.supervisorId").value;
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
function setReason(status) {
	var value=""; 
	var arr = document.getElementsByName("repositoryEntity.repository.reason");
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
	
<html:form action="/ledger/repository" styleId="repositoryForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="repositoryDetail"/>

<table class="formTable">
	<tr class="formTitle">
		<td colspan="4">储备库信息详情</td>
	</tr>
	<tr>
		<td colspan="4"> 
			<table >
				<tr>
					<td class="nameCol" colspan="6" style="text-align: left;">一、招聘信息(监管员管理部招聘专员填写)</td>
				</tr>
				<tr>
					<td class="nameCol">监管员：</td>
					<td colspan="5">
						 <form:select property="repositoryEntity.repository.supervisorId"
								styleId="repositoryEntity.repository.supervisorId" onchange="changeSupervisor(this.value)">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="supervisors" label="label"
									value="value" />
							</form:select></td> 
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
					<td class="nameCol">户口所在地：</td>
					<td class="codeCol" colspan="5"><input style="width: 700px" type="text" id="nativePlace" disabled="disabled" /></td>
				</tr>
				<tr>
					<td class="nameCol">现住址：</td>
					<td class="codeCol" colspan="5"><input style="width: 700px" type="text" id="liveAddress"disabled="disabled" /></td>
				</tr>
				<tr>
					<td class="nameCol">面试人：</td>
					<td class="codeCol"><html:text property="repositoryEntity.repository.interviewee" styleId="repositoryEntity.repository.interviewee"/></td>
					<td class="nameCol">面试评分：</td>
					<td class="codeCol"><html:text property="repositoryEntity.repository.interviewScore" styleId="repositoryEntity.repository.interviewScore"/></td>
					<td class="nameCol">面试点评：</td>
					<td class="codeCol"><html:text property="repositoryEntity.repository.interviewComment" styleId="repositoryEntity.repository.interviewComment"/></td>
				</tr>
				<tr>
					<td class="nameCol">状态：</td>
					<td class="nameCol"style="text-align: left;" >
						<form:radios  property="repositoryEntity.repository.status" collection="statusOptions" styleId="repositoryEntity.repository.status" disabled="disabled"/>
					</td>
					<td class="nameCol">无效原因：</td>
					<td class="nameCol"style="text-align: left;" colspan="3" >
						<form:radios property="repositoryEntity.repository.reason" collection="reasonOptions" styleId="repositoryEntity.repository.reason" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol">属性：</td>
					<td class="codeCol" colspan="5" ><html:text style="width:700px;" property="repositoryEntity.repository.attribute" styleId="repositoryEntity.repository.attribute"/></td>
				</tr>
				<tr>
					<td class="nameCol" colspan="6" style="text-align: left;">二、培训信息(监管员管理部培训专员填写)</td>
				</tr>
				<tr>
					<td class="nameCol">培训开始时间：</td>
					<td class="codeCol">
						<form:calendar property="repositoryEntity.repositoryTrain.startTime" styleId="repositoryEntity.repositoryTrain.startTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
					</td>
					<td class="nameCol">培训结束时间：</td>
					<td class="codeCol">
						<form:calendar property="repositoryEntity.repositoryTrain.endTime" styleId="repositoryEntity.repositoryTrain.endTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
					</td>
					<td class="nameCol">培训专员：</td>
					<td class="codeCol"><html:text property="repositoryEntity.repositoryTrain.trainSpecialist" styleId="repositoryEntity.repositoryTrain.trainSpecialist"/></td>
				</tr>
				<tr>
					<td class="nameCol">经销商：</td>
					<td class="codeCol">
						<form:select property="repositoryEntity.repositoryTrain.dealer"
							styleId="repositoryEntity.repositoryTrain.dealer" onchange="changeDealer(this.value)">
							<html:option value="-1">请选择</html:option>
							<html:optionsCollection name="dealers" label="label" value="value" />
						</form:select></td>
					</td>
					<td class="nameCol">经销商品牌：</td>
					<td class="codeCol"><input type="text"  id="brand" disabled="true" /></td>
					<td class="nameCol">经销商地址：</td>
					<td class="codeCol"><input type="text"  id="address" disabled="true" /></td>
				</tr>
				<tr>
					<td class="nameCol">合作银行（总）：</td>
					<td class="codeCol"><input type="text"  id="headBank"disabled="true" /></td>
					<td class="nameCol">合作银行（分）：</td>
					<td class="codeCol"><input type="text" id="branch"disabled="true" /></td>
					<td class="nameCol">合作银行（支）：</td>
					<td class="codeCol"><input type="text" id="subbranch"disabled="true" /></td>
				</tr>
				<tr>
					<td class="nameCol">培训人：</td>
					<td class="codeCol"><html:text property="repositoryEntity.repositoryTrain.trainer" styleId="repositoryEntity.repositoryTrain.trainer"/></td>
					<td class="nameCol">联系方式：</td>
					<td class="codeCol"><html:text property="repositoryEntity.repositoryTrain.contactNumber" styleId="repositoryEntity.repositoryTrain.contactNumber"/></td>
					<td class="nameCol">员工工号：</td>
					<td class="codeCol"><html:text property="repositoryEntity.repositoryTrain.staffNo" styleId="repositoryEntity.repositoryTrain.staffNo"/></td>
				</tr>
				<tr>
					<td class="nameCol">培训内容（业务）：</td>
					<td class="codeCol" colspan="6" style="text-align: left;">
						<html:text style="width:700px" property="repositoryEntity.repositoryTrain.trainingContent" styleId="repositoryEntity.repositoryTrain.trainingContent"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol">培训内容（入职基础知识）：</td>
					<td class="codeCol" colspan="6" style="text-align: left;">
						<html:text style="width:700px" property="repositoryEntity.repositoryTrain.trainingContentBasic" styleId="repositoryEntity.repositoryTrain.trainingContentBasic"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol">考核总评（电脑水平）：</td>
					<td class="codeCol"><html:text property="repositoryEntity.repositoryTrain.assessmentComputerScore" styleId="repositoryEntity.repositoryTrain.assessmentComputerScore"/></td>
					<td class="nameCol">考核总评（理论知识）：</td>
					<td class="codeCol"><html:text property="repositoryEntity.repositoryTrain.assessmentTheoryScore" styleId="repositoryEntity.repositoryTrain.assessmentTheoryScore"/></td>
					<td class="nameCol">考核总评（沟通及其它）：</td>
					<td class="codeCol"><html:text property="repositoryEntity.repositoryTrain.assessmentCommunicateScore" styleId="repositoryEntity.repositoryTrain.assessmentCommunicateScore"/></td>
				</tr>
				<tr>
					<td class="nameCol">考核得分：</td>
					<td class="codeCol"><html:text property="repositoryEntity.repositoryTrain.assessmentScore" styleId="repositoryEntity.repositoryTrain.assessmentScore"/></td>
					<td class="nameCol">备注：</td>
					<td class="codeCol"colspan="3" style="width:400px"><html:text property="repositoryEntity.repositoryTrain.remark" styleId="repositoryEntity.repositoryTrain.remark"/></td>
				</tr>
				
				<tr>
					<td class="nameCol" colspan="6" style="text-align: left;">三、可驻派城市</td>
				</tr>
				<tr>
					<td colspan="6">
						<div class="dvScroll">
						<table  class="listTalbe" cellpadding="0" cellspacing="0">
							<thead>
								<tr class="title">
							      <td>序号</td>
							      <td><thumbpage:order cname="可驻派城市（省）" filedName="province"/></td>
							      <td><thumbpage:order cname="可驻派城市（市）" filedName="city"/></td>
							      <td><thumbpage:order cname="可驻派城市（县/区）" filedName="county"/></td>
							    </tr>
							</thead>
							<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
								<logic:iterate name="list" id="row" indexId="index">
									<tr class="listTr_a">
										<td align="center">&nbsp;<c:out value="${index+1}"/></td>
										<td>&nbsp;<c:out value="${row.province}" /></td>
										<td>&nbsp;<c:out value="${row.city}" /></td>
										<td>&nbsp;<c:out value="${row.county}" /></td>
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
