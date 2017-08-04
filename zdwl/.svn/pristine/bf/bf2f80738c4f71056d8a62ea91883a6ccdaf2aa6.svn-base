package com.zd.csms.supervisorymanagement.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.service.SuperviseArrearsService;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.service.SupervisoryService;
import com.zd.csms.supervisorymanagement.dao.IFixedAssetListDAO;
import com.zd.csms.supervisorymanagement.dao.SupervisoryManagementDAOFactory;
import com.zd.csms.supervisorymanagement.dao.mapper.FixedAssetListMapper;
import com.zd.csms.supervisorymanagement.model.FixedAssetListQueryVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetListVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class FixedAssetListService extends ServiceSupport {

	private IFixedAssetListDAO dao = SupervisoryManagementDAOFactory.getFixedAssetListDAO();
	
	public int addFixedAssetList(FixedAssetListVO vo) throws Exception {
		
		int id = 0;
		
		id = Util.getID(FixedAssetListVO.class);
		
		vo.setId(id);
		
		dao.add(vo);
		
		return id;
	}
	
	public boolean updFixedAssetList(FixedAssetListVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteFixedAssetList(int id) throws Exception {
		return dao.delete(FixedAssetListVO.class, id);
    }
	
	public FixedAssetListVO loadFixedAssetListById(int id) throws Exception{
		FixedAssetListVO fixedAssetList = dao.get(FixedAssetListVO.class, id,new FixedAssetListMapper());
		return fixedAssetList;
	}
	
	public List<FixedAssetListVO> searchFixedAssetList(FixedAssetListQueryVO query){
		return dao.searchFixedAssetList(query);
	}
	
	public List<FixedAssetListVO> searchFixedAssetListByPage(FixedAssetListQueryVO vo, IThumbPageTools tools){
		return dao.searchFixedAssetListByPage(vo, tools);
	}

	/**
	 * 
	 * @number:		63
	 * @author:		sxs
	 * @describe:
	 * @param:
	 * @return:
	 */
	public List<FixedAssetListVO> searchfixedAssetListByClientId(int client_id) {
		return dao.searchfixedAssetListByClientId(client_id);
	}
	
	public List<FixedAssetListVO> searchfixedAssetListByUserName(int username){
		return dao.searchfixedAssetListByUserName(username);
	}



}
