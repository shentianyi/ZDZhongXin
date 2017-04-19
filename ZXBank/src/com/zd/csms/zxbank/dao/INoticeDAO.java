package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.Notice;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface INoticeDAO extends IDAO{
	//查询
	public List<Notice> findNotice(Notice notice,IThumbPageTools tools);
	//查询通知类型
	public List<Notice> findnoticetype();
	
	public Notice getNotice(Notice notice);
	
	public boolean update(Notice notice);
}
