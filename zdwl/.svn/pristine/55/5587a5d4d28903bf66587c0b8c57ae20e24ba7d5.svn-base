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

<link type="text/css" rel="stylesheet" href="/css/planandreport/planApproval.css" />
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/public.css" />
<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<style type="text/css">
.title td {
	padding: 0 15px 0 15px;
}

.oneBankId, .twoBankId, .threeBankId, .provinceId, .cityId {
	margin: 3% 15%;
	width: 58%;
	height: 64%;
}

.public-main-input .ly-col .input {
	width: 61%;
}

.public-main-input .ly-col .label {
	width: 39%;
}
</style>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script type="text/javascript" src="js/jquery.divscroll.js"></script>
<script>
	//页面初始化函数
	function doLoad() {
		 
	}
	function checkAll(name) {
		var el = document.getElementsByTagName('input');
		var len = el.length;
		for (var i = 0; i < len; i++) {
			if ((el[i].type == "checkbox") && (el[i].name == name)) {
				el[i].checked = true;
			}
		}
	}
	function clearAll(name) {
		var el = document.getElementsByTagName('input');
		var len = el.length;
		for (var i = 0; i < len; i++) {
			if ((el[i].type == "checkbox") && (el[i].name == name)) {
				el[i].checked = false;
			}
		}
	}
	//执行批量删除操作
	function doDeleteAll() {
		var infoIds = $("[name='infoIds']:checked");
		var ids = [];
		if(infoIds.length<=0){
			alert("请选择要删除的记录!");
			return false;
		}
		infoIds.each(function(){
			ids.push(this.value);
		});
		ids = ids.join();
		if(confirm("删除后数据不可恢复\n是否继续？")){
			document.getElementById("ids").value = ids;
			$("#method").val("batchDelete");
			document.forms[0].submit();
		}
	}
	//执行删除操作
	function doDel(id) {
		var planCodeInfo = $("#planCodeInfo").val();//获取已选择的经销商列表的计划编号
		var status = $("#videoPlanInfoStatus").val();
		if (confirm("删除后数据不可恢复\n是否继续？")) {
			if(planCodeInfo && planCodeInfo > 0){
				location = "<url:context/>/videoPlan.do?method=deletePlanCode&videoPlan.id=" + id + 
						"&videoPlan.planCode=" + planCodeInfo + "&videoPlanInfo.status="+status;
			}
		}
	}
	
	//执行查询操作
	function doQuery() {
		$("#method").val("loadDealerList");
		if($("#one").val() > -1){
			if($("#two").val() == -1){
				alert("请选择分行！");
				return;
			}
		}
		
		document.forms[0].submit();
	}

	$(function (){
		$("[name='videoPlan.predictCheckDate']").datebox({    
			editable:false ,
			onSelect: function(date){
				console.info($(this).val());
				var url = "/json/saveCheckDate.do?callback=?&id=" + $(this).attr("id") + "&date="
						+ date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
				$.getJSON(url, function(result) {
					var data = result.data[0];
					console.info(data);
				});
		    }
		});
	});
	function saveCheckMinute(obj,id){
		var num = obj.value;
		if(num != ""){
			if(!/^[0-9]*$/.test(num)){  
		        alert("请输入正整数!");  
		        $("#"+id).val("")
		        return;
		    }
			if(num < 0){
				alert("请输入大于0的正整数!");
				$("#"+id).val("");
				return;
			}
			if(id > 0){
				var url = "/json/saveCheckMinute.do?callback=?&id=" + id + "&num=" + num;
				$.getJSON(url, function(result) {
					var data = result.data[0];
				});
			}
		}
	}
	//提交已选经销商列表
	function validateMinute(){
		var boolFlag = true;
		$("[name='videoPlan.checkMinute']").each(function(){
			if($(this).val() == ""){
				alert("预计检查分钟为必填项");
				boolFlag = false;
				return false;
			}
	  	});
		return boolFlag;
	}
	function validateCheckDate(){
		var boolFlag = true;
		$("[name='videoPlan.predictCheckDate']").each(function(){
			if($(this).val() == ""){
				alert("预计检查日期必填项");
				boolFlag = false;
				return false;
			}
	  	});
		return boolFlag;
	}
	function goConfirm() {
		if(validateMinute() && validateCheckDate()){
			var planCodeInfo = $("#planCodeInfo").val();
			$("#planCodeInfo").val("");
			location.href = "<url:context/>/videoPlan.do?method=saveDealers&videoPlan.planCode=" + planCodeInfo;
		}		
		
	}
	
	//已审核列表
	function goOld() {
		var planCodeInfo = $("#planCodeInfo").val();//获取已选择的经销商列表的计划编号
		var status = $("#videoPlanInfoStatus").val();
		if(planCodeInfo && planCodeInfo > 0){
			location.href = "<url:context/>/videoPlan.do?method=preAdd&videoPlan.planCode=" + planCodeInfo
					+ "&videoPlanInfo.status="+status;
		}
	}
	
	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/videoPlan.do?method=findList";
	}
	
</script>
</head>
<body class="h-100 public" onLoad="doLoad()">
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">视频管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">视频检查计划</a>
              &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">新增</a>
         </span>
