package com.zd.csms.messageAndWaring.message.service.market;

import java.util.List;
import com.zd.csms.messageAndWaring.message.dao.MessageDAOFactory;
import com.zd.csms.messageAndWaring.message.dao.market.IMarketMessageTypeDAO;
import com.zd.csms.messageAndWaring.message.queryBean.market.MarketMessageQueryBean;
import com.zd.csms.messageAndWaring.message.queryBean.market.MarketWaringQueryBean;
import com.zd.csms.messageAndWaring.message.service.MessageManagerService;
import com.zd.csms.messageAndWaring.message.web.form.MessageManagerForm;
import com.zd.csms.messageAndWaring.message.web.form.market.MarkrtMessageManagerForm;
import com.zd.tools.thumbPage.IThumbPageTools;

public class MarketMessageManagerService extends MessageManagerService {
	private IMarketMessageTypeDAO daoType = MessageDAOFactory.getMarketMessageTypeDAO();

	// 消息提醒类别下的消息列表
	public List<MarketMessageQueryBean> findMessageList(MessageManagerForm form,IThumbPageTools tools) {
		MarkrtMessageManagerForm remindForm = (MarkrtMessageManagerForm) form;
		List<MarketMessageQueryBean> list = daoType.findMessageList(remindForm, tools) ;
		return list;
	}
	
	// 消息预警类别下的消息列表
	public List<MarketWaringQueryBean> findWaringList(MessageManagerForm form,IThumbPageTools tools) {
		MarkrtMessageManagerForm remindForm = (MarkrtMessageManagerForm) form;
		List<MarketWaringQueryBean> list = daoType.findWaringList(remindForm, tools);
		return list ;
	}

	
}
