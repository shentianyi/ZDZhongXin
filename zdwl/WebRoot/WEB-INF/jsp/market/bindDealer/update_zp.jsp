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

<!-- <link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" /> -->
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>

<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script src="/pagejs/bindDealer.js"></script>
<script>
	//页面初始化函数
	function doLoad(){
		//显示提示信息
		showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	}
	
	$(function(){
		showDealer($("#dealerId").val().split(","));
		initDealer();
		$('[numberboxname=changeMoney]').numberbox('disable');
		$('[comboname=changeMoneyDate]').datebox('disable');
		$("[name=changeMode]").attr('disabled','disabled');
		changeSupervisor($("#repositoryId").val());
	});
	//执行保存操作
	function doSave() {
		if($("#repositoryId").val()==null||$("#repositoryId").val()=="-1"||$("#repositoryId").val()==""
				||$("#repositoryId").val()<=0){
			alert("请选择监管员");
			return false;
		}
		var value=""; 
		var arr = document.getElementsByName("bd.supervisorSource");
		for(var i=0;i<arr.length;i++){
		        if(arr[i].checked){    
		          value=arr[i].value;
		        }
		    }
		if(value == ""){
			alert("请选择监管员来源");
			return false;
		}
		var value = document.getElementById("bd.supervisorAttribute").value;
		if(value == ""){
			alert("请填写监管员属性");
			return false;
		}
		var value = document.getElementById("arriveDate").value;
		if(value == ""){
			alert("请填写预计到达时间");
			return false;
		}
		var value = document.getElementById("bd.qq").value;
		if(value == ""){
			alert("请填写QQ号码");
			return false;
		}
		var value = document.getElementById("bd.pwd").value;
		if(value == ""){
			alert("请填写QQ密码");
			return false;
		}
		var value = document.getElementById("bd.gqpx").value;
		if(value == ""){
			alert("请填写是否岗前培训");
			return false;
		}
		document.forms[0].submit();
	}

	function doReturn() {
		location = "<url:context/>/market/bindDealer.do?method=findList";
	}

	//执行表单重置操作
	function doReset() {
		document.forms[0].reset();
	}	
	
	// -----------------------------招聘专员---------------------
	
	var selectRepo = new selectRepo(1, [],[]);

	function openSelect(){
		$("#selectRepo iframe").attr("src","/repository.do?method=selectRepositoryList");
		$("#selectRepo").dialog({
		    title: '选择监管员',
		    width: 800,
		    height: 450,
		    closed: false,
		    cache: false,
		    modal: true,
		    onBeforeClose:function(){
				if(selectRepo.ids.length>selectRepo.maxSize){
					alert("最多可以选择"+selectRepo.maxSize+"个监管员");
					return false;
				}
		    	setValue();
		    }
		});
	}
	function closeSelect(close){
		$("#selectRepo").dialog('close');
	}
	function setValue(){
		$("#repositoryId").val(selectRepo.ids.join(","));
		if(selectRepo.ids!=null&&selectRepo.ids.length>0){
			changeSupervisor(selectRepo.ids[0]);
			
		}
			
	}

	function clearValue(){
		$("#repositoryId").val("0");
		selectRepo.ids=[];
		selectRepo.names=[];
		changeSupervisor(-1);
	}

</script>
</head>
<body onLoad="doLoad()">

	<div class="title">经销商/金融机构绑定信息</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/market/bindDealer.do" styleId="bdForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="update" />
				<html:hidden property="bd.id"/>
				<html:hidden property="bd.dealerId" styleId="dealerId"/>
				<html:hidden property="bd.repositoryId" styleId="repositoryId"/>
				<html:hidden property="bd.changeMoney" styleId="changeMoneyJson"/>
				
				<table class="formTable">
					<tr class="formTitle jxsInfo" >
						<td colspan="4">市   场   部</td>
					</tr>
					
					
					<tr class="formTitle">
						<td colspan="4">监 管 员 管 理 部</td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>监管员：</td>
						<td class="codeCol">
							<input id="name" readonly="readonly" type="text"/>
							<a style="color: blue;" href="javascript:void(0)" onclick="openSelect()">选择监管员</a>
							<a style="color: blue;" href="javascript:void(0)" onclick="clearValue()">清空</a>
						</td>
						<td class="nameCol">监管员身份证号：</td>
						<td class="codeCol"><input type="text" id="idNumber" readonly="readonly"/></td>
					</tr>
					<tr>
						<td class="nameCol">监管员工号：</td>
						<td class="codeCol"><input type="text" id="staffNo" readonly="readonly"/></td>
						<td class="nameCol">联系电话：</td>
						<td class="codeCol"><input type="text" id="selfTelephone" readonly="readonly"/></td>
					</tr>
					<tr>
						
					</tr>
					<tr>
						<td class="nameCol">性别：</td>
						<td class="codeCol"><input type="text" id="gender" readonly="readonly"/></td>
						<td class="nameCol">招聘地点：</td>
						<td class="codeCol"><input type="text" id="liveAddress" readonly="readonly"/></td>
					</tr>
					<tr>
						<td class="nameCol">学历：</td>
						<td class="codeCol"><input type="text" id="educationBackground" readonly="readonly"/></td>
						<td class="nameCol"><font color="#FF0000">*</font>监管员来源：</td>
						<td class="codeCol"><form:radios property="bd.supervisorSource" styleId="bd.supervisorSource"  collection="supervisorSources"></form:radios></td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>监管员属性：</td>
						<td class="codeCol">
							<form:select
								property="bd.supervisorAttribute" styleId="bd.supervisorAttribute">
								<html:optionsCollection name="supervisorAttributes" label="label"
									value="value" />
							</form:select>
						</td>
						<td class="nameCol">推介人：</td>
						<td class="codeCol"><input type="text" id="recommenderName" readonly="readonly"/></td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>预计到达时间：</td>
						<td class="codeCol">
							<form:calendar
								property="bd.arriveDate" styleId="arriveDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"/>
						</td>
						<td class="nameCol"><font color="#FF0000">*</font>QQ号码：</td>
						<td class="codeCol"><html:text property="bd.qq" styleId="bd.qq"></html:text></td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>密码：</td>
						<td class="codeCol">
							<html:text property="bd.pwd" styleId="bd.pwd"></html:text>
						</td>
						<td class="nameCol">面试人：</td>
						<td class="codeCol"><input type="text" id="interviewee" readonly="readonly"/></td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>是否岗前培训：</td>
						<td class="codeCol">
							<html:text property="bd.gqpx" styleId="bd.gqpx"></html:text>
						</td>
						<td class="nameCol">晚到原因：</td>
						<td class="codeCol"><html:text property="bd.laterReason" styleId="bd.laterReason"></html:text></td>
					</tr>
					<tr class="formTableFoot">
						<td colspan="4" align="center">
							<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
							<button class="formButton" onClick="doReset()">重&nbsp;置</button>&nbsp;
							<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
						</td>
					</tr>
				</table>
				<br />
				<div class="message" id="message" style="display: none"></div>
			</html:form>

		</div>
	</div>
	<div id="selectRepo" style="overflow: hidden;">
		<iframe height="400px" width="788px" frameborder="0" marginheight="0" marginwidth="0" ></iframe>
	</div>
</body>
</html>
