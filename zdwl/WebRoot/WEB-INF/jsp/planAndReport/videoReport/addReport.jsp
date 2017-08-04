﻿<%@page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ page import="com.zd.csms.constants.Constants"%>
<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<link href="/css/public.css" rel="stylesheet" type="text/css" />
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
a{cursor: pointer;}
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
 .formButton{
width:10%;
}
</style>
<!--   <script type="text/javascript"> 
	var oBtn = document.getElementById('btn'); 
	var oSel = document.getElementById('sel'); 
		oBrn.onclick=function(){
			var oNewSel = oSel.cloneNode(ture);
			document.body.appendChild(oNewSel);

}; 
  </script>  -->
  <script type="text/javascript">
	window.onload = function(){
	var reasonWriteVal = $("#asif").val();
	 if(reasonWriteVal == 1){
	 $("#inputchecked").prop("checked",true);
	 $("#hideDiv").hide();
	} else{
		 
	}
}
</script> 
 
</head>
<body>
	<div class="title">视频检查报告</div>
	<hr size="1">
	<br />
	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/videoReport.do" styleId="videoReportForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="addReport" />
				<input type="hidden" name="videoReport.id"
					value="<c:out value='${planId}'/>" />
					<input type="hidden" id="asif" value="<c:out value='${videoReport.skip_fag}' />" />
				<table class="formTable" style="border-bottom: solid 1px #eee;">
					<tr >
						<td class="add_tdText">经销商名称：</td>
						<td class="addtdCount">
						<input type="text"
							name="videoReport.dealer_name" readonly="readonly"
							value="<c:out value='${dealer.dealerName}'/>" /></td>
						<td class="add_tdText">金融机构：</td>
						<td class="addtdCount"><input type="text"
							name="videoReport.bank_name" readonly="readonly"
							value="<c:out value='${dealer.bankName}'/>" /></td>
						<td class="add_tdText">品牌：</td>
						<td class="addtdCount" style="border-right: solid 1px #eee;"><input type="text"
							name="videoReport.brand" readonly="readonly"
							value="<c:out value='${dealer.brand}'/>" /></td>
					</tr>
					<tr>
						<td class="add_tdText">操作模式：</td>
						<td class="addtdCount"><input type="text"
							name="videoReport.supervisionMode" readonly="readonly"
							value="<c:out value='${dealer.supervisionMode}'/>" /></td>
						<td class="add_tdText">监管员：</td>
						<td class="addtdCount"><input type="text"
							name="videoReport.supervisor_name" readonly="readonly"
							value="<c:out value='${dealer.supervisorName}'/>" /></td>
						<td class="add_tdText" >工号：</td>
						<td class="addtdCount" ><input type="text"
							name="videoReport.staff_no"  style="border-right: solid 1px #eee;" readonly="readonly"
							value="<c:out value='${dealer.staffNo}'/>" /></td>
					</tr>
					<tr>
					<td class="addtdCount2" >视频专员：</td>
					<td class="add_tdText2" colspan="2"><input type="text"
							name="videoReport.check_name" readonly="readonly"
							value="<c:out value='${check_name}'/>" />
					</td>
					<td class="addtdCount2">最近检查日期：</td>

					<td class="add_tdText2" style="border-right:1px solid #eee" colspan="2"> <input type="text" name="videoReport.last_check_time" value="<c:out value='${plan.recentCheckTime}'/>" />  </td>
		
					</tr>
				<%-- 	<tr>
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
						<span><input type="checkbox" id="inputchecked" value="1"
							name="videoReport.skip_fag" style="float:left;width:30px;height:40px;"  onclick="skipWrite(this)"/></span>
							<span style="margin-top:12px;width:80px;display:block;float:right;">跳过填写：</span></td>
						<td class="add_tdText2" colspan="2">
						<input type="text"id="reasonWrite"  name="videoReport.reason" value="<c:out value='${videoReport.reason}'/>" style="width: 100%;"readonly="readonly" /></td>
						<td class="addtdCount2">检查日期：</td>
						<td class="add_tdText2" style="border-right: solid 1px #eee;"  colspan="2">
						<form:calendar property="videoReport.check_time" styleId="videoReport.check_time" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" /></td>
					</tr>
					<tr><td class="addtdCount2">系统库存情况：</td>
					<td class="add_tdText2" style="border-right:1px solid #eee" colspan="5"></td>
					</tr>
					<tr><td class="addtdCount2" >在库（台）：</td>
					<td class="add_tdText2" colspan="2"><input type="text" readonly="readonly" name="videoReport.stock_num" value='<c:out value="${plan.stock }" />' />  </td>
					<td class="addtdCount2">在途（台）：</td>
					<td class="add_tdText2" style="border-right:1px solid #eee"  colspan="2"><input type="text" readonly="readonly" name="videoReport.way" value="<c:out value='${dealer.wayNum }'/>" /> </td></tr>
					<tr><td class="addtdCount2" colspan="2"> 实际库存情况（电子总账）：</td>
					<td class="add_tdText2" style="border-right:1px solid #eee" colspan="4"></td>
					</tr>
					<tr><td class="addtdCount2" >在库（台）：</td>
					<td class="add_tdText2" colspan="2"><input type="text" name="videoReport.actual_stock"  />  </td>
					<td class="addtdCount2">在途（台）：</td>
					<td class="add_tdText2" colspan="2" style="border-right:1px solid #eee" colspan="2"><input type="text" name="videoReport.actual_way"  /> </td></tr>
				<tr><td class="addtdCount2" colspan="2">实际检查用时（分钟）：</td>
					<td class="add_tdText2" style="border-right:1px solid #eee" colspan="4"><input type="text"
							id="checkMinute" name="videoReport.check_minute"
							  /></td>
					</tr> 
					
				</table>
 
			<table id="hideDiv" class="formTable">
			<tr>
			<td class="problem_title_text" >问题分类</td>
			<td class="problem_title_text" >问题描述</td>
			<td class="problem_title_text" >归属部门</td>
			<td class="problem_title_text2">操作</td>
			</tr>
			<tbody id="demoid">
				<tr  class="demo">
					<td class="problem_table_td">
					<select class="ly-bor-none"  id="name_jflx2" name="question_classify1">
						<option value="-1">未选择</option>
						<option value="1">办公设备</option>
						<option value="2">办公工位</osption>
						<option value="3">其他</option>
					</select>
					</td>
					<td class="problem_table_td"><input type="text" class="requiredWrite"  placeholder="电脑、保险柜、打印机、传真机、网络…" name="question_desc1" /></td>
					<td class="problem_table_td">
					<select class="ly-bor-none"  id="name_jflx2" name="depart1">
						<option value="-1">未选择</option>
						<option value="5">市场部</option>
						<option value="2">监管员管理部</osption>
						<option value="4">业务部</option>
						<option value="6">风控部</option>
					</select>
					</td>
					<td class="problem_table_td2"><a id="delRow"  onclick="javascript:delTR(this);">删除</a></td>
				</tr>
			</tbody>
			<tr>
			<td class="problem_title dotted" colspan="4"><input class="btn addAppIntegral formButton" id="Insert_row"  type="button"    value="新&nbsp;增" /></td>
			</tr>
			<tbody id="demoid2">
				<tr  class="demo2">
					<td  class="problem_table_td">
					<select class="ly-bor-none"  id="name_jflx2" name="question_classify2">
						<option value="-1">未选择</option>
						<option value="4">入职资料</option>
						<option value="5">考勤</osption>
						<option value="6">其他</option>
					</select>
					</td>
					<td class="problem_table_td"><input type="text" class="requiredWrite" placeholder="设备接收确认书、作息时间表" name="question_desc2" /></td>
					<td class="problem_table_td">
					<select class="ly-bor-none"  id="name_jflx2" name="depart2">
						<option value="-1">未选择</option>
						<option value="5">市场部</option>
						<option value="2">监管员管理部</osption>
						<option value="4">业务部</option>
						<option value="6">风控部</option>
					</select>
					</td>
					<td class="problem_table_td2"><a id="delRow"  onclick="javascript:delTR(this);">删除</a></td>
				</tr>
			</tbody>
			<tr>
			<td class="problem_title dotted" colspan="4"><input id="Insert_row2"class="btn addAppIntegral formButton"   type="button"    value="新&nbsp;增" /></td>
			</tr>
			<tbody id="demoid3">
				<tr  class="demo3">
					<td class="problem_table_td">
					<select class="ly-bor-none"  id="name_jflx2" name="question_classify3">
						<option value="-1">未选择</option>
						<option value="7">进店资料</option>
						<option value="8">总账</osption>
						<option value="9">系统</option>
						<option value="10">手工台账</osption>
						<option value="11">日查库表单</option>
						<option value="12">钥匙交接表</osption>
						<option value="13">监管确认书</option>
						<option value="14">释放申请书</osption>
						<option value="15">移动申请书</option>
						<option value="16">月库存核对清单</option>
						<option value="17">私自放证</option>
						<option value="18">提前放证</option>
						<option value="19">私移</option>
						<option value="20">私售</option>
						<option value="21">其他</option>
					</select>
					</td>
					<td class="problem_table_td"><input type="text" class="requiredWrite" placeholder="委任书、授权书、经销商告知函" name="question_desc3" /></td>
					<td class="problem_table_td">
					<select class="ly-bor-none"  id="name_jflx2" name="depart3">
						<option value="-1">未选择</option>
						<option value="5">市场部</option>
						<option value="2">监管员管理部</osption>
						<option value="4">业务部</option>
						<option value="6">风控部</option>
					</select>
					</td>
					<td class="problem_table_td2"><a id="delRow"  onclick="javascript:delTR(this);">删除</a></td>
				</tr>
			</tbody>
			<tr>
			<td class="problem_title dotted" colspan="4"><input id="Insert_row3" class="btn addAppIntegral3 formButton"  type="button"  value="新&nbsp;增" /></td>
			</tr>
			<tr>
			<td class="problem_title" style="border-bottom:1px solid #eee;font-size:14px;font-weight: 600;">评价</td>
			<td class="problem_title" style="border-bottom: 1px solid #eee" colspan="2" >
			<textarea name="videoReport.evaluate" style="border:solid 1px #eee;height:100px;"><c:out value="${videoReport.evaluate}" /></textarea></td>
			</tr>
			
			</table>
				<br />
				<br />
              
			 
			<button class="formButton" onClick="doSave()">保&nbsp;存</button>
			 &nbsp;&nbsp;&nbsp;&nbsp;
			<button class="formButton" onClick="doSubmit()">提&nbsp;交</button> 
			&nbsp;&nbsp;<button class="formButton" onClick="goList()">返&nbsp;回</button>
		</div>
	</div>
	</html:form>
	  <script type="text/javascript">
	     var doc = document,
     myTable = doc.getElementById("demoid");
       //添加tr
 	doc.getElementById("Insert_row").onclick = function(){
     var tr = doc.createElement("tr");
     for(var i=0; i<4; i++){
         var td = doc.createElement("td");
         tr.appendChild(td);
         if(i==0){
			td.innerHTML += "<select  class='ly-bor-none' name='question_classify1' ><option value='-1'>未选择</option><option value='1'>办公设备</option><option value='2'>办公工位</osption><option value='3'>其他</option></select>";
         }
         if(i==1){
			td.innerHTML += "<input type='text' class='requiredWrite' placeholder='电脑、保险柜、打印机、传真机、网络…' name='question_desc1' />";
         }
          if(i==2){
			td.innerHTML += '<select class="ly-bor-none" name="depart1"  id="name_jflx2" name="depart1"><option value="-1">未选择</option><option value="5">市场部</option><option value="2">监管员管理部</osption><option value="4">业务部</option><option value="6">风控部</option></select>';
         }
          if(i==3){
			td.innerHTML += "<a id='delRow'  onclick='javascript:delTR(this);'>删除</a>";
         }
     }
     myTable.appendChild(tr);
 }
  //添加tr2
  var doc2 = document,
     myTable2 = doc2.getElementById("demoid2");
 doc2.getElementById("Insert_row2").onclick = function(){
     var tr = doc2.createElement("tr");
     for(var i=0; i<4; i++){
         var td = doc2.createElement("td");
         tr.appendChild(td);
         if(i==0){
			td.innerHTML += '<select class="ly-bor-none"  id="name_jflx2" name="question_classify2"><option value="-1">未选择</option><option value="4">入职资料</option><option value="5">考勤</osption><option value="6">其他</option></select>';
         }
         if(i==1){
			td.innerHTML += '<input type="text" class="requiredWrite" placeholder="设备接收确认书、作息时间表" name="question_desc2" />';
         }
          if(i==2){
			td.innerHTML += '<select class="ly-bor-none"  id="name_jflx2" name="depart2"><option value="-1">未选择</option><option value="5">市场部</option><option value="2">监管员管理部</osption><option value="4">业务部</option><option value="6">风控部</option></select>';
         }
          if(i==3){
			td.innerHTML += "<a id='delRow'  onclick='javascript:delTR(this);'>删除</a>";
         }
     }
     myTable2.appendChild(tr);
 }
 
   //添加tr3
  var doc3 = document,
     myTable3 = doc3.getElementById("demoid3");
 doc3.getElementById("Insert_row3").onclick = function(){
     var tr = doc3.createElement("tr");
     for(var i=0; i<4; i++){
         var td = doc3.createElement("td");
         tr.appendChild(td);
         if(i==0){
			td.innerHTML += '<select class="ly-bor-none"  id="name_jflx2" name="question_classify3"><option value="-1">未选择</option><option value="7">进店资料</option><option value="8">总账</osption><option value="9">系统</option><option value="10">手工台账</osption><option value="11">日查库表单</option><option value="12">钥匙交接表</osption><option value="13">监管确认书</option><option value="14">释放申请书</osption><option value="15">移动申请书</option><option value="16">月库存核对清单</option><option value="17">私自放证</option><option value="18">提前放证</option><option value="19">私移</option><option value="20">私售</option><option value="21">其他</option></select>';
         }
         if(i==1){
			td.innerHTML += '<input type="text" class="requiredWrite" placeholder="委任书、授权书、经销商告知函" name="question_desc3" />';
         }
          if(i==2){
			td.innerHTML += '<select class="ly-bor-none"  id="name_jflx2" name="depart3"><option value="-1">未选择</option><option value="5">市场部</option><option value="2">监管员管理部</osption><option value="4">业务部</option><option value="6">风控部</option></select>';
         }
          if(i==3){
			td.innerHTML += '<a id="delRow"  onclick="javascript:delTR(this);">删除</a>';
         }
     }
     myTable3.appendChild(tr);
 }
 
 function delTR(obj) {
  var table=obj.parentNode.parentNode.parentNode;
  table.removeChild(obj.parentNode.parentNode);
}  
 </script>
</body>
</html>
