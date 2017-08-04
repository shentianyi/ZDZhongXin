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
<%@ page import="com.zd.tools.validate.model.DemoValidate" %>
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
<script src="/js/dynamic/dynamic_table.js"></script>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
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
	//初始化动态新增行对象
	initializeTable("demoTable",doDel,addCallBack);
	
	var arry=document.getElementsByName("leaveApply.isReplace");
	var value="";
	for(var i=0;i<arry.length;i++){
		if(arry[i].checked){
			value=arry[i].value;
		}
	}
	changeIsReplace(value);
	
	/* init(); */
	initTools();
	initCombobox();
	
});

function initCombobox(){
	var comboboxs =  $("select[name$='replaceSupervisory']");
	//初始化时间插件
	var dateOptions={    
			editable:false,
		    required: true,
		    showSeconds: false
		};
	comboboxs.each(function(rowNum){
		$(this).combobox({
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
				} else {
					changeReplaceSupervisory(newValue,rowNum);
					$("input[name$='["+rowNum+"].stateFlag']").val(updFlag);
				}
			}
		});
		var supervisoryId = $(this).combobox('getValue');
		if (supervisoryId) {
			changeReplaceSupervisory(supervisoryId,rowNum);
		}
	});
	
	$("input[name$='replaceStartTime'],input[name$='replaceEndTime']").each(function(rowNum){
		$("input[name$='["+rowNum+"].stateFlag']").val(updFlag);
		$(this).datetimebox(dateOptions);
		$(this).datetimebox(dateOptions);
	});
	
}

