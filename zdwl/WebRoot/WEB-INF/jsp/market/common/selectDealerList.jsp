<%@page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

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

<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script type="text/javascript" src="/js/jquery.divscroll.js"></script>
<script>
	var selectDealer = new selectDealer();
	$(function() {
		selectDealer = parent.selectDealer;
		var dealerIds = selectDealer.dealerIds;
		if (dealerIds != null && dealerIds.length > 0) {//如果经销商id不为空，则循环赋值
			for (var i = 0; i < dealerIds.length; i++) {
				$("[name='dealerIds']").each(function() {
					if (dealerIds[i] == this.value) {
						$(this).prop("checked", true);
					}
				});
			}
		}
	});
	$(function() {
		$("[name='dealerIds']").change(function() {
			if (this.checked) {
				selectDealer.addDealer(this.value, $(this).attr("dealerName"));
				if(selectDealer.maxSize==3){//只有绑定的maxSize才为3
					//这段代码只有绑定才执行
					if(selectDealer.dealerIds.length>selectDealer.maxSize){
						alert("最多绑定3家店");
						this.checked=false;
						selectDealer.removeDealer(this.value);
						return false;
					}
					
					var bindCount = $(this).attr("bindCount");
					if(bindCount>1){
						selectDealer.addDealer($(this).attr("bindInfo"),$(this).attr("bindShopName"));
						$("input[name='dealerIds'][value='"+$(this).attr("bindInfo")+"']").prop("checked",true);
					}
				}
			} else {
				selectDealer.removeDealer(this.value);
				if(selectDealer.maxSize==3){//只有绑定的maxSize才为3
					selectDealer.removeDealer($(this).attr("bindInfo"));
					$("input[name='dealerIds'][value='"+$(this).attr("bindInfo")+"']").prop("checked",false);
				}
			}
			console.info(selectDealer);
		});
	});
	//执行查询操作
	function doQuery() {
		document.forms[0].submit();
	}

	//执行表单清空操作
	function doClear() {
		//清空角色名输入框
		getElement("dealerName").value = "";
		getElement("bankName").value = "";
		getElement("brandName").value = "";
	}

	function doSave() {

		window.parent.closeSelect();
	}
</script>
</head>

<body class="h-100 public">
	<div class="public-main abs" style="top:20px;">
		<div class="ly-contai rel">
			<html:form action="/market/dealer.do" styleId="dealerForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="selectDealer">
				<html:hidden property="query.ddorbd" />
				<div class="public-main-input ly-col-1 hidden abs">
					<div class="ly-input-w">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">经销商：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none" property="query.dealerName" styleId="dealerName" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">银行：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none" property="query.bankName" styleId="bankName" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">品牌：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none" property="query.brand" styleId="brandName" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden"></div>
								<div class="input block fl hidden"></div>
							</div>
						</div>
					</div>
					<div class="ly-button-w">
						<a href="javascript:doQuery();" class="button btn-query">查询</a> <a
							href="javascript:doClear();" class="button btn-reset">重置</a>
					</div>
				</div>
				<div class="public-main-table hidden abs">
					<div class="ly-cont">
						<table class="t-table" border="0" cellspacing="0" cellpadding="0">
							<thead class="t-thead">
								<tr class="t-tr">
									<th class="t-th"></th>
									<th class="t-th">序号</th>
									<th class="t-th">经销商名称</th>
									<th class="t-th">金融机构</th>
									<th class="t-th">品牌</th>
									<th class="t-th">已绑定的店</th>
								</tr>
							</thead>
							<tbody class="t-tbody hidden" onMouseOver="mouseOver()"
								onMouseOut="mouseOut()" onClick="clickRow()">
								<logic:iterate name="list" id="row" indexId="index">
									<tr class="listTr_a">
										<td class="t-td"><input type="checkbox" id="dealerIds"
											name="dealerIds" value="<c:out value='${row.id}'/>"
											dealerName="<c:out value='${row.dealerName}'/>" 
											bindInfo="<c:out value='${row.bindInfo }'/>"
											bindShopName="<c:out value="${row.bindShopName}" />"
											bindCount="<c:out value="${row.bindCount}" />"
											 /></td>
										<td align="center"><c:out value="${index+1}" /></td>
										<td class="t-td"><c:out value="${row.dealerName}" /></td>
										<td class="t-td"><c:out value="${row.bankName}" /></td>
										<td class="t-td"><c:out value="${row.brandName}" /></td>
										<td class="t-td" title="<c:out value="${row.bindShopName}" />"><c:out value="${row.bindShopName}" /></td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
				</div>
				<div class="public-main-footer hidden abs">
					<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
					<div class="public-main-footer-pagin fr">
						<thumbpage:tools
							className="<%=ThumbPageConstants.CLASSNAME_TAGBAR.getCode()%>"
							tableName="selectDealerList" action="/market/dealer.do" />
					</div>
				</div>
			</html:form>
		</div>
	</div>
</body>
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
</html>
