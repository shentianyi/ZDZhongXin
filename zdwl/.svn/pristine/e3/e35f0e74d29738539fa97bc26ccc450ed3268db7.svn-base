package com.zd.csms.message.dao;


import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.message.model.NoticeQueryVO;
import com.zd.csms.message.model.NoticeVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface INoticeDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<NoticeVO> searchNoticeList(NoticeQueryVO query);
	public List<NoticeVO> searchNoticeListByPage(NoticeQueryVO query, IThumbPageTools tools);
	
	/**
	 * 批量新增
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public boolean addBatch(final List<NoticeVO> list) throws Exception;
	boolean deleteByObj(int objId, int type);
	public List<NoticeVO> systemList(NoticeQueryVO query);
	public List<NoticeVO> searchNoticeAllList(NoticeQueryVO query);
}
