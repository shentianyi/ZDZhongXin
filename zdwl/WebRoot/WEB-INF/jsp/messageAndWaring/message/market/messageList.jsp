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

<html class="h-100">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
</head>
<body>
	<table class="t-table" border="0" cellspacing="0" cellpadding="0">
		<thead class="t-thead">
			<tr class="t-tr">
				<th class="t-th  dealerTd"  style="display:none ;">经销商</th>
				<th class="t-th  oneFinanceTd"  style="display:none ;">总行/金融机构</th>
				<th class="t-th  oneFinanceTd"  style="display:none ;">分行/分支机构</th>
				<th class="t-th  oneFinanceTd"  style="display:none ;">支行</th>
				<th class="t-th  areaTd"   style="display:none ;">省</th>
				<th class="t-th  areaTd"   style="display:none ;">区</th>
				<th class="t-th  areaTd"   style="display:none ;">县</th>
				<th class="t-th  areaTd"   style="display:none ;">详细地址</th>
				<th class="t-th  brandTd"  style="display:none ;">品牌</th>
				<th class="t-th  clientManagerTd"  style="display:none ;">客户经理</th>
				<th class="t-th  inDateTd"    style="display:none ;">进驻时间</th>
				<!-- <th class="t-th  feePayTimeTd"  style="display:none ;">监管费缴纳时间</th>
				<th class="t-th  payAmountTd"  style="display:none ;">监管费缴纳金额</th>
				<th class="t-th  balanceTd"     style="display:none ;">余额</th>  -->
				<th class="t-th  mailingTimeTd"  style="display:none ;">协议邮寄时间</th>
				<th class="t-th  recoveryTimeTd"  style="display:none ;">协议回收时间</th>
				<th class="t-th  protocolExpireTd"    style="display:none ;">协议到期时间</th>
			    <th class="t-th  proInTransferTimeTd"  style="display:none ;">项目进驻流转单时间</th> 
			    <th class="t-th  busInTransferTimeTd" style="display:none ;">业务进驻流转单进驻时间</th> 
			  	<th class="t-th  predictOutDateTd"  style="display:none ;">项目预计撤出时间</th> 
				<th class="t-th  bindingTimeTd"     style="display:none ;">绑定时间</th>
				<th class="t-th  officeLocationTd"  style="display:none ;">在哪家店办公</th>
				<th class="t-th  unBindingTimeTd"   style="display:none ;">解除绑定时间</th>
				
				<th class="t-th">
				<label for="checkAllValue">
				</label> 
				 <input id="fullRead" type="checkbox" name="fullRead" onclick="checkAll(this);" />已读
				</th>
				
			</tr>
		</thead>
		<tbody class="t-tbody">
			<logic:iterate name="list" id="row" indexId="index">
				<tr class="t-tr"
					<c:if test="${row.read==1}">style="color:red;"</c:if>>
					<td class="t-td  dealerTd"  style="display:none ;" ><c:out value="${row.dealerName}" /></td>
					<td class="t-td  oneFinanceTd"  style="display:none ;"><c:out value="${row.oneBankName}" /></td>
					<td class="t-td  oneFinanceTd"  style="display:none ;"><c:out value="${row.twoBankName}" /></td>
					<td class="t-td  oneFinanceTd"  style="display:none ;"><c:out value="${row.threeBankName}" /></td>
					<td class="t-td  areaTd"  style="display:none ;"><c:out value="${row.provinceName}" /></td>
					<td class="t-td  areaTd"  style="display:none ;"><c:out value="${row.areaName}" /></td>
					<td class="t-td  areaTd"  style="display:none ;"><c:out value="${row.countyName}" /></td>
					<td class="t-td  areaTd"  style="display:none ;"><c:out value="${row.detailAddr}" /></td>
					<td class="t-td  brandTd"  style="display:none ;"><c:out value="${row.brandName}" /></td>
					<td class="t-td  clientManagerTd"  style="display:none ;"><c:out value="${row.manager}" /></td>
					
					<td class="t-td  inDateTd"  style="display:none ;">
					  <fmt:formatDate value="${row.inDate }"
							pattern="yyyy-MM-dd" />
					 </td>
					<%--  <td class="t-td  feePayTimeTd"  style="display:none ;">
					   <fmt:formatDate value="${row.feePayDate}"
							pattern="yyyy-MM-dd" />
					</td>
					<td class="t-td  payAmountTd"  style="display:none ;"><c:out value="${row.payAmount}" /></td>
					<td class="t-td  balanceTd" style="display:none ;"><c:out value="${row.balance}" /></td> --%>
					 
					<td class="t-td  mailingTimeTd"  style="display:none ;">
					  <fmt:formatDate value="${row.mailingDate }"
							pattern="yyyy-MM-dd" />
					 </td>
					 
					<td class="t-td  recoveryTimeTd"  style="display:none ;">
					  <fmt:formatDate value="${row.recoveryDate }"
							pattern="yyyy-MM-dd" />
					 </td>
					
					<td class="t-td  protocolExpireTd"  style="display:none ;">
					  <fmt:formatDate value="${row.protocolExpire }"
							pattern="yyyy-MM-dd" />
					 </td>
					 
				    <td class="t-td  proInTransferTimeTd"  style="display:none ;">
					  <fmt:formatDate value="${row.proInTransferDate }"
							pattern="yyyy-MM-dd" />
				 </td>
				     <td class="t-td  busInTransferTimeTd"  style="display:none ;">
					  <fmt:formatDate value="${row.busInTransferDate }"
							pattern="yyyy-MM-dd" />
					 </td> 
					
				
					<td class="t-td  predictOutDateTd"  style="display:none ;">
					  <fmt:formatDate value="${row.predictOutDate}"
							pattern="yyyy-MM-dd" />
					 </td>  
					 
					<td class="t-td  bindingTimeTd"  style="display:none ;">
					  <fmt:formatDate value="${row.bindingDate}"
							pattern="yyyy-MM-dd" />
					 </td>
					 
					 <td class="t-td  officeLocationTd" style="display:none ;"><c:out value="${row.officeLocation}" /></td>
					
					<td class="t-td  unBindingTimeTd"  style="display:none ;" >
					  <fmt:formatDate value="${row.unBindingDate }"
							pattern="yyyy-MM-dd" />
					 </td>

					<td class="t-td " style="color: black !important"><c:if
							test="${row.read==1}">
							<input type="checkbox" name="isReadIds"
								value="<c:out value='${row.id}'/>" />
						</c:if> 已读</td>
						
				 
				</tr>
			</logic:iterate>
		 </tbody>
	  </table>
	</body>
</html>
		