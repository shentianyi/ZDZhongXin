package com.zd.csms.marketing.querybean;

import com.zd.csms.marketing.model.DealerAVO;
import com.zd.csms.marketing.model.DealerVO;

public class DealerBusinessQueryBean extends DealerVO {
	private String draft_way;// 汇票下发方式
	private String guard_way;// 押车方式
	private String certificate_delivery;// 合格证送达方式
	private String actual_supervision;// 实际监管模式
	private String key_supervise;// 钥匙监管
	private String website;// 二网
	private String old_car;// 二库
	private String special_oper;// 特殊操作
	private String moveperc; //监管物移动百分比


	public String getDraft_way() {
		return draft_way;
	}

	public void setDraft_way(String draft_way) {
		this.draft_way = draft_way;
	}

	public String getGuard_way() {
		return guard_way;
	}

	public void setGuard_way(String guard_way) {
		this.guard_way = guard_way;
	}

	public String getCertificate_delivery() {
		return certificate_delivery;
	}

	public void setCertificate_delivery(String certificate_delivery) {
		this.certificate_delivery = certificate_delivery;
	}

	public String getActual_supervision() {
		return actual_supervision;
	}

	public void setActual_supervision(String actual_supervision) {
		this.actual_supervision = actual_supervision;
	}

	public String getKey_supervise() {
		return key_supervise;
	}

	public void setKey_supervise(String key_supervise) {
		this.key_supervise = key_supervise;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getOld_car() {
		return old_car;
	}

	public void setOld_car(String old_car) {
		this.old_car = old_car;
	}

	public String getSpecial_oper() {
		return special_oper;
	}

	public void setSpecial_oper(String special_oper) {
		this.special_oper = special_oper;
	}

	public String getMoveperc() {
		return moveperc;
	}

	public void setMoveperc(String moveperc) {
		this.moveperc = moveperc;
	}
	

}
