package com.zd.csms.messageAndWaring.message.service.business;
import java.util.List;
import com.zd.csms.messageAndWaring.message.dao.MessageDAOFactory;
import com.zd.csms.messageAndWaring.message.dao.business.IBunisessMessageTypeDAO;
import com.zd.csms.messageAndWaring.message.queryBean.business.BusinMessageQueryBean;
import com.zd.csms.messageAndWaring.message.queryBean.business.BusinWaringQueryBean;
import com.zd.csms.messageAndWaring.message.service.MessageManagerService;
import com.zd.csms.messageAndWaring.message.web.form.MessageManagerForm;
import com.zd.csms.messageAndWaring.message.web.form.business.BusinessMessageForm;
import com.zd.tools.thumbPage.IThumbPageTools;

public class BusinessMessageManagerService extends  MessageManagerService{
	private IBunisessMessageTypeDAO daoType = MessageDAOFactory.getBusinessMessageTypeDAO();

	public List<BusinWaringQueryBean> findWaringList(MessageManagerForm form,IThumbPageTools tools){
		BusinessMessageForm remindForm = (BusinessMessageForm) form;
		return daoType.findWaringList(remindForm, tools);
	}
	

	@Override
	public List<BusinMessageQueryBean> findMessageList(MessageManagerForm form,
			IThumbPageTools tools) {
		BusinessMessageForm remindForm = (BusinessMessageForm) form;
		return daoType.findMessageList(remindForm, tools);
	}

}
