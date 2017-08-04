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
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>

<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script src="/pagejs/draft.js"></script>
<script>
$(function(){
	init();
});
//执行返回列表操作
function doReturn(){
	location = "<url:context/>/draft.do?method=draftList";
}

//执行保存操作
function doSave(){
	var distribid = document.getElementById("jxs").value;
	if(distribid == ""){
		alert("请填写经销商");
		return false;
	}
	var draft_num = document.getElementById("draft.draft_num").value;
	if(draft_num == ""){
		alert("请填写汇票号码");
		return false;
	}
	
	if(!$("#billing_money").val()){
		alert("请填写开票金额");
		return false;
	}
	//验证时间日期
	if(!validTime()){
		return false;
	}

	document.forms[0].submit();

}

//验证时间日期
function validTime(){
	//开票日期
	var billing_date = document.getElementById("draft.billing_date").value;
	if(billing_date == ""){
		alert("请填写开票日");
		return false;
	}

	//到期日期
	var due_date = document.getElementById("draft.due_date").value;
	if(due_date == ""){
		alert("请填写到期日");
		return false;
	}
	
	var billing_arr = billing_date.split("-");
	var due_arr = due_date.split("-");
	var billing_time = new Date(parseInt(billing_arr[0]),parseInt(billing_arr[1]-1),parseInt(billing_arr[2]),0,0,0);
	var due_time = new Date(parseInt(due_arr[0]),parseInt(due_arr[1]-1),parseInt(due_arr[2]),0,0,0);
	
	
	if(billing_time.getTime() > due_time.getTime()){
		alert("开票日期不能大于到期日期");
		return false;
	}else if(billing_time.getTime() == due_time.getTime()){
		alert("开票日期不能等于到期日期");
		return false;
	}else{
		return true;
	}  
	return true;
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
$(function(){
	var msg ="<c:out value='${message}'/>";
	if(msg!=null&&msg!=""&&msg!="null"){
		alert(msg);
	}
});
</script>
</head>
  
<body>
<div class="title">新增汇票</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/draft" styleId="draftForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addDraft">
<table class="formTable">
	<tr>
		<td   class="add_textStyle">质押协议号：</td>
		<td>
			<html:text property="draft.agreement" style="width:100%;height:30px;border:1px solid #DDDDDD" styleClass="easyui-numberbox" styleId="draft.agreement"></html:text>
		</td>
		<td   class="add_textRight">保证金账号：</td>
		<td   class="td_width">
			<html:text property="draft.bail_num" style="width:100%;height:30px;border:1px solid #DDDDDD"  styleId="draft.bail_num"></html:text>
		</td>
	</tr>
	<tr>
		<td   class="add_textStyle"><font color="#FF0000">*</font>经销商：</td>
		<td>
			<select style="width:100%;height:30px;" id="jxs" name="draft.distribid" >
				<c:forEach items="${dealersOptions }" var="row">
					<option value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
				</c:forEach>
			</select>
			
		</td>
		<td   class="add_textRight">金融机构：</td>
		<td   class="td_width">
			<input type="text"id="bankName"style="width:100%;height:30px;border:1px solid #DDDDDD" readonly="readonly" />
		</td>
	</tr>
	<tr>
		<td   class="add_textStyle">品牌：</td>
		<td>
			<input type="text" id="brand"style="width:100%;height:30px;border:1px solid #DDDDDD" readonly="readonly"/>
		</td>
		<td   class="add_textRight"><font color="#FF0000">*</font>汇票号码：</td>
		<td  class="td_width">
			<html:text property="draft.draft_num"style="width:100%;height:30px;border:1px solid #DDDDDD" styleId="draft.draft_num"></html:text>
		</td>
	</tr>
	<tr>
		<td   class="add_textStyle"><font color="#FF0000">*</font>开票日：</td>
		<td>
			<form:calendar property="draft.billing_date" style="width:100%;height:30px;border:1px solid #DDDDDD" styleId="draft.billing_date" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
		<td   class="add_textRight"><font color="#FF0000">*</font>到期日：</td>
		<td  class="td_width">
			<form:calendar property="draft.due_date" style="width:100%;height:30px;border:1px solid #DDDDDD" styleId="draft.due_date" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
	</tr>
	<tr>
		<td   class="add_textStyle"><font color="#FF0000">*</font>开票金额：</td>
		<td>
			<input type="text"style="height:30px;width:100%" name="draft.billing_money" id="billing_money" 
								class="easyui-numberbox" 
								data-options="min:0,precision:2"></input>
		</td>
		<td  class="add_textRight"><font color="#FF0000">*</font>状态：</td>
		<td  class="td_width">
			<form:select value="2" property="draft.state" style="height:30px;border:1px solid #DDDDDD" styleId="draft.state">
				<html:optionsCollection name="isClearTicket" label="label" value="value" />
			</form:select>
		</td>
	</tr>
	<tr>
		<td   class="add_textStyle"><font color="#FF0000">*</font>保证金比例：</td>
		<td>
			<input type="text" name="draft.bailscale"style="height:30px;width:100%" id="bailscale" 
								class="easyui-numberbox" 
								data-options="min:0.00,max:100.00,precision:2">%</input>
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
