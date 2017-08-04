<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<!-- easyui -->
<link href="/easyui/themes/material/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	var handoverNature=""; 
	var arr = document.getElementsByName("handoverLog.handoverNature");
	for(var i=0;i<arr.length;i++) {
	        if(arr[i].checked){    
	        	handoverNature=arr[i].value;
	        }
	    }
	setReason(handoverNature);
	var dealerId=document.getElementById("dealerId").value;
	changeDealer(dealerId);
	var deliverer=document.getElementById("deliver").value;
	changeDeliverer(deliverer);
	var receiver=document.getElementById("receiver").value;
	changeReceiver(receiver);
	
	var objS = document.getElementById("handoverLog.handoverType");
    var text = objS.options[objS.selectedIndex].text;
    if(text == "撤店交接"){
        $(".yc").hide();
        document.getElementById("receiver").value="";
        document.getElementById("rgender").value="";
        document.getElementById("ridNumber").value="";
        document.getElementById("rstaffNo").value="";
        document.getElementById("rentryDate").value="";
        document.getElementById("rcontactNumber").value="";
    }else{
         $(".yc").show();
    }
}

//执行保存操作
function doSave(){
    var sel = document.getElementById("handoverLog.handoverType");
    var selected_val = sel.options[sel.selectedIndex].text;
    /* 需求46 */
    if(selected_val != "撤店交接"){
        var receiver =$("#receiver").combobox("getValue");
        if(!receiver){
            alert("请选择接收人");
            return false;
        }else if(receiver==deliverer){
            alert("接收人不能与交付人一致，请重新选择");
            return false;
        }
        
        var value=""; 
        var arr = document.getElementsByName("handoverLog.receiveNature");
        for(var i=0;i<arr.length;i++)
            {
                if(arr[i].checked)
                {    
                  value=arr[i].value;
                }
            }
        if(value == ""){
            alert("请选择接收人接收性质");
            return false;
        }
        var value=""; 
        var arr = document.getElementsByName("handoverLog.afterHandoverNature");
        for(var i=0;i<arr.length;i++)
            {
                if(arr[i].checked)
                {    
                  value=arr[i].value;
                }
            }
        if(value == ""){
            alert("请选择接收人接收后属性");
            return false;
        }
        var value = document.getElementById("handoverLog.missionDate").value;
        if(value == ""){
            alert("请选择接收人上岗时间");
            return false;
        }
        var value = document.getElementById("handoverLog.handoverDate").value;
        if(value == ""){
            alert("请填写交接时间");
            return false;
        }
    }
    if (!$("#dealerId").combobox("getValue")) {
        alert("请选择经销商");
        return false;
    }
    /* var value = document.getElementById("handoverLog.accommodationProvider").value;
    if(value == ""){
        alert("请填写经销商住宿提供方");
        return false;
    } */
    var deliverer=$("#deliverer").combobox("getValue");
    if (!deliverer) {
        alert("请选择交付人");
        return false;
    }
    $("#deliver").val(deliverer);
    
    var value = document.getElementById("handoverLog.delivererApplicationDate").value;
    if(value == ""){
        alert("请选择交付人申请时间");
        return false;
    }
    
    var value = document.getElementById("handoverLog.expectedDimissionDate").value;
    if(value == ""){
        alert("请选择交付人预计离岗时间");
        return false;
    }
    
    var value=""; 
    var arr = document.getElementsByName("handoverLog.handoverNature");
    for(var i=0;i<arr.length;i++)
        {
            if(arr[i].checked)
            {    
              value=arr[i].value;
            }
        }
    if(value == ""){
        alert("请选择交付人交接性质");
        return false;
    }else if(value==1){
        var value=""; 
        var arr = document.getElementsByName("handoverLog.resignReason");
        for(var i=0;i<arr.length;i++)
            {
                if(arr[i].checked)
                {    
                  value=arr[i].value;
                }
            }
        if(value == ""){
            alert("请选择辞职原因");
            return false;
        }
    }
    var value = document.getElementById("handoverLog.dimissionDate").value;
    if(value == ""){
        alert("请选择离岗时间");
        return false;
    }
    
    
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

//执行提交操作
function doSubmit(){
	if(confirm("确认提交？提交后将不可编辑，进入制作交接书流程")){
		var id=document.getElementsByName("handoverLog.id")[0].value;
		window.location="<url:context/>/handoverLog.do?method=updHandoverLogEditStatus&handoverLog.id="+id;
	}
}
//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
function changeDealer(id) {
	if(id==-1){
		return;
	}
	var url = "../json/getDealerByIdJsonAction.do?callback=?&findRep=yes&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setDealer(data[0]);
		//设置交付人
		var repList = data[0].repList;
		$("#deliverer").combobox('clear');
		$("#deliverer").combobox('loadData',repList);
		 if(repList!=null && repList.length>0){
			for(var i=0;i<repList.length;i++){
				if(repList[0].value==$("#deliver").val()){
					$("#deliverer").combobox('select',$("#deliver").val());
				}
			}
		} 
	});
}
function setReason(status) {
	var arr = document.getElementsByName("handoverLog.resignReason");
	if(status==1){
		for(var i=0;i<arr.length;i++){
			arr[i].removeAttribute("disabled");
		}
	}else{
		for(var i=0;i<arr.length;i++){
			arr[i].checked=false;
			arr[i].disabled=true;
		}
	}
}
function setDealer(dealer){
	$("#brand").val(dealer.brand);
	$("#address").val(dealer.address);
	var bank=dealer.bankName;
	var banks=bank.split("/");
	$("#headBank").val(banks[0]);
	$("#branch").val(banks[1]);
	$("#subbranch").val(banks[2]);
	$("#province").val(dealer.province);
	$("#city").val(dealer.city);
	$('#equipmentProvide').val(dealer.equipmentProvide);
	$('#bindMerchant').val(dealer.bindInfo);
	$('#bindBank').val(dealer.bindBank);
	
}
function changeDeliverer(id) {
	if(id==-1){
		return;
	}
	var url = "../json/getSupervisorById.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setDeliverer(data[0]);
	});
}
function setDeliverer(supervisor){
	$("#idNumber").val(supervisor.idNumber);
	$("#gender").val(supervisor.gender);
	$("#staffNo").val(supervisor.staffNo);
	$("#entryDate").val(supervisor.entryTimeStr);
	$("#contactNumber").val(supervisor.selfTelephone);
}
function changeReceiver(id) {
	if(id==-1){
		return;
	}
	var url = "../json/getSupervisorById.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setReceiver(data[0]);
	});
}
function setReceiver(supervisor){
	$("#ridNumber").val(supervisor.idNumber);
	$("#rgender").val(supervisor.gender);
	$("#rstaffNo").val(supervisor.staffNo);
	$("#rentryDate").val(supervisor.entryTimeStr);
	$("#rcontactNumber").val(supervisor.selfTelephone);
}
function init() {
	$("#deliverer").combobox({
		valueField:'value',
		textField:'label',
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
				changeDeliverer(newValue); 
			}

		}
	});
	 var draftValue = $("#deliverer").combobox('getValue');
	if (draftValue) {
		changeDeliverer(draftValue);
	} 
	$("#receiver").combobox({
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
				 changeReceiver(newValue);
			}

		}
	});
	 var draftValue = $("#receiver").combobox('getValue');
		if (draftValue) {
			changeReceiver(draftValue);
		}
	$("#dealerId").combobox({
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
				changeDealer(newValue)
			}

		}
	});
	 var draftValue = $("#dealerId").combobox('getValue');
		if (draftValue) {
			changeDealer(draftValue);
		}
}

