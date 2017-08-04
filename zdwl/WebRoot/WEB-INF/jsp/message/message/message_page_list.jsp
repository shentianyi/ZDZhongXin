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
<script type="text/javascript" src="/js/jquery.divscroll.js"></script>
<script type="text/javascript">

	function messageRead(id){
		$.getJSON("/json/messageRead.do?callback=?&id="+id, function(json){
			  console.info(json);
		});
	}
	function goRead(isread){
		location.href="/message.do?method=messagePageList&messagequery.isread="+isread;
	}
	
	//执行查询操作
	function doQuery() {
	   $("#method").val("messagePageList");
		document.forms[0].submit();
	}

	//执行表单清空操作
	function doClear() {
		$("[name='messagequery.deptName']").val("");
		$("[name='messagequery.typeName']").val("");
	}
	
	//将选中标记为已读
	function choiceReaded(){
	   var chk_value =[]; 
	    $('input[name="ids"]:checked').each(function(){ 
	        var num = $(this).val();
	        chk_value.push(num); 
	    }); 
	    var ids=chk_value.join(",");
	    console.log(ids);
	    if(ids.length==0){
	        alert("系统提示：请选择您要标记已读的信息！");
	    }else{
	        if(confirm("系统提示：确定标记为已读？？？")){
	            //路径
	            if(ids!=null && ids!=""){
	               submitMessageToReaded(ids);
	            }
	        }
	    }
	}
	
	//全部已读
	function readAll(){
       /* var chk_value =[]; 
       var list = $('input[type="checkbox"][name="ids"]');
       for(var i=0;i<list.length && list[i];i++){
            chk_value.push(list[i].value); 
       }
        var ids=chk_value.join(",");
        if(ids!=null && ids!=""){
            alert(ids);
           submitMessageToReaded(ids);
        } */
        $("#method").val("newsReadedAll");
        document.forms[0].submit();
    }
    
	//将选中的消息提交到后台
	function submitMessageToReaded(ids){
	   $.ajax({
	        type:"post",
	        url:"/message.do?method=newsReaded",
	        data:{"ids":ids},
	        success:function(data){
	           //alert(data);
	           $("#message").html(data);
	           location.reload(true); 
	        },
	        error:function(data){}
	    });
	}
	$(function(){
	//限制超级角色操作
	    restrict();
	});
	
	function restrict(){
	    var crole = $("#crole").val();
	    if(crole == 30){
	        $(".yc").hide();
	    }
	}
	function selectAll(flag){
		if (flag){
		  $("input[type=checkbox]:gt(0)").attr("checked", "checked");
		} else {
		  $("input[type=checkbox]:gt(0)").removeAttr("checked");
		}
    }

	function oneCheck(id){
		var $subBox = $("input[type=checkbox]:gt(0)");
		$("#"+id).attr("checked",$subBox.length == $("input[type=checkbox]:gt(0)").length ? true : false);
	}
</script>
</head>
<body class="h-100 public" onload="mynav()">
<input type="hidden" value="<%= UserSessionUtil.getUserSession(request).getUser().getClient_type()%>" id="crole" />
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#"></a>
     </span>
</div>
	<div class="public-main abs" style="margin-top: 40px;">
		<div class="ly-contai rel">
			<html:form action="/message" styleId="messageForm" method="post" onsubmit="return false">
				<html:hidden property="messagequery.type" styleId="messagequery.type" /> 
				<input name="method" id="method" type="hidden" value="messagePageList"/>
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
		
                     
					<div class="ly-button-w" style="margin-top: -70px;margin-left: 24%;">
					<c:choose>
					<c:when test="${type != 18 }">
                         <a href="javascript:choiceReaded();" class="button">将选中标记为已读</a>
                        <a href="javascript:readAll();" class="button">全部已读</a> 
					</c:when>
					</c:choose>
                        <a href="javascript:doQuery();" class="button btn-query">查询</a> 
                        <a href="javascript:doClear();" class="button btn-reset">重置</a>
                        
                    </div>
				</div>
				<div class="public-main-table hidden abs" style="top: 9px;">
					<div class="ly-cont">
						<table class="t-table" border="0" cellspacing="0" cellpadding="0">
							<thead class="t-thead">
								<tr class="t-tr">
								    <th><input type="checkbox" id="all" onclick="selectAll(this.checked)" /></th>
							      	<th class="t-th">序号</th>
							      	<th class="t-th">部门</th>
									<th class="t-th">消息类别</th>
									<th class="t-th">数量</th>
								</tr>
							</thead>
							<tbody class="t-tbody hidden">
								<logic:iterate name="list" id="row" indexId="index">
									<tr class="t-tr">
									    <td class="t-td"><input type="checkbox" name="ids" value="<c:out value='${row.id}'/>" onclick="oneCheck('all')" /></td>
										<td class="t-td"><c:out value="${index+1}" /></td>
										<td class="t-td"><c:out value="${row.deptName}" /></td>
										<td class="t-td"><select:msgtype mid="${row.type}"></select:msgtype></td>
										<td class="t-td">
											<a href="<c:out value='${row.url}'/>&parentId=<c:out value='${row.id}'/>"
												onclick="messageRead('<c:out value="${row.id }"/>')">
												<font <c:if test="${row.isread == 1}">color="red"</c:if>>
													<%-- <c:out value="${row.name}" /> --%>
													<c:choose>
                                                        <c:when test="${type == 18 }">
														  <c:out value="${row.num}" />
														</c:when>
													</c:choose>
													<c:choose>
                                                        <c:when test="${type != 18 }">
                                                            <c:out value="${row.name}" />
                                                        </c:when>
                                                    </c:choose>
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
			         <div id="message"></div>
					<div class="public-main-footer-pagin fr">
						<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="messagePageList" action="/message.do?method=messagePageList"/>
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
