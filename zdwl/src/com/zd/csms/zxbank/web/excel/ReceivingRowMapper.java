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
		return new String[] { rn.getNyNo() == null ? "" : rn.getNyNo(),
				rn.getNyCorentnm() == null ? "" : rn.getNyCorentnm(),
				rn.getNySpventnm() == null ? "" : rn.getNySpventnm(),
				rn.getNyOnwspvenm() == null ? "" : rn.getNyOnwspvenm(),
				rn.getNyTrsptentnm() == null ? "" : rn.getNyTrsptentnm(),
				rn.getNyLonentno() == null ? "" : rn.getNyLonentno(),
				rn.getNyLonentname() == null ? "" : rn.getNyLonentname(),
				rn.getNyCsndate() == null ? "" : rn.getNyCsndate().toString(),
				rn.getNyEta() == null ? "" : rn.getNyEta().toString(),
				rn.getNyEpa() == null ? "" : rn.getNyEpa(),
				rn.getNyOfflnsatno() == null ? "" : rn.getNyOfflnsatno(),
				rn.getNyNtcdate() == null ? "" : rn.getNyNtcdate().toString(),
				rn.getNyTtlcmdval() == null ? "" : rn.getNyTtlcmdval() + "",
				rn.getNyExcplace() == null ? "" : rn.getNyExcplace(),
				rn.getNyTotnum() == null ? "" : rn.getNyTotnum() + "",
				rn.getNyRemark() == null ? "" : rn.getNyRemark() };
	}

	@Override
	public String[] exportTitle() {
		return new String[] { "收货通知书编号", "核心企业名称", "(在库)监管企业名称", "(在途)监管企业名称",
				"运输企业名称", "借款企业ID", "借款企业名称", "发货日期", "预计到港(库)日期", "预计到港(库)",
				"纸质监管协议编号", "通知书日期", "货物价值合计", "交货地点", "总记录", "备注" };
	}
}
