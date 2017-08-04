package com.zd.csms.ledger;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.repository.constants.RepositoryStatus;
import com.zd.csms.supervisorymanagement.model.MailcostQueryVO;
import com.zd.csms.supervisorymanagement.model.MailcostVO;
import com.zd.csms.supervisorymanagement.service.MailcostService;
import com.zd.csms.supervisorymanagement.web.excel.MailcostRowMapper;
import com.zd.csms.supervisorymanagement.web.form.MailcostForm;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class MailcostAction extends ActionSupport {

	private MailcostService service = new MailcostService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();
	
	/**
	 * 分页查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mailcostLedger(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		MailcostForm mform = (MailcostForm)form;
		MailcostQueryVO query = mform.getMailcostquery();
		
		query.setPageType(2);
		query.setCurrRole(-1);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("mailcostLedger", request);
		tools.saveQueryVO(query);
		
		List<MailcostVO> list = service.searchMailcostListByPage(query, tools);

		request.setAttribute("list", list);
		request.setAttribute("mailcostStateOptions", OptionUtil.mailcostState());
		request.setAttribute("superviseOptions", OptionUtil.getRepository(RepositoryStatus.VALID.getCode(),RepositoryStatus.ALREADYPOST.getCode()));
		return mapping.findForward("mailcost_ledger");
	}

	/**
	 * 详情页
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mailcostDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MailcostForm mform = (MailcostForm) form;
		MailcostVO bean = service.loadMailcostById(mform.getMailcost().getId());

		mform.setMailcost(bean);
		String mailItems=bean.getMailingitems();
		if(mailItems!=null && mailItems!=""){
			String[] mailingitemsStr=mailItems.split(",");
			Integer[] mailingitems=new Integer[mailingitemsStr.length];
			for(int i=0;i<mailingitemsStr.length;i++){
				mailingitems[i]=Integer.parseInt(mailingitemsStr[i]);
			}
			mform.setMailingitemss(mailingitems);
		}
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.POSTAGEREQUISITION.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));

		setOptions(request);
		return mapping.findForward("mailcost_detail");
	}
	
	public ActionForward export(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		MailcostForm mform = (MailcostForm)form;
		MailcostQueryVO query = mform.getMailcostquery();
		query.setPageType(2);
		query.setCurrRole(-1);
		List<MailcostVO> list = service.searchMailcostList(query);
		IExportFile export = new ExportFileExcelImpl();
		try {
			export.export(list, new MailcostRowMapper(), export.createDefaultFileName("邮寄费用申请台账"),"邮寄费用申请台账", response);
			System.out.println("--------------------exportEnd:"+new Date().getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回列表页面
		return null;
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("mailcostStateOptions", OptionUtil.mailcostState());
		request.setAttribute("superviseOptions",  OptionUtil.getRepository(RepositoryStatus.VALID.getCode(),RepositoryStatus.ALREADYPOST.getCode()));
		
	}
}
