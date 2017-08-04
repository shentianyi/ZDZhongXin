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
<%@ page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>

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
//执行查询操作
function doQuery(){
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("superviseImportquery.dealername").value="";
	getElement("superviseImportquery.brandid").value=-1;
	getElement("superviseImportquery.draft_num").value="";
	getElement("superviseImportquery.vin").value="";
	
}

function checkAll(name) { 
	var el = document.getElementsByTagName('input'); 
	var len = el.length; 
	for(var i=0; i<len; i++) { 
		if((el[i].type=="checkbox") && (el[i].name==name)) { 
			el[i].checked = true; 
		} 
	} 
} 
function clearAll(name) { 
	var el = document.getElementsByTagName('input'); 
	var len = el.length; 
	for(var i=0; i<len; i++) { 
		if((el[i].type=="checkbox") && (el[i].name==name)) { 
			el[i].checked = false; 
		} 
	} 
}
function doSave(){
	var len =$("[name=carIds]:checked").length;
	if(len<=0){
		alert("请选择车辆");
		return false;
	}
	
	if(confirm("是否确认审核通过")){
		//检查是否选择了要新增的资源
		if(hasCheckedItem("carIds")){
			var carIds = document.getElementsByName("carIds");
			var ids = "";
			
			for(var i = 0;i < carIds.length; i++){
				if(carIds[i].checked){
					ids = ids + carIds[i].value + ",";
				}
			}
			ids = ids.substring(0,ids.length - 1);
			document.getElementById("ids").value=ids;
			getElement("method").value="updSuperviseOuts";
			document.forms[0].submit();
		} else{
			showMessage("请选择要出库的监管物!");
		}
	}
}
function doSaveno(){
	var len =$("[name=carIds]:checked").length;
	if(len<=0){
		alert("请选择车辆");
		return false;
	}
	
	if(confirm("是否确认审核不通过")){
		//检查是否选择了要新增的资源
		if(hasCheckedItem("carIds")){
			var carIds = document.getElementsByName("carIds");
			var ids = "";
			
			for(var i = 0;i < carIds.length; i++){
				if(carIds[i].checked){
					ids = ids + carIds[i].value + ",";
				}
			}
			ids = ids.substring(0,ids.length - 1);
			document.getElementById("ids").value=ids;
			getElement("method").value="updSuperviseOutsno";
			document.forms[0].submit();
		} else{
			showMessage("请选择要出库的监管物!");
		}
	}
}
$(function(){
    restrict();
});
function restrict(){
    var crole = $("#crole").val();
    if(crole == 30){
        $(".yc").hide();
    }
}
</script>
</head>
<body class="h-100 public">
<input type="hidden" value="<%= UserSessionUtil.getUserSession(request).getUser().getClient_type()%>" id="crole" />
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">监管物审批</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">监管物出库审批</a>
         </span>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/carOperation" styleId="carOperationForm" method="post" onsubmit="return false">
			<input name="method" id="method" type="hidden" value="outList"/>
			<input type="hidden" id="ids" name="ids" />
			<div class="public-main-input ly-col-2   abs"style="margin-top:-10px;">
				<div class="ly-input-w">
					<div class="ly-row clearfix">
						<div class="ly-col fl">
	                        <div class="label block fl hidden">经销商：</div>
	                        <div class="input block fl hidden">
	                        	<html:text styleClass="ly-bor-none" property="superviseImportquery.dealername" styleId="superviseImportquery.dealername"/>
	                        </div>
	                    </div>
	                    <div class="ly-col fl">
	                        <div class="label block fl hidden">品牌：</div>
	                        <div class="input block fl hidden">
	                        	<form:select styleClass="ly-bor-none" property="superviseImportquery.brandid" styleId="superviseImportquery.brandid">
									<html:option value="-1">请选择</html:option>
									<html:optionsCollection name="brandOptions" label="label" value="value" />
								</form:select>
	                        </div>
	                    </div>
	                    <div class="ly-col fl">
	                        <div class="label block fl hidden">票号：</div>
	                        <div class="input block fl hidden">
	                        	<div class="ly-sel-w">
	                        		<html:text styleClass="ly-bor-none" property="superviseImportquery.draft_num" styleId="superviseImportquery.draft_num"/>
	                        	</div>
	                        </div>
	                    </div>
	                    <div class="ly-col fl">
	                        <div class="label block fl hidden">车架号：</div>
	                        <div class="input block fl hidden">
	                        	<html:text styleClass="ly-bor-none" property="superviseImportquery.vin" styleId="superviseImportquery.vin"/>
	                        </div>
	                    </div>
					</div>
					<div class="ly-row clearfix">
	                    <div class="ly-col fl">
	                        <div class="label block fl hidden"></div>
	                        <div class="input block fl hidden"></div>
	                    </div>
	                    <div class="ly-col fl">
	                        <div class="label block fl hidden"></div>
	                        <div class="input block fl hidden"></div>
	                    </div>
	                    <div class="ly-col fl">
	                        <div class="label block fl hidden"></div>
	                        <div class="input block fl hidden"></div>
	                    </div>
					</div>
				</div>
				<div class="ly-button-w">
	                <a href="javascript:doQuery();" class="button btn-query">查询</a>
	                <a href="javascript:doClear();" class="button btn-reset">重置</a>
	                <c:if test="${list!=null&&summary!=null&&summary.count!=0 }">
						<span style="position: absolute;top:65%;left:58%;">
						台数:<c:out value="${summary.count }"/>
						&nbsp;&nbsp;&nbsp;车价总金额：<fmt:formatNumber value="${summary.carMoney }" pattern="#.##" minFractionDigits="2" /> 
						&nbsp;&nbsp;&nbsp;总回款金额:<fmt:formatNumber value="${summary.carPaymentAmount}" pattern="#.##" minFractionDigits="2" />
						</span>
					</c:if>
	            </div>
			</div>
			<div class="public-main-table hidden abs">
				<div class="ly-cont">
					<table class="t-table" border="0" cellspacing="0" cellpadding="0">
						<thead class="t-thead">
							<tr class="t-tr">
								<th class="t-th">
									<label for="checkAllValue"></label>
			  						<input name="test" value="" onclick="if(this.checked==true) { checkAll('carIds'); } else { clearAll('carIds'); }" type="checkbox">
								</th>
								<th class="t-th">序号</th>
								<th class="t-th">经销商</th>
								<th class="t-th">品牌</th>
								<th class="t-th">票号</th>
								<th class="t-th">车辆型号</th>
								<th class="t-th">颜色</th>
								<th class="t-th">车架号</th>
								<th class="t-th">发动机号</th>
								<th class="t-th">合格证号</th>
								<th class="t-th">钥匙(数)</th>
								<th class="t-th">单价(元)</th>
								<th class="t-th">入库时间</th>
								<th class="t-th">提交时间</th>
								<th class="t-th">回款金额</th>
							</tr> 
						</thead>
						<tbody class="t-tbody hidden">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><input type="checkbox" id="carIds" name="carIds" value="<c:out value='${row.id}'/>"/></td>
									<td class="t-td"><c:out value="${index+1}"/></td>
									<td class="t-td"><c:out value="${row.dealerName}" /></td>
									<td class="t-td"><c:out value="${row.brandName}" /></td>
									<td class="t-td">
										<c:out value="${row.draft_num}" />
										<input type="hidden" id="<c:out value='${row.id}'/>_cn" name="<c:out value='${row.id}'/>_cn" value="<c:out value="${row.draft_num}"/>" />
										<input type="hidden" id="<c:out value='${row.id}'/>_vin" name="<c:out value='${row.id}'/>_vin" value="<c:out value="${row.vin}"/>" />
									</td>
									<td class="t-td"><c:out value="${row.car_model}" /></td>
									<td class="t-td"><c:out value="${row.color}" /></td>
									<td class="t-td"><c:out value="${row.vin}" /></td>
									<td class="t-td"><c:out value="${row.engine_num}" /></td>
									<td class="t-td"><c:out value="${row.certificate_num}" /></td>
									<td class="t-td"><c:out value="${row.key_amount}" /></td>
									<td class="t-td"><c:out value="${row.money}" /></td>
									<td class="t-td"><select:timestamp timestamp="${row.storagetime}" idtype="date"/></td>
									<td class="t-td"><select:bankaprove sid="${row.id}" idtype="outtime"/></td>
									<td class="t-td"><c:out value="${row.payment_amount}" /></td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
			<div class="public-main-footer hidden abs">
				<a href="javascript:doSave();" class="button btn-add fl yc">通过</a>
				<a href="javascript:doSaveno();" class="button btn-add fl yc">不通过</a>
				<div class="public-main-footer-pagin fr">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="outList" action="/carOperation.do?method=outList"/>
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


