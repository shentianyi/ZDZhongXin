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
//页面初始化函数
<%-- function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
} --%>

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
	location = "<url:context/>/ZXinterface.do?method=warehouse";
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
	
		<html:form action="/ZXinterface.do" styleId="whForm" method="post" onsubmit="return false">
		<input type="hidden" name="method" id="method" value="warehouseUpdate" />
		 <table class="formTable">
			<tr>
				<td class="nameCol">ECIF客户号：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="warehouse.custNo" value="${warehouse.custNo} " disabled />
					<input type="hidden" name="warehouse.custNo" value="${warehouse.custNo}" />
				</td>
				<td class="nameCol">借款企业名称：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="warehouse.whlonentnm" value="${warehouse.whlonentnm}" disabled/>
					<input type="hidden" name="warehouse.whlonentnm" value="${warehouse.whlonentnm}" />
				</td>
			</tr>
			<tr>
				<td class="nameCol">仓库名称：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="warehouse.whName" value="${warehouse.whName}" disabled/>
					<input type="hidden" name="warehouse.whName" value="${warehouse.whName}" />
				</td>
				<td class="nameCol">仓库代码：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="warehouse.whCode" value="${warehouse.whCode}" disabled/>
					<input type="hidden" name="warehouse.whCode" value="${warehouse.whCode}" />
				</td>
			</tr>
			<tr>
				<td class="nameCol">仓库级别：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="warehouse.whLevel" value="${warehouse.whLevel==1?'一级仓库':warehouse.whLevel==2?'二级仓库':''}" disabled/>
					<input type="hidden" name="warehouse.whLevel" value="${warehouse.whLevel}" />
				</td>
				<td class="nameCol">经办行：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="warehouse.whOperorg" value="${warehouse.whOperorg}" disabled/>
					<input type="hidden" name="warehouse.whOperorg" value="${warehouse.whOperorg}" />
				</td>
			</tr>
			<tr>
				<td class="nameCol">地址：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="warehouse.whAddress" value="${warehouse.whAddress}" disabled/>
					<input type="hidden" name="warehouse.whAddress" value="${warehouse.whAddress}" />
				</td>
				<td class="nameCol">电话：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="warehouse.phone" value="${warehouse.phone}" disabled/>
					<input type="hidden" name="warehouse.phone" value="${warehouse.phone}" />
				</td>
			</tr>
			<tr>
				<td class="nameCol">仓库距离：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="warehouse.whdistance" value="${warehouse.whdistance}"/>
					<input type="hidden" name="warehouse.whdistance" value="${warehouse.whdistance}" />
				</td>
				<td class="nameCol">监管仓库联系人：</td>
				<td class="codeCol">
					<input class="ly-bor-none" type="text" name="warehouse.whContacts" value="${warehouse.whContacts}"/>
					<input type="hidden" name="warehouse.whContacts" value="${warehouse.whContacts}" />
					<input type="hidden" name="warehouse.lonentid" value="${warehouse.lonentid}" />
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
