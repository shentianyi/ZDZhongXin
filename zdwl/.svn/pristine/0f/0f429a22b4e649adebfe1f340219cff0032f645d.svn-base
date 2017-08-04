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
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>

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

function initTools(){
	$("#expectResignTime").datebox({    
		required: true,    
	    showSeconds: false,
	    spinnerWidth:'80%',
	    editable:false
	});   
	var expectResignTime="<%=request.getAttribute("expectResignTime")%>";
	if(expectResignTime && expectResignTime!="null" && expectResignTime!="" ){
		$('#expectResignTime').datebox('setValue',expectResignTime); 
	}
	var c = $('#expectResignTime').datebox('calendar');
	var applyTime = $("#applyTime").val();
	applyTime = new Date(applyTime);
	applyTime = new Date(applyTime.getTime()+30*24*60*60*1000);
	c.calendar({
		validator: function(date){
			 if(date>applyTime){
				return true;
			} 
			return false;
		}
	});
}

$(function(){
	//initTools();
	 var message="<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>";
		if(message!=null&&message!=""&&message!="null"){
			alert(message);
		} 
});

//执行保存操作
function doSave(){
//	var value=$("#expectResignTime").datebox('getValue');
	var value=$("#expectResignTime").val();
	if(value==null || value==""){
		alert("请选择期望离职日期！");
		return false;
	}
	
	var value=$("#mobile").val();
	if(value==null || value==""){
		alert("请输入手机号码！");
		return false;
	}else if(!(/^1[34578]\d{9}$/.test(value))){ 
        alert("手机号码有误，请重新输入！");  
        return false; 
    } 
	
	var value=$("#email").val();
	if(value==null || value==""){
		alert("请输入邮箱！");
		return false;
	}else if(!(/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(value))){ 
        alert("邮箱格式有误，请重新输入！");  
        return false; 
    } 
	
	var value= document.getElementsByName("resignApply.resignReason")[0].value;
	if(value==null || value==""){
		alert("请填写离职原因！");
		return false;
	}
	document.forms[0].submit();
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/resignApply.do?method=findPageList";
}

