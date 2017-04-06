package com.zd.csms.zxbank.web.excel;

import com.zd.csms.zxbank.bean.RemovePledgeDetail;
import com.zd.tools.file.importFile.IImportRowMapper;

public class RemoveRowMapper implements IImportRowMapper{

	@Override
	public Object importRow(String[] values) {
		return values;
	}

	@Override
	public String[] exportRow(Object obj) {
		RemovePledgeDetail rd = (RemovePledgeDetail) obj;
		return new String[]{rd.getRdCmdcode(),rd.getRdCmdname(),rd.getRdUnit(),rd.getRdStknum()+"",rd.getRdRlsmgnum()+"",rd.getRdWhcode(),rd.getRdScflonno(),rd.getRdChattelpdno(),rd.getRdNumber().toString(),rd.getRdChassisno(),rd.getRdCertificationno(),rd.getRdCarprice().toString()};
	}

	@Override
	public String[] exportTitle() {
		/*return new String[]{"解除质押通知书编号","经办行","出质人名称","核心企业名称","借款企业ID","借款企业名称","监管企业名称",
				"解除质押日期","通知书日期","出库原因"};*/
		return new String[]{"商品代码","商品名称","计量单位","库存数量","解除质押数量","所在仓库编号","SCF放款批次号","动产质押担保合同编号","移库数量","车架号","合格证编号","车价"};
	}

}
