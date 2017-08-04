<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="select.tld" prefix="select" %>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<link href="/css/base.css" rel="stylesheet" type="text/css" />
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
	var handoverNature=document.getElementsByName("handoverLog.handoverNature").value;
	setReason(handoverNature);
	var dealerId=document.getElementById("handoverLog.dealerId").value;
	changeDealer(dealerId);
	var deliverer=document.getElementById("handoverLog.deliverer").value;
	changeDeliverer(deliverer);
	var receiver=document.getElementById("handoverLog.receiver").value;
	changeReceiver(receiver);
	loadReceiverRepository(receiver);
}

//执行保存操作
function doSave(){
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("handoverLogForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/handoverLog.do?method=handoverLogPageList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
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
	var arr = document.getElementsByName("handoverLog.resignReason");
	if(status==1){
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
	var bank=dealer.bankName;
	var banks=bank.split("/");
	$("#headBank").val(banks[0]);
	$("#branch").val(banks[1]);
	$("#subbranch").val(banks[2]);
	$("#province").val(dealer.province);
	$("#city").val(dealer.city);
	$('#equipmentProvide').val(dealer.equipmentProvide);
}
function changeDeliverer(id) {
	if(id==-1){
		document.forms[0].reset();
		return;
	}
	var url = "../json/getSupervisorById.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setDeliverer(data[0]);
	});
}
function setDeliverer(supervisor){
	$("#idNumber").val(supervisor.idNumber);
	$("#gender").val(supervisor.gender);
	$("#staffNo").val(supervisor.staffNo);
	$("#entryDate").val(supervisor.entryTimeStr);
	$("#contactNumber").val(supervisor.selfTelephone);
}
function changeReceiver(id) {
	if(id==-1){
		document.forms[0].reset();
		return;
	}
	var url = "../json/getSupervisorById.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setReceiver(data[0]);
	});
}
function setReceiver(supervisor){
	$("#ridNumber").val(supervisor.idNumber);
	$("#rgender").val(supervisor.gender);
	$("#rstaffNo").val(supervisor.staffNo);
	$("#rentryDate").val(supervisor.entryTimeStr);
	$("#rcontactNumber").val(supervisor.selfTelephone);
	$("#rnativePlace").val(supervisor.nativePlace);
	$("#reducationBackground").val(supervisor.educationBackground);
	$("#rrecommenderName").val(supervisor.recommenderName);
	$("#rrecommendChannel").val(supervisor.recommendChannel);
	
}
function loadReceiverRepository(id) {
	if(id==-1){
		document.forms[0].reset();
		return;
	}
	var url = "../json/getRepositoryBySupervisorId.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setReceiverRepository(data[0]);
	});
}
function setReceiverRepository(repository){
	$("#isTrain").val(repository.isTrain);
	$("#trainer").val(repository.trainer);
	$("#trainingContent").val(repository.trainingContent);
}
function agree(){
	$("#approvalState").val(1);
	doSave();
}

