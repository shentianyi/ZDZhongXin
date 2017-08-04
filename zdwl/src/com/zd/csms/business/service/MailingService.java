package com.zd.csms.business.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.business.dao.BusinessDAOFactory;
import com.zd.csms.business.dao.IMailingDAO;
import com.zd.csms.business.dao.mapper.MailingMapper;
import com.zd.csms.business.model.MailingQueryVO;
import com.zd.csms.business.model.MailingVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class MailingService extends ServiceSupport {

	private IMailingDAO dao = BusinessDAOFactory.getMailingDAO();
	
	public boolean addMailing(MailingVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(MailingVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updMailing(MailingVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteMailing(int id) throws Exception {
		return dao.delete(MailingVO.class, id);
    }
	
	public MailingVO loadMailingById(int id) throws Exception{
		MailingVO mailing = dao.get(MailingVO.class, id,new MailingMapper());
		return mailing;
	}
	
	public List<MailingVO> searchMailingList(MailingQueryVO query){
		return dao.searchMailingList(query);
	}
	
	public List<MailingVO> searchMailingListByPage(MailingQueryVO vo, IThumbPageTools tools){
		return dao.searchMailingListByPage(vo, tools);
	}
}
