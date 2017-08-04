package com.zd.csms.marketing.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.marketing.model.AgreementBackQueryVO;
import com.zd.csms.marketing.querybean.AgreementQueryBean;
import com.zd.csms.marketing.service.AgreementBackService;
import com.zd.csms.marketing.web.form.AgreementBackForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class AgreementLedgerAction extends ActionSupport {

	public ActionForward agreementLedgerListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return agreementLedgerList(mapping, form,request, response);
	}
	
	
	public ActionForward agreementLedgerList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		AgreementBackForm abform = (AgreementBackForm)form;
		AgreementBackService service = new AgreementBackService();
		
		AgreementBackQueryVO asQuery = abform.getAgreementBackquery();
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("agreementLedgerList",request);
		thumbPageTools.saveQueryVO(asQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<AgreementQueryBean> list = service.searchAgreementBackListByPage(asQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//返回列表页面
		return mapping.findForward("agreement_ledger_list");
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("yesornoOptions", OptionUtil.yesorno());
		request.setAttribute("dealersOptions", OptionUtil.getDealers());
		request.setAttribute("brandOptions", OptionUtil.getBrands()); 
		
	}
}
