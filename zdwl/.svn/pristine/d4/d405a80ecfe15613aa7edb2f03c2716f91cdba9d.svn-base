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

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>中都汽车金融监管系统</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<!-- easyui -->
<link href="/easyui/themes/material/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>

<script>

$(function(){
	initBank();
	init();
});
//执行保存操作
function doSave(){
	$("#headBank").val($("#one").val());
	$("#branch").val($("#two").val());
	$("#subbranch").val($("#three").val());
	var value=""; 
	var arr = document.getElementsByName("dataMailcost.mailItems");
	for(var i=0;i<arr.length;i++){
	        if(arr[i].checked){    
	          value=arr[i].value;
	        }
	    }
	if(value == ""){
		alert("请选择邮寄项目");
		return false;
	}
	var value = document.getElementById("dataMailcost.fqDate").value;
	if(value == ""){
		alert("请填写发起日期");
		return false;
	}
	
	
	  var value=$("#mailPersonDealer").val(); 
	if(value == "-1"||value == ""){
		alert("请选择邮寄人经销商");
		return false;
	}  
	var value = document.getElementById("dataMailcost.express").value;
	if(value == ""){
		alert("请填写快递公司");
		return false;
	}
	
	var value = document.getElementById("dataMailcost.money").value;
	if(value == ""){
		alert("请填写预计金额");
		return false;
	}
	var value = document.getElementById("dataMailcost.transportBegin").value;
	if(value == ""){
		alert("请填写运输城市（起）");
		return false;
	}
	var value = document.getElementById("dataMailcost.transportEnd").value;
	if(value == ""){
		alert("请填写运输城市（止）");
		return false;
	}
	
	var value=$("#receiverType").val(); 
	if(value == "-1"){
		alert("请选择接收人部门");
		return false;
	}else if(value == "1"){
		var value=$("#businessOfficer").combobox('getValue'); 
		if(value == "-1" || value == "null" || value ==null){
			alert("请选择业务专员");
			return false;
		}
	}else if(value == "2"){
		var headBank = $("#headBank").val();
		if(headBank == "-1" || headBank == "null" || headBank ==null){
			alert("请选择金融机构(总行)");
			return false;
		}
	}else if(value == "3"){
		var value =$("#dealerId").combobox('getValue');
		if(value == "-1" || value == "null" || value ==null){
			alert("请选择经销商");
			return false;
		}
	}else if(value == "4"){
		var value = $("#supervisoryId").combobox('getValue');
		if(value == "-1" || value == "null" || value ==null){
			alert("请选择监管员");
			return false;
		}
	}
	document.forms[0].submit();  
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/dataMailcost.do?method=findList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}

function changeMailPersonDealer(id){
	if(id==-1){
		document.forms[0].reset();
		return;
	}
	var url = "../json/getDealerByIdJsonAction.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setMailPersonDealer(data[0]);
	});
}
function setMailPersonDealer(dealer){
	$("#yjjrjg").val(dealer.bankName);
}

function changeReceiverType(receiverType){
	if(receiverType==-1){
		return;
	}else if(receiverType==1){
		//获取所有业务专员
		$("#businessOfficertr").show();
		$("#bank").hide();
		$("#dealer").hide();
		$("#supervisory").hide();
		
		$("#supervisoryId").val("-1");
		$("#dealerId").val("-1");
		$("#headBank").val("-1");
		$("#branch").val("-1");
		$("#subbranch").val("-1");
	}else if(receiverType==2){
		//获取金融机构
		$("#bank").show();
		$("#businessOfficertr").hide();
		$("#dealer").hide();
		$("#supervisory").hide();
		
		$("#businessOfficer").val("-1");
		$("#supervisoryId").val("-1");
		$("#dealerId").val("-1");
		$("#headBank").val($("#one").value);
		$("#branch").val($("#two").value);
		$("#subbranch").val($("#three").value);
	}else if(receiverType==3){
		//获取所有经销商
		$("#dealer").show();
		$("#bank").hide();
		$("#businessOfficertr").hide();
		$("#supervisory").hide();
		
		$("#businessOfficer").val("-1");
		$("#supervisoryId").val("-1");
		$("#headBank").val("-1");
		$("#branch").val("-1");
		$("#subbranch").val("-1");
	}else if(receiverType==4){
		//获取所有监管员
		$("#supervisory").show();
		$("#businessOfficertr").hide();
		$("#bank").hide();
		$("#dealer").hide();
		
		$("#businessOfficer").val("-1");
		$("#dealerId").val("-1");
		$("#headBank").val("-1");
		$("#branch").val("-1");
		$("#subbranch").val("-1");
	}
}

