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
<%-- <%@ taglib uri="fmt.tld" prefix="fmt"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=IUTF-8">
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/jquery-ui.min.css">
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/thumbpage/thumbpage.js"></script>
<script>
function doQuery(){
	document.forms[0].submit();
}

  $(function() {
	$.ajax({
		url:"ZXinterface.do?method=disajax",
		type:"post",
		date:{
			
		},
		dataType:"json",
		success:function(result){
			console.log(result[0]);
			var datas = eval('{' + result.organizationcode + '}');
			console.log(datas);
			$("#custOrganizationcode").autocomplete(result[0], {
				source:[result[0].organizationcode,"1233123"]
				});
			/* var datas = eval('(' + msg.d + ')');
				$("#custOrganizationcode").autocomplete(datas, {
					formatItem: function (row, i, max) {
						return "<table width='400px'><tr><td align='left'>" + row.Key + "</td><td align='right'><font style='color: #009933; font-family: 黑体; font-style: italic'>" + row.Value + "</font>&nbsp;&nbsp;</td></tr></table>";
					},
					formatMatch: function(row, i, max){
						return row.Key;
						}
			}); */s
		}
	});
	  
    
  });
  
  $(function() {
	    var availableTags = [
			"ActionScript",
			"AppleScript",
			"Asp",
			"BASIC",
			"C",
			"C++",
			"Clojure",
			"COBOL",
			"ColdFusion",
			"Erlang",
			"Fortran",
			"Groovy",
			"Haskell",
			"Java",
			"JavaScript",
			"Lisp",
			"Perl",
			"PHP",
			"Python",
			"Ruby",
			"Scala",
			"Scheme"
	    ];
	    $( "#custName" ).autocomplete({
	      source: availableTags
	    });
	  });
  </script>
</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="/ZXBank">中信银行接口</a>
				>
				<a class="crumbs-link" href="#">客户信息查询</a>
			</div>
		</div>
	</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="ZXinterface.do" styleId="iForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="customer" />
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">组织机构代码：</div>
	                    <div class="input block fl hidden">
	                    	<html:text property="customer.custOrganizationcode"  styleId="custOrganizationcode" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">客户名称：</div>
	                    <div class="input block fl hidden">
	                    	<html:text property="customer.custName" styleId="custName" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
	                    	<!-- <input id="custName" type="text" name="custName" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" /> -->
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">查询方式：</div>
	                    <div class="input block fl hidden">
	                    	<select class="ly-bor-none" id="" name="" style="min-width:150px;width:80%;">
	                    		<option>请选择</option>
	                    		<option>本地查询</option>
	                    		<option>远程查询</option>
	                    	</select>
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
				<div style="overflow-x: auto; overflow-y: auto; height: 100%; width:100%">
				<table class="t-table" border="0" cellspacing="0" cellpadding="0">
					<thead class="t-thead">
						<tr class="t-tr">
							<th class="t-th">序号</th>
							<th class="t-th">组织机构代码</th>
							<th class="t-th">ECIF客户号</th>
							<th class="t-th">客户名称</th>
							<th class="t-th">创建时间</th>
							<th class="t-th">更新时间</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td"><c:out value="${index+1}"/></td>
								<td class="t-td"><c:out value="${row.custOrganizationcode}"/></td>
								<td class="t-td"><c:out value="${row.custNo}"/></td>
								<td class="t-td"><c:out value="${row.custName}"/></td>
								<td class="t-td"><c:out value="${row.custCreateDate}"/></td>
								<td class="t-td"><c:out value="${row.custUpdateDate}"/></td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
				</div>
			</div>
		</div>
		
		<div class="public-main-footer hidden abs">
			<div class="public-main-footer-pagin fr">
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="Customer" action="ZXinterface.do?method=customer" />
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