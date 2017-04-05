package com.zd.csms.zxbank.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.*;
import com.zd.csms.zxbank.dao.*;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 解除质押通知业务层
 * @author duyong
 *
 */
public class MoveDetailService extends ServiceSupport{

	private IMoveDetailDAO dao = ZXBankDAOFactory.getMoveDetailDAO();
	
	public List<MoveDetail> findByQuery(MoveDetail query, IThumbPageTools tools) {
		return dao.findByQuery(query, tools);
	}
	
}
