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
<link href="/css/base.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/css/fileupload/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="/css/fileupload/jquery.fileupload.css" type="text/css" />
<link rel="stylesheet" href="/css/fileupload/jquery.fileupload-ui.css" type="text/css" />
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<!--  图片上传-->
<%@ include file="../../jsp/base/handoverFileupload.jsp"%>
<script type="text/javascript">
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//执行保存操作
function doSave(){

	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("handoverLogForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[1].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/handoverLog.do?method=handoverLogPageList";
}


//执行返回列表操作
function doSubmit(HPICid){
	
	var deletes = $("input[name='delete']");
	if(deletes.length <= 0){
		showMessage("请先上传交接书图片再提交");
		return;
	}
	
	if(confirm("确认提交？提交后交接书图片将不可编辑，进入审批流程")){
		var id=getElement("hpicId").value;
		if(id==null||id==""){
			id=HPICid;
		}
		window.location="<url:context/>/handoverLog.do?method=updPicEditStatus&hpic.id="+id;
	}
}


function isEdit(value){
	if(value == 1){
		showMessage("已经进入审批流程，不可删除");
	}


}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
$(function() {
	$('#tt').tabs('disableTab', 1);
	$('#tt').tabs('disableTab', 2);
	$('#tt').tabs('disableTab', 3);
	$('#tt').tabs('disableTab', 4);
	$('#tt').tabs('disableTab', 5);
	$('#tt').tabs('disableTab', 6);
});
</script>
</head>
<body onLoad="doLoad()">
	<div class="title">上传/修改交接书图片</div>

	<div id="tt" class="easyui-tabs" style="width: 100%; height: 100%;">
		<div style="padding: 20px; display: none;">
			<div class="add_nav" style="margin-top: -50px;">
				<span class="add_nav_acolor"> <a class="crumbs-link" style="color: #929291;" href=" ">监管员信息管理</a> &gt; <a class="crumbs-link" style="color: #929291;" href="#">交接记录管理</a> &gt; <a class="crumbs-link" style="color: #929291;" href="#">上传交接书图片</a>
				</span>
			</div>
			<div style="margin-left: 18px;">
				<form id="fileupload" action="/handoverLog.do?method=addHandoverPic" method="POST" enctype="multipart/form-data" class="">
					<c:choose>
						<c:when test="${isEdit == 0}">
							<div class="row fileupload-buttonbar">
								<div class="col-lg-7">
									<span class="btn btn-success fileinput-button"> <i class="glyphicon glyphicon-plus"></i> <span>添加文件</span> <input type="file" name="fileNNT" multiple='' /></span> 
									&nbsp;&nbsp;&nbsp;
									<button type="submit" class="btn btn-primary start">
										<i class="glyphicon glyphicon-upload"></i> <span>开始上传</span>
									</button>
									&nbsp;&nbsp;&nbsp;
									<button type="reset" class="btn btn-warning cancel">
										<i class="glyphicon glyphicon-ban-circle"></i> <span>取消上传</span>
									</button>
									<span class="fileupload-process"></span>
								</div>
								<div class="col-lg-5 fileupload-progress fade">
									<div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
										<div class="progress-bar progress-bar-success" style="width: 0%;"></div>
									</div>
									<div class="progress-extended">&nbsp;</div>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<tr>
								<div colspan="4" class="message" style="text-align: center;">已经进入审批流程，不可编辑。</div>
						</c:otherwise>
					</c:choose>


					<table role="presentation" class="table table-striped">
						<tbody class="files">
						</tbody>
					</table>
					<input id="hpicId" type="hidden" name="hpicId" value="<c:out value='${hpic.id }'/>" />
				</form>
				<html:form action="/handoverLog" styleId="handoverLogForm" method="post" onsubmit="return false">
					<input type="hidden" name="method" id="method" value="upLoadHandoverBookPic">
					<html:hidden property="handoverLog.id" styleId="handoverLog.id" />
					<html:hidden property="hpic.id" styleId="hpic.id" />
					<tr align="center">
						<td class="nameCol">备注：</td>
						<td class="codeCol" align="center"><html:textarea property="hpic.remark" styleId="hpic.remark" style="width: 40%;height: 10%;"></html:textarea></td>
						<button class="formButton" onClick="doSave()" style="width: 5%; margin-top: 20px; margin-left: 60px; position: absolute;">保&nbsp;存</button>
						&nbsp;
					</tr>

				</html:form>
				<br /> <br />
				<div colspan="4" align="center">
					<c:choose>
						<c:when test="${isEdit == 0}">
							<button class="formButton" onClick="doSubmit(<c:out value='${hpic.id }'/>)">提&nbsp;交</button>&nbsp;
				</c:when>
					</c:choose>
					<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
				</div>
			</div>
			<%@include file="../base/fileuploadShow.jsp"%>
		</div>
	</div>

	<div id="message" class="message" style="display: none"></div>
</body>
</html>
