package com.zd.csms.zxbank.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.RemovePledgeDetail;
import com.zd.csms.zxbank.dao.IRemovePledgeDetailDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 解除质押通知业务层
 * @author duyong
 *
 */
public class RemovePledgeDetailService extends ServiceSupport{

	private IRemovePledgeDetailDAO dao = ZXBankDAOFactory.getRemovePledgeDetailDAO();
	
	public List<RemovePledgeDetail> findByQuery(RemovePledgeDetail query, IThumbPageTools tools) {
		return dao.findByQuery(query, tools);
	}
	
	public List<RemovePledgeDetail> findAll(String no) {
		return dao.findAll(no);
	}
}
