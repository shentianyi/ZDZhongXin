package com.zd.csms.marketing.dao;

import java.util.Date;
import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.marketing.model.MarketProjectCirculationQueryVO;
import com.zd.csms.marketing.model.MarketProjectCirculationVO;
import com.zd.csms.marketing.querybean.PCListQueryBean;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IMarketProjectCirculationDAO extends IDAO{
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<PCListQueryBean> findList(MarketProjectCirculationQueryVO query,IThumbPageTools tools);
	
	/**
	 * 根据经销商主键ID获取入驻信息
	 * @param dealerId
	 * @return
	 */
	public MarketProjectCirculationVO getByDealerId(int dealerId);
	
	/**
	 * 验证表单是否重复
	 * @param dealerName
	 * @param bankid
	 * @return
	 */
	public int validateRepeat(String dealerName,int bankid);
	/**
	 * 查询项目进驻流转单发出五天的信息，消息类型为 1查询当天为创建五天的信息，消息类型为 2查询当天为创建五天以上的信息
	 * @param createDate
	 * @param msgType 消息类型 1：信息提醒，2：信息预警
	 * @return
	 */
	public List<MarketProjectCirculationVO> findListByCreateDate(Date createDate,int msgType);

	public List<PCListQueryBean> findmarketProjectListLedger(
			MarketProjectCirculationQueryVO query, IThumbPageTools tools);
}
