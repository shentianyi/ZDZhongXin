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
<script src="/pagejs/unbindDealer.js"></script>
<script>
	//页面初始化函数
	function doLoad(){
		//显示提示信息
		showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	}
	
	
	function agree(){
		$("#approvalState").val(1);
		doSave();
	}
	
	function disAgree(){
		$("#approvalState").val(2);
		doSave();
	}

	//执行表单重置操作
	function doReset() {
		document.forms[0].reset();
	}
	
	$(function(){
		showDealerForDetail();
		changeSupervisor($("#repositoryId").val());
		initDealer();
		$('[numberboxname=changeMoney]').numberbox('disable');
		$('[comboname=changeMoneyDate]').datebox('disable');
		$("[name=changeMode]").attr('disabled','disabled');

	});
</script>
</head>
<body onLoad="doLoad()">

	<div class="title">经销商/金融机构拆分合作信息流程</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/market/unbindDealer.do" styleId="unbdForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="approval" />
				<html:hidden property="query.approvalState" styleId="approvalState"/>
				<html:hidden property="unbd.id"/>
				<html:hidden property="unbd.dealerId" styleId="dealerId"/>
				<html:hidden property="unbd.repositoryId" styleId="repositoryId"/>
				<html:hidden property="unbd.changeMoney" styleId="changeMoneyJson"/>

				<table class="formTable">
					<tr class="formTitle scb jxsInfo" >
						<td colspan="4">市   场   部</td>
					</tr>
					
					<tr class="formTitle">
						<td colspan="4">监 管 员 管 理 部</td>
					</tr>
					<tr>
						<td class="nameCol">监管员：</td>
						<td class="codeCol">
							<input id="name" readonly="readonly" type="text"/>
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
						<td class="nameCol">监管员来源：</td>
						<td class="codeCol"><form:radios property="unbd.supervisorSource" collection="supervisorSources"></form:radios></td>
					</tr>
					<tr>
						<td class="nameCol">监管员属性：</td>
						<td class="codeCol">
							<form:select
								property="unbd.supervisorAttribute" styleId="dealerId">
								<html:optionsCollection name="supervisorAttributes" label="label"
									value="value" />
							</form:select>
						</td>
						<td class="nameCol">推介人：</td>
						<td class="codeCol"><input type="text" id="recommenderName" readonly="readonly"/></td>
					</tr>
					<tr>
						<td class="nameCol">预计到达时间：</td>
						<td class="codeCol">
							<form:calendar disabled="true"
								property="unbd.arriveDate" styleId="arriveDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"/>
						</td>
						<td class="nameCol">QQ号码：</td>
						<td class="codeCol"><html:text property="unbd.qq" readonly="true"></html:text></td>
					</tr>
					<tr>
						<td class="nameCol">密码：</td>
						<td class="codeCol">
							<html:text property="unbd.pwd" readonly="true"></html:text>
						</td>
						<td class="nameCol">面试人：</td>
						<td class="codeCol"><input type="text" id="interviewee" readonly="readonly"/></td>
					<tr>
						<td class="nameCol">是否岗前培训：</td>
						<td class="codeCol">
							<html:text property="unbd.gqpx" readonly="true"></html:text>
						</td>
						<td class="nameCol">晚到原因：</td>
						<td class="codeCol"><html:text property="unbd.laterReason" readonly="true"></html:text></td>
					</tr>
					
					<tr class="formTitle">
						<td colspan="4">业务部</td>
					</tr>
					<tr>
						<td class="nameCol">实际到店时间：</td>
						<td class="codeCol">
							<form:calendar property="unbd.actualinDate"
								styleId="unbd.actualinDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" disabled="true"/>
						</td>
						<td class="nameCol">业务培训：</td>
						<td class="codeCol">
							<form:radios disabled="disabled"
								property="unbd.businessDelegate" collection="yesorno" 
								styleId="unbd.businessDelegate"></form:radios>
						</td> 
					</tr>
					<tr>
						<td class="nameCol">培训日期：</td>
						<td class="codeCol">
							<form:calendar property="unbd.trainDate"
								styleId="unbd.trainDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" disabled="true"/>
						</td>
						<td class="nameCol">授权书制作：</td>
						<td class="codeCol">
							<form:radios disabled="disabled"
								property="unbd.authorizationBook" collection="yesorno" styleId="authorizationBook"></form:radios>
						</td>
					</tr>
					<tr>
						<td class="nameCol">委任书制作：</td>
						<td class="codeCol">
							<form:radios disabled="disabled"
								property="unbd.appointmentBook" collection="yesorno" styleId="appointmentBook"></form:radios>
						</td>
						<td class="nameCol">培训效果：</td>
						<td class="codeCol">
							<form:radios disabled="disabled"
								property="unbd.traineffect" collection="trainingEffect" styleId="traineffect"></form:radios>
						</td>
					</tr>
					
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

					
					<tr class="formTableFoot">
						<td colspan="4" align="center">
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
