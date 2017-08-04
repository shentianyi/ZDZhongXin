package com.zd.csms.dbsy.model;

import java.io.Serializable;


public class DbsyVO implements Serializable {
	
	private static final long serialVersionUID = 6193321541195591611L;
	
	private int workType;	//工作类型
	private String des;		//工作描述
	private String url;		//连接地址
	private int num;		//本业务等待办理的数量
	private int level;		//紧急程度
	
	public int getWorkType(){
		return workType;
	}
	
	public void setWorkType(int workType){
		this.workType = workType;
	}
	
	public String getDes(){
		return des;
	}
	
	public void setDes(String des){
		this.des = des;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}

	public int getNum(){
		return num;
	}
	
	public void setNum(int num){
		this.num = num;
	}
	
	public int getLevel(){
		return level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
}
