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
<script src="/js/common.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/video/initinfo.js"></script>
<script type="text/javascript" src="/js/jquery.divscroll.js"></script>
<script type="text/javascript" src="/pagejs/videoReport/list.js"></script>
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

</head>
<body class="h-100 public" onLoad="doLoad()">
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">视频管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">视频检查报告</a>
         </span>
</div>
    <div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-tab fr hidden clearfix">
				<div class="ly-button-w fr">
					<a class="button fl" href="javascript:progress();">未完成</a> 
					<a class="button btn-sel fl" href="javascript:void(0);">已完成</a>
				</div>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/videoReport.do" styleId="videoReportForm"
				method="post" onsubmit="return false">
				<input name="method" id="method" type="hidden" value="findCompletedList" />
				<input type="hidden" id="onehiId"
					value="<c:out value="${videoReportForm.query.oneBankId}"/>" />
				<input type="hidden" id="twohiId"
					value="<c:out value="${videoReportForm.query.twoBankId}"/>" />
				<input type="hidden" id="threehiId"
					value="<c:out value="${videoReportForm.query.threeBankId}"/>" />
				<input type="hidden" id="provincehiId"
					value="<c:out value="${videoReportForm.query.provinceId}"/>" />
				<input type="hidden" id="cityhiId"
					value="<c:out value="${videoReportForm.query.cityId}"/>" />
			   <div class="public-main-input ly-col-3 hidden abs">
					<div class="ly-input-w" style="margin-top:30px;margin-bottom:-10px">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">总行/金融机构：</div>
								<div class="input block fl hidden">
									<select id="one" name="query.oneBankId" style="height:24px;padding: 0 auto;" class="ly-bor-none oneBankId">
										<option value="-1">请选择...</option>
									</select>
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">分行/分支机构：</div>
								<div class="input block fl hidden">
									<select id="two" name="query.twoBankId" style="height:24px;padding: 0 auto;" class="ly-bor-none twoBankId">
										<option value="-1">请选择...</option>
									</select>
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">支行：</div>
								<div class="input block fl hidden">
									<div class="ly-sel-w">
										<select id="three" name="query.threeBankId"
											
											style="height:24px;padding: 0 auto;margin-top:-1px;" class="ly-bor-none threeBankId" style="margin: auto;">
											<option value="-1">请选择...</option>
										</select>
									</div>
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">省份：</div>
								<div class="input block fl hidden">
									<div class="ly-sel-w">
										<select id="provinceId" name="query.provinceId"
											style="height:24px;padding: 0 auto;margin-top:2px;" class="ly-bor-none provinceId" style="margin: 0;"
											onchange="changeProvince(this.value,$('#cityId'))">
											<option value="-1">请选择...</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">城市：</div>
								<div class="input block fl hidden">
									<select id="cityId" name="query.cityId"style="height:24px;padding: 0 auto;" class="ly-bor-none cityId">
										<option value="-1">请选择...</option>
									</select>
								</div>
							</div>

							<div class="ly-col fl">
								<div class="label block fl hidden">经销商：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none" property="query.dealerName"
										styleId="query.dealerName" />
								</div>
							</div>

							<div class="ly-col fl">
								<div class="label block fl hidden">计划编号：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none" property="query.planCode"
										styleId="query.planCode" />
								</div>
							</div>
						</div>
						<div class="ly-row clearfix">
							<div class="ly-col fl" style="width: 50%;">
								<div class="label block fl hidden"
									style="width: 20%; margin-left: -3px;">提交时间：</div>
								<div class="input block fl hidden"
									style="width: 80%; padding-left: 23px;">
									<input style="width: 28%;" class="ly-bor-none"
										id="submitTimeBegin" name="query.submitTimeBegin" type="text"
										value='<fmt:formatDate value="${videoReportForm.query.submitTimeBegin }" pattern="yyyy-MM-dd"/>' /> <span>至</span> 
									
									<input style="width: 28%;"
										class="ly-bor-none" id="submitTimeEnd"
										name="query.submitTimeEnd" type="text"  value='<fmt:formatDate value="${videoReportForm.query.submitTimeEnd }" pattern="yyyy-MM-dd"/>' />
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
									<th class="t-th">计划编号</th>
									<th class="t-th">经销商</th>
									<th class="t-th">省份</th>
									<th class="t-th">城市</th>
									<th class="t-th">总行/金融机构</th>
									<th class="t-th">分行/分支机构</th>
									<th class="t-th">支行</th>
									<th class="t-th">品牌</th>
								    <th class="t-th">状态</th>
									<th class="t-th">提交时间</th>
									<th class="t-th">操作</th>
								</tr>
							</thead>
							<tbody class="t-tbody">
							  <c:if test="${!empty list}">
								<logic:iterate name="list" id="row" indexId="index">
									<tr class="t-tr">
										<td class="t-td"><c:out value="${index+1}" /></td>
										<td class="t-td"><c:out value="${row.planCode}" /></td>
										<td class="t-td"><c:out value="${row.dealerName}" /></td>
										<td class="t-td"><c:out value="${row.provinceName}" /></td>
										<td class="t-td"><c:out value="${row.cityName}" /></td>
										<td class="t-td"><c:out value="${row.oneBankName}" /></td>
										<td class="t-td"><c:out value="${row.twoBankName}" /></td>
										<td class="t-td"><c:out value="${row.threeBankName}" /></td>
										<td class="t-td"><c:out value="${row.brandName}" /></td>
										<td class="t-td">
										   <c:if test="${row.editStatus==2}">已提交</c:if>
										 </td>
										 <td class="t-td">
										 <fmt:formatDate value="${row.submitReportTime}" pattern="yyyy-MM-dd HH:mm"/>
										 </td>
									     <td class="t-td">
									       <a href="javascript:detail('<c:out value="${row.id}"/>')">查看</a>
										</td>
									 </tr>
								</logic:iterate>
							  </c:if>	
							</tbody>
						</table>
					</div>
				</div>
				<div class="public-main-footer hidden abs">

					<div class="public-main-footer-pagin fr">
						<thumbpage:tools
							className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>"
							tableName="pagelist" action="/videoReport.do?method=findCompletedList" />
					</div>
				</div>
			</html:form>
		</div>
	</div>
<script src="/pagejs/scrollbar.js"></script>
</body>
</html>