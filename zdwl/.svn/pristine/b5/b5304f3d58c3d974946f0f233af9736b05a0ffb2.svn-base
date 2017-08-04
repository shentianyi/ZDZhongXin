<%@page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ taglib uri="select.tld" prefix="select"%>


<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />

<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>

<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script src="/js/complaint.js"></script>
<script src="/js/complaintvalidate.js"></script>
<script>
	//页面初始化函数
	function doLoad(){
		//显示提示信息
		showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	}
	
	//执行保存操作
	function doSave() {
		if(complaintValidate()){
			document.forms[0].submit();
		}
	}

	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/complaint.do?method=findList";
	}

	//执行表单重置操作
	function doReset() {
		document.forms[0].reset();
	}

	/* 需求32修改下拉框为easyUI下拉筛选控件 */
/* $(function(){
	$("#dealerId").combobox({
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
			} 
		}
	});
}); */
</script>
<style type="text/css">
.nameCol2{
width:25%;
border-bottom:1px solid #eee;padding-top:13px;padding-bottom:13px;
border-right:1px solid #eee; 
text-align: right;
}
.codeCol2{
width:25%;
border-bottom:1px solid #eee;padding-top:13px;padding-bottom:13px;
border-right:1px solid #eee; 
} 
</style>
</head>
<body onLoad="doLoad()">
	<div class="title">来电投诉记录表</div>
	<hr size="1">
	<br />
	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/complaint.do" styleId="complaintForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="add" />
				<input type="hidden" id="roster" value="" />
				<input type="hidden" id="financeId" name="complaint.financeId" value="" />
				
				<html:hidden property="complaint.brandId" styleId="brandId"/>
				<html:hidden property="complaint.phoneType" styleId="complaint.phoneType"/>
				<html:hidden property="complaint.storeId" styleId="complaint.storeId"/>
				<html:hidden property="complaint.rosterId" styleId="complaint.rosterId"/>
				<html:hidden property="complaint.bankId" styleId="bankId"/>
				<table class="formTable">
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>日期：</td>
						
						<td class="codeCol2">
							<input id="createDate" name="complaint.createDate" type="text" value='<fmt:formatDate value="${complaintForm.complaint.createDate }" pattern="yyyy-MM-dd"/>'/> 
						</td>
						<td class="nameCol2"><font color="#FF0000">*</font>时间：</td>
						<td class="codeCol2">
							<input id="createTime" name="complaint.createTime" />  
						</td>
					</tr>
					
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>记录人：</td>
						<td class="codeCol2"><html:text property="complaint.createUserName" styleId="complaint.createUserName" readonly="true"></html:text></td>
						<td class="nameCol2"><font color="#FF0000">*</font>所属部门：</td>
						<td class="codeCol2"><html:text property="complaint.createUserDept" styleId="complaint.createUserDept"></html:text></td>
					</tr>
					
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>来电类型：</td>
						<td class="codeCol2" colspan="3">
							<form:checkboxs property="phoneTypes" styleId="phoneTypes" collection="telephoneTypes"/>
						</td>
					</tr>
					
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>投诉对象：</td>
						<td class="codeCol2" colspan="3" id="complaintObjId">
							<form:radios collection="complaintObjs" property="complaint.complaintObjId" styleId="complaint.complaintObjId"></form:radios>
						</td>
					</tr>
					
					<tr id="complaintDealer">
						<td class="nameCol2"><font color="#FF0000">*</font>经销商：</td>
						<td class="codeCol2">
 							<select class="ly-bor-none" id="dealerId" name="complaint.dealerId" onchange="changeDealer(this.value)" style="width:85%;">
							  			<option value="-1">请选择...</option>
							  			<c:forEach items="${dealers}" var="row">
							  				<option <c:if test="${row.value==complaintForm.complaint.dealerId}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
							  			</c:forEach>
							  </select>
 						</td>
					</tr>
					
					<tr id="complaintFinance">
						<td class="nameCol2">金融机构：</td>
						<td class="codeCol2" colspan="3">
							<select id="one">
									<option value="-1">请选择...</option>
							</select> <select id="two">
									<option value="-1">请选择...</option>
							</select> <select id="three">
									<option value="-1">请选择...</option>
							</select>
						</td>
					</tr>
					
					<tr id="complaintObjInfoOne">
						<td class="nameCol2"><font color="#FF0000">*</font>品牌：</td>
						<td class="codeCol2"><html:text property="complaint.brandName" styleId="brand" readonly="true" ></html:text></td>
					</tr>
					
					<tr id="complaintObjInfoTwo">
						<td class="nameCol2"><font color="#FF0000">*</font>来电人：</td>
						<td class="codeCol2"><html:text property="complaint.complainantName" styleId="complaint.complainantName"></html:text></td>
						<td class="nameCol2"><font color="#FF0000">*</font>职务：</td>
						<td class="codeCol2"><html:text property="complaint.complainantPosition" styleId="complaint.complainantPosition"></html:text></td>
					</tr>
					
					<tr id="rosterInfoOne">
						<td class="nameCol2"><font color="#FF0000">*</font>监管员：</td>
						<td class="codeCol2">
							<select id="rosterId" name="complaint.rosterId" onchange="changeRoster(this.value)">
					  			<option value="-1">请选择...</option>
					  			<c:forEach items="${reps}" var="row">
					  				<option value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
					  			</c:forEach>
					  		</select>
						</td>
						<td class="nameCol2">工号：</td>
						<td class="codeCol2">
							<html:text property="complaint.jobNum" styleId="complaint.jobNum" readonly="true"></html:text>
					</tr>
					<tr id="rosterInfoTwo" colspan="2">
						<td class="nameCol2">所在店：</td>
						<td class="codeCol2">
							<select id="storeId" name="complaint.storeId" onchange="changeStore(this.value)">
					  			<option value="-1">请选择...</option>
					  		</select>
						</td>
						<td class="nameCol2">所在行：</td>
						<td class="codeCol2">
							<html:text property="complaint.bankName" styleId="complaint.bankName" readonly="true"></html:text>
						</td>
					</tr>
					
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>处理部门：</td>
						<td class="codeCol2" colspan="3" id="processingDepartment">
							<form:radios collection="processingDepartment" property="complaint.processingDepartment" styleId="initprocessingDepartment"></form:radios>
						</td>
					</tr>
					
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>处理人员：</td>
						<td class="codeCol2">
							<select id="processingId" name="complaint.processingId">
					  			<option value="-1">请选择...</option>
					  		</select>
						</td>
					</tr>
					
					<tr id="rosterInfoTwo" colspan="2">
						<td class="nameCol2">状态：</td>
						<td class="codeCol2">
							<html:text property="complaint.status" styleId="complaint.status" readonly="true"></html:text>
						</td>
					</tr>
					
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>投诉内容：</td>
						<td class="codeCol2" colspan="3"><html:textarea property="complaint.contentText" styleId="complaint.contentText" style="width:530px"></html:textarea></td>
					</tr>
					
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>处理意见：</td>
						<td class="codeCol2" colspan="3"><html:textarea property="complaint.treatmentOpinion" styleId="complaint.treatmentOpinion" style="width:530px"></html:textarea></td>
					</tr>
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font> 经办人：</td>
						<td class="codeCol2"><html:text property="complaint.treatmentProcessName" styleId="complaint.treatmentProcessName" readonly="true"></html:text></td>
						<td class="nameCol2"><font color="#FF0000">*</font> 处理时间：</td>
						<td class="codeCol2">
							<input id="treatmentProcessTime" name="complaint.treatmentProcessTime" value='<fmt:formatDate value="${complaintForm.complaint.treatmentProcessTime }" pattern="yyyy-MM-dd"/>'/>
						</td>
					</tr>
					<br />
					<tr class="formTableFoot">
						<td colspan="4" align="center">
							<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
							<button class="formButton" onClick="doReset()">重&nbsp;置</button>&nbsp;
							<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
						</td>
					</tr>
				</table>
				<br />
				<div class="message" id="message" style="display: none"></div>
			</html:form>

		</div>
	</div>
</body>
</html>
