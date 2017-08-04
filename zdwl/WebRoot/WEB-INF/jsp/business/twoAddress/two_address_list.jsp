﻿<%@ page contentType="text/html; charset=UTF-8"%>
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
	location="<url:context/>/twoAddress.do?method=addTwoAddressEntry";
}

//进入修改页面
function goUpd(id){
	location = "<url:context/>/twoAddress.do?method=updTwoAddressEntry&twoAddress.id="+id;
}

//执行删除操作
function doDel(id){
	if(confirm("删除后数据不可恢复\n是否继续？")){
		location="<url:context/>/twoAddress.do?method=delTwoAddress&twoAddress.id="+id;
	}
}

//执行查询操作
function doQuery(){
	$("#method").val("twoAddressList");
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	//getElement("twoAddressquery.distribid").value="";
	//getElement("twoAddressquery.two_name").value="";
	//getElement("twoAddressquery.two_username").value="";
	getElement("twoAddressquery.distribid").value="-1";
	getElement("twoAddressquery.type").value="-1";
	$("#twoAddressquery\\.distribid").combobox({value:"-1"});
    $("#twoAddressquery\\.type").combobox({ value:"-1" });
}
function goExt(){
	//$("#method").val("extExcel");
	$("#method").val("ExtSupervisionSite");
	document.forms[0].submit();
}
/* 需求32修改下拉框为easyUI下拉筛选控件 */
$(function(){
	$("#twoAddressquery\\.distribid").combobox({
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
	$("#twoAddressquery\\.type").combobox({
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
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">监管物管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">监管场地管理</a>
         </span>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/twoAddress" styleId="twoAddressForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="twoAddressList"/>
		<div class="public-main-input ly-col-1 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">经销商：</div>
                        <div class="input block fl hidden add_easyui">
						<!-- 	<form:select styleClass="ly-bor-none" property="twoAddressquery.distribid" styleId="twoAddressquery.distribid">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="dealersOptions" label="label" value="value" />
							</form:select> -->
							<select id="twoAddressquery.distribid" name="twoAddressquery.distribid" style="width:85%; margin-top:-1px; margin-left:-2px;" class="ly-bor-none">
							  			<option value="-1">请选择...</option>
							  			<c:forEach items="${dealersOptions}" var="row">
							  				<option <c:if test="${row.value==twoAddressForm.twoAddressquery.distribid}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
							  			</c:forEach>
							  		</select>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">类别：</div>
                        <div class="input block fl hidden add_easyui">
                           <!--  <form:select styleClass="ly-bor-none" property="twoAddressquery.type" styleId="twoAddressquery.type">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="twoAddressTypeOptions" label="label" value="value" />
							</form:select> -->
							<select id="twoAddressquery.type" name="twoAddressquery.type" style="width:85%;margin-top:-1px;margin-left:-2px;" class="ly-bor-none">
							  			<option value="-1">请选择...</option>
							  			<c:forEach items="${twoAddressTypeOptions}" var="row">
							  				<option <c:if test="${row.value==twoAddressForm.twoAddressquery.type}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
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
							<th class="t-th">品牌</th>
							<th class="t-th">合作金融机构</th>
							<th class="t-th">类别</th>
							<th class="t-th">名称 </th>
							<th class="t-th">详细地址</th>
							<th class="t-th">联系人</th>
							<th class="t-th">联系电话</th>
							<th class="t-th">距离(公里)</th>
							<th class="t-th">经办人</th>
							<th class="t-th">创建时间</th>
							<th class="t-th">修改人</th>
							<th class="t-th">修改时间</th>
							<th class="t-th yc">操作</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td"><c:out value="${index+1}"/></td>
								<td class="t-td"><select:dealerAll did="${row.distribid}" idtype="jxs"/></td>
								<td class="t-td"><select:dealerAll did="${row.distribid}" idtype="brand"/></td>
								<td class="t-td"><select:dealerAll did="${row.distribid}" idtype="jrjg"/></td>
								<td class="t-td">
									<c:if test="${row.type == 1}">
										本库
									</c:if>
									<c:if test="${row.type == 2}">
										二库
									</c:if>
									<c:if test="${row.type == 3}">
										二网
									</c:if>
								</td>
								<td class="t-td"><c:out value="${row.two_name}" /></td>
								<td class="t-td"><c:out value="${row.two_address}" /></td>
								<td class="t-td"><c:out value="${row.two_username}" /></td>
								<td class="t-td"><c:out value="${row.phone}" /></td>
								<td class="t-td"><c:out value="${row.distance}" /></td>
								<td class="t-td"><select:user userid="${row.createuserid}"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.createdate}" idtype="date"/></td>
								<td class="t-td"><select:user userid="${row.upduserid}"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.upddate}" idtype="date"/></td>
								<td class="t-td yc">
									<c:choose>
										<c:when test="${(role == '10'||role=='14'||role=='80')}">
											<a href="javascript:goUpd('<c:out value='${row.id}'/>');">修改</a>&nbsp;
											<a href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>
										</c:when>
									</c:choose>
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		</div>
		<div class="public-main-footer hidden abs">
			<c:choose>
				<c:when test="${(role == '10'||role=='14')}">
					<a href="javascript:goAdd();" class="button btn-add fl yc">新增</a>
				</c:when>
			</c:choose>
			<c:choose>
                <c:when test="${(role == '10'||role=='14'|| role=='33')}">
				    <a href="javascript:goExt();" class="button btn-add fl yc">导出监管场地</a>
				</c:when>
			</c:choose>
			<div class="public-main-footer-pagin fr">
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="twoAddressList" action="/twoAddress.do?method=twoAddressList"/>
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