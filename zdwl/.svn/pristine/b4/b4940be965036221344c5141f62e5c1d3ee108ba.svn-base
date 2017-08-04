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
<script>
	//页面初始化函数
	function doLoad(){
		//显示提示信息
		showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
		var processingId = $("#processingIdinit").val();
		$("#processingId").val(processingId);
		initcheckBox();
	}
	
	function loadBankInfo(bankId){
		var bankPath = $("#bankPath").val();
		var bankId = $("#financeId").val();
		console.info(bankId);
		console.info(bankPath);
		
		var parentIds = bankPath.split("/");
		if(parentIds.length>3){
			$("#one").val(parentIds[1]);
			$("#one").change();
			$("#two").val(parentIds[2]).change();
			$("#three").val(bankId).change();
		}else if(parentIds.length>2){
			$("#one").val(parentIds[1]);
			$("#one").change();
			$("#two").val(bankId).change();
		}else if(parentIds.length>1){
			$("#one").val(bankId);
			$("#one").change();
		}
	}
	
	function changeRosterOne(id,storeId) {
// 		$("#roster").val(id);
		document.getElementById("complaint.rosterId").value = id;
		url = "/json/getDealerBankInfoByRepId.do?callback=?&id=" + id;
		$.getJSON(url, function(result) {
			var data = result.data;
			$.each(data, function(i, item) {
				if(storeId == item.dealerId){
					var option = "<option selected value="+item.dealerId+">" + item.dealerName + "</option>";
					$("#storeId").append(option);
				}else{
					var optiono = "<option value="+item.dealerId+">" + item.dealerName + "</option>";
					$("#storeId").append(optiono);
				}
			});
		});
	}
	
	function initcheckBox(){
		var complaintObjId = $('#complaintObjId input[name="complaint.complaintObjId"]:checked').val();
		console.info(complaintObjId);
		if(complaintObjId && complaintObjId == 1){//经销商
			$("#complaintDealer").show();$("#complaintObjInfoOne").show();
			$("#complaintObjInfoTwo").show();
			var dealerId = $("[name='complaint.dealerId']").val();
			if(dealerId > 0){
				changeDealer(dealerId);
			}
		}
		if(complaintObjId && complaintObjId == 2){//金融机构
			$("#complaintFinance").show();$("#complaintObjInfoTwo").show();
			loadBankInfo($("#financeId").val());
		}
		if(complaintObjId && complaintObjId == 3){//监管员
			$("#rosterInfoOne").show();$("#rosterInfoTwo").show();
			var rosterId = document.getElementById("complaint.rosterId").value;
			var storeId = document.getElementById("complaint.storeId").value;
			if(rosterId && rosterId > 0){
				changeRosterOne(rosterId,storeId);
			}
		}
		
		var types = document.getElementById("complaint.phoneType").value;
		var phoneTypeSource = types.split(",");
		var phoneTypeTarget = document.getElementsByName("phoneTypes");
		for(var j=0; j<phoneTypeSource.length; j++) {
			for(var i=0; i<phoneTypeTarget.length; i++) {
				if(phoneTypeSource[j] == phoneTypeTarget[i].value) {
					phoneTypeTarget[i].checked = true;
				}
			}
		}
	}
	
	//执行保存操作
	function doSave() {
		var value = document.getElementById("complaint.trackCondition").value;
		if(value == ""){
			alert("请填写跟踪情况");
			return false;
		}
		document.forms[0].submit();
	}

	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/complaint.do?method=findList";
	}

	//执行表单重置操作
	function doReset() {
		$("[name='complaint.trackCondition']").val("");
	}

	
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
				<html:hidden property="complaint.id"/>
				<input type="hidden" name="method" value="update" />
				<html:hidden property="complaint.financeId" styleId="financeId"/>
				<html:hidden property="complaint.phoneType" styleId="complaint.phoneType"/>
				<html:hidden property="complaint.storeId" styleId="complaint.storeId"/>
				<html:hidden property="complaint.rosterId" styleId="complaint.rosterId"/>
				<html:hidden property="complaint.bankId" styleId="bankId"/>
				<input type="hidden" id="processingIdinit" value="<c:out value='${processingIdinit}'/>"/>
				<c:if test="${bank != null }">
					<input type="hidden" id="bankPath" value="<c:out value='${bank.path}'/>"/>
				</c:if>
				<table class="formTable">
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>日期：</td>
						
						<td class="codeCol2">
							<input id="createDate" name="complaint.createDate" type="text" readonly="readonly" value='<fmt:formatDate value="${complaintForm.complaint.createDate }" pattern="yyyy-MM-dd"/>'/> 
						</td>
						<td class="nameCol2"><font color="#FF0000">*</font>时间：</td>
						<td class="codeCol2">
							<input id="createTime" name="complaint.createTime" readonly="readonly"/>  
						</td>
					</tr>
					
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>记录人：</td>
						<td class="codeCol2"><html:text property="complaint.createUserName" styleId="complaint.createUserName" readonly="true"></html:text></td>
						<td class="nameCol2"><font color="#FF0000">*</font>所属部门：</td>
						<td class="codeCol2"><html:text property="complaint.createUserDept" styleId="complaint.createUserDept" readonly="true"></html:text></td>
					</tr>
					
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>来电类型1：</td>
						<td class="codeCol2" colspan="3">
							<form:checkboxs property="phoneTypes" styleId="phoneTypes" collection="telephoneTypes" disabled="disabled"/>
						</td>
					</tr>
					
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>投诉对象：</td>
						<td class="codeCol2" colspan="3" id="complaintObjId">
							<form:radios collection="complaintObjs" property="complaint.complaintObjId" styleId="complaint.complaintObjId" disabled="disabled"></form:radios>
						</td>
					</tr>
					
					<tr id="complaintDealer">
						<td class="nameCol2"><font color="#FF0000">*</font>经销商：</td>
						<td class="codeCol2">
							<form:select property="complaint.dealerId" disabled="true"
 								styleId="dealerId" onchange="changeDealer(this.value)">
 								<html:option value="-1">请选择...</html:option>
 								<html:optionsCollection name="dealers" label="label" 
 									value="value" />
 							</form:select>
 						</td>
					</tr>
					
					<tr id="complaintFinance">
						<td class="nameCol2">金融机构：</td>
						<td class="codeCol2" colspan="3">
							<select id="one" disabled="disabled">
									<option value="-1">请选择...</option>
							</select> 
							<select id="two" disabled="disabled">
									<option value="-1">请选择...</option>
							</select> 
							<select id="three" disabled="disabled">
									<option value="-1">请选择...</option>
							</select>
						</td>
					</tr>
					
					<tr id="complaintObjInfoOne">
						<td class="nameCol2"><font color="#FF0000">*</font>品牌：</td>
						<td class="codeCol2"><html:text property="complaint.brandName" readonly="true" styleId="brand"></html:text></td>
					</tr>
					
					<tr id="complaintObjInfoTwo">
						<td class="nameCol2"><font color="#FF0000">*</font>来电人：</td>
						<td class="codeCol2"><html:text property="complaint.complainantName" readonly="true" styleId="complaint.complainantName"></html:text></td>
						<td class="nameCol2"><font color="#FF0000">*</font>职务：</td>
						<td class="codeCol2"><html:text property="complaint.complainantPosition" readonly="true" styleId="complaint.complainantPosition"></html:text></td>
					</tr>
					
					<tr id="rosterInfoOne">
						<td class="nameCol2"><font color="#FF0000">*</font>监管员：</td>
						<td class="codeCol2">
							<select id="rosterId" name="complaint.rosterId" disabled="disabled" onchange="changeRoster(this.value)">
					  			<option value="-1">请选择...</option>
					  			<c:forEach items="${reps}" var="row">
					  				<option <c:if test="${rosterId==row.value}">selected</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
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
							<select id="storeId" name="complaint.storeId" disabled="disabled" onchange="changeStore(this.value)">
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
							<form:radios collection="processingDepartment" property="complaint.processingDepartment" styleId="initprocessingDepartment" disabled="disabled"></form:radios>
						</td>
					</tr>
					
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>处理人员：</td>
						<td class="codeCol2">
							<select id="processingId" disabled="disabled" name="complaint.processingId">
					  			<option value="">请选择...</option>
					  		</select>
						</td>
					</tr>
					
					<tr id="rosterInfoTwo" colspan="2">
						<td class="nameCol2">状态：</td>
						<td class="codeCol2">
							<c:if test="${complaintForm.complaint.status == 1 }">未提交</c:if>
							<c:if test="${complaintForm.complaint.status == 2 }">已发送</c:if>
							<c:if test="${complaintForm.complaint.status == 3 }">已修正</c:if>
							<c:if test="${complaintForm.complaint.status == 4 }">已完成</c:if>
						</td>
					</tr>
					
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>投诉内容：</td>
						<td class="codeCol2" colspan="3"><html:textarea property="complaint.contentText" styleId="complaint.contentText" readonly="true" style="width:530px"></html:textarea></td>
					</tr>
					
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>处理意见：</td>
						<td class="codeCol2" colspan="3"><html:textarea property="complaint.treatmentOpinion" styleId="complaint.treatmentOpinion" readonly="true" style="width:530px"></html:textarea></td>
					</tr>
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>处理意见经办人：</td>
						<td class="codeCol2"><html:text property="complaint.treatmentProcessName" styleId="complaint.treatmentProcessName" readonly="true"></html:text></td>
						<td class="nameCol2"><font color="#FF0000">*</font>处理意见填写时间：</td>
						<td class="codeCol2">
							<input id="treatmentProcessTime" name="complaint.treatmentProcessTime" readonly="true" value='<fmt:formatDate value="${complaintForm.complaint.treatmentProcessTime }" pattern="yyyy-MM-dd"/>'/>
						</td>
					</tr>
					
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>解决方案：</td>
						<td class="codeCol2" colspan="3"><html:textarea property="complaint.solution" styleId="complaint.solution" readonly="true" style="width:530px"></html:textarea></td>
					</tr>
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>解决方案经办人：</td>
						<td class="codeCol2"><html:text property="complaint.solutionOperatorName" readonly="true" styleId="complaint.solutionOperatorName"></html:text></td>
						<td class="nameCol2"><font color="#FF0000">*</font>解决方案填写时间：</td>
						<td class="codeCol2">
							<input id="solutionProcessTime" name="complaint.solutionProcessTime" readonly="true" value='<fmt:formatDate value="${complaintForm.complaint.solutionProcessTime }" pattern="yyyy-MM-dd"/>'/>
						</td>
					</tr>
					
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>跟踪情况：</td>
						<td class="codeCol2" colspan="3"><html:textarea property="complaint.trackCondition" styleId="complaint.trackCondition" style="width:530px"></html:textarea></td>
					</tr>
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>跟踪情况经办人：</td>
						<td class="codeCol2"><html:text property="complaint.trackOperatorName" readonly="true" styleId="complaint.trackOperatorName"></html:text></td>
						<td class="nameCol2"><font color="#FF0000">*</font>跟踪情况填写时间：</td>
						<td class="codeCol2">
							<input id="trackProcessTime" name="complaint.trackProcessTime" readonly="true" value='<fmt:formatDate value="${complaintForm.complaint.trackProcessTime }" pattern="yyyy-MM-dd"/>'/>
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
