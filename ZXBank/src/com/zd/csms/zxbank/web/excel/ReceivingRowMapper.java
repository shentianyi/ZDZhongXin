package com.zd.csms.zxbank.web.excel;

import com.zd.csms.zxbank.bean.*;
import com.zd.tools.file.importFile.IImportRowMapper;

public class ReceivingRowMapper implements IImportRowMapper {

	@Override
	public Object importRow(String[] values) {
		return values;
	}

	@Override
	public String[] exportRow(Object obj) {
		ReceivingNotice rn = (ReceivingNotice) obj;
		return new String[] { rn.getNyNo(), rn.getNyCorentnm(), rn.getNySpventnm(), rn.getNyOnwspvenm(),
				rn.getNyTrsptentnm(), rn.getNyLonentno(), rn.getNyLonentname(), rn.getNyCsndate(), rn.getNyEta(),
				rn.getNyEpa(), rn.getNyOfflnsatno(), rn.getNyNtcdate(), rn.getNyTtlcmdval() + "", rn.getNyExcplace(),
				rn.getNyTotnum() + "", rn.getNyRemark() };
	}

	@Override
	public String[] exportTitle() {
		return new String[] { "收货通知书编号", "核心企业名称", "(在库)监管企业名称", "(在途)监管企业名称", "运输企业名称", "借款企业ID", "借款企业名称", "发货日期",
				"预计到港(库)日期", "预计到港(库)", "纸质监管协议编号", "通知书日期", "货物价值合计", "交货地点", "总记录", "备注" };
	}

}
