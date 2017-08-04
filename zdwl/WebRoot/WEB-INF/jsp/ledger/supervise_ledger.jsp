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

<%@ page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>
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
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="/js/zxjs.js"></script>

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

$(function(){
	initTools();
});

function initTools(){
	
	var dateboxOptions={"editable":false}
	
	$('#certificate_datebegin').datebox(dateboxOptions);  
	$('#certificate_dateend').datebox(dateboxOptions);  
	$('#storagetimebegin').datebox(dateboxOptions);  
	$('#storagetimeend').datebox(dateboxOptions);
	$('#outtimebegin').datebox(dateboxOptions);  
	$('#outtimeend').datebox(dateboxOptions);
	$('#movetimebegin').datebox(dateboxOptions);  
	$('#movetimeend').datebox(dateboxOptions);

}

//执行查询操作
function doQuery(){
	$("#method").val("superviseLedger");
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	
	//清空资源名输入框
	getElement("superviseImportquery.dealername").value=-1;
	getElement("superviseImportquery.state").value=-1;
	getElement("superviseImportquery.brandid").value=-1;
	getElement("superviseImportquery.car_model").value="";
	getElement("superviseImportquery.car_structure").value="";
	getElement("superviseImportquery.color").value="";
	getElement("superviseImportquery.bankname").value="";
	getElement("superviseImportquery.vin").value="";
	getElement("superviseImportquery.brandName").value="";
	
	$('#certificate_datebegin').datebox('clear');
	$('#certificate_dateend').datebox('clear');
	$('#storagetimebegin').datebox('clear');
	$('#storagetimeend').datebox('clear');
	$('#outtimebegin').datebox('clear');
	$('#outtimeend').datebox('clear');
	$('#movetimebegin').datebox('clear');
	$('#movetimeend').datebox('clear');
	/* $("#superviseImportquery\\.state").val("-1");
	$("#superviseImportquery\\.vin").val(""); */
	/* $("#superviseImportquery\\.draft_num").combobox('clear'); 
	$("#superviseImportquery\\.brandid").combobox({value:"-1"});*/
	document.getElementById("superviseImportquery.draft_num").options.length=0;///清除所有options
    document.getElementById("superviseImportquery.draft_num").options.add(new Option("请选择",-1,true,false));
	
	
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
	$("#method").val("extExcel");
	document.forms[0].submit();
}
//异步加载票号
function draftNumBox(){
    $.ajax({
        type : "POST",
        url : "/ledger/superviseImport.do?method=draftNumBox",
        dataType : "json",
        data : "dealerId="+document.getElementById("superviseImportquery.dealername").options[document.getElementById("superviseImportquery.dealername").selectedIndex].value,
        success : function(json) {
          /*   document.getElementById("superviseImportquery.draft_num").options.length=0;///清除所有options
            document.getElementById("superviseImportquery.draft_num").options.add(new Option("请选择",-1,true,false));
            for ( var i = 0; i < json[0].length; i++) {
                //document.getElementById("superviseImportquery.draft_num").options.add(new Option(json[0][i].label,json[0][i].value));
                document.getElementById("superviseImportquery.draft_num").options.add(new Option(json[0][i].label,json[0][i].label));
            }  */
        },
        
    });
}



$(function(){
	//限制超级角色操作
    restrict();
    
     var dnum = $("#dnum").val();
    if(dnum !=null && dnum !="" && dnum != -1){
        //$("#superviseImportquery.draft_num").append("<option value='dnum' selected>dnum</option>");
        document.getElementById("superviseImportquery.draft_num").options.add(new Option(dnum,dnum));
    }else{
       	document.getElementById("superviseImportquery.draft_num").options.add(new Option("请选择",-1,true,false));
    } 

   
   	comboboxSetId("#superviseImportquery\\.dealername");
	comboboxSetId("#superviseImportquery\\.draft_num");
	comboboxSetId("#superviseImportquery\\.brandid");
	
});

function restrict(){
    var crole = $("#crole").val();
    if(crole == 30){
        $(".yc").hide();
    }
}
</script>
</head>
<body class="h-100 public">
<input type="hidden" value="<%= UserSessionUtil.getUserSession(request).getUser().getClient_type()%>" id="crole" />
<input type="hidden" id="dnum" value='<c:out value='${draftNum}' />' />
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">台账管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">车辆监管台账</a>
         </span>
