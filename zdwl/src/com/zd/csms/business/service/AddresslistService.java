package com.zd.csms.business.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.business.dao.BusinessDAOFactory;
import com.zd.csms.business.dao.IAddresslistDAO;
import com.zd.csms.business.dao.mapper.AddresslistMapper;
import com.zd.csms.business.model.AddresslistQueryVO;
import com.zd.csms.business.model.AddresslistVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class AddresslistService extends ServiceSupport {

	private IAddresslistDAO dao = BusinessDAOFactory.getAddresslistDAO();
	
	public boolean addAddresslist(AddresslistVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(AddresslistVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updAddresslist(AddresslistVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteAddresslist(int id) throws Exception {
		return dao.delete(AddresslistVO.class, id);
    }
	
	public AddresslistVO loadAddresslistById(int id) throws Exception{
		AddresslistVO addresslist = dao.get(AddresslistVO.class, id,new AddresslistMapper());
		return addresslist;
	}
	
	public List<AddresslistVO> searchAddressList(AddresslistQueryVO query){
		return dao.searchAddressList(query);
	}
	
	public List<AddresslistVO> searchAddressListByPage(AddresslistQueryVO vo, IThumbPageTools tools){
		return dao.searchAddressListByPage(vo, tools);
	}
}
