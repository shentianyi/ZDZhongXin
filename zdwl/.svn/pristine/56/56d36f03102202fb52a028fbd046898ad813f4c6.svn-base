<%@page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ page import="com.zd.csms.constants.Constants"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<!-- easyui -->
<link href="/easyui/themes/material/easyui.css" rel="stylesheet"
    type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script src="/pagejs/videoReport/report.js"></script>
<style type="text/css">
.add_tdText{
width:10%;
height:40px;
text-align: right;
font-size:14px;
font-weight: 600;

 
}

.addtdCount2{
text-align:right;
height:40px;
font-size:14px;
font-weight: 600;
}
.add_tdText2{
height:40px;
font-size:14px;
font-weight: 600;
}
td{border-top: solid 1px #eee;border-left: solid 1px #eee;}
.addtdCount{
width:17%;
font-size:14px;
font-weight: 600;

}
.problem_title{
text-align:center;
width:33%;
height:40px;

}
.problem_title_text{
text-align:center;
width:30%;
height:40px;
font-size: 14px;
font-weight:600;

}
.problem_table_td{
text-align:center;
width:30%;
height:40px;
border-top:1px dashed #BBBBBB;
border-left:1px dashed #BBBBBB
}
#demoid td{
text-align:center;
width:30%;
height:40px;
border-top:1px dashed #BBBBBB;
border-left:1px dashed #BBBBBB
}
#demoid2 td{
text-align:center;
width:30%;
height:40px;
border-top:1px dashed #BBBBBB;
border-left:1px dashed #BBBBBB
}
#demoid3 td{
text-align:center;
width:30%;
height:40px;
border-top:1px dashed #BBBBBB;
border-left:1px dashed #BBBBBB
}
.problem_table_td2{
text-align:center;
width:10%;
height:40px;
border-top:1px dashed #BBBBBB;
border-left:1px dashed #BBBBBB
}
.problem_title_text2{
text-align:center;
width:10%;
height:40px;
font-size: 14px;
font-weight:600;

}
input{ width:100%;height:100%;border:none;}
select{ width:100%;height:100%;border:none;text-align: center;}
.demo td{
text-align:center;
height:40px;
border-top:1px dashed #BBBBBB;
border-left:1px dashed #BBBBBB;
}
textarea{
width:100%;height:100%;border:none;
}
 a{cursor: pointer;}
