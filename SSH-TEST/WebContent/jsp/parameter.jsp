<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html ">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<script src="../js/common.js"></script>
<script src="../js/jquery-1.8.3.min.js"></script>
<script src="../js/validate_main.js"></script>
<script src="../js/validate_base.js"></script>
<script>

//执行保存操作
function doSave(){
	if($("#bankDockType").val()!="0"){
		if($("#zsCustNo").val()==""){
			alert("请填写浙商银行客户号!");
			return false;
		}
	}
	document.forms[0].submit();
}

function changeBankDockType(bankDockType){
	if(bankDockType=="不对接"){
		$("#custNoContractNo").hide();
		$("#dealerno").hide();
	}if(bankDockType=="浙商银行"){
		$("#custNoContractNo").show();
		$("#dealerno").hide();
	}if(bankDockType=="中信银行"){
		$("#custNoContractNo").hide();
		$("#dealerno").show();	
	}
}

/* //进入列表页面
function goList() {
	location = "/ledger/dealer.do?method=findBusinessList";
} */
</script>
</head>
<body>
<div class="title">经销商参数设置</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
		<!-- <html:form action="/distribset" styleId="distribsetForm" method="post" onsubmit="return false"> -->
			<input type="hidden" name="method" id="method" value="addOrUpddistribset">
			<!-- <html:hidden property="distribset.distribid" styleId="distribset.distribid" />
			<html:hidden property="distribset.id" styleId="distribset.id" /> -->
			<table class="formTable">
				<tr>
					<td class="nameCol" style="width:25%" >监管物移动百分比:</td>
					<td><input style="height:36px;width:260px;" /></td>
					<!-- <td class="codeCol" >
						<html:text property="distribset.movePerc" styleId="movePerc" />%
					</td> -->
					<td class="nameCol">对接银行:</td>
					<td class="codeCol" >
						<!-- <form:select property="distribset.bankDockType"
							styleId="bankDockType" onchange="changeBankDockType(this.value)">
							<html:option value="0">不对接</html:option>
							<html:optionsCollection name="bankDockTypes" label="label" value="value" />
						</form:select></td> -->
						<select onchange="changeBankDockType(this.value)">
							<option value="不对接">不对接</option>
							<option value="浙商银行">浙商银行</option>
							<option value="中信银行">中信银行</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="4" id="custNoContractNo" hidden="true">
						<div　id="custNoContractN" >
							<table class="formTable">
								<tr>
									<td class="nameCol" style="width:25%" ><font color="#FF0000">*</font>浙商银行客户号:</td>
									<td><input style="height:36px;width:260px;" /></td>
									<!-- <td class="codeCol">
										<html:text property="distribset.zsCustNo" styleId="zsCustNo" />
									</td> -->
									<td class="nameCol">质押合同编号:</td>
									<td><input style="height:36px;width:260px;" /></td>
									<!-- <td class="codeCol" >
										<html:text property="distribset.contractNo" styleId="contractNo" />
									</td> -->
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4" id="dealerno" hidden="true">
						<div id="custNoContractN">
							<table class="formTable">
								<tr>
									<td class="nameCol"  style="width:25%"><font color="#FF0000" >*</font>经销商组织机构代码:</td>
									<td><input style="height:36px;width:260px;" /></td>
									<!-- <td class="codeCol">
										<html:text property="distribset.zsCustNo" styleId="zsCustNo" />
									</td> -->
									<td> 质押合同编号：</td>
									<td><input style="height:36px;width:260px;" /></td>
									<!-- <td><html:text property="distribset.contractNo" styleId="contractNo" /></td> -->
								</tr>
							</table>
						</div>
					</td>
				</tr>
				
				<tr class="formTableFoot">
					<td colspan="4" align="center">
						<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
						<button class="formButton" onclick="history.go(-1)">返&nbsp;回</button>
					</td>
				</tr>
			</table>
		</html:form>
	</div>
</div>
<div class="message" id="message" style="display:none" align="center"></div>
</body>
</html>