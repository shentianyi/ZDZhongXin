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
	initProvince();
}

//执行保存操作
function doSave(){
	var value = document.getElementById("province").value;
	if(value == "-1"){
		alert("请填写可驻派省");
		return false;
	}
	var value = document.getElementById("city").value;
	if(value == "-1"){
		alert("请填写可驻派市");
		return false;
	}
	var value = document.getElementById("county").value;
	if(value == "-1"){
		alert("请填写可驻派县、区");
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
	var id=document.getElementsByName("repository.id")[0].value;
	location = "<url:context/>/repository.do?method=getRepositoryDispatchCityByRepositoryId&repository.id="+id;
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
	var provinces=$("#province");
	for(var i=0;i<province.length;i++){
		var option = "<option value="+province[i].id+">" + province[i].name
		+ "</option>";
		provinces.append(option);
	}
}
function changeProvince(id,nextSelect){
	if(id==-1){
		document.forms[0].reset();
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
	
<html:form action="/repository" styleId="repositoryForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addRepositoryDispatchCity"/>
<html:hidden property="repository.id" styleId="repository.id" />
<table class="formTable">
	<tr class="formTitle">
		<td >新增储备库可派驻城市信息</td>
	</tr>
	<tr>
		<td >
			<table >
				<tr>
					<td class="nameCol" colspan="6" style="text-align: left;">可派驻城市</td>
				</tr>
				<tr>
					<td class="nameCol" style="text-align: center;"><font color="#FF0000">*</font>可派驻城市（省）</td>
					<td class="nameCol"  style="text-align: center;"><font color="#FF0000">*</font>可派驻城市（市）</td>
					<td class="nameCol"  style="text-align: center;"><font color="#FF0000">*</font>可派驻城市（县/区）</td>
				</tr>
				 <tr>
					<td class="codeCol"style="text-align: center;">
						<form:select property="repositoryDispatchCity.province" onchange="changeProvince(this.value,$('#city'))" 
							styleId="province" >
							<html:option value="-1">请选择</html:option>
						</form:select>
					</td>
					<td class="codeCol"style="text-align: center;">
						<form:select property="repositoryDispatchCity.city" onchange="changeProvince(this.value,$('#county'))"
							styleId="city" >
							<html:option value="-1">请选择</html:option>
						</form:select>
					</td>
					<td class="codeCol"style="text-align: center;">
						<form:select property="repositoryDispatchCity.county" 
							styleId="county" >
							<html:option value="-1">请选择</html:option>
						</form:select>
					</td>
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
