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

<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>
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
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	limit();
}

//限制
function limit(){
	if(getElement("userQuery.flag").value==1){
		var options = getElement("userQuery.clienttype").options;
		for(var i = 0; i < options.length;i++){
			if(options[i].value!=3){
				options[i].remove(options[i].value);
				i--;
			}
		}
	}

}

//进入新增页面
function goAdd(){
	window.location="<url:context/>/rbac/user.do?method=addUserEntry&userQuery.flag="+getElement("userQuery.flag").value;
}

//进入修改页面
function goUpd(id){
	window.location="<url:context/>/rbac/user.do?method=updUserEntry&user.id="+id+"&userQuery.flag="+getElement("userQuery.flag").value;
}

//进入为账户分配角色页面
function setRole(userId){
	window.location="<url:context/>/rbac/role.do?method=roleListWithUser&userId="+userId+"&<thumbpage:setQueryFlag tableName="roleListWithUser" out="true"/>";
}

//执行删除操作
function doDel(id){
	if(confirm("确认删除？")){
		window.location="<url:context/>/rbac/user.do?method=delUser&user.id="+id;
	}
}

//执行启用操作
function doUsing(id){
	if(confirm("确认启用？")){
		window.location="<url:context/>/rbac/user.do?method=updUserState&user.id="+id+"&user.state=<%=StateConstants.USING.getCode()%>";
	}
}

//执行停用操作
function doUnused(id){
	if(confirm("确认停用？")){
		window.location="<url:context/>/rbac/user.do?method=updUserState&user.id="+id+"&user.state=<%=StateConstants.UNUSED.getCode()%>";
	}
}

//执行注销操作
function doStateDel(id){
	if(confirm("确认注销？")){
		window.location="<url:context/>/rbac/user.do?method=updUserState&user.id="+id+"&user.state=<%=StateConstants.DELETE.getCode()%>";
	}
}

//执行查询操作
function doQuery(){
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	//清空登录名输入框
	getElement("userQuery.loginid").value="";
	//清空姓名输入框
	getElement("userQuery.userName").value="";
	//清空状态复选框
	var states = document.getElementsByName("userQuery.states");
	for(var i = 0; i<states.length; i++){
		states[i].checked = false;
	}
	getElement("userQuery.clienttype").value=-1;
}

function changeClientType(){

	var clientType = getElement("userQuery.clientType");
	if(clientType.value==5){
		document.getElementById("ri").style.display = "block";
	} else{
		document.getElementById("ri").style.display = "none";
	}
	
}

$(function(){
	$("#userQuery\\.clienttype").combobox({
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
<c:set var="undone"><%=StateConstants.UNDONE.getCode()%></c:set>
<c:set var="using"><%=StateConstants.USING.getCode()%></c:set>
<c:set var="unused"><%=StateConstants.UNUSED.getCode()%></c:set>
<div class="add_nav">
		<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">系统管理与配置</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">账户管理</a>
         </span>
	</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/rbac/user" styleId="userForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="userList"/>
		<html:hidden property="userQuery.flag" styleId="userQuery.flag"/>
		<div class="public-main-input ly-col-1 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">登录名：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="userQuery.loginid" styleId="userQuery.loginid"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">用户名：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="userQuery.userName" styleId="userQuery.userName"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">账户状态：</div>
                        <div class="input block fl hidden">
                            <form:checkboxs property="userQuery.states" styleId="userQuery.states" collection="userStateOptions"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">账户类型：</div>
                        <div class="input block fl hidden add_easyui">
                        	<!-- <form:select styleClass="ly-bor-none" property="userQuery.clienttype" styleId="userQuery.clienttype" onchange="changeClientType()">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="clientTypeOptions" label="label" value="value"/>
							</form:select> -->
							<select id="userQuery.clienttype" name="userQuery.clienttype" style="width:60%;">
										<option value="-1">请选择</option>
										<c:forEach items="${clientTypeOptions }" var="row">
											<option <c:if test="${userForm.userQuery.clienttype==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
										</c:forEach>
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
				<table class="t-table" border="0" cellspacing="0" cellpadding="0">
					<thead class="t-thead">
						<tr class="t-tr">
							    <th class="t-th">序号</th>
							    <th class="t-th">登录名</th>
								<th class="t-th">用户名</th>
								<th class="t-th">账户类型</th>
								<th class="t-th">角色类型</th>
								<th class="t-th">账户状态</th>
						        <th class="t-th yc">操作</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td"><c:out value="${index+1}"/></td>
								<td class="t-td"><c:out value="${row.loginid}"/></td>
								<td class="t-td"><c:out value="${row.userName}"/></td>
								<td class="t-td"><select:clientTypeName clientType="${row.client_type}"/></td>
								<td class="t-td"><select:roleName clientType="${row.id}" roleCode="-1"/></td>
								<td class="t-td"><select:stateName state="${row.state}"/></td>
								<td class="t-td yc">
									<a href="javascript:setRole('<c:out value='${row.id}'/>');">分配角色</a>&nbsp;
									<c:if test="${row.state==using}">
									<a href="javascript:doUnused('<c:out value='${row.id}'/>');">停用</a>&nbsp;
									</c:if>
									<c:if test="${row.state!=using}">
									<a href="javascript:doUsing('<c:out value='${row.id}'/>');">启用</a>&nbsp;
									</c:if>
						
									<a href="javascript:goUpd('<c:out value='${row.id}'/>');">修改</a>&nbsp;
						
									<c:if test="${row.state==undone}">
									<a href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>
									</c:if>
									<c:if test="${row.state==using}">
									<a href="javascript:void(0)" disabled="true">注销</a>
									</c:if>
									<c:if test="${row.state==unused}">
									<a href="javascript:doStateDel('<c:out value='${row.id}'/>')">注销</a>
									</c:if>
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		</div>
		<div class="public-main-footer hidden abs">
			<a href="javascript:goAdd();" class="button btn-add fl yc">新增</a>
			<div class="public-main-footer-pagin fr">
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="userList" action="/rbac/user.do?method=userList"/>
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