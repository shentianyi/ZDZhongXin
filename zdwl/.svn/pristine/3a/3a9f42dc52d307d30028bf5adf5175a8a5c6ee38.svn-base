package com.zd.csms.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.finance.dao.FinanceDAOFactory;
import com.zd.csms.finance.dao.IOpenInvoiceDAO;
import com.zd.csms.finance.dao.mapper.OpenInvoiceMapper;
import com.zd.csms.finance.model.OpenInvoiceQueryVO;
import com.zd.csms.finance.model.OpenInvoiceVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class OpenInvoiceService extends ServiceSupport {

	private IOpenInvoiceDAO dao = FinanceDAOFactory.getOpenInvoiceDAO();
	
	public boolean addOpenInvoice(OpenInvoiceVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(OpenInvoiceVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updOpenInvoice(OpenInvoiceVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteOpenInvoice(int id) throws Exception {
		return dao.delete(OpenInvoiceVO.class, id);
    }
	
	public OpenInvoiceVO loadOpenInvoiceById(int id) throws Exception{
		OpenInvoiceVO openInvoice = dao.get(OpenInvoiceVO.class, id,new OpenInvoiceMapper());
		return openInvoice;
	}
	
	public List<OpenInvoiceVO> searchOpenInvoiceList(OpenInvoiceQueryVO query){
		return dao.searchOpenInvoiceList(query);
	}
	
	public List<OpenInvoiceVO> searchOpenInvoiceListByPage(OpenInvoiceQueryVO vo, IThumbPageTools tools){
		return dao.searchOpenInvoiceListByPage(vo, tools);
	}
}
