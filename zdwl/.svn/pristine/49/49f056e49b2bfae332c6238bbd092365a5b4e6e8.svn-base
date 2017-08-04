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
	document.forms[0].submit();
}

//执行返回列表操作
function doReturn(){
	var fid = document.getElementById("fid").value;
	location = "<url:context/>/ledger/fixedAssetList.do?method=fixedAssetList&fid="+fid;
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

</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">固定资产使用情况</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/ledger/fixedAssetList" styleId="fixedAssetListForm" method="post" onsubmit="return false">
<html:hidden property="fixedAssetList.id" styleId="fixedAssetList.id"/>
<input type="hidden" name="method" id="method" value="detailFixedAssetList">
<input type="hidden" id="fid" name="fid" value="<c:out value='${fid}'/>">

<table class="formTable">
	<tr>
		<td class="nameCol">使用部门：</td>
		<td class="codeCol">
			<html:text property="fixedAssetList.department" styleId="fixedAssetList.department"></html:text>
		</td>
		<td class="nameCol">使用人：</td>
		<td class="codeCol">
			<html:text property="fixedAssetList.username" styleId="fixedAssetList.username"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">存放店：</td>
		<td class="codeCol">
			<html:text property="fixedAssetList.trade" styleId="fixedAssetList.trade"></html:text>
		</td>
		<td class="nameCol">存放地址(省)：</td>
		<td class="codeCol">
			<%-- <html:text property="fixedAssetList.trade_province" styleId="fixedAssetList.trade_province"></html:text> --%>
		    <c:out value="${province }"></c:out>
		</td>
	</tr>
	<tr>
		<td class="nameCol">存放地址(市)：</td>
		<td class="codeCol">
			<%-- <html:text property="fixedAssetList.trade_city" styleId="fixedAssetList.trade_city"></html:text> --%>
		    <c:out value="${city }"></c:out>
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
		<td class="nameCol">存放时间：</td>
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
			<a href="javascript:showDiv('<c:out value='${pic}' />');">
				<form:img value="${file}" height="80" width="120"></form:img>
			</a>
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
