﻿<%@ page contentType="text/html; charset=UTF-8"%>
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
.oneBankId, .twoBankId, .threeBankId, .provinceId, .cityId {
	margin: 3% 15% ;
	width: 58% ;
    height: 64%;
}
.public-main-input .ly-col .input {
    width: 61%;
}
.public-main-input .ly-col .label {
    width: 39%;
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
<script>
	//页面初始化函数
	function doLoad() {
		initBank();
		initProvince();
		var onehiId = $("#onehiId").val();
		var twohiId = $("#twohiId").val();
		var threehiId = $("#threehiId").val();
		var provincehiId = $("#provincehiId").val();
		var cityhiId = $("#cityhiId").val();
		if (onehiId && onehiId != "" && onehiId > 0) {
			$("#one").val(onehiId);
		}
		if (twohiId && twohiId != "" && twohiId > 0) {
			loadSelect(onehiId, $("#two"));
			$("#two").val(twohiId);
		}
		if (threehiId && threehiId !="" && threehiId > 0) {
			loadSelect(twohiId, $("#three"));
			$("#three").val(threehiId);
		}
		if (provincehiId && provincehiId != "" && provincehiId > 0) {
			$("#provinceId").val(provincehiId);
		}
		if (cityhiId && cityhiId != "" && cityhiId > 0) {
			changeProvince(provincehiId,$("#cityId"));
			$("#cityId").val(cityhiId);
		}
		$('#checkBeginDate').datebox({    
			editable:false   
		});
		$('#checkEndDate').datebox({    
			editable:false   
		});
	}
	
	//执行查询操作
	function doQuery() {
		$("#method").val("loadDealerList");
		if($("#one").val() > -1){
			if($("#two").val() == -1){
				alert("请选择分行！");
				return;
			}
		}
		document.forms[0].submit();
	}

	//执行表单清空操作
	function doClear() {
		$("#one").val(-1);
		$("#two").val(-1);
		$("#three").val(-1);
		$("#provinceId").val(-1);
		$("#cityId").val(-1);
		$("[name='query.brandName']").val("");
		$("[name='query.dealerName']").val("");
		$('#checkBeginDate').datebox('clear');
		$('#checkEndDate').datebox('clear');
	}

	function checkAll(name) {
		var el = document.getElementsByTagName('input');
		var len = el.length;
		for (var i = 0; i < len; i++) {
			if ((el[i].type == "checkbox") && (el[i].name == name)) {
				el[i].checked = true;
			}
		}
	}
	function clearAll(name) {
		var el = document.getElementsByTagName('input');
		var len = el.length;
		for (var i = 0; i < len; i++) {
			if ((el[i].type == "checkbox") && (el[i].name == name)) {
				el[i].checked = false;
			}
		}
	}
	//执行返回列表操作
	function goAdd() {
		var infoIds = $("[name='infoIds']:checked");
		var ids = [];
		if(infoIds.length<=0){
			alert("请选择要添加的经销商信息!");
			return false;
		}
		infoIds.each(function(){
			ids.push(this.value);
		});
		ids = ids.join();
		document.getElementById("ids").value = ids;
		$("#method").val("add");
		document.forms[0].submit();
	}
	//已审核列表
	function goAlreadyCheck() {
		var planCodeInfo = $("#planCodeInfo").val();
		var status = $("#videoPlanInfoStatus").val();
		if(planCodeInfo && planCodeInfo > 0){
			location.href = "<url:context/>/inspectionPlan.do?method=findListAlreadyDealer&inspectionPlan.planCode="
					+ planCodeInfo + "&inspectionPlanInfo.status=" + status;
		}
	}
	
	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/inspectionPlan.do?method=findList";
	}
</script>
</head>
<body class="h-100 public" onLoad="doLoad()">
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">视频管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">巡检计划</a>
             &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">新增</a>
         </span>
