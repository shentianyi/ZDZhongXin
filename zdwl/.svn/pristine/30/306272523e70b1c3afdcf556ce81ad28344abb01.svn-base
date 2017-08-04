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
<script type="text/javascript" src="/js/jquery.divscroll.js"></script>
<script>
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
function doQuery(){
	$("#method").val("supMaMsgInfoList");
	document.forms[0].submit();
}
function doClear(){
	$("#supervisorName").val("");
	$("#staffNo").val("");
	$("#dealerName").val("");
	$("#bankName").val("");
	$("#entryTime").val("");
	$("#handoverDuedate").val("");
}
function doSave(){
	var messageIds = $("[name='messageIds']:checked");
	var ids = [];
	if(messageIds.length<=0){
		alert("请选择要标记为已读的消息!");
		return false;
	}
	messageIds.each(function(){
		ids.push(this.value);
	});
	ids = ids.join();
	document.getElementById("ids").value = ids;
	if(confirm("是否确认提交？")){
		$("#method").val("updateSupMaMsgInfoIsread");
		document.forms[0].submit();
	}
}
//全部已读
function readAll(){
    $("#method").val("ReadAllSupMaMsgInfoList");
    document.forms[0].submit();
}
</script>
</head>
<body class="h-100 public">
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/message/supMaMsg" styleId="messageForm" method="post" onsubmit="return false">
			<input name="method" id="method" type="hidden" value="supMaMsgInfoList"/>
			<html:hidden property="supMaQuery.messageId" styleId="supMaQuery.messageId" />
			<html:hidden property="supMaQuery.type" styleId="supMaQuery.type" />
			<input type="hidden" id="ids" name="ids" />
			<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">监管员姓名：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="supMaQuery.supervisorName" styleId="supervisorName"/>
                        </div>
                    </div>
                    <div class="ly-col fl" >
                        <div class="label block fl hidden">工号：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="supMaQuery.staffNo" styleId="staffNo"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">经销商：</div>
                        <div class="input block fl hidden">
                        	 <html:text styleClass="ly-bor-none" property="supMaQuery.dealerName" styleId="dealerName"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">金融机构：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="supMaQuery.bankName" styleId="bankName"/>
                        </div>
                    </div>
                    <div class="ly-col fl" >
                        <div class="label block fl hidden">调入时间：</div>
                        <div class="input block fl hidden">
                        	<form:calendar property="supMaQuery.entryTime" styleId="entryTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">轮岗到期时间：</div>
                        <div class="input block fl hidden">
                        	<form:calendar property="supMaQuery.handoverDuedate" styleId="handoverDuedate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
                        </div>
                    </div>
				</div>
			</div>
			<div class="ly-button-w">
                <a href="javascript:doQuery();" class="button btn-query">查询</a>
                <a href="javascript:doClear();" class="button btn-reset">重置</a>
            </div>
			</div>
			<div class="public-main-table hidden abs" style="margin-top: 0%;">
				<div class="ly-cont">
					<table class="t-table" border="0" cellspacing="0" cellpadding="0">
						<thead class="t-thead">
							<tr class="t-tr">
								<th class="t-th">序号</th>
								<th class="t-th">监管员姓名</th>
								<th class="t-th">工号</th>
								<th class="t-th">经销商</th>
								<th class="t-th">金融机构</th>
								<th class="t-th">调入时间</th>
								<th class="t-th">轮岗到期时间</th>
								<th class="t-th">
									<input name="test" value="" onclick="if(this.checked==true) { checkAll('messageIds'); } else { clearAll('messageIds'); }" type="checkbox">
									<label for="checkAllValue">已读</label>
								</th>
							</tr>
						</thead>
						<tbody class="t-tbody hidden">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}"/></td>
									<td class="t-td">
										<font <c:if test="${row.isread == 1}">color="red"</c:if>>
											<c:out value="${row.supervisorName}"/>
										</font>
									</td>
									<td class="t-td">
										<font <c:if test="${row.isread == 1}">color="red"</c:if>>
											<c:out value="${row.staffNo}"/>
										</font>
									</td>
									<td class="t-td">
										<font <c:if test="${row.isread == 1}">color="red"</c:if>>
											<c:out value="${row.dealerName}"/>
										</font>
									</td>
									<td class="t-td">
										<font <c:if test="${row.isread == 1}">color="red"</c:if>>
											<c:out value="${row.bankName}"/>
										</font>
									</td>
									<td class="t-td">
										<font <c:if test="${row.isread == 1}">color="red"</c:if>>
											<select:timestamp timestamp="${row.entryTime}" idtype="ss"/>
										</font>
									</td>
									<td class="t-td">
										<font <c:if test="${row.isread == 1}">color="red"</c:if>>
											<select:timestamp timestamp="${row.handoverDuedate}" idtype="ss"/>
										</font>
									</td>
									<td class="t-td">
										<c:if test="${row.isread == 1}">
											<input type="checkbox" id="<c:out value='${row.id }'/>checkbox" name="messageIds" value="<c:out value='${row.id}'/>"/>
										</c:if>
										<label>已读</label>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
			<div class="public-main-footer hidden abs">
				<a href="javascript:doSave();" class="button btn-add fl">提交</a>
				<a href="javascript:readAll();" class="button btn-add fl">全部已读</a> 
				<div class="public-main-footer-pagin fr">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="list" action="/message/supMaMsg.do?method=supMaMsgInfoList"/>
				</div>
			</div>
		</html:form>
	</div>
</div>
<div id="chooseTwoAddress">
	<iframe height="340px" width="780px" frameborder="0" ></iframe>
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
