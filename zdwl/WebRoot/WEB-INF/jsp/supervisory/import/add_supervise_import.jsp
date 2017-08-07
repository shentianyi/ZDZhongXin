<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//执行保存操作
function doSave(){
	
	
	if (!$("#draftid").combobox("getValue")) {
		alert("请选择票号");
		return false;
	}
	var vin = document.getElementById("superviseImport.vin").value;
	var vinType = /^[a-zA-Z0-9]/;
	if(vin != ""){
		if (!(vinType.test(vin))) {
			alert("车架号格式错误！");
			return false;
		} else if (vin.length != 17) {
			alert("车架号位数不正确！");
			return false;
		}
	}
	
	if(!$("#money").val()){
		alert("请填写金额");
		return false;
	}
	var url = "../json/checkVin.do?callback=?&vin="+vin;
	$.getJSON(url, function(result) {
		var data = result.data;
		if(data > 0){
			alert("车架号不能重复");
			return false;
		}else{
			document.forms[0].submit();
		}
	});
}
//执行返回列表操作
function doReturn(){
	location = "<url:context/>/superviseImport.do?method=superviseImportList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}

function changedraft(draftNum){
	var url = "../json/changeDraft.do?callback=?";
	jQuery.getJSON(url,{draftNum : draftNum},function(result){
		jQuery.each(result.data, function(i, vehicle){
			var ss = vehicle.split("||");
			var name = ss[0];
			var fi = ss[1];
			document.getElementById("jxs").value=name;
			document.getElementById("jrjg").value=fi;
			
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
});
</script>
<style type="text/css">
.formTable td{
border-bottom:1px solid #eee;padding-top:13px;padding-bottom:13px;
border-right:1px solid #eee;
}
.formTable td input{
width:35%;
}
.formTable td select{
width:35%;
} 
.add_textStyle{
width:15%;text-align: right
}
.add_textRight{
width:15%;text-align: right}
.td_width{
width:35%}
.input_height{height:30px;}
</style>
</head>
  
<body onLoad="doLoad()">
<div class="title">新增监管物导入</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/superviseImport" styleId="superviseImportForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addSuperviseImport">
<input type="hidden" name="superviseImport.state" id="superviseImport.state" value="1">
<input type="hidden" name="superviseImport.apply" id="superviseImport.apply" value="0">
<table class="formTable">
	<tr>
		<td  class="add_textRight">票号：</td>
		<td  class="td_width">
			<select style="width:100%" id="draftid" name="superviseImport.draft_num" >
				<c:forEach items="${draftOptions }" var="row">
					<option value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
				</c:forEach>
			</select>
		</td>
		<td class="add_textStyle">经销商：</td>
		<td >
			<input type="text" id="jxs"style="width:100%;height:30px;border:1px solid #DDDDDD"  readonly="readonly">
		</td>
	</tr>
	<tr>
		<td  class="add_textRight">金融机构：</td>
		<td style="width:100%;"colspan="3">
			<input type="text" id="jrjg"style="height:30px;border:1px solid #DDDDDD;width:100%" readonly="readonly">
		</td>
	</tr>
	<tr>
		<td class="add_textRight">合格证发证日期：</td>
		<td   class="td_width">
			<form:calendar property="superviseImport.certificate_date"style="width:100%;height:30px;border:1px solid #DDDDDD" styleId="superviseImport.certificate_date" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
		<td class="add_textStyle">合格证号：</td>
		<td >
			<html:text property="superviseImport.certificate_num"style="width:100%;height:30px;border:1px solid #DDDDDD" styleId="superviseImport.certificate_num"></html:text>
		</td>
	</tr>
	<tr>
		<td   class="add_textRight">车辆型号：</td>
		<td   class="td_width">
			<html:text property="superviseImport.car_model"style="width:100%;height:30px;border:1px solid #DDDDDD"  styleId="superviseImport.car_model"></html:text>
		</td>
		<td class="add_textStyle">车身结构：</td>
		<td >
			<html:text property="superviseImport.car_structure"style="width:100%;height:30px;border:1px solid #DDDDDD" styleId="superviseImport.car_structure"></html:text>
		</td>
	</tr>
	<tr>
		<td class="add_textRight">排量：</td>
		<td class="td_width">
			<html:text property="superviseImport.displacement"style="width:100%;height:30px;border:1px solid #DDDDDD" styleId="superviseImport.displacement"></html:text>
		</td>
		<td class="add_textStyle">颜色：</td>
		<td >
			<html:text property="superviseImport.color"style="width:100%;height:30px;border:1px solid #DDDDDD" styleId="superviseImport.color"></html:text>
		</td>
	</tr>
	<tr>
		<td class="add_textRight">发动机号：</td>
		<td class="td_width">
			<html:text property="superviseImport.engine_num"style="width:100%;height:30px;border:1px solid #DDDDDD"  styleId="superviseImport.engine_num"></html:text>
		</td>
		<td class="add_textStyle"><font color="#FF0000">*</font>车架号：</td>
		<td >
			<html:text property="superviseImport.vin" style="width:100%;height:30px;border:1px solid #DDDDDD"  styleId="superviseImport.vin"></html:text>
		</td>
	</tr>
	<tr>
		<td class="add_textRight">钥匙号：</td>
		<td class="td_width">
			<html:text property="superviseImport.key_num"style="width:100%;height:30px;border:1px solid #DDDDDD"  styleId="superviseImport.key_num"></html:text>
		</td>
		<td class="add_textStyle"><font color="#FF0000">*</font>金额：</td>
		<td >
			<input type="text" name="superviseImport.money" style="width:100%;height:30px;border:1px solid #DDDDDD"  id="money" 
			class="easyui-numberbox" value="<c:out value='${superviseImportForm.superviseImport.money}'/>"
			data-options="min:0,precision:2"></input>
		</td>
	</tr>
	<tr>
		<td class="add_textRight">备注：</td>
		<td class="td_width" colspan="3">
			<html:text property="superviseImport.des"style="width:100%;height:30px;border:1px solid #DDDDDD"  styleId="superviseImport.des"></html:text>
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

</html:form>
<br/>
<div id="message" class="message" style="display:none"></div>

	</div>
</div>
</body>
</html>
