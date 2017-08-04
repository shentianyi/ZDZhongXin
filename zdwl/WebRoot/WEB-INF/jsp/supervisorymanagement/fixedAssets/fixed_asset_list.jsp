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
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//进入新增页面
function goAdd(){
	var fid = document.getElementById("fid").value;
	location="<url:context/>/fixedAssetList.do?method=addFixedAssetListEntry&fid="+fid;
}

//进入修改页面
function goUpd(id){
	var fid = document.getElementById("fid").value;
	location = "<url:context/>/fixedAssetList.do?method=updFixedAssetListEntry&fixedAssetList.id="+id+"&fid="+fid;
}
function goDetail(id){
	var fid = document.getElementById("fid").value;
	location = "<url:context/>/fixedAssetList.do?method=detailFixedAssetList&fixedAssetList.id="+id+"&fid="+fid;
}

//执行删除操作
function doDel(id){
	var fid = document.getElementById("fid").value;
	if(confirm("删除后数据不可恢复\n是否继续？")){
		location="<url:context/>/fixedAssetList.do?method=delFixedAssetList&fixedAssetList.id="+id+"&fid="+fid;
	}
}

//执行查询操作
function doQuery(){
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("fixedAssetListquery.department").value="";
	getElement("fixedAssetListquery.username").value="";
	getElement("fixedAssetListquery.trade").value="";
	
	getElement("fixedAssetListquery.trade_province").value="";
	getElement("fixedAssetListquery.trade_city").value="";
	getElement("fixedAssetListquery.deposit_time").value="";
	getElement("fixedAssetListquery.out_time").value="";
	getElement("fixedAssetListquery.express").value="";
	getElement("fixedAssetListquery.express_num").value="";
	getElement("fixedAssetListquery.repair_time").value="";
	
}
function doreturn(){
	location="<url:context/>/fixedAssets.do?method=fixedAssetsList";
}
/* 完成需求32 easyUI下拉筛选控件 */
$(function(){
	$("#fixedAssetListquery\\.username").combobox({
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
<div class="public-bar hidden">
	<div class="ly-contai clearfix">
		<div class="public-bar-crumbs fl hidden">
            <a class="crumbs-link" href="#">台账管理</a>
            > 
            <a class="crumbs-link" href="#">固定资产使用情况</a>
        </div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/fixedAssetList" styleId="fixedAssetListForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="fixedAssetList"/>
		<input type="hidden" id="fid" name="fid" value="<c:out value='${fid}'/>">
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">使用部门：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="fixedAssetListquery.department" styleId="fixedAssetListquery.department"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">使用人：</div>
                        <div class="input block fl hidden">
                        	<div class="ly-sel-w">
                        		<!-- <form:select styleClass="ly-bor-none" property="fixedAssetListquery.username" styleId="fixedAssetListquery.username">
									<html:option value="-1">请选择</html:option>
									<html:optionsCollection name="supervisorsOptions" label="label" value="value" />
								</form:select> -->
								<select id="fixedAssetListquery.username" styleClass="ly-bor-none2" name="fixedAssetListquery.username" >
										<option value="-1">请选择</option>
										<c:forEach items="${supervisorsOptions}" var="row">
											<option <c:if test="${fixedAssetListForm.fixedAssetListquery.username==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
										</c:forEach>
							</select>
                        	</div>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">存放店：</div>
                        <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="fixedAssetListquery.trade" styleId="fixedAssetListquery.trade"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">存放时间：</div>
                        <div class="input block fl hidden">
                            <form:calendar property="fixedAssetListquery.deposit_time" styleId="fixedAssetListquery.deposit_time" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
                        </div>
                    </div>
				</div>
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">转出时间：</div>
                        <div class="input block fl hidden">
                            <form:calendar property="fixedAssetListquery.out_time" styleId="fixedAssetListquery.out_time" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
                        </div>
                    </div>
					<div class="ly-col fl">
                        <div class="label block fl hidden">运输公司：</div>
                        <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="fixedAssetListquery.express" styleId="fixedAssetListquery.express"/>
                        </div>
                    </div>
                   <div class="ly-col fl">
                        <div class="label block fl hidden">单号：</div>
                        <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="fixedAssetListquery.express_num" styleId="fixedAssetListquery.express_num"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">维修时间：</div>
                        <div class="input block fl hidden">
                            <form:calendar property="fixedAssetListquery.repair_time" styleId="fixedAssetListquery.repair_time" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
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
					        <th class="t-th">使用部门</th>
					        <th class="t-th">使用人</th>
					        <th class="t-th">员工工号</th>
					        <th class="t-th">存放店</th>
					        <th class="t-th">存放地址(省)</th>
					        <th class="t-th">存放地址(市)</th>
					        <th class="t-th">地址</th>
					        <th class="t-th">密码</th>
					        <th class="t-th">钥匙</th>
					        <th class="t-th">存放时间</th>
					        <th class="t-th">转出时间</th>
					        <th class="t-th">运输公司</th>
					        <th class="t-th">单号</th>
					        <th class="t-th">运费</th>
					        <th class="t-th">维修时间</th>
  					        <th class="t-th">维修金额</th>
					        <th class="t-th">维修内容</th>
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
								<td class="t-td"><c:out value="${row.department}" /></td>
								<td class="t-td"><select:roster sid="${row.username}" idtype="jgy"/></td>
								<td class="t-td"><select:roster sid="${row.username}" idtype="gh"/></td>
								<td class="t-td"><c:out value="${row.trade}" /></td>
								<td class="t-td"><select:address sid="${row.trade_province}" idtype="province"/></td>
								<td class="t-td"><select:address sid="${row.trade_city}" idtype="city"/></td>
								<td class="t-td"><c:out value="${row.address}"  /></td>
								<td class="t-td"><c:out value="${row.password}"/></td>
								<td class="t-td"><c:out value="${row.key}"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.deposit_time}" idtype="date"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.out_time}" idtype="date"/></td>
								<td class="t-td"><c:out value="${row.express}"/></td>
								<td class="t-td"><c:out value="${row.express_num}"/></td>
								<td class="t-td"><c:out value="${row.express_money}"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.repair_time}" idtype="date"/></td>
								<td class="t-td"><c:out value="${row.repair_money}"/></td>
								<td class="t-td"><c:out value="${row.repair_des}"/></td>
								<td class="t-td"><select:user userid="${row.createuserid}"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.createdate}" idtype="date"/></td>
								<td class="t-td"><select:user userid="${row.upduserid}"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.upddate}" idtype="date"/></td>
								<td class="t-td">
									<a href="javascript:goDetail('<c:out value='${row.id}'/>');">查看</a>&nbsp;
									<a href="javascript:goUpd('<c:out value='${row.id}'/>');">修改</a>&nbsp;
									<a href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		</div>
		<div class="public-main-footer hidden abs">
			<a href="javascript:goAdd();" class="button btn-add fl yc">新增</a>
			<a href="javascript:doreturn();" class="button btn-add fl">返回</a>
			<div class="public-main-footer-pagin fr">
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="fixedAssetList" action="/fixedAssetList.do?method=fixedAssetList"/>
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