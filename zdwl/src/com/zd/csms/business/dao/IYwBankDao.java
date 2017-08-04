package com.zd.csms.business.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.business.model.TwoAddressVO;
import com.zd.csms.business.model.YwBankQueryBean;
import com.zd.csms.business.model.YwBankQueryVO;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IYwBankDao extends IDAO{	
	/**
	 * 查询列表
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<YwBankQueryBean> findList(YwBankQueryVO query, IThumbPageTools tools);
	
	public List<UserVO> ywUserList(YwBankQueryVO query, IThumbPageTools tools);
	
	public List<BrandVO> brandList(YwBankQueryVO query, IThumbPageTools tools);
	
	public boolean deleteBrand(int brandId,int id);
	public boolean deleteBrandById(int id);
	
	public List<TwoAddressVO> searchTwoAddress(int userid);

	public String findUserIdByBank(int bankId);
}
