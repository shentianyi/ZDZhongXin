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
<script type="text/javascript" src="/js/jquery.divscroll.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	}

	//进入新增页面
	function goAdd() {
		location = "<url:context/>/supervisory/abnormal.do?method=preAdd";
	}

	//进入修改页面
	function goUpd(id) {
		location = "<url:context/>/supervisory/abnormal.do?method=preUpdate&abnormal.id=" + id;
	}

	//执行删除操作
	function doDel(id) {
		if (confirm("删除后数据不可恢复\n是否继续？")) {
			location = "<url:context/>/supervisory/abnormal.do?method=delete&abnormal.id=" + id;
		}
	}

	//执行查询操作
	function doQuery() {
		document.forms[0].submit();
	}

	//执行表单清空操作
	function doClear() {
		//清空资源名输入框
		getElement("query.dealerName").value = "";
		getElement("query.businessName").value = "";
		getElement("query.province").value = "";
		getElement("query.city").value = "";
	}
	
	//已审批列表
	function goAlreadyApproval(){
		location.href="<url:context/>/supervisory/abnormal.do?method=findList&query.pageType=2";
	}
	
	function doSubmit(id){
		if(confirm("确定要提交吗？")){
			location.href="<url:context/>/supervisory/abnormal.do?method=submit&abnormal.id="+id;
		}
	}
	
	function approval(id){
		location.href="<url:context/>/supervisory/abnormal.do?method=preApproval&abnormal.id="+id;
	}
	
	function detail(id){
		location.href="<url:context/>/supervisory/abnormal.do?method=detailPage&abnormal.id="+id;
	}
	function goImport(){
		location="<url:context/>/supervisory/abnormal.do?method=importExcelEntry";
	}
</script>

</head>
<body class="h-100 public">
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">监管物管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">异常数据管理</a>
         </span>
</div>
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
			
