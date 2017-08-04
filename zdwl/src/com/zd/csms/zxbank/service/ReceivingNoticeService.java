package com.zd.csms.zxbank.service;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.zxbank.bean.ReceivingNotice;
import com.zd.csms.zxbank.dao.IReceivingNoticeDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 收货通知Service
 */
public class ReceivingNoticeService extends ServiceSupport {
	private IReceivingNoticeDAO dao = ZXBankDAOFactory.getReceivingNoticeDAO();

	public List<ReceivingNotice> findBusinessList(ReceivingNotice query, IThumbPageTools tools) {
		return dao.firnAllAgList(query, tools);
	}

	public ReceivingNotice getNotify(String no) {
		return dao.getNotify(no);
	}
	
	/**
	 * 输入框下拉选项列表
	 * @return
	 */
	public List<OptionObject> draftsOptions(){
		List<OptionObject> options = new ArrayList<OptionObject>();
		OptionObject option;
		List<String> drafts =new ArrayList<String>();
		List<String> list=dao.getListNotice();
		for (String string : list) {
			if(string!=null)
				if(!string.trim().isEmpty())
						drafts.add(string);
		}
		if(drafts != null && drafts.size() > 0){
			for(String draftNum : drafts){
				option = new OptionObject(draftNum,draftNum);
				options.add(option);
			}
		}
		return options;
	}
}
