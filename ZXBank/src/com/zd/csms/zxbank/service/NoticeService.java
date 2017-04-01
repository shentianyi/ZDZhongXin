package com.zd.csms.zxbank.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.Notice;
import com.zd.csms.zxbank.dao.INoticeDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.tools.thumbPage.IThumbPageTools;

public class NoticeService extends ServiceSupport{
	private INoticeDAO ndao = ZXBankDAOFactory.getnoticeDAO();
	public List<Notice> findNotice(Notice notice,IThumbPageTools tools){
		return ndao.findNotice(notice, tools);
	}
	//查询通知类型
	public List<Notice> findnoticetype(){
		return ndao.findnoticetype();
	}
}
