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
<!-- easyui -->
<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" /> 
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>

<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script src="/pagejs/projectCirculation.js"></script>

<script>
	$(function(){
		$("[name='mpc\\.isNeedHandover']").click(function(){
			if(this.value==1){
				$("#jiaojie").show();
			}else if(this.value==2){
				$("#jiaojie").hide();
			}
		});
		
		init();
		
		var errorMsg ="<%= request.getAttribute("errorMessage")%>";
		if(errorMsg!=null&&errorMsg!=""&&errorMsg!="null"){
			alert(errorMsg);
		}
		
	});

	

	var managers=null;
	//页面初始化函数
	function doLoad(){
		//显示提示信息
		showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
		initBank();
		initAddress();
	}

	function initBank() {
		loadSelect(-1, $("#one"));

		$("#one").change(function() {
			$("#two option:gt(0)").remove();
			$("#three option:gt(0)").remove();
			var id = this.value;
			if (id>0) {
				loadSelect(id, $("#two"));
				setBank(id);
			}else{
				setBank("");
				
				//清空下级银行内容
				$("#two").val(-1);
				$("#three").val(-1);
			}
			setManagerByBankId(id);
		});
		$("#two").change(function() {
			$("#three option:gt(0)").remove();
			var id = this.value;
			if (id>0) {
				loadSelect(id, $("#three"));
				setBank(id);
			}else{
				setBank($("#one").val());
				$("#three").val(-1);
			}
			setManagerByBankId(id);
		});
		$("#three").change(function(){
			var id = this.value;
			if (id>0) {
				setBank(id);
			}else{
				setBank($("#two").val());
			}
			setManagerByBankId(id);
		});
		
		$("#managerId").change(function(){
			$("#managerPhone").val("");
			var id = this.value;
			if(id>0){
				for(var i = 0;i<managers.length;i++){
					if(id == managers[i].id){
						$("#managerPhone").val(managers[i].managerPhone);
						break;
					}
				}
			}
		});
	}

	

	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/market/projectCirculationForm.do?method=findList";
	}

	//执行表单重置操作
	function doReset() {
		document.forms[0].reset();
	}

	function loadSelect(id, nextSelect) {
		var url = "../json/getBankChildById.do?method=findChildListById&callback=?&bankQuery.id="
				+ id;
		$.getJSON(url, function(result) {
			var data = result.data;
			$.each(data, function(i, item) {
				var option = "<option value="+item.id+">" + item.bankName
						+ "</option>";
				nextSelect.append(option);
			});
		});
	}
	
	function setBank(id,name){
		if(id!=-1){
			$("#bankId").val(id);
		}else{
			$("#bankId").val("");
		}
	}
	
	function initAddress() {
		loadAddress(0, $("#province"));

		$("#province").change(function() {
			$("#city option:gt(0)").remove();
			$("#district option:gt(0)").remove();
			var id = this.value;
			if (id>0) {
				loadAddress(id, $("#city"));
			}else{
				//清空下级地区
				$("#city").val(-1);
				$("#district").val(-1);
			}
		});
		$("#city").change(function() {
			$("#district option:gt(0)").remove();
			var id = this.value;
			if (id>0) {
				loadAddress(id, $("#district"));
			}else{
				$("#district").val(-1);
			}
		});
		$("#district").change(function(){
			
		});
	}
	
	function loadAddress(id,nextSelect){
		if(id<0)
			id=0;
		var url = "../json/findRegionByParentId.do?callback=?&parentId="+id;
		$.getJSON(url, function(result) {
			var province = result.data;
			$.each(province, function(i, item) {
				var option = "<option value="+province[i].id+">" + province[i].name+ "</option>";
				nextSelect.append(option);
			});
		});
	}

	
	function setManagerByBankId(bankid){
		$("#managerId").html("");
		$("#managerPhone").val("");
		if(bankid>0){
			var url = "../json/bankManagerByBankId.do?callback=?&bankId="+bankid;
			$.getJSON(url, function(result) {
				var data = result.data;
				managers = data;
				console.info(data);
				
				for(var i = 0;i<data.length;i++){
					$("#managerId").append("<option value='"+data[i].id+"'>"+data[i].manager+"</option>");
					if(i==0){
						$("#managerPhone").val(managers[i].managerPhone);
					}
				}
			});
		}
	}
	

	
	function isBind(value){
		if(value==2){
			$("#bindShop1").hide();
			$("#bindShop2").hide();
		}else{
			$("#bindShop1").show();
			$("#bindShop2").show();
		}
	}
	
	function bindShop1change(id){
		if(id<=0){
			$("#bindShop1BankName").val("");
			return false;
		}
		var bs1 = getDealer(id);
		if(bs1){
			$("#bindShop1BankName").val(bs1.bankName);
		}
	}
	function bindShop2change(id){
		if(id<=0){
			$("#bindShop2BankName").val("");
			return false;
		}
		var bs2 = getDealer(id);
		if(bs2){
			$("#bindShop2BankName").val(bs2.bankName);
		}
	}
	function addressAdd(obj){
		if(obj.value)
			return;
		var sheng = $("#province option:selected");
		var shi = $("#city option:selected");
		var qu = $("#district option:selected");
		var address="";
		if(sheng.val()!=-1)
			address+=sheng.html();
		if(shi.val()!=-1)
			address+=shi.html();
		if(qu.val()!=-1)
			address+=qu.html();
		obj.value=address;
	}
	
	$(function(){
		$("[name=mpc\\.isSignAnAgreement]").click(function(){
			if(this.value==2){
				$(".agreementIsRecovery").hide();
			}else{
				$(".agreementIsRecovery").show();
			}
		});
	});
	
