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

<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>

<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script src="/pagejs/unbindDealer.js"></script>

<script type="text/javascript">
	var selectDealer = new selectDealer(1, [],[]);
	$(function(){//更新初始化
		selectDealer.dealerIds = $("#dealerId").val().split(",");
		selectDealer.dealerName = $("#dealerName").val().split(",");
		showDealer(selectDealer.dealerIds);
		initDealer();
	});
	function openSelect(){
		$("#selectDealer iframe").attr("src","/market/dealer.do?method=selectDealer&query.ddorbd=1");
		$("#selectDealer").dialog({
		    title: '选择经销商',
		    width: 800,
		    height: 450,
		    closed: false,
		    cache: false,
		    modal: true,
		    onBeforeClose:function(){
				if(selectDealer.dealerIds.length>selectDealer.maxSize){
					alert("最多只可以选择"+selectDealer.maxSize+"个经销商");
					return false;
				}
		    	setValue();
		    }
		});
	}
	function closeSelect(close){
		$("#selectDealer").dialog('close');
	}
	function setValue(){
		$("#dealerId").val(selectDealer.dealerIds.join(","));
		$("#dealerName").val(selectDealer.dealerName.join(","));
		if(selectDealer.dealerIds!=null&&selectDealer.dealerIds.length>0)
			showDealer(selectDealer.dealerIds);
	}

	function clearValue(){
		$("#dealerId").val("");
		$("#dealerName").val("");
		selectDealer.dealerIds=[];
		selectDealer.dealerName=[];
		showDealer(null);
	}
	

</script>
</head>
<body>

	<div class="title">经销商/金融机构拆分合作信息流程</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/market/unbindDealer.do" styleId="unbdForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="update" />
				<html:hidden property="unbd.id"/>
				<html:hidden property="unbd.dealerId" styleId="dealerId"/>
				<html:hidden property="unbd.changeMoney" styleId="changeMoneyJson"/>
				
				<table class="formTable">
					<tr class="formTitle">
						<td colspan="4">市   场   部</td>
					</tr>
					<tr class="jxsInfo">
						<td class="nameCol" colspan="2">经销商：</td>
						<td class="codeCol" colspan="2">
							<html:text property="unbd.dealerName" styleId="dealerName" readonly="true"></html:text>
							<a style="color: blue;" href="javascript:void(0)" onclick="openSelect()">选择经销商</a>
							<a style="color: blue;" href="javascript:void(0)" onclick="clearValue()">清空经销商</a>
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
	<div id="selectDealer" style="overflow: hidden;">
		<iframe height="400px" width="788px" frameborder="0" marginheight="0" marginwidth="0" ></iframe>
	</div>
</body>
</html>
