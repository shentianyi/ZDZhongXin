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
<script src="/js/common.js"></script>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/video/initinfo.js"></script>
<script type="text/javascript" src="/js/jquery.divscroll.js"></script>
<script type="text/javascript" src="/pagejs/windcontrol/lenger.js"></script>
<style type="text/css">
.title td {
	padding: 0 15px 0 15px;
}

.oneBankId, .twoBankId, .threeBankId, .provinceId, .cityId {
	margin: 3% 15%;
	width: 58%;
	height: 64%;
}

.public-main-input .ly-col .input {
	width: 61%;
}

.public-main-input .ly-col .label {
	width: 39%;
}
</style>

<script>
 //进入详情页面
function detail(id) {
	location = "/windcontrol/inspection.do?method=detailsEntry&id=" + id;

}
//导出execl
function goExt(){
    $("#method").val("ExtInspecReportLenger");
    document.forms[0].submit();
}

//查询
function doQuery(){
    $("#method").val("reportLedgerList");
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
<body class="h-100 public" onLoad="doLoad(true)">
<input type="hidden" value="<%= UserSessionUtil.getUserSession(request).getUser().getClient_type()%>" id="clientType" />
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">巡检管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">巡检报告台账</a>
         </span>
</div>

	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/ledger/inspectionReport.do"
				styleId="inspectionLedgerForm" method="post" onsubmit="return false">
				<input name="method" id="method" type="hidden"
					value="reportLedgerList" />
				<input type="hidden" id="onehiId"
					value="<c:out value="${inspectionLedgerForm.oneBankId}"/>" />
				<input type="hidden" id="twohiId"
					value="<c:out value="${inspectionLedgerForm.twoBankId}"/>" />
				<input type="hidden" id="threehiId"
					value="<c:out value="${inspectionLedgerForm.threeBankId}"/>" />
				<input type="hidden" id="provincehiId"
					value="<c:out value="${inspectionLedgerForm.provinceId}"/>" />
				<input type="hidden" id="cityhiId"
					value="<c:out value="${inspectionLedgerForm.cityId}"/>" />
					
			   <input type="hidden" id="dangerhiId"
					value="<c:out value="${inspectionLedgerForm.danger_level}"/>" />
				<input type="hidden" id="dealerhiId"
					value="<c:out value="${inspectionLedgerForm.dealer_level}"/>" />
				<input type="hidden" id="supervisorhiId"
					value="<c:out value="${inspectionLedgerForm.supervisor_level}"/>" /> 
				

				<div class="public-main-input ly-col-4 hidden abs"  style="height: 240px;">
					<div class="ly-input-w">
						<div class="ly-row clearfix" style="border-right:1px solid #eee">
							<div class="ly-col fl">
								<div class="label block fl hidden">总行/金融机构：</div>
								<div class="input block fl hidden">
									<select id="one" name="oneBankId"style="height:24px;padding: 0 auto;" class="ly-bor-none oneBankId">
										<option value="-1">请选择...</option>
									</select>
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">分行/分支机构：</div>
								<div class="input block fl hidden">
									<select id="two" name="twoBankId" style="height:24px;padding: 0 auto;" class="ly-bor-none twoBankId">
										<option value="-1">请选择...</option>
									</select>
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">支行：</div>
								<div class="input block fl hidden">
									<div class="ly-sel-w">
										<select id="three" name="threeBankId" style="height:24px;padding: 0 auto;margin-top:-1px;" class="ly-bor-none threeBankId"
											style="margin: auto;">
											<option value="-1">请选择...</option>
										</select>
									</div>
								</div>
							</div>
							
						</div>

						<div class="ly-row clearfix">
						<div class="ly-col fl">
								<div class="label block fl hidden">省份：</div>
								<div class="input block fl hidden">
									<div class="ly-sel-w">
										<select id="provinceId" name="provinceId" style="height:24px;padding: 0 auto;margin-top:5px;" class="ly-bor-none provinceId"
											style="margin: 0;"
											onchange="changeProvince(this.value,$('#cityId'))">
											<option value="-1">请选择...</option>
										</select>
									</div>
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">城市：</div>
								<div class="input block fl hidden">
									<select id="cityId" name="cityId" style="height:24px;padding: 0 auto;" class="ly-bor-none cityId">
										<option value="-1">请选择...</option>
									</select>
								</div>
							</div>

							<div class="ly-col fl">
								<div class="label block fl hidden">经销商：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none" property="dealerName"
										styleId="dealerName" />
								</div>
							</div>

							<div class="ly-col fl" style="border-top:1px solid #eee">
								<div class="label block fl hidden">品牌：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none" property="brandName"
										styleId="brandName" />
								</div>
							</div>
						</div>

						<div class="ly-row clearfix">
						<div class="ly-col fl">
								<div class="label block fl hidden">风险等级：</div>
								<div class="input block fl hidden ">
									<select id="danger_level" name="danger_level"style="height:24px;padding: 0 auto;" class="ly-bor-none cityId">
										<option value="-1" >请选择...</option>
										<option value="4" <c:if test="${inspectionLedgerForm.danger_level==4 }">selected='selected'</c:if>>无</option>
										<option value="1" <c:if test="${inspectionLedgerForm.danger_level==1 }">selected='selected'</c:if>>A级</option>
										<option value="2" <c:if test="${inspectionLedgerForm.danger_level==2 }">selected='selected'</c:if>>B级</option>
										<option value="3" <c:if test="${inspectionLedgerForm.danger_level==3 }">selected='selected'</c:if>>C级</option>
									</select>
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">经销店等级：</div>
								<div class="input block fl hidden">
									<select id="dealer_level" name="dealer_level" style="height:24px;padding: 0 auto;" class="ly-bor-none cityId">
										<option value="-1">请选择...</option>
										<option value="4" <c:if test="${inspectionLedgerForm.dealer_level==4 }">selected='selected'</c:if>>无</option>
										<option value="1" <c:if test="${inspectionLedgerForm.dealer_level==1 }">selected='selected'</c:if>>A级</option>
										<option value="2" <c:if test="${inspectionLedgerForm.dealer_level==2 }">selected='selected'</c:if>>B级</option>
										<option value="3" <c:if test="${inspectionLedgerForm.dealer_level==3 }">selected='selected'</c:if>>C级</option>
									</select>
								</div>
							</div>
							<div class="ly-row clearfix">
						 <div class="ly-col fl">
								<div class="label block fl hidden">监管员等级：</div>
								<div class="input block fl hidden">
									<select id="supervisor_level" name="supervisor_level"
										style="height:24px;padding: 0 auto;" class="ly-bor-none cityId">
										<option value="-1">请选择...</option>
										<option value="4" <c:if test="${inspectionLedgerForm.supervisor_level==4 }">selected='selected'</c:if>>无</option>
										<option value="1" <c:if test="${inspectionLedgerForm.supervisor_level==1 }">selected='selected'</c:if>>A级</option>
										<option value="2" <c:if test="${inspectionLedgerForm.supervisor_level==2 }">selected='selected'</c:if>>B级</option>
										<option value="3" <c:if test="${inspectionLedgerForm.supervisor_level==3 }">selected='selected'</c:if>>C级</option>
									</select>
								</div>
							</div>
						</div>
							<div class="ly-col fl" style="width: 50%;">
								<div class="label block fl hidden"
									style="width: 20%; margin-left: -3px;">检查日期：</div>
								<div class="input block fl hidden"
									style="width: 80%; padding-left: 23px;">
									<input style="width: 28%;" class="ly-bor-none"
										id="submitTimeBegin" name="checkTimeBegin" type="text"
										value='<fmt:formatDate value="${inspectionLedgerForm.checkTimeBegin }" pattern="yyyy-MM-dd"/>' />
									<span>至</span> <input style="width: 28%;" class="ly-bor-none"
										id="submitTimeEnd" name="checkTimeEnd" type="text"
										value='<fmt:formatDate value="${inspectionLedgerForm.checkTimeEnd }" pattern="yyyy-MM-dd"/>' />
								</div>
							</div>

							
							
						 </div>
						
					</div>
					<div class="ly-button-w">
						<a href="javascript:doQuery();" class="button btn-query">查询</a> <a
							href="javascript:doClear(true);" class="button btn-reset">重置</a>
					</div>
				</div>
				<div class="public-main-table hidden abs" style="top:210px;">
					<div class="ly-cont">
						<table class="t-table" border="0" cellspacing="0" cellpadding="0">
							<thead class="t-thead">
								<tr class="t-tr">
									<th class="t-th">序号</th>
									<th class="t-th">巡检编号</th>
									<th class="t-th">外空专员</th>
									<th class="t-th">经销商</th>
									<th class="t-th">品牌</th>
									<th class="t-th">总行/金融机构</th>
									<th class="t-th">分行/分支机构</th>
									<th class="t-th">支行</th>
								    <th class="t-th">省</th>
									<th class="t-th">市</th>
									<th class="t-th">检查日期</th>
									<th class="t-th">检查用时</th>
									<th class="t-th">检查类型</th>
									<th class="t-th">风险等级</th>
									<th class="t-th">经销店等级</th>
									<th class="t-th">监管员等级</th>
									<th class="t-th">操作</th>
								</tr>
							</thead>
							<tbody class="t-tbody">
                                <c:if test="${!empty list}">
									<logic:iterate name="list" id="row" indexId="index">
										<tr class="t-tr">
											<td class="t-td"><c:out value="${index+1}" /></td>
											<td class="t-td"><c:out value="${row.planCode}" /></td>
											<td class="t-td"><c:out value="${row.outControlUserName}" /></td>
											<td class="t-td"><c:out value="${row.dealerName}" /></td>
											<td class="t-td"><c:out value="${row.brandName}" /></td>
											<td class="t-td"><c:out value="${row.oneBankName}" /></td>
											<td class="t-td"><c:out value="${row.twoBankName}" /></td>
											<td class="t-td"><c:out value="${row.threeBankName}" /></td>
											<td class="t-td"><c:out value="${row.provinceName}" /></td>
											<td class="t-td"><c:out value="${row.cityName}" /></td>
											<td class="t-td"><fmt:formatDate
													value="${row.check_time}" pattern="yyyy-MM-dd" /></td>
											<td class="t-td"><c:out value="${row.check_period}" /></td>
											<td class="t-td"><c:out value="${row.checkTypeName}" /></td>
											<td class="t-td"><c:out value="${row.dangerLevelName}" /></td>
											<td class="t-td"><c:out value="${row.dealerLevelName}" /></td>
											<td class="t-td"><c:out value="${row.supervisorLevelName}" /></td>
											<td class="t-td"><a
												href="javascript:detail('<c:out value="${row.id }"/>')">查看</a>
											</td>
										</tr>
									</logic:iterate>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
				<div class="public-main-footer hidden abs">
				    <!-- 需求89 超级角色只有查看权限 currentRole=80，userId=761  -->
				    <c:if test="${inspectionLedgerForm.userId !=761 && inspectionLedgerForm.currRole !=80}">
                        <a href="javascript:goExt();" class="button btn-add fl yc">导出巡检报告台账</a>
                    </c:if>
                    <!-- end -->
					<div class="public-main-footer-pagin fr">
						<thumbpage:tools
							className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>"
							tableName="pagelist"
							action="/ledger/inspectionReport.do?method=reportLedgerList" />
					</div>
				</div>
			</html:form>
		</div>
	</div>
	<!-- <script src="/pagejs/scrollbar.js"></script>  -->
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