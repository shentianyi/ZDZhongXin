package com.zd.csms.messageAndWaring.message.queryBean.market;

import com.zd.csms.messageAndWaring.message.model.market.MarketMessageInfoVO;
import com.zd.tools.StringUtil;

/**
 * 信息提醒：查询结果实体
 * @author zhangzhicheng
 *
 */
public class MarketMessageQueryBean extends MarketMessageInfoVO {

	private static final long serialVersionUID = 3721339023893639972L;
	private String oneBankName;// 总行/金融机构
	private String twoBankName;// 分行/分支机构
	private String threeBankName;// 支行
	private int read;// 是否读取1：未读 ，2：已读
	

	public String getOneBankName() {
		if (StringUtil.isNotEmpty(this.getBankFullName())) {
			String[] bankNames = this.getBankFullName().split("/");
			if (null != bankNames && bankNames.length >= 1) {
				oneBankName = bankNames[0];
				if (bankNames.length >1) {
					twoBankName = bankNames[1];
				}

				if (bankNames.length >2) {
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


}