function doSubmit(){
	if(confirm("提交后不可修改,确定要提交吗？")){
		$("#method").val("submit");
		doSave();
	}
	/* var id =$("#id").val();
	if(id<=0){
		alert("请先保存辞职信息再提交！");
	}else {
		if(confirm("确定要提交吗？")){
			location.href="<url:context/>/resignApply.do?method=submit&resignApply.id="+id;
		}
	} */
}
function doDelete(){
	var id =$("#id").val();
	if(id<=0){
		alert("您还未保存辞职信息！");
	}else {
		if(confirm("确定要删除吗？")){
			location.href="<url:context/>/resignApply.do?method=deleteResign&resignApply.id="+id;
		}
	}
}
//执行表单重置操作
function doReset(){
	document.forms[0].reset();
	initTools();
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
<style type="text/css">
.formTable2{width: 100%;}
.formTable2 tr{
    width: 100%; height: 40px; border: 1px solid #eee;
}
.formTable2 td,.formTable2 td.nameC{
    width: 16.6%;
    min-width:145px;
}
.formTable2 td.nameC{
text-align: right;
}
.formTable2 td.nameC, .formTable2 td.codeC{
    border-bottom: solid 1px #eee;
    border-right: solid 1px #eee; 
    padding-bottom: 10px;
    padding-top: 10px;  
}
</style>
</head>
  
<body>

<div class="title">辞职申请</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/resignApply" styleId="resignApplyForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="addResign"/>
<html:hidden property="resignApply.id" styleId="id"/>
<html:hidden property="resignApply.repositoryId" styleId="repositoryId"/>
<html:hidden property="resignApply.status" styleId="status"/>
<html:hidden property="resignApply.applyTime" styleId="applyTime"/>
<html:hidden property="resignApply.nextApproval" styleId="nextApproval"/>
<html:hidden property="resignApply.approvalState" styleId="approvalState"/>
<html:hidden property="resignApply.createUser" styleId="createUser"/>
<html:hidden property="resignApply.createTime" styleId="createTime"/>
<html:hidden property="resignApply.pictureId" styleId="pictureId"/>
<table style="width:100%" >
	<tr>
		<td colspan="4"> 
			<table class="formTable2" style="width: 100%">
				<tr>
					<td class="nameC" >申请人工号：</td>
					<td class="codeC" >
						<html:text  property="resignApply.staffNo" styleId="resignApply.staffNo" readonly="true"/>
					</td>
					<td class="nameC" >申请人：</td>
					<td class="codeC" >
						<html:text  property="resignApply.name" styleId="resignApply.name"  readonly="true"/>
					</td>
					<td class="nameC" >身份证号：</td>
					<td class="codeC" >
						<html:text  property="resignApply.idNumber" styleId="resignApply.idNumber"  readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="nameC" width:"100">属性：</td>
					<td class="codeC" colspan="5"  >
						<html:text style="width:80%" property="resignApply.attribute" styleId="resignApply.attribute"  readonly="true"/>
					</td>
				</tr>
				<c:if test="${currentDealerList!=null}">
					<logic:iterate name="currentDealerList" id="row" indexId="index" >
						<tr>
							<td class="nameC" >经销商<c:out value="${index+1}" />：</td>
							<td class="codeC" >
								<c:out value='${row.dealerName}' />
								<%-- <html:text  property="row.dealerName" readonly="true" value=""/> --%>
							</td>
							<td class="nameC" >金融机构<c:out value="${index+1}" />：</td>
							<td class="codeC" >
								<c:out value='${row.bankName}' />
								<%-- <html:text  property="row.bankName" readonly="true" value=""/> --%>
							</td>
							<td class="nameC" >品牌<c:out value="${index+1}" />：</td>
							<td class="codeC" >
								<c:out value='${row.brandName}' />
								<%-- <html:text  property="row.brandName" readonly="true" value=""/> --%>
							</td>
						</tr>
					</logic:iterate>
				</c:if>
				<tr>
					<td class="nameC"  >申请时间：</td>
					<td class="codeC" >
						<%-- <html:text  property="s" readonly="true" value="" /> --%><c:out value='${applyTime }' />
					</td>
					<td class="nameC" ><font color="#FF0000">*</font>期望离职日期：</td>
					<td class="codeC" >
						 <form:calendar style="width:80%" property="resignApply.expectResignTime" styleId="expectResignTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" /> 
						<%--<input  id="expectResignTime" name="resignApply.expectResignTime"  type="text" ></input>--%>
					</td>
					<td class="nameC" ></td>
					<td class="codeC" >&nbsp;</td>
				</tr>
				
				<tr>
					<td class="nameC" colspan="2" style="width:30%"><font color="#FF0000">*</font>离职后联系方式(请真实填写)：</td>
					<td class="nameC" ><font color="#FF0000">*</font>手机号：</td>
					<td class="codeC" >
						<html:text  property="resignApply.mobile" styleId="mobile" />
					</td>
					<td class="nameC" ><font color="#FF0000">*</font>邮箱：</td>
					<td class="codeC" >
						<html:text  property="resignApply.email" styleId="email" />
					</td>
				</tr>
				<tr>
					<td class="nameC"><font color="#FF0000">*</font>离职原因：</td>
					<td class="codeC" colspan="5">
						<html:textarea style="width:80%" property="resignApply.resignReason" styleId="resignReason"/>
					</td>
				</tr>
			</table>
		</td>	
	</tr>
	<tr>
		<td colspan="4">
			<table class="formTable2" style="width: 100%">
				<tr>
					<td class="nameC" style="width: 15%">经销商意见:</td>
					<td class="codeC" colspan="5" style="width: 85%">
						<a href="javascript:showDiv();">
							<img alt="照片" src="/showImg.do?method=showImg&filePath=<c:out value='${filepath}'/>" height="180" width="240"/>
						</a>
						上传扫描件：<input type="file"  name="dealerPicture" id="dealerPicture"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4" >
			<table class="formTable2" style="width: 100%">
				<tr>
					<td class="nameC" style="width: 15%">审批状态:</td>
					<td class="codeC" style="width: 18%">
						<c:if test="${resignApplyForm.resignApply.approvalState==0 }">
							未提交
						</c:if>
						<c:if test="${resignApplyForm.resignApply.approvalState==1 }">
							审批通过
						</c:if>
						<c:if test="${resignApplyForm.resignApply.approvalState==2 }">
							审批不通过
						</c:if>
						<c:if test="${resignApplyForm.resignApply.approvalState==3 }">
							正在审批
						</c:if>
					</td>
					<c:if test="${resignApplyForm.resignApply.approvalState==3 }">
						<td class="nameC" style="width: 15%">下一审批人:</td>
						<td class="codeC" colspan="3" style="width: 52%">
							<select:nextApprovalName roleId="${resignApplyForm.resignApply.nextApproval }"></select:nextApprovalName>
						</td>
					</c:if>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4" style="color: red;text-align: center;">
			注：辞职申请需提前一个月申请，审批通过后按排交接工作，工作交接情况见《交接表》。
		</td>
	</tr>
	<c:if test="${approvals!=null && approvalSize>0}">
		<tr class="formTitle">
			<td colspan="4">部门意见</td>
		</tr>
		
		<logic:iterate name="approvals" id="row" indexId="index">
			<tr>
				<td class="nameC">第<c:out value='${index+1 }'/>步</td>
				<td class="nameC" style="text-align: left;">
					<c:out value="${row.roleName }"/>：
					<c:out value="${row.userName }"/>于<fmt:formatDate value="${row.remarkDate }" pattern="yyyy-MM-dd"/>
				</td>
				<td class="codeC" colspan="2">
					<c:out value="${row.remark }"/>
				</td>
			</tr>
		</logic:iterate>
	</c:if>	
	<tr class="formTableFoot">
		<td colspan="4" align="center">
			<c:if test="${resignApplyForm.resignApply.status==0}">
				<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
				<button class="formButton" onClick="doReset()">重&nbsp;置</button>&nbsp;
				<button class="formButton" onClick="doSubmit()">保存并提交</button>&nbsp;
			</c:if>
			<c:if test="${resignApplyForm.resignApply.status==0 && resignApplyForm.resignApply.id>0}">
				<button class="formButton" onClick="doDelete()">删&nbsp;除</button>&nbsp;
			</c:if>
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
