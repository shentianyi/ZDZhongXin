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
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<link href="/css/public.css" rel="stylesheet" type="text/css" />
<script src="/js/common.js"></script>
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	initProvince();
}

//执行保存操作
function doSave(){
	var value = document.getElementById("baseInfo.job").value;
	if(value == ""){
		alert("请填写应聘岗位");
		return false;
	}
	var value = document.getElementById("baseInfo.name").value;
	if(value == ""){
		alert("请填写姓名");
		return false;
	}
	var value = document.getElementById("baseInfo.birthday").value;
	if(value == ""){
		alert("请填写出生日期");
		return false;
	}
	var value = document.getElementById("baseInfo.gender").value;
	if(value == ""){
		alert("请填写性别");
		return false;
	}
	var value = document.getElementById("baseInfo.idNumber").value;
	if(value == ""){
		alert("请填写身份证号");
		return false;
	}
	var value = document.getElementById("baseInfo.nation").value;
	if(value == ""){
		alert("请填写民族");
		return false;
	}
	var value = document.getElementById("baseInfo.educationBackground").value;
	if(value == ""){
		alert("请填写学历");
		return false;
	}
	/* var value = document.getElementById("baseInfo.nativePlace").value;
	if(value == ""){
		alert("请填写籍贯");
		return false;
	} */
	var value = document.getElementById("baseInfo.politicsStatus").value;
	if(value == ""){
		alert("请填写政治面貌");
		return false;
	}
	var value=""; 
	var arr = document.getElementsByName("baseInfo.registeredStatus");
	for(var i=0;i<arr.length;i++){
	        if(arr[i].checked){    
	          value=arr[i].value;
	        }
	    }
	if(value == ""){
		alert("请选择户口性质");
		return false;
	}
	var value = document.getElementById("baseInfo.selfTelephone").value;
	if(value == ""){
		alert("请填写本人联系电话");
		return false;
	}
	var value = document.getElementById("baseInfo.homeTelephone").value;
	if(value == ""){
		alert("请填写家庭电话");
		return false;
	}
	var value = document.getElementById("baseInfo.fertilityState").value;
	if(value == ""){
		alert("请填写婚姻状况");
		return false;
	}
	/* var value = document.getElementById("baseInfo.registeredAddress").value;
	if(value == ""){
		alert("请填写户口所在地");
		return false;
	}
	var value = document.getElementById("baseInfo.liveAddress").value;
	if(value == ""){
		alert("请填写现居住详细地址");
		return false;
	} */
	var value = document.getElementById("baseInfo.emergencyContactor").value;
	if(value == ""){
		alert("请填写紧急状况联系人");
		return false;
	}
	var value = document.getElementById("baseInfo.emergencyContactNumber").value;
	if(value == ""){
		alert("请填写紧急状况联系电话");
		return false;
	}
	var value = document.getElementById("baseInfo.emergencyContactRelationship").value;
	if(value == ""){
		alert("请填写与紧急状况联系人关系");
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
	location = "<url:context/>/supervisory.do?method=supervisoryPageList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
function initProvince(){
	var url = "../json/findRegionByParentId.do?callback=?&parentId=0";
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setProvince(data);
	});
}
function setProvince(province){
	var nativePlaceProvince=$("#nativePlaceProvince");
	var liveAddressProvince=$("#liveAddressProvince");
	for(var i=0;i<province.length;i++){
		var option = "<option value="+province[i].id+">" + province[i].name
		+ "</option>";
		nativePlaceProvince.append(option);
		liveAddressProvince.append(option);
	}
}
function changeProvince(id,nextSelect){
	if(id==-1){
		return;
	}
	var url = "../json/findRegionByParentId.do?callback=?&parentId="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setCity(data,nextSelect);
	});
}

function setCity(city,nextSelect){
	nextSelect.html("");
	var option = "<option value='-1' >请选择</option>";
	nextSelect.append(option);
	for(var i=0;i<city.length;i++){
		var option = "<option value="+city[i].id+">" + city[i].name
		+ "</option>";
		nextSelect.append(option);
	}
}

function getRegisteredAddress(){
	$("#registeredAddres").val($('#nativePlaceProvince option:selected').text()+" "+$('#nativePlaceCity option:selected').text()+" "+$('#nativePlaceCounty option:selected').text());
}

