package com.zd.csms.marketing.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.dao.ISupervisePayDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.dao.mapper.SupervisePayMapper;
import com.zd.csms.marketing.model.SupervisePayQueryVO;
import com.zd.csms.marketing.model.SupervisePayVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class SupervisePayService extends ServiceSupport {

	private ISupervisePayDAO dao = MarketFactory.getSupervisePayDAO();
	
	public boolean addSupervisePay(SupervisePayVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(SupervisePayVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updSupervisePay(SupervisePayVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteSupervisePay(int id) throws Exception {
		return dao.delete(SupervisePayVO.class, id);
    }
	
	public SupervisePayVO loadSupervisePayById(int id) throws Exception{
		SupervisePayVO supervisePay = dao.get(SupervisePayVO.class, id,new SupervisePayMapper());
		return supervisePay;
	}
	
	public List<SupervisePayVO> searchSupervisePayList(SupervisePayQueryVO query){
		return dao.searchSupervisePayList(query);
	}
	
	public List<SupervisePayVO> searchSupervisePayListByPage(SupervisePayQueryVO vo, IThumbPageTools tools){
		return dao.searchSupervisePayListByPage(vo, tools);
	}
}
