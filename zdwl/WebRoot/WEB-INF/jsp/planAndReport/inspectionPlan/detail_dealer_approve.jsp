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
<link type="text/css" rel="stylesheet" href="/css/planandreport/planApproval.css" />
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/public.css" />
<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
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
.inspection {
	font-size: 18px;
    color: black;
}
</style>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script type="text/javascript" src="js/jquery.divscroll.js"></script>
<script src="/js/video/initinfo.js"></script>
<script>
	//页面初始化函数
	function doLoad() {
		 
	}
	
	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/inspectionPlan.do?method=findListApprove";
	}
	
	$(function (){
		$("[name='inspectionPlan.predictBeginDate']").datebox({    
			editable:false
		});
		$("#predictFinishDate").datebox({    
			editable:false
		});
		$("[name='inspectionPlan.predictCost']").numberbox({    
		    min:0,    
		    precision:2
		}); 
	});
	
	//审批通过
	function approve(){
		var unCheckPassCause = $("[name='inspectionPlanInfo.unCheckPassCause']").val();
		if(confirm("确认审核通过？")){
			$("#unCheckPassCauseInfo").val(unCheckPassCause);
			var planCode = $("#planCodeInfo").val();//计划编号
			$("#planCodes").val(planCode);
			if(planCode && planCode > 0){
				$("#method").val("approve");//执行审批方法
				document.forms[0].submit();
			}
		}
	}
	//审批不通过
	function approveno(){
		var unCheckPassCause = $("[name='inspectionPlanInfo.unCheckPassCause']").val();
		if(unCheckPassCause == ""){
			alert("请填写审批意见");
			return;
		}
		if(confirm("确认审核不通过？")){
			$("#unCheckPassCauseInfo").val(unCheckPassCause);
			var planCode = $("#planCodeInfo").val();//计划编号
			$("#planCodes").val(planCode);
			if(planCode && planCode > 0){
				$("#method").val("approveno");//执行审批不通过方法
				document.forms[0].submit();
			}
		}
	}
	
</script>
</head>
<input type="hidden" value="巡检计划经理未审核详情页"/>
<body class="h-100 public" onLoad="doLoad()">
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/inspectionPlan.do" styleId="inspectionPlanForm" method="post" onsubmit="return false">
				<input name="method" id="method" type="hidden" value="" />
				<input name="singleApproval" id="singleApproval" type="hidden" value="1"/>
				<input name="planCodes" id="planCodes" type="hidden" value=""/>
				<input name="unCheckPassCauseInfo" id="unCheckPassCauseInfo" type="hidden" value=""/>
				<input name="inspectionPlan.planCode" id="planCodeInfo" type="hidden" value="<c:out value="${planCodeInfo}"/>" />
				<div class="public-main-table hidden" style="margin-top: -40px;">
					<div class="ly-cont" style="height: auto;max-height: 70%;">
						<table class="t-table" border="0" cellspacing="0" cellpadding="0">
							<thead class="t-thead">
								<tr class="t-tr">
									<th class="t-th">序号</th>
									<th class="t-th">外控专员</th>
									<th class="t-th">陪同人员</th>
									<th class="t-th">经销商</th>
									<th class="t-th">品牌</th>
									<th class="t-th" style="width: 10%;">总行/金融机构</th>
									<th class="t-th">分行/分支机构</th>
									<th class="t-th">支行</th>
									<th class="t-th">省份</th>
									<th class="t-th">城市</th>
									<th class="t-th">库存数量</th>
									<th class="t-th">预计开始时间</th>
									<th class="t-th">预计产生费用</th>
									<th class="t-th">最近检查时间</th>
									<th class="t-th">备注</th>
								</tr>
							</thead>
							<tbody class="t-tbody">
								<logic:iterate name="list" id="row" indexId="index">
									<tr class="t-tr">
										<td class="t-td"><c:out value="${index+1}" /></td>
										<td class="t-td"><select:user userid="${row.outControlUserId}"/></td>
										<td class="t-td"><c:out value="${row.escortUserInfo}" /></td>
										
										<td class="t-td"><c:out value="${row.dealerName}" /></td>
										<td class="t-td"><c:out value="${row.brandName}" /></td>
										
										<td class="t-td"><c:out value="${row.oneBankName}" /></td>
										<td class="t-td"><c:out value="${row.twoBankName}" /></td>
										<td class="t-td"><c:out value="${row.threeBankName}" /></td>

										<td class="t-td"><c:out value="${row.provinceName}" /></td>
										<td class="t-td"><c:out value="${row.cityName}" /></td>
										
