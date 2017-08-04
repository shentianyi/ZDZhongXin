﻿<%@page import="com.zd.tools.validate.constants.PatternConstants"%>
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
<style type="text/css">
	.title td{
		padding:0 15px 0 15px;
	}
</style>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script type="text/javascript" src="/js/jquery.divscroll.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

function doSet(id){
	location = "<url:context/>/distribset.do?method=distribset&dealer.id="+id;
}
$(function(){
	initTools();
});
function initTools(){
	var dateboxOptions={"editable":false}
	
	$('#startInDate').datebox(dateboxOptions);  
	$('#endInDate').datebox(dateboxOptions);
}

//执行查询操作
function doQuery() {
	/* $("#method").val("findList"); */
	document.forms[0].submit();
}

//执行表单清空操作
function doClear() {
	//清空资源名输入框
	getElement("dealerQuery.dealerName").value="";
	
}


</script>
<style type="text/css">
	.title td{
		padding:0 15px 0 15px;
	}
</style>
</head>
<body class="h-100 public">
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">考勤管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">经销商考勤时间</a>
         </span>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/attSign" styleId="attendanceForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="attendanceTime" />
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden"></div>
                        <div class="input block fl hidden">
                        	<!-- <html:text styleClass="ly-bor-none" property="dealerQuery.dealerName" styleId="query.dealerName"/> -->
                       			
                        
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">经销商名称：</div>
                        <div class="input block fl hidden add_easyui">
                            <select style="width:80%" id="dealerQuery.id" name="dealerQuery.id" class="easyui-combobox">											
									<option value="-1">请选择<%-- <c:out value="${dealerQuery.dealerName }"/> --%></option>		
									<%-- <c:forEach items="${dealers }" var="row">		
										<option  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 	
									</c:forEach> --%>	
									<c:forEach items="${dealers }" var="row">
											 <option <c:if test="${attfrom.dealerQuery.dealerName==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"/><c:out value='${row.label }'/></option> 
										</c:forEach>	
								</select>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden"></div>
                        <div class="input block fl hidden">
                        	
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
				<table class="t-table" border="0" cellspacing="0" cellpadding="0">
					<thead class="t-thead">
						<tr class="t-tr">
							<th class="t-th">序号</th>
							<th class="t-th">经销商</th>
							<th class="t-th">上午签到时间</th>
							<th class="t-th">上午签退时间</th>
							<th class="t-th">下午签到时间</th>
							<th class="t-th">下午签退时间</th>
							<th class="t-th">配置的工作日</th>
							<th class="t-th">操作</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td"><c:out value="${index+1}"/></td>
								<td class="t-td"><c:out value="${row.dealerName }"/></td>
								<td class="t-td"><c:out value="${row.morningStart}"/></td>
								<td class="t-td"><c:out value="${row.morningEnd}"/></td>
								<td class="t-td"><c:out value="${row.afternoonStart}"/></td>
								<td class="t-td"><c:out value="${row.afternoonEnd}"/></td>
								<td class="t-td"><c:out value="${row.workDays}"/></td>
								<td class="t-td">
                                    <a style="color:blue;" href="/attSign.do?method=updateAttendanceTime&dealerId=<c:out value='${row.dealerid }'/>">考勤时间设置</a>
                                </td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		</div>
		<div class="public-main-footer hidden abs">
			<div class="public-main-footer-pagin fr">
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="dealerList" action="/attSign.do?method=attendanceTime" />
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