<%@page import="com.zd.csms.util.DateUtil"%>
<%@page import="java.util.Date"%>
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
<style type="text/css">
.info {
	 width: 107px;
     height: 60%;
     border: 1px solid #eee;
}
.ice {
	text-align:center !important;
/*     padding-right: 20% ; */
}
.bt {
    width: 20%;
    height: 67%;
    font-size: large;
    font-weight: 700;
    margin-left: 20%;
}
</style>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<link href="/css/base.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="/js/common.js"></script>
<script type="text/javascript">
	
	function trim(str) {
		return str.replace(/(^\s+)|(\s+$)/g, "");
	}
	$(function(){
		
		showMessage("<c:out value='${message}'/>");
		if(trim($("#flag").val()) == 2){
			$("#amSign_In").attr("disabled","disabled");
			$("#amSign_In").css("color","gray");
			$("#amSign_Out").attr("disabled","disabled");
			$("#amSign_Out").css("color","gray");
			$("#pmSign_In").attr("disabled","disabled");
			$("#pmSign_In").css("color","gray");
			$("#pmSign_Out").attr("disabled","disabled");
			$("#pmSign_Out").css("color","gray");
		}
		if(trim($("#flag").val()) == 1){
			if(trim($("#amSign_In_Time").text()).length > 0){
				$("#amSign_In").attr("disabled","disabled");
				$("#amSign_In").css("color","gray");
			}
			if(trim($("#amSign_Out_Time").text()).length > 0){
				$("#amSign_Out").attr("disabled","disabled");
				$("#amSign_Out").css("color","gray");
			}
			if(trim($("#pmSign_In_Time").text()).length > 0){
				$("#pmSign_In").attr("disabled","disabled");
				$("#pmSign_In").css("color","gray");
			}
			if(trim($("#pmSign_Out_Time").text()).length > 0){
				$("#pmSign_Out").attr("disabled","disabled");
				$("#pmSign_Out").css("color","gray");
			}
		}
		
		$("#amSign_In").click(function(){
			var morningStart = $("#morningStart").val();
			$("#amSign_In").attr("disabled","disabled");
			$("#amSign_In").css("color","gray");
			setTime(morningStart,1,'amSign_In_Time');
		});
		
		$("#amSign_Out").click(function(){
			var morningEnd = $("#morningEnd").val();
			$("#amSign_Out").attr("disabled","disabled");
			$("#amSign_Out").css("color","gray");
			setTime(morningEnd,2,'amSign_Out_Time');
		});
		$("#pmSign_In").click(function(){
			var afternoonStart = $("#afternoonStart").val();
			$("#pmSign_In").attr("disabled","disabled");
			$("#pmSign_In").css("color","gray");
			setTime(afternoonStart,3,'pmSign_In_Time');
		});
		$("#pmSign_Out").click(function(){
			var afternoonEnd = $("#afternoonEnd").val();
			$("#pmSign_Out").attr("disabled","disabled");
			$("#pmSign_Out").css("color","gray");
			setTime(afternoonEnd,4,'pmSign_Out_Time');
		});
	});
	
	function setTime(time,objid,id){
		var dealerId = $("[name='dealerId']").val();
		var url = "/json/saveAttendanceData.do?callback=?&objid="+ objid 
			+ "&dealerId=" + dealerId+"&currTime="+$("[name='currTime']").val();
		$.getJSON(url, function(result) {
			result = result.data[0];
			if(result.success){
				$("#"+id).text(result.nowDate);
			}else{
				alert(result.message);
			}

			
		});
	}
	
	$(function(){
		$("#currTime").datetimebox({
			showSeconds: false,
			onChange:function(newValue, oldValue){
				var currTime = newValue;
				newValue = new Date(newValue);
				oldValue = new Date(oldValue);
				if(newValue.getFullYear()!=oldValue.getFullYear()
						||newValue.getMonth()!=oldValue.getMonth()
						||newValue.getDate()!=oldValue.getDate()){
					location.href="/attSign.do?method=setAttendanceSign&currTime="+currTime;
				}
			}
		});
	});
	
</script>
</head>
<body>
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">考勤管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">考勤签到</a>
         </span>
