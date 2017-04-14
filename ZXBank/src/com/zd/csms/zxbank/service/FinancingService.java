package com.zd.csms.zxbank.service;

import java.util.Date;
import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.util.DateUtil;
import com.zd.csms.zxbank.bean.Financing;
import com.zd.csms.zxbank.dao.IFinancingDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.model.FinancingQueryVO;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class FinancingService extends ServiceSupport {

	private IFinancingDAO dao = ZXBankDAOFactory.getFinancingDAO();

	public List<Financing> findByQuery(FinancingQueryVO query, IThumbPageTools tools) {
		return dao.findByQuery(query, tools);
	}
	
	public void addOrUpdate(List<Financing> list){
		for (Financing financing : list) {
			int count = dao.getCountByOther(financing.getFgLoanCode());
			if(count>0){
				
			}else{
				financing.setFiId(SqlUtil.getID(Financing.class));
				financing.setFgCreateDate(DateUtil.getStringFormatByDate(new Date(),
						"yyyy-MM-dd HH:mm:ss"));
				dao.add(financing);
			}
		}
	}
}
