package com.zd.csms.messagequartz.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_msg_billnocar")
public class MsgBillNoCarVO implements Serializable {

	/**
	 *  开票10个工作日未到车提醒、开票30天汇票未押满    - 对象  
	 */
	private static final long serialVersionUID = 1179039586360905048L;
	private int id;//主键id
	private String draft_num;//汇票号
	private Date billing_date;//开票日
	private Date due_date;//到期日
	private Double billing_money;//开票金额
	private Double nomortgagecar_money;//未押车金额
	private Integer type;//消息类别
	private Date createDate;//创建时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDraft_num() {
		return draft_num;
	}
	public void setDraft_num(String draft_num) {
		this.draft_num = draft_num;
	}
	public Date getBilling_date() {
		return billing_date;
	}
	public void setBilling_date(Date billing_date) {
		this.billing_date = billing_date;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	public Double getBilling_money() {
		return billing_money;
	}
	public void setBilling_money(Double billing_money) {
		this.billing_money = billing_money;
	}
	public Double getNomortgagecar_money() {
		return nomortgagecar_money;
	}
	public void setNomortgagecar_money(Double nomortgagecar_money) {
		this.nomortgagecar_money = nomortgagecar_money;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
