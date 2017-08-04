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
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
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
	var dateboxOptions={"editable":false}
	
	
	$('#storagetimebegin').datebox(dateboxOptions);  
	$('#storagetimeend').datebox(dateboxOptions);
	
}
//执行删除操作
/* function doDel(id){
	if(confirm("删除后数据不可恢复\n是否继续？")){
		location="<url:context/>/superviseImport.do?method=delSuperviseImport&superviseImport.id="+id;
	}
} */

//批量删除数据
function deleteAll() {
 var idStr="";
 if ($("input[type=checkbox]:checked").size() <= 0) {
  alert("你没有选择删除，请先选择");
  return;
 }
 if (confirm("是否确认删除!")) {
 if(confirm("请再次确认是否删除!")){
  $("input[type=checkbox]:checked").each(function() {
   if (idStr != "") {
    idStr += ",";
   }
   idStr += $(this).val();
  });
   window.location="<url:context/>/superviseImport.do?method=batchDelSuperviseImport&ids="+idStr;
 
 }else{return;}
 
  
 } else {
  return;
 }
}
//全选
function allCheck(){
	var rowid=document.getElementsByName("rowid");
	
	for(var i=0;i<rowid.length;i++){
		rowid[i].checked=true;
		}
	}
function clearCheck(){
	var rowid=document.getElementsByName("rowid");
	
	for(var i=0;i<rowid.length;i++){
		rowid[i].checked=false;
		}
	}
function checkFalse(){

    var checkFlag = true;
    var rowid=document.getElementsByName("rowid");
  
    for(var i=0;i<rowid.length;i++){
        if(!rowid[i].checked){
            checkFlag = false;
            break;
        }
    }
    if(!checkFlag){
        document.getElementById("quanxuan").checked = false;
    }else{
        document.getElementById("quanxuan").checked = true;
    }

}

//执行查询操作
function doQuery(){
	$("#method").val("searchCarInfo");
	document.forms[0].submit();
} 
//执行表单清空操作
function doClear(){
	//清空资源名输入框
	 $('#certificate_datebegin').datebox('clear');
	$('#certificate_dateend').datebox('clear'); 
	
	$('#storagetimebegin').datebox('clear');
	$('#storagetimeend').datebox('clear');	
	$("#superviseImportquery\\.vin").val("");
	// $("#_easyui_textbox_input1").val("");
	$("#superviseImportquery\\.dealername").val("");
	$("#superviseImportquery\\.dealername").combobox('select',-1);
}

function goImport(){
	location="<url:context/>/superviseImport.do?method=importExcelEntry";
}
$(function(){
	var msg ="<c:out value='${message}'/>";
	if(msg){
		alert(msg);
	}
});

</script>
</head>
<body class="h-100 public" onLoad="doLoad();">
<div class="public-bar hidden">
	<div class="ly-contai clearfix">
		<div class="public-bar-crumbs fl hidden">
            <a class="crumbs-link" href="#">监管物管理</a>
            > 
            <a class="crumbs-link" href="#">车辆管理</a>
        </div>
	</div>
</div>
<div class="public-main abs" >
	<div class="ly-contai rel">
		<html:form action="/superviseImport" styleId="superviseImportForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="searchCarInfo"/>
		<div class="public-main-input ly-col-5 hidden abs" style="height:240px;">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">经销商：</div>
                        <div class="input block fl hidden">
                        	  <!-- <html:text styleClass="ly-bor-none" property="superviseImportquery.dealername" styleId="superviseImportquery.dealername"/>  -->
                        	  <div class="ly-sel-w">
	                        		<select class="easyui-combobox"  id="superviseImportquery.dealername" name="superviseImportquery.dealername" 
	                        		style="width:85%;" >
							  			<option value="-1">请选择...</option>
							  			<c:forEach items="${dealersOptions}" var="row">
							  				<option <c:if test="${superviseImportForm.superviseImportquery.dealername==row.value }">
							  				selected='selected'</c:if> 
							  				value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
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
				
			</div>
			<div class="ly-button-w" >
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
							<input id="quanxuan" type="checkbox" name="type" value="" onClick="if(this.checked==true){allCheck()}else{clearCheck()}" />全选
							</th>
							<th class="t-th">经销商</th>								
								<th class="t-th">品牌</th>
								<th class="t-th">金融机构</th>
								<th class="t-th">车架号</th>
								<th class="t-th">入库时间</th>
								<th class="t-th">车辆状态</th>
								<!-- <th class="t-th">操作</th> -->
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td"><input type="checkbox"  value='<c:out value="${row.id}"/>' name="rowid" onClick="checkFalse()"/></td>
										<td class="t-td"><c:out value="${row.dealerName}" /></td>
										<td class="t-td"><c:out value="${row.brandName}" /></td>
									<td class="t-td"><c:out value="${row.bankFullName}" /></td>									
									<td class="t-td"><c:out value="${row.vin}" /></td>
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
										<%-- <td class="t-td">
										<a class="link" href="javascript:goUpd('<c:out value='${row.id}'/>');">修改</a>&nbsp;
										<a class="link" href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>
									</td> --%>
								</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		</div>
		<div class="public-main-footer hidden abs">
			<a href="javascript:deleteAll();" class="button btn-add fl">删除</a>
			<div class="public-main-footer-pagin fr">
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="searchCarInfo" action="/superviseImport.do?method=searchCarInfo"/>
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
                    //template += '<div class="block fl"><input type="checkbox" /></div>';
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
