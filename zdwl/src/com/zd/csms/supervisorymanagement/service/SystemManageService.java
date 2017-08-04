package com.zd.csms.supervisorymanagement.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.supervisorymanagement.dao.ISystemManageDAO;
import com.zd.csms.supervisorymanagement.dao.SupervisoryManagementDAOFactory;
import com.zd.csms.supervisorymanagement.dao.mapper.SystemManageMapper;
import com.zd.csms.supervisorymanagement.model.SystemManageQueryVO;
import com.zd.csms.supervisorymanagement.model.SystemManageVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class SystemManageService extends ServiceSupport {

	private ISystemManageDAO dao = SupervisoryManagementDAOFactory.getSystemManageDAO();
	
	public boolean addSystemManage(SystemManageVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(SystemManageVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updSystemManage(SystemManageVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteSystemManage(int id) throws Exception {
		return dao.delete(SystemManageVO.class, id);
    }
	
	public SystemManageVO loadSystemManageById(int id) throws Exception{
		SystemManageVO systemSupervise = dao.get(SystemManageVO.class, id,new SystemManageMapper());
		return systemSupervise;
	}
	
	public List<SystemManageVO> searchSystemManageList(SystemManageQueryVO query){
		return dao.searchSystemManageList(query);
	}
	
	public List<SystemManageVO> searchSystemManageListByPage(SystemManageQueryVO vo, IThumbPageTools tools){
		return dao.searchSystemManageListByPage(vo, tools);
	}
}
