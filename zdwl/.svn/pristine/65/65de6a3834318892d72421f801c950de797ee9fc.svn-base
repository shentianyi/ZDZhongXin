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
		location.href="<url:context/>/ledger/extraworkApply.do?method=detailPage&extraworkApply.id="+id;
	}
	
	//执行查询操作
	function doQuery(){
		document.forms[0].submit();
	}

	//执行表单清空操作
	function doClear(){
		$("#name").val("");
		$("#staffNo").val("");
		$("#brandId").val("-1");
		$("#bankId").val("-1");
		$("#province").val("");
		$("#city").val("");
		$("#extraWorkDays").val("");
		$("#startTime").val("");
		$("#endTime").val("");
		$("input[type='radio']").removeAttr('checked');
	}
	
	function init(){
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


<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/ledger/extraworkApply" styleId="extraworkApplyForm" method="post" onsubmit="return false">
			<input name="method" id="method" type="hidden" value="findPageList" />
			<div class="public-main-input ly-col-3 hidden abs">
			<div class="ly-input-w" style="margin-top:30px;margin-bottom:-10px">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">员工姓名：</div>
                        <div class="input block fl hidden">
                        	 <html:text styleClass="ly-bor-none" property="query.name" styleId="name"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">员工工号：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="query.staffNo" styleId="staffNo"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
	                    <div class="label block fl hidden">所在省：</div>
	                    <div class="input block fl hidden">
	                    	<html:text styleClass="ly-bor-none" property="query.province" styleId="province"/>
	                    </div>
	                </div>
	                <div class="ly-col fl">
	                    <div class="label block fl hidden">所在市：</div>
	                    <div class="input block fl hidden">
	                    	<html:text styleClass="ly-bor-none" property="query.city" styleId="city"/>
	                    </div>
	                </div>
                    
				</div>
				 <div class="ly-row clearfix">
					<div class="ly-col fl">
	                    <div class="label block fl hidden">开始时间：</div>
	                    <div class="input block fl hidden">
	                    	<form:calendar  style="width:70%" property="query.startTime" styleId="startTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
	                    </div>
	                </div>
	                <div class="ly-col fl">
	                    <div class="label block fl hidden">结束时间：</div>
	                    <div class="input block fl hidden">
	                    	<form:calendar style="width:70%" property="query.endTime" styleId="endTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
	                    </div>
	                </div>
	                <div class="ly-col fl">
	                    <div class="label block fl hidden">加班天数：</div>
	                    <div class="input block fl hidden">
	                    	<input type="text" id="extraWorkDays" name="query.extraWorkDays" class="easyui-numberbox" 
								data-options="min:0,precision:0"/>
	                    </div>
	                </div>
                </div> 
				<div class="ly-row clearfix">
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
                   <div class="ly-col fl">
	                   <div class="label block fl hidden">品牌：</div>
	                   <div class="input block fl hidden">
	                  	 	<select style="width:80%" id="brandId" name="query.brandId" >
								<option value="-1">请选择</option>
								<c:forEach items="${brands }" var="row">
									<option  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
								</c:forEach>
							</select>
	                   </div>
	               </div>
	                <div class="ly-col fl">
	                    <div class="label block fl hidden">审批状态：</div>
	                    <div class="input block fl hidden">
	                  <form:radios  property="query.approvalState" styleId="approvalState" collection="queryApprovalState"/>
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
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="extraworkApplyList" action="/ledger/extraworkApply.do?method=findPageList" />
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