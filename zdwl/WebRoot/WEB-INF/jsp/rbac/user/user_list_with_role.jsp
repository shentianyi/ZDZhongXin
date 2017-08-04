<%@ page language="java" pageEncoding="UTF-8"%>
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
<script language="JavaScript">
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//执行删除操作
function doDel(){
	//检查是否选择了要删除的账户
	if(hasCheckedItem("userIds")){
		if(confirm("确定删除?")){
			//将表单提交至addUserRole方法
			getElement("method").value="delUserRole";
			document.forms[0].submit();
		}
	} else{
		showMessage("请选择要删除的人员!");
	}
}

//进入角色下已分配账户页面
function goUserWithoutRole(){
	location = "<url:context/>/rbac/user.do?method=userListWithoutRole&roleId=<c:out value="${roleId}"/>&<thumbpage:setQueryFlag tableName="userListWithoutRole" out="true"/>";
}

//返回到角色维护页面
function doReturn(){
	location = "<url:context/>/rbac/role.do?method=roleList";
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
	
	//清空账户状态复选框
	var elements = document.getElementsByName("userQuery.states");
	for(var i = 0; i < elements.length; i++){
		elements[i].checked = false;
	}
}
</script>
</head>
<body class="h-100 public" onLoad="doLoad()">
<c:set var="undone"><%=StateConstants.UNDONE.getCode()%></c:set>
<c:set var="using"><%=StateConstants.USING.getCode()%></c:set>
<c:set var="unused"><%=StateConstants.UNUSED.getCode()%></c:set>
<div class="public-bar hidden">
	<div class="ly-contai clearfix">
        <div class="public-bar-tab fr hidden clearfix">
            <div class="ly-button-w fr">
                <a class="button btn-sel fl" href="javascript:void(0);">已保存账户</a>
                <a class="button fl" href="javascript:goUserWithoutRole();">添加新账户</a>
            </div>
        </div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/rbac/user" styleId="userForm" method="post" onsubmit="return false">
			<input type="hidden" name="method" id="method" value="userListWithRole" />
			<input type="hidden" name="roleId" id="roleId" value="<c:out value="${roleId}"/>"/>
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
	                        <div class="label block fl hidden">人员状态：</div>
	                        <div class="input block fl hidden">
	                        	<form:checkboxs property="userQuery.states" styleId="userQuery.states" collection="userStateOptions"/>
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
								<th class="t-th">
									<label for="checkAllValue"></label>
									<input type="checkbox" name="checkAllValue" id="checkAllValue" class="checkbox_title" onClick="checkAll('userIds')" >
								</th>
								<th class="t-th">序号</th>
								<th class="t-th">登录名</th>
								<th class="t-th">用户名</th>
								<th class="t-th">账户类型</th>
								<th class="t-th">状态</th>
							</tr>
						</thead>
						<tbody class="t-tbody hidden">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><input type="checkbox" id="userIds" name="userIds" value="<c:out value='${row.id}'/>" /></td>
									<td class="t-td"><c:out value="${index+1}"/></td>
									<td class="t-td"><c:out value="${row.loginid}"/></td>
									<td class="t-td"><c:out value="${row.userName}"/></td>
									<td class="t-td"><select:clientTypeName clientType="${row.client_type}"/></td>
									<td class="t-td"><select:stateName state="${row.state}"/></td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
			<div class="public-main-footer hidden abs">
				<a href="javascript:doDel();" class="button btn-add fl">删除</a>
				<a href="javascript:doReturn();" class="button btn-add fl">返回</a>
				<div class="public-main-footer-pagin fr">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_TAGBAR.getCode()%>" tableName="userListWithRole" action="/rbac/user.do?method=userListWithRole"/>
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