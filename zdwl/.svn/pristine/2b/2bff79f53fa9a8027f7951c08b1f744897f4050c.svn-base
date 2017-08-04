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
        var month = $("#month").val();
        location = "<url:context/>/attendance.do?method=SignRecordCheckSpList&squery.month="+month;
    }
</script>
<style type="text/css">
td{border:0;}
</style>
</head>

<body>

    <div class="title">每日考勤详情</div>
    <div class="pagebodyOuter">
        <div class="pagebodyInner">
          <html:form action="/attendance" styleId="attendanceForm"
                method="post" onsubmit="return false">
                <input type="hidden" name="method" value="" />
                <input type="hidden" value="${month }" id="month" />
             <table class="formTable" border="1">
                    <tr>
                        <td colspan="4" align="center">员工姓名：
                            <select:supervisorName repositoryId="${respId}" idtype="name" supervisorId="0" />
                        </td>
                    </tr>
                    
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
                                       <logic:iterate name="list" id="row" indexId="index">
                                           <tr class="t-tr" style="text-align: center;">
                                               <td class="t-td"><input type="hidden" name="id" value="<c:out value='${row.respId}'/>"/>
                                                   <select:timestamp idtype="date" timestamp="${row.createDate}"></select:timestamp>
                                               </td>
                                               
                                               <td class="t-td">
                                                    <c:if test="${row.isLate==1 }">迟到</c:if>
                                                    <c:if test="${row.isEarly==1 }">早退</c:if>
                                                    <c:if test="${row.isAbsenteeism==1 }">旷工</c:if>
                                                    <c:if test="${row.isNormal==1 }">正常出勤</c:if>
                                               </td>
                                               
                                               <td class="t-td">迟到:<input  type="checkbox" name="isLate" value="1" disabled="disabled"
                                                   <c:if test="${row.isLate==1 }">checked='checked'</c:if> />
                                                   早退:<input   type="checkbox" name="isEarly" value="1" disabled="disabled"
                                                   <c:if test="${row.isEarly==1 }">checked='checked'</c:if> />
                                                   旷工:<input  type="checkbox" name="isAbsenteeism" value="1" disabled="disabled"
                                                   <c:if test="${row.isAbsenteeism==1 }">checked='checked'</c:if> />
                                                   正常出勤:<input  type="checkbox" name="isNormal" value="1" disabled="disabled"
                                                   <c:if test="${row.isNormal==1 }">checked='checked'</c:if> />
                                               </td>
                                               <td class="t-td">
                                                   <select:user userid="${row.updateUserId}"></select:user>
                                               </td>
                                               <td class="t-td">
                                                   <select:timestamp timestamp="${row.updateDate}" idtype="date"/>
                                               </td>

                                           </tr>
                                       </logic:iterate>
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
            </html:form>
        </div>
    </div>
</body>
</html>
