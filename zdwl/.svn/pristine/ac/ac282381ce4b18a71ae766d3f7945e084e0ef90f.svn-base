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
<script type="text/javascript" src="js/jquery.divscroll.js"></script>

<script>

$(function(){
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
});

//执行查询操作
function doQuery(){
	document.forms[0].submit();
	getElement("superviseImportquery.dealername").value="";
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("superviseImportquery.dealername").value="";
	getElement("superviseImportquery.draft_num").value="";
	getElement("superviseImportquery.brandid").value=-1;
	getElement("superviseImportquery.car_model").value="";
	getElement("superviseImportquery.car_structure").value="";
	getElement("superviseImportquery.color").value="";
	getElement("superviseImportquery.certificate_datebegin").value="";
	getElement("superviseImportquery.certificate_dateend").value="";
	getElement("superviseImportquery.state").value=-1;
	getElement("superviseImportquery.storagetimebegin").value="";
	getElement("superviseImportquery.storagetimeend").value="";
	getElement("superviseImportquery.outtimebegin").value="";
	getElement("superviseImportquery.outtimeend").value="";
	getElement("superviseImportquery.movetimebegin").value="";
	getElement("superviseImportquery.movetimeend").value="";
	getElement("superviseImportquery.vin").value="";
	$("#superviseImportquery\\.draft_num").combobox('clear');
	
	
	var jrj = document.getElementById("jrj").value;
	if(jrj == 1){
		getElement("superviseImportquery.bankname").value="";
	}
}
//进入修改页面
function goUpd(id){
	location = "<url:context/>/superviseImport.do?method=updSuperviseImportEntry&superviseImport.id="+id;
}

