package com.zd.tools.file.importFile.test;

import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.file.importFile.IImportRowMapper;

public class TelePhoneRowMapper implements IImportRowMapper{

	@Override
	public Object importRow(String[] values) {
		TelePhone tp = new TelePhone();
		tp.setDepartment(values[0]);
		tp.setName(values[1]);
		tp.setPhone(values[3]);
		return tp;
	}

	@Override
	public String[] exportRow(Object obj) {
		//根据传入的Bean 编写要导出的列
		UserVO user = (UserVO) obj;
		return new String[]{user.getUserName(),"asd"};
	}

	@Override
	public String[] exportTitle() {
		//导出excel的表头标题
		return new String[]{"姓名","车辆型号"};
	}
	
}
