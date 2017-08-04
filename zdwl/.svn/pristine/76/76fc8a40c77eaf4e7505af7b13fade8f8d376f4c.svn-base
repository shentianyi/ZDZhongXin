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
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
	//页面初始化函数
	function doLoad(){
		//显示提示信息
		showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	}
	
	$(function(){
		changeDealer($('#dealerId').val());
		changeRoster($("#rosterId").val());
	});
	
	//执行保存操作
	function doSave() {
		var dealerId = document.getElementById("dealerId").value;
		if(dealerId == "-1"){
			alert("请选择经销商");
			return false;
		}
		
		var value = document.getElementById("complaint.complainantPosition").value;
		if(value == ""){
			alert("请填写投诉人职位");
			return false;
		}
		
		var value = document.getElementById("complaint.complainantName").value;
		if(value == ""){
			alert("请填写投诉人姓名");
			return false;
		}
		var value = document.getElementById("complaint.complainantPhone").value;
		if(value == ""){
			alert("请填写投诉人电话");
			return false;
		}
		
		var value = document.getElementById("rosterId").value;
		if(value == "-1"){
			alert("请选择监管员");
			return false;
		}
		
		var value=""; 
		var arr = document.getElementsByName("complaint.complaintSorts");
		for(var i=0;i<arr.length;i++){
		        if(arr[i].checked){    
		          value=arr[i].value;
		        }
		    }
		if(value == ""){
			alert("请选择投诉问题分类");
			return false;
		}
		
		var value = document.getElementById("complaint.complaintContent").value;
		if(value == ""){
			alert("请填写投诉内容");
			return false;
		}
		var value=""; 
		var arr = document.getElementsByName("complaint.processingDepartment");
		for(var i=0;i<arr.length;i++){
		        if(arr[i].checked){    
		          value=arr[i].value;
		        }
		    }
		if(value == ""){
			alert("请选择处理部门");
		
		//对表单内容进行验证，包括对输入类型等限制方式
		if (validateMain("complaintForm")) {
			//为时间类型输入项补齐时间戳
			setTimeSuffix();
			//提交表单
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

	function changeDealer(id) {
		if (id == -1) {
			setDealer(new Object());
			return;
		}
		var url = "../json/getDealerByIdJsonAction.do?callback=?&id=" + id;
		$.getJSON(url, function(result) {
			var data = result.data;
			console.info(data);
			setDealer(data[0]);
		});
	}
	
	function changeRoster(id) {
		if (id == -1) {
			document.forms[0].reset();
			return;
		}
		var url = "../json/getRosterById.do?callback=?&id=" + id;
		$.getJSON(url, function(result) {
			var data = result.data[0];
			setRoster(data);
		});
	}

	function setDealer(dealer) {
		$("#dealerName").val(dealer.dealerName);
		$("#brand").val(dealer.brand);
		$("#bankName").val(dealer.bankName);
	}
	
	function setRoster(roster){
		$("#rosterName").val(roster.name);
		$("#staffNo").val(roster.staffNo);
		$("#rosterPhone").val(roster.phone);
	}
</script>
</head>
<body onLoad="doLoad()">

	<div class="title">来电投诉记录表</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/complaint.do" styleId="complaintForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="update" />
				<html:hidden property="complaint.id"/>

				<table class="formTable">
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>经销商：</td>
						<td class="codeCol"><form:select property="complaint.dealerId"
								styleId="dealerId" onchange="changeDealer(this.value)">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="dealers" label="label"
									value="value" />
							</form:select></td>
					</tr>
					<tr>
						<td class="nameCol">经销店：</td>
						<td class="codeCol"><input type="text" id="dealerName"
							readonly="readonly" /></td>
						<td class="nameCol">金融机构：</td>
						<td class="codeCol"><input type="text" id="bankName"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="nameCol">品牌：</td>
						<td class="codeCol"><input type="text" id="brand"
							readonly="readonly" /></td>
						<td class="nameCol"><font color="#FF0000">*</font>投诉人职位：</td>
						<td class="codeCol"><html:text property="complaint.complainantPosition" styleId="complaint.complainantPosition"></html:text></td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>投诉人姓名：</td>
						<td class="codeCol"><html:text property="complaint.complainantName" styleId="complaint.complainantName"></html:text></td>
						<td class="nameCol"><font color="#FF0000">*</font>投诉人电话：</td>
						<td class="codeCol"><html:text property="complaint.complainantPhone" styleId="complaint.complainantPhone"></html:text></td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>监管员：</td>
						<td class="codeCol">
							<form:select property="complaint.rosterId"
								styleId="rosterId" >
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="rosters" label="label"
									value="value" />
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="nameCol">监管员工号：</td>
						<td class="codeCol"><input type="text" id="staffNo" readonly="readonly"></td>
						<td class="nameCol">监管员姓名：</td>
						<td class="codeCol"><input type="text" id="rosterName" readonly="readonly"></td>
					</tr>
					<tr>
						<td class="nameCol">监管员电话：</td>
						<td class="codeCol"><input type="text" id="rosterPhone" readonly="readonly"></td>
						
					</tr>
					
					
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>投诉问题分类：</td>
						<td class="codeCol" colspan="3">
							<form:radios collection="complaintSorts" property="complaint.complaintSorts" styleId="complaint.complaintSorts"></form:radios>
						</td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>投诉内容：</td>
						<td class="codeCol" colspan="3"><html:text property="complaint.complaintContent"  styleId="complaint.complaintContent"></html:text></td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>处理部门：</td>
						<td class="codeCol" colspan="3">
							<form:radios collection="processingDepartment" property="complaint.processingDepartment"styleId="complaint.processingDepartment"></form:radios>
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
				<br />
				<div class="message" id="message" style="display: none"></div>
			</html:form>

		</div>
	</div>
</body>
</html>
