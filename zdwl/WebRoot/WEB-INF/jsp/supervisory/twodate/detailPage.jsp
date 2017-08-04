<%@page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ taglib uri="select.tld" prefix="select"%>


<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/calendar.js"></script>
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
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//执行返回列表操作
function doReturn() {
	location = "<url:context/>/twodate.do?method=findList";
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

	<div class="title">二网日查库</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/twodate.do" styleId="duedateForm" method="post" onsubmit="return false">
				<table class="formTable">
					<tr>
						<td class="nameCol">图片1：</td>
						<td class="codeCol">
							<a href="javascript:showDiv('<c:out value='${pic1}' />');">
								<form:img value="${file1}" height="80" width="120"></form:img>
							</a>
						</td>
						<td class="nameCol">图片2：</td>
						<td class="codeCol">
							<a href="javascript:showDiv('<c:out value='${pic2}' />');">
								<form:img value="${file2}" height="80" width="120"></form:img>
							</a>
						</td>
					</tr>
					<tr>
						<td class="nameCol">图片3：</td>
						<td class="codeCol">
							<a href="javascript:showDiv('<c:out value='${pic3}' />');">
								<form:img value="${file3}" height="80" width="120"></form:img>
							</a>
						</td>
						<td class="nameCol">图片4：</td>
						<td class="codeCol">
							<a href="javascript:showDiv('<c:out value='${pic4}' />');">
								<form:img value="${file4}" height="80" width="120"></form:img>
							</a>
						</td>
					</tr>
					<tr>
						<td class="nameCol">图片5：</td>
						<td class="codeCol">
							<a href="javascript:showDiv('<c:out value='${pic5}' />');">
								<form:img value="${file5}" height="80" width="120"></form:img>
							</a>
						</td>
						<td class="nameCol"></td>
						<td class="codeCol"></td>
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
					<tr class="formTableFoot">
						<td colspan="4" align="center">
							<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
						</td>
					</tr>
				</table>
				<br />
				<div class="message" id="message" style="display: none"></div>
			</html:form>

		</div>
	</div>
	<div id="pd_<c:out value='${pic1}'/>" style="display: none" class="dlDiv"> 
	 	<form:img value="${file1}" height="960px" width="1280px"/>
	</div> 
	<div id="overDiv_<c:out value='${pic1}'/>" style="display:none;" class="overDiv" onclick="closeDiv('<c:out value='${pic1}'/>')"></div>
	
	<div id="pd_<c:out value='${pic2}'/>" style="display: none" class="dlDiv"> 
	 	<form:img value="${file2}" height="960px" width="1280px"/>
	</div> 
	<div id="overDiv_<c:out value='${pic2}'/>" style="display:none;" class="overDiv" onclick="closeDiv('<c:out value='${pic2}'/>')"></div>
	
	<div id="pd_<c:out value='${pic3}'/>" style="display: none" class="dlDiv"> 
	 	<form:img value="${file3}" height="960px" width="1280px"/>
	</div> 
	<div id="overDiv_<c:out value='${pic3}'/>" style="display:none;" class="overDiv" onclick="closeDiv('<c:out value='${pic3}'/>')"></div>
	
	<div id="pd_<c:out value='${pic4}'/>" style="display: none" class="dlDiv"> 
	 	<form:img value="${file4}" height="960px" width="1280px"/>
	</div> 
	<div id="overDiv_<c:out value='${pic4}'/>" style="display:none;" class="overDiv" onclick="closeDiv('<c:out value='${pic4}'/>')"></div>
	
	<div id="pd_<c:out value='${pic5}'/>" style="display: none" class="dlDiv"> 
	 	<form:img value="${file5}" height="960px" width="1280px"/>
	</div> 
	<div id="overDiv_<c:out value='${pic5}'/>" style="display:none;" class="overDiv" onclick="closeDiv('<c:out value='${pic5}'/>')"></div>
</body>
</html>
