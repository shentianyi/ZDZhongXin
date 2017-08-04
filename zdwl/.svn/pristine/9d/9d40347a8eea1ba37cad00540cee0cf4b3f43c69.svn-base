package com.zd.csms.supervisorymanagement.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.contants.SupervisorAttributeContant;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.querybean.SupervisorJsonBean;
import com.zd.csms.marketing.service.BusinessTransferService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.repository.model.RepositoryEntity;
import com.zd.csms.repository.model.RepositoryQueryVO;
import com.zd.csms.repository.web.excel.RepositoryRowMapper;
import com.zd.csms.repository.web.form.RepositoryForm;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.service.SupervisoryService;
import com.zd.csms.supervisorymanagement.contants.ReceiveNatureContants;
import com.zd.csms.supervisorymanagement.model.HandoverLogVO;
import com.zd.csms.supervisorymanagement.model.TransferQueryVO;
import com.zd.csms.supervisorymanagement.model.TransferRepositoryVO;
import com.zd.csms.supervisorymanagement.model.TransferSupervisorVO;
import com.zd.csms.supervisorymanagement.model.TransferVO;
import com.zd.csms.supervisorymanagement.service.HandoverLogService;
import com.zd.csms.supervisorymanagement.service.TransferService;
import com.zd.csms.supervisorymanagement.web.excel.TransferRowMapper;
import com.zd.csms.supervisorymanagement.web.form.TransferForm;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;
public class TransferAction extends ActionSupport {
	
	TransferService service=new TransferService();
	
