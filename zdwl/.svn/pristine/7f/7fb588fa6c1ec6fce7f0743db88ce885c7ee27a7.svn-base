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
				<th class="t-th  brandTd"  style="display:none ;">品牌</th>
				<th class="t-th  draftNumTd"  style="display:none ;">票号</th>
				<th class="t-th  billingDteTd"  style="display:none ;">开票日</th>
				<th class="t-th  dueDateTd"  style="display:none ;">到期日</th>
				<th class="t-th  billingMoneyTd"  style="display:none ;">开票金额</th>
				<th class="t-th  nomortgagecarTd"  style="display:none ;">未押车金额</th>
				<th class="t-th  vinTd"  style="display:none ;">车架号</th>
				<th class="t-th  movetimeTd"  style="display:none ;">移动时间</th>
				<th class="t-th  carMoneyTd"   style="display:none ;">车辆价值</th>
				<th class="t-th  businDateTd"  style="display:none ;">最后一笔业务办理时间</th>
				<th class="t-th  updDateTd"  style="display:none ;">最近一笔释放业务时间</th>
				<th class="t-th  carNumTd"  style="display:none ;">台数</th>
				<th class="t-th  moneyTd"   style="display:none ;">金额</th>
				<th class="t-th  moveNUmTd"  style="display:none ;">移动数量</th>
				<th class="t-th  allCarTd"  style="display:none ;">总库存数量</th>
				<th class="t-th  moveCarMoneyTd"  style="display:none ;">移动车辆金额</th>
				<th class="t-th  inCarMoneyTd"   style="display:none ;">在库车辆金额</th>
				<th class="t-th  priceTd"  style="display:none ;">价格</th>
				<th class="t-th  moveAddressTd"  style="display:none ;">移动位置</th>
				<!-- <th class="t-th  stateTd"  style="display:none ;">状态</th> -->
				
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
					<td class="t-td  brandTd"  style="display:none ;"><c:out value="${row.brandName}" /></td>
					<td class="t-td  draftNumTd"  style="display:none ;"><c:out value="${row.draft_num}" /></td>
					<td class="t-td  billingDteTd"  style="display:none ;">
					  <fmt:formatDate value="${row.billing_date }"
							pattern="yyyy-MM-dd" />
					 </td>
					 
					 <td class="t-td  dueDateTd"  style="display:none;">
					  <fmt:formatDate value="${row.due_date }"
							pattern="yyyy-MM-dd" />
					 </td>
					 <td class="t-td  billingMoneyTd"  style="display:none ;"><c:out value="${row.billing_money}" /></td>
					 <td class="t-td  nomortgagecarTd"  style="display:none ;"><c:out value="${row.nomortgagecar_money}" /></td>
                     <td class="t-td  vinTd"  style="display:none ;" ><c:out value="${row.vin}" /></td>
                     <td class="t-td  movetimeTd"  style="display:none ;">
					  <fmt:formatDate value="${row.movetime }"
							pattern="yyyy-MM-dd" />
					 </td>
					  <td class="t-td  carMoneyTd"  style="display:none ;" ><c:out value="${row.money}" /></td>
					  <td class="t-td  businDateTd"  style="display:none ;">
					    <fmt:formatDate value="${row.updDate}"
							pattern="yyyy-MM-dd" />
					   </td>
					  <td class="t-td  updDateTd"  style="display:none ;">
					  <fmt:formatDate value="${row.updDate}"
							pattern="yyyy-MM-dd" />
					  </td>
					  <td class="t-td  carNumTd"  style="display:none ;" ><c:out value="${row.car_num}" /></td>
					  <td class="t-td  moneyTd"  style="display:none ;" ><c:out value="${row.money}" /></td>
					   <td class="t-td  moveNUmTd"  style="display:none ;" ><c:out value="${row.yc}" /></td>
					   <td class="t-td  allCarTd"  style="display:none ;" ><c:out value="${row.allCar}" /></td>
					   <td class="t-td  moveCarMoneyTd"  style="display:none ;" ><c:out value="${row.money}" /></td>
					   <td class="t-td  inCarMoneyTd"  style="display:none ;" ><c:out value="${row.money}" /></td>
					   <td class="t-td  priceTd"  style="display:none ;" ><c:out value="${row.price}" /></td>
					   <td class="t-td  moveAddressTd"  style="display:none ;" ><c:out value="${row.moveAddress}" /></td>
					 
					   <%--   <td class="t-td  stateTd"  style="display:none ;" ><c:out value="${row.state}" /></td> --%>
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
		