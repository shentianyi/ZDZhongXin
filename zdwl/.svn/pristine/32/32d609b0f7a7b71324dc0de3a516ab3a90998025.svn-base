package com.zd.csms.planandreport.web.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.zd.csms.planandreport.model.VideoPlanQueryVO;
import com.zd.csms.planandreport.model.VideoReportItemVO;
import com.zd.csms.planandreport.model.VideoReportQuestionVO;
import com.zd.csms.planandreport.model.VideoReportVO;

public class VideoReportForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6971671530810813787L;
	private VideoReportVO videoReport = new VideoReportVO();
	private VideoReportItemVO reportItem = new VideoReportItemVO();
	private VideoPlanQueryVO query = new VideoPlanQueryVO();
	private List<VideoReportQuestionVO> vrqList = new ArrayList<VideoReportQuestionVO>();
	private int reportStatus;// 保存,还是提交
	
	public int getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(int reportStatus) {
		this.reportStatus = reportStatus;
	}

	public VideoPlanQueryVO getQuery() {
		return query;
	}

	public void setQuery(VideoPlanQueryVO query) {
		this.query = query;
	}

	public VideoReportVO getVideoReport() {
		return videoReport;
	}

	public void setVideoReport(VideoReportVO videoReport) {
		this.videoReport = videoReport;
	}

	public VideoReportItemVO getReportItem() {
		return reportItem;
	}

	public void setReportItem(VideoReportItemVO reportItem) {
		this.reportItem = reportItem;
	}

	public List<VideoReportQuestionVO> getVrqList() {
		return vrqList;
	}

	public void setVrqList(List<VideoReportQuestionVO> vrqList) {
		this.vrqList = vrqList;
	}
	
	

}
