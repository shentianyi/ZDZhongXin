package com.zd.csms.messageAndWaring.message.web.action.market;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zd.core.ActionSupport;
import com.zd.csms.messageAndWaring.message.queryBean.market.MarketMessageQueryBean;
import com.zd.csms.messageAndWaring.message.queryBean.market.MarketWaringQueryBean;
import com.zd.csms.messageAndWaring.message.service.MessageManagerService;
import com.zd.csms.messageAndWaring.message.service.market.MarketMessageManagerService;
import com.zd.csms.messageAndWaring.message.util.MessageTemplateUtil;
import com.zd.csms.messageAndWaring.message.web.form.market.MarkrtMessageManagerForm;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;
/**
 * 市场部-信息提醒和预警
 * @author zhangzhicheng
 *
 */
public class MarketMessageManagerAction extends ActionSupport {
	private MessageManagerService service = new MarketMessageManagerService();

	@SuppressWarnings("unchecked")
	public ActionForward findMessageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		MarkrtMessageManagerForm remindForm = (MarkrtMessageManagerForm) form;
		if (remindForm.getMsgType()<=0) {
			return null ;
		
	     }
		 UserVO user = UserSessionUtil.getUserSession(request).getUser();
		 remindForm.setUserId(user.getId());
		 IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		 tools.saveQueryVO(remindForm);
		 Object  objs=  service.findMessageList(remindForm,tools);
		 List<MarketMessageQueryBean> list=null ;
		 if(objs!=null){
				list = (List<MarketMessageQueryBean>) objs ;
			}
		 request.setAttribute("list", list);
		 request.setAttribute("queryIds",MessageTemplateUtil.getQueryTemplate(remindForm.getMsgType()));
		 request.setAttribute("columnClasss",MessageTemplateUtil.getColumnTemplate(remindForm.getMsgType()));
		 return mapping.findForward("msg_info_list");

	}
	
	@SuppressWarnings("unchecked")
	public ActionForward findWaringList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MarkrtMessageManagerForm remindForm = (MarkrtMessageManagerForm) form;
		if (remindForm.getMsgType() <= 0) {
			return null;
	    }
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		remindForm.setUserId(user.getId());
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		tools.saveQueryVO(remindForm);
		List<MarketWaringQueryBean> list= (List<MarketWaringQueryBean>) service.findWaringList(remindForm, tools);
        request.setAttribute("list", list);
		request.setAttribute("queryIds",
				MessageTemplateUtil.getQueryTemplate(remindForm.getMsgType()));
		request.setAttribute("columnClasss",
				MessageTemplateUtil.getColumnTemplate(remindForm.getMsgType()));
		return mapping.findForward("waring_info_list");

	}

	public ActionForward submitMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MarkrtMessageManagerForm remindForm = (MarkrtMessageManagerForm) form;
		if (remindForm.getMsgType()<=0) {
			return null ;
		
	    }
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		remindForm.setUserId(user.getId());
	    service.submitMessage(remindForm);
        return findMessageList(mapping, form, request, response);
	}
	
	public ActionForward submitWaring(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MarkrtMessageManagerForm remindForm = (MarkrtMessageManagerForm) form;
		if (remindForm.getMsgType()<=0) {
			return null ;
		
	    }
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		remindForm.setUserId(user.getId());
		service.submitWaring(remindForm);
		return findWaringList(mapping, form, request, response);
	}

}
