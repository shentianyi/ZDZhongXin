package com.zd.csms.bank.web.mapper;

import com.zd.csms.bank.bean.Gyl013;
import com.zd.tools.file.importFile.IImportRowMapper;

public class Gyl013ExcelRowMapper implements IImportRowMapper{

	@Override
	public Object importRow(String[] values) {
		return null;
	}

	@Override
	public String[] exportRow(Object obj) {
		Gyl013 gyl013 = (Gyl013) obj;
		return new String[]{gyl013.getMorgageNo(),gyl013.getName(),gyl013.getModel(),gyl013.getManufacturer()
				,gyl013.getQuantity(),gyl013.getMortgageUnits(),gyl013.getPrice(),gyl013.getEngineNo(),
				gyl013.getChassisNo(),gyl013.getCertificationNo(),""};
	}

	@Override
	public String[] exportTitle() {
		return new String[]{"押品编号","押品名称","押品规格型号","押品生产厂家","数量/重量",
				"数量/重量 单位","买入单价","发动机号","车架号","合格证号","备注"};
	}

}
