package com.zd.csms.ledger;

import java.text.SimpleDateFormat;
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
import com.zd.csms.supervisorymanagement.contants.CurrentDealerTypeContants;
import com.zd.csms.supervisorymanagement.model.CurrentDealerVO;
import com.zd.csms.supervisorymanagement.model.ExtraworkApplyBean;
import com.zd.csms.supervisorymanagement.model.ExtraworkApplyQueryVO;
import com.zd.csms.supervisorymanagement.model.ExtraworkApplyVO;
import com.zd.csms.supervisorymanagement.service.ExtraworkApplyService;
import com.zd.csms.supervisorymanagement.web.form.ExtraworkApplyForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class ExtraworkApplyAction extends ActionSupport {
	
	private ExtraworkApplyService service = new ExtraworkApplyService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();
	
	public ActionForward findPageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		ExtraworkApplyForm extraworkApplyForm=(ExtraworkApplyForm) form;
		ExtraworkApplyQueryVO query=extraworkApplyForm.getQuery();
		if (query.getPageType() == 0) {
			query.setPageType(1);
		}
		int pageType=query.getPageType();
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("extraworkApplyList", request);
		//记录查询条件,用于查询条件变更时返回第一页
		tools.saveQueryVO(query);
		query.setPageType(pageType);
		query.setCurrentRole(-1);
		List<ExtraworkApplyBean> list=service.findPageList(query,tools);
		request.setAttribute("list", list);
		setOptions(request);
		return mapping.findForward("extraworkApply_ledger");
	}
	
	public ActionForward detailPage(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		ExtraworkApplyForm extraworkForm=(ExtraworkApplyForm) actionform;
		int extraworkApplyId=extraworkForm.getExtraworkApply().getId();
		ExtraworkApplyVO extraworkApply=service.get(extraworkApplyId);
		//申请时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		String applyTime=sdf.format(extraworkApply.getApplyTime());
		request.setAttribute("applyTime",applyTime);
		List<CurrentDealerVO> currentDealerList=service.findCurrentDealerListByExtraworkApplyId(extraworkApplyId,CurrentDealerTypeContants.EXTRAWORKAPPLY.getCode());
		request.setAttribute("currentDealerList",currentDealerList);
		extraworkForm.setExtraworkApply(extraworkApply);
		setOptions(request);
		
		try {
			MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
			approvalQuery.setObjectId(extraworkApplyId);
			approvalQuery.setObjectType(ApprovalTypeContant.SUPERVISORYEXTRAWORKAPPLY.getCode());
			request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回修改页面
		return mapping.findForward("extraworkApply_detail");
	}
	
	public void setOptions(HttpServletRequest request){
		//所有监管员
		request.setAttribute("superviseOptions", OptionUtil.getRepository());
		//所有经销商
		request.setAttribute("dealers", OptionUtil.getDealers());
		//所有品牌
		request.setAttribute("brands", OptionUtil.getBrands());
		//所有银行
		request.setAttribute("banks", OptionUtil.getBanks());
		//列表页面审批状态
		request.setAttribute("queryApprovalState", OptionUtil.getQueryApprovalState());
	}

}
