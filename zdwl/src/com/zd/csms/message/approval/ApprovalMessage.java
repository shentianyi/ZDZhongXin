package com.zd.csms.message.approval;

/**
 * 审批消息类
 * @author licheng
 *
 */
public class ApprovalMessage {
	private Class<?> type;//审批类型
	private String url;//跳转地址
	private int count;//未审批数量
	private String name;//需要审批的任务名称，用于展示
	public Class<?> getType() {
		return type;
	}
	public void setType(Class<?> type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ApprovalMessage(Class<?> type, String url, String name) {
		super();
		this.type = type;
		this.url = url;
		this.name = name;
	}
	
	
	
}