	public ActionForward addTransferEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("dealers", OptionUtil.getDealers());
		request.setAttribute("supervisors", OptionUtil.getAllSupervise());
		setOptions(request);
		//返回新增页面
		return mapping.findForward("add_transfer");
	}
	
	public ActionForward addTransfer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		TransferForm transferForm =(TransferForm) form;
		TransferVO transfer=transferForm.getTransfer();
		UserSession user = UserSessionUtil.getUserSession(request);
		transfer.setCreateTime(new Date());
		transfer.setCreateUser(user.getUser().getId());
		boolean flag=false;
		try {
			flag = service.add(transfer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		if(flag){
			//新增成功返回列表页
			return transferPageList(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return addTransferEntity(mapping,form,request,response);
		}
	}
	
	public ActionForward updTransferEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		TransferForm transferForm =(TransferForm) actionform;
		TransferVO transfer=null;
		try {
			transfer = service.load(transferForm.getTransfer().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//对象不存在时返回列表页
		if(transfer==null){
			//将执行结果及消息设置到request为页面处理使用
			request.setAttribute(Constants.OPERATE_FLAG.getCode(), false);
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改内容不存在");
			return transferPageList(mapping,transferForm,request,response);
		}
		transferForm.setTransfer(transfer);
		request.setAttribute("dealers", OptionUtil.getDealers());
		request.setAttribute("supervisors", OptionUtil.getAllSupervise());
		setOptions(request);
		//返回新增页面
		return mapping.findForward("upd_transfer");
	}
	
	public ActionForward updTransfer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		TransferForm transferForm =(TransferForm) form;
		TransferVO transfer=transferForm.getTransfer();
		UserSession user = UserSessionUtil.getUserSession(request);
		transfer.setLastModifyTime(new Date());
		transfer.setLastModifyUser(user.getUser().getId());
		boolean flag=false;
		try {
			flag = service.update(transfer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		if(flag){
			//修改成功返回列表页
			return transferPageList(mapping,form,request,response);
		}else{
			//修改失败返回修改页面
			return updTransferEntity(mapping,form,request,response);
		}
	}
	public ActionForward delTransfer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		TransferForm transferForm =(TransferForm) form;
		boolean flag=false;
		try {
			flag = service.delete(transferForm.getTransfer().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		//返回列表页
		return transferPageList(mapping,transferForm,request,response);
	}
	/**
	 * 返回分页列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward transferPageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		TransferForm transferForm =(TransferForm) form;
		TransferQueryVO transferQuery=transferForm.getQuery();
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("transferList", request);
		//记录查询条件,用于查询条件变更时返回第一页
		tools.saveQueryVO(transferQuery);
		//按条件查询分页数据
		List<TransferVO> list = service.searchTransferListByPage(transferQuery, tools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		request.setAttribute("dealers", OptionUtil.getDealers());
		request.setAttribute("supervisors", OptionUtil.getAllSupervise());
		setOptions(request);
		request.setAttribute("role", getCurrRole(request));
		//返回列表页面
		return mapping.findForward("transfer_list");
	}
	/**
	 * 列表页需要使用的角色
	 */
	private static int[] approvalRole = new int[]{
			RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY.getCode(),
			RoleConstants.SUPERVISORY.getCode()};
	/**
	 * 获取当前用户的权限
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}
	
	/**
	 * 返回列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public List<TransferVO> TransferList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		TransferForm transferForm =(TransferForm) form;
		TransferQueryVO transferQuery=transferForm.getQuery();
		
		//按条件查询分页数据
		List<TransferVO> list = service.searchTransferList(transferQuery);
		
		return list;
	}
	/**
	 * 根据经销商ID获取各任监管员信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getSupervisorListByTransferId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		BusinessTransferService businessTransferService = new BusinessTransferService();
		TransferForm transferForm =(TransferForm) form;
		int dealerId=transferForm.getTransfer().getDealerId();
		List<TransferSupervisorVO> list=new ArrayList<TransferSupervisorVO>();
		TransferSupervisorVO ts=null;
		List<TransferRepositoryVO> transferRepositoryList=new ArrayList<TransferRepositoryVO>();
		transferRepositoryList=service.getSupervisorListByDealerId(dealerId);
		if(transferRepositoryList!=null && transferRepositoryList.size()>0){
			for(TransferRepositoryVO transferRepository:transferRepositoryList){
				int repositoryId=transferRepository.getRepositoryId();
				SupervisorJsonBean sBaseInfo;
				try {
					sBaseInfo = businessTransferService.getSupervisorByRepositoryId(repositoryId);
					if(sBaseInfo!=null){
						ts=new TransferSupervisorVO();
						ts.setName(sBaseInfo.getName());
						ts.setGender(sBaseInfo.getGender());
						ts.setContactNumber(sBaseInfo.getSelfTelephone());
						ts.setEntryDate(transferRepository.getEntryTime());
						ts.setEntryNature(transferRepository.getEntryNature());
//						新增
						ts.setLeaveDate(transferRepository.getLeaveTime());
						ts.setLeaveNature(transferRepository.getLeaveNature());
						ts.setRemark("");
						ts.setWorkCondition("");
						RosterService rosterService=new RosterService();
						RosterVO roster=rosterService.getRosterBySupervisorId(sBaseInfo.getId());
						if(roster!=null){
							ts.setStaffNo(roster.getStaffNo());
						}
						list.add(ts);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		/*TransferForm transferForm =(TransferForm) form;
		int dealerId=transferForm.getTransfer().getDealerId();
		//交接流转单  根据 经销商ID 获取 监管员ID
		DealerSupervisorService dsService=new DealerSupervisorService();
		DealerSupervisorQueryVO dsquery=new DealerSupervisorQueryVO();
		dsquery.setDealerId(dealerId);
		List<DealerSupervisorVO> dsVOs=dsService.searchDealerSupervisorList(dsquery);
		//现任监管员
		TransferSupervisorVO ts=null;
		if(dsVOs!=null&&dsVOs.size()>0){
			DealerSupervisorVO ds1=dsVOs.get(0);
			if(ds1!=null){
				String entryNature= SupervisorAttributeContant.getName(ds1.getSupervisorAttribute());
				if(entryNature!=null){
					ts=new TransferSupervisorVO();
					ts.setEntryNature(SupervisorAttributeContant.getName(ds1.getSupervisorAttribute()));
				}
			}
			int supervisorId=ds1.getRepositoryId();
			SupervisoryService service = new SupervisoryService();
			SupervisorBaseInfoVO sBaseInfo = service.getBaseInfo(supervisorId);
			if(sBaseInfo!=null){
				if(ts==null){
					ts=new TransferSupervisorVO();
				}
				ts.setName(sBaseInfo.getName());
				ts.setGender(sBaseInfo.getGender());
				ts.setContactNumber(sBaseInfo.getSelfTelephone());
				ts.setEntryDate(sBaseInfo.getEntryTime());
			}
			
			RosterService rosterService=new RosterService();
			RosterVO roster=rosterService.getRosterBySupervisorId(supervisorId);
			if(roster!=null){
				ts.setStaffNo(roster.getStaffNo());
			}
			
		}
		if(ts!=null){
			list.add(ts);
		}
		//交接记录表中的接收人
		HandoverLogService hlService=new HandoverLogService();
		List<HandoverLogVO> handOverLogs=hlService.getHandoverLogByDealerId(dealerId);
		if(handOverLogs!=null && handOverLogs.size()>0){
			for(HandoverLogVO handoverLogVO:handOverLogs){
				if(handoverLogVO!=null){
					list.add(assemTransferSupervisorVO(handoverLogVO));
				}
			}
		}*/
		
		request.setAttribute("list", list);
		request.setAttribute("max", list.size());
		//返回列表页面
		return mapping.findForward("transfer_supervisor_list");
	}
	
	public TransferSupervisorVO assemTransferSupervisorVO(HandoverLogVO handoverLogVO){
		int supervisorId=handoverLogVO.getReceiver();
		TransferSupervisorVO ts=new TransferSupervisorVO();
		SupervisoryService service = new SupervisoryService();
		SupervisorBaseInfoVO sBaseInfo = service.getBaseInfo(supervisorId);
		if(sBaseInfo!=null){
			ts.setName(sBaseInfo.getName());
			ts.setGender(sBaseInfo.getGender());
			ts.setContactNumber(sBaseInfo.getSelfTelephone());
			
		}
		RosterService rosterService=new RosterService();
		RosterVO roster=rosterService.getRosterBySupervisorId(supervisorId);
		if(roster!=null){
			ts.setStaffNo(roster.getStaffNo());
		}
		ts.setEntryDate(handoverLogVO.getMissionDate());
		ts.setEntryNature(ReceiveNatureContants.getByCode(handoverLogVO.getReceiveNature()).getName());
		ts.setRemark(handoverLogVO.getRemark());
		ts.setWorkCondition(handoverLogVO.getWorkCondition());
		return ts;
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("handoverNatures", OptionUtil.handoverNatureOptions());
		request.setAttribute("resignReasons", OptionUtil.resignReasonOptions());
		request.setAttribute("receiveNatures", OptionUtil.receiveNatureOptions());
		request.setAttribute("afterHandoverNature", OptionUtil.afterHandoverNatureOptions());
		
	}
	
	public ActionForward extExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		TransferForm transferForm =(TransferForm) form;
		TransferQueryVO transferQuery = transferForm.getQuery();
		//按条件查询分页数据
		List<TransferVO> list = service.searchTransferList(transferQuery);
		IExportFile export = new ExportFileExcelImpl();
		try {
			export.export(list, new TransferRowMapper(), export.createDefaultFileName("调动表"),"调动表", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回列表页面
		return null;
	}
	
}
