package com.zd.csms.zxbank.web.excel;

import com.zd.csms.zxbank.bean.*;
import com.zd.tools.file.importFile.IImportRowMapper;

public class FinancingRowMapper implements IImportRowMapper {

	@Override
	public Object importRow(String[] values) {
		return values;
	}

	@Override
	public String[] exportRow(Object obj) {
		//把为空的数据判断下
		Financing rd = (Financing) obj;
		return new String[] {rd.getFgLonentNo(),rd.getFgLoncpName(),rd.getFgStDate(),rd.getFgEndDate(),rd.getFgLoanCode(),rd.getFgScftxNo(),rd.getFgLoanAmt(),rd.getFgBailRat(),rd.getFgSlfcap(),rd.getFgFstblRat(),rd.getFgProcrt(),rd.getFgBizMod(),rd.getFgOperOrg(),rd.getFgagtid(),rd.getFgCreateDate()==null?"":rd.getFgCreateDate().toString(),rd.getFgUpdateDate()==null?"":rd.getFgUpdateDate().toString()};
	}

	@Override
	public String[] exportTitle() {
		return new String[] { "借款企业ID", "借款企业名称  ", "融资起始日", "融资到期日", "融资编号", "放款批次号", "融资金额", "首付保证金比例", "自有资金比例",
				"首付保证金可提货比例", "授信产品", "业务模式", "经办行", "协议编号","创建时间","更新时间"};
	}

}