</style>
<script type="text/javascript">
//返回上一页
function goReturn(){
    location = "/ledger/videoReport.do?method=reportLedger";
}
</script>
</head>
<body>
    <div class="title">视频检查报告台账详情</div>
    <hr size="1">
    <br />
    <div class="pagebodyOuter">
        <div class="pagebodyInner">
            <html:form action="/videoReport.do" styleId="videoReportForm"
                method="post" onsubmit="return false">
                <input type="hidden" name="method" value="viewReport" />
                <input type="hidden" name="videoReport.id"
                    value="<c:out value='${planId}'/>" />
                <table class="formTable" style="border-bottom: solid 1px #eee;">
                    <tr >
                        <td class="add_tdText">经销商名称：</td>
                        <td class="addtdCount">
                        <input type="text"
                            name="videoReport.dealer_name" readonly="readonly"
                            value="<c:out value='${videoReport.dealer_name}'/>" /></td>
                        <td class="add_tdText">金融机构：</td>
                        <td class="addtdCount"><input type="text"
                            name="videoReport.bank_name" readonly="readonly"
                            value="<c:out value='${videoReport.bank_name}'/>" /></td>
                        <td class="add_tdText">品牌：</td>
                        <td class="addtdCount" style="border-right: solid 1px #eee;"><input type="text"
                            name="videoReport.brand" readonly="readonly"
                            value="<c:out value='${videoReport.brand}'/>" /></td>
                    </tr>
                    <tr>
                        <td class="add_tdText">操作模式：</td>
                        <td class="addtdCount"><input type="text"
                            name="videoReport.supervisionMode" readonly="readonly"
                            value="<c:out value='${videoReport.supervisionMode}'/>" /></td>
                        <td class="add_tdText">监管员：</td>
                        <td class="addtdCount"><input type="text"
                            name="videoReport.supervisor_name" readonly="readonly"
                            value="<c:out value='${videoReport.supervisor_name}'/>" /></td>
                        <td class="add_tdText" >工号：</td>
                        <td class="addtdCount" ><input type="text"
                            name="videoReport.staff_no"  style="border-right: solid 1px #eee;" readonly="readonly"
                            value="<c:out value='${videoReport.staff_no}'/>" /></td>
                    </tr>
                    <tr>
                    <td class="addtdCount2" >视频专员：</td>
                    <td class="add_tdText2" colspan="2"><input type="text"
                            name="videoReport.check_name" readonly="readonly"
                            value="<c:out value='${videoReport.check_name}'/>" />
                    </td>
                    <td class="addtdCount2">最近检查日期：</td>
                    <td class="add_tdText2" style="border-right:1px solid #eee" colspan="2"> 
                    <input  readonly="readonly" type="text" name="videoReport.last_check_time" value="<c:out value='${videoReport.last_check_time}'/>" /> 
                    
                     </td>
                        
                    </tr>
                <%--    <tr>
                        <td class="add_tdText" >检查人员：</td>
                        <td class="addtdCount"><c:out value='${check_name}'/></td>

                    </tr>
                    <tr>
                        <td class="nameCol" style="width: 2%;">日期：</td>
                        <td class="codeCol" colspan="5">
                            <fmt:formatDate value="${check_time}" pattern="yyyy-MM-dd" /></td>
                    </tr> --%>
                    <tr>
                        <td class="addtdCount2">
                        <span><input type="checkbox" value="1"  readonly="readonly"
                            name="videoReport.skip_fag"  style="float:left;width:30px;height:40px;"  onclick="skipWrite(this)"/></span>
                            <span style="margin-top:12px;width:80px;display:block;float:right;">跳过填写：</span></td>
                        <td class="add_tdText2" colspan="2">
                        <input type="text"id="reasonWrite" name="videoReport.reason"  readonly="readonly" value="<c:out value='${videoReport.reason}'/>" style="width: 100%;"readonly="readonly" /></td>
                        <td class="addtdCount2">检查日期：</td>
                        <td class="add_tdText2" style="border-right: solid 1px #eee;"  colspan="2">
                        <%-- <input name="videoReport.check_time" id="videoReport.check_time"  readonly="readonly" value='<c:out value="${videoReport.check_time}"/>' type="text"  /> --%>
                        <fmt:formatDate value="${videoReport.check_time}" pattern="yyyy-MM-dd"/>
                        </td>
                    </tr>
                    <tr><td class="addtdCount2">系统库存情况：</td>
                    <td class="add_tdText2" style="border-right:1px solid #eee" colspan="5"></td>
                    </tr>
                    <tr><td class="addtdCount2" >在库（台）：</td>
                    <td class="add_tdText2" colspan="2"><input type="text"  readonly="readonly" name="videoReport.stock_num" value='<c:out value="${videoReport.stock_num}" />' />  </td>
                    <td class="addtdCount2">在途（台）：</td>
                    <td class="add_tdText2" style="border-right:1px solid #eee" colspan="2"><input  readonly="readonly" type="text" name="videoReport.way" value='<c:out value="${videoReport.way}" />'  /> </td></tr>
                    <tr><td class="addtdCount2" colspan="2"> 实际库存情况（电子总账）：</td>
                    <td class="add_tdText2" style="border-right:1px solid #eee" colspan="4"></td>
                    </tr>
                    <tr><td class="addtdCount2" >在库（台）：</td>
                    <td class="add_tdText2" colspan="2"><input type="text" name="videoReport.actual_stock"  readonly="readonly" value='<c:out value="${videoReport.actual_stock}" />'  />  </td>
                    <td class="addtdCount2">在途（台）：</td>
                    <td class="add_tdText2" colspan="2" style="border-right:1px solid #eee" colspan="2"><input  readonly="readonly" type="text" name="videoReport.actual_way" value='<c:out value="${videoReport.actual_way}" />' /> </td></tr>
                <tr><td class="addtdCount2" colspan="2">实际检查用时（分钟）：</td>
                    <td class="add_tdText2" style="border-right:1px solid #eee" colspan="4"><input  readonly="readonly" type="text"
                            id="checkMinute" name="videoReport.check_minute" value='<c:out value="${videoReport.check_minute}" />'
                              /></td>
                    </tr> 
                    
                </table>
 
            <table id="hideDiv" class="formTable">
            <tr>
            <td class="problem_title_text" >问题分类</td>
            <td class="problem_title_text" >问题描述</td>
            <td class="problem_title_text" >归属部门</td>
            </tr>
            <tbody id="demoid">
                <logic:iterate name="reportQuestion" id="row" indexId="index">
                    <c:if test="${row.type == 1 }">
                    
                        <tr  class="demo">
                    <td class="problem_table_td">
                    <select class="ly-bor-none"  id="name_jflx2" name="question_classify1" disabled="disabled" >
                        <option value="-1">未选择</option>
                        <option value="1" <c:if test="${row.question_classify ==1 }" >selected="selected"</c:if>>办公设备</option>
                        <option value="2" <c:if test="${row.question_classify ==2 }" >selected="selected"</c:if>>办公工位</option>
                        <option value="3" <c:if test="${row.question_classify ==3 }" >selected="selected"</c:if>>其他</option>
                    </select>
                    </td>
                    <td class="problem_table_td"><input type="text" class="verification"  placeholder="电脑、保险柜、打印机、传真机、网络…" name="question_desc1" value="<c:out value='${row.question_desc }'/>"  readonly="readonly" /></td>
                    <td class="problem_table_td">
                    <select class="ly-bor-none"  id="name_jflx2" name="depart1" disabled="disabled" >
                        <option value="-1">未选择</option>
                        <option value="5" <c:if test="${row.depart ==5 }" >selected="selected"</c:if>>市场部</option>
                        <option value="2" <c:if test="${row.depart ==2 }" >selected="selected"</c:if>>监管员管理部</option>
                        <option value="4" <c:if test="${row.depart ==4 }" >selected="selected"</c:if>>业务部</option>
                        <option value="6" <c:if test="${row.depart ==6 }" >selected="selected"</c:if>>风控部</option>
                    </select>
                    </td>
                </tr>
                    </c:if>
                </logic:iterate>
                
            </tbody>
            <tr>
            </tr>
            <tbody id="demoid2">
                <logic:iterate name="reportQuestion" id="row2" indexId="index">
                    <c:if test="${row2.type == 2 }">
                <tr  class="demo2">
                    <td  class="problem_table_td">
                    <select class="ly-bor-none"  id="name_jflx2" name="question_classify2" disabled="disabled" >
                        <option value="-1" >未选择</option>
                        <option value="4" <c:if test="${row2.question_classify ==4 }" >selected="selected"</c:if>>入职资料</option>
                        <option value="5" <c:if test="${row2.question_classify ==5 }" >selected="selected"</c:if>>考勤</option>
                        <option value="6" <c:if test="${row2.question_classify ==6 }" >selected="selected"</c:if>>其他</option>
                    </select>
                    </td>
                    <td class="problem_table_td"><input class="requiredWrite"  type="text" placeholder="设备接收确认书、作息时间表" name="question_desc2" value="<c:out value='${row2.question_desc }'/>"  readonly="readonly"  /></td>
                    <td class="problem_table_td">
                    <select class="ly-bor-none"  id="name_jflx2" name="depart2" disabled="disabled" >
                        <option value="-1">未选择</option>
                        <option value="5" <c:if test="${row2.depart ==5 }" >selected="selected"</c:if>>市场部</option>
                        <option value="2" <c:if test="${row2.depart ==2 }" >selected="selected"</c:if>>监管员管理部</option>
                        <option value="4" <c:if test="${row2.depart ==4 }" >selected="selected"</c:if>>业务部</option>
                        <option value="6" <c:if test="${row2.depart ==6 }" >selected="selected"</c:if>>风控部</option>
                    </select>
                    </td>
                </tr>
                </c:if>
                </logic:iterate>
            </tbody>
            <tr>
            </tr>
            <tbody id="demoid3">
            <logic:iterate name="reportQuestion" id="row3" indexId="index">
                    <c:if test="${row3.type == 3 }">
                <tr  class="demo3">
                    <td class="problem_table_td">
                    <select class="ly-bor-none"  id="name_jflx2" name="question_classify3" disabled="disabled" >
                        <option value="-1">未选择</option>
                        <option value="7" <c:if test="${row3.question_classify ==7 }" >selected="selected"</c:if>>进店资料</option>
                        <option value="8" <c:if test="${row3.question_classify ==8 }" >selected="selected"</c:if>>总账</option>
                        <option value="9" <c:if test="${row3.question_classify ==9 }" >selected="selected"</c:if>>系统</option>
                        <option value="10" <c:if test="${row3.question_classify ==10 }" >selected="selected"</c:if>>手工台账</option>
                        <option value="11" <c:if test="${row3.question_classify ==11 }" >selected="selected"</c:if>>日查库表单</option>
                        <option value="12" <c:if test="${row3.question_classify ==12 }" >selected="selected"</c:if>>钥匙交接表</option>
                        <option value="13" <c:if test="${row3.question_classify ==13 }" >selected="selected"</c:if>>监管确认书</option>
                        <option value="14" <c:if test="${row3.question_classify ==14 }" >selected="selected"</c:if>>释放申请书</option>
                        <option value="15" <c:if test="${row3.question_classify ==15 }" >selected="selected"</c:if>>移动申请书</option>
                        <option value="16" <c:if test="${row3.question_classify ==16 }" >selected="selected"</c:if>>月库存核对清单</option>
                        <option value="17" <c:if test="${row3.question_classify ==17 }" >selected="selected"</c:if>>私自放证</option>
                        <option value="18" <c:if test="${row3.question_classify ==18 }" >selected="selected"</c:if>>提前放证</option>
                        <option value="19" <c:if test="${row3.question_classify ==19 }" >selected="selected"</c:if>>私移</option>
                        <option value="20" <c:if test="${row3.question_classify ==20 }" >selected="selected"</c:if>>私售</option>
                        <option value="21" <c:if test="${row3.question_classify ==21 }" >selected="selected"</c:if>>其他</option>
                    </select>
                    </td>
                    <td class="problem_table_td"><input type="text" class="requiredWrite"  placeholder="委任书、授权书、经销商告知函" name="question_desc3" value="<c:out value='${row3.question_desc }'/>" readonly="readonly"  /></td>
                    <td class="problem_table_td">
                    <select class="ly-bor-none"  id="name_jflx2" name="depart3" disabled="disabled" >
                        <option value="-1">未选择</option>
                        <option value="5" <c:if test="${row3.depart ==5 }" >selected="selected"</c:if>>市场部</option>
                        <option value="2" <c:if test="${row3.depart ==2 }" >selected="selected"</c:if>>监管员管理部</option>
                        <option value="4" <c:if test="${row3.depart ==4 }" >selected="selected"</c:if>>业务部</option>
                        <option value="6" <c:if test="${row3.depart ==6 }" >selected="selected"</c:if>>风控部</option>
                    </select>
                    </td>
                </tr>
                    </c:if>
                </logic:iterate>
            </tbody>
            <tr>
            </tr>
            <tr>
            <td class="problem_title" style="border-bottom:1px solid #eee;font-size:14px;font-weight: 600;">评价</td>
            <td class="problem_title" style="border-bottom: 1px solid #eee" colspan="2" >
            <textarea name="videoReport.evaluate" readonly="readonly"  style="border:solid 1px #eee;height:100px;"><c:out value="${videoReport.evaluate}" /></textarea></td>
            </tr>
            
            </table>
                <br />
                <br />

            </html:form>
            &nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;<button class="formButton" onClick="goReturn()">返&nbsp;回</button>
        </div>
    </div>
</body>
</html>
