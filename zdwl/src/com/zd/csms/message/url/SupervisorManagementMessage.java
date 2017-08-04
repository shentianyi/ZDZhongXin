package com.zd.csms.message.url;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.constants.Constants;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.model.SupMaMsgInfoVO;
import com.zd.csms.message.model.SupMaMsgQueryVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.message.service.SupMaMsgService;
import com.zd.csms.message.web.form.MessageForm;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class SupervisorManagementMessage extends ActionSupport {
	SupMaMsgService service = new SupMaMsgService();
	MessageService msgService=new MessageService();
	
	public ActionForward supMaMsgInfoList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MessageForm mform = (MessageForm)form;
		SupMaMsgQueryVO query=mform.getSupMaQuery();
		int type=query.getType();
		String messageIdStr=request.getParameter("parentId");
		int messageId=0;
		if(query.getMessageId()>0){
			messageId=query.getMessageId();
		}else{
			if(StringUtil.isNotEmpty(messageIdStr)){
				messageId=Integer.parseInt(messageIdStr);
			}else{
				messageId=(Integer) request.getAttribute("parentId");
			}
		}
		query.setMessageId(messageId);
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		//按条件查询分页数据
		List<SupMaMsgInfoVO> list = service.searchMessageInfoListByPage(query,tools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		mform.setSupMaQuery(query);
		//返回列表页面
		String returnPage="";
		switch (type) {
		case 41:
			returnPage="handoverPlanInfo";
			break;
		case 42:
			returnPage="supervisionFeeList";
			break;
		case 43:
			returnPage="supervisorAttendanceList";
			break;
		case 44:
			returnPage="noExecuteHandoverPlan";
			break;	
		case 45:
			returnPage="supervisorSignInAbnormal";
			break;	
		case 46:
			returnPage="supervisorSignOutAbnormal";
			break;	
		case 47:
			returnPage="projectCircuNoSupervisor";
			break;
		case 48:
			returnPage="repositoryNoTrain";
			break;
		case 49:
			returnPage="repFifteenDaysNoPost";
			break;
		case 50:
			returnPage="resignNoHandoverLog";
			break;
		case 51:
			returnPage="supWorkFiveMonth";
			break;
		case 52:
			returnPage="supervisorThreeDaySignAbnormal";
			break;
		case 53:
			returnPage="projectCircuNoSupervisorWarning";
			break;
		case 54:
			returnPage="postNoTrain";
			break;
		case 55:
			returnPage="repositoryNoTrainWarning";
			break;
		case 56:
			returnPage="resignNoHandoverLogWarning";
			break;
		case 57:
			returnPage="supWorkSixMonthWarning";
			break;
		case 58:
			returnPage="supAssessNotPass";
			break;
		default:
			break;
		}
		return mapping.findForward(returnPage);
	}
	/**
	 * 修改提醒为已读
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward updateSupMaMsgInfoIsread(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MessageForm mform = (MessageForm)form;
		SupMaMsgQueryVO query=mform.getSupMaQuery();
		int messageId = query.getMessageId();
		int type=query.getType();
		String messageInfoIds = request.getParameter("ids");
		boolean flag=false;
		if(StringUtil.isNotEmpty(messageInfoIds)){
			flag=updateMessageInfoIsread(messageId,messageInfoIds);
		}
		String messageInfoIsmaskIds = request.getParameter("ismaskIds");
		if(StringUtil.isNotEmpty(messageInfoIsmaskIds)){
			flag=updateMessageInfoIsmask(messageId,messageInfoIsmaskIds);
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		String message="";
		if(flag){
			message="提交成功！";
		}else{
			message="提交失败！";
		}
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(),message);
		mform.getSupMaQuery().setMessageId(messageId);
		request.setAttribute("parentId", messageId);
		return supMaMsgInfoList(mapping,form,request,response);
	}
	private boolean updateMessageInfoIsmask(int messageId, String messageInfoIsmaskIds) {
		String[] idList=messageInfoIsmaskIds.split(",");
		boolean flag=true;
		if(idList!=null && idList.length>0){
			for(String id:idList){
				flag=flag && service.updateMessageInfoIsmask(Integer.parseInt(id),2);
			}
		}
		return flag;
	}
	public boolean updateMessageInfoIsread(int messageId,String messageInfoIds){
		String[] idList=messageInfoIds.split(",");
		boolean flag=true;
		if(idList!=null && idList.length>0){
			for(String id:idList){
				flag=flag && service.updateMessageInfoIsread(Integer.parseInt(id),2);
			}
		}
		//修改MessageVO对象中的是否已读（MessageInfo全部已读才修改为已读）
		if(flag){
			MessageVO message;
			try {
				message = msgService.loadMessageById(messageId);
				if(message!=null){
					int count =service.searchNotReadMessageInfoCount(messageId);
					if(count==0){
						message.setIsread(2);
						flag=msgService.updMessage(message);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	/*
	 * 预警信息标记全部已读
	*/
	public ActionForward ReadAllSupMaMsgInfoList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MessageForm mform = (MessageForm)form;
		SupMaMsgQueryVO query=mform.getSupMaQuery();
		String messageIdStr=request.getParameter("parentId");
		int messageId=0;
		if(query.getMessageId()>0){
			messageId=query.getMessageId();
		}else{
			if(StringUtil.isNotEmpty(messageIdStr)){
				messageId=Integer.parseInt(messageIdStr);
			}else{
				messageId=(Integer) request.getAttribute("parentId");
			}
		}
		query.setMessageId(messageId);
		int result = service.updateMessageInfoIsreadByAll(query);
		if (result > 0) {
			System.out.println("标记成功！");
			return supMaMsgInfoList(mapping, mform, request, response);
		}else {
			System.out.println("标记失败！");
		}
		return supMaMsgInfoList(mapping, mform, request, response);
		
	}
	
}
