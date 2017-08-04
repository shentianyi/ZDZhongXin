package com.zd.csms.business.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_flow")
public class FlowVO implements Serializable {

	/**
	 * 操作流程
	 */
	private static final long serialVersionUID = -8762255558772028859L;

	private int id;
	private String flowname;//流程名称
	private String content;
	private int uploadid;//上传人id
	private Date createtime;//创建时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFlowname() {
		return flowname;
	}
	public void setFlowname(String flowname) {
		this.flowname = flowname;
	}
	public int getUploadid() {
		return uploadid;
	}
	public void setUploadid(int uploadid) {
		this.uploadid = uploadid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
