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

<html class="h-100">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>中都汽车金融监管系统</title>

<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/public.css" />
<link href="/easyui/themes/material/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<style type="text/css">
.title td {
	padding: 0 15px 0 15px;
}
.title {
	width:100%;
	height:50px;
	margin: 0 auto;
	font-family:'Microsoft YaHei';
	font-size:18px;
	font-weight:bold;
	text-align:center;
	line-height:50px;
	background:#eee;
}
.public-main-input .ly-col .input {
    width: 61%;
}
.public-main-input .ly-col .label {
    width: 39%;
}
.sif {
    margin: 6px 10%;
    width: 60%;
    height: 65%;
}
.bt {
    width: 50px;
    height: 23px;
    font-size: small;
    color: blue;
}
</style>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/video/initinfo.js"></script>
<script type="text/javascript" src="js/jquery.divscroll.js"></script>
<script src="/js/payment/payment_info.js" defer="defer"></script>
<script>
	
	//页面初始化函数
	function doLoad() {
		initDate();
		initProvince();
		var provincehiId = $("#provincehiId").val();
		var cityhiId = $("#cityhiId").val();
		if (provincehiId && provincehiId != "" && provincehiId > 0) {
			$("#provinceId").val(provincehiId);
		}
		if (cityhiId && cityhiId != "" && cityhiId > 0) {
			changeProvince(provincehiId,$("#cityId"));
			$("#cityId").val(cityhiId);
		}
	}
	
	//执行查询操作
	function doQuery() {
		$("#method").val("findList");
		document.forms[0].submit();
	}

	//执行表单清空操作
	function doClear() {
		$("#staffNo").val("");
		$("#staffName").val("");
		$("#cardNo").val("");
		$("#dealerNames").val("");
		$("#provinceId").val(-1);
		$("#cityId").val(-1);
		$("[name='query.cityType']").val(-1);
		$("[name='query.status']").val(-1);
		$("#year").combobox('setValue',$("#payYear").val());
		$("#month").combobox('setValue',$("#payMonth").val());
		
	}

	//提交操作
	function doSubmit(){
		var id = $("#paymentId").val();
		if(id > 0){
			if(confirm("确定要提交吗？")){
				location.href="<url:context/>/paymentInfo.do?method=submit&payment.id="+id;
			}
		}else{
			alert("无数据可提交");
		}
	}
	
	//薪酬信息记录导出
	function goExport(){
		$("#method").val("exportExcel");
		document.forms[0].submit();
	}
 	
	/* $(function(){
	$("#provinceId").combobox({
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
}); */
	 
</script>
</head>
<body class="h-100 public" onLoad="doLoad()">
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">薪酬管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">薪酬信息</a>
         </span>
