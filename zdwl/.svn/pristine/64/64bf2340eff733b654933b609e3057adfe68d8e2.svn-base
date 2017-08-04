package com.zd.csms.marketing.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.dao.IAgreementBackDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.dao.mapper.AgreementBackMapper;
import com.zd.csms.marketing.model.AgreementBackQueryVO;
import com.zd.csms.marketing.model.AgreementBackVO;
import com.zd.csms.marketing.querybean.AgreementQueryBean;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;

public class AgreementBackService extends ServiceSupport {

	private IAgreementBackDAO dao = MarketFactory.getAgreementBackDAO();
	
	public boolean addAgreementBack(AgreementBackVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(AgreementBackVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updAgreementBack(AgreementBackVO vo) throws Exception {
		AgreementBackVO temp =  loadAgreementBackById(vo.getId());
		vo.setCreatedate(temp.getCreatedate());
		vo.setCreateuserid(temp.getCreateuserid());
		vo.setBack_date(temp.getBack_date());
		return dao.update(vo);
	}
	
	public boolean deleteAgreementBack(int id) throws Exception {
		return dao.delete(AgreementBackVO.class, id);
    }
	
	public AgreementBackVO loadAgreementBackById(int id) throws Exception{
		AgreementBackVO agreementBack = dao.get(AgreementBackVO.class, id,new AgreementBackMapper());
		return agreementBack;
	}
	
	public List<AgreementBackVO> searchAgreementBackList(AgreementBackQueryVO query){
		return dao.searchAgreementBackList(query);
	}
	
	public List<AgreementQueryBean> searchAgreementBackListByPage(AgreementBackQueryVO vo, IThumbPageTools tools){
		return dao.searchAgreementBackListByPage(vo, tools);
	}

	/*
	 * 需求38 -- 导出协议管理台账
	 * @time 20170518
	*/
	public List<AgreementQueryBean> ExtAgreementLedger(AgreementBackQueryVO asQuery) {
		return dao.ExtAgreementLedger(asQuery);
	}

	/*
	 * 更改协议管理台账 回收时间 和 回收状态
	 * @time 20170518
	*/
	public boolean updBackDateAndIsback(AgreementBackVO abvo) {
		return dao.updBackDateAndIsback(abvo);
	}
}