<%-- 										<td class="t-td"><c:out value="${row.mkTableUserId}" /></td> --%>
										<td class="t-td"><c:out value="${row.stock}" /></td>
										
										<!-- 预计开始时间 <c:out value="${row.id }"/>-->
										<td class="t-td"><input style="width: 62%;" class="ly-bor-none" readonly="readonly"
											id="<c:out value="${row.id }"/>" name="inspectionPlan.predictBeginDate" type="text"
											value='<fmt:formatDate value="${row.predictBeginDate }" pattern="yyyy-MM-dd"/>' />
										</td>
										<!-- 预计产生费用 onblur="savePredictCost(this,<c:out value="${row.id }"/>);"-->
										<td class="t-td"><input style="width: 50% !important; height: 78%;" 
											styleClass="ly-bor-none" readonly="readonly"
											name="inspectionPlan.predictCost" id="<c:out value="${row.id }"/>" 
											value="<c:out value="${row.predictCost }"/>"/>
										</td>
										<td class="t-td"><c:out value="${row.recentCheckTime}" /></td>
										<td class="t-td"><c:out value="${row.remark}" /></td>
									</tr>
								</logic:iterate>
									<tr style="height: 40px;">
										<td class="t-td inspection">合计：</td>
										<td class="t-td inspection"><c:out value="${outControlUserAmount}" /></td>
										<td class="t-td"></td>
										<td class="t-td inspection"><c:out value="${storesAmount}" /></td>
										
										<td class="t-td"></td>
										<td class="t-td"></td>
										<td class="t-td"></td>
										<td class="t-td"></td>
										
										<td class="t-td inspection"><c:out value="${provinceAmount}" /></td>
										<td class="t-td inspection"><c:out value="${cityAmount}" /></td>
										<td class="t-td"></td>
										<td class="t-td"></td>
										
										<td class="t-td inspection" id="predictCostAmount">
											<c:out value="${predictCostAmountValue}" />
										</td>
										<td class="t-td"></td>
										<td class="t-td"></td>
										<td class="t-td"></td>
									</tr class="t-tr">
									<tr  style="height: 35px;">
										<td class="t-td inspection">制表人：</td>
										<td class="t-td inspection">
											<select:user userid="${mkTableUserId}"/>
										</td>
									</tr class="t-tr">
									<tr  style="height: 35px;">
										<td class="t-td inspection">预计完成日期：</td>
										<td class="t-td">
											<input style="width: 62%;" class="ly-bor-none" readonly="readonly"
											id="predictFinishDate" name="predictFinishDate" type="text"
											value='<fmt:formatDate value="${predictFinishDate }" pattern="yyyy-MM-dd"/>' />
										</td>
									</tr class="t-tr">
									<tr  style="height: 30px;"></tr>
							</tbody>
						</table>
					</div>
				</div>
				<div style="margin-top:20px;">
					<div class="public-main-footer-pagin fr">
						<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" 
	 							tableName="detail" action="/inspectionPlan.do?method=detailApprove" />
					</div>
				</div>
				<br/><br/>
				<!-- 新增 -->
               
				
				<div>
					<h3 style="margin-left: 15%;margin-top:46px;float:left;">审批意见 :</h3>
				</div>
				<div>
					<html:textarea property="inspectionPlanInfo.unCheckPassCause" styleId="unCheckPassCause"
					 	style="width:50%;margin-bottom:60px;height: 13%;margin: 10 0 35px 1%;"></html:textarea>
				</div>
				<div class="public-main-footer" style="margin:0 auto; width:420px;">
					<a href="javascript:approve();" class="button btn-add fl">审批通过</a>
					<a href="javascript:approveno();" class="button btn-add fl">审批不通过</a>
					<a href="javascript:doReturn();" class="button btn-add fl">返回</a>
				</div><br/>
				 <c:if test="${ status==2 || status==3 || status==4 || status==5 }">
                <jsp:include page="/WEB-INF/jsp/approvalRecord/approvalRecord.jsp"/>
                </c:if>
			</html:form>
			<br/><br/>
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