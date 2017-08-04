package com.zd.csms.business.service;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.ServiceSupport;
import com.zd.csms.business.dao.BusinessDAOFactory;
import com.zd.csms.business.dao.IYwBankDao;
import com.zd.csms.business.model.TwoAddressVO;
import com.zd.csms.business.model.YwBankBrandVO;
import com.zd.csms.business.model.YwBankQueryBean;
import com.zd.csms.business.model.YwBankQueryVO;
import com.zd.csms.business.model.YwBankVO;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;

public class YwBankService extends ServiceSupport{
	private IYwBankDao dao = BusinessDAOFactory.getYwBankDao();
	
	public boolean add(YwBankVO vo){
		try {
			vo.setId(Util.getID(YwBankVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.add(vo);
	}
	
	public boolean update(YwBankVO vo){
		return dao.update(vo);
	}
	
	public boolean delete(int id){
		dao.delete(YwBankVO.class, id);
		dao.deleteBrandById(id);
		return true;
	}
	
	public YwBankVO get(int id){
		return dao.get(YwBankVO.class, id, new BeanPropertyRowMapper(YwBankVO.class));
	}
	
	/**
	 * 查询列表
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<YwBankQueryBean> findList(YwBankQueryVO query, IThumbPageTools tools){
		return dao.findList(query, tools);
	}
	
	public List<UserVO> ywUserList(YwBankQueryVO query, IThumbPageTools tools){
		return dao.ywUserList(query, tools);
	}
	
	public List<BrandVO> brandList(YwBankQueryVO query, IThumbPageTools tools){
		return dao.brandList(query, tools);
	}
	
	public boolean addBrand(YwBankBrandVO vo){
		try {
			vo.setId(Util.getID(YwBankBrandVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.add(vo);
		
	}
	
	public boolean deleteBrand(String[] ids,int id){
		for (String brandId : ids) {
			dao.deleteBrand(Integer.parseInt(brandId), id);
		}
		return true;
	}
	
	public boolean deleteBrandByYwBankId(int ywId){
		
		return true;
	}
	
	public List<TwoAddressVO> searchTwoAddress(int userid){
		return dao.searchTwoAddress(userid);
	}
	
	//根据银行id查询对应的专员
	public String findUserIdByBank(int bankId){
		return dao.findUserIdByBank(bankId);
	}
	
}
