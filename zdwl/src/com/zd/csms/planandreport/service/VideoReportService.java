package com.zd.csms.planandreport.service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.contants.MarketProjectCirculationContant;
import com.zd.csms.planandreport.dao.IVideoReportDAO;
import com.zd.csms.planandreport.dao.PlanAndReportDAOFactory;
import com.zd.csms.planandreport.model.ReportBaseInfoBean;
import com.zd.csms.planandreport.model.VideoPlanQueryVO;
import com.zd.csms.planandreport.model.VideoReportItemVO;
import com.zd.csms.planandreport.model.VideoReportQuestionVO;
import com.zd.csms.planandreport.model.VideoReportVO;
import com.zd.csms.planandreport.querybean.VideoPlanQueryBean;
import com.zd.csms.planandreport.web.form.VideoReportForm;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;


public class VideoReportService extends ServiceSupport {
	private IVideoReportDAO dao = PlanAndReportDAOFactory.getVideoReportDAO();
	private VideoPlanService planService = new VideoPlanService();

	/**
	 * 鏍规嵁缁忛攢鍟嗘煡璇㈠叿浣撶殑鎶ュ憡琛ㄥ熀鏈俊鎭�
	 * 
	 * @param dealer
	 * @return ReportBaseInfoBean
	 */
	public ReportBaseInfoBean findBaseInfoByDealerId(int dealerId) {
		ReportBaseInfoBean queryBean = dao.findBaseInfoByDealerId(dealerId);
		if (queryBean == null) {
			return null;
		}
		queryBean.setCertificationNum(queryBean.getStockNum());
		//灏嗙洃绠℃ā寮忕敱鏁板瓧鍙樻垚姹夊瓧
				String supervisionMode = queryBean.getSupervisionMode();
				if(supervisionMode!=null){
					StringBuffer supervisionModeStr = new StringBuffer();
					if(queryBean.getSupervisionMode().contains(MarketProjectCirculationContant.SUPERVISIONMODE_CAR.getCode()+""))
						supervisionModeStr.append(MarketProjectCirculationContant.SUPERVISIONMODE_CAR.getName()+",");
					if(queryBean.getSupervisionMode().contains(MarketProjectCirculationContant.SUPERVISIONMODE_PASS.getCode()+""))
							supervisionModeStr.append(MarketProjectCirculationContant.SUPERVISIONMODE_PASS.getName()+",");
					if(queryBean.getSupervisionMode().contains(MarketProjectCirculationContant.HDXK.getCode()+""))
						supervisionModeStr.append(MarketProjectCirculationContant.HDXK.getName()+",");
					if(queryBean.getSupervisionMode().contains(MarketProjectCirculationContant.JRJGK.getCode()+""))
						supervisionModeStr.append(MarketProjectCirculationContant.JRJGK.getName()+",");
					if(supervisionModeStr.length()>0)
						supervisionModeStr.deleteCharAt(supervisionModeStr.length()-1);
					queryBean.setSupervisionMode(supervisionModeStr.toString());	
				}
		return queryBean;
	}

	public boolean addReport(VideoReportForm videoReportform,
			HttpServletRequest request) {
		boolean fag = false;
		try {
			UserSession userSession = UserSessionUtil.getUserSession(request);
			VideoReportVO report = videoReportform.getVideoReport();
			report.setCheck_name(userSession.getUser().getUserName());
			if(report.getCheck_time() == null){
				report.setCheck_time(new Date());
			}
			fag = dao.add(report);
			if (fag) {
			/*	if (report.getSkip_fag()<=0) {
					videoReportform.getReportItem().setId(report.getId());
					fag = dao.add(videoReportform.getReportItem());
				}*/
				
				if (report.getSkip_fag()<=0) {
					for (VideoReportQuestionVO vo : videoReportform.getVrqList()) {
						vo.setId(report.getId());
					}
					fag = dao.addVideoReportQuestionVO(videoReportform.getVrqList());
				}

				if (fag) {
					fag = planService.updateStatus(videoReportform.getReportStatus(), report.getId());
				}
			}
		} catch (Exception e) {
			fag = false;
			e.printStackTrace();
		}
		return fag;

	}

	public boolean updateReport(VideoReportForm videoReportform,
			HttpServletRequest request) {
		boolean fag = false;
		try {
			UserSession userSession = UserSessionUtil.getUserSession(request);
			VideoReportVO report = videoReportform.getVideoReport();
			fag = dao.updateReport(report);
			//int count = dao.findReportQyestuibCountById(report.getId());
			if(fag){
				dao.delete(VideoReportQuestionVO.class, report.getId());
				if(report.getSkip_fag()<=0){
					for (VideoReportQuestionVO vo : videoReportform.getVrqList()) {
						vo.setId(report.getId());
					}
					fag = dao.addVideoReportQuestionVO(videoReportform.getVrqList());
				}
			}
			
			
			/*if (fag) {
				if (report.getSkip_fag()<=0) {
					if (videoReportform.getReportItem().getId() > 0) {
						fag = dao.update(videoReportform.getReportItem());
					} else {
						videoReportform.getReportItem().setId(report.getId());
						fag = dao.add(videoReportform.getReportItem());
					}

				} else{
					VideoReportItemVO vo = new VideoReportItemVO();
					vo.setId(report.getId());
					dao.update(vo);
				}
				
			}*/
		} catch (Exception e) {
			fag = false;
			e.printStackTrace();
		}
		return fag;

	}