function addCallBack(rowNum){
	var replacePerson=$(getElement("leaveReplaceDynamicList["+rowNum+"].replaceSupervisory"));
	replacePerson.combobox({
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
			} else {
				changeReplaceSupervisory(newValue,rowNum);
			}
		}
	});
	var supervisoryId = replacePerson.combobox('getValue');
	if (supervisoryId) {
		changeReplaceSupervisory(supervisoryId,rowNum);
	}
	var nowDate = new Date();
	//初始化时间插件
	var dateOptions={    
			editable:false,
		    required: true,
		    showSeconds: false,
		    value:nowDate.getFullYear()+"-"+nowDate.getMonth()+"-"+nowDate.getDate()+" "+nowDate.getHours()+":"+nowDate.getMinutes()
		};
	var startTime = getElement("leaveReplaceDynamicList["+rowNum+"].replaceStartTime");
	var endTime = getElement("leaveReplaceDynamicList["+rowNum+"].replaceEndTime");
	$(startTime).datetimebox(dateOptions);
	$(endTime).datetimebox(dateOptions);
}

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
function initTools(){
	var now = new Date().Format("yyyy-MM-dd");
	$('#leaveStartTime').datetimebox({    
	    required: true,    
	    showSeconds: false,
	    spinnerWidth:'80%',
	    editable:false
	});
	var c1 = $('#leaveStartTime').datebox('calendar');
	c1.calendar({
		validator: function(date){
			var date=date.Format("yyyy-MM-dd");
			 if(date>=now){
				return true;
			} 
			return false;
		}
	});
	$('#leaveEndTime').datetimebox({    
	    required: true,    
	    showSeconds: false,   
	    spinnerWidth:'80%',
	    editable:false
	});
	var c2 = $('#leaveEndTime').datebox('calendar');
	c2.calendar({
		validator: function(date){
			var date=date.Format("yyyy-MM-dd");
			 if(date>=now){
				return true;
			} 
			return false;
		}
	});
	var leaveStartTime="<%=request.getAttribute("leaveStartTime")%>";
	if(leaveStartTime && leaveStartTime!="null" && leaveStartTime!="" ){
		$('#leaveStartTime').datetimebox('setValue',leaveStartTime); 
	}
	var leaveEndTime="<%=request.getAttribute("leaveEndTime")%>";
	if(leaveEndTime && leaveEndTime!="null" && leaveEndTime!="" ){
		$('#leaveEndTime').datetimebox('setValue',leaveEndTime); 
	}
}
//执行删除行操作，删除行时触发调用
function doDel(){
	//此处为示例，用于示例。
}
//执行保存操作
function doSave(){
	var value=""; 
	var arr = document.getElementsByName("leaveApply.leaveType");
	for(var i=0;i<arr.length;i++){
	        if(arr[i].checked){    
	          value=arr[i].value;
	        }
	    }
	if(value == ""){
		alert("请选择假别！");
		return false;
	}
	var value=$("#leaveReason").val();
	if(value==null || value==""){
		alert("请填写请假事由！");
		return false;
	}
	
	var value=$("#leaveStartTime").datebox('getValue');
	if(value==null || value==""){
		alert("请选择请假开始时间！");
		return false;
	}
	var value=$("#leaveEndTime").datebox('getValue');
	if(value==null || value==""){
		alert("请选择请假结束时间！");
		return false;
	}
	
	var leaveDays=$("#leaveDays").val();
	if(leaveDays==null || leaveDays==""){
		alert("请填写请假天数！");
		return false;
	}else if(!(/^(0.5|\+?[1-9][0-9]*(\.(5|0))?)$/.test(leaveDays))){
		alert("请假天数有误，请输入0.5的倍数！"); 
        return false; 
	}
	var value=""; 
	var arr = document.getElementsByName("leaveApply.isReplace");
	for(var i=0;i<arr.length;i++){
	        if(arr[i].checked){    
	          value=arr[i].value;
	        }
	    }
	if(value == ""){
		alert("请选择是否替岗！");
		return false;
	}else if(value==1){
		//判断替岗列表
		var replaceDays=0;
		var tr=$("#demoTable").find("tbody").find("tr");
		if(!tr || tr.length<=0){
			alert("请创建替岗人！");
			return false;
		}else{
			for(var i=0;i<tr.length;i++){
				var index=parseInt($(tr[i]).find("td:first").text())-1;
				var flag =$(tr[i]).find("td:first input:first").val();
				if(flag==delFlag){
					continue;
				}
				value=document.getElementsByName("leaveReplaceDynamicList["+index+"].replaceSupervisory")[0].value;
				if(value=="-1"){
					alert("请选择第"+(index+1)+"条替岗人工号！");
					return false;
				}
				/* if(!$(getElement("leaveReplaceDynamicList["+index+"].replaceStartTime")).datetimebox('getValue')){
					alert("请选择第"+(index+1)+"条替岗人替岗开始时间！");
					return false;
				}
				if(!$(getElement("leaveReplaceDynamicList["+index+"].replaceEndTime")).datetimebox('getValue')){
					alert("请选择第"+(index+1)+"条替岗人替岗结束时间！");
					return false;
				} */
				value=document.getElementsByName("leaveReplaceDynamicList["+index+"].replaceDays")[0].value;
				if(value==""){
					alert("请填写第"+(index+1)+"条替岗人替岗天数！");
					return false;
				}else{
					if(!(/^(0.5|\+?[1-9][0-9]*(\.(5|0))?)$/.test(value))){  
				        alert("第"+(index+1)+"条替岗人替岗天数有误,请输入0.5的正整数倍！");  
				        return false;
				    }  
				}
				replaceDays=parseFloat(replaceDays)+parseFloat(value);
			}
		}
		if(replaceDays>leaveDays){
			 alert("替岗总天数大于请假总天数,请验证并重新填写！");  
		     return false;
		}else if(replaceDays<leaveDays){
			 alert("替岗总天数小于请假总天数,请验证并重新填写！");  
		     return false;
		}
	}
	document.forms[0].submit();
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/leaveApply.do?method=findPageList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
	initTools();
	var leaveDays="<%=request.getAttribute("leaveDays")%>";
	$("#leaveDays").numberbox('setValue',parseInt(leaveDays));
}

