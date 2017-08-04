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

<%@ page import="com.zd.tools.taglib.form.FormTagConstants"%>
<%@ page import="com.zd.tools.validate.model.DemoValidate"%>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>中都汽车金融监管系统</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<!-- easyui -->
<link href="/easyui/themes/material/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script src="/js/dynamic/dynamic_table.js"></script>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<c:set var="CalendarScript" value="true" />

<script>
	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/attendance.do?method=findAttendanceList";
	}
</script>
</head>

<body>

	<div class="title">考勤详情</div>
	<div class="pagebodyOuter">
		<div class="pagebodyInner">
          <html:form action="/attendance" styleId="attendanceForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" 
					value="updateAttendanceSignRecord" />
		     <table class="formTable">
					<tr>
						<td colspan="4" align="center">员工姓名：
							<select:supervisorName repositoryId="${repositoryId}" idtype="name" supervisorId="0" />
						</td>
					</tr>
					
					<!-- 修改于20170520 需求87 增加修改人和修改日期 -->
					<%-- <tr>
                        <td colspan="2" align="center">修改人：
                            <select:user userid="${updateUserId}"></select:user>
                        </td>
                        <td colspan="2" align="center">修改时间：
                            <select:timestamp timestamp="${updateDate}" idtype="date"/>
                        </td>
                    </tr> --%>
                    
					<tr>
						<td colspan="4">
							<div class="dvScroll" style="width: 100%">
								<table class="listTalbe" cellpadding="0" cellspacing="0"
									id="demoTable">
									<thead>
										<tr class="title">
											<td align="center">日期</td>
											<td align="center">实际考勤结果</td>
											<td align="center">修改</td>
											<td align="center">修改人</td>
										    <td align="center">修改日期</td>  
										</tr>
									</thead>
								  <tbody>
						              <c:if test="${!empty signRecords}">
											<c:forEach items="${signRecords}" var="row" varStatus="statu">
												<tr class="listTr_a signRecord">
												    
												   
												    
													<td><input type="hidden" name="id" value="<c:out value='${row.id}'/>"/>
														<input  type="text" name="todayDate" readonly="readonly"
														value="<c:out value='${row.todayDate}'/>" /></td>
													<td><c:out value='${row.oldAttendanceName}'/> </td>
													<td>迟到:<input  type="checkbox" name="isLate" value="1" disabled="disabled"
														<c:if test="${row.isLate==1 }">checked='checked'</c:if> />
														早退:<input   type="checkbox" name="isEarly" value="1" disabled="disabled"
														<c:if test="${row.isEarly==1 }">checked='checked'</c:if> />
														旷工:<input  type="checkbox" name="isAbsenteeism" value="1" disabled="disabled"
														<c:if test="${row.isAbsenteeism==1 }">checked='checked'</c:if> />
														正常出勤:<input  type="checkbox" name="isNormal" value="1" disabled="disabled"
														<c:if test="${row.isNormal==1 }">checked='checked'</c:if> />
													</td>
													 <td>
							                            <select:user userid="${updateUserId}"></select:user>
							                        </td>
							                        <td>
							                            <select:timestamp timestamp="${updateDate}" idtype="date"/>
							                        </td>

												</tr>
											</c:forEach>
									  </c:if>
								   </tbody>
								</table>
							</div>
						</td>
					</tr>
					<tr class="formTableFoot">
						<td colspan="4" align="center">
							<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
						</td>
					</tr>
				</table>
				<input id="signRecord" type="hidden" name="signRecord" value="" />
			</html:form>
		</div>
	</div>
</body>
</html>
