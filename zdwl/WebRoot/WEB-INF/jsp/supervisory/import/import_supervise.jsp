<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>

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

<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script>

//执行保存操作
function doSave(){
	if (!$("#draftid").combobox("getValue")) {
		alert("请选择票号");
		return false;
	}
	if(!getElement("excelfile").value){
		alert("请选择导入的文件");
		return false;
	}
	$("#save").prop("disabled",true);
	document.forms[0].submit();
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/superviseImport.do?method=superviseImportList";
}

function changedraft(draftNum){	
	var url = "../json/changeDraft.do?callback=?";
	jQuery.getJSON(url,{draftNum : draftNum},function(result){
		jQuery.each(result.data, function(i, vehicle){
			var ss = vehicle.split("||");
			var name = ss[0];
			var fi = ss[1];
			document.getElementById("dealerName").value=name;
			document.getElementById("bankName").value=fi;
			
		});
	});
}
function init() {
	$("#draftid").combobox({
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
				changedraft(newValue);
			}

		}
	});
	var draftValue = $("#draftid").combobox('getValue');
	if (draftValue) {
		changedraft(draftValue);
	}
}
$(function(){
	init();
	var msg ="<c:out value='${message}'/>";
	if(msg!=null&&msg!=""&&msg!="null"){
		alert(msg);
	}
});

</script>
</head>
  
<body>
<div class="title">导入监管物</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/superviseImport" styleId="superviseImportForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="importExcel">
<table class="formTable">
	<tr>
		<td colspan="4" style="text-align: center;">
			导入模板：<a style="color: blue;" href="/system/importTemplate/cheliangdaoru.xls">下载</a>
		</td>
	</tr>
	<tr>
		<td class="nameCol">票号：</td>
		<td class="codeCol" colspan="3">
			<select style="width:50%" id="draftid" name="draftNum" onchange="changedraft(this.value)" >
				<c:forEach items="${draftOptions }" var="row">
					<option value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td class="nameCol">经销商：</td>
		<td class="codeCol" colspan="3">
			<input type="text" id="dealerName" readonly="readonly">
		</td>
	</tr>
	<tr>
		<td class="nameCol">金融机构：</td>
		<td class="codeCol" colspan="3">
			<input type="text" id="bankName" readonly="readonly">
		</td>
	</tr>
	<tr>
		<td class="nameCol">上传：</td>
		<td class="codeCol" colspan="3"><html:file styleId="excelfile" property="excelfile"></html:file></td>
	</tr>
	<tr class="formTableFoot">
		<td colspan="4" align="center">
			<button id="save" class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
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
