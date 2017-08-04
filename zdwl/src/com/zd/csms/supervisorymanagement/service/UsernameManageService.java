package com.zd.csms.supervisorymanagement.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.supervisorymanagement.dao.IUsernameManageDAO;
import com.zd.csms.supervisorymanagement.dao.SupervisoryManagementDAOFactory;
import com.zd.csms.supervisorymanagement.dao.mapper.UsernameManageMapper;
import com.zd.csms.supervisorymanagement.model.UsernameManageQueryVO;
import com.zd.csms.supervisorymanagement.model.UsernameManageVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class UsernameManageService extends ServiceSupport {

	private IUsernameManageDAO dao = SupervisoryManagementDAOFactory.getUsernameManageDAO();
	
	public boolean addUsernameManage(UsernameManageVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(UsernameManageVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updUsernameManage(UsernameManageVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteUsernameManage(int id) throws Exception {
		return dao.delete(UsernameManageVO.class, id);
    }
	
	public UsernameManageVO loadUsernameManageById(int id) throws Exception{
		UsernameManageVO usernameManage = dao.get(UsernameManageVO.class, id,new UsernameManageMapper());
		return usernameManage;
	}
	
	public List<UsernameManageVO> searchUsernameManageList(UsernameManageQueryVO query){
		return dao.searchUsernameManageList(query);
	}
	
	public List<UsernameManageVO> searchUsernameManageListByPage(UsernameManageQueryVO vo, IThumbPageTools tools){
		return dao.searchUsernameManageListByPage(vo, tools);
	}
}