</div>
	<div class="title"><h2>考勤</h2></div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/attSign" styleId="attendanceForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="addorupdateAttendanceTime" />
				<input type="hidden" name="dealerId" value="<c:out value='${attendance.id}'/>">
				<input type="hidden" name="workDays" id="workDays" value="<c:out value='${workDays}'/>">
				<input type="hidden" name="time.id" value="<c:out value='${dealer.id}'/>">
				<input type="hidden" name="flag" id="flag" value="<c:out value='${flag}'/>">
				<input type="hidden" name="userId" id="userId" value="<c:out value='${userId}'/>">
				<input type="hidden" name="respId" id="respId" value="<c:out value='${respId}'/>">

				<table class="formTable">
					<tr>
						<td class="nameCol" >时间设置(测试通过后移除)：</td>
						<td class="codeCol">
							<input id="currTime" name="currTime"  style="width:150px"
        					value='<fmt:formatDate value="${currTime }" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate>'
        					/>
						</td>
					</tr>
					<tr>
						<td class="nameCol" >经销商：</td>
						<td class="codeCol">
							<select:dealerName dealerid="${attendance.id}"/>
						</td>
						<td class="nameCol" style="color:red;">早上签到时间：</td>
						<td class="codeCol">
							<input id="morningStart" name="time.morningStart" class="info" readonly="readonly" style="color:red;"
							value="<c:out value='${attendance.morningStart}'/>"/>
						</td>
						<td class="nameCol" style="color:red;">中午签退时间：</td>
						<td class="codeCol">
							<input id="morningEnd" name="time.morningEnd" class="info" readonly="readonly" style="color:red;"
							value="<c:out value='${attendance.morningEnd}'/>"/>
						</td>
					</tr>	
					<tr>
						<td class="nameCol"></td><td class="codeCol"></td>
						<td class="nameCol" style="color:red;">下午签到时间：</td>
						<td class="codeCol">
							<input id="afternoonStart" name="time.afternoonStart" class="info" readonly="readonly" style="color:red;"
							value="<c:out value='${attendance.afternoonStart}'/>"/>
						</td>
						<td class="nameCol" style="color:red;">下午签退时间：</td>
						<td class="codeCol">
							<input id="afternoonEnd" name="time.afternoonEnd" class="info" readonly="readonly" style="color:red;"
							value="<c:out value='${attendance.afternoonEnd}'/>"/>
						</td>
					</tr>	
					<tr>
						<td class="nameCol ice" colspan="3" style="padding-left: 11%;">操作</td>
						<td class="nameCol ice" colspan="3" style="padding-right: 15%;">时间</td>
					</tr>
					<tr>
						<td class="nameCol ice" colspan="3">
							<button type="button" id="amSign_In" style="color:blue;" class="bt">早上签到</button>
						</td>
						<td class="nameCol ice" colspan="3" id="amSign_In_Time" style="padding-right: 15%;">
							<c:out value="${signMorningStart }"></c:out>
						</td>
					</tr>
					<tr>
						<td class="nameCol ice" colspan="3">
							<button type="button" id="amSign_Out" style="color:blue;" class="bt">中午签退</button>
						</td>
						<td class="nameCol ice" colspan="3" id="amSign_Out_Time" style="padding-right: 15%;">
							<c:out value="${signMorningEnd }"></c:out>
						</td>
					</tr>
					<tr>
						<td class="nameCol ice" colspan="3">
							<button type="button" id="pmSign_In" style="color:blue;" class="bt">下午签到</button>
						</td>
						<td class="nameCol ice" colspan="3" id="pmSign_In_Time" style="padding-right: 15%;">
							<c:out value="${signAfternoonStart }"></c:out>
						</td>
					</tr>
					<tr>
						<td class="nameCol ice" colspan="3">
							<button type="button" id="pmSign_Out" style="color:blue;" class="bt">下午签退</button>
						</td>
						<td class="nameCol ice" colspan="3" id="pmSign_Out_Time" style="padding-right: 15%;">
							<c:out value="${signAfternoonEnd }"></c:out>
						</td>
					</tr>	
					<tr>
						<td class="nameCol ice"></td>
						<td class="codeCol ice" colspan="3"></td>
					</tr>
					<tr>
						<td class="nameCol ice" style="padding-left: 8%;">考勤制度：</td>
						<td class="codeCol ice" colspan="5">
							<textarea id="systemContent" style="margin-left: -35%; border: 1px solid #eee;" readonly="readonly" maxlength="300" name="time.systemContent"><c:out value="${attendance.systemContent }"/></textarea>
						</td>
					</tr>
				<br />
				<div class="message" id="message" style="display: none"></div>
			</html:form>

		</div>
	</div>
</body>
</html>