</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/paymentInfo.do" styleId="paymentInfoForm" method="post" onsubmit="return false">
			
				<input name="method" id="method" type="hidden" value="" />
				<html:hidden property="payment.id" styleId="paymentId" />
				<html:hidden property="payment.state" styleId="payState" />
				<html:hidden property="payment.year" styleId="payYear" />
				<html:hidden property="payment.month" styleId="payMonth" />
				<input type="hidden" id="provincehiId" value="<c:out value="${paymentInfoForm.query.provinceId}"/>"/>
				<input type="hidden" id="cityhiId" value="<c:out value="${paymentInfoForm.query.cityId}"/>"/>
				
				<div class="public-main-input ly-col-2 hidden abs" style="overflow: visible;">
					<div class="ly-input-w" style="margin-top: -4px;height: 110px;">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
		                       <div class="label block fl hidden">年份：</div>
		                       <div class="input block fl hidden" style="padding: 2% 5%;">
									<select id="year" name="query.year" >
										<option value="-1">请选择</option>
										<c:forEach items="${years }" var="row">
											<option <c:if test="${paymentInfoForm.query.year==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
										</c:forEach>
									</select>
		                       </div>
		                    </div>
							<div class="ly-col fl">
		                        <div class="label block fl hidden">月份：</div>
		                        <div class="input block fl hidden" style="padding: 2% 5%;">
		                        	<select id="month" name="query.month" >
										<option value="-1">请选择</option>
										<c:forEach items="${months }" var="row">
											<option <c:if test="${paymentInfoForm.query.month==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
										</c:forEach>
									</select>
								 </div>
		                    </div>
							<div class="ly-col fl">
								<div class="label block fl hidden">省份：</div>
								<div class="input block fl hidden">
									<%-- <select id="provinceId" name="query.provinceId" class="sif" 
											onchange="changeProvince(this.value,$('#cityId'))">
										<option value="-1">请选择...</option>
									</select> --%>
									<select  class="ly-bor-none" id="provinceId" name="query.provinceId" >
										<option value="-1">请选择</option>
										<c:forEach items="${provinceId}" var="row">
											<option <c:if test="${paymentInfoForm.query.provinceId==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">城市：</div>
								<div class="input block fl hidden">
									<!-- <select id="cityId" name="query.cityId" class="sif">
										<option value="-1">请选择...</option>
									</select> -->
									<select  class="ly-bor-none" id="cityId" name="query.cityId" >
										<option value="-1">请选择</option>
										<c:forEach items="${cityId}" var="row">
											<option <c:if test="${paymentInfoForm.query.cityId==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">员工工号：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none" property="query.staffNo" styleId="staffNo"/>
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">员工姓名：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none" property="query.staffName" styleId="staffName"/>
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">身份证号：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none" property="query.cardNo" styleId="cardNo"/>
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">店名：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none" property="query.dealerNames" styleId="dealerNames"/>
								</div>
							</div>
						</div>
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">城市类型：</div>
								<div class="input block fl hidden">
									<select class=" ly-bor-none "  name="query.cityType">
										<option value="-1">请选择...</option>
										<c:forEach items="${cityTypes }" var="row">
											<option <c:if test="${paymentInfoForm.query.cityType==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="ly-col fl appstate">
								<div class="label block fl hidden">审批状态：</div>
								<div class="input block fl hidden">
									<select id="status" class="sif" name="query.status">
										<option value="-1">请选择</option>
										<c:forEach items="${approvalStates }" var="row">
											<option <c:if test="${paymentInfoForm.query.status==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="ly-button-w">
						<a href="javascript:doQuery();" class="button btn-query">查询</a> 
						<a href="javascript:doClear();" class="button btn-reset">重置</a>
						<a href="javascript:goExport();" class="button btn-query">导出</a>
					</div>
				</div>
				<div class="public-main-table hidden abs" style="margin-top: 25px;">
					<div class="ly-cont">
						<table class="t-table" border="0" cellspacing="0" cellpadding="0">
							<thead class="t-thead">
								<tr class="t-tr">
									<!-- 19 -->
									<th class="t-th">操作</th>
									<th class="t-th appstate">审批状态</th>
									<th class="t-th">序号</th>
									<th class="t-th">员工工号</th>
									<th class="t-th">员工姓名</th>
									
									<th class="t-th">身份证号</th>
									<th class="t-th">店名</th>
									<th class="t-th">所在省</th>
									<th class="t-th">所在市</th>
									<th class="t-th">城市类型</th>
									
									<th class="t-th">是否转正</th>
									<th class="t-th">是否远疆</th>
									<th class="t-th">驻派属性</th>
									<th class="t-th">实际出勤天数</th>
									<th class="t-th">应出勤天数</th>
									
									<th class="t-th">当月是否全勤</th>
									<th class="t-th">入职日期</th>
									<th class="t-th">司龄（月）</th>
									<th class="t-th">加班天数</th>
									
									<!-- 以下为核算项 18  -->
									<th class="t-th">基本工资（固定）</th>
									<th class="t-th">基本工资</th>
									<th class="t-th">司龄工资</th>
									<th class="t-th">   餐补     </th>
									<th class="t-th">话补</th>
									
									<th class="t-th">交通补</th>
									<th class="t-th">房补</th>
									<th class="t-th">多店多行补助</th>
									<th class="t-th">远疆补助</th>
									<th class="t-th">全勤</th>
									
									<th class="t-th">安家费</th>
									<th class="t-th">替岗费</th>
									<th class="t-th">加班费</th>
									<th class="t-th">异常补助</th>
									
									<th class="t-th">补款一</th>
									<th class="t-th">补款二</th>
									<th class="t-th">扣款一</th>
									<th class="t-th">扣款二</th>
									
									<!-- //以下为合计项  10-->
									<th class="t-th">应发金额</th>
									<th class="t-th">个人所得税</th>
									<th class="t-th">补款一</th>
									<th class="t-th">补款二</th>
									<th class="t-th">扣款一</th>
									
									<th class="t-th">扣款二</th>
									<th class="t-th">实发金额</th>
									<th class="t-th">备注</th>
									<th class="t-th">银行卡号</th>
									<th class="t-th">开户行</th>
								</tr>
							</thead>
							<tbody class="t-tbody">
								<logic:iterate name="list" id="row" indexId="index">
									<tr class="t-tr" <c:if test="${row.status == 2}"> style="background-color:#77FFEE;"</c:if>>
										<td class="t-td">
											<button type="button" id="editInfo<c:out value="${row.id}"/>" name="edit"
											onclick="goEdit('<c:out value="${row.id}"/>');" class="bt" 
											<c:if test="${row.status == 1 || row.status == 3}"> style="color:gray;" disabled="disabled"</c:if> >编辑</button>