</div>
<div class="public-main abs" style="top:50px;">
	<div class="ly-contai rel">
		<html:form action="/ledger/superviseImport" styleId="superviseImportForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="superviseLedger"/>
		<div class="public-main-input ly-col-5 hidden abs" style="height:240px;">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">经销商：</div>
                        <div class="input block fl hidden ">
                        	<%-- <form:select styleClass="ly-bor-none"  property="superviseImportquery.dealername" styleId="superviseImportquery.dealername" onchange="draftNumBox()" style="width:90%;margin-top:-1px; margin-left:0px;">
		                        <html:option value="">请选择</html:option>
		                        <html:optionsCollection name="dealersName" label="label" value="value"/>
		                    </form:select> --%>
		                    <div class="ly-sel-w">
			                    <select id="superviseImportquery.dealername" style="width:85%;" name="superviseImportquery.dealername" >
									<option value="">请选择</option>
									<c:forEach items="${dealersName }" var="row">
										<option <c:if test="${superviseImportForm.superviseImportquery.dealername==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
									</c:forEach>
								</select>
							</div>	
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
                   <%--  <div class="ly-col fl">
                        <div class="label block fl hidden">票号：</div>
                        <div class="input block fl hidden">
                            <form:select styleClass="ly-bor-none" style="width:80%" property="superviseImportquery.draft_num" styleId="superviseImportquery.draft_num" >
                                <c:if test="${draftNum !=null }">
                                    <html:option value="<c:out value='${draftNum}' />"><c:out value="${draftNum }" /></html:option>
                                </c:if>
                                <c:if test="${draftNum ==-1 }">
                                    <html:option value="-1">请选择</html:option>
                                </c:if>
                                <c:if test="${draftNum ==null}">
                                <html:option value="-1">请选择</html:option>
                                </c:if>
                            </form:select>
                        </div>
                    </div> --%>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">车架号：</div>
                        <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="superviseImportquery.vin" styleId="superviseImportquery.vin"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">品牌：</div>
                        <div class="input block fl hidden add_easyui">
                            <!-- <form:select styleClass="ly-bor-none" property="superviseImportquery.brandid" styleId="superviseImportquery.brandid">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="brandsOptions" label="label" value="value" />
							</form:select> -->
							 <select id="superviseImportquery.brandid" name="superviseImportquery.brandid" class="ly-bor-none" style="width:85%;">
								<option value="-1">请选择</option>
								<c:forEach items="${brandsOptions }" var="row">
									<option <c:if test="${superviseImportForm.superviseImportquery.brandid==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
								</c:forEach>
							</select>
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
                        <div class="label block fl hidden">车辆状态：</div>
                        <div class="input block fl hidden add_easyui">
							<!-- <form:select styleClass="ly-bor-none" property="superviseImportquery.state" styleId="superviseImportquery.state">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="superviseStateOptions" label="label" value="value" />
							</form:select> -->
							 <select id="superviseImportquery.state"name="superviseImportquery.state" class="ly-bor-none" style="margin-top:-1px;">
								<option value="-1">请选择</option>
								<c:forEach items="${superviseStateOptions }" var="row">
								<option <c:if test="${superviseImportForm.superviseImportquery.state==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
								</c:forEach>
							</select>
                        </div>
                    </div>
				</div>
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">金融机构：</div>
                        <div class="input block fl hidden">
							<html:text styleClass="ly-bor-none" property="superviseImportquery.bankname" styleId="superviseImportquery.bankname"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">品牌：</div>
                        <div class="input block fl hidden"><html:text styleClass="ly-bor-none" property="superviseImportquery.brandName" styleId="superviseImportquery.brandName"/></div>
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
				<div class="ly-row clearfix">
					<div class="ly-col fl" style="width:50%;">
                        <div class="label block fl hidden">合格证发证日期：</div>
                        <div class="input block fl hidden">
                        	<input style="width:40%" id="certificate_datebegin" name="superviseImportquery.certificate_datebegin" type="text" 
                        	value='<fmt:formatDate value="${superviseImportForm.superviseImportquery.certificate_datebegin }" pattern="yyyy-MM-dd" />' ></input>
                        	<span>至</span>
							<input style="width:40%" id="certificate_dateend" name="superviseImportquery.certificate_dateend" type="text" 
                        	value='<fmt:formatDate value="${superviseImportForm.superviseImportquery.certificate_dateend }" pattern="yyyy-MM-dd" />' ></input>
                        </div>
                    </div>
					<div class="ly-col fl" style="width:50%;">
                        <div class="label block fl hidden">入库时间：</div>
                        <div class="input block fl hidden">
                        	<input style="width:40%" id="storagetimebegin" name="superviseImportquery.storagetimebegin" type="text" 
                        	value='<fmt:formatDate value="${superviseImportForm.superviseImportquery.storagetimebegin }" pattern="yyyy-MM-dd" />' ></input>
                        	<span>至</span>
							<input style="width:40%" id="storagetimeend" name="superviseImportquery.storagetimeend" type="text" 
                        	value='<fmt:formatDate value="${superviseImportForm.superviseImportquery.storagetimeend }" pattern="yyyy-MM-dd" />' ></input>
                        </div>
                    </div>
                   
				</div>
				<div class="ly-row clearfix">
					 <div class="ly-col fl" style="width:50%;">
                        <div class="label block fl hidden">出库时间：</div>
                        <div class="input block fl hidden">
                       		<input style="width:40%" id="outtimebegin" name="superviseImportquery.outtimebegin" type="text" 
                        	value='<fmt:formatDate value="${superviseImportForm.superviseImportquery.outtimebegin }" pattern="yyyy-MM-dd" />' ></input>
                        	<span>至</span>
							<input style="width:40%" id="outtimeend" name="superviseImportquery.outtimeend" type="text" 
                        	value='<fmt:formatDate value="${superviseImportForm.superviseImportquery.outtimeend }" pattern="yyyy-MM-dd" />' ></input>
                        </div>
                    </div>
                    <div class="ly-col fl" style="width:50%;">
                        <div class="label block fl hidden">移动时间：</div>
                        <div class="input block fl hidden">
                        	<input style="width:40%" id="movetimebegin" name="superviseImportquery.movetimebegin" type="text" 
                        	value='<fmt:formatDate value="${superviseImportForm.superviseImportquery.movetimebegin }" pattern="yyyy-MM-dd" />' ></input>
                        	<span>至</span>
							<input style="width:40%" id="movetimeend" name="superviseImportquery.movetimeend" type="text" 
                        	value='<fmt:formatDate value="${superviseImportForm.superviseImportquery.movetimeend}" pattern="yyyy-MM-dd" />' ></input>
                        </div>
                    </div>
				</div>
			</div>
			<div class="ly-button-w">
                <a href="javascript:doQuery();" class="button btn-query" style="margin-left:50%;">查询</a>
                <a href="javascript:doClear();" class="button btn-reset">重置</a>
                 <c:if test="${list!=null&&summary!=null&&summary.count!=0 }">
						<span style="position: absolute;top:84%;left:0; font-size: 14px;">
						台数:<c:out value="${summary.count }"/>
						&nbsp;&nbsp;&nbsp;车价总金额：<fmt:formatNumber value="${summary.carMoney }" pattern="#.##" minFractionDigits="2" />
						&nbsp;&nbsp;&nbsp; 回款总金额： <fmt:formatNumber value="${summary.carPaymentAmount }" pattern="#.##" minFractionDigits="2" />
						</span>
				 </c:if>
            </div>
		</div>
		<div class="public-main-table hidden abs" style="top:240px;">
			<div class="ly-cont">
				<table class="t-table" border="0" cellspacing="0" cellpadding="0">
					<thead class="t-thead">
						<tr class="t-tr">
							<th class="t-th">序号</th>
							<th class="t-th">经销商</th>
						    <th class="t-th">金融机构</th>
							<th class="t-th">品牌</th>
							<th class="t-th">票号</th>
							<th class="t-th">开票金额</th>
							<th class="t-th">开票日</th>
							<th class="t-th">到期日</th>
							<th class="t-th">合格证发证日期</th>
							<th class="t-th">合格证号</th>
							<th class="t-th">车辆型号</th>
							<th class="t-th">车身结构</th>
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
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td"><c:out value="${index+1}"/></td>
								<td class="t-td"><c:out value="${row.dealerName}" /></td>
								<td class="t-td"><c:out value="${row.bankFullName}" /></td>
								<td class="t-td"><c:out value="${row.brandName}" /></td>
								<td class="t-td"><c:out value="${row.draft_num}" /></td>
								<td class="t-td"><select:draft draftid="0" draftNum="${row.draft_num}" idtype="billing_money"/></td>
								<td class="t-td"><select:draft draftid="0" draftNum="${row.draft_num}" idtype="billing_date"/></td>
								<td class="t-td"><select:draft draftid="0" draftNum="${row.draft_num}" idtype="due_date"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.certificate_date}" idtype="date"/></td>
								<td class="t-td"><c:out value="${row.certificate_num}" /></td>
								<td class="t-td"><c:out value="${row.car_model}" /></td>
								<td class="t-td"><c:out value="${row.car_structure}" /></td>
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
			<a href="javascript:goExt();" class="button btn-add fl yc">导出监管车辆台账</a>
			<div class="public-main-footer-pagin fr">
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="superviseLedger" action="/ledger/superviseImport.do?method=superviseLedger"/>
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
