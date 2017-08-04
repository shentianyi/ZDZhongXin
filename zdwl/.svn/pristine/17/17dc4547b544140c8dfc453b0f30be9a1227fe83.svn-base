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

	//执行删除操作
	function doDel(id) {
		if (confirm("删除后数据不可恢复\n是否继续？")) {
			location = "<url:context/>/extraworkApply.do?method=deleteExtraworkApply&extraworkApply.id=" + id;
		}
	}
	
	//待审批列表
	function goWaitApproval(){
		location.href="<url:context/>/extraworkApply.do?method=findPageList";
	}
	
	function doSubmit(id){
		if(confirm("确定要提交吗？")){
			location.href="<url:context/>/extraworkApply.do?method=submit&extraworkApply.id="+id;
		}
	}
	
	function approval(id){
		location.href="<url:context/>/extraworkApply.do?method=preApproval&extraworkApply.id="+id;
	}
	
	function detail(id){
		location.href="<url:context/>/extraworkApply.do?method=detailPage&extraworkApply.id="+id;
	}
	
	//进入修改页面
	function doUpd(id){
		location = "<url:context/>/extraworkApply.do?method=updateExtraworkApplyEntry&extraworkApply.id="+id;
	}
	//执行查询操作
	function doQuery(){
		document.forms[0].submit();
	}

	//执行表单清空操作
	function doClear(){
		$("#extraworkPerson").val("-1");
		$("#applyTime").val("");
		$("#dealerId").val("-1");
		$("#brandId").val("-1");
	}
	function init(){
		$("#extraworkPerson").combobox({
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
            <a class="crumbs-link" style="color:#929291;" href="#">加班申请</a>
         </span>
</div>
<div class="public-bar hidden">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
	        <div class="public-bar-tab fr hidden clearfix">
	            <div class="ly-button-w fr">
	                <a class="button fl" href="javascript:goWaitApproval();">待审批</a>
	                <a class="button btn-sel fl" href="javascript:void(0);">已审批</a>
	            </div>
	        </div>
		</div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/extraworkApply" styleId="extraworkApplyForm" method="post" onsubmit="return false">
			<input name="method" id="method" type="hidden" value="findPageList" />
			<input name="query.pageType" value="2" type="hidden"/>
			<div class="public-main-input ly-col-1 hidden abs">
			<div class="ly-input-w" style="margin-top:30px;margin-bottom:-10px">
				<div class="ly-row clearfix">
					 <div class="ly-col fl">
	                       <div class="label block fl hidden">申请人：</div>
	                       <div class="input block fl hidden">
	                       	<div class="ly-sel-w">
								<select style="width:80%" id="extraworkPerson" name="query.extraworkPerson" >
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
								<th class="t-th">省</th>
								<th class="t-th">市</th>
								<th class="t-th">经销商</th>
								<th class="t-th">金融机构</th>
								<th class="t-th">品牌</th>
								<th class="t-th">申请时间</th>
								<th class="t-th">加班天数</th>
								<th class="t-th">开始时间</th>
								<th class="t-th">结束时间</th>
								<!-- <th class="t-th">审批部门</th> -->
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
									<td class="t-td">&nbsp;<c:out value="${row.staffNo}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.name}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.province}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.city}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.dealerName}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.bankName}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.brandName}" /></td>
									<td class="t-td"><select:timestamp timestamp="${row.applyTime}" idtype="mm"/></td>
									<td class="t-td">&nbsp;<c:out value="${row.extraWorkDays}" /></td>
									<td class="t-td"><select:timestamp timestamp="${row.startTime}" idtype="mm"/></td>
									<td class="t-td"><select:timestamp timestamp="${row.endTime}" idtype="mm"/></td>
									<%-- <td class="t-td">
										<c:if test="${row.approvalDepartment==1 }">监管员管理部</c:if>
										<c:if test="${row.approvalDepartment==2 }">业务部</c:if>
										<c:if test="${row.approvalDepartment==3 }">风控部</c:if>
										<c:if test="${row.approvalDepartment==4 }">视频部</c:if>
										<c:if test="${row.approvalDepartment==5 }">市场部</c:if>
									</td> --%>
									<td class="t-td"><select:approvalState type="${row.approvalState }"></select:approvalState></td>
									<td class="t-td"><select:nextApprovalName roleId="${row.nextApproval }"></select:nextApprovalName></td>
									<td class="t-td"><select:user userid="${row.createUser}"/></td>
									<td class="t-td"><select:timestamp timestamp="${row.createTime}" idtype="ss"/></td>
									<td class="t-td"><select:user userid="${row.lastModifiedUser}"/></td>
									<td class="t-td"><select:timestamp timestamp="${row.lastModifiedTime}" idtype="ss"/></td>
									<td class="t-td">
										<a href="javascript:detail('<c:out value="${row.id }"/>')">详情</a>
										<c:if test="${row.approvalState==2 && row.status==0  && extraworkApplyForm.query.currentRole == 10}">
											<a href="javascript:doSubmit('<c:out value="${row.id }"/>')">提交</a>
											<a href="javascript:doUpd('<c:out value="${row.id }"/>')">更新</a>
											<a href="javascript:doDel('<c:out value="${row.id }"/>')">删除</a>
										</c:if>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
			<div class="public-main-footer hidden abs">
				<div class="public-main-footer-pagin fr">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="extraworkApplyList" action="/extraworkApply.do?method=findPageList" />
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