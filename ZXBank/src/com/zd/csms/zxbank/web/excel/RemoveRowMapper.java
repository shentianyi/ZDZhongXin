package com.zd.csms.zxbank.web.excel;

import com.zd.csms.zxbank.bean.*;
import com.zd.tools.file.importFile.IImportRowMapper;

public class RemoveRowMapper implements IImportRowMapper {

	@Override
	public Object importRow(String[] values) {
		return values;
	}

	@Override
	public String[] exportRow(Object obj) {
		RemovePledge rp = (RemovePledge) obj;
		return new String[] { rp.getRpNo(), rp.getRpOperorg(), rp.getRpPldegeename(), rp.getRpCorename(),
				rp.getRpLoncpid(), rp.getRpLoncpname(), rp.getRpSupervisename(), rp.getRpRelievepddate(),
				rp.getRpNoticedate(), rp.getRpContent() };
	}

	@Override
	public String[] exportTitle() {
		return new String[] { "解除质押通知书编号", "经办行", "出质人名称", "核心企业名称", "借款企业ID", "借款企业名称", "监管企业名称", "解除质押日期", "通知书日期",
				"出库原因" };
	}

}
