<%@page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ taglib uri="select.tld" prefix="select"%>


<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />

<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="/js/common.js"></script>
<script type="text/javascript">
    function doSave(){
        var value= $('#morningStart').timespinner('getValue');
        if(!value){
            alert("早上签到时间不能为空");
            return false;
        }
        
        value= $('#morningEnd').timespinner('getValue');
        if(!value){
            alert("中午签退时间不能为空");
            return false;
        }
        
        value= $('#afternoonStart').timespinner('getValue');
        if(!value){
            alert("下午签到时间不能为空");
            return false;
        }
        
        value= $('#afternoonEnd').timespinner('getValue');
        if(!value){
            alert("下午签退时间不能为空");
            return false;
        }
        
        var workdays =  $(".workday:checked");
        if(workdays!=null&&workdays.length>0){
            var days =[];
            workdays.each(function(){
                days.push(this.value);
            });
            $("#workDays").val(days.join());
        }
        
        document.forms[0].submit();
    }
    function doReset(){
        $('#morningStart').timespinner('setValue','');
        $('#morningEnd').timespinner('setValue','');
        $('#afternoonStart').timespinner('setValue','');
        $('#afternoonEnd').timespinner('setValue','');
        $("#systemContent").html("");
        document.forms[0].reset();
    }
    $(function(){
        var options = {};
        $('#morningStart').timespinner(options);
        $('#morningEnd').timespinner(options);
        $('#afternoonStart').timespinner(options);
        $('#afternoonEnd').timespinner(options);
        showMessage("<c:out value='${message}'/>");
        var days = $("#workDays").val();
        if(days){
            days = days.split(",");
            console.info(days);
            for(var i = 0;i<days.length;i++){
                $(".workday[value='"+days[i]+"']").prop("checked",true);
            }
        }

    });
    
    //执行返回列表操作
    function doReturn() {
        location = "<url:context/>/attSign.do?method=attendanceTime";
    }
</script>
</head>
<body>

    <div class="title">经销商考勤时间修改</div>
    <hr size="1">
    <br />

    <div class="pagebodyOuter">
        <div class="pagebodyInner">
            <html:form action="/attSign" styleId="attendanceForm"
                method="post" onsubmit="return false">
                <input type="hidden" name="method" value="addorupdateAttendanceTime" />
                <input type="hidden" name="dealerId" value="<c:out value='${dealer.id}'/>">
                <input type="hidden" name="time.id" value="<c:out value='${dealer.id}'/>">
                <input type="hidden" id="workDays" name="time.workDays" value="<c:out value='${attendance.workDays}'/>">

                <table class="formTable">
                    <tr>
                        <td class="nameCol" >经销商：</td>
                        <td class="codeCol" colspan="3">
                            <c:out value="${dealer.dealerName }"/>
                        </td>
                    </tr>   
                    <tr>
                        <td class="nameCol">早上签到时间：</td>
                        <td class="codeCol">
                            <input id="morningStart"   name="time.morningStart" style="width:180px;" 
                            value="<c:out value='${attendance.morningStart}'/>"/>
                        </td>
                        <td class="nameCol">中午签退时间：</td>
                        <td class="codeCol">
                            <input id="morningEnd"   name="time.morningEnd" style="width:180px;" 
                            value="<c:out value='${attendance.morningEnd}'/>"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="nameCol">下午签到时间：</td>
                        <td class="codeCol">
                            <input id="afternoonStart"  name="time.afternoonStart" style="width:180px;" 
                            value="<c:out value='${attendance.afternoonStart}'/>"/>
                        </td>
                        <td class="nameCol">下午签退时间：</td>
                        <td class="codeCol">
                            <input id="afternoonEnd"   name="time.afternoonEnd" style="width:180px;" 
                            value="<c:out value='${attendance.afternoonEnd}'/>"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="nameCol">工作日：</td>
                        <td class="codeCol" colspan="3">
                            <input <c:if test="${isOneDay==false }">disabled='disabled'</c:if> type="checkbox" class='workday' value="1"/>周一
                            <input <c:if test="${isOneDay==false }">disabled='disabled'</c:if> type="checkbox" class='workday' value="2"/>周二
                            <input <c:if test="${isOneDay==false }">disabled='disabled'</c:if> type="checkbox" class='workday' value="3"/>周三
                            <input <c:if test="${isOneDay==false }">disabled='disabled'</c:if> type="checkbox" class='workday' value="4"/>周四
                            <input <c:if test="${isOneDay==false }">disabled='disabled'</c:if> type="checkbox" class='workday' value="5"/>周五
                            <input <c:if test="${isOneDay==false }">disabled='disabled'</c:if> type="checkbox" class='workday' value="6"/>周六
                            <input <c:if test="${isOneDay==false }">disabled='disabled'</c:if> type="checkbox" class='workday' value="7"/>周日
                        </td>
                        
                    </tr>   
                    <tr>
                        <td class="nameCol">考勤制度：</td>
                        <td class="codeCol" colspan="3">
                            <textarea id="systemContent"  maxlength="300" name="time.systemContent"><c:out value="${attendance.systemContent }"/></textarea>
                        </td>
                        
                    </tr>   
                    
                    <tr class="formTableFoot">
                        <td colspan="4" align="center">
                            <button class="formButton" onClick="doSave()">保&nbsp;存</button>
                            <button class="formButton" onClick="doReset()">重&nbsp;置</button>
                            <button class="formButton" onClick="doReturn()">返&nbsp;回</button>
                        </td>
                    </tr>
                </table>
                <br />
                <div class="message" id="message" style="display: none"></div>
            </html:form>

        </div>
    </div>
</body>
</html>
