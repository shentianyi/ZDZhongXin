package com.zd.csms.supervisory.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.supervisory.dao.IActualAddressDAO;
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.csms.supervisory.dao.mapper.ActualAddressMapper;
import com.zd.csms.supervisory.model.ActualAddressQueryVO;
import com.zd.csms.supervisory.model.ActualAddressVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class ActualAddressService extends ServiceSupport {

	private IActualAddressDAO dao = SupervisorDAOFactory.getActualAddressDAO();
	
	public boolean addActualAddress(ActualAddressVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(ActualAddressVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updActualAddress(ActualAddressVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteActualAddress(int id) throws Exception {
		return dao.delete(ActualAddressVO.class, id);
    }
	
	public ActualAddressVO loadActualAddressById(int id) throws Exception{
		ActualAddressVO draft = dao.get(ActualAddressVO.class, id,new ActualAddressMapper());
		return draft;
	}
	
	public List<ActualAddressVO> searchActualAddressList(ActualAddressQueryVO query){
		return dao.searchActualAddressList(query);
	}
	
	public List<ActualAddressVO> searchActualAddressListByPage(ActualAddressQueryVO vo, IThumbPageTools tools){
		return dao.searchActualAddressListByPage(vo, tools);
	}
	
	public List<String> findAllIds(UserVO user,int type) {
		return dao.findAllIds(user,type);
	}
}