function initBank() {
	$("#one option:gt(0)").remove();
	loadSelect(-1, $("#one"));

	$("#one").change(function() {
		$("#two option:gt(0)").remove();
		$("#three option:gt(0)").remove();
		var id = this.value;
		if (id>0) {
			loadSelect(id, $("#two"));
			setBank(id,$(this).find("option:selected").text());
		}else{
			setBank($("#one").val(),$("#one").find("option:selected").text());
		}
	});
	$("#two").change(function() {
		var id = this.value;
		if (id>0) {
			$("#three option:gt(0)").remove();
			loadSelect(id, $("#three"));
			setBank(id,$(this).find("option:selected").text());
		}else{
			setBank($("#one").val(),$("#one").find("option:selected").text());
			$("#three").val("-1");
		}
	});
	$("#three").change(function(){
		var id = this.value;
		if (id>0) {
			setBank(id,$(this).find("option:selected").text());
		}else{
			setBank($("#two").val(),$("#two").find("option:selected").text());
		}
	});
	
}


function loadSelect(id, nextSelect) {
	var url = "../json/getBankChildById.do?method=findChildListById&callback=?&bankQuery.id="
			+ id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		$.each(data, function(i, item) {
			var option = "<option value="+item.id+">" + item.bankName
					+ "</option>";
			nextSelect.append(option);
		});
	});
}

function setBank(id,name){
	if(id>0){
		$("#bankId").val(id);
	}else{
		$("#bankId").val("");
	}
}

function changeDeliverer(id) {
	cleanDeliver();
	if(id==-1){
		return;
	}
	var url = "../json/getSupervisorByRepositoryId.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setDeliverer(data[0]);
	});
	getDealerBySupervisor(id);
}
function setDeliverer(supervisor){
	$("#staffNo").val(supervisor.staffNo);
	
}

function getDealerBySupervisor(id) {
	if(id==-1){
		return;
	}
	var url = "../json/getDealerByRepositoryId.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		changeDealer(data);
	});
}

function changeDealer(dealers) {
	if(dealers==null){
		return;
	}
	
	for(var i=0;i<dealers.length;i++){
		var url = "../json/getDealerByIdJsonAction.do?callback=?&id="+dealers[i].dealerId;
		$.getJSON(url, function(result) {
			var data = result.data;
			console.info(data);
			setDealer(data[0]);
		});
	}
	
}
var delivererDealerID="";
function setDealer(dealer){
	delivererDealerID=delivererDealerID+dealer.id+",";
	$("#dDealer").val($("#dDealer").val()+"  "+dealer.dealerName);
	$("#dBank").val($("#dBank").val()+"  "+dealer.bankName);
}

function cleanDeliver(){
	$("#staffNo").val("");
	$("#dDealer").val("");
	$("#dBank").val("");
}
function init() {
	$("#businessOfficer").combobox({
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
	
	$("#supervisoryId").combobox({
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
				changeDeliverer(newValue);
			}

		}
	});
	 var supervisoryId = $("#supervisoryId").combobox('getValue');
		if (supervisoryId) {
			changeDeliverer(supervisoryId);
		}
	
}


