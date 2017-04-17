package com.zd.csms.zxbank.web.mapper;

import com.zd.csms.zxbank.bean.CheckstockVO;
import com.zd.tools.file.importFile.IImportRowMapper;

public class CheckstockVOImportRowMapper implements IImportRowMapper {

	@Override
	public Object importRow(String[] values) {
		for (int i = 0; i < values.length; i++) {
			if (values[i] == null)
				values[i] = "";
			//判断其是否有.0
			if (values[i].indexOf(".") > 0)
				values[i] = values[i].substring(0, values[i].indexOf("."));
		}
		int index = 0;
		CheckstockVO check = new CheckstockVO();
		check.setWhlevel(values[index++]);
		check.setWhcode(values[index++]);
		check.setWhaddr(values[index++]);
		check.setCmcode(values[index++]);
		check.setNum(values[index++]);
		check.setCmgrtcntno(values[index++]);
		check.setVin(values[index++]);
		return check;
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
