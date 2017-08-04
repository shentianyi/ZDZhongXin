<%@page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>
<%@page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ taglib uri="select.tld" prefix="select"%>


<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>
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
	.ly-bor-none {
	display: block;
	width: 80%;
	padding: 5px 0 3px 0;
	margin-left: 10%;
	margin-top: 5px;
	text-indent: 8px;
	border: 1px solid #eee;
	-ms-border-radius: 4px;
	border-radius: 4px;
	outline: none;
}
</style>
<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script type="text/javascript" src="/js/jquery.divscroll.js"></script>
<script>
	//页面初始化函数
	$(function(){
		//显示提示信息
		showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
		init();
	})
	function init(){
		$("#dealerId").combobox({
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
				} else {
					changeDealer(newValue)
				}

			}
		});
		 var draftValue = $("#dealerId").combobox('getValue');
			if (draftValue) {
				changeDealer(draftValue);
			}
	}
	//执行保存操作
	function doSave() {
		if ($("#dealerId").combobox("getValue")==-1) {
			alert("请选择经销商");
			return false;
		}
		//提交表单
		document.forms[0].submit();
	}

	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/supervisor/checkStockManage.do?method=findList";
	}
	function goExtExcel(){
		var id= document.getElementById("checkStock.id").value;
		if(id<=0){
			alert("请先选择经销商并提交后再下载!");
			return false;
		}
		location.href="<url:context/>/supervisor/checkStockManage.do?method=extExcel&checkStock.id="+id;
	}
	function changeDealer(id) {
		if(id==-1){
			return;
		}
		var url = "../json/getDealerByIdJsonAction.do?callback=?&id="+id;
		$.getJSON(url, function(result) {
			var data = result.data;
			console.info(data);
			setDealer(data[0]);
		});
	}
	
	function setDealer(dealer){
		$("#dealerAttr").val(dealer.dealerNature);
		$("#brandName").val(dealer.brand);
		$("#bankName").val(dealer.bankName);
	}
</script>
</head>
<body class="h-100 public">
	<div class="add_nav">
		<span class="add_nav_acolor">
            <a class="crumbs-link" href="#">监管物管理</a>
            > 
            <a class="crumbs-link" href="#">日查库管理新台账</a>
            > 
            <a class="crumbs-link" href="#">新增</a>
         </span>
	</div>
</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/supervisor/checkStockManage.do" styleId="checkStockManageForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="add" />
				<html:hidden property="checkStock.id" styleId="checkStock.id" />
				<div class="public-main-input ly-col-2 hidden abs">
				<div class="ly-input-w">
					<div class="ly-row clearfix">
						<div class="ly-col fl">
	                        <div class="label block fl hidden">经销商：</div>
	                        <div class="input block fl hidden">
	                       		<div class="ly-sel-w">
									<select style="width:80%" id="dealerId" name="checkStock.dealerid" onchange="changeDealer(this.value)" >
										<option value="-1">请选择</option>
										<c:forEach items="${dealers }" var="row">
											<option <c:if test="${checkStockManageForm.checkStock.dealerid==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
										</c:forEach>
									</select>
								</div>
	                        </div>
	                    </div>
	                    <div class="ly-col fl">
	                       <div class="label block fl hidden">金融机构：</div>
	                        <div class="input block fl hidden">
	                        	<input type="text"  id="bankName" readonly="readonly"  class="ly-bor-none"/>
	                        </div>
	                    </div>
	                    <div class="ly-col fl">
	                       <div class="label block fl hidden">品牌：</div>
	                        <div class="input block fl hidden">
	                        	<input type="text" id="brandName" readonly="readonly" class="ly-bor-none"/>
	                        </div>
	                    </div>
	                    <div class="ly-col fl">
	                        <div class="label block fl hidden">经销商属性：</div>
	                        <div class="input block fl hidden">
	                       		<input type="text" id="dealerAttr" readonly="readonly" class="ly-bor-none"/>
	                        </div>
	                    </div>
					</div>
					<div class="ly-row clearfix">
						<div class="ly-col fl">
	                        <div class="label block fl hidden">车辆状态：</div>
	                        <div style="width:900px; height:35px; border:1px solid #eee; line-height:35px;">
	                        	&nbsp;<form:checkboxs property="query.carStatus" collection="carStatusOptions" styleId="query.carStatus"/>
	                        </div>
	                    </div>
					</div>
				</div>
				<div class="ly-button-w">
	                <a href="javascript:doSave();" class="button btn-query">提交</a>
	              
	            </div>
			</div>
			<div class="public-main-table hidden abs">
				<div class="ly-cont">
					<table class="t-table" border="0" cellspacing="0" cellpadding="0">
						<thead class="t-thead">
							<tr class="t-tr">
								<th class="t-th">序号</th>
								<th class="t-th">型号</th>
								<th class="t-th">颜色</th>
								<th class="t-th">车架号</th>
								<th class="t-th">后五位数</th>
								<th class="t-th">检查时间</th>
								<th class="t-th">系统状态</th>
							</tr>
						</thead>
						<tbody class="t-tbody hidden">
							<logic:iterate name="checkStockCars" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}" /></td>
									<td class="t-td"><c:out value="${row.model }" /></td>
									<td class="t-td"><c:out value="${row.color }" /></td>
									<td class="t-td"><c:out value="${row.vin }" /></td>
									<td class="t-td"><c:out value="${row.lastFiveVin }" /></td>
									<td class="t-td"><select:timestamp timestamp="${row.check_date}" idtype="ss"/></td>
									<td class="t-td">
										<c:choose>  
										   <c:when test="${row.currstate==1}">本库</c:when>  
										   <c:when test="${row.currstate==2}">二库</c:when>  
										   <c:when test="${row.currstate==3}">移动</c:when>  
										</c:choose> 
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
			<div class="public-main-footer hidden abs">
				<a href="javascript:goExtExcel();" class="button btn-add fl">下载导出</a>
	            <a href="javascript:doReturn();" class="button btn-add fl">返回</a>
				<div class="public-main-footer-pagin fr">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="list" action="/supervisor/checkStockManage.do?method=preAdd" />
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
