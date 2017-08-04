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
               <input name="method" id="method" type="hidden" value="" />
	           <input type="hidden"  name="msgType"
					value="<c:out value="${remindAndWaringForm.msgType}"/>" />
					<!--合作金融机构（总、分、支）  -->
				<input type="hidden" id="onehiId"
					value="<c:out value="${remindAndWaringForm.oneBankId}"/>" />
				<input type="hidden" id="twohiId"
					value="<c:out value="${remindAndWaringForm.twoBankId}"/>" />
				<input type="hidden" id="threehiId"
					value="<c:out value="${remindAndWaringForm.threeBankId}"/>" />
					<!-- 地址（省、区、县） -->
				<input type="hidden" id="provincehiId"
					value="<c:out value="${remindAndWaringForm.oneBankId}"/>" />
				<input type="hidden" id="cityhiId"
					value="<c:out value="${remindAndWaringForm.twoBankId}"/>" />
				<input type="hidden" id="countyhiId"
					value="<c:out value="${remindAndWaringForm.threeBankId}"/>" />
					
				<input type="hidden" id="idsRead" name="idsRead" />
				<input type="hidden" id="idsShield" name="idsShield" />
				
                 <div class="public-main-input ly-col-3 hidden abs"
					style="height: 165px; margin-top: -50px;">
					<div class="ly-input-w" style="height: 110px;">
						<div class="ly-row clearfix" style="display:none" id="bankQuery">
							<div class="ly-col fl" style="width: 33%;">
								<div class="label block fl hidden">总行/金融机构：</div>
								<div class="input block fl hidden">
									<select id="one" name="oneBankId" class="oneBankId">
										<option value="-1">请选择...</option>
									</select>
								</div>
							</div>
							<div class="ly-col fl" style="width: 33%;">
								<div class="label block fl hidden">分行/分支机构：</div>
								<div class="input block fl hidden">
									<select id="two" name="twoBankId" class="twoBankId">
										<option value="-1">请选择...</option>
									</select>
								</div>
							</div>
							<div class="ly-col fl" style="width: 34%;">
								<div class="label block fl hidden">支行：</div>
								<div class="input block fl hidden">
									<div class="ly-sel-w">
										<select id="three" name="threeBankId" class="threeBankId"
											style="margin: auto;">
											<option value="-1">请选择...</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						
						<div class="ly-row clearfix">
							<div class="ly-col fl" style="width: 33%; display:none ;" id="dealerQuery">
								<div class="label block fl hidden">经销商：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none allText"   property="dealerName"
										styleId="dealerName" />
								</div>
							</div>
							
							<div class="ly-col fl" style="width: 33%; display:none ;" id="brandQuery">
								<div class="label block fl hidden">品牌：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none allText"  property="brandName"
										styleId="brandName" />
								</div>
							</div>

							<div class="ly-col fl" style="width: 33%; display:none ;" id="inDateQuery" >
								<div class="label block fl hidden"
									style="width: 20%; margin-left: -3px;">进驻时间：</div>
								<div class="input block fl hidden"
									style="width: 80%; padding-left: 28px;">
									<input style="width: 35%;" class="ly-bor-none allDate" 
										name="inStartDate" type="text"
										value='<fmt:formatDate value="${remindAndWaringForm.inStartDate }" pattern="yyyy-MM-dd"/>' />
									
									<span>至</span> 
									<input style="width: 35%;" class="ly-bor-none allDate"  name="inEndDate" type="text"
										value='<fmt:formatDate value="${remindAndWaringForm.inEndDate }" pattern="yyyy-MM-dd"/>' />
								</div>
							</div>
							
							<div class="ly-col fl" style="width: 33%; display:none ;" id="spaceQuery" >
								<div class="label block fl hidden"
									style="width: 100%; margin-left: -3px;"></div>
								
							</div>
							
						<div class="ly-col fl" style="width: 66%; display:none ;" id="busInTransferQuery">
								<div class="label block fl hidden"
									style="width: 20%; margin-left: -3px;">业务进驻流转单时间：</div>
								<div class="input block fl hidden"
									style="width: 80%; padding-left: 28px;">
									<input style="width: 24%;" class="ly-bor-none allDate" 
										name="inStartDate" type="text"
										value='<fmt:formatDate value="${remindAndWaringForm.startBusInTransferDate}" pattern="yyyy-MM-dd"/>' />
									<span>至</span>
									 <input style="width: 24%;" class="ly-bor-none allDate"
										 name="endBusInTransferDate" type="text"
										value='<fmt:formatDate value="${remindAndWaringForm.endBusInTransferDate }" pattern="yyyy-MM-dd"/>' />
								</div>
						</div>
							
						</div>
						
					    <div class="ly-row clearfix">
					        <div class="ly-col fl" style="width: 50%; display:none ;" id="proInTransferQuery">
								<div class="label block fl hidden"
									style="width: 27%; margin-left: -3px;">项目进驻流转单时间：</div>
								<div class="input block fl hidden"
									style="width: 73%; padding-left: 28px;">
									<input style="width: 35%;" class="ly-bor-none allDate" 
										name="startBusInTransferDate" type="text"
										value='<fmt:formatDate value="${remindAndWaringForm.startProInTransferDate }" pattern="yyyy-MM-dd"/>' />
									<span>至</span> 
									<input style="width: 35%;" class="ly-bor-none allDate"
										 name="inEndDate" type="text"
										value='<fmt:formatDate value="${remindAndWaringForm.endProInTransferDate }" pattern="yyyy-MM-dd"/>' />
								</div>
						    </div>
						    
						   	<div class="ly-col fl" style="width: 50%; display:none ;" id="mailingTimeQuery">
								<div class="label block fl hidden"
									style="width: 20%; margin-left: -3px;">协议邮寄时间：</div>
								<div class="input block fl hidden"
									style="width: 80%; padding-left: 28px;">
									<input style="width: 35%;" class="ly-bor-none allDate" 
										name="startMailingDate" type="text"
										value='<fmt:formatDate value="${remindAndWaringForm.startMailingDate }" pattern="yyyy-MM-dd"/>' />
									<span>至</span> 
									<input style="width: 35%;" class="ly-bor-none allDate"
										name="endMailingDate" type="text"
										value='<fmt:formatDate value="${remindAndWaringForm.endMailingDate }" pattern="yyyy-MM-dd"/>' />
								</div>
							 </div>	
							 
							<div class="ly-col fl" style="width: 50%; display:none ;" id="protocolExpireQuery">	
							  <div class="label block fl hidden"
									style="width: 20%; margin-left: -3px;">协议到期时间：</div>
									
								<div class="input block fl hidden"
									style="width: 80%; padding-left: 28px;">
									<input style="width: 35%;" class="ly-bor-none allDate" 
										name="startProtocolExpire" type="text"
										value='<fmt:formatDate value="${remindAndWaringForm.startProtocolExpire }" pattern="yyyy-MM-dd"/>' />
									<span>至</span> <input style="width: 35%;" class="ly-bor-none allDate"
										 name="endProtocolExpire" type="text"
										value='<fmt:formatDate value="${remindAndWaringForm.endProtocolExpire }" pattern="yyyy-MM-dd"/>' />
								</div>
								
							 </div>	
							 
							<%--  <div class="ly-col fl" style="width: 33%; display:none ;" id="bindingTimeQuery">	
							   <div class="label block fl hidden"
									style="width: 20%; margin-left: -3px;">绑定时间：</div>
									
								 <div class="input block fl hidden"
									style="width: 80%; padding-left: 28px; display:none">
									<input style="width: 24%;" class="ly-bor-none allDate" 
										name="startBindingDate" type="text"
										value='<fmt:formatDate value="${remindAndWaringForm.startBindingDate }" pattern="yyyy-MM-dd"/>' />
									<span>至</span> 
									<input style="width: 24%;" class="ly-bor-none allDate"
										 name="endBindingDate" type="text"
										value='<fmt:formatDate value="${remindAndWaringForm.endBindingDate }" pattern="yyyy-MM-dd"/>' />
								 </div>
								
							 </div>	 --%>
							 
							<%--  <div class="ly-col fl" style="width: 66%; display:none ;" id="billingTimeQuery">	
							   <div class="label block fl hidden"
									style="width: 20%; margin-left: -3px;">开票时间：</div>
									
								 <div class="input block fl hidden"
									style="width: 80%; padding-left: 28px; display:none">
									<input style="width: 24%;" class="ly-bor-none allDate" 
										name="startBillingDate" type="text"
										value='<fmt:formatDate value="${remindAndWaringForm.startBillingDate }" pattern="yyyy-MM-dd"/>' />
									<span>至</span> 
									<input style="width: 24%;" class="ly-bor-none allDate"
										 name="endBillingDate" type="text"
										value='<fmt:formatDate value="${remindAndWaringForm.endBillingDate }" pattern="yyyy-MM-dd"/>' />
								 </div>
								
							 </div>	 --%>
							 	
						  </div>
						  
						  <div class="ly-row clearfix" style="display:none" id="areaQuery">
						   <!-- 地址（省、区、县、详细地址） -->
						    <div class="ly-col fl" style="width: 33%;">
								<div class="label block fl hidden">省份：</div>
								<div class="input block fl hidden">
										<select id="provinceId" name="provinceId"
											class="provinceId" style="margin: 0;"
											onchange="changeProvince(this.value,$('#cityId'))">
											<option value="-1">请选择...</option>
										</select>
								</div>
							</div>

							<div class="ly-col fl" style="width: 33%;">
								<div class="label block fl hidden">城市：</div>
								<div class="input block fl hidden">
									<select id="cityId" name="cityId" class="cityId"  style="margin: 0;"
											onchange="changeCity(this.value,$('#cityId'))">
										<option value="-1">请选择...</option>
									</select>
								</div>
							</div>
							
							<div class="ly-col fl" style="width: 33%;">
								<div class="label block fl hidden">县/区：</div>
								<div class="input block fl hidden">
									<select id="countyId" name="countyId" class="countyId">
										<option value="-1">请选择...</option>
									</select>
								</div>
							</div>
						  </div>
						  
						</div>
                            <div class="ly-button-w" style="margin-top: 25px;">
						      <a href="javascript:doMessageQuery();" class="button btn-query">查询</a> 
						      <a href="javascript:doMessageClear();" class="button btn-reset">重置</a>
				            </div> 
					</div>
					
</body>
</html>