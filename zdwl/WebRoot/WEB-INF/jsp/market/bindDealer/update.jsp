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
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>


<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script src="/pagejs/bindDealer.js"></script>
<script type="text/javascript">
	//页面初始化函数
	function doLoad(){
		//显示提示信息
		showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	}

	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/market/bindDealer.do?method=findList";
	}

	//执行表单重置操作
	function doReset() {
		document.forms[0].reset();
	}
	
	var selectDealer = new selectDealer(3, [],[]);
	$(function(){//更新初始化
		selectDealer.dealerIds = $("#dealerId").val().split(",");
		selectDealer.dealerName = $("#dealerName").val().split(",");
		console.info(selectDealer);
		showDealer(selectDealer.dealerIds);
		initDealer();
	});
	function openSelect(){
		$("#selectDealer iframe").attr("src","/market/dealer.do?method=selectDealer&query.ddorbd=3");
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
				<html:hidden property="bd.changeMoney" styleId="changeMoneyJson"/>
				<table class="formTable">
					<tr class="formTitle">
						<td colspan="4">市   场   部</td>
					</tr>
					<tr class="jxsInfo">
						<td class="nameCol" colspan="2">经销商：</td>
						<td class="codeCol" colspan="2">
							<html:text property="bd.dealerName" styleId="dealerName" readonly="true"></html:text>
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
