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
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<html class="h-100">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>中都汽车金融监管系统</title>

<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/public.css" />
<link href="/easyui/themes/material/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<style type="text/css">
.title td {
	padding: 0 15px 0 15px;
}
</style>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script type="text/javascript" src="/js/jquery.divscroll.js"></script>
<script type="text/javascript">
	function messageRead(id){
		$.getJSON("/json/messageRead.do?callback=?&id="+id, function(json){
			  console.info(json);
		});
	}
	function goRead(isread) {
		location.href = "/message.do?method=warningPageList&messagequery.isread="
				+ isread;
	}
	//执行查询操作
	function doQuery() {
		document.forms[0].submit();
	}

	//执行表单清空操作
	function doClear() {
		$("[name='messagequery.deptName']").val("");
		$("[name='messagequery.typeName']").val("");
	}
	/* //导航栏标签 序号19
		window.onload=function(){ 
		var messagequery_list = document.getElementsByClassName("crumbs-link");
		for(var i=0;i<messagequery_list.length;i++){
		if(getElement("messagequery.type").value ==53){
			messagequery_list[0].innerText="信息预警＞项目进驻流转单发出后五天未匹配人员信息预警";
		}
		if(getElement("messagequery.type").value ==54){
			messagequery_list[1].innerText="信息预警＞监管员上岗未培训考核预警";
		}
		if(getElement("messagequery.type").value ==55){
			messagequery_list[2].innerText="信息预警＞监管员面试完成三日未安排培训预警";
		}
		if(getElement("messagequery.type").value ==56){
			messagequery_list[3].innerText="信息预警＞监管员辞职离岗时间前五天未提交人员信息预警";
		}
			}
	} */
	
	
</script>
</head>
<body class="h-100 public" onload="mynav2();">
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">预警提醒</a>
     </span>
     
</div>
	<div class="public-main abs" style="margin-top: 40px;">
		<div class="ly-contai rel">
			<html:form action="/message" styleId="messageForm" method="post"
				onsubmit="return false">
				<input name="method" id="method" type="hidden" value="warningPageList" />
				<html:hidden property="messagequery.type" styleId="messagequery.type" />
				<div class="public-main-input ly-col-2 hidden abs" style="margin-top: -5%;">
					<div class="ly-input-w">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">部门：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none" property="messagequery.deptName" styleId="messagequery.deptName"/>
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">消息类别：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none" property="messagequery.typeName" styleId="messagequery.typeName"/>
								</div>
							</div>
						</div>
					</div>
					<div class="ly-button-w" style="margin-top: -70px;margin-right: -20%;float: right;">
						<a href="javascript:doQuery();" class="button btn-query">查询</a> 
						<a href="javascript:doClear();" class="button btn-reset">重置</a>
					</div>
				</div>
				<div class="public-main-table hidden abs" style="top: 9px;">
					<div class="ly-cont">
						<table class="t-table" border="0" cellspacing="0" cellpadding="0">
							<thead class="t-thead">
								<tr class="t-tr">
									<th class="t-th">序号</th>
							      	<th class="t-th">部门</th>
									<th class="t-th">消息类别</th>
									<th class="t-th">数量</th>
								</tr>
							</thead>
							<tbody class="t-tbody hidden">
								<logic:iterate name="list" id="row" indexId="index">
									<tr class="t-tr">
										<td class="t-td"><c:out value="${index+1}" /></td>
										<td class="t-td"><c:out value="${row.deptName}" /></td>
										<td class="t-td"><select:msgtype mid="${row.type}"></select:msgtype></td>
										<td class="t-td">
											<a href="<c:out value='${row.url}'/>&parentId=<c:out value='${row.id}'/>"
											onclick="messageRead('<c:out value="${row.id }"/>')">
												<font <c:if test="${row.isread == 1}">color="red"</c:if>>
													<c:out value="${row.name}" />
												</font>
											</a>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
				</div>
				<div class="public-main-footer hidden abs">
					<div class="public-main-footer-pagin fr">
						<thumbpage:tools
							className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>"
							tableName="warningPageList"
							action="/message.do?method=warningPageList" />
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
