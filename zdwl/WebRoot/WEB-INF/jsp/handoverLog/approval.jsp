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
<script src="/js/calendar.js"></script>
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<style type="text/css">
img{border:none;}
</style>
<style>   
.overDiv{
background-color: #000;
background-repeat:repeat;
width: 100%;
height: 100%;
left:0;
top:0;/*FF IE7*/
filter:alpha(opacity=40);/*IE*/
opacity:0.4;/*FF*/
z-index:1;
position:fixed!important;/*FF IE7*/
position:absolute;/*IE6*/
_top: expression(eval(document.compatMode &&
document.compatMode=='CSS1Compat') ?
documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/
document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);/*IE5 IE5.5*/
}
.dlDiv{
background-image:url(dlbj.jpg);
background-repeat:repeat-x;
border:2px solid #ffffff;
z-index:2;
left:38%;/*FF IE7*/
top:10%;/*FF IE7*/
margin-left:-150px!important;/*FF IE7 该值为本身宽的一半 */
margin-top:-60px!important;/*FF IE7 该值为本身高的一半*/
margin-top:0px;
position:fixed!important;/*FF IE7*/
position:absolute;/*IE6*/
_top:expression(eval(document.compatMode &&
document.compatMode=='CSS1Compat') ?
documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/
document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);/*IE5 IE5.5*/
}
</style>
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
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/handoverLog.do?method=handoverLogPageList";
}
//执行返回列表操作
function doSubmit(){
	if(confirm("确认提交？提交后交接书图片将不可编辑，进入审批流程")){
		var id=document.getElementsByName("hpic.id")[0].value;
		window.location="<url:context/>/handoverLog.do?method=updPicEditStatus&hpic.id="+id;
	}
}
//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
function setImagePreview(fileId) {
	var docObj = document.getElementById(fileId);
	var imgObjPreview = document.getElementById("preview");
	if(docObj.files && docObj.files[0]){
		//火狐下，直接设img属性 
		imgObjPreview.style.display = 'block';
		imgObjPreview.style.width = '300px';
		imgObjPreview.style.height = '120px';
		//imgObjPreview.src = docObj.files[0].getAsDataURL();
		//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
		imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
	}else{
		//IE下，使用滤镜
		docObj.select();
		//获取图片路径
		var imgSrc = document.selection.createRange().text;
		var localImagId = document.getElementById("localImag");
		//必须设置初始大小
		localImagId.style.width = "500px";
		localImagId.style.height = "300px";
		//图片异常的捕捉，防止用户修改后缀来伪造图片
		try{
			localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
		}catch(e){
			return false;
		}
		
		imgObjPreview.style.display = 'none';
		document.selection.empty();
	}
	return true;
}
function showDiv(id){
	document.getElementById("pd_"+id).style.display = "block" ;
	document.getElementById("overDiv_"+id).style.display = "block" ;
}

function closeDiv(id){
	document.getElementById("overDiv_"+id).style.display = "none" ;
	document.getElementById("pd_"+id).style.display = "none" ;
}

function agree(){
	$("#approvalState").val(1);
	doSave();
}

function disAgree(){
	$("#approvalState").val(2);
	doSave();
}
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">审批监管员交接书</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/handoverLog" styleId="handoverLogForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="approval">
<input type="hidden" name="query.id" value="<c:out value='${handoverLogForm.hpic.id }'/>"/>
<html:hidden property="query.approvalState" styleId="approvalState"/>
<table class="formTable">
	
	<tr>
		<td class="nameCol">图片一：</td>
		<td class="codeCol">
			<a href="javascript:showDiv('<c:out value='${picId1}' />');">
				<form:img value="${fixedpath1}" height="80" width="120"></form:img>
			</a>
		</td>
		<td class="nameCol">图片二：</td>
		<td class="codeCol">
			<a href="javascript:showDiv('<c:out value='${picId2}' />');">
				<form:img value="${fixedpath2}" height="80" width="120"></form:img>
			</a>
		</td>
	</tr>
	<tr>
		<td class="nameCol">图片三：</td>
		<td class="codeCol">
			<a href="javascript:showDiv('<c:out value='${picId3}' />');">
				<form:img value="${fixedpath3}" height="80" width="120"></form:img>
			</a>
		</td>
		<td class="nameCol">图片四：</td>
		<td class="codeCol">
			<a href="javascript:showDiv('<c:out value='${picId4}' />');">
				<form:img value="${fixedpath4}" height="80" width="120"></form:img>
			</a>
		</td>
	</tr>
	<tr>
		<td class="nameCol">图片五：</td>
		<td class="codeCol">
			<a href="javascript:showDiv('<c:out value='${picId5}' />');">
				<form:img value="${fixedpath5}" height="80" width="120"></form:img>
			</a>
		</td>
		<td class="nameCol">图片六：</td>
		<td class="codeCol">
			<a href="javascript:showDiv('<c:out value='${picId6}' />');">
				<form:img value="${fixedpath6}" height="80" width="120"></form:img>
			</a>
		</td>
	</tr>
	<tr>
		<td class="nameCol">图片七：</td>
		<td class="codeCol">
			<a href="javascript:showDiv('<c:out value='${picId7}' />');">
				<form:img value="${fixedpath7}" height="80" width="120"></form:img>
			</a>
		</td>
		<td class="nameCol">图片八：</td>
		<td class="codeCol">
			<a href="javascript:showDiv('<c:out value='${picId8}' />');">
				<form:img value="${fixedpath8}" height="80" width="120"></form:img>
			</a>
		</td>
	</tr>
	<tr>
		<td class="nameCol">图片九：</td>
		<td class="codeCol">
			<a href="javascript:showDiv('<c:out value='${picId9}' />');">
				<form:img value="${fixedpath9}" height="80" width="120"></form:img>
			</a>
		</td>
		<td class="nameCol">图片十：</td>
		<td class="codeCol">
			<a href="javascript:showDiv('<c:out value='${picId10}' />');">
				<form:img value="${fixedpath10}" height="80" width="120"></form:img>
			</a>
		</td>
	</tr>
	<tr>
		<td class="nameCol">备注：</td>
		<td class="codeCol">
			<html:textarea property="hpic.remark" styleId="hpic.remark" readonly="true"></html:textarea>
		</td>
	</tr>
		<c:if test="${approvals!=null }">
						<tr class="formTitle">
							<td colspan="4">部门意见</td>
						</tr>
						
						<logic:iterate name="approvals" id="row" indexId="index">
							<tr>
							<td class="nameCol">第<c:out value='${index+1 }'/>步</td>
							<td class="nameCol" style="text-align: left;">
								<c:out value="${row.roleName }"/>：
								<c:out value="${row.userName }"/>于<fmt:formatDate value="${row.remarkDate }" pattern="yyyy-MM-dd"/>
							</td>
							<td class="codeCol" colspan="2">
								<c:if test="${row.approvalResult==1}">同意&nbsp;</c:if>
								<c:if test="${row.approvalResult==2}">不同意&nbsp;</c:if>
								<c:out value="${row.remark }"/>
							</td>
						</tr>
						</logic:iterate>
					</c:if>
					
					<tr>
						<td class="nameCol">审批意见：</td>
						<td class="codeCol" colspan="3"><html:text style="width:700px" property="query.remark"></html:text></td>
					</tr>
					
					<tr class="formTableFoot">
						<td colspan="4" align="center">
							<button class="formButton" onClick="agree()">同&nbsp;意</button>&nbsp;
							<button class="formButton" onClick="disAgree()">不&nbsp;同&nbsp;意</button>&nbsp;
							<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
						</td>
					</tr>
