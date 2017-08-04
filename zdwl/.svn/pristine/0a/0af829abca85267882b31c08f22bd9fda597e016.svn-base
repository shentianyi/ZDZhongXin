<%@page import="com.zd.csms.rbac.constants.RoleConstants"%>
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
<script type="text/javascript" src="js/jquery.divscroll.js"></script>
<script>
$(function(){
	 var message="<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>";
	if(message!=null&&message!=""&&message!="null"){
		alert(message);
	} 
	init();
});
	//进入新增页面
	function goAdd() {
		location="<url:context/>/leaveApply.do?method=addLeaveApplyEntry";
	}

	//执行删除操作
	function doDel(id) {
		if (confirm("删除后数据不可恢复\n是否继续？")) {
			location = "<url:context/>/leaveApply.do?method=deleteLeaveApply&leaveApply.id=" + id;
		}
	}
	
	//已审批列表
	function goAlreadyApproval(){
		location.href="<url:context/>/leaveApply.do?method=findPageList&query.pageType=2";
	}
	
	function doSubmit(id){
		if(confirm("确定要提交吗？")){
			location.href="<url:context/>/leaveApply.do?method=submit&leaveApply.id="+id;
		}
	}
	
	function approval(id){
		location.href="<url:context/>/leaveApply.do?method=preApproval&leaveApply.id="+id;
	}
	
	function detail(id){
		location.href="<url:context/>/leaveApply.do?method=detailPage&leaveApply.id="+id;
	}
	
	//进入修改页面
	function doUpd(id){
		location = "<url:context/>/leaveApply.do?method=updateLeaveApplyEntry&leaveApply.id="+id;
	}
	//执行查询操作
	function doQuery(){
		document.forms[0].submit();
	}

	//执行表单清空操作
	function doClear(){
		$("#leavePerson").val("-1");
		$("#applyTime").val("");
		$("#dealerId").val("-1");
		$("#brandId").val("-1");
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
		$("#dealerId").combobox({
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
		$("#brandId").combobox({
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
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">监管员信息管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">请假申请</a>
         </span>
</div>

<div class="public-bar hidden">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
	        <div class="public-bar-tab fr hidden clearfix">
	            <div class="ly-button-w fr">
	                <a class="button btn-sel fl" href="javascript:void(0);">待审批</a>
	                <a class="button fl" href="javascript:goAlreadyApproval();">已审批</a>
	            </div>
	        </div>
		</div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/leaveApply" styleId="leaveApplyForm" method="post" onsubmit="return false">
			<input name="method" id="method" type="hidden" value="findPageList" />
			<div class="public-main-input ly-col-1 hidden abs">
			<div class="ly-input-w" style="margin-top:30px;margin-bottom:-10px">
				<div class="ly-row clearfix">
					 <div class="ly-col fl">
	                       <div class="label block fl hidden">申请人：</div>
	                       <div class="input block fl hidden">
	                       		<div class="ly-sel-w">
									<select style="width:80%" id="leavePerson" name="query.leavePerson" >
										<option value="-1">请选择</option>
										<c:forEach items="${superviseOptions }" var="row">
											<option  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
										</c:forEach>
									</select>
								</div>
	                       </div>
	                   </div>
					<div class="ly-col fl">
                        <div class="label block fl hidden">申请日期：</div>
                        <div class="input block fl hidden">
                        	<form:calendar property="query.applyTime" styleId="applyTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
                        </div>
                    </div>
                    <div class="ly-col fl">
                       <div class="label block fl hidden">经销商：</div>
                       <div class="input block fl hidden">
                       		<div class="ly-sel-w">
								<select style="width:80%" id="dealerId" name="query.dealerId" >
									<option value="-1">请选择</option>
									<c:forEach items="${dealers }" var="row">
										<option  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
									</c:forEach>
								</select>
							</div>
                       </div>
	                </div>
                    <div class="ly-col fl">
                       <div class="label block fl hidden">品牌：</div>
                       <div class="input block fl hidden">
                       		<div class="ly-sel-w">
	                      	 	<select style="width:80%" id="brandId" name="query.brandId" >
									<option value="-1">请选择</option>
									<c:forEach items="${brands }" var="row">
										<option  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
									</c:forEach>
								</select>
							</div>
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
								<th class="t-th">下一审批人</th>
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
									<td class="t-td"><select:nextApprovalName roleId="${row.nextApproval }"></select:nextApprovalName></td>
									<td class="t-td"><select:user userid="${row.createUser}"/></td>
									<td class="t-td"><select:timestamp timestamp="${row.createTime}" idtype="ss"/></td>
									<td class="t-td"><select:user userid="${row.lastModifiedUser}"/></td>
									<td class="t-td"><select:timestamp timestamp="${row.lastModifiedTime}" idtype="ss"/></td>
									<td class="t-td">
										<a href="javascript:detail('<c:out value="${row.id }"/>')">详情</a>
										<c:if test="${row.approvalState==0 && row.status==0  && leaveApplyForm.query.currentRole == 10}">
											<a href="javascript:doSubmit('<c:out value="${row.id }"/>')">提交</a>
											<a href="javascript:doUpd('<c:out value="${row.id }"/>')">更新</a>
											<a href="javascript:doDel('<c:out value="${row.id }"/>')">删除</a>
										</c:if>
										<c:if test="${leaveApplyForm.query.currentRole == 14 && row.approvalState==3 }">
											<a href="javascript:approval('<c:out value="${row.id }"/>')">审批</a>
										</c:if>
										<c:if test="${leaveApplyForm.query.currentRole == 16 && row.approvalState==3 }">
                                            <a href="javascript:approval('<c:out value="${row.id }"/>')">审批</a>
                                        </c:if>
										<c:if test="${leaveApplyForm.query.currentRole == 8 && row.approvalState==3 }">
											<a href="javascript:approval('<c:out value="${row.id }"/>')">审批</a>
										</c:if>
										<c:if test="${leaveApplyForm.query.currentRole == 9 && row.approvalState==3 }">
											<a href="javascript:approval('<c:out value="${row.id }"/>')">审批</a>
										</c:if>
										<%-- <c:if test="${leaveApplyForm.query.currentRole == 80 && row.approvalState==3 }">
											<a href="javascript:approval('<c:out value="${row.id }"/>')">审批</a>
										</c:if> --%>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
			<div class="public-main-footer hidden abs">
				<c:if test="${leaveApplyForm.query.currentRole == sc || leaveApplyForm.query.currentRole == 10}">
					<a href="javascript:goAdd();" class="button btn-add fl">新增</a>
				</c:if>
				<div class="public-main-footer-pagin fr">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="leaveApplyList" action="/leaveApply.do?method=findPageList" />
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