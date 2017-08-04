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

<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
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
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//进入新增页面
function goAdd(){
	window.location="<url:context/>/handoverLog.do?method=addHandoverLogEntity";
}


 function goSaveSubmit(id,obj){
	$.ajax({
		type:"POST",
		url:"/handoverLog.do?method=saveSubmit",
		dataType:"text",
		data:"handoverLog.id="+id,
		success:function(result){
			alert(result);
			$("#ycc").hide();
			if (result == '通知提交成功') {
				var label = obj.parentNode;
				var tr = label.parentNode;
				var child =tr.childNodes;	
			 	child[29].innerHTML="未提交";	
				
			}
		}
	
	});
	
} 



//进入修改页面
function goUpd(id){
	window.location="<url:context/>/handoverLog.do?method=updHandoverLogEntity&handoverLog.id="+id;
}

//下载交接书
function downloadHandoverBook(id){
	window.location="<url:context/>/handoverLog.do?method=jjsDownLoad&handoverLog.id="+id;

}
//上传交接书图片
function uploadHandoverBook(id){
	window.location="<url:context/>/handoverLog.do?method=upLoadHandoverBookPicEntity&handoverLog.id="+id;
}

//下载交接书图片
function downloadHandoverPic(id,name){
	window.location = "<url:context/>/handoverLog.do?method=downLoadPathFile&hpic.id="+id+"&distribName="+name;
}



//执行返回列表操作
function submitHandoverBook(id){
	if(!this.doCheckPicExists(id)){
		showMessage("请先上传交接书图片再提交");
		return;
	}
	if(confirm("确认提交？提交后交接书将不可编辑，进入审批流程")){
		window.location="<url:context/>/handoverLog.do?method=updHandoverLogEditStatus&handoverLog.id="+id;
	}
}
//已审批列表
function goAlreadyApproval(){
	location.href="<url:context/>/handoverLog.do?method=handoverLogPageList&query.pageType=2";
}
//进入审批页面
function approval(id){
	location.href="<url:context/>/handoverLog.do?method=preApproval&handoverLog.id="+id;
}
//进入私自售卖车辆列表页
function goPrivateSellCarList(id){
	window.location="<url:context/>/handoverLog.do?method=getPrivateSellCarListByhandoverLogId&handoverLog.id="+id;
}
//进入私自移动车辆列表页
function goPrivateMoveCarList(id){
	window.location="<url:context/>/handoverLog.do?method=getPrivateMoveCarListByhandoverLogId&handoverLog.id="+id;
}
//修改电子文本资料
function updetext(id){
	window.location="<url:context/>/handoverLog.do?method=updetext&handoverLog.id="+id;
}
//修改纸质文本资料
function updptext(id){
	window.location="<url:context/>/handoverLog.do?method=updptext&handoverLog.id="+id;
}
//其他资料
function updotherData(id){
	window.location="<url:context/>/handoverLog.do?method=updotherData&handoverLog.id="+id;
}
function updofficeEquipment(){
	window.location="<url:context/>/handoverLog.do?method=updofficeEquipment&handoverLog.id="+id;
}
//进入修改交接书页面
function updHandoverBook(id){
	window.location="<url:context/>/handoverLog.do?method=updHandoverBook&handoverLog.id="+id;
}

