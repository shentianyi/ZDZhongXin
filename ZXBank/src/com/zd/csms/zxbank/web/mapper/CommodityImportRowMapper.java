package com.zd.csms.zxbank.web.mapper;

import java.math.BigDecimal;

import com.zd.csms.zxbank.bean.Commodity;
import com.zd.tools.file.importFile.IImportRowMapper;

public class CommodityImportRowMapper implements IImportRowMapper{

	@Override
	public Object importRow(String[] values) {
		for (int i = 0; i < values.length; i++) {
			if(values[i]==null)
				values[i]="";
			//判断其是否有.0
			if(values[i].indexOf(".")>0)
				values[i] = values[i].substring(0, values[i].indexOf("."));
		}
		int index=0;
		Commodity commodity = new Commodity();
		commodity.setCmCmdcode(values[index++]);
		commodity.setCmStknum(Integer.parseInt(values[index++]));
		commodity.setCmIstkprc(new BigDecimal(values[index++]));
		commodity.setCmWhcode(values[index++]);
		commodity.setCmVin(values[index++]);
		commodity.setCmHgzno(values[index++]);
		commodity.setCmCarprice(new BigDecimal(values[index++]));
		commodity.setCmLoancode(values[index++]);
		return commodity;
	}

	@Override
	public String[] exportRow(Object obj) {
		return null;
	}

	@Override
	public String[] exportTitle() {
		return null;
	}

}
