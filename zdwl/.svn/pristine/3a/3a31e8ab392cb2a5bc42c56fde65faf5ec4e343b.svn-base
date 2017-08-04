package com.zd.csms.marketing.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.marketing.model.ModeChangeLogVO;
import com.zd.csms.marketing.model.ModeChangeQueryVO;
import com.zd.csms.marketing.model.ModeChangeVO;
import com.zd.csms.marketing.querybean.ModeChangeQueryBean;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 操作模式变更流转DAO接口
 * @author licheng
 *
 */
public interface IModeChangeDAO extends IDAO{
	/**
	 * 监管模式变更单 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<ModeChangeQueryBean> findList(ModeChangeQueryVO query,IThumbPageTools tools);

	public List<ModeChangeVO> selectLastTwoModeChangeByDearlerId(int dearlerId);

	public int addModeChangeLog(ModeChangeLogVO mcl);

/*	
	*//**
	 * 添加变更日志
	 * @number:	
	 * @author:		sxs
	 *//*
	public void addRecordModeChangeLogVO(ModeChangeLogVO mcl);*/
}
