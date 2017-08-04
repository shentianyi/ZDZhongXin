package com.zd.csms.file.service;

import com.zd.core.ServiceSupport;
import com.zd.csms.file.dao.FileDAOFactory;
import com.zd.csms.file.dao.IUploadfileDAO;
import com.zd.csms.file.dao.mapper.UploadfileMapper;
import com.zd.csms.file.model.UploadfileVO;
import com.zd.csms.util.Util;

public class UploadfileService extends ServiceSupport {

	private IUploadfileDAO dao = FileDAOFactory.getUploadfileDAO();
	
	public int addUploadfile(UploadfileVO vo) throws Exception {
		
		int id = Util.getID(UploadfileVO.class);
		
		vo.setId(id);
		
		dao.add(vo);
		
		return id;
	}
	
	public boolean deleteUploadfile(int id) throws Exception {
		return dao.delete(UploadfileVO.class, id);
    }
	
	public UploadfileVO loadUploadfileById(int id) throws Exception{
		UploadfileVO uploadfile = dao.get(UploadfileVO.class, id,new UploadfileMapper());
		return uploadfile;
	}

	
}
