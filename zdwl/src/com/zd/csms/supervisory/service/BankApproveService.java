package com.zd.csms.supervisory.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.supervisory.dao.IBankApproveDAO;
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.csms.supervisory.dao.mapper.BankApproveMapper;
import com.zd.csms.supervisory.model.BankApproveQueryVO;
import com.zd.csms.supervisory.model.BankApproveVO;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.querybean.BankApproveQueryBean;
import com.zd.csms.supervisory.querybean.CarSummary;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class BankApproveService extends ServiceSupport {

	private IBankApproveDAO dao = SupervisorDAOFactory.getBankApproveDAO();
	
	public boolean addBankApprove(BankApproveVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(BankApproveVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updBankApprove(BankApproveVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteBankApprove(int id) throws Exception {
		return dao.delete(BankApproveVO.class, id);
    }
	
	public BankApproveVO loadBankApproveById(int id) throws Exception{
		BankApproveVO bankApprove = dao.get(BankApproveVO.class, id,new BankApproveMapper());
		return bankApprove;
	}
	
	public List<BankApproveVO> searchBankApproveList(BankApproveQueryVO query){
		return dao.searchBankApproveList(query);
	}
	
	public List<BankApproveQueryBean> searchBankApproveListByPage(BankApproveQueryVO vo, IThumbPageTools tools){
		return dao.searchBankApproveListByPage(vo, tools);
	}
	public CarSummary getSummaryByBank(BankApproveQueryVO query) {
		return dao.getSummaryByBank(query);
	}
	
	public BankApproveVO searchBankApproveBySid(int sid,int type){
		return dao.searchBankApproveBySid(sid,type);
	}
	
}
