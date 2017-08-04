﻿<%@page import="com.zd.csms.rbac.constants.RoleConstants"%>
<%@page import="com.zd.csms.rbac.util.UserSession"%>
<%@page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>
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
<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
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
$(function(){
	 var message="<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>";
	if(message!=null&&message!=""&&message!="null"){
		alert(message);
	} 
	init();
});

	function detail(id){
		location.href="<url:context/>/ledger/leaveApply.do?method=detailPage&leaveApply.id="+id;
	}
	
	//执行查询操作
	function doQuery(){
		document.forms[0].submit();
	}

	//执行表单清空操作
	function doClear(){
		$("#leavePersonStaffNo").val("");
		$("#leavePerson").val("-1");
		$("input[type='radio']").removeAttr('checked');
		$("#bankId").val("-1");
		$("#leaveStartTime").val("");
		$("#leaveEndTime").val("");
		$("#replaceName").val("-1");
		$("#replaceStaffNo").val("-1");
	}
	function init(){
		$("#leavePerson").combobox({
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
		$("#bankId").combobox({
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
		$("#replaceStaffNo").combobox({
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
		$("#replaceName").combobox({
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
		
	}
</script>
</head>
<body  class="h-100 public">
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/ledger/leaveApply" styleId="leaveApplyForm" method="post" onsubmit="return false">
			<input name="method" id="method" type="hidden" value="findPageList" />
			<input name="query.pageType" value="2" type="hidden"/>
			<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
                    <div class="ly-col fl">
                        <div class="label block fl hidden">申请工号：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="query.leavePersonStaffNo" styleId="leavePersonStaffNo"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
	                    <div class="label block fl hidden">申请人：</div>
	                    <div class="input block fl hidden">
							<select style="width:80%" id="leavePerson" name="query.leavePerson" >
								<option value="-1">请选择</option>
								<c:forEach items="${superviseOptions }" var="row">
									<option  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
								</c:forEach>
							</select>
	                    </div>
	                </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden" style="width:25%;">假别：</div>
                        <div class="input block fl hidden" style="width:75%;">
							<form:radios  property="query.leaveType" styleId="leaveType" collection="leaveTypes"/>
                        </div>
                    </div>
					  
                   <div class="ly-col fl">
                       <div class="label block fl hidden">金融机构：</div>
                       <div class="input block fl hidden">
                      	 	<select style="width:80%" id="bankId" name="query.bankId" >
								<option value="-1">请选择</option>
								<c:forEach items="${banks }" var="row">
									<option  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
								</c:forEach>
							</select>
                       </div>
                   </div>
                </div>
                <div class="ly-row clearfix">
                   <div class="ly-col fl">
                        <div class="label block fl hidden">请假开始时间：</div>
                        <div class="input block fl hidden">
                        	<form:calendar property="query.leaveStartTime" styleId="leaveStartTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">结束时间：</div>
                        <div class="input block fl hidden">
                        	<form:calendar property="query.leaveEndTime" styleId="leaveEndTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
                        </div>
                    </div>
                   <div class="ly-col fl">
                        <div class="label block fl hidden">替岗人姓名：</div>
                        <div class="input block fl hidden">
                       		<select style="width:80%" id="replaceName" name="query.replaceName" >
								<option value="-1">请选择</option>
								<c:forEach items="${superviseOptions }" var="row">
									<option  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
								</c:forEach>
							</select>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">替岗人工号：</div>
                        <div class="input block fl hidden">
                        	<select style="width:80%" id="replaceStaffNo" name="query.replaceStaffNo" >
								<option value="-1">请选择</option>
								<c:forEach items="${leaveReplaceRepositorys }" var="row">
									<option  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
								</c:forEach>
							</select>
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
								<th class="t-th">申请人工号</th> 
								<th class="t-th">申请人姓名</th>
								<th class="t-th">经销商</th>
								<th class="t-th">申请时间</th>
								<th class="t-th">假别</th>
								<th class="t-th">是否替岗</th>
								<th class="t-th">替岗人</th>
								<th class="t-th">请假开始时间</th>
								<th class="t-th">请假结束时间</th>
								<th class="t-th">请假天数</th>
								<th class="t-th">审批状态</th>
								<th class="t-th">创建人</th>
								<th class="t-th">创建时间</th>
								<th class="t-th">修改人</th>
								<th class="t-th">修改时间</th>
						      	<th class="t-th">操作</th>
							</tr>
						</thead>
						<tbody class="t-tbody hidden">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td">&nbsp;<c:out value="${index+1}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.leavePersonStaffNo}" /></td>
									<td class="t-td"><select:supervisorName repositoryId="${row.leavePerson}" idtype="name" supervisorId="0"/></td>
 									<td class="t-td">&nbsp;<c:out value="${row.dealerName}" /></td>
									<td class="t-td"><select:timestamp timestamp="${row.applyTime}" idtype="date"/></td>
									<td class="t-td">
										<c:choose>  
										   <c:when test="${row.leaveType==1}">事假</c:when>  
										   <c:when test="${row.leaveType==2}">病假 </c:when>  
										   <c:when test="${row.leaveType==3}">倒休</c:when>
										   <c:when test="${row.leaveType==4}">正休</c:when>  
										</c:choose> 
									</td>
									<td class="t-td">
										<c:choose>  
										   <c:when test="${row.isReplace==1}">是</c:when>  
										   <c:when test="${row.isReplace==2}">否</c:when>  
										</c:choose> 
									</td>
									<td class="t-td"><c:out value="${row.leaveReplace}" /></td>
									<td class="t-td"><select:timestamp timestamp="${row.leaveStartTime}" idtype="mm"/></td>
									<td class="t-td"><select:timestamp timestamp="${row.leaveEndTime}" idtype="mm"/></td>
									<td class="t-td">&nbsp;<c:out value="${row.leaveDays}" /></td>
									
									<td class="t-td"><select:approvalState type="${row.approvalState }"></select:approvalState></td>
									<td class="t-td"><select:user userid="${row.createUser}"/></td>
									<td class="t-td"><select:timestamp timestamp="${row.createTime}" idtype="ss"/></td>
									<td class="t-td"><select:user userid="${row.lastModifiedUser}"/></td>
									<td class="t-td"><select:timestamp timestamp="${row.lastModifiedTime}" idtype="ss"/></td>
									<td class="t-td">
										<a href="javascript:detail('<c:out value="${row.id }"/>')">详情</a>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
			<div class="public-main-footer hidden abs">
				<div class="public-main-footer-pagin fr">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="leaveApplyList" action="/ledger/leaveApply.do?method=findPageList" />
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