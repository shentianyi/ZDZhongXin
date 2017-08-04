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
	doLoad();
});
function doLoad(){
	initBank();
	var headBank=$("#headBank").val();
	var branch=$("#branch").val();
	var subbranch=$("#subbranch").val();
	if(headBank){
		$("#one").val(headBank).change()
		if(branch){
			$("#two").val(branch).change();
			if(subbranch)
				$("#three").val(subbranch).change();
		}
	}
	
	var mailPersonDealer = document.getElementById("mailPersonDealer").value;
	changeMailPersonDealer(mailPersonDealer);
	
	var supervisoryId=document.getElementsByName("dataMailcost.supervisoryId")[0].value;
	changeDeliverer(supervisoryId);
	
	var receiverType = document.getElementById("dataMailcost.receiverType").value;
	changeReceiverType(receiverType);
	
	
	
}


//执行返回列表操作
function doReturn(){
	location = "<url:context/>/dataMailcost.do?method=findList";
}
function agree(){
	$("#approvalState").val(1);
	doSave();
}

function disAgree(){
	$("#approvalState").val(2);
	doSave();
}
//执行保存操作
function doSave() {
	//对表单内容进行验证，包括对输入类型等限制方式
	if (validateMain("dataMailcostForm")) {
		//提交表单
		document.forms[0].submit();
	}
}
function changeMailPersonDealer(id){
	if(id==-1){
		/* document.forms[0].reset(); */
		return false;
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
		/* document.forms[0].reset(); */
		return false;
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
		$("#headBank").val($("#one").val());
		$("#branch").val($("#two").val());
		$("#subbranch").val($("#three").val());
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
	$.ajax({
		url:url,
		async:false,
		dataType:"jsonp",
		success:function(result){
			var data = result.data;
			$.each(data, function(i, item) {
				var option = "<option value="+item.id+">" + item.bankName
						+ "</option>";
				nextSelect.append(option);
			});
		}
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
		return false;
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
		return false;
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
		return false;
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
</script>
</head>
  
<body>
<div class="title">资料邮寄费用申请审批</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/dataMailcost" styleId="dataMailcostForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="approval">
<html:hidden property="dataMailcost.id" styleId="dataMailcost.id"/>
<html:hidden property="dataMailcost.promoter" styleId="dataMailcost.promoter"/>
<html:hidden property="dataMailcost.receiveid" styleId="dataMailcost.receiveid"/>
<html:hidden property="dataMailcost.nextApproval" styleId="dataMailcost.nextApproval"/>
<html:hidden property="dataMailcostquery.approvalState" styleId="approvalState"/>
<html:hidden property="dataMailcost.createuserid" styleId="dataMailcost.createuserid"/>
<html:hidden property="dataMailcost.createdate" styleId="dataMailcost.createdate"/>
<html:hidden property="dataMailcost.upduserid" styleId="dataMailcost.upduserid"/>
<html:hidden property="dataMailcost.upddate" styleId="dataMailcost.upddate"/>
<html:hidden property="dataMailcost.mailPerson" styleId="dataMailcost.mailPerson"/>
<html:hidden property="dataMailcost.mailPersonStaffNo" styleId="dataMailcost.mailPersonStaffNo"/>
<html:hidden property="dataMailcost.headBank" styleId="headBank"/>
<html:hidden property="dataMailcost.branch" styleId="branch"/>
<html:hidden property="dataMailcost.subbranch" styleId="subbranch"/>
<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>邮寄项目：</td>
		<td class="codeCol" style="width:450px" colspan="3" >
			<form:radios  property="dataMailcost.mailItems" styleId="dataMailcost.mailItems" collection="dataMailcostStateOptions" disabled="true"/>
		</td>
	</tr>
	<tr>
		<td class="nameCol" style="width:250px"><font color="#FF0000">*</font>发起日期：</td>
		<td class="codeCol" colspan="3">
			<form:calendar property="dataMailcost.fqDate" styleId="dataMailcost.fqDate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" disabled="true" />
		</td>
	</tr>
	<tr>
		<td class="nameCol">配件：</td>
		<td class="codeCol">
			<html:text property="dataMailcost.parts" styleId="dataMailcost.parts" disabled="true"></html:text>
		</td>
		<td class="nameCol">其它：</td>
		<td class="codeCol">
			<html:text property="dataMailcost.other" styleId="dataMailcost.other" disabled="true"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>邮寄人：</td>
		<td class="codeCol">
			<c:out value="${mailPerson}"/>
		</td>
		<td class="nameCol">工号：</td>
		<td class="codeCol">
			<html:text property="dataMailcost.mailPersonStaffNo" styleId="dataMailcost.mailPersonStaffNo" disabled="true"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">经销商：</td>
		<td class="codeCol">
			<form:select property="dataMailcost.mailPersonDealer" styleId="mailPersonDealer" onchange="changeMailPersonDealer(this.value)" disabled="true">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="allDealers" label="label" value="value" />
			</form:select>
		</td>
		<td class="nameCol">金融机构：</td>
		<td class="codeCol">
			<input type="text" id="yjjrjg" disabled="true"/>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>快递公司：</td>
		<td class="codeCol">
			<html:text property="dataMailcost.express" styleId="dataMailcost.express" disabled="true"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>预计金额：</td>
		<td class="codeCol">
			<html:text property="dataMailcost.money" styleId="dataMailcost.money" disabled="true"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol" style="width:300px"><font color="#FF0000">*</font>运输城市起：</td>
		<td class="codeCol">
			<html:text property="dataMailcost.transportBegin" styleId="dataMailcost.transportBegin" disabled="true"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>运输城市止：</td>
		<td class="codeCol">
			<html:text property="dataMailcost.transportEnd" styleId="dataMailcost.transportEnd" disabled="true"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">接收人部门：</td>
		<td class="codeCol" colspan="3">
			<form:select property="dataMailcost.receiverType" styleId="dataMailcost.receiverType" onchange="changeReceiverType(this.value)" disabled="true">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="receiverTypes" label="label" value="value" />
			</form:select>
		</td>
	</tr>
		<tr id="businessOfficertr" hidden="hidden">
			<td class="nameCol"><font color="#FF0000">*</font>业务专员：</td>
			<td class="codeCol" colspan="3">
				<form:select property="dataMailcost.businessOfficer" styleId="businessOfficer" disabled="true">
					<html:option value="-1">请选择</html:option>
					<html:optionsCollection name="businessOfficers" label="label" value="value" />
				</form:select>
			</td>
		</tr>
		<tr  id="bank" hidden="hidden">
			<td class="nameCol">金融机构：</td>
			<td class="codeCol" colspan="3" >
				<select id="one" disabled="true" >
						<option value="-1">请选择...</option>
				</select disabled="true">
				 <select id="two" disabled="true"> 
						<option value="-1">请选择...</option>
				</select > 
				<select id="three"disabled="true">
						<option value="-1">请选择...</option>
				</select>
			</td>
		</tr>
		<tr id="dealer" hidden="hidden">
			<td class="nameCol"><font color="#FF0000">*</font>经销商：</td>
			<td class="codeCol" colspan="3">
				<form:select property="dataMailcost.dealerId" styleId="dealerId" disabled="true">
					<html:option value="-1">请选择</html:option>
					<html:optionsCollection name="allDealers" label="label" value="value" />
				</form:select>
			</td>
		</tr>
		<tr id="supervisory" hidden="hidden">
			<td colspan="4" >
				<table width="100%">
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>监管员：</td>
						<td class="codeCol">
							<form:select property="dataMailcost.supervisoryId" styleId="supervisoryId" onchange="changeDeliverer(this.value)" disabled="true">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="supervisorys" label="label" value="value" />
							</form:select>
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
			<textarea readonly="readonly" name="dataMailcost.des" id="dataMailcost.des"><c:out value='${dataMailcostForm.dataMailcost.des }'/></textarea>
		</td>
	</tr>
	
<c:if test="${approvals!=null }">
	<tr class="formTitle">
		<td colspan="4">部门意见</td>
	</tr>
	
	<logic:iterate name="approvals" id="row" indexId="index">
		<tr>
		<td class="nameCol">第<c:out value='${index+1 }'/>步</td>
		<td class="nameCol" style="text-align: left;">
			<c:out value="${row.roleName }"/>：
			<c:out value="${row.userName }"/>于<fmt:formatDate value="${row.remarkDate }" pattern="yyyy-MM-dd"/>
		</td>
		<td class="codeCol" colspan="2">
			<c:if test="${row.approvalResult==1}">同意&nbsp;</c:if>
			<c:if test="${row.approvalResult==2}">不同意&nbsp;</c:if>
			<c:out value="${row.remark }"/>
		</td>
	</tr>
	</logic:iterate>
</c:if>

<tr>
	<td class="nameCol">审批意见：</td>
	<td class="codeCol" colspan="3">
		<textarea  name="dataMailcostquery.remark" ></textarea>
	</td>
</tr>

<tr class="formTableFoot">
	<td colspan="4" align="center">
		<button class="formButton" onClick="agree()">同&nbsp;意</button>&nbsp;
		<button class="formButton" onClick="disAgree()">不&nbsp;同&nbsp;意</button>&nbsp;
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
