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
<script type="text/javascript" src="js/jquery.divscroll.js"></script>
<script>
//页面初始化函数
function doLoad(str){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	if("" != str){
		strs = str.split(",");
	}
	if(!isNull(strs) && strs.length !=0){
		$.each(strs, function(i, item) {
			$('#'+item).datebox({    
				editable:false   
			});
		});
	}
}

//进入新增页面
function goAdd(){
	location="<url:context/>/draft.do?method=addDraftEntry";
}
function goImport(){
	location="<url:context/>/draft.do?method=importExcelEntry";
}
function goExt(){
	$("#method").val("extExcel");
	document.forms[0].submit();
}

//进入修改页面
function goUpd(id){
	location = "<url:context/>/draft.do?method=updDraftEntry&draft.id="+id;
}

//执行删除操作
function doDel(id){
	if(confirm("删除后数据不可恢复\n是否继续？")){
		location="<url:context/>/draft.do?method=delDraft&draft.id="+id;
	}
}

//执行查询操作
function doQuery(){
	$("#method").val("draftList");
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("draftquery.distribname").value="";
	getElement("draftquery.draft_num").value="";
	getElement("draftquery.bankName").value="";
	getElement("draftquery.brand").value="";
	getElement("draftquery.state").value=-1;
}

</script>
</head>
<body class="h-100 public" onLoad="doLoad('datebeginDate,dateendDate,duebeginDate,dueendDate')">
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">监管物管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">汇票信息</a>
         </span>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/draft" styleId="draftForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="draftList"/>
		<div class="public-main-input ly-col-3 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">	
                        <div class="label block fl hidden">经销商：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="draftquery.distribname" styleId="draftquery.distribname"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">金融机构：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="draftquery.bankName" styleId="draftquery.bankName"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">品牌：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="draftquery.brand" styleId="draftquery.brand"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">汇票号码：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="draftquery.draft_num" styleId="draftquery.draft_num"/>
                        </div>
                    </div>
            	</div>
            	
            	<div class="ly-row clearfix">
            		<div class="ly-col fl">
                        <div class="label block fl hidden">状态：</div>
                        <div class="input block fl hidden">
							<form:select styleClass="ly-bor-none" property="draftquery.state" styleId="draftquery.state">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="isClearTicket" label="label" value="value" />
							</form:select>
                        </div>
                    </div>
				
					<div class="ly-col fl" style="width:66%;">
                       	<div class="label block fl hidden" style="width:20%;margin-left: -61px;">开票日：</div>
                       	<div class="input block fl hidden" style="width:80%;padding-left:14px;">
                        	<input style="width:24%;" class="date" id="datebeginDate" name="draftquery.billing_datebeginDate" type="text" value='<fmt:formatDate value="${draftForm.draftquery.billing_datebeginDate }" pattern="yyyy-MM-dd"/>'/>
                        	<span>至</span>
                        	<input style="width:24%;" class="datee" id="dateendDate" name="draftquery.billing_dateendDate" type="text" value='<fmt:formatDate value="${draftForm.draftquery.billing_dateendDate }" pattern="yyyy-MM-dd"/>'/>
                       	</div>
                    </div>
                </div>
            	<div class="ly-row clearfix">
					<div class="ly-col fl" style="width:66%;">
                       	<div class="label block fl hidden" style="width:20%;margin-left: -61px;">到期日：</div>
                       	<div class="input block fl hidden" style="width:80%;padding-left:14px;">
                        	<input style="width:24%;" class="date" id="duebeginDate" name="draftquery.due_datebegin" type="text" value='<fmt:formatDate value="${draftForm.draftquery.due_datebegin }" pattern="yyyy-MM-dd"/>'/>
                        	<span>至</span>
                        	<input style="width:24%;" class="datee" id="dueendDate" name="draftquery.due_dateend" type="text" value='<fmt:formatDate value="${draftForm.draftquery.due_dateend }" pattern="yyyy-MM-dd"/>'/>
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
							<th class="t-th">质押协议号</th>
							<th class="t-th">保证金账号</th>
							<th class="t-th">保证金比例(%)</th>
							<th class="t-th">经销商</th>
						    <th class="t-th">金融机构</th>
						    <th class="t-th">品牌</th>
							<th class="t-th">汇票号码</th>
							<th class="t-th">开票日</th>
							<th class="t-th">到期日</th>
							<th class="t-th">开票金额(元)</th>
							<th class="t-th">已押车金额</th>
							<th class="t-th">库存台数</th>
							<th class="t-th">库存金额</th>
							<th class="t-th">敞口金额</th>
							<th class="t-th">回款金额</th>
							<th class="t-th">未押车金额</th>
							<th class="t-th">状态</th>
							<th class="t-th">创建人</th>
							<th class="t-th">创建时间</th>
							<th class="t-th">修改人</th>
							<th class="t-th">修改时间</th>
							<c:if test="${draftForm.draftquery.currRole == 14 || draftForm.draftquery.currRole == 16}">
					      		<th class="t-th">操作</th>
					      	</c:if>	
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td"><c:out value="${index+1}"/></td>
								<td class="t-td"><c:out value="${row.agreement}" /></td>
								<td class="t-td"><c:out value="${row.bail_num}" /></td>
								<td class="t-td"><c:out value="${row.bailscale}" /></td>
								<td class="t-td"><c:out value="${row.dealerName }"/></td>
								<td class="t-td"><c:out value="${row.bankName }"/></td>
								<td class="t-td"><c:out value="${row.brandName }"/></td>
								<td class="t-td"><c:out value="${row.draft_num}" /></td>
								<td class="t-td"><select:timestamp timestamp="${row.billing_date}" idtype="date"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.due_date}" idtype="date"/></td>
								<td class="t-td"><c:out value="${row.billing_money}"/></td>
								<td class="t-td"><fmt:formatNumber value="${row.yycMoney}" pattern="0.00"/></td>
								<td class="t-td"><c:out value="${row.kcts}"/></td>
								<td class="t-td">
									<fmt:formatNumber value="${row.kcMoney}" pattern="0.00"/>
								</td>
								<td class="t-td">
									<fmt:formatNumber value="${row.ckMoney}" pattern="0.00"/>
								</td>
								<td class="t-td">
									<fmt:formatNumber value="${row.hkMoney}" pattern="0.00"/>
								</td>
								<td class="t-td">
									<fmt:formatNumber value="${row.wycMoney}" pattern="0.00"/>
								</td>
								<td class="t-td">
									<c:if test="${row.state == 1}">
										清票
									</c:if>
									<c:if test="${row.state == 2}">
										未清票
									</c:if>
								</td>
								<td class="t-td"><select:user userid="${row.createuserid}"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.createdate}" idtype="date"/></td>
								<td class="t-td"><select:user userid="${row.upduserid}"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.upddate}" idtype="date"/></td>
								<%-- <c:if test="${draftForm.draftquery.currRole == 14 || draftForm.draftquery.currRole == 16 || draftForm.draftquery.currRole !=80}"> --%>
								<c:if test="${draftForm.draftquery.currRole == 14 || draftForm.draftquery.currRole == 16}">
									<td class="t-td">
										<a href="javascript:goUpd('<c:out value='${row.id}'/>');" >修改</a>&nbsp;
										<a href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>
									</td>
								</c:if>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		</div>
		<div class="public-main-footer hidden abs">
			<c:if test="${draftForm.draftquery.currRole == 14 || draftForm.draftquery.currRole == 16 }">
				<a href="javascript:goAdd();" class="button btn-add fl">新增</a>
				<a href="javascript:goImport();" class="button btn-add fl">导入汇票</a>
			</c:if>
			<c:if test="${draftForm.draftquery.currRole !=80}">
			<a href="javascript:goExt();" class="button btn-add fl">导出汇票</a>
			</c:if>
			<div class="public-main-footer-pagin fr">
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="draftList" action="/draft.do?method=draftList"/>
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