</div>
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-tab fr hidden clearfix">
				<div class="ly-button-w fr">
					<a class="button fl" href="javascript:goOld();">待选经销商</a> <a class="button btn-sel fl"
						href="javascript:void(0);">已选经销商</a>
				</div>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel" >
			<html:form action="/videoPlan.do" styleId="videoPlanForm" method="post" onsubmit="return false">
				<input name="method" id="method" type="hidden" value="" />
				<input  name="videoPlanInfo.status" id="videoPlanInfoStatus" type="hidden" value="<c:out value="${status}"/>"/>
				<input name="videoPlan.planCode" id="planCodeInfo" type="hidden" value="<c:out value="${planCodeInfo}"/>" />
				<input type="hidden" id="ids" name="ids" />
				<div class="public-main-table hidden"style="margin-top:30px;">
					<div class="ly-cont">
						<table class="t-table" border="0" cellspacing="0" cellpadding="0">
							<thead class="t-thead">
								<tr class="t-tr">
									<th class="t-th"><label for="checkAllValue"></label> <input
										name="test" value=""
										onclick="if(this.checked==true) { checkAll('infoIds'); } else { clearAll('infoIds'); }"
										type="checkbox">  全选</th>
									<th class="t-th">序号</th>
									<th class="t-th">视频专员</th>
									<th class="t-th">经销商</th>
									<th class="t-th" style="width: 10%;">总行/金融机构</th>
									<th class="t-th">分行/分支机构</th>
									<th class="t-th">支行</th>
									<th class="t-th">品牌</th>
									<th class="t-th">省份</th>
									<th class="t-th">城市</th>
									<th class="t-th">库存数量</th>
									<th class="t-th">预计检查分钟</th>
									<th class="t-th">预计检查日期</th>
<!-- 									<th class="t-th">实际检查时间</th> -->
									<th class="t-th">最近检查时间</th>
									<th class="t-th">操作</th>
								</tr>
							</thead>
							<tbody class="t-tbody">
								<logic:iterate name="list" id="row" indexId="index">
									<tr class="t-tr">
										<td class="t-td">
											<input type="checkbox" id="infoIds"
												name="infoIds" value="<c:out value='${row.id}'/>"/>
										</td>
										<td class="t-td"><c:out value="${index+1}" /></td>
										<td class="t-td"><select:user userid="${row.videoUserId}"/></td>
										<td class="t-td"><c:out value="${row.dealerName}" /></td>
										<td class="t-td"><c:out value="${row.oneBankName}" /></td>
										<td class="t-td"><c:out value="${row.twoBankName}" /></td>
										<td class="t-td"><c:out value="${row.threeBankName}" /></td>

										<td class="t-td"><c:out value="${row.brandName}" /></td>
										<td class="t-td"><c:out value="${row.provinceName}" /></td>
										<td class="t-td"><c:out value="${row.cityName}" /></td>
										<td class="t-td"><c:out value="${row.stock}" /></td>

										<!-- 预计检查分钟 -->
										<td class="t-td"><input style="width: 60%; height: 78%;"
											onblur="saveCheckMinute(this,<c:out value="${row.id }"/>);" styleClass="ly-bor-none"
											name="videoPlan.checkMinute" id="<c:out value="${row.id }"/>" value="<c:out value="${row.checkMinute }"/>"/>
										</td>
										<!-- 预计检查日期 <c:out value="${row.id }"/>-->
										<td class="t-td"><input style="width: 62%;" class="ly-bor-none"
											id="<c:out value="${row.id }"/>" name="videoPlan.predictCheckDate" type="text"
											value='<fmt:formatDate value="${row.predictCheckDate }" pattern="yyyy-MM-dd"/>' />
										</td>
										<!-- 实际检查时间 -->
<%-- 										<td class="t-td"><c:out value="${row.practicalCheckTime}" /></td> --%>
										<!-- 最近检查时间 -->
										<td class="t-td"><c:out value="${row.recentCheckTime}" /></td>
										<td class="t-td"><a id="deletebt"
											href="javascript:doDel('<c:out value="${row.id }"/>','<c:out value="${row.id }"/>')">删除</a>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
				</div>
				<c:if test="${status==3 || status==4}">
					<jsp:include page="/WEB-INF/jsp/approvalRecord/approvalRecord.jsp"/>
					<div class="public-main-footer" style="margin:0 auto; width:420px;">
						<a href="javascript:goConfirm();" class="button btn-add fl">确定</a>
						<a href="javascript:doReturn();" class="button btn-add fl">返回</a>
						<a href="javascript:doDeleteAll();" class="button btn-add fl">删除</a>
					</div>
				</c:if>
				<div class="public-main-footer hidden" style="bottom: -28px;">
					<c:if test="${status!=3 && status!=4}">
						<a href="javascript:goConfirm();" class="button btn-add fl">确定</a>
						<a href="javascript:doReturn();" class="button btn-add fl">返回</a>
						<a href="javascript:doDeleteAll();" class="button btn-add fl">删除</a>
					</c:if>
					<c:choose>
						<c:when test="${toolsFlag=='preUpdate'}">
							<div class="public-main-footer-pagin fr">
								<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" 
		  							tableName="preUpdate" action="/videoPlan.do?method=preUpdate" />
							</div>
						</c:when>
						<c:when test="${toolsFlag=='other'}">
							<div class="public-main-footer-pagin fr">
								<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" 
		  							tableName="list" action="/videoPlan.do?method=findListAlreadyDealer" />
							</div>
						</c:when>
					</c:choose>
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