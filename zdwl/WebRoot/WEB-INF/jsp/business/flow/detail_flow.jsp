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
<script src="/js/common.js"></script>
<script src="/js/img.js"></script>
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script src="/ue/ueditor.parse.js"></script>
<script type="text/javascript">
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

$(function(){
	uParse("#ueContent",{
	    rootPath: '/ue/'
	});
});
//执行返回列表操作
function doReturn(){
	history.back();
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">查看操作流程</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/flow" styleId="flowForm" method="post" onsubmit="return false">
<table class="formTable">
	<tr>
		<td colspan="4">
			<div style="width:100%;height:auto;">
				<div id="ueContent" style="min-height:300px; margin:0 20% 0 20%; border:1px solid lightgray; padding: 10px 0 0 20px;">
					<h1 align="center"><c:out value="${flowForm.flow.flowname }"></c:out></h1>
					<%=request.getAttribute("content")%>
				</div>
			</div>
		</td>
	</tr>
	<tr class="formTableFoot">
		<td colspan="4" align="center">
			<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
		</td>
	</tr>
</table>
<br/>

</html:form>

	</div>
</div>
<div id="pd_<c:out value='${pic}'/>" style="display: none" class="dlDiv"> 
 	<form:img value="${file}" height="960px" width="1280px"/>
</div> 
<div id="overDiv_<c:out value='${pic}'/>" style="display:none;" class="overDiv" onclick="closeDiv('<c:out value='${pic}'/>')"></div>
</body>
</html>
