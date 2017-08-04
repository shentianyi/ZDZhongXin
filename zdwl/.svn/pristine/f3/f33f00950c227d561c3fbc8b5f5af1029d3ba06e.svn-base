package com.zd.csms.supervisory.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisory.model.BankApproveQueryVO;
import com.zd.csms.supervisory.model.BankApproveVO;
import com.zd.csms.supervisory.querybean.BankApproveQueryBean;
import com.zd.csms.supervisory.querybean.CarSummary;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IBankApproveDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<BankApproveVO> searchBankApproveList(BankApproveQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<BankApproveQueryBean> searchBankApproveListByPage(BankApproveQueryVO query,IThumbPageTools tools);
	
	/**
	 * 根据车ID查询资源对象
	 * @param vin
	 * @return
	 */
	public BankApproveVO searchBankApproveBySid(int vin,int type);
	
	/**
	 * 
	 * @param query
	 * @return
	 */
	public CarSummary getSummaryByBank(BankApproveQueryVO query);
}
