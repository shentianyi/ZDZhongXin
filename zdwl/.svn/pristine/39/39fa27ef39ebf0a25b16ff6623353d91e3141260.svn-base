<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.csms.constants.Constants"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>中都汽车金融监管系统</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script type="text/javascript">
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	var pid=$("#provinceId option:selected") .val();
	console.log(pid);
	if(pid !=null && pid != ""){
	   searchCity(pid);
	}
	
}

//执行保存操作
function doSave(){
	var value = document.getElementById("fixedAssetList.department").value;
	if(value == ""){
		alert("请填写使用部门");
		return false;
	}
	var value = document.getElementById("fixedAssetList.username").value;
	if(value == "-1"){
		alert("请选择使用人");
		return false;
	}
	
	var value = document.getElementById("fixedAssetList.trade").value;
	if(value == ""){
		alert("请填写存放店");
		return false;
	}
	var value = document.getElementById("fixedAssetList.deposit_time").value;
	if(value == ""){
		alert("请填写存放时间");
		return false;
	}
	document.forms[0].submit();
}

//执行返回列表操作
function doReturn(){
	var fid = document.getElementById("fid").value;
	location = "<url:context/>/fixedAssetList.do?method=fixedAssetList&fid="+fid;
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}

function searchCity(pid){
   $.ajax({
       type : "POST",
       url : "/fixedAssetList.do?method=searchCity",
       dataType : "json",
       data : "fixedAssetList.trade_province="+ pid,
       success : function(json) {
            console.log(json);
            document.getElementById("cityId").options.length=0;///清除所有options
            for ( var i = 0; i < json[0].length; i++) {
                if(json[0][i].value == document.getElementById("trade_city").value){
                   document.getElementById("cityId").options.add(new Option(json[0][i].label,json[0][i].value,true,true));
                }else{
                    document.getElementById("cityId").options.add(new Option(json[0][i].label,json[0][i].value));
                }
            } 
       }
           
   }); 
}
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">修改固定资产使用情况</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/fixedAssetList" styleId="fixedAssetListForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<html:hidden property="fixedAssetList.id" styleId="fixedAssetList.id"/>
<input type="hidden" name="method" id="method" value="updFixedAssetList">
<input type="hidden" id="fid" name="fid" value="<c:out value='${fid}'/>">
<input type="hidden" id="trade_city" value="<c:out value='${cid }'/>" />
<html:hidden property="fixedAssetList.createuserid" styleId="fixedAssetList.createuserid" />
<html:hidden property="fixedAssetList.createdate" styleId="fixedAssetList.createdate" />
<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>使用部门：</td>
		<td class="codeCol">
			<html:text property="fixedAssetList.department" styleId="fixedAssetList.department"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>使用人：</td>
		<td class="codeCol">
			<form:select property="fixedAssetList.username" styleId="fixedAssetList.username">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="supervisorsOptions" label="label" value="value" />
			</form:select>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>存放店：</td>
		<td class="codeCol">
			<html:text property="fixedAssetList.trade" styleId="fixedAssetList.trade"></html:text>
		</td>
		<td class="nameCol">存放地址(省)：</td>
		<td class="codeCol">
			<%-- <html:text property="fixedAssetList.trade_province" styleId="fixedAssetList.trade_province"></html:text> --%>
		    <select id="provinceId"  name="fixedAssetList.trade_province" onchange="searchCity(this.value);" class="ly-bor-none">
                <option value="-1">请选择</option>
                <c:forEach items="${provincesOptions}" var="row">
                    <option <c:if test="${fixedAssetListForm.fixedAssetList.trade_province==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
                </c:forEach>
            </select>
		</td>
	</tr>
	<tr>
		<td class="nameCol">存放地址(市)：</td>
		<td class="codeCol">
			<select id="cityId" name="fixedAssetList.trade_city" class="ly-bor-none">
            </select>
		</td>
		<td class="nameCol">密码：</td>
		<td class="codeCol">
			<html:text property="fixedAssetList.password" styleId="fixedAssetList.password"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">钥匙：</td>
		<td class="codeCol">
			<html:text property="fixedAssetList.key" styleId="fixedAssetList.key"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>存放时间：</td>
		<td class="codeCol">
			<form:calendar property="fixedAssetList.deposit_time" styleId="fixedAssetList.deposit_time" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
	</tr>
	<tr>
		<td class="nameCol">转出时间：</td>
		<td class="codeCol">
			<form:calendar property="fixedAssetList.out_time" styleId="fixedAssetList.out_time" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
		<td class="nameCol">运输公司：</td>
		<td class="codeCol">
			<html:text property="fixedAssetList.express" styleId="fixedAssetList.express"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">单号：</td>
		<td class="codeCol">
			<html:text property="fixedAssetList.express_num" styleId="fixedAssetList.express_num"></html:text>
		</td>
		<td class="nameCol">运费：</td>
		<td class="codeCol">
			<html:text property="fixedAssetList.express_money" styleId="fixedAssetList.express_money"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">维修时间：</td>
		<td class="codeCol">
			<form:calendar property="fixedAssetList.repair_time" styleId="fixedAssetList.repair_time" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
		<td class="nameCol">维修金额：</td>
		<td class="codeCol">
			<html:text property="fixedAssetList.repair_money" styleId="fixedAssetList.repair_money"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">维修内容：</td>
		<td class="codeCol">
			<html:text property="fixedAssetList.repair_des" styleId="fixedAssetList.repair_des"></html:text>
		</td>
		<td class="nameCol">设备维修单：</td>
		<td class="codeCol">
			上传：<c:out value="${picname}" />
				<input type="file" name="fixed" id="fixed"/>
		</td>
	</tr>
	<tr>
		<td class="nameCol">备注：</td>
		<td class="codeCol">
			<html:text property="fixedAssetList.des" styleId="fixedAssetList.des"></html:text>
		</td>
		<td class="nameCol">地址：</td>
		<td class="codeCol">
			<html:text property="fixedAssetList.address" styleId="fixedAssetList.address"></html:text>
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