//执行删除操作
function doDel(id){
	if(confirm("删除后数据不可恢复\n是否继续？")){
		location="<url:context/>/superviseImport.do?method=delSuperviseImport&superviseImport.id="+id;
	}
}
function goExt(){
	/* var dealername = document.getElementById("superviseImportquery.dealername").value;
	var draft_num = document.getElementById("superviseImportquery.draft_num").value;
	var brandid = document.getElementById("superviseImportquery.brandid").value;
	var car_model = document.getElementById("superviseImportquery.car_model").value;
	var car_structure = document.getElementById("superviseImportquery.car_structure").value;
	var color = document.getElementById("superviseImportquery.color").value;
	var certificate_datebegin = document.getElementById("superviseImportquery.certificate_datebegin").value;
	var certificate_dateend = document.getElementById("superviseImportquery.certificate_dateend").value;
	var state = document.getElementById("superviseImportquery.state").value;
	var storagetimebegin = document.getElementById("superviseImportquery.storagetimebegin").value;
	var storagetimeend = document.getElementById("superviseImportquery.storagetimeend").value;
	var outtimebegin = document.getElementById("superviseImportquery.outtimebegin").value;
	var outtimeend = document.getElementById("superviseImportquery.outtimeend").value;
	var movetimebegin = document.getElementById("superviseImportquery.movetimebegin").value;
	var movetimeend = document.getElementById("superviseImportquery.movetimeend").value;
	var vin = document.getElementById("superviseImportquery.vin").value;
	
	
	
	location="<url:context/>/superviseImport.do?method=extExcel&dealername="+dealername+"&draft_num="+draft_num
	+"&brandid="+brandid+"&car_model="+car_model+"&car_structure="+car_structure+"&color="+color
	+"&certificate_datebegin="+certificate_datebegin+"&certificate_dateend="+certificate_dateend
	+"&state="+state+"&storagetimebegin="+storagetimebegin+"&storagetimeend="+storagetimeend
	+"&outtimebegin="+outtimebegin+"&outtimeend="+outtimeend+"&movetimebegin="+movetimebegin
	+"&movetimeend="+movetimeend+"&vin="+vin; */
	
	$("#method").val("extExcel");
	document.forms[0].submit();
}
</script>
</head>
<body class="h-100 public">
<div class="public-bar hidden">
	<div class="ly-contai clearfix">
		<div class="public-bar-crumbs fl hidden">
            <a class="crumbs-link" href="#">台账管理</a>
            > 
            <a class="crumbs-link" href="#">监管车辆台账</a>
        </div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/superviseImport" styleId="superviseImportForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="superviseLedger"/>
		<input type="hidden" id="jrj" name="jrj" value="<c:out value='${jr}'/>" />
		<div class="public-main-input ly-col-3 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">经销商：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="superviseImportquery.dealername" styleId="superviseImportquery.dealername"/>
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
                    <div class="ly-col fl">
                        <div class="label block fl hidden">品牌：</div>
                        <div class="input block fl hidden">
                            <form:select styleClass="ly-bor-none" property="superviseImportquery.brandid" styleId="superviseImportquery.brandid">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="brandOptions" label="label" value="value" />
							</form:select>
                        </div>
                    </div>
				</div>
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">型号：</div>
                        <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="superviseImportquery.car_model" styleId="superviseImportquery.car_model"/>
                        </div>
                    </div>
					<div class="ly-col fl">
                        <div class="label block fl hidden">车身结构：</div>
                        <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="superviseImportquery.car_structure" styleId="superviseImportquery.car_structure"/>
                        </div>
                    </div>
                   <div class="ly-col fl">
                        <div class="label block fl hidden">颜色：</div>
                        <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="superviseImportquery.color" styleId="superviseImportquery.color"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">合格证发证日期：</div>
                        <div class="input block fl hidden">
                            <form:calendar property="superviseImportquery.certificate_datebegin" styleId="superviseImportquery.certificate_datebegin" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
							-<form:calendar property="superviseImportquery.certificate_dateend" styleId="superviseImportquery.certificate_dateend" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
                        </div>
                    </div>
				</div>
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">车辆状态：</div>
                        <div class="input block fl hidden">
							<form:select styleClass="ly-bor-none" property="superviseImportquery.state" styleId="superviseImportquery.state">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="superviseStateOptions" label="label" value="value" />
							</form:select>
                        </div>
                    </div>
					<div class="ly-col fl">
                        <div class="label block fl hidden">入库时间：</div>
                        <div class="input block fl hidden">
                            <form:calendar property="superviseImportquery.storagetimebegin" styleId="superviseImportquery.storagetimebegin" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
							-<form:calendar property="superviseImportquery.storagetimeend" styleId="superviseImportquery.storagetimeend" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">出库时间：</div>
                        <div class="input block fl hidden">
                        	<form:calendar property="superviseImportquery.outtimebegin" styleId="superviseImportquery.outtimebegin" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
							-<form:calendar property="superviseImportquery.outtimeend" styleId="superviseImportquery.outtimeend" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">移动时间：</div>
                        <div class="input block fl hidden">
                        	<form:calendar property="superviseImportquery.movetimebegin" styleId="superviseImportquery.movetimebegin" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
							-<form:calendar property="superviseImportquery.movetimeend" styleId="superviseImportquery.movetimeend" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
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
							<th class="t-th">经销商</th>
							<c:choose>
						      	<c:when test="${jr == 1}">
						      		<th class="t-th">金融机构</th>
						      	</c:when>
						      	<c:otherwise>
						      	
						      	</c:otherwise>
					      	</c:choose>
							<th class="t-th">品牌</th>
							<th class="t-th">票号</th>
							<th class="t-th">开票金额</th>
							<th class="t-th">开票日</th>
							<th class="t-th">到期日</th>
							<th class="t-th">合格证发证日期</th>
							<th class="t-th">合格证号</th>
							<th class="t-th">车辆型号</th>
							<th class="t-th">排量</th>
							<th class="t-th">颜色</th>
							<th class="t-th">发动机号</th>
							<th class="t-th">车架号</th>
							<th class="t-th">钥匙号</th>
							<th class="t-th">钥匙数</th>
							<th class="t-th">单价</th>
							<th class="t-th">合格证接收时间</th>
							<th class="t-th">入库时间</th>
							<th class="t-th">车辆状态</th>
							<th class="t-th">移动时间</th>
							<th class="t-th">释放时间</th>
							<th class="t-th">二网名称</th>
							<th class="t-th">保证金</th>
							<th class="t-th">回款金额</th>
							<th class="t-th">创建人</th>
							<th class="t-th">创建时间</th>
							<th class="t-th">修改人</th>
							<th class="t-th">修改时间</th>
							<c:if test="${isjl == 1}">
					      		<th class="t-th">操作</th>
					      </c:if>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td"><c:out value="${index+1}"/></td>
								<td class="t-td"><select:dealer sid="${row.id}" dn="${row.draft_num}" idtype="jxs"/></td>
								<c:choose>
							      	<c:when test="${jr == 1}">
							      		<td class="t-td"><select:dealer sid="${row.id}" dn="${row.draft_num}" idtype="jrjg"/></td>
							      	</c:when>
							      	<c:otherwise></c:otherwise>
							    </c:choose>
								<td class="t-td"><select:superviseImport sid="${row.id}" idtype="brand"/></td>
								<td class="t-td"><c:out value="${row.draft_num}" /></td>
								<td class="t-td"><select:draft draftid="0" draftNum="${row.draft_num}" idtype="billing_money"/></td>
								<td class="t-td"><select:draft draftid="0" draftNum="${row.draft_num}" idtype="billing_date"/></td>
								<td class="t-td"><select:draft draftid="0" draftNum="${row.draft_num}" idtype="due_date"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.certificate_date}" idtype="date"/></td>
								<td class="t-td"><c:out value="${row.certificate_num}" /></td>
								<td class="t-td"><c:out value="${row.car_model}" /></td>
								<td class="t-td"><c:out value="${row.displacement}" /></td>
								<td class="t-td"><c:out value="${row.color}" /></td>
								<td class="t-td"><c:out value="${row.engine_num}" /></td>
								<td class="t-td"><c:out value="${row.vin}" /></td>
								<td class="t-td"><c:out value="${row.key_num}" /></td>
								<td class="t-td"><c:out value="${row.key_amount}" /></td>
								<td class="t-td"><c:out value="${row.money}" /></td>
								<td class="t-td"><select:timestamp timestamp="${row.certificate_intime}" idtype="date"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.storagetime}" idtype="date"/></td>
								<td class="t-td">
									<c:choose>
										<c:when test="${row.apply == 1}">
											<c:if test="${row.state == 2}">入库申请中</c:if>
											<c:if test="${row.state == 3}">出库申请中</c:if>
											<c:if test="${row.state == 4}">移动申请中</c:if>
										</c:when>
										<c:otherwise>
											<c:if test="${row.state == 1}">在途</c:if>
											<c:if test="${row.state == 2}">在库</c:if>
											<c:if test="${row.state == 3}">出库</c:if>
											<c:if test="${row.state == 4}">移动</c:if>
										</c:otherwise>
									</c:choose>
								</td>
								<td class="t-td"><select:timestamp timestamp="${row.movetime}" idtype="date"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.outtime}" idtype="date"/></td>
								<td class="t-td"><c:out value="${row.two_name}" /></td>
								<td class="t-td"><c:out value="${row.bond}" /></td>
								<td class="t-td"><c:out value="${row.payment_amount}" /></td>
								<td class="t-td"><select:user userid="${row.createuserid}"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.createdate}" idtype="date"/></td>
								<td class="t-td"><select:user userid="${row.upduserid}"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.upddate}" idtype="date"/></td>
								<c:if test="${isjl == 1}">
					      			<td class="t-td">
										<a href="javascript:goUpd('<c:out value='${row.id}'/>');">修改</a>&nbsp;
										<a href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>
									</td>
					      		</c:if>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		</div>
		<div class="public-main-footer hidden abs">
			<a href="javascript:goExt();" class="button btn-add fl">导出监管车辆台账</a>
			<div class="public-main-footer-pagin fr">
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="superviseLedger" action="/superviseImport.do?method=superviseLedger"/>
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