function getLiveAddress(){
	$("#liveAddres").val($('#liveAddressProvince option:selected').text()+" "+$('#liveAddressCity option:selected').text()+" "+$('#liveAddressCounty option:selected').text());
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
<div class="public-bar hidden">
	<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">监管员信息管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">监管员招聘管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">新增监管员基本信息</a>
         </span>
	</div>
</div>
<br/>

<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/supervisory" styleId="supervisoryForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="addSupervisoryBaseInfo"/>



<table class="formTable">
	<tr class="formTitle">
		<td colspan="4">新增监管员基本信息</td>
	</tr>
	<tr>
		<td colspan="4" class="message" style="text-align: center;">请提供您的真实信息，我们将对您的信息进行全面核实，由于信息不实所带来的一切后果将由应试人员承担。（注：<font color="#FF0000">*</font>为必填项）：</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>应聘岗位：</td>
		<td class="codeCol"><html:text property="baseInfo.job" styleId="baseInfo.job"/></td>
		<td class="nameCol">入职时间：</td>
		<td class="codeCol">
			<form:calendar property="baseInfo.entryTime" styleId="baseInfo.entryTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
	</tr>
	<tr>
		<td colspan="4"> 
			<table>
				<!-- <tr>
					<td class="nameCol" colspan="7" style="text-align: left;">一、基本资料</td>
				</tr> -->
				<tr>
					<td class="nameCol"  style="width: 150px;line-height:20px;"><font color="#FF0000">*</font>姓名</td>
					<td class="codeCol" ><html:text  style="width: 150px" property="baseInfo.name" styleId="baseInfo.name"/></td>
					<td class="nameCol" style="width: 150px;line-height:20px;"><font color="#FF0000">*</font>出生日期</td>
					<td class="codeCol">
						<form:calendar property="baseInfo.birthday" styleId="baseInfo.birthday" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
					</td>
					<td class="nameCol"  style="width:150px;line-height:20px;"><font color="#FF0000">*</font>性别</td>
					<td class="codeCol" ><html:text style="width: 150px" property="baseInfo.gender" styleId="baseInfo.gender"/></td>
					<td rowspan="4">
					<!-- 上传头像 -->
					上传：<input type="file" style="width:150px" name="headPicture" id="headPicture"/>
						<!-- <div style="position:absolute;width:80%;height:100%; ">
							<input id='f_file' type="file" style="width:100px;hight:100px;border:1px; border-color:black; " />

							<span class="sp_file"><font color="#FF0000">*</font>照片 （一寸免冠）点击上传</span>
						</div> -->
					</td>
				</tr>
				<tr>
					<td class="nameCol" style="width: 150px;line-height:20px;"><font color="#FF0000">*</font>身份证号</td>
					<td class="codeCol"><html:text style="width: 150px" property="baseInfo.idNumber" styleId="baseInfo.idNumber"/></td>
					<td class="nameCol" style="width: 150px;line-height:20px;"><font color="#FF0000">*</font>民族</td>
					<td class="codeCol"><html:text style="width: 150px" property="baseInfo.nation" styleId="baseInfo.nation"/></td>
					<td class="nameCol" style="width: 150px;line-height:20px;"><font color="#FF0000">*</font>学历</td>
					<td class="codeCol"><html:text style="width: 150px" property="baseInfo.educationBackground" styleId="baseInfo.educationBackground"/></td>
				</tr>
				<tr>
					<td class="nameCol" style="width: 150px;line-height:20px;">籍贯</td>
					<td class="codeCol"><html:text style="width: 150px" property="baseInfo.nativePlace" styleId="baseInfo.nativePlace"/></td>
					<td class="nameCol" style="width: 150px;line-height:20px;"><font color="#FF0000">*</font>政治面貌</td>
					<td class="codeCol"><html:text style="width: 150px" property="baseInfo.politicsStatus" styleId="baseInfo.politicsStatus"/></td>
					<td class="nameCol" style="width:150px;line-height:20px;"><font color="#FF0000">*</font>户口性质</td>
					<td class="nameCol"style="text-align: left;" >
						<form:radios  property="baseInfo.registeredStatus" collection="registeredStatus" styleId="baseInfo.registeredStatus"/>
					</td>
					<%-- <td class="codeCol"><html:text property="baseInfo.registeredStatus" styleId="baseInfo.registeredStatus"/></td> --%>
				</tr>
				<tr>
					<td class="nameCol" style="width: 150px;line-height:20px;"><font color="#FF0000">*</font>本人联系电话</td>
					<td class="codeCol" ><html:text style="width: 150px" property="baseInfo.selfTelephone" styleId="baseInfo.selfTelephone"/></td>
					<td class="nameCol" style="width: 150px;line-height:20px;"><font color="#FF0000">*</font>家庭电话</td>
					<td class="codeCol"><html:text style="width: 150px" property="baseInfo.homeTelephone" styleId="baseInfo.homeTelephone"/></td>
					<td class="nameCol" style="width: 150px;line-height:20px;"><font color="#FF0000">*</font>婚育状况</td>
					<td class="codeCol"><html:text  style="width: 150px"property="baseInfo.fertilityState" styleId="baseInfo.fertilityState"/></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<table>
				<tr>
					<td class="nameCol"  style="width: 200px;">户口所在地（省）：</td>
					<td class="codeCol"style="width:180px;">
						<form:select styleClass="ly-bor-none"  style="width: 150px;" property="baseInfo.nativePlaceProvince" onchange="changeProvince(this.value,$('#nativePlaceCity'))" 
								styleId="nativePlaceProvince" >
								<html:option value="-1">请选择</html:option>
							</form:select>
							<%-- <select id="nativePlaceProvince" name="baseInfo.nativePlaceProvince"  >
	                 	 	<c:forEach items="${nativePlaceProvince }" var="row">
	                    		<option <c:if test="${row.value==supervisoryForm.user.baseInfo.nativePlaceProvince}">selected='selected'</c:if> value="<c:out value='${row.value}'/>"><c:out value="${row.label }"/></option>
	                  		</c:forEach>
	       			 		</select> --%>
					</td> 
					<td class="nameCol"  style="width:220px;">户口所在地（市）：</td>
					<td class="codeCol"style="width:180px;">
						<form:select style="width: 150px;"  property="baseInfo.nativePlaceCity"  onchange="changeProvince(this.value,$('#nativePlaceCounty'))"
								styleId="nativePlaceCity">
								<html:option value="-1">请选择</html:option>
							</form:select></td>
					<td class="nameCol"  style="width:300px;">户口所在地（县/区）：</td>
					<td class="codeCol"style="width:180px;">
						<form:select style="width: 150px;"  property="baseInfo.nativePlaceCounty" 
								styleId="nativePlaceCounty">
								<html:option value="-1">请选择</html:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="nameCol"style="width:250px">户口所在地详细地址:</td>
					<td class="codeCol" colspan="5" style="text-align: left;">
						<input type="text" id="registeredAddres" style="width:200px" disabled="disabled"/>
						<html:text style="width:500px" property="baseInfo.registeredAddress" styleId="registeredAddress" onfocus="getRegisteredAddress()"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol" style="width: 200px;" ><font color="#FF0000">*</font>现住址（省）：</td>
					<td class="codeCol"style="width:180px;">
						<form:select style="width: 150px;"  property="baseInfo.liveAddressProvince" onchange="changeProvince(this.value,$('#liveAddressCity'))" 
								styleId="liveAddressProvince" >
								<html:option value="-1">请选择</html:option>
						</form:select>
					</td> 
					<td class="nameCol" style="width: 200px;" ><font color="#FF0000">*</font>现住址（市）：</td>
					<td class="codeCol"style="width:180px;">
						<form:select style="width: 150px;"  property="baseInfo.liveAddressCity" onchange="changeProvince(this.value,$('#liveAddressCounty'))" 
								styleId="liveAddressCity">
								<html:option value="-1">请选择</html:option>
						</form:select>
					</td>
					<td class="nameCol" style="width: 200px;"><font color="#FF0000">*</font>现住址（县/区）：</td>
					<td class="codeCol"style="width:180px;" >
						<form:select style="width: 150px;"  property="baseInfo.liveAddressCounty"
								styleId="liveAddressCounty">
								<html:option value="-1">请选择</html:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="nameCol" style="width: 200px"><font color="#FF0000">*</font>现居住详细地址</td>
					<td class="codeCol" colspan="5" style="text-align: left;">
						<input type="text" id="liveAddres" style="width:200px" disabled="disabled"/>
						<html:text style="width:500px" property="baseInfo.liveAddress" styleId="liveAddress" onfocus="getLiveAddress()"/>
					</td>
				</tr> 
				<tr>
					<td class="nameCol" style="width: 200px"><font color="#FF0000">*</font>紧急状况联系人</td>
					<td class="codeCol"><html:text style="width: 180px;" property="baseInfo.emergencyContactor" styleId="baseInfo.emergencyContactor"/></td>
					<td class="nameCol"  style="width: 200px"><font color="#FF0000">*</font>联系电话</td>
					<td class="codeCol"><html:text style="width: 180px;" property="baseInfo.emergencyContactNumber" styleId="baseInfo.emergencyContactNumber"/></td>
					<td class="nameCol"  style="width:200px"><font color="#FF0000">*</font>与紧急联系人的关系</td>
					<td class="codeCol"><html:text style="width: 180px;" property="baseInfo.emergencyContactRelationship" styleId="baseInfo.emergencyContactRelationship"/></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4" class="message" style="text-align: center;">
			本人声明：以上情况均为如实、正确填写，如有任何虚构，愿接受无偿之解雇；本人确认已阅读过公司发布的制度，了解了所有内容，并且公司为我解答了相关疑问。我将遵守公司的管理制度及规定。						
		</td>
	</tr>
	<%-- <tr>
		<td  class="nameCol" >本人对以上内容表示同意并签字确认：</td>
		<td class="codeCol"><html:text property="baseInfo.sign" styleId="baseInfo.sign"/></td>
		<td class="nameCol">日期：</td>
		<td class="codeCol">
			<form:calendar property="baseInfo.currentTime" styleId="baseInfo.currentTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
	</tr> --%>
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
