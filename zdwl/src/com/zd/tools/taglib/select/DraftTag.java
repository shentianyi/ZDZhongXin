package com.zd.tools.taglib.select;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.service.SuperviseImportService;
import com.zd.tools.StringUtil;

@SuppressWarnings("serial")
public class DraftTag extends TagSupport {

	private DealerService dealerService = new DealerService();
	private Object draftid;
	private Object idtype;
	private Object draftNum;
	java.text.DecimalFormat myformat=new java.text.DecimalFormat("0.00");
	
	public int doStartTag() throws JspException{
		int id = (Integer) draftid;
	
		String name="";
		String type = "";
		String num = "";
		if(idtype != null){
			type = idtype.toString();
		}
		if(draftNum != null){
			num = draftNum.toString();
		}
		
		try{
			
			if(id > 0){
				DraftService service = new DraftService();
				DraftVO draft = service.loadDraftById(id);
				
				if(draft != null){
					//敞口金额
					if(type.equals("ckje")){
						//开票金额
						String billing_money = draft.getBilling_money();
						double billing = Double.parseDouble(billing_money==null?"0":billing_money.trim());
						//回款金额
						String draft_num = "";
						if(!StringUtil.isEmpty(draft.getDraft_num())){
							draft_num = draft.getDraft_num();
						}
						SuperviseImportService siservice = new SuperviseImportService();
						SuperviseImportQueryVO siquery = new SuperviseImportQueryVO();
						siquery.setDraft_num(draft_num);
						siquery.setState(4);
						List<SuperviseImportVO> list = siservice.searchSuperviseImportList(siquery);
						double sum = 0;
						if(list != null && list.size() > 0){
							for(SuperviseImportVO si : list){
								if(!StringUtil.isEmpty(si.getPayment_amount())){
									double price = Double.parseDouble(si.getPayment_amount()==null?"0":si.getPayment_amount().trim());
									sum += price;
								}
							}
						}
						
						
						name = myformat.format(billing - sum);
						//库存台数
					}else if(type.equals("kcts")){
						String draft_num = "";
						if(!StringUtil.isEmpty(draft.getDraft_num())){
							draft_num = draft.getDraft_num();
						}
						SuperviseImportService siservice = new SuperviseImportService();
						SuperviseImportQueryVO siquery = new SuperviseImportQueryVO();
						siquery.setDraft_num(draft_num);
						siquery.setStates("2,4");
						List<SuperviseImportVO> ztList = siservice.searchSuperviseImportList(siquery);
						
						name = ztList.size()+"";
						//库存金额
					}else if(type.equals("kcje")){
						String draft_num = draft.getDraft_num();
						SuperviseImportService siservice = new SuperviseImportService();
						SuperviseImportQueryVO siquery = new SuperviseImportQueryVO();
						siquery.setDraft_num(draft_num);
						siquery.setStates("2,4");
						List<SuperviseImportVO> list = siservice.searchSuperviseImportList(siquery);
						double sum = 0;
						for(SuperviseImportVO si : list){
							if(!StringUtil.isEmpty(si.getMoney())){
								double price = Double.parseDouble(si.getMoney()==null?"0":si.getMoney().trim());
								sum += price;
							}
						}
						name = myformat.format(sum);
						//未押车金额
					}else if(type.equals("wycje")){
						//开票金额
						String billing_money = draft.getBilling_money()!=null?draft.getBilling_money().trim():"0";
						
						double billing = Double.parseDouble(billing_money);
						double yycsum = 0;
						//已押车金额
						BigDecimal sum = new BigDecimal(0);
						
						SuperviseImportService siservice = new SuperviseImportService();
						SuperviseImportQueryVO siquery = new SuperviseImportQueryVO();
						siquery.setDraft_num(draft.getDraft_num());
						siquery.setStates("2,3,4");
						List<SuperviseImportVO> siList = siservice.searchSuperviseImportList(siquery);
						if(siList != null && siList.size()>0){
							for(SuperviseImportVO sivo : siList){
								BigDecimal money = new BigDecimal(sivo.getMoney()==null?"0":sivo.getMoney().trim());
								sum = sum.add(money);
							}
						}
						yycsum = sum.intValue();
						
						name = myformat.format(billing - yycsum);
						//已押车金额
					}else if(type.equals("yycje")){
						
						BigDecimal sum = new BigDecimal(0);
						
						SuperviseImportService siservice = new SuperviseImportService();
						SuperviseImportQueryVO siquery = new SuperviseImportQueryVO();
						siquery.setDraft_num(draft.getDraft_num());
						siquery.setStates("2,3,4");
						List<SuperviseImportVO> siList = siservice.searchSuperviseImportList(siquery);
						if(siList != null && siList.size()>0){
							for(SuperviseImportVO sivo : siList){
								BigDecimal money = new BigDecimal(sivo.getMoney()==null?"0":sivo.getMoney().trim());
								sum = sum.add(money);
							}
						}
						
						
						name = myformat.format(sum);
					}
					
					if(type.equals("jxs")){
						DealerVO dealer = dealerService.get(draft.getDistribid());
						if(dealer != null){
							name = dealer.getDealerName();
						}
					}if(type.equals("jrjg")){
						name = dealerService.getBankNameByDealerId(draft.getDistribid());
					}
					
					
				}
				
			}
			if(!StringUtil.isEmpty(num)){
				DraftService service = new DraftService();
				DraftQueryVO dquery = new DraftQueryVO();
				dquery.setDraft_num(num);
				List<DraftVO> dList = service.searchDraftList(dquery);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		       
				if(dList != null && dList.size()>0){
					DraftVO draft = dList.get(0);
					//开票金额
					if(type.equals("billing_money")){
						name = draft.getBilling_money();
					//开票日
					}else if(type.equals("billing_date")){
						if(draft.getBilling_date()!=null)
							name = sdf.format(draft.getBilling_date());
						else 
							name="";
					//到期日
					}else if(type.equals("due_date")){
						if(draft.getDue_date()!=null)
							name = sdf.format(draft.getDue_date());
						else
							name="";
					}
					
					
				}
				
			}
			pageContext.getOut().write(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 6;
		
	}
	
	public Object getDraftid() {
		return draftid;
	}
	public void setDraftid(Object draftid) throws JspException {
		this.draftid = (Integer) ExpressionEvaluatorManager.evaluate("draftid", draftid.toString(), Integer.class, this,pageContext);
	}
	public Object getIdtype() {
		return idtype;
	}
	public void setIdtype(Object idtype) throws JspException {
		this.idtype = (String) ExpressionEvaluatorManager.evaluate("idtype", idtype.toString(), String.class, this,pageContext);
	}

	public Object getDraftNum() {
		return draftNum;
	}

	public void setDraftNum(Object draftNum) throws JspException {
		this.draftNum = (String) ExpressionEvaluatorManager.evaluate("draftNum", draftNum.toString(), String.class, this,pageContext);
	}
	
	
}
