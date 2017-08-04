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
    //限制“超级角色”操作
    var crole = $("#crole").val();
    if(crole == 80){
       $('input[type="text"][onchange="save(this);"]').attr("readonly","readonly");
    }
});

    //考勤更新
    function goUpdateEntry(repositoryId){
        var year=$("#year").val();
        var month=$("#month").val();
        location.href="<url:context/>/attendance.do?method=updateSignRecordCheckSpList"+
                "&squery.respId="+repositoryId+"&squery.year="+year+"&squery.month="+month;
    }
    
    //考勤详情
    function goDetail(repositoryId){
        var year=$("#year").val();
        var month=$("#month").val();
        location.href="<url:context/>/attendance.do?method=searchSignRecordCheckSpListDetails&squery.respId="+repositoryId
        +"&squery.year="+year+"&squery.month="+month;
    }
    
    
    //执行查询操作
    function doQuery(){
        $("#method").val("ToExtSignRecordCheckSpList");
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
    //导出
    function goExt(){
        $("#method").val("ExtSignRecordCheckSpList");
        document.forms[0].submit();
    }
    //提交
    function doSubmit(repositoryId){
        var year=$("#year").val();
        var month=$("#month").val();
        if(confirm("确认提交？")){
            location.href="<url:context/>/attendance.do?method=SubmitSignRecordCheckSpToApprove"+
                "&squery.respId="+repositoryId+"&squery.year="+year+"&squery.month="+month;
        }
        
    }
    //审批页面
    function doApproval(repositoryId){
        var year=$("#year").val();
        var month=$("#month").val();
        location.href="<url:context/>/attendance.do?method=toApprovalPage"+
            "&squery.respId="+repositoryId+"&squery.year="+year+"&squery.month="+month;
    }
</script>
</head>
<body  class="h-100 public">
<!-- 需求89 超级角色只有查看权限 currentRole=80 -->
<input type="hidden" value='<c:out value="${attendanceForm.squery.currentRole }" />' id="crole" />
<!-- end -->
<div class="add_nav">
    <span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">监管员信息管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">每日考勤详情台账</a>
         </span>
</div>
<div class="public-main abs">
    <div class="ly-contai rel">
        <html:form action="/attendance" styleId="attendanceForm" method="post" onsubmit="return false">
            <input name="method" id="method" type="hidden" value="ToExtSignRecordCheckSpList" />
            <html:hidden property="query.id" styleId="attendanceId" />
            <div class="public-main-input ly-col-3 hidden abs">
            <div class="ly-input-w" style="margin-top:30px;margin-bottom:-10px">
                <div class="ly-row clearfix">
                     <div class="ly-col fl">
                           <div class="label block fl hidden">年：</div>
                           <div class="input block fl hidden">
                               <div class="ly-sel-w">
                                    <select class="ly-bor-none" style="width:80%" id="year" name="squery.year" >
                                        <c:forEach items="${years }" var="row">
                                            <option <c:if test="${attendanceForm.squery.year==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
                                        </c:forEach>
                                    </select>
                               </div>
                                
                           </div>
                       </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">月：</div>
                        <div class="input block fl hidden">
                             <div class="ly-sel-w">
                                <select class="ly-bor-none" style="width:80%" id="month" name="squery.month" >
                                    <c:forEach items="${months }" var="row">
                                        <option <c:if test="${attendanceForm.squery.month==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
                                    </c:forEach>
                                </select>
                             </div>
                            
                         </div>
                    </div>
                    <div class="ly-col fl">
                       <div class="label block fl hidden">员工工号：</div>
                       <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="squery.staffNo" styleId="staffNo"/>
                       </div>
                    </div>
                    <div class="ly-col fl">
                       <div class="label block fl hidden">员工姓名：</div>
                       <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="squery.name" styleId="name"/>
                       </div>
                    </div>
                </div>
                <div class="ly-row clearfix">
                     <div class="ly-col fl">
                       <div class="label block fl hidden">所在省：</div>
                       <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="squery.provinceName" styleId="provinceName"/>
                       </div>
                    </div>
                    <div class="ly-col fl">
                       <div class="label block fl hidden">所在市：</div>
                       <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="squery.cityName" styleId="cityName"/>
                       </div>
                    </div>
                    <div class="ly-col fl">
                       <div class="label block fl hidden">经销商：</div>
                       <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="squery.dealersName" styleId="dealerNames"/>    
                       </div>
                    </div>
                    <div class="ly-col fl">
                       <div class="label block fl hidden">金融机构：</div>
                       <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="squery.banksName" styleId="bankNames"/>
                       </div>
                    </div>
                </div>
                <div class="ly-row clearfix">
                    <div class="ly-col fl" style="width: 50%">
                        <div class="label block fl hidden"style="width: 15%">审批状态：</div>
                        <div class="input block fl hidden" style="width: 85%">
                            <form:radios  property="squery.state" styleId="state" collection="queryApprovalState"/>
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
                                <th class="t-th">姓名</th>
                                <th class="t-th">员工工号</th> 
                                <th class="t-th">经销商</th>
                                <th class="t-th">金融机构</th>
                                <th class="t-th">省</th>
                                <th class="t-th">市</th>
                                <th class="t-th">1日</th>
                                <th class="t-th">2日</th>
                                <th class="t-th">3日</th>
                                <th class="t-th">4日</th>
                                <th class="t-th">5日</th>
                                <th class="t-th">6日</th>
                                <th class="t-th">7日</th>
                                <th class="t-th">8日</th>
                                <th class="t-th">9日</th>
                                <th class="t-th">10日</th>
                                <th class="t-th">11日</th>
                                <th class="t-th">12日</th>
                                <th class="t-th">13日</th>
                                <th class="t-th">14日</th>
                                <th class="t-th">15日</th>
                                <th class="t-th">16日</th>
                                <th class="t-th">17日</th>
                                <th class="t-th">18日</th>
                                <th class="t-th">19日</th>
                                <th class="t-th">20日</th>
                                <th class="t-th">21日</th>
                                <th class="t-th">22日</th>
                                <th class="t-th">23日</th>
                                <th class="t-th">24日</th>
                                <th class="t-th">25日</th>
                                <th class="t-th">26日</th>
                                <th class="t-th">27日</th>
                                <th class="t-th">28日</th>
                                <th class="t-th">29日</th>
                                <th class="t-th">30日</th>
                                <th class="t-th">31日</th>
                                <th class="t-th">最后修改人</th>
                                <th class="t-th">最后修改时间</th>
                                <th class="t-th">审批状态</th>
                                <th class="t-th">下一审批人</th>
                                <th class="t-th">操作</th>
                            </tr>
                        </thead>
                        <tbody class="t-tbody hidden">
                            <logic:iterate name="list" id="row" indexId="index">
                                <tr class="t-tr">
                                    <td class="t-td"><c:out value="${index+1}" /></td>
                                    <td class="t-td"><c:out value="${row.name}" /></td>
                                    <td class="t-td"><c:out value="${row.staffNo}" /></td>
                                    <td class="t-td"><c:out value="${row.dealersName}" /></td>
                                    <td class="t-td"><c:out value="${row.banksName}" /></td>
                                    <td class="t-td"><c:out value="${row.provinceName}" /></td>
                                    <td class="t-td"><c:out value="${row.cityName}" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_one }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_two}" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_three }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_four }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_five }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_six }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_seven }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_eight }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_nine }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_ten }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_Eleven }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_twelve }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_thirteen }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_fourteen }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_fifteen }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_sixteen }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_seventeen }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_eighteen }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_nineteen }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_twenty }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_twentyOne }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_twentyTwo }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_twentyThree }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_twentyFour }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_twentyFive }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_twentySix }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_twentySeven }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_twentyEight }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_twentyNine }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_thirty }" /></td>
                                    <td class="t-td"><c:out value="${row.days.day_thirtyOne }" /></td>
                                    <td class="t-td">
                                        <select:user userid="${row.updateUserId}"></select:user>
                                    </td>
                                    <td class="t-td">
                                        <select:timestamp idtype="date" timestamp="${row.updateDate }"></select:timestamp>
                                    </td>
                                    
                                    <td class="t-td">
                                          <select:approvalState type="${row.state }"></select:approvalState>
                                    </td>
                                    <td class="t-td"><select:nextApprovalName roleId="${row.nextApproval}" /></td>
                                    <td class="t-td">
                                        <a href="javascript:goDetail('<c:out value="${row.respId }"/>')">详情</a>
                                    </td>
                                </tr>
                            </logic:iterate>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="public-main-footer hidden abs">
                <a href="javascript:goExt()" class="button btn-add fl yc">导出每日考勤详情</a>
                <div class="public-main-footer-pagin fr">
                    <thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="ToExtSignRecordCheckSpList" action="/attendance.do?method=ToExtSignRecordCheckSpList" />
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