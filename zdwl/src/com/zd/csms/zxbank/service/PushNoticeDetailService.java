package com.zd.csms.zxbank.service;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.zxbank.bean.PushNoticeDetail;
import com.zd.csms.zxbank.dao.IPushNoticeDetailDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class PushNoticeDetailService extends ServiceSupport {
	private IPushNoticeDetailDAO pndd = ZXBankDAOFactory.getPushNoticeDetailDAO();
	
	public List<PushNoticeDetail> findNotice(PushNoticeDetail query, IThumbPageTools tools) {
		return pndd.findNotice(query, tools);
	}

	public boolean add(PushNoticeDetail pnd) {
		pnd.setId(SqlUtil.getID(PushNoticeDetail.class));
		return pndd.add(pnd);
	}
	public List<PushNoticeDetail> findPushNoticeDetail() {
		return pndd.findPushNoticeDetail();
	}
	
	

	public Object draftsOptions() {
		List<OptionObject> options = new ArrayList<OptionObject>();
		OptionObject option;
		List<String> drafts = pndd.getListNtcno();
		if(drafts != null && drafts.size() > 0){
			for(String draftNum : drafts){
				option = new OptionObject(draftNum,draftNum);
				options.add(option);
			}
		}
		return options;
	}
}
