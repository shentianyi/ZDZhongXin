package com.zd.csms.marketing.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.dao.IBrandDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.dao.mapper.BrandMapper;
import com.zd.csms.marketing.model.BrandQueryVO;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class BrandService extends ServiceSupport {

	private IBrandDAO dao = MarketFactory.getBrandDAO();
	
	public boolean addBrand(BrandVO vo) throws Exception {
		
		boolean flag = false;
		if(validateBrandName(vo.getName())){
			setExecuteMessage("品牌名已存在");
			return flag;
		}
		vo.setId(Util.getID(BrandVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updBrand(BrandVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteBrand(int id) throws Exception {
		return dao.delete(BrandVO.class, id);
    }
	
	public BrandVO loadBrandById(int id) throws Exception{
		BrandVO brand = dao.get(BrandVO.class, id,new BrandMapper());
		return brand;
	}
	
	public List<BrandVO> searchBrandList(BrandQueryVO query){
		return dao.searchBrandList(query);
	}
	
	public List<BrandVO> searchBrandListByPage(BrandQueryVO vo, IThumbPageTools tools){
		return dao.searchBrandListByPage(vo, tools);
	}
	
	/**
	 * 验证品牌名是否存在，返回true为不存在，返回false为存在
	 * @param name
	 * @return
	 */
	public boolean validateBrandName(String name){
		return dao.validateBrandName(name);
	}
}
