<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
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
<link href="/css/base.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/js/common.js"></script>
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>

//页面初始化函数
function doLoad(){
	//显示提示信息
	var msg ="<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>";
	if(msg!=""&&msg!=null){
	}
	changeClientType();
	limit();
}

//限制
function limit(){
	if(getElement("userQuery.flag").value==1){
		var options = getElement("user.client_type").options;
		for(var i = 0; i < options.length;i++){
			if(options[i].value!=3){
				options[i].remove(options[i].value);
				i--;
			}
		}
	}

}

//执行保存操作
function doSave(){
	
	var loginId = getElement("user.loginid").value;
	
	if(!loginId){
		alert("登录名不能为空");
		return false;
	}
	
	for ( var i = 0; i < loginId.length; i++) { 
		var cca = loginId.charCodeAt(i); 
		if ((cca > 19968)) { 
			alert("登录名不能有中文");
			return false;
		}
	} 
	
	if(!getElement("user.userName").value){
		alert("用户名不能为空");
		return false;
	}
	
	//检验用户是否选择修改密码，如选择修改密码则对密码输入内容进行校验
	var updPassword = getElement("updPassword");
	if(updPassword.checked){
		//检验登录密码是否输入，及确认密码与登录密码输入是否一致
		var password = getElement("user.password");
		var confirmPassword = getElement("confirmPassword");
		var passwordValue=password.value;
		var passwordTest=/^(?![^a-zA-Z]+$)(?!\D+$).{8,16}$/;
		if(password.value == ""){
			showMessage("登录密码不能为空");
			eFocus(password);
			return false;
		}
		if(!passwordTest.test(passwordValue)){
			showMessage("登录密码必须包含字母和数字,且长度为8-16位！");
			return false;
		} 
		if(password.value != confirmPassword.value){
			showMessage("登录密码与确认登录密码填写不一致，请检查后重新保存");
			eFocus(confirmPassword);
			return false;
		}
	}

	var phone = document.getElementById("user.mobilephone").value;
	if(phone == ""){
		alert("请填写手机号码");
		return false;
	}
	var pattern = /^[1][3-8]+\d{9}$/;
	if (!pattern.test(phone)) {
		alert("手机号码不正确！");
		return false;
	}

	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("userForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/rbac/user.do?method=userList";
}

//切换是否修改密码事件
function clickUpdPassword(){
	
	//当用户选择修改密码时才显示登录密码和确认密码输入框
	var updPassword = getElement("updPassword");
	var passwordRow = getElement("passwordRow");
	
	var password = getElement("user.password");
	var confirmPassword = getElement("confirmPassword");
	
	if(updPassword.checked){
		passwordRow.style.display = "";
		password.disabled = false;
		confirmPassword.disabled = false;
	}
	else{
		passwordRow.style.display = "none";
		password.disabled = true;
		confirmPassword.disabled = true;
	}
}
function openSelectOrg(){
	$("#selectJgy iframe").attr("src","/select.do?method=repositoryList");
	$('#selectJgy').dialog({
	    title: '选择监管员',
	    width: 800,
	    height: 400,
	    closed: false,
	    cache: false,
	    modal: true
	});
}

function changeClientType(){
	var clientid = document.getElementById("user.client_id").value;
	var clientType = document.getElementById("user.client_type").value;
	if(clientType == 3){
		var url = "../json/getSuperviser.do?callback=?&supervisorId="+clientid;
		$.getJSON(url, function(result) {
			var data = result.data;
			getElement("clientId_clientName").innerHTML = data;
			
		});
		document.getElementById("iss").style.display="block";
		document.getElementById("issh").style.display="block";
	}
	
}
function cClientType(){
	var clientType = getElement("user.client_type");
	if(clientType.value==3){
		document.getElementById("iss").style.display="block";
		document.getElementById("issh").style.display="block";
	}else{
		document.getElementById("iss").style.display="none";
		document.getElementById("issh").style.display="none";
	}
	if(clientType.value==13){
		$("#bank").show();	
	}else{
		$("#bank").hide();
		$("#user\\.client_id").val("");
	}
}

 $(function(){
	 initBank();
	 if($("#user\\.client_type").val()==13){
		 $("#bank").show();
		
		var bankPath = "<c:out value='${bank.path}'/>";
		var bankId = "<c:out value='${bank.id}'/>";
		console.info(bankPath);
		var parentIds = bankPath.split("/");
		if(parentIds.length>3){
			$("#one").val(parentIds[1]);
			$("#one").change();
			$("#two").val(parentIds[2]).change();
			$("#three").val(bankId).change();
		}else if(parentIds.length>2){
			$("#one").val(parentIds[1]);
			$("#one").change();
			$("#two").val(bankId).change();
		}else if(parentIds.length>1){
			$("#one").val(bankId);
			$("#one").change();
		}
	 }
}); 
 
 function initBank() {
		loadSelect(-1, $("#one"));

		$("#one").change(function() {
			$("#two option:gt(0)").remove();
			$("#three option:gt(0)").remove();
			var id = this.value;
			if (id>0) {
				loadSelect(id, $("#two"));
				setBank(id,$(this).find("option:selected").text());
			}else{
				setBank($("#one").val(),$("#one").find("option:selected").text());
			}
		});
		$("#two").change(function() {
			var id = this.value;
			if (id>0) {
				$("#three option:gt(0)").remove();
				loadSelect(id, $("#three"));
				setBank(id,$(this).find("option:selected").text());
			}else{
				setBank($("#one").val(),$("#one").find("option:selected").text());
				$("#three").val("-1");
			}

		});
		$("#three").change(function(){
			var id = this.value;
			if (id>0) {
				setBank(id,$(this).find("option:selected").text());
			}else{
				setBank($("#two").val(),$("#two").find("option:selected").text());
			}
		});
		
		
	}
 
 function loadSelect(id, nextSelect) {
		var url = "../json/getBankChildById.do?method=findChildListById&callback=?&bankQuery.id="
				+ id;
		$.ajax({
			url:url,
			async:false,
			dataType:"jsonp",
			success:function(result){
				var data = result.data;
				$.each(data, function(i, item) {
					var option = "<option value="+item.id+">" + item.bankName
							+ "</option>";
					nextSelect.append(option);
				});
			}
		});
	}
	
 function setBank(id,name){
		if(id>0){
			$("#user\\.client_id").val(id);
		}else{
			$("#user\\.client_id").val("");
		}
	}
 
 function closeDialog(name){
		$("#"+name).dialog("close");
	}
	function changeName(id,name){
		getElement("user.client_id").value = id;
		getElement("clientId_clientName").innerHTML = name;
	}
	
	  $(function(){
	$("#user\\.client_type").combobox({
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
			} 
		}
	}); 
}); 
</script>
</head>

