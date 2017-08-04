<%@page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>
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
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<html class="h-100">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/public.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link rel="stylesheet" type="text/css" href="/css/style2.css">
 <link rel="stylesheet" href="/css/bootstrap-grid.min.css"> 
<link rel="stylesheet" href="/css/zoomify.css">
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/zoomify.js"></script>
<script src="/js/common.js"></script>
<!-- <script src="/js/calendar.js"></script> -->
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
	//页面初始化函数
	$(function(){
		//显示提示信息
		showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	})
	
	function doDownloadPictures(dealerName){
		location = "<url:context/>/supervisor/checkStockManage.do?method=downloadPictures&checkStockBean.dealerName="+dealerName+"&checkStockBean.id="+$("#checkStockBean\\.id").val();
	}
	
	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/supervisor/checkStockManage.do?method=findList";
	}
</script>
<script type="text/javascript" src="/js/jquery.divscroll.js"></script>
</head>
<body >
<div class="pagebodyOuter">
	<div class="pagebodyInner">
		<html:form action="/supervisor/checkStockManage.do" styleId="checkStockManageForm"
			method="post" onsubmit="return false">
			<input type="hidden" name="method" value="detailPage" />
			<html:hidden property="checkStockBean.id" styleId="checkStockBean.id" />
			<table class="formTable">
				<tr class="formTitle">
					<td colspan="8">质押物盘点表</td>
				</tr>
				<tr>
					<td class="nameCol">经销商：</td>
					<td class="codeCol" ><c:out value="${checkStockManageForm.checkStockBean.dealerName}" /></td>
					<td class="nameCol" >金融机构：</td>
					<td class="codeCol" ><c:out value="${checkStockManageForm.checkStockBean.bankName}" /></td>
					<td class="nameCol" style="width:7%;">品牌：</td>
					<td class="codeCol" ><c:out value="${checkStockManageForm.checkStockBean.brandName}" /></td>
					<td class="nameCol" >经销商属性：</td>
					<td class="codeCol" ><c:out value="${checkStockManageForm.checkStockBean.dealerAttr}" /></td>
				</tr>
				<tr>
				</tr>
				<tr class="formTitle">
					<td colspan="8" >概述</td>
				</tr>
				<tr>
					<td class="codeCol" colspan="2">
						<span style="margin-left:25%;">在库车辆：</span> 
						<c:out value="${checkStockManageForm.checkStockBean.all_wh_count}" />
						<span>辆</span>
					</td>
					<td class="codeCol" colspan="6">
						<span>本库车辆：</span>
						<c:out value="${checkStockManageForm.checkStockBean.wh_count}" />
						<span>辆</span>
						<span style="margin-left:10%;">二库车辆：</span>           
                      	<c:out value="${checkStockManageForm.checkStockBean.two_wh_count}" />
                      	<span>辆</span>
                      	<span style="margin-left:10%;">移动车辆：</span>           
                      	<c:out value="${checkStockManageForm.checkStockBean.move_count}" />
                      	<span>辆</span>           
					</td>
				</tr>
				<tr>
					<td class="codeCol"> 
						<span style="margin-left:25%;">实盘在库：</span>
						<c:out value="${checkStockManageForm.checkStockBean.actual_all_wh_count}" /> 辆
					</td>
					<td class="codeCol" colspan="6">
						<span>实盘本库：</span>
						<c:out value="${checkStockManageForm.checkStockBean.actual_wh_count}" />
						<span>辆</span>
						<span style="margin-left:10%;">实盘二库：</span>
                      	<c:out value="${checkStockManageForm.checkStockBean.actual_two_wh_count}" />
                      	<span>辆</span>
                      	<span style="margin-left:10%;">实盘移动：</span>            
                      	<c:out value="${checkStockManageForm.checkStockBean.actual_move_count}" /> 
                      	<span>辆 </span>
                      	<span style="margin-left:10%;">出库：</span>          
                      	<c:out value="${checkStockManageForm.checkStockBean.actual_out_count}" />
                      	<span>辆</span>  
                      	<span style="margin-left:10%;">展厅统计：</span>          
                        <c:out value="${checkStockManageForm.checkStockBean.exhibition_room_count}" />
                        <span>辆</span>           
					</td>
				</tr>
				<!-- 新增 -->
                <tr>
                    <td class="codeCol" > 
                        <span style="margin-left:25%;">实盘异常：</span>
                        <c:out value="${checkStockManageForm.checkStockBean.actual_abnormal_count}" /> 辆
                    </td>
                    <td class="codeCol" colspan="6">
                        <span>私自移动：</span>
                        <c:out value="${checkStockManageForm.checkStockBean.secretly_move_count}" />
                        <span>辆</span>
                        <span style="margin-left:10%;">私自销售：</span>
                        <c:out value="${checkStockManageForm.checkStockBean.secretly_sale_count}" />
                        <span>辆</span>
                        <span style="margin-left:10%;">在途销售：</span>            
                        <c:out value="${checkStockManageForm.checkStockBean.way_sale_count}" /> 
                        <span>辆 </span>
                        <span style="margin-left:10%;">在途移动：</span>          
                        <c:out value="${checkStockManageForm.checkStockBean.way_move_count}" />
                        <span>辆</span>   
                        <span style="margin-left:10%;">信誉额度：</span>          
                        <c:out value="${checkStockManageForm.checkStockBean.credit_line_count}" />
                        <span>辆</span>           
                    </td>
                </tr>
                <!--  -->
				<tr>
					<td class="codeCol" colspan="8">
						<span style="margin-left:12%;">系统状态与实际库存：</span>
						<form:radios  property="checkStockBean.result" 
						collection="checkStockResults" styleId="checkStockBean.result" disabled="true"/>
						<span style="margin-left:20%;">盘点时间：</span>
						<fmt:formatDate value="${checkStockManageForm.checkStockBean.check_date}" pattern="yyyy-MM-dd HH:mm"/>
					</td>
                 </tr>
			</table>
			<table class="formTable" style="margin-top:20px;">
				<tr class="formTitle">
					<td>车辆信息</td>
				</tr>
			</table>
			<div style="height:760px;">
			<div class="public-main-table hidden abs"  style="margin-top:-175px;height:756px;position: relative;">
			<div class="ly-cont">
				<table class="t-table"  border="0" cellspacing="0" cellpadding="0" >
					<thead class="t-thead">
						<tr class="t-tr">
							<th class="t-th">序号</th>
							<th class="t-th">型号</th>
							<th class="t-th">颜色</th>
							<th class="t-th">车架号</th>
							<th class="t-th">后五位数</th>
							<th class="t-th">检查时间</th>
							<th class="t-th">系统状态</th>
							<th class="t-th">实际状态</th>
							<th class="t-th">备注</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden" >
						<logic:iterate name="checkStockManageForm" property="checkStockCarBeans"  id="checkStockCarBean" indexId="index">
							<tr class="t-tr">
								<td class="t-td" ><c:out value="${index+1}" /><html:hidden name="checkStockCarBean" property="id" indexed="true"/></td>
								<td class="t-td" ><c:out value="${checkStockCarBean.model }" /></td>
								<td class="t-td" ><c:out value="${checkStockCarBean.color }" /></td>
								<td class="t-td" ><c:out value="${checkStockCarBean.vin }" /></td>
								<td class="t-td" ><c:out value="${checkStockCarBean.lastFiveVin }" /></td>
								<td class="t-td"><select:timestamp timestamp="${checkStockCarBean.check_date}" idtype="ss"/></td>
								<td class="t-td" >
									<c:choose>  
									   <c:when test="${checkStockCarBean.currstate==1}">本库</c:when>  
									   <c:when test="${checkStockCarBean.currstate==2}">二库</c:when>  
									   <c:when test="${checkStockCarBean.currstate==3}">移动</c:when>  
									</c:choose> 
								</td>
								<%-- <td style="text-align: center;" > 
									<c:choose>  
									   <c:when test="${checkStockCarBean.actualstate==1}">本库</c:when>  
									   <c:when test="${checkStockCarBean.actualstate==2}">二库</c:when>  
									   <c:when test="${checkStockCarBean.actualstate==3}">移动</c:when>  
									   <c:when test="${checkStockCarBean.actualstate==4}">出库</c:when>  
									</c:choose> 
								</td> --%>
								<td  class="t-td" style="text-align: center;" > 
                                    <div class="ly-col">
                                        <div class="input block hidden" style="text-align: center;">
                                         <select style="height:24px;padding: 0 auto;" class="ly-bor-none twoBankId" name="checkStockCarBean.actualstate" id="<c:out value="${checkStockCarBean.id}" />" disabled="true">
                                            <option value="-1"  >请选择</option>
                                            <c:forEach var="obj" items="${carStatusOptions}">
                                                <option <c:if test="${obj.value == checkStockCarBean.actualstate }">selected='selected'</c:if> value="<c:out value='${obj.value }'/>"><c:out value="${obj.label }"/></option>
                                            </c:forEach>
                                        </select> 
                                        </div>
                                    </div>
                                </td>
                                <td class="t-td"><input type="text"  maxlength=10 style="margin-left:34px;"  class="checkStockCarBean[<c:out value="${checkStockCarBean.id}" />].remark" name="checkStockCarBean.remark" value="<c:out value='${checkStockCarBean.remark }' />" readonly="readonly" /></td>
								</tr>
						</logic:iterate>
					</tbody>
				</table>
				</div>
			</div>
			</div>
			<table class="formTable" style="top:1210px;position:absolute">
				<tr class="formTitle">
					<td colspan="4">盘点结果异常处理</td>
				</tr>
				
				<tr class="t-tr">
					<th colspan="2" class="t-th">常规异常</th>
					<th colspan="2" class="t-th">非常规异常</th>
				</tr>
               	<tr>
               		<td colspan="2" align="center" >
                		<html:textarea style="width:80%;height:100px;"  property="checkStockBean.normal_difference" styleId="checkStockBean.normal_difference" disabled="true"/>
                	<td>
                	<td colspan="2" align="center" >
                		<html:textarea style="width:80%;height:100px;" property="checkStockBean.un_normal_difference" styleId="checkStockBean.un_normal_difference" disabled="true"/>
                	<td>
               	</tr>
               	<tr class="formTitle">
					<td colspan="4">图片展示</td>
				</tr>
				<tr>
					<td colspan="4">
						<div class="container">		
							<div class="examples">	
		
									<c:forEach items="${list }" var="img" varStatus="index">
									<c:if test="${(index.index%4)==0 && index.index!=0}">
										</div>
									</c:if>
									<c:if test="${(index.index%4)==0 && index.index != 20 }">
										<div class="row">
									</c:if>
										<div class="example col-xs-3 col-md-3">
											<p><img src="<c:out value="${img.cancelUrl }" />" class="img-rounded" alt=""/></p>
										</div>
									</c:forEach>
									</div>
							</div>	
						</div>
					</td>
				</tr>
               	<tr class="formTableFoot">
					<td colspan="4" align="center">
						<button class="formButton" style="width:80px;" onClick="doDownloadPictures('<c:out value="${checkStockManageForm.checkStockBean.dealerName}" />')" />下载图片</button>&nbsp;&nbsp;
						<button class="formButton" style="width:80px;" onClick="doReturn()">返&nbsp;回</button>
					</td>
				</tr>
			</table>
			</html:form>
		</div>
	</div>
	<script type="text/javascript">
	
	$('.example img').zoomify();//图片
	
	
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
