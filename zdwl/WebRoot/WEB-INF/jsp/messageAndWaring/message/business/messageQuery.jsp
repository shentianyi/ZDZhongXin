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
	<input type="hidden" name="msgType"
		value="<c:out value="${businessMessageForm.msgType}"/>" />
		
     <input type="hidden" id="onehiId"   value="<c:out value="${businessMessageForm.oneBankId}"/>" />
	 <input type="hidden" id="twohiId"   value="<c:out value="${businessMessageForm.twoBankId}"/>" />
	 <input type="hidden" id="threehiId" value="<c:out value="${businessMessageForm.threeBankId}"/>" />
	 <input type="hidden" id="idsRead" name="idsRead" />
	 <input type="hidden" id="idsShield" name="idsShield" />
	 
	<div class="public-main-input ly-col-2 hidden abs"
		style="height: 165px; margin-top: -50px;">
       <div class="ly-input-w" style="height: 110px;">
						<div class="ly-row clearfix"  style="display:none ;" id="bankQuery">
							<div class="ly-col fl" style="width:33%;" >
								<div class="label block fl hidden">总行/金融机构：</div>
								<div class="input block fl hidden">
									<select id="one" name="oneBankId" class="oneBankId">
										<option value="-1">请选择...</option>
									</select>
								</div>
							</div>
							<div class="ly-col fl" style="width:33%;"  >
								<div class="label block fl hidden">分行/分支机构：</div>
								<div class="input block fl hidden">
									<select id="two" name="twoBankId" class="twoBankId">
										<option value="-1">请选择...</option>
									</select>
								</div>
							</div>
							<div class="ly-col fl" style="width:34%;"  >
								<div class="label block fl hidden">支行：</div>
								<div class="input block fl hidden">
									<div class="ly-sel-w">
										<select id="three" name="threeBankId" class="threeBankId" style="margin:0 0 0 20;">
											<option value="-1">请选择...</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="ly-row clearfix">
							<div class="ly-col fl" style="width:33%;display:none ;" id="dealerQuery" >
								<div class="label block fl hidden">经销商：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none  allText" property="dealerName" styleId="dealerName"/>
								</div>
							</div>
							<div class="ly-col fl" style="width:33%;display:none ;" id="brandQuery" >
								<div class="label block fl hidden">品牌：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none allText" property="brandName" styleId="brandName"/>
								</div>
							</div>
							<div class="ly-col fl" style="width:34%;display:none ;" id="draftNumQuery" >
								<div class="label block fl hidden">票号：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none  allText" property="draft_num" styleId="draft_num"/>
								</div>
							</div>
							
						</div>
						<div class="ly-row clearfix" >
							<div class="ly-col fl" style="width:50%;display:none ;" id="billingTimeQuery" >
	                        	<div class="label block fl hidden" style="width:20%;margin-left: 31px;">开票日：</div>
	                        	<div class="input block fl hidden" style="width:66%;padding-left:22px;">
		                        	<input style="width:35%;" class="ly-bor-none  allDate"  name="billing_date_begin" type="text" value='<fmt:formatDate value="${businessMessageForm.billing_date_begin }" pattern="yyyy-MM-dd"/>'/>
		                        	<span>至</span>
		                        	<input style="width:35%;" class="ly-bor-none  allDate"  name="billing_date_end" type="text" value='<fmt:formatDate value="${businessMessageForm.billing_date_end }" pattern="yyyy-MM-dd"/>'/>
	                        	</div>
	                    	</div>
							<div class="ly-col fl" style="width:50%;display:none ;" id="dueDateQuery" >
	                        	 <div class="label block fl hidden" style="width:15%;">到期日：</div>
	                        	<div class="input block fl hidden" style="width:85%;padding-left:28px;">
		                        	<input style="width:25%;" class="ly-bor-none allDate"  name="due_date_begin" type="text" value='<fmt:formatDate value="${businessMessageForm.due_date_begin }" pattern="yyyy-MM-dd"/>'/>
		                        	<span>至</span>
		                        	<input style="width:25%;" class="ly-bor-none allDate"  name="due_date_end" type="text" value='<fmt:formatDate value="${businessMessageForm.due_date_begin }" pattern="yyyy-MM-dd"/>'/>
	                        	</div> 
	                    	</div>
	                    </div>
	                    
	                    <div class="ly-row clearfix" >
							<div class="ly-col fl" style="width:60%;display:none ;" id="moveQuery" >
	                        	<div class="label block fl hidden" style="width:20%;margin-left: 67px;">移动时间：</div>
	                        	<div class="input block fl hidden" style="width:63%;padding-left:28px;">
		                        	<input style="width:45%;" class="ly-bor-none  allDate"  name="move_date_begin" type="text" value='<fmt:formatDate value="${businessMessageForm.move_date_begin }" pattern="yyyy-MM-dd"/>'/>
		                        	<span>至</span>
		                        	<input style="width:45%;" class="ly-bor-none  allDate"  name="move_date_end" type="text" value='<fmt:formatDate value="${businessMessageForm.move_date_end }" pattern="yyyy-MM-dd"/>'/>
	                        	</div>
	                    	</div>
							<div class="ly-col fl" style="width:33%;display:none ;" id="vinQuery" >
								<div class="label block fl hidden">车架号：</div>
								<div class="input block fl hidden">
									<html:text styleClass="ly-bor-none  allText" property="vin" styleId="vin"/>
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