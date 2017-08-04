package com.zd.csms.zxbank.service;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.zxbank.bean.RemovePledge;
import com.zd.csms.zxbank.dao.IRemovePledgeDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 解除质押通知Service
 */
public class RemovePledgeService extends ServiceSupport {

	private IRemovePledgeDAO dao = ZXBankDAOFactory.getRemovePledgeDAO();

	public List<RemovePledge> findByQuery(RemovePledge query, IThumbPageTools tools) {
		return dao.findByQuery(query, tools);
	}

	public RemovePledge fingByNo(String no) {
		return dao.fingByNo(no);
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