function changeReplaceSupervisory(repositoryId,rowNum){
	cleanReplaceSupervisory(rowNum);
	if(repositoryId==-1){
		return;
	}
	var url = "../json/getSupervisorByRepositoryId.do?callback=?&id="+repositoryId;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setReplaceSupervisory(data[0],rowNum);
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
<style type="text/css">
.nameCol2{
width:25%;
border-bottom:1px solid #eee;padding-top:13px;padding-bottom:13px;
border-right:1px solid #eee; 
text-align: right;
}
.codeCol2{
width:25%;
border-bottom:1px solid #eee;padding-top:13px;padding-bottom:13px;
border-right:1px solid #eee; 
} 
</style>
</head>
  
<body>

<%=new DemoValidate().getValidataHTML("leaveReplaceDynamicList")%>
<div class="title">请假申请</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/leaveApply" styleId="leaveApplyForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="updateLeaveApply">
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
					<td class="nameCol2" style="width:15%">申请人工号</td>
					<td class="codeCol2" style="width:15%">
						<html:text  property="leaveApply.leavePersonStaffNo" styleId="leaveApply.leavePersonStaffNo" readonly="true"/>
					</td>
					<td class="nameCol2" style="width:15%">申请人</td>
					<td class="codeCol2" style="width:15%"><c:out value="${leavePerson }" /></td>
					<td class="nameCol2" style="width:15%">申请时间：</td>
					<td class="codeCol2" style="width:15%">
						<c:out value="${applyTime }" />
					</td>
				</tr>
				<c:if test="${currentDealerList!=null}">
					<logic:iterate name="currentDealerList" id="row" indexId="index" >
						<tr>
							<td class="nameCol2" style="width:15%">经销商<c:out value="${index+1}" />：</td>
							<td class="codeCol2" style="width:15%">
								<c:out value='${row.dealerName}' />
							</td>
							<td class="nameCol2" style="width:15%">金融机构<c:out value="${index+1}" />：</td>
							<td class="codeCol2" style="width:25%">
								<c:out value='${row.bankName}' />
							</td>
							<td class="nameCol2" style="width:15%">品牌<c:out value="${index+1}" />：</td>
							<td class="codeCol2" style="width:15%">
								<c:out value='${row.brandName}' />
							</td>
						</tr>
					</logic:iterate>
				</c:if>
				<tr>
					<td class="nameCol2"><font color="#FF0000">*</font>假别</td>
					<td class="codeCol2" colspan="5">
						<form:radios  property="leaveApply.leaveType" styleId="leaveApply.leaveType" collection="leaveTypes"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol2"><font color="#FF0000">*</font>请假事由</td>
					<td class="codeCol2" colspan="5">
						<html:text style="width:80%" property="leaveApply.leaveReason" styleId="leaveReason"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol2" rowspan="4" style="text-align: right;">休假期间工作安排</td>
					<td class="nameCol2" colspan="2" ><font color="#FF0000">*</font>
						自 <input style="width: 40%" id="leaveStartTime" name="leaveApply.leaveStartTime" type="text"/>
						 至  <input style="width:40%" id="leaveEndTime" name="leaveApply.leaveEndTime" type="text"/>
					</td>
					<td class="nameCol2"><font color="#FF0000">*</font>天数：</td>
					<td class="codeCol2" colspan="2">
						<input type="text" id="leaveDays" name="leaveApply.leaveDays" class="easyui-numberbox" value="<c:out value='${leaveApplyForm.leaveApply.leaveDays}'/>"
								data-options="min:0,precision:1"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol2"colspan="2" >车辆合格证是否全锁入保险柜：</td>
					<td class="codeCol2" colspan="3">
						<html:text  style="width:90%" property="leaveApply.certificate" styleId="leaveApply.certificate"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol2"colspan="2" >车辆钥匙是否锁入保险柜：</td>
					<td class="codeCol2" colspan="3">
						<html:text style="width:90%" property="leaveApply.key" styleId="leaveApply.key"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol2"colspan="2" >保险柜钥匙是否妥善保存：</td>
					<td class="codeCol2" colspan="3">
						<html:text style="width:90%" property="leaveApply.safetyBox" styleId="leaveApply.safetyBox"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol2"><font color="#FF0000">*</font>是否替岗</td>
					<td class="codeCol2" colspan="5">
						<form:radios  property="leaveApply.isReplace" styleId="isReplace" collection="isReplace" onchange="changeIsReplace(this.value)"/>
					</td>
				</tr>
			</table>
		</td>	
	</tr>
	<tr id="replaceList" hidden="true">
		<td colspan="4">
			<div class="dvScroll" style="width:95%">
				<table  class="listTalbe" cellpadding="0" cellspacing="0"  id="demoTable">
					<thead>
						<tr class="title">
					      <td>序号</td>
					      <td align="center">操作</td>
					      <td><thumbpage:order cname="<font color='#FF0000'>*</font>替岗人工号" filedName="car_model"/></td> 
					      <td><thumbpage:order cname="替岗人姓名" filedName="color"/></td>
					      <td><thumbpage:order cname="替岗人经销店" filedName="vin"/></td>
					      <td><thumbpage:order cname="<font color='#FF0000'>*</font>替岗开始时间" filedName="certificate_num"/></td>
					      <td><thumbpage:order cname="<font color='#FF0000'>*</font>替岗结束时间" filedName="key_num"/></td>
					      <td><thumbpage:order cname="<font color='#FF0000'>*</font>替岗天数" filedName="key_amount"/></td> 
					    </tr>
					</thead>
					<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
						<logic:iterate name="leaveApplyForm" property="leaveReplaceDynamicList" id="leaveReplaceDynamicList" indexId="index"  >
							<tr class="listTr_a" >
								<td ><html:hidden name="leaveReplaceDynamicList" property="stateFlag" indexed="true"/></td>
								<td></td>
								<td >
									<select id="replaceSupervisory" name="].replaceSupervisory" style="width:80%;">
										<option value="-1">请选择</option>
										<c:forEach items="${leaveReplaceRepositorys }" var="row">
											<option  <c:if test="${leaveApplyForm.leaveReplaceDynamicList[index].replaceSupervisory==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
										</c:forEach>
									</select>
									<html:hidden name="leaveReplaceDynamicList" property="id" indexed="true"/>
								</td>
								<td >
									<html:text name="leaveReplaceDynamicList"  property="leaveReplaceName" styleId="leaveReplaceName" disabled="true" indexed="true"></html:text>
								</td>
								<td >
									<html:text name="leaveReplaceDynamicList"  property="leaveReplaceDealer" styleId="leaveReplaceDealer" disabled="true" indexed="true"></html:text>
								</td>
								<td >
									<input id="replaceStartTime" type="text" name="].replaceStartTime" value='<fmt:formatDate value="${leaveApplyForm.leaveReplaceDynamicList[index].replaceStartTime}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate>'/>
								</td>
								<td >
									<input id="replaceEndTime" type="text" name="].replaceEndTime" value='<fmt:formatDate value="${leaveApplyForm.leaveReplaceDynamicList[index].replaceEndTime}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate>'/>
								</td>
								<td >
									<html:text name="leaveReplaceDynamicList" property="replaceDays" indexed="true"  ></html:text>
								</td>
								
							</tr>
						</logic:iterate>
					</tbody>  
				</table>
				<table class="bottomTable">
					<tr>	
						<td>
							<button class="formButton" onClick="addRow('demoTable')">新&nbsp;增&nbsp;行</button>
						</td>
					</tr>
				</table>
			</div>
		</td>
	</tr>
	
	<tr>
		<td colspan="4">
			<table style="width: 100%">
					<td class="nameCol2"></td>
					<td class="nameCol2"></td>
					<td class="nameCol2"></td>
					<td class="nameCol2"></td>
					<td class="nameCol2"></td>
				<tr>
					<td class="nameCol2">经销商意见</td>
					<td class="codeCol2" colspan="5">
						<a href="javascript:showDiv();">
							<img alt="照片" src="/showImg.do?method=showImg&filePath=<c:out value='${filepath}'/>" height="180" width="240"/>
						</a>
						上传扫描件：<input type="file"  name="dealerPicture" id="dealerPicture"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>		
	<tr class="formTableFoot">
		<td colspan="4" align="center">
			<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
			<button class="formButton" onClick="doReset()">重&nbsp;置</button>&nbsp;
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
