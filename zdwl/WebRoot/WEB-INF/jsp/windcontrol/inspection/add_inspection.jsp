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
<link rel="stylesheet" href="/css/fileupload/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet" href="/css/fileupload/jquery.fileupload.css"
	type="text/css" />
<link rel="stylesheet" href="/css/fileupload/jquery.fileupload-ui.css"
	type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<!--  图片上传-->
<%@include file="../../base/fileupload.jsp"%>
<script src="/pagejs/windcontrol/inspection.js"></script>
<script>
$(function() {
	$('#tt').tabs('disableTab', 1);
	$('#tt').tabs('disableTab', 2);
	$('#tt').tabs('disableTab', 3);
	$('#tt').tabs('disableTab', 4);
	$('#tt').tabs('disableTab', 5);
	$('#tt').tabs('disableTab', 6);
});

</script>
</head>
<body>
<h2 align="center"><c:if test="${dealerName !=null}"><c:out value='${dealerName}'/></c:if>巡检报告</h2>
	<div id="tt" class="easyui-tabs" style="width: 100%; height: 100%;">

		<div title="基础信息" style="padding: 20px; display: none;">
			<form id="inspectionInfo"
				action="/windcontrol/inspection.do?method=addAndUpdateInfo">
				<table class="formTable">
					<tr>
						<td class="nameCol">经销商：</td>
						<td class="codeCol" colspan="2"><input type="text"
							name="dealer_name" style="width: 80%;" /></td>
						<td class="nameCol">合作金融机构：</td>
						<td class="codeCol" colspan="2"><input type="text"
							name="bank" style="width: 90%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">客户经理：</td>
						<td class="codeCol"><input type="text"
							name="customer_manager" /></td>
						<td class="nameCol">品牌：</td>
						<td class="codeCol"><input type="text" name="brand" /></td>
						<td class="nameCol">企业性质：</td>
						<td class="codeCol"><input type="radio" value="1" checked
							name="company_type" />国企 <input type="radio" value="2"
							name="company_type" />民企 <input type="radio" value="3"
							name="company_type" />合资</td>
					</tr>
					<tr>
						<td class="nameCol">地址：</td>
						<td class="codeCol" colspan="5"><input type="text"
							name="address" style="width: 70%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">合作开始时间：</td>
						<td class="codeCol"><input type="text" class="easyui-datebox"
							name="cooperate_time" data-options="editable:false" /></td>
						<td class="nameCol">操作模式：</td>
						<td class="codeCol"><input type="radio" value="1" checked
							name="oper_mode" />车证 <input type="radio" value="2"
							name="oper_mode" />合格证 <input type="radio" value="3"
							name="oper_mode" />其他</td>
						<td class="nameCol">检查类型：</td>
						<td class="codeCol"><input type="radio" value="1" checked
							name="check_type" />常规检查 <input type="radio" value="2"
							name="check_type" />异常检查</td>
					</tr>
					<tr>
						<td class="nameCol">业务专员：</td>
						<td class="codeCol"><input type="text" name="business_name" /></td>
						<td class="nameCol">是否存在二库：</td>
						<td class="codeCol"><select class="easyui-combobox"
							style="width: 50%" name="exist_storage"
							data-options="editable:false">
								<option value="1">是</option>
								<option value="0" selected>否</option>
						</select></td>
						<td class="nameCol">是否存在二网：</td>
						<td class="codeCol"><select class="easyui-combobox"
							style="width: 50%" name="exist_network"
							data-options="editable:false">
								<option value="1">是</option>
								<option value="0" selected>否</option>
						</select></td>
					</tr>
					<tr>
						<td class="nameCol">检查人员：</td>
						<td class="codeCol"><input type="text" name="check_name" /></td>
						<td class="nameCol">检查日期：</td>
						<td class="codeCol"><input type="text" class="easyui-datebox"
							name="check_time" data-options="editable:false" /></td>
						<td class="nameCol">检查用时：</td>
						<td class="codeCol"><input type="text" name="check_period" /></td>
					</tr>
					<tr>
						<td class="nameCol">陪同人员：</td>
						<td class="codeCol" colspan="5"><input type="text"
							name="entourage" style="width: 70%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">基础信息介绍：</td>
						<td class="codeCol" colspan="5" >
						<textarea name="description" placeholder="例：***********公司为*****品牌经销店。该店成立于*****年，现为***地区唯一一家***品牌的一级代理商。*******公司为***集团企业，集团除西藏正和五菱店外，还经营者******业务，同时在内陆地区经营着****等产业，目前该店与*****支行融资合作，授信额度为：*********。该店月均销量为***台，设有***二库、***二网二级网点"></textarea>
						</td>
					</tr>
					<tr>
						<td class="nameCol">二网、二库地址及介绍：</td>
						<td class="codeCol" colspan="5">
						<textarea name="other_description"></textarea>
					   </td>
					</tr>
				</table>
				<input type="hidden" name="id"
					value="<c:out value='${inspectionId }'/>" />
			</form>
			<br /> <br /> <a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'"
				onClick="saveInfo('inspectionInfo',1)" style="margin-left: 45%">下一步</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" id="button1"
				data-options="iconCls:'icon-save'"
				onClick="next(1)" style="display: none">跳过</a>
			
		</div>
		<div title="检查内容" style="padding: 20px; display: none;">
			<form id="inspectionItem"
				action="/windcontrol/inspection.do?method=addAndUpdateItem">
				<table class="formTable">
					<tr>
						<td class="nameCol" style="width: 1%;">序号</td>
						<td class="nameCol" colspan="3" style="text-align: center;">项目</td>
						<td class="nameCol" style="text-align: center;">填写项(台)</td>
						<td class="nameCol" style="width: 35%; text-align: center;">备注</td>
					</tr>
					<tr>
						<td class="nameCol" rowspan="11" style="width: 1%;">1</td>
						<td class="nameCol" rowspan="11">质押物情况</td>
						<td class="nameCol" rowspan="7">质押车总数量 <input type="text"
							name="car_num" class="easyui-numberbox"
							style="width: 25%; height: 23px; line-height: 0px;" />台
						</td>
						<td class="nameCol">展厅及周边（台）</td>
						<td class="codeCol"><input type="text" name="hall_num"
							class="easyui-numberbox" style="width: 35%;" /></td>
						<td class="codeCol"><input type="text" name="hall_remark" placeholder="检查内容的备注可填字数不足85"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">二库（台）</td>
						<td class="codeCol"><input type="text" name="store_num"
							class="easyui-numberbox" style="width: 35%;" /></td>
						<td class="codeCol"><input type="text" name="store_remark"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">二网（台）</td>
						<td class="codeCol"><input type="text" name="network_num"
							class="easyui-numberbox" style="width: 35%;" /></td>
						<td class="codeCol"><input type="text" name="network_remark"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">销售未还款</td>
						<td class="codeCol"><input type="text" name="arrears"
							class="easyui-numberbox" style="width: 35%;" /></td>
						<td class="codeCol"><input type="text" name="arrears_remark"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">当日销售（台）</td>
						<td class="codeCol"><input type="text" name="sale_num"
							class="easyui-numberbox" style="width: 35%;" /></td>
						<td class="codeCol"><input type="text" name="sale_remark"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">私自售卖车辆（台）</td>
						<td class="codeCol"><input type="text" name="priv_sale"
							class="easyui-numberbox" style="width: 35%;" /></td>
						<td class="codeCol"><input type="text" name="privSale_remark"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">私自移动车辆（台）</td>
						<td class="codeCol"><input type="text" name="move_car"
							class="easyui-numberbox" style="width: 35%;" /></td>
						<td class="codeCol"><input type="text" name="moveCar_remark"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol" rowspan="2">合格证/关单 <input type="text"
							name="certificate_num" class="easyui-numberbox"
							style="width: 25%; height: 23px; line-height: 0px;" />份
						</td>
						<td class="nameCol">正常合格证数量</td>
						<td class="codeCol"><input type="text"
							name="normal_certificate" class="easyui-numberbox"
							style="width: 35%;" /></td>
						<td class="codeCol"><input type="text" name="normal_remark"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">异常合格证数量</td>
						<td class="codeCol"><input type="text"
							name="abnormal_certificate" class="easyui-numberbox"
							style="width: 35%;" /></td>
						<td class="codeCol"><input type="text" name="abnormal_remark"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol" rowspan="2">钥匙 <input type="text"
							name="key_num" class="easyui-numberbox"
							style="width: 25%; height: 23px; line-height: 0px;" />把
						</td>
						<td class="nameCol">正常钥匙数量</td>
						<td class="codeCol"><input type="text" name="normal_key"
							class="easyui-numberbox" style="width: 35%;" /></td>
						<td class="codeCol"><input type="text" name="key_remark"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">异常钥匙数量</td>
						<td class="codeCol"><input type="text" name="abnormal_key"
							class="easyui-numberbox" style="width: 35%;" /></td>
						<td class="codeCol"><input type="text"
							name="abnormalKey_remark" style="width: 100%;" /></td>
					</tr>

					<tr>
						<td class="nameCol" rowspan="6" style="width: 1%;">2</td>
						<td class="nameCol" rowspan="6">纸质文件情况</td>
						<td class="nameCol" rowspan="2">手工台账</td>
						<td class="nameCol">是否与总账一致</td>
						<td class="codeCol"><select style="width: 35%"
							class="easyui-combobox" name="manual_accounting"
							data-options="editable:false">
								<option value="1">是</option>
								<option value="0">否</option>
						</select></td>
						<td class="codeCol"><input type="text" name="manual_remark"
							style="width: 100%;" /></td>
					</tr>

					<tr>
						<td class="nameCol">授权人签字是否规范</td>
						<td class="codeCol"><select style="width: 35%"
							class="easyui-combobox" name="manual_standard"
							data-options="editable:false">
								<option value="1">是</option>
								<option value="0">否</option>
						</select></td>
						<td class="codeCol"><input type="text" name="standard_remark"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol" rowspan="2">日查库检查表</td>
						<td class="nameCol">是否与总账一致</td>
						<td class="codeCol"><select style="width: 35%"
							class="easyui-combobox" name="check_list"
							data-options="editable:false">
								<option value="1">是</option>
								<option value="0">否</option>
						</select></td>
						<td class="codeCol"><input type="text" name="check_remark"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">日期是否连续</td>
						<td class="codeCol"><select style="width: 35%"
							class="easyui-combobox" name="check_continuity"
							data-options="editable:false">
								<option value="1">是</option>
								<option value="0">否</option>
						</select></td>
						<td class="codeCol"><input type="text"
							name="continuity_remark" style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">授权书、委任书</td>
						<td class="nameCol">是否符合规范</td>
						<td class="codeCol"><select style="width: 35%"
							class="easyui-combobox" name="authorization"
							data-options="editable:false">
								<option value="1">是</option>
								<option value="0">否</option>
						</select></td>
						<td class="codeCol"><input type="text" name="auth_remark"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">钥匙交接及钥匙借出登记表</td>
						<td class="nameCol">授权人签字是否规范</td>
						<td class="codeCol"><select style="width: 35%"
							class="easyui-combobox" name="key_handover"
							data-options="editable:false">
								<option value="1">是</option>
								<option value="0">否</option>
						</select></td>
						<td class="codeCol"><input type="text" name="handover_remark"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol" rowspan="4" style="width: 1%;">3</td>
						<td class="nameCol" rowspan="4">汇票及押车情况</td>
						<td class="nameCol" rowspan="4">汇票异常情况</td>
						<td class="nameCol">开票1个月未押满</td>
						<td class="codeCol"><input type="text" name="billing"
							style="width: 85%;" /></td>
						<td class="codeCol"><input type="text" name="billing_remark"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">开票15日未押车</td>
						<td class="codeCol"><input type="text" name="guard"
							style="width: 85%;" /></td>
						<td class="codeCol"><input type="text" name="guard_remark"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">超期未清票</td>
						<td class="codeCol"><input type="text" name="clear_ticket"
							style="width: 85%;" /></td>
						<td class="codeCol"><input type="text"
							name="clearTicket_remark" style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">其他情况</td>
						<td class="codeCol"><input type="text" name="other"
							style="width: 85%;" /></td>
						<td class="codeCol"><input type="text" name="other_remark"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol" rowspan="4" style="width: 1%;">4</td>
						<td class="nameCol" rowspan="4">其他情况</td>
						<td class="nameCol">系统情况</td>
						<td class="nameCol">是否可以正常使用</td>
						<td class="codeCol"><select style="width: 35%"
							class="easyui-combobox" name="system_state"
							data-options="editable:false">
								<option value="1">是</option>
								<option value="0">否</option>
						</select></td>
						<td class="codeCol"><input type="text" name="state_remark"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">标识</td>
						<td class="nameCol">质押车是否摆放标识</td>
						<td class="codeCol"><select style="width: 35%"
							class="easyui-combobox" name="identification"
							data-options="editable:false">
								<option value="1">是</option>
								<option value="0">否</option>
						</select></td>
						<td class="codeCol"><input type="text"
							name="identification_remark" style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">办公设备</td>
						<td class="nameCol">是否符合检查要求</td>
						<td class="codeCol"><select style="width: 35%"
							class="easyui-combobox" name="office_machine"
							data-options="editable:false">
								<option value="1">是</option>
								<option value="0">否</option>
						</select></td>
						<td class="codeCol"><input type="text" name="machine_remark"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">办公条件</td>
						<td class="nameCol">是否为独立办公室</td>
						<td class="codeCol"><select style="width: 35%"
							class="easyui-combobox" name="office_conditions"
							data-options="editable:false">
								<option value="1">是</option>
								<option value="0">否</option>
						</select></td>
						<td class="codeCol"><input type="text"
							name="conditions_remark" style="width: 100%;" /></td>
					</tr>
				</table>
				<input type="hidden" name="id"
					value="<c:out value='${inspectionId }'/>" />
			</form>
			  <a href="#" class="easyui-linkbutton" 
				data-options="iconCls:'icon-save'"
				onClick="saveInfo('inspectionItem',2)" style="margin-left: 45%">下一步</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" id="button2"
				data-options="iconCls:'icon-save'"
				onClick="next(2)" style="display: none">跳过</a>
		</div>
		<div title="检查过程中发现的问题及监管员优/缺点" style="padding: 20px; display: none;">
			<form id="inspectionRecord"
				action="/windcontrol/inspection.do?method=addAndUpdateRecord">
				<table class="formTable" id="problem2">
					<tr>
						<td class="nameCol" style="width: 1%;">序号：</td>
						<td class="codeCol" colspan="5">发现问题</td>
					</tr>
				</table>

				<table class="formTable" id="advantage">
					<tr>
						<td class="nameCol" style="width: 1%;">序号：</td>
						<td class="codeCol" colspan="5">监管员优点/缺点</td>
					</tr>
				</table>
				<input type="hidden" name="inspectionId"
					value="<c:out value='${inspectionId }'/>" />
			</form>
			<br /> <a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'" onClick="addTr('problem2')">问题</a>&nbsp;
			&nbsp;&nbsp; <a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'" onClick="addTr('advantage')">优缺点</a>
			<br /> <br /> <a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" 
				onClick="saveRecord('inspectionRecord',3)" style="margin-left: 45%">下一步</a>
			    &nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" id="button3"
				data-options="iconCls:'icon-save'"
				onClick="next(3)" style="display: none">跳过</a>
		</div>
		<div title="与店方沟通情况" style="padding: 20px; display: none;">
			<form id="inspectionCommunication"
				action="/windcontrol/inspection.do?method=addAndUpdateCommunication">
				<table class="formTable">
					<tr>
						<td class="nameCol" style="width: 5%;">店方人员姓名：</td>
						<td class="codeCol" colspan="2"><input type="text"
							name="name" /></td>
						<td class="nameCol">职位：</td>
						<td class="codeCol" colspan="2"><input type="text"
							name="position" /></td>
					</tr>

					<tr>
						<td class="nameCol" style="width: 5%;">月销量（台）：</td>
						<td class="codeCol" colspan="2"><input type="text"
							class="easyui-numberbox" name="month_sales" /></td>
						<td class="nameCol">自有车辆（台）：</td>
						<td class="codeCol" colspan="2"><input type="text" name="car_count" /></td>
					</tr>
					<tr>
						<td class="nameCol" style="width: 5%;">座谈内容：</td>
						<td class="codeCol" colspan="5"><textarea name="content"></textarea></td>
					</tr>
					<tr>
						<td class="nameCol" style="width: 5%;">店方情况：</td>
						<td class="codeCol" colspan="5"><textarea name="info"></textarea></td>
					</tr>
					<tr>
						<td class="nameCol" style="width: 5%;">我司情况：</td>
						<td class="codeCol" colspan="5"><textarea name="company_info"></textarea></td>
					</tr>
				</table>
				<input type="hidden" name="id"
					value="<c:out value='${inspectionId }'/>" />
			</form>
			<br /> <a href="#" class="easyui-linkbutton"  
				data-options="iconCls:'icon-save'"
				onClick="saveInfo('inspectionCommunication',4)"
				style="margin-left: 45%">下一步</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton"  id="button4"
				data-options="iconCls:'icon-save'"
				onClick="next(4)" style="display: none">跳过</a>
		</div>
		<div title="监管员基本情况介绍" style="padding: 20px; display: none;">
			<form id="inspectionSupervisor"
				action="/windcontrol/inspection.do?method=addAndUpdateSupervisor">
				<table class="formTable">
					<tr>
						<td class="nameCol">监管员姓名：</td>
						<td class="codeCol"><input type="text" name="name" /></td>
						<td class="nameCol">联系方式：</td>
						<td class="codeCol"><input type="text"
							class="easyui-numberbox" name="contact_phone" /></td>
						<td class="nameCol">紧急联系方式：</td>
						<td class="codeCol"><input type="text"
							class="easyui-numberbox" name="emergency_telephone" /></td>
					</tr>
					<tr>
						<td class="nameCol">性别：</td>
						<td class="codeCol"><select style="width: 60%"
							class="easyui-combobox" name="sex" data-options="editable:false">
								<option value="0" selected>未知</option>
								<option value="1">男</option>
								<option value="2">女</option>
						</select></td>
						<td class="nameCol">学历：</td>
						<td class="codeCol" colspan="3"><input type="text"
							name="education" /></td>
					</tr>
					<tr>
						<td class="nameCol">年龄：</td>
						<td class="codeCol"><input type="text"
							class="easyui-numberbox" name="age" /></td>
						<td class="nameCol">公司认可属性：</td>
						<td class="codeCol" colspan="3"><input type="text"
							name="company_attr" style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">户口所在地：</td>
						<td class="codeCol"><input type="text"
							name="registered_address" /></td>
						<td class="nameCol">本人实际属性：</td>
						<td class="codeCol" colspan="3"><input type="text"
							name="people_attr" style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">住宿：</td>
						<td class="codeCol" colspan="2"><input type="text"
							name="address" style="width: 100%;" /></td>
						<td class="nameCol">用餐：</td>
						<td class="codeCol" colspan="2"><input type="text"
							name="meal" style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">电脑技能：</td>
						<td class="codeCol" colspan="2"><input type="text"
							name="computer_skills" style="width: 100%;" /></td>
						<td class="nameCol">招聘来源：</td>
						<td class="codeCol" colspan="2"><input type="text"
							name="job_channel" style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">培训方式：</td>
						<td class="codeCol" colspan="2"><input type="text"
							name="train_mode" style="width: 100%;" /></td>
						<td class="nameCol">培训人员：</td>
						<td class="codeCol" colspan="2"><input type="text"
							name="train_man" style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol" rowspan="2">周末休假：</td>
						<td class="codeCol" rowspan="2"><select style="width: 60%"
							class="easyui-combobox" name="weekend">
								<option value="1">是</option>
								<option value="0" selected>否</option>
						</select></td>
						<td class="nameCol" rowspan="2">上岗时间：</td>
						<td class="nameCol">初次上岗时间：</td>
						<td class="codeCol" colspan="2"><input type="text"
							class="easyui-datebox" name="posts_time"
							data-options="editable:false" /></td>
					</tr>
					<tr>
						<td class="nameCol">接管此店时间：</td>
						<td class="codeCol" colspan="2"><input type="text"
							class="easyui-datebox" name="takeOver_time"
							data-options="editable:false" /></td>
					</tr>
					<tr>
						<td class="nameCol">工作经历(监管员对工作、总部建议)：</td>
						<td class="codeCol" colspan="5"><textarea
								name="work_experience"></textarea></td>
					</tr>
					<tr>
						<td class="nameCol">巡检人员对监管员评价：</td>
						<td class="codeCol" colspan="5"><textarea name="evaluate"></textarea></td>
					</tr>
				</table>
				<input type="hidden" name="id"
					value="<c:out value='${inspectionId }'/>" />
			</form>
			<br /> <a href="#" class="easyui-linkbutton" 
				data-options="iconCls:'icon-save'"
				onClick="saveInfo('inspectionSupervisor',5)"
				style="margin-left: 45%">下一步</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton"  id="button5"
				data-options="iconCls:'icon-save'"
				onClick="next(5)" style="display: none">跳过</a>
		</div>
		<div title="巡店总结" style="padding: 20px; display: none;">
			<form id="inspectionResult"
				action="/windcontrol/inspection.do?method=addAndUpdateResult">
				<table class="formTable">
					<tr>
						<td class="nameCol" colspan="2" style="width: 5%;">风险等级：</td>
						<td class="codeCol"><input type="radio" value="4" checked
							name="danger_level" />无</td>
						<td class="codeCol"><input type="radio" value="1"
							name="danger_level" />A级</td>
						<td class="codeCol"><input type="radio" value="2"
							name="danger_level" />B级</td>
						<td class="codeCol"><input type="radio" value="3"
							name="danger_level" />C级</td>
					</tr>
					<tr>
						<td class="nameCol" colspan="2" style="width: 5%;">经销店等级：</td>
						<td class="codeCol"><input type="radio" value="4" checked
							name="dealer_level" />无(新进驻一个月内)</td>
						<td class="codeCol"><input type="radio" value="1"
							name="dealer_level" />A级</td>
						<td class="codeCol"><input type="radio" value="2"
							name="dealer_level" />B级</td>
						<td class="codeCol"><input type="radio" value="3"
							name="dealer_level" />C级</td>
					</tr>
					<tr>
						<td class="nameCol" colspan="2" style="width: 5%;">监管员等级：</td>
						<td class="codeCol"><input type="radio" value="4" checked
							name="supervisor_level" />无(新进驻一个月内)</td>
						<td class="codeCol"><input type="radio" value="1"
							name="supervisor_level" />A级</td>
						<td class="codeCol"><input type="radio" value="2"
							name="supervisor_level" />B级</td>
						<td class="codeCol"><input type="radio" value="3"
							name="supervisor_level" />C级</td>
					</tr>
					<tr>
						<td class="codeCol" colspan="6"><textarea name="remak"></textarea></td>
					</tr>
				</table>
				<input type="hidden" name="id"
					value="<c:out value='${inspectionId }'/>" />
			</form>
			<br /> <a href="#" class="easyui-linkbutton" 
				data-options="iconCls:'icon-save'"
				onClick="saveInfo('inspectionResult',6)" style="margin-left: 50%">下一步</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton"  id="button6"
				data-options="iconCls:'icon-save'"
				onClick="next(6)" style="display: none">跳过</a>
		</div>
		<div title="店面拍照" style="padding: 20px; display: none;">
			<div style="margin-left: 18px;">
				<form id="fileupload"
					action="/windcontrol/inspection.do?method=showPicture"
					method="POST" enctype="multipart/form-data" class="">
					<div class="row fileupload-buttonbar">
						<div class="col-lg-7">
							<span class="btn btn-success fileinput-button"> <i
								class="glyphicon glyphicon-plus"></i> <span>添加文件</span><input
								type="file" name="file" multiple="" />
							</span> &nbsp;&nbsp;&nbsp;
							 <button type="submit" class="btn btn-primary start">
								<i class="glyphicon glyphicon-upload"></i> <span>开始上传</span>
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="reset" class="btn btn-warning cancel">
								<i class="glyphicon glyphicon-ban-circle"></i> <span>取消上传</span>
							</button>
							<span class="fileupload-process"></span>
						</div>

						<div class="col-lg-5 fileupload-progress fade">
							<div class="progress progress-striped active" role="progressbar"
								aria-valuemin="0" aria-valuemax="100">
								<div class="progress-bar progress-bar-success"
									style="width: 0%;"></div>
							</div>

							<div class="progress-extended">&nbsp;</div>
						</div>
					</div>

					<table role="presentation" class="table table-striped">
						<tbody class="files">
						</tbody>
					</table>
					<input id="inspectionId" type="hidden" name="inspectionId"
						value="<c:out value='${inspectionId }'/>" />
				</form>
             
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'"
					onClick="goSubmit('<c:out value='${inspectionId }'/>','<c:out value='${dealerName }'/>')"
					style="margin-left: 50%">提交</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
					href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-save'" onClick="goList()">返回</a>
			</div>
			<%@include file="../../base/fileuploadShow.jsp"%>
		</div>
	</div>

</body>
</html>