<%-- 											<a href="javascript:goEdit('<c:out value="${row.id}"/>');" style="color:blue;">编辑</a> --%>
										</td>
										<td class="t-td appstate"><select:approvalState type="${row.status }"/></td>
										<td class="t-td"><c:out value="${index+1}"/></td>
										<td class="t-td"><c:out value="${row.staffNo}"/></td>
										<td class="t-td"><c:out value="${row.staffName}"/></td>
										
										<td class="t-td"><c:out value="${row.cardNo}"/></td>
										<td class="t-td"><c:out value="${row.dealerNames}"/></td>
										<td class="t-td"><c:out value="${row.provinceName}"/></td>
										<td class="t-td"><c:out value="${row.cityName}"/></td>
										<td class="t-td">
											<c:if test="${row.cityType=='1' }">特殊城市</c:if>
											<c:if test="${row.cityType=='2' }">一类城市</c:if>
											<c:if test="${row.cityType=='3' }">二类城市</c:if>
										</td>
										
										<td class="t-td">
											<c:if test="${row.isFormal=='-1' }">未转正</c:if>
											<c:if test="${row.isFormal=='0' }">半转正</c:if>
											<c:if test="${row.isFormal=='1' }">转正</c:if>
										</td>
										<td class="t-td">
											<c:if test="${row.isFar=='0' }">否</c:if>
											<c:if test="${row.isFar=='1' }">是</c:if>
										</td>
										<td class="t-td">
<!-- 										<%-- 	<c:if test="${row.stationedPro=='0' }">否</c:if> -->
<!-- 											<c:if test="${row.stationedPro=='1' }">是</c:if> --%> -->
										<c:choose>  
											<c:when test="${row.stationedPro==0}"></c:when>
										   <c:when test="${row.stationedPro==1}">属地</c:when>  
										   <c:when test="${row.stationedPro==2}">外派</c:when>  
										</c:choose>
										</td>
										<td class="t-td">
										<c:out value="${row.actualAttendance}"/></td>
										<td class="t-td"><c:out value="${row.shouldAttendance}"/></td>
										
										<td class="t-td">
											<c:if test="${row.isFullTime=='0' }">否</c:if>
											<c:if test="${row.isFullTime=='1' }">是</c:if>
										</td>
										<td class="t-td"><select:timestamp timestamp="${row.entryDate}" idtype="date"/></td>
										<td class="t-td"><c:out value="${row.companyAge}"/></td>
										<td class="t-td"><c:out value="${row.overtimeDays}"/></td>
										
										<!-- 以下为核算项 18 -->
										<td class="t-td"><c:out value="${row.basicSalary}"/></td>
										<td class="t-td"><c:out value="${row.basePay}"/></td>
										
										<td class="t-td"><c:out value="${row.companyAgePay}"/></td>
										<!-- 餐补 -->
										<td class="t-td">
											<input style="width: 90px; height: 78%;border-radius: 5px;"
											styleClass="ly-bor-none"
											name="paymentData" id="mealSubsidy-3-<c:out value="${row.id }"/>"
											value="<c:out value="${row.mealSubsidy }"/>"/>
										</td>
										<!-- 话补 -->
										<td class="t-td">
											<input style="width: 90px;height: 78%;border-radius: 5px;"
											styleClass="ly-bor-none"
											name="paymentData" id="phoneSubsidy-4-<c:out value="${row.id }"/>"
											value="<c:out value="${row.phoneSubsidy }"/>"/>
										</td>
										<!-- 交通补 -->
										<td class="t-td">
											<input style="width: 90px; height: 78%;border-radius: 5px;"
											styleClass="ly-bor-none"
											name="paymentData" id="trafficSubsidy-5-<c:out value="${row.id }"/>"
											value="<c:out value="${row.trafficSubsidy }"/>"/>
										</td>
										<!-- 房补 -->
										<td class="t-td">
											<input style="width: 90px; height: 78%;border-radius: 5px;"
											styleClass="ly-bor-none"
											name="paymentData" id="houseSubsidy-6-<c:out value="${row.id }"/>"
											value="<c:out value="${row.houseSubsidy }"/>"/>
										</td>
										<!-- 多点多行补 -->
										<td class="t-td">
											<input style="width: 90px; height: 78%;border-radius: 5px;"
											styleClass="ly-bor-none"
											name="paymentData" id="manySubsidy-7-<c:out value="${row.id }"/>"
											value="<c:out value="${row.manySubsidy }"/>"/>
										</td>
										<!-- 远疆补 -->
										<td class="t-td">
											<input style="width: 90px; height: 78%;border-radius: 5px;"
											styleClass="ly-bor-none"
											name="paymentData" id="farSubsidy-8-<c:out value="${row.id }"/>"
											value="<c:out value="${row.farSubsidy }"/>"/>
										</td>
										<!-- 全勤补 -->
										<td class="t-td">
											<input style="width: 90px; height: 78%;border-radius: 5px;"
											styleClass="ly-bor-none"
											name="paymentData" id="fullTimeSubsidy-9-<c:out value="${row.id }"/>"
											value="<c:out value="${row.fullTimeSubsidy }"/>"/>
										</td>
										<!-- 安家补 -->
										<td class="t-td">
											<input style="width: 90px; height: 78%;border-radius: 5px;"
											styleClass="ly-bor-none"
											name="paymentData" id="settleCost-10-<c:out value="${row.id }"/>"
											value="<c:out value="${row.settleCost }"/>"/>
										</td>
