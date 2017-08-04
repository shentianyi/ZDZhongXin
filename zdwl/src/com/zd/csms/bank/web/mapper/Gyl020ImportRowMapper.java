package com.zd.csms.bank.web.mapper;

import com.zd.csms.bank.bean.Gyl016VO;
import com.zd.csms.bank.bean.Gyl020VO;
import com.zd.tools.file.importFile.IImportRowMapper;

public class Gyl020ImportRowMapper implements IImportRowMapper{

	@Override
	public Object importRow(String[] values) {
		for (int i = 0; i < values.length; i++) {
			if(values[i]==null)
				values[i]="";
		}
		int index=0;
		Gyl020VO gyl020 = new Gyl020VO();
		gyl020.setChassisNo(values[index++]);
		gyl020.setDeliveryQuantity(values[index++]);
		return gyl020;
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
