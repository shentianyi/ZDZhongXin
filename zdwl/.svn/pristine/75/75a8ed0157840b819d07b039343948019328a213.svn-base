package com.zd.csms.messageAndWaring.message.queryBean.business;

import com.zd.csms.messageAndWaring.message.model.business.BusinWaringInfoVO;
import com.zd.tools.StringUtil;
/**
 * 信息预警：查询结果实体
 * @author zhangzhicheng
 *
 */
public class BusinWaringQueryBean extends BusinWaringInfoVO {
	private static final long serialVersionUID = -1430937519209724600L;
	private String oneBankName;// 总行/金融机构
	private String twoBankName;// 分行/分支机构
	private String threeBankName;// 支行
	private int read;// 是否读取1：未读 ，2：已读
	private int shield;//是否屏蔽  1："未屏蔽" 2：已屏蔽
	private String dealerName;// 经销商名称
	private String brandName;// 品牌名称
	private String bankFullName;// 银行全名
	
	public String getOneBankName() {
		if (StringUtil.isNotEmpty(this.getBankFullName())) {
			String[] bankNames = this.getBankFullName().split("/");
			if (null != bankNames && bankNames.length >= 1) {
				oneBankName = bankNames[0];
				if (bankNames.length == 2) {
					twoBankName = bankNames[1];
				}

				if (bankNames.length >= 3) {
					threeBankName = bankNames[2];
				}

			}
		}
		return oneBankName;
	}
	
	public void setOneBankName(String oneBankName) {
		this.oneBankName = oneBankName;
	}
	public String getTwoBankName() {
		return twoBankName;
	}
	public void setTwoBankName(String twoBankName) {
		this.twoBankName = twoBankName;
	}
	public String getThreeBankName() {
		return threeBankName;
	}
	public void setThreeBankName(String threeBankName) {
		this.threeBankName = threeBankName;
	}
	public int getRead() {
		return read;
	}
	public void setRead(int read) {
		this.read = read;
	}
	public int getShield() {
		return shield;
	}
	public void setShield(int shield) {
		this.shield = shield;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBankFullName() {
		return bankFullName;
	}

	public void setBankFullName(String bankFullName) {
		this.bankFullName = bankFullName;
	}
	
	
}