function disAgree(){
	$("#approvalState").val(2);
	doSave();
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
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">监管员信息管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">交接记录管理</a>
             &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">监管员工作交接书</a>
         </span>
</div>
<br/>

<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/handoverLog" styleId="handoverLogForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="approval"/>
<input type="hidden" name="query.id" value="<c:out value='${handoverLogForm.handoverLog.id }'/>"/>
<html:hidden property="query.approvalState" styleId="approvalState"/>
<table class="formTable">
	<tr class="formTitle">
		<td colspan="4">监管员工作交接书</td>
	</tr>
	<tr>
		<td colspan="4"> 
			<table >
				<tr>
					<td class="nameCol">交接类型：</td>
					<td class="codeCol">
						<form:select property="handoverLog.handoverType" disabled="true"
							styleId="handoverLog.handoverType" >
							<html:optionsCollection name="handoverTypes" label="label" value="value" />
						</form:select></td>
					</td>
				</tr>
				<tr>
					<td class="nameCol">经销商：</td>
					<td class="codeCol">
						<form:select property="handoverLog.dealerId" disabled="true"
							styleId="handoverLog.dealerId" onchange="changeDealer(this.value)">
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
					<td class="nameCol">交付人：</td>
					<td class="codeCol">
						 <form:select property="handoverLog.deliverer"  disabled="true"
								styleId="handoverLog.deliverer" onchange="changeDeliverer(this.value)">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="supervisors" label="label"
									value="value" />
						</form:select>
					</td> 
					<td class="nameCol">联系方式：</td>
					<td class="codeCol"><input type="text"  id="contactNumber" disabled="disabled"/></td>
					<td class="nameCol">交接时间：</td>
					<td class="codeCol">
						<form:calendar property="handoverLog.handoverDate" styleId="handoverLog.handoverDate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" disabled="true"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol">交接原因：</td>
					<td class="nameCol"style="text-align: left;" colspan="5">
						<form:radios  property="handoverLog.handoverNature" collection="handoverNatures" styleId="handoverLog.handoverNature" disabled="true"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol">接收人：</td>
					<td class="codeCol">
						 <form:select property="handoverLog.receiver" disabled="true"
								styleId="handoverLog.receiver" onchange="changeReceiver(this.value)">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="supervisors" label="label"
									value="value" />
							</form:select>
					</td>
					<td class="nameCol">性别：</td>
					<td class="codeCol"><input type="text"  id="rgender" disabled="disabled"/></td>
					<td class="nameCol">身份证号：</td>
					<td class="codeCol"><input type="text"  id="ridNumber" disabled="disabled"/></td>
				</tr>
				 <tr>
					<td class="nameCol">籍贯：</td>
					<td class="codeCol"><input type="text"  id="rnativePlace" disabled="disabled"/></td>
				 	<td class="nameCol">学历：</td>
					<td class="codeCol"><input type="text"  id="reducationBackground" disabled="disabled"/></td>
					<td class="nameCol">联系方式：</td>
					<td class="codeCol"><input type="text"  id="rcontactNumber" disabled="disabled"/></td>
				</tr>
				 <tr>
					<td class="nameCol">推荐人：</td>
					<td class="codeCol"><input type="text"  id="rrecommenderName" disabled="disabled"/></td>
				 	<td class="nameCol">招聘渠道：</td>
					<td class="codeCol"><input type="text"  id="rrecommendChannel" disabled="disabled"/></td>
				</tr>
				<tr>
					<td class="nameCol">接收性质：</td>
					<td class="nameCol"style="text-align: left;" >
						<form:radios  property="handoverLog.receiveNature" collection="receiveNatures" styleId="handoverLog.receiveNature" disabled="true"/>
					</td>
					<td class="nameCol">接收后属性：</td>
					<td class="nameCol"style="text-align: left;" >
						<form:radios  property="handoverLog.afterHandoverNature" collection="afterHandoverNature" styleId="handoverLog.afterHandoverNature" disabled="true" />
					</td>
					<td class="nameCol">上岗时间：</td>
					<td class="codeCol">
						<form:calendar property="handoverLog.missionDate" styleId="handoverLog.missionDate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true"  disabled="true" />
					</td>
				</tr>
				<tr>
					
					<td class="nameCol">岗前是否进店培训：</td>
					<td class="codeCol" style="text-align: left;">
						<input type="text" id="isTrain" disabled="disabled" />
					</td>
					<td class="nameCol">考核人员姓名：</td>
					<td class="codeCol" style="text-align: left;">
						<input type="text" id="trainer" disabled="disabled" />
					</td>
				</tr>
				<tr>
					<td class="nameCol">培训内容：</td>
					<td class="codeCol"colspan="5" style="text-align: left;">
						<input type="text" id="trainingContent" disabled="disabled" />
					</td>
				</tr>
				<tr>
				</tr>
				<tr></tr>
			</table>
		</td>
	</tr>
	<tr class="formTitle">
		<td colspan="4">一、业务内容交接</td>
	</tr>
	<tr>
		<td class="nameCol" colspan="4" style="text-align: left;">1、在库车辆</td>
	</tr>
	<tr>
		<td colspan="4">
		<div class="dvScroll">
			<table  class="listTalbe" cellpadding="0" cellspacing="0">
				<thead>
					<tr class="title">
				      <td>序号</td>
				      <td><thumbpage:order cname="车辆型号" filedName="car_model"/></td> 
				      <td><thumbpage:order cname="颜色" filedName="color"/></td>
				      <td><thumbpage:order cname="车架号" filedName="vin"/></td>
				      <td><thumbpage:order cname="金额" filedName="money"/></td>
				      <td><thumbpage:order cname="合格证号" filedName="certificate_num"/></td>
				      <td><thumbpage:order cname="钥匙号" filedName="key_num"/></td>
				      <td><thumbpage:order cname="钥匙数量（把）" filedName="key_amount"/></td> 
				      <td><thumbpage:order cname="车辆状态" filedName="state"/></td>
				    </tr>
				</thead>
				<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
					<logic:iterate name="inStockCar" id="row" indexId="index">
						<tr class="listTr_a">
							<td align="center">&nbsp;<c:out value="${index+1}"/></td>
							<td>&nbsp;<c:out value="${row.car_model}" /></td>
							<td>&nbsp;<c:out value="${row.color}" /></td>
							<td>&nbsp;<c:out value="${row.vin}" /></td>
							<td>&nbsp;<c:out value="${row.money}" /></td>
							<td>&nbsp;<c:out value="${row.certificate_num}" /></td>
							<td>&nbsp;<c:out value="${row.key_num}" /></td>
							<td>&nbsp;<c:out value="${row.key_amount}" /></td>
							<td>&nbsp;
								<c:choose>  
								   <c:when test="${row.state==1}">在途</c:when>  
								   <c:when test="${row.state==2}">在库  </c:when>  
								   <c:when test="${row.state==3}">出库</c:when> 
								   <c:when test="${row.state==4}">移动</c:when>  
								   <c:when test="${row.state==5}">私自售卖</c:when>  
								   <c:when test="${row.state==6}">私自移动</c:when>  
								</c:choose>  
							</td>
						</tr>
					</logic:iterate>
				</tbody>  
			</table>
		</div>
		</td>
	</tr>
	<tr>
		<td colspan="7" align="right">
			合计：<c:out value="${zkCount}"/> 辆        合计金额：<c:out value="${zkMoney}" />
		</td>
	</tr>
	<tr>
		<td class="nameCol" colspan="4" style="text-align: left;">2、在途车辆</td>
	</tr>
	<tr>
	<td colspan="4">
		<div class="dvScroll">
			<table  class="listTalbe" cellpadding="0" cellspacing="0">
				<thead>
					<tr class="title">
				      <td>序号</td>
				       <td><thumbpage:order cname="车辆型号" filedName="car_model"/></td> 
				      <td><thumbpage:order cname="车架号" filedName="vin"/></td>
				      <td><thumbpage:order cname="金额" filedName="money"/></td>
				      <td><thumbpage:order cname="颜色" filedName="color"/></td>
				      <td><thumbpage:order cname="合格证号" filedName="certificate_num"/></td>
				      <td><thumbpage:order cname="钥匙数量（把）" filedName="key_amount"/></td> 
				      <td><thumbpage:order cname="车辆状态" filedName="state"/></td>
				    </tr>
				</thead>
				<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
					<logic:iterate name="onWayCar" id="row" indexId="index">
						<tr class="listTr_a">
							<td align="center">&nbsp;<c:out value="${index+1}"/></td>
							<td>&nbsp;<c:out value="${row.car_model}" /></td>
							<td>&nbsp;<c:out value="${row.vin}" /></td>
							<td>&nbsp;<c:out value="${row.money}" /></td>
							<td>&nbsp;<c:out value="${row.color}" /></td>
							<td>&nbsp;<c:out value="${row.certificate_num}" /></td>
							<td>&nbsp;<c:out value="${row.key_amount}" /></td>
							<td>&nbsp;
								<c:choose>  
								   <c:when test="${row.state==1}">在途</c:when>  
								   <c:when test="${row.state==2}">在库  </c:when>  
								   <c:when test="${row.state==3}">出库</c:when> 
								   <c:when test="${row.state==4}">移动</c:when>  
								   <c:when test="${row.state==5}">私自售卖</c:when>  
								   <c:when test="${row.state==6}">私自移动</c:when>  
								</c:choose>  
							</td>
						</tr>
					</logic:iterate>
				</tbody>  
			</table>
		</div>
		</td>
	</tr>
	<tr>
		<td colspan="7" align="right">
			合计：<c:out value="${ztCount}"/> 辆        合计金额：<c:out value="${ztMoney}" />
		</td>
	</tr>
	<tr>
		<td class="nameCol" colspan="4" style="text-align: left;">3、销售未还款车辆</td>
	</tr>
	<tr>
	<td colspan="4">
		<div class="dvScroll">
			<table  class="listTalbe" cellpadding="0" cellspacing="0">
				<thead>
					<tr class="title">
				      <td>序号</td>
				      <td><thumbpage:order cname="车辆型号" filedName="car_model"/></td> 
				      <td><thumbpage:order cname="颜色" filedName="color"/></td>
				      <td><thumbpage:order cname="车架号" filedName="vin"/></td>
				      <td><thumbpage:order cname="合格证号" filedName="certificate_num"/></td>
				      <td><thumbpage:order cname="钥匙号" filedName="key_num"/></td>
				      <td><thumbpage:order cname="钥匙数量（把）" filedName="key_amount"/></td> 
				      <td><thumbpage:order cname="车辆状态" filedName="state"/></td>
				    </tr>
				</thead>
				<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
					<logic:iterate name="sellNoPayCar" id="row" indexId="index">
						<tr class="listTr_a">
							<td align="center">&nbsp;<c:out value="${index+1}"/></td>
							<td>&nbsp;<c:out value="${row.car_model}" /></td>
							<td>&nbsp;<c:out value="${row.color}" /></td>
							<td>&nbsp;<c:out value="${row.vin}" /></td>
							<td>&nbsp;<c:out value="${row.certificate_num}" /></td>
							<td>&nbsp;<c:out value="${row.key_num}" /></td>
							<td>&nbsp;<c:out value="${row.key_amount}" /></td>
							<td>&nbsp;
								<c:choose>  
								   <c:when test="${row.state==1}">在途</c:when>  
								   <c:when test="${row.state==2}">在库  </c:when>  
								   <c:when test="${row.state==3}">出库</c:when> 
								   <c:when test="${row.state==4}">移动</c:when> 
								   <c:when test="${row.state==5}">私自售卖</c:when>  
								   <c:when test="${row.state==6}">私自移动</c:when>   
								</c:choose>  
							</td>
						</tr>
					</logic:iterate>
				</tbody>  
			</table>
		</div>
	</td>
	</tr>
	<tr>
		<td class="nameCol" colspan="7" style="text-align: left;">4、私自售卖车辆</td>
	</tr>
	<tr>
	<td colspan="4">
		<div class="dvScroll">
			<table  class="listTalbe" cellpadding="0" cellspacing="0">
				<thead>
					<tr class="title">
				      <td>序号</td>
				      <td><thumbpage:order cname="车辆型号" filedName="car_model"/></td> 
				      <td><thumbpage:order cname="颜色" filedName="color"/></td>
				      <td><thumbpage:order cname="车架号" filedName="vin"/></td>
				      <td><thumbpage:order cname="合格证号" filedName="certificate_num"/></td>
				      <td><thumbpage:order cname="钥匙号" filedName="key_num"/></td>
				      <td><thumbpage:order cname="钥匙数量（把）" filedName="key_amount"/></td> 
				      <td><thumbpage:order cname="车辆状态" filedName="state"/></td>
				    </tr>
				</thead>
				<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
					<logic:iterate name="privateSellCar" id="row" indexId="index">
						<tr class="listTr_a">
							<td align="center">&nbsp;<c:out value="${index+1}"/></td>
							<td>&nbsp;<c:out value="${row.car_model}" /></td>
							<td>&nbsp;<c:out value="${row.color}" /></td>
							<td>&nbsp;<c:out value="${row.vin}" /></td>
							<td>&nbsp;<c:out value="${row.certificate_num}" /></td>
							<td>&nbsp;<c:out value="${row.key_num}" /></td>
							<td>&nbsp;<c:out value="${row.key_amount}" /></td>
							<td>&nbsp;
								<c:choose>  
								   <c:when test="${row.state==1}">在途</c:when>  
								   <c:when test="${row.state==2}">在库  </c:when>  
								   <c:when test="${row.state==3}">出库</c:when> 
								   <c:when test="${row.state==4}">移动</c:when>
								   <c:when test="${row.state==5}">私自售卖</c:when>  
								   <c:when test="${row.state==6}">私自移动</c:when>    
								</c:choose>  
							</td>
						</tr>
					</logic:iterate>
				</tbody>  
			</table>
		</div>
	</td>
		
	</tr> 
	<tr>
		<td class="nameCol" colspan="7" style="text-align: left;">5、私自移动车辆</td>
	</tr>
	<tr>
		<td colspan="4">
			<div class="dvScroll">
			<table  class="listTalbe" cellpadding="0" cellspacing="0">
				<thead>
					<tr class="title">
				      <td>序号</td>
				      <td><thumbpage:order cname="车辆型号" filedName="car_model"/></td> 
				      <td><thumbpage:order cname="颜色" filedName="color"/></td>
				      <td><thumbpage:order cname="车架号" filedName="vin"/></td>
				      <td><thumbpage:order cname="合格证号" filedName="certificate_num"/></td>
				      <td><thumbpage:order cname="钥匙号" filedName="key_num"/></td>
				      <td><thumbpage:order cname="钥匙数量（把）" filedName="key_amount"/></td> 
				      <td><thumbpage:order cname="车辆状态" filedName="state"/></td>
				    </tr>
				</thead>
				<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
					<logic:iterate name="privateMoveCar" id="row" indexId="index">
						<tr class="listTr_a">
							<td align="center">&nbsp;<c:out value="${index+1}"/></td>
							<td>&nbsp;<c:out value="${row.car_model}" /></td>
							<td>&nbsp;<c:out value="${row.color}" /></td>
							<td>&nbsp;<c:out value="${row.vin}" /></td>
							<td>&nbsp;<c:out value="${row.certificate_num}" /></td>
							<td>&nbsp;<c:out value="${row.key_num}" /></td>
							<td>&nbsp;<c:out value="${row.key_amount}" /></td>
							<td>&nbsp;
								<c:choose>  
								   <c:when test="${row.state==1}">在途</c:when>  
								   <c:when test="${row.state==2}">在库  </c:when>  
								   <c:when test="${row.state==3}">出库</c:when> 
								   <c:when test="${row.state==4}">移动</c:when>
								   <c:when test="${row.state==5}">私自售卖</c:when>  
								   <c:when test="${row.state==6}">私自移动</c:when>    
								</c:choose>  
							</td>
						</tr>
					</logic:iterate>
				</tbody>  
			</table>
		</div>
		</td>
	</tr> 
	<tr>
		<td colspan="4"> 
			<table style="width:100%">
				<tr >
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
				</tr>
				<tr >
					<td class="nameCol" colspan="8" style="text-align: left;">6、电子文本资料</td>
				</tr>
				<tr>
					<td class="nameCol" >电子台账：</td>
					<td class="codeCol" colspan="3" >
						<html:text style="width:350px" property="eText.electronBill" styleId="eText.electronBill" disabled="true"/>
					</td>
					<td class="nameCol">周库存核对清单：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="eText.weekBillAmount" styleId="eText.weekBillAmount" disabled="true"/> 份，
						时间：自     <form:calendar style="width:80px" property="eText.weekBillStartTime" styleId="eText.weekBillStartTime" disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="eText.weekBillEndTime" styleId="eText.weekBillEndTime"  disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
				</tr>
				<tr>
					<td class="nameCol">接车明细：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="eText.receiveCarDetailAmount" styleId="eText.receiveCarDetailAmount"disabled="true"/> 份，
						时间：自     <form:calendar style="width:80px" property="eText.receiveCarDetailStartTime" styleId="eText.receiveCarDetailStartTime" disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="eText.receiveCarDetailEndTime" styleId="eText.receiveCarDetailEndTime" disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
					<td class="nameCol" >月库存核对清单：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="eText.monthBillAmount" styleId="eText.monthBillAmount" disabled="true"/> 份，
						时间：自     <form:calendar style="width:80px" property="eText.monthBillStartTime" styleId="eText.monthBillStartTime" disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="eText.monthBillEndTime" styleId="eText.monthBillEndTime" disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
				</tr>
				<tr>
					<td class="nameCol">申领释放书：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="eText.applyFreeBookAmount" styleId="eText.applyFreeBookAmount" disabled="true"/> 份，
						时间：自     <form:calendar style="width:80px" property="eText.applyFreeBookStartTime" styleId="eText.applyFreeBookStartTime" disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="eText.applyFreeBookEndTime" styleId="eText.applyFreeBookEndTime"  disabled="true"pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
					<td class="nameCol" >监管确认书：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="eText.confirmationAmount" styleId="eText.confirmationAmount" disabled="true"/> 份，
						时间：自     <form:calendar style="width:80px" property="eText.confirmationStartTime" styleId="eText.confirmationStartTime"disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="eText.confirmationEndTime" styleId="eText.confirmationEndTime"disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
				</tr>
				<tr>
					<td class="nameCol">二网申请：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="eText.towApplyAmount" styleId="eText.towApplyAmount"disabled="true"/> 份，
						时间：自     <form:calendar style="width:80px" property="eText.towApplyStartTime" styleId="eText.towApplyStartTime"disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="eText.towApplyEndTime" styleId="eText.towApplyEndTime"disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
					<td class="nameCol" >其他：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="eText.otherAmount" styleId="eText.otherAmount"disabled="true"/> 份，
						时间：自     <form:calendar style="width:80px" property="eText.otherStartTime" styleId="eText.otherStartTime" disabled="true"pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="eText.otherEndTime" styleId="eText.otherEndTime" disabled="true"pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
				</tr>
				<tr>
					<td class="nameCol" >备注：</td>
					<td class="codeCol" colspan="7">
						<html:text style="width:800px"disabled="true" property="eText.remark" styleId="eText.remark"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4"> 
			<table >
				<tr >
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
				</tr>
				<tr >
					<td class="nameCol" colspan="8" style="text-align: left;">7、纸质文本资料</td>
				</tr>
				<tr>
					<td class="nameCol" >手工台账：</td>
					<td class="codeCol" colspan="3" >
						共  <html:text style="width:30px" property="pText.paperBillAmount" styleId="pText.paperBillAmount"disabled="true"/> 份，
						时间：自     <form:calendar style="width:80px" property="pText.paperBillStartTime" styleId="pText.paperBillStartTime"disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="pText.paperBillEndTime" styleId="pText.paperBillEndTime"disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
					<td class="nameCol">二网申请：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="pText.towApplyAmount" styleId="pText.towApplyAmount"disabled="true"/> 份，
						时间：自     <form:calendar style="width:80px" property="pText.towApplyStartTime" styleId="pText.towApplyStartTime"disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="pText.towApplyEndTime" styleId="pText.towApplyEndTime"disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
				</tr>
				<tr>
					<td class="nameCol">换证申请：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="pText.changeApplyAmount" styleId="pText.changeApplyAmount"disabled="true"/> 份，
						时间：自     <form:calendar style="width:80px" property="pText.changeApplyStartTime" styleId="pText.changeApplyStartTime"disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="pText.changeApplyEndTime" styleId="pText.changeApplyEndTime"disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
					<td class="nameCol" >监管确认书：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="pText.confirmationAmount" styleId="pText.confirmationAmount"disabled="true"/> 份，
						时间：自     <form:calendar style="width:80px" property="pText.confirmationStartTime" styleId="pText.confirmationStartTime"disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="pText.confirmationEndTime" styleId="pText.confirmationEndTime"disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
				</tr>
				<tr>
					<td class="nameCol">申领释放书：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="pText.applyFreeBookAmount" styleId="pText.applyFreeBookAmount"disabled="true"/> 份，
						时间：自     <form:calendar style="width:80px" property="pText.applyFreeBookStartTime" styleId="pText.applyFreeBookStartTime"disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="pText.applyFreeBookEndTime" styleId="pText.applyFreeBookEndTime"disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
					<td class="nameCol">授权书(店方)</td>
					<td class="nameCol"style="text-align: left;" colspan="3" >
						<form:radios  property="pText.authorization" collection="haveorno" styleId="pText.authorization" disabled="true"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol">周库存核对清单：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="pText.weekBillAmount" styleId="pText.weekBillAmount"disabled="true"/> 份，
						时间：自     <form:calendar style="width:80px" property="pText.weekBillStartTime" styleId="pText.weekBillStartTime"disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="pText.weekBillEndTime" styleId="pText.weekBillEndTime"disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
					<td class="nameCol">经销商告知函</td>
					<td class="nameCol" style="text-align: left;" colspan="3" >
						<form:radios  property="pText.informLetter" collection="haveorno"disabled="true" styleId="pText.informLetter" />
					</td>
				</tr>
				<tr>
					<td class="nameCol" >月库存核对清单：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="pText.monthBillAmount" styleId="pText.monthBillAmount"disabled="true"/> 份，
						时间：自     <form:calendar style="width:80px" property="pText.monthBillStartTime" styleId="pText.monthBillStartTime"disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="pText.monthBillEndTime" styleId="pText.monthBillEndTime"disabled="true" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
					<td class="nameCol">合格证、车钥匙：</td>
					<td class="nameCol" style="text-align: left;" colspan="3" >
						数量：<form:radios  property="pText.keyAmount" collection="matchorno" styleId="pText.keyAmount" disabled="true"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol" >其他：</td>
					<td class="codeCol" colspan="7">
						共  <html:text style="width:30px" property="pText.otherAmount" styleId="pText.otherAmount"disabled="true"/> 份，
						时间：自     <form:calendar style="width:80px" property="pText.otherStartTime" styleId="pText.otherStartTime" disabled="true"pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="pText.otherEndTime" styleId="pText.otherEndTime" disabled="true"pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr >
		<td class="nameCol" colspan="4" style="text-align: left;">8、其他资料交接(未结清汇票)</td>
	</tr>
	<tr>
		<td colspan="4">
			<div class="dvScroll">
				<table  class="listTalbe" cellpadding="0" cellspacing="0">
					<thead>
						<tr class="title">
					      <td>序号</td>
					      <td><thumbpage:order cname="票号" filedName="draft_num"/></td> 
					      <td><thumbpage:order cname="开票日期" filedName="billing_date"/></td>
					      <td><thumbpage:order cname="到期日期" filedName="due_date"/></td>
					      <td><thumbpage:order cname="票面金额" filedName="billing_money"/></td>
					      <td><thumbpage:order cname="押车金额" filedName="mortgagecar_money"/></td>
					      <td><thumbpage:order cname="未押车金额" filedName="payment_money"/></td> 
					    </tr>
					</thead>
					<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
						<logic:iterate name="wjqdrafts" id="row" indexId="index">
							<tr class="listTr_a">
								<td align="center">&nbsp;<c:out value="${index+1}"/></td>
								<td>&nbsp;<c:out value="${row.draft_num}" /></td>
								<td>&nbsp;<c:out value="${row.billing_date}" /></td>
								<td>&nbsp;<c:out value="${row.due_date}" /></td>
								<td>&nbsp;<c:out value="${row.billing_money}" /></td>
								<td class="t-td"><select:draft draftid="${row.id}" idtype="yycje"/></td>
								<td class="t-td"><select:draft draftid="${row.id}" idtype="wycje"/></td>
							</tr>
						</logic:iterate>
					</tbody>  
				</table>
			</div>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<table style="width:100%">
				<tr>
					<td class="nameCol"></td>
					<td class="nameCol"></td>
					<td class="nameCol"></td>
					<td class="nameCol"></td>
					<td class="nameCol"></td>
				</tr>
				<tr>
					<td class="nameCol">情况说明：</td>
					<td class="codeCol" colspan="4">
						<html:textarea property="OData.situationExplain"disabled="true" styleId="OData.situationExplain" ></html:textarea>
					</td>
				</tr>
				<tr>
					<td class="nameCol">特殊操作说明：</td>
					<td class="codeCol"colspan="4">
						<html:textarea property="OData.specialOperation"disabled="true" styleId="OData.specialOperation" ></html:textarea>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<table style="width:100%">
				<tr class="formTitle">
					<td colspan="4">二、办公设备及用品交接</td>
				</tr>
				<tr>
					<td class="nameCol">项目</td>
					<td align="center" colspan="3">1.本公司交接内容</td>
				</tr>
				<tr>
					<td class="nameCol" rowspan="6">笔记本电脑</td>
					<td class="codeCol">品牌：<input style="width:100px" type="text" name="dn.brand" disabled="true" value="<c:out value="${dn.brand}"/>"/></td>
					<td class="codeCol">型号：<input style="width:100px" type="text" name="dn.model" disabled="true"  value="<c:out value="${dn.model}"/>"/></td>
					<td class="codeCol">资产编号：<input style="width:100px" type="text" name="dn.asset_num" disabled="true"  value="<c:out value="${dn.asset_num}"/>"/></td>
				</tr>
				<tr>
					<td class="codeCol" colspan="3">使用性能：<form:radios  property="officeEquipment.computerProperty" disabled="disabled" collection="nomalorno" styleId="officeEquipment.computerProperty" />
						  <html:text style="width:200px" property="officeEquipment.computerPropertyReason" disabled="true"styleId="officeEquipment.computerPropertyReason" />
					</td>
				</tr>
				<tr>
					<td class="codeCol" colspan="3">开机密码：<form:radios  property="officeEquipment.computerOnPassword" disabled="disabled" collection="informorno" styleId="officeEquipment.computerOnPassword" />
						  <html:text style="width:200px" property="officeEquipment.computerPassword" disabled="true" styleId="officeEquipment.computerPassword" />
					</td>
				</tr>
				<tr>
					<td class="codeCol" colspan="3">外观：<form:radios  property="officeEquipment.computerAppearance" disabled="disabled" collection="breakorno" styleId="officeEquipment.computerAppearance" /></td>
				</tr>
				<tr>
					<td class="codeCol">鼠标</td>
					<td class="codeCol" colspan="2">使用性能：<form:radios  property="officeEquipment.mouseProperty" disabled="disabled" collection="nomalorno" styleId="officeEquipment.mouseProperty" />
					</td>
				</tr>
				<tr>
					<td class="codeCol">摄像头</td>
					<td class="codeCol" colspan="2">使用性能：<form:radios  property="officeEquipment.cameraProperty" disabled="disabled" collection="nomalorno" styleId="officeEquipment.cameraProperty" />
					</td>
				</tr>
				<tr>
					<td class="nameCol" rowspan="6">保险柜</td>
					<td class="codeCol">品牌：<input style="width:100px" type="text" name="bxg.brand" disabled="true"value="<c:out value="${bxg.brand}"/>"/></td>
					<td class="codeCol">型号：<input style="width:100px" type="text" name="bxg.model" disabled="true"value="<c:out value="${bxg.model}"/>"/></td>
					<td class="codeCol">资产编号：<input style="width:100px" type="text" name="bxg.asset_num" disabled="true"value="<c:out value="${bxg.asset_num}"/>"/></td>
				</tr>
				<tr>
					<td class="codeCol" colspan="3">使用性能：<form:radios  disabled="disabled" property="officeEquipment.safetyBoxProperty" collection="nomalorno" styleId="officeEquipment.safetyBoxProperty" />
					 	<html:text style="width:200px" disabled="true" property="officeEquipment.safetyBoxPropertyReason" styleId="officeEquipment.safetyBoxPropertyReason" />
					</td>
				</tr>
				<tr>
					<td class="codeCol" colspan="3">外观：<form:radios  disabled="disabled" property="officeEquipment.safetyBoxAppearance" collection="breakorno" styleId="officeEquipment.safetyBoxAppearance" /></td>
				</tr>
				<tr>
					<td class="codeCol" colspan="3">密码：<form:radios  disabled="disabled" property="officeEquipment.safetyBoxPasswordStatus" collection="informorno" styleId="officeEquipment.safetyBoxPasswordStatus" />
						  <html:text style="width:200px" disabled="true" property="officeEquipment.safetyBoxPassword" styleId="officeEquipment.safetyBoxPassword" />
					</td>
				</tr>
				<tr>
					<td class="codeCol" colspan="3">钥匙（一套四把）：<form:radios disabled="disabled"  property="officeEquipment.keyAmount" collection="keyAmountorno" styleId="officeEquipment.keyAmount" />
					</td>
				</tr>
				<tr>
					<td class="codeCol" colspan="3">其他情况说明：<html:text disabled="true" style="width:500px" property="officeEquipment.situationExplain" styleId="officeEquipment.situationExplain" />
					</td>
				</tr>
				<tr>
					<td class="nameCol">耳麦</td>
					<td class="codeCol" colspan="3">使用性能：<form:radios  disabled="disabled" property="officeEquipment.headsetProperty" collection="nomalorno" styleId="officeEquipment.headsetProperty" />
					</td>
				</tr>
				<tr>
					<td class="nameCol">监管员工牌</td>
					<td class="codeCol" colspan="3"><form:radios  disabled="disabled" property="officeEquipment.haveCard" collection="haveorno" styleId="officeEquipment.haveCard" />
					</td>
				</tr>
				<tr>
					<td class="nameCol">工作QQ</td>
					<td class="codeCol" colspan="3">
					         号码：<html:text style="width:150px" disabled="true" property="officeEquipment.qqNumber" styleId="officeEquipment.qqNumber" />
					        密码：<html:text style="width:150px"  disabled="true"property="officeEquipment.qqPassword" styleId="officeEquipment.qqPassword" />      
					</td>
				</tr>
				<tr>
					<td  align="center" colspan="4">2.店方相关交接</td>
				</tr>
				<tr>
					<td class="nameCol">借用物品</td>
					<td class="codeCol" colspan="3"><form:radios disabled="disabled"  property="officeEquipment.borrowGoods" collection="haveorno" styleId="officeEquipment.borrowGoods" />
						 <html:text style="width:350px" disabled="true" property="officeEquipment.borrowGoodsRemark" styleId="officeEquipment.borrowGoodsRemark" />
					</td>
				</tr>
				<tr>
					<td class="nameCol">钱款</td>
					<td class="codeCol" colspan="3"><form:radios  disabled="disabled" property="officeEquipment.moneyStatus" collection="haveornoDebt" styleId="officeEquipment.moneyStatus" />
						 明细：<html:text style="width:350px" disabled="true" property="officeEquipment.moneyRemark" styleId="officeEquipment.moneyRemark" />
					</td>
				</tr>
				<tr>
					<td class="nameCol">其他</td>
					<td class="codeCol" colspan="3">
						<html:text style="width:500px"  disabled="true"property="officeEquipment.merchantRemark" styleId="officeEquipment.merchantRemark" />
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<table style="width:100%">
				<c:if test="${not empty approvals}">
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
			</table>
		</td>
	
	</tr>
	<tr>
			<td colspan="4">
				<table class="formTable">
					<tr class="formTitle">
					<td colspan="4">三、交接书图片</td>
					</tr>
				<tr>
					<td class="nameCol">图片一：</td>
					<td class="codeCol">
						<a href="javascript:showDiv('<c:out value='${picId1}' />');">
							<img alt="" src="/showImg.do?method=showImg&filePath=<c:out value='${fixedpath1}'/>" height="80" width="120"/>
						</a>
					</td>
					<td class="nameCol">图片二：</td>
					<td class="codeCol">
						<a href="javascript:showDiv('<c:out value='${picId2}' />');">
							<img alt="" src="/showImg.do?method=showImg&filePath=<c:out value='${fixedpath2}'/>" height="80" width="120"/>
						</a>
					</td>
				</tr>
				<tr>
					<td class="nameCol">图片三：</td>
					<td class="codeCol">
						<a href="javascript:showDiv('<c:out value='${picId3}' />');">
							<img alt="" src="/showImg.do?method=showImg&filePath=<c:out value='${fixedpath3}'/>" height="80" width="120"/>
						</a>
					</td>
					<td class="nameCol">图片四：</td>
					<td class="codeCol">
						<a href="javascript:showDiv('<c:out value='${picId4}' />');">
							<img alt="" src="/showImg.do?method=showImg&filePath=<c:out value='${fixedpath4}'/>" height="80" width="120"/>
						</a>
					</td>
				</tr>
				<tr>
					<td class="nameCol">图片五：</td>
					<td class="codeCol">
						<a href="javascript:showDiv('<c:out value='${picId5}' />');">
							<img alt="" src="/showImg.do?method=showImg&filePath=<c:out value='${fixedpath5}'/>" height="80" width="120"/>
						</a>
					</td>
					<td class="nameCol">图片六：</td>
					<td class="codeCol">
						<a href="javascript:showDiv('<c:out value='${picId6}' />');">
							<img alt="" src="/showImg.do?method=showImg&filePath=<c:out value='${fixedpath6}'/>" height="80" width="120"/>
						</a>
					</td>
				</tr>
				<tr>
					<td class="nameCol">图片七：</td>
					<td class="codeCol">
						<a href="javascript:showDiv('<c:out value='${picId7}' />');">
							<img alt="" src="/showImg.do?method=showImg&filePath=<c:out value='${fixedpath7}'/>" height="80" width="120"/>
						</a>
					</td>
					<td class="nameCol">图片八：</td>
					<td class="codeCol">
						<a href="javascript:showDiv('<c:out value='${picId8}' />');">
							<img alt="" src="/showImg.do?method=showImg&filePath=<c:out value='${fixedpath8}'/>" height="80" width="120"/>
						</a>
					</td>
				</tr>
				<tr>
					<td class="nameCol">图片九：</td>
					<td class="codeCol">
						<a href="javascript:showDiv('<c:out value='${picId9}' />');">
							<img alt="" src="/showImg.do?method=showImg&filePath=<c:out value='${fixedpath9}'/>" height="80" width="120"/>
						</a>
					</td>
					<td class="nameCol">图片十：</td>
					<td class="codeCol">
						<a href="javascript:showDiv('<c:out value='${picId10}' />');">
							<img alt="" src="/showImg.do?method=showImg&filePath=<c:out value='${fixedpath10}'/>" height="80" width="120"/>
						</a>
					</td>
				</tr>
				<tr>
					<td class="nameCol">备注：</td>
					<td class="codeCol">
						<html:textarea property="hpic.remark" styleId="hpic.remark" readonly="true"></html:textarea>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	
	<tr>
		<td colspan="4">
			<table>
				<tr>
					<td class="nameCol">审批意见：</td>
					<td class="codeCol" colspan="3"><textarea style="width:700px" name="query.remark"></textarea></td>
				</tr>
			
				<tr class="formTableFoot">
					<td colspan="4" align="center">
						<button class="formButton" onClick="agree()">同&nbsp;意</button>&nbsp;
						<button class="formButton" onClick="disAgree()">不&nbsp;同&nbsp;意</button>&nbsp;
						<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
					</td>
				</tr>
			</table>
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
