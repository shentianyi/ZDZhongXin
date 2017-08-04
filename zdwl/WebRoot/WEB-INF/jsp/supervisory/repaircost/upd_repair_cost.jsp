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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>中都汽车金融监管系统</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="/css/public.css" />
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script>
function doLoad(){
	changeYj();
}
//执行保存操作
function doSave(){
	var value = document.getElementById("repaircost.repair_project").value;
	if(value == "" || value == -1){
		alert("请选择维修项目");
		return false;
	}
	var value = document.getElementById("repaircost.problem").value;
	if(value == ""){
		alert("请填写具体问题");
		return false;
	}
	var value = document.getElementById("repaircost.money").value;
	if(value == ""){
		alert("请填写维修费用");
		return false;
	}
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("repairCostForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

function detail(id){
		location.href="<url:context/>/repaircost.do?method=detailPage&repaircost.id="+id;
	}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/repaircost.do?method=findList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
function changeYj() {
	var repositoryId = document.getElementById("repositoryId").value;
	var url = "../json/getMailCost.do?callback=?&id="+repositoryId;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setYj(data[0]);
	});
}
	
function setYj(yj){
	$("#yjgh").val(yj.gh);
	$("#yjjxs").val(yj.jxs);
	$("#yjjrjg").val(yj.jrjg);
}
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">修改设备维修申请单</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/repaircost" styleId="repairCostForm" method="post" onsubmit="return false">
<html:hidden property="repaircost.id" styleId="repaircost.id"/>
<input type="hidden" name="method" id="method" value="updRepairCost">
<input type="hidden" name="repositoryId" id="repositoryId" value="<c:out value='${repositoryId}'/>" />
<html:hidden property="repaircost.promoter" />
<html:hidden property="repaircost.credatetime" />
<html:hidden property="repaircost.createuserid" />
<html:hidden property="repaircost.createdate" />
<html:hidden property="repaircost.upduserid" />
<html:hidden property="repaircost.upddate" />
<html:hidden property="repaircost.nextApproval" />
<html:hidden property="repaircost.approvalState" />
<table class="formTable">
	<tr>
		<td class="nameCol">姓名：</td>
		<td class="codeCol">
			<c:out value="${username}"/>
		</td>
		<td class="nameCol">经销商：</td>
		<td class="codeCol">
			<input type="text" id="yjjxs" readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<td class="nameCol">工号：</td>
		<td class="codeCol">
			<input type="text" id="yjgh" readonly="readonly"/>
		</td>
		<td class="nameCol">金融机构：</td>
		<td class="codeCol">
			<input type="text" id="yjjrjg" readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>维修项目：</td>
		<td class="codeCol">
			<form:select styleClass="ly-bor-none" property="repaircost.repair_project" styleId="repaircost.repair_project" >
					<html:option value="-1">请选择</html:option>
					<html:optionsCollection name="fixedAssetsOptions" label="label" value="value"/>
			</form:select>
		</td>
		<td class="nameCol"></td>
		<td class="codeCol"></td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>具体问题：</td>
		<td class="codeCol">
			<html:text property="repaircost.problem" styleId="repaircost.problem"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>维修费用(元)：</td>
		<td class="codeCol">
			<html:text property="repaircost.money" styleId="repaircost.money" onchange= "if( !/^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='';}"></html:text>
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
<div class="title">维修项目维修记录</div>
<table class="formTable">
	<tr>
		<td class="nameCol">资产编号：</td>
		<td class="codeCol">
			<c:out value="${asset_num}"/>
		</td>
		<td class="nameCol">使用时间：</td>
		<td class="codeCol">
			<c:out value="${useTime}"/>
		</td>
	</tr>
	</table>
	
<div class="public-main-table  abs" >
					<table class="t-table" border="0" cellspacing="0" cellpadding="0">
						<thead class="t-thead">
							<tr class="t-tr">
						      	<th class="t-th">序号</th>
								<th class="t-th">申报人</th>
								<th class="t-th">维修项目</th>
								<th class="t-th">维修费用</th>
								<th class="t-th">具体问题</th>
								<th class="t-th">审批状态</th>
								<th class="t-th">下一审批人</th>
								<th class="t-th">创建人</th>
								<th class="t-th">创建时间</th>
								<th class="t-th">修改人</th>
								<th class="t-th">修改时间</th>
								<!-- <th class="t-th">操作</th> -->
							</tr>
						</thead>
						<tbody class="t-tbody hidden">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}" /></td>
									<td class="t-td"><select:user userid="${row.promoter}"/></td>
									<td class="t-td"><c:out value="${row.repair_project}" /></td>
									<td class="t-td"><c:out value="${row.money}" /></td>
									<td class="t-td"><c:out value="${row.problem}" /></td>
									<td class="t-td"><select:approvalState type="${row.approvalState }"></select:approvalState></td>
									<td class="t-td"><select:nextApprovalName roleId="${row.nextApproval }"></select:nextApprovalName></td>
									<td class="t-td"><select:user userid="${row.createuserid}"/></td>
									<td class="t-td"><select:timestamp timestamp="${row.createdate}" idtype="date"/></td>
									<td class="t-td"><select:user userid="${row.upduserid}"/></td>
									<td class="t-td"><select:timestamp timestamp="${row.upddate}" idtype="date"/></td>
									<%-- <td class="t-td">
										<a href="javascript:detail('<c:out value="${row.id }"/>')">详情</a>
									</td> --%>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
			</div>


<div class="public-main-footer-pagin fr">
<div  class="public-main-footer-pagin fr" >
	<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="findHistoryList" action="/repaircost.do?method=updRepairCostEntry"/>
</div>
</div>
</html:form>
<br/>
<div id="message" class="message" style="display:none"></div>

	</div>
</div>
</body>
</html>
