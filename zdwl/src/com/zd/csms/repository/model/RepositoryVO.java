package com.zd.csms.repository.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;
@table(name="T_REPOSITORY")
public class RepositoryVO implements Serializable {
	
	private static final long serialVersionUID = 6348857295726046280L;
	/**
	 * 主键ID
	 */
	private int id;                     
	/**
	 * 状态：1：已上岗，2：有效，3：无效
	 */
	private int status;                 
	/**
	 * 原因：1：撤店，2：放弃（已有新工作），3：放弃（工资问题），4：辞退，5：辞职
	 */
    private int reason;                 
    /**
	 * 监管员ID
	 */
	private int supervisorId;           
	/**
	 * 面试人
	 */
	private String interviewee;         
	/**
	 * 面试评分
	 */
	private double interviewScore;     
	/**
	 * 面试点评
	 */
	private String interviewComment;   
	/**
	 * 属性
	 */
	private String attribute;          
	/**
	 * 籍贯（省）
	 */
	private String nativePlaceProvince;
	/**
	 * 籍贯（市）
	 */
	private String nativePlaceCity;    
	/**
	 * 籍贯（区/县）
	 */
	private String nativePlaceCounty;  
	/**
	 * 现住址（省）
	 */
	private String liveAddressProvince;
	/**
	 * 现住址（市）
	 */
	private String liveAddressCity;    
	/**
	 * 现住址（区/县）
	 */
	private String liveAddressCounty;
	/**
	 * 创建人
	 */
	private int createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后修改人
	 */
	private int lastModifyUser;
	/**
	 * 最后修改时间
	 */
	private Date lastModifyTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getReason() {
		return reason;
	}
	public void setReason(int reason) {
		this.reason = reason;
	}
	public String getInterviewee() {
		return interviewee;
	}
	public void setInterviewee(String interviewee) {
		this.interviewee = interviewee;
	}
	public int getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}
	public double getInterviewScore() {
		return interviewScore;
	}
	public void setInterviewScore(double interviewScore) {
		this.interviewScore = interviewScore;
	}
	public String getInterviewComment() {
		return interviewComment;
	}
	public void setInterviewComment(String interviewComment) {
		this.interviewComment = interviewComment;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getNativePlaceProvince() {
		return nativePlaceProvince;
	}
	public void setNativePlaceProvince(String nativePlaceProvince) {
		this.nativePlaceProvince = nativePlaceProvince;
	}
	public String getNativePlaceCity() {
		return nativePlaceCity;
	}
	public void setNativePlaceCity(String nativePlaceCity) {
		this.nativePlaceCity = nativePlaceCity;
	}
	public String getNativePlaceCounty() {
		return nativePlaceCounty;
	}
	public void setNativePlaceCounty(String nativePlaceCounty) {
		this.nativePlaceCounty = nativePlaceCounty;
	}
	public String getLiveAddressProvince() {
		return liveAddressProvince;
	}
	public void setLiveAddressProvince(String liveAddressProvince) {
		this.liveAddressProvince = liveAddressProvince;
	}
	public String getLiveAddressCity() {
		return liveAddressCity;
	}
	public void setLiveAddressCity(String liveAddressCity) {
		this.liveAddressCity = liveAddressCity;
	}
	public String getLiveAddressCounty() {
		return liveAddressCounty;
	}
	public void setLiveAddressCounty(String liveAddressCounty) {
		this.liveAddressCounty = liveAddressCounty;
	}
	public int getCreateUser() {
		return createUser;
	}
	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getLastModifyUser() {
		return lastModifyUser;
	}
	public void setLastModifyUser(int lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	
}
