<%@page import="com.zd.csms.rbac.constants.RoleConstants"%>
<%@page import="com.zd.csms.rbac.util.UserSession"%>
<%@page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>
<%@page import="com.zd.csms.bank.contants.BankContants"%>
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

<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<style type="text/css">
	.title td{
		padding:0 15px 0 15px;
	}
</style>
<script src="/js/common.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script type="text/javascript" src="js/jquery.divscroll.js"></script>
<script>
//页面初始化函数//
	function doLoad(){
		initTools();
		//显示提示信息
		showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	}

	//进入新增页面
	function goAdd() {
		location = "<url:context/>/complaint.do?method=preAdd";
	}

	//进入修改页面
	function goUpd(id) {
		location = "<url:context/>/complaint.do?method=preUpdate&complaint.id=" + id;
	}

	//执行删除操作
	function doDel(id) {
		if (confirm("删除后数据不可恢复\n是否继续？")) {
			location = "<url:context/>/complaint.do?method=delete&complaint.id=" + id;
		}
	}

	//执行查询操作
	function doQuery() {
		$("#method").val("findList");
		document.forms[0].submit();
	}
	
	//投诉记录信息导出
	function goExport(){
		$("#method").val("exportExcel");
		document.forms[0].submit();
	}

	//执行表单清空操作
	function doClear() {
		//清空资源名输入框
		getElement("query.dealerName").value = "";
		getElement("query.createUserName").value="";
		getElement("query.financeName").value="";
		$('#createDate').datebox('clear');
		$('#endDate').datebox('clear');
		$("#query\\.phoneType").combobox('clear');
		$("#query\\.complaintObjId").combobox('clear');
		
	}
	
	//已审批列表
	function goAlreadyApproval(){
		location.href="<url:context/>/complaint.do?method=findList&query.pageType=2";
	}
	
	function doSubmit(id,status){
		if(status == 1 || status == 4){
			if(confirm("确定要提交吗？")){
				location.href="<url:context/>/complaint.do?method=submit&complaint.id="+id;
			}
		}
		if(status == 2){
			var url = "/json/getContentExist.do?callback=?&id=" + id + "&status=" + status;
			$.getJSON(url, function(result) {
				console.info(result);
				var data = result.data[0];
				if(data > 0){
					alert("解决方案未填写,请填写后提交！");
					return;
				}else{
					if(confirm("确定要提交吗？")){
						location.href="<url:context/>/complaint.do?method=submit&complaint.id="+id;
					}
				}
			});
		}
		if(status == 3){
			var url = "/json/getContentExist.do?callback=?&id=" + id + "&status=" + status;
			$.getJSON(url, function(result) {
				console.info(result);
				var data = result.data[0];
				if(data > 0){
					alert("跟踪情况未填写,请填写后提交！");
					return;
				}else{
					if(confirm("确定要提交吗？")){
						location.href="<url:context/>/complaint.do?method=submit&complaint.id="+id;
					}
				}
			});
		}
	}
	
	function approval(id){
		location.href="<url:context/>/complaint.do?method=preApproval&complaint.id="+id;
	}
	
	function detail(id){
		location.href="<url:context/>/complaint.do?method=detailPage&complaint.id="+id;
	}
	function initTools(){
		$('#createDate').datebox({    
			editable:false   
		});
		$('#endDate').datebox({    
			editable:false   
		});
		$(".phoneType,.complaintObjId").combobox({
			width : "125.778px",
			onHidePanel : function() {// 验证输入的值，如果不存在下拉列表 则置空   
				var newValue = $(this).combobox('getValue');
				var data = $(this).combobox('getData');
				var flag = false;
				if (data != null && data.length > 0) {
					for (var i = 0; i < data.length; i++) {
						if (newValue == data[i].value) {
							flag = true;
							if(newValue == ""){
								flag = false;
							}
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
</script>
</head>
<body class="h-100 public" onLoad="doLoad()">
<input type="hidden" value="<%= UserSessionUtil.getUserSession(request).getUser().getClient_type()%>" id="crole" />
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">经销商信息管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">投诉记录信息管理</a>
         </span>
</div>

<c:set var="business_amaldar"><%=RoleConstants.BUSINESS_AMALDAR.getCode() %></c:set>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/complaint.do" styleId="complaintForm" method="post" onsubmit="return false">
			<input name="method" id="method" type="hidden" value="" />
			<div class="public-main-input ly-col-2 hidden abs">
				<div class="ly-input-w" >
					<div class="ly-row clearfix">
	                    <div class="ly-col fl" style="width:50%;">
	                        <div class="label block fl hidden" style="width:15%;">创建日期：</div>
	                        <div class="input block fl hidden" style="width:85%;padding-left:20px;">
	                        	<input style="width:35%;" class="ly-bor-none" id="createDate" name="query.createDate" type="text" value='<fmt:formatDate value="${complaintForm.query.createDate }" pattern="yyyy-MM-dd"/>'/>
	                        	<span>至</span>
	                        	<input style="width:35%;" class="ly-bor-none" id="endDate" name="query.endDate" type="text" value='<fmt:formatDate value="${complaintForm.query.endDate }" pattern="yyyy-MM-dd"/>'/>
	                        </div>
	                    </div>
	                    <div class="ly-col fl">
	                        <div class="label block fl hidden">来电类型：</div>
	                        <div class="input block fl hidden">
	                        	<div class="ly-sel-w" style="width:80%">
	                        		<select id="query.phoneType" name="query.phoneType" class="phoneType">
							  			<option value="">请选择...</option>
							  			<c:forEach items="${telephoneTypes}" var="row">
							  				<option <c:if test="${row.value==complaintForm.query.phoneType }">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
							  			</c:forEach>
							  		</select>
	                        	</div>
	                        </div>
	                    </div>
	                    <div class="ly-col fl">
	                        <div class="label block fl hidden">来电人类型：</div>
	                        <div class="input block fl hidden">
	 							<div class="ly-sel-w">
	                        		<select id="query.complaintObjId" name="query.complaintObjId" class="complaintObjId">
							  			<option value="">请选择...</option>
							  			<c:forEach items="${complaintObjs}" var="row">
							  				<option <c:if test="${row.value==complaintForm.query.complaintObjId }">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
							  			</c:forEach>
							  		</select>
	                        	</div>
	                        </div>
	                    </div>
	                    <div class="ly-col fl">
	                        <div class="label block fl hidden">记录人：</div>
	                        <div class="input block fl hidden">
	                        	<html:text styleClass="ly-bor-none" property="query.createUserName" styleId="query.createUserName"/>
	                        </div>
	                    </div>
	                    <div class="ly-col fl">
	                        <div class="label block fl hidden">经销商：</div>
	                        <div class="input block fl hidden">
	                        	<html:text styleClass="ly-bor-none" property="query.dealerName" styleId="query.dealerName"/>
	                        </div>
	                    </div>
	                    <div class="ly-col fl">
	                        <div class="label block fl hidden">金融机构：</div>
	                        <div class="input block fl hidden">
	                        	<html:text styleClass="ly-bor-none" property="query.financeName" styleId="query.financeName"/>
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
								<th class="t-th">创建日期</th>
								<th class="t-th">创建时间</th>
								<th class="t-th">记录人</th>
								<th class="t-th">所属部门</th>
						      	<th class="t-th">来电类型</th>
						      	<th class="t-th">来电人类型</th>
						      	<th class="t-th">来电对象名称</th>
								<!-- <th class="t-th">来电人</th>
						      	<th class="t-th">职务</th>
								<th class="t-th">品牌</th>
								<th class="t-th">工号</th>
								<th class="t-th">所在店</th>
								<th class="t-th">所在行</th> -->
						      	<th class="t-th">状态</th>
						      	<th class="t-th">操作</th>
							</tr>
						</thead>
						<tbody class="t-tbody hidden">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}"/></td>
									<td class="t-td">
										<select:timestamp timestamp="${row.createDate}" idtype="date"/>
									</td>
									<td class="t-td"><c:out value="${row.createTime }" /></td>
									<td class="t-td"><c:out value="${row.createUserName }" /></td>
									<td class="t-td"><c:out value="${row.createUserDept }" /></td>
									<td class="t-td">
										<select:LoadPhoneType idtype="${row.phoneType }"></select:LoadPhoneType>
					  				</td>
									<td class="t-td">
										<select:LoadComplaintObj idtype="${row.complaintObjId }"></select:LoadComplaintObj>
									</td>
									<td class="t-td">
										<c:out value="${row.objName }"></c:out>
									</td>
									<%-- <td class="t-td"><c:out value="${row.complainantName }" /></td>
									<td class="t-td"><c:out value="${row.complainantPosition }" /></td>
									<td class="t-td"><c:out value="${row.brandName }" /></td>
									<td class="t-td"><c:out value="${row.jobNum }" /></td>
									<td class="t-td"><c:out value="${row.storeId }" /></td>
									<td class="t-td"><c:out value="${row.bankId }" /></td> --%>
									<td class="t-td">
										<c:if test="${row.status == 1}">未提交</c:if>
										<c:if test="${row.status == 2}">已发送</c:if>
										<c:if test="${row.status == 3}">已修正</c:if>
										<c:if test="${row.status == 4}">已完成</c:if>
									</td>
									<td class="t-td">
										<a id="detailbt" href="javascript:detail('<c:out value="${row.id }"/>')">详情</a>
										<!-- 部长角色只有查看详情权限 -->
										<c:if test="${(row.createUserId == userId&&(row.status==1||row.status==3))
													||(row.processingId==userId&&row.status==2)}">
											<a id="submitbt" href="javascript:doSubmit('<c:out value="${row.id }"/>','<c:out value="${row.status}"/>')" class="yc">提交</a>
											<a id="updatbt" href="javascript:goUpd('<c:out value="${row.id }"/>')" class="yc">更新</a>
										</c:if>
										<c:if test="${row.createUserId == userId && row.status == 1}">
											<a id="deletebt" href="javascript:doDel('<c:out value="${row.id }"/>')" class="yc">删除</a>
										</c:if>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
			<div class="public-main-footer hidden abs">
				<c:set value="${complaintForm.query.currRole }" var="j"></c:set>
				<c:if test="${j == 9 || j == 13 || j == 16 || j == 20 || j == 23 || j == 25 }">
					<a href="javascript:goAdd();" class="button btn-add fl yc">新增</a>
				</c:if>
				<a href="javascript:goExport();" class="button btn-add fl yc">导出</a>
				<div class="public-main-footer-pagin fr">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="list" action="/complaint.do?method=findList" />
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