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

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/common.js"></script>
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	initProvince();
}

//进入新增页面
function goAdd(){
	location="<url:context/>/agreementSend.do?method=addAgreementSendEntry";
}

//进入修改页面
function goUpd(id){
	location = "<url:context/>/agreementSend.do?method=updAgreementSendEntry&agreementSend.id="+id;
}

//执行删除操作
function doDel(id){
	if(confirm("删除后数据不可恢复\n是否继续？")){
		location="<url:context/>/agreementSend.do?method=delAgreementSend&agreementSend.id="+id;
	}
}

//执行查询操作
function doQuery(){
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("agreementSendForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("agreementSendquery.distribid").value="";
	getElement("agreementSendquery.financial_institution").value="";
	getElement("agreementSendquery.agreement_num").value="";
	getElement("agreementSendquery.agreement_date").value="";
	getElement("agreementSendquery.financial_user").value="";
	getElement("agreementSendquery.financial_phone").value="";
	getElement("agreementSendquery.back_date").value="";
}

function initProvince(){
	var url = "../json/findRegionByParentId.do?callback=?&parentId=0";
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setProvince(data);
	});
}
function setProvince(province){
	var provinces=$("#province");
	for(var i=0;i<province.length;i++){
		var option = "<option value="+province[i].id+">" + province[i].name
		+ "</option>";
		provinces.append(option);
	}
}
function changeProvince(id,nextSelect){
	if(id==-1){
		document.forms[0].reset();
		return;
	}
	var url = "../json/findRegionByParentId.do?callback=?&parentId="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setCity(data,nextSelect);
	});
}

function setCity(city,nextSelect){
	nextSelect.html("");
	var option = "<option value='-1' >请选择</option>";
	nextSelect.append(option);
	for(var i=0;i<city.length;i++){
		var option = "<option value="+city[i].id+">" + city[i].name
		+ "</option>";
		nextSelect.append(option);
	}
} 
</script>
</head>
<body onLoad="doLoad()">

<div class="title">协议发送</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/agreementSend" styleId="agreementSendForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="agreementSendList"/>
<!-- 查询条件 -->
<table class="formTable" cellpadding="0" cellspacing="0">
	<tr>
		<td class="nameCol">经销商：</td>
		<td class="codeCol">
			<form:select property="agreementSendquery.distribid" styleId="agreementSendquery.distribid">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="dealersOptions" label="label" value="value" />
			</form:select>
		</td>
		<td class="nameCol">协议编号：</td>
	  	<td class="codeCol"><html:text property="agreementSendquery.agreement_num" styleId="agreementSendquery.agreement_num"/></td>
		<td class="nameCol">协议邮寄时间：</td>
		<td class="codeCol">
			<form:calendar property="agreementSendquery.agreement_date" styleId="agreementSendquery.agreement_date" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
	</tr>
	<tr>
	  	<td class="nameCol">金融机构联系人：</td>
		<td class="codeCol">
			<html:text property="agreementSendquery.financial_user" styleId="agreementSendquery.financial_user"/>
		</td>
		<td class="nameCol">预计回收时间：</td>
	  	<td class="codeCol">
	  		<form:calendar property="agreementSendquery.back_date" styleId="agreementSendquery.back_date" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
	  	</td>
	  	<td class="nameCol">品牌：</td>
		<td class="codeCol">
			<form:select property="agreementSendquery.brandid" styleId="agreementSendquery.brandid">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="brandOptions" label="label" value="value" />
			</form:select>
		</td>
	</tr>
	<tr>
		<td class="nameCol">省：</td>
		<td class="codeCol">
			<form:select property="agreementSendquery.province" onchange="changeProvince(this.value,$('#city'))" styleId="province" >
				<html:option value="-1">请选择</html:option>
			</form:select>
		</td>
		<td class="nameCol">市：</td>
		<td class="codeCol">
			<form:select property="agreementSendquery.city" onchange="changeProvince(this.value,$('#county'))" styleId="city" >
				<html:option value="-1">请选择</html:option>
			</form:select>
		</td>
		<td class="nameCol">区：</td>
		<td class="codeCol">
			<form:select property="agreementSendquery.county" styleId="county" >
				<html:option value="-1">请选择</html:option>
			</form:select>
		</td>
	</tr>
	<!-- 查询按钮 -->
	<tr class="formTableFoot" >
		<td colspan="6" align="center" class="tdPadding">
			<button class="formButton" onClick="doQuery()">查&nbsp;询</button>&nbsp;
			<button class="formButton" onClick="doClear()">重&nbsp;置</button>
		</td>
	</tr>
