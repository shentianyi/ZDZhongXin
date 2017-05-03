<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import="com.zd.csms.constants.Constants"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>中都汽车金融监管系统</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/easyui.css" rel="stylesheet" type="text/css" />
<link href="css/icon.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.min.js"></script>
<script src="js/jquery.easyui.min.js"></script>
<script src="js/common.js"></script>
<script src="js/calendar.js"></script>
<script type="text/javascript">
 $(function(){
	if("${msg}"!=""){
		alert("${msg}");
	} 
 });

//执行保存操作
function doSave(){
	document.forms[0].submit();
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/ZXinterface.do?method=financing";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">修改仓库信息</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
		<html:form action="/ZXinterface.do" styleId="finForm" method="post" onsubmit="return false">
		<input type="hidden" name="method" id="method" value="financingUpdate" />
		 <table class="formTable">
			<tr>
				<td class="nameCol">借款企业ID：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="financing.fgLonentNo" value="${financing.fgLonentNo}" disabled />
					<input type="hidden" name="financing.fgLonentNo" value="${financing.fgLonentNo}" />
				</td>
				<td class="nameCol">借款企业名称：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="financing.fgLoncpName" value="${financing.fgLoncpName}" disabled />
					<input type="hidden" name="financing.fgLoncpName" value="${financing.fgLoncpName}" />
				</td>
			</tr>
			
			<tr>
				<td class="nameCol">融资编号：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="financing.fgLoanCode" value="${financing.fgLoanCode}" disabled />
					<input type="hidden" name="financing.fgLoanCode" value="${financing.fgLoanCode}" />
				</td>
				<td class="nameCol">经办行：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="financing.fgOperOrg" value="${financing.fgOperOrg}" disabled />
					<input type="hidden" name="financing.fgOperOrg" value="${financing.fgOperOrg}" />
				</td>
			</tr>
			
			<tr>
				<td class="nameCol">放款批次号：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="financing.fgScftxNo" value="${financing.fgScftxNo}" disabled />
					<input type="hidden" name="financing.fgScftxNo" value="${financing.fgScftxNo}" />
				</td>
				<td class="nameCol">融资金额：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="financing.fgLoanAmt" value="${financing.fgLoanAmt}" disabled />
					<input type="hidden" name="financing.fgLoanAmt" value="${financing.fgLoanAmt}" />
				</td>
			</tr>
			
			<tr>
				<td class="nameCol">保证金比例：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="financing.fgBailRat" value="${financing.fgBailRat}%" disabled />
					<input type="hidden" name="financing.fgBailRat" value="${financing.fgBailRat}" />
				</td>
				<td class="nameCol">自有资金比例：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="financing.fgSlfcap" value="${financing.fgSlfcap}%" disabled />
					<input type="hidden" name="financing.fgSlfcap" value="${financing.fgSlfcap}" />
				</td>
			</tr>
			<tr>
				<td class="nameCol">首付保证金可提货比例：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="financing.fgFstblRat" value="${financing.fgFstblRat}%" disabled />
					<input type="hidden" name="financing.fgFstblRat" value="${financing.fgFstblRat}" />
				</td>
				<td class="nameCol">授信产品：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="financing.fgProcrt" value="${financing.fgProcrt}" disabled />
					<input type="hidden" name="financing.fgProcrt" value="${financing.fgProcrt}" />
				</td>
			</tr>
			<tr>
				<td class="nameCol">融资起始日：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="financing.fgStDate" value="${financing.fgStDate}" disabled />
					<input type="hidden" name="financing.fgStDate" value="${financing.fgStDate}" />
				</td>
				<td class="nameCol">融资到期日：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="financing.fgEndDate" value="${financing.fgEndDate}" disabled />
					<input type="hidden" name="financing.fgEndDate" value="${financing.fgEndDate}" />
				</td>
			</tr>
			<tr>
				<td class="nameCol">协议编号：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="financing.fgagtid" value="${financing.fgagtid}" />
					<input type="hidden" name="financing.fgagtid" value="${financing.fgagtid}" />
				</td>
				<td class="nameCol">业务模式：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="financing.fgBizMod" value="${financing.fgBizMod}" disabled />
					<input type="hidden" name="financing.fgBizMod" value="${financing.fgBizMod}" />
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
		<br/>
		<div id="message" class="message" style="display:none"></div>
		</html:form>
	</div>
</div>
</body>
</html>
