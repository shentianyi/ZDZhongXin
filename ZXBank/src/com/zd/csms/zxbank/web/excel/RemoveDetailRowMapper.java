package com.zd.csms.zxbank.web.excel;

import com.zd.csms.zxbank.bean.RemovePledgeDetail;
import com.zd.tools.file.importFile.IImportRowMapper;

public class RemoveDetailRowMapper implements IImportRowMapper{

	@Override
	public Object importRow(String[] values) {
		return values;
	}

	@Override
	public String[] exportRow(Object obj) {
		RemovePledgeDetail rd = (RemovePledgeDetail) obj;
		return new String[]{rd.getRdCmdcode(),rd.getRdCmdname(),rd.getRdUnit(),rd.getRdStknum()+"",rd.getRdRlsmgnum()+"",rd.getRdWhcode(),rd.getRdScflonno(),rd.getRdChattelpdno(),rd.getRdUsername(),rd.getRdUsercardid(),rd.getRdNumber().toString(),rd.getRdChassisno(),rd.getRdCertificationno(),rd.getRdCarprice().toString()};
	}

	@Override
	public String[] exportTitle() {
		return new String[]{"商品代码","商品名称","计量单位","库存数量","解除质押数量","所在仓库编号","SCF放款批次号","动产质押担保合同编号","赎货经办人姓名","赎货经办人身份证号码","移库数量","车架号","合格证编号","车价"};
	}

}
