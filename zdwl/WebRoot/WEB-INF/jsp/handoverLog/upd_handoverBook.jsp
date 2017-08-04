<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="select.tld" prefix="select" %>
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
	var handoverNature=document.getElementsByName("handoverLog.handoverNature").value;
	setReason(handoverNature);
	var dealerId=document.getElementById("handoverLog.dealerId").value;
	changeDealer(dealerId);
	var deliverer=document.getElementById("handoverLog.deliverer").value;
	changeDeliverer(deliverer);
	var receiver=document.getElementById("handoverLog.receiver").value;
	changeReceiver(receiver);
	loadReceiverRepository(receiver);
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

function doUpdBook(){
 	goUpdElectronicText();//保存电子文本资料
	goUpdPaperText();//保存纸质文本资料
	goUpdOtherData();//保存其他资料
	goUpdOfficeEquipment();//保存办公用品及设备 
	alert("保存资料成功");
	doReturn();
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/handoverLog.do?method=handoverLogPageList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
function changeDealer(id) {
	if(id==-1){
		document.forms[0].reset();
		return;
	}
	var url = "../json/getDealerByIdJsonAction.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setDealer(data[0]);
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
}
function changeDeliverer(id) {
	if(id==-1){
		document.forms[0].reset();
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
		document.forms[0].reset();
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
	$("#rnativePlace").val(supervisor.nativePlace);
	$("#reducationBackground").val(supervisor.educationBackground);
	$("#rrecommenderName").val(supervisor.recommenderName);
	$("#rrecommendChannel").val(supervisor.recommendChannel);
	
}
function loadReceiverRepository(id) {
	if(id==-1){
		document.forms[0].reset();
		return;
	}
	var url = "../json/getRepositoryBySupervisorId.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setReceiverRepository(data[0]);
	});
}
function setReceiverRepository(repository){
	$("#isTrain").val(repository.isTrain);
	$("#trainer").val(repository.trainer);
	$("#trainingContent").val(repository.trainingContent);
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
function goAddPrivateSellCar(){
	var handoverLogId=document.getElementById("handoverLog.id").value;
	window.location="<url:context/>/handoverLog.do?method=addSuperviseImportEntity&superviseImport.state=5&handoverLog.id="+handoverLogId;
}
function goUpdPrivateSellCar(id){
	var handoverLogId=document.getElementById("handoverLog.id").value;
	window.location="<url:context/>/handoverLog.do?method=updSuperviseImportEntity&superviseImport.state=5&superviseImport.id="+id+"&handoverLog.id="+handoverLogId;
}
function goAddPrivateMoveCar(){
	var handoverLogId=document.getElementById("handoverLog.id").value;
	window.location="<url:context/>/handoverLog.do?method=addSuperviseImportEntity&superviseImport.state=6&handoverLog.id="+handoverLogId;
}
function goUpdPrivateMoveCar(id){
	var handoverLogId=document.getElementById("handoverLog.id").value;
	window.location="<url:context/>/handoverLog.do?method=updSuperviseImportEntity&superviseImport.state=6&superviseImport.id="+id+"&handoverLog.id="+handoverLogId;
}
function goDelSuperviseImport(id){
	if(confirm("确认删除？")){
		var handoverLogId=document.getElementById("handoverLog.id").value;
		window.location="<url:context/>/handoverLog.do?method=delSuperviseImport&superviseImport.id="+id+"&handoverLog.id="+handoverLogId;
	}
}
function goUpdElectronicText(){
	var supervisorId=document.getElementById("handoverLog.deliverer").value;
	var id=document.getElementById("eText.id").value;
	var handoverLogId=document.getElementById("handoverLog.id").value;
	var electronBill=document.getElementById("eText.electronBill").value;
	var receiveCarDetailAmount=document.getElementById("eText.receiveCarDetailAmount").value;
	var receiveCarDetailStartTime=document.getElementById("eText.receiveCarDetailStartTime").value;
	var receiveCarDetailEndTime=document.getElementById("eText.receiveCarDetailEndTime").value;
	var weekBillAmount=document.getElementById("eText.weekBillAmount").value;
	var weekBillStartTime=document.getElementById("eText.weekBillStartTime").value;
	var weekBillEndTime=document.getElementById("eText.weekBillEndTime").value;
	var monthBillAmount=document.getElementById("eText.monthBillAmount").value;
	var monthBillStartTime=document.getElementById("eText.monthBillStartTime").value;
	var monthBillEndTime=document.getElementById("eText.monthBillEndTime").value;
	var applyFreeBookAmount=document.getElementById("eText.applyFreeBookAmount").value;
	var applyFreeBookStartTime=document.getElementById("eText.applyFreeBookStartTime").value;
	var applyFreeBookEndTime=document.getElementById("eText.applyFreeBookEndTime").value;
	var confirmationAmount=document.getElementById("eText.confirmationAmount").value;
	var confirmationStartTime=document.getElementById("eText.confirmationStartTime").value;
	var confirmationEndTime=document.getElementById("eText.confirmationEndTime").value;
	var towApplyAmount=document.getElementById("eText.towApplyAmount").value;
	var towApplyStartTime=document.getElementById("eText.towApplyStartTime").value;
	var towApplyEndTime=document.getElementById("eText.towApplyEndTime").value;
	var otherAmount=document.getElementById("eText.otherAmount").value;
	var otherStartTime=document.getElementById("eText.otherStartTime").value;
	var otherEndTime=document.getElementById("eText.otherEndTime").value;
	var remark=document.getElementById("eText.remark").value;
/* 	window.location=window.encodeURI(window.encodeURI("<url:context/>/handoverLog.do?method=updElectronicText&eText.id="+id+"&eText.supervisorId="+supervisorId+"&handoverLog.id="+handoverLogId
	+"&eText.electronBill="+electronBill+"&eText.receiveCarDetailAmount="+receiveCarDetailAmount+"&eText.confirmationEndTime="+confirmationEndTime+"&eText.receiveCarDetailStartTime="+receiveCarDetailStartTime
	+"&eText.receiveCarDetailEndTime="+receiveCarDetailEndTime+"&eText.weekBillAmount="+weekBillAmount+"&eText.weekBillStartTime="+weekBillStartTime+"&eText.weekBillEndTime="+weekBillEndTime
	+"&eText.monthBillAmount="+monthBillAmount+"&eText.monthBillStartTime="+monthBillStartTime+"&eText.monthBillEndTime="+monthBillEndTime+"&eText.applyFreeBookAmount="+applyFreeBookAmount
	+"&eText.applyFreeBookStartTime="+applyFreeBookStartTime+"&eText.applyFreeBookEndTime="+applyFreeBookEndTime+"&eText.confirmationAmount="+confirmationAmount+"&eText.confirmationStartTime="+confirmationStartTime
	+"&eText.towApplyAmount="+towApplyAmount+"&eText.towApplyStartTime="+towApplyStartTime+"&eText.towApplyEndTime="+towApplyEndTime+"&eText.otherAmount="+otherAmount
	+"&eText.otherStartTime="+otherStartTime+"&eText.otherEndTime="+otherEndTime+"&eText.remark="+remark));  */
	
	
 	 $.ajax({
			type : "POST",
			url : "/handoverLog.do?method=updElectronicText",
			dataType : "json",
			data : "eText.id="+id+"&eText.supervisorId="+supervisorId+"&handoverLog.id="+handoverLogId
					+"&eText.electronBill="+electronBill+"&eText.receiveCarDetailAmount="+receiveCarDetailAmount+"&eText.confirmationEndTime="+confirmationEndTime+"&eText.receiveCarDetailStartTime="+receiveCarDetailStartTime
					+"&eText.receiveCarDetailEndTime="+receiveCarDetailEndTime+"&eText.weekBillAmount="+weekBillAmount+"&eText.weekBillStartTime="+weekBillStartTime+"&eText.weekBillEndTime="+weekBillEndTime
					+"&eText.monthBillAmount="+monthBillAmount+"&eText.monthBillStartTime="+monthBillStartTime+"&eText.monthBillEndTime="+monthBillEndTime+"&eText.applyFreeBookAmount="+applyFreeBookAmount
					+"&eText.applyFreeBookStartTime="+applyFreeBookStartTime+"&eText.applyFreeBookEndTime="+applyFreeBookEndTime+"&eText.confirmationAmount="+confirmationAmount+"&eText.confirmationStartTime="+confirmationStartTime
					+"&eText.towApplyAmount="+towApplyAmount+"&eText.towApplyStartTime="+towApplyStartTime+"&eText.towApplyEndTime="+towApplyEndTime+"&eText.otherAmount="+otherAmount
					+"&eText.otherStartTime="+otherStartTime+"&eText.otherEndTime="+otherEndTime+"&eText.remark="+remark,
			success : function(data) {
							
					}
			});
}  
function goUpdPaperText(){
	var supervisorId=document.getElementById("handoverLog.deliverer").value;
	var id=document.getElementById("pText.id").value;
	var handoverLogId=document.getElementById("handoverLog.id").value;
	var paperBillAmount=document.getElementById("pText.paperBillAmount").value;
	var paperBillStartTime=document.getElementById("pText.paperBillStartTime").value;
	var paperBillEndTime=document.getElementById("pText.paperBillEndTime").value;
	var towApplyAmount=document.getElementById("pText.towApplyAmount").value;
	var towApplyStartTime=document.getElementById("pText.towApplyStartTime").value;
	var towApplyEndTime=document.getElementById("pText.towApplyEndTime").value;
	//
	var changeApplyAmount=document.getElementById("pText.changeApplyAmount").value;
	var changeApplyStartTime=document.getElementById("pText.changeApplyStartTime").value;
	var changeApplyEndTime=document.getElementById("pText.changeApplyEndTime").value;
	
	var confirmationAmount=document.getElementById("pText.confirmationAmount").value;
	var confirmationStartTime=document.getElementById("pText.confirmationStartTime").value;
	var confirmationEndTime=document.getElementById("pText.confirmationEndTime").value;
	
	var applyFreeBookAmount=document.getElementById("pText.applyFreeBookAmount").value;
	var applyFreeBookStartTime=document.getElementById("pText.applyFreeBookStartTime").value;
	var applyFreeBookEndTime=document.getElementById("pText.applyFreeBookEndTime").value;
	
	var weekBillAmount=document.getElementById("pText.weekBillAmount").value;
	var weekBillStartTime=document.getElementById("pText.weekBillStartTime").value;
	var weekBillEndTime=document.getElementById("pText.weekBillEndTime").value;
	var monthBillAmount=document.getElementById("pText.monthBillAmount").value;
	var monthBillStartTime=document.getElementById("pText.monthBillStartTime").value;
	var monthBillEndTime=document.getElementById("pText.monthBillEndTime").value;
	var otherAmount=document.getElementById("pText.otherAmount").value;
	var otherStartTime=document.getElementById("pText.otherStartTime").value;
	var otherEndTime=document.getElementById("pText.otherEndTime").value;
	
	var authorization=""; 
	var authorizationArr=document.getElementsByName("pText.authorization");
	for(var i=0;i<authorizationArr.length;i++){
	        if(authorizationArr[i].checked){    
	        	authorization=authorizationArr[i].value;
	        }
	    }
	var informLetter="";
	var informLetterArr=document.getElementsByName("pText.informLetter");
	for(var i=0;i<informLetterArr.length;i++){
        if(informLetterArr[i].checked){    
        	informLetter=informLetterArr[i].value;
        }
    }
	var keyAmount="";
	var keyAmountArr=document.getElementsByName("pText.keyAmount");
	for(var i=0;i<keyAmountArr.length;i++){
        if(keyAmountArr[i].checked){    
        	keyAmount=keyAmountArr[i].value;
        }
    }
	/* window.location=window.encodeURI(window.encodeURI("<url:context/>/handoverLog.do?method=updPaperText&pText.id="+id+"&pText.supervisorId="+supervisorId+"&handoverLog.id="+handoverLogId
	+"&pText.confirmationEndTime="+confirmationEndTime
	+"&pText.weekBillAmount="+weekBillAmount+"&pText.weekBillStartTime="+weekBillStartTime+"&pText.weekBillEndTime="+weekBillEndTime
	+"&pText.monthBillAmount="+monthBillAmount+"&pText.monthBillStartTime="+monthBillStartTime+"&pText.monthBillEndTime="+monthBillEndTime+"&pText.applyFreeBookAmount="+applyFreeBookAmount
	+"&pText.applyFreeBookStartTime="+applyFreeBookStartTime+"&pText.applyFreeBookEndTime="+applyFreeBookEndTime+"&pText.confirmationAmount="+confirmationAmount+"&pText.confirmationStartTime="+confirmationStartTime
	+"&pText.towApplyAmount="+towApplyAmount+"&pText.towApplyStartTime="+towApplyStartTime+"&pText.towApplyEndTime="+towApplyEndTime+"&pText.otherAmount="+otherAmount
	+"&pText.otherStartTime="+otherStartTime+"&pText.otherEndTime="+otherEndTime
	+"&pText.paperBillAmount="+paperBillAmount+"&pText.paperBillStartTime="+paperBillStartTime+"&pText.paperBillEndTime="+paperBillEndTime
	+"&pText.authorization="+authorization+"&pText.informLetter="+informLetter+"&pText.keyAmount="+keyAmount
	+"&pText.changeApplyAmount="+changeApplyAmount+"&pText.changeApplyStartTime="+changeApplyStartTime+"&pText.changeApplyEndTime="+changeApplyEndTime)); 
	 */
	  $.ajax({
			type : "POST",
			url : "/handoverLog.do?method=updPaperText",
			dataType : "json",
			data : "pText.id="+id+"&pText.supervisorId="+supervisorId+"&handoverLog.id="+handoverLogId
	+"&pText.confirmationEndTime="+confirmationEndTime
	+"&pText.weekBillAmount="+weekBillAmount+"&pText.weekBillStartTime="+weekBillStartTime+"&pText.weekBillEndTime="+weekBillEndTime
	+"&pText.monthBillAmount="+monthBillAmount+"&pText.monthBillStartTime="+monthBillStartTime+"&pText.monthBillEndTime="+monthBillEndTime+"&pText.applyFreeBookAmount="+applyFreeBookAmount
	+"&pText.applyFreeBookStartTime="+applyFreeBookStartTime+"&pText.applyFreeBookEndTime="+applyFreeBookEndTime+"&pText.confirmationAmount="+confirmationAmount+"&pText.confirmationStartTime="+confirmationStartTime
	+"&pText.towApplyAmount="+towApplyAmount+"&pText.towApplyStartTime="+towApplyStartTime+"&pText.towApplyEndTime="+towApplyEndTime+"&pText.otherAmount="+otherAmount
	+"&pText.otherStartTime="+otherStartTime+"&pText.otherEndTime="+otherEndTime
	+"&pText.paperBillAmount="+paperBillAmount+"&pText.paperBillStartTime="+paperBillStartTime+"&pText.paperBillEndTime="+paperBillEndTime
	+"&pText.authorization="+authorization+"&pText.informLetter="+informLetter+"&pText.keyAmount="+keyAmount
	+"&pText.changeApplyAmount="+changeApplyAmount+"&pText.changeApplyStartTime="+changeApplyStartTime+"&pText.changeApplyEndTime="+changeApplyEndTime,
			success : function(data) {
							
				}
			});
	 


}
function goUpdOtherData(){
	var supervisorId=document.getElementById("handoverLog.deliverer").value;
	var id=document.getElementById("OData.id").value;
	var handoverLogId=document.getElementById("handoverLog.id").value;
	var ODataSituationExplain=document.getElementById("OData.situationExplain").value;
	var ODataSpecialOperation=document.getElementById("OData.specialOperation").value;
	/* window.location=window.encodeURI(window.encodeURI("<url:context/>/handoverLog.do?method=updOtherData&OData.id="+id+"&OData.supervisorId="+supervisorId+"&handoverLog.id="+handoverLogId
			+"&OData.situationExplain="+ODataSituationExplain+"&OData.specialOperation="+ODataSpecialOperation));  */
			
 	$.ajax({
			type : "POST",
			url : "/handoverLog.do?method=updOtherData",
			dataType : "json",
			data : "OData.id="+id+"&OData.supervisorId="+supervisorId+"&handoverLog.id="+handoverLogId
			+"&OData.situationExplain="+ODataSituationExplain+"&OData.specialOperation="+ODataSpecialOperation,
			success : function(data) {
							
			}
		});		
			 

}
function goUpdOfficeEquipment(){
	var supervisorId=document.getElementById("handoverLog.deliverer").value;
	var id=document.getElementById("officeEquipment.id").value;
	var handoverLogId=document.getElementById("handoverLog.id").value;
	var computerProperty="";
	var computerPropertyArr=document.getElementsByName("officeEquipment.computerProperty");
	for(var i=0;i<computerPropertyArr.length;i++){
        if(computerPropertyArr[i].checked){    
        	computerProperty=computerPropertyArr[i].value;
        }
    }
	var computerPropertyReason=document.getElementById("officeEquipment.computerPropertyReason").value;
	
	var computerOnPassword="";
	var computerOnPasswordArr=document.getElementsByName("officeEquipment.computerOnPassword");
	for(var i=0;i<computerOnPasswordArr.length;i++){
        if(computerOnPasswordArr[i].checked){    
        	computerOnPassword=computerOnPasswordArr[i].value;
        }
    }
	var computerPassword=document.getElementById("officeEquipment.computerPassword").value;
	
	var computerAppearance="";
	var computerAppearanceArr=document.getElementsByName("officeEquipment.computerAppearance");
	for(var i=0;i<computerAppearanceArr.length;i++){
        if(computerAppearanceArr[i].checked){    
        	computerAppearance=computerAppearanceArr[i].value;
        }
    }
	var mouseProperty="";
	var mousePropertyArr=document.getElementsByName("officeEquipment.mouseProperty");
	for(var i=0;i<mousePropertyArr.length;i++){
        if(mousePropertyArr[i].checked){    
        	mouseProperty=mousePropertyArr[i].value;
        }
    }
	var cameraProperty="";
	var cameraPropertyArr=document.getElementsByName("officeEquipment.cameraProperty");
	for(var i=0;i<cameraPropertyArr.length;i++){
        if(cameraPropertyArr[i].checked){    
        	cameraProperty=cameraPropertyArr[i].value;
        }
    }
	var safetyBoxProperty="";
	var safetyBoxPropertyArr=document.getElementsByName("officeEquipment.safetyBoxProperty");
	for(var i=0;i<safetyBoxPropertyArr.length;i++){
        if(safetyBoxPropertyArr[i].checked){    
        	safetyBoxProperty=safetyBoxPropertyArr[i].value;
        }
    }
	var safetyBoxAppearance="";
	var safetyBoxAppearanceArr=document.getElementsByName("officeEquipment.safetyBoxAppearance");
	for(var i=0;i<safetyBoxAppearanceArr.length;i++){
        if(safetyBoxAppearanceArr[i].checked){    
        	safetyBoxAppearance=safetyBoxAppearanceArr[i].value;
        }
    }
	var safetyBoxPasswordStatus="";
	var safetyBoxPasswordStatusArr=document.getElementsByName("officeEquipment.safetyBoxPasswordStatus");
	for(var i=0;i<safetyBoxPasswordStatusArr.length;i++){
		if(safetyBoxPasswordStatusArr[i].checked){    
			safetyBoxPasswordStatus=safetyBoxPasswordStatusArr[i].value;
        }
	}
	var safetyBoxPassword=document.getElementById("officeEquipment.safetyBoxPassword").value;
	var keyAmount="";
	var keyAmountArr=document.getElementsByName("officeEquipment.keyAmount");
	for(var i=0;i<keyAmountArr.length;i++){
		if(keyAmountArr[i].checked){    
			keyAmount=keyAmountArr[i].value;
        }
	}
	var situationExplain=document.getElementById("officeEquipment.situationExplain").value;
	var headsetProperty="";
	var headsetPropertyArr=document.getElementsByName("officeEquipment.headsetProperty");
	for(var i=0;i<headsetPropertyArr.length;i++){
		if(headsetPropertyArr[i].checked){    
			headsetProperty=headsetPropertyArr[i].value;
        }
	}
	var haveCard="";
	var haveCardArr=document.getElementsByName("officeEquipment.haveCard");
	for(var i=0;i<haveCardArr.length;i++){
		if(haveCardArr[i].checked){    
			haveCard=haveCardArr[i].value;
        }
	}
	var qqNumber=document.getElementById("officeEquipment.qqNumber").value;
	var qqPassword=document.getElementById("officeEquipment.qqPassword").value;
	var borrowGoods="";
	var borrowGoodsArr=document.getElementsByName("officeEquipment.borrowGoods");
	for(var i=0;i<borrowGoodsArr.length;i++){
		if(borrowGoodsArr[i].checked){    
			borrowGoods=borrowGoodsArr[i].value;
        }
	}
	var borrowGoodsRemark=document.getElementById("officeEquipment.borrowGoodsRemark").value;
	var moneyStatus="";
	var moneyStatusArr=document.getElementsByName("officeEquipment.moneyStatus");
	for(var i=0;i<moneyStatusArr.length;i++){
		if(moneyStatusArr[i].checked){    
			moneyStatus=moneyStatusArr[i].value;
        }
	}
	var moneyRemark=document.getElementById("officeEquipment.moneyRemark").value;
	var merchantRemark=document.getElementById("officeEquipment.merchantRemark").value;
	var safetyBoxPropertyReason=document.getElementById("officeEquipment.safetyBoxPropertyReason").value;
	
	var mouseRemark = getElement("officeEquipment.mouseRemark").value;
	var cameraRemark = getElement("officeEquipment.cameraRemark").value;
	var headsetRemark = getElement("officeEquipment.headsetRemark").value;
/* 	window.location=window.encodeURI(window.encodeURI("<url:context/>/handoverLog.do?method=updOfficeEquipment&officeEquipment.id="+id+"&officeEquipment.supervisorId="+supervisorId+"&handoverLog.id="+handoverLogId
			+"&officeEquipment.computerProperty="+computerProperty+"&officeEquipment.computerProperty="+computerProperty
			+"&officeEquipment.computerPropertyReason="+computerPropertyReason+"&officeEquipment.computerOnPassword="+computerOnPassword+"&officeEquipment.computerPassword="+computerPassword
			+"&officeEquipment.computerAppearance="+computerAppearance+"&officeEquipment.mouseProperty="+mouseProperty+"&officeEquipment.cameraProperty="+cameraProperty
			+"&officeEquipment.safetyBoxProperty="+safetyBoxProperty+"&officeEquipment.safetyBoxAppearance="+safetyBoxAppearance+"&officeEquipment.safetyBoxPasswordStatus="+safetyBoxPasswordStatus
			+"&officeEquipment.safetyBoxPassword="+safetyBoxPassword+"&officeEquipment.keyAmount="+keyAmount+"&officeEquipment.situationExplain="+situationExplain
			+"&officeEquipment.headsetProperty="+headsetProperty+"&officeEquipment.haveCard="+haveCard+"&officeEquipment.qqNumber="+qqNumber
			+"&officeEquipment.qqNumber="+qqNumber+"&officeEquipment.qqPassword="+qqPassword+"&officeEquipment.borrowGoods="+borrowGoods
			+"&officeEquipment.borrowGoodsRemark="+borrowGoodsRemark+"&officeEquipment.moneyStatus="+moneyStatus+"&officeEquipment.moneyRemark="+moneyRemark
			+"&officeEquipment.merchantRemark="+merchantRemark+"&officeEquipment.safetyBoxPropertyReason="+safetyBoxPropertyReason));  */

	$.ajax({
		type:"POST",
		url:"/handoverLog.do?method=updOfficeEquipment",
		dataType : "json",
		data : "officeEquipment.id="+id+"&officeEquipment.supervisorId="+supervisorId+"&handoverLog.id="+handoverLogId
			+"&officeEquipment.computerProperty="+computerProperty+"&officeEquipment.computerProperty="+computerProperty
			+"&officeEquipment.computerPropertyReason="+computerPropertyReason+"&officeEquipment.computerOnPassword="+computerOnPassword+"&officeEquipment.computerPassword="+computerPassword
			+"&officeEquipment.computerAppearance="+computerAppearance+"&officeEquipment.mouseProperty="+mouseProperty+"&officeEquipment.cameraProperty="+cameraProperty
			+"&officeEquipment.safetyBoxProperty="+safetyBoxProperty+"&officeEquipment.safetyBoxAppearance="+safetyBoxAppearance+"&officeEquipment.safetyBoxPasswordStatus="+safetyBoxPasswordStatus
			+"&officeEquipment.safetyBoxPassword="+safetyBoxPassword+"&officeEquipment.keyAmount="+keyAmount+"&officeEquipment.situationExplain="+situationExplain
			+"&officeEquipment.headsetProperty="+headsetProperty+"&officeEquipment.haveCard="+haveCard+"&officeEquipment.qqNumber="+qqNumber
			+"&officeEquipment.qqNumber="+qqNumber+"&officeEquipment.qqPassword="+qqPassword+"&officeEquipment.borrowGoods="+borrowGoods
			+"&officeEquipment.borrowGoodsRemark="+borrowGoodsRemark+"&officeEquipment.moneyStatus="+moneyStatus+"&officeEquipment.moneyRemark="+moneyRemark
			+"&officeEquipment.merchantRemark="+merchantRemark+"&officeEquipment.safetyBoxPropertyReason="+safetyBoxPropertyReason
			+"&officeEquipment.mouseRemark="+mouseRemark+"&officeEquipment.cameraRemark="+cameraRemark+"&officeEquipment.headsetRemark="+headsetRemark,
		success : function(data) {
		} 
	
	
	});
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
</style>
</head>
<body  onLoad="doLoad()">

<br/>

<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/handoverLog" styleId="handoverLogForm" method="post" onsubmit="return false" enctype="multipart/form-data">
<input type="hidden" name="method" id="method" value="updHandoverLog"/>
<html:hidden property="handoverLog.id" styleId="handoverLog.id" />
<html:hidden property="eText.id" styleId="eText.id" />
<html:hidden property="officeEquipment.id" styleId="officeEquipment.id" />
<html:hidden property="OData.id" styleId="OData.id" />
<html:hidden property="pText.id" styleId="pText.id" />
<table class="formTable">
	<tr class="formTitle">
		<td colspan="4">监管员工作交接书</td>
	</tr>
	<tr>
		<td colspan="4"> 
			<table >
				<tr>
					<td class="nameCol">交接类型：</td>
					<td>
						<form:select property="handoverLog.handoverType" disabled="true"
								styleId="handoverLog.handoverType" >
							<html:optionsCollection name="handoverTypes" label="label" value="value" />
						</form:select></td>
					</td>
				</tr>
				<tr>
					<td class="nameCol">经销商：</td>
					<td class="codeCol">
						<form:select property="handoverLog.dealerId" disabled="true"
							styleId="handoverLog.dealerId" onchange="changeDealer(this.value)">
							<html:option value="-1">请选择</html:option>
							<html:optionsCollection name="dealers" label="label" value="value" />
						</form:select></td>
					</td>
					<td class="nameCol">经销商品牌：</td>
					<td class="codeCol"><input type="text"  id="brand" disabled="true" /></td>
					<td class="nameCol">经销商地址：</td>
					<td class="codeCol"><input type="text"  id="address" disabled="true" /></td>
				</tr>
				<tr>
					<td class="nameCol">合作银行（总）：</td>
					<td class="codeCol"><input type="text"  id="headBank"disabled="true" /></td>
					<td class="nameCol">合作银行（分）：</td>
					<td class="codeCol"><input type="text" id="branch"disabled="true" /></td>
					<td class="nameCol">合作银行（支）：</td>
					<td class="codeCol"><input type="text" id="subbranch"disabled="true" /></td>
				</tr>
				<tr>
					<td class="nameCol">交付人：</td>
					<td class="codeCol">
						 <form:select property="handoverLog.deliverer"  disabled="true"
								styleId="handoverLog.deliverer" onchange="changeDeliverer(this.value)">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="supervisors" label="label"
									value="value" />
						</form:select>
					</td> 
					<td class="nameCol">联系方式：</td>
					<td class="codeCol"><input type="text"  id="contactNumber" disabled="disabled"/></td>
					<td class="nameCol">交接时间：</td>
					<td class="codeCol">
						<form:calendar property="handoverLog.handoverDate" styleId="handoverLog.handoverDate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" disabled="true"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol">交接原因：</td>
					<td class="nameCol"style="text-align: left;" colspan="5">
						<form:radios  property="handoverLog.handoverNature" collection="handoverNatures" styleId="handoverLog.handoverNature" disabled="true"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol">接收人：</td>
					<td class="codeCol">
						 <form:select property="handoverLog.receiver" disabled="true"
								styleId="handoverLog.receiver" onchange="changeReceiver(this.value)">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="supervisors" label="label"
									value="value" />
							</form:select>
					</td>
					<td class="nameCol">性别：</td>
					<td class="codeCol"><input type="text"  id="rgender" disabled="disabled"/></td>
					<td class="nameCol">身份证号：</td>
					<td class="codeCol"><input type="text"  id="ridNumber" disabled="disabled"/></td>
				</tr>
				 <tr>
					<td class="nameCol">籍贯：</td>
					<td class="codeCol"><input type="text"  id="rnativePlace" disabled="disabled"/></td>
				 	<td class="nameCol">学历：</td>
					<td class="codeCol"><input type="text"  id="reducationBackground" disabled="disabled"/></td>
					<td class="nameCol">联系方式：</td>
					<td class="codeCol"><input type="text"  id="rcontactNumber" disabled="disabled"/></td>
				</tr>
				 <tr>
					<td class="nameCol">推荐人：</td>
					<td class="codeCol"><input type="text"  id="rrecommenderName" disabled="disabled"/></td>
				 	<td class="nameCol">招聘渠道：</td>
					<td class="codeCol"><input type="text"  id="rrecommendChannel" disabled="disabled"/></td>
				</tr>
				<tr>
					<td class="nameCol">接收性质：</td>
					<td class="nameCol"style="text-align: left;" >
						<form:radios  property="handoverLog.receiveNature" collection="receiveNatures" styleId="handoverLog.receiveNature" disabled="true"/>
					</td>
					<td class="nameCol">接收后属性：</td>
					<td class="nameCol"style="text-align: left;" >
						<form:radios  property="handoverLog.afterHandoverNature" collection="afterHandoverNature" styleId="handoverLog.afterHandoverNature" disabled="true" />
					</td>
					<td class="nameCol">上岗时间：</td>
					<td class="codeCol">
						<form:calendar property="handoverLog.missionDate" styleId="handoverLog.missionDate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true"  disabled="true" />
					</td>
				</tr>
				<tr>
					
					<td class="nameCol">岗前是否进店培训：</td>
					<td class="codeCol" style="text-align: left;">
						<input type="text" id="isTrain" disabled="disabled" />
					</td>
					<td class="nameCol">考核人员姓名：</td>
					<td class="codeCol" style="text-align: left;">
						<input type="text" id="trainer" disabled="disabled" />
					</td>
				</tr>
				<tr>
					<td class="nameCol">培训内容：</td>
					<td class="codeCol"colspan="5" style="text-align: left;">
						<input type="text" id="trainingContent" disabled="disabled" />
					</td>
				</tr>
				<tr>
				</tr>
				<tr></tr>
			</table>
		</td>
	</tr>
	<tr class="formTitle">
		<td colspan="4">一、业务内容交接</td>
	</tr>
	<tr>
		<td class="nameCol" colspan="7" style="text-align: left;">1、在库车辆</td>
	</tr>
	<tr>
		<td colspan="7">
		<div class="dvScroll">
			<table  class="listTalbe" cellpadding="0" cellspacing="0">
				<thead>
					<tr class="title">
				      <td>序号</td>
				      <td><thumbpage:order cname="车辆型号" filedName="car_model"/></td> 
				      <td><thumbpage:order cname="颜色" filedName="color"/></td>
				      <td><thumbpage:order cname="车架号" filedName="vin"/></td>
				      <td><thumbpage:order cname="金额" filedName="money"/></td>
				      <td><thumbpage:order cname="合格证号" filedName="certificate_num"/></td>
				      <td><thumbpage:order cname="钥匙号" filedName="key_num"/></td>
				      <td><thumbpage:order cname="钥匙数量（把）" filedName="key_amount"/></td> 
				      <td><thumbpage:order cname="车辆状态" filedName="state"/></td>
				    </tr>
				</thead>
				<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
					<logic:iterate name="inStockCar" id="row" indexId="index">
						<tr class="listTr_a">
							<td align="center">&nbsp;<c:out value="${index+1}"/></td>
							<td>&nbsp;<c:out value="${row.car_model}" /></td>
							<td>&nbsp;<c:out value="${row.color}" /></td>
							<td>&nbsp;<c:out value="${row.vin}" /></td>
							<td>&nbsp;<c:out value="${row.money}" /></td>
							<td>&nbsp;<c:out value="${row.certificate_num}" /></td>
							<td>&nbsp;<c:out value="${row.key_num}" /></td>
							<td>&nbsp;<c:out value="${row.key_amount}" /></td>
							<td>&nbsp;
								<c:choose>  
								   <c:when test="${row.state==1}">在途</c:when>  
								   <c:when test="${row.state==2}">在库  </c:when>  
								   <c:when test="${row.state==3}">出库</c:when> 
								   <c:when test="${row.state==4}">移动</c:when>  
								   <c:when test="${row.state==5}">私自售卖</c:when>  
								   <c:when test="${row.state==6}">私自移动</c:when>  
								</c:choose>  
							</td>
						</tr>
					</logic:iterate>
				</tbody>  
			</table>
		</div>
		</td>
	</tr>
	<tr>
		<td colspan="7" align="right">
			合计：<c:out value="${zkCount}"/> 辆         合计金额：<c:out value="${zkMoney}" />
		</td>
	</tr>
	<tr>
		<td class="nameCol" colspan="7" style="text-align: left;">2、在途车辆</td>
	</tr>
	<tr>
		<td colspan="7">
			<div class="dvScroll">
				<table  class="listTalbe" cellpadding="0" cellspacing="0">
					<thead>
						<tr class="title">
					      <td>序号</td>
					       <td><thumbpage:order cname="车辆型号" filedName="car_model"/></td> 
					      <td><thumbpage:order cname="车架号" filedName="vin"/></td>
					       <td><thumbpage:order cname="金额" filedName="money"/></td>
					      <td><thumbpage:order cname="颜色" filedName="color"/></td>
					      <td><thumbpage:order cname="合格证号" filedName="certificate_num"/></td>
					      <td><thumbpage:order cname="钥匙数量（把）" filedName="key_amount"/></td> 
					      <td><thumbpage:order cname="车辆状态" filedName="state"/></td>
					    </tr>
					</thead>
					<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
						<logic:iterate name="onWayCar" id="row" indexId="index">
							<tr class="listTr_a">
								<td align="center">&nbsp;<c:out value="${index+1}"/></td>
								<td>&nbsp;<c:out value="${row.car_model}" /></td>
								<td>&nbsp;<c:out value="${row.vin}" /></td>
								<td>&nbsp;<c:out value="${row.money}" /></td>
								<td>&nbsp;<c:out value="${row.color}" /></td>
								<td>&nbsp;<c:out value="${row.certificate_num}" /></td>
								<td>&nbsp;<c:out value="${row.key_amount}" /></td>
								<td>&nbsp;
									<c:choose>  
									   <c:when test="${row.state==1}">在途</c:when>  
									   <c:when test="${row.state==2}">在库  </c:when>  
									   <c:when test="${row.state==3}">出库</c:when> 
									   <c:when test="${row.state==4}">移动</c:when>  
									   <c:when test="${row.state==5}">私自售卖</c:when>  
									   <c:when test="${row.state==6}">私自移动</c:when>  
									</c:choose>  
								</td>
							</tr>
						</logic:iterate>
					</tbody>  
				</table>
			</div>
		</td>
		
	</tr>
	<tr>
		<td colspan="7" align="right">
			合计：<c:out value="${ztCount}"/> 辆        合计金额：<c:out value="${ztMoney}" />
		</td>
	</tr>
	<tr>
		<td class="nameCol" colspan="7" style="text-align: left;">3、销售未还款车辆</td>
	</tr>
	<tr>
		<td colspan="7">
			<div class="dvScroll">
				<table  class="listTalbe" cellpadding="0" cellspacing="0">
					<thead>
						<tr class="title">
					      <td>序号</td>
					      <td><thumbpage:order cname="车辆型号" filedName="car_model"/></td> 
					      <td><thumbpage:order cname="颜色" filedName="color"/></td>
					      <td><thumbpage:order cname="车架号" filedName="vin"/></td>
					      <td><thumbpage:order cname="合格证号" filedName="certificate_num"/></td>
					      <td><thumbpage:order cname="钥匙号" filedName="key_num"/></td>
					      <td><thumbpage:order cname="钥匙数量（把）" filedName="key_amount"/></td> 
					      <td><thumbpage:order cname="车辆状态" filedName="state"/></td>
					    </tr>
					</thead>
					<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
						<logic:iterate name="sellNoPayCar" id="row" indexId="index">
							<tr class="listTr_a">
								<td align="center">&nbsp;<c:out value="${index+1}"/></td>
								<td>&nbsp;<c:out value="${row.car_model}" /></td>
								<td>&nbsp;<c:out value="${row.color}" /></td>
								<td>&nbsp;<c:out value="${row.vin}" /></td>
								<td>&nbsp;<c:out value="${row.certificate_num}" /></td>
								<td>&nbsp;<c:out value="${row.key_num}" /></td>
								<td>&nbsp;<c:out value="${row.key_amount}" /></td>
								<td>&nbsp;
									<c:choose>  
									   <c:when test="${row.state==1}">在途</c:when>  
									   <c:when test="${row.state==2}">在库  </c:when>  
									   <c:when test="${row.state==3}">出库</c:when> 
									   <c:when test="${row.state==4}">移动</c:when> 
									   <c:when test="${row.state==5}">私自售卖</c:when>  
									   <c:when test="${row.state==6}">私自移动</c:when>   
									</c:choose>  
								</td>
							</tr>
						</logic:iterate>
					</tbody>  
				</table>
			</div>
		</td>
	</tr>
	<tr>
		<td class="nameCol" colspan="7" style="text-align: left;">4、私自售卖车辆</td>
	</tr>
	<tr>
		<td colspan="7">
			<div class="dvScroll">
				<table  class="listTalbe" cellpadding="0" cellspacing="0">
					<thead>
						<tr class="title">
					      <td>序号</td>
					      <td><thumbpage:order cname="车辆型号" filedName="car_model"/></td> 
					      <td><thumbpage:order cname="颜色" filedName="color"/></td>
					      <td><thumbpage:order cname="车架号" filedName="vin"/></td>
					      <td><thumbpage:order cname="合格证号" filedName="certificate_num"/></td>
					      <td><thumbpage:order cname="钥匙号" filedName="key_num"/></td>
					      <td><thumbpage:order cname="钥匙数量（把）" filedName="key_amount"/></td> 
					      <td><thumbpage:order cname="车辆状态" filedName="state"/></td>
					      <td align="center">操作</td>
					    </tr>
					</thead>
					<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
						<logic:iterate name="privateSellCar" id="row" indexId="index">
							<tr class="listTr_a">
								<td align="center">&nbsp;<c:out value="${index+1}"/></td>
								<td>&nbsp;<c:out value="${row.car_model}" /></td>
								<td>&nbsp;<c:out value="${row.color}" /></td>
								<td>&nbsp;<c:out value="${row.vin}" /></td>
								<td>&nbsp;<c:out value="${row.certificate_num}" /></td>
								<td>&nbsp;<c:out value="${row.key_num}" /></td>
								<td>&nbsp;<c:out value="${row.key_amount}" /></td>
								<td>&nbsp;
									<c:choose>  
									   <c:when test="${row.state==1}">在途</c:when>  
									   <c:when test="${row.state==2}">在库  </c:when>  
									   <c:when test="${row.state==3}">出库</c:when> 
									   <c:when test="${row.state==4}">移动</c:when>
									   <c:when test="${row.state==5}">私自售卖</c:when>  
									   <c:when test="${row.state==6}">私自移动</c:when>    
									</c:choose>  
								</td>
								<td align="center" nowrap="true" class="right-border-none">
									<a href="javascript:goUpdPrivateSellCar('<c:out value='${row.id}'/>');">修改</a>&nbsp;
									<a href="javascript:goDelSuperviseImport('<c:out value='${row.id}'/>');">删除</a>&nbsp;
								</td>
							</tr>
						</logic:iterate>
					</tbody>  
				</table>
				<table class="bottomTable">
					<tr align="center">
						<td><button class="formButton" onClick="goAddPrivateSellCar()">新增私自售卖车辆</button></td>
					</tr>
				</table>
			</div>
		</td>
	</tr> 
	<tr>
		<td class="nameCol" colspan="7" style="text-align: left;">5、私自移动车辆</td>
	</tr>
	<tr>
		<td colspan="7">
			<div class="dvScroll">
				<table  class="listTalbe" cellpadding="0" cellspacing="0">
					<thead>
						<tr class="title">
					      <td>序号</td>
					      <td><thumbpage:order cname="车辆型号" filedName="car_model"/></td> 
					      <td><thumbpage:order cname="颜色" filedName="color"/></td>
					      <td><thumbpage:order cname="车架号" filedName="vin"/></td>
					      <td><thumbpage:order cname="合格证号" filedName="certificate_num"/></td>
					      <td><thumbpage:order cname="钥匙号" filedName="key_num"/></td>
					      <td><thumbpage:order cname="钥匙数量（把）" filedName="key_amount"/></td> 
					      <td><thumbpage:order cname="车辆状态" filedName="state"/></td>
					      <td align="center">操作</td>
					    </tr>
					</thead>
					<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
						<logic:iterate name="privateMoveCar" id="row" indexId="index">
							<tr class="listTr_a">
								<td align="center">&nbsp;<c:out value="${index+1}"/></td>
								<td>&nbsp;<c:out value="${row.car_model}" /></td>
								<td>&nbsp;<c:out value="${row.color}" /></td>
								<td>&nbsp;<c:out value="${row.vin}" /></td>
								<td>&nbsp;<c:out value="${row.certificate_num}" /></td>
								<td>&nbsp;<c:out value="${row.key_num}" /></td>
								<td>&nbsp;<c:out value="${row.key_amount}" /></td>
								<td>&nbsp;
									<c:choose>  
									   <c:when test="${row.state==1}">在途</c:when>  
									   <c:when test="${row.state==2}">在库  </c:when>  
									   <c:when test="${row.state==3}">出库</c:when> 
									   <c:when test="${row.state==4}">移动</c:when>
									   <c:when test="${row.state==5}">私自售卖</c:when>  
									   <c:when test="${row.state==6}">私自移动</c:when>    
									</c:choose>  
								</td>
								<td align="center" nowrap="true" class="right-border-none">
									<a href="javascript:goUpdPrivateMoveCar('<c:out value='${row.id}'/>');">修改</a>&nbsp;
									<a href="javascript:goDelSuperviseImport('<c:out value='${row.id}'/>');">删除</a>&nbsp;
								</td>
							</tr>
						</logic:iterate>
					</tbody>  
				</table>
				<table class="bottomTable">
					<tr align="center">
						<td><button class="formButton" onClick="goAddPrivateMoveCar()">新增私自移动车辆</button></td>
					</tr>
				</table>
			</div>
		</td>
	</tr> 
	<tr>
		<td colspan="4"> 
			<table style="width:100%;">
				<tr >
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
				</tr>
				<tr >
					<td class="nameCol" colspan="8" style="text-align: left;">6、电子文本资料</td>
				</tr>
				<tr>
					<td class="nameCol" >电子台账：</td>
					<td class="codeCol" colspan="3" >
						<html:text style="width:350px" property="eText.electronBill" styleId="eText.electronBill"/>
					</td>
					<td class="nameCol">周库存核对清单：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="eText.weekBillAmount" styleId="eText.weekBillAmount"/> 份，
						时间：自     <form:calendar style="width:80px" property="eText.weekBillStartTime" styleId="eText.weekBillStartTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="eText.weekBillEndTime" styleId="eText.weekBillEndTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
				</tr>
				<tr>
					<td class="nameCol">接车明细：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="eText.receiveCarDetailAmount" styleId="eText.receiveCarDetailAmount"/> 份，
						时间：自     <form:calendar style="width:80px" property="eText.receiveCarDetailStartTime" styleId="eText.receiveCarDetailStartTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="eText.receiveCarDetailEndTime" styleId="eText.receiveCarDetailEndTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
					<td class="nameCol" >月库存核对清单：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="eText.monthBillAmount" styleId="eText.monthBillAmount"/> 份，
						时间：自     <form:calendar style="width:80px" property="eText.monthBillStartTime" styleId="eText.monthBillStartTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="eText.monthBillEndTime" styleId="eText.monthBillEndTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
				</tr>
				<tr>
					<td class="nameCol">申领释放书：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="eText.applyFreeBookAmount" styleId="eText.applyFreeBookAmount"/> 份，
						时间：自     <form:calendar style="width:80px" property="eText.applyFreeBookStartTime" styleId="eText.applyFreeBookStartTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="eText.applyFreeBookEndTime" styleId="eText.applyFreeBookEndTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
					<td class="nameCol" >监管确认书：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="eText.confirmationAmount" styleId="eText.confirmationAmount"/> 份，
						时间：自     <form:calendar style="width:80px" property="eText.confirmationStartTime" styleId="eText.confirmationStartTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="eText.confirmationEndTime" styleId="eText.confirmationEndTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
				</tr>
				<tr>
					<td class="nameCol">二网申请：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="eText.towApplyAmount" styleId="eText.towApplyAmount"/> 份，
						时间：自     <form:calendar style="width:80px" property="eText.towApplyStartTime" styleId="eText.towApplyStartTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="eText.towApplyEndTime" styleId="eText.towApplyEndTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
					<td class="nameCol" >其他：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="eText.otherAmount" styleId="eText.otherAmount"/> 份，
						时间：自     <form:calendar style="width:80px" property="eText.otherStartTime" styleId="eText.otherStartTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="eText.otherEndTime" styleId="eText.otherEndTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
				</tr>
				<tr>
					<td class="nameCol" >备注：</td>
					<td class="codeCol" colspan="7">
						<html:text style="width:800px" property="eText.remark" styleId="eText.remark"/>
					</td>
				</tr>
				<!-- <tr>
					<td align="center" colspan="8">
						<table class="bottomTable" >
							<tr align="center">
								<td align="center"><button class="formButton" onClick="goUpdElectronicText()">保存电子文本资料</button></td>
							</tr>
						</table>
					</td>
				</tr> -->
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4"> 
			<table >
				<tr >
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
					<td class="nameCol" ></td>
				</tr>
				<tr >
					<td class="nameCol" colspan="8" style="text-align: left;">7、纸质文本资料</td>
				</tr>
				<tr>
					<td class="nameCol" >手工台账：</td>
					<td class="codeCol" colspan="3" >
						共  <html:text style="width:30px" property="pText.paperBillAmount" styleId="pText.paperBillAmount"/> 份，
						时间：自     <form:calendar style="width:80px" property="pText.paperBillStartTime" styleId="pText.paperBillStartTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="pText.paperBillEndTime" styleId="pText.paperBillEndTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
					<td class="nameCol">二网申请：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="pText.towApplyAmount" styleId="pText.towApplyAmount"/> 份，
						时间：自     <form:calendar style="width:80px" property="pText.towApplyStartTime" styleId="pText.towApplyStartTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="pText.towApplyEndTime" styleId="pText.towApplyEndTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
				</tr>
				<tr>
					<td class="nameCol">换证申请：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="pText.changeApplyAmount" styleId="pText.changeApplyAmount"/> 份，
						时间：自     <form:calendar style="width:80px" property="pText.changeApplyStartTime" styleId="pText.changeApplyStartTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="pText.changeApplyEndTime" styleId="pText.changeApplyEndTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
					<td class="nameCol" >监管确认书：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="pText.confirmationAmount" styleId="pText.confirmationAmount"/> 份，
						时间：自     <form:calendar style="width:80px" property="pText.confirmationStartTime" styleId="pText.confirmationStartTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="pText.confirmationEndTime" styleId="pText.confirmationEndTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
				</tr>
				<tr>
					<td class="nameCol">申领释放书：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="pText.applyFreeBookAmount" styleId="pText.applyFreeBookAmount"/> 份，
						时间：自     <form:calendar style="width:80px" property="pText.applyFreeBookStartTime" styleId="pText.applyFreeBookStartTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="pText.applyFreeBookEndTime" styleId="pText.applyFreeBookEndTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
					<td class="nameCol">授权书(店方)</td>
					<td class="nameCol"style="text-align: left;" colspan="3" >
						<form:radios  property="pText.authorization" collection="haveorno" styleId="pText.authorization" />
					</td>
				</tr>
				<tr>
					<td class="nameCol">周库存核对清单：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="pText.weekBillAmount" styleId="pText.weekBillAmount"/> 份，
						时间：自     <form:calendar style="width:80px" property="pText.weekBillStartTime" styleId="pText.weekBillStartTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="pText.weekBillEndTime" styleId="pText.weekBillEndTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
					<td class="nameCol">经销商告知函</td>
					<td class="nameCol" style="text-align: left;" colspan="3" >
						<form:radios  property="pText.informLetter" collection="haveorno" styleId="pText.informLetter" />
					</td>
				</tr>
				<tr>
					<td class="nameCol" >月库存核对清单：</td>
					<td class="codeCol" colspan="3">
						共  <html:text style="width:30px" property="pText.monthBillAmount" styleId="pText.monthBillAmount"/> 份，
						时间：自     <form:calendar style="width:80px" property="pText.monthBillStartTime" styleId="pText.monthBillStartTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="pText.monthBillEndTime" styleId="pText.monthBillEndTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
					<td class="nameCol">合格证、车钥匙：</td>
					<td class="nameCol" style="text-align: left;" colspan="3" >
						数量：<form:radios  property="pText.keyAmount" collection="matchorno" styleId="pText.keyAmount" />
					</td>
				</tr>
				<tr>
					<td class="nameCol" >其他：</td>
					<td class="codeCol" colspan="7">
						共  <html:text style="width:30px" property="pText.otherAmount" styleId="pText.otherAmount"/> 份，
						时间：自     <form:calendar style="width:80px" property="pText.otherStartTime" styleId="pText.otherStartTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
						至     <form:calendar style="width:80px" property="pText.otherEndTime" styleId="pText.otherEndTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />  
					</td>
				</tr>
				<!-- <tr>
					<td colspan="8">
						<table class="bottomTable">
							<tr align="center">
								<td align="center"><button class="formButton" onClick="goUpdPaperText()">保存纸质文本资料</button></td>
							</tr>
						</table>
					</td>
				</tr> -->
			</table>
		</td>
	</tr>
	<tr >
		<td class="nameCol" colspan="4" style="text-align: left;">8、其他资料交接(未结清汇票)</td>
	</tr>
	<tr>
		<div class="dvScroll">
				<table  class="listTalbe" cellpadding="0" cellspacing="0">
					<thead>
						<tr class="title">
					      <td>序号</td>
					      <td><thumbpage:order cname="票号" filedName="draft_num"/></td> 
					      <td><thumbpage:order cname="开票日期" filedName="billing_date"/></td>
					      <td><thumbpage:order cname="到期日期" filedName="due_date"/></td>
					      <td><thumbpage:order cname="票面金额" filedName="billing_money"/></td>
					      <td><thumbpage:order cname="押车金额" filedName="mortgagecar_money"/></td>
					      <td><thumbpage:order cname="未押车金额" filedName="payment_money"/></td> 
					    </tr>
					</thead>
					<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
						<logic:iterate name="wjqdrafts" id="row" indexId="index">
							<tr class="listTr_a">
								<td align="center">&nbsp;<c:out value="${index+1}"/></td>
								<td>&nbsp;<c:out value="${row.draft_num}" /></td>
								<td>&nbsp;<c:out value="${row.billing_date}" /></td>
								<td>&nbsp;<c:out value="${row.due_date}" /></td>
								<td>&nbsp;<c:out value="${row.billing_money}" /></td>
								<td class="t-td"><select:draft draftid="${row.id}" idtype="yycje"/></td>
								<td class="t-td"><select:draft draftid="${row.id}" idtype="wycje"/></td>
							</tr>
						</logic:iterate>
					</tbody>  
				</table>
			</div>
	</tr>
	<tr>
		<td>
			<table>
				<tr>
					<td class="nameCol"></td>
					<td class="nameCol"></td>
					<td class="nameCol"></td>
					<td class="nameCol"></td>
					<td class="nameCol"></td>
				</tr>
				<tr>
					<td class="nameCol">情况说明：</td>
					<td class="codeCol" colspan="4">
						<html:textarea property="OData.situationExplain" styleId="OData.situationExplain" ></html:textarea>
					</td>
				</tr>
				<tr>
					<td class="nameCol">特殊操作说明：</td>
					<td class="codeCol"colspan="4">
						<html:textarea property="OData.specialOperation" styleId="OData.specialOperation" ></html:textarea>
					</td>
				</tr>
			</table>
			<!-- <table class="bottomTable">
				<tr align="center">
					<td align="center"><button class="formButton" onClick="goUpdOtherData()">保存其他资料</button></td>
				</tr>
			</table> -->
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<table>
				<tr class="formTitle">
					<td colspan="4">二、办公设备及用品交接</td>
				</tr>
				<tr>
					<td class="nameCol">项目</td>
					<td align="center" colspan="3">1.本公司交接内容</td>
				</tr>
				<tr>
					<td class="nameCol" rowspan="6">笔记本电脑</td>
					<td class="codeCol">品牌：<html:text style="width:100px" property="officeEquipment.computerAssetsNTT.brand"  styleId="dn.brand"  disabled="true" /></td>
					<td class="codeCol">型号：<html:text style="width:100px" property="officeEquipment.computerAssetsNTT.model"  styleId="dn.model" disabled="true"  /></td>
					<td class="codeCol">资产编号：<html:text style="width:100px" property="officeEquipment.computerAssetsNTT.asset_num"  styleId="dn.asset_num" disabled="true"  /></td>
				</tr>
				<tr>
					<td class="codeCol" colspan="3">使用性能：<form:radios  property="officeEquipment.computerProperty" collection="nomalorno" styleId="officeEquipment.computerProperty" />
						  <html:text style="width:200px" property="officeEquipment.computerPropertyReason" styleId="officeEquipment.computerPropertyReason" />
					</td>
				</tr>
				<tr>
					<td class="codeCol" colspan="3">开机密码：<form:radios  property="officeEquipment.computerOnPassword" collection="informorno" styleId="officeEquipment.computerOnPassword" />
						  <html:text style="width:200px" property="officeEquipment.computerPassword" styleId="officeEquipment.computerPassword" />
					</td>
				</tr>
				<tr>
					<td class="codeCol" colspan="3">外观：<form:radios  property="officeEquipment.computerAppearance" collection="breakorno" styleId="officeEquipment.computerAppearance" /></td>
				</tr>
				<tr>
					<td class="codeCol">鼠标</td>
					<td class="codeCol" colspan="2">使用性能：<form:radios  property="officeEquipment.mouseProperty" collection="nomalorno" styleId="officeEquipment.mouseProperty" />
						<html:text  property="officeEquipment.mouseRemark" styleId="officeEquipment.mouseRemark"/>
					</td>
				</tr>
				<tr>
					<td class="codeCol">摄像头</td>
					<td class="codeCol" colspan="2">使用性能：<form:radios  property="officeEquipment.cameraProperty" collection="nomalorno" styleId="officeEquipment.cameraProperty" />
					<html:text  property="officeEquipment.cameraRemark" styleId="officeEquipment.cameraRemark"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol" rowspan="6">保险柜</td>
					<td class="codeCol">品牌：<html:text style="width:100px" property="officeEquipment.boxesAssetsNTT.brand"  styleId="bxg.brand"  disabled="true" /></td>
					<td class="codeCol">型号：<html:text style="width:100px" property="officeEquipment.boxesAssetsNTT.model"  styleId="bxg.model"  disabled="true" /></td>
					<td class="codeCol">资产编号：<html:text style="width:100px" property="officeEquipment.boxesAssetsNTT.asset_num"  styleId="bxg.asset_num"  disabled="true" /></td>
				</tr>
				<tr>
					<td class="codeCol" colspan="3">使用性能：<form:radios  property="officeEquipment.safetyBoxProperty" collection="nomalorno" styleId="officeEquipment.safetyBoxProperty" />
					 	<html:text style="width:200px" property="officeEquipment.safetyBoxPropertyReason" styleId="officeEquipment.safetyBoxPropertyReason" />
					</td>
				</tr>
				<tr>
					<td class="codeCol" colspan="3">外观：<form:radios  property="officeEquipment.safetyBoxAppearance" collection="breakorno" styleId="officeEquipment.safetyBoxAppearance" /></td>
				</tr>
				<tr>
					<td class="codeCol" colspan="3">密码：<form:radios  property="officeEquipment.safetyBoxPasswordStatus" collection="informorno" styleId="officeEquipment.safetyBoxPasswordStatus" />
						  <html:text style="width:200px" property="officeEquipment.safetyBoxPassword" styleId="officeEquipment.safetyBoxPassword" />
					</td>
				</tr>
				<tr>
					<td class="codeCol" colspan="3">钥匙（一套四把）：<form:radios  property="officeEquipment.keyAmount" collection="keyAmountorno" styleId="officeEquipment.keyAmount" />
					</td>
				</tr>
				<tr>
					<td class="codeCol" colspan="3">其他情况说明：<html:text style="width:500px" property="officeEquipment.situationExplain" styleId="officeEquipment.situationExplain" />
					</td>
				</tr>
				<tr>
					<td class="nameCol">耳麦</td>
					<td class="codeCol" colspan="3">使用性能：<form:radios  property="officeEquipment.headsetProperty" collection="nomalorno" styleId="officeEquipment.headsetProperty" />
					<html:text  property="officeEquipment.headsetRemark" styleId="officeEquipment.headsetRemark"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol">监管员工牌</td>
					<td class="codeCol" colspan="3"><form:radios  property="officeEquipment.haveCard" collection="haveorno" styleId="officeEquipment.haveCard" />
					</td>
				</tr>
				<tr>
					<td class="nameCol">工作QQ</td>
					<td class="codeCol" colspan="3">
					         号码：<html:text style="width:150px" property="officeEquipment.qqNumber" styleId="officeEquipment.qqNumber" />
					        密码：<html:text style="width:150px" property="officeEquipment.qqPassword" styleId="officeEquipment.qqPassword" />      
					</td>
				</tr>
				<tr>
					<td  align="center" colspan="4">2.店方相关交接</td>
				</tr>
				<tr>
					<td class="nameCol">借用物品</td>
					<td class="codeCol" colspan="3"><form:radios  property="officeEquipment.borrowGoods" collection="haveorno" styleId="officeEquipment.borrowGoods" />
						 <html:text style="width:350px" property="officeEquipment.borrowGoodsRemark" styleId="officeEquipment.borrowGoodsRemark" />
					</td>
				</tr>
				<tr>
					<td class="nameCol">钱款</td>
					<td class="codeCol" colspan="3"><form:radios  property="officeEquipment.moneyStatus" collection="haveornoDebt" styleId="officeEquipment.moneyStatus" />
						 明细：<html:text style="width:350px" property="officeEquipment.moneyRemark" styleId="officeEquipment.moneyRemark" />
					</td>
				</tr>
				<tr>
					<td class="nameCol">其他</td>
					<td class="codeCol" colspan="3">
						<html:text style="width:500px" property="officeEquipment.merchantRemark" styleId="officeEquipment.merchantRemark" />
					</td>
				</tr>
				<!-- <tr>
					<td colspan="4">
						<table class="bottomTable">
							<tr align="center">
								<td align="center"><button class="formButton" onClick="goUpdOfficeEquipment()">保存办公用品及设备</button></td>
							</tr>
						</table>
					</td>
				</tr> -->
			</table>
		</td>
	</tr>
	
	<%-- <tr>
		<td class="nameCol">备注：</td>
		<td class="codeCol">
			<html:textarea property="hpic.remark" styleId="hpic.remark" readonly="true"></html:textarea>
		</td>
	</tr> --%>
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
			<button class="formButton" onClick="doUpdBook()">保存资料</button>&nbsp;&nbsp;
			<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
		</td>
	</tr>
</table>
<br/>
<div class="message" id="message" style="display:none"></div>
</html:form>
<%-- <html:form action="/handoverLog" styleId="handoverLogForm" method="post" onsubmit="return false" >
<input type="hidden" name="method" id="method" value="updOfficeEquipment"/>
<html:hidden property="handoverLog.id" styleId="handoverLog.id" />
<html:hidden property="eText.id" styleId="eText.id" />
<html:hidden property="officeEquipment.id" styleId="officeEquipment.id" />
</html:form> --%>

	</div>
</div>

</body>
</html>
