package com.zd.csms.zxbank.web.excel;

import com.zd.csms.zxbank.bean.*;
import com.zd.tools.file.importFile.IImportRowMapper;

public class MoveDetailRowMapper implements IImportRowMapper {

	@Override
	public Object importRow(String[] values) {
		return values;
	}

	@Override
	public String[] exportRow(Object obj) {
		MoveDetail md = (MoveDetail) obj;
		return new String[] { md.getMdRemoveoutno(), md.getMdRemoveinno(), md.getMdWareno(), md.getMdMovenumber() + "",
				md.getMdChassisno(), md.getMdCertificationno(), md.getMdCarprice() + "" };
	}

	@Override
	public String[] exportTitle() {
		return new String[] { "移出仓库号", "移入仓库号", "商品代码", "移库数量", "车架号", "合格证编号", "车价" };
	}

}
