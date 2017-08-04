package com.zd.csms.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.finance.dao.FinanceDAOFactory;
import com.zd.csms.finance.dao.IRefundInvoiceDAO;
import com.zd.csms.finance.dao.mapper.RefundInvoiceMapper;
import com.zd.csms.finance.model.RefundInvoiceQueryVO;
import com.zd.csms.finance.model.RefundInvoiceVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class RefundInvoiceService extends ServiceSupport {

	private IRefundInvoiceDAO dao = FinanceDAOFactory.getRefundInvoiceDAO();
	
	public boolean addRefundInvoice(RefundInvoiceVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(RefundInvoiceVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updRefundInvoice(RefundInvoiceVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteRefundInvoice(int id) throws Exception {
		return dao.delete(RefundInvoiceVO.class, id);
    }
	
	public RefundInvoiceVO loadRefundInvoiceById(int id) throws Exception{
		RefundInvoiceVO refundInvoice = dao.get(RefundInvoiceVO.class, id,new RefundInvoiceMapper());
		return refundInvoice;
	}
	
	public List<RefundInvoiceVO> searchRefundInvoiceList(RefundInvoiceQueryVO query){
		return dao.searchRefundInvoiceList(query);
	}
	
	public List<RefundInvoiceVO> searchRefundInvoiceListByPage(RefundInvoiceQueryVO vo, IThumbPageTools tools){
		return dao.searchRefundInvoiceListByPage(vo, tools);
	}
}
