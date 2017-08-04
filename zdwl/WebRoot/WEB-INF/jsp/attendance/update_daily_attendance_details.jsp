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
/*function Late(obj){
    var ss=$(obj);
    var obj2 = ss.siblings();
    if (obj.checked) {
        obj2.each(function(i) {
            if ($(this).attr("name")=="isAbsenteeism") {
                $(this).prop("checked", false);
                $(this).prop("disabled", true);
            }

        });

    }else{
        obj2.each(function(i) {
            if ($(this).attr("name")=="isAbsenteeism") {
                $(this).prop("disabled", false);
            }

        });
    }  
 }*/
//旷工
function Absenteeism(obj){
    var ss=$(obj);
    var obj2 =ss.siblings();
    if (obj.checked) {
        obj2.each(function(i) { 
         $(this).prop("disabled", true);
        });
    }else{
        obj2.each(function(i){
            $(this).prop("disabled", false);
        });
    } 
}
   //执行保存操作
    function doSave() {
    	var jsonArr = eval('('+"[]"+')');
    	var arr = $("input[name=squery\\.isLate]");
    	for(var i = 0;i < arr.length;i++){
    		var id = arr[i].className;
    		var isLate = arr[i].checked?1:0;
    		var isEarly = $("input[name=squery\\.isEarly][class="+arr[i].className+"]")[0].checked?1:0;
    		var isAbsenteeism = $("input[name=squery\\.isAbsenteeism][class="+arr[i].className+"]")[0].checked?1:0;
    		var isNormal = $("input[name=squery\\.isNormal][class="+arr[i].className+"]")[0].checked?1:0;
    		var json = '{"id":"'+id+'","isLate":"'+isLate+'","isEarly":"'+isEarly+'","isAbsenteeism":"'+isAbsenteeism+'","isNormal":"'+isNormal+'"}';
    		jsonArr.push(json);
    	}
    	$("#jsonData").val(JSON.stringify(jsonArr));
        document.forms[0].submit();
    }
        

    //执行返回列表操作
    function doReturn() {
        var month = $("#month").val();
        location = "<url:context/>/attendance.do?method=SignRecordCheckSpList&query.month="+month;
    }

    //执行表单重置操作
    function doReset() {
        var obj = $("input[type=checkbox]") ;
            obj.each(function(i) { 
             if($(this).attr('class')==1){
                 $(this).prop("checked", true);
             }else{
                 $(this).prop("checked", false); 
             }
              $(this).prop("disabled", false);
            });
    }
</script>
</head>

<body>
  <div class="title">修改每日考勤详情</div>
    <div class="pagebodyOuter">
        <div class="pagebodyInner">
          <html:form action="/attendance" styleId="attendanceForm"
                method="post" onsubmit="return false">
                <input type="hidden" name="method"  value="update" />
                <input type="hidden" value="${year }" id="year" name="year" />
                <input type="hidden" value="${month }" id="month" name="month" />
                <input type="hidden" id="jsonData" name="jsonData"/>
                <%-- <html:hidden property="squery.month" styleId="squery.month" />
                <html:hidden property="squery.year" styleId="squery.year" /> --%>
	             <table class="formTable">
	                    <tr>
	                        <td colspan="4" align="center">员工姓名：
	                            <select:supervisorName repositoryId="${respId}" idtype="name"  supervisorId="0" />
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
	                                               <td class="t-td"><input type="hidden" name="squery.respId" value="<c:out value='${row.respId}'/>"/>
	                                                   <select:timestamp idtype="date" timestamp="${row.createDate}"></select:timestamp>
	                                                   <input  type="hidden" name="squery.createDate" readonly="readonly"
                                                        value="<c:out value='${row.createDate}'/>" />
	                                               </td>
	                                               
	                                               <td class="t-td">
	                                                    <c:if test="${row.isLate==1 }">迟到</c:if>
	                                                    <c:if test="${row.isEarly==1 }">早退</c:if>
	                                                    <c:if test="${row.isAbsenteeism==1 }">旷工</c:if>
	                                                    <c:if test="${row.isNormal==1 }">正常出勤</c:if>
	                                               </td>
	                                               
	                                               <td class="t-td">迟到:<input class="<c:out value='${row.id }' />"  type="checkbox" name="squery.isLate" value="1" 
	                                                   <c:if test="${row.isLate==1 }">checked='checked'</c:if> />
	                                                   早退:<input class="<c:out value='${row.id }' />"    type="checkbox" name="squery.isEarly" value="1" 
	                                                   <c:if test="${row.isEarly==1 }">checked='checked'</c:if> />
	                                                   旷工:<input class="<c:out value='${row.id }' />"   type="checkbox" name="squery.isAbsenteeism" value="1" 
	                                                   <c:if test="${row.isAbsenteeism==1 }">checked='checked'</c:if> />
	                                                   正常出勤:<input  class="<c:out value='${row.id }' />"  type="checkbox" name="squery.isNormal" value="1" 
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
	                            <button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
	                            <button class="formButton" onClick="doReset()">重&nbsp;置</button>&nbsp;
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