<div class="public-main abs">
	<div class="ly-contai rel">
		<c:set var="supervisory"><%=RoleConstants.SUPERVISORY.getCode() %></c:set>
		<html:form action="/supervisory/abnormal.do" styleId="abnormalForm" method="post" onsubmit="return false">
			<input name="method" id="method" type="hidden" value="findList" />
			<div class="public-main-input ly-col-1 hidden abs">
				<div class="ly-input-w" style="margin-top:30px;margin-bottom:-10px">
					<div class="ly-row clearfix">
						<div class="ly-col fl">
	                        <div class="label block fl hidden">经销商名称：</div>
	                        <div class="input block fl hidden">
	                        	<html:text styleClass="ly-bor-none" property="query.dealerName"styleId="query.dealerName" />
							</div>
	                    </div>
	                    <div class="ly-col fl">
	                    	<div class="label block fl hidden">业务专员：</div>
	                        <div class="input block fl hidden">
	                        	<html:text styleClass="ly-bor-none" property="query.businessName"styleId="query.businessName" />
							</div>
	                    </div>
	                    <div class="ly-col fl">
							 <div class="label block fl hidden">省：</div>
	                        <div class="input block fl hidden">
	                        	<html:text styleClass="ly-bor-none" property="query.province"styleId="query.province" />
							</div>
	                    </div>
	                     <div class="ly-col fl">
							 <div class="label block fl hidden">市：</div>
	                        <div class="input block fl hidden">
	                        	<html:text styleClass="ly-bor-none" property="query.city"styleId="query.city" />
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
					<table class="t-table" border="0" cellspacing="0" cellpadding="0" >
						<thead class="t-thead">
							<tr class="t-tr">
								<th class="t-th" rowspan="2">序号</th>
								<th class="t-th" rowspan="2">业务专员</th>
								<th class="t-th" rowspan="2">经销商全称</th>
								<th class="t-th" rowspan="2">融资机构</th>
								<th class="t-th" rowspan="2">省</th>
								<th class="t-th" rowspan="2">市</th>
								<th class="t-th" rowspan="2">总库存</th>
								<th class="t-th" rowspan="2">经营异常</th>
								<th class="t-th" rowspan="2">异常占比</th>
								<th class="t-th" colspan="3">私售</th>
								<th class="t-th" colspan="3">私移</th>
								<th class="t-th" colspan="3">销售未还款</th>
								<th class="t-th" colspan="2">开票30天未押满</th>
								<th class="t-th" colspan="2">汇票到期未按时清票</th>
								<th class="t-th" colspan="2">跟进结果</th>
								<th class="t-th" rowspan="2">备注</th>
								<th class="t-th" rowspan="2">审批状态</th>
								<th rowspan="2" align="center">操作</th>
							</tr>
							<tr class="t-tr">
								<th  class="t-th">台数</th>
								<th  class="t-th">金额</th>
								<th  class="t-th">持续时间（天）</th>
								<th  class="t-th">台数</th>
								<th  class="t-th">金额</th>
								<th  class="t-th">持续时间（天）</th>
								<th  class="t-th">台数</th>
								<th  class="t-th">金额</th>
								<th  class="t-th">持续时间（天）</th>
								<th  class="t-th">最早开票日</th>
								<th  class="t-th">未押满金额</th>
								<th  class="t-th">最早到期日</th>
								<th  class="t-th">未结清金额</th>
								<th  class="t-th">跟进日期</th>
								<th  class="t-th">进展</th>
							</tr>
						</thead>
						<tbody class="t-tbody hidden">
								<logic:iterate name="list" id="row" indexId="index">
									<tr class="listTr_a">
										<td class="t-td"><c:out value="${index+1}" /></td>
										<td class="t-td"><c:out value="${row.businessName }" /></td>
										<td class="t-td"><c:out value="${row.dealerName }" /></td>
										<td class="t-td"><c:out value="${row.bankName }" /></td>
										<td class="t-td"><c:out value="${row.province }" /></td>
										<td class="t-td"><c:out value="${row.city }" /></td>
										<td class="t-td"><c:out value="${row.totalStock }" /></td>
										<td class="t-td"><c:out value="${row.jyAnomalies }" /></td>
										<td class="t-td"><c:out value="${row.ycproportion }" /></td>
										<td class="t-td"><c:out value="${row.shCarNumber }" /></td>
										<td class="t-td"><c:out value="${row.shCarMoney }" /></td>
										<td class="t-td"><c:out value="${row.shContinuedDay }" /></td>
										<td class="t-td"><c:out value="${row.syCarNumber }" /></td>
										<td class="t-td"><c:out value="${row.syCarMoney }" /></td>
										<td class="t-td"><c:out value="${row.syContinuedDay }" /></td>
										<td class="t-td"><c:out value="${row.xsCarNumber }" /></td>
										<td class="t-td"><c:out value="${row.xsCarMoney }" /></td>
										<td class="t-td"><c:out value="${row.xsContinuedDay }" /></td>
										
										<td class="t-td"><select:timestamp timestamp="${row.earliestInvoice }" idtype="date"></select:timestamp></td>
										<td class="t-td"><c:out value="${row.amountnotfilled }" /></td>
										<td class="t-td"><select:timestamp timestamp="${row.earliestExpire }" idtype="date"></select:timestamp></td>
										<td class="t-td"><c:out value="${row.outstandingAmount }" /></td>
										<td class="t-td"><select:timestamp timestamp="${row.gjDate }" idtype="date"></select:timestamp></td>
										<td class="t-td"><c:out value="${row.progress }" /></td>
										<td class="t-td"><c:out value="${row.remark }" /></td>	
										<td class="t-td"><select:approvalState type="${row.approvalState }"></select:approvalState></td>
										<td class="t-td">
											<c:if test="${row.approvalState==0 }">
												<a href="javascript:doSubmit('<c:out value="${row.id }"/>')">提交</a>
												<a href="javascript:goUpd('<c:out value="${row.id }"/>')">更新</a>
												<a href="javascript:doDel('<c:out value="${row.id }"/>')">删除</a>
											</c:if>
											<c:if test="${abnormalForm.query.currRole != supervisory }">
												<a href="javascript:goUpd('<c:out value="${row.id }"/>')">审阅</a>
											</c:if>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
					</table>
				</div>
			</div>
			<div class="public-main-footer hidden abs">
				<c:if test="${abnormalForm.query.currRole == supervisory }">
					<tr>
						<td>
							<button class="formButton" onClick="goAdd()">新&nbsp;增</button>&nbsp;
							<!-- <button class="formButton" onClick="goImport()">导入质押监管异常数据</button> -->
						</td>
					</tr>
				</c:if>
				<div class="public-main-footer-pagin fr">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="list" action="/supervisory/abnormal.do?method=findList" />
				</div>
				<div id="message" class="message" style="display: none"></div>
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