//进入详情页面
function goDetail(id){
	location.href="<url:context/>/handoverLog.do?method=handoverLogDetail&handoverLog.id="+id;
}
//执行删除操作
function doDel(id){
	if(confirm("确认删除？")){
		window.location="<url:context/>/handoverLog.do?method=delHandoverLog&handoverLog.id="+id;
	}
}
//执行提交操作
function doSubmit(id){
	if(confirm("确认提交？提交后将不可编辑，进入制作交接书流程")){
		window.location="<url:context/>/handoverLog.do?method=updHandoverLogEditStatus&handoverLog.id="+id;
	}
}
//执行查询操作
function doQuery(){
	document.forms[0].submit();
}
//导出
function goExport(){
	var deliverer=document.getElementById("queryDeliverer").value;
	var arr = document.getElementsByName("query.deliverer");
	for(var i=0;i<arr.length;i++)
	    {
	        if(arr[i].checked)
	        {    
	          value=arr[i].value;
	        }
	    }
	var receiver=document.getElementById("queryReceiver").value;
	var dealerId=document.getElementById("queryDealerId").value;
	window.location="<url:context/>/handoverLog.do?method=extExcel"
		+"&query.deliverer="+deliverer+"&query.receiver="+receiver
		+"&query.dealerId="+dealerId;
}
//执行表单清空操作
function doClear(){
	$("#queryDeliverer").combobox('select',-1);
	$("#queryReceiver").combobox('select',-1);
	$("#queryDealerId").combobox('select',-1);
}
function changeSupervisor(id) {
	if(id==-1){
		document.forms[0].reset();
		return;
	}
	var url = "../json/getSupervisorById.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
	});
}
function init() {
	$("#queryDeliverer").combobox({
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
			}

		}
	});
	var draftValue = $("#queryDeliverer").combobox('getValue');
	if (draftValue) {
	} 
	$("#queryReceiver").combobox({
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
			}

		}
	});
	var draftValue = $("#queryReceiver").combobox('getValue');
	if (draftValue) {
	} 
	$("#queryDealerId").combobox({
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
			}

		}
	});
}

function doCheckPicExists(id){
	var flag;
	 $.ajax({
		type:"POST",
		url:"/handoverLog.do?method=checkHandoverBookPicExists",
		dataType:"JSON",
		data:"id="+id,
		async: false,
		success:function(result){
			if(parseInt(result.num) > 0){
				flag = true;
			}else{
				flag = false;
			}
		}
	
	});
	
	return flag;
	
}