<%-- 										<td class="t-td edit"><c:out value="${row.replaceCost}"/></td> --%>
										<!-- 替岗费 -->
										<td class="t-td">
											<input style="width: 90px; height: 78%;border-radius: 5px;"
											data-payrowid="<c:out value="${row.id }"/>" styleClass="ly-bor-none"
											name="paymentData" id="replaceCost-2-<c:out value="${row.id }"/>"
											value="<c:out value="${row.replaceCost }"/>"/>
										</td>
										
										<td class="t-td"><c:out value="${row.overtimeCost}"/></td>
										<!-- 异常补助 -->
										<td class="t-td">
											<input style="width: 90px; height: 78%;border-radius: 5px;"
											data-payrowid="<c:out value="${row.id }"/>" styleClass="ly-bor-none"
											name="paymentData" id="elseSubsidy-11-<c:out value="${row.id }"/>"
											value="<c:out value="${row.elseSubsidy }"/>"/>
										</td>
										<!-- 补款一 -->
										<td class="t-td">
											<input style="width: 90px; height: 78%;border-radius: 5px;"
											data-payrowid="<c:out value="${row.id }"/>" styleClass="ly-bor-none"
											name="paymentData" id="subsidyOne-12-<c:out value="${row.id }"/>"
											value="<c:out value="${row.subsidyOne }"/>"/>
										</td>
										<!-- 补款二 -->
										<td class="t-td">
											<input style="width: 90px; height: 78%;border-radius: 5px;"
											data-payrowid="<c:out value="${row.id }"/>" styleClass="ly-bor-none"
											name="paymentData" id="subsidyTwo-13-<c:out value="${row.id }"/>"
											value="<c:out value="${row.subsidyTwo }"/>"/>
										</td>
										<!-- 扣款一 -->
										<td class="t-td">
											<input style="width: 90px; height: 78%;border-radius: 5px;"
											data-payrowid="<c:out value="${row.id }"/>" styleClass="ly-bor-none"
											name="paymentData" id="deductOne-14-<c:out value="${row.id }"/>"
											value="<c:out value="${row.deductOne }"/>"/>
										</td>
										<!-- 扣款二 -->
										<td class="t-td">
											<input style="width: 90px; height: 78%;border-radius: 5px;"
											data-payrowid="<c:out value="${row.id }"/>" styleClass="ly-bor-none"
											name="paymentData" id="deductTwo-15-<c:out value="${row.id }"/>"
											value="<c:out value="${row.deductTwo }"/>"/>
										</td>
										
										<!-- 以下为合计项  10-->
										<td class="t-td"><c:out value="${row.shouldMoney}"/></td>
										<td class="t-td"><c:out value="${row.revenue}"/></td>
										
										<td class="t-td">
											<input style="width: 90px; height: 78%;border-radius: 5px;"
											data-payrowid="<c:out value="${row.id }"/>" styleClass="ly-bor-none"
											name="paymentData" id="subsidyOneTotal-16-<c:out value="${row.id }"/>"
											value="<c:out value="${row.subsidyOneTotal }"/>"/>
										</td>
										<td class="t-td">
											<input style="width: 90px; height: 78%;border-radius: 5px;"
											data-payrowid="<c:out value="${row.id }"/>" styleClass="ly-bor-none"
											name="paymentData" id="subsidyTwoTotal-17-<c:out value="${row.id }"/>"
											value="<c:out value="${row.subsidyTwoTotal }"/>"/>
										</td>
										<td class="t-td">
											<input style="width: 90px; height: 78%;border-radius: 5px;"
											data-payrowid="<c:out value="${row.id }"/>" styleClass="ly-bor-none"
											name="paymentData" id="deductOneTotal-18-<c:out value="${row.id }"/>"
											value="<c:out value="${row.deductOneTotal }"/>"/>
										</td>
										<td class="t-td">
											<input style="width: 90px; height: 78%;border-radius: 5px;"
											data-payrowid="<c:out value="${row.id }"/>" styleClass="ly-bor-none"
											name="paymentData" id="deductTwoTotal-19-<c:out value="${row.id }"/>"
											value="<c:out value="${row.deductTwoTotal }"/>"/>
										</td>
										
										<td class="t-td"><c:out value="${row.praticleMoney}"/></td>
										
										<td class="t-td">
											<input style="height: 78%;border-radius: 5px;" name="editremark"
											onblur="saveRemark(this,<c:out value="${row.id }"/>);" 
											styleClass="ly-bor-none" id="remark-20-<c:out value="${row.id }"/>"
											value="<c:out value="${row.remark }"/>"/>
										</td>
										<td class="t-td"><c:out value="${row.bankCardNo}"/></td>
										<td class="t-td"><c:out value="${row.openBankName}"/></td>

									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
				</div>
				<div class="public-main-footer abs">
					<c:if test="${paymentInfoForm.query.currRole !=80 }">
                        <a href="javascript:doSubmit();" class="button btn-add fl submitBt">提交</a>
                    </c:if>
					<div class="public-main-footer-pagin fr">
						<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" 
  							tableName="list" action="/paymentInfo.do?method=findList" />
					</div>
				</div>
			</html:form>
		</div>
	</div>
