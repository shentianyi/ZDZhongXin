package com.zd.csms.marketing.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.marketing.dao.IDealerSupervisorDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.dao.mapper.DealerSupervisorMapper;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;


public class DealerSupervisorService extends ServiceSupport {

	private IDealerSupervisorDAO dao = MarketFactory.getDealerSupervisorDAO();
	
	public DealerSupervisorVO loadDealerSupervisorById(int id) throws Exception{
		DealerSupervisorVO dealerSupervisor = dao.get(DealerSupervisorVO.class, id,new DealerSupervisorMapper());
		return dealerSupervisor;
	}
	
	public List<DealerSupervisorVO> searchDealerSupervisorList(DealerSupervisorQueryVO query){
		return dao.searchDealerSupervisorList(query);
	}
	
	public List<DraftVO> searchDraftSupervisorList(int id){
		return dao.searchDraftSupervisorList(id);
	}
	
	public boolean updateRepoByDealerId(int dealerId,int repoId){
		return dao.updateRepoByDealerId(dealerId, repoId);
	}
	public boolean update(DealerSupervisorVO vo) throws Exception{
		boolean flag=dao.update(vo);
		return flag;
	}
}
