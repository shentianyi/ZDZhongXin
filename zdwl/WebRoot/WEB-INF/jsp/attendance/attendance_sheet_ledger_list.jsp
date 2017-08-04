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
<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
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
    input[type="text"][onchange="save(this);"]{
       width: 80px;
    }
</style>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script type="text/javascript" src="/js/jquery.divscroll.js"></script>
<script>
$(function(){
     var message="<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>";
    if(message!=null&&message!=""&&message!="null"){
        alert(message);
    } 
    init(); 
});
    //提交
    function doSubmit(){
        var id=$("#attendanceId").val();
        if(confirm("确定要提交吗？")){
            location.href="<url:context/>/attendance.do?method=submit&attendance.id="+id;
        }
    }
    //审批
    function doApproval(id){
        if(confirm("确定审批不通过该条记录吗？")){
            location.href="<url:context/>/attendance.do?method=approval&attendanceInfo.id="+id;
        }
    }
    
    //考勤更新
    function goUpdateEntry(repositoryId){
        var year=$("#year").val();
        var month=$("#month").val();
        location.href="<url:context/>/attendance.do?method=updateAttendanceSignRecordEntry"+
                "&attendanceInfo.respId="+repositoryId+"&query.year="+year+"&query.month="+month;
    }
    
    //考勤详情
    function goDetail(repositoryId){
        var year=$("#year").val();
        var month=$("#month").val();
        location.href="<url:context/>/attendance.do?method=updateAttendanceSignRecordDetail&attendanceInfo.respId="+repositoryId
        +"&query.year="+year+"&query.month="+month;
    }
    
    //需求待定：考勤列表修改事假,病假,倒休，正休，加班
    function doUpd(id){
        //此处待需求待定
        //$("."+id).prop("readonly", false);
    }
    
    function save(obj){
        var ss=$(obj);
        var url="/attendance.do?method=updateInfo";
        var id=ss.prop("class");
        var name=ss.prop("name");
        var value=ss.val();
         $.ajax({
            type : "POST",
            url : url,
            dataType : "json",
            data : {
                "updateKey" : name,
                "updateValue" : value,
                "id" : id
                
            },
            success : function(result) {
                if (result.success) {
                    ss.prop("readonly", true);
                } 
            }
        }); 
        
    };
    //执行查询操作
    function doQuery(){
        document.forms[0].submit();
    }

    //执行表单清空操作
    function doClear(){
        $("#year").val("-1");
        $("#month").val("-1");
        $("#staffNo").val("");
        $("#name").val("");
        $("#provinceName").val("");
        $("#cityName").val("");
        $("#dealerNames").val("");
        $("#bankNames").val("");
        $("input[type='radio']").removeAttr('checked');
        
    }
    function init(){
        $("#year").combobox({
            editable:false,
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
                }
            }
        });
        $("#month").combobox({
            editable:false,
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
                }
            }
        });
    }
    //导出execl
    function goExt(){
        $("#method").val("ExtAttendanceSheetLedger");
        document.forms[0].submit();
    }
</script>
</head>
<body  class="h-100 public">
<div class="add_nav">
    <span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">监管员信息管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">考勤表台账</a>
         </span>