$(function(){
	init();
	//限制超级角色操作
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
            <a class="crumbs-link" style="color:#929291;" href="#">监管员信息管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">交接记录管理</a>
         </span>
</div>

<c:set var="using"><%=StateConstants.USING.getCode()%></c:set>
<c:set var="unused"><%=StateConstants.UNUSED.getCode()%></c:set>
<c:set var="menu"><%=RbacConstants.RESOURCE_NODE_TYPE_MENU.getCode()%></c:set>
<c:set var="node"><%=RbacConstants.RESOURCE_NODE_TYPE_NODE.getCode()%></c:set>
<div class="public-bar hidden">
	<div class="ly-contai clearfix">
        <div class="public-bar-tab fr hidden clearfix">
            <div class="ly-button-w fr">
                <a class="button btn-sel fl" href="javascript:void(0);">待审批</a>
                <a class="button fl" href="javascript:goAlreadyApproval();">已审批</a>
            </div>
        </div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/handoverLog" styleId="handoverLogForm" method="post" onsubmit="return false">
			<input name="method" id="method" type="hidden" value="handoverLogPageList"/>
			<div class="public-main-input ly-col-1 hidden abs">
				<div class="ly-input-w" style="margin-top:30px;margin-bottom:-10px">
					<div class="ly-row clearfix">
						<div class="ly-col fl">
	                        <div class="label block fl hidden">交付人姓名：</div>
	                        <div class="input block fl hidden">
	                        	<div class="ly-sel-w" >
	                        		<select style="width:99%" id="queryDeliverer" name="query.deliverer" >
										<option value="-1">请选择</option>
										<c:forEach items="${supervisors }" var="row">
											<option <c:if test="${handoverLogForm.query.deliverer==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
										</c:forEach>
									</select>
	                        	</div>
	                        </div>
	                    </div>
	                    <div class="ly-col fl">
	                        <div class="label block fl hidden">接收人姓名：</div>
	                        <div class="input block fl hidden">
	                        	<div class="ly-sel-w">
	                        		<select style="width:99%" id="queryReceiver" name="query.receiver" >
										<option value="-1">请选择</option>
										<c:forEach items="${supervisors }" var="row">
											 <option <c:if test="${handoverLogForm.query.receiver==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
										</c:forEach>
									</select>
	                        	</div>
	                        </div>
	                    </div>
	                   <div class="ly-col fl">
	                        <div class="label block fl hidden">经销商名称：</div>
	                        <div class="input block fl hidden">
	                        	<div class="ly-sel-w">
	                        		<select style="width:99%" id="queryDealerId" name="query.dealerId" >
										<option value="-1">请选择</option>
										<c:forEach items="${dealers }" var="row">
											<option <c:if test="${handoverLogForm.query.dealerId==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
										</c:forEach>
									</select>
	                        	</div>
	                        </div>
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
								<th class="t-th">序号</th>
								<th class="t-th">经销商名称</th>
								<th class="t-th">交付人姓名</th>
								<th class="t-th">交付人交付性质</th>
								<th class="t-th">交付人离岗时间</th>
								<th class="t-th">接收人姓名</th>
								<th class="t-th">接收人接收性质</th>
								<th class="t-th">接收人交接后属性</th>
								<th class="t-th">接收人上岗时间</th>
								<th class="t-th">交接时间</th>
								<th class="t-th">创建人</th>
								<th class="t-th">创建时间</th>
								<th class="t-th">最后更新人</th>
								<th class="t-th">最后更新时间</th>
								<th class="t-th">审批状态</th>
								<th class="t-th">下一审批人</th>
						      	<th class="t-th">操作</th>
							</tr>
						</thead>
						<tbody class="t-tbody hidden">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr" >
									<td class="t-td"><c:out value="${index+1}"/></td>
									<td class="t-td"><select:dealerName dealerid="${row.dealerId}"/></td>
									<td class="t-td"><select:supervisorName repositoryId="-1" idtype="name" supervisorId="${row.deliverer}"/></td>
									<td class="t-td">
										<c:choose>  
										   <c:when test="${row.handoverNature==1}">辞职</c:when>  
										   <c:when test="${row.handoverNature==2}">辞退  </c:when>  
										   <c:when test="${row.handoverNature==3}">正常轮岗</c:when> 
										   <c:when test="${row.handoverNature==4}">投诉轮岗</c:when>  
										   <c:when test="${row.handoverNature==5}">撤店</c:when>
										</c:choose>  
									</td>
									<td class="t-td"><select:timestamp timestamp="${row.dimissionDate}" idtype="date"/></td>
									<td class="t-td"><select:supervisorName  repositoryId="-1"idtype="name" supervisorId="${row.receiver}"/></td>
									<td class="t-td">
										<c:choose>  
										   <c:when test="${row.receiveNature==1}">轮岗</c:when>  
										   <c:when test="${row.receiveNature==2}">新入职  </c:when>  
										   <c:when test="${row.receiveNature==3}">二次上岗</c:when> 
										</c:choose>  
									</td>
									<td class="t-td">
										<c:choose>  
										   <c:when test="${row.afterHandoverNature==1}">属地</c:when>  
										   <c:when test="${row.afterHandoverNature==2}">外派</c:when>  
										</c:choose> 
									</td>
									<td class="t-td"><select:timestamp timestamp="${row.missionDate}" idtype="date"/></td>
									<td class="t-td"><select:timestamp timestamp="${row.handoverDate}" idtype="date"/></td>
									<td class="t-td"><select:user userid="${row.createUserId }"></select:user></td>
									<td class="t-td"><select:timestamp timestamp="${row.createTime}" idtype="date"/></td>
									<td class="t-td"><select:user userid="${row.lastModifyUser }"></select:user></td>
									<td class="t-td"><select:timestamp timestamp="${row.lastModifyTime}" idtype="date"/></td>
									<td class="t-td" name="tagName"><c:out value="${row.approveStatusStr}" /></td>
									<td class="t-td"><c:out value="${row.nextApprovalStr}" /></td>
									<td class="t-td">
										<a href="javascript:goDetail('<c:out value='${row.id}'/>');">详情</a>&nbsp;
										<c:choose>
											<c:when test="${role == '5' && row.approveStatusStr == '未通知' }">
												<a href="#" onclick="javascript:goSaveSubmit('<c:out value="${row.id}"/>',this)" id="ycc" class="yc">提交</a>
											</c:when>
										</c:choose> 
										<c:choose>
											<c:when test="${(role == '5'||role=='80')&& row.isEdit=='0'}">
												<a href="javascript:goUpd('<c:out value='${row.id}'/>');" class="yc">修改</a>&nbsp;
												<a href="javascript:doDel('<c:out value='${row.id}'/>');" class="yc">删除</a>&nbsp;
											</c:when>
										</c:choose>
										<c:choose>
											<c:when test="${(role == '10'||role=='80')&& row.isEdit=='0'}">
												<a  href="javascript:updHandoverBook('<c:out  value='${row.id}'/>');" class="yc">修改交接书</a>&nbsp;
												<a  href="javascript:submitHandoverBook('<c:out  value='${row.id}'/>');" class="yc">提交交接书</a>&nbsp;
											</c:when>
										</c:choose>
										<c:choose>
											<c:when test="${(role == '14'||role=='80' )&& row.isEdit=='1'}">
												<a href="javascript:approval('<c:out value="${row.id }"/>')" class="yc">审批</a>
											</c:when>
											<c:when test="${ role == '16'  && row.isEdit=='1'}">
												<a href="javascript:approval('<c:out value="${row.id }"/>')" class="yc">审批</a>
											</c:when>
											<c:when test="${ role == '3'  && row.isEdit=='1'}">
												<a href="javascript:approval('<c:out value="${row.id }"/>')" class="yc">审批</a>
											</c:when>
											<c:when test="${role == '9'  && row.isEdit=='1'}">
												<a href="javascript:approval('<c:out value="${row.id }"/>')" class="yc">审批</a>
											</c:when>
											<c:when test="${role == '28'  && row.isEdit=='1'}">
												<a href="javascript:approval('<c:out value="${row.id }"/>')" class="yc">审批</a>
											</c:when>
										</c:choose>
										<c:choose>
											<c:when test="${role == '10'||role=='80'}">
												<a  href="javascript:downloadHandoverBook('<c:out  value='${row.id}'/>');" class="yc">下载交接书</a>&nbsp;
											</c:when>
										</c:choose>
											<c:choose>
											<c:when test="${row.isEdit=='1'}">
												<a  href="javascript:downloadHandoverPic('<c:out  value='${row.id}'/>','<select:dealerName dealerid="${row.dealerId}"/>');" class="yc">下载交接书图片</a>&nbsp;
											</c:when>
										</c:choose>
										<c:choose>
											<c:when test="${(role == '10'||role=='80')&& row.isEdit=='0'}">
												<a  href="javascript:uploadHandoverBook('<c:out  value='${row.id}'/>');" class="yc">上传交接书图片</a>&nbsp;
											</c:when>
											<%-- <c:when test="${role == '10'&& row.nextApprovalStr=='监管员管理部综合专员'}"> --%>
											
											<%-- <c:when test="${role=='80'&& row.nextApprovalStr=='监管员管理部综合专员'}"> --%>
											
										</c:choose>
										
										
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
			<div class="public-main-footer hidden abs">
				<c:choose>
					<c:when test="${role == '5'}">
						<a href="javascript:goAdd();" class="button btn-add fl yc">新增</a>
					</c:when>
				</c:choose>
				<a href="javascript:goExport();" class="button btn-add fl yc">导出</a>
				<div class="message" id="message" style="display:none;color: red"></div>
				<div class="public-main-footer-pagin fr">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="handoverLogList" action="/handoverLog.do?method=handoverLogPageList"/>
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