$(function(){
	init();
});
function handoverTypesChange(){
     var objS = document.getElementById("handoverLog.handoverType");
     var text = objS.options[objS.selectedIndex].text;
     if(text == "撤店交接"){
        $(".yc").hide();
    }else{
         $(".yc").show();
    }
}
</script>
<style type="text/css">
#f_file{
    left: 0;
    opacity: 0;
    position: absolute;
    top: -100;
    z-index: 2;
    width: 100%;
    height: 100%;
}
.codeC select{
    width:200px; height:30px; border:2px soild #EEEEEE; border-radius:2px;
}
.codeC input[eventtype="target"]{
    width:200px; height:30px; border:1px soild #EEEEEE; 
}
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
<body  onLoad="doLoad()">

<br/>

<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/handoverLog" styleId="handoverLogForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="updHandoverLog"/>
<html:hidden property="handoverLog.id" styleId="handoverLog.id" />
<html:hidden property="handoverLog.isEdit" styleId="handoverLog.isEdit" />
<html:hidden property="handoverLog.createUserId" styleId="handoverLog.createUserId" />
<html:hidden property="handoverLog.createTime" styleId="handoverLog.createTime" />
<html:hidden property="handoverLog.lastModifyUser" styleId="handoverLog.lastModifyUser" />
<html:hidden property="handoverLog.lastModifyTime" styleId="handoverLog.lastModifyTime" />
<html:hidden property="handoverLog.deliverer" styleId="deliver" />
<table class="formTable">
	<tr class="formTitle">
		<td colspan="4">修改监管员交接记录</td>
	</tr>
	<tr>
		<td colspan="4"> 
			<table class="formTable2">
				<tr>
					<td class="nameC"><font color="#FF0000">*</font>交接类型：</td>
					<td class="codeC">
						<form:select property="handoverLog.handoverType" 
							styleId="handoverLog.handoverType" onchange="handoverTypesChange();">
							<html:optionsCollection name="handoverTypes" label="label" value="value" />
						</form:select>
					</td>
					<td class="nameC"></td>
                    <td class="codeC"></td>
                    <td class="nameC"></td>
                    <td class="codeC"></td>
				</tr>
				<tr>
					<td class="nameC"><font color="#FF0000">*</font>经销商：</td>
					<td class="codeC">
						<select style="width:99%" id="dealerId" name="handoverLog.dealerId" onchange="changeDealer(this.value)" >
							<c:forEach items="${dealers }" var="row">
								<option <c:if test="${handoverLogForm.handoverLog.dealerId==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
							</c:forEach>
						</select>
					</td>
					<td class="nameC">经销商品牌：</td>
					<td class="codeC"><input type="text"  id="brand" disabled="true" /></td>
					<td class="nameC">经销商地址：</td>
					<td class="codeC"><input type="text"  id="address" disabled="true" /></td>
				</tr>
				<tr>
					<td class="nameC">经销商所在省：</td>
					<td class="codeC"><input type="text"  id="province"disabled="true" /></td>
					<td class="nameC">经销商所在市：</td>
					<td class="codeC"><input type="text" id="city"disabled="true" /></td>
					<td class="nameC">设备提供方：</td>
					<td class="codeC"><input type="text" id="equipmentProvide"disabled="true" /></td>
				</tr>
				<tr>
					<td class="nameC">合作银行（总）：</td>
					<td class="codeC"><input type="text"  id="headBank"disabled="true" /></td>
					<td class="nameC">合作银行（分）：</td>
					<td class="codeC"><input type="text" id="branch"disabled="true" /></td>
					<td class="nameC">合作银行（支）：</td>
					<td class="codeC"><input type="text" id="subbranch"disabled="true" /></td>
				</tr>
				<tr>
					<%-- <td class="nameC"><font color="#FF0000">*</font>住宿提供方：</td>
					<td class="codeC"><html:text property="handoverLog.accommodationProvider" styleId="handoverLog.accommodationProvider"/></td> --%>
					<td class="nameC">绑定店：</td>
					<td class="codeC"><input type="text" id="bindMerchant"disabled="true" /></td>
					<td class="nameC">绑定行：</td>
					<td class="codeC"><input type="text" id="bindBank"disabled="true" /></td>
					<td class="nameC"></td>
                    <td class="codeC"></td>
				</tr>
				<tr>
					<td class="nameC">店方要求：</td>
					<td class="codeC" colspan="5"><html:text style="width:700px" property="handoverLog.merchantDemand" styleId="handoverLog.merchantDemand"/></td>
				</tr>
				<tr>
					<td class="nameC"><font color="#FF0000">*</font>交付人：</td>
					<td class="codeC">
						<select style="width:99%" id="deliverer"  onchange="changeDeliverer(this.value)" >
						</select>
					</td> 
					<td class="nameC">身份证号：</td>
					<td class="codeC"><input type="text"  id="idNumber" disabled="disabled"/></td>
					<td class="nameC">性别：</td>
					<td class="codeC"><input type="text"  id="gender" disabled="disabled"/></td>
				</tr>
				 <tr>
				 	<td class="nameC">员工工号：</td>
					<td class="codeC"><input type="text"  id="staffNo" disabled="disabled"/></td>
					<td class="nameC">调入时间：</td>
					<td class="codeC">
						<input type="text"  id="entryDate" disabled="disabled"/>
					</td> 
					<td class="nameC">联系方式：</td>
					<td class="codeC"><input type="text"  id="contactNumber" disabled="disabled"/></td>
				</tr>
				<tr>
					<td class="nameC"><font color="#FF0000">*</font>交付人申请时间：</td>
					<td class="codeC">
						<form:calendar property="handoverLog.delivererApplicationDate" styleId="handoverLog.delivererApplicationDate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
					</td>
					<td class="nameC"><font color="#FF0000">*</font>预计离岗时间：</td>
					<td class="codeC">
						<form:calendar property="handoverLog.expectedDimissionDate" styleId="handoverLog.expectedDimissionDate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
					</td>
					<td class="nameC"><font color="#FF0000">*</font>离岗时间：</td>
					<td class="codeC">
						<form:calendar property="handoverLog.dimissionDate" styleId="handoverLog.dimissionDate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
					</td>
				</tr>
				<tr>
					<td class="nameC"><font color="#FF0000">*</font>交接性质：</td>
					<td class="nameC"style="text-align: left;" colspan="2">
						<form:radios  property="handoverLog.handoverNature" collection="handoverNatures" styleId="handoverLog.handoverNature" onchange="setReason(this.value)"/>
					</td>
					<td class="nameC"><font color="#FF0000">*</font>辞职原因：</td>
					<td class="nameC"style="text-align: left;" colspan="2">
						<form:radios  property="handoverLog.resignReason" collection="resignReasons" styleId="handoverLog.resignReason" disabled="true"/>
					</td>
				</tr>
				<tr class="yc">
					<td class="nameC yc"><font color="#FF0000" class="font">*</font>接收人：</td>
					<td class="codeC yc">
						<select style="width:99%" id="receiver" name="handoverLog.receiver" onchange="changeReceiver(this.value)" >
							<c:forEach items="${supervisors }" var="row">
								<option <c:if test="${handoverLogForm.handoverLog.receiver==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
							</c:forEach>
						</select>
					</td>
					<td class="nameC yc">性别：</td>
					<td class="codeC yc"><input type="text"  id="rgender" disabled="disabled"/></td>
					<td class="nameC yc">身份证号：</td>
					<td class="codeC yc"><input type="text"  id="ridNumber" disabled="disabled"/></td>
				</tr>
				 <tr class="yc">
				 	<td class="nameC yc">员工工号：</td>
					<td class="codeC yc"><input type="text"  id="rstaffNo" disabled="disabled"/></td>
					<td class="nameC yc">调入时间：</td>
					<td class="codeC yc">
						<input type="text"  id="rentryDate" disabled="disabled"/>
					</td> 
					<td class="nameC yc">联系方式：</td>
					<td class="codeC yc"><input type="text"  id="rcontactNumber" disabled="disabled"/></td>
				</tr>
				<tr class="yc">
					<td class="nameC yc"><font color="#FF0000" class="font">*</font>接收性质：</td>
					<td class="nameC yc"style="text-align: left;width: 300px"  >
						<form:radios  property="handoverLog.receiveNature" collection="receiveNatures" styleId="handoverLog.receiveNature"/>
					</td>
					<td class="nameC yc"><font color="#FF0000" class="font">*</font>接收后属性：</td>
					<td class="nameC yc"style="text-align: left;" >
						<form:radios  property="handoverLog.afterHandoverNature" collection="afterHandoverNature" styleId="handoverLog.afterHandoverNature" />
					</td>
					<td class="nameC yc"><font color="#FF0000" class="font">*</font>上岗时间：</td>
					<td class="codeC yc">
						<form:calendar property="handoverLog.missionDate" styleId="handoverLog.missionDate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
					</td>
				</tr>
				<tr class="yc">
					<td class="nameC yc"><font color="#FF0000" class="font">*</font>交接时间：</td>
					<td class="codeC yc">
						<form:calendar property="handoverLog.handoverDate" styleId="handoverLog.handoverDate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
					</td>
					<%-- //修改
					<td class="nameC">备注：</td>
					<td class="codeC"colspan="3" style="text-align: left;">
						<html:text style="width:700px" property="handoverLog.remark" styleId="handoverLog.remark"/>
					</td> --%>
				</tr>
				<tr >
                    <td class="nameC">备注：</td>
                    <td class="codeC"colspan="5" style="text-align: left;">
                        <html:text style="width:700px" property="handoverLog.remark" styleId="handoverLog.remark"/>
                    </td>
                </tr>
				<tr>
				   <td class="nameC" colspan="2">接收方监管公司名称：</td> 
				   <td class="codeC"colspan="4" style="text-align: left;">
                        <html:text style="width:700px" property="handoverLog.recipientCompanyName" styleId="handoverLog.recipientCompanyName"/>
                    </td>
				</tr>
			</table>
		</td>
	</tr>
	<tr class="formTableFoot">
		<c:choose>
			<c:when test="${isEdit == 0}">
				<td colspan="4" align="center">
					<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
					<button class="formButton" onClick="doReset()">重&nbsp;置</button>&nbsp;
				<!-- 	<button class="formButton" onClick="doSubmit()">提&nbsp;交</button>&nbsp; -->
					<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
				</td>
			</c:when>
			<c:otherwise>
				<td colspan="4" class="message" style="text-align: center;">已经进入制作交接书流程，不可编辑。</td>
			</c:otherwise>
		</c:choose>
	</tr>
</table>
<br/>
<div class="message" id="message" style="display:none"></div>
</html:form>
	
	</div>
</div>
</body>
</html>
