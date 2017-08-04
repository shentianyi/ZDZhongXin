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
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
	//页面初始化函数
	function doLoad(){
		//显示提示信息
		showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	}
	function qianfei(value){
		if(value=='2'){
			$("#qianfei").hide();
		}else{
			$("#qianfei").show();
		}
	}
	$(function(){
		changeDealer($('#dealerId').val());
		qianfei($("input[name='de\\.isArrears']:checked").val());
	});
	//执行保存操作
	function doSave() {
		document.forms[0].submit();
	}

	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/market/dealerExit.do?method=findList";
	}

	//执行表单重置操作
	function doReset() {
		document.forms[0].reset();
	}

</script>
</head>
<body onLoad="doLoad()">

	<div class="title">经销商撤店信息流转</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/market/dealerExit.do" styleId="deForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="update" />
				<html:hidden property="de.id"/>
				<html:hidden property="de.dealerId" styleId="dealerId"/>

				<table class="formTable">
					 <tr class="formTitle">
						<td colspan="4">市   场   部</td>
					</tr>
					<tr>
						<td class="nameCol">经销店名称：</td>
						<td class="codeCol"><input type="text" id="dealerName" readonly="readonly"/></td>
					</tr>
					<tr>
						<td class="nameCol">店方联系人：</td>
						<td class="codeCol"><input type="text" id="contact" readonly="readonly"/></td>
						<td class="nameCol">联系人电话：</td>
						<td class="codeCol"><input type="text" id="contactPhone" readonly="readonly"/></td>
					</tr>
					<tr>
						<td class="nameCol">金融机构：</td>
						<td class="codeCol">
							<input type="text"id="bankName" readonly="readonly"/>
						</td>
						<td class="nameCol">银行客户经理：</td>
						<td class="codeCol">
							<input type="text"id="bankContact" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">联系电话：</td>
						<td class="codeCol">
							<input type="text"id="bankPhone" readonly="readonly"/>
						</td>
						<td class="nameCol">品牌：</td>
						<td class="codeCol"><input type="text" id="brand" readonly="readonly"/></td>
					</tr>
					<tr>
						<td class="nameCol">协议监管模式：</td>
						<td class="codeCol">
							<input type="checkbox" value="车证" id="supervisionMode_1" disabled="disabled"/>车证
							<input type="checkbox" value="合格证" id="supervisionMode_2" disabled="disabled"/>合格证
							<input type="checkbox" value="合格证" id="supervisionMode_3" disabled="disabled"/>贷后巡库
							<input type="checkbox" value="合格证" id="supervisionMode_4" disabled="disabled"/>金融监管库
						</td>
						<td class="nameCol">合作模式：</td>
						<td class="codeCol">
							<input type="radio" id="cooperationModel_1" disabled="disabled"/>两方
							<input type="radio" id="cooperationModel_2" disabled="disabled"/>三方
						</td>
					</tr>
					<tr>
						<td class="nameCol">撤店时间：</td>
						<td class="codeCol">
							<form:calendar
								property="de.exitDateByMarket" styleId="de.exitDateByMarket"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								disabled="true" />
						</td>
						<td class="nameCol"><font color="#FF0000">*</font>是否需要退费：</td>
						<td class="codeCol">
							<form:radios styleId="de.isRefundByMarket" 
								property="de.isRefundByMarket" collection="yesorno" disabled="true"></form:radios>
						</td>
					</tr>
					<tr>
						<td class="nameCol">制单日期：</td>
						<td class="codeCol">
							<form:calendar
								property="de.createDate" styleId="de.createDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								disabled="true" />
						</td>
						<td class="nameCol">备注：</td>
						<td class="codeCol">
							<html:text property="de.remarkByMarket" readonly="true"></html:text>
						</td>
					</tr>
					
					<tr class="formTitle">
						<td colspan="4">财务部</td>
					</tr>
					<tr>
						<td class="nameCol">最近一次交费时间：</td>
						<td class="codeCol">
							<form:calendar
								property="de.lastPayDate" styleId="de.lastPayDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								disabled="true" />
						</td>
						<td class="nameCol"><font color="#FF0000">*</font>交费金额：</td>
						<td class="codeCol">
							<html:text property="de.payMoney" styleId="de.payMoney" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>付款方式：</td>
						<td class="codeCol">
							<html:text property="de.payMode" styleId="de.payMode" readonly="true"></html:text>
						</td>
						<td class="nameCol"><font color="#FF0000">*</font>是否欠费：</td>
						<td class="codeCol">
							<form:radios styleId="de.isArrears"
								property="de.isArrears" collection="yesorno" disabled="true"></form:radios>
						</td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>欠费时间：</td>
						<td class="codeCol">
							<form:calendar
								property="de.arrearsDate" styleId="de.arrearsDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								disabled="true" />
						</td>
						<td class="nameCol"><font color="#FF0000">*</font>欠费金额：</td>
						<td class="codeCol">
							<html:text property="de.arrearsNumber" styleId="de.arrearsNumber" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>是否需要退费：</td>
						<td class="codeCol">
							<form:radios styleId="de.isRefundByFinance" 
								property="de.isRefundByFinance" collection="yesorno" disabled="true"></form:radios>
						</td>
						<td class="nameCol"><font color="#FF0000">*</font>撤店时间：</td>
						<td class="codeCol">
							<form:calendar
								property="de.exitDateByFinance" styleId="de.exitDateByFinance"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								disabled="true" />
						</td>
					</tr>
					<tr>
						<td class="nameCol">备注	：</td>
						<td class="codeCol">
							<html:text property="de.remarkByFinance" readonly="true"></html:text>
						</td>
					</tr>
					
					<tr class="formTitle">
						<td colspan="4">业务部</td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>业务专员：</td>
						<td class="codeCol">
							<html:text property="de.businessCommissioner" styleId="de.businessCommissioner" readonly="true"/>
						</td>
						<td class="nameCol"><font color="#FF0000">*</font>业务是否结清：</td>
						<td class="codeCol">
							<form:radios styleId="de.isBusinessEnd"
								property="de.isBusinessEnd" collection="yesorno" disabled="true"></form:radios>
						</td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>结清方式：</td>
						<td class="codeCol">
							<form:radios styleId="de.endMode"
								property="de.endMode" collection="endMode" disabled="true"></form:radios>
						</td>
						<td class="nameCol"><font color="#FF0000">*</font>撤店时间：</td>
						<td class="codeCol">
							<form:calendar
								property="de.exitDateByBusiness" styleId="de.exitDateByBusiness"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								disabled="true" />
						</td>
					</tr>
					<tr>
						<td class="nameCol">备注：</td>
						<td class="codeCol">
							<html:text property="de.remarkByBusiness" readonly="true"></html:text>
						</td>
					</tr> 
					
					<tr class="formTitle">
						<td colspan="4">监管员管理部</td>
					</tr>
					<tr>
						<td class="nameCol">监管员姓名：</td>
						<td class="codeCol">
							<input type="text" readonly="readonly" value="<c:out value='${rep.name }'/>"/>
						</td>
						<td class="nameCol">监管员工号：</td>
						<td class="codeCol">
							<input type="text" readonly="readonly" value="<c:out value='${rep.staffno }'/>"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">监管员属性：</td>
						<td class="codeCol">
							<input type="text" readonly="readonly" value="<c:out value='${rep.supervisorAttribute }'/>"/>
						</td>
						<td class="nameCol">性别：</td>
						<td class="codeCol">
							<input type="text" readonly="readonly" value="<c:out value='${rep.gender }'/>"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">QQ账号：</td>
						<td class="codeCol">
							<input type="text" readonly="readonly" value="<c:out value='${rep.qq }'/>"/>
						</td>
						<td class="nameCol">QQ密码：</td>
						<td class="codeCol">
							<input type="text" readonly="readonly" value="<c:out value='${rep.qqPwd }'/>"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">联系电话：</td>
						<td class="codeCol">
							<input type="text" readonly="readonly" value="<c:out value='${rep.contactnumber }'/>"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">电脑品牌：</td>
						<td class="codeCol">
							<input type="text" readonly="readonly" value="<c:out value='${rep.computerBrand }'/>"/>
						</td>
						<td class="nameCol">型号：</td>
						<td class="codeCol">
							<input type="text" readonly="readonly" value="<c:out value='${rep.computerModel }'/>"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">保险柜品牌：</td>
						<td class="codeCol">
							<input type="text" readonly="readonly" value="<c:out value='${rep.safeBrand }'/>"/>
						</td>
						<td class="nameCol">保险柜型号：</td>
						<td class="codeCol">
							<input type="text" readonly="readonly" value="<c:out value='${rep.safeModel }'/>"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">钥匙：</td>
						<td class="codeCol">
							<input type="text" readonly="readonly" value="<c:out value='${rep.key }'/>"/>
						</td>
						<td class="nameCol">其他：</td>
						<td class="codeCol">
							<input type="text" readonly="readonly" value="<c:out value='${rep.other }'/>"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">储存地址：</td>
						<td class="codeCol">
							<input type="text" value="<c:out value='${row.saveAddress }'/>"/>
						</td>
					</tr>
					
					<tr>
						<td class="nameCol">撤店时间：</td>
						<td class="codeCol">
							<form:calendar
								property="de.exitDateBySupervise" styleId="de.exitDateBySupervise"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								readonly="true" />
						</td>
						<td class="nameCol">备注：</td>
						<td class="codeCol">
							<html:text property="de.remarkBySupervise"></html:text>
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
				<br />
				<div class="message" id="message" style="display: none"></div>
			</html:form>
		</div>
	</div>
</body>
</html>
