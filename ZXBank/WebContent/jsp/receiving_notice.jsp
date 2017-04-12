<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/jquery-ui.min.css">
<link rel="stylesheet" href="css/tablecs.css">
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/thumbpage/thumbpage.js"></script>
<script type="text/javascript" src="js/jquery.divscroll.js"></script>
<script>
	function doQuery() {
		document.forms[0].submit();
	}
	function doClear() {
		/* $("custNo").value="";
		getElement("pledgeName").value=""; */
	}
	$(function() {
		var availableTags = [ "41234", "12312", "12341", "5123412", "234125",
				"51234", "234534", "623452", "6346345", "62346", "62346",
				"1412341y", "1234234", "Java", "JavaScript", "Lisp", "Perl",
				"PHP", "Python", "Ruby", "Scala", "Scheme" ];
		$("#R_No").autocomplete({
			source : availableTags
		});
	});

	$(function() {
		var availableTags = [ "ActionScript", "AppleScript", "Asp", "BASIC",
				"C", "C++", "Clojure", "COBOL", "ColdFusion", "Erlang",
				"Fortran", "Groovy", "Haskell", "Java", "JavaScript", "Lisp",
				"Perl", "PHP", "Python", "Ruby", "Scala", "Scheme" ];
		$("#d_name").autocomplete({
			source : availableTags
		});
	});
</script>
</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="/ZXBank">中信银行接口</a> > <a
					class="crumbs-link" href="#">收货通知</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/receivingnotice.do" styleId="rForm" method="post" onsubmit="return false">
			<input name="method" id="method" type="hidden" value="receivingnotice" />
			<div class="public-main-input ly-col-1 hidden abs">
				<div class="ly-input-w">
					<div class="ly-row clearfix">
						<div class="ly-col fl">
							<div class="label block fl hidden">收货通知书编号：</div>
							<div class="input block fl hidden">
								<input class="ly-bor-none" id="R_No" type="text" name="receivingnotice.nyNo"
									value="${nyNo }" />
							</div>
						</div>

						<div class="ly-col fl">
							<div class="label block fl hidden">借款企业名称：</div>
							<div class="input block fl hidden">
								<input class="ly-bor-none" id="d_name" type="text" name="receivingnotice.nyLonentname"
									value="${nyLonentname }" />
							</div>
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
						<table width="100%" class="t-table" border="0" cellspacing="0"
							cellpadding="0">
							<thead class="t-thead">
								<tr class="t-tr">
									<th class="t-th">序号</th>
									<th class="t-th">收货通知书编号</th>
									<th class="t-th">借款企业ID</th>
									<th class="t-th">借款企业名称</th>
									<th class="t-th">核心企业名称</th>
									<th class="t-th">(在库)监管企业名称</th>
									<th class="t-th">(在途)监管企业名称</th>
									<th class="t-th">运输企业名称</th>
									<th class="t-th">纸质监管协议编号</th>
									<th class="t-th">交货地点</th>
									<th class="t-th">货物价值合计</th>
									<th class="t-th">通知书日期</th>
									<th class="t-th">发货日期</th>
									<th class="t-th">预计到港（库）日期</th>
									<th class="t-th">预计到港（库）</th>
									<th class="t-th">备注</th>
									<th class="t-th">总记录数</th>
									<th class="t-th">创建时间</th>
									<th class="t-th">更新时间</th>
									<th class="t-th">操作</th>
								</tr>
							</thead>
							<tbody class="t-tbody hidden">
								<logic:iterate name="list" id="row" indexId="index">
									<tr class="t-tr">
										<td class="t-td"><c:out value="${index+1 }" /></td>
										<td class="t-td"><c:out value="${row.nyNo }" /></td>
										<td class="t-td"><c:out value="${row.nyLonentno }" /></td>
										<td class="t-td"><c:out value="${row.nyLonentname }" /></td>
										<td class="t-td"><c:out value="${row.nyCorentnm }" /></td>
										<td class="t-td"><c:out value="${row.nySpventnm }" /></td>
										<td class="t-td"><c:out value="${row.nyOnwspvenm }" /></td>
										<td class="t-td"><c:out value="${row.nyTrsptentnm }" /></td>
										<td class="t-td"><c:out value="${row.nyOfflnsatno }" /></td>
										<td class="t-td"><c:out value="${row.nyExcplace }" /></td>
										<td class="t-td"><c:out value="${row.nyTtlcmdval }" /></td>
										<td class="t-td">
											<select:timestamp timestamp="${row.nyNtcdate}" idtype="ss"/>
										</td>
										<td class="t-td">
											<select:timestamp timestamp="${row.nyCsndate}" idtype="ss"/>
										</td>
										<td class="t-td">
											<select:timestamp timestamp="${row.nyEta}" idtype="ss"/>
										</td>
										<td class="t-td"><c:out value="${row.nyEpa }" /></td>
										<td class="t-td"><c:out value="${row.nyRemark }" /></td>
										<td class="t-td"><c:out value="${row.nyTotnum}" /></td>
										<td class="t-td">
											<select:timestamp timestamp="${row.nyCreatedate}" idtype="ss"/>
										</td>
										<td class="t-td">
											<select:timestamp timestamp="${row.nyUpdatedate}" idtype="ss"/>
										</td>
										<td class="t-td"><a href="receivingdetail.do?method=receivingdetail&nyNo=${row.nyNo}">详情</a></td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
				</div>
			</div>
			<div class="public-main-footer hidden abs">
				<div class="public-main-footer-pagin fr">
					<c:if test="${not empty list }">
						<thumbpage:tools
							className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>"
							tableName="ReceivingNotice" action="receivingnotice.do?method=receivingnotice" />
					</c:if>
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