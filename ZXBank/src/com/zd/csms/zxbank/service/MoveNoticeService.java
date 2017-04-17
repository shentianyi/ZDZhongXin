package com.zd.csms.zxbank.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.*;
import com.zd.csms.zxbank.dao.*;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 解除质押通知业务层
 *
 */
public class MoveNoticeService extends ServiceSupport {

	private IMoveNoticeDAO dao = ZXBankDAOFactory.getMoveNoticeDAO();

	public List<MoveNotice> findByQuery(MoveNotice query, IThumbPageTools tools) {
		return dao.findByQuery(query, tools);
	}

	public MoveNotice fingByNo(String no) {
		return dao.fingByNo(no);
	}
}