/* 需求32修改下拉框为easyUI下拉筛选控件 */
/* $(function(){
	$("#mailPersonDealer").combobox({
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
/* 需求32修改下拉框为easyUI下拉筛选控件 */
/* $(function(){
	$("#receiverType").combobox({
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
</head>
  
<body>
<div class="title">新增资料邮寄费用申请</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/dataMailcost" styleId="dataMailcostForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addDataMailcost">
<html:hidden property="dataMailcost.mailPerson" styleId="dataMailcost.mailPerson"/>
<html:hidden property="dataMailcost.mailPersonStaffNo" styleId="dataMailcost.mailPersonStaffNo"/>
<html:hidden property="dataMailcost.status" styleId="dataMailcost.status" value="0"/>
<html:hidden property="dataMailcost.headBank" styleId="headBank"/>
<html:hidden property="dataMailcost.branch" styleId="branch"/>
<html:hidden property="dataMailcost.subbranch" styleId="subbranch"/>
<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>邮寄项目：</td>
		<td class="codeCol" style="width:450px" colspan="3">
			<form:radios  property="dataMailcost.mailItems" styleId="dataMailcost.mailItems" collection="dataMailcostStateOptions"/>
		</td>
	</tr>
	<tr>
		<td class="nameCol" style="width:250px"><font color="#FF0000">*</font>发起日期：</td>
		<td class="codeCol" colspan="3">
			<form:calendar property="dataMailcost.fqDate" styleId="dataMailcost.fqDate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
	</tr>
	<tr>
		<td class="nameCol">配件：</td>
		<td class="codeCol">
			<html:text property="dataMailcost.parts" styleId="dataMailcost.parts"></html:text>
		</td>
		<td class="nameCol">其它：</td>
		<td class="codeCol">
			<html:text property="dataMailcost.other" styleId="dataMailcost.other"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><!-- <font color="#FF0000">*</font> -->邮寄人：</td>
		<td class="codeCol">
			<c:out value="${mailPerson}"/>
		</td>
		<td class="nameCol">工号：</td>
		<td class="codeCol">
			<html:text property="dataMailcost.mailPersonStaffNo" styleId="dataMailcost.mailPersonStaffNo" disabled="true"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>经销商：</td>
		<td class="codeCol">
			<select id="mailPersonDealer" style="width:150px;"  name="dataMailcost.mailPersonDealer" onchange="changeMailPersonDealer(this.value)" >
				<option value="-1">请选择</option>
				<c:forEach items="${mailPersonDealers }" var="row">
				<option <c:if test="${dataMailcostForm.dataMailcost.mailPersonDealer==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
				</c:forEach>
			</select>
		</td>
		<td class="nameCol">金融机构：</td>
		<td class="codeCol">
			<input type="text" id="yjjrjg" readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>快递公司：</td>
		<td class="codeCol">
			<html:text property="dataMailcost.express" styleId="dataMailcost.express"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>预计金额：</td>
		<td class="codeCol">
			<html:text property="dataMailcost.money" styleId="dataMailcost.money"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol" style="width:300px"><font color="#FF0000">*</font>运输城市起：</td>
		<td class="codeCol">
			<html:text property="dataMailcost.transportBegin" styleId="dataMailcost.transportBegin"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>运输城市止：</td>
		<td class="codeCol">
			<html:text property="dataMailcost.transportEnd" styleId="dataMailcost.transportEnd"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>接收人部门：</td>
		<td class="codeCol" colspan="3">
			<select id="receiverType" style="width:150px;" name="dataMailcost.receiverType" onchange="changeReceiverType(this.value)" >
				<option value="-1">请选择</option>
				<c:forEach items="${receiverTypes }" var="row">
				<option <c:if test="${dataMailcostForm.dataMailcost.receiverType==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
				</c:forEach>
			</select>
		</td>
	</tr>
		<tr id="businessOfficertr" hidden="hidden">
			<td class="nameCol"><font color="#FF0000">*</font>业务专员：</td>
			<td class="codeCol" colspan="3">
				<select style="width:260px;" id="businessOfficer" name="dataMailcost.businessOfficer">
					<option value="-1">请选择</option>
					<c:forEach items="${businessOfficers }" var="row">
						<option value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr  id="bank" hidden="hidden">
			<td class="nameCol"><font color="#FF0000">*</font>金融机构：</td>
			<td class="codeCol" colspan="3" >
				<select id="one" name="dataMailcost.headBank">
						<option value="-1">请选择...</option>
				</select> <select id="two" name="dataMailcost.branch"> 
						<option value="-1">请选择...</option>
				</select> <select id="three" name="dataMailcost.subbranch">
						<option value="-1">请选择...</option>
				</select>
			</td>
		</tr>
		<tr id="dealer" hidden="hidden">
			<td class="nameCol"><font color="#FF0000">*</font>经销商：</td>
			<td class="codeCol" colspan="3">
				<select style="width:260px;"id="dealerId" name="dataMailcost.dealerId" >
					<option value="-1">请选择</option>
					<c:forEach items="${allDealers }" var="row">
						<option value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr id="supervisory" hidden="hidden">
			<td colspan="4" >
				<table width="100%">
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>监管员：</td>
						<td class="codeCol">
							<select style="width:260px;" id="supervisoryId" name="dataMailcost.supervisoryId"   onchange="changeDeliverer(this.value)">
								<option value="-1">请选择</option>
								<c:forEach items="${supervisorys }" var="row">
									<option value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
								</c:forEach>
							 </select>
						</td> 
						<td class="nameCol">工号：</td>
						<td class="codeCol">
							<input type="text" id="staffNo" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">经销商：</td>
						<td class="codeCol">
							<input type="text" id="dDealer" readonly="readonly"/>
						</td>
						<td class="nameCol">金融机构：</td>
						<td class="codeCol">
							<input type="text" id="dBank" readonly="readonly"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	<tr>
		<td class="nameCol">事件描述：</td>
		<td class="codeCol" colspan="3">
			<textarea name="dataMailcost.des" id="dataMailcost.des"></textarea>
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
<br/>
<div id="message" class="message" style="display:none"></div>

	</div>
</div>
</body>
</html>