</script>
<style type="text/css">
	select{min-width:100px;}
	
	.formTable2{width: 100%;}
.formTable2 tr{
    width: 100%; height: 40px; border: 1px solid #eee;
}
.formTable2 td,.formTable2 td.nameC{
    width: 16.6%;
    min-width:145px;
}
.formTable2 td.nameC{
text-align: right;
}
.formTable2 td.nameC, .formTable2 td.codeC{
    border-bottom: solid 1px #eee;
    border-right: solid 1px #eee; 
    padding-bottom: 10px;
    padding-top: 10px;  
}
</style>
</head>
<body onLoad="doLoad()">

	<div class="title">新增项目进驻流转单</div>
	<!-- <hr size="1"> -->
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/market/projectCirculationForm" styleId="mpcForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="add" />
				<input type="hidden" name="mpc.bankId" id="bankId">

				<table class="formTable2">

					<tr class="formTitle">
						<td colspan="4">一、新进店信息</td>
					</tr>
					<tr>
						<td class="nameC"><font color="#FF0000">*</font>经销店名称：</td>
						<td class="codeC" colspan="3"><html:text style="width:50%;" property="mpc.dealerName" styleId="dealerName" ></html:text></td>
					</tr>
					<tr>
						<td class="nameC">店方联系人：</td>
						<td class="codeC"><html:text property="mpc.dealerPerson"></html:text></td>
						<td class="nameC">联系人电话：</td>
						<td class="codeC"><html:text property="mpc.dealerPhone"></html:text></td>
					</tr>
					<tr>
						<td class="nameC"><font color="#FF0000">*</font>金融机构：</td>
						<td class="codeC" colspan="3">
							<select id="one">
									<option value="-1">请选择...</option>
							</select> <select id="two">
									<option value="-1">请选择...</option>
							</select> <select id="three">
									<option value="-1">请选择...</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="nameC">客户经理：</td>
						<td class="codeC">
							<select name="mpc.bankManagerId" id="managerId" >
							</select>
						</td>
						<td class="nameC">联系电话</td>
						<td class="codeC">
							<input type="text" id="managerPhone" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameC"><font color="#FF0000">*</font>品牌：</td>
						<td class="codeC">
							<select name="mpc.brandId" style="width:50%" id="pinpai">
								<c:forEach items="${brands }" var="row">
									<option value="<c:out value='${row.value}'/>"><c:out value="${row.label }"/></option>
								</c:forEach>
							</select>
						</td>
						<td class="nameC"><font color="#FF0000">*</font>协议监管模式：</td>
						<td class="codeC">
							<form:radios styleId="supervisionMode"
								property="mpc.supervisionMode" collection="supervisionModes"></form:radios>
						</td>
					</tr>
					<tr>
						<td class="nameC">合作模式：</td>
						<td class="codeC"><form:radios
								property="mpc.cooperationModel" styleId="mpc.cooperationModel"
								collection="cooperationModels"/></td>
						<td class="nameC">经销商性质：</td>
						<td class="codeC"><html:text property="mpc.dealerNature"
								styleId="mpc.dealerNature"></html:text></td>
					</tr>
					
					<tr>
						<td class="nameC"><font color="#FF0000">*</font>地址：</td>
						<td class="codeC" colspan="3">
							<form:select property="mpc.province" styleId="province" >
								<html:option value="-1">请选择</html:option>
							</form:select>
							<form:select property="mpc.city" styleId="city" >
								<html:option value="-1">请选择</html:option>
							</form:select>
							<form:select property="mpc.district" styleId="district" >
								<html:option value="-1">请选择</html:option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="nameC"><font color="#FF0000">*</font>监管详细地址：</td>
						<td class="codeC" colspan="3"><html:text style="width:80%;" onfocus="addressAdd(this)"
								property="mpc.superviseAddress" styleId="superviseAddress"></html:text>
						</td>
						
					</tr>
					<tr>
						<td class="nameC">是否绑定店：</td>
						<td class="codeC"><form:radios property="mpc.isBindShop"
								collection="yesorno" styleId="isBind" onclick="isBind(this.value)"></form:radios></td>
						<td class="nameC">是否提供午餐：</td>
						<td class="codeC"><form:radios property="mpc.provideLunch"
								collection="yesorno"></form:radios></td>
					</tr>
					<tr id="bindShop1" style="display:none;">
						<td class="nameC">绑定店名称：</td>
						<td class="codeC">
							<select name="mpc.bindShop" style="min-width:250px;" id="bs1">
								<option selected="selected" value="-1">请选择...</option>
								<c:forEach items="${dealers }" var="row">
									<option value="<c:out value='${row.value}'/>"><c:out value="${row.label }"/></option>
								</c:forEach>
							</select>
							
						</td>
						<td class="nameC">银行名称：</td>
						<td class="codeC">
							<input type="text" id="bindShop1BankName" readonly="readonly"/>
						</td>
					</tr>
					<tr id="bindShop2" style="display:none;">
						<td class="nameC">绑定店名称：</td>
						<td class="codeC">
							<select name="mpc.bindShop2" style="min-width:250px;" id="bs2">
								<option selected="selected" value="-1">请选择...</option>
								<c:forEach items="${dealers }" var="row">
									<option value="<c:out value='${row.value}'/>"><c:out value="${row.label }"/></option>
								</c:forEach>
							</select>
						</td>
						<td class="nameC">银行名称：</td>
						<td class="codeC">
							<input type="text" id="bindShop2BankName" readonly="readonly"/>
						</td>
						
					</tr>
					<tr>
						<td class="nameC">是否需要交接：</td>
						<td class="codeC"><form:radios
								property="mpc.isNeedHandover" collection="yesorno" styleId="isNeedHandover"></form:radios>
						</td>
						<td class="nameC"><font color="#FF0000">*</font>预进驻时间：</td>
						<td class="codeC"><form:calendar property="mpc.inStoreDate"
								styleId="inStoreDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								readonly="true" /></td>
						
					</tr>
					<tr id="jiaojie" style="display:none;">
						<td class="nameC">交接时间：</td>
						<td class="codeC"><form:calendar
								property="mpc.handoverDate" styleId="mpc.handoverDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								 /></td>
						<td class="nameC"><font color="#FF0000">*</font>交接公司：</td>
						<td class="codeC"><html:text property="mpc.handoverCompany"
								styleId="handoverCompany"></html:text></td>
					</tr>
					<tr>
						<td class="nameC">备注：</td>
						<td class="codeC" colspan="3"><html:textarea
								property="mpc.inStoreRemark" styleId="mpc.inStoreRemark" style="width:60%;"></html:textarea>
						</td>
					</tr>
											<!-- 市场部信息 -->
					<tr class="formTitle">
						<td colspan="4">二、市场部信息</td>
					</tr>
					<tr>
						<td class="nameC">监管费责任人：</td>
						<td class="codeC"><html:text
								property="mpc.superviseMoneyPerson" styleId="mpc.superviseMoneyPerson" readonly="true"></html:text>
						</td>
						<td class="nameC">付费方式：</td>
						<td class="codeC">
							<form:select property="mpc.payMode" styleId="mpc.payMode" >
								<html:optionsCollection name="payModes" label="label" value="value"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="nameC"><font color="#FF0000">*</font>监管费（元/年）：</td>
						<td class="codeC">
						<%-- <html:text
								property="mpc.superviseMoney" styleId="superviseMoney"></html:text> --%>
								<input type="text" name="mpc.superviseMoney" id="superviseMoney" 
								class="easyui-numberbox" data-options="min:0,precision:2"></input>  
						</td>
						<td class="nameC"><font color="#FF0000">*</font>发票开具方式：</td>
						<td class="codeC">
							<html:text
								property="mpc.invoiceMode" styleId="invoiceMode"></html:text>
						</td>
					</tr>
					<tr>
						<td class="nameC"><font color="#FF0000">*</font>协议是否签署：</td>
						<td class="codeC">
							<form:radios collection="yesorno" property="mpc.isSignAnAgreement"></form:radios>
						</td>
						<td class="nameC agreementIsRecovery"><font color="#FF0000">*</font>协议是否回收：</td>
						<td class="codeC agreementIsRecovery">
							<form:radios collection="yesorno" property="mpc.agreementIsRecovery"></form:radios>
						</td>
					</tr>
					<tr>
						<td class="nameC">付款对象：</td>
						<td class="codeC">
							<html:text
								property="mpc.paymentObject" styleId="mpc.paymentObject"></html:text>
						</td>
						<td class="nameC">授信额度：</td>
						<td class="codeC">
							<input type="text" name="mpc.credit" 
							class="easyui-numberbox" data-options="min:0,max:99999,precision:0"></input>万元 
						</td>
					</tr>
					<tr>
						<td class="nameC">备注：</td>
						<td class="codeC" colspan="3">
							<html:textarea property="mpc.marketRemark" ></html:textarea>
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
