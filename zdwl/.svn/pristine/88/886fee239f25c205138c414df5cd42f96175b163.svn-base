package com.zd.csms.windcontrol.querybean;

/**
 * 巡检报告台账列表集合实体
 */
import java.util.Date;
import com.zd.tools.StringUtil;

public class InspectionLedgerQueryBean {
	private int id;
	private Integer planCode;// 计划编号
	private String outControlUserName;// 外空专员
	private Integer dealerId;// 经销商Id
	private String dealerName;// 经销商名称
	private Integer brandId;// 品牌Id
	private String brandName;// 品牌名称
	private Integer bankId;// 银行Id
	private String bankFullName;// 银行全名
	private String oneBankName;// 总行/金融机构
	private String twoBankName;// 分行/分支机构
	private String threeBankName;// 支行
	private Integer provinceId;// 省份Id
	private String provinceName;// 省份名称
	private Integer cityId;// 城市Id
	private String cityName;// 城市名称
	private Date check_time;// 检查日期
	private String check_period;// 检查用时
	private int check_type;// 检查类型 1：常规检查 2：异常检查
	private String checkTypeName;// 检查类型 1：常规检查 2：异常检查
	private int danger_level;// 风险等级 :4无 1：A级 2:B级 3:C级
	private int dealer_level;// 经销店等级：4无 1：A级 2:B级 3:C级
	private int supervisor_level;// 监管员等级：4无 1：A级 2:B级 3:C级

	private String dangerLevelName;// 风险等级 :4无 1：A级 2:B级 3:C级
	private String dealerLevelName;// 经销店等级：4无 1：A级 2:B级 3:C级
	private String supervisorLevelName;// 监管员等级：4无 1：A级 2:B级 3:C级

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getPlanCode() {
		return planCode;
	}

	public void setPlanCode(Integer planCode) {
		this.planCode = planCode;
	}

	public String getOutControlUserName() {
		return outControlUserName;
	}

	public void setOutControlUserName(String outControlUserName) {
		this.outControlUserName = outControlUserName;
	}

	public Integer getDealerId() {
		return dealerId;
	}

	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getBankFullName() {
		return bankFullName;
	}

	public void setBankFullName(String bankFullName) {
		if (StringUtil.isNotEmpty(bankFullName)) {
			String[] bankNames = bankFullName.split("/");
			if (bankNames!=null&&bankNames.length==2) {
				oneBankName=bankNames[0];
				twoBankName=bankNames[1];
				
			} else if (bankNames!=null&&bankNames.length == 3) {
				oneBankName=bankNames[0];
				twoBankName=bankNames[1];
				threeBankName=bankNames[2];
			}
		}
		this.bankFullName = bankFullName;
	}

	public String getOneBankName() {
		return oneBankName;
	}

	

	public String getTwoBankName() {
		return twoBankName;
	}

	

	public String getThreeBankName() {
		return threeBankName;
	}

	

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Date getCheck_time() {
		return check_time;
	}

	public void setCheck_time(Date check_time) {
		this.check_time = check_time;
	}

	public String getCheck_period() {
		return check_period;
	}

	public void setCheck_period(String check_period) {
		this.check_period = check_period;
	}

	public int getCheck_type() {
		return check_type;
	}

	public void setCheck_type(int check_type) {
		this.check_type = check_type;
	}

	public int getDanger_level() {
		return danger_level;
	}

	public void setDanger_level(int danger_level) {
		this.danger_level = danger_level;
	}

	public int getDealer_level() {
		return dealer_level;
	}

	public void setDealer_level(int dealer_level) {
		this.dealer_level = dealer_level;
	}

	public int getSupervisor_level() {
		return supervisor_level;
	}

	public void setSupervisor_level(int supervisor_level) {
		this.supervisor_level = supervisor_level;
	}

	public String getDangerLevelName() {
		switch (this.danger_level) {
		case 1:
			dangerLevelName="A级" ;
			break;
		case 2:
			dangerLevelName="B级" ;
			break;
		case 3:
			dangerLevelName="C级" ;
			break;
		case 4:
			dangerLevelName="无" ;
			break;
		default:
			dangerLevelName=null;
			break;
		}
		return dangerLevelName;
	}

	public String getDealerLevelName() {
		switch (this.dealer_level) {
		case 1:
			dealerLevelName="A级" ;
			break;
		case 2:
			dealerLevelName="B级" ;
			break;
		case 3:
			dealerLevelName="C级" ;
			break;
		case 4:
			dealerLevelName="无" ;
			break;
		default:
			dealerLevelName=null;
			break;
		}
		return dealerLevelName;
	}

	public String getSupervisorLevelName() {
		switch (this.supervisor_level) {
		case 1:
			supervisorLevelName="A级" ;
			break;
		case 2:
			supervisorLevelName="B级" ;
			break;
		case 3:
			supervisorLevelName="C级" ;
			break;
		case 4:
			supervisorLevelName="无" ;
			break;
		default:
			supervisorLevelName=null;
			break;
		}
		return supervisorLevelName;
	}

	public String getCheckTypeName() {
		switch (this.check_type) {
		case 1:
			checkTypeName="常规检查" ;
			break;
		case 2:
			checkTypeName="异常检查" ;
			break;
		
		default:
			checkTypeName=null;
			break;
		}
		
		return checkTypeName;
	}

}
