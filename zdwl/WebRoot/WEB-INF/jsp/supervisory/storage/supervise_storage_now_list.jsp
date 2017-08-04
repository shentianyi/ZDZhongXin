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
function doLoad(){
	
}

//执行查询操作
function doQuery(){
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	getElement("superviseImportquery.dealername").value="";
	getElement("superviseImportquery.bankname").value="";
	getElement("superviseImportquery.draft_num").value="";
	getElement("superviseImportquery.brandid").value=-1;
	getElement("superviseImportquery.vin").value="";
	$("#superviseImportquery\\.draft_num").combobox('clear');
}

$(function(){
	var msg ="<c:out value='${message}'/>";
	if(msg){
		alert(msg);
	}
	
	$("#superviseImportquery\\.draft_num").combobox({
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
});
/* 需求32修改下拉框为easyUI下拉筛选控件 */
$(function(){
	$("#superviseImportquery\\.brandid").combobox({
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
});

//已审批列表
function goOld(){
	location.href="<url:context/>/superviseStorage.do?method=superviseStorageList";
}
</script>
</head>
<body class="h-100 public" onLoad="doLoad()">
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">监管物管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">监管物入库</a>
         </span>
</div>
<div class="public-bar hidden">
	<div class="ly-contai clearfix">
        <div class="public-bar-tab fr hidden clearfix">
            <div class="ly-button-w fr">
                <a class="button fl" href="javascript:goOld();">监管物入库</a>
                <a class="button btn-sel fl" href="javascript:void(0);">监管物入库申请中</a>
            </div>
        </div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/superviseStorage" styleId="superviseImportForm" method="post" onsubmit="return false">
			<input name="method" id="method" type="hidden" value="superviseStorageNowList"/>
			<input name="superviseImportquery.state" id="superviseImportquery.state" type="hidden" value="1"/>
			<input type="hidden" id="ids" name="ids" />
			<div class="public-main-input ly-col-2 hidden abs">
				<div class="ly-input-w" style="margin-top:30px;margin-bottom:-10px">
					<div class="ly-row clearfix">
						<div class="ly-col fl">
	                        <div class="label block fl hidden">经销商：</div>
	                        <div class="input block fl hidden">
	                        	<html:text styleClass="ly-bor-none" property="superviseImportquery.dealername" styleId="superviseImportquery.dealername"/>
	                        </div>
	                    </div>
	                    <div class="ly-col fl">
	                        <div class="label block fl hidden">金融机构：</div>
	                        <div class="input block fl hidden">
	                        	<html:text styleClass="ly-bor-none" property="superviseImportquery.bankname" styleId="superviseImportquery.bankname"/>
	                        </div>
	                    </div>
	                    <div class="ly-col fl">
	                        <div class="label block fl hidden">票号：</div>
	                        <div class="input block fl hidden">
	                        	<div class="ly-sel-w">
	                        		<select id="superviseImportquery.draft_num" name="superviseImportquery.draft_num" style="width:85%;">
							  			<option value="">请选择...</option>
							  			<c:forEach items="${draftOptions}" var="row">
							  				<option <c:if test="${row.label==superviseImportForm.superviseImportquery.draft_num }">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
							  			</c:forEach>
							  		</select>
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
	                        <div class="label block fl hidden">品牌：</div>
	                        <div class="input block fl hidden add_easyui">
	                           <!--  <form:select styleClass="ly-bor-none" property="superviseImportquery.brandid" styleId="superviseImportquery.brandid">
									<html:option value="-1">请选择</html:option>
									<html:optionsCollection name="brandOptions" label="label" value="value" />
								</form:select> -->
								<select id="superviseImportquery.brandid" name="superviseImportquery.brandid" style="width:85%;">
							  			<option value="-1">请选择...</option>
							  			<c:forEach items="${brandOptions}" var="row">
							  				<option <c:if test="${row.value==superviseImportForm.superviseImportquery.brandid }">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
							  			</c:forEach>
							  		</select>
	                        </div>
	                    </div>
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
	            </div>
			</div>
			<div class="public-main-table hidden abs">
				<div class="ly-cont">
					<table class="t-table" border="0" cellspacing="0" cellpadding="0">
						<thead class="t-thead">
							<tr class="t-tr">
								<th class="t-th">序号</th>
								<th class="t-th">钥匙数</th>
								<th class="t-th">票号</th>
								<th class="t-th">经销商</th>
								<th class="t-th">金融机构</th>
								<th class="t-th">品牌</th>
								<th class="t-th">合格证发证日期</th>
								<th class="t-th">合格证号</th>
								<th class="t-th">车辆型号</th>
								<th class="t-th">车身结构</th>
								<th class="t-th">排量</th>
								<th class="t-th">颜色</th>
								<th class="t-th">发动机号</th>
								<th class="t-th">车架号</th>
								<th class="t-th">钥匙号</th>
								<th class="t-th">金额</th>
								<th class="t-th">合格证接收时间</th>
								<th class="t-th">创建人</th>
								<th class="t-th">创建时间</th>
								<th class="t-th">修改人</th>
								<th class="t-th">修改时间</th>
							</tr>
						</thead>
						<tbody class="t-tbody hidden">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}"/></td>
									<td class="t-td"><c:out value="${row.key_amount}" /></td>
									<td class="t-td">
										<c:out value="${row.draft_num}" />
										<input type="hidden" id="<c:out value='${row.id}'/>_cn" name="<c:out value='${row.id}'/>_cn" value="<c:out value="${row.draft_num}"/>" />
										<input type="hidden" id="<c:out value='${row.id}'/>_vin" name="<c:out value='${row.id}'/>_vin" value="<c:out value="${row.vin}"/>" />
									</td>
									<%-- <td class="t-td"><select:dealer sid="${row.id}" dn="${row.draft_num}" idtype="jxs"/></td>
									<td class="t-td"><select:dealer sid="${row.id}" dn="${row.draft_num}" idtype="jrjg"/></td>
									<td class="t-td"><select:superviseImport sid="${row.id}" idtype="brand"/></td> --%>
									<td class="t-td"><c:out value="${row.dealerName}" /></td>
									<td class="t-td"><c:out value="${row.bankFullName}" /></td>
									<td class="t-td"><c:out value="${row.brandName}" /></td>
									<td class="t-td"><select:timestamp timestamp="${row.certificate_date}" idtype="date"/></td>
									<td class="t-td"><c:out value="${row.certificate_num}" /></td>
									<td class="t-td"><c:out value="${row.car_model}" /></td>
									<td class="t-td"><c:out value="${row.car_structure}" /></td>
									<td class="t-td"><c:out value="${row.displacement}" /></td>
									<td class="t-td"><c:out value="${row.color}" /></td>
									<td class="t-td"><c:out value="${row.engine_num}" /></td>
									<td class="t-td"><c:out value="${row.vin}" /></td>
									<td class="t-td"><c:out value="${row.key_num}" /></td>
									<td class="t-td"><c:out value="${row.money}" /></td>
									<td class="t-td"><select:timestamp timestamp="${row.certificate_intime}" idtype="date"/></td>
									<td class="t-td"><c:out value="${row.createUserName }"/></td>
									<td class="t-td"><select:timestamp timestamp="${row.createdate}" idtype="date"/></td>
									<td class="t-td"><c:out value="${row.updUserName }"/></td>
									<td class="t-td"><select:timestamp timestamp="${row.upddate}" idtype="date"/></td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
			<div class="public-main-footer hidden abs">
				<div class="public-main-footer-pagin fr">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="superviseStorageNowList" action="/superviseStorage.do?method=superviseStorageNowList"/>
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