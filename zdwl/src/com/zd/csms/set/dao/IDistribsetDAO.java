package com.zd.csms.set.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.set.model.DistribsetQueryVO;
import com.zd.csms.set.model.DistribsetVO;
import com.zd.csms.set.model.ExtendDistribsetVO;

public interface IDistribsetDAO extends IDAO {

	public String getDataSourceName();

	/**
	 * 按条件查询资源集合
	 * 
	 * @param query
	 *            查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<DistribsetVO> searchDistribsetList(DistribsetQueryVO query);

	/**
	 * 根据客户号获取经销商ID
	 * 
	 * @param custNo
	 * @return
	 */
	public int findDistidByCustNo(String custNo);

	/**
	 * 根据组织机构代码获取经销商ID
	 * 
	 * @param orgcode
	 * @return
	 */
	public int findDistidByOrg(String orgcode);

	public DistribsetVO getDistribsetVOByDistribid(int id);

	/**
	 * 根据客户号同步合同编号
	 * 
	 * @param contractNo
	 * @param zsCustNo
	 * @return
	 */
	public boolean updateContractNoByZsCustNo(String contractNo, String zsCustNo);

	/**
	 * 验证此经销商是否是对接银行
	 * 
	 * @return
	 */
	public boolean validateDealer(int dealerId);

	// 需求167
	public DealerVO getDealerEquipmentProvideAndCredit(int dealerId);

	public boolean updDealerEquipmentProvideAndCredit(DealerVO query);

	/**
	 * 获取所有客户的组织机构代码
	 * 
	 * @return
	 */
	public List<String> getListZxOrgCode();

}
