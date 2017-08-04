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
	var msg = "<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>";
	if(msg!=null&&msg!='null'&&msg!=''){
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
}


//进入修改页面
function goUpd(id){
	location = "<url:context/>/superviseOut.do?method=updSuperviseOutEntry&superviseImport.id="+id;
}

//执行删除操作
function doDel(id){
	if(confirm("删除后数据不可恢复\n是否继续？")){
		location="<url:context/>/superviseOut.do?method=delSuperviseOut&superviseImport.id="+id;
	}
}

//执行查询操作
function doQuery(){
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("superviseImportquery.dealername").value="";
	getElement("superviseImportquery.bankname").value="";
	getElement("superviseImportquery.draft_num").value="";
	$("#superviseImportquery\\.brandid").combobox('select',-1);
	getElement("superviseImportquery.vin").value="";
	$("#superviseImportquery\\.draft_num").combobox('clear');
}

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

function doSave(){
	var carIds = $("[name='carIds']:checked");
	var ids = [];
	if(carIds.length<=0){
		alert("请选择要出库的监管物!");
		return false;
	}
	var dealerId = $(carIds[0]).attr('dealerId');
	var flag = true;
	carIds.each(function(){
			if($(this).attr('dealerId')!=dealerId){
				alert("所选的监管物必须都属于同一家经销商");
				flag=false;
				return flag;
			}
			var payamount = $(this).attr('payamount');
			var vin = $(this).attr('vin');
			if (!payamount) {
				alert("车架号为"+vin+"的车没有回款金额，不能出库！");
				flag=false;
				return flag;
			}
			ids.push(this.value);
	});
	if(!flag){
		return flag;
	}
	if(!confirm("是否确认出库？")){
		return false;
	}
	ids = ids.join();
	document.getElementById("ids").value = ids;
	getElement("method").value = "updSuperviseOuts";
	document.forms[0].submit();
}

function goNow(){
	location.href="<url:context/>/superviseOut.do?method=superviseOutNowList";
}

var flag=false;
$(function(){
	$(".amountNumberBox").each(function(){
		$(this).numberbox({    
		    min:0,    
		    precision:2
		});
		$(this).next('span').find('input:first').blur(function(){//失去焦点
			var td = $(this).closest('td');
			var thisValue= this.value;
			$.ajax({
				url:"/superviseOut.do?method=updatePaymentAmount&id="+td.attr("carid")+"&payAmount="+this.value,
				type:"get",
				dataType:"json",
				success:function(result){
					if(result.success){
						var oldValue = td.find('span:first').text();
						td.find('span:first').text(thisValue);
						$("#"+td.attr("carid")+"checkbox").attr('payamount',thisValue);
			    		$(".numberbox").hide();
			    		flag=false;
			    		document.forms[0].submit();
					}else{
						alert(result.message);
						return false;
					}
				}
				
			});
			
		});
		$(this).next('span').find('input:first').keydown(function(event){//回车键提交
			var evt = window.event || arguments.callee.caller.arguments[0];
			var charCode = (evt.which)? evt.which : evt.keyCode;
			if(charCode == 13 || charCode == 0 || charCode == 1){	
				$(this).blur();
				return false;
			} 
		});
		$(this).next('span').hide();
	});
	
	//点击变成可编辑状态
	$(".updateAmount").click(function(){
		if(flag) return;
		flag=true;
		var obj = this;
		var id = $(this).attr("carid");
		var amount = $(this).find('span:first').html();
		$(this).find('span:first').html("");
		$('#'+id+'updatepayamount').numberbox('setValue',amount);
		$('#'+id+'updatepayamount').next('span').show().find('input').focus();
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
            <a class="crumbs-link" style="color:#929291;" href="#">监管物出库</a>
         </span>
</div>
<div class="public-bar hidden">
	<div class="ly-contai clearfix">
        <div class="public-bar-tab fr hidden clearfix">
            <div class="ly-button-w fr">
                <a class="button btn-sel fl" href="javascript:void(0);">监管物出库</a>
                <a class="button fl" href="javascript:goNow();">监管物出库申请中</a>
            </div>
        </div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/superviseOut" styleId="superviseImportForm" method="post" onsubmit="return false">
			<input name="method" id="method" type="hidden" value="superviseOutList"/>
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
								<th class="t-th">
									<label for="checkAllValue"></label>
									<input name="test" value="" onclick="if(this.checked==true) { checkAll('carIds'); } else { clearAll('carIds'); }" type="checkbox">
								</th>
								<th class="t-th">序号</th>
								<th class="t-th">经销商</th>
								<th class="t-th">金融机构</th>
								<th class="t-th">品牌</th>
								<th class="t-th">票号</th>
								<th class="t-th">车架号</th>
								<th class="t-th">单价(元)</th>
								<th class="t-th">回款金额(元)</th>
								<!-- <th class="t-th">操作</th> -->
								<th class="t-th">合格证号</th>
								<th class="t-th">车辆型号</th>
								
								
								<th class="t-th">颜色</th>
								<th class="t-th">发动机号</th>
								
								<th class="t-th">钥匙数</th>
								
								<th class="t-th">创建人</th>
								<th class="t-th">创建时间</th>
								<th class="t-th">修改人</th>
								<th class="t-th">修改时间</th>
						      	
							</tr>
						</thead>
						<tbody class="t-tbody hidden">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td">
										<input type="checkbox" id="<c:out value='${row.id }'/>checkbox" name="carIds" value="<c:out value='${row.id}'/>"
										dealerId="<c:out value='${row.dealerId }'/>"
										payamount="<c:out value="${row.payment_amount}"/>"
										vin="<c:out value="${row.vin}"/>"
										/>
									</td>
									<td class="t-td"><c:out value="${index+1}"/></td>
									<td class="t-td"><c:out value="${row.dealerName}" /></td>
									<td class="t-td"><c:out value="${row.bankFullName}" /></td>
									<td class="t-td"><c:out value="${row.brandName}" /></td>
									<td class="t-td"><c:out value="${row.draft_num}" /></td>
									<td class="t-td"><c:out value="${row.vin}" /></td>
									<td class="t-td"><c:out value="${row.money}" /></td>
									<td class="t-td updateAmount" carid="<c:out value='${row.id }'/>">
										<span>
											<c:out value="${row.payment_amount}" />
										</span>
										<input style="width:100px;" class="amountNumberBox" type="text" id="<c:out value='${row.id }'/>updatepayamount">
									</td>
									<%-- <td class="t-td">
										<a style="color:blue;" href="javascript:goUpd('<c:out value='${row.id}'/>');">修改</a>
									</td> --%>
									<td class="t-td"><c:out value="${row.certificate_num}" /></td>
									<td class="t-td"><c:out value="${row.car_model}" /></td>
									
									
									<td class="t-td"><c:out value="${row.color}" /></td>
									
									<td class="t-td"><c:out value="${row.engine_num}" /></td>
									
									<td class="t-td"><c:out value="${row.key_amount}" /></td>
									
									
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
				<a href="javascript:doSave();" class="button btn-add fl yc">出库</a>
				<div class="public-main-footer-pagin fr">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="superviseOutList" action="/superviseOut.do?method=superviseOutList"/>
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