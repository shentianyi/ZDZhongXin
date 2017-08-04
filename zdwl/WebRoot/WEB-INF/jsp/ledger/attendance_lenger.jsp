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
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
	.title td{
		padding:0 15px 0 15px;
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
	initProvince();
	initStartTime(); 
	initEndTime();
	init();
});
function initProvince(){
	var url = "../json/findRegionByParentId.do?callback=?&parentId=0";
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setProvince(data);
	});
}
function setProvince(province){
	var provinces=$("#province");
	for(var i=0;i<province.length;i++){
		var option = "<option value="+province[i].id+">" + province[i].name
		+ "</option>";
		provinces.append(option);
	}
}
function changeProvince(id,nextSelect){
	if(id==-1){
		document.forms[0].reset();
		return;
	}
	var url = "../json/findRegionByParentId.do?callback=?&parentId="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setCity(data,nextSelect);
	});
}

function setCity(city,nextSelect){
	nextSelect.html("");
	var option = "<option value='-1' >请选择</option>";
	nextSelect.append(option);
	for(var i=0;i<city.length;i++){
		var option = "<option value="+city[i].id+">" + city[i].name
		+ "</option>";
		nextSelect.append(option);
	}
}
function initStartTime(){
	$("#startTime").datebox({    
		editable:false
	});
}
function initEndTime(){
	$("#endTime").datebox({    
		editable:false
	});   
	var c = $('#endTime').datebox('calendar');
	var applyTime = $("#startTime").datebox('getValue');
	c.calendar({
		validator: function(date){
			 if(date>applyTime){
				return true;
			} 
			return false;
		}
	});
}
	//执行查询操作
	function doQuery(){
		$("#method").val("findLedgerList");
		document.forms[0].submit();
	}

	//执行表单清空操作
	function doClear(){
		$("#startTime").val("");
		$("#endTime").val("");
		$("#staffNo").val("");
		$("#name").val("");
		$("#province").val("-1");
		$("#city").val("-1");
		$("#bankId").val("-1");
		$("#dealerId").val("-1");
		
	}
	function doExport(){
		$("#method").val("exportExcel");
		document.forms[0].submit();
	}
	function init(){
		$("#dealerId").combobox({
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
		$("#bankId").combobox({
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
	$(function () {
           $('#startTime').datebox({
               onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
                   span.trigger('click'); //触发click事件弹出月份层
                   if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                       tds = p.find('div.calendar-menu-month-inner td');
                       tds.click(function (e) {
                           e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
                           var year = /\d{4}/.exec(span.html())[0]//得到年份
                           , month = parseInt($(this).attr('abbr'), 10); //月份，这里不需要+1
                           $('#startTime').datebox('hidePanel')//隐藏日期对象
                           .datebox('setValue', year + '-' + month+'-'+1); //设置日期的值
                       });
                   }, 0);
                   yearIpt.unbind();//解绑年份输入框中任何事件
               },
               parser: function (s) {
                   if (!s) return new Date();
                   var arr = s.split('-');
                   return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
               },
               formatter: function (d) {
               var a = parseInt(d.getMonth())<parseInt('9')?'0'+parseInt(d.getMonth()+ 1):d.getMonth() + 1;
               return d.getFullYear() + '-' +a+"-"+1; }
           });
           var p = $('#startTime').datebox('panel'), //日期选择对象
               tds = false, //日期选择对象中月份
               yearIpt = p.find('input.calendar-menu-year'),//年份输入框
               span = p.find('span.calendar-text'); //显示月份层的触发控件
           console.log(yearIpt)
	  });
	$(function () {
        $('#endTime').datebox({
            onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
                span.trigger('click'); //触发click事件弹出月份层
                if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                    tds = p.find('div.calendar-menu-month-inner td');
                    tds.click(function (e) {
                        e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
                        var year = /\d{4}/.exec(span.html())[0]//得到年份
                        , month = parseInt($(this).attr('abbr'), 10); //月份，这里不需要+1
                        $('#endTime').datebox('hidePanel')//隐藏日期对象
                        .datebox('setValue', year + '-' + month+"-"+1); //设置日期的值
                    });
                }, 0);
                yearIpt.unbind();//解绑年份输入框中任何事件
            },
            parser: function (s) {
                if (!s) return new Date();
                var arr = s.split('-');
                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
            },
            formatter: function (d) {
            var a = parseInt(d.getMonth())<parseInt('9')?'0'+parseInt(d.getMonth()+ 1):d.getMonth() + 1;
            return d.getFullYear() + '-' +a+'-'+1; }
        });
        var p = $('#endTime').datebox('panel'), //日期选择对象
            tds = false, //日期选择对象中月份
            yearIpt = p.find('input.calendar-menu-year'),//年份输入框
            span = p.find('span.calendar-text'); //显示月份层的触发控件
        console.log(yearIpt)
	  });
