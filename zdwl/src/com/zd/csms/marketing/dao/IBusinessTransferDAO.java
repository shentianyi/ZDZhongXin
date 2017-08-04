package com.zd.csms.marketing.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.marketing.model.BusinessTransferQueryVO;
import com.zd.csms.marketing.model.BusinessTransferVO;
import com.zd.csms.marketing.querybean.BusinessTransferQueryBean;
import com.zd.csms.marketing.querybean.SupervisorJsonBean;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IBusinessTransferDAO extends IDAO{
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<BusinessTransferQueryBean> findList(BusinessTransferQueryVO query,IThumbPageTools tools);
	
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<BusinessTransferQueryBean> findAllList(BusinessTransferQueryVO query,IThumbPageTools tools);
	public List<BusinessTransferQueryBean> findAllList(BusinessTransferQueryVO query);
	
	/**
	 * 根据储备库Id获取有效的监管员信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SupervisorJsonBean getSupervisorByRepositoryId(int id) throws Exception;
	
	/**
	 * 发送进店流转单未按时进驻提醒
	 * @return
	 */
	public List<BusinessTransferVO> findListByWarring();
}
