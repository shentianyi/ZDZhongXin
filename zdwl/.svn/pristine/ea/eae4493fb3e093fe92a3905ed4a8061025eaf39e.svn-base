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

	var managers=null;

	//页面初始化函数
	function doLoad(){
		//显示提示信息
		showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	}
	$(function(){
		
		$("[name='mpc\\.isNeedHandover']").click(function(){
			isNeedHandoverShow(this.value);
		});
		var isNeedHandover = $("[name='mpc\\.isNeedHandover']:checked").val();
		isNeedHandoverShow(isNeedHandover);
		init();
		isBind('<c:out value="${mpcForm.mpc.isBindShop}"/>');
	});
	function isNeedHandoverShow(val){
		if(val==1){
			$("#jiaojie").show();
		}else if(val==2){
			$("#jiaojie").hide();
		}
	}
	
	
	$(function(){
		initBank();
		var bankPath = "<c:out value='${bank.path}'/>";
		var bankId = "<c:out value='${bank.id}'/>";
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
		setManagerByBankId($("#bankId").val());
		$("#managerId").change();
		//初始化地址
		initAddress();
		var province = "<c:out value='${mpcForm.mpc.province}'/>";
		var city = "<c:out value='${mpcForm.mpc.city}'/>";
		var district = "<c:out value='${mpcForm.mpc.district}'/>";
		if(province){
			$("#province").val(province);
			$("#province").change();
			if(city){
				$("#city").val(city);
				$("#city").change();
				if(district){
					$("#district").val(district);
				}
			}
		}
		
		
	});
	
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
		if(id!=-1){
			$("#bankId").val(id);
		}else{
			$("#bankId").val("");
		}
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
		$.ajax({
			url:url,
			async:false,
			dataType:"jsonp",
			success:function(result){
				var province = result.data;
				$.each(province, function(i, item) {
					var option = "<option value="+province[i].id+">" + province[i].name+ "</option>";
					nextSelect.append(option);
				});
			}
		});
	}
	

	function isBind(value){
		if(value==2){
			$("#bindShop1").hide();
			$("#bindShop2").hide();
		}else{
			$("#bindShop1").show();
			$("#bindShop2").show();
			
			var bs1 = "<c:out value='${mpcForm.mpc.bindShop}'/>";
			var bs2 = "<c:out value='${mpcForm.mpc.bindShop2}'/>";
			if(bs1>0){
				$("#bs1").combobox('setValue',bs1);
				bindShop1change(bs1);
			}
			if(bs2>0){
				$("#bs2").combobox('setValue',bs2);
				bindShop2change(bs2);
			}
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
	
	$(function(){
		if($("[name=mpc\\.isSignAnAgreement]:checked").val()==2){
			$(".agreementIsRecovery").hide();
		}
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

	<div class="title">新增项目进驻流转单</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/market/projectCirculationForm" styleId="mpcForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="update" />
				<html:hidden property="mpc.bankId" styleId="bankId"/>
				<html:hidden property="mpc.id"/>
				<table class="formTable">

										<tr class="formTitle">
						<td colspan="4">一、新进店信息</td>
					</tr>
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>经销店名称：</td>
						<td class="codeCol2" colspan="3"><html:text style="width:50%;" property="mpc.dealerName" styleId="dealerName"></html:text></td>
					</tr>
					<tr>
						<td class="nameCol2">店方联系人：</td>
						<td class="codeCol2"><html:text property="mpc.dealerPerson"></html:text></td>
						<td class="nameCol2">联系人电话：</td>
						<td class="codeCol2"><html:text property="mpc.dealerPhone"></html:text></td>
					</tr>
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>金融机构：</td>
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
					<tr>
						<td class="nameCol2">客户经理：</td>
						<td class="codeCol2">
							<select name="mpc.bankManagerId" id="managerId" >
							</select>
						</td>
						<td class="nameCol2">联系电话</td>
						<td class="codeCol2">
							<input type="text" id="managerPhone" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>品牌：</td>
						<td class="codeCol2">
							<select name="mpc.brandId" style="width:50%" id="pinpai">
								<c:forEach items="${brands }" var="row">
									<option <c:if test="${mpcForm.mpc.brandId==row.value}">selected='selected'</c:if> value="<c:out value='${row.value}'/>"><c:out value="${row.label }"/></option>
								</c:forEach>
							</select>
						</td>
						<td class="nameCol2"><font color="#FF0000">*</font>协议监管模式：</td>
						<td class="codeCol2">
							<form:radios styleId="supervisionMode"
								property="mpc.supervisionMode" collection="supervisionModes"></form:radios>
						</td>
					</tr>
					<tr>
						<td class="nameCol2">合作模式：</td>
						<td class="codeCol2"><form:radios
								property="mpc.cooperationModel" styleId="mpc.cooperationModel"
								collection="cooperationModels"/></td>
						<td class="nameCol2">经销商性质：</td>
						<td class="codeCol2"><html:text property="mpc.dealerNature"
								styleId="mpc.dealerNature"></html:text></td>
					</tr>
					
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>地址：</td>
						<td class="codeCol2" colspan="3">
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
						<td class="nameCol2"><font color="#FF0000">*</font>监管详细地址：</td>
						<td class="codeCol2" colspan="3"><html:text style="width:80%;"
								property="mpc.superviseAddress" styleId="superviseAddress"></html:text>
						</td>
						
					</tr>
					<tr>
						
						<td class="nameCol2">是否绑定店：</td>
						<td class="codeCol2"><form:radios property="mpc.isBindShop"
								collection="yesorno" styleId="isBind" onclick="isBind(this.value)"></form:radios></td>
						<td class="nameCol2">是否提供午餐：</td>
						<td class="codeCol2"><form:radios property="mpc.provideLunch"
								collection="yesorno"></form:radios></td>
					</tr>
					<tr id="bindShop1" style="display:none;">
						<td class="nameCol2">绑定店名称：</td>
						<td class="codeCol2">
							<select name="mpc.bindShop" style="min-width:250px;" id="bs1">
								<option selected="selected" value="-1">请选择...</option>
								<c:forEach items="${dealers }" var="row">
									<option value="<c:out value='${row.value}'/>"><c:out value="${row.label }"/></option>
								</c:forEach>
							</select>
							
						</td>
						<td class="nameCol2">银行名称：</td>
						<td class="codeCol2">
							<input type="text" id="bindShop1BankName" readonly="readonly"/>
						</td>
					</tr>
					<tr id="bindShop2" style="display:none;">
						<td class="nameCol2">绑定店名称：</td>
						<td class="codeCol2">
							<select name="mpc.bindShop2" style="min-width:250px;" id="bs2">
								<option selected="selected" value="-1">请选择...</option>
								<c:forEach items="${dealers }" var="row">
									<option value="<c:out value='${row.value}'/>"><c:out value="${row.label }"/></option>
								</c:forEach>
							</select>
						</td>
						<td class="nameCol2">银行名称：</td>
						<td class="codeCol2">
							<input type="text" id="bindShop2BankName" readonly="readonly"/>
						</td>
						
					</tr>
					<tr>
						<td class="nameCol2">是否需要交接：</td>
						<td class="codeCol2"><form:radios
								property="mpc.isNeedHandover" collection="yesorno" styleId="isNeedHandover"></form:radios>
						</td>
						<td class="nameCol2"><font color="#FF0000">*</font>预进驻时间：</td>
						<td class="codeCol2"><form:calendar property="mpc.inStoreDate"
								styleId="inStoreDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								 /></td>
					</tr>
					<tr id="jiaojie">
						<td class="nameCol2">交接时间：</td>
						<td class="codeCol2"><form:calendar
								property="mpc.handoverDate" styleId="mpc.handoverDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" /></td>
						<td class="nameCol2">交接公司：</td>
						<td class="codeCol2"><html:text property="mpc.handoverCompany"
								styleId="handoverCompany"></html:text></td>
						
					</tr>
					<tr>
						<td class="nameCol2">备注：</td>
						<td class="codeCol2" colspan="3"><html:textarea
								property="mpc.inStoreRemark" styleId="mpc.inStoreRemark" style="width:60%;"></html:textarea>
						</td>
					</tr>
											<!-- 市场部信息 -->
					<tr class="formTitle">
						<td colspan="4">二、市场部信息</td>
					</tr>
					<tr>
						<td class="nameCol2">监管费责任人：</td>
						<td class="codeCol2"><html:text
								property="mpc.superviseMoneyPerson" styleId="mpc.superviseMoneyPerson" readonly="true"></html:text>
						</td>
						<td class="nameCol2">付费方式：</td>
						<td class="codeCol2">
							<form:select property="mpc.payMode" styleId="mpc.payMode" >
								<html:optionsCollection name="payModes" label="label" value="value"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>监管费（元/年）：</td>
						<td class="codeCol2">
						<input type="text" name="mpc.superviseMoney" id="superviseMoney" 
								class="easyui-numberbox" value="0" data-options="min:0,precision:2,value:<c:out value='${mpcForm.mpc.superviseMoney}'/>" ></input>
						<%-- <html:text
								property="mpc.superviseMoney" styleId="superviseMoney"></html:text> --%>
						</td>
						<td class="nameCol2"><font color="#FF0000">*</font>发票开具方式：</td>
						<td class="codeCol2">
							<html:text
								property="mpc.invoiceMode" styleId="invoiceMode"></html:text>
						</td>
					<tr>
						<td class="nameCol2">协议是否签署：</td>
						<td class="codeCol2">
							<form:radios collection="yesorno" property="mpc.isSignAnAgreement"></form:radios>
						</td>
						<td class="nameCol2 agreementIsRecovery">协议是否回收：</td>
						<td class="codeCol2 agreementIsRecovery">
							<form:radios collection="yesorno" property="mpc.agreementIsRecovery"></form:radios>
						</td>
					</tr>
					<tr>
						
						<td class="nameCol2">付款对象：</td>
						<td class="codeCol2">
							<html:text
								property="mpc.paymentObject" styleId="mpc.paymentObject"></html:text>
						</td>
						<td class="nameCol2">授信额度：</td>
						<td class="codeCol2">
							<input type="text" name="mpc.credit" 
							class="easyui-numberbox" value="<c:out value='${mpcForm.mpc.credit}'/>" data-options="min:0,max:99999,precision:0"></input>万元 
						</td>
					</tr>
					<tr>
						<td class="nameCol2">备注：</td>
						<td class="codeCol2" colspan="3">
							<html:textarea property="mpc.marketRemark"></html:textarea>
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
