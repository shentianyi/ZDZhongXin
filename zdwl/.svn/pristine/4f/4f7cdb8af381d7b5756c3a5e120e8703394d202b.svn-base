package com.zd.csms.bank.web.mapper;

import com.zd.csms.bank.bean.Gyl009VO;
import com.zd.tools.file.importFile.IImportRowMapper;

public class Gyl009ImportRowMapper implements IImportRowMapper{

	@Override
	public Object importRow(String[] values) {
		for (int i = 0; i < values.length; i++) {
			if(values[i]==null)
				values[i]="";
		}
		Gyl009VO gyl009 = new Gyl009VO();
		gyl009.setName(values[0]);
		gyl009.setModel(values[1]);
		gyl009.setManufacturer(values[2]);
		gyl009.setQuantity(values[3]);
		gyl009.setMortgageUnits(values[4]);
		gyl009.setPrice(values[5]);
		gyl009.setEngineNo(values[6]);
		gyl009.setChassisNo(values[7]);
		gyl009.setCertificationNo(values[8]);
		return gyl009;
	}

	@Override
	public String[] exportRow(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] exportTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