</table>

<div class="dvScroll">
<table  class="listTalbe" cellpadding="0" cellspacing="0">
	<thead>
		<tr class="title">
	      <td>序号</td>
	      <td><thumbpage:order cname="经销商" filedName="distribid"/></td>
	      <td><thumbpage:order cname="金融机构" filedName="financial_institution"/></td>
	      <td><thumbpage:order cname="品牌" filedName="financial_institution"/></td>
	      <td><thumbpage:order cname="省" filedName="financial_institution"/></td>
	      <td><thumbpage:order cname="市" filedName="financial_institution"/></td>
	      <td><thumbpage:order cname="区" filedName="financial_institution"/></td>
	      <td><thumbpage:order cname="协议编号" filedName="agreement_num"/></td>
	      <td><thumbpage:order cname="协议邮寄时间" filedName="agreement_date"/></td>
	      <td><thumbpage:order cname="金融机构联系人" filedName="financial_user"/></td>
	      <td><thumbpage:order cname="联系方式" filedName="financial_phone"/></td>
	      <td><thumbpage:order cname="协议签署日期" filedName="financial_phone"/></td>
	      <td><thumbpage:order cname="协议到期日" filedName="financial_phone"/></td>
	      <td><thumbpage:order cname="邮寄地址" filedName="send_address"/></td>
	      <td><thumbpage:order cname="预计回收时间" filedName="back_date"/></td>
	      <td align="center">操作</td>
	    </tr>
	</thead>
	<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a">
				<td align="center"><c:out value="${index+1}"/></td>
				<td align="center"><select:dealerName dealerid="${row.distribid}"/></td>
				<td align="center"><select:bankName bid="${row.distribid}"/></td>
				<td align="center"><select:brand bid="${row.brandid}"/></td>
				<td align="center"><select:address sid="${row.province}" idtype="province"/></td>
				<td align="center"><select:address sid="${row.city}" idtype="city"/></td>
				<td align="center"><select:address sid="${row.county}" idtype="county"/></td>
				<td align="center"><c:out value="${row.agreement_num}" /></td>
				<td align="center"><select:timestamp timestamp="${row.agreement_date}" idtype="date"/></td>
				<td align="center"><c:out value="${row.financial_user}"/></td>
				<td align="center"><c:out value="${row.financial_phone}"/></td>
				<td align="center"><select:timestamp timestamp="${row.agreementsigntime}" idtype="date"/></td>
				<td align="center"><select:timestamp timestamp="${row.agreementexpiretime}" idtype="date"/></td>
				<td align="center"><c:out value="${row.send_address}"/></td>
				<td align="center"><select:timestamp timestamp="${row.back_date}" idtype="date"/></td>
				<td align="center" nowrap="true" class="right-border-none">
					<a href="javascript:goUpd('<c:out value='${row.id}'/>');">修改</a>&nbsp;
					<a href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>
				</td>
			</tr>
		</logic:iterate>
	</tbody>  
</table>
</div>
<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="agreementSendList" action="/agreementSend.do?method=agreementSendList"/>
<table class="bottomTable">
	<tr>
		<td><button class="formButton" onClick="goAdd()">新&nbsp;增</button></td>
	</tr>
</table>
<div id="message" class="message" style="display:none"></div>
</html:form>

	</div>
</div>

</body>
</html>