<script type="text/javascript">
	$(function(){
		var payState = $("#payState").val();//每月薪酬信息提交状态
		if(payState && payState > 1){//审核中  或是审核通过
			$(".submitBt").hide();//隐藏提交按钮
		}
	})

    var ele = $('#ly-table-thead-w'),
        template;

    $('.public-main-table .ly-cont').perfectScrollbar({
        cursorwidth: 10,
        cursorborder: "none",
        cursorcolor: "#999",
        hidecursordelay: 0,
        zindex: 10001,
        horizrailenabled: true,
        callbackFn: function(){
            if (parseInt($('#ascrail2000').find('div').css('top')) > 0) {
                if (ele.length === 0) {
                    var tr = $('.public-main-table .t-tbody tr'),
                        width = 0;

                    // 生成thead区块
                    template = '<div id="ly-table-thead-w"><div class="ly-table-scroll">';
                    $('.public-main-table .t-thead th').each(function(key){
                        template += '<div class="block fl">'+ $(this).text() +'</div>';
                    });
                    template += '</div></div>';

                    ele = $(template).css({
                        position: 'absolute',
                        top: 0,
                        left: 0,
                        width: '100%',
                        height: '36px',
                        overflow: 'hidden'
                    });

                    // 复制操作
                    tr.eq(0).find('td').each(function(key){
                        var _width = $(this).width() + 1;

                        ele.find('.block').eq(key).css({
                            width: _width,
                            padding: '0 5px',
                            height: '36px',
                            lineHeight: '36px',
                            fontSize: '14px',
                            fontFamily: 'Microsoft Yahei',
                            textAlign: 'center',
                        });
                        width += _width + 10;
                    });
                    ele.find('.ly-table-scroll').css({
                        position: 'relative',
                        width: width,
                        height: '100%',
                        background: '#eee'
                    });

                    $('.public-main-table .ly-cont').append(ele);
                } else {
                    ele.show();
                };
            } else {
                ele.hide();
            };
        },
        _callbackFn: function(left){
            ele.find('.ly-table-scroll').css('left', -left);
        }
    });

</script>
</body>
</html>