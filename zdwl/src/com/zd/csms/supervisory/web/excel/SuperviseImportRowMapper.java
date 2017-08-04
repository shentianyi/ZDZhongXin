package com.zd.csms.supervisory.web.excel;

import java.text.SimpleDateFormat;

import com.zd.csms.supervisory.contants.CheckStockCarOperationContants;
import com.zd.csms.supervisory.querybean.ExportCarInfoBean;
import com.zd.csms.util.DateUtil;
import com.zd.tools.file.importFile.IImportRowMapper;

public class SuperviseImportRowMapper implements IImportRowMapper {
	@Override
	public String[] exportRow(Object obj) {
		
		ExportCarInfoBean vo = (ExportCarInfoBean)obj;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String certificate_date = "";
		if(vo.getCertificate_date() != null){
			certificate_date = formatter.format(vo.getCertificate_date());
		}
		
		String certificate_intime = "";
		if(vo.getCertificate_intime() != null){
			certificate_intime = formatter.format(vo.getCertificate_intime());
		}
		String storagetime ="";
		if(vo.getStoragetime() != null){
			storagetime = formatter.format(vo.getStoragetime());
		}
		String movetime = "";
		if(vo.getMovetime() != null){
			movetime = formatter.format(vo.getMovetime());
		}
		String outtime = "";
		if(vo.getOuttime() != null){
			outtime = formatter.format(vo.getOuttime());
		}
		String state = "";
		if(vo.getApply() == 1){
			if(vo.getState() == 2){
				state = "入库申请中";
			}
			if(vo.getState() == 3){
				state = "出库申请中";
			}
			if(vo.getState() == 4){
				state = "移动申请中";
			}
		}else{
			if(vo.getState() == 1){
				state = "在途";
			}
			if(vo.getState() == 2){
				state = "在库";
			}
			if(vo.getState() == 3){
				state = "出库";
			}
			if(vo.getState() == 4){
				state = "移动";
			}
		}
		
		String first_abnormal_time ="";
		/*if(vo.getFirst_abnormal_time() != null){
			first_abnormal_time = DateUtil.getStringFormatByTimestamp(vo.getFirst_abnormal_time(), "yyyy/MM/dd");
		}*/
		
		
		return new String[]{vo.getDealerName(),vo.getBankFullName(),vo.getBrandName(),vo.getDraft_num(),vo.getBilling_money(),
				DateUtil.getStringFormatByDate( vo.getBilling_date(), "yyyy-MM-dd"),
				DateUtil.getStringFormatByDate( vo.getDue_date(), "yyyy-MM-dd"),certificate_date,vo.getCertificate_num(),
				vo.getCar_model(),vo.getCar_structure(),vo.getDisplacement(),vo.getColor(),vo.getEngine_num(),vo.getVin(),
				vo.getKey_num(),vo.getKey_amount(),vo.getMoney(),certificate_intime,storagetime,
				state,movetime,outtime,vo.getTwo_name(),vo.getBond(),vo.getPayment_amount()};
	}
	
	
	public static void main(String[] args) {
		System.out.println(CheckStockCarOperationContants.getName(-1));
	}

	@Override
	public String[] exportTitle() {
		return new String[]{"经销商","金融机构","品牌","票号","开票金额","开票日","到期日","合格证发证日期","合格证号","车辆型号","车身结构","排量","颜色"
				,"发动机号","车架号","钥匙号","钥匙数","单价","合格证接收时间","入库时间","车辆状态","移动时间","释放时间"
				,"二网名称","保证金","日查库状态","回款金额","备注"};
	}

	@Override
	public Object importRow(String[] values) {
		return values;
	}

}
