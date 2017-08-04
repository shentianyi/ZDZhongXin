package com.zd.csms.business.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.business.dao.BusinessDAOFactory;
import com.zd.csms.business.dao.INoteDAO;
import com.zd.csms.business.dao.mapper.NoteMapper;
import com.zd.csms.business.model.NoteQueryVO;
import com.zd.csms.business.model.NoteVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class NoteService extends ServiceSupport {

	private INoteDAO dao = BusinessDAOFactory.getNoteDAO();
	
	public boolean addNote(NoteVO vo,String content) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(NoteVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updNote(NoteVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteNote(int id) throws Exception {
		return dao.delete(NoteVO.class, id);
    }
	
	public NoteVO get(int id) throws Exception{
		return dao.get(NoteVO.class, id, new NoteMapper());
	}
	
	public List<NoteVO> searchNoteList(NoteQueryVO query){
		return dao.searchNoteList(query);
	}

	public List<NoteVO> searchNoteListByPage(NoteQueryVO query, IThumbPageTools tools){
		return dao.searchNoteListByPage(query, tools);
	}


}
