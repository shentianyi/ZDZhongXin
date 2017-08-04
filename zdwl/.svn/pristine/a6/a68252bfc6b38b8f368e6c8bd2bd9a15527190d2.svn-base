package com.zd.csms.bank.web.mapper;

import com.zd.csms.bank.bean.Gyl016VO;
import com.zd.tools.file.importFile.IImportRowMapper;

public class Gyl016ImportRowMapper implements IImportRowMapper{

	@Override
	public Object importRow(String[] values) {
		for (int i = 0; i < values.length; i++) {
			if(values[i]==null)
				values[i]="";
		}
		int index=0;
		Gyl016VO gyl016 = new Gyl016VO();
		gyl016.setMorgageNo(values[index++]);
		gyl016.setName(values[index++]);
		gyl016.setModel(values[index++]);
		gyl016.setManufacturer(values[index++]);
		gyl016.setQuantity(values[index++]);
		gyl016.setMortgageUnits(values[index++]);
		gyl016.setPrice(values[index++]);
		gyl016.setEngineNo(values[index++]);
		gyl016.setChassisNo(values[index++]);
		gyl016.setCertificationNo(values[index++]);
		gyl016.setMemo(values[index++]);
		return gyl016;
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
