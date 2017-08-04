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
<%@ page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>
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
<script type="text/javascript" src="/js/jquery.divscroll.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//执行查询操作
function doQuery(){
	document.forms[0].submit();
}

function goUse(fid){
	location = "<url:context/>/ledger/fixedAssetList.do?method=fixedAssetList&fid="+fid;
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("fixedAssetsquery.asset_num").value="";
	getElement("fixedAssetsquery.asset_type").value=-1;
	getElement("fixedAssetsquery.brand").value="";
	getElement("fixedAssetsquery.model").value="";
	getElement("fixedAssetsquery.factory_code").value="";
	getElement("fixedAssetsquery.purchase_date").value="";
	getElement("fixedAssetsquery.asset_state").value=-1;
	getElement("fixedAssetsquery.trade_name").value="";
	
}
function goExt(){
	location="<url:context/>/ledger/fixedAssets.do?method=extExcel";
}
/* 完成需求32 easyUI下拉筛选控件 */
$(function(){
	$("#fixedAssetsquery\\.asset_type").combobox({
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
/* 完成需求32 easyUI下拉筛选控件 */
$(function(){
	$("#fixedAssetsquery\\.asset_state").combobox({
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

$(function(){
//限制超级角色操作
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
<body class="h-100 public" onLoad="doLoad()">
<input type="hidden" value="<%= UserSessionUtil.getUserSession(request).getUser().getClient_type()%>" id="crole" />

<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">台账管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">设备管理台帐</a>
         </span>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/ledger/fixedAssets" styleId="fixedAssetsForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="fixedAssetsLedger"/>
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">资产编码：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="fixedAssetsquery.asset_num" styleId="fixedAssetsquery.asset_num"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">资产类别：</div>
                        <div class="input block fl hidden add_easyui">
							<!-- <form:select styleClass="ly-bor-none" property="fixedAssetsquery.asset_type" styleId="fixedAssetsquery.asset_type">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="AssetsTypeOptions" label="label" value="value" />
							</form:select> -->
							<select id="fixedAssetsquery.asset_type" style="width:100px;" name="fixedAssetsquery.asset_type" >
										<option value="-1">请选择</option>
										<c:forEach items="${AssetsTypeOptions}" var="row">
											<option <c:if test="${fixedAssetsForm.fixedAssetsquery.asset_type==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
										</c:forEach>
							</select>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">品牌：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="fixedAssetsquery.brand" styleId="fixedAssetsquery.brand"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">规格型号：</div>
                        <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="fixedAssetsquery.model" styleId="fixedAssetsquery.model"/>
                        </div>
                    </div>
				</div>
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">出厂编码：</div>
                        <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="fixedAssetsquery.factory_code" styleId="fixedAssetsquery.factory_code"/>
                        </div>
                    </div>
					<div class="ly-col fl">
                        <div class="label block fl hidden">购置日期：</div>
                        <div class="input block fl hidden">
                            <form:calendar property="fixedAssetsquery.purchase_date" styleId="fixedAssetsquery.purchase_date" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
                        </div>
                    </div>
                   <div class="ly-col fl">
                        <div class="label block fl hidden">资产状态：</div>
                        <div class="input block fl hidden add_easyui">
							<!-- <form:select styleClass="ly-bor-none" property="fixedAssetsquery.asset_state" styleId="fixedAssetsquery.asset_state">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="AssetsStateOptions" label="label" value="value" />
							</form:select> -->
							<select id="fixedAssetsquery.asset_state" style="width:100px;" name="fixedAssetsquery.asset_state" >
										<option value="-1">请选择</option>
										<c:forEach items="${AssetsStateOptions}" var="row">
											<option <c:if test="${fixedAssetsForm.fixedAssetsquery.asset_state==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
										</c:forEach>
							</select>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">店名：</div>
                        <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="fixedAssetsquery.trade_name" styleId="fixedAssetsquery.trade_name"/>
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
						    <th class="t-th">资产编码</th>
						    <th class="t-th">资产类别</th>
						    <th class="t-th">资产名称</th>
						    <th class="t-th">品牌</th>
						    <th class="t-th">规格型号</th>
						    <th class="t-th">出厂编码</th>
						    <th class="t-th">资产原值</th>
						    <th class="t-th">生产日期</th>
						    <th class="t-th">数量</th>
						    <th class="t-th">购置日期</th>
						    <th class="t-th">已使用年限(天)</th>
						    <th class="t-th">年限</th>
						    <th class="t-th">资产状态</th>
						    <th class="t-th">出厂派发时间</th>
						    <th class="t-th">快递公司</th>
						    <th class="t-th">运单号</th>
						    <th class="t-th">店名</th>
						    <th class="t-th">地址</th>
						    <th class="t-th">接收人</th>
						    <th class="t-th">钥匙</th>
						    <th class="t-th">密码</th>
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
								<td class="t-td"><c:out value="${index+1}"/></td>
								<td class="t-td"><c:out value="${row.asset_num}" /></td>
								<td class="t-td">
									<c:if test="${row.asset_type == 1}">
										电子设备
									</c:if>
									<c:if test="${row.asset_type == 2}">
										办公设备
									</c:if>
									<c:if test="${row.asset_type == 3}">
										其它
									</c:if>
								</td>
								<td class="t-td"><c:out value="${row.asset_name}" /></td>
								<td class="t-td"><c:out value="${row.brand}" /></td>
								<td class="t-td"><c:out value="${row.model}" /></td>
								<td class="t-td"><c:out value="${row.factory_code}" /></td>
								<td class="t-td"><c:out value="${row.asset_moeny}" /></td>
								<td class="t-td"><select:timestamp timestamp="${row.producedate}" idtype="date"/></td>
								<td class="t-td"><c:out value="${row.amount}" /></td>
								<td class="t-td"><select:timestamp timestamp="${row.purchase_date}" idtype="date"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.purchase_date}" idtype="nx"/></td>
								<td class="t-td"><c:out value="${row.life}" /></td>
								<td class="t-td">
									<c:if test="${row.asset_state == 1}">
										使用中
									</c:if>
									<c:if test="${row.asset_state == 2}">
										闲置
									</c:if>
									<c:if test="${row.asset_state == 3}">
										报废
									</c:if>
									<c:if test="${row.asset_state == 4}">
										待处理
									</c:if>
									<c:if test="${row.asset_state == 5}">
										损毁待报废
									</c:if>
								</td>
								<td class="t-td"><select:timestamp timestamp="${row.factory_date}" idtype="date"/></td>
								<td class="t-td"><c:out value="${row.express}" /></td>
								<td class="t-td"><c:out value="${row.express_num}" /></td>
								<td class="t-td"><c:out value="${row.trade_name}" /></td>
								<td class="t-td"><c:out value="${row.address}" /></td>
								<td class="t-td"><select:roster sid="${row.sendee}" idtype="jgy"/></td>
								<td class="t-td"><c:out value="${row.key}" /></td>
								<td class="t-td"><c:out value="${row.password}" /></td>
								<td class="t-td"><select:user userid="${row.createuserid}"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.createdate}" idtype="date"/></td>
								<td class="t-td"><select:user userid="${row.upduserid}"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.upddate}" idtype="date"/></td>
								<td class="t-td">
									<a href="javascript:goUse('<c:out value='${row.id}'/>');">使用情况</a>
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		</div>
		<div class="public-main-footer hidden abs">
			<a href="javascript:goExt();" class="button btn-add fl yc">导出固定资产管理台账</a>
			<div class="public-main-footer-pagin fr">
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="fixedAssetsLedger" action="/ledger/fixedAssets.do?method=fixedAssetsLedger"/>
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