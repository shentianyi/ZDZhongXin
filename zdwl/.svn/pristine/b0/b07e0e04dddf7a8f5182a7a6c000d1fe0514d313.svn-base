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
<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
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
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

function goDetail(id){
	window.location="<url:context/>/ledger/roster.do?method=rosterDetail&roster.id="+id;
}

//导出
function goExport(){
	var supervisorId=document.getElementById("querySupervisorId").value;
	var entryDate=document.getElementById("rosterQuery.entryDate").value;
	window.location="<url:context/>/roster.do?method=extExcel"
		+"&rosterQuery.supervisorId="+supervisorId
		+"&rosterQuery.entryDate="+entryDate;
}
//执行查询操作
function doQuery(){
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	$("#querySupervisorId").combobox('select',-1);
	getElement("rosterQuery.entryDate").value="";
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
	$("#querySupervisorId").combobox({
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
				changeSupervisor(newValue);
			}

		}
	});
	var draftValue = $("#querySupervisorId").combobox('getValue');
	if (draftValue) {
		changeSupervisor(draftValue);
	}
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
<c:set var="using"><%=StateConstants.USING.getCode()%></c:set>
<c:set var="unused"><%=StateConstants.UNUSED.getCode()%></c:set>
<c:set var="menu"><%=RbacConstants.RESOURCE_NODE_TYPE_MENU.getCode()%></c:set>
<c:set var="node"><%=RbacConstants.RESOURCE_NODE_TYPE_NODE.getCode()%></c:set>
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">台帐管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">花名册</a>
         </span>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/ledger/roster" styleId="rosterForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="rosterLedger"/>
		<div class="public-main-input ly-col-1 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">监管员姓名：</div>
                        <div class="input block fl hidden">
                        	<div class="ly-sel-w">
                        		<select style="width:99%" id="querySupervisorId" name="rosterQuery.supervisorId" onchange="changeSupervisor(this.value)" >
									<option value="-1">请选择</option>
									<c:forEach items="${supervisors }" var="row">
										<option <c:if test="${rosterForm.rosterQuery.supervisorId==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
									</c:forEach>
								</select>
                        	</div>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">入职时间：</div>
                        <div class="input block fl hidden">
                        	<form:calendar property="rosterQuery.entryDate" styleId="rosterQuery.entryDate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
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
							  <th class="t-th">监管员姓名</th>
						      <th class="t-th">员工工号</th>
						      <th class="t-th">工资卡号</th>
						      <th class="t-th">开户行</th>
						      <th class="t-th">编制类型</th>
						      <th class="t-th">司龄（年）</th>
						      <th class="t-th">司龄（月）</th>
						      <th class="t-th">驻派属性</th>
						      <th class="t-th">驻派地区</th>
						      <th class="t-th">服役状态</th>
						      <th class="t-th">最后上岗时间</th>
						      <th class="t-th">最后上岗性质</th>
						      <th class="t-th">最后离岗时间</th>
						      <th class="t-th">最后离岗性质</th>
						      <th class="t-th">系统账号</th>
						      <th class="t-th">系统密码</th>
						      <th class="t-th">创建人</th>
						   	  <th class="t-th">创建时间</th>
						      <th class="t-th">最后更新人</th>
						      <th class="t-th">最后更新时间</th>
						      <th class="t-th">操作</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td"><c:out value="${index+1}"/></td>
								<td class="t-td"><select:supervisorName repositoryId="-1" idtype="name" supervisorId="${row.roster.supervisorId}"/></td>
								<td class="t-td"><c:out value="${row.roster.staffNo}" /></td>
								<td class="t-td"><c:out value="${row.roster.paycardNo}" /></td>
								<td class="t-td"><c:out value="${row.roster.openBank}" /></td>
								<td class="t-td"><c:out value="${row.roster.organizeType}" /></td>
								<td class="t-td"><c:out value="${row.roster.driveYear}" /></td>
								<td class="t-td"><c:out value="${row.roster.driveMonth}" /></td>
								<td class="t-td"><c:out value="${row.roster.dispatchAttribute}" /></td>
								<td class="t-td"><c:out value="${row.roster.dispatchCity}" /></td>
								<td class="t-td"><c:out value="${row.roster.enlistStatus}" /></td>
								<td class="t-td"><c:out value="${row.roster.missionDateNTT}" /></td>
								<td class="t-td"><c:out value="${row.roster.missionNatureNTT}" /></td>
								<td class="t-td"><c:out value="${row.roster.dimissionDateNTT}" /></td>
								<td class="t-td"><c:out value="${row.roster.dimissionNatureNTT}" /></td>
								<td class="t-td"><c:out value="${row.roster.systemAccount}" /></td>
								<td class="t-td"><c:out value="${row.roster.systemPassword}" /></td>
								<td class="t-td"><select:user userid="${row.roster.createUser }"></select:user></td>
								<td class="t-td"><select:timestamp timestamp="${row.roster.createTime}" idtype="date"/></td>
								<td class="t-td"><select:user userid="${row.roster.lastModifyUser }"></select:user></td>
								<td class="t-td"><select:timestamp timestamp="${row.roster.lastModifyTime}" idtype="date"/></td>
								<td class="t-td">
									<a href="javascript:goDetail('<c:out value='${row.roster.id}'/>');">详情</a>&nbsp;
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		</div>
		<div class="public-main-footer hidden abs">
			<a href="javascript:goExport();" class="button btn-add fl yc">导出</a>
			<div class="public-main-footer-pagin fr">
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="rosterLedger" action="/ledger/roster.do?method=rosterLedger"/>
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