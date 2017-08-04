package com.zd.csms.marketing.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.dao.IMarketApprovalDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.querybean.ApprovalQueryBean;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 市场部审批表
 * @author licheng
 *
 */

public class MarketApprovalSerivce extends ServiceSupport{
	IMarketApprovalDAO marketApprovalDao = MarketFactory.getMarketApprovalDAO();
	
	public boolean add(MarketApprovalVO marketApproval){
		try {
			marketApproval.setId(Util.getID(MarketApprovalVO.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return marketApprovalDao.add(marketApproval);
	}
	
	public boolean update(MarketApprovalVO marketApproval){
		//MarketApprovalVO newBean = marketApprovalDao
		//		.get(MarketApprovalVO.class,marketApproval.getId(),new BeanPropertyRowMapper(MarketApprovalVO.class));
		return marketApprovalDao.add(marketApproval);
	}
	
	public boolean delete(int id){
		return marketApprovalDao.delete(MarketApprovalVO.class, id);
	}
	
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<MarketApprovalVO> findList(MarketApprovalQueryVO query,IThumbPageTools tools){
		return marketApprovalDao.findList(query, tools);
	}
	
	/**
	 * 根据表单类型和目标ID查询审批记录
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<ApprovalQueryBean> findListByApprovalType(MarketApprovalQueryVO query) throws Exception{
		return marketApprovalDao.findListByApprovalType(query);
	}
}
