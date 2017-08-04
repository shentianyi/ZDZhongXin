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
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<!-- easyui -->
<link href="/easyui/themes/material/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	init();
}

function suffix(file_name){
	var result = file_name.substring(file_name.lastIndexOf('.'),file_name.length);
	return result
}

//执行保存操作
function doSave(){
	var dealerId=document.getElementsByName("duedate.dealerId")[0].value;
	if(dealerId==-1){
		alert("请选择经销商！");
		return false;
	}
	var value=""; 
	var arr = document.getElementsByName("duedate.type");
	for(var i=0;i<arr.length;i++)
	    {
	        if(arr[i].checked)
	        {    
	          value=arr[i].value;
	        }
	    }
	if(value == "" || value == null ){
		alert("请选择文件类型！");
		return false;
	}
	var fileName = document.getElementById("ben").value;
	if(fileName.length <= 0){
		alert("请选择需要上传的文件!");
		return false;
	}
	if (!/.((rar)|(zip)|(gzip)|(jar)|(tar)|(iso))$/i.test(suffix(fileName))){    
		alert('上传文件格式不合法,仅支持 zip,rar,gzip,jar,tar,iso 格式!');
		return false;
	} 
	document.forms[0].submit();
	
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/duedate.do?method=findList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
function changeDealer(id) {
	if(id==-1){
		return;
	}
	var url = "../json/getDealerByIdJsonAction.do?callback=?&findRep=yes&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setDealer(data[0]);
	});
}
function setDealer(dealer){
	$("#dealerName").attr("value",dealer.dealerName);
	$("#brandName").attr("value",dealer.brand);
	$("#brandId").attr("value",dealer.brandId);
	$("#bankName").attr("value",dealer.bankName);
	$("#bankId").attr("value",dealer.bankId);
	/* $("#dealerName").val(dealer.dealerName); 
	$("#brandName").val(dealer.brand);
	$("#brandId").val(dealer.brandId);
	$("#bankName").val(dealer.bankName);
	$("#bankId").val(dealer.bankId);*/
}
function init() {
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
			} else {
				changeDealer(newValue)
			}

		}
	});
	 var draftValue = $("#dealerId").combobox('getValue');
		if (draftValue) {
			changeDealer(draftValue);
		}
}
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">新增日查库</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/duedate" styleId="duedateForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="addDuedate">
<table class="formTable">
	<tr>
		<td colspan="4">
			<table style="width: 100%">
				<tr>
					<td class="nameCol"><font color="#FF0000">*</font>经销商：</td>
					<td class="codeCol">
						<select style="width:99%" id="dealerId" name="duedate.dealerId" onchange="changeDealer(this.value)" >
							<option value="-1">请选择</option>
							<c:forEach items="${dealers }" var="row">
								<option value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
							</c:forEach>
						</select>
					</td>
					<td class="nameCol">金融机构：</td>
					<td class="codeCol">
						<input type="text" hidden="true" id="dealerName" name="duedate.dealerName" value="" readonly="readonly"/>
						<input type="text" hidden="true" id="bankId" name="duedate.bankId" value="" readonly="readonly"/>
						<input type="text"  id="bankName" name="duedate.bankName" value="" readonly="readonly"/>
					</td>
					<td class="nameCol">品牌：</td>
					<td class="codeCol">
						<input type="text" hidden="true" id="brandId" name="duedate.brandId" value="" readonly="readonly"/>
						<input type="text"  id="brandName" name="duedate.brandName" value="" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol" ><font color="#FF0000">*</font>文件类型：</td>
					<td class="nameCol"style="text-align: left;" colspan="2" >
						<form:radios  property="duedate.type" collection="dateType" styleId="duedate.type"/>
					</td>
					<td class="nameCol">文件：</td>
					<td class="codeCol" colspan="2">
						<input type="file" name="ben" id="ben"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr class="formTableFoot">
		<td colspan="4" align="center">
			<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
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
