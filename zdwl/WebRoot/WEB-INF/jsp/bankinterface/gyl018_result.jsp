<%@page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@page import="com.zd.csms.bank.contants.BankContants"%>
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
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<html class="h-100">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/public.css" />
<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="/pagejs/bankdock.js"></script>
<style type="text/css">
	.title td{
		padding:0 15px 0 15px;
	}
	.textbox{
		margin-top:5px;
		margin-left:10%;
	}
</style>
<script src="/js/common.js"></script>
<script type="text/javascript">
	function doQuery(){
		if(!$("#custNo").combobox('getValue')){
			alert("客户名称不能为空");
			return false;
		}
		if(!getElement("businessNo").value){
			alert("业务编号不能为空");
			return false;
		}
		
		if(getElement("bailAccount").value!=""&&getElement("bailAccountNm").value==""){
			alert("有保证金账号时必输");
			return false;
		}
		
		document.forms[0].submit();
	}
	
	function doClear(){
		getElement("custNo").value="";
		getElement("serialNo").value="";
		$("#applyType").val("2116");
		$("#status").val("01");
		$('#startDate').datebox('clear');
		$('#endDate').datebox('clear');
	}
</script>
<style type="text/css">
	.title td{
		padding:0 15px 0 15px;
	}
</style>
</head>
<body class="h-100 public" >
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">浙商银行对接</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">可提货金额查询</a>
         </span>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/bank/interface.do" styleId="iForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="gyl018" />
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>客户名称：</div>
	                    <div class="input block fl hidden">
	                    	<select id="custNo" name="custNo" style="min-width:150px;width:80%;" >
	                    		<c:forEach items="${custList }" var="row">
	                    			<option <c:if test="${row.value==custNo}">selected='selected'</c:if> value="<c:out value='${row.value}'/>"><c:out value="${row.label }"/></option>
	                    		</c:forEach>
	                    	</select>
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>业务模式：</div>
	                    <div class="input block fl hidden">
	                    	<select class="ly-bor-none" name="businessType" id="businessType">
	                    		<option <c:if test="${businessType=='1210020' }">selected='selected'</c:if> value="1210020">先票/款后货</option>
	                    		<option <c:if test="${businessType=='1210010' }">selected='selected'</c:if> value="1210010">厂商一票通</option>
	                    		<option <c:if test="${businessType=='1220020' }">selected='selected'</c:if> value="1220020">动产动态质押</option>
	                    	</select>
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>业务编号：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="businessNo" type="text" name="businessNo" value="<c:out value='${businessNo }'/>"/>
	                    </div>
                    </div>
                                   
				</div>
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">保证金账号：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="bailAccount" name="bailAccount" type="text" value="<c:out value='${bailAccount }'/>"> </input>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">保证金户名：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="bailAccountNm" name="bailAccountNm" type="text" value="<c:out value='${bailAccountNm }'/>"> </input>
	                    </div>
                    </div>
				</div>
			</div>
			<div class="ly-button-w">
                <a href="javascript:doQuery();" class="button btn-query">查询</a>
                <a href="javascript:doClear();" class="button btn-reset">重置</a>
            </div>
		</div>
		<div class="public-main-table hidden abs">
			<div class="ly-cont">
				<c:if test="${amount!=null }">
					保证金额：<c:out value="${amount }"/>
				</c:if>
				<c:if test="${amount==null }">
					未查询到数据
				</c:if>
			</div>
		</div>
		
		</html:form>
	</div>
</div>

</body>
</html>
