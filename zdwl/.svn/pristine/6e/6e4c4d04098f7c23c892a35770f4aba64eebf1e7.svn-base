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
<style type="text/css">
h2{
width:100%;
margin: 0 auto;
text-align: center;
}
</style>
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
							name="dealer_name" style="width: 80%;"
							value="<c:out value='${inspectionReport.info.dealer_name }'/>" /></td>
						<td class="nameCol">合作金融机构：</td>
						<td class="codeCol" colspan="2"><input type="text"
							name="bank" style="width: 90%;"
							value="<c:out value='${inspectionReport.info.bank}'/>" /></td>
					</tr>
					<tr>
						<td class="nameCol">客户经理：</td>
						<td class="codeCol"><input type="text"
							name="customer_manager"
							value="<c:out value='${inspectionReport.info.customer_manager}'/>" /></td>
						<td class="nameCol">品牌：</td>
						<td class="codeCol"><input type="text" name="brand"
							value="<c:out value='${inspectionReport.info.brand}'/>" /></td>
						<td class="nameCol">企业性质：</td>
						<td class="codeCol"><input type="radio" value="1"
							<c:if test="${inspectionReport.info.company_type==1 }">checked='checked'</c:if>
							name="company_type" />国企 <input type="radio" value="2"
							<c:if test="${inspectionReport.info.company_type==2 }">checked='checked'</c:if>
							name="company_type" />民企 <input type="radio" value="3"
							<c:if test="${inspectionReport.info.company_type==3 }">checked='checked'</c:if>
							name="company_type" />合资</td>
					</tr>
					<tr>
						<td class="nameCol">地址：</td>
						<td class="codeCol" colspan="5"><input type="text"
							name="address" style="width: 70%;"
							value="<c:out value='${inspectionReport.info.address}'/>" /></td>
					</tr>
					<tr>
						<td class="nameCol">合作开始时间：</td>
						<td class="codeCol"><input type="text" class="easyui-datebox"
							name="cooperate_time" data-options="editable:false"
							value="<c:out value='${inspectionReport.info.cooperate_time}'/>" /></td>
						<td class="nameCol">操作模式：</td>
						<td class="codeCol"><input type="radio" value="1"
							<c:if test="${inspectionReport.info.oper_mode==1 }">checked='checked'</c:if>
							name="oper_mode" />车证 <input type="radio" value="2"
							<c:if test="${inspectionReport.info.oper_mode==3 }">checked='checked'</c:if>
							name="oper_mode" />合格证 <input type="radio" value="3"
							<c:if test="${inspectionReport.info.oper_mode==3 }">checked='checked'</c:if>
							name="oper_mode" />其他</td>
						<td class="nameCol">检查类型：</td>
						<td class="codeCol"><input type="radio" value="1"
							<c:if test="${inspectionReport.info.check_type==1 }">checked='checked'</c:if>
							name="check_type" />常规检查 <input type="radio" value="2"
							<c:if test="${inspectionReport.info.check_type==2 }">checked='checked'</c:if>
							name="check_type" />异常检查</td>
					</tr>
					<tr>
						<td class="nameCol">业务专员：</td>
						<td class="codeCol"><input type="text" name="business_name"
							value="<c:out value='${inspectionReport.info.business_name}'/>" /></td>
						<td class="nameCol">是否存在二库：</td>
						<td class="codeCol"><select class="easyui-combobox"
							style="width: 50%" name="exist_storage"
							data-options="editable:false">
								<option value="1"
									<c:if test="${inspectionReport.info.exist_storage==1 }">selected='selected'</c:if>>是</option>
								<option value="0"
									<c:if test="${inspectionReport.info.exist_storage==0 }">selected='selected'</c:if>>否</option>
						</select></td>
						<td class="nameCol">是否存在二网：</td>
						<td class="codeCol"><select class="easyui-combobox"
							style="width: 50%" name="exist_network"
							data-options="editable:false">
								<option value="1"
									<c:if test="${inspectionReport.info.exist_network==1 }">selected='selected'</c:if>>是</option>
								<option value="0"
									<c:if test="${inspectionReport.info.exist_network==0 }">selected='selected'</c:if>>否</option>
						</select></td>
					</tr>
					<tr>
						<td class="nameCol">检查人员：</td>
						<td class="codeCol"><input type="text" name="check_name"
							value="<c:out value='${inspectionReport.info.check_name }'/>" /></td>
						<td class="nameCol">检查日期：</td>
						<td class="codeCol"><input type="text" class="easyui-datebox"
							name="check_time" data-options="editable:false"
							value="<c:out value='${inspectionReport.info.check_time }'/>" /></td>
						<td class="nameCol">检查用时：</td>
						<td class="codeCol"><input type="text" name="check_period"
							value="<c:out value='${inspectionReport.info.check_period }'/>" /></td>
					</tr>
					<tr>
						<td class="nameCol">陪同人员：</td>
						<td class="codeCol" colspan="5"><input type="text"
							name="entourage" style="width: 70%;"
							value="<c:out value='${inspectionReport.info.entourage }'/>" /></td>
					</tr>
					<tr>
						<td class="nameCol">基础信息介绍：</td>
						<td class="codeCol" colspan="5">
						 <c:choose>
							<c:when test="${not empty inspectionReport.info.description}">
									<textarea  name="description" ><c:out value='${inspectionReport.info.description}' /></textarea>
							</c:when>
							<c:otherwise>
								<textarea  name="description"
								placeholder="例：***********公司为*****品牌经销店。该店成立于*****年，现为***地区唯一一家***品牌的一级代理商。*******公司为***集团企业，集团除西藏正和五菱店外，还经营者******业务，同时在内陆地区经营着****等产业，目前该店与*****支行融资合作，授信额度为：*********。该店月均销量为***台，设有***二库、***二网二级网点" ></textarea>
							</c:otherwise>
					    </c:choose>
					  </td>
					</tr>
					<tr>
						<td class="nameCol">二网、二库地址及介绍：</td>
						<td class="codeCol" colspan="5">
						<textarea name="other_description"><c:out value='${inspectionReport.info.other_description }'/></textarea>
					 </td>
					</tr>
				</table>
				<input type="hidden" name="id"
					value="<c:out value='${inspectionId }'/>" />
			</form>
			<br /> <br /> <a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'"
				onClick="saveInfo('inspectionInfo',1)" style="margin-left: 45%">保存</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" 
				data-options="iconCls:'icon-save'"
				onClick="next(1)">跳过</a>

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
							value="<c:out value='${inspectionReport.item.car_num}'/>"
							style="width: 68%; height: 23px; line-height: 0px;" />台
						</td>
						<td class="nameCol">展厅及周边（台）</td>
						<td class="codeCol"><input type="text" name="hall_num"
							class="easyui-numberbox" style="width: 35%;"   value="<c:out value='${inspectionReport.item.hall_num}'/>" /></td>
						<td class="codeCol"><input type="text" name="hall_remark"
							placeholder="检查内容的备注可填字数不足85"
							value="<c:out value='${inspectionReport.item.hall_remark}'/>" style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">二库（台）</td>
						<td class="codeCol"><input type="text" name="store_num"
							class="easyui-numberbox" style="width: 35%;"   value="<c:out value='${inspectionReport.item.store_num}'/>"  /></td>
						<td class="codeCol"><input type="text" name="store_remark"
							value="<c:out value='${inspectionReport.item.store_remark}'/>" style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">二网（台）</td>
						<td class="codeCol"><input type="text" name="network_num"
							class="easyui-numberbox" style="width: 35%;"   value="<c:out value='${inspectionReport.item.network_num}'/>" /></td>
						<td class="codeCol"><input type="text" name="network_remark"
							value="<c:out value='${inspectionReport.item.network_remark}'/>"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">销售未还款</td>
						<td class="codeCol"><input type="text" name="arrears"
							class="easyui-numberbox" style="width: 35%;"   value="<c:out value='${inspectionReport.item.arrears}'/>" /></td>
						<td class="codeCol"><input type="text" name="arrears_remark"
							value="<c:out value='${inspectionReport.item.arrears_remark}'/>"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">当日销售（台）</td>
						<td class="codeCol"><input type="text" name="sale_num"
							class="easyui-numberbox" style="width: 35%;"   value="<c:out value='${inspectionReport.item.sale_num}'/>" /></td>
						<td class="codeCol"><input type="text" name="sale_remark"
							value="<c:out value='${inspectionReport.item.sale_remark}'/>" style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">私自售卖车辆（台）</td>
						<td class="codeCol"><input type="text" name="priv_sale"
							class="easyui-numberbox" style="width: 35%;"  value="<c:out value='${inspectionReport.item.priv_sale}'/>" /></td>
						<td class="codeCol"><input type="text" name="privSale_remark"
							value="<c:out value='${inspectionReport.item.privSale_remark}'/>"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">私自移动车辆（台）</td>
						<td class="codeCol"><input type="text" name="move_car"
							class="easyui-numberbox" style="width: 35%;"  value="<c:out value='${inspectionReport.item.move_car}'/>" /></td>
						<td class="codeCol"><input type="text" name="moveCar_remark"
							value="<c:out value='${inspectionReport.item.moveCar_remark}'/>"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol" rowspan="2">合格证/关单 <input type="text"
							name="certificate_num" class="easyui-numberbox"
							value="<c:out value='${inspectionReport.item.certificate_num}'/>"
							style="width: 25%; height: 23px; line-height: 0px;" />份
						</td>
						<td class="nameCol">正常合格证数量</td>
						<td class="codeCol"><input type="text"
							name="normal_certificate" class="easyui-numberbox"
							style="width: 35%;"
							value="<c:out value='${inspectionReport.item.normal_certificate}'/>" /></td>
						<td class="codeCol">
						 <input type="text" name="normal_remark"  value="<c:out value='${inspectionReport.item.normal_remark}'/>"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">异常合格证数量</td>
						<td class="codeCol"><input type="text"
							name="abnormal_certificate" class="easyui-numberbox"
							style="width: 35%;"
							value="<c:out value='${inspectionReport.item.abnormal_certificate}'/>" /></td>
						<td class="codeCol"><input type="text" name="abnormal_remark"
							style="width: 100%;"
							value="<c:out value='${inspectionReport.item.abnormal_remark}'/>" /></td>
					</tr>
					<tr>
						<td class="nameCol" rowspan="2">钥匙 <input type="text"
							value="<c:out value='${inspectionReport.item.key_num}'/>" name="key_num"
							class="easyui-numberbox"
							style="width: 25%; height: 23px; line-height: 0px;" />把
						</td>
						<td class="nameCol">正常钥匙数量</td>
						<td class="codeCol"><input type="text" name="normal_key"
							class="easyui-numberbox" style="width: 35%;"
							value="<c:out value='${inspectionReport.item.normal_key}'/>" /></td>
						<td class="codeCol"><input type="text" name="key_remark"
							value="<c:out value='${inspectionReport.item.key_remark}'/>" style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">异常钥匙数量</td>
						<td class="codeCol"><input type="text" name="abnormal_key"
							value="<c:out value='${inspectionReport.item.abnormal_key}'/>"
							class="easyui-numberbox" style="width: 35%;" /></td>
						<td class="codeCol"><input type="text"
							name="abnormalKey_remark" style="width: 100%;"
							value="<c:out value='${inspectionReport.item.abnormalKey_remark}'/>" /></td>
					</tr>

					<tr>
						<td class="nameCol" rowspan="6" style="width: 1%;">2</td>
						<td class="nameCol" rowspan="6">纸质文件情况</td>
						<td class="nameCol" rowspan="2">手工台账</td>
						<td class="nameCol">是否与总账一致</td>
						<td class="codeCol"><select style="width: 35%"
							class="easyui-combobox" name="manual_accounting"
							data-options="editable:false">
								<option value="1"
									<c:if test="${inspectionReport.item.manual_accounting==1 }">selected='selected'</c:if>>是</option>
								<option value="0"
									<c:if test="${inspectionReport.item.manual_accounting==0 }">selected='selected'</c:if>>否</option>
						</select></td>
						<td class="codeCol"><input type="text" name="manual_remark"
							value="<c:out value='${inspectionReport.item.manual_remark}'/>"
							style="width: 100%;" /></td>
					</tr>

					<tr>
						<td class="nameCol">授权人签字是否规范</td>
						<td class="codeCol"><select style="width: 35%"
							class="easyui-combobox" name="manual_standard"
							data-options="editable:false">
								<option value="1"
									<c:if test="${inspectionReport.item.manual_standard==1 }">selected='selected'</c:if>>是</option>
								<option value="0"
									<c:if test="${inspectionReport.item.manual_standard==0 }">selected='selected'</c:if>>否</option>
						</select></td>
						<td class="codeCol"><input type="text" name="standard_remark"
							value="<c:out value='${inspectionReport.item.standard_remark}'/>"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol" rowspan="2">日查库检查表</td>
						<td class="nameCol">是否与总账一致</td>
						<td class="codeCol"><select style="width: 35%"
							class="easyui-combobox" name="check_list"
							data-options="editable:false">
								<option value="1"
									<c:if test="${inspectionReport.item.check_list==1 }">selected='selected'</c:if>>是</option>
								<option value="0"
									<c:if test="${inspectionReport.item.check_list==0 }">selected='selected'</c:if>>否</option>
						</select></td>
						<td class="codeCol"><input type="text" name="check_remark"
							value="<c:out value='${inspectionReport.item.check_remark}'/>"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">日期是否连续</td>
						<td class="codeCol"><select style="width: 35%"
							class="easyui-combobox" name="check_continuity"
							data-options="editable:false">
								<option value="1"
									<c:if test="${inspectionReport.item.check_continuity==1 }">selected='selected'</c:if>>是</option>
								<option value="0"
									<c:if test="${inspectionReport.item.check_continuity==0 }">selected='selected'</c:if>>否</option>
						</select></td>
						<td class="codeCol"><input type="text"
							name="continuity_remark" style="width: 100%;"
							value="<c:out value='${inspectionReport.item.continuity_remark}'/>" /></td>
					</tr>
					<tr>
						<td class="nameCol">授权书、委任书</td>
						<td class="nameCol">是否符合规范</td>
						<td class="codeCol"><select style="width: 35%"
							class="easyui-combobox" name="authorization"
							data-options="editable:false">
								<option value="1"
									<c:if test="${inspectionReport.item.authorization==1 }">selected='selected'</c:if>>是</option>
								<option value="0"
									<c:if test="${inspectionReport.item.authorization==0 }">selected='selected'</c:if>>否</option>
						</select></td>
						<td class="codeCol"><input type="text" name="auth_remark"
							style="width: 100%;" value="<c:out value='${inspectionReport.item.auth_remark}'/>" /></td>
					</tr>
					<tr>
						<td class="nameCol">钥匙交接及钥匙借出登记表</td>
						<td class="nameCol">授权人签字是否规范</td>
						<td class="codeCol"><select style="width: 35%"
							class="easyui-combobox" name="key_handover"
							data-options="editable:false">
								<option value="1"
									<c:if test="${inspectionReport.item.key_handover==1 }">selected='selected'</c:if>>是</option>
								<option value="0"
									<c:if test="${inspectionReport.item.key_handover==0 }">selected='selected'</c:if>>否</option>
						</select></td>
						<td class="codeCol"><input type="text" name="handover_remark"
							style="width: 100%;"
							value="<c:out value='${inspectionReport.item.handover_remark}'/>" /></td>
					</tr>
					<tr>
						<td class="nameCol" rowspan="4" style="width: 1%;">3</td>
						<td class="nameCol" rowspan="4">汇票及押车情况</td>
						<td class="nameCol" rowspan="4">汇票异常情况</td>
						<td class="nameCol">开票1个月未押满</td>
						<td class="codeCol"><input type="text" name="billing"
							style="width: 85%;" value="<c:out value='${inspectionReport.item.billing}'/>" /></td>
						<td class="codeCol"><input type="text" name="billing_remark"
							style="width: 100%;"
							value="<c:out value='${inspectionReport.item.billing_remark}'/>" /></td>
					</tr>
					<tr>
						<td class="nameCol">开票15日未押车</td>
						<td class="codeCol"><input type="text" name="guard"
							style="width: 85%;" value="<c:out value='${inspectionReport.item.guard}'/>" /></td>
						<td class="codeCol"><input type="text" name="guard_remark"
							style="width: 100%;"
							value="<c:out value='${inspectionReport.item.guard_remark}'/>" /></td>
					</tr>
					<tr>
						<td class="nameCol">超期未清票</td>
						<td class="codeCol"><input type="text" name="clear_ticket"
							value="<c:out value='${inspectionReport.item.clear_ticket}'/>" style="width: 85%;" /></td>
						<td class="codeCol"><input type="text"
							value="<c:out value='${inspectionReport.item.clearTicket_remark}'/>"
							name="clearTicket_remark" style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">其他情况</td>
						<td class="codeCol"><input type="text" name="other"
							value="<c:out value='${inspectionReport.item.other}'/>" style="width: 85%;" /></td>
						<td class="codeCol"><input type="text" name="other_remark"
							value="<c:out value='${inspectionReport.item.other_remark}'/>"
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
								<option value="1"
									<c:if test="${inspectionReport.item.system_state==1 }">selected='selected'</c:if>>是</option>
								<option value="0"
									<c:if test="${inspectionReport.item.system_state==0 }">selected='selected'</c:if>>否</option>
						</select></td>
						<td class="codeCol"><input type="text" name="state_remark"
							value="<c:out value='${inspectionReport.item.state_remark}'/>"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">标识</td>
						<td class="nameCol">质押车是否摆放标识</td>
						<td class="codeCol"><select style="width: 35%"
							class="easyui-combobox" name="identification"
							data-options="editable:false">
								<option value="1"
									<c:if test="${inspectionReport.item.identification==1 }">selected='selected'</c:if>>是</option>
								<option value="0"
									<c:if test="${inspectionReport.item.identification==0 }">selected='selected'</c:if>>否</option>
						</select></td>
						<td class="codeCol"><input type="text"
							name="identification_remark" style="width: 100%;"
							value="<c:out value='${inspectionReport.item.identification_remark}'/>" /></td>
					</tr>
					<tr>
						<td class="nameCol">办公设备</td>
						<td class="nameCol">是否符合检查要求</td>
						<td class="codeCol"><select style="width: 35%"
							class="easyui-combobox" name="office_machine"
							data-options="editable:false">
								<option value="1"
									<c:if test="${inspectionReport.item.office_machine==1 }">selected='selected'</c:if>>是</option>
								<option value="0"
									<c:if test="${inspectionReport.item.office_machine==0 }">selected='selected'</c:if>>否</option>
						</select></td>
						<td class="codeCol"><input type="text" name="machine_remark"
							style="width: 100%;"
							value="<c:out value='${inspectionReport.item.machine_remark}'/>" /></td>
					</tr>
					<tr>
						<td class="nameCol">办公条件</td>
						<td class="nameCol">是否为独立办公室</td>
						<td class="codeCol"><select style="width: 35%"
							class="easyui-combobox" name="office_conditions"
							data-options="editable:false">
								<option value="1"
									<c:if test="${inspectionReport.item.office_conditions==1 }">selected='selected'</c:if>>是</option>
								<option value="0"
									<c:if test="${inspectionReport.item.office_conditions==0 }">selected='selected'</c:if>>否</option>
						</select></td>
						<td class="codeCol"><input type="text"
							value="<c:out value='${inspectionReport.item.conditions_remark}'/>"
							name="conditions_remark" style="width: 100%;" /></td>
					</tr>
				</table>
				<input type="hidden" name="id"
					value="<c:out value='${inspectionId }'/>" />
			</form>
			   <a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'"
				onClick="saveInfo('inspectionItem',2)" style="margin-left: 45%">保存</a>
                 &nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" 
				data-options="iconCls:'icon-save'"
				onClick="next(2)" >跳过</a>
		</div>
		<div title="检查过程中发现的问题及监管员优/缺点" style="padding: 20px; display: none;">
			<form id="inspectionRecord"
				action="/windcontrol/inspection.do?method=addAndUpdateRecord">
				<table class="formTable" id="problem2">
					<tr>
						<td class="nameCol" style="width: 1%;">序号：</td>
						<td class="codeCol" colspan="5">发现问题</td>
					</tr>
					<c:if test="${not empty inspectionReport.records}">
						<c:if test="${not empty inspectionReport.records['problems']}">
							<c:forEach items="${inspectionReport.records['problems']}" var="info"
								varStatus="statu">
								<tbody>
									<tr>
										<td class="nameCol" style="width: 1%;"><c:out
												value='${statu.index+1}' />:</td>
										<td class="codeCol" colspan="5"><input type="text"
											name="problemContent" style="width: 90%;"
											value="<c:out value='${info.content}'/>" /></td>
									</tr>

									<tr>
										<td class="nameCol" style="width: 1%;">处理结果：</td>
										<td class="codeCol" colspan="5"><input type="text"
											value="<c:out value='${info.result}'/>" name="problemResult"
											style="width: 90%;">&nbsp;&nbsp;&nbsp;<a class="link"
											href="javascript:void(0);" onclick="deleteTbody(this)">删除</a></td>
									</tr>
								</tbody>
							</c:forEach>
						</c:if>
					</c:if>
				</table>

				<table class="formTable" id="advantage">
					<tr>
						<td class="nameCol" style="width: 1%;">序号：</td>
						<td class="codeCol" colspan="5">监管员优点/缺点</td>
					</tr>
					<c:if test="${not empty inspectionReport.records}">
						<c:if test="${not empty inspectionReport.records['supervisors']}">
							<c:forEach items="${inspectionReport.records['supervisors']}" var="info"
								varStatus="statu">
								<tbody>
									<tr>
										<td class="nameCol" style="width: 1%;"><c:out
												value='${statu.index+1}' />:</td>
										<td class="codeCol" colspan="5"><input type="text"
											value="<c:out value='${info.content}'/>"
											name="supervisorContent" style="width: 90%;" /></td>
									</tr>
									<tr>
										<td class="nameCol" style="width: 1%;">处理结果：</td>
										<td class="codeCol" colspan="5"><input type="text"
											value="<c:out value='${info.result}'/>"
											name="supervisorResult" style="width: 90%;">&nbsp;&nbsp;&nbsp;<a
											class="link" href="javascript:void(0);"
											onclick="deleteTbody(this)">删除</a></td>
									</tr>
								</tbody>
							</c:forEach>
						</c:if>
					</c:if>
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
				onClick="saveRecord('inspectionRecord',3)"
				style="margin-left: 45%">保存</a>
                  &nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" 
				data-options="iconCls:'icon-save'"
				onClick="next(3)" >跳过</a>
		</div>
		<div title="与店方沟通情况" style="padding: 20px; display: none;">
			<form id="inspectionCommunication"
				action="/windcontrol/inspection.do?method=addAndUpdateCommunication">
				<table class="formTable">
					<tr>
						<td class="nameCol" style="width: 5%;">店方人员姓名：</td>
						<td class="codeCol" colspan="2"><input type="text"
							value="<c:out value='${inspectionReport.communion.name}'/>" name="name" /></td>
						<td class="nameCol">职位：</td>
						<td class="codeCol" colspan="2"><input type="text"
							name="position" value="<c:out value='${inspectionReport.communion.position}'/>" /></td>
					</tr>

					<tr>
						<td class="nameCol" style="width: 5%;">月销量（台）：</td>
						<td class="codeCol" colspan="2"><input type="text"
							class="easyui-numberbox" name="month_sales"
							value="<c:out value='${inspectionReport.communion.month_sales}'/>" /></td>
						<td class="nameCol">自有车辆（台）：</td>
						<td class="codeCol" colspan="2"><input type="text"
							name="car_count" value="<c:out value='${inspectionReport.communion.car_count}'/>" /></td>
					</tr>
					<tr>
						<td class="nameCol" style="width: 5%;">座谈内容：</td>
						<td class="codeCol" colspan="5"><textarea name="content"><c:out
									value='${inspectionReport.communion.content}' /></textarea></td>
					</tr>
					<tr>
						<td class="nameCol" style="width: 5%;">店方情况：</td>
						<td class="codeCol" colspan="5"><textarea name="info"><c:out
									value='${inspectionReport.communion.info}' /></textarea></td>
					</tr>
					<tr>
						<td class="nameCol" style="width: 5%;">我司情况：</td>
						<td class="codeCol" colspan="5"><textarea name="company_info"><c:out
									value='${inspectionReport.communion.company_info}' /></textarea></td>
					</tr>
				</table>
				<input type="hidden" name="id"
					value="<c:out value='${inspectionId }'/>" />
			</form>
			<br /> <a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'"
				onClick="saveInfo('inspectionCommunication',4)"
				style="margin-left: 45%">保存</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" 
				data-options="iconCls:'icon-save'"
				onClick="next(4)">跳过</a>
		</div>
		<div title="监管员基本情况介绍" style="padding: 20px; display: none;">
			<form id="inspectionSupervisor"
				action="/windcontrol/inspection.do?method=addAndUpdateSupervisor">
				<table class="formTable">
					<tr>
						<td class="nameCol">监管员姓名：</td>
						<td class="codeCol"><input type="text" name="name"
							value="<c:out value='${inspectionReport.supervisor.name}'/>" /></td>
						<td class="nameCol">联系方式：</td>
						<td class="codeCol"><input type="text"
							class="easyui-numberbox" name="contact_phone"
							value="<c:out value='${inspectionReport.supervisor.contact_phone}'/>" /></td>
						<td class="nameCol">紧急联系方式：</td>
						<td class="codeCol"><input type="text"
							class="easyui-numberbox" name="emergency_telephone"
							value="<c:out value='${inspectionReport.supervisor.emergency_telephone}'/>" /></td>
					</tr>
					<tr>
						<td class="nameCol">性别：</td>
						<td class="codeCol"><select style="width: 60%"
							class="easyui-combobox" name="sex" data-options="editable:false">
								<option value="0"
									<c:if test="${inspectionReport.supervisor.sex==0 }">selected='selected'</c:if>>未知</option>
								<option value="1"
									<c:if test="${inspectionReport.supervisor.sex==1 }">selected='selected'</c:if>>男</option>
								<option value="2"
									<c:if test="${inspectionReport.supervisor.sex==2 }">selected='selected'</c:if>>女</option>
						</select></td>
						<td class="nameCol">学历：</td>
						<td class="codeCol" colspan="3"><input type="text"
							value="<c:out value='${inspectionReport.supervisor.education}'/>" name="education" /></td>
					</tr>
					<tr>
						<td class="nameCol">年龄：</td>
						<td class="codeCol"><input type="text"
							value="<c:out value='${inspectionReport.supervisor.age}'/>" class="easyui-numberbox"
							name="age" /></td>
						<td class="nameCol">公司认可属性：</td>
						<td class="codeCol" colspan="3"><input type="text"
							value="<c:out value='${inspectionReport.supervisor.company_attr}'/>" name="company_attr"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">户口所在地：</td>
						<td class="codeCol"><input type="text"
							value="<c:out value='${inspectionReport.supervisor.registered_address}'/>"
							name="registered_address" /></td>
						<td class="nameCol">本人实际属性：</td>
						<td class="codeCol" colspan="3"><input type="text"
							value="<c:out value='${inspectionReport.supervisor.people_attr}'/>" name="people_attr"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">住宿：</td>
						<td class="codeCol" colspan="2"><input type="text"
							name="address" style="width: 100%;"
							value="<c:out value='${inspectionReport.supervisor.address}'/>" /></td>
						<td class="nameCol">用餐：</td>
						<td class="codeCol" colspan="2"><input type="text"
							name="meal" style="width: 100%;"
							value="<c:out value='${inspectionReport.supervisor.meal}'/>" /></td>
					</tr>
					<tr>
						<td class="nameCol">电脑技能：</td>
						<td class="codeCol" colspan="2"><input type="text"
							value="<c:out value='${inspectionReport.supervisor.computer_skills}'/>"
							name="computer_skills" style="width: 100%;" /></td>
						<td class="nameCol">招聘来源：</td>
						<td class="codeCol" colspan="2"><input type="text"
							value="<c:out value='${inspectionReport.supervisor.job_channel}'/>" name="job_channel"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol">培训方式：</td>
						<td class="codeCol" colspan="2"><input type="text"
							value="<c:out value='${inspectionReport.supervisor.train_mode}'/>" name="train_mode"
							style="width: 100%;" /></td>
						<td class="nameCol">培训人员：</td>
						<td class="codeCol" colspan="2"><input type="text"
							value="<c:out value='${inspectionReport.supervisor.train_man}'/>" name="train_man"
							style="width: 100%;" /></td>
					</tr>
					<tr>
						<td class="nameCol" rowspan="2">周末休假：</td>
						<td class="codeCol" rowspan="2"><select style="width: 60%"
							class="easyui-combobox" name="weekend">
								<option value="1"
									<c:if test="${inspectionReport.supervisor.weekend==1 }">selected='selected'</c:if>>是</option>
								<option value="0"
									<c:if test="${inspectionReport.supervisor.weekend==0 }">selected='selected'</c:if>>否</option>
						</select></td>
						<td class="nameCol" rowspan="2">上岗时间：</td>
						<td class="nameCol">初次上岗时间：</td>
						<td class="codeCol" colspan="2"><input type="text"
							class="easyui-datebox" name="posts_time"
							value="<c:out value='${inspectionReport.supervisor.posts_time}'/>"
							data-options="editable:false" /></td>
					</tr>
					<tr>
						<td class="nameCol">接管此店时间：</td>
						<td class="codeCol" colspan="2"><input type="text"
							class="easyui-datebox" name="takeOver_time"
							value="<c:out value='${inspectionReport.supervisor.takeOver_time}'/>"
							data-options="editable:false" /></td>
					</tr>
					<tr>
						<td class="nameCol">工作经历(监管员对工作、总部建议)：</td>
						<td class="codeCol" colspan="5"><textarea
								name="work_experience"><c:out
									value='${inspectionReport.supervisor.work_experience}' /></textarea></td>
					</tr>
					<tr>
						<td class="nameCol">巡检人员对监管员评价：</td>
						<td class="codeCol" colspan="5"><textarea name="evaluate"><c:out
									value='${inspectionReport.supervisor.evaluate}' /></textarea></td>
					</tr>
				</table>
				<input type="hidden" name="id"
					value="<c:out value='${inspectionId }'/>" />
			</form>
			<br /> <a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'"
				onClick="saveInfo('inspectionSupervisor',5)"
				style="margin-left: 45%">保存</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" 
				data-options="iconCls:'icon-save'"
				onClick="next(5)" >跳过</a>
		</div>
		<div title="巡店总结" style="padding: 20px; display: none;">
			<form id="inspectionResult"
				action="/windcontrol/inspection.do?method=addAndUpdateResult">
				<table class="formTable">
					<tr>
						<td class="nameCol" colspan="2" style="width: 5%;">风险等级：</td>
						<td class="codeCol"><input type="radio" value="4"
							name="danger_level"
							<c:if test="${inspectionReport.inspecResult.danger_level==4 }">checked='checked'</c:if> />无</td>
						<td class="codeCol"><input type="radio" value="1"
							name="danger_level"
							<c:if test="${inspectionReport.inspecResult.danger_level==1 }">checked='checked'</c:if> />A级</td>
						<td class="codeCol"><input type="radio" value="2"
							name="danger_level"
							<c:if test="${inspectionReport.inspecResult.danger_level==2 }">checked='checked'</c:if> />B级</td>
						<td class="codeCol"><input type="radio" value="3"
							name="danger_level"
							<c:if test="${inspectionReport.inspecResult.danger_level==3 }">checked='checked'</c:if> />C级</td>
					</tr>
					<tr>
						<td class="nameCol" colspan="2" style="width: 5%;">经销店等级：</td>
						<td class="codeCol"><input type="radio" value="4"
							name="dealer_level"
							<c:if test="${inspectionReport.inspecResult.dealer_level==4 }">checked='checked'</c:if> />无(新进驻一个月内)</td>
						<td class="codeCol"><input type="radio" value="1"
							name="dealer_level"
							<c:if test="${inspectionReport.inspecResult.dealer_level==1 }">checked='checked'</c:if> />A级</td>
						<td class="codeCol"><input type="radio" value="2"
							name="dealer_level"
							<c:if test="${inspectionReport.inspecResult.dealer_level==2 }">checked='checked'</c:if> />B级</td>
						<td class="codeCol"><input type="radio" value="3"
							name="dealer_level"
							<c:if test="${inspectionReport.inspecResult.dealer_level==3 }">checked='checked'</c:if> />C级</td>
					</tr>
					<tr>
						<td class="nameCol" colspan="2" style="width: 5%;">监管员等级：</td>
						<td class="codeCol"><input type="radio" value="4"
							name="supervisor_level"
							<c:if test="${inspectionReport.inspecResult.supervisor_level==4 }">checked='checked'</c:if> />无(新进驻一个月内)</td>
						<td class="codeCol"><input type="radio" value="1"
							name="supervisor_level"
							<c:if test="${inspectionReport.inspecResult.supervisor_level==1 }">checked='checked'</c:if> />A级</td>
						<td class="codeCol"><input type="radio" value="2"
							name="supervisor_level"
							<c:if test="${inspectionReport.inspecResult.supervisor_level==2 }">checked='checked'</c:if> />B级</td>
						<td class="codeCol"><input type="radio" value="3"
							name="supervisor_level"
							<c:if test="${inspectionReport.inspecResult.supervisor_level==3 }">checked='checked'</c:if> />C级</td>
					</tr>
					<tr>
					<td class="nameCol" colspan="2" style="width: 5%;">巡店总结：</td>
						<td class="codeCol" colspan="4">
						<textarea name="remak"><c:out value='${inspectionReport.inspecResult.remak}' /></textarea></td>
					</tr>
				</table>
				<input type="hidden" name="id"
					value="<c:out value='${inspectionId }'/>" />
			</form>
			<br /> <a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'"
				onClick="saveInfo('inspectionResult',6)" style="margin-left: 45%">保存</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" 
				data-options="iconCls:'icon-save'"
				onClick="next(6)" >跳过</a>
		</div>
		<div title="店面拍照" style="padding: 20px; display: none;">
			<div style="margin-left: 18px;">
				<form id="fileupload"
					action="/windcontrol/inspection.do?method=showPicture" method="GET"
					enctype="multipart/form-data" class="">
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

				<a href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-save'"
					onClick="goSubmit('<c:out value='${inspectionId }'/>','<c:out value='${dealerName }'/>')"
					style="margin-left: 40%">提交</a> 
				    &nbsp;&nbsp;&nbsp;&nbsp; <a href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-save'" onClick="goList()">返回</a>
			</div>
			<%@include file="../../base/fileuploadShow.jsp"%>
		</div>
	</div>
</body>
</html>
