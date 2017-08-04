package com.zd.csms.marketing.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.dao.IAgreementSendDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.dao.mapper.AgreementSendMapper;
import com.zd.csms.marketing.model.AgreementSendQueryVO;
import com.zd.csms.marketing.model.AgreementSendVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class AgreementSendService extends ServiceSupport {

	private IAgreementSendDAO dao = MarketFactory.getAgreementSendDAO();
	
	public boolean addAgreementSend(AgreementSendVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(AgreementSendVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updAgreementSend(AgreementSendVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteAgreementSend(int id) throws Exception {
		return dao.delete(AgreementSendVO.class, id);
    }
	
	public AgreementSendVO loadAgreementSendById(int id) throws Exception{
		AgreementSendVO agreementSend = dao.get(AgreementSendVO.class, id,new AgreementSendMapper());
		return agreementSend;
	}
	
	public List<AgreementSendVO> searchAgreementSendList(AgreementSendQueryVO query){
		return dao.searchAgreementSendList(query);
	}
	
	public List<AgreementSendVO> searchAgreementSendListByPage(AgreementSendQueryVO vo, IThumbPageTools tools){
		return dao.searchAgreementSendListByPage(vo, tools);
	}
}