	public VideoReportVO getVideoReportByPlanId(int id) {
		return dao.get(VideoReportVO.class, id, new BeanPropertyRowMapper(
				VideoReportVO.class));
	}

	public VideoReportItemVO getReportItemByPlanId(int id) {
		return dao.get(VideoReportItemVO.class, id, new BeanPropertyRowMapper(
				VideoReportItemVO.class));
	}

	// 瑙嗛妫�煡鎶ュ憡鍒楄〃
	public List<VideoPlanQueryBean> findReportList(VideoReportForm queryForm,
			HttpServletRequest request) {
		VideoPlanQueryVO query = queryForm.getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("pagelist",
				request);

		tools.saveQueryVO(query);

		List<VideoPlanQueryBean> videoPlanQueryBeans = dao.findReportList(
				query, tools);

		return videoPlanQueryBeans;
	}

	// 瑙嗛妫�煡鎶ュ憡鍙拌处
	public List<VideoPlanQueryBean> findReportLedger(VideoReportForm videoPlanForm,HttpServletRequest request) {
		VideoPlanQueryVO query = videoPlanForm.getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("pagelist",request);
		tools.saveQueryVO(query);
		List<VideoPlanQueryBean> queryBeans = dao.findReportLedger(query, tools);
		
		if (queryBeans != null && queryBeans.size() > 0) {
			Set<Integer> dealerIds = new HashSet<Integer>();
			for (VideoPlanQueryBean queyBean : queryBeans) {
				if (queyBean.getDealerId() != null) {
					dealerIds.add(queyBean.getDealerId());
				}
			}
			Map<Integer, Integer> planCountMap=new HashMap<Integer, Integer>();
			if(dealerIds.size()>0){
			   planCountMap = dao.findPlanCount(dealerIds.toString());
					 
			 }
			request.setAttribute("planCountMap", planCountMap);	
		}
		
		return queryBeans;
	}
	public boolean submitReport(VideoReportForm videoReportform,
			HttpServletRequest request) {

		if (getVideoReportByPlanId(videoReportform.getVideoReport().getId()) != null) {
			if(updateReport(videoReportform, request)){
				 return planService.updateStatus(videoReportform.getReportStatus(),videoReportform.getVideoReport().getId());
			}
			
		}
		
		int id = videoReportform.getVideoReport().getId();
		VideoReportVO vo = getVideoReportByPlanId(id);
		if(vo!=null){
			return updateReport(videoReportform, request);
		}else{
			return addReport(videoReportform, request);
		}
	}

	/*
	 * 闇�眰38
	 * 瀵煎嚭瑙嗛妫�煡鎶ュ憡鍙拌处
	*/
	public List<VideoPlanQueryBean> ExtReportLedger(VideoReportForm videoPlanForm, HttpServletRequest request) {
		VideoPlanQueryVO query = videoPlanForm.getQuery();
		List<VideoPlanQueryBean> queryBeans = dao.ExtReportLedger(query);
		/*
		if (queryBeans != null && queryBeans.size() > 0) {
			Set<Integer> dealerIds = new HashSet<Integer>();
			for (VideoPlanQueryBean queyBean : queryBeans) {
				if (queyBean.getDealerId() != null) {
					dealerIds.add(queyBean.getDealerId());
				}
			}
			Map<Integer, Integer> planCountMap=new HashMap<Integer, Integer>();
			if(dealerIds.size()>0){
			   planCountMap = dao.findPlanCount(dealerIds.toString());
					 
			 }
			request.setAttribute("planCountMap", planCountMap);
		}*/
		
		return queryBeans;
	}

	public boolean mergeQuestion(int type, String[] question_classify, String[] question_desc,String[] depart, List<VideoReportQuestionVO> vrqList) {
		VideoReportQuestionVO vo = null;
		if(depart==null || question_desc == null || question_classify == null){
			return false;
		}
		for(int i = 0; i < depart.length;i++){
			if(question_desc[i]!=null && !"".equals(question_desc[i].trim())){
				vo = new VideoReportQuestionVO();
				vo.setType(type);
				if(question_classify[i] != null){
					vo.setQuestion_classify(Integer.parseInt(question_classify[i]));
				}
				vo.setQuestion_desc(question_desc[i]);
				if(depart[i]!=null){
					vo.setDepart(Integer.parseInt(depart[i]));
				}
				vrqList.add(vo);
			}
		}
		return true;
	}

	public List<VideoReportQuestionVO> findVideoReportQuestionVOByVrid(int id) {
		return dao.findVideoReportQuestionVOByVrid(id);
	}   

}