</table>
<br/>
<div id="message" class="message" style="display:none"></div>
</html:form>
	</div>
</div>
<div id="pd_<c:out value='${picId1}'/>" style="display: none" class="dlDiv"> 
 	<form:img value="${fixedpath1}" height="960px" width="1280px"/>
</div> 
<div id="overDiv_<c:out value='${picId1}'/>" style="display:none;" class="overDiv" onclick="closeDiv('<c:out value='${picId1}'/>')"></div>

<div id="pd_<c:out value='${picId2}'/>" style="display: none" class="dlDiv"> 
 	<form:img value="${fixedpath2}" height="960px" width="1280px"/>
</div> 
<div id="overDiv_<c:out value='${picId2}'/>" style="display:none;" class="overDiv" onclick="closeDiv('<c:out value='${picId2}'/>')"></div>

<div id="pd_<c:out value='${picId3}'/>" style="display: none" class="dlDiv"> 
 	<form:img value="${fixedpath3}" height="960px" width="1280px"/>
</div> 
<div id="overDiv_<c:out value='${picId3}'/>" style="display:none;" class="overDiv" onclick="closeDiv('<c:out value='${picId3}'/>')"></div>

<div id="pd_<c:out value='${picId4}'/>" style="display: none" class="dlDiv"> 
 	<form:img value="${fixedpath4}" height="960px" width="1280px"/>
</div> 
<div id="overDiv_<c:out value='${picId4}'/>" style="display:none;" class="overDiv" onclick="closeDiv('<c:out value='${picId4}'/>')"></div>

<div id="pd_<c:out value='${picId5}'/>" style="display: none" class="dlDiv"> 
 	<form:img value="${fixedpath5}" height="960px" width="1280px"/>
</div> 
<div id="overDiv_<c:out value='${picId5}'/>" style="display:none;" class="overDiv" onclick="closeDiv('<c:out value='${picId5}'/>')"></div>

<div id="pd_<c:out value='${picId6}'/>" style="display: none" class="dlDiv"> 
 	<form:img value="${fixedpath6}" height="960px" width="1280px"/>
</div> 
<div id="overDiv_<c:out value='${picId6}'/>" style="display:none;" class="overDiv" onclick="closeDiv('<c:out value='${picId6}'/>')"></div>

<div id="pd_<c:out value='${picId7}'/>" style="display: none" class="dlDiv"> 
 	<form:img value="${fixedpath7}" height="960px" width="1280px"/>
</div> 
<div id="overDiv_<c:out value='${picId7}'/>" style="display:none;" class="overDiv" onclick="closeDiv('<c:out value='${picId7}'/>')"></div>

<div id="pd_<c:out value='${picId8}'/>" style="display: none" class="dlDiv"> 
 	<form:img value="${fixedpath8}" height="960px" width="1280px"/>
</div> 
<div id="overDiv_<c:out value='${picId8}'/>" style="display:none;" class="overDiv" onclick="closeDiv('<c:out value='${picId8}'/>')"></div>

<div id="pd_<c:out value='${picId9}'/>" style="display: none" class="dlDiv"> 
 	<form:img value="${fixedpath9}" height="960px" width="1280px"/>
</div> 
<div id="overDiv_<c:out value='${picId9}'/>" style="display:none;" class="overDiv" onclick="closeDiv('<c:out value='${picId9}'/>')"></div>

<div id="pd_<c:out value='${picId10}'/>" style="display: none" class="dlDiv"> 
 	<form:img value="${fixedpath10}" height="960px" width="1280px"/>
</div> 
<div id="overDiv_<c:out value='${picId10}'/>" style="display:none;" class="overDiv" onclick="closeDiv('<c:out value='${picId10}'/>')"></div>

</body>
</html>
