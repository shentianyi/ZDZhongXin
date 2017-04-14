package com.zd.csms.zxbank.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.RemovePledge;
import com.zd.csms.zxbank.dao.IRemovePledgeDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 解除质押通知业务层
 * @author duyong
 *
 */
public class RemovePledgeService extends ServiceSupport{

	private IRemovePledgeDAO dao = ZXBankDAOFactory.getRemovePledgeDAO();
	
	public List<RemovePledge> findByQuery(RemovePledge query, IThumbPageTools tools) {
		return dao.findByQuery(query, tools);
	}
	
	public RemovePledge fingByNo(String no){
		return dao.fingByNo(no);
	}
}
