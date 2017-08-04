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

<%@ page import="com.zd.tools.taglib.form.FormTagConstants"%>
<%@ page import="com.zd.csms.supervisorymanagement.model.LeaveReplaceDynamicVO" %>
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
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<c:set var="CalendarScript" value="true"/>
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
left:25%;/*FF IE7*/
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
//执行返回列表操作
function doReturn(){
	location = "<url:context/>/resignApply.do?method=findPageList";
}
function showDiv(){
	document.getElementById("pd").style.display = "block" ;
	document.getElementById("overDiv").style.display = "block" ;
}

function closeDiv(){
	document.getElementById("overDiv").style.display = "none" ;
	document.getElementById("pd").style.display = "none" ;
}
</script>
</head>
  
<body>

<div class="title">辞职申请详情</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/resignApply" styleId="resignApplyForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="detailPage"/>
<table class="formTable">
	<tr>
		<td colspan="4"> 
			<table style="width: 100%">
				<tr>
					<td class="nameCol">申请人工号：</td>
					<td class="codeCol" >
						<html:text  property="resignApply.staffNo" styleId="resignApply.staffNo" readonly="true"/>
					</td>
					<td class="nameCol">申请人：</td>
					<td class="codeCol" >
						<html:text  property="resignApply.name" styleId="resignApply.name"  readonly="true"/>
					</td>
					<td class="nameCol" >身份证号：</td>
					<td class="codeCol" >
						<html:text  property="resignApply.idNumber" styleId="resignApply.idNumber"  readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol" >属性：</td>
					<td class="codeCol" colspan="5">
						<html:text style="width:80%" property="resignApply.attribute" styleId="resignApply.attribute"  readonly="true"/>
					</td>
				</tr>
				<c:if test="${currentDealerList!=null}">
					<logic:iterate name="currentDealerList" id="row" indexId="index" >
						<tr>
							<td class="nameCol" style="width:15%">经销商<c:out value="${index+1}" />：</td>
							<td class="codeCol" style="width:15%">
								<c:out value='${row.dealerName}' />
							</td>
							<td class="nameCol" style="width:15%">金融机构<c:out value="${index+1}" />：</td>
							<td class="codeCol" style="width:15%">
								<c:out value='${row.bankName}' />
							</td>
							<td class="nameCol" style="width:15%">品牌<c:out value="${index+1}" />：</td>
							<td class="codeCol" style="width:15%">
								<c:out value='${row.brandName}' />
							</td>
						</tr>
					</logic:iterate>
				</c:if>
				<tr>
					<td class="nameCol" >申请时间：</td>
					<td class="codeCol" >
						<c:out value="${applyTime }" />
					</td>
					<td class="nameCol" >期望离职日期：</td>
					<td class="codeCol" >
						<form:calendar style="width:80%" property="resignApply.expectResignTime" styleId="expectResignTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" disabled="true" />
					</td>
					<td class="nameCol" ></td>
					<td class="codeCol" ></td>
				</tr>
				
				<tr>
					<td class="nameCol" colspan="2">离职后联系方式(请真实填写)：</td>
					<td class="nameCol">手机号：</td>
					<td class="codeCol" >
						<html:text  property="resignApply.mobile" styleId="mobile" readonly="true"/>
					</td>
					<td class="nameCol" >邮箱：</td>
					<td class="codeCol" >
						<html:text  property="resignApply.email" styleId="email" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol">离职原因：</td>
					<td class="codeCol" colspan="5">
						<html:textarea style="width:80%" property="resignApply.resignReason" styleId="resignReason" readonly="true"/>
					</td>
				</tr>
			</table>
		</td>	
	</tr>
	<tr>
		<td colspan="4">
			<table style="width: 100%">
				<td class="nameCol"></td>
					<td class="nameCol"></td>
					<td class="nameCol"></td>
					<td class="nameCol"></td>
					<td class="nameCol"></td>
					<td class="nameCol"></td>
				<tr>
					<td class="nameCol">经销商意见:</td>
					<td class="codeCol" colspan="5">
						<a href="javascript:showDiv();">
							<img alt="照片" src="/showImg.do?method=showImg&filePath=<c:out value='${filepath}'/>" height="180" width="240"/>
						</a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4" style="color: red;text-align: center;">
			注：辞职申请需提前一个月申请，审批通过后按排交接工作，工作交接情况见《交接表》。
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

</html:form>
</div>
</div>
<div id="pd" style="display: none" class="dlDiv"> 
 	<img alt="" src="/showImg.do?method=showImg&filePath=<c:out value='${filepath}'/>" height="680" width="1000"/>
</div> 
<div id="overDiv" style="display:none;" class="overDiv" onclick="closeDiv()">
</div>
</body>
</html>
