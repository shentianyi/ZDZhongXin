package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.Notice;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface INoticeDAO extends IDAO{
	
	public String getDataSourceName();
	/**
	 * 分页查询
	 */
	public List<Notice> findNotice(Notice notice,IThumbPageTools tools);
	/**
	 *  查询所有
	 */
	public List<Notice> findnoticetype();
	/**
	 * 根据条件查询单个
	 */
	public Notice getNotice(Notice notice);
	/**
	 * 更新
	 */
	public boolean update(Notice notice);
	
	public List<String> getListNtcno();
}
