package com.zd.csms.supervisory.web.excel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.rbac.model.UserQueryVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.supervisory.model.AbnormalVO;
import com.zd.tools.file.importFile.IImportRowMapper;

public class AbnormalRowMapper implements IImportRowMapper {

	@Override
	public String[] exportRow(Object obj) {
		return null;
	}

	@Override
	public String[] exportTitle() {
		return null;
	}

	@Override
	public Object importRow(String[] values) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		UserService us = new UserService();
		DealerService ds = new DealerService();
		
		AbnormalVO o = new AbnormalVO();
		
		try {
			UserQueryVO bquery = new UserQueryVO();
			bquery.setUserName(values[1]);
			List<UserVO> bList = us.searchUserList(bquery);
			if(bList != null && bList.size()>0){
				UserVO bu = bList.get(0);
				o.setBusiness(bu.getId());
			}
			DealerQueryVO dquery = new DealerQueryVO();
			dquery.setDealerName(values[2]);
			List<DealerVO> dList = ds.findList(dquery);
			if(dList != null && dList.size()>0){
				DealerVO dvo = dList.get(0);
				o.setDealerId(dvo.getId());
			}
			o.setTotalStock(values[6]);
			o.setJyAnomalies(values[7]);
			o.setShCarNumber(values[8]);
			o.setShCarMoney(values[9]);
			o.setShContinuedDay(values[10]);
			o.setXsCarNumber(values[11]);
			o.setXsCarMoney(values[12]);
			o.setXsContinuedDay(values[13]);
			o.setSyCarNumber(values[14]);
			o.setSyCarMoney(values[15]);
			o.setSyContinuedDay(values[16]);
			o.setYcproportion(values[17]);
			Date earliestInvoice = formatter.parse(values[18]);
			o.setEarliestInvoice(earliestInvoice);
			o.setAmountnotfilled(values[19]);
			Date earliestExpire = formatter.parse(values[20]);
			o.setEarliestExpire(earliestExpire);
			o.setOutstandingAmount(values[21]);
			Date gjDate = formatter.parse(values[22]);
			o.setGjDate(gjDate);
			o.setProgress(values[23]);
			o.setRemark(values[24]);
			o.setCreateDate(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return o;
	}

}
