package com.zd.csms.zxbank.bean;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;
/**
 * 解除质押通知书
 * @author duyong
 */
@table(name="zx_removepledge")
public class RemovePledge implements Serializable{

	private static final long serialVersionUID = -4016128896242148226L;

	private int rpId;//序列号 主键id
	private String rpNo;//通知书编号
	private String rpOperorg;//经办行
	private String rpPldegeename;//出质人名称
	private String rpLoncpid;//借款企业id
	private String rpLoncpname;//借款企业名称
	private String rpSupervisename;//监管企业名称
	private String rpCorename;//核心企业名称
	private String rpRelievepddate;//解除质押日期
	private String rpContent;//出库原因
	private String rpNoticedate;//通知书日期
	private Date rpCreatedate;//数据创建时间
	private Date rpUpdatedate;//数据更新时间
	public int getRpId() {
		return rpId;
	}
	public void setRpId(int rpId) {
		this.rpId = rpId;
	}
	public String getRpNo() {
		return rpNo;
	}
	public void setRpNo(String rpNo) {
		this.rpNo = rpNo;
	}
	public String getRpOperorg() {
		return rpOperorg;
	}
	public void setRpOperorg(String rpOperorg) {
		this.rpOperorg = rpOperorg;
	}
	public String getRpPldegeename() {
		return rpPldegeename;
	}
	public void setRpPldegeename(String rpPldegeename) {
		this.rpPldegeename = rpPldegeename;
	}
	public String getRpLoncpid() {
		return rpLoncpid;
	}
	public void setRpLoncpid(String rpLoncpid) {
		this.rpLoncpid = rpLoncpid;
	}
	public String getRpLoncpname() {
		return rpLoncpname;
	}
	public void setRpLoncpname(String rpLoncpname) {
		this.rpLoncpname = rpLoncpname;
	}
	public String getRpSupervisename() {
		return rpSupervisename;
	}
	public void setRpSupervisename(String rpSupervisename) {
		this.rpSupervisename = rpSupervisename;
	}
	public String getRpCorename() {
		return rpCorename;
	}
	public void setRpCorename(String rpCorename) {
		this.rpCorename = rpCorename;
	}
	
	
	public String getRpRelievepddate() {
		return rpRelievepddate;
	}
	public void setRpRelievepddate(String rpRelievepddate) {
		this.rpRelievepddate = rpRelievepddate;
	}
	public String getRpContent() {
		return rpContent;
	}
	public void setRpContent(String rpContent) {
		this.rpContent = rpContent;
	}
	public String getRpNoticedate() {
		return rpNoticedate;
	}
	public void setRpNoticedate(String rpNoticedate) {
		this.rpNoticedate = rpNoticedate;
	}
	public Date getRpCreatedate() {
		return rpCreatedate;
	}
	public void setRpCreatedate(Date rpCreatedate) {
		this.rpCreatedate = rpCreatedate;
	}
	public Date getRpUpdatedate() {
		return rpUpdatedate;
	}
	public void setRpUpdatedate(Date rpUpdatedate) {
		this.rpUpdatedate = rpUpdatedate;
	}
	
	@Override
	public String toString() {
		return "RemovePledge [rpId=" + rpId + ", rpNo=" + rpNo + ", rpOperorg=" + rpOperorg + ", rpPldegeename=" + rpPldegeename + ", rpLoncpid="
				+ rpLoncpid + ", rpLoncpname=" + rpLoncpname + ", rpSupervisename=" + rpSupervisename + ", rpCorename=" + rpCorename
				+ ", rpRelievepddate=" + rpRelievepddate + ", rpContent=" + rpContent + ", rpNoticedate=" + rpNoticedate + ", rpCreatedate="
				+ rpCreatedate + ", rpUpdatedate=" + rpUpdatedate + "]";
	}
	
}
