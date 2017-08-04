package com.zd.csms.messageAndWaring.message.queryBean.market;

import com.zd.csms.messageAndWaring.message.model.market.MarketWaringInfoVO;
import com.zd.tools.StringUtil;
/**
 * 信息预警：查询结果实体
 * @author zhangzhicheng
 *
 */
public class MarketWaringQueryBean extends MarketWaringInfoVO {
	private static final long serialVersionUID = 4130339431860966130L;
	private String oneBankName;// 总行/金融机构
	private String twoBankName;// 分行/分支机构
	private String threeBankName;// 支行
	private int read;// 是否读取1：未读 ，2：已读
	private int shield;//是否屏蔽  1："未屏蔽" 2：已屏蔽
	
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

	public String getTwoBankName() {
		return twoBankName;
	}

	public String getThreeBankName() {
		return threeBankName;
	}

	public int getRead() {
		return read;
	}

	public void setRead(int read) {
		this.read = read;
	}


	public void setOneBankName(String oneBankName) {
		this.oneBankName = oneBankName;
	}

	public void setTwoBankName(String twoBankName) {
		this.twoBankName = twoBankName;
	}

	public void setThreeBankName(String threeBankName) {
		this.threeBankName = threeBankName;
	}

	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}

}
