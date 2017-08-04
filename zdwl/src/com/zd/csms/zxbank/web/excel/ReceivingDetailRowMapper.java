package com.zd.csms.zxbank.web.excel;

import com.zd.csms.zxbank.bean.*;
import com.zd.tools.file.importFile.IImportRowMapper;

public class ReceivingDetailRowMapper implements IImportRowMapper {

	@Override
	public Object importRow(String[] values) {
		return values;
	}

	@Override
	public String[] exportRow(Object obj) {
		//把为空的数据判断下
		ReceivingDetail rd = (ReceivingDetail) obj;
		return new String[] { rd.getNdIdtplanno(), rd.getNdIdtplannm(), rd.getNdCmdcode(), rd.getNdCmdname(),
				rd.getNdCsnnum() + "", rd.getNdUnit(), rd.getNdCsnprc() + "", rd.getNdReqcsnval() + "",
				rd.getNdScflonno(), rd.getNdMtgcntno(), rd.getNdLoancode(), rd.getNdHgzno(), rd.getNdVin(),
				rd.getNdCarprice(), rd.getNdRemark() };
	}

	@Override
	public String[] exportTitle() {
		return new String[] { "实际订单纸质编号", "实际订单名称", "商品代码", "商品名称", "发货数量", "计量单位", "发货单价", "发货价值", "SCF放款批次号",
				"质押合同编号", "融资编号", "合格证编号", "车架号", "车价", "备注" };
	}

}