<body onLoad="doLoad()">
<div class="add_nav">
		<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">系统管理与配置</a>
             &gt;
             <a class="crumbs-link" style="color:#929291;" href="#">账户管理</a>
              &gt;
              <a class="crumbs-link" style="color:#929291;" href="#">修改账户</a>
         </span>
	</div>
<div class="title">修改账户</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form styleId="userForm" action="/rbac/user" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="updUser">
<html:hidden property="user.id"/>
<html:hidden property="user.client_id" styleId="user.client_id"/>
<html:hidden property="userQuery.flag" styleId="userQuery.flag"/>
<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>登录名：</td>
		<td class="codeCol"><html:text property="user.loginid" styleId="user.loginid"/></td>
		<td class="nameCol"><font color="#FF0000">*</font>用户名：</td>
		<td class="codeCol"><html:text property="user.userName" styleId="user.userName"/></td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>选择部门：</td>
		<td class="codeCol">
			<!-- <form:select property="user.client_type" styleId="user.client_type" onchange="cClientType()">
				<html:optionsCollection name="clientTypeOptions" label="label" value="value"/>
			</form:select> -->
			<select id="user.client_type" name="user.client_type" style="width: 60%;">
										<option value="-1">请选择</option>
										<c:forEach items="${clientTypeOptions }" var="row">
											<option <c:if test="${userForm.user.client_type==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
										</c:forEach>
			</select>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>手机号码：</td>
		<td class="codeCol"><html:text property="user.mobilephone" styleId="user.mobilephone"/></td>
	</tr>
	<tr>
		<td class="nameCol">电子邮箱：</td>
		<td class="codeCol"><html:text property="user.email" styleId="user.email"/></td>
		<td class="nameCol"><span id="iss" style="display: none;">选择对应角色：</span></td>
		<td class="codeCol"><span id="clientId_clientName"></span><a id="issh" style="display: none;" href="javascript:openSelectOrg()">选择</a></td>
	</tr>
	<tr class="formTitle">
		<td colspan="4" align="center"><input type="checkbox" name="updPassword" id="updPassword" onClick="clickUpdPassword()"><label for="updPassword">修改密码</label></td>
	</tr>
	<tr style="display:none" id="passwordRow">
		<td class="nameCol"><font color="#FF0000">*</font>登录密码：</td>
		<td class="codeCol"><html:password styleId="user.password" property="user.password" value="" disabled="true"/></td>
		<td class="nameCol"><font color="#FF0000">*</font>确认登录密码：</td>
		<td class="codeCol"><input type="password" id="confirmPassword" name="confirmPassword" disabled="true"></td>
	</tr>
	<tr id="bank" style="display: none;">
		<td class="nameCol">选择绑定银行：</td>
		<td class="codeCol" colspan="3">
			<select id="one">
					<option value="-1">请选择...</option>
			</select> <select id="two">
					<option value="-1">请选择...</option>
			</select> <select id="three">
					<option value="-1">请选择...</option>
			</select>
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
<br/>
<div class="message" id="message" style="display:none"></div>

	</div>
</div>
<div id="selectJgy" style="overflow: hidden;">
	<iframe height="350px" width="788px" frameborder="0" ></iframe>
</div>
</body>
</html>