</div>

	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-tab fr hidden clearfix">
				<div class="ly-button-w fr">
					<a class="button btn-sel fl" href="javascript:void(0);">待选经销商</a> 
					<a class="button fl" href="javascript:goAlreadyCheck();">已选经销商</a>
				</div>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/inspectionPlan.do" styleId="inspectionPlanForm" method="post" onsubmit="return false">
			
				<input name="method" id="method" type="hidden" value="" />
				<input type="hidden" id="onehiId" value="<c:out value="${inspectionPlanForm.query.oneBankId}"/>"/>
				<input type="hidden" id="twohiId" value="<c:out value="${inspectionPlanForm.query.twoBankId}"/>"/>
				<input type="hidden" id="threehiId" value="<c:out value="${inspectionPlanForm.query.threeBankId}"/>"/>
				<input type="hidden" id="provincehiId" value="<c:out value="${inspectionPlanForm.query.provinceId}"/>"/>
				<input type="hidden" id="cityhiId" value="<c:out value="${inspectionPlanForm.query.cityId}"/>"/>
				<input type="hidden" id="planCodeInfo" name="planCodeInfo" value="<c:out value="${planCodeInfo}"/>"/>
				<input type="hidden" id="videoPlanInfoStatus" name="status" value="<c:out value="${status}"/>"/>
				<input type="hidden" id="ids" name="ids" />
				<div class="public-main-input ly-col-2 hidden abs" style="height:220px;">
					<div class="ly-input-w" style="margin-top:30px;margin-bottom:-10px;height: 130px;">
						<div class="ly-row clearfix">
							<div class="ly-col fl" style="width:33%;">
								<div class="label block fl hidden">总行/金融机构：</div>
								<div class="input block fl hidden">
									<select id="one" name="query.oneBankId" style="height:24px;padding: 0 auto;" class="ly-bor-none oneBankId">
										<option value="-1">请选择...</option>
									</select>
								</div>
							</div>
							<div class="ly-col fl" style="width:33%;">
								<div class="label block fl hidden">分行/分支机构：</div>
								<div class="input block fl hidden">
									<select id="two" name="query.twoBankId" style="height:24px;padding: 0 auto;" class="ly-bor-none twoBankId">
										<option value="-1">请选择...</option>
									</select>
								</div>
							</div>
							<div class="ly-col fl" style="width:33%;">
								<div class="label block fl hidden">支行：</div>
								<div class="input block fl hidden">
									<div class="ly-sel-w">
										<select id="three" name="query.threeBankId"  style="height:24px;padding: 0 auto;margin-top:-1px;" class="ly-bor-none threeBankId" style="margin:auto;">
											<option value="-1">请选择...</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="ly-row clearfix">
							<div class="ly-col fl" style="width:33%;">
								<div class="label block fl hidden">省份：</div>
								<div class="input block fl hidden">
									<div class="ly-sel-w">
										<select id="provinceId" name="query.provinceId"style="height:24px;padding: 0 auto;margin-top:2px;" class="ly-bor-none "provinceId" style="margin: 0 11%;width: 65%;" onchange="changeProvince(this.value,$('#cityId'))">
											<option value="-1">请选择...</option>
										</select>
									</div>
								</div>
							</div>
							<div class="ly-col fl" style="width:33%;">
								<div class="label block fl hidden">城市：</div>
								<div class="input block fl hidden">
									<select id="cityId" name="query.cityId" style="height:24px;padding: 0 auto;" class="ly-bor-none cityId">
										<option value="-1">请选择...</option>
									</select>
								</div>
							</div>
							<div class="ly-col fl" style="width:33%;">
								<div class="label block fl hidden">经销商：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none" property="query.dealerName" styleId="query.dealerName"/>
								</div>
							</div>
						</div>
						<div class="ly-row clearfix">
							<div class="ly-col fl" style="width:33%;">
								<div class="label block fl hidden">品牌：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none" property="query.brandName" styleId="query.brandName"/>
								</div>
							</div>
							<div class="ly-col fl" style="width:66%;">
	                        	<div class="label block fl hidden" style="width:20%;margin-left: -3px;">上次检查时间：</div>
	                        	<div class="input block fl hidden" style="width:80%;padding-left:30px;">
		                        	<input style="width:24%;" class="ly-bor-none" id="checkBeginDate" name="query.checkBeginDate" type="text" value='<fmt:formatDate value="${inspectionPlanForm.query.checkBeginDate }" pattern="yyyy-MM-dd"/>'/>
		                        	<span>至</span>
		                        	<input style="width:24%;" class="ly-bor-none" id="checkEndDate" name="query.checkEndDate" type="text" value='<fmt:formatDate value="${inspectionPlanForm.query.checkEndDate }" pattern="yyyy-MM-dd"/>'/>
	                        	</div>
	                    	</div>
						</div>
					</div>
					<div class="ly-button-w" style="margin-top: -10px;">
						<a href="javascript:doQuery();" class="button btn-query">查询</a> 
						<a href="javascript:doClear();" class="button btn-reset">重置</a>
					</div>
				</div>
				<div class="public-main-table hidden abs" style="top:180px;">
					<div class="ly-cont">
						<table class="t-table" border="0" cellspacing="0" cellpadding="0">
							<thead class="t-thead">
								<tr class="t-tr">
									<th class="t-th"><label for="checkAllValue"></label> <input
										name="test" value=""
										onclick="if(this.checked==true) { checkAll('infoIds'); } else { clearAll('infoIds'); }"
										type="checkbox">  全选</th>
									<th class="t-th">序号</th>
									<th class="t-th">经销商</th>
									<th class="t-th">省份</th>
									<th class="t-th">城市</th>
									<th class="t-th">总行/金融机构</th>
									<th class="t-th">分行/分支机构</th>
									<th class="t-th">支行</th>
									<th class="t-th">品牌</th>
									<th class="t-th">库存数量</th>
									<th class="t-th">最近检查时间</th>
								</tr>
							</thead>
							<tbody class="t-tbody">
								<logic:iterate name="list" id="row" indexId="index">
									<tr class="t-tr">
										<td class="t-td">
										<input type="checkbox" id="infoIds"
											name="infoIds" value="<c:out value='${row.dealerId}'/>"/>
										</td>
										<td class="t-td"><c:out value="${index+1}" /></td>
										<td class="t-td"><c:out value="${row.dealerName}" /></td>
										<td class="t-td"><c:out value="${row.provinceName}" /></td>
										<td class="t-td"><c:out value="${row.cityName}" /></td>
										<td class="t-td"><c:out value="${row.oneBankName}" /></td>
										<td class="t-td"><c:out value="${row.twoBankName}" /></td>
										<td class="t-td"><c:out value="${row.threeBankName}" /></td>
										<td class="t-td"><c:out value="${row.brandName}" /></td>
										<td class="t-td"><c:out value="${row.stock}" /></td>
										<td class="t-td"><c:out value="${row.recentCheckTime}" /></td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
				</div>
				<div class="public-main-footer hidden abs">
					<a href="javascript:goAdd();" class="button btn-add fl">确定</a>
					<a href="javascript:doReturn();" class="button btn-add fl">返回</a>
					<div class="public-main-footer-pagin fr">
						<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" 
 							tableName="list" action="/inspectionPlan.do?method=preAdd" />
					</div>
				</div>
			</html:form>
		</div>
	</div>
<script type="text/javascript">
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