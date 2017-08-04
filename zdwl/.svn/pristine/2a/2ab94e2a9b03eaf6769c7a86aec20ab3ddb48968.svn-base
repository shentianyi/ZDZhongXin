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
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<%@ page import="com.zd.tools.taglib.form.FormTagConstants"%>
<%@ page import="com.zd.csms.supervisorymanagement.model.LeaveReplaceDynamicVO" %>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>中都汽车金融监管系统</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<!-- easyui -->
<!-- <link href="/easyui/themes/material/easyui.css" rel="stylesheet"
	type="text/css" /> -->
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script src="/js/dynamic/dynamic_table.js"></script>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<c:set var="CalendarScript" value="true"/>
<script>

$(function(){
	initTools();
});

//执行保存操作
function doSave(){
	var value=$("#startTime").datebox('getValue');
	if(value==null || value==""){
		alert("请选择加班开始时间！");
		return false;
	}
	var value=$("#endTime").datebox('getValue');
	if(value==null || value==""){
		alert("请选择加班结束时间！");
		return false;
	}
	var extraWorkDays=$("#extraWorkDays").val();
	if(extraWorkDays==null || extraWorkDays==""){
		alert("请填写加班天数！");
		return false;
	}else if(!(/^(0.5|\+?[1-9][0-9]*(\.(5|0))?)$/.test(extraWorkDays))){
		alert("加班天数有误，请输入0.5的正整数倍！"); 
        return false; 
	}
	var value= document.getElementsByName("extraworkApply.reason")[0].value;
	if(value==null || value==""){
		alert("请填写加班事由！");
		return false;
	}
	document.forms[0].submit();
}
function initTools(){
	$('#startTime').datetimebox({    
	    required: true,    
	    showSeconds: false,
	    spinnerWidth:'80%',
	    editable:false
	});
	$('#endTime').datetimebox({    
	    required: true,    
	    showSeconds: false,   
	    spinnerWidth:'80%',
	    editable:false
	});
	var startTime="<%=request.getAttribute("startTime")%>";
	if(startTime && startTime!="null" && startTime!="" ){
		$('#startTime').datetimebox('setValue',startTime); 
	}
	var endTime="<%=request.getAttribute("endTime")%>";
	if(endTime && endTime!="null" && endTime!="" ){
		$('#endTime').datetimebox('setValue',endTime); 
	}
}
//执行返回列表操作
function doReturn(){
	location = "<url:context/>/extraworkApply.do?method=findPageList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}

</script>
</head>
  
<body>

<div class="title">修改加班申请</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/extraworkApply" styleId="extraworkApplyForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="updateExtraworkApply"/>
<html:hidden property="extraworkApply.repositoryId" styleId="repositoryId"/>
<html:hidden property="extraworkApply.status" styleId="extraworkApply.status" value="0"/>
<html:hidden property="extraworkApply.applyTime" styleId="extraworkApply.applyTime"/>
<html:hidden property="extraworkApply.id" styleId="id"/>
<html:hidden property="extraworkApply.nextApproval" styleId="nextApproval"/>
<html:hidden property="extraworkApply.approvalState" styleId="approvalState"/>
<html:hidden property="extraworkApply.createUser" styleId="createUser"/>
<html:hidden property="extraworkApply.createTime" styleId="createTime"/>
<table class="formTable">
	<tr>
		<td colspan="4"> 
			<table style="width: 100%">
				<tr>
					<td class="nameCol">申请人工号：</td>
					<td class="codeCol" >
						<html:text  property="extraworkApply.staffNo" styleId="extraworkApply.staffNo" readonly="true"/>
					</td>
					<td class="nameCol">申请人：</td>
					<td class="codeCol" >
						<html:text  property="extraworkApply.name" styleId="extraworkApply.name"  readonly="true"/>
					</td>
					<td class="nameCol" >申请时间：</td>
					<td class="codeCol" >
						<c:out value="${applyTime }" />
					</td>
				</tr>
				<tr>
					<td class="nameCol">省：</td>
					<td class="codeCol" >
						<html:text  property="extraworkApply.province" styleId="extraworkApply.province" readonly="true"/>
					</td>
					<td class="nameCol">市：</td>
					<td class="codeCol" >
						<html:text  property="extraworkApply.city" styleId="extraworkApply.city" readonly="true"/>
					</td>
				</tr>
				<c:if test="${currentDealerList!=null}">
					<logic:iterate name="currentDealerList" id="row" indexId="index" >
						<tr>
							<td class="nameCol" style="width:15%">经销商<c:out value="${index+1}" />：</td>
							<td class="codeCol" style="width:15%">
								<c:out value='${row.dealerName}' />
							</td>
							<td class="nameCol" style="width:15%">金融机构<c:out value="${index+1}" />：</td>
							<td class="codeCol" style="width:25%">
								<c:out value='${row.bankName}' />
							</td>
							<td class="nameCol" style="width:15%">品牌<c:out value="${index+1}" />：</td>
							<td class="codeCol" style="width:15%">
								<c:out value='${row.brandName}' />
							</td>
						</tr>
					</logic:iterate>
				</c:if>
				<tr>
					<td class="nameCol"><font color="#FF0000">*</font>开始时间：</td>
					<td class="codeCol" >
						<input style="width: 80%" id="startTime" name="extraworkApply.startTime" type="text"/>
					</td>
					<td class="nameCol"><font color="#FF0000">*</font>结束时间：</td>
					<td class="codeCol" >
						<input style="width: 80%" id="endTime" name="extraworkApply.endTime" type="text"/>
					</td>
					<td class="nameCol" ><font color="#FF0000">*</font>加班天数：</td>
					<td class="codeCol" >
						<input type="text" id="extraWorkDays" name="extraworkApply.extraWorkDays" class="easyui-numberbox" value="<c:out value='${extraworkApplyForm.extraworkApply.extraWorkDays}'/>"
								data-options="min:0,precision:1"/>
					</td>
				</tr>
				<tr>
					<%-- <td class="nameCol"><font color="#FF0000">*</font>审批部门：</td>
					<td class="codeCol">
						<form:select property="extraworkApply.approvalDepartment"
							styleId="approvalDepartment" >
							<html:optionsCollection name="departments" label="label" value="value" />
						</form:select>
					</td> --%>
					<td class="nameCol"><font color="#FF0000">*</font>加班事由：</td>
					<td class="codeCol" colspan="3">
						<html:textarea style="width:80%" property="extraworkApply.reason" styleId="reason"/>
					</td>
				</tr>
				<tr>
					<td colspan="6"class="nameCol"></td>
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

</html:form>
</div>
</div>
</body>
</html>
