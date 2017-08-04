package com.zd.csms.message.qrtz;

import com.zd.csms.marketing.warning.Warring;
import com.zd.csms.message.dbsy.Billing15nocar;
import com.zd.csms.message.dbsy.Billing30nofull;
import com.zd.csms.message.dbsy.Billing7nocar;
import com.zd.csms.message.dbsy.Continuity3out;
import com.zd.csms.message.dbsy.Expire7day;
import com.zd.csms.message.dbsy.Expirenotclear;
import com.zd.csms.message.dbsy.Expiretheday;
import com.zd.csms.message.dbsy.Move30day;
import com.zd.csms.message.dbsy.NostockNodraft;
import com.zd.csms.message.dbsy.Nowork3day;

/**
 * 
 * @author licheng
 *
 */
public class MessageQrtz {
	public void run(){
		Warring warring = new Warring();
		//协议到期提醒和预警
		try {
			warring.agreementExpires();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//协议邮寄30天未回收预警 30天
		try {
			warring.agreementOverdue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//协议到期未续签
		try {
			warring.daoqi();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		//小组长区域
		try {
			//汇票开票15个工作日未到车
			Billing15nocar.billing15();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Billing30nofull.billing30nofull();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Billing7nocar.billing7();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Continuity3out.continuity3out();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Expire7day.expire7day();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Expirenotclear.expirenotclear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Expiretheday.expiretheday();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Move30day.move30day();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			NostockNodraft.nostocknodraft();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Nowork3day.nowork3day();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
