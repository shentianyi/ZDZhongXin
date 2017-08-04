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
import com.zd.csms.file.model.UploadfileVO;
import com.zd.csms.file.service.UploadfileService;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.querybean.ApprovalQueryBean;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.supervisorymanagement.contants.CurrentDealerTypeContants;
import com.zd.csms.supervisorymanagement.model.CurrentDealerVO;
import com.zd.csms.supervisorymanagement.model.ResignApplyBean;
import com.zd.csms.supervisorymanagement.model.ResignApplyQueryVO;
import com.zd.csms.supervisorymanagement.model.ResignApplyVO;
import com.zd.csms.supervisorymanagement.service.ResignApplyService;
import com.zd.csms.supervisorymanagement.web.form.ResignApplyForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class ResignApplyAction extends ActionSupport {
	
	private ResignApplyService service = new ResignApplyService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();
	UploadfileService uploadfileService = new UploadfileService();
	
	public ActionForward findPageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		ResignApplyForm resignForm=(ResignApplyForm) form;
		ResignApplyQueryVO query=resignForm.getQuery();
		int pageType=query.getPageType();
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("resignList", request);
		//记录查询条件,用于查询条件变更时返回第一页
		tools.saveQueryVO(query);
		query.setPageType(pageType);
		query.setCurrentRole(-1);
		List<ResignApplyBean> list=service.findPageList(query,tools);
		request.setAttribute("list", list);
		setOptions(request);
		return mapping.findForward("resignApply_ledger");
	}
	
	public ActionForward detailPage(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		ResignApplyForm resignForm=(ResignApplyForm) actionform;
		int resignId=resignForm.getResignApply().getId();
		ResignApplyVO resign=service.get(resignId);
		try {
			UploadfileVO ufvo = uploadfileService.loadUploadfileById(resign.getPictureId());
			if(ufvo!=null){
				request.setAttribute("filepath", ufvo.getFile_path());
				request.setAttribute("picname", ufvo.getFile_name());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//申请时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		String applyTime=sdf.format(resign.getApplyTime());
		request.setAttribute("applyTime",applyTime);
		
		List<CurrentDealerVO> currentDealerList=service.findCurrentDealerListByResignApplyId(resignId,CurrentDealerTypeContants.RESIGNAPPLY.getCode());
		request.setAttribute("currentDealerList",currentDealerList);
		resignForm.setResignApply(resign);
		setOptions(request);
		
		try {
			MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
			approvalQuery.setObjectId(resignId);
			approvalQuery.setObjectType(ApprovalTypeContant.SUPERVISORYRESIGNAPPLY.getCode());
			List<ApprovalQueryBean> approvals=approvalService.findListByApprovalType(approvalQuery);
			request.setAttribute("approvals",approvals);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("resignApply_detail");
	}

	public void setOptions(HttpServletRequest request){
		/*//所有监管员
		request.setAttribute("superviseOptions", OptionUtil.getRepository());
		//所有经销商
		request.setAttribute("dealers", OptionUtil.getDealers());
		//所有品牌
		request.setAttribute("brands", OptionUtil.getBrands());
		//所有银行
		request.setAttribute("banks", OptionUtil.getBanks());*/
		//列表页面审批状态
		request.setAttribute("queryApprovalState", OptionUtil.getQueryApprovalState());
		
	}

}
