package com.zd.csms.payment.web.excel;

import com.zd.csms.payment.model.PaymentInfoVO;
import com.zd.tools.file.importFile.IImportRowMapper;

public class PaymentInfoRowMapper implements IImportRowMapper {

	private static int i = 1;
	@Override
	public String[] exportRow(Object obj) {
		
		PaymentInfoVO vo = (PaymentInfoVO)obj;
		StringBuffer sb = new StringBuffer("");
		
		return new String[]{vo.getStatus()+"",i++ +"",vo.getStaffNo(),vo.getStaffName(),vo.getCardNo(),
				vo.getDealerNames(),vo.getProvinceName(),vo.getCityName(),vo.getCityType()+"",vo.getIsFormal()+"",
				vo.getIsFar()+"",vo.getStationedPro()+"",vo.getActualAttendance()+"",vo.getShouldAttendance()+"",vo.getIsFullTime()+"",
				vo.getEntryDate()+"",vo.getCompanyAge()+"",vo.getOvertimeDays()+"",vo.getBasicSalary()+"",
				vo.getBasePay()+"",vo.getCompanyAgePay()+"",vo.getMealSubsidy()+"",vo.getPhoneSubsidy()+"",vo.getTrafficSubsidy()+"",vo.getHouseSubsidy()+"",
				vo.getManySubsidy()+"",vo.getFarSubsidy()+"",vo.getFullTimeSubsidy()+"",vo.getSettleCost()+"",vo.getReplaceCost()+"",
				vo.getOvertimeCost()+"",vo.getElseSubsidy()+"",vo.getSubsidyOne()+"",vo.getSubsidyTwo()+"",vo.getDeductOne()+"",vo.getDeductTwo()+"",
				vo.getShouldMoney()+"",vo.getRevenue()+"",vo.getSubsidyOneTotal()+"",vo.getSubsidyTwoTotal()+"",vo.getDeductOneTotal()+"",vo.getDeductTwoTotal()+"",
				vo.getPraticleMoney()+"",vo.getRemark(),vo.getBankCardNo(),vo.getOpenBankName()
				
				
		};
	}

	@Override
	public String[] exportTitle() {
		return new String[]{
					"审批状态","序号","员工工号","员工姓名","身份证号码",
					"店名","所在省","所在市","城市类型","是否转正",
					"是否远疆","驻派属性","实际出勤天数","应出勤天数","当月是否全勤",
					"入职日期","司领（月）","加班天数","基本工资（固定）",
					"基本工资","司龄工资","餐补","话补","交通补","房补",
					"多店多行补助","远疆补助","全勤","安家费","替岗费",
					"加班费","异常补助","补款一","补款二","扣款一","扣款二",
					"应发金额","个人所得税","补款一","补款二","扣款一","扣款二",
					"实发金额","备注","银行卡号","开户行"
				};
	}

	@Override
	public Object importRow(String[] values) {
		return null;
	}

}
