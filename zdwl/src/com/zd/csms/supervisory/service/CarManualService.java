package com.zd.csms.supervisory.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.supervisory.dao.ICarManualDAO;
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.csms.supervisory.dao.mapper.CarManualMapper;
import com.zd.csms.supervisory.model.CarManualQueryVO;
import com.zd.csms.supervisory.model.CarManualVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class CarManualService extends ServiceSupport {

	private ICarManualDAO dao = SupervisorDAOFactory.getCarManualDAO();
	
	public boolean addCarManual(CarManualVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(CarManualVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updCarManual(CarManualVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteCarManual(int id) throws Exception {
		return dao.delete(CarManualVO.class, id);
    }
	
	public CarManualVO loadCarManualById(int id) throws Exception{
		CarManualVO carManual = dao.get(CarManualVO.class, id,new CarManualMapper());
		return carManual;
	}
	
	public List<CarManualVO> searchCarManualList(CarManualQueryVO query){
		return dao.searchCarManualList(query);
	}
	
	public List<CarManualVO> searchCarManualListByPage(CarManualQueryVO vo, IThumbPageTools tools){
		return dao.searchCarManualListByPage(vo, tools);
	}
}
