package com.zd.csms.supervisorymanagement.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.supervisorymanagement.dao.ISystemSuperviseDAO;
import com.zd.csms.supervisorymanagement.dao.SupervisoryManagementDAOFactory;
import com.zd.csms.supervisorymanagement.dao.mapper.SystemSuperviseMapper;
import com.zd.csms.supervisorymanagement.model.SystemSuperviseQueryVO;
import com.zd.csms.supervisorymanagement.model.SystemSuperviseVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class SystemSuperviseService extends ServiceSupport {

	private ISystemSuperviseDAO dao = SupervisoryManagementDAOFactory.getSystemSuperviseDAO();
	
	public boolean addSystemSupervise(SystemSuperviseVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(SystemSuperviseVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updSystemSupervise(SystemSuperviseVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteSystemSupervise(int id) throws Exception {
		return dao.delete(SystemSuperviseVO.class, id);
    }
	
	public SystemSuperviseVO loadSystemSuperviseById(int id) throws Exception{
		SystemSuperviseVO systemSupervise = dao.get(SystemSuperviseVO.class, id,new SystemSuperviseMapper());
		return systemSupervise;
	}
	
	public List<SystemSuperviseVO> searchSystemSuperviseList(SystemSuperviseQueryVO query){
		return dao.searchSystemSuperviseList(query);
	}
	
	public List<SystemSuperviseVO> searchSystemSuperviseListByPage(SystemSuperviseQueryVO vo, IThumbPageTools tools){
		return dao.searchSystemSuperviseListByPage(vo, tools);
	}
}
