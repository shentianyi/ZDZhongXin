package com.zd.csms.message.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.message.dao.INoticeDAO;
import com.zd.csms.message.dao.MessageDAOFactory;
import com.zd.csms.message.dao.mapper.NoticeContentMapper;
import com.zd.csms.message.model.NoticeQueryVO;
import com.zd.csms.message.model.NoticeVO;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class NoticeService extends ServiceSupport {

	private INoticeDAO dao = MessageDAOFactory.getNoticeDao();
	
	public boolean add(NoticeVO vo,String content) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(NoticeVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updNotice(NoticeVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteNotice(int id,HttpServletRequest request) throws Exception {
		return dao.delete(NoticeVO.class, id);
    }
	
	public NoticeVO get(int id) throws Exception{
		return dao.get(NoticeVO.class, id, new NoticeContentMapper());
	}
	

	public List<NoticeVO> searchNoticeListByPage(NoticeQueryVO query, IThumbPageTools tools){
		return dao.searchNoticeListByPage(query, tools);
	}

	public List<NoticeVO> searchNoticeList(NoticeQueryVO query){
		if(query.getUser().getClient_type() == ClientTypeConstants.SUPERVISORY.getCode()){
			return dao.searchNoticeList(query);
		}else{
			return dao.searchNoticeAllList(query);
		}
	}
	
	public List<NoticeVO> systemList(NoticeQueryVO query){
		return dao.systemList(query);
	}

}
