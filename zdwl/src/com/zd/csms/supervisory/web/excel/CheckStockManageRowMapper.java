package com.zd.csms.supervisory.web.excel;

import com.zd.csms.supervisory.model.CheckStockCarBean;
import com.zd.csms.util.DateUtil;
import com.zd.tools.file.importFile.IImportRowMapper;

public class CheckStockManageRowMapper implements IImportRowMapper {

	@Override
	public String[] exportRow(Object obj) {
		
		CheckStockCarBean vo = (CheckStockCarBean)obj;
		String state = "";
		if(vo.getCurrstate() == 1){
			state = "本库";
		}else if(vo.getCurrstate() == 2){
			state = "二库";
		}else if(vo.getCurrstate() == 3){
			state = "移动";
		}
		return new String[]{vo.getModel(),vo.getColor(),vo.getVin(),vo.getLastFiveVin(),
				DateUtil.getStringFormatByDate( vo.getCheck_date(), "yyyy-MM-dd HH:mm:ss"),state};
	}

	@Override
	public String[] exportTitle() {
		return new String[]{"型号","颜色","车架号","后5位数","检查时间","系统状态"};
	}

	@Override
	public Object importRow(String[] values) {
		return values;
	}

}
