package com.zd.csms.marketing.web.excel;

import java.text.SimpleDateFormat;

import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.util.tagutil.PayModeUtil;
import com.zd.tools.file.importFile.IImportRowMapper;

public class DealerRowMapper implements IImportRowMapper {
    private  String[] title ;
    private int addTitle;
	public DealerRowMapper() {
		 this.title=new String[]{"经销商全称","金融机构","品牌","经销商所在省","经销商所在市","经销商具体地址","进驻时间","经销商联系人"
				,"电话","监管员","电话","金融机构联系人","电话","经销商性质","设备提供方","协议监管模式","合作模式","单店/绑定"
				,"绑定信息","监管费标准/年","付费方式","是否变更监管费","变更监管费时间","变更前监管费标准","交接公司","交接时间"
				,"合作状态","撤店时间","移动比例"};
	}
    
    public DealerRowMapper(int addTitle) {
    	this.addTitle=addTitle ;
    	this.title=new String[]{"经销商全称","金融机构","品牌","经销商所在省","经销商所在市","经销商具体地址","进驻时间","经销商联系人"
				,"电话","监管员","电话","金融机构联系人","电话","经销商性质","设备提供方","协议监管模式","合作模式","单店/绑定"
				,"绑定信息","监管费标准/年","付费方式","是否变更监管费","变更监管费时间","变更前监管费标准","交接公司","交接时间"
				,"合作状态","撤店时间","移动比例","授信额度","汇票下发方式","押车方式","合格证送达方式","实际监管模式","钥匙监管","二网",
				"二库","特殊操作"};
	}
    
	@Override
	public String[] exportRow(Object obj) {
		
		DealerQueryBean vo = (DealerQueryBean)obj;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		String inDate = "";
		if(vo.getInDate() != null){
			inDate = formatter.format(vo.getInDate());
		}
		
		String changeSuperviseMoneyDate ="";
		if(vo.getChangeSuperviseMoneyDate() != null){
			changeSuperviseMoneyDate = formatter.format(vo.getChangeSuperviseMoneyDate());
		}
		
		String cooperationModel = "";
		if(vo.getCooperationModel() == 1){
			cooperationModel = "两方";
		}
		if(vo.getCooperationModel() == 2){
			cooperationModel = "三方";
		}
		
		String ddorbd ="";
		if(vo.getDdorbd() == 2){
			ddorbd = "单店";
		}
		if(vo.getDdorbd() == 1){
			ddorbd = "绑定";
		}
		
		String handoverDate = "";
		if(vo.getHandoverDate() != null){
			handoverDate = formatter.format(vo.getHandoverDate());
		}
		
		String cooperationState ="";
		if(vo.getCooperationState() == 1){
			cooperationState = "合作中";
		}
		if(vo.getCooperationState() == 2){
			cooperationState = "未进店";
		}
		if(vo.getCooperationState() == 3){
			cooperationState = "撤店";
		}
		String exitDate = "";
		if(vo.getExitDate() != null){
			exitDate = formatter.format(vo.getExitDate());
		}
		
		PayModeUtil pmUtil = new PayModeUtil();
		if(addTitle>0){
			return new String[]{vo.getDealerName(),vo.getBankName(),vo.getBrand(),vo.getProvince(),
					vo.getCity(),vo.getAddress(),inDate,vo.getContact(),vo.getContactPhone(),vo.getSuperviseName(),
					vo.getSupervisePhone(),vo.getBankContact(),vo.getBankPhone(),vo.getDealerNature(),
					vo.getEquipmentProvide(),vo.getSupervisionMode(),cooperationModel,ddorbd,vo.getBindInfo(),
					vo.getSuperviseMoney(),pmUtil.payMode(vo.getPayMode()),vo.getIsChangeSuperviseMoney()+"",
					changeSuperviseMoneyDate,vo.getChangeBeforeInfo(),vo.getHandoverCompany(),handoverDate,
					cooperationState,exitDate,vo.getYdbl(),vo.getCredit()+"",vo.getDraft_way(),vo.getGuard_way(),
					vo.getCertificate_delivery(),vo.getActual_supervision(),vo.getKey_supervise(),vo.getWebsite(),
					vo.getOld_car(),vo.getSpecial_oper()};
			
		}else{
			return new String[]{vo.getDealerName(),vo.getBankName(),vo.getBrand(),vo.getProvince(),
					vo.getCity(),vo.getAddress(),inDate,vo.getContact(),vo.getContactPhone(),vo.getSuperviseName(),
					vo.getSupervisePhone(),vo.getBankContact(),vo.getBankPhone(),vo.getDealerNature(),
					vo.getEquipmentProvide(),vo.getSupervisionMode(),cooperationModel,ddorbd,vo.getBindInfo(),
					vo.getSuperviseMoney(),pmUtil.payMode(vo.getPayMode()),vo.getIsChangeSuperviseMoney()+"",
					changeSuperviseMoneyDate,vo.getChangeBeforeInfo(),vo.getHandoverCompany(),handoverDate,
					cooperationState,exitDate,vo.getYdbl()};
		}
		
	}

	@Override
	public String[] exportTitle() {
		return title ;
	}

	@Override
	public Object importRow(String[] values) {
		// TODO Auto-generated method stub
		return null;
	}

}
