<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="file.tld" prefix="file"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ taglib uri="select.tld" prefix="select"%>

<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.csms.constants.CacheStorageNameConstant"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>选择二网名称</title>
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
	var ta = $("#superviseImport\\.two_name").combobox('getValue');
	if(!ta){
		alert("请选择二网名称");
		return false;
	}
	if (confirm("确认移动？")) {
		//var ta = document.getElementById("superviseImport.two_name").value;
		
	 	if(ta > 0){
	 		window.parent.closeDialog(ta);
	 	}
	}
}

function init() {
	$("#superviseImport\\.two_name").combobox({
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

}

$(function(){
	init();
});
</script>
</head>
<body>
<div class="title">选择二网名称</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
		<html:form action="/superviseMove" styleId="superviseImportForm" method="post" onsubmit="return false">
		<input type="hidden" id="method" name="method" value="superviseMoveList">
		<table class="formTable">
			<tr>
				<td class="nameCol">二网名称：</td>
	  			<td class="codeCol">
	  				<%-- <form:select property="superviseImport.two_name" styleId="superviseImport.two_name">
						<html:option value="-1">请选择</html:option>
						<html:optionsCollection name="twoAddressOptions" label="label" value="value" />
					</form:select> --%>
					<select style="width:50%" id="superviseImport.two_name" name="superviseImport.two_name" >
						<c:forEach items="${twoAddressOptions }" var="row">
						
							<option value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
						</c:forEach>
					</select>
	  			</td>
			</tr>
			<tr class="formTableFoot">
				<td colspan="3" align="center">
					<button class="formButton" onClick="doSave()">提&nbsp;交</button>&nbsp;
				</td>
			</tr>
		</table>
		</html:form>
	</div>
</div>
</body>
</html>

