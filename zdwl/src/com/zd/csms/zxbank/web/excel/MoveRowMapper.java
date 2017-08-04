package com.zd.csms.zxbank.web.excel;

import com.zd.csms.zxbank.bean.*;
import com.zd.tools.file.importFile.IImportRowMapper;

public class MoveRowMapper implements IImportRowMapper {

	@Override
	public Object importRow(String[] values) {
		return values;
	}

	@Override
	public String[] exportRow(Object obj) {
		MoveNotice mn = (MoveNotice) obj;
		return new String[] { mn.getMnNo(), mn.getMnSupervisename(), mn.getMnLoncpname(), mn.getMnOperorg(),
				mn.getMnMovedate(), mn.getMnNoticedate() };
	}

	@Override
	public String[] exportTitle() {
		return new String[] { "移库通知书编号", "监管企业名称", "借款企业名称", "经办行", "移库申请日期", "通知书日期" };
	}

}
