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

$(function(){
	var arry=document.getElementsByName("leaveApply.isReplace");
	var value="";
	for(var i=0;i<arry.length;i++){
		if(arry[i].checked){
			value=arry[i].value;
		}
	}
	changeIsReplace(value);
	
});
//执行删除行操作，删除行时触发调用
function doDel(){
	//此处为示例，用于示例。
}
//执行返回列表操作
function doReturn(){
	location = "<url:context/>/leaveApply.do?method=findPageList";
}

function changeReplaceSupervisory(obj){
	var repositoryId = obj.value;
	var n = $(obj).closest("tr").find("td:first").text();
	n = parseInt(n)-1;
	cleanReplaceSupervisory(n);
	if(repositoryId==-1){
		return;
	}
	var url = "../json/getSupervisorByRepositoryId.do?callback=?&id="+repositoryId;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setReplaceSupervisory(data[0],n);
	});
}
function cleanReplaceSupervisory(i){
	document.getElementsByName("leaveReplaceDynamicList["+i+"].leaveReplaceName")[0].value="";
	document.getElementsByName("leaveReplaceDynamicList["+i+"].leaveReplaceDealer")[0].value="";
}
function setReplaceSupervisory(supervisor,i){
	document.getElementsByName("leaveReplaceDynamicList["+i+"].leaveReplaceName")[0].value=supervisor.name;
	document.getElementsByName("leaveReplaceDynamicList["+i+"].leaveReplaceDealer")[0].value=supervisor.dealerName;
}

function changeIsReplace(isReplace){
	if(isReplace==1){
		$("#replaceList").show();
	}else if(isReplace==2){
		$("#replaceList").hide();
		
	}
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

<div class="title">请假申请</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/leaveApply" styleId="leaveApplyForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="detailPage"/>
<html:hidden property="leaveApply.leavePerson" styleId="leavePerson"/>
<html:hidden property="leaveApply.status" styleId="leaveApply.status" />
<html:hidden property="leaveApply.applyTime" styleId="leaveApply.applyTime"/>
<html:hidden property="leaveApply.id" styleId="id"/>
<html:hidden property="leaveApply.nextApproval" styleId="nextApproval"/>
<html:hidden property="leaveApply.approvalState" styleId="approvalState"/>
<html:hidden property="leaveApply.createUser" styleId="createUser"/>
<html:hidden property="leaveApply.createTime" styleId="createTime"/>
<html:hidden property="leaveApply.picture" styleId="picture"/>
<table class="formTable">
	<tr>
		<td colspan="4"> 
			<table style="width: 100%">
				<tr>
					<td class="nameCol">申请人工号</td>
					<td class="codeCol" >
						<html:text  property="leaveApply.leavePersonStaffNo" styleId="leaveApply.leavePersonStaffNo" readonly="true"/>
					</td>
					<td class="nameCol">申请人</td>
					<td class="codeCol" ><c:out value="${leavePerson }" /></td>
					<td class="nameCol" >申请时间：</td>
					<td class="codeCol" >
						<c:out value="${applyTime }" />
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
							<td class="codeCol" style="width:25%">
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
					<td class="nameCol">假别</td>
					<td class="codeCol" colspan="5">
						<form:radios  property="leaveApply.leaveType" styleId="leaveApply.leaveType" collection="leaveTypes" disabled="true"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol">请假事由</td>
					<td class="codeCol" colspan="5">
						<html:text style="width:80%" property="leaveApply.leaveReason" styleId="leaveApply.leaveReason"  disabled="true"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol" rowspan="4" style="text-align: right;">休假期间工作安排</td>
					<td class="nameCol" colspan="2">
						 自 <c:out value="${leaveStartTime }" />       
						 至  <c:out value="${leaveEndTime }" />  止
					</td>
					<td class="nameCol">天数：</td>
					<td class="codeCol" colspan="2">
						<input type="text" id="leaveDays" name="leaveApply.leaveDays"  value="<c:out value='${leaveApplyForm.leaveApply.leaveDays}'/>"  disabled="true"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol"colspan="2" >车辆合格证是否全锁入保险柜：</td>
					<td class="codeCol" colspan="3">
						<html:text  style="width:90%" property="leaveApply.certificate" styleId="leaveApply.certificate"  disabled="true"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol"colspan="2" >车辆钥匙是否锁入保险柜：</td>
					<td class="codeCol" colspan="3">
						<html:text style="width:90%" property="leaveApply.key" styleId="leaveApply.key"  disabled="true"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol"colspan="2" >保险柜钥匙是否妥善保存：</td>
					<td class="codeCol" colspan="3">
						<html:text style="width:90%" property="leaveApply.safetyBox" styleId="leaveApply.safetyBox"  disabled="true"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol">是否替岗</td>
					<td class="codeCol" colspan="5">
						<form:radios  property="leaveApply.isReplace" styleId="isReplace" collection="isReplace"   disabled="true"/>
					</td>
				</tr>	
			</table>
		</td>	
	</tr>
	<tr>
		<td class="nameCol" colspan="4" style="text-align: center;">替岗人列表</td>
	</tr>
	<tr id="replaceList" hidden="true">
		<td colspan="4">
			<div class="dvScroll" style="width:80%" >
					<table class="listTalbe"  cellspacing="0" cellpadding="0" >
						<thead >
							<tr class="title">
								<th>序号</th>
								<td><thumbpage:order cname="替岗人工号" /></td> 
								<td><thumbpage:order cname="替岗人姓名" /></td> 
								<td><thumbpage:order cname="替岗人经销店" /></td> 
								<td><thumbpage:order cname="替岗开始时间" /></td> 
								<td><thumbpage:order cname="替岗结束时间" /></td> 
								<td><thumbpage:order cname="替岗天数"  /></td> 
							</tr>
						</thead>
						<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
							<logic:iterate name="leaveReplaceDynamicList" id="row" indexId="index" >
								<tr class="listTr_a">
									<td align="center">&nbsp;<c:out value="${index+1}" /></td>
									<td >
										<select:supervisorName repositoryId="${row.replaceSupervisory}" idtype="staffNo" supervisorId="0"/>
									</td>
									<td >&nbsp;<c:out value="${row.leaveReplaceName}" /></td>
									<td >&nbsp;<c:out value="${row.leaveReplaceDealer}" /></td>
									
									<td >
										<select:timestamp timestamp="${row.replaceStartTime}" idtype="mm" />
									</td>
									<td >
										<select:timestamp timestamp="${row.replaceEndTime}" idtype="mm" />
									</td>
									<td >
										<c:out value="${row.replaceDays}" />
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
			</div>
		</td>
	</tr>
	
	<tr>
		<td colspan="4">
			<table style="width: 100%">
				<tr>
					<td class="nameCol"></td>
					<td class="nameCol"></td>
					<td class="nameCol"></td>
					<td class="nameCol"></td>
					<td class="nameCol"></td>
					<td class="nameCol"></td>
				</tr>
				<tr>
					<td class="nameCol">经销商意见</td>
					<td class="codeCol" colspan="5">
						<a href="javascript:showDiv();">
							<img alt="照片" src="/showImg.do?method=showImg&filePath=<c:out value='${filepath}'/>" height="180" width="240"/>
						</a>
					</td>
				</tr>
			</table>
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
