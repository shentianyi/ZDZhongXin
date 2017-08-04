package com.zd.csms.marketing.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.dao.ISuperviseArrearsDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.dao.mapper.SuperviseArrearsMapper;
import com.zd.csms.marketing.model.SuperviseArrearsQueryVO;
import com.zd.csms.marketing.model.SuperviseArrearsVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class SuperviseArrearsService extends ServiceSupport {

	private ISuperviseArrearsDAO dao = MarketFactory.getSuperviseArrearsDAO();
	
	public boolean addSuperviseArrears(SuperviseArrearsVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(SuperviseArrearsVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updSuperviseArrears(SuperviseArrearsVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteSuperviseArrears(int id) throws Exception {
		return dao.delete(SuperviseArrearsVO.class, id);
    }
	
	public SuperviseArrearsVO loadSuperviseArrearsById(int id) throws Exception{
		SuperviseArrearsVO superviseArrears = dao.get(SuperviseArrearsVO.class, id,new SuperviseArrearsMapper());
		return superviseArrears;
	}
	
	public List<SuperviseArrearsVO> searchSuperviseArrearsList(SuperviseArrearsQueryVO query){
		return dao.searchSuperviseArrearsList(query);
	}
	
	public List<SuperviseArrearsVO> searchSuperviseArrearsListByPage(SuperviseArrearsQueryVO vo, IThumbPageTools tools){
		return dao.searchSuperviseArrearsListByPage(vo, tools);
	}
}
