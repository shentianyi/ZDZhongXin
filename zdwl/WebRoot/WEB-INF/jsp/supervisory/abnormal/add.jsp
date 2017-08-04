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
<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script>
	//页面初始化函数
	function doLoad(){
		//显示提示信息
		showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	}
	//执行保存操作
	function doSave() {
		/* var value = document.getElementsByName("dealerId")[0].value;
		if(value == "-1"){
			alert("请选择经销商");
			return false;
		}
		var value = document.getElementsByName("business")[0].value;
		if(value == "-1"){
			alert("请选择业务专员");
			return false;
		} */
		var value = $('#dealerId').combobox('getValue');
		if(value == "请选择..."){
		      alert("请选择经销商");
            return false;
		}
		var value = $('#business').combobox('getValue');
        if(value == "请选择..."){
            alert("请选择业务专员");
            return false;
        }
		var value = document.getElementById("abnormal.jyAnomalies").value;
		if(value == ""){
			alert("请填写经营异常");
			return false;
		}
		var value = document.getElementById("abnormal.ycproportion").value;
		if(value == ""){
			alert("请输入异常占比");
			return false;
		}
		var vin = document.getElementById("abnormal.shCarNumber").value;
		if(vin == ""){
			alert("请填写私售车辆台数");
			return false;
		}
		var money = document.getElementById("abnormal.shCarMoney").value;
		if(money == ""){
			alert("请填写私售金额");
			return false;
		}
		var value = document.getElementById("abnormal.shContinuedDay").value;
		if(value == ""){
			alert("请填写私售持续天数");
			return false;
		}
		
		var vin = document.getElementById("abnormal.syCarNumber").value;
		if(vin == ""){
			alert("请填写私移车辆台数");
			return false;
		}
		var value = document.getElementById("abnormal.syCarMoney").value;
		if(value == ""){
			alert("请输入私移金额");
			return false;
		}
		var value = document.getElementById("abnormal.syContinuedDay").value;
		if(value == ""){
			alert("请输入私移持续天数");
			return false;
		}
		var vin = document.getElementById("abnormal.xsCarNumber").value;
		if(vin == ""){
			alert("请填写销售未还款车辆台数");
			return false;
		}
		var value = document.getElementById("abnormal.xsCarMoney").value;
		if(value == ""){
			alert("请填写销售未还款金额");
			return false;
		}
		var value = document.getElementById("abnormal.xsContinuedDay").value;
		if(value == ""){
			alert("请填写销售未还款天数");
			return false;
		}
		//对表单内容进行验证，包括对输入类型等限制方式
		if (validateMain("abnormalForm")) {
			//为时间类型输入项补齐时间戳
			setTimeSuffix();
			//提交表单
			document.forms[0].submit();
		}
	}

	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/supervisory/abnormal.do?method=findList";
	}

	//执行表单重置操作
	function doReset() {
		document.forms[0].reset();
	}

	function changeDealer(id) {
		if(id==-1){
			return;
		}
		var url = "../json/getDealerByIdJsonAction.do?callback=?&id="+id;
		$.getJSON(url, function(result) {
			var data = result.data;
			console.info(data);
			setDealer(data[0]);
		});
	}
	
	function setDealer(dealer){
		$("#dealerName").val(dealer.dealerName);
		$("#bankName").val(dealer.bankName);
		$("#province").val(dealer.province);
		$("#city").val(dealer.city);
		$("#totalStock").val(dealer.totalStock);
		$("#abnormaltotalStock").val(dealer.totalStock);
	}
	$(function(){
	$("#dealerId").combobox({
		onHidePanel : function() {// 验证输入的值，如果不存在下拉列表 则置空
			var newValue = $(this).combobox('getValue');
			var data = $(this).combobox('getData');
			var flag = false;
			if (data != null && data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					if (newValue == data[i].value) {
						flag = true;
						changeDealer(data[i].value);
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
	$(function(){
	$("#business").combobox({
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

	<div class="title">异常事件/异常数据</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/supervisory/abnormal.do" styleId="abnormalForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="add" />
				<html:hidden property="abnormal.totalStock" styleId="abnormaltotalStock"/>
				<table class="formTable">
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>经销商：</td>
						<td class="codeCol">
						<!-- <form:select property="abnormal.dealerId"
								styleId="dealerId" onchange="changeDealer(this.value)">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="dealers" label="label"
									value="value" />
							</form:select> -->
							<select id="dealerId" name="abnormal.dealerId" style="width:85%;">
							<option value="-1">请选择...</option>
							<c:forEach items="${dealers}" var="row">
							<option <c:if test="${row.value==abnormalForm.abnormal.dealerId }">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
							</c:forEach>
							</select>
						</td>
						<td class="nameCol"><font color="#FF0000">*</font>业务专员：</td>
						<td class="codeCol">
						<!-- <form:select property="abnormal.business"
								styleId="business" >
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="business" label="label"
									value="value" />
							</form:select> -->
							<select id="business" name="abnormal.business" style="width:85%;">
							<option value="-1">请选择...</option>
							<c:forEach items="${business}" var="row">
							<option <c:if test="${row.value==abnormalForm.abnormal.dealerId }">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
							</c:forEach>
							</select>
						</td>
					</tr>
				
					<tr>
						<td class="nameCol">融资机构：</td>
						<td class="codeCol">
							<input type="text" id="bankName" readonly="readonly"/>
						</td>
						<td class="nameCol">总库存：</td>
						<td class="codeCol">
							<input type="text" id="totalStock" readonly="readonly"/>
							<%-- <html:text property="abnormal.totalStock" styleId="abnormal.totalStock"></html:text> --%>
						</td>
					</tr>
					<tr>
						<td class="nameCol">省：</td>
						<td class="codeCol">
							<input type="text" id="province" readonly="readonly"/>
						</td>
						<td class="nameCol">市：</td>
						<td class="codeCol">
							<input type="text" id="city" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>经营异常：</td>
						<td class="codeCol">
							<html:text property="abnormal.jyAnomalies" styleId="abnormal.jyAnomalies"></html:text>
						</td>
						<td class="nameCol"><font color="#FF0000">*</font>异常占比：</td>
						<td class="codeCol" colspan="3">
							<html:text property="abnormal.ycproportion" styleId="abnormal.ycproportion"></html:text>
						</td>
					</tr>
					<tr>
						<td  colspan="4" >
							<table style="width: 100%">
								<tr>
									<td class="nameCol" rowspan="3" style="width: 15%">私售</td>
									<td class="nameCol" style="width: 10%"><font color="#FF0000">*</font>车辆台数：</td>
									<td class="codeCol" style="width: 25%">
										<html:text property="abnormal.shCarNumber" styleId="abnormal.shCarNumber"></html:text>
									</td>
									<td class="nameCol" rowspan="3" style="width: 15%">私移</td>
									<td class="nameCol" style="width: 10%"><font color="#FF0000">*</font>车辆台数：</td>
									<td class="codeCol" style="width: 25%">
										<html:text property="abnormal.syCarNumber"  styleId="abnormal.syCarNumber"></html:text>
									</td>
								</tr>
								<tr>
									<td class="nameCol"><font color="#FF0000">*</font>金额：</td>
									<td class="codeCol">
										<html:text property="abnormal.shCarMoney" styleId="abnormal.shCarMoney"></html:text>
									</td>
									<td class="nameCol"><font color="#FF0000">*</font>金额：</td>
									<td class="codeCol">
										<html:text property="abnormal.syCarMoney" styleId="abnormal.syCarMoney"></html:text>
									</td>
								</tr>
								<tr>
									<td class="nameCol"><font color="#FF0000">*</font>持续天数：</td>
									<td class="codeCol">
										<html:text property="abnormal.shContinuedDay" styleId="abnormal.shContinuedDay"></html:text>
									</td>
									<td class="nameCol"><font color="#FF0000">*</font>持续天数：</td>
									<td class="codeCol">
										<html:text property="abnormal.syContinuedDay" styleId="abnormal.syContinuedDay"></html:text>
									</td>
								</tr>
								<tr>
									<td class="nameCol" rowspan="3" style="width: 15%">销售未还款</td>
									<td class="nameCol"><font color="#FF0000">*</font>车辆台数：</td>
									<td class="codeCol">
										<html:text property="abnormal.xsCarNumber" styleId="abnormal.xsCarNumber"></html:text>
									</td>
								</tr>
								<tr>
									<td class="nameCol"><font color="#FF0000">*</font>金额：</td>
									<td class="codeCol">
										<html:text property="abnormal.xsCarMoney" styleId="abnormal.xsCarMoney"></html:text>
									</td>
								</tr>
								<tr>
									<td class="nameCol"><font color="#FF0000">*</font>持续天数：</td>
									<td class="codeCol">
										<html:text property="abnormal.xsContinuedDay" styleId="abnormal.xsContinuedDay"></html:text>
									</td>
								</tr>
								<tr>
									<td class="nameCol" rowspan="2" style="width: 15%">开票30天未押满</td>
									<td class="nameCol" style="width: 10%">最早开票日：</td>
									<td class="codeCol" style="width: 25%">
										<form:calendar
											property="abnormal.earliestInvoice" styleId="abnormal.earliestInvoice"
											pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
											readonly="true" />
									</td>
									<td class="nameCol" rowspan="2" style="width: 15%">汇票到期未按时清票</td>
									<td class="nameCol" style="width: 10%">最早到期日：</td>
									<td class="codeCol" style="width: 25%">
										<form:calendar
											property="abnormal.earliestExpire" styleId="abnormal.earliestExpire"
											pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
											readonly="true" />
									</td>
								</tr>
								<tr>
									<td class="nameCol" style="width: 10%">未压满金额：</td>
									<td class="codeCol" style="width: 25%">
										<html:text property="abnormal.amountnotfilled" styleId="abnormal.amountnotfilled"></html:text>
									</td>
									<td class="nameCol" style="width: 10%">未结清金额：</td>
									<td class="codeCol" style="width: 25%">
										<html:text property="abnormal.outstandingAmount" styleId="abnormal.outstandingAmount"></html:text>
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
				<br />
				<div class="message" id="message" style="display: none"></div>
			</html:form>
		</div>
	</div>
</body>
</html>