</script>
</head>
<body  class="h-100 public">
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">台账管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">考勤表</a>
         </span>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/ledger/attendance" styleId="attendanceForm" method="post" onsubmit="return false" enctype="multipart/form-data">
			<input name="method" id="method" type="hidden" value="findLedgerList" />
			<html:hidden property="attendance.id" styleId="attendanceId" />
			<div class="public-main-input ly-col-3 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					 <div class="ly-col fl">
                       <div class="label block fl hidden">起始时间：</div>
                       <div class="input block fl hidden  add_easyui">
                       		<input editable="false" class="easyui-datebox" type="text"
								name="query.startTime" id="startTime" style="width: 80%"></input>
                      </div>
	                </div>
					<div class="ly-col fl">
                        <div class="label block fl hidden">结束时间：</div>
                        <div class="input block fl hidden  add_easyui">
                       		 <input editable="false" class="easyui-datebox" type="text"
								name="query.endTime" id="endTime" style="width: 80%"></input>
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
                       		<form:select style="width:80%" styleClass="ly-bor-none" property="query.provinceId" onchange="changeProvince(this.value,$('#city'))" 
							styleId="province" >
							<html:option value="-1">请选择</html:option>
						</form:select>
                       </div>
	                </div>
	                <div class="ly-col fl">
                       <div class="label block fl hidden">所在市：</div>
                       <div class="input block fl hidden">
                       		<form:select style="width:80%" styleClass="ly-bor-none" property="query.cityId" 
							styleId="city" >
							<html:option value="-1">请选择</html:option>
						</form:select>
                       </div>
	                </div>
	                 <div class="ly-col fl">
                       <div class="label block fl hidden">经销商：</div>
                       <div class="input block fl hidden  add_easyui">
							<select style="width:80%" id="dealerId" name="query.dealerId" >
								<option value="-1">请选择</option>
								<c:forEach items="${dealers }" var="row">
									<option  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
								</c:forEach>
							</select>
                       </div>
	                </div>
                   <div class="ly-col fl">
                       <div class="label block fl hidden">金融机构：</div>
                       <div class="input block fl hidden  add_easyui">
                      	 	<select style="width:80%" id="bankId" name="query.bankId" >
								<option value="-1">请选择</option>
								<c:forEach items="${banks }" var="row">
									<option  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
								</c:forEach>
							</select>
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
								<th class="t-th" >序号</th>
								<th class="t-th" >员工工号</th> 
								<th class="t-th" >姓名</th>
								<th class="t-th" >年月</th>
								<th class="t-th" >省</th>
								<th class="t-th" >市</th>
								<th class="t-th" >经销商</th>
								<th class="t-th" >金融机构</th>
								<th class="t-th" >1</th>
								<th class="t-th" >2</th>
								<th class="t-th" >3</th>
								<th class="t-th" >4</th>
								<th class="t-th" >5</th>
								<th class="t-th" >6</th>
								<th class="t-th" >7</th>
								<th class="t-th" >8</th>
								<th class="t-th" >9</th>
								<th class="t-th" >10</th>
								<th class="t-th" >11</th>
								<th class="t-th" >12</th>
								<th class="t-th" >13</th>
								<th class="t-th" >14</th>
								<th class="t-th" >15</th>
								<th class="t-th" >16</th>
								<th class="t-th" >17</th>
								<th class="t-th" >18</th>
								<th class="t-th" >19</th>
								<th class="t-th" >20</th>
								<th class="t-th" >21</th>
								<th class="t-th" >22</th>
								<th class="t-th" >23</th>
								<th class="t-th" >24</th>
								<th class="t-th" >25</th>
								<th class="t-th" >26</th>
								<th class="t-th" >27</th>
								<th class="t-th" >28</th>
								<th class="t-th" >29</th>
								<th class="t-th" >30</th>
								<th class="t-th" >31</th>
								<th class="t-th" >事假</th>
								<th class="t-th" >病假</th>
								<th class="t-th" >迟到</th>
								<th class="t-th" >早退</th>
								<th class="t-th" >旷工</th>
								<th class="t-th" >倒休</th>
								<th class="t-th" >正休</th>
								<th class="t-th" >加班</th>
								<th class="t-th" >轮岗明细</th>
								<th class="t-th" >实际出勤</th>
								<th class="t-th" >全勤</th>
								<th class="t-th" >备注</th>
								<th class="t-th" >审批状态</th>
							</tr>
						</thead>
						<tbody class="t-tbody hidden">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td">&nbsp;<c:out value="${index+1}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.staffNo}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.name}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.date}" /></td>
 									<td class="t-td">&nbsp;<c:out value="${row.provinceName}" /></td>
 									<td class="t-td">&nbsp;<c:out value="${row.cityName}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.dealerNames}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.bankNames}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.one}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.two}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.three}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.four}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.five}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.six}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.seven}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.eight}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.nine}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.ten}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.eleven}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.twelve}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.thirteen}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.fourteen}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.fifteen}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.sixteen}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.seventeen}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.eighteen}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.nineteen}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.twenty}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.twentyOne}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.twentyTwo}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.twentyThree}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.twentyFour}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.twentyFive}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.twentySix}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.twentySeven}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.twentyEight}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.twentyNine}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.thirty}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.thirtyOne}" /></td>
									<td class="t-td">
										<c:out value="${row.matterHoliday}" />
										<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="<c:out value='${row.matterHolidayUpdate}' />" style="width: 40px;text-align:right;"  readonly="readonly"/>
									</td>
									<td class="t-td">
										<c:out value="${row.ailingHoliday}" />
										<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="<c:out value='${row.ailingHolidayUpdate}' />" style="width: 40px;text-align:right;"  readonly="readonly"/>
									</td> 
									<td class="t-td">&nbsp;<c:out value="${row.lateDay}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.earlyDay}" /></td>
									<td class="t-td">&nbsp;<c:out value="${row.absenteeism}" /></td>
									<td class="t-td">
										<c:out value="${row.daoxiu}" />
										<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="<c:out value='${row.daoxiuUpdate}' />" style="width: 40px;text-align:right;"  readonly="readonly"/>
									</td> 
									<td class="t-td">
										<c:out value="${row.zhengxiu}" />
										<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="<c:out value='${row.zhengxiuUpdate}' />" style="width: 40px;text-align:right;"  readonly="readonly"/>
									</td>
									<td class="t-td">
										<c:out value="${row.overtime}" />
										<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="<c:out value='${row.overtimeUpdate}' />" style="width: 40px;text-align:right;"  readonly="readonly"/>
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
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
			<div class="public-main-footer hidden abs">
					<a href="javascript:doExport()" class="button btn-add fl">导出</a>
				<div class="public-main-footer-pagin fr">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="attendanceLegerList" action="/ledger/attendance.do?method=findLedgerList" />
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