</div>
<div class="public-main abs">
    <div class="ly-contai rel">
        <html:form action="/attendance" styleId="attendanceForm" method="post" onsubmit="return false">
            <input name="method" id="method" type="hidden" value="attendanceSheetLedger" />
            <html:hidden property="attendance.id" styleId="attendanceId" />
            <div class="public-main-input ly-col-3 hidden abs">
            <div class="ly-input-w" style="margin-top:30px;margin-bottom:-10px">
                <div class="ly-row clearfix">
                     <div class="ly-col fl">
                           <div class="label block fl hidden">年：</div>
                           <div class="input block fl hidden">
                               <div class="ly-sel-w">
                                    <select class="ly-bor-none" style="width:80%" id="year" name="query.year" >
                                        <c:forEach items="${years }" var="row">
                                            <option <c:if test="${attendanceForm.query.year==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
                                        </c:forEach>
                                    </select>
                               </div>
                                
                           </div>
                       </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">月：</div>
                        <div class="input block fl hidden">
                             <div class="ly-sel-w">
                                <select class="ly-bor-none" style="width:80%" id="month" name="query.month" >
                                    <c:forEach items="${months }" var="row">
                                        <option <c:if test="${attendanceForm.query.month==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
                                    </c:forEach>
                                </select>
                             </div>
                            
                         </div>
                    </div>
                    <div class="ly-col fl">
                       <div class="label block fl hidden">员工工号：</div>
                       <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="query.staffNo" styleId="staffNo"/>
                       </div>
                    </div>
                    <div class="ly-col fl">
                       <div class="label block fl hidden">员工姓名：</div>
                       <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="query.name" styleId="name"/>
                       </div>
                    </div>
                </div>
                <div class="ly-row clearfix">
                     <div class="ly-col fl">
                       <div class="label block fl hidden">所在省：</div>
                       <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="query.provinceName" styleId="provinceName"/>
                       </div>
                    </div>
                    <div class="ly-col fl">
                       <div class="label block fl hidden">所在市：</div>
                       <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="query.cityName" styleId="cityName"/>
                       </div>
                    </div>
                    <div class="ly-col fl">
                       <div class="label block fl hidden">经销商：</div>
                       <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="query.dealerNames" styleId="dealerNames"/>    
                       </div>
                    </div>
                    <div class="ly-col fl">
                       <div class="label block fl hidden">金融机构：</div>
                       <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="query.bankNames" styleId="bankNames"/>
                       </div>
                    </div>
                </div>
                <div class="ly-row clearfix">
                    <div class="ly-col fl" style="width: 50%">
                        <div class="label block fl hidden"style="width: 15%">审批状态：</div>
                        <div class="input block fl hidden" style="width: 85%">
                            <form:radios  property="query.approvalState" styleId="approvalState" collection="queryApprovalState"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="ly-button-w">
                <a href="javascript:doQuery();" class="button btn-query">查询</a>
                <a href="javascript:doClear();" class="button btn-reset">重置</a>
                <c:out value="${ attendanceForm.query.currentRole}"></c:out>
            </div>
            </div>
            <div class="public-main-table hidden abs">
                <div class="ly-cont">
                    <table class="t-table" border="0" cellspacing="0" cellpadding="0">
                        <thead class="t-thead">
                            <tr class="t-tr">
                                <%--  <c:if test="${ attendanceForm.query.currentRole == 9 || attendanceForm.query.currentRole == 80 }">
                                    <th class="t-th" rowspan="2">操作</th>
                                </c:if> --%>
                                
                                <!-- 需求89 超级角色只有查看权限 currentRole=80 -->
                                 <c:if test="${ attendanceForm.query.currentRole == 9 || attendanceForm.query.currentRole != 80}">
                                    <th class="t-th" rowspan="2">操作</th>
                                 </c:if>
                                 <!-- end -->
                                
                                <th class="t-th" rowspan="2">序号</th>
                                <th class="t-th" rowspan="2">员工工号</th> 
                                <th class="t-th" rowspan="2">姓名</th>
                                <th class="t-th" rowspan="2">省</th>
                                <th class="t-th" rowspan="2">市</th>
                                <th class="t-th" rowspan="2">经销商</th>
                                <th class="t-th" rowspan="2">金融机构</th>
                                <th class="t-th" rowspan="2">考勤详情</th>
                                <th class="t-th" colspan="2">事假</th>
                                <th class="t-th" colspan="2">病假</th>
                                <th class="t-th" rowspan="2">迟到</th>
                                <th class="t-th" rowspan="2">早退</th>
                                <th class="t-th" rowspan="2">旷工</th>
                                <th class="t-th" colspan="2">倒休</th>
                                <th class="t-th" colspan="2">正休</th>
                                <th class="t-th" colspan="2">加班</th>
                                <th class="t-th" rowspan="2">轮岗明细</th>
                                <th class="t-th" rowspan="2">实际出勤</th>
                                <th class="t-th" rowspan="2">全勤</th>
                                <th class="t-th" rowspan="2">备注</th>
                                <th class="t-th" rowspan="2">审批状态</th>
                                <%-- <c:if test="${ attendanceForm.query.currentRole == 8 || attendanceForm.query.currentRole == 80}">
                                    <th class="t-th" rowspan="2">操作</th>
                                </c:if> --%>
                            </tr>
                            <tr class="t-tr">
                                <th class="t-th">修改前</th>
                                <th class="t-th">修改后</th>
                                <th class="t-th">修改前</th>
                                <th class="t-th">修改后</th>
                                <th class="t-th">修改前</th>
                                <th class="t-th">修改后</th>
                                <th class="t-th">修改前</th>
                                <th class="t-th">修改后</th>
                                <th class="t-th">修改前</th>
                                <th class="t-th">修改后</th>
                            </tr>
                        </thead>
                        <tbody class="t-tbody hidden">
                            <logic:iterate name="list" id="row" indexId="index">
                                <tr class="t-tr">
                                    <%-- <c:if test="${ (attendanceForm.query.currentRole == 9 && row.state==3) || (attendanceForm.query.currentRole == 80 && row.state==3) }">
                                        <td class="t-td">
                                            <a href="javascript:doApproval('<c:out value="${row.id }"/>')" class="button btn-add fl" style="color: blue">审批不通过</a>
                                        </td>
                                    </c:if>
                                    <c:if test="${ (attendanceForm.query.currentRole == 9 && row.state!=3) || (attendanceForm.query.currentRole == 80 && row.state!=3) }">
                                        <td></td>
                                    </c:if> --%>
                                    
                                    <!-- 需求89 超级角色只有查看权限 currentRole=80 -->
                                    <c:if test="${ (attendanceForm.query.currentRole == 9 && row.state==3) || (attendanceForm.query.currentRole != 80 && row.state==3) }">
                                        <td class="t-td">
                                            <a href="javascript:doApproval('<c:out value="${row.id }"/>')" class="button btn-add fl" style="color: blue">审批不通过</a>
                                        </td>
                                    </c:if>
                                    <c:if test="${ (attendanceForm.query.currentRole == 9 && row.state!=3) || (attendanceForm.query.currentRole != 80 && row.state!=3) }">
                                        <td></td>
                                    </c:if>
                                    <!-- end -->
                                    
                                    <td class="t-td">&nbsp;<c:out value="${index+1}" /></td>
                                    <td class="t-td">&nbsp;<c:out value="${row.staffNo}" /></td>
                                    <td class="t-td">&nbsp;<c:out value="${row.name}" /></td>
                                    <td class="t-td">&nbsp;<c:out value="${row.provinceName}" /></td>
                                    <td class="t-td">&nbsp;<c:out value="${row.cityName}" /></td>
                                    <td class="t-td">&nbsp;<c:out value="${row.dealerNames}" /></td>
                                    <td class="t-td">&nbsp;<c:out value="${row.bankNames}" /></td>
                                    <td class="t-td">
                                    <c:if test="${ (attendanceForm.query.currentRole == 8 || attendanceForm.query.currentRole == 80 ) && (row.state==2 || row.state==0) }">
                                        <%-- <a style="color: blue" href="javascript:goUpdateEntry('<c:out value="${row.respId }"/>')">查看修改</a> --%>
                                        <!-- 需求87将“查看修改”改为“查看” -->
                                        <a style="color: blue" href="javascript:goDetail('<c:out value="${row.respId }"/>')">查看</a>
                                    </td>
                                    </c:if>
                                    <c:if test="${ ( row.state !=2 && row.state !=0) || (attendanceForm.query.currentRole == 9) }">
                                        <a style="color: blue" href="javascript:goDetail('<c:out value="${row.respId }"/>')">查看</a></td>
                                    </c:if>
                                    <!-- 事假 -->
                                    <td class="t-td">&nbsp;<c:out value="${row.matterHoliday}" /></td>
                                    <td class="t-td">&nbsp;
                                    <input type="text" class='<c:out value="${row.id}" />'
                                     readonly="readonly" name="matterHolidayUpdate"  onchange="save(this);"
                                     value='<c:out value="${row.matterHolidayUpdate}" />'
                                     />
                                     <%-- <input type="text" class='<c:out value="${row.id}" />'
                                     name="matterHolidayUpdate"  onchange="save(this);"
                                     value='<c:out value="${row.matterHolidayUpdate}" />'
                                     /> --%>
                                    </td>
                                    <!-- 病假 -->
                                    <td class="t-td">&nbsp;<c:out value="${row.ailingHoliday}" /></td>
                                    <td class="t-td">&nbsp;
                                    <input type="text"  class='<c:out value="${row.id}" />' onchange="save(this);"
                                     readonly="readonly" name="ailingHolidayUpdate" 
                                     value='<c:out value="${row.ailingHolidayUpdate}" />'
                                     />
                                     <%-- <input type="text"  class='<c:out value="${row.id}" />' onchange="save(this);"
                                     name="ailingHolidayUpdate" 
                                     value='<c:out value="${row.ailingHolidayUpdate}" />'
                                     /> --%>
                                    </td>
                                    <td class="t-td">&nbsp;<c:out value="${row.lateDay}" /></td>
                                    <td class="t-td">&nbsp;<c:out value="${row.earlyDay}" /></td>
                                    <td class="t-td">&nbsp;<c:out value="${row.absenteeism}" /></td>
                                    <!-- 倒休 -->
                                    <td class="t-td">&nbsp;<c:out value="${row.daoxiu}" /></td>
                                    <td class="t-td">&nbsp;
                                     <input type="text"  class='<c:out value="${row.id}" />'
                                     readonly="readonly" name="daoxiuUpdate" 
                                     value='<c:out value="${row.daoxiuUpdate}" />' onchange="save(this);"
                                     />
                                    <%-- <input type="text"  class='<c:out value="${row.id}" />'
                                     name="daoxiuUpdate" value='<c:out value="${row.daoxiuUpdate}" />' 
                                     onchange="save(this);"/> --%>
                                    </td>
                                    <!-- 正休 -->
                                    <td class="t-td">&nbsp;<c:out value="${row.zhengxiu}" /></td>
                                    <td class="t-td">&nbsp;
                                      <input type="text" class='<c:out value="${row.id}" />'
                                      readonly="readonly" name="zhengxiuUpdate"  onchange="save(this);"
                                      value='<c:out value="${row.zhengxiuUpdate}" />'
                                     />
                                    <%-- <input type="text" class='<c:out value="${row.id}" />'
                                      name="zhengxiuUpdate"  onchange="save(this);"
                                      value='<c:out value="${row.zhengxiuUpdate}" />'
                                     /> --%>
                                    </td>
                                    <!-- 加班 -->
                                    <td class="t-td">&nbsp;<c:out value="${row.overtime}" /></td>
                                    <td class="t-td">&nbsp;
                                       <input type="text" class='<c:out value="${row.id}" />'
                                       readonly="readonly" name="overtimeUpdate"  onchange="save(this);"
                                       value='<c:out value="${row.overtimeUpdate}" />'
                                     />
                                    <%-- <input type="text" class='<c:out value="${row.id}" />'
                                       name="overtimeUpdate"  onchange="save(this);"
                                       value='<c:out value="${row.overtimeUpdate}" />'
                                     /> --%>
                                    </td>
                                    <td class="t-td">&nbsp;<c:out value="${row.changePostInfo}" /></td>
                                    <td class="t-td">&nbsp;<c:out value="${row.actualAttendance}" /></td>
                                    <td class="t-td">
                                        <c:choose>  
                                           <c:when test="${row.fullTime==1}">是</c:when>  
                                           <c:when test="${row.fullTime==0}">否 </c:when>  
                                        </c:choose> 
                                    </td>
                                    <td class="t-td">&nbsp;<c:out value="${row.remark}" /></td>
                                    <td class="t-td"><select:approvalState type="${row.state }"></select:approvalState></td>
                                   <%--  <c:if test="${(row.state==0 || row.state==2) && (attendanceForm.query.currentRole == 8 || attendanceForm.query.currentRole == 80)}">
                                        <td class="t-td">
                                                <a style="color: blue" href="javascript:doUpd('<c:out value="${row.id }"/>')">更新</a>
                                        </td>
                                    </c:if> --%>
                                    
                                </tr>
                            </logic:iterate>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="public-main-footer hidden abs">
                <!-- 需求89 超级角色只有查看权限 currentRole=80 -->
                <c:if test="${attendanceForm.query.currentRole != 80}">
                <a href="javascript:goExt()" class="button btn-add fl">导出考勤表台账</a>
                </c:if>
                <!-- end -->
                
                <c:if test="${(attendanceForm.query.currentRole == sc || attendanceForm.query.currentRole == 8 || attendanceForm.query.currentRole == 9) && attendanceForm.attendance.state == 0 && listSize>0}">
                    <a href="javascript:doSubmit()" class="button btn-add fl">提交</a>
                </c:if>
                <div class="public-main-footer-pagin fr">
                    <thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="attendanceSheetLedger" action="/attendance.do?method=attendanceSheetLedger" />
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