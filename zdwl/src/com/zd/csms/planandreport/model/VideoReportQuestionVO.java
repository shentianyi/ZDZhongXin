package com.zd.csms.planandreport.model;

import java.io.Serializable;

import com.zd.core.annotation.table;

@table(name = "t_video_report_question")
public class VideoReportQuestionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8944601693923703552L;
	  int id;	////视频检查计划 id
	  
	  int type;		//问题类型
	  
	  int question_classify;	//问题分类
	  
	  String question_desc;		//问题描述
	  
	  int depart;				//归属部门	ClientTypeConstants



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getQuestion_classify() {
		return question_classify;
	}

	public void setQuestion_classify(int question_classify) {
		this.question_classify = question_classify;
	}

	public String getQuestion_desc() {
		return question_desc;
	}

	public void setQuestion_desc(String question_desc) {
		this.question_desc = question_desc;
	}

	public int getDepart() {
		return depart;
	}

	public void setDepart(int depart) {
		this.depart = depart;
	}
	  
	  
	  

}
