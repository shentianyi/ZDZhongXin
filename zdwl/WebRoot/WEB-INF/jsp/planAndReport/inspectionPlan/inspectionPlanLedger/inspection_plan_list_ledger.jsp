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
<%@ page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>
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
	}
	
	//执行查询操作
	function doQuery() {
		$("#method").val("findListDealerLedger");
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
		$("[name='query.inControlUserName']").val("");
	}

	
	//已审核列表
	function goAlreadyCheck() {
		location.href = "<url:context/>/inspectionPlan.do?method=findListDealerLedgerNo";
	}
	
	//进入详情页面
	function detail(id,planCode,status) {
		location = "<url:context/>/inspectionPlan.do?method=detailLedger&inspectionPlan.id=" + id + 
				"&inspectionPlan.planCode=" + planCode + 
				"&inspectionPlanInfo.status=" + status;;
	}
	   //导出execl
function goExt(){
    $("#method").val("ExtFindListLedgerNotAudited");
    document.forms[0].submit();
}

$(function(){
//限制超级角色操作
    restrict();
});

function restrict(){
    var crole = $("#crole").val();
    if(crole == 30){
        $(".yc").hide();
    }
}
</script>
</head>
<body class="h-100 public" onLoad="doLoad()">
<input type="hidden" value="<%= UserSessionUtil.getUserSession(request).getUser().getClient_type()%>" id="crole" />
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">视频管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">巡检计划台账</a>
         </span>
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
				<input type="hidden" id="ids" name="ids" />
				<div class="public-main-input ly-col-2 hidden abs">
					<div class="ly-input-w"style="margin-top:30px;margin-bottom:-10px;height: 130px;" >
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
										<select id="three" name="query.threeBankId" style="height:24px;padding: 0 auto;margin-top:-1px;" class="ly-bor-none threeBankId" style="margin-left: 10%;margin-top: 1%;">
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
										<select id="provinceId" name="query.provinceId"style="height:24px;padding: 0 auto;margin-top:2px;" class="ly-bor-none provinceId"  style="margin: 0 11%;width: 65%;" 
												onchange="changeProvince(this.value,$('#cityId'))">
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
							<%-- <div class="ly-col fl" style="width:33%;">
								<div class="label block fl hidden">内控专员：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none" property="query.inControlUserName" styleId="query.inControlUserName"/>
								</div>
							</div> --%>
						</div>
					</div>
					<div class="ly-button-w" style="margin-top:-50px;">
						<a href="javascript:doQuery();" class="button btn-query">查询</a> 
						<a href="javascript:doClear();" class="button btn-reset">重置</a>
					</div>
				</div>
				<div class="public-main-table hidden abs">
					<div class="ly-cont">
						<table class="t-table" border="0" cellspacing="0" cellpadding="0">
							<thead class="t-thead">
								<tr class="t-tr">
									<th class="t-th">序号</th>
									<th class="t-th">巡检编号</th>
									<th class="t-th">内控专员</th>
									<th class="t-th">省数</th>
									<th class="t-th">市数</th>
									<th class="t-th">涉及店数</th>
									<th class="t-th">银行数</th>
									<th class="t-th">品牌数</th>
									<th class="t-th">库存合计</th>
									<th class="t-th">预计检查天数</th>
									<th class="t-th">计划开始时间</th>
									<th class="t-th">计划提交时间</th>
									<th class="t-th">状态</th>
									<th class="t-th">完成情况</th>
									<th class="t-th">操作</th>
								</tr>
							</thead>
							<tbody class="t-tbody">
								<logic:iterate name="list" id="row" indexId="index">
									<tr class="t-tr">
										<td class="t-td"><c:out value="${index+1}" /></td>
										<td class="t-td"><c:out value="${row.planCode}" /></td>
										<td class="t-td"><select:user userid="${row.inControlUserId}"/></td>
										<td class="t-td"><c:out value="${row.provinceAmount}" /></td>
										<td class="t-td"><c:out value="${row.cityAmount}" /></td>
										<td class="t-td"><c:out value="${row.stores}" /></td>
										
										<td class="t-td"><c:out value="${row.bankAmount}" /></td>
										<td class="t-td"><c:out value="${row.brandAmount}" /></td>
										<td class="t-td"><c:out value="${row.stockAmount}" /></td>
										
										<td class="t-td"><c:out value="${row.predictCheckdays}" /></td>
										<td class="t-td"><c:out value="${row.planBeginTime}" /></td>
										<td class="t-td"><c:out value="${row.planSubmitTime}" /></td>
										
										<td class="t-td">
											<c:if test="${row.status == 1}">未提交</c:if>
											<c:if test="${row.status == 2}">待审核</c:if>
											<c:if test="${row.status == 3}">审核通过</c:if>
											<c:if test="${row.status == 4}">审核未通过</c:if>
										</td>
										<td class="t-td"><c:out value="${row.performance}" /></td>
										<td class="t-td">
											<a id="detailbt" href="javascript:detail('<c:out value="${row.id }"/>','<c:out value="${row.planCode }"/>'
												,'<c:out value="${row.status }"/>')">详情</a>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
				</div>
				<div class="public-main-footer hidden abs">
				    <a href="javascript:goExt();" class="button btn-add fl yc">导出巡检计划台账</a>
<!-- 					<a href="javascript:goAdd();" class="button btn-add fl">新增</a> -->
					<div class="public-main-footer-pagin fr">
						<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" 
  							tableName="list" action="/inspectionPlan.do?method=findListDealerLedger" />
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