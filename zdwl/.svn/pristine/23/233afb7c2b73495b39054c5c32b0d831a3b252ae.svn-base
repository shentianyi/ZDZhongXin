<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
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

<!-- easyui -->
<link href="/easyui/themes/material/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script>
	function doSave(){
		
		var type = $("#noticeType").val();
		if(type=='88'){
			if(!$("#result").val()){
				alert("发货对账通知书回执必填对账结果");
				return false;
			}
			if(!$("#sellerChecker").val()){
				alert("发货对账通知书回执必填卖方对账负责人");
				return false;
			}
		}
		
		if($("#result").val()=='02'
				&&($("#remark").val()==""||$("#remark").val()==null)){
			alert("对账结果为对账不平时必输对账不平原因");
			return false;
		}
		
		document.forms[0].submit();
	}
	
	//页面初始化函数
	function doLoad(){
		var message = "<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>";
		//显示提示信息
		if(message && message!="" && message!="null"){
			if(message="success"){
				alert("发送成功");
				window.close();
			}else{
				alert(message);	
			}
			
		}
	}
	

</script>
</head>
<body onLoad="doLoad()">

	<div class="title">通知书回执</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/bank/interface.do" 
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="gyl003" />
				<input type="hidden" name="key" value="notnull" />
				<input type="hidden" name="noticeType" id="noticeType" value="<c:out value='${noticeType }'/>" />

				<table class="formTable">
					<tr>
						<td class="nameCol">通知书编号：</td>
						<td class="codeCol">
							<input type="text" name="noticeAckId" value="<c:out value='${noticeAckId}'/>" readonly="readonly"/>
						</td>
						<td class="nameCol"><c:if test="${noticeType=='88'}"><font color="#FF0000">*</font></c:if>对账结果：</td>
						<td class="codeCol">
							<select name="result" id="result">
								<option value="">不选择</option>
								<option value="01">对账成功</option>
								<option value="02">对账不平</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="nameCol"><c:if test="${noticeType=='88'}"><font color="#FF0000">*</font></c:if>卖方对账负责人：</td>
						<td class="codeCol"><input type="text" id="sellerChecker" name="sellerChecker" maxlength="6" /></td>
						<td class="nameCol">对账不平原因：</td>
						<td class="codeCol">
							<textarea maxlength="65" id="remark" name="remark"></textarea>
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
</body>
</html>
