package com.zd.csms.business.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.business.dao.BusinessDAOFactory;
import com.zd.csms.business.dao.ITwoAddressDAO;
import com.zd.csms.business.dao.mapper.TwoAddressMapper;
import com.zd.csms.business.model.ExtTwoAddressVO;
import com.zd.csms.business.model.TwoAddressEVO;
import com.zd.csms.business.model.TwoAddressQueryVO;
import com.zd.csms.business.model.TwoAddressVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class TwoAddressService extends ServiceSupport {

	private ITwoAddressDAO dao = BusinessDAOFactory.getTwoAddressDAO();
	
	public boolean addTwoAddress(TwoAddressVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(TwoAddressVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updTwoAddress(TwoAddressVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteTwoAddress(int id) throws Exception {
		return dao.delete(TwoAddressVO.class, id);
    }
	
	public TwoAddressVO loadTwoAddressById(int id) throws Exception{
		TwoAddressVO draft = dao.get(TwoAddressVO.class, id,new TwoAddressMapper());
		return draft;
	}
	
	public List<TwoAddressVO> searchTwoAddressList(TwoAddressQueryVO query){
		return dao.searchTwoAddressList(query);
	}
	
	public List<TwoAddressVO> searchTwoAddressListByPage(TwoAddressQueryVO vo, IThumbPageTools tools){
		return dao.searchTwoAddressListByPage(vo, tools);
	}

	/*
	 * 需求90 -- 导出监管场地
	 * @time 20170519
	*/
	public List<ExtTwoAddressVO> ExtSupervisionSite(TwoAddressQueryVO taQuery) {
		return dao.ExtSupervisionSite(taQuery);
	}
	
	public TwoAddressVO searchBycode(String code){
		return dao.searchByCode(code);
	}
	
	public TwoAddressEVO searchInfo(int did){
		return dao.searchBydid(did);
	}
	
}
