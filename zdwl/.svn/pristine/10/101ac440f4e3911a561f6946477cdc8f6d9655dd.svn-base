package com.zd.csms.business.web.excel;

import com.zd.csms.business.model.TwoAddressVO;
import com.zd.csms.util.tagutil.DealerNameUtil;
import com.zd.tools.file.importFile.IImportRowMapper;

public class TwoAddressRowMapper implements IImportRowMapper {

	@Override
	public String[] exportRow(Object obj) {
		DealerNameUtil dnUtil = new DealerNameUtil();
		TwoAddressVO vo = (TwoAddressVO)obj;
		
		String dealername = dnUtil.dealerName(vo.getDistribid(), "s");
		
		return new String[]{dealername,vo.getTwo_name(),vo.getTwo_address(),vo.getTwo_username(),
				vo.getPhone(),vo.getDistance()};
	}

	@Override
	public String[] exportTitle() {
		return new String[]{"经销商","名称","详细地址","联系人","联系方式","距离(公里)"};
	}

	@Override
	public Object importRow(String[] values) {
		// TODO Auto-generated method stub
		return null;
	}

}
