package com.zd.csms.file.model;

import java.util.Date;

public class UploadfileQueryVO {

	private int id;
	private String file_name;//附件名称
	private String file_type;//附件类型
	private String file_path;//附件路径
	private Date createtime;//上传时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String fileName) {
		file_name = fileName;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String fileType) {
		file_type = fileType;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String filePath) {
		file_path = filePath;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
