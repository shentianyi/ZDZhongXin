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
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	var value="";  
	var arr = document.getElementsByName("recommend.otherChannel");
	for(var i=0;i<arr.length;i++){
	        if(arr[i].checked){    
	          value=arr[i].value;
	        }
	    }
	showRecommender(value);
}

//执行保存操作
function doSave(){
	/* var value = document.getElementById("recommend.isInsideRecommend").value;
	if(value == ""){
		alert("请填写是否内部人员推荐");
		return false;
	}*/
	var value="";  
	var arr = document.getElementsByName("recommend.otherChannel");
	for(var i=0;i<arr.length;i++)
	    {
	        if(arr[i].checked)
	        {    
	          value=arr[i].value;
	        }
	    }
	if(value == ""){
		alert("请选择招聘渠道");
		return false;
	}else if(value=="2"||value=="4"){
		var value = document.getElementById("recommenderName").value;
		if(value == ""){
			alert("请填写推荐人姓名");
			return false;
		}
		var value = document.getElementById("recommenderPosition").value;
		if(value == ""){
			alert("请填写推荐人职位");
			return false;
		}
		var value = document.getElementById("recommenderDepartment").value;
		if(value == ""){
			alert("请填写推荐人所在部门或4S店名称");
			return false;
		}
		var value = document.getElementById("recommenderPhone").value;
		if(value == ""){
			alert("请填写推荐人联系方式");
			return false;
		}
		var value = document.getElementById("recommenderRelationship").value;
		if(value == ""){
			alert("请填写与推荐人关系");
			return false;
		}
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
	var id=document.getElementsByName("recommend.supervisorId")[0].value;
	location = "<url:context/>/supervisory.do?method=getRecommendBySupervisoryId&baseInfo.id="+id;
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
function showRecommender(recommenderType){
	if(recommenderType=="2"||recommenderType=="4"){
		$("#recommendInfo").show();
	}else{
		$("#recommendInfo").hide();
		$("#recommenderName").val("");
		$("#recommenderPosition").val("");
		$("#recommenderDepartment").val("");
		$("#recommenderPhone").val("");
		$("#recommenderRelationship").val("");
	}
}
</script>
</head>
<body onLoad="doLoad()">
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/supervisory" styleId="supervisoryForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="updSupervisoryRecommend"/>
<html:hidden property="recommend.id" styleId="recommend.id" />
<html:hidden property="recommend.supervisorId" styleId="recommend.supervisorId" />
<table class="formTable">
	<tr class="formTitle">
		<td colspan="4">修改监管员招聘渠道</td>
	</tr>
	<tr>
		<td colspan="4">
			<table >
				<tr>
					<td class="nameCol" colspan="7" style="text-align: left;">五、招聘渠道</td>
				</tr>
				<tr>
					<%-- <td class="nameCol" style="text-align: center;width: 300px;" ><font color="#FF0000">*</font>是否内部人员推荐</td>
					<td class="codeCol" ><html:text property="recommend.isInsideRecommend" styleId="recommend.isInsideRecommend"/></td> --%>
					<td class="nameCol" ><font color="#FF0000">*</font>招聘渠道</td>
					<td class="nameCol"style="text-align: left;width:700px" colspan="6">
						<form:radios property="recommend.otherChannel" collection="RecommendChannelOptions" styleId="recommend.otherChannel" onchange="showRecommender(this.value)"/>
					</td>
				</tr>
				<tr>
					<td colspan="7">
						<div id="recommendInfo" hidden="true">
							<table>
								<tr>
								 	<td class="nameCol"><font color="#FF0000">*</font>推荐人姓名</td>
									<td class="codeCol"><html:text property="recommend.recommenderName" styleId="recommenderName"/></td>
									<td class="nameCol"><font color="#FF0000">*</font>推荐人职位</td>
									<td class="codeCol"><html:text property="recommend.recommenderPosition" styleId="recommenderPosition"/></td>
									<td class="nameCol" style="text-align: center;"rowspan="2"><font color="#FF0000">*</font>推荐人所在部门或4S店名称</td>
									<td class="codeCol" rowspan="2" colspan="2" style="width:300px;height: 50px;"  >
										<html:text style="width:300px;height: 50px;" property="recommend.recommenderDepartment" styleId="recommenderDepartment"/>
									</td>
								</tr>
								<tr>
									<td class="nameCol"><font color="#FF0000">*</font>推荐人联系方式</td>
									<td class="codeCol"><html:text property="recommend.recommenderPhone" styleId="recommenderPhone"/></td>
									<td class="nameCol"><font color="#FF0000">*</font>与推荐人关系</td>
									<td class="codeCol"><html:text property="recommend.recommenderRelationship" styleId="recommenderRelationship"/></td>
									
								</tr>
							</table>
						</div>
					</td>
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