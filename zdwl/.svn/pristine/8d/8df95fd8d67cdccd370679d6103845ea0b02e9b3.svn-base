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
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//执行保存操作
function doSave(){
	var value = document.getElementById("fixedAssets.asset_num").value;
	if(value == ""){
		alert("请填写资产编码");
		return false;
	}
 	 var value = document.getElementById("fixedAssets.asset_type").value;
	if(value == "-1"){
		alert("请填写资产类别");
		return false;
	}  
	var value = document.getElementById("fixedAssets.asset_name").value;
	if(value == ""){
		alert("请填写资产名称");
		return false;
	}
	var value = document.getElementById("fixedAssets.asset_moeny").value;
	if(value == ""){
		alert("请填写资产原值");
		return false;
	}
	var value = document.getElementById("fixedAssets.amount").value;
	if(value == ""){
		alert("请填写数量");
		return false;
	}
	var value = document.getElementById("fixedAssets.purchase_date").value;
	if(value == ""){
		alert("请填写购置日期");
		return false;
	}
 /* 	var value = document.getElementById("fixedAssets.purchase_date").value;
	if(value == ""){
		alert("请填写已使用年限");
		return false;
	}  */
	var value = document.getElementById("fixedAssets.life").value;
	if(value == ""){
		alert("请填写资产年限");
		return false;
	}
 	 var value = document.getElementById("fixedAssets.asset_state").value;
	if(value == "-1"){
		alert("请填写资产状态");
		return false;
	}  
	
	var value = document.getElementById("fixedAssets.factory_date").value;
	if(value == ""){
		alert("请填写资产出厂派发时间");
		return false;
	}
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("fixedAssetsForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/fixedAssets.do?method=fixedAssetsList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
/* /* 完成需求32 easyUI下拉筛选控件 */
/*   $(function(){
	$("#fixedAssets\\.asset_state").combobox({
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
			} 
		}
	});
});   */
/* 完成需求32 easyUI下拉筛选控件 */
/*   $(function(){
	$("#fixedAssets\\.asset_type").combobox({
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
			} 
		}
	});
});   */
/* 完成需求32 easyUI下拉筛选控件 */
  $(function(){
	$("#fixedAssets\\.sendee").combobox({
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
			} 
		}
	});
});  
</script>
</head>
  
<body onLoad="doLoad()">
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">资产管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">固定资产管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">新增固定资产</a>
         </span>
</div>
<div class="title">新增固定资产</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/fixedAssets" styleId="fixedAssetsForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addFixedAssets">

<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>资产编码：</td>
		<td class="codeCol">
			<html:text property="fixedAssets.asset_num" styleId="fixedAssets.asset_num"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>资产类别：</td>
		<td class="codeCol">
			<!-- <form:select property="fixedAssets.asset_type" styleId="fixedAssets.asset_type">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="AssetsTypeOptions" label="label" value="value" />
			</form:select> -->
				<select id="fixedAssets.asset_type" style="width: 120px;"  name="fixedAssets.asset_type" >
										<option value="-1">请选择</option>
										<c:forEach items="${AssetsTypeOptions}" var="row">
											<option <c:if test="${fixedAssetsForm.fixedAssets.asset_type==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
										</c:forEach>
				</select>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>资产名称：</td>
		<td class="codeCol">
			<html:text property="fixedAssets.asset_name" styleId="fixedAssets.asset_name"></html:text>
		</td>
		<td class="nameCol">品牌：</td>
		<td class="codeCol">
			<html:text property="fixedAssets.brand" styleId="fixedAssets.brand"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">规格型号：</td>
		<td class="codeCol">
			<html:text property="fixedAssets.model" styleId="fixedAssets.model"></html:text>
		</td>
		<td class="nameCol">出厂编码：</td>
		<td class="codeCol">
			<html:text property="fixedAssets.factory_code" styleId="fixedAssets.factory_code"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>资产原值：</td>
		<td class="codeCol">
			<html:text property="fixedAssets.asset_moeny" styleId="fixedAssets.asset_moeny"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>数量：</td>
		<td class="codeCol">
			<html:text property="fixedAssets.amount" styleId="fixedAssets.amount"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>购置日期：</td>
		<td class="codeCol">
			<form:calendar property="fixedAssets.purchase_date" styleId="fixedAssets.purchase_date" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
		<!-- <td class="nameCol"><font color="#FF0000">*</font>已使用年限：</td>
		<td class="codeCol">
			<html:text property="fixedAssets.useful_life" styleId="fixedAssets.useful_life"></html:text>
		</td> -->
		<td class="nameCol">生产日期：</td>
		<td class="codeCol">
			<form:calendar property="fixedAssets.producedate" styleId="fixedAssets.producedate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>年限：</td>
		<td class="codeCol">
			<html:text property="fixedAssets.life" styleId="fixedAssets.life"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>资产状态：</td>
		<td class="codeCol">
			<!-- <form:select property="fixedAssets.asset_state" styleId="fixedAssets.asset_state">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="AssetsStateOptions" label="label" value="value" />
			</form:select> -->
			<select id="fixedAssets.asset_state" style="width: 120px;" name="fixedAssets.asset_state" >
										<option value="-1">请选择</option>
										<c:forEach items="${AssetsStateOptions}" var="row">
											<option <c:if test="${fixedAssetsForm.fixedAssets.asset_state==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
										</c:forEach>
							</select>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>出厂派发时间：</td>
		<td class="codeCol">
			<form:calendar property="fixedAssets.factory_date" styleId="fixedAssets.factory_date" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
		<td class="nameCol">快递公司：</td>
		<td class="codeCol">
			<html:text property="fixedAssets.express" styleId="fixedAssets.express"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">运单号：</td>
		<td class="codeCol">
			<html:text property="fixedAssets.express_num" styleId="fixedAssets.express_num"></html:text>
		</td>
		<td class="nameCol">店名：</td>
		<td class="codeCol">
			<html:text property="fixedAssets.trade_name" styleId="fixedAssets.trade_name"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">地址：</td>
		<td class="codeCol">
			<html:text property="fixedAssets.address" styleId="fixedAssets.address"></html:text>
		</td>
		<td class="nameCol">接收人：</td>
		<td class="codeCol">
			<!-- <form:select property="fixedAssets.sendee" styleId="fixedAssets.sendee">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="supervisorsOptions" label="label" value="value" />
			</form:select> -->
			<select id="fixedAssets.sendee" style="width: 120px;" name="fixedAssets.sendee" >
										<option value="-1">请选择</option>
										<c:forEach items="${supervisorsOptions}" var="row">
											<option <c:if test="${fixedAssetsForm.fixedAssets.sendee==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
										</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td class="nameCol">钥匙：</td>
		<td class="codeCol">
			<html:text property="fixedAssets.key" styleId="fixedAssets.key"></html:text>
		</td>
		<td class="nameCol">密码：</td>
		<td class="codeCol">
			<html:text property="fixedAssets.password" styleId="fixedAssets.password"></html:text>
		</td>
	</tr>
<%-- 	<tr>
		<td class="nameCol">生产日期：</td>
		<td class="codeCol">
			 <form:calendar property="fixedAssets.producedate" styleId="fixedAssets.producedate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" /> 
		</td>
		<td class="nameCol"></td>
		<td class="codeCol"></td>
	</tr> --%>
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
