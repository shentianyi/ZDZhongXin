<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="c.tld" prefix="c"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>经销商参数设置</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<script src="../js/common.js"></script>
<script src="../js/jquery-1.8.3.min.js"></script>
<script src="../js/validate_main.js"></script>
<script src="../js/validate_base.js"></script>
<script>

//执行保存操作
function doSave(){
	var chooseVal = $("#chooseBank").val();
	if(chooseVal=="0"){
		
	}if(chooseVal=="1"){
		alert("浙商银行");
	}if(chooseVal=="2"){
		if($("#organizationcode").val()=="" || $("#contract").val()==""){
			alert("组织机构代码或合同编号不能为空");
			return false;
		}
	}
	document.forms[0].submit();
}

function changeBankDockType(bankDockType){
	if(bankDockType=="0"){
		$("#custNoContractNo").hide();
		$("#dealerno").hide();
	}if(bankDockType=="1"){
		$("#custNoContractNo").show();
		$("#dealerno").hide();
	}if(bankDockType=="2"){
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
		<form action="../distribset.do" method="post" onsubmit="return false">
			<input type="hidden" name="method" id="method" value="chooseBank">
			<input type="hidden" name="did" value="6">
			<!-- <html:hidden property="distribset.distribid" styleId="distribset.distribid" />
			<html:hidden property="distribset.id" styleId="distribset.id" /> -->
			<table class="formTable">
				<tr>
					<td class="nameCol">监管物移动百分比:</td>
					<td class="codeCol" >
						<input style="height:36px;width:260px;" name="moveperc" />%
					</td>
					<td class="nameCol">对接银行:</td>
					<td class="codeCol" >
						<select onchange="changeBankDockType(this.value)" id="chooseBank" name="bankdocktype">
							<option value="0">不对接</option>
							<option value="1">浙商银行</option>
							<option value="2">中信银行</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="4" id="custNoContractNo" hidden="true">
						<div　id="custNoContractN" >
							<table class="formTable">
								<tr>
									<td class="nameCol" ><font color="#FF0000">*</font>浙商银行客户号:</td>
									<td class="codeCol" ><input style="height:36px;width:260px;"/></td>
									<td class="nameCol">质押合同编号:</td>
									<td class="codeCol" ><input style="height:36px;width:260px;" /></td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4" id="dealerno" hidden="true">
						<div id="custNoContractM">
							<table class="formTable">
								<tr>
									<td class="nameCol" ><font color="#FF0000" >*</font>经销商组织机构代码:</td>
									<td class="codeCol" ><input style="height:36px;width:260px;" name="organizationcode" id="organizationcode"/></td>
									<td class="nameCol" > 质押合同编号：</td>
									<td class="codeCol" ><input style="height:36px;width:260px;" name="contract" id="contract"/></td>
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
		 </form>
	</div>
</div>
<div class="message" id="message" style="display:none" align="center"></div>
</body>
</html>