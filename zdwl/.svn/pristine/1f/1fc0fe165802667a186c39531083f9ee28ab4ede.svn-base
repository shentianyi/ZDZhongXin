package com.zd.csms.planandreport.dao;

import java.util.List;
import java.util.Map;
import com.zd.core.IDAO;
import com.zd.csms.planandreport.model.ReportBaseInfoBean;
import com.zd.csms.planandreport.model.VideoPlanQueryVO;
import com.zd.csms.planandreport.model.VideoReportQuestionVO;
import com.zd.csms.planandreport.model.VideoReportVO;
import com.zd.csms.planandreport.querybean.VideoPlanQueryBean;
import com.zd.tools.thumbPage.IThumbPageTools;

//视频检查报告
public interface IVideoReportDAO extends IDAO {
	/**
	 * 根据经销商查询具体的报告表基本信息
	 * 
	 * @param dealer
	 * @return ReportBaseInfoBean
	 */
	public ReportBaseInfoBean findBaseInfoByDealerId(int dealerId);

	public boolean updateReport(VideoReportVO report);

	// 视频检查报告列表
	public List<VideoPlanQueryBean> findReportList(VideoPlanQueryVO query,
			IThumbPageTools tools);

	// 视频检查报告台账
	public List<VideoPlanQueryBean> findReportLedger(VideoPlanQueryVO query,
			IThumbPageTools tools);
	// 视频检查报告台账-计划次数
	public Map<Integer,Integer> findPlanCount(String dealerIds);
	// 导出视频检查报告台账
	public List<VideoPlanQueryBean> ExtReportLedger(VideoPlanQueryVO query);

	public boolean addVideoReportQuestionVO(List<VideoReportQuestionVO> vrqList);
	
	public List<VideoReportQuestionVO> findVideoReportQuestionVOByVrid(int vr_id);

	public int findReportQyestuibCountById(int id);
	
}
