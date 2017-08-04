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

	//进入新增页面
	function goAdd() {
		location="<url:context/>/duedate.do?method=addDuedateEntry";
	}

	//执行删除操作
	function doDel(id) {
		if (confirm("删除后数据不可恢复\n是否继续？")) {
			location = "<url:context/>/duedate.do?method=delDuedate&duedate.id=" + id;
		}
	}
	
	//已审批列表
	function goAlreadyApproval(){
		location.href="<url:context/>/duedate.do?method=findList&duedatequery.pageType=2";
	}
	
	function doSubmit(id){
		if(confirm("确定要提交吗？")){
			location.href="<url:context/>/duedate.do?method=submit&duedate.id="+id;
		}
	}
	
	function approval(id){
		location.href="<url:context/>/duedate.do?method=preApproval&duedate.id="+id;
	}
	
	function detail(id){
		location.href="<url:context/>/duedate.do?method=detailPage&duedate.id="+id;
	}
	function goCheckDueDate(){
		location="<url:context/>/duedate.do?method=benList";
	}
	function goCheckTwoDate(){
		location="<url:context/>/twodate.do?method=twoList";
	}
	//执行查询操作
	function doQuery(){
		document.forms[0].submit();
		//对表单内容进行验证，包括对输入类型等限制方式
		/* if(validateMain("duedateForm")){
			//为时间类型输入项补齐时间戳
			setTimeSuffix();
			//提交表单
			document.forms[0].submit();
		} */
	}

	//执行表单清空操作
	function doClear(){
		var arr = document.getElementsByName("duedatequery.type");
		for(var i=0;i<arr.length;i++){
		        if(arr[i].checked){    
		        	arr[i].checked=false;
		        }
		}
		$("#dealerName").val("");
		$("#bankName").val("");
		$("#brandName").val("");
	}
</script>
</head>
<body class="h-100 public">
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">监管物管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">日查库管理</a>
         </span>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<c:set var="supervisory"><%=RoleConstants.SUPERVISORY.getCode() %></c:set>
		<html:form action="/duedate" styleId="duedateForm" method="post" onsubmit="return false">
			<input name="method" id="method" type="hidden" value="findList" />
			<div class="public-main-input ly-col-1 hidden abs">
				<div class="ly-input-w">
					<div class="ly-row clearfix">
						<div class="ly-col fl">
	                        <div class="label block fl hidden">文件类型：</div>
	                        <div class="input block fl hidden">
	                        	<form:radios  property="duedatequery.type" collection="dateType" styleId="duedatequery.type"/>
							</div>
	                    </div>
	                    <div class="ly-col fl">
	                        <div class="label block fl hidden">经销商：</div>
	                        <div class="input block fl hidden">
	                        	<html:text styleClass="ly-bor-none" property="duedatequery.dealerName" styleId="dealerName"/>
							</div>
	                    </div>
	                    <div class="ly-col fl">
	                        <div class="label block fl hidden">金融机构：</div>
	                        <div class="input block fl hidden">
	                        	<html:text styleClass="ly-bor-none" property="duedatequery.bankName" styleId="bankName"/>
							</div>
	                    </div>
	                    <div class="ly-col fl">
	                        <div class="label block fl hidden">品牌：</div>
	                        <div class="input block fl hidden">
	         					<html:text styleClass="ly-bor-none" property="duedatequery.brandName" styleId="brandName"/>
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
								<th class="t-th">金融机构</th>
								<th class="t-th">品牌</th>
								<th class="t-th">类型</th>
								<th class="t-th">创建人</th>
								<th class="t-th">创建时间</th>
						      	<th class="t-th">操作</th>
							</tr>
						</thead>
						<tbody class="t-tbody hidden">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}" /></td>
									<td class="t-td"><c:out value="${row.dealerName}" /></td>
									<td class="t-td"><c:out value="${row.bankName}" /></td>
									<td class="t-td"><c:out value="${row.brandName}" /></td>
									<td class="t-td">
										<c:choose>  
										   <c:when test="${row.type==1}">本库日查库</c:when>  
										   <c:when test="${row.type==2}">二网日查库  </c:when>  
										</c:choose>  
									</td>
									<td class="t-td"><select:user userid="${row.createuserid}"/></td>
									<td class="t-td"><select:timestamp timestamp="${row.createdate}" idtype="date"/></td>
									<td class="t-td">
										<a href="javascript:detail('<c:out value="${row.id }"/>')">详情</a>
										<c:if test="${duedateForm.duedatequery.currRole == supervisory }">
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
				<c:if test="${duedateForm.duedatequery.currRole == supervisory }">
					<a href="javascript:goCheckDueDate();" class="button btn-add fl">查看当前本库车辆</a>
					<a href="javascript:goCheckTwoDate();" class="button btn-add fl">查看当前二网车辆</a>
					<a href="javascript:goAdd();" class="button btn-add fl">新增</a>
				</c:if>
				<div class="public-main-footer-pagin fr">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="findList" action="/duedate.do?method=findList" />
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