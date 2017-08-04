package com.zd.csms.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.finance.dao.FinanceDAOFactory;
import com.zd.csms.finance.dao.IOpenInvoiceListDAO;
import com.zd.csms.finance.dao.mapper.OpenInvoiceListMapper;
import com.zd.csms.finance.model.OpenInvoiceListQueryVO;
import com.zd.csms.finance.model.OpenInvoiceListVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class OpenInvoiceListService extends ServiceSupport {

	private IOpenInvoiceListDAO dao = FinanceDAOFactory.getOpenInvoiceListDAO();
	
	public boolean addOpenInvoiceList(OpenInvoiceListVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(OpenInvoiceListVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updOpenInvoiceList(OpenInvoiceListVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteOpenInvoiceList(int id) throws Exception {
		return dao.delete(OpenInvoiceListVO.class, id);
    }
	
	public OpenInvoiceListVO loadOpenInvoiceListById(int id) throws Exception{
		OpenInvoiceListVO openInvoiceList = dao.get(OpenInvoiceListVO.class, id,new OpenInvoiceListMapper());
		return openInvoiceList;
	}
	
	public List<OpenInvoiceListVO> searchOpenInvoiceList(OpenInvoiceListQueryVO query){
		return dao.searchOpenInvoiceList(query);
	}
	
	public List<OpenInvoiceListVO> searchOpenInvoiceListByPage(OpenInvoiceListQueryVO vo, IThumbPageTools tools){
		return dao.searchOpenInvoiceListByPage(vo, tools);
	}
}
